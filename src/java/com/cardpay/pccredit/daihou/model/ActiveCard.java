package com.cardpay.pccredit.daihou.model;

import com.wicresoft.jrad.base.database.model.BusinessModel;

public class ActiveCard extends BusinessModel{
	private static final long serialVersionUID = 1L;
	
	private String customerid;
	private String chinesename;
	private String product;
	private String cardid;//card_information表的id
	private String cardnumber;//card_information表的CARD_NUMBER
	private String cardactivatedate;
	private String expiredate;//到期日
	public String getCustomerid() {
		return customerid;
	}
	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}
	public String getChinesename() {
		return chinesename;
	}
	public void setChinesename(String chinesename) {
		this.chinesename = chinesename;
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
	public String getCardnumber() {
		return cardnumber;
	}
	public void setCardnumber(String cardnumber) {
		this.cardnumber = cardnumber;
	}
	public String getCardactivatedate() {
		return cardactivatedate;
	}
	public void setCardactivatedate(String cardactivatedate) {
		this.cardactivatedate = cardactivatedate;
	}
	public String getExpiredate() {
		return expiredate;
	}
	public void setExpiredate(String expiredate) {
		this.expiredate = expiredate;
	}
}
