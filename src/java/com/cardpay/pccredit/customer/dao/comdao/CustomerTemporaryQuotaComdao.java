package com.cardpay.pccredit.customer.dao.comdao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cardpay.pccredit.customer.filter.TemporaryQuotaFilter;
import com.cardpay.pccredit.customer.model.RolesSql;
import com.cardpay.pccredit.customer.model.TemporaryQuotaRoles;
import com.cardpay.pccredit.customer.web.CustomerTemporaryquotaForm;
import com.wicresoft.jrad.base.database.dao.common.CommonDao;
import com.wicresoft.jrad.base.database.model.QueryResult;

@Service
public class CustomerTemporaryQuotaComdao {
	
	@Autowired
	private CommonDao commonDao;
	
	public static final Logger logger = Logger.getLogger(CustomerTemporaryQuotaComdao.class);
	
	public List<CustomerTemporaryquotaForm> queryTemporaryquota(TemporaryQuotaFilter filter){
		List<CustomerTemporaryquotaForm> list = new ArrayList<CustomerTemporaryquotaForm>();
		//获取页面传入的参数
		String productId = filter.getProductId();//产品编号
		String orgId = filter.getOrgId();//机构号
		//获取普卡临时额度评估规则sql
		String sql ="";
		String subRoleSql = "";
		String roleCode;
		String[] sqlList;
		//查询规则
		String temquotasql = "select * from temporaryquotaroles where status=#{status}";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("status", "1");
		List<TemporaryQuotaRoles> temquotalist = commonDao.queryBySql(TemporaryQuotaRoles.class, temquotasql, param);
		//System.out.println(temquotalist.size());
		//System.out.println(temquotalist.get(0).getRolecode());
		for(int i=0;i<temquotalist.size();i++){
			roleCode = temquotalist.get(i).getRolecode();
			//根据规则编号查询子规则sql
			subRoleSql = "select * from role_sql r where r.rolecode =#{rolecode}";
			Map<String, Object> roleparam = new HashMap<String, Object>();
			roleparam.put("rolecode", roleCode);
			List<RolesSql>  rolesqllist = commonDao.queryBySql(RolesSql.class, subRoleSql, roleparam);
			sqlList = new String[rolesqllist.size()];
			for(int j = 0; j < rolesqllist.size(); j++){
				sqlList[j] = rolesqllist.get(j).getRolesql();
				sql += (sqlList[j]+" INTERSECT ");
			}
			sql = sql.substring(0,(sql.length()-10));
			sql += " union ";
		}
		//最终的sql
		String resultsql = sql.substring(0,(sql.length()-7));
		//System.out.println("sql语句=" + resultsql);
		Map<String, Object> params = new HashMap<String, Object>();
		String querysql = "select p.chinese_name,p.card_id,p.card_type,p.product_id,p.card_number,p.credit_amount,p.card_status_code,p.org_id,p.org_parent_name "
				+"from ("+ resultsql+") p where p.product_id = #{productId}";
		//System.out.println("查询的sql语句：" + querysql);
		params.put("productId", productId);
		
		if(StringUtils.isNotBlank(orgId)){
			querysql += " and p.org_id = #{orgId}";
			params.put("orgId", orgId);
		}
		logger.info(querysql);
		//查询符合该规则的列表
		List<CustomerTemporaryquotaForm> queryList = commonDao.queryBySql(CustomerTemporaryquotaForm.class, querysql, params);
		return queryList;
	}
}
