/*
 * Copyright 2013 Wicresoft, Inc. All rights reserved.
 */

package com.cardpay.pccredit.xm_appln.model;

import com.wicresoft.jrad.base.database.model.ModelParam;
import com.wicresoft.jrad.base.database.model.BusinessModel;

/**
 * Description of xm_appln_ywxx
 * 
 * @author 谭文华
 * 
 * @created on Dec 26, 2013
 * 
 * @version $Id: xm_appln_ywxx.java 1650 2014-10-09 14:55:25Z 谭文华 $
 */
@ModelParam(table = "xm_appln_ywxx")
public class XM_APPLN_YWXX extends BusinessModel {

	private static final long serialVersionUID = 1L;

	private String customer_id;
	private String app_giftno;
	private String app_cross;
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public String getApp_giftno() {
		return app_giftno;
	}
	public void setApp_giftno(String app_giftno) {
		this.app_giftno = app_giftno;
	}
	public String getApp_cross() {
		return app_cross;
	}
	public void setApp_cross(String app_cross) {
		this.app_cross = app_cross;
	}

}
