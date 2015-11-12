package com.cardpay.pccredit.intopieces.web;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.osgi.service.application.ApplicationAdminPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cardpay.pccredit.customer.filter.CustomerInforFilter;
import com.cardpay.pccredit.customer.model.CustomerCareersInformation;
import com.cardpay.pccredit.customer.model.CustomerInfor;
import com.cardpay.pccredit.customer.service.CustomerInforService;
import com.cardpay.pccredit.intopieces.constant.ApplicationStatusEnum;
import com.cardpay.pccredit.intopieces.constant.Constant;
import com.cardpay.pccredit.intopieces.filter.CustomerApplicationProcessFilter;
import com.cardpay.pccredit.intopieces.model.BasicCustomerInformationS;
import com.cardpay.pccredit.intopieces.model.CustomerAccountData;
import com.cardpay.pccredit.intopieces.model.CustomerApplicationCom;
import com.cardpay.pccredit.intopieces.model.CustomerApplicationContact;
import com.cardpay.pccredit.intopieces.model.CustomerApplicationContactVo;
import com.cardpay.pccredit.intopieces.model.CustomerApplicationGuarantor;
import com.cardpay.pccredit.intopieces.model.CustomerApplicationGuarantorVo;
import com.cardpay.pccredit.intopieces.model.CustomerApplicationInfo;
import com.cardpay.pccredit.intopieces.model.CustomerApplicationOther;
import com.cardpay.pccredit.intopieces.model.CustomerApplicationProcess;
import com.cardpay.pccredit.intopieces.model.CustomerApplicationRecom;
import com.cardpay.pccredit.intopieces.model.CustomerApplicationRecomVo;
import com.cardpay.pccredit.intopieces.model.CustomerCareersInformationS;
import com.cardpay.pccredit.intopieces.service.CustomerApplicationIntopieceWaitService;
import com.cardpay.pccredit.intopieces.service.CustomerApplicationProcessService;
import com.cardpay.pccredit.intopieces.service.IntoPiecesService;
import com.cardpay.pccredit.product.model.AddressAccessories;
import com.cardpay.pccredit.product.model.AppendixDict;
import com.cardpay.pccredit.product.model.ProductAttribute;
import com.cardpay.pccredit.product.service.ProductService;
import com.cardpay.pccredit.system.model.NodeControl;
import com.cardpay.pccredit.system.model.SystemConfiguration;
import com.cardpay.pccredit.system.service.NodeAuditService;
import com.cardpay.pccredit.system.service.SystemConfigurationService;
import com.cardpay.pccredit.system.web.NodeAuditForm;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_POZL;
import com.cardpay.pccredit.xm_appln.service.XM_APPLN_Service;
import com.cardpay.workflow.service.ProcessService;
import com.wicresoft.jrad.base.auth.IUser;
import com.wicresoft.jrad.base.auth.JRadModule;
import com.wicresoft.jrad.base.auth.JRadOperation;
import com.wicresoft.jrad.base.constant.JRadConstants;
import com.wicresoft.jrad.base.database.id.IDGenerator;
import com.wicresoft.jrad.base.database.model.BusinessModel;
import com.wicresoft.jrad.base.database.model.QueryResult;
import com.wicresoft.jrad.base.i18n.I18nHelper;
import com.wicresoft.jrad.base.web.JRadModelAndView;
import com.wicresoft.jrad.base.web.controller.BaseController;
import com.wicresoft.jrad.base.web.result.JRadPagedQueryResult;
import com.wicresoft.jrad.base.web.result.JRadReturnMap;
import com.wicresoft.jrad.base.web.security.LoginManager;
import com.wicresoft.jrad.base.web.utility.WebRequestHelper;
import com.wicresoft.jrad.modules.privilege.model.User;
import com.wicresoft.jrad.modules.privilege.service.UserService;
import com.wicresoft.util.spring.Beans;
import com.wicresoft.util.spring.mvc.mv.AbstractModelAndView;
import com.wicresoft.util.web.RequestHelper;

/**
 * CustomerApplicationIntopieceWaitController类的描述
 * 
 * @author 王海东
 * @created on 2014-11-28
 * 
 * @version $Id:$
 */
@Controller
@RequestMapping("/intopieces/applyintopieceend/*")
@JRadModule("intopieces.applyintopieceend")
public class IntopiecesEndApplnController extends BaseController {
	@Autowired
	private CustomerApplicationIntopieceWaitService customerApplicationIntopieceWaitService;
	@Autowired
	private CustomerInforService customerInforService;
	@Autowired
	private UserService userService;

	@Autowired
	private ProcessService processService;

	@Autowired
	private IntoPiecesService intoPiecesService;

	@Autowired
	private ProductService productService;
	
	@Autowired
	private NodeAuditService nodeAuditService;
	
	@Autowired
	private SystemConfigurationService systemConfigurationService;
	
	@Autowired
	private CustomerApplicationProcessService customerApplicationProcessService;
	@Autowired
	private XM_APPLN_Service xm_APPLN_Service;

	/**
	 * 终审浏览页面
	 * 
	 * @param filter
	 * @param request
	 * @return
	 * @throws SQLException
	 */
	@ResponseBody
	@RequestMapping(value = "browse.page", method = { RequestMethod.GET })
	@JRadOperation(JRadOperation.BROWSE)
	public AbstractModelAndView browse(@ModelAttribute CustomerApplicationProcessFilter filter, HttpServletRequest request) throws SQLException {
		filter.setRequest(request);
		IUser user = Beans.get(LoginManager.class).getLoggedInUser(request);
		String loginId = user.getId();
		filter.setLoginId(loginId);
		filter.setIsReceive("NO");
		filter.setNextNodeName("终审");
		QueryResult<CustomerApplicationIntopieceWaitForm> result = customerApplicationIntopieceWaitService.findCustomerApplicationIntopieceWaitForm(filter);
		JRadPagedQueryResult<CustomerApplicationIntopieceWaitForm> pagedResult = new JRadPagedQueryResult<CustomerApplicationIntopieceWaitForm>(filter, result);
		JRadModelAndView mv = new JRadModelAndView("/intopieces/intopieces_wait/intopiecesApprove_end_browse", request);
		mv.addObject(PAGED_RESULT, pagedResult);

		return mv;
	}

	/**
	 * 
	 * 进件终审页面
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "change.page")
	public AbstractModelAndView change(HttpServletRequest request) {
		JRadModelAndView mv = new JRadModelAndView("/intopieces/intopieces_wait/intopiecesApprove_end_approve", request);

		String waitId = RequestHelper.getStringValue(request, ID);
		if (StringUtils.isNotEmpty(waitId)) {
			String[] ss = waitId.split("@");
			String customerId = ss[1];
			String serialNumber = ss[0];
			String applicationId = ss[2];
			String applyQuota = "";
			if(ss.length > 3){
				applyQuota = ss[3];
			}
			//获取配偶信息
			XM_APPLN_POZL pozl = xm_APPLN_Service.findXM_APPLN_POZLByCustomerId(customerId);
			CustomerInfor ci = customerInforService.findCustomerInforById(customerId);
			CustomerApplicationIntopieceWaitForm customerApplicationProcess = customerApplicationIntopieceWaitService.findCustomerApplicationProcessBySerialNumber(serialNumber);
			
			String currentNodeId = customerApplicationProcess.getNextNodeId();
			NodeAuditForm nodeAudit = nodeAuditService.findNodeAuditById(currentNodeId);
			mv.addObject("nodeAuditOperationType", nodeAudit.getType());
			List<NodeControl> nodeControls = nodeAuditService.findNodeControlByCurrentNodeId(currentNodeId);
			//有异议的节点
			if(nodeControls.size() > 1){
				mv.addObject("objection","true");
			} else {
				mv.addObject("objection","false");
			}
			
			List<SystemConfiguration> systemConfigurations = systemConfigurationService.findSystemConfigurationByCode("approveObjectionAmount");
			if(systemConfigurations != null && systemConfigurations.size() > 0){
				SystemConfiguration configuration = systemConfigurations.get(0);
				mv.addObject("objectionAmount",configuration.getSysValues());
			}
			
			String userId = ci.getUserId();
			User user = userService.getUserById(userId);
			mv.addObject("applyQuota", applyQuota);
			mv.addObject("applicationId", applicationId);
			mv.addObject("customerId", customerId);
			mv.addObject("serialNumber", serialNumber);
			mv.addObject("user", user);
			mv.addObject("ci", ci);
			mv.addObject("xM_APPLN_POZL", pozl);
			mv.addObject("customerApplicationProcess", customerApplicationProcess);
		}else{
			String serialNumberJump = request.getParameter("serialNumber");
			String customerIdJump =request.getParameter("customerId");
			String applicationIdJump =request.getParameter("applicationId");
			String applyQuotaJump =request.getParameter("applyQuota");
			CustomerInfor ci = customerInforService.findCustomerInforById(customerIdJump);
			CustomerApplicationIntopieceWaitForm customerApplicationProcess = customerApplicationIntopieceWaitService.findCustomerApplicationProcessBySerialNumber(serialNumberJump);
			
			String currentNodeId = customerApplicationProcess.getNextNodeId();
			NodeAuditForm nodeAudit = nodeAuditService.findNodeAuditById(currentNodeId);
			mv.addObject("nodeAuditOperationType", nodeAudit.getType());
			List<NodeControl> nodeControls = nodeAuditService.findNodeControlByCurrentNodeId(currentNodeId);
			//有异议的节点
			if(nodeControls.size() > 1){
				mv.addObject("objection","true");
			} else {
				mv.addObject("objection","false");
			}
			List<SystemConfiguration> systemConfigurations = systemConfigurationService.findSystemConfigurationByCode("approveObjectionAmount");
			if(systemConfigurations != null && systemConfigurations.size() > 0){
				SystemConfiguration configuration = systemConfigurations.get(0);
				mv.addObject("objectionAmount",configuration.getSysValues());
			}
			
			String userId = ci.getUserId();
			User user = userService.getUserById(userId);
			mv.addObject("applyQuota", applyQuotaJump);
			mv.addObject("applicationId", applicationIdJump);
			mv.addObject("customerId", customerIdJump);
			mv.addObject("serialNumber", serialNumberJump);
			mv.addObject("user", user);
			mv.addObject("ci", ci);
			mv.addObject("customerApplicationProcess", customerApplicationProcess);
		}

		return mv;
	}

	/**
	 * 执行提交
	 * 
	 * @param customerApplicationProcess
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "update.json", method = { RequestMethod.GET })
	public JRadReturnMap update(HttpServletRequest request) {
		JRadReturnMap returnMap = new JRadReturnMap();

		if (returnMap.isSuccess()) {
			try {
				request.setAttribute("appType", Constant.APP_STATE_END);
				customerApplicationIntopieceWaitService.updateCustomerApplicationProcessBySerialNumberApplicationInfo(request);
				returnMap.addGlobalMessage(CHANGE_SUCCESS);
			} catch (Exception e) {
				return WebRequestHelper.processException(e);
			}
		}

		return returnMap;
	}

}
