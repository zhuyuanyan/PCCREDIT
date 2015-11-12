package com.cardpay.pccredit.intopieces.filter;

import java.util.List;

import com.cardpay.pccredit.intopieces.model.MakeCard;
import com.wicresoft.jrad.base.database.dao.business.BusinessFilter;

public class MakeCardFilter extends BusinessFilter{
	private String cardNumber;
	
	private String cardOrganization;
	
	private List<String> ids;
	
	private String id;
	
	private String signStatus;
	
	
	
	public String getSignStatus() {
		return signStatus;
	}

	public void setSignStatus(String signStatus) {
		this.signStatus = signStatus;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardOrganization() {
		return cardOrganization;
	}

	public void setCardOrganization(String cardOrganization) {
		this.cardOrganization = cardOrganization;
	}

}
