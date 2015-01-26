/*
 * Copyright 2013 Wicresoft, Inc. All rights reserved.
 */

package com.cardpay.pccredit.xm_appln.model;

import com.wicresoft.jrad.base.database.model.ModelParam;
import com.wicresoft.jrad.base.database.model.BusinessModel;

/**
 * Description of xm_appln_jczl
 * 
 * @author 谭文华
 * 
 * @created on Dec 26, 2013
 * 
 * @version $Id: xm_appln_jczl.java 1650 2014-10-09 14:55:25Z 谭文华 $
 */
@ModelParam(table = "xm_appln_jczl")
public class XM_APPLN_JCZL extends BusinessModel {

	private static final long serialVersionUID = 1L;

	private String customer_id;
	private String surname;
	private String race_code;
	private String lang_code;
	private String dob_input;
	private String home_phone;
	private String gender;
	private String mar_status;
	private String mo_phone;
	private String title_inp;
	private String mthr_mname;
	private String custr_ref;
	private String emply_nbr;
	private String nation;
	private String nation_cd;
	private String educa;
	private String home_code;
	private String income_src;
	private String dependents;
	private String yr_there;
	private String home_loan;
	private String email_addr;
	private String comp_name;
	private String emply_dept;
	private String ird_number;
	private String occ_catgry;
	private String int_taxcod;
	private String income_ann;
	private String occ_code;
	private String posn_emply;
	private String yr_in_comp;
	private String busi_phone;
	private String cune_cr;
	private String secur_nbr;
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getRace_code() {
		return race_code;
	}
	public void setRace_code(String race_code) {
		this.race_code = race_code;
	}
	public String getLang_code() {
		return lang_code;
	}
	public void setLang_code(String lang_code) {
		this.lang_code = lang_code;
	}
	public String getDob_input() {
		return dob_input;
	}
	public void setDob_input(String dob_input) {
		this.dob_input = dob_input;
	}
	public String getHome_phone() {
		return home_phone;
	}
	public void setHome_phone(String home_phone) {
		this.home_phone = home_phone;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMar_status() {
		return mar_status;
	}
	public void setMar_status(String mar_status) {
		this.mar_status = mar_status;
	}
	public String getMo_phone() {
		return mo_phone;
	}
	public void setMo_phone(String mo_phone) {
		this.mo_phone = mo_phone;
	}
	public String getTitle_inp() {
		return title_inp;
	}
	public void setTitle_inp(String title_inp) {
		this.title_inp = title_inp;
	}
	public String getMthr_mname() {
		return mthr_mname;
	}
	public void setMthr_mname(String mthr_mname) {
		this.mthr_mname = mthr_mname;
	}
	public String getCustr_ref() {
		return custr_ref;
	}
	public void setCustr_ref(String custr_ref) {
		this.custr_ref = custr_ref;
	}
	public String getEmply_nbr() {
		return emply_nbr;
	}
	public void setEmply_nbr(String emply_nbr) {
		this.emply_nbr = emply_nbr;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getNation_cd() {
		return nation_cd;
	}
	public void setNation_cd(String nation_cd) {
		this.nation_cd = nation_cd;
	}
	public String getEduca() {
		return educa;
	}
	public void setEduca(String educa) {
		this.educa = educa;
	}
	public String getHome_code() {
		return home_code;
	}
	public void setHome_code(String home_code) {
		this.home_code = home_code;
	}
	public String getIncome_src() {
		return income_src;
	}
	public void setIncome_src(String income_src) {
		this.income_src = income_src;
	}
	public String getDependents() {
		return dependents;
	}
	public void setDependents(String dependents) {
		this.dependents = dependents;
	}
	public String getYr_there() {
		return yr_there;
	}
	public void setYr_there(String yr_there) {
		this.yr_there = yr_there;
	}
	public String getHome_loan() {
		return home_loan;
	}
	public void setHome_loan(String home_loan) {
		this.home_loan = home_loan;
	}
	public String getEmail_addr() {
		return email_addr;
	}
	public void setEmail_addr(String email_addr) {
		this.email_addr = email_addr;
	}
	public String getComp_name() {
		return comp_name;
	}
	public void setComp_name(String comp_name) {
		this.comp_name = comp_name;
	}
	public String getEmply_dept() {
		return emply_dept;
	}
	public void setEmply_dept(String emply_dept) {
		this.emply_dept = emply_dept;
	}
	public String getIrd_number() {
		return ird_number;
	}
	public void setIrd_number(String ird_number) {
		this.ird_number = ird_number;
	}
	public String getOcc_catgry() {
		return occ_catgry;
	}
	public void setOcc_catgry(String occ_catgry) {
		this.occ_catgry = occ_catgry;
	}
	public String getInt_taxcod() {
		return int_taxcod;
	}
	public void setInt_taxcod(String int_taxcod) {
		this.int_taxcod = int_taxcod;
	}
	public String getIncome_ann() {
		return income_ann;
	}
	public void setIncome_ann(String income_ann) {
		this.income_ann = income_ann;
	}
	public String getOcc_code() {
		return occ_code;
	}
	public void setOcc_code(String occ_code) {
		this.occ_code = occ_code;
	}
	public String getPosn_emply() {
		return posn_emply;
	}
	public void setPosn_emply(String posn_emply) {
		this.posn_emply = posn_emply;
	}
	public String getYr_in_comp() {
		return yr_in_comp;
	}
	public void setYr_in_comp(String yr_in_comp) {
		this.yr_in_comp = yr_in_comp;
	}
	public String getBusi_phone() {
		return busi_phone;
	}
	public void setBusi_phone(String busi_phone) {
		this.busi_phone = busi_phone;
	}
	public String getCune_cr() {
		return cune_cr;
	}
	public void setCune_cr(String cune_cr) {
		this.cune_cr = cune_cr;
	}
	public String getSecur_nbr() {
		return secur_nbr;
	}
	public void setSecur_nbr(String secur_nbr) {
		this.secur_nbr = secur_nbr;
	}

}
