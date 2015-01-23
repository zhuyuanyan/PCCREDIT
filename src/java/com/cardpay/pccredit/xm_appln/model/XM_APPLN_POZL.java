/*
 * Copyright 2013 Wicresoft, Inc. All rights reserved.
 */

package com.cardpay.pccredit.xm_appln.model;

import com.wicresoft.jrad.base.database.model.ModelParam;
import com.wicresoft.jrad.base.database.model.BusinessModel;

/**
 * Description of xm_appln_pozl
 * 
 * @author 谭文华
 * 
 * @created on Dec 26, 2013
 * 
 * @version $Id: xm_appln_pozl.java 1650 2014-10-09 14:55:25Z 谭文华 $
 */
@ModelParam(table = "xm_appln_pozl")
public class XM_APPLN_POZL extends BusinessModel {

	private static final long serialVersionUID = 1L;

	private String customer_id;
	private String compnm;
	private String custr_nbr;
	private String income_src;
	private String mobile;
	private String yr_in_comp;
	private String income_ann;
	private String name;
	private String telno;
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public String getCompnm() {
		return compnm;
	}
	public void setCompnm(String compnm) {
		this.compnm = compnm;
	}
	public String getCustr_nbr() {
		return custr_nbr;
	}
	public void setCustr_nbr(String custr_nbr) {
		this.custr_nbr = custr_nbr;
	}
	public String getIncome_src() {
		return income_src;
	}
	public void setIncome_src(String income_src) {
		this.income_src = income_src;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getYr_in_comp() {
		return yr_in_comp;
	}
	public void setYr_in_comp(String yr_in_comp) {
		this.yr_in_comp = yr_in_comp;
	}
	public String getIncome_ann() {
		return income_ann;
	}
	public void setIncome_ann(String income_ann) {
		this.income_ann = income_ann;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTelno() {
		return telno;
	}
	public void setTelno(String telno) {
		this.telno = telno;
	}
	
}
