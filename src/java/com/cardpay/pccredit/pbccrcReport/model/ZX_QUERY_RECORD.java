package com.cardpay.pccredit.pbccrcReport.model;

import com.wicresoft.jrad.base.auth.IOrganization;
import com.wicresoft.jrad.base.database.model.BusinessModel;
import com.wicresoft.jrad.base.database.model.ModelParam;

/**
 * 征信查询记录
 * @author 池能和
 *
 * 2015-6-11下午9:51:30
 */
@ModelParam(table = "ZX_QUERY_RECORD")
public class ZX_QUERY_RECORD extends BusinessModel {
	
	private static final long serialVersionUID = 1L;
	
	private String customer_id;//客户id
	private String org_id;//机构号
	private String create_datetime;//创建时间
	private String user_id;//客户经理登录号
	
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public String getOrg_id() {
		return org_id;
	}
	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}
	public String getCreate_datetime() {
		return create_datetime;
	}
	public void setCreate_datetime(String create_datetime) {
		this.create_datetime = create_datetime;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	

}
