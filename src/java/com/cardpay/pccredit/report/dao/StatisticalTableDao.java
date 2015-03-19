package com.cardpay.pccredit.report.dao;

import org.apache.ibatis.annotations.Param;

import com.wicresoft.util.annotation.Mapper;

/**
 * @author chenzhifang
 *
 * 2014-11-27下午4:55:15
 */
@Mapper
public interface StatisticalTableDao {

	public void statisticalTableInfo(@Param("dateStr") String dateStr);
}
