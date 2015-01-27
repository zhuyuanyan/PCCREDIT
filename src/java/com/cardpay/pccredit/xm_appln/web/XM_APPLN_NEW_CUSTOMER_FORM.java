package com.cardpay.pccredit.xm_appln.web;

import com.cardpay.pccredit.xm_appln.model.XM_APPLN_JCZL;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_POZL;
import com.wicresoft.jrad.base.web.form.BaseForm;

public class XM_APPLN_NEW_CUSTOMER_FORM extends BaseForm {
	public static final long serialVersionUID = 1L;

	//个人资料
	private String customer_id;
	private String card_id;
	private String surname;
	private String race_code;
	private String mo_phone;
	private String mar_status;
	private String gender;
	
	//配偶
	private String spu_name;
	private String spu_custr_nbr;
	
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public String getMar_status() {
		return mar_status;
	}
	public void setMar_status(String mar_status) {
		this.mar_status = mar_status;
	}
	public String getCard_id() {
		return card_id;
	}
	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getRace_code() {
		return race_code;
	}
	public void setRace_code(String race_code) {
		this.race_code = race_code;
	}
	public String getMo_phone() {
		return mo_phone;
	}
	public void setMo_phone(String mo_phone) {
		this.mo_phone = mo_phone;
	}
	public String getSpu_name() {
		return spu_name;
	}
	public void setSpu_name(String spu_name) {
		this.spu_name = spu_name;
	}
	public String getSpu_custr_nbr() {
		return spu_custr_nbr;
	}
	public void setSpu_custr_nbr(String spu_custr_nbr) {
		this.spu_custr_nbr = spu_custr_nbr;
	}
	
	//个人信息
	public XM_APPLN_JCZL createXM_APPLN_JCZL(String customerId, String id) {
		XM_APPLN_JCZL obj = new XM_APPLN_JCZL();
		obj.setCustomer_id(customerId);
		obj.setSurname(surname);
		obj.setRace_code(race_code);
		obj.setCard_id(card_id);
		obj.setMo_phone(mo_phone);
		obj.setMar_status(mar_status);
		obj.setGender(gender);
		return obj;
	}
	//配偶资料
	public XM_APPLN_POZL createXM_APPLN_POZL(String customerId, String id) {
		XM_APPLN_POZL obj = new XM_APPLN_POZL();
		obj.setCustomer_id(customerId);
		obj.setName(spu_name);
		obj.setCustr_nbr(spu_custr_nbr);
		return obj;
	}

}
