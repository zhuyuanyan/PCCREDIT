package com.cardpay.pccredit.pbccrcReport.dao;

import com.cardpay.pccredit.pbccrcReport.model.RH_DWDB_INFO;
import com.wicresoft.util.annotation.Mapper;
/**
 * 
 * @author 谭文华
 *
 */
@Mapper
public interface RH_DWDB_INFO_Dao {
	/**
	 * 得到RH_INFO
	 * @param id
	 * @return
	 */
	public RH_DWDB_INFO findByCustomerId(String customer_id);
}
