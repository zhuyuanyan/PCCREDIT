package com.cardpay.pccredit.customer.dao.comdao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cardpay.pccredit.customer.filter.CustomerMarketingFilter;
import com.cardpay.pccredit.customer.model.MarketingPlanWeb;
import com.cardpay.pccredit.manager.model.AccountManagerParameter;
import com.cardpay.pccredit.manager.model.ManagerBelongMap;
import com.wicresoft.jrad.base.database.dao.common.CommonDao;
import com.wicresoft.jrad.base.database.model.QueryResult;

@Service
public class CustomerMarketingCommDao {
	@Autowired
	private CommonDao commonDao;
	/**
	 * 得到营销计划页面信息
	 * @param filter
	 * @return
	 */
	public QueryResult<MarketingPlanWeb> findMarketingPlansByFilter(CustomerMarketingFilter filter) {
		try {
//			//查询自己以及下属的营销计划页面
//			String userSql = "select * from account_manager_parameter where id in ( select t.child_id from manager_belong_map t left join account_manager_parameter amp on amp.id = t.parent_id where amp.user_id = '"+filter.getCustomerManagerId()+"')";
//			List<AccountManagerParameter> userList = commonDao.queryBySql(AccountManagerParameter.class, userSql,null );
//			String userString = "('"+filter.getCustomerManagerId()+"',";
//			for(int i=0;i<userList.size();i++){
//				userString+="'"+userList.get(i).getUserId()+"',";
//			}
//			userString=userString.substring(0, userString.length()-1)+")";
			String userString = filter.getCustomerManagerId();
			HashMap<String,Object> params = new HashMap<String,Object>();
			StringBuffer sql = new StringBuffer();
			sql.append("select m.id,b.chinese_name,p.product_name,m.marketing_time,m.marketing_method,m.marketing_endtime,m.end_result,m.create_way,t.display_name,");
			sql.append("(select count(*) from marketing_plans_action a where a.marketing_plan_id=m.id) as count_action ");
			sql.append("from marketing_plan m ");
			sql.append("left join sys_user t ");
			sql.append("on m.customer_manager_id=t.id ");
			sql.append("left join product_attribute p ");
			sql.append("on m.product_id=p.id ");
			sql.append("left join basic_customer_information b ");
			sql.append("on m.customer_id=b.id where b.user_id ='"+userString+"'" );
			sql.append(" order by m.create_way,m.created_time desc");
			QueryResult<MarketingPlanWeb> result = commonDao.queryBySqlInPagination(MarketingPlanWeb.class, sql.toString(), params,
					filter.getStart(), filter.getLimit());
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
