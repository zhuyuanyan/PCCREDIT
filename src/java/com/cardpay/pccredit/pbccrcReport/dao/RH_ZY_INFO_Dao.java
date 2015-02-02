package com.cardpay.pccredit.pbccrcReport.dao;

import java.util.List;

import com.cardpay.pccredit.pbccrcReport.model.RH_ZY_INFO;
import com.wicresoft.util.annotation.Mapper;
/**
 * 
 * @author 谭文华
 *
 */
@Mapper
public interface RH_ZY_INFO_Dao {
	/**
	 * 得到RH_INFO
	 * @param id
	 * @return
	 */
	public List<RH_ZY_INFO> findByCustomerId(String customer_id);
}
