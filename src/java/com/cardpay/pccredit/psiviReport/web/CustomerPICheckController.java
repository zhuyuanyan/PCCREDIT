package com.cardpay.pccredit.psiviReport.web;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.cardpay.pccredit.common.IdCardValidate;
import com.cardpay.pccredit.customer.filter.CustomerInforFilter;
import com.cardpay.pccredit.customer.model.CustomerInfor;
import com.cardpay.pccredit.customer.model.XmZxLogin;
import com.cardpay.pccredit.customer.service.CustomerInforService;
import com.cardpay.pccredit.customer.service.CustomerMainManagerService;
import com.cardpay.pccredit.datapri.constant.DataPriConstants;
import com.cardpay.pccredit.product.service.ProductService;
import com.cardpay.pccredit.psiviReport.service.PsviCheckService;
import com.cardpay.pccredit.psiviReport.util.PsviReport;
import com.cardpay.pccredit.xm_appln.web.XM_APPLN_NEW_CUSTOMER_FORM;
import com.wicresoft.jrad.base.auth.IUser;
import com.wicresoft.jrad.base.auth.JRadModule;
import com.wicresoft.jrad.base.auth.JRadOperation;
import com.wicresoft.jrad.base.constant.JRadConstants;
import com.wicresoft.jrad.base.web.JRadModelAndView;
import com.wicresoft.jrad.base.web.controller.BaseController;
import com.wicresoft.jrad.base.web.result.JRadReturnMap;
import com.wicresoft.jrad.base.web.security.LoginManager;
import com.wicresoft.jrad.base.web.utility.WebRequestHelper;
import com.wicresoft.jrad.modules.privilege.service.UserService;
import com.wicresoft.util.spring.Beans;
import com.wicresoft.util.spring.mvc.mv.AbstractModelAndView;

/**
 * CustomerPICheckController类的描述
 * 
 * @author 池能和
 * @created on 2015-06-15
 * 
 * @version $Id:$
 */
@Controller
@RequestMapping("/customer/customerIdCheck/*")
@JRadModule("customer.customerIdCheck")
public class CustomerPICheckController extends BaseController{
	
	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private ProductService productService;

	@Autowired
	private UserService userService;

	@Autowired
	private CustomerInforService customerInforService;

	@Autowired
	private CustomerMainManagerService customerMainManagerService;
	
	@Autowired
	private PsviCheckService psviCheckService;
	
	/**
	 * 设置公安身份核查查询条件
	 */
	@ResponseBody
	@RequestMapping(value = "conditionIdCheck.page", method = { RequestMethod.GET })
	@JRadOperation(JRadOperation.IDCHECK)
	public AbstractModelAndView conditionRhzx(HttpServletRequest request) {
		String customerId = request.getParameter("customerId");
		CustomerInfor customerInfor = customerInforService.findCustomerInforById(customerId);
		JRadModelAndView mv = new JRadModelAndView("/customer/customeridcheck/customeridcheck_condition",request);
		mv.addObject("customerInfor", customerInfor);
		return mv;
	}
	
	/**
	 * 查询公安身份核查
	 * 
	 * @param filter
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "checkPivi.json", method = { RequestMethod.POST })
	@JRadOperation(JRadOperation.IDCHECK)
	public JRadReturnMap xm_appln_page0_apply(@ModelAttribute XM_APPLN_NEW_CUSTOMER_FORM xM_APPLN_NEW_CUSTOMER_FORM, HttpServletRequest request) {
		JRadReturnMap returnMap = new JRadReturnMap();
		try{
			request.setCharacterEncoding("utf8");
			String customerId = request.getParameter("customer_id");
			String chineseName = request.getParameter("surname");
			String identityType = request.getParameter("race_code");
			String identityNo = request.getParameter("card_id");
			String IdCheckQueryType = "01";//request.getParameter("IdCheckQueryType");//业务类型
			String IdCheckQueryTrait = "01";//request.getParameter("IdCheckQueryTrait");//业务特征
			String msgNo ="0001";
			String transCode = "0001";
			
			//身份证号码验证
			String msg = IdCardValidate.IDCardValidate(identityNo);
			if(msg !="" && msg != null){
				returnMap.put("checkresult", msg);
				return returnMap;
			}
			//同一类型证件号码不得重复
			CustomerInforFilter customerInforFilter = new CustomerInforFilter();
			customerInforFilter.setCardId(identityNo);
			customerInforFilter.setCardType(identityType);
			int i = customerInforService.findCustomerOriginaCountList(customerInforFilter);
			if(i!=0){
				returnMap.put("checkresult", "证件号码已存在!");
				return returnMap;
			}
			
			//判断用户证件类型是否是身份证，不是则不用核查
			if(!identityType.equals("01")){
				returnMap.addGlobalMessage("证件类型非身份证类型，不需要核查!");
				returnMap.put(RECORD_ID, identityNo);
				returnMap.put("checkresult", "证件类型非身份证类型，不需要核查!");
				return returnMap;
			}
			CustomerInfor customer = customerInforService.findCustomerInforById(customerId);
			IUser user = Beans.get(LoginManager.class).getLoggedInUser(request);
			
			//获取查询账户和密码
			List<XmZxLogin> list = customerInforService.getLoginByOrg("2");
			//生成list之内的一个随机数
			int k = (int)(Math.random()*(list.size()));
			System.out.println(k);
			String userid = list.get(k).getUserName();
			String passwd = list.get(k).getPassWord();
			
			logger.info("人行身份信息查询---客户:"+chineseName);
			PsviReport psviReport = new PsviReport();
			Map resultMap = psviReport.manuProcessPsviInfo(chineseName, identityNo, IdCheckQueryType, IdCheckQueryTrait, userid, passwd,transCode,msgNo);
			if(!resultMap.isEmpty()){
				//保存客户身份核查结果
				psviCheckService.insertOrUpdateIC(resultMap);
				String checkResult = (String)resultMap.get("CheckResult1");
				returnMap.addGlobalMessage(checkResult);
				returnMap.put(RECORD_ID, resultMap.get("ID1"));
				returnMap.put("checkresult", checkResult);
				return returnMap;
			}
			returnMap.addGlobalMessage("系统出现异常，请手动登录公安系统进行核查确认!");
			returnMap.put(RECORD_ID, resultMap.get("ID1"));
			returnMap.put("checkresult", "系统出现异常，请手动登录公安系统进行核查确认!");
		} catch (Exception e) {
			returnMap.put(JRadConstants.MESSAGE, DataPriConstants.SYS_EXCEPTION_MSG);
			returnMap.put(JRadConstants.SUCCESS, false);
			return WebRequestHelper.processException(e);
		}
		
		return returnMap;
	}
}
