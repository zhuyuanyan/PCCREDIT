package com.cardpay.pccredit.report.web;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
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

import com.cardpay.pccredit.product.filter.ProductFilter;
import com.cardpay.pccredit.product.model.ProductAttribute;
import com.cardpay.pccredit.product.service.ProductService;
import com.cardpay.pccredit.report.filter.StatisticalFilter;
import com.cardpay.pccredit.report.model.CreditPayment;
import com.cardpay.pccredit.report.model.ZXQueryForOrg;
import com.cardpay.pccredit.report.service.CustomerZXQueryService;
import com.wicresoft.jrad.base.auth.JRadModule;
import com.wicresoft.jrad.base.auth.JRadOperation;
import com.wicresoft.jrad.base.database.model.QueryResult;
import com.wicresoft.jrad.base.web.JRadModelAndView;
import com.wicresoft.jrad.base.web.controller.BaseController;
import com.wicresoft.util.date.DateHelper;
import com.wicresoft.util.spring.mvc.mv.AbstractModelAndView;

/**
 * 征信查询记录统计
 * @author 池能和
 *
 * 2015-5-11下午3:48:42
 */
@Controller
@RequestMapping("/report/customerzxquery/*")
@JRadModule("report.customerzxquery")
public class CustomerZXQueryController extends BaseController {
	
	@Autowired
	private CustomerZXQueryService customerzxqueryservice;
	
	@Autowired
	private ProductService productService;
	
	/**
	 * 浏览机构征信查询统计
	 * 
	 * @param filter
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "browse.page", method = { RequestMethod.GET })
	@JRadOperation(JRadOperation.BROWSE)
	public AbstractModelAndView browse(@ModelAttribute StatisticalFilter filter, HttpServletRequest request) {
		filter.setRequest(request);
		
		ProductFilter pFilter = new ProductFilter();
		pFilter.setLimit(Integer.MAX_VALUE);
		QueryResult<ProductAttribute> qs = productService.findProductsByFilter(pFilter);
		
		if(StringUtils.isEmpty(filter.getProductId())){
			filter.setProductId(qs.getItems().size() != 0 ? qs.getItems().get(0).getId() : "");
		}
		
		if(filter.getBasicDate() == null){
			filter.setBasicDate(DateHelper.getDateFormat("2013-08-01", "yyyy-MM-dd"));
		}
		if(filter.getReportDate() == null){
			filter.setReportDate(new Date());
		}
		if(filter.getOrgId()==null ||filter.getOrgId().equals("-1")){
			filter.setOrgId(null);
		}
		List<ZXQueryForOrg> list = customerzxqueryservice.getzxqueryrecord(filter);
		JRadModelAndView mv = new JRadModelAndView("/report/zxqueryreport/customer_orgzxquery_browse", request);
		mv.addObject("list", list);
		mv.addObject("filter", filter);
		
		mv.addObject(PAGED_RESULT, qs);
		return mv;
	}
	
	/**
	 * 浏览机构征信查询统计
	 * 导出excel
	 * @param filter
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "export.page", method = { RequestMethod.GET })
	@JRadOperation(JRadOperation.BROWSE)
	public void export(@ModelAttribute StatisticalFilter filter, HttpServletRequest request,HttpServletResponse response) {
		filter.setRequest(request);
		
		ProductFilter pFilter = new ProductFilter();
		pFilter.setLimit(Integer.MAX_VALUE);
		QueryResult<ProductAttribute> qs = productService.findProductsByFilter(pFilter);
		
		if(StringUtils.isEmpty(filter.getProductId())){
			filter.setProductId(qs.getItems().size() != 0 ? qs.getItems().get(0).getId() : "");
		}
		
		if(filter.getBasicDate() == null){
			filter.setBasicDate(DateHelper.getDateFormat("2013-08-01", "yyyy-MM-dd"));
		}
		if(filter.getReportDate() == null){
			filter.setReportDate(new Date());
		}
		if(filter.getOrgId()==null ||filter.getOrgId().equals("-1")){
			filter.setOrgId(null);
		}
		List<ZXQueryForOrg> list = customerzxqueryservice.getzxqueryrecord(filter);
		create(list, response,filter);
	}
	
	public void create(List<ZXQueryForOrg> list,HttpServletResponse response,StatisticalFilter filter){
		// 第一步，创建一个webbook，对应一个Excel文件  
		HSSFWorkbook wb = new HSSFWorkbook();  
      // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
		HSSFSheet sheet = wb.createSheet("机构征信查询统计");  
      // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
		HSSFRow row = sheet.createRow((int) 0);  
      // 第四步，创建单元格，并设置值表头 设置表头居中  
		HSSFCellStyle style = wb.createCellStyle();  
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  

		HSSFCell cell = row.createCell((short) 0);  
		cell.setCellValue("机构名称");  
		cell.setCellStyle(style);  
		cell = row.createCell((short) 1);  
		cell.setCellValue("征信查询次数");  
		cell.setCellStyle(style);  
		cell = row.createCell((short) 2);  
		cell.setCellValue("报表开始时间");  
		cell.setCellStyle(style);  
		cell = row.createCell((short) 3);  
		cell.setCellValue("报表结束时间");  
		cell.setCellStyle(style);  
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	    for (int i = 0; i < list.size(); i++)  
	    {  
	       row = sheet.createRow((int) i + 1);
	       row.createCell((short) 0).setCellValue((String) list.get(i).getOrgName());
	       row.createCell((short) 1).setCellValue((String) list.get(i).getCount());
	       String basicDate  = format.format(filter.getBasicDate());
           row.createCell((short) 2).setCellValue((String) basicDate);
           row.createCell((short) 3).setCellValue((String) format.format(filter.getReportDate()));
	    }
	    String fileName = "机构征信查询统计报表";
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
	/**
	 * 浏览机构征信查询统计
	 * 
	 * @param filter
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "mgrbrowse.page", method = { RequestMethod.GET })
	@JRadOperation(JRadOperation.BROWSE)
	public AbstractModelAndView mgrbrowse(@ModelAttribute StatisticalFilter filter, HttpServletRequest request) {
		filter.setRequest(request);
		
		ProductFilter pFilter = new ProductFilter();
		pFilter.setLimit(Integer.MAX_VALUE);
		QueryResult<ProductAttribute> qs = productService.findProductsByFilter(pFilter);
		
		if(StringUtils.isEmpty(filter.getProductId())){
			filter.setProductId(qs.getItems().size() != 0 ? qs.getItems().get(0).getId() : "");
		}
		
		if(filter.getBasicDate() == null){
			filter.setBasicDate(DateHelper.getDateFormat("2013-08-01", "yyyy-MM-dd"));
		}
		if(filter.getReportDate() == null){
			filter.setReportDate(new Date());
		}
		if(filter.getOrgId()==null ||filter.getOrgId().equals("-1")){
			filter.setOrgId(null);
		}
		List<ZXQueryForOrg> list = customerzxqueryservice.getzxqueryrecordformgr(filter);
		JRadModelAndView mv = new JRadModelAndView("/report/zxqueryreport/customer_mgrzxquery_browse", request);
		mv.addObject("list", list);
		mv.addObject("filter", filter);
		
		mv.addObject(PAGED_RESULT, qs);
		return mv;
	}
	
	/**
	 * 浏览机客户经理征信查询统计
	 * 导出excel
	 * @param filter
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "mgrexport.page", method = { RequestMethod.GET })
	@JRadOperation(JRadOperation.BROWSE)
	public void mgrexport(@ModelAttribute StatisticalFilter filter, HttpServletRequest request,HttpServletResponse response) {
		filter.setRequest(request);
		
		ProductFilter pFilter = new ProductFilter();
		pFilter.setLimit(Integer.MAX_VALUE);
		QueryResult<ProductAttribute> qs = productService.findProductsByFilter(pFilter);
		
		if(StringUtils.isEmpty(filter.getProductId())){
			filter.setProductId(qs.getItems().size() != 0 ? qs.getItems().get(0).getId() : "");
		}
		
		if(filter.getBasicDate() == null){
			filter.setBasicDate(DateHelper.getDateFormat("2013-08-01", "yyyy-MM-dd"));
		}
		if(filter.getReportDate() == null){
			filter.setReportDate(new Date());
		}
		if(filter.getOrgId()==null ||filter.getOrgId().equals("-1")){
			filter.setOrgId(null);
		}
		List<ZXQueryForOrg> list = customerzxqueryservice.getzxqueryrecordformgr(filter);
		mgrcreate(list, response,filter);
	}
	
	public void mgrcreate(List<ZXQueryForOrg> list,HttpServletResponse response,StatisticalFilter filter){
		// 第一步，创建一个webbook，对应一个Excel文件  
		HSSFWorkbook wb = new HSSFWorkbook();  
      // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
		HSSFSheet sheet = wb.createSheet("客户经理征信查询统计");  
      // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
		HSSFRow row = sheet.createRow((int) 0);  
      // 第四步，创建单元格，并设置值表头 设置表头居中  
		HSSFCellStyle style = wb.createCellStyle();  
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  

		HSSFCell cell = row.createCell((short) 0);  
		cell.setCellValue("客户经理名称");  
		cell.setCellStyle(style);  
		cell = row.createCell((short) 1);  
		cell.setCellValue("征信查询次数");  
		cell.setCellStyle(style);  
		cell = row.createCell((short) 2);  
		cell.setCellValue("报表开始时间");  
		cell.setCellStyle(style);  
		cell = row.createCell((short) 3);  
		cell.setCellValue("报表结束时间");  
		cell.setCellStyle(style);  
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	    for (int i = 0; i < list.size(); i++)  
	    {  
	       row = sheet.createRow((int) i + 1);
	       row.createCell((short) 0).setCellValue((String) list.get(i).getOrgName());
	       row.createCell((short) 1).setCellValue((String) list.get(i).getCount());
	       String basicDate  = format.format(filter.getBasicDate());
           row.createCell((short) 2).setCellValue((String) basicDate);
           row.createCell((short) 3).setCellValue((String) format.format(filter.getReportDate()));
	    }
	    String fileName = "客户经理征信查询统计报表";
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
