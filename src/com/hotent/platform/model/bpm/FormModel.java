package com.hotent.platform.model.bpm;

import com.hotent.core.util.StringUtil;

/**
 * 表单对象。
 * @author ray
 *
 */
public class FormModel {
	
	
	/**
	 * -1,没有设置表单。
	 * 0,在线表单
	 * 1,url表单
	 * 2, 有明细url。
	 */
	private int formType=-1;
	
	/**
	 * 在线表单的html。
	 */
	private String formHtml="";
	
	/**
	 * 表单明细url。
	 */
	private String detailUrl="";
	
	
	private String formUrl="";
	
	
	/**
	 * 表单是否有效。
	 */
	private boolean isValid=true;
	

	public int getFormType() {
		return formType;
	}

	public void setFormType(int formType) {
		this.formType = formType;
	}

	public String getFormHtml() {
		return formHtml;
	}

	public void setFormHtml(String formHtml) {
		this.formHtml = formHtml;
		this.formType=0;
	}

	public String getDetailUrl() {
		return detailUrl;
	}

	public void setDetailUrl(String detailUrl) {
		this.detailUrl = detailUrl;
		if(StringUtil.isNotEmpty(detailUrl)){
			this.formType=2;
		}
		
	}

	public String getFormUrl() {
		return formUrl;
	}

	public void setFormUrl(String formUrl) {
		this.formUrl = formUrl;
	}
	
	/**
	 * 表单为空。
	 * @return
	 */
	public boolean isFormEmpty(){
		boolean rtn=false;
		switch (this.formType) {
			case -1:
				rtn= true;
				break;
			case 0:
				rtn=StringUtil.isEmpty(formHtml);
				break;
			case 1:
				rtn=StringUtil.isEmpty(formUrl);
			case 2:
				rtn=StringUtil.isEmpty(formUrl);
				break;
		}
		return rtn;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
	
}
