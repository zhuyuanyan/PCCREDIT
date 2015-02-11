package com.cardpay.pccredit.intopieces.service;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cardpay.pccredit.common.UploadFileTool;
import com.cardpay.pccredit.customer.constant.CustomerInforConstant;
import com.cardpay.pccredit.customer.model.CustomerCareersInformation;
import com.cardpay.pccredit.customer.model.CustomerInfor;
import com.cardpay.pccredit.customer.service.CustomerInforService;
import com.cardpay.pccredit.divisional.constant.DivisionalProgressEnum;
import com.cardpay.pccredit.divisional.constant.DivisionalTypeEnum;
import com.cardpay.pccredit.divisional.service.DivisionalService;
import com.cardpay.pccredit.intopieces.constant.ApplicationStatusEnum;
import com.cardpay.pccredit.intopieces.constant.Constant;
import com.cardpay.pccredit.intopieces.constant.IntoPiecesException;
import com.cardpay.pccredit.intopieces.constant.NumberContext;
import com.cardpay.pccredit.intopieces.dao.CustomerApplicationIntopieceWaitDao;
import com.cardpay.pccredit.intopieces.dao.IntoPiecesDao;
import com.cardpay.pccredit.intopieces.dao.comdao.IntoPiecesComdao;
import com.cardpay.pccredit.intopieces.filter.IntoPiecesCardQueryFilter;
import com.cardpay.pccredit.intopieces.filter.IntoPiecesFilter;
import com.cardpay.pccredit.intopieces.filter.MakeCardFilter;
import com.cardpay.pccredit.intopieces.model.ApplicationDataImport;
import com.cardpay.pccredit.intopieces.model.BasicCustomerInformationS;
import com.cardpay.pccredit.intopieces.model.CustomerAccountData;
import com.cardpay.pccredit.intopieces.model.CustomerApplicationCom;
import com.cardpay.pccredit.intopieces.model.CustomerApplicationContact;
import com.cardpay.pccredit.intopieces.model.CustomerApplicationGuarantor;
import com.cardpay.pccredit.intopieces.model.CustomerApplicationInfo;
import com.cardpay.pccredit.intopieces.model.CustomerApplicationOther;
import com.cardpay.pccredit.intopieces.model.CustomerApplicationProcess;
import com.cardpay.pccredit.intopieces.model.CustomerApplicationRecom;
import com.cardpay.pccredit.intopieces.model.CustomerCareersInformationS;
import com.cardpay.pccredit.intopieces.model.IntoPieces;
import com.cardpay.pccredit.intopieces.model.IntoPiecesCardQuery;
import com.cardpay.pccredit.intopieces.model.MakeCard;
import com.cardpay.pccredit.intopieces.model.VideoAccessories;
import com.cardpay.pccredit.intopieces.web.ApproveHistoryForm;
import com.cardpay.pccredit.intopieces.web.CustomerApplicationIntopieceWaitForm;
import com.cardpay.pccredit.intopieces.web.XmApplnSxjcForm;
import com.cardpay.pccredit.product.model.AddressAccessories;
import com.cardpay.pccredit.riskControl.filter.AgrCrdXykCunegFilter;
import com.cardpay.pccredit.riskControl.filter.RiskCustomerFilter;
import com.cardpay.pccredit.riskControl.model.AgrCrdXykCuneg;
import com.cardpay.pccredit.riskControl.model.RiskCustomer;
import com.cardpay.pccredit.system.filter.DictFilter;
import com.cardpay.pccredit.system.model.Dict;
import com.cardpay.pccredit.system.model.NodeControl;
import com.cardpay.pccredit.xm_appln.dao.XM_APPLN_ADDR_Dao;
import com.cardpay.pccredit.xm_appln.dao.XM_APPLN_Dao;
import com.cardpay.pccredit.xm_appln.dao.XM_APPLN_JCZL_Dao;
import com.cardpay.pccredit.xm_appln.dao.XM_APPLN_KPMX_Dao;
import com.cardpay.pccredit.xm_appln.dao.XM_APPLN_LXRZL_Dao;
import com.cardpay.pccredit.xm_appln.dao.XM_APPLN_POZL_Dao;
import com.cardpay.pccredit.xm_appln.dao.XM_APPLN_TJINFO_Dao;
import com.cardpay.pccredit.xm_appln.dao.XM_APPLN_ZXQSZL_Dao;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_ADDR;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_JCZL;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_KPMX;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_LXRZL;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_POZL;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_SXJC;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_TJINFO;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_ZXQSZL;
import com.cardpay.workflow.dao.WfStatusResultDao;
import com.cardpay.workflow.models.WfProcessRecord;
import com.cardpay.workflow.models.WfStatusQueueRecord;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.wicresoft.jrad.base.auth.IUser;
import com.wicresoft.jrad.base.database.dao.common.CommonDao;
import com.wicresoft.jrad.base.database.model.BusinessModel;
import com.wicresoft.jrad.base.database.model.QueryResult;
import com.wicresoft.jrad.base.web.security.LoginManager;
import com.wicresoft.util.spring.Beans;

@Service
public class IntoPiecesService {

	// TODO 路径使用相对路径，首先获得应用所在路径，之后建立上传文件目录，图片类型使用IMG，文件使用DOC

	@Autowired
	private CommonDao commonDao;
	
	@Autowired
	private IntoPiecesDao intoPiecesDao;

	@Autowired
	private IntoPiecesComdao intoPiecesComdao;
	
	@Autowired
	private CustomerInforService customerInforService;
	
	@Autowired
	private DivisionalService divisionalService;
	
	@Autowired
	private CustomerApplicationProcessService customerApplicationProcessService;
	
	@Autowired
	private CustomerApplicationIntopieceWaitService customerApplicationIntopieceWaitService;
	
	@Autowired
	private WfStatusResultDao wfStatusResultDao;
	@Autowired
	private CustomerApplicationIntopieceWaitDao customerApplicationIntopieceWaitDao;
	
	@Autowired
	private XM_APPLN_JCZL_Dao jczl_Dao;
	
	@Autowired
	private XM_APPLN_Dao appln_Dao;
	@Autowired
	private XM_APPLN_ADDR_Dao addr_Dao;
	@Autowired
	private XM_APPLN_ZXQSZL_Dao zxqs_Dao;
	@Autowired
	private XM_APPLN_LXRZL_Dao lxrzl_Dao;
	@Autowired
	private XM_APPLN_POZL_Dao pozl_Dao;
	@Autowired
	private XM_APPLN_KPMX_Dao kpmx_Dao;
	@Autowired
	private XM_APPLN_TJINFO_Dao tjinfo_Dao;
	
	/* 查询进价信息 */
	/*
	 * TODO 1.添加注释 2.SQL写进DAO层
	 */
	public QueryResult<IntoPieces> findintoPiecesByFilter(
			IntoPiecesFilter filter) {
		QueryResult<IntoPieces> queryResult = intoPiecesComdao.findintoPiecesByFilter(filter);
		int sum = intoPiecesComdao.findintoPiecesByFilterCount(filter);
		QueryResult<IntoPieces> qs = new QueryResult<IntoPieces>(sum, queryResult.getItems());
		List<IntoPieces> intoPieces = qs.getItems();
		for(IntoPieces pieces : intoPieces){
			if(pieces.getStatus()==null){
				pieces.setNodeName("未提交申请");
			}
			else{
				if(pieces.getStatus().equals(Constant.SAVE_INTOPICES)){
					pieces.setNodeName("未提交申请");
				} else if(pieces.getStatus().equals(Constant.APPROVE_INTOPICES)){
					String nodeName = intoPiecesComdao.findAprroveProgress(pieces.getId());
					if(StringUtils.isNotEmpty(nodeName)){
						pieces.setNodeName(nodeName);
					} else {
						pieces.setNodeName("不在审批中");
					}
				} else {
					pieces.setNodeName("审批结束");
				}
			}
		}
		return qs;
	}
	
	
	/* 经理岗查询进件信息 */
	/*
	 * TODO 1.添加注释 2.SQL写进DAO层
	 */
	public QueryResult<IntoPieces> customerfindintoPiecesByFilter(
			IntoPiecesFilter filter) {
		QueryResult<IntoPieces> queryResult = intoPiecesComdao.customerfindintoPiecesByFilter(filter);
		int sum = intoPiecesComdao.customerfindintoPiecesByFilterCount(filter);
		QueryResult<IntoPieces> qs = new QueryResult<IntoPieces>(sum, queryResult.getItems());
		List<IntoPieces> intoPieces = qs.getItems();
		for(IntoPieces pieces : intoPieces){
			if(pieces.getStatus().equals(Constant.SAVE_INTOPICES)){
				pieces.setNodeName("未提交申请");
			} else if(pieces.getStatus().equals(Constant.APPROVE_INTOPICES)){
				String nodeName = intoPiecesComdao.findAprroveProgress(pieces.getId());
				if(StringUtils.isNotEmpty(nodeName)){
					pieces.setNodeName(nodeName);
				} else {
					pieces.setNodeName("不在审批中");
				}
			} else {
				pieces.setNodeName("审批结束");
			}
		}
		return qs;
	}
	
	/*
	 * 查询已经申请通过进件
	 */
	public QueryResult<IntoPieces> findintoApplayPiecesByFilter(
			IntoPiecesFilter filter) {
		List<IntoPieces> listCAI = intoPiecesComdao.findintoApplayPiecesByFilter(filter).getItems();
		int size = intoPiecesComdao.findintoApplayPiecesCountByFilter(filter);
		QueryResult<IntoPieces> qs = new QueryResult<IntoPieces>(size, listCAI);
		return qs;
	}
	
	/*
	 * 判断经理是否有权操作，否则移交
	 */
	public int checkValue(String userId,String valueType) {
		return intoPiecesDao.checkValue(userId,valueType);
	}
	
	/*
	 * 查询已经申请通过进件
	 */
	public QueryResult<MakeCard> findCardByFilter(
			MakeCardFilter filter) {
		return intoPiecesComdao.findCardByFilter(filter);
	}

	/* 保存客户所有资料(主表及其其他的表) */
	/*
	 * TODO 1.文件上传使用另外的工具类进行操作
	 * 2.文件上传文件与服务器端文件名相同使用删除极为不正确，应每次对文件进行重命名，使用UUID作为文件名
	 * 3.文件上传设置类型后缀枚举类型，对不在枚举中的文件类型不进行上传(不仅仅是前端检查)
	 * 4.使用stringbuffer作为文件缓存，不要使用类似byte[8192]这种 5.注释应标明输入输出，异常抛出类型
	 */
	public void saveAllInfo(List<BusinessModel> list,
			HttpServletRequest request, String userId, String status,
			Map<String, Object> paramMap){
		if (list != null && list.size() > 0) {
			for (BusinessModel obj : list) {
				obj.setCreatedTime(new Date());
				obj.setCreatedBy(userId);
				if (!(obj instanceof AddressAccessories)) {
					if(obj instanceof CustomerInfor){
						CustomerInfor customerInfor = (CustomerInfor)obj;
						/* 判断是否需要移交进件 */
						if ((Boolean) paramMap.get("checkFlag")&&(Boolean)paramMap.get("flag")) {
							divisionalService.insertDivisionalCustomer(
									customerInfor.getId(),
									DivisionalTypeEnum.initiative,
									DivisionalProgressEnum.charge);
						}
					}
					commonDao.insertObject(obj);
				} else if (obj instanceof AddressAccessories) {
					AddressAccessories v = (AddressAccessories) obj;
					String fileName = UploadFileTool.uploadFileBySpring(
							request, v);
					if (StringUtils.trimToNull(fileName) != null) {
						v.setProductAccessoriesUrl(Constant.FILE_PATH
								+ fileName);
					}
					commonDao.insertObject(v);
				}
			}
		}
		/* 生成快照 */
		if (StringUtils.trimToNull(status) != null
				&& Constant.APPROVE_INTOPICES.equalsIgnoreCase(status)
				&& (Boolean)paramMap.get("flag")) {
			this.cloneData(paramMap.get("customerId").toString(),
					paramMap.get("applicationId").toString(), paramMap.get("productId").toString());
			paramMap.put("flag", false);
		}
	}

	/* 更新客户所有信息 */
	public void updateAllInfo(List<BusinessModel> list,
			HttpServletRequest request, String userId, String status,Map<String, Object> paramMap){
		if (list != null && list.size() > 0) {
			for (BusinessModel obj : list) {
				obj.setModifiedTime(new Date());
				obj.setModifiedBy(userId);
				if (!(obj instanceof AddressAccessories)) {
					if(obj instanceof CustomerInfor){
						CustomerInfor customerInfor = (CustomerInfor)obj;
						/* 判断是否需要移交进件 */
						if ((Boolean) paramMap.get("checkFlag")&&(Boolean)paramMap.get("flag")) {
							divisionalService.insertDivisionalCustomer(
									customerInfor.getId(),
									DivisionalTypeEnum.initiative,
									DivisionalProgressEnum.charge);
						}
					}
					commonDao.updateObject(obj);
				} else {
					AddressAccessories v = (AddressAccessories) obj;
					String fileName = UploadFileTool.uploadFileBySpring(
							request, v);
					if (StringUtils.trimToNull(fileName) != null) {
						v.setProductAccessoriesUrl(Constant.FILE_PATH
								+ fileName);
					}
					commonDao.updateObject(v);
				}
			}
		}
		/*生成快照*/
		if (StringUtils.trimToNull(status) != null
				&& Constant.APPROVE_INTOPICES.equalsIgnoreCase(status)&&(Boolean)paramMap.get("flag")) {
			this.cloneData(paramMap.get("customerId").toString(),
					paramMap.get("applicationId").toString(), paramMap.get("productId").toString());
			paramMap.put("flag", false);
		}
	}
	
	
	/* 申请件待审核中维护进件
	 * 注：基本资料 和 客户职业信息资料 更新快照表中数据
	 * 更新客户所有信息 
	 * 
	 * author：王海东
	 * */
	public void updateAllInfoWait(List<BusinessModel> list,
			HttpServletRequest request, String userId, String status,Map<String, Object> paramMap){
		if (list != null && list.size() > 0) {
			for (BusinessModel obj : list) {
				obj.setModifiedTime(new Date());
				obj.setModifiedBy(userId);
				if (!(obj instanceof AddressAccessories)) {
					if(obj instanceof BasicCustomerInformationS){
						BasicCustomerInformationS customerInfor = (BasicCustomerInformationS)obj;
					    this.updateBasicCustomerInformationS(customerInfor);
					}
					if(obj instanceof CustomerCareersInformationS){
						CustomerCareersInformationS customerCareersInformationS = (CustomerCareersInformationS)obj;
					    this.updateCustomerCareersInformationS(customerCareersInformationS);
					}
					commonDao.updateObject(obj);
				} else {
					AddressAccessories v = (AddressAccessories) obj;
					String fileName = UploadFileTool.uploadFileBySpring(
							request, v);
					if (StringUtils.trimToNull(fileName) != null) {
						v.setProductAccessoriesUrl(Constant.FILE_PATH
								+ fileName);
					}
					commonDao.updateObject(v);
				}
			}
		}
	}
	/*
	 * 更新客户基本信息快照表BASIC_CUSTOMER_INFORMATION_S
	 * 
	 */
	public int updateBasicCustomerInformationS(BasicCustomerInformationS basicCustomerInformationS){
		return commonDao.updateObject(basicCustomerInformationS);
	}
	/*
	 * 更新客户职业信息快照表CUSTOMER_CAREERS_INFORMATION_S
	 */
	public int updateCustomerCareersInformationS(CustomerCareersInformationS customerCareersInformationS) {
		return commonDao.updateObject(customerCareersInformationS);
		
	}
	private void cloneData(String customerId,String applicationId,String productId){
		customerInforService.insertCustomerInfoBySubmitApp(
				customerId, applicationId,productId);
	}

	/* 客户证件ID模糊匹配 */
	/*
	 * TODO 1.注释标明输入输出，异常抛出类型 2.SQL写进DAO层
	 */
	public void selectLikeByCardId(HttpServletResponse response,
			String tempParam) throws Exception {
		intoPiecesComdao.selectLikeByCardId(response, tempParam);
	}
	
	/*
	 * TODO 1.注释标明输入输出，异常抛出类型 2.SQL写进DAO层
	 */
	public void delete(String name,String value) throws Exception {
		if(Constant.CONTACTID.equals(name)){
			commonDao.deleteObject(CustomerApplicationContact.class, value);
		}else if(Constant.GUARANTORID.equals(name)){
			commonDao.deleteObject(CustomerApplicationGuarantor.class, value);
		}else if(Constant.RECOMMENDID.equals(name)){
			commonDao.deleteObject(CustomerApplicationRecom.class, value);
		}else{
			throw new Exception("该方法只用来删除联系人，担保人，推荐人");
		}
	}
	
	/*
	 * 导出文本格式的文件并且上传到ftp服务器上
	 */
	public String exportData(String applicationId, String customerId,HttpServletResponse response) throws Exception {
		StringBuffer content = new StringBuffer();
		StringBuffer sb = new StringBuffer();
		List<CustomerApplicationContact> customerApplicationContactList = null;
		List<CustomerApplicationGuarantor> customerApplicationGuarantorList = null;
		List<CustomerApplicationRecom> customerApplicationRecomList = null;
		CustomerInfor customerInfor = this.findCustomerInforById(customerId);
		customerInfor = customerInfor==null?new CustomerInfor():customerInfor;
		CustomerCareersInformation customerCareersInformation = this.findCustomerCareersInformationByCustomerId(customerId);
		customerCareersInformation = customerCareersInformation==null?new CustomerCareersInformation():customerCareersInformation;
		CustomerApplicationInfo customerApplicationInfo = this.findCustomerApplicationInfoByApplicationId(applicationId);
		customerApplicationInfo = customerApplicationInfo==null?new CustomerApplicationInfo():customerApplicationInfo;
		List<CustomerApplicationContact> customerApplicationContactListDB = this.findCustomerApplicationContactsByApplicationId(applicationId);
		customerApplicationContactListDB = customerApplicationContactListDB==null?new ArrayList<CustomerApplicationContact>():customerApplicationContactListDB;
		List<CustomerApplicationGuarantor> customerApplicationGuarantorListDB = this.findCustomerApplicationGuarantorsByApplicationId(applicationId);
		customerApplicationGuarantorListDB = customerApplicationGuarantorListDB==null?new ArrayList<CustomerApplicationGuarantor>():customerApplicationGuarantorListDB;
		List<CustomerApplicationRecom> customerApplicationRecomListDB = this.findCustomerApplicationRecomsByApplicationId(applicationId);
		customerApplicationRecomListDB = customerApplicationRecomListDB==null?new ArrayList<CustomerApplicationRecom>():customerApplicationRecomListDB;
		CustomerApplicationOther customerApplicationOther = this.findCustomerApplicationOtherByApplicationId(applicationId);
		customerApplicationOther = customerApplicationOther==null?new CustomerApplicationOther():customerApplicationOther;
		CustomerAccountData customerAccountData = this.findCustomerAccountDataByApplicationId(applicationId);
		customerAccountData = customerAccountData==null?new CustomerAccountData():customerAccountData;
		CustomerApplicationCom customerApplicationCom = this.findCustomerApplicationComByApplicationId(applicationId);
		customerApplicationCom = customerApplicationCom==null?new CustomerApplicationCom():customerApplicationCom;
		List<ApplicationDataImport> applicationDataImportList = this.findApplicationDataImport();
		if(customerApplicationContactListDB!=null){
			customerApplicationContactList = customerApplicationContactListDB;
			if(customerApplicationContactList.size()!=2){
				for(int i=customerApplicationContactList.size();i<2;i++){
					customerApplicationContactList.add(new CustomerApplicationContact());
				}
			}
		}
		if(customerApplicationGuarantorListDB!=null){
			customerApplicationGuarantorList = customerApplicationGuarantorListDB;
			if(customerApplicationGuarantorList.size()!=2){
				for(int i=customerApplicationGuarantorList.size();i<2;i++){
					customerApplicationGuarantorList.add(new CustomerApplicationGuarantor());
				}
			}
		}
		if(customerApplicationRecomListDB!=null){
			customerApplicationRecomList = customerApplicationRecomListDB;
			if(customerApplicationRecomList.size()!=2){
				for(int i=customerApplicationRecomList.size();i<2;i++){
					customerApplicationRecomList.add(new CustomerApplicationRecom());
				}
			}
		}
		//基本资料form
		XM_APPLN_JCZL jczl = jczl_Dao.findByCustomerId(customerId) ;
		//地址录入form
		XM_APPLN appln = appln_Dao.findByCustomerId(customerId);
		//地址form
		XM_APPLN_ADDR addr = addr_Dao.findByCustomerId(customerId);
		//直系亲属form
		XM_APPLN_ZXQSZL zxqszl = zxqs_Dao.findByCustomerId(customerId);
		//联系人
		List<XM_APPLN_LXRZL> lxr = lxrzl_Dao.findByCustomerId(customerId);
		//配偶
		XM_APPLN_POZL pozl = pozl_Dao.findByCustomerId(customerId);
		//卡片明细
		List<XM_APPLN_KPMX> kpmx = kpmx_Dao.findByCustomerId(customerId);
		//推荐人
		XM_APPLN_TJINFO tjinfo = tjinfo_Dao.findByCustomerId(customerId);
		String uuid19 = NumberContext.getUUid(19);
		for(int i=0;i<applicationDataImportList.size();i++){
			ApplicationDataImport applicationDataImport = applicationDataImportList.get(i);
			int id = Integer.valueOf(applicationDataImport.getId());
			int length = Integer.valueOf(applicationDataImport.getFieldLength());
			switch(id-1){
				 //银行编号
			    case 0:content = UploadFileTool.getContent(content,CustomerInforConstant.BANK_ID,length);break;
			    //申请书条形码/流水号
			    case 1:content = UploadFileTool.getContent(content,uuid19,length);break;
			    //申请卡片产品
			    case 2:content = UploadFileTool.getContent(content,CustomerInforConstant.PRODUCT_ID,length);break;
			    //主卡/副卡标志
			    case 3:content = UploadFileTool.getContent(content,CustomerInforConstant.PRODUCT_STATUS,length);break;
			    //申请流程状态标志
			    case 4:content = UploadFileTool.getContent(content,CustomerInforConstant.APPROVE_STATUS,length);break;
			    case 5:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    //申请卡片费用类型
			    case 6:content = UploadFileTool.getContent(content,CustomerInforConstant.PRODUCT_TYPE,length);break;
			    //卡片所属分行
			    case 7:content = UploadFileTool.getContent(content,jczl.getBelong_bank(),length);break;
			    case 8:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 9:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 10:
			    	content = UploadFileTool.getContent(content,customerInfor.getCardType(),length);
			    	break;
			    case 11:content = UploadFileTool.getContent(content,customerInfor.getCardId(),length);break;
			    case 12:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 13:content = UploadFileTool.getContent(content,customerInfor.getChineseName(),length);break;
			    case 14:content = UploadFileTool.getContent(content,customerInfor.getPinyinenglishName(),length);break;
			    case 15:
			    	if(customerInfor.getSex()!=null){
				    	if(customerInfor.getSex().equals("Male")){
				    		content = UploadFileTool.getContent(content,"M",length);
				    	}else{
				    		content = UploadFileTool.getContent(content,"F",length);
				    	}
			    	}
			    	break;
			    case 16:
			    	content = UploadFileTool.getContent(content,customerInfor.getCardId().substring(6, 14),length);
			    	break;
			    case 17:
			    	content = UploadFileTool.getContent(content,"1",length);
			    	break;
			    case 18:
			    	//婚姻状况
			    	content = UploadFileTool.getContent(content,jczl.getMar_status(),length);
			    	break;
			    	//教育程度
			    case 19:content = UploadFileTool.getContent(content,jczl.getEduca(),length);break;
			    	//职称/岗位
			    case 20:content = UploadFileTool.getContent(content,jczl.getInt_taxcod(),length);break;
			    //家庭电话
			    case 21:content = UploadFileTool.getContent(content,jczl.getHome_phone(),length);break;
			    //手机号码
			    case 22:content = UploadFileTool.getContent(content,jczl.getMo_phone(),length);break;
			    //E-mail地址
			    case 23:content = UploadFileTool.getContent(content,jczl.getEmail_addr(),length);break;
			    //传真机号码
			    case 24:content = UploadFileTool.getContent(content,jczl.getIrd_number(),length);break;
			    //家庭地址区段1
			    case 25:
			    	if(addr.getAddr1_type().equals("家庭地址")){
			    		if(addr.getAddr1_l1()!=null){
			    			content = UploadFileTool.getContent(content,addr.getAddr1_l1(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else if(addr.getAddr2_type().equals("家庭地址")){
			    		if(addr.getAddr2_l1()!=null){
			    			content = UploadFileTool.getContent(content,addr.getAddr2_l1(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else if(addr.getAddr3_type().equals("家庭地址")){
			    		if(addr.getAddr3_l1()!=null){
			    			content = UploadFileTool.getContent(content,addr.getAddr3_l1(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else if(addr.getAddr4_type().equals("家庭地址")){
			    		if(addr.getAddr4_l1()!=null){
			    			content = UploadFileTool.getContent(content,addr.getAddr4_l1(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else{
			    		content = UploadFileTool.getContent(content,sb.toString(),length);
			    	}
			    	break;
		    	 //家庭地址区段2
			    case 26:
			    	if(addr.getAddr1_type().equals("家庭地址")){
			    		if(addr.getAddr1_l2()!=null){
			    			content = UploadFileTool.getContent(content,addr.getAddr1_l2(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else if(addr.getAddr2_type().equals("家庭地址")){
			    		if(addr.getAddr2_l2()!=null){
			    			content = UploadFileTool.getContent(content,addr.getAddr2_l2(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else  	if(addr.getAddr3_type().equals("家庭地址")){
			    		if(addr.getAddr3_l2()!=null){
			    			content = UploadFileTool.getContent(content,addr.getAddr3_l2(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else   	if(addr.getAddr4_type().equals("家庭地址")){
			    		if(addr.getAddr4_l2()!=null){
			    			content = UploadFileTool.getContent(content,addr.getAddr4_l2(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else{
			    		content = UploadFileTool.getContent(content,sb.toString(),length);
			    	}
			    break;
			    //家庭地址区段3
			    case 27:
			    	if(addr.getAddr1_type().equals("家庭地址")){
			    		if(addr.getAddr1_l3()!=null){
			    			content = UploadFileTool.getContent(content,addr.getAddr1_l3(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else  	if(addr.getAddr2_type().equals("家庭地址")){
			    		if(addr.getAddr2_l3()!=null){
			    			content = UploadFileTool.getContent(content,addr.getAddr2_l3(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else  	if(addr.getAddr3_type().equals("家庭地址")){
			    		if(addr.getAddr3_l3()!=null){
			    			content = UploadFileTool.getContent(content,addr.getAddr3_l3(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else   	if(addr.getAddr4_type().equals("家庭地址")){
			    		if(addr.getAddr4_l3()!=null){
			    			content = UploadFileTool.getContent(content,addr.getAddr4_l3(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else{
			    		content = UploadFileTool.getContent(content,sb.toString(),length);
			    	}
			    	break;
			    	//家庭地址区段4
			    case 28:
			    	if(addr.getAddr1_type().equals("家庭地址")){
			    		if(addr.getAddr1_l4()!=null){
			    			content = UploadFileTool.getContent(content,addr.getAddr1_l4(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else  	if(addr.getAddr2_type().equals("家庭地址")){
			    		if(addr.getAddr2_l4()!=null){
			    			content = UploadFileTool.getContent(content,addr.getAddr2_l4(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else   	if(addr.getAddr3_type().equals("家庭地址")){
			    		if(addr.getAddr3_l4()!=null){
			    			content = UploadFileTool.getContent(content,addr.getAddr3_l4(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else   	if(addr.getAddr4_type().equals("家庭地址")){
			    		if(addr.getAddr4_l4()!=null){
			    			content = UploadFileTool.getContent(content,addr.getAddr4_l4(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else{
			    		content = UploadFileTool.getContent(content,sb.toString(),length);
			    	}
			    	break;
			    	//家庭邮政编码
			    case 29:
			    	if(addr.getAddr1_type().equals("家庭地址")){
			    		if(addr.getPostcode1()!=null){
			    			content = UploadFileTool.getContent(content,addr.getPostcode1(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else  	if(addr.getAddr2_type().equals("家庭地址")){
			    		if(addr.getPostcode2()!=null){
			    			content = UploadFileTool.getContent(content,addr.getPostcode2(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else   	if(addr.getAddr3_type().equals("家庭地址")){
			    		if(addr.getPostcode3()!=null){
			    			content = UploadFileTool.getContent(content,addr.getPostcode3(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else  	if(addr.getAddr4_type().equals("家庭地址")){
			    		if(addr.getPostcode4()!=null){
			    			content = UploadFileTool.getContent(content,addr.getPostcode4(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else{
			    		content = UploadFileTool.getContent(content,sb.toString(),length);
			    	}
			    break;
			    //家庭房屋状况
			    case 30:
			    	content = UploadFileTool.getContent(content,jczl.getHome_code(),length);break;
			    	//居住年数
			    case 31:content = UploadFileTool.getContent(content,jczl.getYr_there(),length);break;
			    //月还款额/月租金额
			    case 32:content = UploadFileTool.getContent(content,jczl.getHome_loan(),length);break;
			    //户籍地址区段1
			    case 33:
			    	if(addr.getAddr1_type().equals("户籍地址")){
			    		if(addr.getAddr1_l1()!=null){
			    			content = UploadFileTool.getContent(content,addr.getAddr1_l1(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else   	if(addr.getAddr2_type().equals("户籍地址")){
			    		if(addr.getAddr2_l1()!=null){
			    			content = UploadFileTool.getContent(content,addr.getAddr2_l1(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else   	if(addr.getAddr3_type().equals("户籍地址")){
			    		if(addr.getAddr3_l1()!=null){
			    			content = UploadFileTool.getContent(content,addr.getAddr3_l1(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else  	if(addr.getAddr4_type().equals("户籍地址")){
			    		if(addr.getAddr4_l1()!=null){
			    			content = UploadFileTool.getContent(content,addr.getAddr4_l1(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else{
			    		content = UploadFileTool.getContent(content,sb.toString(),length);
			    	}
			    break;
			    //户籍地址区段2
			    case 34:
			    	if(addr.getAddr1_type().equals("户籍地址")){
			    		if(addr.getAddr1_l2()!=null){
			    			content = UploadFileTool.getContent(content,addr.getAddr1_l2(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else   	if(addr.getAddr2_type().equals("户籍地址")){
			    		if(addr.getAddr2_l2()!=null){
			    			content = UploadFileTool.getContent(content,addr.getAddr2_l2(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else  	if(addr.getAddr3_type().equals("户籍地址")){
			    		if(addr.getAddr3_l2()!=null){
			    			content = UploadFileTool.getContent(content,addr.getAddr3_l2(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else   	if(addr.getAddr4_type().equals("户籍地址")){
			    		if(addr.getAddr4_l2()!=null){
			    			content = UploadFileTool.getContent(content,addr.getAddr4_l2(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else{
			    		content = UploadFileTool.getContent(content,sb.toString(),length);
			    	}
			    break;
			    //户籍地址区段3
			    case 35:
			    	if(addr.getAddr1_type().equals("户籍地址")){
			    		if(addr.getAddr1_l3()!=null){
			    			content = UploadFileTool.getContent(content,addr.getAddr1_l3(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else  	if(addr.getAddr2_type().equals("户籍地址")){
			    		if(addr.getAddr2_l3()!=null){
			    			content = UploadFileTool.getContent(content,addr.getAddr2_l3(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else  	if(addr.getAddr3_type().equals("户籍地址")){
			    		if(addr.getAddr3_l3()!=null){
			    			content = UploadFileTool.getContent(content,addr.getAddr3_l3(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else  	if(addr.getAddr4_type().equals("户籍地址")){
			    		if(addr.getAddr4_l3()!=null){
			    			content = UploadFileTool.getContent(content,addr.getAddr4_l3(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else{
			    		content = UploadFileTool.getContent(content,sb.toString(),length);
			    	}
			    break;
			    //户籍地址区段4
			    case 36:
			    	if(addr.getAddr1_type().equals("户籍地址")){
			    		if(addr.getAddr1_l4()!=null){
			    			content = UploadFileTool.getContent(content,addr.getAddr1_l4(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else  	if(addr.getAddr2_type().equals("户籍地址")){
			    		if(addr.getAddr2_l4()!=null){
			    			content = UploadFileTool.getContent(content,addr.getAddr2_l4(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else  	if(addr.getAddr3_type().equals("户籍地址")){
			    		if(addr.getAddr3_l4()!=null){
			    			content = UploadFileTool.getContent(content,addr.getAddr3_l4(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else   	if(addr.getAddr4_type().equals("户籍地址")){
			    		if(addr.getAddr4_l4()!=null){
			    			content = UploadFileTool.getContent(content,addr.getAddr4_l4(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else{
			    		content = UploadFileTool.getContent(content,sb.toString(),length);
			    	}
			    break;
			    //户籍邮政编码
			    case 37:
			    	if(addr.getAddr1_type().equals("户籍地址")){
			    		if(addr.getPostcode1()!=null){
			    			content = UploadFileTool.getContent(content,addr.getPostcode1(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else   	if(addr.getAddr2_type().equals("户籍地址")){
			    		if(addr.getPostcode2()!=null){
			    			content = UploadFileTool.getContent(content,addr.getPostcode2(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else   	if(addr.getAddr3_type().equals("户籍地址")){
			    		if(addr.getPostcode3()!=null){
			    			content = UploadFileTool.getContent(content,addr.getPostcode3(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else   	if(addr.getAddr4_type().equals("户籍地址")){
			    		if(addr.getPostcode4()!=null){
			    			content = UploadFileTool.getContent(content,addr.getPostcode4(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else{
			    		content = UploadFileTool.getContent(content,sb.toString(),length);
			    	}
			    break;
			    //房产地址区段1
			    case 38:
			    	if(addr.getAddr1_type().equals("房产地址")){
			    		if(addr.getAddr1_l1()!=null){
			    			content = UploadFileTool.getContent(content,addr.getAddr1_l1(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else   	if(addr.getAddr2_type().equals("房产地址")){
			    		if(addr.getAddr2_l1()!=null){
			    			content = UploadFileTool.getContent(content,addr.getAddr2_l1(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else   	if(addr.getAddr3_type().equals("房产地址")){
			    		if(addr.getAddr3_l1()!=null){
			    			content = UploadFileTool.getContent(content,addr.getAddr3_l1(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else   	if(addr.getAddr4_type().equals("房产地址")){
			    		if(addr.getAddr4_l1()!=null){
			    			content = UploadFileTool.getContent(content,addr.getAddr4_l1(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else{
			    		content = UploadFileTool.getContent(content,sb.toString(),length);
			    	}
			    break;
			  //房产地址区段2
			    case 39:
			    	if(addr.getAddr1_type().equals("房产地址")){
			    		if(addr.getAddr1_l2()!=null){
			    			content = UploadFileTool.getContent(content,addr.getAddr1_l2(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else  	if(addr.getAddr2_type().equals("房产地址")){
			    		if(addr.getAddr2_l2()!=null){
			    			content = UploadFileTool.getContent(content,addr.getAddr2_l2(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else   	if(addr.getAddr3_type().equals("房产地址")){
			    		if(addr.getAddr3_l2()!=null){
			    			content = UploadFileTool.getContent(content,addr.getAddr3_l2(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else  	if(addr.getAddr4_type().equals("房产地址")){
			    		if(addr.getAddr4_l2()!=null){
			    			content = UploadFileTool.getContent(content,addr.getAddr4_l2(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else{
			    		content = UploadFileTool.getContent(content,sb.toString(),length);
			    	}
			    break;
			    //房产地址段3
			    case 40:
			    	if(addr.getAddr1_type().equals("房产地址")){
			    		if(addr.getAddr1_l3()!=null){
			    			content = UploadFileTool.getContent(content,addr.getAddr1_l3(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else   	if(addr.getAddr2_type().equals("房产地址")){
			    		if(addr.getAddr2_l3()!=null){
			    			content = UploadFileTool.getContent(content,addr.getAddr2_l3(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else   	if(addr.getAddr3_type().equals("房产地址")){
			    		if(addr.getAddr3_l3()!=null){
			    			content = UploadFileTool.getContent(content,addr.getAddr3_l3(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else  	if(addr.getAddr4_type().equals("房产地址")){
			    		if(addr.getAddr4_l3()!=null){
			    			content = UploadFileTool.getContent(content,addr.getAddr4_l3(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else{
			    		content = UploadFileTool.getContent(content,sb.toString(),length);
			    	}
			    break;
			    //房产地址段4
			    case 41:
			    	if(addr.getAddr1_type().equals("房产地址")){
			    		if(addr.getAddr1_l4()!=null){
			    			content = UploadFileTool.getContent(content,addr.getAddr1_l4(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else  	if(addr.getAddr2_type().equals("房产地址")){
			    		if(addr.getAddr2_l4()!=null){
			    			content = UploadFileTool.getContent(content,addr.getAddr2_l4(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else  	if(addr.getAddr3_type().equals("房产地址")){
			    		if(addr.getAddr3_l4()!=null){
			    			content = UploadFileTool.getContent(content,addr.getAddr3_l4(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else   	if(addr.getAddr4_type().equals("房产地址")){
			    		if(addr.getAddr4_l4()!=null){
			    			content = UploadFileTool.getContent(content,addr.getAddr4_l4(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else{
			    		content = UploadFileTool.getContent(content,sb.toString(),length);
			    	}
			    break;
			    //房产邮政编码
			    case 42:
			    	if(addr.getAddr1_type().equals("房产地址")){
			    		if(addr.getPostcode1()!=null){
			    			content = UploadFileTool.getContent(content,addr.getPostcode1(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else  	if(addr.getAddr2_type().equals("房产地址")){
			    		if(addr.getPostcode2()!=null){
			    			content = UploadFileTool.getContent(content,addr.getPostcode2(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else   	if(addr.getAddr3_type().equals("房产地址")){
			    		if(addr.getPostcode3()!=null){
			    			content = UploadFileTool.getContent(content,addr.getPostcode3(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else  	if(addr.getAddr4_type().equals("房产地址")){
			    		if(addr.getPostcode4()!=null){
			    			content = UploadFileTool.getContent(content,addr.getPostcode4(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else{
			    		content = UploadFileTool.getContent(content,sb.toString(),length);
			    	}
			    break;
			    case 43:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 44:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 45:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 46:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    //公司地段1
			    case 47:
			    	if(addr.getAddr1_type().equals("公司地址")){
			    		if(addr.getAddr1_l1()!=null){
			    			content = UploadFileTool.getContent(content,addr.getAddr1_l1(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else  	if(addr.getAddr2_type().equals("公司地址")){
			    		if(addr.getAddr2_l1()!=null){
			    			content = UploadFileTool.getContent(content,addr.getAddr2_l1(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else   	if(addr.getAddr3_type().equals("公司地址")){
			    		if(addr.getAddr3_l1()!=null){
			    			content = UploadFileTool.getContent(content,addr.getAddr3_l1(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else  	if(addr.getAddr4_type().equals("公司地址")){
			    		if(addr.getAddr4_l1()!=null){
			    			content = UploadFileTool.getContent(content,addr.getAddr4_l1(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else{
			    		content = UploadFileTool.getContent(content,sb.toString(),length);
			    	}
			    break;
			    case 48:
			    	if(addr.getAddr1_type().equals("公司地址")){
			    		if(addr.getAddr1_l2()!=null){
			    			content = UploadFileTool.getContent(content,addr.getAddr1_l2(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else  	if(addr.getAddr2_type().equals("公司地址")){
			    		if(addr.getAddr2_l2()!=null){
			    			content = UploadFileTool.getContent(content,addr.getAddr2_l2(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else   	if(addr.getAddr3_type().equals("公司地址")){
			    		if(addr.getAddr3_l3()!=null){
			    			content = UploadFileTool.getContent(content,addr.getAddr3_l3(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else  	if(addr.getAddr4_type().equals("公司地址")){
			    		if(addr.getAddr4_l4()!=null){
			    			content = UploadFileTool.getContent(content,addr.getAddr4_l4(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else{
			    		content = UploadFileTool.getContent(content,sb.toString(),length);
			    	}
			    break;
			    case 49:
			    	if(addr.getAddr1_type().equals("公司地址")){
			    		if(addr.getAddr1_l3()!=null){
			    			content = UploadFileTool.getContent(content,addr.getAddr1_l3(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else  	if(addr.getAddr2_type().equals("公司地址")){
			    		if(addr.getAddr2_l3()!=null){
			    			content = UploadFileTool.getContent(content,addr.getAddr2_l3(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else   	if(addr.getAddr3_type().equals("公司地址")){
			    		if(addr.getAddr3_l3()!=null){
			    			content = UploadFileTool.getContent(content,addr.getAddr3_l3(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else  	if(addr.getAddr4_type().equals("公司地址")){
			    		if(addr.getAddr4_l4()!=null){
			    			content = UploadFileTool.getContent(content,addr.getAddr4_l4(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else{
			    		content = UploadFileTool.getContent(content,sb.toString(),length);
			    	}
			    break;
			    case 50:
			    	if(addr.getAddr1_type().equals("公司地址")){
			    		if(addr.getAddr1_l4()!=null){
			    			content = UploadFileTool.getContent(content,addr.getAddr1_l4(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else  	if(addr.getAddr2_type().equals("公司地址")){
			    		if(addr.getAddr2_l4()!=null){
			    			content = UploadFileTool.getContent(content,addr.getAddr2_l4(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else   	if(addr.getAddr3_type().equals("公司地址")){
			    		if(addr.getAddr3_l4()!=null){
			    			content = UploadFileTool.getContent(content,addr.getAddr3_l4(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else  	if(addr.getAddr4_type().equals("公司地址")){
			    		if(addr.getAddr4_l4()!=null){
			    			content = UploadFileTool.getContent(content,addr.getAddr4_l4(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else{
			    		content = UploadFileTool.getContent(content,sb.toString(),length);
			    	}
			    break;
			    //公司邮政编码
			    case 51:
			    	if(addr.getAddr1_type().equals("公司地址")){
			    		if(addr.getPostcode1()!=null){
			    			content = UploadFileTool.getContent(content,addr.getPostcode1(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else  	if(addr.getAddr2_type().equals("公司地址")){
			    		if(addr.getPostcode2()!=null){
			    			content = UploadFileTool.getContent(content,addr.getPostcode2(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else   	if(addr.getAddr3_type().equals("公司地址")){
			    		if(addr.getPostcode3()!=null){
			    			content = UploadFileTool.getContent(content,addr.getPostcode3(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else  	if(addr.getAddr4_type().equals("公司地址")){
			    		if(addr.getPostcode4()!=null){
			    			content = UploadFileTool.getContent(content,addr.getPostcode4(),length);
			    		}else{
			    			content = UploadFileTool.getContent(content,sb.toString(),length);
			    		}
			    	}else{
			    		content = UploadFileTool.getContent(content,sb.toString(),length);
			    	}
			    break;
			    //公司中文全称
			    case 52:content = UploadFileTool.getContent(content,jczl.getComp_name(),length);break;
			    //公司部门
			    case 53:content = UploadFileTool.getContent(content,jczl.getEmply_dept(),length);break;
			    //公司职务
			    case 54:content = UploadFileTool.getContent(content,jczl.getPosn_emply(),length);break;
			    //公司行业类别
			    case 55:content = UploadFileTool.getContent(content,jczl.getOcc_code(),length);break;
			    case 56:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			  //公司性质
			    case 57:content = UploadFileTool.getContent(content,jczl.getOcc_catgry(),length);break;
			    //公司电话
			    case 58:content = UploadFileTool.getContent(content,jczl.getBusi_phone(),length);break;
			    //公司电话分机
			    case 59:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 60:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    //申请人年收入
			    case 61:content = UploadFileTool.getContent(content,jczl.getIncome_ann(),length);break;
			    case 62:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 63:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 64:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 65:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 66:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 67:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    //直系亲属姓名
			    case 68:content = UploadFileTool.getContent(content,zxqszl.getName(),length);break;
			    //直系亲属电话
			    case 69:content = UploadFileTool.getContent(content,zxqszl.getTelno(),length);break;
			    //直系亲属关系
			    case 70:content = UploadFileTool.getContent(content,zxqszl.getRel(),length);break;
			    //直系亲属手机
			    case 71:content = UploadFileTool.getContent(content,zxqszl.getMobile(),length);break;
			    //联系人
			    case 72:content = UploadFileTool.getContent(content,lxr.get(0).getName(),length);break;
			    case 73:content = UploadFileTool.getContent(content,lxr.get(0).getTelno(),length);break;
			    case 74:content = UploadFileTool.getContent(content,lxr.get(0).getRel(),length);break;
			    case 75:content = UploadFileTool.getContent(content,lxr.get(0).getCompnm(),length);break;
			    case 76:content = UploadFileTool.getContent(content,lxr.get(0).getMobile(),length);break;
			    case 77:content = UploadFileTool.getContent(content,lxr.get(1).getName(),length);break;
			    case 78:content = UploadFileTool.getContent(content,lxr.get(1).getTelno(),length);break;
			    case 79:content = UploadFileTool.getContent(content,lxr.get(1).getRel(),length);break;
			    case 80:content = UploadFileTool.getContent(content,lxr.get(1).getCompnm(),length);break;
			    case 81:content = UploadFileTool.getContent(content,lxr.get(1).getMobile(),length);break;
			    //配偶信息
			    case 82:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 83:content = UploadFileTool.getContent(content,pozl.getCustr_nbr(),length);break;
			    case 84:content = UploadFileTool.getContent(content,pozl.getName(),length);break;
			    case 85:content = UploadFileTool.getContent(content,pozl.getMobile(),length);break;
			    case 86:content = UploadFileTool.getContent(content,pozl.getCompnm(),length);break;
			    case 87:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 88:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 89:content = UploadFileTool.getContent(content,pozl.getTelno(),length);break;
			    case 90:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 91:content = UploadFileTool.getContent(content,pozl.getIncome_ann(),length);break;
			    //附卡信息
			    case 92:content = UploadFileTool.getContent(content,"01",length);break;
			    case 93:content = UploadFileTool.getContent(content,kpmx.get(1).getCustr_nbr(),length);break;
			    case 94:content = UploadFileTool.getContent(content,kpmx.get(1).getEmbname_d(),length);break;
			    case 95:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 96:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 97:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 98:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 99:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 100:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 101:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 102:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 103:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 104:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 105:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 106:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 107:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 108:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 109:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 110:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 111:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 112:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 113:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 114:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 115:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 116:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 117:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 118:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 119:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 120:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 121:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 122:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 123:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 124:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 125:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 126:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 127:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 128:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 129:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 130:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 131:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 132:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 133:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 134:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 135:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 136:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 137:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 138:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 139:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 140:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 141:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 142:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 143:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 144:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 145:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 146:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 147:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 148:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 149:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 150:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 151:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 152:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 153:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 154:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 155:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 156:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 157:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 158:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 159:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 160:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 161:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 162:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 163:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 164:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 165:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 166:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 167:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 168:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 169:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 170:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 171:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 172:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 173:content = UploadFileTool.getContent(content,appln.getCycle_nbr(),length);break;
			    case 174:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 175:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    //主卡英文
			    case 176:content = UploadFileTool.getContent(content,kpmx.get(0).getEmbname_d(),length);break;
			    case 177:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    //是否需要密码
			    case 178:content = UploadFileTool.getContent(content,kpmx.get(0).getPin_chk(),length);break;
			    //是否短信通知
			    case 179:content = UploadFileTool.getContent(content,kpmx.get(0).getSms_yn(),length);break;
			    case 180:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 181:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 182:
			    	content = UploadFileTool.getContent(content,kpmx.get(0).getCdespmtd(),length);break;
			    case 183:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 184:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    //主卡暗语
			    case 185:content = UploadFileTool.getContent(content,kpmx.get(0).getSpec_inst(),length);break;
			    case 186:content = UploadFileTool.getContent(content,kpmx.get(1).getSpec_inst(),length);break;
			    //征信确认
			    case 187:content = UploadFileTool.getContent(content,"1",length);break;
			    case 188:content = UploadFileTool.getContent(content,"1",length);break;
			    case 189:content = UploadFileTool.getContent(content,"1",length);break;
			    case 190:content = UploadFileTool.getContent(content,"1",length);break;
			    case 191:content = UploadFileTool.getContent(content,"1",length);break;
			    case 192:content = UploadFileTool.getContent(content,"1",length);break;
			    case 193:content = UploadFileTool.getContent(content,"1",length);break;
			    case 194:content = UploadFileTool.getContent(content,"1",length);break;
			    case 195:content = UploadFileTool.getContent(content,"1",length);break;
			    case 196:content = UploadFileTool.getContent(content,"1",length);break;
			    case 197:content = UploadFileTool.getContent(content,"1",length);break;
			    case 198:content = UploadFileTool.getContent(content,"1",length);break;
			    
			    case 199:content = UploadFileTool.getContent(content,"A",length);break;
			    case 200:content = UploadFileTool.getContent(content,"10",length);break;
			    case 201:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 202:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 203:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    //递送方式
			    case 204:content = UploadFileTool.getContent(content,"COUR",length);break;
			    case 205:content = UploadFileTool.getContent(content,kpmx.get(0).getCourierf(),length);break;
			    case 206:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    //主卡卡面版本
			    case 207:content = UploadFileTool.getContent(content,kpmx.get(0).getCdfrm(),length);break;
			    case 208:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 209:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 210:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 211:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 212:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 213:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 214:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 215:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 216:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 217:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 218:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 219:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 220:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 221:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 222:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 223:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 224:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 225:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 226:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 227:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 228:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 229:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 230:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 231:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    //是否快速发卡
			    case 232:content = UploadFileTool.getContent(content,appln.getRush_card(),length);break;
			    //是否收取发卡费用
			    case 233:content = UploadFileTool.getContent(content,appln.getRush_fee(),length);break;
			    case 234:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 235:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 236:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 237:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 238:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 239:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 240:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 241:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 242:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 243:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 244:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 245:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 246:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 247:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 248:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 249:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 250:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 251:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 252:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 253:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 254:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 255:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 256:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 257:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 258:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 259:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 260:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 261:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 262:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    //推荐人代号
			    case 263:content = UploadFileTool.getContent(content,tjinfo.getIntr_nbr(),length);break;
			    case 264:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 265:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 266:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 267:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 268:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    //利率代码
			    case 269:content = UploadFileTool.getContent(content,appln.getInt_code(),length);break;
			    case 270:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 271:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 272:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 273:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 274:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 275:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 276:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 277:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 278:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 279:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 280:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 281:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 282:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 283:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 284:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 285:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 286:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 287:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 288:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 289:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 290:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 291:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 292:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 293:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 294:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 295:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 296:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 297:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 298:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 299:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 300:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 301:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 302:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 303:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 304:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 305:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 306:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 307:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 308:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 309:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 310:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 311:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 312:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 313:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 314:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 315:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 316:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 317:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 318:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 319:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 320:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 321:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 322:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 323:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 324:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 325:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 326:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 327:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 328:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 329:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 330:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 331:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 332:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 333:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 334:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 335:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 336:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 337:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 338:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 339:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 340:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 341:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 342:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 343:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 344:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 345:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 346:content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 347:
			    	content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    case 348:
			    	content = UploadFileTool.getContent(content,sb.toString(),length);break;
			    default:break;
			}
		}
		//生成文件名
		DateFormat format2 = new SimpleDateFormat("yyyyMMdd");
		String nowDate  = format2.format(new Date());
		String fileName = CustomerInforConstant.BANK_ID+"-APPLN-";
		String nowCount = intoPiecesDao.nowCount(nowDate)+1+"";
		for(int i=6;i>nowCount.length();i--){
			fileName+="0";
		}
		fileName+=nowCount+"-"+nowDate;
		/*生成的接口数据上传到ftp文件上*/

//		UploadFileTool.uploadFileToFtp(Constant.FTPIP, Integer.valueOf(Constant.FTPPORT), Constant.FTPUSERNAME, Constant.FTPPASSWORD, Constant.FTPPATH, fileName, content.toString());
		
		
		UploadFileTool.create(fileName,content.toString());
		/*如果要下载接口数据,将下面的exportTextFile方法打开*/
		/*if(response!=null){
			UploadFileTool.exportTextFile(response, content.toString(), fileName);
		}*/
		/*上传成功后讲进件信息主表的状态置为已上传状态*/
		if(customerApplicationInfo!=null&&StringUtils.trimToNull(customerApplicationInfo.getId())!=null){
			/*更新上传状态为防止影响到别的属性，这里new一个新实例*/
			CustomerApplicationInfo temp = new CustomerApplicationInfo();
			temp.setId(customerApplicationInfo.getId());
			temp.setUploadStatus(Constant.UPLOAD_INTOPICES);
			commonDao.updateObject(temp);
		}
		return uuid19;
	}

	/* 根据客户id查询客户职业资料 */
	public CustomerCareersInformation findCustomerCareersInformationByCustomerId(
			String customerId) {
		return intoPiecesComdao
				.findCustomerCareersInformationByCustomerId(customerId);
	}

	/* 根据id查询联系人资料 */
	public List<CustomerApplicationContact> findCustomerApplicationContactsByApplicationId(
			String applicationId) {
		return intoPiecesComdao
				.findCustomerApplicationContactsByApplicationId(applicationId);
	}

	/* 根据id查询推荐人资料 */
	public List<CustomerApplicationRecom> findCustomerApplicationRecomsByApplicationId(
			String applicationId) {
		return intoPiecesComdao
				.findCustomerApplicationRecomsByApplicationId(applicationId);
	}

	/* 根据id查询担保人资料 */
	public List<CustomerApplicationGuarantor> findCustomerApplicationGuarantorsByApplicationId(
			String applicationId) {
		return intoPiecesComdao
				.findCustomerApplicationGuarantorsByApplicationId(applicationId);
	}

	/* 根据id查询申请的主表信息 */
	public CustomerApplicationInfo findCustomerApplicationInfoByApplicationId(
			String applicationId) {
		return intoPiecesComdao
				.findCustomerApplicationInfoByApplicationId(applicationId);
	}

	/* 根据id查询申请主表信息 的其他资料信息 */
	public CustomerApplicationOther findCustomerApplicationOtherByApplicationId(
			String applicationId) {
		return intoPiecesComdao
				.findCustomerApplicationOtherByApplicationId(applicationId);
	}

	/* 根据id查询申请主表信息 的行社专栏信息 */
	public CustomerApplicationCom findCustomerApplicationComByApplicationId(
			String applicationId) {
		return intoPiecesComdao
				.findCustomerApplicationComByApplicationId(applicationId);
	}
	
	/* 根据id查询客户账户信息 */
	public CustomerAccountData findCustomerAccountDataByApplicationId(
			String applicationId) {
		return intoPiecesComdao
				.findCustomerAccountDataByApplicationId(applicationId);
	}
	

	/* 根据客户id查询客户申请主表信息 的影像资料信息 */
	public VideoAccessories findVideoAccessoriesByCustomerId(String customerId) {
		return intoPiecesComdao.findVideoAccessoriesByCustomerId(customerId);
	}

	/* 校验客户是否存在，如果存在这里需要更新客户信息 */
	public CustomerInfor findCustomerInforById(String id) {
		return intoPiecesComdao.findCustomerInforById(id);
	}

	/* 校验职业信息是否存在，如果存在这里需要更新该客户信息 */
	public CustomerCareersInformation findCustomerCareersInformationById(
			String id) {
		return intoPiecesComdao.findCustomerCareersInformationById(id);
	}

	/* 信息主表信息是否存在，如果存在则更新信息 */
	public CustomerApplicationInfo findCustomerApplicationInfoById(String id) {
		return intoPiecesComdao.findCustomerApplicationInfoById(id);
	}

	/* 查询申请的某一笔进件申请单中上传的产品的附件 */
	public List<AddressAccessories> findAddressAccessories(
			String applicationId, String productId) {
		return intoPiecesComdao
				.findAddressAccessories(applicationId, productId);
	}
	
	/* 查找接口字段配置表 */
	public List<ApplicationDataImport> findApplicationDataImport() {
		return intoPiecesComdao.findApplicationDataImport();
	}
	
	
	/* 查找接口字段配置表 */
	public void saveCard(MakeCard makeCard) {
		commonDao.insertObject(makeCard);
	}
	
	/* 卡片下发 */
	public void organizationIssuedCard(String id,String cardOrganizationStatus,String cardCustomerStatus) {
		MakeCard makeCard = new MakeCard();
		makeCard.setId(id);
		if(StringUtils.trimToNull(cardOrganizationStatus)!=null){
			makeCard.setCardOrganizationStatus(cardOrganizationStatus);
		}
		if(StringUtils.trimToNull(cardCustomerStatus)!=null){
			makeCard.setCardCustomerStatus(cardCustomerStatus);
		}
		commonDao.updateObject(makeCard);
	}
	
	/* 查看卡片信息 */
	public MakeCard findMakeCardById(String id) {
		return commonDao.findObjectById(MakeCard.class, id);
	}
	
	/**
	 * 
	 * @param id
	 * @param dataType application 进件 amountadjustment 调额信息
	 * @return
	 */
	public List<ApproveHistoryForm> findApplicationDataImport(String id, String dataType) {
		return intoPiecesComdao.findApproveHistoryByDataId(id, dataType);
	}
	
	public boolean checkApplyQuota(float applyQuota,String userId,String productId){
		boolean flag = false;
		Float floatDb = intoPiecesComdao.checkApplyQuota(userId, productId);
		if(floatDb!=null){
			if(applyQuota<=floatDb.floatValue()){
				flag = true;
			}
		}
		return flag;
	}
	
	
	public QueryResult<Dict> getDict(String dictType){
		DictFilter filter = new DictFilter();
    	filter.setTypeCode(dictType);
    	QueryResult<Dict> dict= commonDao.findObjectsByFilter(Dict.class, filter);
    	return dict;
	}
	
	/**
	 * 判断是否风险用户
	 * @param cardId
	 * @return
	 */
	public Boolean checkRisk(String cardId){
		RiskCustomerFilter filter = new RiskCustomerFilter();
		filter.setCardId(cardId);
		QueryResult<RiskCustomer> risk = commonDao.findObjectsByFilter(RiskCustomer.class, filter);
		if(risk.getItems().size()>0){
			return false;
		}
		return true;
	}
	/**
	 * 判断是否黑名单用户
	 * @param cardId
	 * @return
	 */
	public Boolean checkblack(String cardId){
		AgrCrdXykCunegFilter filter = new AgrCrdXykCunegFilter();
		filter.setCustrNbr(cardId);
		QueryResult<AgrCrdXykCuneg> black = commonDao.findObjectsByFilter(AgrCrdXykCuneg.class, filter);
		if(black.getItems().size()>0){
			return false;
		}
		return true;
	}
	
	/*
	 * 添加三性检测，并进入下一节点录入
	 */
	public void addSxjc(XmApplnSxjcForm filter,HttpServletRequest request) throws Exception{
		XM_APPLN_SXJC sxjc = new XM_APPLN_SXJC();
		sxjc.setApplication_id(filter.getApplicationId());
		sxjc.setReality(filter.getReality());
		sxjc.setComplete(filter.getComplete());
		sxjc.setStandard(filter.getStandard());
		commonDao.insertObject(sxjc);
		CustomerApplicationProcess process =  customerApplicationProcessService.findByAppId(filter.getApplicationId());
		process.setIfRecieved(Constant.recieve_type);
		commonDao.updateObject(process);
		request.setAttribute("serialNumber", process.getSerialNumber());
		request.setAttribute("applicationId", process.getApplicationId());
		request.setAttribute("applicationStatus", ApplicationStatusEnum.APPROVE);
		request.setAttribute("objection", "false");
		request.setAttribute("examineAmount", "");
		customerApplicationIntopieceWaitService.updateCustomerApplicationProcessBySerialNumberApplicationInfo1(request);
	}
	
	/*
	 * 复核退回到录入
	 */
	public void refuse(String applicationId,HttpServletRequest request) throws Exception{
		IUser user = Beans.get(LoginManager.class).getLoggedInUser(request);
		String loginId = user.getId();
		//更新申请表
		CustomerApplicationInfo customerApplicationInfo = new CustomerApplicationInfo();
		customerApplicationInfo.setStatus(Constant.APPROVE_INTOPICES);
		customerApplicationInfo.setId(applicationId);
		customerApplicationInfo.setModifiedBy(user.getId());
		customerApplicationInfo.setModifiedTime(new Date());
		commonDao.updateObject(customerApplicationInfo);
		
		//通过申请表ID获取流程表
		CustomerApplicationProcess process =  customerApplicationProcessService.findByAppId(applicationId);
		//通过流程表的当前节点获取上一节点
		NodeControl nodeControl = customerApplicationProcessService.getLastStatus(process.getNextNodeId());
		//更新业务流程表
		process.setNextNodeId(nodeControl.getCurrentNode());
		process.setAuditUser(loginId);
		process.setFuheUser(null);
		process.setCreatedTime(new Date());
	    customerApplicationIntopieceWaitDao.updateCustomerApplicationProcessBySerialNumber(process);
	    
	    //更新流程备份表
	    
	   //查找当前所处流转状态
  		WfProcessRecord wfProcessRecord = commonDao.findObjectById(WfProcessRecord.class, process.getSerialNumber());
		WfStatusQueueRecord wfStatusQueueRecord = commonDao.findObjectById(WfStatusQueueRecord.class,wfProcessRecord.getWfStatusQueueRecord());
		//查找上一节点
		String beforeStatus = wfStatusQueueRecord.getBeforeStatus();
		//通过上一节点获取上一流程
		WfStatusQueueRecord befoRecord = wfStatusResultDao.getLastStatus(beforeStatus);
		
		wfProcessRecord.setWfStatusQueueRecord(befoRecord.getId());
		commonDao.updateObject(wfProcessRecord);
	}
	
	/* 查询制卡结果信息 */
	/*
	 * TODO 1.添加注释 2.SQL写进DAO层
	 */
	public QueryResult<IntoPiecesCardQueryFilter> findintoPiecesCardQueryByFilter(
			IntoPiecesCardQueryFilter filter) {
		List<IntoPiecesCardQueryFilter> list = intoPiecesDao.cardQuery(filter);
		int size = intoPiecesDao.cardQueryCount(filter);
		QueryResult<IntoPiecesCardQueryFilter> qs = new QueryResult<IntoPiecesCardQueryFilter>(size, list);
		return qs;
	}
	
	/* 接收节点退回进件 */
	/*
	 * TODO 1.添加注释 2.SQL写进DAO层
	 */
	public void checkDoNot(XmApplnSxjcForm filter) throws Exception{
		//获取进件信息
		CustomerApplicationInfo applicationInfo= commonDao.findObjectById(CustomerApplicationInfo.class, filter.getApplicationId());
		//获取客户信息
		CustomerInfor infor = commonDao.findObjectById(CustomerInfor.class, applicationInfo.getCustomerId());
		//删除进件信息
		commonDao.deleteObject(CustomerApplicationInfo.class, filter.getApplicationId());
		//更新客户信息--退回
		infor.setProcessId("1");
		commonDao.updateObject(infor);
	}
	
	/**
	 * 定时解析FTP获取的文件
	 */
	public void importDataFromBank(){
        InputStreamReader inputReader = null;
        BufferedReader bufferReader = null;
        try
        {
            InputStream inputStream = new FileInputStream("e://6517-APPRESULT-000008-20150127");
            inputReader = new InputStreamReader(inputStream);
            bufferReader = new BufferedReader(inputReader);
            
            // 读取一行
            String line = null;
            StringBuffer strBuffer = new StringBuffer();
                
            while ((line = bufferReader.readLine()) != null)
            {
                strBuffer.append(line);
            } 
            System.out.println(strBuffer);
        	bufferReader.close();
        	inputReader.close();
        	//将制卡数据存入data
        	inputData(strBuffer);
        }
        catch (IOException e)
        {
        	e.printStackTrace();
        }
	}
	
	private void inputData(StringBuffer str){
		IntoPiecesCardQueryFilter cardQueryFilter = new IntoPiecesCardQueryFilter();
		//银行id
		String bankId = str.substring(0, 3);
		//发卡系统申请书编号
		String makeCardId = str.substring(23, 32);
		//申请人证件类型
		String cardType = str.substring(33, 34);
		//申请人证件号码
		String cardId = str.substring(35, 52);
		//处理结果
		String resultType = str.substring(53, 54);
		String resultText = "";
		if(resultType.equals("00")){
			resultText = Constant.CARD_RETURN_TYPE1;
		}else if(resultType.equals("10")){
			resultText = Constant.CARD_RETURN_TYPE2;
		}else{
			resultText = Constant.CARD_RETURN_TYPE3;
		}
		
		cardQueryFilter.setCardId(cardId);
		cardQueryFilter.setMakeCardId(null);
		QueryResult<IntoPiecesCardQuery> cardList = commonDao.findObjectsByFilter(IntoPiecesCardQuery.class, cardQueryFilter);
		if(cardList.getItems().size()>0){
			IntoPiecesCardQuery card = cardList.getItems().get(0);
			card.setMakeCardId(makeCardId);
			card.setResultType(resultType);
			card.setResultText(resultText);
			commonDao.updateObject(card);
		}
	} 
}
