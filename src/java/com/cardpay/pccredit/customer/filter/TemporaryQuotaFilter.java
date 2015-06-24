package com.cardpay.pccredit.customer.filter;

import com.wicresoft.jrad.base.web.filter.BaseQueryFilter;

/**
 * 
 * 描述 ：普通临时额度filter
 * @author 池能和
 *
 * 2014-11-5 上午11:49:26
 */
public class TemporaryQuotaFilter extends BaseQueryFilter{
	private String orgId;
	private String productId;
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
}
