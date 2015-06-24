package com.cardpay.pccredit.report.model;

import com.wicresoft.jrad.base.database.model.BusinessModel;

/**
 * @author 池能和
 *
 * 2015-6-11下午5:02:29
 */
public class ZXQueryForOrg extends BusinessModel{
	private static final long serialVersionUID = 1L;
	
	private String rowIndex;
	
	private String orgName;
	
	private String count;

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getRowIndex() {
		return rowIndex;
	}

	public void setRowIndex(String rowIndex) {
		this.rowIndex = rowIndex;
	}
	

}
