package com.cardpay.pccredit.xm_appln.dao;

import com.cardpay.pccredit.xm_appln.model.XM_APPLN_DCSC;
import com.wicresoft.util.annotation.Mapper;
/**
 * 
 * @author 谭文华
 *
 */
@Mapper
public interface XM_APPLN_DCSC_Dao {
	/**
	 * 得到XM_APPLN
	 * @param id
	 * @return
	 */
	public XM_APPLN_DCSC findByCustomerId(String customer_id);
}
