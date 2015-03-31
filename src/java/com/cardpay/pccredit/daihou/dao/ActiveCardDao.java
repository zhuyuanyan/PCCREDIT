package com.cardpay.pccredit.daihou.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cardpay.pccredit.customer.filter.CustomerInforFilter;
import com.cardpay.pccredit.daihou.model.ActiveCard;
import com.cardpay.pccredit.riskControl.filter.AccountabilityRecordFilter;
import com.cardpay.pccredit.riskControl.model.AccountabilityRecord;
import com.wicresoft.jrad.base.database.dao.common.CommonDao;
import com.wicresoft.jrad.base.database.model.QueryResult;
/**
 * 
 * @author tanwenhua 
 *
 * 2014-11-20 下午2:34:11
 */
@Service
public class ActiveCardDao {
	
	@Autowired
	private CommonDao commonDao;

	/* 激活卡查询 */
	public QueryResult<ActiveCard> findActiveCard(CustomerInforFilter filter) {
		try{
			HashMap<String, Object> params = new HashMap<String, Object>();
			String chineseName = filter.getChineseName();
			String cardId = filter.getCardId();
			
			//拼接sql
			StringBuilder sql = new StringBuilder();
			/*sql.append("select c.*,bci.chinese_name,xa.product from ");
			sql.append("(select ci.customer_id,ci.card_number,ci.id as card_id,ci.card_activate_date,ci.expire_date from ");
			sql.append("((select * from customer_card_information where activation_status = '1')cci ");
			sql.append("left join ");	
			sql.append("card_information ci on cci.card_id = ci.id ))c,basic_customer_information bci,xm_appln xa where ");	
			sql.append("c.customer_id = bci.id and c.customer_id = xa.customer_id ");	*/
			sql.append("SELECT * FROM (SELECT ROW_.*,ROWNUM ROWNUM_ FROM ( ");
			sql.append("select ci.customer_id as customerid,bci.chinese_name as chinesename,ci.id as cardid,ci.card_number as cardnumber,ci.card_activate_date as cardactivatedate,ci.expire_date as expiredate from card_information ci ");
			sql.append("left join basic_customer_information bci on ");
			sql.append("ci.customer_id = bci.id ");
			sql.append("where ci.CARD_ACTIVATE_DATE is not null ");
			
			if(StringUtils.trimToNull(chineseName)!=null){
				params.put("chineseName", chineseName);
				sql.append("and bci.chinese_name like '%'||#{chineseName}||'%' ");
			}
			if(StringUtils.trimToNull(cardId)!=null){
				params.put("cardId", cardId);
				sql.append("and bci.card_id = #{cardId} ");
			}
			sql.append("order by ci.card_activate_date desc ");
			sql.append(") ROW_ WHERE ROWNUM <= "+(filter.getLimit()*filter.getPage()+filter.getLimit())+") WHERE ROWNUM_ > "+filter.getLimit()*filter.getPage());
			
			return commonDao.queryBySqlInPagination(ActiveCard.class, sql.toString(), params,
					filter.getStart(), filter.getLimit());
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public int findActiveCardCount(CustomerInforFilter filter) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		String chineseName = filter.getChineseName();
		String cardType = filter.getCardType();
		String cardId = filter.getCardId();
		
		//拼接sql
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) as COUNT from card_information ci ");
		sql.append("left join basic_customer_information bci on ");
		sql.append("ci.customer_id = bci.id ");
		sql.append("where ci.CARD_ACTIVATE_DATE is not null ");	
		
		if(StringUtils.trimToNull(chineseName)!=null){
			params.put("chineseName", chineseName);
			sql.append("and bci.chinese_name like '%'||#{chineseName}||'%' ");
		}
		if(StringUtils.trimToNull(cardId)!=null){
			params.put("cardId", cardId);
			sql.append("and bci.card_id = #{cardId} ");
		}
		
		List<HashMap<String, Object>> list = commonDao.queryBySql(sql.toString(), params);
		if(list != null && list.size() > 0){
			BigDecimal b = (BigDecimal) list.get(0).get("COUNT");
			return b.intValue();
		}
		return 0;
	}
}
