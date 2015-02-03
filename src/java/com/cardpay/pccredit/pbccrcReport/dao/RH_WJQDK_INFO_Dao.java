package com.cardpay.pccredit.pbccrcReport.dao;

import com.cardpay.pccredit.pbccrcReport.model.RH_WJQDK_INFO;
import com.wicresoft.util.annotation.Mapper;
/**
 * 
 * @author 谭文华
 *
 */
@Mapper
public interface RH_WJQDK_INFO_Dao {
	/**
	 * 得到RH_INFO
	 * @param id
	 * @return
	 */
	public RH_WJQDK_INFO findByCustomerId(String customer_id);
}