/**
 * 
 */
package com.cardpay.pccredit.report.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cardpay.pccredit.report.filter.UserDefFilter;
import com.cardpay.pccredit.report.model.IntelligentAccountReport;
import com.cardpay.pccredit.report.model.IntelligentAccountReport2;
import com.cardpay.pccredit.report.model.IntelligentCustomerReport;
import com.cardpay.pccredit.report.model.PostLoanManagementData;
import com.wicresoft.jrad.base.database.dao.business.BusinessFilter;
import com.wicresoft.jrad.base.database.model.QueryResult;
import com.wicresoft.util.annotation.Mapper;

/**
 * @author shaoming
 *
 * 2014年12月22日   下午3:28:30
 */
@Mapper
public interface IntelligentReportDao {
	
	List<IntelligentCustomerReport> findIntelligentCustomerReport();
	
	List<IntelligentAccountReport2> findIntelligentAccountReportAll(UserDefFilter filter);
	List<IntelligentAccountReport2> findIntelligentAccountReport(UserDefFilter filter);
	int findIntelligentAccountReportCount();

	PostLoanManagementData findPostLoanManagementData();
}
