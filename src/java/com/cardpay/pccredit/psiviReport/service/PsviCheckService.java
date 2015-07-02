package com.cardpay.pccredit.psiviReport.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cardpay.pccredit.pbccrcReport.util.PbccrcReport;
import com.cardpay.pccredit.psiviReport.constant.Constants;
import com.cardpay.pccredit.psiviReport.dao.IdCheck_INFO_Dao;
import com.cardpay.pccredit.psiviReport.model.ID_CHECK_INFO;
import com.jcraft.jsch.Logger;
import com.wicresoft.jrad.base.database.dao.common.CommonDao;



/**
 * 公安身份核信服务类
 * @author chenzhifang
 *
 * 2015-6-16上午15:45:51
 */
@Service
public class PsviCheckService {
	
	@Autowired
	private CommonDao commonDao;
	
	@Autowired
	private IdCheck_INFO_Dao idcheck_info_dao;
	
	private List<Element> content_Element_List = null;
	
	/**
	 * 保存核查信息
	 * @param customerId
	 * @param url
	 */
	public void insertOrUpdateIC(Map resultMap){
		//解析map
		String identityNo = (String)resultMap.get("ID1");
		String customerName = (String)resultMap.get("Name1");
		String checkResult = (String)resultMap.get("CheckResult1");
		String issueOffice = (String)resultMap.get("IssueOffice1");
		String photo = (String)resultMap.get("Photo1");
		System.out.println(identityNo +","+customerName + "," + checkResult + "," + issueOffice );
		ID_CHECK_INFO idcheckinfo = this.idcheck_info_dao.findByCustomerId(identityNo);
		if(idcheckinfo == null){
			//idcheckinfo.setCustomer_id(customerId);
			idcheckinfo = new ID_CHECK_INFO();
			idcheckinfo.setCard_name(customerName);
			idcheckinfo.setCard_checkResult(checkResult);
			idcheckinfo.setCard_office(issueOffice);
			idcheckinfo.setCard_no(identityNo);
			this.commonDao.insertObject(idcheckinfo);
		}else{
			idcheckinfo.setCard_checkResult(checkResult);
			this.commonDao.updateObject(idcheckinfo);
		}
	}
	
}
