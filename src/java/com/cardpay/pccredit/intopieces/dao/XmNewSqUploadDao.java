package com.cardpay.pccredit.intopieces.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cardpay.pccredit.intopieces.filter.AddIntoPiecesFilter;
import com.cardpay.pccredit.intopieces.filter.XmNewSqFilter;
import com.cardpay.pccredit.intopieces.model.LocalImage;
import com.cardpay.pccredit.intopieces.web.LocalImageForm;
import com.cardpay.pccredit.intopieces.web.XmNewSqUploadForm;
import com.wicresoft.util.annotation.Mapper;

@Mapper
public interface XmNewSqUploadDao {
	public List<XmNewSqUploadForm> findSqUploadList(XmNewSqFilter filter);
	public int findSqUploadListCount(XmNewSqFilter filter);
	
}