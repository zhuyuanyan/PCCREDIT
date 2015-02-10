/*
 * Copyright 2013 Wicresoft, Inc. All rights reserved.
 */

package com.cardpay.pccredit.xm_appln.model;

import com.wicresoft.jrad.base.database.model.ModelParam;
import com.wicresoft.jrad.base.database.model.BusinessModel;

/**
 * Description of xm_appln_zxxx
 * 
 * @author 谭文华
 * 
 * @created on Dec 26, 2013
 * 
 * @version $Id: xm_appln_zxxx.java 1650 2014-10-09 14:55:25Z 谭文华 $
 */
@ModelParam(table = "xm_appln_zxxx")
public class XM_APPLN_ZXXX extends BusinessModel {

	private static final long serialVersionUID = 1L;

	private String customer_id;
	private String current_process;
	private String before_status;
	private String current_status;
	private String cu_crcod1;
	private String cu_crcod2;
	private String ac_crcod1;
	private String ac_crcod2;
	
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public String getCurrent_process() {
		return current_process;
	}
	public void setCurrent_process(String current_process) {
		this.current_process = current_process;
	}
	public String getBefore_status() {
		return before_status;
	}
	public void setBefore_status(String before_status) {
		this.before_status = before_status;
	}
	public String getCurrent_status() {
		return current_status;
	}
	public void setCurrent_status(String current_status) {
		this.current_status = current_status;
	}
	public String getCu_crcod1() {
		return cu_crcod1;
	}
	public void setCu_crcod1(String cu_crcod1) {
		this.cu_crcod1 = cu_crcod1;
	}
	public String getCu_crcod2() {
		return cu_crcod2;
	}
	public void setCu_crcod2(String cu_crcod2) {
		this.cu_crcod2 = cu_crcod2;
	}
	public String getAc_crcod1() {
		return ac_crcod1;
	}
	public void setAc_crcod1(String ac_crcod1) {
		this.ac_crcod1 = ac_crcod1;
	}
	public String getAc_crcod2() {
		return ac_crcod2;
	}
	public void setAc_crcod2(String ac_crcod2) {
		this.ac_crcod2 = ac_crcod2;
	}
	
}
