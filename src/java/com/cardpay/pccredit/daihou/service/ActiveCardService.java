package com.cardpay.pccredit.daihou.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cardpay.pccredit.customer.filter.CustomerInforFilter;
import com.cardpay.pccredit.daihou.dao.ActiveCardDao;
import com.cardpay.pccredit.daihou.model.ActiveCard;
import com.cardpay.pccredit.riskControl.dao.AgrCrdXykCunegDao;
import com.cardpay.pccredit.riskControl.filter.AgrCrdXykCunegFilter;
import com.cardpay.pccredit.riskControl.model.AgrCrdXykCuneg;
import com.wicresoft.jrad.base.database.model.QueryResult;

/**
 * 黑名单数据资料
 * @author chenzhifang
 *
 * 2014-12-22下午4:22:41
 */
@Service
public class ActiveCardService {
	@Autowired
	private ActiveCardDao activeCardComdao;
	
	public QueryResult<ActiveCard> findActiveCard(CustomerInforFilter filter) {
		List<ActiveCard> datas = activeCardComdao.findActiveCard(filter);
		int size = activeCardComdao.findActiveCardCount(filter);
		QueryResult<ActiveCard> qs = new QueryResult<ActiveCard>(size, datas);
		return qs;
	}
	
}
