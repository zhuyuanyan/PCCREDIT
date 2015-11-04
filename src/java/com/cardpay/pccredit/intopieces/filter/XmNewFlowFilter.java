package com.cardpay.pccredit.intopieces.filter;

import com.wicresoft.jrad.base.web.filter.BaseQueryFilter;

/**
 * 产品过滤条件
 * 
 * @author 王海东
 * @created on 2014-10-11
 * 
 * @version $Id:$
 */
public class XmNewFlowFilter extends BaseQueryFilter {

	private String flowName;

	public String getFlowName() {
		return flowName;
	}

	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}

}
