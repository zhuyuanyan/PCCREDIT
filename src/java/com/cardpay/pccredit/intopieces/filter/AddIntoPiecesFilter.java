package com.cardpay.pccredit.intopieces.filter;

import com.wicresoft.jrad.base.database.dao.business.BusinessFilter;

public class AddIntoPiecesFilter extends BusinessFilter{
	
	private String customerId;
    private String productId;
    private String excelId;
    private String imageId;
    private String applicationId;
    private String intopiecesType;
    private String applyQuota;
    private String ApplyIntopiecesSpareType_1;
    private String ApplyIntopiecesSpareType_2;
    private String custType;
    
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getExcelId() {
		return excelId;
	}
	public void setExcelId(String excelId) {
		this.excelId = excelId;
	}
	public String getImageId() {
		return imageId;
	}
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
	public String getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
	public String getIntopiecesType() {
		return intopiecesType;
	}
	public void setIntopiecesType(String intopiecesType) {
		this.intopiecesType = intopiecesType;
	}
	public String getApplyQuota() {
		return applyQuota;
	}
	public void setApplyQuota(String applyQuota) {
		this.applyQuota = applyQuota;
	}
	public String getApplyIntopiecesSpareType_1() {
		return ApplyIntopiecesSpareType_1;
	}
	public void setApplyIntopiecesSpareType_1(String applyIntopiecesSpareType_1) {
		ApplyIntopiecesSpareType_1 = applyIntopiecesSpareType_1;
	}
	public String getApplyIntopiecesSpareType_2() {
		return ApplyIntopiecesSpareType_2;
	}
	public void setApplyIntopiecesSpareType_2(String applyIntopiecesSpareType_2) {
		ApplyIntopiecesSpareType_2 = applyIntopiecesSpareType_2;
	}
	public String getCustType() {
		return custType;
	}
	public void setCustType(String custType) {
		this.custType = custType;
	} 
	
}
