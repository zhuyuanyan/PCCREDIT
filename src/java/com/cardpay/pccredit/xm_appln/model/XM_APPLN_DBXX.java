/*
 * Copyright 2013 Wicresoft, Inc. All rights reserved.
 */

package com.cardpay.pccredit.xm_appln.model;

import com.wicresoft.jrad.base.database.model.ModelParam;
import com.wicresoft.jrad.base.database.model.BusinessModel;

/**
 * Description of xm_appln_dbxx
 * 
 * @author 谭文华
 * 
 * @created on Dec 26, 2013
 * 
 * @version $Id: xm_appln_dbxx.java 1650 2014-10-09 14:55:25Z 谭文华 $
 */
@ModelParam(table = "xm_appln_dbxx")
public class XM_APPLN_DBXX extends BusinessModel {

	private static final long serialVersionUID = 1L;

	private String customer_id;
	private String guarn_id;
	private String guarn_bank;
	private String guarn_ref;
	private String guarn_code;
	private String guarn_ref2;
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public String getGuarn_id() {
		return guarn_id;
	}
	public void setGuarn_id(String guarn_id) {
		this.guarn_id = guarn_id;
	}
	public String getGuarn_bank() {
		return guarn_bank;
	}
	public void setGuarn_bank(String guarn_bank) {
		this.guarn_bank = guarn_bank;
	}
	public String getGuarn_ref() {
		return guarn_ref;
	}
	public void setGuarn_ref(String guarn_ref) {
		this.guarn_ref = guarn_ref;
	}
	public String getGuarn_code() {
		return guarn_code;
	}
	public void setGuarn_code(String guarn_code) {
		this.guarn_code = guarn_code;
	}
	public String getGuarn_ref2() {
		return guarn_ref2;
	}
	public void setGuarn_ref2(String guarn_ref2) {
		this.guarn_ref2 = guarn_ref2;
	}
}
