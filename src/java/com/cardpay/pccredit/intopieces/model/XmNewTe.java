package com.cardpay.pccredit.intopieces.model;

import com.wicresoft.jrad.base.database.model.BusinessModel;
import com.wicresoft.jrad.base.database.model.ModelParam;

/**
 * 客户调额表
 * @author songchen
 */
@ModelParam(table = "xm_new_te")
public class XmNewTe extends BusinessModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8470111754965975277L;
	 private String id;           
	 private String custId;      
	 private String custName;    
	 private String certiCode;   
	 private String productId;   
	 private String productName; 
	 private String teType;      
	 private String teRule;      
	 private String status;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}