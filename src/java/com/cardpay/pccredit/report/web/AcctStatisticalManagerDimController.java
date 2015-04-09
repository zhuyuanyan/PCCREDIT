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
import com.cardpay.pccredit.report.model.AcctStatistical;
import com.cardpay.pccredit.report.model.CardStatistical;
import com.cardpay.pccredit.report.service.AcctStatisticalService;
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
 * 2014-12-1上午10:02:02
 */
@Controller
@RequestMapping("/report/acctmanagerdimension/*")
@JRadModule("report.acctmanagerdimension")
public class AcctStatisticalManagerDimController extends BaseController {
	@Autowired
	private AcctStatisticalService acctStatisticalService;
	
	@Autowired
	private ProductService productService;
	
	/**
	 * 浏览“灵活金”透支情况统计表（客户经理/微贷经理、营销经理维度）（单位：万元）
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

//		ProductFilter pFilter = new ProductFilter();
//		pFilter.setLimit(Integer.MAX_VALUE);
//		QueryResult<ProductAttribute> qs = productService.findProductsByFilter(pFilter);
		
//		if(StringUtils.isEmpty(filter.getProductId())){
//			filter.setProductId(qs.getItems().size() != 0 ? qs.getItems().get(0).getId() : "");
//		}
		if(filter.getBasicDate() == null){
			filter.setBasicDate(DateHelper.getDateFormat("2013-08-01", "yyyy-MM-dd"));
		}
		if(filter.getReportDate() == null){
			filter.setReportDate(new Date());
		}
		List<AcctStatistical> list = acctStatisticalService.getManagerAcctStatistical(filter);

		JRadModelAndView mv = new JRadModelAndView("/report/acctstatistical/acctstatistical_manager_browse", request);
		mv.addObject("list", list);
		mv.addObject("filter", filter);
		
//		mv.addObject(PAGED_RESULT, qs);
		return mv;
	}
	
	/**
	 * 浏览“灵活金”透支情况统计表（客户经理/微贷经理、营销经理维度）（单位：万元）
	 * 导出excel
	 * @param filter
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "export.page", method = { RequestMethod.GET })
	public void export(@ModelAttribute StatisticalFilter filter, HttpServletRequest request,HttpServletResponse response) {
		filter.setRequest(request);

		if(filter.getBasicDate() == null){
			filter.setBasicDate(DateHelper.getDateFormat("2013-08-01", "yyyy-MM-dd"));
		}
		if(filter.getReportDate() == null){
			filter.setReportDate(new Date());
		}
		List<AcctStatistical> list = acctStatisticalService.getManagerAcctStatistical(filter);
		create(list, response,filter);
	}
	
	public void create(List<AcctStatistical> list,HttpServletResponse response,StatisticalFilter filter){
		
		// 第一步，创建一个webbook，对应一个Excel文件  
       HSSFWorkbook wb = new HSSFWorkbook();  
       // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
      HSSFSheet sheet = wb.createSheet("客户经理“灵活金”透支情况统计");  
       // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
      HSSFRow row = sheet.createRow((int) 0);  
       // 第四步，创建单元格，并设置值表头 设置表头居中  
       HSSFCellStyle style = wb.createCellStyle();  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  

       HSSFCell cell = row.createCell((short) 0);  
       cell.setCellValue("客户经理");  
       cell.setCellStyle(style);  
     cell = row.createCell((short) 1);  
       cell.setCellValue("基准日期");  
       cell.setCellStyle(style);  
      cell = row.createCell((short) 2);  
       cell.setCellValue("授信总额度");  
      cell.setCellStyle(style);  
       cell = row.createCell((short) 3);  
      cell.setCellValue("激活卡总授信额度");  
      cell.setCellStyle(style);  
      cell = row.createCell((short) 4);  
      cell.setCellValue("透支本金余额");  
      cell.setCellStyle(style);  
      cell = row.createCell((short) 5);  
      cell.setCellValue("透支总余额");  
      cell.setCellStyle(style);  
      cell = row.createCell((short) 6);  
      cell.setCellValue("年日均透支本金");  
      cell.setCellStyle(style);  
      cell = row.createCell((short) 7);  
      cell.setCellValue("年日均透支总余额");  
      cell.setCellStyle(style);  
      cell = row.createCell((short) 8);  
      cell.setCellValue("不良透支本金");  
      cell.setCellStyle(style);  
      cell = row.createCell((short) 9);  
      cell.setCellValue("不良率");  
      cell.setCellStyle(style);  
      cell = row.createCell((short) 10);  
      cell.setCellValue("报表日期");  
      cell.setCellStyle(style);  
      cell = row.createCell((short) 11);  
      cell.setCellValue("授信总额度");  
      cell.setCellStyle(style);  
      cell = row.createCell((short) 12);  
      cell.setCellValue("激活卡总授信额度");  
      cell.setCellStyle(style);  
      cell = row.createCell((short) 13);  
      cell.setCellValue("透支本金余额");  
      cell.setCellStyle(style);  
      cell = row.createCell((short) 14);  
      cell.setCellValue("透支总余额");  
      cell.setCellStyle(style);  
      cell = row.createCell((short) 15);  
      cell.setCellValue("年日均透支本金");  
      cell.setCellStyle(style);  
      cell = row.createCell((short) 16);  
      cell.setCellValue("年日均透支总余额");  
      cell.setCellStyle(style);  
      cell = row.createCell((short) 17);  
      cell.setCellValue("不良透支本金");  
      cell.setCellStyle(style);  
      cell = row.createCell((short) 18);  
      cell.setCellValue("不良率");  
      cell.setCellStyle(style);  
      cell = row.createCell((short) 19);  
      cell.setCellValue("授信总额度净增");  
      cell.setCellStyle(style);  
      cell = row.createCell((short) 20);  
      cell.setCellValue("激活卡总授信额度净增");  
      cell.setCellStyle(style);  
      cell = row.createCell((short) 21);  
      cell.setCellValue("透支本金余额净增");  
      cell.setCellStyle(style);  
      cell = row.createCell((short) 22);  
      cell.setCellValue("透支总余额净增");  
      cell.setCellStyle(style);  
      cell = row.createCell((short) 23);  
      cell.setCellValue("年日均透支本金净增");  
      cell.setCellStyle(style);  
      cell = row.createCell((short) 24);  
      cell.setCellValue("年日均透支总余额净增");  
      cell.setCellStyle(style);  
      cell = row.createCell((short) 25);  
      cell.setCellValue("不良透支本金净增");  
      cell.setCellStyle(style);  
      cell = row.createCell((short) 26);  
      cell.setCellValue("不良率变动");  
      cell.setCellStyle(style);  
      DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
      for (int i = 0; i < list.size(); i++)  
        {  
            row = sheet.createRow((int) i + 1);  
            row.createCell((short) 0).setCellValue((String) list.get(i).getName());  
            String basicDate  = format.format(filter.getBasicDate());
            row.createCell((short) 1).setCellValue((String) basicDate);  
            row.createCell((short) 2).setCellValue((Double) Double.parseDouble(list.get(i).getbCreditExtensionAmount()==null?"0":list.get(i).getbCreditExtensionAmount())); 
            row.createCell((short) 3).setCellValue((Double) Double.parseDouble(list.get(i).getbActivateExtensionAmount()==null?"0":list.get(i).getbActivateExtensionAmount())); 
            row.createCell((short) 4).setCellValue((Double) Double.parseDouble(list.get(i).getbOverdraftPrincipal()==null?"0":list.get(i).getbOverdraftPrincipal())); 
            row.createCell((short) 5).setCellValue((Double) Double.parseDouble(list.get(i).getbOverdraftAmount()==null?"0":list.get(i).getbOverdraftAmount())); 
            row.createCell((short) 6).setCellValue((Double) Double.parseDouble(list.get(i).getbOverdraftPrincipalAvg()==null?"0":list.get(i).getbOverdraftPrincipalAvg())); 
            row.createCell((short) 7).setCellValue((Double) Double.parseDouble(list.get(i).getbOverdraftAmountAvg()==null?"0":list.get(i).getbOverdraftAmountAvg())); 
            row.createCell((short) 8).setCellValue((Double) Double.parseDouble(list.get(i).getbBadOverdraftPrincipal()==null?"0":list.get(i).getbBadOverdraftPrincipal())); 
            row.createCell((short) 9).setCellValue((Double) Double.parseDouble(list.get(i).getbBadRate()==null?"0":list.get(i).getbBadRate())+"%"); 
            row.createCell((short) 10).setCellValue((String) format.format(filter.getReportDate())); 
            row.createCell((short) 11).setCellValue((Double) Double.parseDouble(list.get(i).getrCreditExtensionAmount()==null?"0":list.get(i).getrCreditExtensionAmount())); 
            row.createCell((short) 12).setCellValue((Double) Double.parseDouble(list.get(i).getrActivateExtensionAmount()==null?"0":list.get(i).getrActivateExtensionAmount())); 
            row.createCell((short) 13).setCellValue((Double) Double.parseDouble(list.get(i).getrOverdraftPrincipal()==null?"0":list.get(i).getrOverdraftPrincipal())); 
            row.createCell((short) 14).setCellValue((Double) Double.parseDouble(list.get(i).getrOverdraftAmount()==null?"0":list.get(i).getrOverdraftAmount())); 
            row.createCell((short) 15).setCellValue((Double) Double.parseDouble(list.get(i).getrOverdraftPrincipalAvg()==null?"0":list.get(i).getrOverdraftPrincipalAvg())); 
            row.createCell((short) 16).setCellValue((Double) Double.parseDouble(list.get(i).getrOverdraftAmountAvg()==null?"0":list.get(i).getrOverdraftAmountAvg())); 
            row.createCell((short) 17).setCellValue((Double) Double.parseDouble(list.get(i).getrBadOverdraftPrincipal()==null?"0":list.get(i).getrBadOverdraftPrincipal())); 
            row.createCell((short) 18).setCellValue((Double) Double.parseDouble(list.get(i).getrBadRate()==null?"0":list.get(i).getrBadRate())+"%"); 
            row.createCell((short) 19).setCellValue((Double) Double.parseDouble(list.get(i).getAddCreditExtensionAmount()==null?"0":list.get(i).getAddCreditExtensionAmount())); 
            row.createCell((short) 20).setCellValue((Double) Double.parseDouble(list.get(i).getAddActivateExtensionAmount()==null?"0":list.get(i).getAddActivateExtensionAmount())); 
            row.createCell((short) 21).setCellValue((Double) Double.parseDouble(list.get(i).getAddOverdraftPrincipal()==null?"0":list.get(i).getAddOverdraftPrincipal())); 
            row.createCell((short) 22).setCellValue((Double) Double.parseDouble(list.get(i).getAddOverdraftAmount()==null?"0":list.get(i).getAddOverdraftAmount())); 
            row.createCell((short) 23).setCellValue((Double) Double.parseDouble(list.get(i).getAddOverdraftPrincipalAvg()==null?"0":list.get(i).getAddOverdraftPrincipalAvg())); 
            row.createCell((short) 24).setCellValue((Double) Double.parseDouble(list.get(i).getAddOverdraftAmountAvg()==null?"0":list.get(i).getAddOverdraftAmountAvg())); 
            row.createCell((short) 25).setCellValue((Double) Double.parseDouble(list.get(i).getAddBadOverdraftPrincipal()==null?"0":list.get(i).getAddBadOverdraftPrincipal())); 
            row.createCell((short) 26).setCellValue((Double) Double.parseDouble(list.get(i).getAddBadRate()==null?"0":list.get(i).getAddBadRate())+"%"); 
        }
      String fileName = "客户经理“灵活金”透支情况统计";
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
