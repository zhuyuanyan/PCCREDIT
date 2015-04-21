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
import com.wicresoft.util.annotation.Mapper;
/**
 * 
 * @author tanwenhua 
 *
 * 2014-11-20 下午2:34:11
 */
@Mapper
public interface ActiveCardDao {

	/* 激活卡查询 */
	public List<ActiveCard> findActiveCard(CustomerInforFilter filter);
	
	public int findActiveCardCount(CustomerInforFilter filter);
}
