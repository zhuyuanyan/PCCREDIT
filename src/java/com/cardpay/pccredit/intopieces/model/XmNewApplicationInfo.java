package com.cardpay.pccredit.intopieces.model;

import com.wicresoft.jrad.base.database.id.IDType;
import com.wicresoft.jrad.base.database.model.BusinessModel;
import com.wicresoft.jrad.base.database.model.ModelParam;

@ModelParam(table = "xm_new_application_info", generator=IDType.assigned)
public class XmNewApplicationInfo  extends BusinessModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = -4785713660231246580L;

	private String sqId;

    private String status;

    private String sqFlowId;

    private String serialNumber;
    
    private String zbUser;

	public String getSqId() {
		return sqId;
	}

	public void setSqId(String sqId) {
		this.sqId = sqId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSqFlowId() {
		return sqFlowId;
	}

	public void setSqFlowId(String sqFlowId) {
		this.sqFlowId = sqFlowId;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getZbUser() {
		return zbUser;
	}

	public void setZbUser(String zbUser) {
		this.zbUser = zbUser;
	}

}