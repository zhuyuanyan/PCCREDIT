package com.cardpay.pccredit.product.model;

import com.wicresoft.jrad.base.database.model.BusinessModel;
import com.wicresoft.jrad.base.database.model.ModelParam;
/**
 * Description of ProductAttribute
 * 
 * @author 王海东
 * @created on 2014-10-11
 * 
 * @version $Id:$
 */
public class ProductAttributeVo extends BusinessModel {

	private static final long serialVersionUID = 1L;
	private String productName;
	private String purposeLoan;
	private String creditLine;
	private String rateRange;
	private String productTypeCode;
	private String loanTimeLimit;
	private String loanPrincipal;
	private String letterPaymentWay;
	private String numbererestSettlementWay;
	private String assureMeans;
	private String penaltyNumbererest;
	private String productRiskTolerance;
	private String status;
	private String pictureUrl;
	private String pictureName;
	private String seqno;
	private String default_type;
	
	private String appId;
    private String countNum;
    
    private String ed;//额度
    private String lv;//利率
    private String nf;//年费
    private String endTime;//到期日
    


	public String getEd() {
		return ed;
	}

	public void setEd(String ed) {
		this.ed = ed;
	}

	public String getLv() {
		return lv;
	}

	public void setLv(String lv) {
		this.lv = lv;
	}

	public String getNf() {
		return nf;
	}

	public void setNf(String nf) {
		this.nf = nf;
	}

	

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getCountNum() {
		return countNum;
	}

	public void setCountNum(String countNum) {
		this.countNum = countNum;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getPictureName() {
		return pictureName;
	}

	public void setPictureName(String pictureName) {
		this.pictureName = pictureName;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public String getSeqno() {
		return seqno;
	}

	public void setSeqno(String seqno) {
		this.seqno = seqno;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProductTypeCode() {
		return productTypeCode;
	}

	public void setProductTypeCode(String productTypeCode) {
		this.productTypeCode = productTypeCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getPurposeLoan() {
		return purposeLoan;
	}

	public void setPurposeLoan(String purposeLoan) {
		this.purposeLoan = purposeLoan;
	}

	public String getCreditLine() {
		return creditLine;
	}

	public void setCreditLine(String creditLine) {
		this.creditLine = creditLine;
	}

	public String getRateRange() {
		return rateRange;
	}

	public void setRateRange(String rateRange) {
		this.rateRange = rateRange;
	}

	public String getLoanTimeLimit() {
		return loanTimeLimit;
	}

	public void setLoanTimeLimit(String loanTimeLimit) {
		this.loanTimeLimit = loanTimeLimit;
	}

	public String getLoanPrincipal() {
		return loanPrincipal;
	}

	public void setLoanPrincipal(String loanPrincipal) {
		this.loanPrincipal = loanPrincipal;
	}

	public String getLetterPaymentWay() {
		return letterPaymentWay;
	}

	public void setLetterPaymentWay(String letterPaymentWay) {
		this.letterPaymentWay = letterPaymentWay;
	}

	public String getNumbererestSettlementWay() {
		return numbererestSettlementWay;
	}

	public void setNumbererestSettlementWay(String numbererestSettlementWay) {
		this.numbererestSettlementWay = numbererestSettlementWay;
	}

	public String getAssureMeans() {
		return assureMeans;
	}

	public void setAssureMeans(String assureMeans) {
		this.assureMeans = assureMeans;
	}

	public String getPenaltyNumbererest() {
		return penaltyNumbererest;
	}

	public void setPenaltyNumbererest(String penaltyNumbererest) {
		this.penaltyNumbererest = penaltyNumbererest;
	}

	public String getProductRiskTolerance() {
		return productRiskTolerance;
	}

	public void setProductRiskTolerance(String productRiskTolerance) {
		this.productRiskTolerance = productRiskTolerance;
	}

	public String getDefault_type() {
		return default_type;
	}

	public void setDefault_type(String default_type) {
		this.default_type = default_type;
	}

}
