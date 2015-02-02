package com.cardpay.pccredit.pbccrcReport.model;

import com.wicresoft.jrad.base.database.model.ModelParam;

/**
 * 人行信息
 * @author chenzhifang
 *
 * 2014-12-24下午3:51:30
 */
// RH_INFO为表格名
/*征信拒绝类型：
 * 当前逾期金额大于1000元；
 * 近两年逾期期数大于等于3；
 * 近半年逾期次数大于3；
 * 一年以内连续逾期3次；
 * 2年以上逾期期数大于等于5且金额大于2000*/
@ModelParam(table = "RH_INFO")
public class RH_INFO extends AbstractCustomerInfo{
	private static final long serialVersionUID = 1L;
	
	private String url;//地址
	private String DQYQJE;//当前逾期金额
	private String JLNYQQS;//近两年逾期期数
	private String JBNYQCS;//近半年逾期次数
	private String YNNLXYQCS;//一年内连续逾期次数
	private String LNYSYQQS;//两年以上逾期期数
	private String LNYSYQJE;//两年以上逾期金额
	private String IS_REFUSE;//是否拒绝
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDQYQJE() {
		return DQYQJE;
	}
	public void setDQYQJE(String dQYQJE) {
		DQYQJE = dQYQJE;
	}
	public String getJLNYQQS() {
		return JLNYQQS;
	}
	public void setJLNYQQS(String jLNYQQS) {
		JLNYQQS = jLNYQQS;
	}
	public String getJBNYQCS() {
		return JBNYQCS;
	}
	public void setJBNYQCS(String jBNYQCS) {
		JBNYQCS = jBNYQCS;
	}
	public String getYNNLXYQCS() {
		return YNNLXYQCS;
	}
	public void setYNNLXYQCS(String yNNLXYQCS) {
		YNNLXYQCS = yNNLXYQCS;
	}
	public String getLNYSYQQS() {
		return LNYSYQQS;
	}
	public void setLNYSYQQS(String lNYSYQQS) {
		LNYSYQQS = lNYSYQQS;
	}
	public String getLNYSYQJE() {
		return LNYSYQJE;
	}
	public void setLNYSYQJE(String lNYSYQJE) {
		LNYSYQJE = lNYSYQJE;
	}
	public String getIS_REFUSE() {
		return IS_REFUSE;
	}
	public void setIS_REFUSE(String iS_REFUSE) {
		IS_REFUSE = iS_REFUSE;
	}
}
