package com.hotent.mobile.model.form;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;

/**
 * 对象功能:手机表单 Model对象 
 * 开发公司:广州宏天软件有限公司 
 * 开发人员:zxh 
 * 创建时间:2014-04-02 15:09:58
 */
public class BpmMobileForm extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 是默认版本 */
	public static final Integer IS_DEFAULT_Y = 0;
	// 主键
	protected Long id;
	// 表单主键
	protected Long formId;
	// 表单key
	protected String formKey;
	// html
	protected String html;
	// 模板
	protected String template;
	// 字段列表
	protected String formJson;
	// 是否默认
	protected Integer isDefault =0;
	//GUID 判断是否修改模板
	protected String guid;

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 返回 主键
	 * 
	 * @return
	 */
	public Long getId() {
		return this.id;
	}

	public void setFormId(Long formId) {
		this.formId = formId;
	}

	/**
	 * 返回 表单主键
	 * 
	 * @return
	 */
	public Long getFormId() {
		return this.formId;
	}

	public void setFormKey(String formKey) {
		this.formKey = formKey;
	}

	/**
	 * 返回 表单key
	 * 
	 * @return
	 */
	public String getFormKey() {
		return this.formKey;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	/**
	 * 返回 html
	 * 
	 * @return
	 */
	public String getHtml() {
		return this.html;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	/**
	 * 返回 模板
	 * 
	 * @return
	 */
	public String getTemplate() {
		return this.template;
	}

	public void setFormJson(String formJson) {
		this.formJson = formJson;
	}

	/**
	 * 返回 字段列表
	 * 
	 * @return
	 */
	public String getFormJson() {
		return this.formJson;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

	/**
	 * 返回 是否默认
	 * 
	 * @return
	 */
	public Integer getIsDefault() {
		return this.isDefault;
	}
	
	

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof BpmMobileForm)) {
			return false;
		}
		BpmMobileForm rhs = (BpmMobileForm) object;
		return new EqualsBuilder().append(this.id, rhs.id)
				.append(this.formId, rhs.formId)
				.append(this.formKey, rhs.formKey).append(this.html, rhs.html)
				.append(this.template, rhs.template)
				.append(this.formJson, rhs.formJson)
				.append(this.isDefault, rhs.isDefault)
				.append(this.guid, rhs.guid)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.id)
				.append(this.formId).append(this.formKey).append(this.html)
				.append(this.template).append(this.formJson).append(this.isDefault).append(this.guid)
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("id", this.id)
				.append("formId", this.formId).append("formKey", this.formKey)
				.append("html", this.html).append("template", this.template)
				.append("json", this.formJson).append("isDefault", this.isDefault)
				.append("guid", this.guid)
				.toString();
	}

}