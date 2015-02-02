package com.cardpay.pccredit.xm_appln.dao;

import java.util.List;

import com.cardpay.pccredit.xm_appln.model.XM_APPLN_SQCL;
import com.wicresoft.util.annotation.Mapper;
/**
 * 
 * @author 谭文华
 *
 */
@Mapper
public interface XM_APPLN_SQCL_Dao {
	/**
	 * 得到XM_APPLN_SQCL
	 * @param id
	 * @return
	 */
	public XM_APPLN_SQCL findByCurrentStatus(String currentStatus);
	public List<XM_APPLN_SQCL> findByApplicationID(String applicationId);
}
