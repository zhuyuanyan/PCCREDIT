package com.cardpay.pccredit.psiviReport.dao;

import com.cardpay.pccredit.psiviReport.model.ID_CHECK_INFO;
import com.wicresoft.util.annotation.Mapper;

/**
 * 
 * @author 池能和
 *
 */
@Mapper
public interface IdCheck_INFO_Dao {
	/**
	 * 得到Idcheck_info
	 * @param id
	 * @return
	 */
	public ID_CHECK_INFO findByCustomerId(String card_no);
}
