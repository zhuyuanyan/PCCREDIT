package com.cardpay.pccredit.customer.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cardpay.pccredit.customer.constant.CustomerInforConstant;
import com.cardpay.pccredit.customer.service.CustomerInforService;
import com.cardpay.pccredit.datapri.constant.DataPriConstants;
import com.cardpay.pccredit.xm_appln.service.XM_APPLN_Service;
import com.cardpay.pccredit.xm_appln.web.XM_APPLN_JBZL_FORM;
import com.wicresoft.jrad.base.auth.JRadModule;
import com.wicresoft.jrad.base.auth.JRadOperation;
import com.wicresoft.jrad.base.constant.JRadConstants;
import com.wicresoft.jrad.base.web.JRadModelAndView;
import com.wicresoft.jrad.base.web.controller.BaseController;
import com.wicresoft.jrad.base.web.result.JRadReturnMap;
import com.wicresoft.jrad.base.web.security.LoginManager;
import com.wicresoft.jrad.base.web.utility.WebRequestHelper;
import com.wicresoft.jrad.modules.privilege.model.User;
import com.wicresoft.util.spring.Beans;
import com.wicresoft.util.spring.mvc.mv.AbstractModelAndView;

@Controller
@RequestMapping("/customer/basiccustomerinforCreate/*")
@JRadModule("customer.basiccustomerinforCreate")
public class CustomerInforInsertController extends BaseController{
	
	@Autowired
	private CustomerInforService customerInforService;
	
	@Autowired
	private XM_APPLN_Service xM_APPLN_Service;
	
	/**
	 * 跳转到增加客户信息页面
	 * 此方法改为xm_appln版本
	 * @param request
	 * @return
	*/
	/*@ResponseBody
	@RequestMapping(value = "insert.page")
	@JRadOperation(JRadOperation.BROWSE)
	public AbstractModelAndView create(HttpServletRequest request) {        
		JRadModelAndView mv = new JRadModelAndView("/customer/customerInforInsert/customerinfor_create", request);
		return mv;
	}*/
	
	@ResponseBody
	@RequestMapping(value = "insert.page")
	@JRadOperation(JRadOperation.BROWSE)
	public AbstractModelAndView create_xm_appln_jbzl(HttpServletRequest request) {        
		JRadModelAndView mv = new JRadModelAndView("/xm_appln/xm_appln_page1", request);
		return mv;
	}
	
	/**
	 * 执行添加客户信息
	 * 此方法改为xm_appln版本
	 * @param customerinfoForm
	 * @param request
	 * @return
	 */

	/*@ResponseBody
	@RequestMapping(value = "insert.json")
	@JRadOperation(JRadOperation.CREATE)
	public JRadReturnMap insert(@ModelAttribute CustomerInforForm customerinfoForm, HttpServletRequest request) {
		JRadReturnMap returnMap = new JRadReturnMap();
		if (returnMap.isSuccess()) {
			try {
				CustomerInfor customerinfor = customerinfoForm.createModel(CustomerInfor.class);
				User user = (User) Beans.get(LoginManager.class).getLoggedInUser(request);
				customerinfor.setCreatedBy(user.getId());
				customerinfor.setUserId(user.getId());
				String id = customerInforService.insertCustomerInfor(customerinfor);
				returnMap.put(RECORD_ID, id);
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
	}*/
	
	@ResponseBody
	@RequestMapping(value = "insert.json")
	@JRadOperation(JRadOperation.CREATE)
	public JRadReturnMap insert_xm_appln_jbzl(@ModelAttribute XM_APPLN_JBZL_FORM xM_APPLN_JBZL_FORM, HttpServletRequest request) {
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
}
