package com.cardpay.pccredit.riskControl.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cardpay.pccredit.common.ConnUtils;
import com.cardpay.pccredit.customer.service.CustomerInforService;
import com.cardpay.pccredit.riskControl.constant.RiskCustomerStatusEnum;
import com.cardpay.pccredit.riskControl.dao.RiskCustomerDao;
import com.cardpay.pccredit.riskControl.filter.RiskCustomerFilter;
import com.cardpay.pccredit.riskControl.model.RiskCustomer;
import com.wicresoft.jrad.base.database.dao.common.CommonDao;
import com.wicresoft.jrad.base.database.model.QueryResult;

/**
 * @author chenzhifang
 *
 * 2014-10-24下午5:51:18
 */
@Service
public class RiskCustomerService {
	
	@Autowired
	private CommonDao commonDao;
	
	@Autowired
	private RiskCustomerDao riskCustomerDao;
	
	@Autowired
	private RiskAttributeService riskAttributeService;
	
	@Autowired
	private CustomerInforService customerInforService;
	
	/**
	 * 过滤查询
	 * @param filter
	 * @return
	 */
	public QueryResult<RiskCustomer> findRiskCustomersByFilter(RiskCustomerFilter filter) {
		//return commonDao.findObjectsByFilter(RiskCustomer.class, filter);
		List<RiskCustomer> riskCustomers = riskCustomerDao.findRiskCustomersByFilter(filter);
		int size = riskCustomerDao.findRiskCustomersCountByFilter(filter);
		QueryResult<RiskCustomer> qs = new QueryResult<RiskCustomer>(size, riskCustomers);
		return qs;
	}
	
	/**
	 * 过滤查询客户风险等级
	 * @param filter
	 * @return
	 */
	public QueryResult<RiskCustomer> findRiskListByFilter(RiskCustomerFilter filter) {
		List<RiskCustomer> riskCustomers = riskCustomerDao.findRiskListByFilter(filter);
		int size = riskCustomerDao.findRiskListCountByFilter(filter);
		QueryResult<RiskCustomer> qs = new QueryResult<RiskCustomer>(size, riskCustomers);
		return qs;
	}
	
	/**
	 * 过滤查询(不分页)
	 * @param filter
	 * @return
	 */
	public List<RiskCustomer> findRiskCustomerListByFilter(RiskCustomerFilter filter){
		return riskCustomerDao.findRiskCustomersByFilter(filter);
	}
	
	
	/**
	 * 过滤查询(不分页)根据customerId
	 * @param filter
	 * @return
	 */
	public List<RiskCustomer> findRiskCustomerBycustomerId(String customerId){
		return riskCustomerDao.findRiskCustomerBycustomerId(customerId);
	}
	
	/**
	 * 更新风险客户
	 * @param riskCustomer
	 * @return
	 */
	public int updateRiskCustomer(RiskCustomer riskCustomer) {
		riskCustomer.setModifiedTime(Calendar.getInstance().getTime());
		
		return commonDao.updateObject(riskCustomer);
	}
	
	/**
	 * 插入风险客户
	 * @param riskCustomer
	 * @return
	 */
	public int insertRiskCustomer(RiskCustomer riskCustomer) {
		riskCustomer.setCreatedTime(Calendar.getInstance().getTime());
		riskCustomer.setModifiedTime(Calendar.getInstance().getTime());
		return commonDao.insertObject(riskCustomer);
	}

	/**
	 * 删除风险客户
	 * @param riskCustomerId
	 * @return
	 */
	public int deleteRiskCustomer(String riskCustomerId) {
		return commonDao.deleteObject(RiskCustomer.class, riskCustomerId);
	}

	/**
	 * 通过ID查找风险客户
	 * @param filter
	 * @return
	 */
	public RiskCustomer findRiskCustomerById(String riskCustomerId) {
		return commonDao.findObjectById(RiskCustomer.class, riskCustomerId);
	}
	
	/**
	 * 上报风险客户
	 * @param filter
	 * @return
	 */
	public boolean reportRiskCustomer(String riskCustomerId){
		boolean flag = false;
		try{
			RiskCustomer riskCustomer = findRiskCustomerById(riskCustomerId);
			
			riskCustomer.setStatus(RiskCustomerStatusEnum.getReportedNextStatusByCurrent(riskCustomer.getStatus()));
			// 更新风险客户
			updateRiskCustomer(riskCustomer);
			flag = true;
		} catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 确认风险客户
	 * @param filter
	 * @return
	 */
	public boolean confirmedRiskCustomer(String riskCustomerId){
		boolean flag = false;
		try{
			RiskCustomer riskCustomer = findRiskCustomerById(riskCustomerId);
			riskCustomer.setStatus(RiskCustomerStatusEnum.getConfirmedNextStatusByCurrent(riskCustomer.getStatus()));
			// 更新风险客户
			updateRiskCustomer(riskCustomer);
			flag = true;
		} catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	
	/*
	 * 判断是否在风险名单中
	 */
	public boolean isInBlacklist(RiskCustomerFilter riskCustomerFilter){
		boolean flag = false;
		try{
			QueryResult<RiskCustomer> qr = commonDao.findObjectsByFilter(RiskCustomer.class, riskCustomerFilter);
			flag = qr.getItems().isEmpty() ? false : true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 拒绝风险客户
	 * @param filter
	 * @return
	 */
	public boolean rejectRiskCustomer(RiskCustomer rc){
		boolean flag = false;
		try{
			RiskCustomer riskCustomer = findRiskCustomerById(rc.getId());
			riskCustomer.setRefuseReason(rc.getRefuseReason());
			riskCustomer.setStatus(RiskCustomerStatusEnum.getRejectNextStatusByCurrent(riskCustomer.getStatus()));
			// 更新风险客户
			updateRiskCustomer(riskCustomer);
			flag = true;
		} catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 通过cardId查询风险客户
	 * @param cardId
	 * @return
	 */
	public int findRiskByCardId(String cardId){
		return riskCustomerDao.findRiskByCardId(cardId);
	}
	
	/**
	 * 通过cardId查询产品信息
	 * @param cardId
	 * @return
	 */
	public String findProductByCardId(String cardId){
		Connection conn = null;
        CallableStatement statement = null;
        String sname="";
//        String sql = "{call stu_test(?, ?)}";
      String sql = "{call ods.xmnx_qk_pkg.xmnx_idens_info_view(?, ?)}";
        try {
            conn = ConnUtils.getConnection();
            statement = conn.prepareCall(sql);
            statement.setString(1, cardId);
            statement.registerOutParameter(2, Types.VARCHAR);
            statement.execute();
            sname = statement.getString(2);
            System.out.println("身份id"+cardId+"---行内产品返回："+sname);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            ConnUtils.free(null, statement, conn);
        }
        return sname;
	}
}
