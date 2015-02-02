package com.cardpay.pccredit.xm_appln.dao;

import java.util.List;

import com.cardpay.pccredit.xm_appln.model.XM_APPLN_SPED;
import com.wicresoft.util.annotation.Mapper;
/**
 * 
 * @author 谭文华
 *
 */
@Mapper
public interface XM_APPLN_SPED_Dao {
	/**
	 * 得到XM_APPLN_SPED
	 * @param id
	 * @return
	 */
	public XM_APPLN_SPED findByCurrentStatus(String currentStatus);
	public List<XM_APPLN_SPED> findByApplicationID(String applicationId);
}
