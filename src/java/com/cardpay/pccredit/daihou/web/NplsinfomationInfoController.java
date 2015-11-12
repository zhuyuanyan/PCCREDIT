package com.cardpay.pccredit.daihou.web;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cardpay.pccredit.common.Arith;
import com.cardpay.pccredit.customer.constant.CustomerInforConstant;
import com.cardpay.pccredit.customer.constant.MarketingCreateWayEnum;
import com.cardpay.pccredit.customer.filter.CustomerInforFilter;
import com.cardpay.pccredit.customer.model.CustomerOverdue;
import com.cardpay.pccredit.customer.service.CustomerAccountInfoService;
import com.cardpay.pccredit.daihou.model.ActiveCard;
import com.cardpay.pccredit.daihou.service.ActiveCardService;
import com.cardpay.pccredit.datapri.constant.DataPriConstants;
import com.cardpay.pccredit.intopieces.constant.Constant;
import com.cardpay.pccredit.riskControl.constant.RiskCustomerCollectionConstant;
import com.cardpay.pccredit.riskControl.constant.RiskCustomerCollectionEndResultEnum;
import com.cardpay.pccredit.riskControl.filter.AgrCrdXykCunegFilter;
import com.cardpay.pccredit.riskControl.filter.NplsInfomationFilter;
import com.cardpay.pccredit.riskControl.model.AgrCrdXykCuneg;
import com.cardpay.pccredit.riskControl.model.RiskCustomerCollectionPlan;
import com.cardpay.pccredit.riskControl.model.RiskCustomerCollectionPlansAction;
import com.cardpay.pccredit.riskControl.service.AgrCrdXykCunegService;
import com.cardpay.pccredit.riskControl.service.NplsInfomationService;
import com.cardpay.pccredit.riskControl.service.RiskCustomerCollectionService;
import com.cardpay.pccredit.riskControl.web.NplsInfomationForm;
import com.cardpay.pccredit.riskControl.web.RiskCustomerCollectionPlanForm;
import com.cardpay.pccredit.riskControl.web.RiskCustomerCollectionPlansActionForm;
import com.wicresoft.jrad.base.auth.IUser;
import com.wicresoft.jrad.base.auth.JRadModule;
import com.wicresoft.jrad.base.auth.JRadOperation;
import com.wicresoft.jrad.base.constant.JRadConstants;
import com.wicresoft.jrad.base.database.model.QueryResult;
import com.wicresoft.jrad.base.i18n.I18nHelper;
import com.wicresoft.jrad.base.web.JRadModelAndView;
import com.wicresoft.jrad.base.web.controller.BaseController;
import com.wicresoft.jrad.base.web.result.JRadPagedQueryResult;
import com.wicresoft.jrad.base.web.result.JRadReturnMap;
import com.wicresoft.jrad.base.web.security.LoginManager;
import com.wicresoft.jrad.base.web.utility.WebRequestHelper;
import com.wicresoft.jrad.modules.privilege.model.User;
import com.wicresoft.util.date.DateHelper;
import com.wicresoft.util.spring.Beans;
import com.wicresoft.util.spring.mvc.mv.AbstractModelAndView;
import com.wicresoft.util.web.RequestHelper;

/**
 * 激活卡资料
 * @author 谭文华
 *
 * 2014-12-22下午4:22:04
 */
@Controller
@RequestMapping("/daihou/nplsinfomationinfo/*")
@JRadModule("daihou.nplsinfomationinfo")
public class NplsinfomationInfoController extends BaseController {
	
	@Autowired
	private ActiveCardService activeCardService;
	
	@Autowired
	private NplsInfomationService nplsInfomationService;
	
	@Autowired
	private CustomerAccountInfoService customerAccountInfoService;
	
	@Autowired
	private RiskCustomerCollectionService riskCustomerCollectionService;
	
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
	public AbstractModelAndView browse(@ModelAttribute NplsInfomationFilter filter,HttpServletRequest request) {
		//设置当前查询用户
		IUser user = Beans.get(LoginManager.class).getLoggedInUser(request);
        filter.setUserId(user.getId());
        filter.setUserType(user.getUserType());//如果是客户经理，则查看自己的客户
        
        filter.setRequest(request);
		QueryResult<NplsInfomationForm> result = nplsInfomationService.findNplsInfomationByFilter(filter);
		JRadPagedQueryResult<NplsInfomationForm> pagedResult = new JRadPagedQueryResult<NplsInfomationForm>(filter, result);
		JRadModelAndView mv = new JRadModelAndView("/riskcontrol/nplsinfomation/nplsinfomation_browse",request);
		mv.addObject(PAGED_RESULT, pagedResult);
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value = "create_collection.json", method = { RequestMethod.GET })
	//@JRadOperation(JRadOperation.CREATE)
	public JRadReturnMap create_collection(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		JRadReturnMap returnMap = new JRadReturnMap();
		if (returnMap.isSuccess()) {
			try {
				String customer_id = request.getParameter("customer_id");
				IUser user = Beans.get(LoginManager.class).getLoggedInUser(request);
				String collectionPlanId = "";
				//查找催收计划 无则添加一条
				List<RiskCustomerCollectionPlanForm> collectionPlan_ls = riskCustomerCollectionService.findRiskCollectionPlansByCustomerId(customer_id);
				if(collectionPlan_ls == null || collectionPlan_ls.size() == 0){
					RiskCustomerCollectionPlan riskCustomerCollectionPlan = new RiskCustomerCollectionPlan();
					riskCustomerCollectionPlan.setCustomerId(customer_id);
					riskCustomerCollectionPlan.setCreatedBy(user.getId());
					collectionPlanId = riskCustomerCollectionService.insertRiskCustomerCollectionPlan(riskCustomerCollectionPlan);
				}
				else{
					collectionPlanId = collectionPlan_ls.get(0).getId();
				}
				returnMap.put("collectionPlanId",collectionPlanId);
				returnMap.addGlobalMessage(CHANGE_SUCCESS);
			}catch (Exception e) {
				return WebRequestHelper.processException(e);
			}
		}

		return returnMap;
	}
	
	@ResponseBody
	@RequestMapping(value = "create.page")
	@JRadOperation(JRadOperation.CREATE)
	public AbstractModelAndView create(HttpServletRequest request) {
		String customerId = RequestHelper.getStringValue(request, ID);
		String type = RequestHelper.getStringValue(request, "type");
		JRadModelAndView mv = new JRadModelAndView("/riskcontrol/riskCustomerCollection/collection_plan_action_create", request);
		mv.addObject("customerId",customerId);
		mv.addObject("type",type);
		return mv;
	}
	
	/**
	 * 添加催收计划
    */
	@ResponseBody
	@RequestMapping(value = "insert.json")
	@JRadOperation(JRadOperation.CREATE)
	public JRadReturnMap insert(@ModelAttribute RiskCustomerCollectionPlansActionForm form, HttpServletRequest request) {
		JRadReturnMap returnMap = new JRadReturnMap();
		if (returnMap.isSuccess()) {
			try {
					String customer_id = request.getParameter("id");
					String type = request.getParameter("type");
					IUser user = Beans.get(LoginManager.class).getLoggedInUser(request);
					String createdBy = user.getId();
					//实施action
					RiskCustomerCollectionPlansAction riskCustomerCollectionPlansAction = new RiskCustomerCollectionPlansAction();
					String collectionStartTime = form.getCollectionStartTime();
					String collectionEndTime = form.getCollectionEndTime();
					if(collectionStartTime!=null && !collectionStartTime.equals("")){
						Date start = DateHelper.getDateFormat(collectionStartTime, "yyyy-MM-dd HH:mm:ss");
						riskCustomerCollectionPlansAction.setCollectionStartTime(start);
					}
					if(collectionEndTime!=null && !collectionEndTime.equals("")){
					    Date end = DateHelper.getDateFormat(collectionEndTime, "yyyy-MM-dd HH:mm:ss");
					    riskCustomerCollectionPlansAction.setCollectionEndTime(end);
					}
					riskCustomerCollectionPlansAction.setCreatedBy(createdBy);
					riskCustomerCollectionPlansAction.setCreatedTime(new Date());
					
					riskCustomerCollectionPlansAction.setCollection(form.getCollection());
					riskCustomerCollectionPlansAction.setCollectionResult(form.getCollectionResult());
					riskCustomerCollectionPlansAction.setWhetherImplement(form.getWhetherImplement());
					riskCustomerCollectionPlansAction.setCustomerId(customer_id);
					riskCustomerCollectionPlansAction.setCustomerManagerId(createdBy);
					String id = riskCustomerCollectionService.insertRiskCustomerCollectionPlansAction(riskCustomerCollectionPlansAction);
					returnMap.put(JRadConstants.MESSAGE, "");
					returnMap.addGlobalMessage(CREATE_SUCCESS);
					returnMap.put("type",type);
				
			}catch (Exception e) {
				returnMap.put(JRadConstants.MESSAGE, DataPriConstants.SYS_EXCEPTION_MSG);
				returnMap.put(JRadConstants.SUCCESS, false);
				return WebRequestHelper.processException(e);
			}
		}
		return returnMap;
	}
}
