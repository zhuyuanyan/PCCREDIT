/*
 * Copyright 2013 Wicresoft, Inc. All rights reserved.
 */

package com.cardpay.pccredit.xm_appln.model;

import com.wicresoft.jrad.base.database.model.ModelParam;
import com.wicresoft.jrad.base.database.model.BusinessModel;

/**
 * Description of xm_appln_xpmx
 * 
 * @author 谭文华
 * 
 * @created on Dec 26, 2013
 * 
 * @version $Id: xm_appln_xpmx.java 1650 2014-10-09 14:55:25Z 谭文华 $
 */
@ModelParam(table = "xm_appln_kpmx")
public class XM_APPLN_KPMX extends BusinessModel {

	private static final long serialVersionUID = 1L;

	private String customer_id;
	private int lsh;
	private String custr_nbr;
	private String embname_d;
	private String emboss_cd;
	private String pin_reqd;
	private String sms_yn;
	private String pin_chk;
	private String cdesploc;
	private String cdespmtd;
	private String courierf;
	private String cdfrm;
	private String ppno;
	private String cred_lmt;
	private String spec_inst;
	private String atm;
	private String tele;
	private String net;
	private String phone;
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public int getLsh() {
		return lsh;
	}
	public void setLsh(int lsh) {
		this.lsh = lsh;
	}
	public String getCustr_nbr() {
		return custr_nbr;
	}
	public void setCustr_nbr(String custr_nbr) {
		this.custr_nbr = custr_nbr;
	}
	public String getEmbname_d() {
		return embname_d;
	}
	public void setEmbname_d(String embname_d) {
		this.embname_d = embname_d;
	}
	public String getEmboss_cd() {
		return emboss_cd;
	}
	public void setEmboss_cd(String emboss_cd) {
		this.emboss_cd = emboss_cd;
	}
	public String getPin_reqd() {
		return pin_reqd;
	}
	public void setPin_reqd(String pin_reqd) {
		this.pin_reqd = pin_reqd;
	}
	public String getSms_yn() {
		return sms_yn;
	}
	public void setSms_yn(String sms_yn) {
		this.sms_yn = sms_yn;
	}
	public String getPin_chk() {
		return pin_chk;
	}
	public void setPin_chk(String pin_chk) {
		this.pin_chk = pin_chk;
	}
	public String getCdesploc() {
		return cdesploc;
	}
	public void setCdesploc(String cdesploc) {
		this.cdesploc = cdesploc;
	}
	public String getCdespmtd() {
		return cdespmtd;
	}
	public void setCdespmtd(String cdespmtd) {
		this.cdespmtd = cdespmtd;
	}
	public String getCourierf() {
		return courierf;
	}
	public void setCourierf(String courierf) {
		this.courierf = courierf;
	}
	public String getCdfrm() {
		return cdfrm;
	}
	public void setCdfrm(String cdfrm) {
		this.cdfrm = cdfrm;
	}
	public String getPpno() {
		return ppno;
	}
	public void setPpno(String ppno) {
		this.ppno = ppno;
	}
	public String getCred_lmt() {
		return cred_lmt;
	}
	public void setCred_lmt(String cred_lmt) {
		this.cred_lmt = cred_lmt;
	}
	public String getSpec_inst() {
		return spec_inst;
	}
	public void setSpec_inst(String spec_inst) {
		this.spec_inst = spec_inst;
	}
	public String getAtm() {
		return atm;
	}
	public void setAtm(String atm) {
		this.atm = atm;
	}
	public String getTele() {
		return tele;
	}
	public void setTele(String tele) {
		this.tele = tele;
	}
	public String getNet() {
		return net;
	}
	public void setNet(String net) {
		this.net = net;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
