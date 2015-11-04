package com.cardpay.pccredit.intopieces.model;

import com.wicresoft.jrad.base.database.model.BusinessModel;
import com.wicresoft.jrad.base.database.model.ModelParam;

@ModelParam(table = "xm_new_sq_log")
public class XmNewSqLog extends BusinessModel {
	/**
	 * 商圈流程日志
	 */
	private static final long serialVersionUID = -8470111754965975277L;
	private String sqId;
	private String reviewId;
	private String reviewNodeName;
	private String customerType;
	private String customerLevel;
	private String ed;
	private String resultType;
	public String getReviewId() {
		return reviewId;
	}
	public void setReviewId(String reviewId) {
		this.reviewId = reviewId;
	}
	public String getReviewNodeName() {
		return reviewNodeName;
	}
	public void setReviewNodeName(String reviewNodeName) {
		this.reviewNodeName = reviewNodeName;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	public String getCustomerLevel() {
		return customerLevel;
	}
	public void setCustomerLevel(String customerLevel) {
		this.customerLevel = customerLevel;
	}
	public String getEd() {
		return ed;
	}
	public void setEd(String ed) {
		this.ed = ed;
	}
	public String getSqId() {
		return sqId;
	}
	public void setSqId(String sqId) {
		this.sqId = sqId;
	}
	public String getResultType() {
		return resultType;
	}
	public void setResultType(String resultType) {
		this.resultType = resultType;
	}
	
}