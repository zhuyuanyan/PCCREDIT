/*
 * Copyright 2013 Wicresoft, Inc. All rights reserved.
 */

package com.cardpay.pccredit.xm_appln.model;

import com.wicresoft.jrad.base.database.model.ModelParam;
import com.wicresoft.jrad.base.database.model.BusinessModel;

/**
 * Description of xm_appln_sqcl
 * 
 * @author 谭文华
 * 
 * @created on Dec 26, 2013
 * 
 * @version $Id: xm_appln_sqcl.java 1650 2014-10-09 14:55:25Z 谭文华 $
 */
@ModelParam(table = "xm_appln_sqcl")
public class XM_APPLN_SQCL extends BusinessModel {

	private static final long serialVersionUID = 1L;

	private String current_process;
	private String before_status;
	private String current_status;
	private String deccan_cde;
	private String deccan_let;
	private String deccan_sup;
	private String rush_card;
	private String rush_fee;
	private String appdec_dte;
	private String deccan_rea;
	private String pin_dspch;
	private String pincourfee;
	
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
	public String getDeccan_cde() {
		return deccan_cde;
	}
	public void setDeccan_cde(String deccan_cde) {
		this.deccan_cde = deccan_cde;
	}
	public String getDeccan_let() {
		return deccan_let;
	}
	public void setDeccan_let(String deccan_let) {
		this.deccan_let = deccan_let;
	}
	public String getDeccan_sup() {
		return deccan_sup;
	}
	public void setDeccan_sup(String deccan_sup) {
		this.deccan_sup = deccan_sup;
	}
	public String getRush_card() {
		return rush_card;
	}
	public void setRush_card(String rush_card) {
		this.rush_card = rush_card;
	}
	public String getRush_fee() {
		return rush_fee;
	}
	public void setRush_fee(String rush_fee) {
		this.rush_fee = rush_fee;
	}
	public String getAppdec_dte() {
		return appdec_dte;
	}
	public void setAppdec_dte(String appdec_dte) {
		this.appdec_dte = appdec_dte;
	}
	public String getDeccan_rea() {
		return deccan_rea;
	}
	public void setDeccan_rea(String deccan_rea) {
		this.deccan_rea = deccan_rea;
	}
	public String getPin_dspch() {
		return pin_dspch;
	}
	public void setPin_dspch(String pin_dspch) {
		this.pin_dspch = pin_dspch;
	}
	public String getPincourfee() {
		return pincourfee;
	}
	public void setPincourfee(String pincourfee) {
		this.pincourfee = pincourfee;
	}
}
