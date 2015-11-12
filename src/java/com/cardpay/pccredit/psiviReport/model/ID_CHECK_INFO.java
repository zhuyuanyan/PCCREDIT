package com.cardpay.pccredit.psiviReport.model;

import com.cardpay.pccredit.pbccrcReport.model.AbstractCustomerInfo;
import com.wicresoft.jrad.base.database.model.BusinessModel;
import com.wicresoft.jrad.base.database.model.ModelParam;

/**
 * 身份核查信息结果表
 * @author chenzhifang
 *
 * 2015-6-16下午3:51:30
 */
@ModelParam(table = "ID_CHECK_INFO")
public class ID_CHECK_INFO extends BusinessModel{
	private static final long serialVersionUID = 1L;
	
	private String card_name;//客户名称
	
	private String customer_id;//客户id
	
	private String card_no;//身份证号
	
	private String card_office;//签发机关
	
	private String card_checkResult;//核查结果

	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}

	public String getCard_office() {
		return card_office;
	}

	public void setCard_office(String card_office) {
		this.card_office = card_office;
	}

	public String getCard_checkResult() {
		return card_checkResult;
	}

	public void setCard_checkResult(String card_checkResult) {
		this.card_checkResult = card_checkResult;
	}

	public String getCard_no() {
		return card_no;
	}

	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}

	public String getCard_name() {
		return card_name;
	}

	public void setCard_name(String card_name) {
		this.card_name = card_name;
	}
}
