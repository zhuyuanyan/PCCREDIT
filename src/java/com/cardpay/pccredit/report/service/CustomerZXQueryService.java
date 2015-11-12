package com.cardpay.pccredit.report.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cardpay.pccredit.report.dao.CustomerZXQueryDao;
import com.cardpay.pccredit.report.filter.StatisticalFilter;
import com.cardpay.pccredit.report.filter.ZXQueryFilter;
import com.cardpay.pccredit.report.model.CreditPayment;
import com.cardpay.pccredit.report.model.ZXQueryForOrg;

/**
 * 征信查询统计统计
 * @author 池能和
 *
 * 2015-6-11下午4:51:59
 */
@Service
public class CustomerZXQueryService {
	@Autowired
	private CustomerZXQueryDao customerzxqueryDao;
	/**
     * 统计机构号征信查询情况
     * @param filter
     * @return
     */
	public List<ZXQueryForOrg> getzxqueryrecord(StatisticalFilter filter){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("HHmmss");
		//表表结束日期要精确到秒
		Date now = new Date();
		ZXQueryFilter zxq = new ZXQueryFilter();
		zxq.setProductId(filter.getProductId());
		zxq.setOrgId(filter.getOrgId());
		zxq.setBasicDate(sdf.format(filter.getBasicDate()));
		zxq.setReportDate(sdf.format(filter.getReportDate()) + sdf1.format(now));
		return customerzxqueryDao.getZXQueryforOrg(zxq);
	}
	/**
     * 统计客户经理征信查询情况
     * @param filter
     * @return
     */
	public List<ZXQueryForOrg> getzxqueryrecordformgr(StatisticalFilter filter){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("HHmmss");
		//表表结束日期要精确到秒
		Date now = new Date();
		ZXQueryFilter zxq = new ZXQueryFilter();
		zxq.setProductId(filter.getProductId());
		zxq.setOrgId(filter.getOrgId());
		zxq.setBasicDate(sdf.format(filter.getBasicDate()));
		zxq.setReportDate(sdf.format(filter.getReportDate()) + sdf1.format(now));
		System.out.println(zxq.getProductId()+"_" +zxq.getOrgId()+"_"+zxq.getBasicDate()+"_"+zxq.getReportDate());
		return customerzxqueryDao.getzxqueryrecordformgr(zxq);
	}
}
