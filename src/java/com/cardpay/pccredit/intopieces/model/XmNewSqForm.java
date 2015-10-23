package com.cardpay.pccredit.intopieces.model;

import com.wicresoft.jrad.base.web.form.BaseForm;

public class XmNewSqForm extends BaseForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8470111754965975277L;
	private String orgId;
	private String orgName;
	private String userId;
	private String userName;
	private String productId;
	private String productName;
	private String sqName;
	private String remark;
	private String customerType;
	private String customerLevel;
	private String customerTypeCode;
	private String customerLevelCode;
	private String ed;
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getSqName() {
		return sqName;
	}
	public void setSqName(String sqName) {
		this.sqName = sqName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public String getCustomerTypeCode() {
		return customerTypeCode;
	}
	public void setCustomerTypeCode(String customerTypeCode) {
		this.customerTypeCode = customerTypeCode;
	}
	public String getCustomerLevelCode() {
		return customerLevelCode;
	}
	public void setCustomerLevelCode(String customerLevelCode) {
		this.customerLevelCode = customerLevelCode;
	}

}