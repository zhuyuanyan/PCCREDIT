/*
 * Copyright 2013 Wicresoft, Inc. All rights reserved.
 */

package com.cardpay.pccredit.xm_appln.model;

import com.wicresoft.jrad.base.database.model.ModelParam;
import com.wicresoft.jrad.base.database.model.BusinessModel;

/**
 * Description of xm_appln_qtxykxx
 * 
 * @author 谭文华
 * 
 * @created on Dec 26, 2013
 * 
 * @version $Id: xm_appln_qtxykxx.java 1650 2014-10-09 14:55:25Z 谭文华 $
 */
@ModelParam(table = "xm_appln_qtxykxx")
public class XM_APPLN_QTXYKXX extends BusinessModel {

	private static final long serialVersionUID = 1L;

	private String customer_id;
	private String xrefinac1;
	private String xrefcode1;
	private String xrefcrlmt1;
	private String xrefinac2;
	private String xrefcode2;
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public String getXrefinac1() {
		return xrefinac1;
	}
	public void setXrefinac1(String xrefinac1) {
		this.xrefinac1 = xrefinac1;
	}
	public String getXrefcode1() {
		return xrefcode1;
	}
	public void setXrefcode1(String xrefcode1) {
		this.xrefcode1 = xrefcode1;
	}
	public String getXrefcrlmt1() {
		return xrefcrlmt1;
	}
	public void setXrefcrlmt1(String xrefcrlmt1) {
		this.xrefcrlmt1 = xrefcrlmt1;
	}
	public String getXrefinac2() {
		return xrefinac2;
	}
	public void setXrefinac2(String xrefinac2) {
		this.xrefinac2 = xrefinac2;
	}
	public String getXrefcode2() {
		return xrefcode2;
	}
	public void setXrefcode2(String xrefcode2) {
		this.xrefcode2 = xrefcode2;
	}

}
