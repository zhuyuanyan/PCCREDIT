/**
 * 
 */
package com.cardpay.pccredit.customer.model;

import com.wicresoft.jrad.base.web.form.BaseForm;

/**
 * @author songchen
 *
 * 2014年11月18日   下午2:58:41
 */
public class managerChageForm extends BaseForm{

	private static final long serialVersionUID = 1L;
	private String custId;//被移交客户姓名
	private String custManagerId;//原客户经理
	private String oldOrganId;//所属机构
	private String customerManagerId;//移交客户经理
	private String auditUserIds;//营销人员
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getCustManagerId() {
		return custManagerId;
	}
	public void setCustManagerId(String custManagerId) {
		this.custManagerId = custManagerId;
	}
	public String getOldOrganId() {
		return oldOrganId;
	}
	public void setOldOrganId(String oldOrganId) {
		this.oldOrganId = oldOrganId;
	}
	public String getCustomerManagerId() {
		return customerManagerId;
	}
	public void setCustomerManagerId(String customerManagerId) {
		this.customerManagerId = customerManagerId;
	}
	public String getAuditUserIds() {
		return auditUserIds;
	}
	public void setAuditUserIds(String auditUserIds) {
		this.auditUserIds = auditUserIds;
	}
	
}
