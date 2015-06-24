package com.cardpay.pccredit.customer.web;

import java.util.Date;

import com.wicresoft.jrad.base.database.model.BaseModel;
import com.wicresoft.jrad.base.web.form.BaseForm;
/**
 * @author chinenghe
 *
 * 2015年6月8日   下午15:58:41
 */
public class CustomerTemporaryquotaForm extends BaseModel{
	
	
	private String chineseName; //客户名称
	
	private String cardId; //证件号码
	
	private String cardType; //证件类型
	
	private String productId; //产品类型
	
	private String cardNumber; //卡号
	
	private String creditAmount; //实际额度
	
	private String cardStatusCode; // 卡片状态
	
	private String orgId;
	
	private String orgParentName;//支行名称
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getChineseName() {
		return chineseName;
	}
	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getCreditAmount() {
		return creditAmount;
	}
	public void setCreditAmount(String creditAmount) {
		this.creditAmount = creditAmount;
	}
	public String getCardStatusCode() {
		return cardStatusCode;
	}
	public void setCardStatusCode(String cardStatusCode) {
		this.cardStatusCode = cardStatusCode;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgParentName() {
		return orgParentName;
	}
	public void setOrgParentName(String orgParentName) {
		this.orgParentName = orgParentName;
	}

}
