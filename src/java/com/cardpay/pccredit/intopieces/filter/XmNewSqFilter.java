package com.cardpay.pccredit.intopieces.filter;

import com.wicresoft.jrad.base.web.filter.BaseQueryFilter;

public class XmNewSqFilter extends BaseQueryFilter{
	
	private String name;
	
	private String orgId;
	private String orgName;
	
	private String userId;

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

}
