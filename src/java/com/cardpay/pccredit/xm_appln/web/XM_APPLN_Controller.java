package com.cardpay.pccredit.xm_appln.web;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cardpay.pccredit.common.Cn2Spell;
import com.cardpay.pccredit.common.ConnUtils;
import com.cardpay.pccredit.customer.constant.CustomerInforConstant;
import com.cardpay.pccredit.customer.constant.WfProcessInfoType;
import com.cardpay.pccredit.customer.filter.CustomerInforFilter;
import com.cardpay.pccredit.customer.model.CustomerInfor;
import com.cardpay.pccredit.customer.service.CustomerInforService;
import com.cardpay.pccredit.datapri.constant.DataPriConstants;
import com.cardpay.pccredit.intopieces.constant.ApplicationStatusEnum;
import com.cardpay.pccredit.intopieces.constant.Constant;
import com.cardpay.pccredit.intopieces.constant.IntoPiecesException;
import com.cardpay.pccredit.intopieces.model.CustomerApplicationInfo;
import com.cardpay.pccredit.intopieces.model.CustomerApplicationProcess;
import com.cardpay.pccredit.intopieces.service.CustomerApplicationInfoService;
import com.cardpay.pccredit.intopieces.service.CustomerApplicationIntopieceWaitService;
import com.cardpay.pccredit.intopieces.service.CustomerApplicationProcessService;
import com.cardpay.pccredit.product.service.ProductService;
import com.cardpay.pccredit.riskControl.model.RiskCustomer;
import com.cardpay.pccredit.riskControl.service.RiskCustomerService;
import com.cardpay.pccredit.system.service.NodeAuditService;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_ADDR;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_DBXX;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_DCSC;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_HKSZ;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_HNPF;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_JCZL;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_KHED;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_KHFW;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_KHLB;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_KPMX;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_LXRZL;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_POZL;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_QTXYKXX;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_SPED;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_SQCL;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_SQED;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_TJINFO;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_YWXX;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_ZHSX;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_ZXQSZL;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_ZXXX;
import com.cardpay.pccredit.xm_appln.service.XM_APPLN_Service;
import com.cardpay.workflow.models.WfProcessInfo;
import com.cardpay.workflow.models.WfStatusInfo;
import com.cardpay.workflow.models.WfStatusResult;
import com.cardpay.workflow.service.ProcessService;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import com.wicresoft.jrad.base.auth.IUser;
import com.wicresoft.jrad.base.auth.JRadModule;
import com.wicresoft.jrad.base.auth.JRadOperation;
import com.wicresoft.jrad.base.constant.JRadConstants;
import com.wicresoft.jrad.base.database.dao.common.CommonDao;
import com.wicresoft.jrad.base.database.id.IDGenerator;
import com.wicresoft.jrad.base.database.model.QueryResult;
import com.wicresoft.jrad.base.i18n.I18nHelper;
import com.wicresoft.jrad.base.web.JRadModelAndView;
import com.wicresoft.jrad.base.web.controller.BaseController;
import com.wicresoft.jrad.base.web.result.JRadPagedQueryResult;
import com.wicresoft.jrad.base.web.result.JRadReturnMap;
import com.wicresoft.jrad.base.web.security.LoginManager;
import com.wicresoft.jrad.base.web.utility.WebRequestHelper;
import com.wicresoft.jrad.modules.dictionary.DictionaryManager;
import com.wicresoft.jrad.modules.dictionary.model.Dictionary;
import com.wicresoft.jrad.modules.dictionary.model.DictionaryItem;
import com.wicresoft.jrad.modules.privilege.model.User;
import com.wicresoft.util.spring.Beans;
import com.wicresoft.util.spring.mvc.mv.AbstractModelAndView;
import com.wicresoft.util.web.RequestHelper;

@Controller
@RequestMapping("/xm_appln/*")
@JRadModule("xm_appln")
public class XM_APPLN_Controller extends BaseController {
	
	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private CommonDao commonDao;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private NodeAuditService nodeAuditService;
	
	@Autowired
	private CustomerInforService customerInforservice;
	
	@Autowired
	private CustomerApplicationInfoService customerApplicationInfoService;
	
	@Autowired
	private ProcessService processService;
	
	@Autowired
	private XM_APPLN_Service xM_APPLN_Service;
	
	@Autowired
	private CustomerApplicationIntopieceWaitService customerApplicationIntopieceWaitService;
	
	@Autowired
	private CustomerApplicationProcessService customerApplicationProcessService;
	
	@Autowired
	private RiskCustomerService riskCustomerService;
	
	
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
	public AbstractModelAndView browse(@ModelAttribute CustomerInforFilter filter,HttpServletRequest request) {
        filter.setRequest(request);
        IUser user = Beans.get(LoginManager.class).getLoggedInUser(request);
		filter.setUserId(user.getId());
		QueryResult<CustomerInfor> result = customerInforservice.findCustomerInforByFilter(filter);
		JRadPagedQueryResult<CustomerInfor> pagedResult = new JRadPagedQueryResult<CustomerInfor>(filter, result);
		JRadModelAndView mv = new JRadModelAndView("/xm_appln/xm_appln_page0_browse",request);
		mv.addObject(PAGED_RESULT, pagedResult);

		return mv;
	}
	
	//跳转到page0
	@ResponseBody
	@RequestMapping(value = "xm_appln_page0.page")
	@JRadOperation(JRadOperation.BROWSE)
	public AbstractModelAndView xm_appln_page0(HttpServletRequest request) {        
		JRadModelAndView mv = new JRadModelAndView("/xm_appln/xm_appln_page0", request);
		String customerInforId = request.getParameter("customerId");
		if (StringUtils.isNotEmpty(customerInforId)) {
			CustomerInfor customerInfor = customerInforservice.findCustomerInforById(customerInforId);
			XM_APPLN_JCZL xM_APPLN_JCZL = xM_APPLN_Service.findXM_APPLN_JCZLByCustomerId(customerInforId);
			XM_APPLN_POZL xM_APPLN_POZL = xM_APPLN_Service.findXM_APPLN_POZLByCustomerId(customerInforId);
			mv.addObject("customerInfor", customerInfor);
			mv.addObject("customerId", customerInfor.getId());
			mv.addObject("xM_APPLN_JCZL", xM_APPLN_JCZL);
			mv.addObject("xM_APPLN_POZL", xM_APPLN_POZL);
		}
		return mv;
	}
	
	//page0保存
	@ResponseBody
	@RequestMapping(value = "xm_appln_page0_update.json")
	@JRadOperation(JRadOperation.CREATE)
	public JRadReturnMap xm_appln_page0_update(@ModelAttribute XM_APPLN_NEW_CUSTOMER_FORM xM_APPLN_NEW_CUSTOMER_FORM, HttpServletRequest request) {
		JRadReturnMap returnMap = new JRadReturnMap();
		if (returnMap.isSuccess()) {
			try {
				User user = (User) Beans.get(LoginManager.class).getLoggedInUser(request);
				String customerId = request.getParameter("customer_id");
				customerId = xM_APPLN_Service.insertOrUpdateXM_APPLN_NEW_CUSTOMER(xM_APPLN_NEW_CUSTOMER_FORM,user);
				returnMap.put(RECORD_ID, customerId);
				returnMap.addGlobalMessage(CREATE_SUCCESS);
			}catch (Exception e) {
				returnMap.put(JRadConstants.MESSAGE, DataPriConstants.SYS_EXCEPTION_MSG);
				returnMap.put(JRadConstants.SUCCESS, false);
				return WebRequestHelper.processException(e);
			}
		}else{
			returnMap.setSuccess(false);
			returnMap.addGlobalError(CustomerInforConstant.CREATEERROR);
		}
		return returnMap;
	}
	

	//录入跳转到iframe
	@ResponseBody
	@RequestMapping(value = "changewh_xm_appln.page")
	@JRadOperation(JRadOperation.MAINTENANCE)
	public AbstractModelAndView changewh_xm_appln(HttpServletRequest request) {
		JRadModelAndView mv = new JRadModelAndView("/xm_appln/iframe", request);
		
		String customerInforId = RequestHelper.getStringValue(request, ID);
		String appId = RequestHelper.getStringValue(request, "aid");
		if (StringUtils.isNotEmpty(customerInforId)) {
			CustomerInfor customerInfor = customerInforservice.findCustomerInforById(customerInforId);
			mv.addObject("customerInfor", customerInfor);
			mv.addObject("customerId", customerInfor.getId());
			mv.addObject("appId", appId);
		}
		return mv;
	}
	//复核跳转到iframe_fuhe
	@ResponseBody
	@RequestMapping(value = "changewh_xm_appln_fuhe.page")
	@JRadOperation(JRadOperation.MAINTENANCE)
	public AbstractModelAndView changewh_xm_appln_fuhe(HttpServletRequest request) {
		JRadModelAndView mv = new JRadModelAndView("/xm_appln/iframe_fuhe", request);
		
		String customerInforId = RequestHelper.getStringValue(request, ID);
		String appId = RequestHelper.getStringValue(request, "aid");
		if (StringUtils.isNotEmpty(customerInforId)) {
			CustomerInfor customerInfor = customerInforservice.findCustomerInforById(customerInforId);
			mv.addObject("customerInfor", customerInfor);
			mv.addObject("customerId", customerInfor.getId());
			mv.addObject("appId", appId);
		}
		return mv;
	}
	
	//终审跳转到iframe（查看）
	@ResponseBody
	@RequestMapping(value = "changewh_xm_appln_zhongshen.page")
	@JRadOperation(JRadOperation.MAINTENANCE)
	public AbstractModelAndView changewh_xm_appln_zhongshen(HttpServletRequest request) {
		JRadModelAndView mv = new JRadModelAndView("/xm_appln/iframe", request);
		
		String customerInforId = RequestHelper.getStringValue(request, "customerId");
		String appId = RequestHelper.getStringValue(request, "applicationId");
		if (StringUtils.isNotEmpty(customerInforId)) {
			CustomerInfor customerInfor = customerInforservice.findCustomerInforById(customerInforId);
			mv.addObject("customerInfor", customerInfor);
			mv.addObject("customerId", customerInfor.getId());
			mv.addObject("appId", appId);
			mv.addObject("type", "read");
		}
		return mv;
	}
	
	//iframe跳转到第page1
	@ResponseBody
	@RequestMapping(value = "xm_appln_page1_update.page")
	@JRadOperation(JRadOperation.MAINTENANCE)
	public AbstractModelAndView changewh_xm_appln_page1_update(HttpServletRequest request) {
		JRadModelAndView mv = new JRadModelAndView("/xm_appln/xm_appln_page1_update", request);
		String customerInforId = RequestHelper.getStringValue(request, ID);
		String appid = RequestHelper.getStringValue(request, "appId");
		if (StringUtils.isNotEmpty(customerInforId)) {
			CustomerInfor customerInfor = customerInforservice.findCustomerInforById(customerInforId);
			//查找xm_appln相关信息
			XM_APPLN_JCZL xM_APPLN_JCZL = xM_APPLN_Service.findXM_APPLN_JCZLByCustomerId(customerInforId);
			XM_APPLN_KHFW xM_APPLN_KHFW = xM_APPLN_Service.findXM_APPLN_KHFWByCustomerId(customerInforId);
			XM_APPLN_KHED xM_APPLN_KHED = xM_APPLN_Service.findXM_APPLN_KHEDByCustomerId(customerInforId);
			XM_APPLN_KHLB xM_APPLN_KHLB = xM_APPLN_Service.findXM_APPLN_KHLBByCustomerId(customerInforId);
			XM_APPLN_POZL xM_APPLN_POZL = xM_APPLN_Service.findXM_APPLN_POZLByCustomerId(customerInforId);
			List<XM_APPLN_LXRZL> xM_APPLN_LXRZL_ls = xM_APPLN_Service.findXM_APPLN_LXRZLByCustomerId(customerInforId);
			if(xM_APPLN_LXRZL_ls == null || xM_APPLN_LXRZL_ls.size() == 0){
				xM_APPLN_LXRZL_ls = new ArrayList<XM_APPLN_LXRZL>();
				XM_APPLN_LXRZL obj = new XM_APPLN_LXRZL();
				obj.setLsh(0);
				obj.setCustomer_id(customerInforId);
				xM_APPLN_LXRZL_ls.add(obj);
				XM_APPLN_LXRZL obj2 = new XM_APPLN_LXRZL();
				obj2.setLsh(1);
				obj2.setCustomer_id(customerInforId);
				xM_APPLN_LXRZL_ls.add(obj2);
			}
			XM_APPLN_ZXQSZL xM_APPLN_ZXQSZL = xM_APPLN_Service.findXM_APPLN_ZXQSZLByCustomerId(customerInforId);
			mv.addObject("xM_APPLN_JCZL", xM_APPLN_JCZL);
			mv.addObject("xM_APPLN_KHFW", xM_APPLN_KHFW);
			mv.addObject("xM_APPLN_KHED", xM_APPLN_KHED);
			mv.addObject("xM_APPLN_KHLB", xM_APPLN_KHLB);
			mv.addObject("xM_APPLN_POZL", xM_APPLN_POZL);
			mv.addObject("xM_APPLN_LXRZL_ls", xM_APPLN_LXRZL_ls);
			mv.addObject("xM_APPLN_ZXQSZL", xM_APPLN_ZXQSZL);
			
			mv.addObject("customerInfor", customerInfor);
			mv.addObject("customerId", customerInfor.getId());
			mv.addObject("appid",appid);
		}
		return mv;
	}
		
	//iframe跳转到第page2
	@ResponseBody
	@RequestMapping(value = "xm_appln_page2.page")
	@JRadOperation(JRadOperation.MAINTENANCE)
	public AbstractModelAndView changewh_xm_appln_page2(HttpServletRequest request) {
		JRadModelAndView mv = new JRadModelAndView("/xm_appln/xm_appln_page2", request);
		String customerInforId = RequestHelper.getStringValue(request, ID);
		if (StringUtils.isNotEmpty(customerInforId)) {
			CustomerInfor customerInfor = customerInforservice.findCustomerInforById(customerInforId);
			//查找xm_appln相关信息
			XM_APPLN xM_APPLN = this.xM_APPLN_Service.findXM_APPLNByCustomerId(customerInforId);
			XM_APPLN_SQED xM_APPLN_SQED = this.xM_APPLN_Service.findXM_APPLN_SQEDByCustomerId(customerInforId);
			List<XM_APPLN_KPMX> xM_APPLN_KPMX_ls = this.xM_APPLN_Service.findXM_APPLN_KPMXByCustomerId(customerInforId);
			if(xM_APPLN_KPMX_ls == null || xM_APPLN_KPMX_ls.size() == 0){
				xM_APPLN_KPMX_ls = new ArrayList<XM_APPLN_KPMX>();
				XM_APPLN_KPMX obj = new XM_APPLN_KPMX();
				obj.setLsh(0);
				obj.setCustomer_id(customerInforId);
				xM_APPLN_KPMX_ls.add(obj);
				XM_APPLN_KPMX obj2 = new XM_APPLN_KPMX();
				obj2.setLsh(1);
				obj2.setCustomer_id(customerInforId);
				xM_APPLN_KPMX_ls.add(obj2);
			}
			XM_APPLN_HKSZ xM_APPLN_HKSZ = this.xM_APPLN_Service.findXM_APPLN_HKSZByCustomerId(customerInforId);
			XM_APPLN_DBXX xM_APPLN_DBXX = this.xM_APPLN_Service.findXM_APPLN_DBXXByCustomerId(customerInforId);
			XM_APPLN_QTXYKXX xM_APPLN_QTXYKXX = this.xM_APPLN_Service.findXM_APPLN_QTXYKXXByCustomerId(customerInforId);
			XM_APPLN_YWXX xM_APPLN_YWXX = this.xM_APPLN_Service.findXM_APPLN_YWXXByCustomerId(customerInforId);
			XM_APPLN_DCSC xM_APPLN_DCSC = this.xM_APPLN_Service.findXM_APPLN_DCSCByCustomerId(customerInforId);
			mv.addObject("xM_APPLN", xM_APPLN);
			mv.addObject("xM_APPLN_SQED", xM_APPLN_SQED);
			mv.addObject("xM_APPLN_KPMX_ls", xM_APPLN_KPMX_ls);
			mv.addObject("xM_APPLN_HKSZ", xM_APPLN_HKSZ);
			mv.addObject("xM_APPLN_DBXX", xM_APPLN_DBXX);
			mv.addObject("xM_APPLN_QTXYKXX", xM_APPLN_QTXYKXX);
			mv.addObject("xM_APPLN_YWXX", xM_APPLN_YWXX);
			mv.addObject("xM_APPLN_DCSC", xM_APPLN_DCSC);
			
			mv.addObject("customerInfor", customerInfor);
			mv.addObject("customerId", customerInfor.getId());
		}
		return mv;
	}
	
	//iframe跳转到第page3
	@ResponseBody
	@RequestMapping(value = "xm_appln_page3.page")
	@JRadOperation(JRadOperation.MAINTENANCE)
	public AbstractModelAndView changewh_xm_appln_page3(HttpServletRequest request) {
		JRadModelAndView mv = new JRadModelAndView("/xm_appln/xm_appln_page3", request);
		String customerInforId = RequestHelper.getStringValue(request, ID);
		if (StringUtils.isNotEmpty(customerInforId)) {
			CustomerInfor customerInfor = customerInforservice.findCustomerInforById(customerInforId);
			//查找xm_appln信息
			XM_APPLN_TJINFO xM_APPLN_TJINFO = this.xM_APPLN_Service.findXM_APPLN_TJINFOByCustomerId(customerInforId);
			XM_APPLN_ADDR xM_APPLN_ADDR = this.xM_APPLN_Service.findXM_APPLN_ADDRByCustomerId(customerInforId);
			XM_APPLN xM_APPLN = this.xM_APPLN_Service.findXM_APPLNByCustomerId(customerInforId);
			XM_APPLN_JCZL xM_APPLN_JCZL = xM_APPLN_Service.findXM_APPLN_JCZLByCustomerId(customerInforId);
			List<XM_APPLN_KPMX> xM_APPLN_KPMX_ls = this.xM_APPLN_Service.findXM_APPLN_KPMXByCustomerId(customerInforId);
			if(xM_APPLN_KPMX_ls == null || xM_APPLN_KPMX_ls.size() == 0){
				xM_APPLN_KPMX_ls = new ArrayList<XM_APPLN_KPMX>();
				XM_APPLN_KPMX obj = new XM_APPLN_KPMX();
				obj.setLsh(0);
				obj.setCustomer_id(customerInforId);
				xM_APPLN_KPMX_ls.add(obj);
				XM_APPLN_KPMX obj2 = new XM_APPLN_KPMX();
				obj2.setLsh(1);
				obj2.setCustomer_id(customerInforId);
				xM_APPLN_KPMX_ls.add(obj2);
			}
			mv.addObject("xM_APPLN_TJINFO", xM_APPLN_TJINFO);
			mv.addObject("xM_APPLN_ADDR", xM_APPLN_ADDR);
			mv.addObject("xM_APPLN", xM_APPLN);
			mv.addObject("xM_APPLN_JCZL", xM_APPLN_JCZL);
			mv.addObject("xM_APPLNKPMX_ls", xM_APPLN_KPMX_ls);
			
			mv.addObject("customerInfor", customerInfor);
			mv.addObject("customerId", customerInfor.getId());
		}
		return mv;
	}
		
	//iframe跳转到第page4
	@ResponseBody
	@RequestMapping(value = "xm_appln_page4.page", method = { RequestMethod.GET })
	@JRadOperation(JRadOperation.CREATE)
	public AbstractModelAndView xm_appln_page4(HttpServletRequest request) {
		String customerId = RequestHelper.getStringValue(request, ID);
		String appId = RequestHelper.getStringValue(request, "appId");
		CustomerInfor customerInfor = customerInforservice.findCustomerInforById(customerId);
		
		//查找相关xm_xppln信息
		XM_APPLN xM_APPLN = xM_APPLN_Service.findXM_APPLNByCustomerId(customerId);
		if(xM_APPLN == null){
			xM_APPLN = new XM_APPLN();
		}
		XM_APPLN_SQED xM_APPLN_SQED = xM_APPLN_Service.findXM_APPLN_SQEDByCustomerId(customerId);
		if(xM_APPLN_SQED == null){
			xM_APPLN_SQED = new XM_APPLN_SQED();
		}
		List<XM_APPLN_KPMX> xM_APPLN_KPMX_ls = xM_APPLN_Service.findXM_APPLN_KPMXByCustomerId(customerId);
		if(xM_APPLN_KPMX_ls == null || xM_APPLN_KPMX_ls.size() == 0){
			xM_APPLN_KPMX_ls.add(new XM_APPLN_KPMX());
			xM_APPLN_KPMX_ls.add(new XM_APPLN_KPMX());
		}
		XM_APPLN_HKSZ xM_APPLN_HKSZ = xM_APPLN_Service.findXM_APPLN_HKSZByCustomerId(customerId);
		if(xM_APPLN_HKSZ == null){
			xM_APPLN_HKSZ = new XM_APPLN_HKSZ();
		}
		XM_APPLN_DBXX xM_APPLN_DBXX = xM_APPLN_Service.findXM_APPLN_DBXXByCustomerId(customerId);
		if(xM_APPLN_DBXX == null){
			xM_APPLN_DBXX = new XM_APPLN_DBXX();
		}
		XM_APPLN_QTXYKXX xM_APPLN_QTXYKXX = xM_APPLN_Service.findXM_APPLN_QTXYKXXByCustomerId(customerId);
		if(xM_APPLN_QTXYKXX == null){
			xM_APPLN_QTXYKXX = new XM_APPLN_QTXYKXX();
		}
		XM_APPLN_DCSC xM_APPLN_DCSC = xM_APPLN_Service.findXM_APPLN_DCSCByCustomerId(customerId);
		if(xM_APPLN_DCSC == null){
			xM_APPLN_DCSC = new XM_APPLN_DCSC();
		}
		XM_APPLN_TJINFO xM_APPLN_TJINFO = xM_APPLN_Service.findXM_APPLN_TJINFOByCustomerId(customerId);
		if(xM_APPLN_TJINFO == null){
			xM_APPLN_TJINFO = new XM_APPLN_TJINFO();
		}
		XM_APPLN_ADDR xM_APPLN_ADDR = xM_APPLN_Service.findXM_APPLN_ADDRByCustomerId(customerId);
		if(xM_APPLN_ADDR == null){
			xM_APPLN_ADDR = new XM_APPLN_ADDR();
		}
		
		//转化数据字典value为显示值
		xM_APPLN.setProduct(conventDic2Title("PRODUCT", xM_APPLN.getProduct()));
		xM_APPLN.setAddl_card((xM_APPLN.getAddl_card()!=null&&xM_APPLN.getAddl_card().equals("1"))?"是":"否");
		xM_APPLN.setRush_card((xM_APPLN.getRush_card()!=null&&xM_APPLN.getRush_card().equals("1"))?"是":"否");
		xM_APPLN.setApp_source(conventDic2Title("APP_SOURCE", xM_APPLN.getApp_source()));
		xM_APPLN.setRush_fee((xM_APPLN.getRush_fee()!=null&&xM_APPLN.getRush_fee().equals("1"))?"是":"否");
		xM_APPLN.setAcc_type(conventDic2Title("ACC_TYPE", xM_APPLN.getAcc_type()));
		xM_APPLN.setGtoc((xM_APPLN.getGtoc()!=null&&xM_APPLN.getGtoc().equals("1"))?"是":"否");
		
		for(XM_APPLN_KPMX obj : xM_APPLN_KPMX_ls){
			obj.setEmboss_cd((obj.getEmboss_cd()!=null&&obj.getEmboss_cd().equals("1"))?"是":"否");
			obj.setPin_reqd((obj.getPin_reqd()!=null&&obj.getPin_reqd().equals("1"))?"是":"否");
			obj.setSms_yn((obj.getSms_yn()!=null&&obj.getSms_yn().equals("1"))?"是":"否");
			obj.setPin_chk((obj.getPin_chk()!=null&&obj.getPin_chk().equals("1"))?"是":"否");
			obj.setCdfrm(conventDic2Title("CDFRM", obj.getCdfrm()));
			if(obj.getAtm() == null){obj.setAtm("MR");}
			if(obj.getAtm().equals("MR"))
				obj.setAtm("取产品新卡参数");
			if(obj.getAtm().equals("BKT"))
				obj.setAtm("不开通");
			if(obj.getAtm().equals("KT"))
				obj.setAtm("开通");
			if(obj.getTele() == null){obj.setTele("MR");}
			if(obj.getTele().equals("MR"))
				obj.setTele("取产品新卡参数");
			if(obj.getTele().equals("BKT"))
				obj.setTele("不开通");
			if(obj.getTele().equals("KT"))
				obj.setTele("开通");
			if(obj.getNet() == null){obj.setNet("MR");}
			if(obj.getNet().equals("MR"))
				obj.setNet("取产品新卡参数");
			if(obj.getNet().equals("BKT"))
				obj.setNet("不开通");
			if(obj.getNet().equals("KT"))
				obj.setNet("开通");
			if(obj.getPhone() == null){obj.setPhone("MR");}
			if(obj.getPhone().equals("MR"))
				obj.setPhone("取产品新卡参数");
			if(obj.getPhone().equals("BKT"))
				obj.setPhone("不开通");
			if(obj.getPhone().equals("KT"))
				obj.setPhone("开通");
		}
		
		xM_APPLN_HKSZ.setRepay_code(conventDic2Title("REPAY_CODE", xM_APPLN_HKSZ.getRepay_code()));
		xM_APPLN_HKSZ.setRepay_codx(conventDic2Title("REPAY_CODX", xM_APPLN_HKSZ.getRepay_codx()));
		xM_APPLN_HKSZ.setExch_flag(conventDic2Title("EXCH_FLAG", xM_APPLN_HKSZ.getExch_flag()));
		xM_APPLN_HKSZ.setExch_code(conventDic2Title("EXCH_CODE", xM_APPLN_HKSZ.getExch_code()));
		
		xM_APPLN_DBXX.setGuarn_code(conventDic2Title("GUARN_CODE", xM_APPLN_DBXX.getGuarn_code()));
		
		if(xM_APPLN_QTXYKXX.getXrefcode1() == null){
			xM_APPLN_QTXYKXX.setXrefcode1("WU");
		}
		if(xM_APPLN_QTXYKXX.getXrefcode1().equals("WU")){
			xM_APPLN_QTXYKXX.setXrefcode1("无");
		}
		if(xM_APPLN_QTXYKXX.getXrefcode1().equals("BH")){
			xM_APPLN_QTXYKXX.setXrefcode1("本行");
		}
		if(xM_APPLN_QTXYKXX.getXrefcode1().equals("TH")){
			xM_APPLN_QTXYKXX.setXrefcode1("他行");
		}
		if(xM_APPLN_QTXYKXX.getXrefcode2() == null){
			xM_APPLN_QTXYKXX.setXrefcode2("WU");
		}
		if(xM_APPLN_QTXYKXX.getXrefcode2().equals("WU")){
			xM_APPLN_QTXYKXX.setXrefcode2("无");
		}
		if(xM_APPLN_QTXYKXX.getXrefcode2().equals("BH")){
			xM_APPLN_QTXYKXX.setXrefcode2("本行");
		}
		if(xM_APPLN_QTXYKXX.getXrefcode2().equals("TH")){
			xM_APPLN_QTXYKXX.setXrefcode2("他行");
		}
		
		xM_APPLN_TJINFO.setIntr_recom(conventDic2Title("INTR_RECOM", xM_APPLN_TJINFO.getIntr_recom()));
		xM_APPLN_TJINFO.setBrnch_intr(conventDic2Title("BRNCH_INTR", xM_APPLN_TJINFO.getBrnch_intr()));
		xM_APPLN_TJINFO.setIntr_rl(conventDic2Title("INTR_RL", xM_APPLN_TJINFO.getIntr_rl()));
		xM_APPLN_TJINFO.setIntr_qc(conventDic2Title("INTR_QC", xM_APPLN_TJINFO.getIntr_qc()));
		
		xM_APPLN_ADDR.setAddr1_type(conventDic2Title("ADDR_TYPE",xM_APPLN_ADDR.getAddr1_type()));
		xM_APPLN_ADDR.setAddr2_type(conventDic2Title("ADDR_TYPE",xM_APPLN_ADDR.getAddr2_type()));
		xM_APPLN_ADDR.setAddr3_type(conventDic2Title("ADDR_TYPE",xM_APPLN_ADDR.getAddr3_type()));
		xM_APPLN_ADDR.setAddr4_type(conventDic2Title("ADDR_TYPE",xM_APPLN_ADDR.getAddr4_type()));
		xM_APPLN_ADDR.setOsea_f1((xM_APPLN_ADDR.getOsea_f1()!=null&&xM_APPLN_ADDR.getOsea_f1().equals("1"))?"是":"否");
		xM_APPLN_ADDR.setOsea_f2((xM_APPLN_ADDR.getOsea_f2()!=null&&xM_APPLN_ADDR.getOsea_f2().equals("1"))?"是":"否");
		xM_APPLN_ADDR.setOsea_f3((xM_APPLN_ADDR.getOsea_f3()!=null&&xM_APPLN_ADDR.getOsea_f3().equals("1"))?"是":"否");
		xM_APPLN_ADDR.setOsea_f4((xM_APPLN_ADDR.getOsea_f4()!=null&&xM_APPLN_ADDR.getOsea_f4().equals("1"))?"是":"否");
		
		xM_APPLN.setMail_to(conventDic2Title("MAIL_TO", xM_APPLN.getMail_to()));
		
		JRadModelAndView mv = new JRadModelAndView("/xm_appln/xm_appln_page4", request);
		
		mv.addObject("customerId", customerId);
		mv.addObject("customer", customerInfor);
		mv.addObject("appId", appId);
		mv.addObject("xM_APPLN", xM_APPLN);
		mv.addObject("xM_APPLN_SQED", xM_APPLN_SQED);
		mv.addObject("xM_APPLN_KPMX_ls", xM_APPLN_KPMX_ls);
		mv.addObject("xM_APPLN_HKSZ", xM_APPLN_HKSZ);
		mv.addObject("xM_APPLN_DBXX", xM_APPLN_DBXX);
		mv.addObject("xM_APPLN_QTXYKXX", xM_APPLN_QTXYKXX);
		mv.addObject("xM_APPLN_DCSC", xM_APPLN_DCSC);
		mv.addObject("xM_APPLN_TJINFO", xM_APPLN_TJINFO);
		mv.addObject("xM_APPLN_ADDR", xM_APPLN_ADDR);
		
		return mv;
	}
	
	//跳转到page5
	@ResponseBody
	@RequestMapping(value = "xm_appln_page5.page")
	@JRadOperation(JRadOperation.CREATE)
	public AbstractModelAndView xm_appln_page5(HttpServletRequest request) {
		JRadModelAndView mv = new JRadModelAndView("/xm_appln/xm_appln_page5", request);
		
		String customerInforId = RequestHelper.getStringValue(request, ID);
		if (StringUtils.isNotEmpty(customerInforId)) {
			CustomerInfor customerInfor = customerInforservice.findCustomerInforById(customerInforId);
			XM_APPLN_HNPF xM_APPLN_HNPF = xM_APPLN_Service.findXM_APPLN_HNPFByCustomerId(customerInforId);
			mv.addObject("customerInfor", customerInfor);
			mv.addObject("customerId", customerInfor.getId());
			
			mv.addObject("xM_APPLN_HNPF", xM_APPLN_HNPF);
		}
		return mv;
	}
		
	//跳转到第page6
	@ResponseBody
	@RequestMapping(value = "xm_appln_page6.page", method = { RequestMethod.GET })
	@JRadOperation(JRadOperation.CREATE)
	public AbstractModelAndView xm_appln_page6(HttpServletRequest request) {
		String customerId = RequestHelper.getStringValue(request, ID);
		String appId = RequestHelper.getStringValue(request, "appId");
		CustomerInfor customerInfor = customerInforservice.findCustomerInforById(customerId);
		
		//查找相关xm_xppln信息
		XM_APPLN xM_APPLN = xM_APPLN_Service.findXM_APPLNByCustomerId(customerId);
		if(xM_APPLN == null){
			xM_APPLN = new XM_APPLN();
		}
		XM_APPLN_SQED xM_APPLN_SQED = xM_APPLN_Service.findXM_APPLN_SQEDByCustomerId(customerId);
		if(xM_APPLN_SQED == null){
			xM_APPLN_SQED = new XM_APPLN_SQED();
		}
		List<XM_APPLN_KPMX> xM_APPLN_KPMX_ls = xM_APPLN_Service.findXM_APPLN_KPMXByCustomerId(customerId);
		if(xM_APPLN_KPMX_ls == null || xM_APPLN_KPMX_ls.size() == 0){
			xM_APPLN_KPMX_ls.add(new XM_APPLN_KPMX());
			xM_APPLN_KPMX_ls.add(new XM_APPLN_KPMX());
		}
		XM_APPLN_HKSZ xM_APPLN_HKSZ = xM_APPLN_Service.findXM_APPLN_HKSZByCustomerId(customerId);
		if(xM_APPLN_HKSZ == null){
			xM_APPLN_HKSZ = new XM_APPLN_HKSZ();
		}
		XM_APPLN_DBXX xM_APPLN_DBXX = xM_APPLN_Service.findXM_APPLN_DBXXByCustomerId(customerId);
		if(xM_APPLN_DBXX == null){
			xM_APPLN_DBXX = new XM_APPLN_DBXX();
		}
		XM_APPLN_QTXYKXX xM_APPLN_QTXYKXX = xM_APPLN_Service.findXM_APPLN_QTXYKXXByCustomerId(customerId);
		if(xM_APPLN_QTXYKXX == null){
			xM_APPLN_QTXYKXX = new XM_APPLN_QTXYKXX();
		}
		XM_APPLN_DCSC xM_APPLN_DCSC = xM_APPLN_Service.findXM_APPLN_DCSCByCustomerId(customerId);
		if(xM_APPLN_DCSC == null){
			xM_APPLN_DCSC = new XM_APPLN_DCSC();
		}
		XM_APPLN_TJINFO xM_APPLN_TJINFO = xM_APPLN_Service.findXM_APPLN_TJINFOByCustomerId(customerId);
		if(xM_APPLN_TJINFO == null){
			xM_APPLN_TJINFO = new XM_APPLN_TJINFO();
		}
		XM_APPLN_ADDR xM_APPLN_ADDR = xM_APPLN_Service.findXM_APPLN_ADDRByCustomerId(customerId);
		if(xM_APPLN_ADDR == null){
			xM_APPLN_ADDR = new XM_APPLN_ADDR();
		}
		XM_APPLN_SPED xM_APPLN_SPED = xM_APPLN_Service.findXM_APPLN_SPEDByCustomerId(customerId);
		if(xM_APPLN_SPED == null){
			xM_APPLN_SPED = new XM_APPLN_SPED();
		}
		XM_APPLN_SQCL xM_APPLN_SQCL = xM_APPLN_Service.findXM_APPLN_SQCLByCustomerId(customerId);
		if(xM_APPLN_SQCL == null){
			xM_APPLN_SQCL = new XM_APPLN_SQCL();
		}
		XM_APPLN_ZHSX xM_APPLN_ZHSX = xM_APPLN_Service.findXM_APPLN_ZHSXByCustomerId(customerId);
		if(xM_APPLN_ZHSX == null){
			xM_APPLN_ZHSX = new XM_APPLN_ZHSX();
		}
		XM_APPLN_ZXXX xM_APPLN_ZXXX = xM_APPLN_Service.findXM_APPLN_ZXXXByCustomerId(customerId);
		if(xM_APPLN_ZXXX == null){
			xM_APPLN_ZXXX = new XM_APPLN_ZXXX();
		}
		
		//转化数据字典value为显示值
		xM_APPLN.setProduct(conventDic2Title("PRODUCT", xM_APPLN.getProduct()));
		xM_APPLN.setAddl_card((xM_APPLN.getAddl_card()!=null&&xM_APPLN.getAddl_card().equals("1"))?"是":"否");
		xM_APPLN.setRush_card((xM_APPLN.getRush_card()!=null&&xM_APPLN.getRush_card().equals("1"))?"是":"否");
		xM_APPLN.setApp_source(conventDic2Title("APP_SOURCE", xM_APPLN.getApp_source()));
		xM_APPLN.setRush_fee((xM_APPLN.getRush_fee()!=null&&xM_APPLN.getRush_fee().equals("1"))?"是":"否");
		xM_APPLN.setAcc_type(conventDic2Title("ACC_TYPE", xM_APPLN.getAcc_type()));
		xM_APPLN.setGtoc((xM_APPLN.getGtoc()!=null&&xM_APPLN.getGtoc().equals("1"))?"是":"否");
		
		for(XM_APPLN_KPMX obj : xM_APPLN_KPMX_ls){
			obj.setEmboss_cd((obj.getEmboss_cd()!=null&&obj.getEmboss_cd().equals("1"))?"是":"否");
			obj.setPin_reqd((obj.getPin_reqd()!=null&&obj.getPin_reqd().equals("1"))?"是":"否");
			obj.setSms_yn((obj.getSms_yn()!=null&&obj.getSms_yn().equals("1"))?"是":"否");
			obj.setPin_chk((obj.getPin_chk()!=null&&obj.getPin_chk().equals("1"))?"是":"否");
			obj.setCdfrm(conventDic2Title("CDFRM", obj.getCdfrm()));
			if(obj.getAtm() == null){obj.setAtm("MR");}
			if(obj.getAtm().equals("MR"))
				obj.setAtm("取产品新卡参数");
			if(obj.getAtm().equals("BKT"))
				obj.setAtm("不开通");
			if(obj.getAtm().equals("KT"))
				obj.setAtm("开通");
			if(obj.getTele() == null){obj.setTele("MR");}
			if(obj.getTele().equals("MR"))
				obj.setTele("取产品新卡参数");
			if(obj.getTele().equals("BKT"))
				obj.setTele("不开通");
			if(obj.getTele().equals("KT"))
				obj.setTele("开通");
			if(obj.getNet() == null){obj.setNet("MR");}
			if(obj.getNet().equals("MR"))
				obj.setNet("取产品新卡参数");
			if(obj.getNet().equals("BKT"))
				obj.setNet("不开通");
			if(obj.getNet().equals("KT"))
				obj.setNet("开通");
			if(obj.getPhone() == null){obj.setPhone("MR");}
			if(obj.getPhone().equals("MR"))
				obj.setPhone("取产品新卡参数");
			if(obj.getPhone().equals("BKT"))
				obj.setPhone("不开通");
			if(obj.getPhone().equals("KT"))
				obj.setPhone("开通");
		}
		
		xM_APPLN_HKSZ.setRepay_code(conventDic2Title("REPAY_CODE", xM_APPLN_HKSZ.getRepay_code()));
		xM_APPLN_HKSZ.setRepay_codx(conventDic2Title("REPAY_CODX", xM_APPLN_HKSZ.getRepay_codx()));
		xM_APPLN_HKSZ.setExch_flag(conventDic2Title("EXCH_FLAG", xM_APPLN_HKSZ.getExch_flag()));
		xM_APPLN_HKSZ.setExch_code(conventDic2Title("EXCH_CODE", xM_APPLN_HKSZ.getExch_code()));
		
		xM_APPLN_DBXX.setGuarn_code(conventDic2Title("GUARN_CODE", xM_APPLN_DBXX.getGuarn_code()));
		
		if(xM_APPLN_QTXYKXX.getXrefcode1() == null){
			xM_APPLN_QTXYKXX.setXrefcode1("WU");
		}
		if(xM_APPLN_QTXYKXX.getXrefcode1().equals("WU")){
			xM_APPLN_QTXYKXX.setXrefcode1("无");
		}
		if(xM_APPLN_QTXYKXX.getXrefcode1().equals("BH")){
			xM_APPLN_QTXYKXX.setXrefcode1("本行");
		}
		if(xM_APPLN_QTXYKXX.getXrefcode1().equals("TH")){
			xM_APPLN_QTXYKXX.setXrefcode1("他行");
		}
		if(xM_APPLN_QTXYKXX.getXrefcode2() == null){
			xM_APPLN_QTXYKXX.setXrefcode2("WU");
		}
		if(xM_APPLN_QTXYKXX.getXrefcode2().equals("WU")){
			xM_APPLN_QTXYKXX.setXrefcode2("无");
		}
		if(xM_APPLN_QTXYKXX.getXrefcode2().equals("BH")){
			xM_APPLN_QTXYKXX.setXrefcode2("本行");
		}
		if(xM_APPLN_QTXYKXX.getXrefcode2().equals("TH")){
			xM_APPLN_QTXYKXX.setXrefcode2("他行");
		}
		
		xM_APPLN_TJINFO.setIntr_recom(conventDic2Title("INTR_RECOM", xM_APPLN_TJINFO.getIntr_recom()));
		xM_APPLN_TJINFO.setBrnch_intr(conventDic2Title("BRNCH_INTR", xM_APPLN_TJINFO.getBrnch_intr()));
		xM_APPLN_TJINFO.setIntr_rl(conventDic2Title("INTR_RL", xM_APPLN_TJINFO.getIntr_rl()));
		xM_APPLN_TJINFO.setIntr_qc(conventDic2Title("INTR_QC", xM_APPLN_TJINFO.getIntr_qc()));
		
		xM_APPLN_ADDR.setAddr1_type(conventDic2Title("ADDR_TYPE",xM_APPLN_ADDR.getAddr1_type()));
		xM_APPLN_ADDR.setAddr2_type(conventDic2Title("ADDR_TYPE",xM_APPLN_ADDR.getAddr2_type()));
		xM_APPLN_ADDR.setAddr3_type(conventDic2Title("ADDR_TYPE",xM_APPLN_ADDR.getAddr3_type()));
		xM_APPLN_ADDR.setAddr4_type(conventDic2Title("ADDR_TYPE",xM_APPLN_ADDR.getAddr4_type()));
		xM_APPLN_ADDR.setOsea_f1((xM_APPLN_ADDR.getOsea_f1()!=null&&xM_APPLN_ADDR.getOsea_f1().equals("1"))?"是":"否");
		xM_APPLN_ADDR.setOsea_f2((xM_APPLN_ADDR.getOsea_f2()!=null&&xM_APPLN_ADDR.getOsea_f2().equals("1"))?"是":"否");
		xM_APPLN_ADDR.setOsea_f3((xM_APPLN_ADDR.getOsea_f3()!=null&&xM_APPLN_ADDR.getOsea_f3().equals("1"))?"是":"否");
		xM_APPLN_ADDR.setOsea_f4((xM_APPLN_ADDR.getOsea_f4()!=null&&xM_APPLN_ADDR.getOsea_f4().equals("1"))?"是":"否");
		
		xM_APPLN.setMail_to(conventDic2Title("MAIL_TO", xM_APPLN.getMail_to()));
		
		JRadModelAndView mv = new JRadModelAndView("/xm_appln/xm_appln_page6", request);
		
		mv.addObject("customerId", customerId);
		mv.addObject("customer", customerInfor);
		mv.addObject("appId", appId);
		mv.addObject("xM_APPLN", xM_APPLN);
		mv.addObject("xM_APPLN_SQED", xM_APPLN_SQED);
		mv.addObject("xM_APPLN_KPMX_ls", xM_APPLN_KPMX_ls);
		mv.addObject("xM_APPLN_HKSZ", xM_APPLN_HKSZ);
		mv.addObject("xM_APPLN_DBXX", xM_APPLN_DBXX);
		mv.addObject("xM_APPLN_QTXYKXX", xM_APPLN_QTXYKXX);
		mv.addObject("xM_APPLN_DCSC", xM_APPLN_DCSC);
		mv.addObject("xM_APPLN_TJINFO", xM_APPLN_TJINFO);
		mv.addObject("xM_APPLN_ADDR", xM_APPLN_ADDR);
		mv.addObject("xM_APPLN_SPED", xM_APPLN_SPED);
		mv.addObject("xM_APPLN_SQCL", xM_APPLN_SQCL);
		mv.addObject("xM_APPLN_ZHSX", xM_APPLN_ZHSX);
		mv.addObject("xM_APPLN_ZXXX", xM_APPLN_ZXXX);
		return mv;
	}
		
	//保存page1
	@ResponseBody
	@RequestMapping(value = "update_xm_appln_page1.json")
	@JRadOperation(JRadOperation.CREATE)
	public JRadReturnMap update_xm_appln_page1(@ModelAttribute XM_APPLN_JBZL_FORM xM_APPLN_JBZL_FORM, HttpServletRequest request) {
		JRadReturnMap returnMap = new JRadReturnMap();
		if (returnMap.isSuccess()) {
			try {
				User user = (User) Beans.get(LoginManager.class).getLoggedInUser(request);
				String customerId = request.getParameter("customer_id");
				customerId = xM_APPLN_Service.insertXM_APPLN_JBZL(customerId,xM_APPLN_JBZL_FORM,user);
				returnMap.put(RECORD_ID, customerId);
				returnMap.addGlobalMessage(CREATE_SUCCESS);
			}catch (Exception e) {
				returnMap.put(JRadConstants.MESSAGE, DataPriConstants.SYS_EXCEPTION_MSG);
				returnMap.put(JRadConstants.SUCCESS, false);
				return WebRequestHelper.processException(e);
			}
		}else{
			returnMap.setSuccess(false);
			returnMap.addGlobalError(CustomerInforConstant.CREATEERROR);
		}
		return returnMap;
	}
	
	//保存page2
	@ResponseBody
	@RequestMapping(value = "update_xm_appln_page2.json")
	@JRadOperation(JRadOperation.CHANGE)
	public JRadReturnMap update_xm_appln_page2(@ModelAttribute XM_APPLN_FORM xM_APPLN_FORM, HttpServletRequest request) {

		JRadReturnMap returnMap = new JRadReturnMap();
		if (returnMap.isSuccess()) {
			try {
				User user = (User) Beans.get(LoginManager.class).getLoggedInUser(request);
				xM_APPLN_Service.insertXM_APPLN(xM_APPLN_FORM,user);
				returnMap.put("customerId",xM_APPLN_FORM.getCustomer_id());
				returnMap.addGlobalMessage(CHANGE_SUCCESS);
			}catch (Exception e) {
				return WebRequestHelper.processException(e);
			}
		}

		return returnMap;
	}
	
	//保存page3
	@ResponseBody
	@RequestMapping(value = "update_xm_appln_page3.json")
	@JRadOperation(JRadOperation.CHANGE)
	public JRadReturnMap update_xm_appln_page3(@ModelAttribute XM_APPLN_ADDR_FORM xM_APPLN_ADDR_FORM, HttpServletRequest request) {
		
		JRadReturnMap returnMap = new JRadReturnMap();
		if (returnMap.isSuccess()) {
			try {
				User user = (User) Beans.get(LoginManager.class).getLoggedInUser(request);
				xM_APPLN_Service.insertXM_APPLN_ADDR(xM_APPLN_ADDR_FORM, user);
				returnMap.put("customerId",xM_APPLN_ADDR_FORM.getCustomer_id());
				returnMap.addGlobalMessage(CHANGE_SUCCESS);
			}catch (Exception e) {
				return WebRequestHelper.processException(e);
			}
		}

		return returnMap;
	}
	
	//保存page4
	@ResponseBody
	@RequestMapping(value = "update_xm_appln_page4.json", method = {RequestMethod.GET })
	@JRadOperation(JRadOperation.CHANGE)
	public JRadReturnMap update_xm_appln_page4(HttpServletRequest request)throws Exception {
		JRadReturnMap returnMap = new JRadReturnMap();
		if (returnMap.isSuccess()) {
			try {
				String appId = request.getParameter("appId");
				String fuheUser = request.getParameter("fuheUser");
				//设置审批金额
				CustomerApplicationInfo customerApplicationInfo = customerApplicationInfoService.findCustomerApplicationrById(appId);
				String customer_id = customerApplicationInfo.getCustomerId();
				String sqed = this.xM_APPLN_Service.findXM_APPLN_SQEDByCustomerId(customer_id).getCrdlmt_req();
				customerApplicationInfo.setApplyQuota(Integer.valueOf(sqed)*100+"");
				customerApplicationInfoService.update(customerApplicationInfo);
				
				CustomerApplicationProcess process =  customerApplicationProcessService.findByAppId(appId);
				request.setAttribute("serialNumber", process.getSerialNumber());
				request.setAttribute("applicationId", process.getApplicationId());
				request.setAttribute("applicationStatus", ApplicationStatusEnum.APPROVE);
				request.setAttribute("objection", "false");
				request.setAttribute("examineAmount", "");
				customerApplicationIntopieceWaitService.updateCustomerApplicationProcessBySerialNumberApplicationInfo1(request);
				
				
				CustomerApplicationProcess process1 =  customerApplicationProcessService.findByAppId(appId);
				
				//设置复核节点审批人
				customerApplicationProcessService.addfuheProcess(fuheUser, process1.getId());
				returnMap.addGlobalMessage(CHANGE_SUCCESS);
			}catch (Exception e) {
				e.printStackTrace();
				return WebRequestHelper.processException(e);
			}
		}
		return returnMap;
	}

	//保存page5
	@ResponseBody
	@RequestMapping(value = "update_xm_appln_page5.json")
	@JRadOperation(JRadOperation.CHANGE)
	public JRadReturnMap update_xm_appln_page5(@ModelAttribute XM_APPLN_HNPF_FORM xM_APPLN_HNPF_FORM, HttpServletRequest request) {
		JRadReturnMap returnMap = new JRadReturnMap();
		if (returnMap.isSuccess()) {
			try {
				User user = (User) Beans.get(LoginManager.class).getLoggedInUser(request);
				xM_APPLN_Service.insertXM_APPLN_HNPF(xM_APPLN_HNPF_FORM,user);
				returnMap.put("customerId",xM_APPLN_HNPF_FORM.getCustomer_id());
				returnMap.addGlobalMessage(CHANGE_SUCCESS);
			}catch (Exception e) {
				return WebRequestHelper.processException(e);
			}
		}

		return returnMap;
	}
	
	//转化数据字典的vlaue值为显示值
	private String conventDic2Title(String dicName,String value){
		if(value == null){
			return "";
		}
		DictionaryManager dictMgr = Beans.get(DictionaryManager.class);
		Dictionary dictionary = dictMgr.getDictionaryByName(dicName);
		List<DictionaryItem> ls = dictionary.getItems();
		for(DictionaryItem obj : ls){
			if(obj.getName().equals(value)){
				return obj.getTitle();
			}
		}
		return "";
	}
	
	@ResponseBody
	@RequestMapping(value = "checkFuheOrLuru.page")
	public JRadReturnMap checkFuheOrLuru(HttpServletRequest request)throws Exception {
		JRadReturnMap returnMap = new JRadReturnMap();
		String appId = request.getParameter("appId");
		CustomerApplicationProcess process = customerApplicationProcessService.findByAppId(appId);
		if(process.getFuheUser()==null||process.getFuheUser().equals("")){
			//录入节点
			returnMap.put("result", true);
		}else{
			//复核节点
			returnMap.put("result", false);
			}
		return returnMap;
		}
	
	/**
	 * 通过cardId查询是否风险客户
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "checkRisk1.json")
	public JRadReturnMap checkRisk1(HttpServletRequest request)throws Exception {
		JRadReturnMap returnMap = new JRadReturnMap();
		String cardId = request.getParameter("cardId");
		int list = riskCustomerService.findRiskByCardId(cardId);
		if(list>0){
			returnMap.put("result", true);
		}else{
			returnMap.put("result", false);
			}
		return returnMap;
		}
	
	/**
	 * 通过cardId查询产品信息
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "checkRisk2.json")
	public  String checkRisk2(HttpServletRequest request)throws Exception {
		String cardId = request.getParameter("cardId");
		String result  = riskCustomerService.findProductByCardId(cardId);
//		String result = "{D:'1000',L:[{T:'2000000',R:'30000',C:'五级分类'}]}";
		JSONObject obj = JSONObject.fromObject(result);
		return obj.toString();
		}
	
}
