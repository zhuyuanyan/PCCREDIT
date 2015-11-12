package com.cardpay.pccredit.report.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cardpay.pccredit.common.PccOrganizationService;
import com.cardpay.pccredit.customer.service.CustomerInforService;
import com.cardpay.pccredit.datapri.web.FlatTreeNode;
import com.cardpay.pccredit.manager.service.AccountManagerParameterService;
import com.cardpay.pccredit.manager.web.AccountManagerParameterForm;
import com.cardpay.pccredit.report.dao.ProceMonitorDao;
import com.cardpay.pccredit.report.dao.QuailMonitorRejectDao;
import com.cardpay.pccredit.report.dao.QuailMonitorReturnDao;
import com.cardpay.pccredit.report.filter.StatisticalFilter;
import com.cardpay.pccredit.report.model.LCJC;
import com.cardpay.pccredit.report.model.LCJCBank;
import com.cardpay.pccredit.report.model.QuailBankRejectMonitor;
import com.cardpay.pccredit.report.model.QuailBankReturnMonitor;
import com.cardpay.pccredit.report.model.QuailManaRejectMonitor;
import com.cardpay.pccredit.report.model.QuailManaReturnMonitor;
import com.cardpay.pccredit.report.model.bankProceMonitor;
import com.cardpay.pccredit.report.model.manaProceMonitor;
import com.cardpay.pccredit.system.filter.SystemUserFilter;
import com.cardpay.pccredit.system.model.SystemUser;
import com.cardpay.pccredit.system.service.SystemUserService;
import com.wicresoft.jrad.base.database.dao.common.CommonDao;
import com.wicresoft.jrad.base.database.model.QueryResult;
import com.wicresoft.jrad.modules.privilege.constant.PrivilegeConstants;
import com.wicresoft.jrad.modules.privilege.model.User;


/**
 * ProceMonitorService类的描述
 *
 * @author 王海东
 * @created on 2014-12-19
 * 
 * @version $Id:$
 */
@Service
public class ProceMonitorService {
	
	@Autowired
	private ProceMonitorDao proceMonitorDao;
	
	@Autowired
	private QuailMonitorRejectDao quailMonitorRejectDao;
	
	@Autowired
	private QuailMonitorReturnDao quailMonitorReturnDao;
	
	@Autowired
	private AccountManagerParameterService accountManagerParameterService;
	
	@Autowired
	private  PccOrganizationService  pccOrganizationService;
	
	@Autowired
	private  CommonDao  commonDao;
	
	/**
	 * “灵活金”/普通信用卡业务流程监测报表（统计期间：XXXX年XX月XX日-XXXX年XX月XX日，客户经理/微贷经理维度）
	 * @param filter
	 * @return
	 */
	public List<manaProceMonitor> getProceMonitorStatistical(StatisticalFilter filter) {
		List<manaProceMonitor> rtn_ls = new ArrayList<manaProceMonitor>();
		List<LCJC> LCJC_ls = proceMonitorDao.getProceMonitorStatistical(filter);
		//处理数据
//		List<AccountManagerParameterForm> accountManagerParameterForm_ls = accountManagerParameterService.findAccountManagerParameterAll();//所有客户经理
		List<SystemUser> accountManagerParameterForm_ls = commonDao.queryBySql(SystemUser.class, "select s.* from sys_user s where s.user_type='1'", null);
		 for(int i = 0;i < accountManagerParameterForm_ls.size(); i ++){
			manaProceMonitor manaprocemonitor = new manaProceMonitor("0",String.valueOf(i),accountManagerParameterForm_ls.get(i).getDisplayName(),"0","0","0","0","0","0","0",accountManagerParameterForm_ls.get(i).getId());
			for(LCJC lcjc : LCJC_ls){
				if(accountManagerParameterForm_ls.get(i).getId().endsWith(lcjc.getUserid())){
					//填充数据到返回list
					if(lcjc.getSeq_no().equals("1")){//初审
						if(lcjc.getExamine_result() == null){
							manaprocemonitor.setChushen(lcjc.getTotal());
						}
						else if(lcjc.getExamine_result().equals("APPROVE")){
							manaprocemonitor.setChushenapprove(lcjc.getTotal());
						}
					}
					else if(lcjc.getSeq_no().equals("2")){//录入
						if(lcjc.getExamine_result() == null){
							manaprocemonitor.setLuru(lcjc.getTotal());
						}
						else if(lcjc.getExamine_result().equals("APPROVE")){
							manaprocemonitor.setLuruapprove(lcjc.getTotal());
						}
					}
					else if(lcjc.getSeq_no().equals("3")){//复核
						if(lcjc.getExamine_result() == null){
							manaprocemonitor.setFushen(lcjc.getTotal());
						}
						else if(lcjc.getExamine_result().equals("APPROVE")){
							manaprocemonitor.setFushenapprove(lcjc.getTotal());
						}
					}
					manaprocemonitor.setJinjian((Integer.parseInt(manaprocemonitor.getJinjian())+Integer.parseInt(lcjc.getTotal()))+"");
				}
			}
			rtn_ls.add(manaprocemonitor);
		}
		return rtn_ls;
	}

	/**
	 * “灵活金”/信用卡业务流程监测报表（统计期间：XXXX年XX月XX日-XXXX年XX月XX日，一级支行/二级支行维度）
	 * @param filter
	 * @return
	 */
	public List<manaProceMonitor> getBankProceMonitorStatistical(StatisticalFilter filter) {
		List<manaProceMonitor> rtn_ls = new ArrayList<manaProceMonitor>();
		List<LCJCBank> LCJCBank_ls = proceMonitorDao.getBankProceMonitorStatistical(filter);
		//处理数据
		List<FlatTreeNode> results= pccOrganizationService.queryAllOrgTreeList(PrivilegeConstants.INIT_ID);//所有机构
		 for(int i = 0;i < results.size(); i ++){
			manaProceMonitor manaprocemonitor = new manaProceMonitor("0",String.valueOf(i),results.get(i).getName(),"0","0","0","0","0","0","0",results.get(i).getId());
			for(LCJCBank lcjc : LCJCBank_ls){
				if(results.get(i).getId().endsWith(lcjc.getOrg_id())){
					//填充数据到返回list
					if(lcjc.getSeq_no().equals("1")){//初审
						if(lcjc.getExamine_result() == null){
							manaprocemonitor.setChushen(lcjc.getTotal());
						}
						else if(lcjc.getExamine_result().equals("APPROVE")){
							manaprocemonitor.setChushenapprove(lcjc.getTotal());
						}
					}
					else if(lcjc.getSeq_no().equals("2")){//录入
						if(lcjc.getExamine_result() == null){
							manaprocemonitor.setLuru(lcjc.getTotal());
						}
						else if(lcjc.getExamine_result().equals("APPROVE")){
							manaprocemonitor.setLuruapprove(lcjc.getTotal());
						}
					}
					else if(lcjc.getSeq_no().equals("3")){//复核
						if(lcjc.getExamine_result() == null){
							manaprocemonitor.setFushen(lcjc.getTotal());
						}
						else if(lcjc.getExamine_result().equals("APPROVE")){
							manaprocemonitor.setFushenapprove(lcjc.getTotal());
						}
					}
					manaprocemonitor.setJinjian((Integer.parseInt(manaprocemonitor.getJinjian())+Integer.parseInt(lcjc.getTotal()))+"");
				}
				//如果选择了机构（只有一条机构数据）
			}
			//如果没有选择机构（显示所有数据）
			if(filter.getOrgId()==null){
				rtn_ls.add(manaprocemonitor);
			}else{
				if(results.get(i).getId().endsWith(filter.getOrgId())){
					rtn_ls.add(manaprocemonitor);
				}
				
			}
		}
		return rtn_ls;
	}
	
	/**
	 * ReturnApprove
	 * “灵活金”业务/普通信用卡进件质量监测报表统计期间：（XXXX年XX月XX日-XXXX年XX月XX日） 客户经理/微贷经理维度
	 * @param filter
	 * @return
	 */
	public List<QuailManaReturnMonitor> getQuailReturnMonitorStatistical(StatisticalFilter filter) {
		return quailMonitorReturnDao.getQuailReturnMonitorStatistical(filter);
	}
	/**
	 * ReturnApprove
	 * “灵活金”业务/普通信用卡进件质量监测报表统计期间：（XXXX年XX月XX日-XXXX年XX月XX日）一级支行/二级支行维度
	 * @param filter
	 * @return
	 */
	public List<QuailBankReturnMonitor> getQuailBankReturnMonitorStatistical(StatisticalFilter filter) {
		return quailMonitorReturnDao.getQuailBankReturnMonitorStatistical(filter);
	}
	
	/**
	 * RejectApprove
	 * “灵活金”业务/普通信用卡进件质量监测报表统计期间：（XXXX年XX月XX日-XXXX年XX月XX日） 客户经理/微贷经理维度
	 * @param filter
	 * @return
	 */
	public List<QuailManaRejectMonitor> getQuailRejectMonitorStatistical(StatisticalFilter filter) {
		return quailMonitorRejectDao.getQuailRejectMonitorStatistical(filter);
	}
	/**
	 * RejectApprove
	 * “灵活金”业务/普通信用卡进件质量监测报表统计期间：（XXXX年XX月XX日-XXXX年XX月XX日）一级支行/二级支行维度
	 * @param filter
	 * @return
	 */
	public List<QuailBankRejectMonitor> getQuailBankRejectMonitorStatistical(StatisticalFilter filter) {
		return quailMonitorRejectDao.getQuailBankRejectMonitorStatistical(filter);
	}

}
