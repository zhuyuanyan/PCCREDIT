package com.cardpay.pccredit.pbccrcReport.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cardpay.pccredit.pbccrcReport.constant.Constants;
import com.cardpay.pccredit.pbccrcReport.dao.RH_DWDB_INFO_Dao;
import com.cardpay.pccredit.pbccrcReport.dao.RH_INFO_Dao;
import com.cardpay.pccredit.pbccrcReport.dao.RH_JZ_INFO_Dao;
import com.cardpay.pccredit.pbccrcReport.dao.RH_PO_INFO_Dao;
import com.cardpay.pccredit.pbccrcReport.dao.RH_QUERY_INFO_Dao;
import com.cardpay.pccredit.pbccrcReport.dao.RH_WJQDK_INFO_Dao;
import com.cardpay.pccredit.pbccrcReport.dao.RH_WXHDJK_INFO_Dao;
import com.cardpay.pccredit.pbccrcReport.dao.RH_XYTS_INFO_Dao;
import com.cardpay.pccredit.pbccrcReport.dao.RH_YH_INFO_Dao;
import com.cardpay.pccredit.pbccrcReport.dao.RH_YQ_INFO_Dao;
import com.cardpay.pccredit.pbccrcReport.dao.RH_ZY_INFO_Dao;
import com.cardpay.pccredit.pbccrcReport.model.RH_DWDB_INFO;
import com.cardpay.pccredit.pbccrcReport.model.RH_INFO;
import com.cardpay.pccredit.pbccrcReport.model.RH_JZ_INFO;
import com.cardpay.pccredit.pbccrcReport.model.RH_PO_INFO;
import com.cardpay.pccredit.pbccrcReport.model.RH_QUERY_INFO;
import com.cardpay.pccredit.pbccrcReport.model.RH_WJQDK_INFO;
import com.cardpay.pccredit.pbccrcReport.model.RH_WXHDJK_INFO;
import com.cardpay.pccredit.pbccrcReport.model.RH_XYTS_INFO;
import com.cardpay.pccredit.pbccrcReport.model.RH_YH_INFO;
import com.cardpay.pccredit.pbccrcReport.model.RH_YQ_INFO;
import com.cardpay.pccredit.pbccrcReport.model.RH_ZY_INFO;
import com.cardpay.pccredit.sample2.filter.Sample2Filter;
import com.cardpay.pccredit.sample2.model.Sample2;
import com.wicresoft.jrad.base.database.dao.common.CommonDao;
import com.wicresoft.jrad.base.database.id.IDGenerator;
import com.wicresoft.jrad.base.database.model.QueryResult;

/**
 * 人行征信服务类
 * @author chenzhifang
 *
 * 2014-12-24上午9:45:51
 */
@Service
public class RhzxService {
	
	@Autowired
	private CommonDao commonDao;
	
	@Autowired
	private RH_DWDB_INFO_Dao rH_DWDB_INFO_Dao;
	@Autowired
	private RH_INFO_Dao rH_INFO_Dao;
	@Autowired
	private RH_JZ_INFO_Dao rH_JZ_INFO_Dao;
	@Autowired
	private RH_PO_INFO_Dao rH_PO_INFO_Dao;
	@Autowired
	private RH_QUERY_INFO_Dao rH_QUERY_INFO_Dao;
	@Autowired
	private RH_WJQDK_INFO_Dao rH_WJQDK_INFO_Dao;
	@Autowired
	private RH_WXHDJK_INFO_Dao rH_WXHDJK_INFO_Dao;
	@Autowired
	private RH_XYTS_INFO_Dao rH_XYTS_INFO_Dao;
	@Autowired
	private RH_YH_INFO_Dao rH_YH_INFO_Dao;
	@Autowired
	private RH_YQ_INFO_Dao rH_YQ_INFO_Dao;
	@Autowired
	private RH_ZY_INFO_Dao rH_ZY_INFO_Dao;
	
	/**   
	* @Title: readUrl    
	* @Description: 加载html文件   
	* @param url
	* @return List<Element>
	* @throws SQLException 
	*/
	private List<Element> content_Element_List = null;
	
	public void readUrl(String url){
		Source source=null;
		try
		{
			source=new Source(new URL(url));
		}
		catch(MalformedURLException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		List<Element> elementList=source.getAllElements(HTMLElementName.TABLE);
		Element content_Element = elementList.get(Constants.PBOC_CONTENT_TABLEINDEX);
		content_Element_List = content_Element.getAllElements(HTMLElementName.TABLE);
	}

	/**   
	* @Title: readRH    
	* @Description: 读取征信信息 返回是否满足通过条件
	* @param url
	* @return boolean
	*/
	public void readRH(RH_INFO rh_info){
		float dqyqje = 0f;//当前逾期金额
		for(Element ele : content_Element_List){
			//读取当前逾期金额
			if(ele.getContent().toString().indexOf("当前逾期金额")>0){
				Element trEle = ele.getAllElements(HTMLElementName.TR).get(3);
				int tdCount = trEle.getAllElements(HTMLElementName.TD).size();
				if(tdCount == 6){//贷款
					dqyqje += Float.parseFloat(trEle.getAllElements(HTMLElementName.TD).get(1).getTextExtractor().toString().replace(",", ""));
				}
				if(tdCount == 5){//贷记卡
					dqyqje += Float.parseFloat(trEle.getAllElements(HTMLElementName.TD).get(4).getTextExtractor().toString().replace(",", ""));
				}
			}
			rh_info.setDQYQJE(String.valueOf(dqyqje));
			//读取近两年逾期期数
			
		}
	}
	
	/**   
	* @Title: readYH    
	* @Description: 读取用户信息
	* @param url
	* @return YH_Info
	*/
	public RH_YH_INFO readYH(RH_YH_INFO yh_Info)
	{
		//List<Element> content_Element_List = readUrl(url);
		
		/** start **/
		//RH_YH_INFO yh_Info = new RH_YH_INFO();
		Element yh_Element = content_Element_List.get(Constants.PBOC_YH_INFO_TABLEINDEX).getAllElements(HTMLElementName.TR).get(1);
		/*<td  class="tdStyle" width="20%"> 
	        <div class=high align=center><font color=#0066cc><span class=high>蒋建峰</span></font></div>
	      </td>
	      <td  class="tdStyle" width="20%">
	        <div class=high align=center><font color=#0066cc><span class=high>身份证</span></font></div>
	      </td>
	      <td  class="tdStyle" width="20%">
	        <div class=high align=center><font color=#0066cc><font color=#0066cc>320421197803130918</font></font></div>
	      </td>
	      <td  class="tdStyle" width="20%">
	        <div class=high align=center><font color=#0066cc><span class=high>江苏江南农村商业银行股份有限公司/jnnshgr253</span></font></div>
	      </td>
	      <td  class="tdStyle" width="20%">
	        <div class=high align=center><font color=#0066cc><span class=high>信用卡审批</span></font></div>
	      </td>*/
		List<Element> yh_Info_List = yh_Element.getAllElements(HTMLElementName.TD);
		yh_Info.setUserName(yh_Info_List.get(0).getTextExtractor().toString());
		yh_Info.setCreditType(yh_Info_List.get(1).getTextExtractor().toString());
		yh_Info.setCreditNO(yh_Info_List.get(2).getTextExtractor().toString());
		yh_Info.setQueryOperator(yh_Info_List.get(3).getTextExtractor().toString());
		yh_Info.setQueryReason(yh_Info_List.get(4).getTextExtractor().toString());
		/** end **/
		
		/** start **/
		yh_Element = content_Element_List.get(Constants.PBOC_YH_OTHER_INFO_TABLEINDEX).getAllElements(HTMLElementName.TR).get(1);
		/*<td   style="word-break:break-all" class="tdStyle" > 
	        <div  align=center><font color=#0066cc><span class=high>男性</span></font></div>
	      </td>
	      <td  class="tdStyle" style="word-break:break-all"> 
	        <div  align=center><font color=#0066cc><font color=#0066cc>1978.03.13</font></font></div>
	      </td>
	      <TD  class="tdStyle" style="word-break:break-all"> 
	        <DIV  align=center><FONT color=#0066cc><font color=#0066cc>未婚</font></FONT></DIV>
	      </TD>
	      <td class="tdStyle" style="word-break:break-all"> 
	        <div  align=center><font color=#0066cc><span class=high>15051982796</span></font></div>
	      </td>
	      <td class="tdStyle"  style="word-break:break-all"> 
	        <div  align=center><font color=#0066cc><span class=high>051986555306</span></font></div>
	      </td>
	      <td  class="tdStyle" style="word-break:break-all"> 
	        <div  align=center><font color=#0066cc><span class=high>051986555306</span></font></div>
	      </td>
	      <td  class="tdStyle" style="word-break:break-all"> 
	        <div  align=center><font color=#0066cc><span class=high>初中</span></font></div>
	      </td>
	      <td class="tdStyle" style="word-break:break-all"> 
	        <div  align=center><font color=#0066cc><span class=high>其他</span></font></div>
	      </td>*/
		List<Element> yh_Other_Info_List_1 = yh_Element.getAllElements(HTMLElementName.TD);
		yh_Info.setSex(yh_Other_Info_List_1.get(0).getTextExtractor().toString());
		yh_Info.setBirth(yh_Other_Info_List_1.get(1).getTextExtractor().toString());
		yh_Info.setMarriage(yh_Other_Info_List_1.get(2).getTextExtractor().toString());
		yh_Info.setCellPhone(yh_Other_Info_List_1.get(3).getTextExtractor().toString());
		yh_Info.setWorkPhone(yh_Other_Info_List_1.get(4).getTextExtractor().toString());
		yh_Info.setHomePhone(yh_Other_Info_List_1.get(4).getTextExtractor().toString());
		yh_Info.setEducation(yh_Other_Info_List_1.get(4).getTextExtractor().toString());
		yh_Info.setDegree(yh_Other_Info_List_1.get(4).getTextExtractor().toString());
		/** end **/
		
		/** start **/
		yh_Element = content_Element_List.get(Constants.PBOC_YH_OTHER_INFO_TABLEINDEX).getAllElements(HTMLElementName.TR).get(3);
		/*<td  class="tdStyle" style="word-break:break-all" colspan=3> 
            <div  align=center><font color=#0066cc><span class=high>江苏省常州市武进区湖塘镇周家巷村委杨树园33号</span></font></div>
          </td>
          <td class="tdStyle" style="word-break:break-all" colspan=5> 
            <div  align=center><font color=#0066cc><span class=high>江苏省武进区</span></font></div>
          </td>*/
		List<Element> yh_Other_Info_List_2 = yh_Element.getAllElements(HTMLElementName.TD);
		yh_Info.setMailingAddress(yh_Other_Info_List_2.get(0).getTextExtractor().toString());
		yh_Info.setResidenceAddress(yh_Other_Info_List_2.get(1).getTextExtractor().toString());
		/** end **/
		
		return yh_Info;
	}
	
	/**   
	* @Title: readPO    
	* @Description: 读取配偶信息    
	* @param url
	* @return PO_Info
	*/
	public RH_PO_INFO readPO(RH_PO_INFO po_Info)
	{
		//List<Element> content_Element_List = readUrl(url);
		
		/** start **/
		//RH_PO_INFO po_Info = new RH_PO_INFO();
		Element po_Element = content_Element_List.get(Constants.PBOC_PO_INFO_TABLEINDEX).getAllElements(HTMLElementName.TR).get(1);
		/*<TD  class="tdStyle" style="word-break:break-all" colspan=2> 
            <DIV  align=center><FONT color=#0066cc><SPAN class=high>--</SPAN></FONT></DIV>
          </TD>
          <TD class="tdStyle"  style="word-break:break-all" colspan=1> 
            <DIV  align=center><FONT color=#0066cc><SPAN class=high>--</SPAN></FONT></DIV>
          </TD>
          <TD class="tdStyle" style="word-break:break-all" colspan=1> 
            <DIV  align=center><FONT color=#0066cc><SPAN class=high>--</SPAN></FONT></DIV>
          </TD>
          <TD class="tdStyle"  style="word-break:break-all" colspan=3> 
            <DIV  align=center><FONT color=#0066cc><SPAN class=high>--</SPAN></FONT></DIV>
          </TD>
          <TD class="tdStyle"  style="word-break:break-all" colspan=1> 
            <DIV  align=center><FONT color=#0066cc><SPAN class=high>--</SPAN></FONT></DIV>
          </TD>*/
		List<Element> po_Info_List = po_Element.getAllElements(HTMLElementName.TD);
		po_Info.setName(po_Info_List.get(0).getTextExtractor().toString());
		po_Info.setCreditType(po_Info_List.get(0).getTextExtractor().toString());
		po_Info.setCreditNO(po_Info_List.get(0).getTextExtractor().toString());
		po_Info.setWorkUnit(po_Info_List.get(0).getTextExtractor().toString());
		po_Info.setPhone(po_Info_List.get(0).getTextExtractor().toString());
		return po_Info;
		/** end **/
	}
	
	/**   
	* @Title: readJZ    
	* @Description: 读取居住信息    
	* @param url
	* @return List<JZ_Info>
	*/
	public List<RH_JZ_INFO> readJZ()
	{
		//List<Element> content_Element_List = readUrl(url);
		/** start **/
		List<RH_JZ_INFO> jz_Info_List = new ArrayList<RH_JZ_INFO>();
		List<Element> jz_Element_List = content_Element_List.get(Constants.PBOC_JZ_INFO_TABLEINDEX).getAllElements(HTMLElementName.TR);
		jz_Element_List.remove(0);
		/*<td width="6%"  style="word-break:break-all" class="tdStyle"> 
              <div class=high align=center><font color=#0066cc><span class=high>1</span></font></div>
            </td>
            <td width="59%"  style="word-break:break-all" class="tdStyle"> 
              <div class=high align="center"><font color=#0066cc><span class=high>江苏省常州市武进区湖塘镇周家巷村委杨树园33号</span></font></div>
            </td>
            <td width="15%"  style="word-break:break-all" class="tdStyle"> 
              <div class=high align=center><font color=#0066cc><span class=high>自置</span></font></div>
            </td>
            <td width="20%"  style="word-break:break-all" class="tdStyle"> 
              <div class=high align=center><font color=#0066cc><span class=high>2011.09.15</span></font></div>
            </td>*/
		for(int i=0;i<jz_Element_List.size();i++){
			RH_JZ_INFO jz_Info = new RH_JZ_INFO();
			List<Element> tmp_ls = jz_Element_List.get(i).getAllElements(HTMLElementName.TD);
			jz_Info.setIndex_(tmp_ls.get(0).getTextExtractor().toString());
			jz_Info.setAddress(tmp_ls.get(1).getTextExtractor().toString());
			jz_Info.setCondition(tmp_ls.get(2).getTextExtractor().toString());
			jz_Info.setUpdateDate(tmp_ls.get(3).getTextExtractor().toString());
			jz_Info_List.add(jz_Info);
		}
		return jz_Info_List;
		/** end **/
	}
	
	/**   
	* @Title: readZY    
	* @Description: 读取职业信息    
	* @param url
	* @return List<ZY_Info>
	*/
	public List<RH_ZY_INFO> readZY()
	{
		//List<Element> content_Element_List = readUrl(url);
		
		/** start **/
		List<RH_ZY_INFO> zy_Info_List = new ArrayList<RH_ZY_INFO>();
		List<Element> zy_Element_List = content_Element_List.get(Constants.PBOC_ZY_INFO_TABLEINDEX).getAllElements(HTMLElementName.TR);
		/*<td width="6%" style="word-break:break-all" class="tdStyle"> 
              <div class=high align=center><font color=#0066cc><span class=high>1</span></font></div>
            </td>
            <td colspan=4 style="word-break:break-all" class="tdStyle"> 
              <div class=high align=center><font color=#0066cc><span class=high>常州市天峰轴承厂</span></font></div>
            </td>
            <td colspan=2  style="word-break:break-all" class="tdStyle"> 
              <div class=high align=center><font color=#0066cc><span class=high>中国江苏常州武进区湖圹镇周家港村委常州市天峰轴承厂</span></font></div>
            </td>
            -----------
            <td  width="6%" style="word-break:break-all" class="tdStyle"> 
              <div class=high align=center><font color=#0066cc><span class=high>1</span></font></div>
            </td>
            <td width="16%" style="word-break:break-all" class="tdStyle"> 
              <div class=high align=center><font color=#0066cc><span class=high>不便分类的其它从业人员</span></font></div>
            </td>
            <td width="16%" style="word-break:break-all" class="tdStyle"> 
              <div class=high align=center><font color=#0066cc><span class=high>金融业</span></font></div>
            </td>
            <td width="16%" style="word-break:break-all" class="tdStyle"> 
              <div class=high align=center><font color=#0066cc><span class=high>高级领导（行政级别局级及局级以上领导或大公司高级管理人员）</span></font></div>
            </td>
            <td width="16%" style="word-break:break-all" class="tdStyle"> 
              <div class=high align=center><font color=#0066cc><span class=high>高级</span></font></div>
            </td>
            <td width="16%" style="word-break:break-all" class="tdStyle"> 
              <div class=high align=center><font color=#0066cc><span class=high>--</span></font></div>
            </td>
            <td width="14%"  style="word-break:break-all" class="tdStyle"> 
              <div class=high align=center><font color=#0066cc><span class=high>2009.04.28</span></font></div>
            </td>*/
		zy_Element_List.remove(zy_Element_List.size()/2);
		zy_Element_List.remove(0);
		
		for(int i=0;i<zy_Element_List.size()/2;i++){
			RH_ZY_INFO zy_Info = new RH_ZY_INFO();
			List<Element> tmp_ls = zy_Element_List.get(i).getAllElements(HTMLElementName.TD);
			zy_Info.setIndex_(tmp_ls.get(0).getTextExtractor().toString());
			zy_Info.setWorkUnit(tmp_ls.get(1).getTextExtractor().toString());
			zy_Info.setWorkAddress(tmp_ls.get(2).getTextExtractor().toString());
			zy_Info_List.add(zy_Info);
		}
		
		for(int i=zy_Element_List.size()/2,j=0;i<zy_Element_List.size();i++,j++){
			List<Element> tmp_ls = zy_Element_List.get(i).getAllElements(HTMLElementName.TD);
			zy_Info_List.get(j).setJob(tmp_ls.get(1).getTextExtractor().toString());
			zy_Info_List.get(j).setIndustry(tmp_ls.get(2).getTextExtractor().toString());
			zy_Info_List.get(j).setDuty(tmp_ls.get(3).getTextExtractor().toString());
			zy_Info_List.get(j).setHeadship(tmp_ls.get(4).getTextExtractor().toString());
			zy_Info_List.get(j).setJoinTime(tmp_ls.get(5).getTextExtractor().toString());
			zy_Info_List.get(j).setUpdateDate(tmp_ls.get(6).getTextExtractor().toString());
		}
		return zy_Info_List;
		/** end **/
	}
	
	/**   
	* @Title: readXYTS    
	* @Description: 读取信息提示   
	* @param url
	* @return XYTS_Info
	*/
	public RH_XYTS_INFO readXYTS(RH_XYTS_INFO xyts_Info)
	{
		//List<Element> content_Element_List = readUrl(url);
		
		/** start **/
		//RH_XYTS_INFO xyts_Info = new RH_XYTS_INFO();
		Element po_Element = content_Element_List.get(Constants.PBOC_XYTS_INFO_TABLEINDEX).getAllElements(HTMLElementName.TR).get(1);
		/*<td width="10%" class="tdStyle"> 
            <div class=high align=center><font color=#0066cc><strong>0</strong></font></div>
          </td>
          <td width="10%" class="tdStyle"> 
            <div class=high align=center><font color=#0066cc>5</font></div>
          </td>
          
          <td width="13%" class="tdStyle"> 
            <div class=high align=center><font color=#0066cc>2010.05</font></div>
          </td>
         <td width="10%" class="tdStyle"> 
            <div class=high align=center><font color=#0066cc>5</font></div>
          </td>
          
          <td width="13%" class="tdStyle"> 
            <div class=high align=center><font color=#0066cc>2008.03</font></div>
          </td>
         <td width="10%" class="tdStyle"> 
            <div class=high align=center><font color=#0066cc>0</font></div>
          </td>
         
          <td width="14%" class="tdStyle"> 
            <div class=high align=center><font color=#0066cc>--</font></div>
          </td>
         <td width="10%" class="tdStyle"> 
            <div class=high align=center><font color=#0066cc>0</font></div>
          </td>
          <td width="10%" class="tdStyle"> 
            <div class=high align=center><font color=#0066cc>0</font></div>
          </td>*/
		List<Element> xyts_Info_List = po_Element.getAllElements(HTMLElementName.TD);
		xyts_Info.setHousingLoanNum(xyts_Info_List.get(0).getTextExtractor().toString());
		xyts_Info.setOtherLoanNum(xyts_Info_List.get(1).getTextExtractor().toString());
		xyts_Info.setFirstLoanDate(xyts_Info_List.get(2).getTextExtractor().toString());
		xyts_Info.setCreditCardNum(xyts_Info_List.get(3).getTextExtractor().toString());
		xyts_Info.setFirstCreditCardDate(xyts_Info_List.get(4).getTextExtractor().toString());
		xyts_Info.setQuasiCreditCardNum(xyts_Info_List.get(5).getTextExtractor().toString());
		xyts_Info.setFirstQuasiCreditCardDate(xyts_Info_List.get(6).getTextExtractor().toString());
		xyts_Info.setDeclareNum(xyts_Info_List.get(7).getTextExtractor().toString());
		xyts_Info.setMarkNum(xyts_Info_List.get(8).getTextExtractor().toString());
		
		return xyts_Info;
		/** end **/
	}
	
	/**   
	* @Title: readYQ    
	* @Description: 读取逾期信息  
	* @param url
	* @return YQ_Info
	*/
	public RH_YQ_INFO readYQ(RH_YQ_INFO yq_Info)
	{
		//List<Element> content_Element_List = readUrl(url);
		
		/** start **/
		//RH_YQ_INFO yq_Info = new RH_YQ_INFO();
		Element yq_Element = content_Element_List.get(Constants.PBOC_YQ_INFO_TABLEINDEX).getAllElements(HTMLElementName.TR).get(2);
		/*<td class="tdStyle" colspan=1> 
              <div class=high align=center><font color=#0066cc><span class=high>2</span></font></div>
            </td>
            <td class="tdStyle" colspan=1> 
              <div class=high align=center><font color=#0066cc><span class=high>23</span></font></div>
            </td>
            <td class="tdStyle" colspan=1> 
              <div class=high align=center><font color=#0066cc><span class=high>141,351</span></font></div>
            </td>
            <td class="tdStyle" colspan=1> 
              <div class=high align=center><font color=#0066cc><span class=high>7</span></font></div>
            </td>
             
            
            <td class="tdStyle" colspan=1> 
              <div class=high align=center><font color=#0066cc><span class=high>1</span></font></div>
            </td>
            <td class="tdStyle" colspan=1> 
              <div class=high align=center><font color=#0066cc><span class=high>29</span></font></div>
            </td>
            <td class="tdStyle" colspan=1> 
              <div class=high align=center><font color=#0066cc><span class=high>3,606</span></font></div>
            </td>
            <td class="tdStyle" colspan=1> 
              <div class=high align=center><font color=#0066cc><span class=high>7</span></font></div>
            </td>
             
            
            <td class="tdStyle" colspan=1> 
              <div class=high align=center><font color=#0066cc><span class=high>0</span></font></div>
            </td>
            <td class="tdStyle" colspan=1> 
              <div class=high align=center><font color=#0066cc><span class=high>0</span></font></div>
            </td>
            <td class="tdStyle" colspan=1> 
              <div class=high align=center><font color=#0066cc><span class=high>0</span></font></div>
            </td>
            <td class="tdStyle" colspan=1> 
              <div class=high align=center><font color=#0066cc><span class=high>0</span></font></div>
            </td>*/
		List<Element> yq_Info_List = yq_Element.getAllElements(HTMLElementName.TD);
		yq_Info.setLoanOverdueNum(yq_Info_List.get(0).getTextExtractor().toString());
		yq_Info.setLoanOverdueMonth(yq_Info_List.get(1).getTextExtractor().toString());
		yq_Info.setLoanOverdueAmountTotal(yq_Info_List.get(2).getTextExtractor().toString());
		yq_Info.setLoanOverdueLongestMonth(yq_Info_List.get(3).getTextExtractor().toString());
		
		yq_Info.setCreditCardNum(yq_Info_List.get(4).getTextExtractor().toString());
		yq_Info.setCreditCardMonth(yq_Info_List.get(5).getTextExtractor().toString());
		yq_Info.setCreditCardAmountTotal(yq_Info_List.get(6).getTextExtractor().toString());
		yq_Info.setCreditCardLongestMonth(yq_Info_List.get(7).getTextExtractor().toString());
		
		yq_Info.setQuasiCreditCardNum(yq_Info_List.get(8).getTextExtractor().toString());
		yq_Info.setQuasiCreditCardMonth(yq_Info_List.get(9).getTextExtractor().toString());
		yq_Info.setQuasiCreditCardAmountTotal(yq_Info_List.get(10).getTextExtractor().toString());
		yq_Info.setQuasiCreditCardLongestMon(yq_Info_List.get(11).getTextExtractor().toString());
		return yq_Info;
		/** end **/
	}

	/**   
	* @Title: readWJQDK    
	* @Description: 读取未结清贷款      
	* @param url
	* @Return WJQDK_Info
	*/
	public RH_WJQDK_INFO readWJQDK(RH_WJQDK_INFO wjqdk_Info)
	{
		//List<Element> content_Element_List = readUrl(url);
		
		/** start **/
		//RH_WJQDK_INFO wjqdk_Info = new RH_WJQDK_INFO();
		Element wjqdk_Element = content_Element_List.get(Constants.PBOC_WJQDK_INFO_TABLEINDEX).getAllElements(HTMLElementName.TR).get(1);
		/* <td class="tdStyle" width=25%>
            <div class=high align=center><font color=#0066cc>2</font></div>
          </td>
          <td class="tdStyle" width=15%>
            <div class=high align=center><font color=#0066cc>2</font></div>
          </td>
          <td class="tdStyle" width=10%>
            <div class=high align=center><font color=#0066cc>2</font></div>
          </td>
          <td class="tdStyle" width=15%>
            <div class=high align=center><font color=#0066cc>400,000</font></div>
          </td>
          <td class="tdStyle" width=10%>
            <div class=high align=center><font color=#0066cc>100,000</font></div>
          </td>
          <td class="tdStyle" width=25%>
            <div class=high align=center><font color=#0066cc>147,279</font></div>
          </td>*/
		List<Element> wjqdk_Info_List = wjqdk_Element.getAllElements(HTMLElementName.TD);
		wjqdk_Info.setLegalInstitutionOrgNum(wjqdk_Info_List.get(0).getTextExtractor().toString());
		wjqdk_Info.setLegalOrgNum(wjqdk_Info_List.get(1).getTextExtractor().toString());
		wjqdk_Info.setNum(wjqdk_Info_List.get(2).getTextExtractor().toString());
		wjqdk_Info.setAmountTotal(wjqdk_Info_List.get(3).getTextExtractor().toString());
		wjqdk_Info.setRemaining(wjqdk_Info_List.get(4).getTextExtractor().toString());
		wjqdk_Info.setAvgPer6month(wjqdk_Info_List.get(5).getTextExtractor().toString());
		return wjqdk_Info;
		/** end **/
	}
	
	/**   
	* @Title: readWXHDJK    
	* @Description: 读取未销户贷记卡信息   
	* @param url
	* @return WXHDJK_Info
	*/
	public RH_WXHDJK_INFO readWXHDJK(RH_WXHDJK_INFO wxhdjk_Info)
	{
		//List<Element> content_Element_List = readUrl(url);
		
		/** start **/
		// = new RH_WXHDJK_INFO();
		Element wxhdjk_Element = content_Element_List.get(Constants.PBOC_WXHDJK_INFO_TABLEINDEX).getAllElements(HTMLElementName.TR).get(1);
		/* <td class="tdStyle" width="12%"> 
              <div class=high align=center><font color=#0066cc><span class=high>2</span></font></div>
            </td>
            <td class="tdStyle" width="12%">  
              <div class=high align=center><font color=#0066cc><span class=high>2</span></font></div>
            </td>
            <td class="tdStyle" width="12%">  
              <div class=high align=center><font color=#0066cc><span class=high>3</span></font></div>
            </td>
            <td class="tdStyle" width="12%">  
              <div class=high align=center><font color=#0066cc><span class=high>5,000</span></font></div>
            </td>
            <td class="tdStyle" width="12%">  
              <div class=high align=center><font color=#0066cc><font color=#0066cc>3,000</span></div>
            </td>
            <td class="tdStyle" width="12%">  
              <div class=high align=center><font color=#0066cc><span class=high>2,000</font></div>
            </td>
            <td class="tdStyle" width="14%"> 
              <div class=high align=center><font color=#0066cc><font color=#0066cc>3,659</span></div>
            </td>
            <td class="tdStyle" width="14%"> 
              <div class=high align=center><font color=#0066cc><span class=high>3,530</font></div>
            </td>*/
		List<Element> wjqdk_Info_List = wxhdjk_Element.getAllElements(HTMLElementName.TD);
		wxhdjk_Info.setInstitutionOrgNum(wjqdk_Info_List.get(0).getTextExtractor().toString());
		wxhdjk_Info.setOrgNum(wjqdk_Info_List.get(1).getTextExtractor().toString());
		wxhdjk_Info.setAccountNum(wjqdk_Info_List.get(2).getTextExtractor().toString());
		wxhdjk_Info.setAwardingTotal(wjqdk_Info_List.get(3).getTextExtractor().toString());
		wxhdjk_Info.setMaxAwarding(wjqdk_Info_List.get(4).getTextExtractor().toString());
		wxhdjk_Info.setMinAwarding(wjqdk_Info_List.get(5).getTextExtractor().toString());
		wxhdjk_Info.setUsed(wjqdk_Info_List.get(6).getTextExtractor().toString());
		wxhdjk_Info.setAvgUsed(wjqdk_Info_List.get(7).getTextExtractor().toString());
		
		return wxhdjk_Info;
		/** end **/
	}
	
	/**   
	* @Title: readDWDB    
	* @Description: 读取对外担保信息    
	* @param url
	* @return DWDB_Info
	*/
	public RH_DWDB_INFO readDWDB(RH_DWDB_INFO dwdb_Info)
	{
		//List<Element> content_Element_List = readUrl(url);
		
		/** start **/
		//dwdb_Info = new RH_DWDB_INFO();
		Element dwdb_Element = content_Element_List.get(Constants.PBOC_DWDB_INFO_TABLEINDEX).getAllElements(HTMLElementName.TR).get(1);
		/* <td class="tdStyle" width="30%"> 
              <div class=high align=center><font color=#0066cc><span class=high>1</span></font></div>
            </td>
            <td class="tdStyle" width="35%"> 
              <div class=high align=center><font color=#0066cc><span class=high>300,000</span></font></div>
            </td>
            <td class="tdStyle" width="35%"> 
              <div class=high align=center><font color=#0066cc><span class=high>0</span></font></div>
            </td>*/
		List<Element> dwdb_Info_List = dwdb_Element.getAllElements(HTMLElementName.TD);
		dwdb_Info.setNum(dwdb_Info_List.get(0).getTextExtractor().toString());
		dwdb_Info.setAmount(dwdb_Info_List.get(1).getTextExtractor().toString());
		dwdb_Info.setCapital(dwdb_Info_List.get(2).getTextExtractor().toString());
		
		return dwdb_Info;
		/** end **/
	}
	
	/**
	 * 查询该用户查询历史
	* @Title readQuery
	* @Description TODO
	* @param @param url
	* @param @return
	* @return Query_Info
	*/
	public RH_QUERY_INFO readQuery(RH_QUERY_INFO query_Info)
	{
		//List<Element> content_Element_List = readUrl(url);
		
		/** start **/
		//RH_QUERY_INFO query_Info = new RH_QUERY_INFO();
		Element query_Element = content_Element_List.get(Constants.PBOC_Query_INFO_TABLEINDEX).getAllElements(HTMLElementName.TR).get(2);
		/* <td class="tdStyle" colspan=2>  
              <div class=high align=center><font color=#0066cc><span class=high>0</span></font></div>
            </td>
            <td class="tdStyle" colspan=2>  
              <div class=high align=center><font color=#0066cc><span class=high>0</span></font></div>
            </td>
            <td class="tdStyle" colspan=2>  
              <div class=high align=center><font color=#0066cc><span class=high>0</span></font></div>
            </td>
            <td class="tdStyle" colspan=2> 
              <div class=high align=center><font color=#0066cc><span class=high>0</span></font></div>
            </td>
            <td class="tdStyle" colspan=1> 
              <div class=high align=center><font color=#0066cc><span class=high>2</span></font></div>
            </td>
            <td class="tdStyle" colspan=1> 
              <div class=high align=center><font color=#0066cc><span class=high>0</span></font></div>
            </td>
            <td class="tdStyle" colspan=2> 
              <div class=high align=center><font color=#0066cc><span class=high>0</span></font></div>
            </td>*/
		List<Element> query_Info_List = query_Element.getAllElements(HTMLElementName.TD);
		query_Info.setLoanApproval1(query_Info_List.get(0).getTextExtractor().toString());
		query_Info.setCreitApproval1(query_Info_List.get(1).getTextExtractor().toString());
		query_Info.setLoanApproval2(query_Info_List.get(2).getTextExtractor().toString());
		query_Info.setCreitApproval2(query_Info_List.get(3).getTextExtractor().toString());
		query_Info.setLoanManagement(query_Info_List.get(4).getTextExtractor().toString());
		query_Info.setGuaranteeExamination(query_Info_List.get(5).getTextExtractor().toString());
		query_Info.setRealnameReview(query_Info_List.get(6).getTextExtractor().toString());
		
		return query_Info;
		/** end **/
	}
	
	public void insertOrUpdateZX(String customerId,String url){
		readUrl(url);
		RH_INFO rh_info = this.rH_INFO_Dao.findByCustomerId(customerId);
		if(rh_info == null){
			rh_info.setCustomerId(customerId);
			rh_info.setCreatedTime(Calendar.getInstance().getTime());
			rh_info.setModifiedTime(Calendar.getInstance().getTime());
			readRH(rh_info);
			this.commonDao.insertObject(rh_info);
		}
		else{
			rh_info.setModifiedTime(Calendar.getInstance().getTime());
			readRH(rh_info);
			this.commonDao.updateObject(rh_info);
		}
		
		/*
		//暂时不需要保存这些信息，先注释掉
		insertorUpdateDWDB_Info(customerId);
		insertorUpdateJZ_Info(customerId);
		insertorUpdatePO_Info(customerId);
		insertorUpdateQuery_Info(customerId);
		insertorUpdateWJQDK_Info(customerId);
		insertorUpdateWXHDJK_Info(customerId);
		insertorUpdateXYTS_Info(customerId);
		insertorUpdateYH_Info(customerId);
		insertorUpdateYQ_Info(customerId);
		insertorUpdateZY_Info(customerId);*/
		
		//查找关键字“当前逾期期数”
		
	}
	
	/**
	 * 插入对外担保信息汇总
	 * @param dwdbInfo
	 * @return
	 */
	public String insertorUpdateDWDB_Info(String customer_id) {
		RH_DWDB_INFO dwdbInfo = this.rH_DWDB_INFO_Dao.findByCustomerId(customer_id);
		if(dwdbInfo == null){
			dwdbInfo = new RH_DWDB_INFO();
			String id = IDGenerator.generateID();
			dwdbInfo.setId(id);
			dwdbInfo.setCustomerId(customer_id);
			// 创建时间
			dwdbInfo.setCreatedTime(Calendar.getInstance().getTime());
			this.readDWDB(dwdbInfo);
			// 修改时间
			dwdbInfo.setModifiedTime(Calendar.getInstance().getTime());
			commonDao.insertObject(dwdbInfo);
		}
		else{
			this.readDWDB(dwdbInfo);
			// 修改时间
			dwdbInfo.setModifiedTime(Calendar.getInstance().getTime());
			commonDao.updateObject(dwdbInfo);
		}	
		
		return dwdbInfo.getId();
	}
	
	/**
	 * 插入居住信息
	 * @param jzInfo
	 * @return
	 */
	public void insertorUpdateJZ_Info(String customer_id) {
		List<RH_JZ_INFO> jzInfo_ls = this.rH_JZ_INFO_Dao.findByCustomerId(customer_id);
		for(RH_JZ_INFO obj : jzInfo_ls){
			commonDao.deleteObject(RH_JZ_INFO.class, obj.getId());
		}
		List<RH_JZ_INFO> jzInfo_ls_new = this.readJZ();
		for(RH_JZ_INFO obj : jzInfo_ls_new){
			String id = IDGenerator.generateID();
			obj.setId(id);
			obj.setCustomerId(customer_id);
			// 创建时间
			obj.setCreatedTime(Calendar.getInstance().getTime());
			// 修改时间
			obj.setModifiedTime(Calendar.getInstance().getTime());
			commonDao.insertObject(obj);
		}
	}
	
	/**
	 * 插入配偶信息
	 * @param poInfo
	 * @return
	 */
	public void insertorUpdatePO_Info(String customer_id) {
		RH_PO_INFO poInfo = this.rH_PO_INFO_Dao.findByCustomerId(customer_id);
		if(poInfo == null){
			poInfo = new RH_PO_INFO();
			String id = IDGenerator.generateID();
			poInfo.setId(id);
			poInfo.setCustomerId(customer_id);
			// 创建时间
			poInfo.setCreatedTime(Calendar.getInstance().getTime());
			this.readPO(poInfo);
			// 修改时间
			poInfo.setModifiedTime(Calendar.getInstance().getTime());
			commonDao.insertObject(poInfo);
		}
		else{
			this.readPO(poInfo);
			// 修改时间
			poInfo.setModifiedTime(Calendar.getInstance().getTime());
			commonDao.updateObject(poInfo);
		}	
	}
	
	/**
	 * 插入查询记录汇总
	 * @param queryInfo
	 * @return
	 */
	public void insertorUpdateQuery_Info(String customer_id) {
		RH_QUERY_INFO queryInfo = this.rH_QUERY_INFO_Dao.findByCustomerId(customer_id);
		
		if(queryInfo == null){
			queryInfo = new RH_QUERY_INFO();
			String id = IDGenerator.generateID();
			queryInfo.setId(id);
			queryInfo.setCustomerId(customer_id);
			// 创建时间
			queryInfo.setCreatedTime(Calendar.getInstance().getTime());
			this.readQuery(queryInfo);
			// 修改时间
			queryInfo.setModifiedTime(Calendar.getInstance().getTime());
			commonDao.insertObject(queryInfo);
		}
		else{
			this.readQuery(queryInfo);
			// 修改时间
			queryInfo.setModifiedTime(Calendar.getInstance().getTime());
			commonDao.updateObject(queryInfo);
		}
	}
	
	/**
	 * 插入未结清贷款信息汇总
	 * @param wjqdkInfo
	 * @return
	 */
	public void insertorUpdateWJQDK_Info(String customer_id) {
		RH_WJQDK_INFO wjqdkInfo = this.rH_WJQDK_INFO_Dao.findByCustomerId(customer_id);
		if(wjqdkInfo == null){
			wjqdkInfo = new RH_WJQDK_INFO();
			String id = IDGenerator.generateID();
			wjqdkInfo.setId(id);
			wjqdkInfo.setCustomerId(customer_id);
			// 创建时间
			wjqdkInfo.setCreatedTime(Calendar.getInstance().getTime());
			this.readWJQDK(wjqdkInfo);
			// 修改时间
			wjqdkInfo.setModifiedTime(Calendar.getInstance().getTime());
			commonDao.insertObject(wjqdkInfo);
		}
		else{
			this.readWJQDK(wjqdkInfo);
			// 修改时间
			wjqdkInfo.setModifiedTime(Calendar.getInstance().getTime());
			commonDao.updateObject(wjqdkInfo);
		}
	}
	
	/**
	 * 插入
	 * @param wxhdjkInfo
	 * @return
	 */
	public void insertorUpdateWXHDJK_Info(String customer_id) {
		RH_WXHDJK_INFO wxhdjkInfo = this.rH_WXHDJK_INFO_Dao.findByCustomerId(customer_id);
		if(wxhdjkInfo == null){
			wxhdjkInfo = new RH_WXHDJK_INFO();
			String id = IDGenerator.generateID();
			wxhdjkInfo.setId(id);
			wxhdjkInfo.setCustomerId(customer_id);
			// 创建时间
			wxhdjkInfo.setCreatedTime(Calendar.getInstance().getTime());
			this.readWXHDJK(wxhdjkInfo);
			// 修改时间
			wxhdjkInfo.setModifiedTime(Calendar.getInstance().getTime());
			commonDao.insertObject(wxhdjkInfo);
		}
		else{
			this.readWXHDJK(wxhdjkInfo);
			// 修改时间
			wxhdjkInfo.setModifiedTime(Calendar.getInstance().getTime());
			commonDao.updateObject(wxhdjkInfo);
		}
	}
	
	/**
	 * 插入
	 * @param xytsInfo
	 * @return
	 */
	public void insertorUpdateXYTS_Info(String customer_id) {
		RH_XYTS_INFO xytsInfo = this.rH_XYTS_INFO_Dao.findByCustomerId(customer_id);
		if(xytsInfo == null){
			xytsInfo = new RH_XYTS_INFO();
			String id = IDGenerator.generateID();
			xytsInfo.setId(id);
			xytsInfo.setCustomerId(customer_id);
			// 创建时间
			xytsInfo.setCreatedTime(Calendar.getInstance().getTime());
			this.readXYTS(xytsInfo);
			// 修改时间
			xytsInfo.setModifiedTime(Calendar.getInstance().getTime());
			commonDao.insertObject(xytsInfo);
		}
		else{
			this.readXYTS(xytsInfo);
			// 修改时间
			xytsInfo.setModifiedTime(Calendar.getInstance().getTime());
			commonDao.updateObject(xytsInfo);
		}
	}
	
	/**
	 * 插入
	 * @param yhInfo
	 * @return
	 */
	public void insertorUpdateYH_Info(String customer_id) {
		RH_YH_INFO yhInfo = this.rH_YH_INFO_Dao.findByCustomerId(customer_id);
		if(yhInfo == null){
			yhInfo = new RH_YH_INFO();
			String id = IDGenerator.generateID();
			yhInfo.setId(id);
			yhInfo.setCustomerId(customer_id);
			// 创建时间
			yhInfo.setCreatedTime(Calendar.getInstance().getTime());
			this.readYH(yhInfo);
			// 修改时间
			yhInfo.setModifiedTime(Calendar.getInstance().getTime());
			commonDao.insertObject(yhInfo);
		}
		else{
			this.readYH(yhInfo);
			// 修改时间
			yhInfo.setModifiedTime(Calendar.getInstance().getTime());
			commonDao.updateObject(yhInfo);
		}
	}
	
	/**
	 * 插入
	 * @param yqInfo
	 * @return
	 */
	public void insertorUpdateYQ_Info(String customer_id) {
		RH_YQ_INFO yqInfo = this.rH_YQ_INFO_Dao.findByCustomerId(customer_id);
		if(yqInfo == null){
			yqInfo = new RH_YQ_INFO();
			String id = IDGenerator.generateID();
			yqInfo.setId(id);
			yqInfo.setCustomerId(customer_id);
			// 创建时间
			yqInfo.setCreatedTime(Calendar.getInstance().getTime());
			this.readYQ(yqInfo);
			// 修改时间
			yqInfo.setModifiedTime(Calendar.getInstance().getTime());
			commonDao.insertObject(yqInfo);
		}
		else{
			this.readYQ(yqInfo);
			// 修改时间
			yqInfo.setModifiedTime(Calendar.getInstance().getTime());
			commonDao.updateObject(yqInfo);
		}
	}
	
	/**
	 * 插入职业信息
	 * @param zyInfo
	 * @return
	 */
	public void insertorUpdateZY_Info(String customer_id) {
		List<RH_ZY_INFO> zyInfo_ls = this.rH_ZY_INFO_Dao.findByCustomerId(customer_id);
		for(RH_ZY_INFO obj : zyInfo_ls){
			commonDao.deleteObject(RH_ZY_INFO.class, obj.getId());
		}
		List<RH_ZY_INFO> zyInfo_ls_new = this.readZY();
		for(RH_ZY_INFO obj : zyInfo_ls_new){
			String id = IDGenerator.generateID();
			obj.setId(id);
			obj.setCustomerId(customer_id);
			// 创建时间
			obj.setCreatedTime(Calendar.getInstance().getTime());
			// 修改时间
			obj.setModifiedTime(Calendar.getInstance().getTime());
			commonDao.insertObject(obj);
		}
	}
	
	/**
	 * 通过id查找
	 * @param clazz
	 * @param id
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object findSample2ById(Class clazz, String id) {
		return commonDao.findObjectById(clazz, id);
	}

	/**
	 * @return
	 */
	public QueryResult<Sample2> findSample2sByFilter(Sample2Filter filter) {
		return commonDao.findObjectsByFilter(Sample2.class, filter);
	}

}
