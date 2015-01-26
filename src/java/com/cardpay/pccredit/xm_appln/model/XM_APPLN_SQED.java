/*
 * Copyright 2013 Wicresoft, Inc. All rights reserved.
 */

package com.cardpay.pccredit.xm_appln.model;

import com.wicresoft.jrad.base.database.model.ModelParam;
import com.wicresoft.jrad.base.database.model.BusinessModel;

/**
 * Description of xm_appln_sqed
 * 
 * @author 谭文华
 * 
 * @created on Dec 26, 2013
 * 
 * @version $Id: xm_appln_sqed.java 1650 2014-10-09 14:55:25Z 谭文华 $
 */
@ModelParam(table = "xm_appln_sqed")
public class XM_APPLN_SQED extends BusinessModel {

	private static final long serialVersionUID = 1L;

	private String customer_id;
	private String crdlmt_req;
	private String crlm_x_req;
	private String mplmt_req;
	private String climit;
	private String cred_lmt;
	
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public String getCrdlmt_req() {
		return crdlmt_req;
	}
	public void setCrdlmt_req(String crdlmt_req) {
		this.crdlmt_req = crdlmt_req;
	}
	public String getCrlm_x_req() {
		return crlm_x_req;
	}
	public void setCrlm_x_req(String crlm_x_req) {
		this.crlm_x_req = crlm_x_req;
	}
	public String getMplmt_req() {
		return mplmt_req;
	}
	public void setMplmt_req(String mplmt_req) {
		this.mplmt_req = mplmt_req;
	}
	public String getClimit() {
		return climit;
	}
	public void setClimit(String climit) {
		this.climit = climit;
	}
	public String getCred_lmt() {
		return cred_lmt;
	}
	public void setCred_lmt(String cred_lmt) {
		this.cred_lmt = cred_lmt;
	}
	
}
