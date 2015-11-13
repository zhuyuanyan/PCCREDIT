package com.cardpay.pccredit.intopieces.model;

import com.wicresoft.jrad.base.web.form.BaseForm;

public class ModelParamConfigureForm extends BaseForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String dictType; 
	private String typeCode; 
	private String typeName;
	private String descp;
	
	public String getDescp() {
		return descp;
	}
	public void setDescp(String descp) {
		this.descp = descp;
	}
	public String getDictType() {
		return dictType;
	}
	public void setDictType(String dictType) {
		this.dictType = dictType;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	
}
