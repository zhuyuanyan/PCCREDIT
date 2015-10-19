package com.cardpay.pccredit.psiviReport.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.cardpay.pccredit.pbccrcReport.util.PbccrcReport;
import com.cardpay.pccredit.pbccrcReport.util.ReporterUtil;

/**   
 * @Title: PsviReport.java 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author 池能和 
 * @date 2015年6月16日 上午10:56:51
*/
public class PsviReport {
	private static final String cLoginSuccess = "身份核查系统登录";
	private static final String cLogonPageInfo = "单笔核查";
	private static final String sigleCheckResult = "单笔核对结果";
	private static final Logger logger = Logger.getLogger(PbccrcReport.class);
	private PropertyUtils propertyUtils;
	
	private boolean psviLogin(HttpClient httpClient,String userid,String passwd) throws Exception {
		NameValuePair ie = new NameValuePair("User-Agent",
				"Mozilla/4.0 (compatible; MSIE 6.0; Windows 2000)");
		boolean loginFlag = false;
//		propertyUtil = new PropertyUtil();
		NameValuePair username = new NameValuePair("userCode", userid);
		NameValuePair password = new NameValuePair("password", passwd);
//		String pbocloginUrl = propertyUtil.getPropertyByKey("pbocloginUrl");
		String pbocloginUrl ="http://10.0.14.83/idcheck/aas/login.action";
		PostMethod method = new PostMethod(pbocloginUrl);
		method.setRequestBody(new NameValuePair[] { ie, username, password });
		Cookie[] cookies = httpClient.getState().getCookies();
		httpClient.getState().addCookies(cookies);
		int statusCode = httpClient.executeMethod(method);
		//method.releaseConnection();
		String buffer = "";
		//新增日志 updated by jinping.chen 2014-04-23
		logger.info("登陆 statusCode" + statusCode);
			
		if ( statusCode == HttpStatus.SC_OK){
			 /**排错*/
			 byte[] resp = method.getResponseBody();
		     buffer = new String(resp, "UTF-8");
		     //logger.info(buffer);
		}
			
		if (checkLoginIsSucc(buffer.toString())) {
			loginFlag = true;
		} else {
		}
		method.releaseConnection();

		logger.info("登录成功???------------>" + loginFlag);
		
		return loginFlag;
	}
	
	public Map manuProcessPsviInfo(String customerName, String identityNo, String businessCode, String businessTrait, String userid,String passwd,String transCode,
			String msgNo)
		throws Exception {
		
		Map resultMap = new HashMap();
		String fileFullPath = null;
		String rtnstr = "";
		HttpClient httpClient = new HttpClient();
		httpClient.getParams().setCookiePolicy(
				CookiePolicy.BROWSER_COMPATIBILITY);
		if (psviLogin(httpClient,userid,passwd)) {
			/*
			 * 储存cookies
			 * 
			 * **/
			Cookie[] cookies = httpClient.getState().getCookies();
			
			String tempcookies ="";
			for(Cookie c :cookies){
				tempcookies+=c.toString()+";";	
			}
			//核查身份证
			NameValuePair ID1 = new NameValuePair("ID1",identityNo);
			//客户名称
			NameValuePair Name1 = new NameValuePair("Name1",customerName);
			NameValuePair BusinessCode = new NameValuePair("BusinessCode",businessCode);
			NameValuePair BusinessTrait = new NameValuePair("BusinessTrait",businessTrait);
			NameValuePair TransCode = new NameValuePair("TransCode",transCode);
			NameValuePair MsgNo = new NameValuePair("MsgNo",msgNo);
			
			String psviReportURL = "http://10.0.14.83/idcheck/action/singleRequest.action";//propertyUtils.getPropertyByKey("psviReportURL");
			PostMethod postMethod = new PostMethod(psviReportURL);
			postMethod.addRequestHeader("Content-Type",
					"application/x-www-form-urlencoded;charset=UTF-8");
			//带上前面的Cookie作为通行证
			postMethod.setRequestHeader("cookie",tempcookies);
			postMethod.setRequestBody(new NameValuePair[] {ID1,Name1,BusinessCode,BusinessTrait,TransCode,MsgNo});
			
			int status = httpClient.executeMethod(postMethod);
			logger.info("status---->:" + status);
			if ((status == HttpStatus.SC_MOVED_TEMPORARILY)
					|| (status == HttpStatus.SC_MOVED_PERMANENTLY)
					|| (status == HttpStatus.SC_SEE_OTHER)
					|| (status == HttpStatus.SC_TEMPORARY_REDIRECT
							|| (status == HttpStatus.SC_OK))) {
				byte[] responseByte = postMethod.getResponseBody();
				
				String buffer = new String(responseByte,"UTF-8");
				postMethod.releaseConnection();
				logger.info("********************核查结果***************:"+buffer);
				//解析返回结果
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				try {
				    DocumentBuilder builder = factory.newDocumentBuilder();
				    if(StringUtils.isNotBlank(buffer)&&buffer.indexOf("ErrorMessage")!=-1){
				    	System.out.println("ErrorMessage");
				    	return resultMap;
				    }
				    StringReader sr = new StringReader(buffer);
				    InputSource is = new InputSource(sr);
				    Document doc = (Document) builder.parse(is);
				
				    XPathFactory xFactory = XPathFactory.newInstance();
				    XPath xpath = xFactory.newXPath();
				   
				    XPathExpression expr = xpath.compile("CFX/MSG");
				    NodeList msg = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);  
				    for (int i = 0; i < msg.getLength(); i++) {
				        Node node = msg.item(i);
				        XPathExpression expr1 = xpath.compile("./*");
				        NodeList list = (NodeList) expr1.evaluate(node, XPathConstants.NODESET);
				        for(int j =0;j<list.getLength();j++){
				            Element e1 = (Element) list.item(j);
				            resultMap.put(e1.getNodeName(), e1.getFirstChild().getNodeValue());
				            logger.info(e1.getNodeName()+"="+resultMap.get(e1.getNodeName()));
				        }
				
				    }
				} catch (ParserConfigurationException e) {
				    // TODO Auto-generated catch block
				    e.printStackTrace();
				}catch (SAXException e) {
				    // TODO Auto-generated catch block
				    e.printStackTrace();
				} catch (IOException e) {
				    // TODO Auto-generated catch block
				    e.printStackTrace();
				}catch (XPathExpressionException e) {
				    // TODO Auto-generated catch block
				    e.printStackTrace();
				}

				
			}
				
		}
		return resultMap;
	}
	
	/**
	 * 判断登录福州农信联网核查系统是否成功
	 * 
	 * @return 登录标志
	 */
	private static boolean checkLoginIsSucc(String responseStr) {
		boolean loginFlag = true;
		try {
			int loginResultFlag = responseStr.indexOf(cLoginSuccess);
			int logonPageInfoFlag = responseStr.indexOf(cLogonPageInfo);

			if (loginResultFlag > 0 && logonPageInfoFlag == -1) {
				loginFlag = true;
			} else {
				loginFlag = false;
			}
		} catch (Exception e) {
			loginFlag = false;
			System.err.println("判断是否已经登录福州农信联网核查系统异常");
		}

		return loginFlag;
	}
	
	public static void main(String[] args) throws Exception {
		//PsviReport psviReport = new PsviReport();
		//psviReport.manuProcessPsviInfo("池能和","350821199102083916","01","01","201863","192327","0001","0001");
		//解析核查系统返回xml文件
		String resultXML = "<CFX><MSG><ID1>350821199102083916</ID1><Name1>池能和</Name1><CheckResult1>公民身份号码与姓名一致，且存在照片</CheckResult1><IssueOffice1>此项暂不返回核查结果</IssueOffice1><Photo1>20150618/350821199102083916.jpg</Photo1></MSG></CFX>";
		Map resultMap = new HashMap();
		
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			try {
			    DocumentBuilder builder = factory.newDocumentBuilder();
			    if(StringUtils.isNotBlank(resultXML)&&resultXML.indexOf("ErrorMessage")!=-1){
			    	System.out.println("ErrorMessage");
			    	return ;
			    }
			    StringReader sr = new StringReader(resultXML);
			    InputSource is = new InputSource(sr);
			    Document doc = (Document) builder.parse(is);
			
			    XPathFactory xFactory = XPathFactory.newInstance();
			    XPath xpath = xFactory.newXPath();
			   
			    XPathExpression expr = xpath.compile("CFX/MSG");
			    NodeList msg = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);  
			    for (int i = 0; i < msg.getLength(); i++) {
			        Node node = msg.item(i);
			        XPathExpression expr1 = xpath.compile("./*");
			        NodeList list = (NodeList) expr1.evaluate(node, XPathConstants.NODESET);
			        for(int j =0;j<list.getLength();j++){
			            Element e1 = (Element) list.item(j);
			            resultMap.put(e1.getNodeName(), e1.getFirstChild().getNodeValue());
			            System.out.println(e1.getNodeName()+"="+resultMap.get(e1.getNodeName()));
			        }
			        if(StringUtils.isBlank(resultMap.get("ID1").toString())){
			        	System.out.println("ok");
			        }
			    }
			} catch (ParserConfigurationException e) {
			    // TODO Auto-generated catch block
			    e.printStackTrace();
			}catch (SAXException e) {
			    // TODO Auto-generated catch block
			    e.printStackTrace();
			} catch (IOException e) {
			    // TODO Auto-generated catch block
			    e.printStackTrace();
			}catch (XPathExpressionException e) {
			    // TODO Auto-generated catch block
			    e.printStackTrace();
			}

		
	}
}
