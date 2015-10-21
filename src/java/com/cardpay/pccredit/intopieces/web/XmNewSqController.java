package com.cardpay.pccredit.intopieces.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cardpay.pccredit.customer.constant.CustomerInforConstant;
import com.cardpay.pccredit.datapri.constant.DataPriConstants;
import com.cardpay.pccredit.intopieces.filter.XmNewSqFilter;
import com.cardpay.pccredit.intopieces.model.XmNewSq;
import com.cardpay.pccredit.intopieces.model.XmNewSqForm;
import com.cardpay.pccredit.intopieces.service.XmNewSqService;
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
import com.wicresoft.jrad.modules.dictionary.DictionaryManager;
import com.wicresoft.jrad.modules.dictionary.model.Dictionary;
import com.wicresoft.jrad.modules.dictionary.model.DictionaryItem;
import com.wicresoft.jrad.modules.privilege.model.User;
import com.wicresoft.util.spring.Beans;
import com.wicresoft.util.spring.mvc.mv.AbstractModelAndView;
/*
 * 商圈
 */
@Controller
@RequestMapping("/product/zainformation/*")
@JRadModule("product.zainformation")
public class XmNewSqController extends BaseController {

	@Autowired
	private XmNewSqService xmNewSqService;
	/**
	 * 浏览页面（商圈申请）
	 * 
	 * @param filter
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "browse.page", method = { RequestMethod.GET })
	@JRadOperation(JRadOperation.BROWSE)
	public AbstractModelAndView browse(@ModelAttribute XmNewSqFilter filter, HttpServletRequest request) {
		filter.setRequest(request);
		User user = (User) Beans.get(LoginManager.class).getLoggedInUser(request);
		filter.setUserId(user.getId());
		QueryResult<XmNewSq> result = xmNewSqService.findAllSq(filter);
		JRadPagedQueryResult<XmNewSq> pagedResult = new JRadPagedQueryResult<XmNewSq>(filter, result);

		JRadModelAndView mv = new JRadModelAndView("/product/za_browse", request);
		Boolean orgManagerType = xmNewSqService.ifOrgManager(user.getId());
		mv.addObject(PAGED_RESULT, pagedResult);
		mv.addObject("orgManagerType", orgManagerType);
		return mv;
	}

	//申请商圈
	@ResponseBody
	@RequestMapping(value = "create.page")
	@JRadOperation(JRadOperation.CREATE)
	public AbstractModelAndView create(HttpServletRequest request) {
		JRadModelAndView mv = new JRadModelAndView("/product/za_create", request);
		return mv;
	}

	//申请商圈（保存）
	@ResponseBody
	@RequestMapping(value = "insert.json")
	@JRadOperation(JRadOperation.CREATE)
	public JRadReturnMap insert(@ModelAttribute XmNewSqForm xmNewSqForm, HttpServletRequest request) {
		JRadReturnMap returnMap = new JRadReturnMap();
		if (returnMap.isSuccess()) {
			try {
				XmNewSq qzApplnZa = xmNewSqForm.createModel(XmNewSq.class);
				User user = (User) Beans.get(LoginManager.class).getLoggedInUser(request);
				qzApplnZa.setUserId(user.getId());
				qzApplnZa.setUserName(user.getDisplayName());
				qzApplnZa.setCreatedTime(new Date());
				xmNewSqService.insert(qzApplnZa);
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
	
	//修改商圈
	@ResponseBody
	@RequestMapping(value = "change.page")
	@JRadOperation(JRadOperation.CREATE)
	public AbstractModelAndView change(HttpServletRequest request) {
		JRadModelAndView mv = new JRadModelAndView("/product/za_change", request);
		String id = request.getParameter(ID);
		XmNewSq xmNewSq = xmNewSqService.findZaById(id);
		mv.addObject("xmNewSq", xmNewSq);
		return mv;
	}
	
	//修改商圈
	@ResponseBody
	@RequestMapping(value = "update.json")
	@JRadOperation(JRadOperation.CREATE)
	public JRadReturnMap update(@ModelAttribute XmNewSqForm qzZAForm, HttpServletRequest request) {
		JRadReturnMap returnMap = new JRadReturnMap();
		String id = request.getParameter(ID);
		if (returnMap.isSuccess()) {
			try {
				xmNewSqService.update(id,qzZAForm);
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
	/**
	 * 浏览页面（商圈审批）
	 * 
	 * @param filter
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "approveBrowse.page", method = { RequestMethod.GET })
	@JRadOperation(JRadOperation.BROWSE)
	public AbstractModelAndView approveBrowse(@ModelAttribute XmNewSqFilter filter, HttpServletRequest request) {
		filter.setRequest(request);
		QueryResult<XmNewSq> result = xmNewSqService.findAllSq(filter);
		JRadPagedQueryResult<XmNewSq> pagedResult = new JRadPagedQueryResult<XmNewSq>(filter, result);

		JRadModelAndView mv = new JRadModelAndView("/product/za_browse_approve", request);
		User user = (User) Beans.get(LoginManager.class).getLoggedInUser(request);
		Boolean orgManagerType =false;
		//卡中心用户
		if(user.getUserType()==2){
			orgManagerType = xmNewSqService.ifOrgManager(user.getId());
		}
		mv.addObject("orgManagerType", orgManagerType);
		mv.addObject(PAGED_RESULT, pagedResult);
		return mv;
	}
	//审批商圈页面
	@ResponseBody
	@RequestMapping(value = "createApprove.page")
	@JRadOperation(JRadOperation.CREATE)
	public AbstractModelAndView createApprove(HttpServletRequest request) {
		JRadModelAndView mv = new JRadModelAndView("/product/za_create_approve", request);
		DictionaryManager dictMgr = Beans.get(DictionaryManager.class);
		Dictionary customerTypeDictionary = dictMgr.getDictionaryByName("KHLX");
		List<DictionaryItem> customerTypeDictItems = customerTypeDictionary.getItems();
		Dictionary customerLevelDictionary = dictMgr.getDictionaryByName("CUSTOMERMANAGERLEVEL");
		List<DictionaryItem> customerLevelDictItems = customerLevelDictionary.getItems();
		mv.addObject("customerTypeDictItems",customerTypeDictItems);
		mv.addObject("customerLevelDictItems",customerLevelDictItems);
		mv.addObject("sqId",request.getParameter(ID));
		return mv;
	}
	
	//审批商圈（通过）
	@ResponseBody
	@RequestMapping(value = "pass.json")
	@JRadOperation(JRadOperation.CREATE)
	public JRadReturnMap pass(@ModelAttribute XmNewSqForm qzZAForm, HttpServletRequest request) {
		JRadReturnMap returnMap = new JRadReturnMap();
		String id = request.getParameter("sqId");
		if (returnMap.isSuccess()) {
			try {
				xmNewSqService.pass(id,qzZAForm);
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
	//审批商圈（不通过）
	@ResponseBody
	@RequestMapping(value = "unpass.json")
	@JRadOperation(JRadOperation.CREATE)
	public JRadReturnMap unpass(@ModelAttribute XmNewSqForm qzZAForm, HttpServletRequest request) {
		JRadReturnMap returnMap = new JRadReturnMap();
		String id = request.getParameter("sqId");
		if (returnMap.isSuccess()) {
			try {
				xmNewSqService.unpass(id,qzZAForm);
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
	//修改商圈（审批后）
	@ResponseBody
	@RequestMapping(value = "passChange.page")
	@JRadOperation(JRadOperation.CREATE)
	public AbstractModelAndView passChange(HttpServletRequest request) {
		JRadModelAndView mv = new JRadModelAndView("/product/za_change_approve", request);
		String id = request.getParameter(ID);
		XmNewSq xmNewSq = xmNewSqService.findZaById(id);
		mv.addObject("xmNewSq", xmNewSq);
		DictionaryManager dictMgr = Beans.get(DictionaryManager.class);
		Dictionary customerTypeDictionary = dictMgr.getDictionaryByName("KHLX");
		List<DictionaryItem> customerTypeDictItems = customerTypeDictionary.getItems();
		Dictionary customerLevelDictionary = dictMgr.getDictionaryByName("CUSTOMERMANAGERLEVEL");
		List<DictionaryItem> customerLevelDictItems = customerLevelDictionary.getItems();
		mv.addObject("customerTypeDictItems",customerTypeDictItems);
		mv.addObject("customerLevelDictItems",customerLevelDictItems);
		return mv;
	}
	/**
	 * 浏览页面（商圈查询）
	 * 
	 * @param filter
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "search.page", method = { RequestMethod.GET })
	@JRadOperation(JRadOperation.BROWSE)
	public AbstractModelAndView search(@ModelAttribute XmNewSqFilter filter, HttpServletRequest request) {
		filter.setRequest(request);
		QueryResult<XmNewSq> result = xmNewSqService.findAllSq(filter);
		JRadPagedQueryResult<XmNewSq> pagedResult = new JRadPagedQueryResult<XmNewSq>(filter, result);

		JRadModelAndView mv = new JRadModelAndView("/product/za_browse_search", request);
		mv.addObject(PAGED_RESULT, pagedResult);
		return mv;
	}
	//查询商圈（审批后）
	@ResponseBody
	@RequestMapping(value = "show.page")
	@JRadOperation(JRadOperation.CREATE)
	public AbstractModelAndView show(HttpServletRequest request) {
		JRadModelAndView mv = new JRadModelAndView("/product/za_search_approve", request);
		String id = request.getParameter(ID);
		XmNewSq xmNewSq = xmNewSqService.findZaById(id);
		mv.addObject("xmNewSq", xmNewSq);
		DictionaryManager dictMgr = Beans.get(DictionaryManager.class);
		Dictionary customerTypeDictionary = dictMgr.getDictionaryByName("KHLX");
		List<DictionaryItem> customerTypeDictItems = customerTypeDictionary.getItems();
		Dictionary customerLevelDictionary = dictMgr.getDictionaryByName("CUSTOMERMANAGERLEVEL");
		List<DictionaryItem> customerLevelDictItems = customerLevelDictionary.getItems();
		mv.addObject("customerTypeDictItems",customerTypeDictItems);
		mv.addObject("customerLevelDictItems",customerLevelDictItems);
		return mv;
	}
}
