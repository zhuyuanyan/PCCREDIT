package com.cardpay.pccredit.report.web;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
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
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.RegionUtil;
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
import com.cardpay.pccredit.report.model.CardStatistical;
import com.cardpay.pccredit.report.service.CardStatisticalService;
import com.wicresoft.jrad.base.auth.JRadModule;
import com.wicresoft.jrad.base.auth.JRadOperation;
import com.wicresoft.jrad.base.database.model.QueryResult;
import com.wicresoft.jrad.base.web.JRadModelAndView;
import com.wicresoft.jrad.base.web.controller.BaseController;
import com.wicresoft.util.date.DateHelper;
import com.wicresoft.util.spring.mvc.mv.AbstractModelAndView;

/**
 * @author chenzhifang
 *
 * 2014-11-28上午10:09:21
 */
@Controller
@RequestMapping("/report/cardmanagerdimension/*")
@JRadModule("report.cardmanagerdimension")
public class CardStatisticalManagerDimensionController extends BaseController {
	@Autowired
	private CardStatisticalService cardStatisticalService;
	
	@Autowired
	private ProductService productService;
	
	/**
	 * 浏览“灵活金”发卡进展情况统计表（客户经理/微贷经理、营销经理维度）
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
		List<CardStatistical> list = cardStatisticalService.getManagerCardStatistical(filter);

		JRadModelAndView mv = new JRadModelAndView("/report/cardstatistical/cardstatistical_manager_browse", request);
		mv.addObject("list", list);
		mv.addObject("filter", filter);
		
		mv.addObject(PAGED_RESULT, qs);
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value = "export.page", method = { RequestMethod.GET })
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
		List<CardStatistical> list = cardStatisticalService.getManagerCardStatistical(filter);
		create(list, response,filter);
	}
	
	public void create(List<CardStatistical> list,HttpServletResponse response,StatisticalFilter filter){
		
//			// 第一步，创建一个webbook，对应一个Excel文件  
//	       HSSFWorkbook wb = new HSSFWorkbook();  
//	       // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
//	      HSSFSheet sheet = wb.createSheet("浏览“灵活金”发卡情况统计");  
//	       // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
//	      HSSFRow row = sheet.createRow((int) 0);  
//	       // 第四步，创建单元格，并设置值表头 设置表头居中  
//	       HSSFCellStyle style = wb.createCellStyle();  
//	        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
//	
//	       HSSFCell cell = row.createCell((short) 0);  
//	       cell.setCellValue("客户经理");  
//	       cell.setCellStyle(style);  
//	     cell = row.createCell((short) 1);  
//	       cell.setCellValue("基准日期");  
//	       cell.setCellStyle(style);  
//	      cell = row.createCell((short) 2);  
//	       cell.setCellValue("发卡数");  
//	      cell.setCellStyle(style);  
//	       cell = row.createCell((short) 3);  
//	      cell.setCellValue("到卡数");  
//	      cell.setCellStyle(style);  
//	      cell = row.createCell((short) 4);  
//	      cell.setCellValue("激活卡数");  
//	      cell.setCellStyle(style);  
//	      cell = row.createCell((short) 5);  
//	      cell.setCellValue("未激活卡数");  
//	      cell.setCellStyle(style);  
//	      cell = row.createCell((short) 6);  
//	      cell.setCellValue("激活率");  
//	      cell.setCellStyle(style);  
//	      cell = row.createCell((short) 7);  
//	      cell.setCellValue("发卡数");  
//	      cell.setCellStyle(style);  
//	      cell = row.createCell((short) 8);  
//	      cell.setCellValue("报表日期");  
//	      cell.setCellStyle(style);  
//	      cell = row.createCell((short) 9);  
//	      cell.setCellValue("到卡数");  
//	      cell.setCellStyle(style);  
//	      cell = row.createCell((short) 10);  
//	      cell.setCellValue("激活卡数");  
//	      cell.setCellStyle(style);  
//	      cell = row.createCell((short) 11);  
//	      cell.setCellValue("未激活卡数");  
//	      cell.setCellStyle(style);  
//	      cell = row.createCell((short) 12);  
//	      cell.setCellValue("激活率");  
//	      cell.setCellStyle(style);  
//	      cell = row.createCell((short) 13);  
//	      cell.setCellValue("发卡净增");  
//	      cell.setCellStyle(style);  
//	      cell = row.createCell((short) 14);  
//	      cell.setCellValue("到卡净增数");  
//	      cell.setCellStyle(style);  
//	      cell = row.createCell((short) 15);  
//	      cell.setCellValue("激活卡净增数");  
//	      cell.setCellStyle(style);  
//	      cell = row.createCell((short) 16);  
//	      cell.setCellValue("未激活净增卡数");  
//	      cell.setCellStyle(style);  
//	      cell = row.createCell((short) 17);  
//	      cell.setCellValue("激活率变动");  
//	      cell.setCellStyle(style);  
//	      cell = row.createCell((short) 18);  
//	      cell.setCellValue("二级支行");  
//	      cell.setCellStyle(style);  
//	      cell = row.createCell((short) 19);  
//	      cell.setCellValue("一级支行");  
//	      cell.setCellStyle(style);  
		HSSFWorkbook wb = new HSSFWorkbook();  
		HSSFSheet sheet = wb.createSheet("浏览“灵活金”发卡情况统计"); 
  
        CellRangeAddress region = new CellRangeAddress(0,1,0,0);  
        sheet.addMergedRegion(region);  
        
          
        int border = 1;   
        RegionUtil.setBorderBottom(border,region, sheet, wb);   
        RegionUtil.setBorderLeft(border,region, sheet, wb);   
        RegionUtil.setBorderRight(border,region, sheet, wb);   
        RegionUtil.setBorderTop(border,region, sheet, wb);
        
        CellRangeAddress region1 = new CellRangeAddress(0,0,1,6);  
        sheet.addMergedRegion(region1); 
        int border1 = 1;   
        RegionUtil.setBorderBottom(border1,region1, sheet, wb);   
        RegionUtil.setBorderLeft(border1,region1, sheet, wb);   
        RegionUtil.setBorderRight(border1,region1, sheet, wb);   
        RegionUtil.setBorderTop(border1,region1, sheet, wb);
        
        CellRangeAddress region2 = new CellRangeAddress(0,0,7,12);  
        sheet.addMergedRegion(region2); 
        int border2 = 1;   
        RegionUtil.setBorderBottom(border2,region2, sheet, wb);   
        RegionUtil.setBorderLeft(border2,region2, sheet, wb);   
        RegionUtil.setBorderRight(border2,region2, sheet, wb);   
        RegionUtil.setBorderTop(border2,region2, sheet, wb);
        
        CellRangeAddress region3 = new CellRangeAddress(0,0,13,17);  
        sheet.addMergedRegion(region3); 
        int border3 = 1;   
        RegionUtil.setBorderBottom(border3,region3, sheet, wb);   
        RegionUtil.setBorderLeft(border3,region3, sheet, wb);   
        RegionUtil.setBorderRight(border3,region3, sheet, wb);   
        RegionUtil.setBorderTop(border3,region3, sheet, wb);
        
        CellRangeAddress region4 = new CellRangeAddress(0,1,18,18);  
        sheet.addMergedRegion(region4); 
        int border4 = 1;   
        RegionUtil.setBorderBottom(border4,region4, sheet, wb);   
        RegionUtil.setBorderLeft(border4,region4, sheet, wb);   
        RegionUtil.setBorderRight(border4,region4, sheet, wb);   
        RegionUtil.setBorderTop(border4,region4, sheet, wb);
        
        CellRangeAddress region5 = new CellRangeAddress(0,1,19,19);  
        sheet.addMergedRegion(region5); 
        int border5 = 1;   
        RegionUtil.setBorderBottom(border5,region5, sheet, wb);   
        RegionUtil.setBorderLeft(border5,region5, sheet, wb);   
        RegionUtil.setBorderRight(border5,region5, sheet, wb);   
        RegionUtil.setBorderTop(border5,region5, sheet, wb);
        
        //创建第一行
        HSSFRow row = (HSSFRow) sheet.createRow((int)0);
        HSSFCellStyle style = (HSSFCellStyle) wb.createCellStyle();  
//        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中    
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
       HSSFCell cell = row.createCell((short) 0);  
       cell.setCellValue("客户经理");  
       cell.setCellStyle(style);
       
       cell = row.createCell((short) 1);  
       cell.setCellValue("基准日期数据");  
       cell.setCellStyle(style);
       
       cell = row.createCell((short) 7);  
       cell.setCellValue("报表日期数据");  
       cell.setCellStyle(style);
       
       cell = row.createCell((short) 13);  
       cell.setCellValue("净增数据");  
       cell.setCellStyle(style);
       
       cell = row.createCell((short) 18);  
       cell.setCellValue("所属二级支行");  
       cell.setCellStyle(style);
       
       cell = row.createCell((short) 19);  
       cell.setCellValue("所属一级支行");  
       cell.setCellStyle(style);
       
     //创建第二行
       HSSFRow row1 = (HSSFRow) sheet.createRow((int)1);
       HSSFCell cell1 = row1.createCell((short) 1);
       	
       cell1 = row1.createCell((short) 1);  
       cell1.setCellValue("基准日期");  
       cell1.setCellStyle(style);
       
       cell1 = row1.createCell((short) 2);  
       cell1.setCellValue("发卡数");  
       cell1.setCellStyle(style);
       
       cell1 = row1.createCell((short) 3);  
       cell1.setCellValue("到卡数");  
       cell1.setCellStyle(style);
       
       cell1 = row1.createCell((short) 4);  
       cell1.setCellValue("激活卡数");  
       cell1.setCellStyle(style);
       
       cell1 = row1.createCell((short) 5);  
       cell1.setCellValue("未激活卡数");  
       cell1.setCellStyle(style);
       
       cell1 = row1.createCell((short) 6);  
       cell1.setCellValue("激活率");  
       cell1.setCellStyle(style);
       
       cell1 = row1.createCell((short) 7);  
       cell1.setCellValue("报表日期");  
       cell1.setCellStyle(style);
       
       cell1 = row1.createCell((short) 8);  
       cell1.setCellValue("发卡数");  
       cell1.setCellStyle(style);
       
       cell1 = row1.createCell((short) 9);  
       cell1.setCellValue("到卡数");  
       cell1.setCellStyle(style);
       
       cell1 = row1.createCell((short) 10);  
       cell1.setCellValue("激活卡数");  
       cell1.setCellStyle(style);
       
       cell1 = row1.createCell((short) 11);  
       cell1.setCellValue("未激活卡数");  
       cell1.setCellStyle(style);
       
       cell1 = row1.createCell((short) 12);  
       cell1.setCellValue("激活率");  
       cell1.setCellStyle(style);
       
       cell1 = row1.createCell((short) 13);  
       cell1.setCellValue("发卡净增");  
       cell1.setCellStyle(style);
       
       cell1 = row1.createCell((short) 14);  
       cell1.setCellValue("到卡净增数");  
       cell1.setCellStyle(style);
       
       cell1 = row1.createCell((short) 15);  
       cell1.setCellValue("激活卡净增数");  
       cell1.setCellStyle(style);
       
       cell1 = row1.createCell((short) 16);  
       cell1.setCellValue("未激活卡净增数");  
       cell1.setCellStyle(style);
       
       cell1 = row1.createCell((short) 17);  
       cell1.setCellValue("激活率变动");  
       cell1.setCellStyle(style);
	      DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	      for (int i = 0; i < list.size(); i++)  
	        {  
	            row = sheet.createRow((int) i + 2);  
	            row.createCell((short) 0).setCellValue((String) list.get(i).getName());  
	            String basicDate  = format.format(filter.getBasicDate());
	            row.createCell((short) 1).setCellValue((String) basicDate);  
	            row.createCell((short) 2).setCellValue((String) list.get(i).getbSendCardNumber()); 
	            row.createCell((short) 3).setCellValue((String) list.get(i).getbAcceptCardNumber()); 
	            row.createCell((short) 4).setCellValue((String) list.get(i).getbActivateCardNumber()); 
	            row.createCell((short) 5).setCellValue((String) list.get(i).getbNoActivateCardNumber()); 
	            row.createCell((short) 6).setCellValue((Double) Double.parseDouble(list.get(i).getbActiveRate()==null?"0":list.get(i).getbActiveRate())+"%"); 
	            row.createCell((short) 7).setCellValue((String) list.get(i).getrSendCardNumber()); 
	            row.createCell((short) 8).setCellValue((String) format.format(filter.getReportDate())); 
	            row.createCell((short) 9).setCellValue((String) list.get(i).getrAcceptCardNumber()); 
	            row.createCell((short) 10).setCellValue((String) list.get(i).getrActivateCardNumber()); 
	            row.createCell((short) 11).setCellValue((String) list.get(i).getrNoActivateCardNumber()); 
	            row.createCell((short) 12).setCellValue((Double) Double.parseDouble(list.get(i).getrActiveRate()==null?"0":list.get(i).getrActiveRate())+"%"); 
	            row.createCell((short) 13).setCellValue((String) list.get(i).getAddSendCardNumber()); 
	            row.createCell((short) 14).setCellValue((String) list.get(i).getAddAcceptCardNumber()); 
	            row.createCell((short) 15).setCellValue((String) list.get(i).getAddActivateCardNumber()); 
	            row.createCell((short) 16).setCellValue((String) list.get(i).getAddNoActivateCardNumber()); 
	            if(list.get(i).getAddActiveRate()==null){
	            	row.createCell((short) 17).setCellValue((Double) Double.parseDouble("0")+"%"); 
	            }else{
	            	row.createCell((short) 17).setCellValue((Double) Double.parseDouble(list.get(i).getAddActiveRate())+"%"); 
	            }
	            row.createCell((short) 18).setCellValue((String) list.get(i).getOrgName()); 
	            row.createCell((short) 19).setCellValue((String) list.get(i).getOrgParentName()); 
	        }
	      String fileName = "客户经理“灵活金”发卡进展情况统计表";
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
