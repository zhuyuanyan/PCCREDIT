/*
 * Copyright 2013 Wicresoft, Inc. All rights reserved.
 */

package com.cardpay.pccredit.xm_appln.model;

import com.wicresoft.jrad.base.database.model.ModelParam;
import com.wicresoft.jrad.base.database.model.BusinessModel;

/**
 * Description of xm_appln_khed
 * 
 * @author 谭文华
 * 
 * @created on Dec 26, 2013
 * 
 * @version $Id: xm_appln_khed.java 1650 2014-10-09 14:55:25Z 谭文华 $
 */
@ModelParam(table = "xm_appln_khed")
public class XM_APPLN_KHED extends BusinessModel {

	private static final long serialVersionUID = 1L;

	private String customer_id;
	private String cred_limit;
	private String credlim_x;
	private String mp_limit;
	
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public String getCred_limit() {
		return cred_limit;
	}
	public void setCred_limit(String cred_limit) {
		this.cred_limit = cred_limit;
	}
	public String getCredlim_x() {
		return credlim_x;
	}
	public void setCredlim_x(String credlim_x) {
		this.credlim_x = credlim_x;
	}
	public String getMp_limit() {
		return mp_limit;
	}
	public void setMp_limit(String mp_limit) {
		this.mp_limit = mp_limit;
	}

}
