package com.cardpay.pccredit.intopieces.web;


import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cardpay.pccredit.customer.constant.CustomerInforConstant;
import com.cardpay.pccredit.customer.dao.CustomerInforDao;
import com.cardpay.pccredit.customer.dao.comdao.CustomerInforCommDao;
import com.cardpay.pccredit.customer.filter.CustomerInforFilter;
import com.cardpay.pccredit.customer.model.CustomerInfor;
import com.cardpay.pccredit.customer.service.CustomerInforService;
import com.cardpay.pccredit.datapri.constant.DataPriConstants;
import com.cardpay.pccredit.datapri.service.DataAccessSqlService;
import com.cardpay.pccredit.intopieces.constant.Constant;
import com.cardpay.pccredit.intopieces.service.CustomerApplicationIntopieceWaitService;
import com.cardpay.pccredit.intopieces.service.CustomerApplicationProcessService;
import com.cardpay.pccredit.intopieces.service.IntoPiecesService;
import com.cardpay.pccredit.product.service.ProductService;
import com.cardpay.pccredit.system.service.NodeAuditService;
import com.cardpay.pccredit.xm_appln.service.XM_APPLN_Service;
import com.cardpay.workflow.service.ProcessService;
import com.wicresoft.jrad.base.auth.IUser;
import com.wicresoft.jrad.base.auth.JRadModule;
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
					String customerId = request.getParameter("id");
					//进件方式
					String intopiecesType = request.getParameter("intopiecesType");
					//先判断是否已有流程
					Boolean processBoolean = customerInforservice.ifProcess(customerId);
					if(processBoolean){
						returnMap.addGlobalMessage("此客户正在申请进件，无法再次申请!");
						returnMap.put(RECORD_ID, customerId);
						returnMap.put("message","此客户正在申请进件，无法再次申请!");
					}
					else{
						//设置流程开始
						xM_APPLN_Service.saveApply(customerId,intopiecesType);
						
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
		

}
