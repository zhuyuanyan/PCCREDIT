package com.cardpay.pccredit.intopieces.filter;

import com.wicresoft.jrad.base.database.dao.business.BusinessFilter;

public class IntoPiecesCardQueryFilter extends BusinessFilter{
	
	private String id;//制卡主键
	private String bankId;//银行id
    private String approveCardId; //申请卡片id
    private String cardType; //证件类型
    private String cardId; //证件号码
    private String resultType;//制卡结果类型
    private String resultText;//制卡结果内容
    private String approveId;//审批人id
    private String approveName;//审批人姓名
    
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	public String getApproveCardId() {
		return approveCardId;
	}
	public void setApproveCardId(String approveCardId) {
		this.approveCardId = approveCardId;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getResultType() {
		return resultType;
	}
	public void setResultType(String resultType) {
		this.resultType = resultType;
	}
	public String getResultText() {
		return resultText;
	}
	public void setResultText(String resultText) {
		this.resultText = resultText;
	}
	public String getApproveId() {
		return approveId;
	}
	public void setApproveId(String approveId) {
		this.approveId = approveId;
	}
	public String getApproveName() {
		return approveName;
	}
	public void setApproveName(String approveName) {
		this.approveName = approveName;
	}
	
}
