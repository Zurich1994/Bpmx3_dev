package com.hotent.core.valid;

import java.util.ArrayList;
import java.util.List;

/**
 * 验证表单类。
 * @author hotent
 *
 */
public class ValidForm {
	
	/**
	 * 表单名称
	 */
	private String formName="";
	
	/**
	 * 表单字段
	 */
	private List<ValidField> listField=new ArrayList<ValidField>();

	
	
	
	public String getFormName() {
		return formName;
	}
	
	/**
	 * 表单名称。
	 * @param formName
	 */
	public void setFormName(String formName) {
		this.formName = formName;
	}

	public List<ValidField> getListField() {
		return listField;
	}

	public void setListField(List<ValidField> listField) {
		this.listField = listField;
	}
	
	/**
	 * 添加字段。
	 * @param field
	 */
	public void addField(ValidField field)
	{
		this.listField.add(field);
	}
	
	
}
