package com.cardpay.pccredit.intopieces.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cardpay.pccredit.intopieces.filter.XmNewSqFilter;
import com.cardpay.pccredit.intopieces.filter.XmNewTeFilter;
import com.cardpay.pccredit.intopieces.model.XmNewSq;
import com.cardpay.pccredit.intopieces.model.XmNewTe;
import com.wicresoft.util.annotation.Mapper;

@Mapper
public interface XmNewTeDao {
	public List<XmNewTe> findAllTeByFilter(XmNewTeFilter filter);
	public int findAllTeCountByFilter(XmNewTeFilter filter);
}
