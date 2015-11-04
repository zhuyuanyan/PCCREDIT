package com.cardpay.pccredit.intopieces.model;

import com.wicresoft.jrad.base.database.model.BusinessModel;
import com.wicresoft.jrad.base.database.model.ModelParam;

@ModelParam(table = "xm_new_flow")
public class XmNewFlow extends BusinessModel {
	/**
	 * 商圈流程
	 */
	private static final long serialVersionUID = -8470111754965975277L;
	private String flowName;
	private String remark;
	public String getFlowName() {
		return flowName;
	}
	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}