/**
 * 
 */
package com.cardpay.pccredit.customer.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cardpay.pccredit.customer.filter.AmountAdjustmentFilter;
import com.cardpay.pccredit.customer.filter.TemporaryQuotaFilter;
import com.cardpay.pccredit.customer.service.AmountAdjustmentService;
import com.cardpay.pccredit.customer.service.CustomerTemporaryQuotaService;
import com.cardpay.workflow.service.ProcessService;
import com.wicresoft.jrad.base.auth.JRadModule;
import com.wicresoft.jrad.base.auth.JRadOperation;
import com.wicresoft.jrad.base.database.model.QueryResult;
import com.wicresoft.jrad.base.web.JRadModelAndView;
import com.wicresoft.jrad.base.web.controller.BaseController;
import com.wicresoft.jrad.base.web.result.JRadPagedQueryResult;
import com.wicresoft.jrad.base.web.result.JRadReturnMap;
import com.wicresoft.jrad.base.web.security.LoginManager;
import com.wicresoft.jrad.base.web.utility.WebRequestHelper;
import com.wicresoft.jrad.modules.privilege.model.User;
import com.wicresoft.util.spring.Beans;
import com.wicresoft.util.spring.mvc.mv.AbstractModelAndView;
import com.wicresoft.util.web.RequestHelper;

/**
 * 
 * 描述 ：普卡临时额度评估
 * @author 张石树
 *
 * 2014-12-3 下午1:32:13
 */
@Controller
@RequestMapping("/customer/temporaryquota/*")
@JRadModule("customer.temporaryquota")
public class CustomerTemporaryQuotaController extends BaseController{
	
	@Autowired
	private AmountAdjustmentService amountAdjustmentService;
	
	@Autowired
	private ProcessService processService;
	
	@Autowired
	private CustomerTemporaryQuotaService customerTemporaryQuotaService;

	/**
	 * 浏览普卡临时额度页面
	 * 
	 * @param filter
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "browse.page", method = { RequestMethod.GET })
	@JRadOperation(JRadOperation.BROWSE)
	public AbstractModelAndView browse(@ModelAttribute TemporaryQuotaFilter filter, HttpServletRequest request) {
		filter.setRequest(request);
		User user = (User) Beans.get(LoginManager.class).getLoggedInUser(request);
		if(filter.getProductId()==null ||filter.getProductId().equals("-1")){
			filter.setProductId("402881e44b6be4e6014b6be55fd40001");
		}
		List<CustomerTemporaryquotaForm> result = customerTemporaryQuotaService.queryTemporaryquota(filter);
		JRadModelAndView mv = new JRadModelAndView("/customer/evaluationquota/customer_temporaryquota_browser", request);
		mv.addObject(PAGED_RESULT, result);
		mv.addObject("filter",filter);
		return mv;
	}
	
}
