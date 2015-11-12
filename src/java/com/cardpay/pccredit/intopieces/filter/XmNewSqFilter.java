package com.cardpay.pccredit.intopieces.filter;

import com.wicresoft.jrad.base.web.filter.BaseQueryFilter;

public class XmNewSqFilter extends BaseQueryFilter{
	
	private String name;
	
	private String orgId;
	private String orgName;
	
	private String userId;
	
	private String status;
	
	private String applnId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getApplnId() {
		return applnId;
	}

	public void setApplnId(String applnId) {
		this.applnId = applnId;
	}

}
