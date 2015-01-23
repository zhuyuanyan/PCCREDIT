/*
 * Copyright 2013 Wicresoft, Inc. All rights reserved.
 */

package com.cardpay.pccredit.xm_appln.model;

import com.wicresoft.jrad.base.database.model.ModelParam;
import com.wicresoft.jrad.base.database.model.BusinessModel;

/**
 * Description of xm_appln
 * 
 * @author 谭文华
 * 
 * @created on Dec 26, 2013
 * 
 * @version $Id: xm_appln.java 1650 2014-10-09 14:55:25Z 谭文华 $
 */
@ModelParam(table = "xm_appln")
public class XM_APPLN extends BusinessModel {

	private static final long serialVersionUID = 1L;

	private String customer_id;
	private String product;
	private String addl_card;
	private String rush_card;
	private String app_source;
	private String int_code;
	private String rush_fee;
	private String business;
	private String emboss_cpy;
	private String busn_name;
	private String base_card;
	private String branch;
	private String brch_name;
	private String stm_code;
	private String app_batch;
	private String ab_branch;
	private String acc_type;
	private String gtoc;
	private String downprod;
	private String cp_no;
	private String cp_nbrmth;
	private String debit_brn;
	private String app_deccd;
	private String app_decreas;
	private String app_decday;
	private String cycle_nbr;
	private String fee_group;
	private String hdwr_sn;
	private String mail_to;
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getAddl_card() {
		return addl_card;
	}
	public void setAddl_card(String addl_card) {
		this.addl_card = addl_card;
	}
	public String getRush_card() {
		return rush_card;
	}
	public void setRush_card(String rush_card) {
		this.rush_card = rush_card;
	}
	public String getApp_source() {
		return app_source;
	}
	public void setApp_source(String app_source) {
		this.app_source = app_source;
	}
	public String getInt_code() {
		return int_code;
	}
	public void setInt_code(String int_code) {
		this.int_code = int_code;
	}
	public String getRush_fee() {
		return rush_fee;
	}
	public void setRush_fee(String rush_fee) {
		this.rush_fee = rush_fee;
	}
	public String getBusiness() {
		return business;
	}
	public void setBusiness(String business) {
		this.business = business;
	}
	public String getEmboss_cpy() {
		return emboss_cpy;
	}
	public void setEmboss_cpy(String emboss_cpy) {
		this.emboss_cpy = emboss_cpy;
	}
	public String getBusn_name() {
		return busn_name;
	}
	public void setBusn_name(String busn_name) {
		this.busn_name = busn_name;
	}
	public String getBase_card() {
		return base_card;
	}
	public void setBase_card(String base_card) {
		this.base_card = base_card;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getBrch_name() {
		return brch_name;
	}
	public void setBrch_name(String brch_name) {
		this.brch_name = brch_name;
	}
	public String getStm_code() {
		return stm_code;
	}
	public void setStm_code(String stm_code) {
		this.stm_code = stm_code;
	}
	public String getApp_batch() {
		return app_batch;
	}
	public void setApp_batch(String app_batch) {
		this.app_batch = app_batch;
	}
	public String getAb_branch() {
		return ab_branch;
	}
	public void setAb_branch(String ab_branch) {
		this.ab_branch = ab_branch;
	}
	public String getAcc_type() {
		return acc_type;
	}
	public void setAcc_type(String acc_type) {
		this.acc_type = acc_type;
	}
	public String getGtoc() {
		return gtoc;
	}
	public void setGtoc(String gtoc) {
		this.gtoc = gtoc;
	}
	public String getDownprod() {
		return downprod;
	}
	public void setDownprod(String downprod) {
		this.downprod = downprod;
	}
	public String getCp_no() {
		return cp_no;
	}
	public void setCp_no(String cp_no) {
		this.cp_no = cp_no;
	}
	public String getCp_nbrmth() {
		return cp_nbrmth;
	}
	public void setCp_nbrmth(String cp_nbrmth) {
		this.cp_nbrmth = cp_nbrmth;
	}
	public String getDebit_brn() {
		return debit_brn;
	}
	public void setDebit_brn(String debit_brn) {
		this.debit_brn = debit_brn;
	}
	public String getApp_deccd() {
		return app_deccd;
	}
	public void setApp_deccd(String app_deccd) {
		this.app_deccd = app_deccd;
	}
	public String getApp_decreas() {
		return app_decreas;
	}
	public void setApp_decreas(String app_decreas) {
		this.app_decreas = app_decreas;
	}
	public String getApp_decday() {
		return app_decday;
	}
	public void setApp_decday(String app_decday) {
		this.app_decday = app_decday;
	}
	public String getCycle_nbr() {
		return cycle_nbr;
	}
	public void setCycle_nbr(String cycle_nbr) {
		this.cycle_nbr = cycle_nbr;
	}
	public String getFee_group() {
		return fee_group;
	}
	public void setFee_group(String fee_group) {
		this.fee_group = fee_group;
	}
	public String getHdwr_sn() {
		return hdwr_sn;
	}
	public void setHdwr_sn(String hdwr_sn) {
		this.hdwr_sn = hdwr_sn;
	}
	public String getMail_to() {
		return mail_to;
	}
	public void setMail_to(String mail_to) {
		this.mail_to = mail_to;
	}

}
