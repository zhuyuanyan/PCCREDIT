package com.cardpay.pccredit.report.filter;

import java.util.Date;

import com.wicresoft.jrad.base.web.filter.BaseQueryFilter;

/**
 * @author 池能和
 *
 * 2015-6-11下午4:29:53
 */
public class ZXQueryFilter extends BaseQueryFilter{
	// 基准日期
		private String basicDate;
		// 报表日期
		private String reportDate;
		// 产品Id
		private String productId;
		
		//选择机构
		private String orgId;

		public String getBasicDate() {
			return basicDate;
		}

		public void setBasicDate(String basicDate) {
			this.basicDate = basicDate;
		}

		public String getReportDate() {
			return reportDate;
		}

		public void setReportDate(String reportDate) {
			this.reportDate = reportDate;
		}

		public String getProductId() {
			return productId;
		}

		public void setProductId(String productId) {
			this.productId = productId;
		}

		public String getOrgId() {
			return orgId;
		}

		public void setOrgId(String orgId) {
			this.orgId = orgId;
		}

}
