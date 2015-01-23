package com.cardpay.pccredit.xm_appln.dao;

import java.util.List;

import com.cardpay.pccredit.xm_appln.model.XM_APPLN_KPMX;
import com.wicresoft.util.annotation.Mapper;
/**
 * 
 * @author 谭文华
 *
 */
@Mapper
public interface XM_APPLN_KPMX_Dao {
	/**
	 * 得到XM_APPLN
	 * @param id
	 * @return
	 */
	public List<XM_APPLN_KPMX> findByCustomerId(String customer_id);
}
