package com.cardpay.pccredit.intopieces.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cardpay.pccredit.customer.constant.CustomerInforConstant;
import com.cardpay.pccredit.customer.model.Maintenance;
import com.cardpay.pccredit.customer.web.MaintenanceForm;
import com.cardpay.pccredit.datapri.constant.DataPriConstants;
import com.cardpay.pccredit.datapri.web.FlatTreeNode;
import com.cardpay.pccredit.intopieces.filter.XmNewSqFilter;
import com.cardpay.pccredit.intopieces.filter.XmNewTeFilter;
import com.cardpay.pccredit.intopieces.model.XmNewSq;
import com.cardpay.pccredit.intopieces.model.XmNewSqForm;
import com.cardpay.pccredit.intopieces.model.XmNewTe;
import com.cardpay.pccredit.intopieces.service.XmNewSqService;
import com.cardpay.pccredit.intopieces.service.XmNewTeService;
import com.cardpay.pccredit.product.model.AppendixDict;
import com.cardpay.pccredit.product.model.ProductAttribute;
import com.cardpay.pccredit.product.model.ProductsAgencyAssociation;
import com.cardpay.pccredit.riskControl.constant.RiskCustomerCollectionEnum;
import com.wicresoft.jrad.base.auth.IUser;
import com.wicresoft.jrad.base.auth.JRadModule;
import com.wicresoft.jrad.base.auth.JRadOperation;
import com.wicresoft.jrad.base.constant.JRadConstants;
import com.wicresoft.jrad.base.database.model.QueryResult;
import com.wicresoft.jrad.base.web.JRadModelAndView;
import com.wicresoft.jrad.base.web.controller.BaseController;
import com.wicresoft.jrad.base.web.result.JRadPagedQueryResult;
import com.wicresoft.jrad.base.web.result.JRadReturnMap;
import com.wicresoft.jrad.base.web.security.LoginManager;
import com.wicresoft.jrad.base.web.utility.WebRequestHelper;
import com.wicresoft.jrad.modules.privilege.constant.PrivilegeConstants;
import com.wicresoft.jrad.modules.privilege.model.User;
import com.wicresoft.util.spring.Beans;
import com.wicresoft.util.spring.mvc.mv.AbstractModelAndView;
import com.wicresoft.util.web.RequestHelper;

/**
 * 客户调额流程
 */
@Controller
@RequestMapping("/customer/te/*")
@JRadModule("customer.te")
public class XmNewTeController extends BaseController {

	@Autowired
	private XmNewTeService xmNewTeService;
	
	/**
	 * 浏览页面（客户调额流程）
	 * @param filter
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "browse.page", method = { RequestMethod.GET })
	@JRadOperation(JRadOperation.BROWSE)
	public AbstractModelAndView browse(@ModelAttribute XmNewTeFilter filter, HttpServletRequest request) {
		filter.setRequest(request);
		User user = (User) Beans.get(LoginManager.class).getLoggedInUser(request);
		filter.setUserId(user.getId());
		filter.setStatus("000");
		QueryResult<XmNewTe> result = xmNewTeService.findAllTe(filter);
		
		JRadPagedQueryResult<XmNewTe> pagedResult = new JRadPagedQueryResult<XmNewTe>(filter, result);
		JRadModelAndView mv = new JRadModelAndView("/intopieces/te_browse", request);
		mv.addObject(PAGED_RESULT, pagedResult);
		return mv;
	}
	
	//发起调额
	@ResponseBody
	@RequestMapping(value = "initiateTone.json")
	@JRadOperation(JRadOperation.CREATE)
	public JRadReturnMap update(HttpServletRequest request) {
		JRadReturnMap returnMap = new JRadReturnMap();
		String id = request.getParameter(ID);
		if (returnMap.isSuccess()) {
			try {
				//TODO 判断是否是评审岗
				String status ="001";//待审核
				xmNewTeService.update(id,status);
				returnMap.put(JRadConstants.SUCCESS, true);
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
	

//========================================================================================
	/**
	 * 浏览页面（客户调额待审核）
	 * @param filter
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "browseforaudit.page", method = { RequestMethod.GET })
	@JRadOperation(JRadOperation.BROWSE)
	public AbstractModelAndView browseforaudit(@ModelAttribute XmNewTeFilter filter, HttpServletRequest request) {
		filter.setRequest(request);
		User user = (User) Beans.get(LoginManager.class).getLoggedInUser(request);
		filter.setUserId(user.getId());
		filter.setStatus("001");
		QueryResult<XmNewTe> result = xmNewTeService.findAllTe(filter);
		
		JRadPagedQueryResult<XmNewTe> pagedResult = new JRadPagedQueryResult<XmNewTe>(filter, result);
		JRadModelAndView mv = new JRadModelAndView("/intopieces/te_foraudit", request);
		mv.addObject(PAGED_RESULT, pagedResult);
		return mv;
	}
	
	
	/**
	 * 客户调额待审核查看
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "display.page")
	@JRadOperation(JRadOperation.DISPLAY)
	public AbstractModelAndView display(HttpServletRequest request) {
		JRadModelAndView mv = new JRadModelAndView("/intopieces/te_audit_display", request);
		String id = request.getParameter(ID);
		XmNewTe xmNewTe = xmNewTeService.findXmNewTeById(id);
		mv.addObject("xmNewTe", xmNewTe);
		return mv;
	}
	
	/**
	 * 审核
	 * @param form
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "update.json")
	@JRadOperation(JRadOperation.CHANGE)
	public JRadReturnMap update(@ModelAttribute MaintenanceForm form, HttpServletRequest request) {
		JRadReturnMap returnMap = new JRadReturnMap();
		if (returnMap.isSuccess()) {
			try {
			
				IUser user = Beans.get(LoginManager.class).getLoggedInUser(request);
				String modifiedBy = user.getId(); 
				String id = request.getParameter(ID);
				String status = request.getParameter("status");
				XmNewTe xmNewTe = xmNewTeService.findXmNewTeById(id);
				if("1".equals(status)){//同意
					xmNewTe.setStatus("002");//审核通过
				}else{
					xmNewTe.setStatus("003");
				}
				xmNewTe.setModifiedBy(modifiedBy);
				xmNewTe.setModifiedTime(new Date());
				boolean flag = xmNewTeService.updateXmNewTe(xmNewTe);
				if(flag){
					/*returnMap.put(ID, "");
					returnMap.put(RECORD_ID,"");*/
					returnMap.addGlobalMessage(CHANGE_SUCCESS);
				}else{
					returnMap.setSuccess(false);
					returnMap.addGlobalError(CustomerInforConstant.UPDATEERROR);
				}
			}catch (Exception e) {
				returnMap.put(JRadConstants.MESSAGE, DataPriConstants.SYS_EXCEPTION_MSG);
				returnMap.put(JRadConstants.SUCCESS, false);
				return WebRequestHelper.processException(e);
			}
		}
		return returnMap;
	}
	
	
	/**
	 * 浏览页面（客户额度管理）
	 * @param filter
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "browsemanager.page", method = { RequestMethod.GET })
	@JRadOperation(JRadOperation.BROWSE)
	public AbstractModelAndView browsemanager(@ModelAttribute XmNewTeFilter filter, HttpServletRequest request) {
		filter.setRequest(request);
		User user = (User) Beans.get(LoginManager.class).getLoggedInUser(request);
		filter.setUserId(user.getId());
		filter.setStatus("002");//审核通过
		QueryResult<XmNewTe> result = xmNewTeService.findAllTe(filter);
		JRadPagedQueryResult<XmNewTe> pagedResult = new JRadPagedQueryResult<XmNewTe>(filter, result);
		JRadModelAndView mv = new JRadModelAndView("/intopieces/te_manager", request);
		mv.addObject(PAGED_RESULT, pagedResult);
		return mv;
	}
	
	
	/**
	 * 普通卡临时调额评估
	 * @param filter
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "normalbrowse.page", method = { RequestMethod.GET })
	@JRadOperation(JRadOperation.BROWSE)
	public AbstractModelAndView normalbrowse(@ModelAttribute XmNewTeFilter filter, HttpServletRequest request) {
		filter.setRequest(request);
		User user = (User) Beans.get(LoginManager.class).getLoggedInUser(request);
		filter.setUserId(user.getId());
		filter.setStatus("000");//审核通过
		QueryResult<XmNewTe> result = xmNewTeService.findAllTe(filter);
		JRadPagedQueryResult<XmNewTe> pagedResult = new JRadPagedQueryResult<XmNewTe>(filter, result);
		JRadModelAndView mv = new JRadModelAndView("/intopieces/te_normal", request);
		mv.addObject(PAGED_RESULT, pagedResult);
		return mv;
	}
	
}
