package com.cardpay.pccredit.report.filter;

import java.util.Date;

import com.wicresoft.jrad.base.web.filter.BaseQueryFilter;

/**
 * @author chenzhifang
 *
 * 2014-11-27下午4:29:53
 */
public class UserDefFilter extends BaseQueryFilter {
	private String userDef;

	public String getUserDef() {
		return userDef;
	}

	public void setUserDef(String userDef) {
		this.userDef = userDef;
	}

	
}
