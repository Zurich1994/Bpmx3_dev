package com.hotent.platform.model.form;

public class FormField {
	//◊÷∂Œ√˚
	private String fieldName;
	
	private String value;
	private Object object;
	
	//◊÷∂Œ√Ë ˆ
	private String fieldDesc;
	// «∑Òœ‘ æ
	private Short isShow =1;
	//≈≈–Ú
	private int sn =1;
	
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getFieldDesc() {
		return fieldDesc;
	}
	public void setFieldDesc(String fieldDesc) {
		this.fieldDesc = fieldDesc;
	}
	public Short getIsShow() {
		return isShow;
	}
	public void setIsShow(Short isShow) {
		this.isShow = isShow;
	}
	public int getSn() {
		return sn;
	}
	public void setSn(int sn) {
		this.sn = sn;
	}
	
}
