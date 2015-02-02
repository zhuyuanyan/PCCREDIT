package com.cardpay.pccredit.xm_appln.web;

import com.wicresoft.jrad.base.web.form.BaseForm;

public class XM_APPLN_AUDIT_FORM extends BaseForm {
	public static final long serialVersionUID = 1L;
	
	private String customer_id;

	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
}
