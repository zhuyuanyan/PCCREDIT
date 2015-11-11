package com.cardpay.pccredit.intopieces.filter;

import com.wicresoft.jrad.base.web.filter.BaseQueryFilter;

public class XmNewTeFilter extends BaseQueryFilter{
	
	private String teType;//调额类型
	private String teRule;//调额规则
	private String userId;
	
	private String status;//状态
	private String custName;//客户姓名
	private String certiCode;//证件号码
	
	
	
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	
	
	public String getCertiCode() {
		return certiCode;
	}
	public void setCertiCode(String certiCode) {
		this.certiCode = certiCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTeType() {
		return teType;
	}
	public void setTeType(String teType) {
		this.teType = teType;
	}
	public String getTeRule() {
		return teRule;
	}
	public void setTeRule(String teRule) {
		this.teRule = teRule;
	}


	

}
