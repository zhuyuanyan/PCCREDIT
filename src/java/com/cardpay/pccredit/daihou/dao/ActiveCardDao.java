package com.cardpay.pccredit.daihou.dao;

import java.util.HashMap;

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
			sql.append("select c.*,bci.chinese_name,xa.product from ");
			sql.append("(select ci.customer_id,ci.card_number,ci.id as card_id,ci.card_activate_date,ci.expire_date from ");
			sql.append("((select * from customer_card_information where activation_status = '0')cci ");
			sql.append("left join ");	
			sql.append("card_information ci on cci.card_id = ci.id ))c,basic_customer_information bci,xm_appln xa where ");	
			sql.append("c.customer_id = bci.id and c.customer_id = xa.customer_id ");	
			
			if(StringUtils.trimToNull(chineseName)!=null){
				params.put("chineseName", chineseName);
				sql.append("and bci.chinese_name like '%'||#{chinesename}||'%' ");
			}
			if(StringUtils.trimToNull(cardId)!=null){
				params.put("cardId", cardId);
				sql.append("and bci.card_id = #{cardid} ");
			}
			sql.append("order by c.card_activate_date desc ");
			
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
		sql.append("select c.*,bci.chinese_name,xa.product from ");
		sql.append("(select ci.customer_id,ci.card_number,ci.id as card_id,ci.card_activate_date,ci.expire_date from ");
		sql.append("((select * from customer_card_information where activation_status = '0')cci ");
		sql.append("left join ");	
		sql.append("card_information ci on cci.card_id = ci.id ))c,basic_customer_information bci,xm_appln xa where ");	
		sql.append("c.customer_id = bci.id and c.customer_id = xa.customer_id ");	
		
		if(StringUtils.trimToNull(chineseName)!=null){
			params.put("chineseName", chineseName);
			sql.append("and bci.chinese_name like '%'||#{chinesename}||'%' ");
		}
		if(StringUtils.trimToNull(cardId)!=null){
			params.put("cardId", cardId);
			sql.append("and bci.card_id = #{cardid} ");
		}
		
		sql.append("order by c.card_activate_date desc ");
		
		return commonDao.queryBySql(ActiveCard.class, sql.toString(), params).size();
	}
}
