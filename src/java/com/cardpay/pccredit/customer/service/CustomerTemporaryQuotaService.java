package com.cardpay.pccredit.customer.service;

import java.io.InputStream;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.*;

import oracle.jdbc.driver.OracleResultSet;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cardpay.pccredit.customer.dao.comdao.CustomerTemporaryQuotaComdao;
import com.cardpay.pccredit.customer.filter.TemporaryQuotaFilter;
import com.cardpay.pccredit.customer.model.RolesSql;
import com.cardpay.pccredit.customer.model.TemporaryQuotaRoles;
import com.cardpay.pccredit.customer.web.*;
import com.wicresoft.jrad.base.database.dao.common.CommonDao;
import com.wicresoft.jrad.base.database.model.QueryResult;

/**
 * 
 * 描述 ：普卡临时额度评估service
 * @author 池能和
 *
 * 2015-06-9 下午5:28:12
 */
@Service
public class CustomerTemporaryQuotaService {
	
	/**
	 * 普卡临时额度评估查询数据
	 * @param TemporaryQuotaFilter
	 * @throws SQLException 
	 */
	@Autowired
	private CommonDao commonDao;
	
	@Autowired
	private CustomerTemporaryQuotaComdao customerTemporaryQuotaComdao;
	
	public List<CustomerTemporaryquotaForm> queryTemporaryquota(TemporaryQuotaFilter filter){
		List<CustomerTemporaryquotaForm> queryList = customerTemporaryQuotaComdao.queryTemporaryquota(filter);
		return queryList;
	}
}
