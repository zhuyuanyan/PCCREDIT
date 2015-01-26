/*
 * Copyright 2013 Wicresoft, Inc. All rights reserved.
 */

package com.cardpay.pccredit.xm_appln.model;

import com.wicresoft.jrad.base.database.model.ModelParam;
import com.wicresoft.jrad.base.database.model.BusinessModel;

/**
 * Description of xm_appln_khlb
 * 
 * @author 谭文华
 * 
 * @created on Dec 26, 2013
 * 
 * @version $Id: xm_appln_khlb.java 1650 2014-10-09 14:55:25Z 谭文华 $
 */
@ModelParam(table = "xm_appln_khlb")
public class XM_APPLN_KHLB extends BusinessModel {

	private static final long serialVersionUID = 1L;

	private String customer_id;
	private String class_code;
	private String risk_levl;
	private String value_levl;
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public String getClass_code() {
		return class_code;
	}
	public void setClass_code(String class_code) {
		this.class_code = class_code;
	}
	public String getRisk_levl() {
		return risk_levl;
	}
	public void setRisk_levl(String risk_levl) {
		this.risk_levl = risk_levl;
	}
	public String getValue_levl() {
		return value_levl;
	}
	public void setValue_levl(String value_levl) {
		this.value_levl = value_levl;
	}
	
}
