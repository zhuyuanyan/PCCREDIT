/*
 * Copyright 2013 Wicresoft, Inc. All rights reserved.
 */

package com.cardpay.pccredit.xm_appln.model;

import com.wicresoft.jrad.base.database.model.ModelParam;
import com.wicresoft.jrad.base.database.model.BusinessModel;

/**
 * Description of xm_appln
 * @author 宋辰
 */
@ModelParam(table = "BANK_PROD_TIME_DATA")
public class BANK_PRODUCT_TIME_DATA extends BusinessModel {

	private static final long serialVersionUID = 1L;
	  
	  private String id;
	  private String three_month_average;
	  private String credit_amount;
	  private String balance;
	  private String five_stage_classification;
	  private String card_id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getThree_month_average() {
		return three_month_average;
	}
	public void setThree_month_average(String three_month_average) {
		this.three_month_average = three_month_average;
	}
	public String getCredit_amount() {
		return credit_amount;
	}
	public void setCredit_amount(String credit_amount) {
		this.credit_amount = credit_amount;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getFive_stage_classification() {
		return five_stage_classification;
	}
	public void setFive_stage_classification(String five_stage_classification) {
		this.five_stage_classification = five_stage_classification;
	}
	public String getCard_id() {
		return card_id;
	}
	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}
	  
	  
}
