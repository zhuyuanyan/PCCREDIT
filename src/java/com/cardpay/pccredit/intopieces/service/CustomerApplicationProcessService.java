package com.cardpay.pccredit.intopieces.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cardpay.pccredit.customer.model.CustomerInfor;
import com.cardpay.pccredit.intopieces.dao.CustomerApplicationInfoDao;
import com.cardpay.pccredit.intopieces.dao.CustomerApplicationProcessDao;
import com.cardpay.pccredit.intopieces.filter.CustomerApplicationInfoFilter;
import com.cardpay.pccredit.intopieces.model.CustomerApplicationInfo;
import com.cardpay.pccredit.intopieces.model.CustomerApplicationProcess;
import com.wicresoft.jrad.base.database.dao.common.CommonDao;

@Service
public class CustomerApplicationProcessService {

	@Autowired
	private CommonDao commonDao;

	@Autowired
	private CustomerApplicationProcessDao customerApplicationProcessDao;

	/**
	 * 统计拒绝进件条数
	 * @return
	 */
	public CustomerApplicationProcess findById(String id){
		return customerApplicationProcessDao.findById(id);
	}

}
