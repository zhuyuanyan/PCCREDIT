/*
 * Copyright 2013 Wicresoft, Inc. All rights reserved.
 */

package com.cardpay.pccredit.xm_appln.model;

import com.wicresoft.jrad.base.database.model.ModelParam;
import com.wicresoft.jrad.base.database.model.BusinessModel;

/**
 * Description of xm_appln_hksz
 * 
 * @author 谭文华
 * 
 * @created on Dec 26, 2013
 * 
 * @version $Id: xm_appln_hksz.java 1650 2014-10-09 14:55:25Z 谭文华 $
 */
@ModelParam(table = "xm_appln_hksz")
public class XM_APPLN_HKSZ extends BusinessModel {

	private static final long serialVersionUID = 1L;

	private String customer_id;
	private String repay_code;
	private String bankacct1;
	private String repay_amt;
	private String repay_pct;
	private String repay_day;
	private String repay_codx;
	private String bankacct2;
	private String repay_amtx;
	private String repay_pctx;
	private String exch_flag;
	private String bankacct3;
	private String exch_code;
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public String getRepay_code() {
		return repay_code;
	}
	public void setRepay_code(String repay_code) {
		this.repay_code = repay_code;
	}
	public String getBankacct1() {
		return bankacct1;
	}
	public void setBankacct1(String bankacct1) {
		this.bankacct1 = bankacct1;
	}
	public String getRepay_amt() {
		return repay_amt;
	}
	public void setRepay_amt(String repay_amt) {
		this.repay_amt = repay_amt;
	}
	public String getRepay_pct() {
		return repay_pct;
	}
	public void setRepay_pct(String repay_pct) {
		this.repay_pct = repay_pct;
	}
	public String getRepay_day() {
		return repay_day;
	}
	public void setRepay_day(String repay_day) {
		this.repay_day = repay_day;
	}
	public String getRepay_codx() {
		return repay_codx;
	}
	public void setRepay_codx(String repay_codx) {
		this.repay_codx = repay_codx;
	}
	public String getBankacct2() {
		return bankacct2;
	}
	public void setBankacct2(String bankacct2) {
		this.bankacct2 = bankacct2;
	}
	public String getRepay_amtx() {
		return repay_amtx;
	}
	public void setRepay_amtx(String repay_amtx) {
		this.repay_amtx = repay_amtx;
	}
	public String getRepay_pctx() {
		return repay_pctx;
	}
	public void setRepay_pctx(String repay_pctx) {
		this.repay_pctx = repay_pctx;
	}
	public String getExch_flag() {
		return exch_flag;
	}
	public void setExch_flag(String exch_flag) {
		this.exch_flag = exch_flag;
	}
	public String getBankacct3() {
		return bankacct3;
	}
	public void setBankacct3(String bankacct3) {
		this.bankacct3 = bankacct3;
	}
	public String getExch_code() {
		return exch_code;
	}
	public void setExch_code(String exch_code) {
		this.exch_code = exch_code;
	}
	
}
