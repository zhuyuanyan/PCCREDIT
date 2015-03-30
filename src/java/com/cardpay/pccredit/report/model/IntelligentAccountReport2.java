/**
 * 
 */
package com.cardpay.pccredit.report.model;

/**
 * @author tanwh
 *
 * 2014年12月22日   下午4:55:37
 */
public class IntelligentAccountReport2 {
	private String card_number;//卡号
	private String is_new_customer;
	private String principal_overdraft;//透支本金
	private String interest_receivable;//应付利息
	private String total_amount_overdraft;//透支总额
	private String recent_lowest_bill;//上期最低应交款
	
	public String getCard_number() {
		return card_number;
	}
	public void setCard_number(String card_number) {
		this.card_number = card_number;
	}
	public String getIs_new_customer() {
		return is_new_customer;
	}
	public void setIs_new_customer(String is_new_customer) {
		this.is_new_customer = is_new_customer;
	}
	public String getPrincipal_overdraft() {
		return principal_overdraft;
	}
	public void setPrincipal_overdraft(String principal_overdraft) {
		this.principal_overdraft = principal_overdraft;
	}
	public String getInterest_receivable() {
		return interest_receivable;
	}
	public void setInterest_receivable(String interest_receivable) {
		this.interest_receivable = interest_receivable;
	}
	public String getTotal_amount_overdraft() {
		return total_amount_overdraft;
	}
	public void setTotal_amount_overdraft(String total_amount_overdraft) {
		this.total_amount_overdraft = total_amount_overdraft;
	}
	public String getRecent_lowest_bill() {
		return recent_lowest_bill;
	}
	public void setRecent_lowest_bill(String recent_lowest_bill) {
		this.recent_lowest_bill = recent_lowest_bill;
	}
}
