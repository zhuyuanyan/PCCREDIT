package com.cardpay.pccredit.intopieces.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cardpay.pccredit.intopieces.constant.Constant;
import com.cardpay.pccredit.intopieces.dao.XmNewSqDao;
import com.cardpay.pccredit.intopieces.filter.XmNewSqFilter;
import com.cardpay.pccredit.intopieces.model.XmNewSq;
import com.cardpay.pccredit.intopieces.model.XmNewSqForm;
import com.cardpay.pccredit.product.model.ProductAttribute;
import com.cardpay.pccredit.system.model.SystemCharge;
import com.wicresoft.jrad.base.database.dao.common.CommonDao;
import com.wicresoft.jrad.base.database.model.QueryResult;
import com.wicresoft.jrad.modules.privilege.model.Organization;

@Service
public class XmNewSqService {

	@Autowired
	private CommonDao commonDao;
	@Autowired
	private XmNewSqDao xmNewSqDao;
	
	public void insert(XmNewSq sq){
		//获取机构id和name
		String userId = sq.getUserId();
		String sql = "select * from sys_charge where user_id='"+userId+"'";
		List<SystemCharge> chargeList = commonDao.queryBySql(SystemCharge.class, sql, null);
		if(chargeList.size()>0){
			sq.setOrgId(chargeList.get(0).getOrgId());
			Organization organization = commonDao.findObjectById(Organization.class, chargeList.get(0).getOrgId());
			sq.setOrgName(organization.getName());
		}
		//获取产品name
		ProductAttribute product = commonDao.findObjectById(ProductAttribute.class,sq.getProductId());
		sq.setProductName(product.getProductName());
		sq.setStatus(Constant.SQ_APPROVE_TYPE_1);
		commonDao.insertObject(sq);
	}
	
	public void update(String id ,XmNewSqForm sq){
		XmNewSq xmNewSq = commonDao.findObjectById(XmNewSq.class, id);
		xmNewSq.setSqName(sq.getSqName());
		xmNewSq.setRemark(sq.getRemark());
		commonDao.updateObject(xmNewSq);
	}
	/*
	 * 通过审批
	 */
	public void pass(String id ,XmNewSqForm sq){
		XmNewSq xmNewSq = commonDao.findObjectById(XmNewSq.class, id);
		xmNewSq.setCustomerType(sq.getCustomerType());
		xmNewSq.setCustomerTypeCode(sq.getCustomerTypeCode());
		xmNewSq.setCustomerLevel(sq.getCustomerLevel());
		xmNewSq.setCustomerLevelCode(sq.getCustomerLevelCode());
		xmNewSq.setEd(sq.getEd());
		xmNewSq.setStatus(Constant.SQ_APPROVE_TYPE_2);
		commonDao.updateObject(xmNewSq);
	}
	/*
	 * 未通过审批
	 */
	public void unpass(String id ,XmNewSqForm sq){
		XmNewSq xmNewSq = commonDao.findObjectById(XmNewSq.class, id);
		xmNewSq.setStatus(Constant.SQ_APPROVE_TYPE_3);
		commonDao.updateObject(xmNewSq);
	}
	
	public XmNewSq findZaById(String id){
		return xmNewSqDao.findZaById(id);
	}
	
	public QueryResult<XmNewSq> findAllSq(XmNewSqFilter filter){
		List<XmNewSq> ls = xmNewSqDao.findAllZaByFilter(filter);
		int size = xmNewSqDao.findAllZaCountByFilter(filter);
		QueryResult<XmNewSq> qs = new QueryResult<XmNewSq>(size, ls);
		return qs;
	}
	
	//判断是否机构负责人
	public Boolean ifOrgManager(String userId){
		String sql = "select * from sys_charge where user_id='"+userId+"'";
		List<SystemCharge> chargeList = commonDao.queryBySql(SystemCharge.class, sql, null);
		if(chargeList.size()>0){
			return true;
		}else{
			return false;
		}
	}
}
