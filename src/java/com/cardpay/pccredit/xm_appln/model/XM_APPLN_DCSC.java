/*
 * Copyright 2013 Wicresoft, Inc. All rights reserved.
 */

package com.cardpay.pccredit.xm_appln.model;

import com.wicresoft.jrad.base.database.model.ModelParam;
import com.wicresoft.jrad.base.database.model.BusinessModel;

/**
 * Description of xm_appln_dcsc
 * 
 * @author 谭文华
 * 
 * @created on Dec 26, 2013
 * 
 * @version $Id: xm_appln_dbxx.java 1650 2014-10-09 14:55:25Z 谭文华 $
 */
@ModelParam(table = "xm_appln_dcsc")
public class XM_APPLN_DCSC extends BusinessModel {

	private static final long serialVersionUID = 1L;

	private String customer_id;
	private String aval_nbr;
	private String aval_name;
	private String achk_nbr;
	private String achk_name;
	private String adec_nbr;
	private String adec_name;
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public String getAval_nbr() {
		return aval_nbr;
	}
	public void setAval_nbr(String aval_nbr) {
		this.aval_nbr = aval_nbr;
	}
	public String getAval_name() {
		return aval_name;
	}
	public void setAval_name(String aval_name) {
		this.aval_name = aval_name;
	}
	public String getAchk_nbr() {
		return achk_nbr;
	}
	public void setAchk_nbr(String achk_nbr) {
		this.achk_nbr = achk_nbr;
	}
	public String getAchk_name() {
		return achk_name;
	}
	public void setAchk_name(String achk_name) {
		this.achk_name = achk_name;
	}
	public String getAdec_nbr() {
		return adec_nbr;
	}
	public void setAdec_nbr(String adec_nbr) {
		this.adec_nbr = adec_nbr;
	}
	public String getAdec_name() {
		return adec_name;
	}
	public void setAdec_name(String adec_name) {
		this.adec_name = adec_name;
	}
	
}
