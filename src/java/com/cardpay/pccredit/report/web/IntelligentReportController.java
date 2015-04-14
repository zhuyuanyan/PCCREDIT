/**
 * 
 */
package com.cardpay.pccredit.report.web;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cardpay.pccredit.report.filter.StatisticalFilter;
import com.cardpay.pccredit.report.filter.UserDefFilter;
import com.cardpay.pccredit.intopieces.filter.CustomerApplicationProcessFilter;
import com.cardpay.pccredit.intopieces.web.CustomerApplicationIntopieceWaitForm;
import com.cardpay.pccredit.report.model.IntelligentAccountReport;
import com.cardpay.pccredit.report.model.IntelligentAccountReport2;
import com.cardpay.pccredit.report.model.IntelligentCustomerReport;
import com.cardpay.pccredit.report.model.PostLoanManagementData;
import com.cardpay.pccredit.report.model.QuailManaRejectMonitor;
import com.cardpay.pccredit.report.service.IntelligentReportService;
import com.wicresoft.jrad.base.auth.JRadModule;
import com.wicresoft.jrad.base.auth.JRadOperation;
import com.wicresoft.jrad.base.database.dao.business.BusinessFilter;
import com.wicresoft.jrad.base.database.model.QueryResult;
import com.wicresoft.jrad.base.web.JRadModelAndView;
import com.wicresoft.jrad.base.web.controller.BaseController;
import com.wicresoft.jrad.base.web.result.JRadPagedQueryResult;
import com.wicresoft.util.spring.mvc.mv.AbstractModelAndView;

/**
 * 智能报表
 * @author shaoming
 *
 * 2014年12月22日   下午2:05:33
 */
@Controller
@JRadModule("report.intelligentcustomerreport")
public class IntelligentReportController extends BaseController {

	@Autowired
	private IntelligentReportService intelligentReportService;
	/**
	 * 客户信息智能报表
	 * 
	 * @param filter
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/report/intelligentcustomerreport/browse.page", method = { RequestMethod.GET })
	@JRadOperation(JRadOperation.BROWSE)
	public AbstractModelAndView browse(HttpServletRequest request) {
		JRadModelAndView mv = new JRadModelAndView("/report/intelligentreport/intelligentcustomerreport_browse", request);
		List<IntelligentCustomerReport> result = intelligentReportService.findIntelligentCustomerReport();
		mv.addObject("result", result);
		return mv;
	}
	/**
	 * 客户账户智能报表
	 * 
	 * @param filter
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/report/intelligentaccountreport/browse.page", method = { RequestMethod.GET })
	@JRadOperation(JRadOperation.BROWSE)
	public AbstractModelAndView AccountBrowse(@ModelAttribute UserDefFilter filter,HttpServletRequest request) {
		filter.setRequest(request);
		JRadModelAndView mv = new JRadModelAndView("/report/intelligentreport/intelligentaccountreport_browse", request);
		QueryResult<IntelligentAccountReport2> result =intelligentReportService.findIntelligentAccountReport(filter);
		JRadPagedQueryResult<IntelligentAccountReport2> pagedResult = new JRadPagedQueryResult<IntelligentAccountReport2>(filter, result);
		mv.addObject(PAGED_RESULT, pagedResult);
		mv.addObject("filter", filter);
		return mv;
	}
	/**
	 * 贷后管理数据
	 * 
	 * @param filter
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/report/postloanmanagementdata/browse.page", method = { RequestMethod.GET })
	@JRadOperation(JRadOperation.BROWSE)
	public AbstractModelAndView PostLoanManagementData(HttpServletRequest request) {
		JRadModelAndView mv = new JRadModelAndView("/report/intelligentreport/postloanmanagementdata_browse", request);
		PostLoanManagementData result = intelligentReportService.findPostLoanManagementData();
		mv.addObject("result", result);
		return mv;
	}
	
	/**
	 * 客户账户智能报表
	 * 导出excel
	 * @param filter
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/report/intelligentaccountreport/export.page", method = { RequestMethod.GET })
	public void export(HttpServletRequest request,HttpServletResponse response) {
		JRadModelAndView mv = new JRadModelAndView("/report/intelligentreport/intelligentaccountreport_browse", request);
		List<IntelligentAccountReport2> result = intelligentReportService.findIntelligentAccountReportAll();
		create(result, response);
	}
	
public void create(List<IntelligentAccountReport2> list,HttpServletResponse response){
		
		// 第一步，创建一个webbook，对应一个Excel文件  
       HSSFWorkbook wb = new HSSFWorkbook();  
       // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
      HSSFSheet sheet = wb.createSheet("智能报表");  
       // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
      HSSFRow row = sheet.createRow((int) 0);  
       // 第四步，创建单元格，并设置值表头 设置表头居中  
       HSSFCellStyle style = wb.createCellStyle();  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  

       HSSFCell cell = row.createCell((short) 0);  
       cell.setCellValue("卡号");  
       cell.setCellStyle(style);  
     cell = row.createCell((short) 1);  
       cell.setCellValue("是否新增客户");  
       cell.setCellStyle(style);  
      cell = row.createCell((short) 2);  
       cell.setCellValue("账户透支本金");  
      cell.setCellStyle(style);  
       cell = row.createCell((short) 3);  
      cell.setCellValue("应付利息");  
      cell.setCellStyle(style);  
      
      cell = row.createCell((short) 4);  
      cell.setCellValue("透支总额");  
      cell.setCellStyle(style);  
      cell = row.createCell((short) 5);  
      cell.setCellValue("上期最低应交款");  
      cell.setCellStyle(style);  
      DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
      for (int i = 0; i < list.size(); i++)  
        {  
            row = sheet.createRow((int) i + 1);  
            row.createCell((short) 0).setCellValue((String) list.get(i).getCard_number());  
            row.createCell((short) 1).setCellValue((String) list.get(i).getIs_new_customer());  
            row.createCell((short) 2).setCellValue((Double) Double.parseDouble(list.get(i).getPrincipal_overdraft()==null?"0":list.get(i).getPrincipal_overdraft())); 
            row.createCell((short) 3).setCellValue((Double) Double.parseDouble(list.get(i).getInterest_receivable()==null?"0":list.get(i).getInterest_receivable())); 
            row.createCell((short) 4).setCellValue((Double) Double.parseDouble(list.get(i).getTotal_amount_overdraft()==null?"0":list.get(i).getTotal_amount_overdraft())); 
            row.createCell((short) 5).setCellValue((Double) Double.parseDouble(list.get(i).getRecent_lowest_bill()==null?"0":list.get(i).getRecent_lowest_bill())); 
        }
      String fileName = "智能报表";
      try {
    	  response.setHeader("Content-Disposition",
                 "attachment;filename="+new String(fileName.getBytes("gbk"),"iso8859-1")+".xls");
         response.setHeader("Connection", "close");
         response.setHeader("Content-Type", "application/vnd.ms-excel");
         OutputStream os=response.getOutputStream();
		wb.write(os);
		os.flush();
		os.close();


		} catch (IOException e) {
			e.printStackTrace();
		} 
}
}
