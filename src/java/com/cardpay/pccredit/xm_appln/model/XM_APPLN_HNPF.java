/*
 * Copyright 2013 Wicresoft, Inc. All rights reserved.
 */

package com.cardpay.pccredit.xm_appln.model;

import com.wicresoft.jrad.base.database.model.ModelParam;
import com.wicresoft.jrad.base.database.model.BusinessModel;

/**
 * Description of xm_appln_dbxx
 * 
 * @author 谭文华
 * 
 * @created on Dec 26, 2013
 * 
 * @version $Id: xm_appln_dbxx.java 1650 2014-10-09 14:55:25Z 谭文华 $
 */
@ModelParam(table = "xm_appln_hnpf")
public class XM_APPLN_HNPF extends BusinessModel {

	private static final long serialVersionUID = 1L;

	private String customer_id;
	private String huji;
	private String have_house_bank;
	private String have_house_huji;
	private String is_xwkh;
	private String reg_type;
	private String reg_no;
	private String org_no;
	private String is_farmer;
	private String is_with_farm;
	private String income_src;
	private String is_add;
	private String is_gun;
	private String is_com_xyk_c;
	private String is_loan_c;
	private String is_loan_cus;
	private String prj;
	private String std_gb_level_1;
	private String std_gb_level_2;
	private String std_gb_level_3;
	private String std_gb_level_4;
	
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public String getHuji() {
		return huji;
	}
	public void setHuji(String huji) {
		this.huji = huji;
	}
	public String getHave_house_bank() {
		return have_house_bank;
	}
	public void setHave_house_bank(String have_house_bank) {
		this.have_house_bank = have_house_bank;
	}
	public String getHave_house_huji() {
		return have_house_huji;
	}
	public void setHave_house_huji(String have_house_huji) {
		this.have_house_huji = have_house_huji;
	}
	public String getIs_xwkh() {
		return is_xwkh;
	}
	public void setIs_xwkh(String is_xwkh) {
		this.is_xwkh = is_xwkh;
	}
	public String getReg_type() {
		return reg_type;
	}
	public void setReg_type(String reg_type) {
		this.reg_type = reg_type;
	}
	public String getReg_no() {
		return reg_no;
	}
	public void setReg_no(String reg_no) {
		this.reg_no = reg_no;
	}
	public String getOrg_no() {
		return org_no;
	}
	public void setOrg_no(String org_no) {
		this.org_no = org_no;
	}
	public String getIs_farmer() {
		return is_farmer;
	}
	public void setIs_farmer(String is_farmer) {
		this.is_farmer = is_farmer;
	}
	public String getIs_with_farm() {
		return is_with_farm;
	}
	public void setIs_with_farm(String is_with_farm) {
		this.is_with_farm = is_with_farm;
	}
	public String getIncome_src() {
		return income_src;
	}
	public void setIncome_src(String income_src) {
		this.income_src = income_src;
	}
	public String getIs_add() {
		return is_add;
	}
	public void setIs_add(String is_add) {
		this.is_add = is_add;
	}
	public String getIs_gun() {
		return is_gun;
	}
	public void setIs_gun(String is_gun) {
		this.is_gun = is_gun;
	}
	public String getIs_com_xyk_c() {
		return is_com_xyk_c;
	}
	public void setIs_com_xyk_c(String is_com_xyk_c) {
		this.is_com_xyk_c = is_com_xyk_c;
	}
	public String getIs_loan_c() {
		return is_loan_c;
	}
	public void setIs_loan_c(String is_loan_c) {
		this.is_loan_c = is_loan_c;
	}
	public String getIs_loan_cus() {
		return is_loan_cus;
	}
	public void setIs_loan_cus(String is_loan_cus) {
		this.is_loan_cus = is_loan_cus;
	}
	public String getPrj() {
		return prj;
	}
	public void setPrj(String prj) {
		this.prj = prj;
	}
	public String getStd_gb_level_1() {
		return std_gb_level_1;
	}
	public void setStd_gb_level_1(String std_gb_level_1) {
		this.std_gb_level_1 = std_gb_level_1;
	}
	public String getStd_gb_level_2() {
		return std_gb_level_2;
	}
	public void setStd_gb_level_2(String std_gb_level_2) {
		this.std_gb_level_2 = std_gb_level_2;
	}
	public String getStd_gb_level_3() {
		return std_gb_level_3;
	}
	public void setStd_gb_level_3(String std_gb_level_3) {
		this.std_gb_level_3 = std_gb_level_3;
	}
	public String getStd_gb_level_4() {
		return std_gb_level_4;
	}
	public void setStd_gb_level_4(String std_gb_level_4) {
		this.std_gb_level_4 = std_gb_level_4;
	}
}
