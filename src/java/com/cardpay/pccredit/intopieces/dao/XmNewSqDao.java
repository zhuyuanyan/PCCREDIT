package com.cardpay.pccredit.intopieces.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cardpay.pccredit.intopieces.filter.XmNewSqFilter;
import com.cardpay.pccredit.intopieces.model.XmNewSq;
import com.wicresoft.util.annotation.Mapper;

@Mapper
public interface XmNewSqDao {
	public XmNewSq findZaById(@Param("id")String id);
	public List<XmNewSq> findAllZaByFilter(XmNewSqFilter filter);
	public int findAllZaCountByFilter(XmNewSqFilter filter);
	public List<XmNewSq> findApplyZaByFilter(XmNewSqFilter filter);
	public int findApplyZaByFilterCount(XmNewSqFilter filter);
	public void deleteZAById(@Param("id")String id);
//	public List<QzApplnZaReturnMap> findZas();
}
