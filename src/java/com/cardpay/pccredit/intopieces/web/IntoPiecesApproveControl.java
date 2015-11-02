package com.cardpay.pccredit.intopieces.web;


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

import com.cardpay.pccredit.customer.constant.CustomerInforConstant;
import com.cardpay.pccredit.customer.dao.CustomerInforDao;
import com.cardpay.pccredit.customer.dao.comdao.CustomerInforCommDao;
import com.cardpay.pccredit.customer.filter.CustomerInforFilter;
import com.cardpay.pccredit.customer.model.CustomerInfor;
import com.cardpay.pccredit.customer.service.CustomerInforService;
import com.cardpay.pccredit.datapri.constant.DataPriConstants;
import com.cardpay.pccredit.datapri.service.DataAccessSqlService;
import com.cardpay.pccredit.intopieces.constant.Constant;
import com.cardpay.pccredit.intopieces.filter.AddIntoPiecesFilter;
import com.cardpay.pccredit.intopieces.model.XmNewSq;
import com.cardpay.pccredit.intopieces.service.AddIntoPiecesService;
import com.cardpay.pccredit.intopieces.service.CustomerApplicationIntopieceWaitService;
import com.cardpay.pccredit.intopieces.service.CustomerApplicationProcessService;
import com.cardpay.pccredit.intopieces.service.IntoPiecesService;
import com.cardpay.pccredit.intopieces.service.XmNewSqService;
import com.cardpay.pccredit.manager.model.AccountManagerParameter;
import com.cardpay.pccredit.manager.service.AccountManagerParameterService;
import com.cardpay.pccredit.product.filter.ProductFilter;
import com.cardpay.pccredit.product.model.ProductAttribute;
import com.cardpay.pccredit.product.service.ProductService;
import com.cardpay.pccredit.riskControl.filter.RiskCustomerFilter;
import com.cardpay.pccredit.riskControl.model.RiskCustomer;
import com.cardpay.pccredit.riskControl.service.RiskCustomerService;
import com.cardpay.pccredit.system.service.NodeAuditService;
import com.cardpay.pccredit.xm_appln.service.XM_APPLN_Service;
import com.cardpay.workflow.service.ProcessService;
import com.wicresoft.jrad.base.auth.IUser;
import com.wicresoft.jrad.base.auth.JRadModule;
import com.wicresoft.jrad.base.auth.JRadOperation;
import com.wicresoft.jrad.base.constant.JRadConstants;
import com.wicresoft.jrad.base.database.dao.common.CommonDao;
import com.wicresoft.jrad.base.database.model.QueryResult;
import com.wicresoft.jrad.base.web.JRadModelAndView;
import com.wicresoft.jrad.base.web.controller.BaseController;
import com.wicresoft.jrad.base.web.result.JRadPagedQueryResult;
import com.wicresoft.jrad.base.web.result.JRadReturnMap;
import com.wicresoft.jrad.base.web.security.LoginManager;
import com.wicresoft.jrad.base.web.utility.WebRequestHelper;
import com.wicresoft.util.spring.Beans;
import com.wicresoft.util.spring.mvc.mv.AbstractModelAndView;

@Controller
@RequestMapping("/intopieces/intopiecesapprove/*")
@JRadModule("intopieces.intopiecesapprove")
public class IntoPiecesApproveControl extends BaseController {

	@Autowired
	private IntoPiecesService intoPiecesService;

	@Autowired
	private CustomerInforService customerInforService;

	@Autowired
	private ProductService productService;
	
	@Autowired
	private CustomerInforService customerInforservice;
	
	@Autowired
	private XM_APPLN_Service xM_APPLN_Service;
	
	@Autowired
	private DataAccessSqlService dataAccessSqlService;

	@Autowired
	private CommonDao commonDao;

	@Autowired
	private CustomerInforDao customerInforDao;
	
	@Autowired
	private CustomerInforCommDao customerinforcommDao;
	
	@Autowired
	private NodeAuditService nodeAuditService;
	
	@Autowired
	private ProcessService processService;
	
	@Autowired
	private CustomerApplicationIntopieceWaitService customerApplicationIntopieceWaitService;
	
	@Autowired
	private CustomerApplicationProcessService customerApplicationProcessService;
	
	@Autowired
	private XmNewSqService xmNewSqService;
	
	@Autowired
	private AccountManagerParameterService accountManagerParameterService;
	
	@Autowired
	private RiskCustomerService riskCustomerService;
	@Autowired
	private AddIntoPiecesService addIntoPiecesService;
	
	/**
	 * 申请页面
	 * 
	 * @param filter
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "approve.page", method = { RequestMethod.GET })
	public AbstractModelAndView browse(@ModelAttribute CustomerInforFilter filter,HttpServletRequest request) {
        filter.setRequest(request);
        IUser user = Beans.get(LoginManager.class).getLoggedInUser(request);
		filter.setUserId(user.getId());
		QueryResult<CustomerInfor> result = customerInforservice.findCustomerInforByFilter(filter);
		for(int i=0;i<result.getItems().size();i++){
				Boolean processBoolean = customerInforservice.ifProcess(result.getItems().get(i).getId());
				if(processBoolean){
					result.getItems().get(i).setProcessId(Constant.APP_STATE_1);
				}else if(result.getItems().get(i).getProcessId()==null){
					result.getItems().get(i).setProcessId(Constant.APP_STATE_2);
				}else{
					result.getItems().get(i).setProcessId(Constant.APP_STATE_3);
				}
			}
		JRadPagedQueryResult<CustomerInfor> pagedResult = new JRadPagedQueryResult<CustomerInfor>(filter, result);
		JRadModelAndView mv = new JRadModelAndView("/intopieces/intopieces_approve",
                                                    request);
		mv.addObject(PAGED_RESULT, pagedResult);

		return mv;
	}
	/**
	 * 执行申请
	 * @param customerInforFilter
	 * @param request
	 * @return
	 */
		@ResponseBody
		@RequestMapping(value = "xm_appln_page0_apply.json")
		public JRadReturnMap xm_appln_page0_apply(@ModelAttribute CustomerInforFilter customerInforFilter, HttpServletRequest request) {
			JRadReturnMap returnMap = new JRadReturnMap();
			if (returnMap.isSuccess()) {
				try {
					//进件方式
					String customerId = request.getParameter("customerId");
					String intopiecesType = request.getParameter("intopiecesType");
					String applyQuota = request.getParameter("applyQuota");
					String ApplyIntopiecesSpareType_1 = request.getParameter("ApplyIntopiecesSpareType_1");
					String ApplyIntopiecesSpareType_2 = request.getParameter("ApplyIntopiecesSpareType_2");
					String productId = request.getParameter("productId");
					String custType = request.getParameter("custType");
					
   				 
					//先判断是否已有流程
					Boolean processBoolean = customerInforservice.ifProcess(customerId);
					if(processBoolean){
						returnMap.addGlobalMessage("此客户正在申请进件，无法再次申请!");
						returnMap.put(RECORD_ID, customerId);
						returnMap.put("message","此客户正在申请进件，无法再次申请!");
					}else{
						//设置流程开始
						xM_APPLN_Service.saveApply(customerId,
												   intopiecesType,
												   ApplyIntopiecesSpareType_1,
												   custType,
												   applyQuota,
												   productId);
						
						returnMap.put(RECORD_ID, customerId);
						returnMap.addGlobalMessage(CREATE_SUCCESS);
						returnMap.put("message","申请成功");
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

//======================================20151019================================//
		/**
		 * 选择产品
		 */
		@ResponseBody
		@RequestMapping(value = "browseProduct.page", method = { RequestMethod.GET })
		@JRadOperation(JRadOperation.BROWSE)
		public AbstractModelAndView browseProduct(@ModelAttribute ProductFilter filter, HttpServletRequest request) {
			filter.setRequest(request);
			IUser user = Beans.get(LoginManager.class).getLoggedInUser(request);
			QueryResult<ProductAttribute> result = productService.findProducts(filter);
			JRadPagedQueryResult<ProductAttribute> pagedResult = new JRadPagedQueryResult<ProductAttribute>(filter, result);
			JRadModelAndView mv = new JRadModelAndView("/intopieces/intopieces_product_browse", request);
			Boolean hasJjzg = productService.hasApproveJJ(user.getId());
			mv.addObject(PAGED_RESULT, pagedResult);
			mv.addObject("hasJjzg", hasJjzg);
			return mv;
		}
		
		/**
		 * 选择客户
		 * @param filter
		 * @param request
		 * @return
		 */
		@ResponseBody
		@RequestMapping(value = "browseCustomer.page", method = { RequestMethod.GET })
		public AbstractModelAndView browseCustomer(@ModelAttribute CustomerInforFilter filter,HttpServletRequest request) {
	        filter.setRequest(request);
	        IUser user = Beans.get(LoginManager.class).getLoggedInUser(request);
			filter.setUserId(user.getId());
			QueryResult<CustomerInfor> result = customerInforservice.findCustomerInforByFilterAndProductId(filter);
			JRadPagedQueryResult<CustomerInfor> pagedResult = new JRadPagedQueryResult<CustomerInfor>(filter, result);
			JRadModelAndView mv = new JRadModelAndView("/intopieces/intopieces_approve",request);
			//获取商圈信息
			List<XmNewSq> xmNewSq = xmNewSqService.findPassSq();
			//获取客户经理资格信息
			List<AccountManagerParameter> parameterList = accountManagerParameterService.getParametersByUserId(user.getId());

			if(parameterList.size()>0){
				mv.addObject("ed", parameterList.get(0).getApplyQuatoLimit());
				mv.addObject("typeCode", parameterList.get(0).getCustomerTypeCode());
			}else{
				mv.addObject("ed", null);
				mv.addObject("typeCode", null);
			}
			mv.addObject(PAGED_RESULT, pagedResult);
			mv.addObject("xmNewSq", xmNewSq);
			mv.addObject("productId", filter.getProductId());
			return mv;
		}
		
		/**
		 * 风险名单检测
		 * a) 系统将检查客户是否属于风险名单客户，若属于风险名单，则将对客户经理进行提示，告知该客户属于风险名单，客户经理可选择继续进件或者停止进件
		 */
		@ResponseBody
		@RequestMapping(value = "isInBlacklist.json")
		public JRadReturnMap isInBlacklist(HttpServletRequest request) {
			String customerId = request.getParameter(ID);
			JRadReturnMap returnMap = new JRadReturnMap();
			
			if (returnMap.isSuccess()) {
				try {
					//判断客户是否风险客户
					RiskCustomerFilter riskCustomerFilter = new RiskCustomerFilter();
					riskCustomerFilter.setCustomerId(customerId);
					Boolean isInList = riskCustomerService.isInBlacklist(riskCustomerFilter);
					returnMap.put("isInList", isInList);
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
		//导入调查报告页面
		@ResponseBody
		@RequestMapping(value = "reportImport.page", method = { RequestMethod.GET })
		@JRadOperation(JRadOperation.BROWSE)
		public AbstractModelAndView reportImport(@ModelAttribute AddIntoPiecesFilter filter,HttpServletRequest request) {
			filter.setRequest(request);
			
			String customerId =request.getParameter("customerId");
			String intopiecesType =request.getParameter("intopiecesType");
			String applyQuota =request.getParameter("applyQuota");
			String ApplyIntopiecesSpareType_1 =request.getParameter("ApplyIntopiecesSpareType_1");
			String ApplyIntopiecesSpareType_2 =request.getParameter("ApplyIntopiecesSpareType_2");
			String custType =request.getParameter("custType");
			String productId =request.getParameter("productId");
			
			QueryResult<LocalExcelForm> result = addIntoPiecesService.findLocalExcelByProductAndCustomer(filter);
			JRadPagedQueryResult<LocalExcelForm> pagedResult = new JRadPagedQueryResult<LocalExcelForm>(filter, result);
			JRadModelAndView mv = new JRadModelAndView("/intopieces/report_import",request);
			mv.addObject(PAGED_RESULT, pagedResult);
			mv.addObject("parameters", filter);
			
			mv.addObject("customerId", customerId);
			mv.addObject("intopiecesType", intopiecesType);
			mv.addObject("applyQuota", applyQuota);
			mv.addObject("ApplyIntopiecesSpareType_1", ApplyIntopiecesSpareType_1);
			mv.addObject("ApplyIntopiecesSpareType_2", ApplyIntopiecesSpareType_2);
			mv.addObject("custType", custType);
			mv.addObject("productId", productId);

			return mv;
		}
		//导入调查报告
		@ResponseBody
		@RequestMapping(value = "reportImport.json")
		public Map<String, Object> reportImport_json(@RequestParam(value = "file", required = false) MultipartFile file,HttpServletRequest request,HttpServletResponse response) throws Exception {        
			response.setContentType("text/html;charset=utf-8");
			Map<String, Object> map = new HashMap<String, Object>();
			try {
				if(file==null||file.isEmpty()){
					map.put(JRadConstants.SUCCESS, false);
					map.put(JRadConstants.MESSAGE, CustomerInforConstant.IMPORTEMPTY);
					return map;
				}
				String productId = request.getParameter("productId");
				String customerId = request.getParameter("customerId");
				addIntoPiecesService.importExcel(file,productId,customerId);
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
		//行内模型
		@ResponseBody
		@RequestMapping(value = "bankModel.page", method = { RequestMethod.GET })
		@JRadOperation(JRadOperation.BROWSE)
		public AbstractModelAndView bankModel(@ModelAttribute AddIntoPiecesFilter filter,HttpServletRequest request) {
			filter.setRequest(request);
			QueryResult<LocalExcelForm> result = addIntoPiecesService.findLocalExcelByProductAndCustomer(filter);
			JRadPagedQueryResult<LocalExcelForm> pagedResult = new JRadPagedQueryResult<LocalExcelForm>(filter, result);
			JRadModelAndView mv = new JRadModelAndView("/intopieces/report_model",request);
			mv.addObject(PAGED_RESULT, pagedResult);
			mv.addObject("parameters", filter);
			return mv;
		}
}
