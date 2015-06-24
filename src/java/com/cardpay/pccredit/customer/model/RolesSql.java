package com.cardpay.pccredit.customer.model;

import com.wicresoft.jrad.base.database.model.BusinessModel;
import com.wicresoft.jrad.base.database.model.ModelParam;

/**
 * @author chinenghe
 *
 * 2015-6-10下午8:46:41
 */
@ModelParam(table = "role_sql")
public class RolesSql extends BusinessModel {
	private static final long serialVersionUID = 1L;
	
	private String rolecode;
	
	private String status;
	
	private String rolesql;
	
	private String detail;

	public String getRolecode() {
		return rolecode;
	}

	public void setRolecode(String roleCode) {
		this.rolecode = roleCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	public String getRolesql() {
		return rolesql;
	}

	public void setRolesql(String roleSql) {
		this.rolesql = roleSql;
	}
}
