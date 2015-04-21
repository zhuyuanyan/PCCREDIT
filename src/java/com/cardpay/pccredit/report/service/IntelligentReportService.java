/**
 * 
 */
package com.cardpay.pccredit.report.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cardpay.pccredit.customer.web.AmountAdjustmentForm;
import com.cardpay.pccredit.report.dao.IntelligentReportDao;
import com.cardpay.pccredit.report.filter.UserDefFilter;
import com.cardpay.pccredit.report.model.IntelligentAccountReport;
import com.cardpay.pccredit.report.model.IntelligentAccountReport2;
import com.cardpay.pccredit.report.model.IntelligentCustomerReport;
import com.cardpay.pccredit.report.model.PostLoanManagementData;
import com.wicresoft.jrad.base.database.dao.business.BusinessFilter;
import com.wicresoft.jrad.base.database.model.QueryResult;

/**
 * @author shaoming
 *
 * 2014年12月22日   下午3:22:19
 */
@Service
public class IntelligentReportService {

	@Autowired
	private IntelligentReportDao intelligentReportDao;
	
	/*客户信息智能报表*/
	public List<IntelligentCustomerReport> findIntelligentCustomerReport(){

		return intelligentReportDao.findIntelligentCustomerReport();		
	}
	
	/*客户信息智能报表-分页*/
	public QueryResult<IntelligentAccountReport2> findIntelligentAccountReport(UserDefFilter filter){
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH)+1;
		int year = cal.get(Calendar.YEAR);
		int lastMonth = cal.get((Calendar.MONTH));
		int lastYear = cal.get(Calendar.YEAR);
		if(lastMonth==0){
			lastMonth = 12;
			lastYear -= 1; 
		}
		
		List<IntelligentAccountReport2> ls = intelligentReportDao.findIntelligentAccountReport(filter);
		int size = intelligentReportDao.findIntelligentAccountReportCount();
		QueryResult<IntelligentAccountReport2> qs = new QueryResult<IntelligentAccountReport2>(size, ls);
		return qs;
	}
	
	/*客户信息智能报表-全部*/
	public List<IntelligentAccountReport2> findIntelligentAccountReportAll(UserDefFilter filter){
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH)+1;
		int year = cal.get(Calendar.YEAR);
		int lastMonth = cal.get((Calendar.MONTH));
		int lastYear = cal.get(Calendar.YEAR);
		if(lastMonth==0){
			lastMonth = 12;
			lastYear -= 1; 
		}
		return intelligentReportDao.findIntelligentAccountReportAll(filter);	
	}
	
	/*贷后管理数据*/
	public PostLoanManagementData findPostLoanManagementData(){

		return intelligentReportDao.findPostLoanManagementData();		
	}
}
