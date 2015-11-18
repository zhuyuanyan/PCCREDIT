package com.cardpay.pccredit.intopieces.filter;

import com.wicresoft.jrad.base.web.filter.BaseQueryFilter;

public class ModelParamFilter extends BaseQueryFilter{
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
