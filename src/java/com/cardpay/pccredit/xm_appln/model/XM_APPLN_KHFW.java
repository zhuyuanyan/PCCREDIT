/*
 * Copyright 2013 Wicresoft, Inc. All rights reserved.
 */

package com.cardpay.pccredit.xm_appln.model;

import com.wicresoft.jrad.base.database.model.ModelParam;
import com.wicresoft.jrad.base.database.model.BusinessModel;

/**
 * Description of xm_appln_khfw
 * 
 * @author 谭文华
 * 
 * @created on Dec 26, 2013
 * 
 * @version $Id: xm_appln_khfw.java 1650 2014-10-09 14:55:25Z 谭文华 $
 */
@ModelParam(table = "xm_appln_khfw")
public class XM_APPLN_KHFW extends BusinessModel {

	private static final long serialVersionUID = 1L;

	private String customer_id;
	private String mail_code;
	private String work_calls;
	private String elig_loyal;
	private String id_verify;
	private String idcp_yn;
	private String imp_cuflag;
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public String getMail_code() {
		return mail_code;
	}
	public void setMail_code(String mail_code) {
		this.mail_code = mail_code;
	}
	public String getWork_calls() {
		return work_calls;
	}
	public void setWork_calls(String work_calls) {
		this.work_calls = work_calls;
	}
	public String getElig_loyal() {
		return elig_loyal;
	}
	public void setElig_loyal(String elig_loyal) {
		this.elig_loyal = elig_loyal;
	}
	public String getId_verify() {
		return id_verify;
	}
	public void setId_verify(String id_verify) {
		this.id_verify = id_verify;
	}
	public String getIdcp_yn() {
		return idcp_yn;
	}
	public void setIdcp_yn(String idcp_yn) {
		this.idcp_yn = idcp_yn;
	}
	public String getImp_cuflag() {
		return imp_cuflag;
	}
	public void setImp_cuflag(String imp_cuflag) {
		this.imp_cuflag = imp_cuflag;
	}
	
}
