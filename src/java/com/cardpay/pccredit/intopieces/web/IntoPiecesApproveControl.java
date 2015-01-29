package com.cardpay.pccredit.intopieces.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cardpay.pccredit.customer.constant.CustomerInforConstant;
import com.cardpay.pccredit.customer.constant.WfProcessInfoType;
import com.cardpay.pccredit.customer.dao.CustomerInforDao;
import com.cardpay.pccredit.customer.dao.comdao.CustomerInforCommDao;
import com.cardpay.pccredit.customer.filter.CustomerInforFilter;
import com.cardpay.pccredit.customer.model.CustomerCareersInformation;
import com.cardpay.pccredit.customer.model.CustomerInfor;
import com.cardpay.pccredit.customer.service.CustomerInforService;
import com.cardpay.pccredit.datapri.constant.DataPriConstants;
import com.cardpay.pccredit.datapri.service.DataAccessSqlService;
import com.cardpay.pccredit.intopieces.constant.ApplicationStatusEnum;
import com.cardpay.pccredit.intopieces.constant.CardStatus;
import com.cardpay.pccredit.intopieces.constant.Constant;
import com.cardpay.pccredit.intopieces.constant.IntoPiecesException;
import com.cardpay.pccredit.intopieces.filter.CustomerApplicationProcessFilter;
import com.cardpay.pccredit.intopieces.filter.IntoPiecesFilter;
import com.cardpay.pccredit.intopieces.filter.MakeCardFilter;
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
import com.cardpay.pccredit.intopieces.model.IntoPieces;
import com.cardpay.pccredit.intopieces.model.MakeCard;
import com.cardpay.pccredit.intopieces.service.CustomerApplicationIntopieceWaitService;
import com.cardpay.pccredit.intopieces.service.CustomerApplicationProcessService;
import com.cardpay.pccredit.intopieces.service.IntoPiecesService;
import com.cardpay.pccredit.product.filter.ProductFilter;
import com.cardpay.pccredit.product.model.AddressAccessories;
import com.cardpay.pccredit.product.model.AppendixDict;
import com.cardpay.pccredit.product.model.ProductAttribute;
import com.cardpay.pccredit.product.service.ProductService;
import com.cardpay.pccredit.system.constants.NodeAuditTypeEnum;
import com.cardpay.pccredit.system.constants.YesNoEnum;
import com.cardpay.pccredit.system.model.NodeAudit;
import com.cardpay.pccredit.system.model.NodeControl;
import com.cardpay.pccredit.system.service.NodeAuditService;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_ADDR;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_DBXX;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_DCSC;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_HKSZ;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_KPMX;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_QTXYKXX;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_SQED;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_TJINFO;
import com.cardpay.pccredit.xm_appln.service.XM_APPLN_Service;
import com.cardpay.pccredit.xm_appln.web.XM_APPLN_Controller;
import com.cardpay.workflow.models.WfProcessInfo;
import com.cardpay.workflow.models.WfStatusInfo;
import com.cardpay.workflow.models.WfStatusResult;
import com.cardpay.workflow.service.ProcessService;
import com.wicresoft.jrad.base.auth.IUser;
import com.wicresoft.jrad.base.auth.JRadModule;
import com.wicresoft.jrad.base.auth.JRadOperation;
import com.wicresoft.jrad.base.constant.JRadConstants;
import com.wicresoft.jrad.base.database.dao.common.CommonDao;
import com.wicresoft.jrad.base.database.id.IDGenerator;
import com.wicresoft.jrad.base.database.model.BusinessModel;
import com.wicresoft.jrad.base.database.model.QueryResult;
import com.wicresoft.jrad.base.i18n.I18nHelper;
import com.wicresoft.jrad.base.web.DataBindHelper;
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
		@RequestMapping(value = "xm_appln_page0_apply.page")
		public JRadReturnMap xm_appln_page0_apply(@ModelAttribute CustomerInforFilter customerInforFilter, HttpServletRequest request) {
			JRadReturnMap returnMap = new JRadReturnMap();
			if (returnMap.isSuccess()) {
				try {
					String customerId = request.getParameter("id");
					//设置流程开始
					saveOrUpdatexm_appln_page4(customerId);
					
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
		
		public void saveOrUpdatexm_appln_page4(String customer_id){
			//设置申请
			CustomerApplicationInfo customerApplicationInfo = new CustomerApplicationInfo();
			//customerApplicationInfo.setStatus(status);
			customerApplicationInfo.setId(IDGenerator.generateID());
			XM_APPLN_SQED xM_APPLN_SQED = xM_APPLN_Service.findXM_APPLN_SQEDByCustomerId(customer_id);
			if(xM_APPLN_SQED==null||xM_APPLN_SQED.getCrdlmt_req()==null||xM_APPLN_SQED.getCrdlmt_req().equals("")){
				customerApplicationInfo.setApplyQuota("0");//设置额度
			}
			customerApplicationInfo.setCustomerId(customer_id);
			customerApplicationInfo.setApplyQuota((Integer.valueOf(customerApplicationInfo.getApplyQuota())*100)+"");
			customerApplicationInfo.setStatus(Constant.APPROVE_INTOPICES);
			//查找默认产品
			ProductFilter filter = new ProductFilter();
			filter.setDefault_type(Constant.DEFAULT_TYPE);
			ProductAttribute productAttribute = productService.findProductsByFilter(filter).getItems().get(0);
			customerApplicationInfo.setProductId(productAttribute.getId());
					
			commonDao.insertObject(customerApplicationInfo);
			
			
			//添加申请件流程
			WfProcessInfo wf=new WfProcessInfo();
			wf.setProcessType(WfProcessInfoType.process_type);
			wf.setVersion("1");
			commonDao.insertObject(wf);
			List<NodeAudit> list=nodeAuditService.findByNodeTypeAndProductId(NodeAuditTypeEnum.Product.name(),productAttribute.getId());
			boolean startBool=false;
			boolean endBool=false;
			//节点id和WfStatusInfo id的映射
			Map<String, String> nodeWfStatusMap = new HashMap<String, String>();
			for(NodeAudit nodeAudit:list){
				if(nodeAudit.getIsstart().equals(YesNoEnum.YES.name())){
					startBool=true;
				}
				
				if(startBool&&!endBool){
					WfStatusInfo wfStatusInfo=new WfStatusInfo();
					wfStatusInfo.setIsStart(nodeAudit.getIsstart().equals(YesNoEnum.YES.name())?"1":"0");
					wfStatusInfo.setIsClosed(nodeAudit.getIsend().equals(YesNoEnum.YES.name())?"1":"0");
					wfStatusInfo.setRelationedProcess(wf.getId());
					wfStatusInfo.setStatusName(nodeAudit.getNodeName());
					wfStatusInfo.setStatusCode(nodeAudit.getId());
					commonDao.insertObject(wfStatusInfo);
					
					nodeWfStatusMap.put(nodeAudit.getId(), wfStatusInfo.getId());
					
					if(nodeAudit.getIsstart().equals(YesNoEnum.YES.name())){
						//添加初始审核
						CustomerApplicationProcess customerApplicationProcess=new CustomerApplicationProcess();
						String serialNumber = processService.start(wf.getId());
						customerApplicationProcess.setSerialNumber(serialNumber);
						customerApplicationProcess.setNextNodeId(nodeAudit.getId()); 
						customerApplicationProcess.setApplicationId(customerApplicationInfo.getId());
						commonDao.insertObject(customerApplicationProcess);
						
						CustomerApplicationInfo applicationInfo = commonDao.findObjectById(CustomerApplicationInfo.class, customerApplicationInfo.getId());
						applicationInfo.setSerialNumber(serialNumber);
						commonDao.updateObject(applicationInfo);
					}
				}
				
				if(nodeAudit.getIsend().equals(YesNoEnum.YES.name())){
					endBool=true;
				}
			}
			//节点关系
			List<NodeControl> nodeControls = nodeAuditService.findNodeControlByNodeTypeAndProductId(NodeAuditTypeEnum.Product.name(), productAttribute.getId());
			for(NodeControl control : nodeControls){
				WfStatusResult wfStatusResult = new WfStatusResult();
				wfStatusResult.setCurrentStatus(nodeWfStatusMap.get(control.getCurrentNode()));
				wfStatusResult.setNextStatus(nodeWfStatusMap.get(control.getNextNode()));
				wfStatusResult.setExamineResult(control.getCurrentStatus());
				commonDao.insertObject(wfStatusResult);
			}
		}
}
