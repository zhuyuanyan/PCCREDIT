package com.cardpay.pccredit.report.dao;

import java.util.List;

import com.cardpay.pccredit.report.filter.ZXQueryFilter;
import com.cardpay.pccredit.report.model.ZXQueryForOrg;
import com.wicresoft.util.annotation.Mapper;

/**
 * @author chenzhifang
 *
 * 2014-11-27下午4:52:44
 * @param <ZXQueryForOrg>
 */
@Mapper
public interface CustomerZXQueryDao {

	/**
     * 机构征信查询统计
     * @param filter
     * @return
     */
	public List<ZXQueryForOrg> getZXQueryforOrg(ZXQueryFilter filter);
	/**
     * 客户经理征信查询统计
     * @param filter
     * @return
     */
	public List<ZXQueryForOrg> getzxqueryrecordformgr(ZXQueryFilter filter);
}
