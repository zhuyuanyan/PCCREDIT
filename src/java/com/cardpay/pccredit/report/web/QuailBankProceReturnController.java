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
import com.cardpay.pccredit.report.model.QuailBankReturnMonitor;
import com.cardpay.pccredit.report.model.QuailManaReturnMonitor;
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
@RequestMapping("/report/cardqualitybank/*")
@JRadModule("report.cardqualitybank")
public class QuailBankProceReturnController extends BaseController {

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
		List<QuailBankReturnMonitor> result = proceMonitorService.getQuailBankReturnMonitorStatistical(filter);
		JRadModelAndView mv = new JRadModelAndView("/report/cardquality/bank_quality_return_browse", request);
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
		List<QuailBankReturnMonitor> result = proceMonitorService.getQuailBankReturnMonitorStatistical(filter);
		create(result, response,filter);
	}
	
public void create(List<QuailBankReturnMonitor> list,HttpServletResponse response,StatisticalFilter filter){
		
		// 第一步，创建一个webbook，对应一个Excel文件  
       HSSFWorkbook wb = new HSSFWorkbook();  
       // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
      HSSFSheet sheet = wb.createSheet("支行退回进件质量监测报表");  
       // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
      HSSFRow row = sheet.createRow((int) 0);  
       // 第四步，创建单元格，并设置值表头 设置表头居中  
       HSSFCellStyle style = wb.createCellStyle();  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  

       HSSFCell cell = row.createCell((short) 0);  
       cell.setCellValue("一级支行/二级支行");  
       cell.setCellStyle(style);  
     cell = row.createCell((short) 1);  
       cell.setCellValue("进件数量");  
       cell.setCellStyle(style);  
      cell = row.createCell((short) 2);  
       cell.setCellValue("退件数量");  
      cell.setCellStyle(style);  
       cell = row.createCell((short) 3);  
      cell.setCellValue("退件率");  
      cell.setCellStyle(style);  
      cell = row.createCell((short) 4);  
      cell.setCellValue("身份信息不正确");  
      cell.setCellStyle(style);  
      cell = row.createCell((short) 5);  
      cell.setCellValue("居住地址缺失");  
      cell.setCellStyle(style);  
      cell = row.createCell((short) 6);  
      cell.setCellValue("缺签章");  
      cell.setCellStyle(style);  
      cell = row.createCell((short) 7);  
      cell.setCellValue("流水未提供");  
      cell.setCellStyle(style);  
      cell = row.createCell((short) 8);  
      cell.setCellValue("身份证件材料模糊");  
      cell.setCellStyle(style);  
      cell = row.createCell((short) 9);  
      cell.setCellValue("资产证明材料模糊");  
      cell.setCellStyle(style);  
      cell = row.createCell((short) 10);  
      cell.setCellValue("“三亲”证明材料不规范");  
      cell.setCellStyle(style);  
      cell = row.createCell((short) 11);  
      cell.setCellValue("虚假流水");  
      cell.setCellStyle(style);  
      cell = row.createCell((short) 12);  
      cell.setCellValue("“三亲”证明材料虚假");  
      cell.setCellStyle(style);  
      cell = row.createCell((short) 13);  
      cell.setCellValue("资产证明材料需求");  
      cell.setCellStyle(style);  
      cell = row.createCell((short) 14);  
      cell.setCellValue("职业、身份证明材料虚假");  
      cell.setCellStyle(style);  
      cell = row.createCell((short) 15);  
      cell.setCellValue("收入证明材料虚假");  
      cell.setCellStyle(style);  
      DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
      for (int i = 0; i < list.size(); i++)  
        {  
            row = sheet.createRow((int) i + 1);  
            row.createCell((short) 0).setCellValue((String) list.get(i).getDisplayName());  
            row.createCell((short) 1).setCellValue((String) list.get(i).getJinjian());  
            row.createCell((short) 2).setCellValue((String) list.get(i).getReturncount());  
            row.createCell((short) 3).setCellValue((String) list.get(i).getPercent());  
            row.createCell((short) 4).setCellValue((String) list.get(i).getAPPRETURESION01());  
            row.createCell((short) 5).setCellValue((String) list.get(i).getAPPRETURESION02());  
            row.createCell((short) 6).setCellValue((String) list.get(i).getAPPRETURESION03());  
            row.createCell((short) 7).setCellValue((String) list.get(i).getAPPRETURESION04());  
            row.createCell((short) 8).setCellValue((String) list.get(i).getAPPRETURESION05());  
            row.createCell((short) 9).setCellValue((String) list.get(i).getAPPRETURESION06());  
            row.createCell((short) 10).setCellValue((String) list.get(i).getAPPRETURESION07());  
            row.createCell((short) 11).setCellValue((String) list.get(i).getAPPRETURESION08());  
            row.createCell((short) 12).setCellValue((String) list.get(i).getAPPRETURESION09());  
            row.createCell((short) 13).setCellValue((String) list.get(i).getAPPRETURESION10());  
            row.createCell((short) 14).setCellValue((String) list.get(i).getAPPRETURESION11());  
            row.createCell((short) 15).setCellValue((String) list.get(i).getAPPRETURESION12());  
        }
      String fileName = "支行退回进件质量监测报表";
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
