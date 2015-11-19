package com.cardpay.pccredit.customer.model;

import com.wicresoft.jrad.base.database.id.IDType;
import com.wicresoft.jrad.base.database.model.BusinessModel;
import com.wicresoft.jrad.base.database.model.ModelParam;

/**
 * 
 * @author songchen
 * 营销人员历史表
 *
 */
@ModelParam(table = "CUSTMANAGER_TRANSFER_HISTORY",generator=IDType.assigned)
public class CUSTMANAGER_TRANSFER_HISTORY extends BusinessModel{
	private static final long serialVersionUID = 1L;
	private String  customer_id;          
	private String  productId;
	private String  jp_userid;             
	private String   yx_userid;
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getJp_userid() {
		return jp_userid;
	}
	public void setJp_userid(String jp_userid) {
		this.jp_userid = jp_userid;
	}
	public String getYx_userid() {
		return yx_userid;
	}
	public void setYx_userid(String yx_userid) {
		this.yx_userid = yx_userid;
	}
	
	
	
}
