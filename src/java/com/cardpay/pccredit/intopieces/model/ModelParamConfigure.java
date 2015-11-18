package com.cardpay.pccredit.intopieces.model;

import com.wicresoft.jrad.base.database.model.BusinessModel;
import com.wicresoft.jrad.base.database.model.ModelParam;
/**
 * 模型参数配置表
 * @author songchen
 */
@ModelParam(table = "MODEL_PARAMETER_CONFIGURATION")
public class ModelParamConfigure extends BusinessModel {
	
	private static final long serialVersionUID = -8470111754965975277L;
	
	private String dictType; 
	private String typeCode; 
	private String typeName;
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