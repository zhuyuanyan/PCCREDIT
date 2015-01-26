/*
 * Copyright 2013 Wicresoft, Inc. All rights reserved.
 */

package com.cardpay.pccredit.xm_appln.model;

import com.wicresoft.jrad.base.database.model.ModelParam;
import com.wicresoft.jrad.base.database.model.BusinessModel;

/**
 * Description of xm_appln_tjinfo
 * 
 * @author 谭文华
 * 
 * @created on Dec 26, 2013
 * 
 * @version $Id: xm_appln_tjinfo.java 1650 2014-10-09 14:55:25Z 谭文华 $
 */
@ModelParam(table = "xm_appln_tjinfo")
public class XM_APPLN_TJINFO extends BusinessModel {

	private static final long serialVersionUID = 1L;

	private String customer_id;
	private String intr_nbr;
	private String intr_name;
	private String intr_cnbr;
	private String intr_recom;
	private String brnchcrlmt;
	private String brnch_intr;
	private String vrf_cntnt;
	private String intr_qc;
	private String intro_code;
	private String intr_rl;
	private String intro_text;
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public String getIntr_nbr() {
		return intr_nbr;
	}
	public void setIntr_nbr(String intr_nbr) {
		this.intr_nbr = intr_nbr;
	}
	public String getIntr_name() {
		return intr_name;
	}
	public void setIntr_name(String intr_name) {
		this.intr_name = intr_name;
	}
	public String getIntr_cnbr() {
		return intr_cnbr;
	}
	public void setIntr_cnbr(String intr_cnbr) {
		this.intr_cnbr = intr_cnbr;
	}
	public String getIntr_recom() {
		return intr_recom;
	}
	public void setIntr_recom(String intr_recom) {
		this.intr_recom = intr_recom;
	}
	public String getBrnchcrlmt() {
		return brnchcrlmt;
	}
	public void setBrnchcrlmt(String brnchcrlmt) {
		this.brnchcrlmt = brnchcrlmt;
	}
	public String getBrnch_intr() {
		return brnch_intr;
	}
	public void setBrnch_intr(String brnch_intr) {
		this.brnch_intr = brnch_intr;
	}
	public String getVrf_cntnt() {
		return vrf_cntnt;
	}
	public void setVrf_cntnt(String vrf_cntnt) {
		this.vrf_cntnt = vrf_cntnt;
	}
	public String getIntr_qc() {
		return intr_qc;
	}
	public void setIntr_qc(String intr_qc) {
		this.intr_qc = intr_qc;
	}
	public String getIntro_code() {
		return intro_code;
	}
	public void setIntro_code(String intro_code) {
		this.intro_code = intro_code;
	}
	public String getIntr_rl() {
		return intr_rl;
	}
	public void setIntr_rl(String intr_rl) {
		this.intr_rl = intr_rl;
	}
	public String getIntro_text() {
		return intro_text;
	}
	public void setIntro_text(String intro_text) {
		this.intro_text = intro_text;
	}
	
}
