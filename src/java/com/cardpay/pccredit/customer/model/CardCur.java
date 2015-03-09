package com.cardpay.pccredit.customer.model;

import com.wicresoft.jrad.base.database.model.BusinessModel;

public class CardCur extends BusinessModel{
	private static final long serialVersionUID = 1L;
	//卡号
	private String cardNbr;
	//身份证号
	private String custrNbr;
	public String getCardNbr() {
		return cardNbr;
	}
	public void setCardNbr(String cardNbr) {
		this.cardNbr = cardNbr;
	}
	public String getCustrNbr() {
		return custrNbr;
	}
	public void setCustrNbr(String custrNbr) {
		this.custrNbr = custrNbr;
	}
	
}
