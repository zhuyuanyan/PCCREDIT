package com.cardpay.pccredit.intopieces.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cardpay.pccredit.common.UploadFileTool;
import com.cardpay.pccredit.customer.constant.CustomerInforConstant;
import com.cardpay.pccredit.datapri.constant.DataPriConstants;
import com.cardpay.pccredit.intopieces.filter.AddIntoPiecesFilter;
import com.cardpay.pccredit.intopieces.filter.XmNewFlowFilter;
import com.cardpay.pccredit.intopieces.filter.XmNewSqFilter;
import com.cardpay.pccredit.intopieces.model.CustomerApplicationInfo;
import com.cardpay.pccredit.intopieces.model.XmNewFlow;
import com.cardpay.pccredit.intopieces.model.XmNewSq;
import com.cardpay.pccredit.intopieces.model.XmNewSqForm;
import com.cardpay.pccredit.intopieces.model.XmNewSqLog;
import com.cardpay.pccredit.intopieces.service.XmNewSqService;
import com.cardpay.pccredit.product.filter.ProductFilter;
import com.cardpay.pccredit.product.model.ProductAttribute;
import com.cardpay.pccredit.product.web.ProductAttributeForm;
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
//		Boolean orgManagerType = xmNewSqService.ifOrgManager(user.getId());
		mv.addObject(PAGED_RESULT, pagedResult);
//		mv.addObject("orgManagerType", orgManagerType);
		return mv;
	}

	//新建商圈
	@ResponseBody
	@RequestMapping(value = "create.page")
	@JRadOperation(JRadOperation.CREATE)
	public AbstractModelAndView create(HttpServletRequest request) {
		JRadModelAndView mv = new JRadModelAndView("/product/za_create", request);
		return mv;
	}

	//新建商圈（保存）
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
	//申请商圈
	@ResponseBody
	@RequestMapping(value = "apply.json")
	@JRadOperation(JRadOperation.CREATE)
	public JRadReturnMap apply(HttpServletRequest request) {
		JRadReturnMap returnMap = new JRadReturnMap();
		try {
			String sqId = request.getParameter(ID);
			Boolean result = xmNewSqService.apply(sqId);
			if(result){
				returnMap.put(JRadConstants.SUCCESS, true);
				returnMap.addGlobalMessage(CREATE_SUCCESS);
			}else{
				returnMap.put(JRadConstants.MESSAGE, DataPriConstants.SYS_EXCEPTION_MSG);
				returnMap.put(JRadConstants.SUCCESS, false);
			}
		}catch (Exception e) {
			returnMap.put(JRadConstants.MESSAGE, DataPriConstants.SYS_EXCEPTION_MSG);
			returnMap.put(JRadConstants.SUCCESS, false);
			return WebRequestHelper.processException(e);
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
		User user = (User) Beans.get(LoginManager.class).getLoggedInUser(request);
		filter.setApplnId(user.getId());
		QueryResult<XmNewSq> result = xmNewSqService.findApplyZaByFilter(filter);
		JRadPagedQueryResult<XmNewSq> pagedResult = new JRadPagedQueryResult<XmNewSq>(filter, result);

		JRadModelAndView mv = new JRadModelAndView("/product/za_browse_approve", request);
		mv.addObject(PAGED_RESULT, pagedResult);
		return mv;
	}
	//审批商圈页面
	@ResponseBody
	@RequestMapping(value = "createApprove.page")
	@JRadOperation(JRadOperation.CREATE)
	public AbstractModelAndView createApprove(HttpServletRequest request) {
		JRadModelAndView mv = new JRadModelAndView("/product/za_create_approve", request);
		String sqId = request.getParameter(ID);
		String nodeName = request.getParameter("nodeName");
		//获取商圈流程日志
		List<XmNewSqLog> list = xmNewSqService.getSqLog(sqId);
		DictionaryManager dictMgr = Beans.get(DictionaryManager.class);
		Dictionary customerTypeDictionary = dictMgr.getDictionaryByName("KHLX");
		List<DictionaryItem> customerTypeDictItems = customerTypeDictionary.getItems();
		Dictionary customerLevelDictionary = dictMgr.getDictionaryByName("CUSTOMERMANAGERLEVEL");
		List<DictionaryItem> customerLevelDictItems = customerLevelDictionary.getItems();
		mv.addObject("customerTypeDictItems",customerTypeDictItems);
		mv.addObject("customerLevelDictItems",customerLevelDictItems);
		mv.addObject("sqId",sqId);
		mv.addObject("jName",nodeName);
		mv.addObject("list",list);
		return mv;
	}
	
	//审批商圈（通过）
	@ResponseBody
	@RequestMapping(value = "pass.json")
	@JRadOperation(JRadOperation.CREATE)
	public JRadReturnMap pass(@ModelAttribute XmNewSqForm qzZAForm, HttpServletRequest request) {
		JRadReturnMap returnMap = new JRadReturnMap();
		String id = request.getParameter("sqId");
		String nodeName = request.getParameter("jName");
		String changeUser = request.getParameter("changeUser");
		String remark = request.getParameter("remark");
		Boolean resultBoolean=true;
		if (returnMap.isSuccess()) {
			try {
				if(changeUser!=null&&changeUser!=""){
					if(changeUser.equals("2")){
						String auditUserIds = request.getParameter("auditUserIds");
						//选择转办人员先审批
						resultBoolean = xmNewSqService.passZ(id,qzZAForm,nodeName,request,auditUserIds,remark);
					}else{
						//评审审批(节点跳跃)
						resultBoolean = xmNewSqService.passP(id,qzZAForm,nodeName,request,remark);
					}
				}else{
					//其它节点审批
					resultBoolean = xmNewSqService.pass(id,qzZAForm,nodeName,request,remark);
				}
				if(resultBoolean){
					returnMap.put(JRadConstants.SUCCESS, true);
					returnMap.addGlobalMessage(CustomerInforConstant.APPROVESUCCESS);
				}else{
					returnMap.setSuccess(false);
					returnMap.addGlobalError(CustomerInforConstant.APPROVEERROR);
				}
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
		User user = (User) Beans.get(LoginManager.class).getLoggedInUser(request);
		String nodeName = request.getParameter("jName");
		if (returnMap.isSuccess()) {
			try {
				xmNewSqService.unpass(id,nodeName,user,request);
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
		QueryResult<XmNewSq> result = xmNewSqService.findApplyZaByFilter(filter);
		JRadPagedQueryResult<XmNewSq> pagedResult = new JRadPagedQueryResult<XmNewSq>(filter, result);

		JRadModelAndView mv = new JRadModelAndView("/product/za_browse_search", request);
		mv.addObject(PAGED_RESULT, pagedResult);
		return mv;
	}
	//查询商圈
	@ResponseBody
	@RequestMapping(value = "read.page")
	@JRadOperation(JRadOperation.CREATE)
	public AbstractModelAndView read(HttpServletRequest request) {
		JRadModelAndView mv = new JRadModelAndView("/product/za_search_approve", request);
		String sqId = request.getParameter(ID);
		String nodeName = request.getParameter("nodeName");
		//获取商圈流程日志
		List<XmNewSqLog> list = xmNewSqService.getSqLog(sqId);
		DictionaryManager dictMgr = Beans.get(DictionaryManager.class);
		Dictionary customerTypeDictionary = dictMgr.getDictionaryByName("KHLX");
		List<DictionaryItem> customerTypeDictItems = customerTypeDictionary.getItems();
		Dictionary customerLevelDictionary = dictMgr.getDictionaryByName("CUSTOMERMANAGERLEVEL");
		List<DictionaryItem> customerLevelDictItems = customerLevelDictionary.getItems();
		mv.addObject("customerTypeDictItems",customerTypeDictItems);
		mv.addObject("customerLevelDictItems",customerLevelDictItems);
		mv.addObject("sqId",sqId);
		mv.addObject("jName",nodeName);
		mv.addObject("list",list);
		return mv;
	}
	
	/**
	 * 商圈流程配置页面
	 * 
	 * @param filter
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "browseFlow.page", method = { RequestMethod.GET })
	@JRadOperation(JRadOperation.BROWSE)
	public AbstractModelAndView browseFlow(@ModelAttribute XmNewFlowFilter filter, HttpServletRequest request) {
		filter.setRequest(request);

		QueryResult<XmNewFlow> result = xmNewSqService.findSqFlowByFilter(filter);
		JRadPagedQueryResult<XmNewFlow> pagedResult = new JRadPagedQueryResult<XmNewFlow>(filter, result);

		JRadModelAndView mv = new JRadModelAndView("/product/sq_flow_browse", request);
		mv.addObject(PAGED_RESULT, pagedResult);

		return mv;
	}
	/**
	 * 跳转到商圈流程增加页面
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "createFlow.page")
	@JRadOperation(JRadOperation.CREATE)
	public AbstractModelAndView createFlow(HttpServletRequest request) {
		JRadModelAndView mv = new JRadModelAndView("/product/sq_flow_create", request);

		return mv;
	}
	
	/**
	 * 流程添加
	 * 
	 * @param ProductAttribute
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "insert.json", method = { RequestMethod.POST })
	@JRadOperation(JRadOperation.CREATE)
	public JRadReturnMap insert(HttpServletRequest request) {
		JRadReturnMap returnMap = new JRadReturnMap();
		try {
			String flowName = request.getParameter("flowName");
			String remark = request.getParameter("remark");
			XmNewFlow xmNewFlow = new XmNewFlow();
			xmNewFlow.setFlowName(flowName);
			xmNewFlow.setRemark(remark);
			xmNewSqService.insertSqFlow(xmNewFlow);
			returnMap.put(MESSAGE, "添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return WebRequestHelper.processException(e);

		}
		return returnMap;
	}
	
	//商圈上传材料
	@ResponseBody
	@RequestMapping(value = "upload.page", method = { RequestMethod.GET })
	@JRadOperation(JRadOperation.BROWSE)
	public AbstractModelAndView upload(@ModelAttribute XmNewSqFilter filter,HttpServletRequest request) {
		filter.setRequest(request);
		String sqId =request.getParameter(ID);
		filter.setSqId(sqId);
		QueryResult<XmNewSqUploadForm> result = xmNewSqService.findSqUploadList(filter);
		JRadPagedQueryResult<XmNewSqUploadForm> pagedResult = new JRadPagedQueryResult<XmNewSqUploadForm>(filter, result);
		
		JRadModelAndView mv = new JRadModelAndView("/product/sq_upload",request);
		mv.addObject(PAGED_RESULT, pagedResult);
		
		mv.addObject("sqId", sqId);
		
		return mv;
	}
	
	//上传材料
	@ResponseBody
	@RequestMapping(value = "upload_save.json")
	public Map<String, Object> upload_save(@RequestParam(value = "file", required = false) MultipartFile file,HttpServletRequest request,HttpServletResponse response) throws Exception {        
		response.setContentType("text/html;charset=utf-8");
		User user = (User) Beans.get(LoginManager.class).getLoggedInUser(request);
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if(file==null||file.isEmpty()){
				map.put(JRadConstants.SUCCESS, false);
				map.put(JRadConstants.MESSAGE, CustomerInforConstant.IMPORTEMPTY);
				return map;
			}
			String sqId = request.getParameter(ID);
			xmNewSqService.importImage(file,sqId,user);
			map.put(JRadConstants.SUCCESS, true);
			map.put(JRadConstants.MESSAGE, CustomerInforConstant.IMPORTSUCCESS);
			JSONObject obj = JSONObject.fromObject(map);
			response.getWriter().print(obj.toString());
		} catch (Exception e) {
			e.printStackTrace();
			map.put(JRadConstants.SUCCESS, false);
			map.put(JRadConstants.MESSAGE, "上传失败:"+e.getMessage());
			JSONObject obj = JSONObject.fromObject(map);
			response.getWriter().print(obj.toString());
		}
		return null;
	}
	//删除影像资料
	@ResponseBody
	@RequestMapping(value = "deleteImage.json")
	public JRadReturnMap deleteImage(HttpServletRequest request) {
		JRadReturnMap returnMap = new JRadReturnMap();
		try {
			String id = request.getParameter("id");
			xmNewSqService.deleteImage(id);
			
			returnMap.addGlobalMessage(CHANGE_SUCCESS);
		} catch (Exception e) {
			return WebRequestHelper.processException(e);
		}

		return returnMap;
	}
	//下载影像资料
	@ResponseBody
	@RequestMapping(value = "downLoadYxzl.json",method = { RequestMethod.GET })
	@JRadOperation(JRadOperation.EXPORT)
	public AbstractModelAndView downLoadYxzlById(HttpServletRequest request,HttpServletResponse response){
		try {
			xmNewSqService.downLoadYxzlById(response,request.getParameter(ID));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	//商圈上传材料（查看）
	@ResponseBody
	@RequestMapping(value = "show.page", method = { RequestMethod.GET })
	@JRadOperation(JRadOperation.BROWSE)
	public AbstractModelAndView show(@ModelAttribute XmNewSqFilter filter,HttpServletRequest request) {
		filter.setRequest(request);
		String sqId =request.getParameter(ID);
		filter.setSqId(sqId);
		QueryResult<XmNewSqUploadForm> result = xmNewSqService.findSqUploadList(filter);
		JRadPagedQueryResult<XmNewSqUploadForm> pagedResult = new JRadPagedQueryResult<XmNewSqUploadForm>(filter, result);
		
		JRadModelAndView mv = new JRadModelAndView("/product/sq_upload_show",request);
		mv.addObject(PAGED_RESULT, pagedResult);
		
		mv.addObject("sqId", sqId);
		
		return mv;
	}
}
