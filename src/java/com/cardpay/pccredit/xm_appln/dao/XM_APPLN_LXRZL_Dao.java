package com.cardpay.pccredit.xm_appln.dao;

import java.util.List;

import com.cardpay.pccredit.xm_appln.model.XM_APPLN_LXRZL;
import com.wicresoft.util.annotation.Mapper;
/**
 * 
 * @author 谭文华
 *
 */
@Mapper
public interface XM_APPLN_LXRZL_Dao {
	/**
	 * 得到XM_APPLN
	 * @param id
	 * @return
	 */
	public List<XM_APPLN_LXRZL> findByCustomerId(String customer_id);
}
