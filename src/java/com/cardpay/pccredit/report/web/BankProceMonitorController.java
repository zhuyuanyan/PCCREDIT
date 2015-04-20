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
import com.cardpay.pccredit.report.model.bankProceMonitor;
import com.cardpay.pccredit.report.model.manaProceMonitor;
import com.cardpay.pccredit.report.service.ProceMonitorService;
import com.wicresoft.jrad.base.auth.JRadModule;
import com.wicresoft.jrad.base.auth.JRadOperation;
import com.wicresoft.jrad.base.web.JRadModelAndView;
import com.wicresoft.jrad.base.web.controller.BaseController;
import com.wicresoft.util.spring.mvc.mv.AbstractModelAndView;

/**
 * ProceMonitorController类的描述
 * 
 * @author 王海东
 * @created on 2014-12-19
 * 
 * @version $Id:$
 */
@Controller
@RequestMapping("/report/cardbusinessbank/*")
@JRadModule("report.cardbusinessbank")
public class BankProceMonitorController extends BaseController {

	@Autowired
	private ProceMonitorService proceMonitorService;

	/**
	 * 浏览页面
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
		if(filter.getOrgId()==null ||filter.getOrgId().equals("-1")){
			filter.setOrgId(null);
		}
		List<manaProceMonitor> result = proceMonitorService.getBankProceMonitorStatistical(filter);
		JRadModelAndView mv = new JRadModelAndView("/report/cardbussiness/bank_business_monitoring_browse", request);
		mv.addObject(PAGED_RESULT, result);
		mv.addObject("filter", filter);
		return mv;
	}

	/**
	 * 浏览页面
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
		if(filter.getOrgId()==null ||filter.getOrgId().equals("-1")){
			filter.setOrgId(null);
		}
		List<manaProceMonitor> result = proceMonitorService.getBankProceMonitorStatistical(filter);
		create(result, response,filter);
	}
	
public void create(List<manaProceMonitor> list,HttpServletResponse response,StatisticalFilter filter){
		
		// 第一步，创建一个webbook，对应一个Excel文件  
       HSSFWorkbook wb = new HSSFWorkbook();  
       // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
      HSSFSheet sheet = wb.createSheet("支行“灵活金”信用卡业务流程监测报表");  
       // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
      HSSFRow row = sheet.createRow((int) 0);  
       // 第四步，创建单元格，并设置值表头 设置表头居中  
       HSSFCellStyle style = wb.createCellStyle();  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  

       HSSFCell cell = row.createCell((short) 0);  
       cell.setCellValue("一级支行/二级支行");  
       cell.setCellStyle(style);  
     cell = row.createCell((short) 1);  
       cell.setCellValue("进件");  
       cell.setCellStyle(style);  
      cell = row.createCell((short) 2);  
       cell.setCellValue("初审数");  
      cell.setCellStyle(style);  
       cell = row.createCell((short) 3);  
      cell.setCellValue("初审通过数");  
      cell.setCellStyle(style);  
      cell = row.createCell((short) 4);  
      cell.setCellValue("录入数");  
      cell.setCellStyle(style);  
      cell = row.createCell((short) 5);  
      cell.setCellValue("录入通过数");  
      cell.setCellStyle(style);  
      cell = row.createCell((short) 6);  
      cell.setCellValue("复审数");  
      cell.setCellStyle(style);  
      cell = row.createCell((short) 7);  
      cell.setCellValue("复审通过数");  
      cell.setCellStyle(style);  
      DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
      for (int i = 0; i < list.size(); i++)  
        {  
            row = sheet.createRow((int) i + 1);  
            row.createCell((short) 0).setCellValue((String) list.get(i).getDisplayName());  
            row.createCell((short) 1).setCellValue((String) list.get(i).getJinjian());  
            row.createCell((short) 2).setCellValue((String) list.get(i).getChushen());  
            row.createCell((short) 3).setCellValue((String) list.get(i).getChushenapprove());  
            row.createCell((short) 4).setCellValue((String) list.get(i).getLuru());  
            row.createCell((short) 5).setCellValue((String) list.get(i).getLuruapprove());  
            row.createCell((short) 6).setCellValue((String) list.get(i).getFushen());  
            row.createCell((short) 7).setCellValue((String) list.get(i).getFushenapprove());  
        }
      String fileName = "支行“灵活金”/信用卡业务流程监测报表";
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
