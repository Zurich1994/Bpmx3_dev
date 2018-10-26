package com.hotent.platform.model.form;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;
/**
 * 对象功能:TT_BPM_PRINT_TEMPLATE Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-12-31 10:01:30
 */
@XmlRootElement(name = "bpmPrintTemplates")
@XmlAccessorType(XmlAccessType.NONE)
public class BpmPrintTemplate extends BaseModel
{
	// ID
	@XmlAttribute
	protected Long  id;
	// FORM_KEY
	@XmlAttribute
	protected String  formKey;
	// TEMAPALTE_NAME
	@XmlAttribute
	protected String  temapalteName;
	// IS_DEFAULT
	@XmlAttribute
	protected Short  isDefault;
	// ALIAS
	@XmlAttribute
	protected Long  tableId;
	//模版
	@XmlAttribute
	protected	String template;
	// HTML
	@XmlAttribute
	protected String  html;
	public void setId(Long id) 
	{
		this.id = id;
	}
	/**
	 * 返回 ID
	 * @return
	 */
	public Long getId() 
	{
		return this.id;
	}
	public void setFormKey(String formKey) 
	{
		this.formKey = formKey;
	}
	/**
	 * 返回 FORM_KEY
	 * @return
	 */
	public String getFormKey() 
	{
		return this.formKey;
	}
	public void setTemapalteName(String temapalteName) 
	{
		this.temapalteName = temapalteName;
	}
	
	
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	/**
	 * 返回 TEMAPALTE_NAME
	 * @return
	 */
	public String getTemapalteName() 
	{
		return this.temapalteName;
	}
	public void setIsDefault(Short isDefault) 
	{
		this.isDefault = isDefault;
	}
	/**
	 * 返回 IS_DEFAULT
	 * @return
	 */
	public Short getIsDefault() 
	{
		return this.isDefault;
	}
	
	public Long getTableId() {
		return tableId;
	}
	public void setTableId(Long tableId) {
		this.tableId = tableId;
	}
	public void setHtml(String html) 
	{
		this.html = html;
	}
	/**
	 * 返回 HTML
	 * @return
	 */
	public String getHtml() 
	{
		return this.html;
	}

   
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof BpmPrintTemplate)) 
		{
			return false;
		}
		BpmPrintTemplate rhs = (BpmPrintTemplate) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.formKey, rhs.formKey)
		.append(this.temapalteName, rhs.temapalteName)
		.append(this.isDefault, rhs.isDefault)
		.append(this.tableId, rhs.tableId)
		.append(this.html, rhs.html)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id) 
		.append(this.formKey) 
		.append(this.temapalteName) 
		.append(this.isDefault) 
		.append(this.tableId) 
		.append(this.html) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("formKey", this.formKey) 
		.append("temapalteName", this.temapalteName) 
		.append("isDefault", this.isDefault) 
		.append("alias", this.tableId) 
		.append("html", this.html) 
		.toString();
	}
   
  

}