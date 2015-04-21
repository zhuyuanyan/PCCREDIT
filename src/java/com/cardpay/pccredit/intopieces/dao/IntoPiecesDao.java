package com.cardpay.pccredit.intopieces.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cardpay.pccredit.intopieces.filter.IntoPiecesCardQueryFilter;
import com.cardpay.pccredit.intopieces.model.IntoPiecesCardQuery;
import com.wicresoft.jrad.base.database.model.QueryResult;
import com.wicresoft.util.annotation.Mapper;

@Mapper
public interface IntoPiecesDao {
	public int checkValue(@Param("userId")String userId,@Param("valueType")String valueType);
	
	//获得制卡结果
	List<IntoPiecesCardQueryFilter> cardQuery(IntoPiecesCardQueryFilter filter);
	
	int cardQueryCount(IntoPiecesCardQueryFilter filter);
	
	int nowCount(String approveDate);
	
	//获得未发送至服务器数据
	List<IntoPiecesCardQuery> getResult(IntoPiecesCardQueryFilter filter);
	
	//根据身份证获取客户经理柜员号
	String getManagerId(String cardId);
}
