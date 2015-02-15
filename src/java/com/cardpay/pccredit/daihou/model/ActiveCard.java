package com.cardpay.pccredit.daihou.model;

import com.wicresoft.jrad.base.database.model.BusinessModel;

public class ActiveCard extends BusinessModel{
	private static final long serialVersionUID = 1L;
	
	private String customer_id;
	private String chinese_name;
	private String product;
	private String cardid;//card_information表的id
	private String card_number;//card_information表的CARD_NUMBER
	private String card_activate_date;
	private String expire_date;//到期日
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public String getChinese_name() {
		return chinese_name;
	}
	public void setChinese_name(String chinese_name) {
		this.chinese_name = chinese_name;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getCardid() {
		return cardid;
	}
	public void setCardid(String cardid) {
		this.cardid = cardid;
	}
	public String getCard_number() {
		return card_number;
	}
	public void setCard_number(String card_number) {
		this.card_number = card_number;
	}
	public String getCard_activate_date() {
		return card_activate_date;
	}
	public void setCard_activate_date(String card_activate_date) {
		this.card_activate_date = card_activate_date;
	}
	public String getExpire_date() {
		return expire_date;
	}
	public void setExpire_date(String expire_date) {
		this.expire_date = expire_date;
	}
	
}
