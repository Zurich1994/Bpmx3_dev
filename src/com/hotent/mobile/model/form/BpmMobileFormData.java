package com.hotent.mobile.model.form;


public class BpmMobileFormData {

	public static int FORMTYPE_NOSETFORM = -1;
	public static int FORMTYPE_ONLINEFORM = 0;
	public static int FORMTYPE_URLFORM = 1;	
	public static int FORMTYPE_URLFORMD = 2;	
	/**
	 * -1,没有设置表单。
	 * 0,在线表单
	 * 1,url表单
	 * 2, 有明细url。
	 */
	//
	private int formType=FORMTYPE_NOSETFORM;

	/**
	 * 表单是否有效。
	 */
	private boolean isValid = true;

	/**
	 * 表单数据。 数据格式为JSON。
	 */
	private String formData = "";
	

	public int getFormType() {
		return formType;
	}

	public void setFormType(int formType) {
		this.formType = formType;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public String getFormData() {
		return formData;
	}

	public void setFormData(String formData) {
		this.formData = formData;
	}



}