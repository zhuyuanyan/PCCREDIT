package com.cardpay.pccredit.customer.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cardpay.pbccrcReport.PbccrcReport;
import com.cardpay.pccredit.customer.filter.CustomerInforFilter;
import com.cardpay.pccredit.customer.model.CustomerInfor;
import com.cardpay.pccredit.customer.model.CustomerMainManager;
import com.cardpay.pccredit.customer.service.CustomerInforService;
import com.cardpay.pccredit.customer.service.CustomerMainManagerService;
import com.cardpay.pccredit.product.service.ProductService;
import com.wicresoft.jrad.base.auth.IUser;
import com.wicresoft.jrad.base.auth.JRadModule;
import com.wicresoft.jrad.base.auth.JRadOperation;
import com.wicresoft.jrad.base.database.model.QueryResult;
import com.wicresoft.jrad.base.web.JRadModelAndView;
import com.wicresoft.jrad.base.web.controller.BaseController;
import com.wicresoft.jrad.base.web.result.JRadPagedQueryResult;
import com.wicresoft.jrad.base.web.result.JRadReturnMap;
import com.wicresoft.jrad.base.web.security.LoginManager;
import com.wicresoft.jrad.base.web.utility.WebRequestHelper;
import com.wicresoft.jrad.modules.privilege.service.UserService;
import com.wicresoft.util.spring.Beans;
import com.wicresoft.util.spring.mvc.mv.AbstractModelAndView;

/**
 * CustomerZXController类的描述
 * 
 * @author 谭文华
 * @created on 2015-01-13
 * 
 * @version $Id:$
 */
@Controller
@RequestMapping("/customer/customerzx/*")
@JRadModule("customer.customerzx")
public class CustomerZXController extends BaseController {

	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private ProductService productService;

	@Autowired
	private UserService userService;

	@Autowired
	private CustomerInforService customerInforService;

	@Autowired
	private CustomerMainManagerService customerMainManagerService;

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
		QueryResult<CustomerInfor> result = customerInforService.findCustomerInforByFilter(filter);
		JRadPagedQueryResult<CustomerInfor> pagedResult = new JRadPagedQueryResult<CustomerInfor>(filter, result);
		JRadModelAndView mv = new JRadModelAndView("/customer/customerzx/customerzx_browse",request);
		mv.addObject(PAGED_RESULT, pagedResult);

		return mv;
	}
	/**
	 * 设置征信查询条件
	 */
	@ResponseBody
	@RequestMapping(value = "conditionRhzx.page", method = { RequestMethod.GET })
	@JRadOperation(JRadOperation.RHZX)
	public AbstractModelAndView conditionRhzx(HttpServletRequest request) {
		String customerId = request.getParameter("customerId");
		CustomerInfor customerInfor = customerInforService.findCustomerInforById(customerId);
		JRadModelAndView mv = new JRadModelAndView("/customer/customerzx/customerzx_condition",request);
		mv.addObject("customerInfor", customerInfor);
		return mv;
	}
	
	/**
	 * 查询人行征信
	 * 
	 * @param filter
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "checkRhzx.page", method = { RequestMethod.POST })
	@JRadOperation(JRadOperation.RHZX)
	public AbstractModelAndView checkRhzx(HttpServletRequest request) {
		try {
			String customerId = request.getParameter("customerId");
			String QueryReason = request.getParameter("QueryReason");
			String QueryType = request.getParameter("QueryType");
			String Vertype = request.getParameter("Vertype");
			request.setCharacterEncoding("utf8");
			CustomerInfor customer = customerInforService.findCustomerInforById(customerId);
			IUser user = Beans.get(LoginManager.class).getLoggedInUser(request);
			logger.info("人行征信查询---查询用户:"+user.getId()+",客户:"+customer.getChineseName());
			PbccrcReport pbccrcReport = new PbccrcReport();
			String fileFullPath = pbccrcReport.manuProcessPbocCreditInfo(customer.getChineseName(), customer.getCardType(), customer.getCardId(),
					QueryReason,QueryType,Vertype);
			if(fileFullPath != null){
				JRadModelAndView mv = new JRadModelAndView("/customer/customerzx/rhzx/"+customer+"_"+customer.getCardId(), request);
				mv.addObject("customer", customer);
				return mv;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JRadModelAndView mv = new JRadModelAndView("/customer/customerzx/rhzx/error", request);
		return mv;
	}
}
