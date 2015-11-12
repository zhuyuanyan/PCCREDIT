package com.cardpay.pccredit.intopieces.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cardpay.pccredit.common.UploadFileTool;
import com.cardpay.pccredit.customer.constant.WfProcessInfoType;
import com.cardpay.pccredit.intopieces.constant.ApplicationStatusEnum;
import com.cardpay.pccredit.intopieces.constant.Constant;
import com.cardpay.pccredit.intopieces.dao.CustomerApplicationIntopieceWaitDao;
import com.cardpay.pccredit.intopieces.dao.XmNewSqDao;
import com.cardpay.pccredit.intopieces.dao.XmNewSqUploadDao;
import com.cardpay.pccredit.intopieces.filter.AddIntoPiecesFilter;
import com.cardpay.pccredit.intopieces.filter.XmNewFlowFilter;
import com.cardpay.pccredit.intopieces.filter.XmNewSqFilter;
import com.cardpay.pccredit.intopieces.model.CustomerApplicationInfo;
import com.cardpay.pccredit.intopieces.model.CustomerApplicationProcess;
import com.cardpay.pccredit.intopieces.model.LocalImage;
import com.cardpay.pccredit.intopieces.model.XmNewApplicationInfo;
import com.cardpay.pccredit.intopieces.model.XmNewFlow;
import com.cardpay.pccredit.intopieces.model.XmNewSq;
import com.cardpay.pccredit.intopieces.model.XmNewSqForm;
import com.cardpay.pccredit.intopieces.model.XmNewSqLog;
import com.cardpay.pccredit.intopieces.web.LocalImageForm;
import com.cardpay.pccredit.intopieces.web.XmApplnSxjcForm;
import com.cardpay.pccredit.intopieces.web.XmNewSqUploadForm;
import com.cardpay.pccredit.product.filter.ProductFilter;
import com.cardpay.pccredit.product.model.ProductAttribute;
import com.cardpay.pccredit.product.model.ProductCollectionRules;
import com.cardpay.pccredit.system.constants.NodeAuditTypeEnum;
import com.cardpay.pccredit.system.constants.YesNoEnum;
import com.cardpay.pccredit.system.model.NodeAudit;
import com.cardpay.pccredit.system.model.NodeControl;
import com.cardpay.pccredit.system.model.SystemCharge;
import com.cardpay.pccredit.system.service.NodeAuditService;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_SQED;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_SXJC;
import com.cardpay.workflow.constant.ApproveOperationTypeEnum;
import com.cardpay.workflow.models.WfProcessInfo;
import com.cardpay.workflow.models.WfStatusInfo;
import com.cardpay.workflow.models.WfStatusResult;
import com.cardpay.workflow.service.ProcessService;
import com.wicresoft.jrad.base.auth.IUser;
import com.wicresoft.jrad.base.database.dao.common.CommonDao;
import com.wicresoft.jrad.base.database.id.IDGenerator;
import com.wicresoft.jrad.base.database.model.QueryResult;
import com.wicresoft.jrad.base.web.security.LoginManager;
import com.wicresoft.jrad.modules.privilege.model.Department;
import com.wicresoft.jrad.modules.privilege.model.Organization;
import com.wicresoft.jrad.modules.privilege.model.User;
import com.wicresoft.util.spring.Beans;

@Service
public class XmNewSqService {

	@Autowired
	private CommonDao commonDao;
	@Autowired
	private XmNewSqDao xmNewSqDao;
	@Autowired
	private XmNewSqUploadDao xmNewSqUploadDao;
	@Autowired
	private NodeAuditService nodeAuditService;
	@Autowired
	private ProcessService processService;
	@Autowired
	private CustomerApplicationIntopieceWaitService customerApplicationIntopieceWaitService;
	@Autowired
	private CustomerApplicationIntopieceWaitDao customerApplicationIntopieceWaitDao;
	/*
	 * 新增商圈
	 */
	public void insert(XmNewSq sq){
		//获取机构id和name
		String userId = sq.getUserId();
		String sql = "select * from sys_department where id in (select dept_id from sys_dept_user where user_id='"+userId+"')";
		List<Department> DepartmentList = commonDao.queryBySql(Department.class, sql, null);
		if(DepartmentList.size()>0){
			sq.setOrgId(DepartmentList.get(0).getOrgId());
			Organization organization = commonDao.findObjectById(Organization.class, DepartmentList.get(0).getOrgId());
			sq.setOrgName(organization.getName());
		}
		//获取产品name
		ProductAttribute product = commonDao.findObjectById(ProductAttribute.class,sq.getProductId());
		sq.setProductName(product.getProductName());
		sq.setStatus(Constant.SQ_APPROVE_TYPE_1);
		sq.setId(IDGenerator.generateID());
		commonDao.insertObject(sq);
	}
	/*
	 * 申请
	 */
	public Boolean apply(String id){
		//提交申请
		Boolean applyResult = saveApply(id);
		return applyResult;
	}
	
	public void update(String id ,XmNewSqForm sq){
		XmNewSq xmNewSq = commonDao.findObjectById(XmNewSq.class, id);
		xmNewSq.setSqName(sq.getSqName());
		xmNewSq.setRemark(sq.getRemark());
		commonDao.updateObject(xmNewSq);
	}
	/*
	 * 通过审批(普通审批)
	 */
	public Boolean pass(String id ,XmNewSqForm sq,String nodeName,HttpServletRequest request,String remark) throws Exception{
		User user = (User) Beans.get(LoginManager.class).getLoggedInUser(request);
		//开始审批
		Boolean resultBoolean = next(id,request);
		if(resultBoolean){
			XmNewSq xmNewSq = commonDao.findObjectById(XmNewSq.class, id);
			xmNewSq.setCustomerType(sq.getCustomerType());
			xmNewSq.setCustomerTypeCode(sq.getCustomerTypeCode());
			xmNewSq.setCustomerLevel(sq.getCustomerLevel());
			xmNewSq.setCustomerLevelCode(sq.getCustomerLevelCode());
			xmNewSq.setEd(sq.getEd());
			commonDao.updateObject(xmNewSq);
			//添加日志
			XmNewSqLog log = new XmNewSqLog();
			log.setSqId(id);
			log.setReviewId(user.getId());
			log.setReviewNodeName(nodeName);
			log.setCustomerType(sq.getCustomerType());
			log.setCustomerLevel(sq.getCustomerLevel());
			log.setEd(sq.getEd());
			log.setRemark(remark);
			log.setResultType(Constant.SQ_APPROVE_TYPE_2);
			log.setCreatedTime(new Date());
			commonDao.insertObject(log);
			//将applicationinfo表的转办人清空
			String sql = "select * from xm_new_application_info where sq_id='"+id+"'";
			List<XmNewApplicationInfo> list = commonDao.queryBySql(XmNewApplicationInfo.class, sql, null);
			if(list.size()>0){
				XmNewApplicationInfo info = list.get(0);
				info.setZbUser("");
				commonDao.updateObject(info);
			}
			return true;
		}else{
			return false;
		}
	}
	/*
	 * 通过审批至---转办人员（无需保存数据，只需进入下一流程）
	 */
	public Boolean passZ(String id ,XmNewSqForm sq,String nodeName,HttpServletRequest request,String auditUserIds,String remark) throws Exception{
		//添加applicationinfo表审批人(转办人员动态变化，采用这方式)
		String sql = "select * from xm_new_application_info where sq_id='"+id+"'";
		List<XmNewApplicationInfo> list = commonDao.queryBySql(XmNewApplicationInfo.class, sql, null);
		if(list.size()>0){
			XmNewApplicationInfo info = list.get(0);
			info.setZbUser(auditUserIds);
			commonDao.updateObject(info);
		}
		//开始审批
		return next(id,request);
	}
	/*
	 * 评审审批---进入部门审批（需保存数据，并跳过转办人员、评审终审节点）
	 */
	public Boolean passP(String id ,XmNewSqForm sq,String nodeName,HttpServletRequest request,String remark) throws Exception{
		User user = (User) Beans.get(LoginManager.class).getLoggedInUser(request);
		//开始审批
		Boolean resultBoolean = nextStepTwo(id,request);
		if(resultBoolean){
			XmNewSq xmNewSq = commonDao.findObjectById(XmNewSq.class, id);
			xmNewSq.setCustomerType(sq.getCustomerType());
			xmNewSq.setCustomerTypeCode(sq.getCustomerTypeCode());
			xmNewSq.setCustomerLevel(sq.getCustomerLevel());
			xmNewSq.setCustomerLevelCode(sq.getCustomerLevelCode());
			xmNewSq.setEd(sq.getEd());
			commonDao.updateObject(xmNewSq);
			//添加日志
			XmNewSqLog log = new XmNewSqLog();
			log.setSqId(id);
			log.setReviewId(user.getId());
			log.setReviewNodeName(nodeName);
			log.setCustomerType(sq.getCustomerType());
			log.setCustomerLevel(sq.getCustomerLevel());
			log.setEd(sq.getEd());
			log.setRemark(remark);
			log.setResultType(Constant.SQ_APPROVE_TYPE_2);
			log.setCreatedTime(new Date());
			commonDao.insertObject(log);
			return true;
		}else{
			return false;
		}
	}
	/*
	 * 未通过审批
	 */
	public void unpass(String id ,String nodeName,User user, HttpServletRequest request){
		String sql = "select * from xm_new_application_info where sq_id='"+id+"'";
		List<XmNewApplicationInfo> list = commonDao.queryBySql(XmNewApplicationInfo.class, sql, null);
		request.setAttribute("serialNumber", list.get(0).getSerialNumber());
		request.setAttribute("applicationId",  list.get(0).getId());
		request.setAttribute("applicationStatus", ApplicationStatusEnum.REJECTAPPROVE);
		request.setAttribute("objection", "false");
		request.setAttribute("examineAmount", "");
		Boolean result = stepToNextProcess(request);
		if(result){
			XmNewSq xmNewSq = commonDao.findObjectById(XmNewSq.class, id);
			xmNewSq.setStatus(Constant.SQ_APPROVE_TYPE_3);
			commonDao.updateObject(xmNewSq);
			//添加日志
			XmNewSqLog log = new XmNewSqLog();
			log.setReviewId(user.getId());
			log.setReviewNodeName(nodeName);
			log.setResultType(Constant.SQ_APPROVE_TYPE_3);
			log.setCreatedTime(new Date());
			log.setSqId(id);
			commonDao.insertObject(log);
		}
	}
	
	public XmNewSq findZaById(String id){
		return xmNewSqDao.findZaById(id);
	}
	/*
	 * 申请页面
	 */
	public QueryResult<XmNewSq> findAllSq(XmNewSqFilter filter){
		List<XmNewSq> ls = xmNewSqDao.findAllZaByFilter(filter);
		int size = xmNewSqDao.findAllZaCountByFilter(filter);
		QueryResult<XmNewSq> qs = new QueryResult<XmNewSq>(size, ls);
		return qs;
	}
	/*
	 * 审批页面
	 */
	public QueryResult<XmNewSq> findApplyZaByFilter(XmNewSqFilter filter){
		List<XmNewSq> ls = xmNewSqDao.findApplyZaByFilter(filter);
		int size = xmNewSqDao.findApplyZaByFilterCount(filter);
		QueryResult<XmNewSq> qs = new QueryResult<XmNewSq>(size, ls);
		return qs;
	}
	
	//判断是否机构负责人
	public Boolean ifOrgManager(String userId){
		String sql = "select * from sys_charge where user_id='"+userId+"'";
		List<SystemCharge> chargeList = commonDao.queryBySql(SystemCharge.class, sql, null);
		if(chargeList.size()>0){
			return true;
		}else{
			return false;
		}
	}
	/*
	 * 获取通过审批的商圈
	 */
	public List<XmNewSq> findPassSq(){
		XmNewSqFilter filter = new XmNewSqFilter();
		filter.setStatus(Constant.SQ_APPROVE_TYPE_2);
		List<XmNewSq> ls = xmNewSqDao.findAllZaByFilter(filter);
		return ls;
	}
	/*
	 * 获取商圈流程log
	 */
	public List<XmNewSqLog> getSqLog(String sqId){
		String sql = "select * from xm_new_sq_log where sq_id='"+sqId+"' order by created_time";
		List<XmNewSqLog> logList = commonDao.queryBySql(XmNewSqLog.class, sql, null);
		return logList;
	}
	
	// 根据过滤条件查询商圈流程
	public QueryResult<XmNewFlow> findSqFlowByFilter(XmNewFlowFilter filter) {
		return commonDao.findObjectsByFilter(XmNewFlow.class, filter);
	}
	
	// 新增商圈流程表
	public void insertSqFlow(XmNewFlow xmNewFlow) {
			commonDao.insertObject(xmNewFlow);
	}
	
	/**
	 * 提交申请，开始流程(商圈流程)
	 * @param customer_id
	 */
	public Boolean saveApply(String sqId){
		//设置申请
		XmNewApplicationInfo xmNewApplicationInfo = new XmNewApplicationInfo();
		//customerApplicationInfo.setStatus(status);
		xmNewApplicationInfo.setId(IDGenerator.generateID());
		xmNewApplicationInfo.setSqId(sqId);
		
		xmNewApplicationInfo.setStatus(Constant.APPROVE_INTOPICES);
		//查找商圈流程
		List<XmNewFlow> list = commonDao.queryBySql(XmNewFlow.class, "select * from xm_new_flow", null);
		if(list.size()==0){
			return false;
		}
		//添加商圈流程id
		xmNewApplicationInfo.setSqFlowId(list.get(0).getId());
		xmNewApplicationInfo.setCreatedTime(new Date());
		
		commonDao.insertObject(xmNewApplicationInfo);
		
		//添加商圈流程
		WfProcessInfo wf=new WfProcessInfo();
		wf.setProcessType(WfProcessInfoType.sq_type);
		wf.setVersion("1");
		commonDao.insertObject(wf);
		List<NodeAudit> lists=nodeAuditService.findByNodeTypeAndProductId(NodeAuditTypeEnum.sqFlow.name(),list.get(0).getId());
		boolean startBool=false;
		boolean endBool=false;
		//节点id和WfStatusInfo id的映射
		Map<String, String> nodeWfStatusMap = new HashMap<String, String>();
		for(NodeAudit nodeAudit:lists){
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
					customerApplicationProcess.setApplicationId(xmNewApplicationInfo.getId());
					commonDao.insertObject(customerApplicationProcess);
					
					XmNewApplicationInfo info = commonDao.findObjectById(XmNewApplicationInfo.class, xmNewApplicationInfo.getId());
					info.setSerialNumber(serialNumber);
					commonDao.updateObject(info);
				}
			}
			
			if(nodeAudit.getIsend().equals(YesNoEnum.YES.name())){
				endBool=true;
			}
		}
		//节点关系
		List<NodeControl> nodeControls = nodeAuditService.findNodeControlByNodeTypeAndProductId(NodeAuditTypeEnum.sqFlow.name(), xmNewApplicationInfo.getSqFlowId());
		for(NodeControl control : nodeControls){
			WfStatusResult wfStatusResult = new WfStatusResult();
			wfStatusResult.setCurrentStatus(nodeWfStatusMap.get(control.getCurrentNode()));
			wfStatusResult.setNextStatus(nodeWfStatusMap.get(control.getNextNode()));
			wfStatusResult.setExamineResult(control.getCurrentStatus());
			commonDao.insertObject(wfStatusResult);
		}
		return true;
	}
	/*
	 * 创建进入下一流程条件
	 */
	public Boolean next(String id,HttpServletRequest request) throws Exception{
		String sql = "select * from xm_new_application_info where sq_id='"+id+"'";
		List<XmNewApplicationInfo> list = commonDao.queryBySql(XmNewApplicationInfo.class, sql, null);
		request.setAttribute("serialNumber", list.get(0).getSerialNumber());
		request.setAttribute("applicationId",  list.get(0).getId());
		request.setAttribute("applicationStatus", ApplicationStatusEnum.APPROVE);
		request.setAttribute("objection", "false");
		request.setAttribute("examineAmount", "");
		return stepToNextProcess(request);
	}
	/*
	 * 进入下一流程
	 */
	public Boolean stepToNextProcess(HttpServletRequest request) {
		try {
			XmNewApplicationInfo xmNewApplicationInfo = new XmNewApplicationInfo();
			CustomerApplicationProcess customerApplicationProcess = new CustomerApplicationProcess();
			IUser user = Beans.get(LoginManager.class).getLoggedInUser(request);
			String loginId = user.getId();
			String serialNumber = request.getAttribute("serialNumber").toString();
			String applicationStatus = request.getAttribute("applicationStatus").toString();
			String applicationId = request.getAttribute("applicationId").toString();
			String objection = request.getAttribute("objection").toString();
			if(objection.equals("true")){
				applicationStatus = ApproveOperationTypeEnum.OBJECTION.toString();
			}
			//applicationStatus 必须是ApproveOperationTypeEnum中的通过，退回，拒绝三个类型
			String examineResutl = processService.examineSq(applicationId,serialNumber, loginId, applicationStatus);
			//更新单据状态
		    if (examineResutl.equals(ApproveOperationTypeEnum.REJECTAPPROVE.toString()) ||
		    		examineResutl.equals(ApproveOperationTypeEnum.RETURNAPPROVE.toString()) ||
		    		examineResutl.equals(ApproveOperationTypeEnum.NORMALEND.toString())) {
				if(examineResutl.equals(ApproveOperationTypeEnum.REJECTAPPROVE.toString())){
					xmNewApplicationInfo.setStatus(Constant.REFUSE_INTOPICES);
				}
				if(examineResutl.equals(ApproveOperationTypeEnum.NORMALEND.toString())){
					xmNewApplicationInfo.setStatus(Constant.APPROVED_INTOPICES);
				}
				xmNewApplicationInfo.setId(applicationId);
				xmNewApplicationInfo.setModifiedBy(user.getId());
				xmNewApplicationInfo.setModifiedTime(new Date());
				commonDao.updateObject(xmNewApplicationInfo);
				
				customerApplicationProcess.setNextNodeId(null);
			} else {
				xmNewApplicationInfo.setStatus(Constant.APPROVE_INTOPICES);
				xmNewApplicationInfo.setId(applicationId);
				xmNewApplicationInfo.setModifiedBy(user.getId());
				xmNewApplicationInfo.setModifiedTime(new Date());
				commonDao.updateObject(xmNewApplicationInfo);
				
				customerApplicationProcess.setNextNodeId(examineResutl);
			}
			if (StringUtils.isNotEmpty(applicationStatus) && applicationStatus.equals(ApplicationStatusEnum.REJECTAPPROVE)) {
				String refusalReason = request.getParameter("reason");
				customerApplicationProcess.setRefusalReason(refusalReason);
			}
			customerApplicationProcess.setProcessOpStatus(applicationStatus);
			customerApplicationProcess.setSerialNumber(serialNumber);
			customerApplicationProcess.setAuditUser(loginId);
			customerApplicationProcess.setCreatedTime(new Date());
//			customerApplicationProcess.setDelayAuditUser(user.getId());//清空字段值 
			customerApplicationIntopieceWaitDao.updateCustomerApplicationProcessBySerialNumber(customerApplicationProcess);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/*
	 * 评审审批---部门审批（跳跃两个节点）
	 */
	public Boolean nextStepTwo(String id,HttpServletRequest request) throws Exception{
		String sql = "select * from xm_new_application_info where sq_id='"+request.getParameter("sqId")+"'";
		List<XmNewApplicationInfo> list = commonDao.queryBySql(XmNewApplicationInfo.class, sql, null);
		request.setAttribute("serialNumber", list.get(0).getSerialNumber());
		request.setAttribute("applicationId",  list.get(0).getId());
		request.setAttribute("applicationStatus", ApplicationStatusEnum.APPROVE);
		request.setAttribute("objection", "false");
		request.setAttribute("examineAmount", "");
		//进入转办人员
		stepToNextProcess(request);
		//进入评审终审
		stepToNextProcess(request);
		//进入部门审批
		return stepToNextProcess(request);
	}
	
	/* 查询商圈资料信息 */
	public QueryResult<XmNewSqUploadForm> findSqUploadList(XmNewSqFilter filter) {
		List<XmNewSqUploadForm> ls = xmNewSqUploadDao.findSqUploadList(filter);
		int size = xmNewSqUploadDao.findSqUploadListCount(filter);
		QueryResult<XmNewSqUploadForm> qr = new QueryResult<XmNewSqUploadForm>(size,ls);
		return qr;
	}
	/*
	 * 上传商圈材料
	 */
	public void importImage(MultipartFile file, String sqId,User user) {
		// TODO Auto-generated method stub
		Map<String, String> map = UploadFileTool.uploadYxzlFileBySpring(file,sqId);
		String fileName = map.get("fileName");
		String url = map.get("url");
		XmNewSqUploadForm form = new XmNewSqUploadForm();
		form.setSqId(sqId);
		form.setCreatedBy(user.getId());
		form.setCreatedTime(new Date());
		if (StringUtils.trimToNull(url) != null) {
			form.setUrl(url);
		}
		if (StringUtils.trimToNull(fileName) != null) {
			form.setAttachment(fileName);
		}
		
		commonDao.insertObject(form);
	}
	/* 删除资料信息 */
	public void deleteImage(String id) {
		commonDao.deleteObject(XmNewSqUploadForm.class, id);
	}
	/**
	 * 下载商圈上传资料
	 * @param id
	 * @throws Exception 
	 */
	public void downLoadYxzlById(HttpServletResponse response,String id) throws Exception{
		XmNewSqUploadForm v = commonDao.findObjectById(XmNewSqUploadForm.class, id);
		if(v!=null){
			UploadFileTool.downLoadFile(response, v.getUrl(), v.getAttachment());
		}
	}
}
