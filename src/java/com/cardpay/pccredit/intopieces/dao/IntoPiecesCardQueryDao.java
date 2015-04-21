package com.cardpay.pccredit.intopieces.dao;

import com.cardpay.pccredit.intopieces.model.IntoPiecesCardQuery;
import com.wicresoft.jrad.base.database.model.QueryResult;
import com.wicresoft.util.annotation.Mapper;

@Mapper
public interface IntoPiecesCardQueryDao {
	QueryResult<IntoPiecesCardQuery> queryResult(Long id);

}