package com.cardpay.pccredit.intopieces.dao;

import java.util.List;

import com.cardpay.pccredit.intopieces.filter.ModelParamFilter;
import com.cardpay.pccredit.intopieces.filter.XmNewTeFilter;
import com.cardpay.pccredit.intopieces.model.ModelParamConfigure;
import com.cardpay.pccredit.intopieces.model.XmNewTe;
import com.wicresoft.util.annotation.Mapper;

@Mapper
public interface XmNewTeDao {
	public List<XmNewTe> findAllTeByFilter(XmNewTeFilter filter);
	public int findAllTeCountByFilter(XmNewTeFilter filter);
	
	public List<ModelParamConfigure> findModelParamFilter(ModelParamFilter filter);
	public int  findModelParamCountFilter(ModelParamFilter filter);
}
