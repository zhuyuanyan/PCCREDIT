/*
 * Copyright 2013 Wicresoft, Inc. All rights reserved.
 */

package com.cardpay.pccredit.xm_appln.model;

import com.wicresoft.jrad.base.database.model.ModelParam;
import com.wicresoft.jrad.base.database.model.BusinessModel;

/**
 * Description of xm_appln_zhsx
 * 
 * @author 谭文华
 * 
 * @created on Dec 26, 2013
 * 
 * @version $Id: xm_appln_zhsx.java 1650 2014-10-09 14:55:25Z 谭文华 $
 */
@ModelParam(table = "xm_appln_zhsx")
public class XM_APPLN_ZHSX extends BusinessModel {

	private static final long serialVersionUID = 1L;

	private String current_process;
	private String before_status;
	private String current_status;
	private String int_code;
	private String acc_type_n;
	private String gtoc;
	private String cycle_nbr;
	private String app_source;
	private String ab_branch;
	private String downprod;
	private String acc_type;
	private String stm_code;
	
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
	public String getInt_code() {
		return int_code;
	}
	public void setInt_code(String int_code) {
		this.int_code = int_code;
	}
	public String getAcc_type_n() {
		return acc_type_n;
	}
	public void setAcc_type_n(String acc_type_n) {
		this.acc_type_n = acc_type_n;
	}
	public String getGtoc() {
		return gtoc;
	}
	public void setGtoc(String gtoc) {
		this.gtoc = gtoc;
	}
	public String getCycle_nbr() {
		return cycle_nbr;
	}
	public void setCycle_nbr(String cycle_nbr) {
		this.cycle_nbr = cycle_nbr;
	}
	public String getApp_source() {
		return app_source;
	}
	public void setApp_source(String app_source) {
		this.app_source = app_source;
	}
	public String getAb_branch() {
		return ab_branch;
	}
	public void setAb_branch(String ab_branch) {
		this.ab_branch = ab_branch;
	}
	public String getDownprod() {
		return downprod;
	}
	public void setDownprod(String downprod) {
		this.downprod = downprod;
	}
	public String getAcc_type() {
		return acc_type;
	}
	public void setAcc_type(String acc_type) {
		this.acc_type = acc_type;
	}
	public String getStm_code() {
		return stm_code;
	}
	public void setStm_code(String stm_code) {
		this.stm_code = stm_code;
	}
	
}
