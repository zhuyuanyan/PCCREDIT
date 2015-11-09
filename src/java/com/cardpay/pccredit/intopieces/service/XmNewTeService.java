package com.cardpay.pccredit.intopieces.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cardpay.pccredit.customer.model.Maintenance;
import com.cardpay.pccredit.intopieces.dao.XmNewTeDao;
import com.cardpay.pccredit.intopieces.filter.XmNewTeFilter;
import com.cardpay.pccredit.intopieces.model.XmNewTe;
import com.wicresoft.jrad.base.database.dao.common.CommonDao;
import com.wicresoft.jrad.base.database.model.QueryResult;
import com.wicresoft.util.date.DateHelper;

@Service
public class XmNewTeService {
	@Autowired
	private CommonDao commonDao;
	@Autowired
	private XmNewTeDao xmNewTeDao;
	
	public QueryResult<XmNewTe> findAllTe(XmNewTeFilter filter){
		List<XmNewTe> ls = xmNewTeDao.findAllTeByFilter(filter);
		int size = xmNewTeDao.findAllTeCountByFilter(filter);
		QueryResult<XmNewTe> qs = new QueryResult<XmNewTe>(size, ls);
		return qs;
	}
	
	
	public void update(String id,String status){
		XmNewTe xmNewTe = commonDao.findObjectById(XmNewTe.class, id);
		xmNewTe.setStatus(status);
	   commonDao.updateObject(xmNewTe);
	}
	
	public XmNewTe findXmNewTeById(String id){
		return commonDao.findObjectById(XmNewTe.class, id);
	}
	
	public boolean updateXmNewTe(XmNewTe xmNewTe){
		int i = commonDao.updateObject(xmNewTe);
		return i>0?true:false;
	}
}
