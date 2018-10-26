package com.hotent.platform.model.system;

import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:桌面栏目 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-03-20 16:39:01
 */
public class DesktopColumn extends BaseModel
{
	// 栏目ID
	protected Long id;
	// 栏目名称
	protected String columnName;
	// 数据方法名

	protected String serviceMethod;
	// 模板名称
	protected String templateName;
	// 模板标识
	protected String templateId;
	// 模板路径
	protected String templatePath;
	// 栏目路径
	protected String columnUrl;
	//栏目Html
	protected String columnHtml;
	// 是否系统栏目
	protected Integer isSys;
	// 栏目模板html
	protected String html;
	
	protected Integer methodType=0;
	
	protected String queryAlias;

	public void setId(Long id) 
	{
		this.id = id;
	}
	/**
	 * 返回 栏目ID
	 * @return
	 */
	public Long getId() 
	{
		return id;
	}
	
	public String getQueryAlias() {
		return queryAlias;
	}
	public void setQueryAlias(String queryAlias) {
		this.queryAlias = queryAlias;
	}
	public String getColumnHtml() {
		return columnHtml;
	}
	public void setColumnHtml(String columnHtml) {
		this.columnHtml = columnHtml;
	}
	/**
	 * 返回 栏目名称
	 * @return
	 */
	

	public void setServiceMethod(String serviceMethod) 
	{
		this.serviceMethod = serviceMethod;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	/**
	 * 返回 数据方法名

	 * @return
	 */
	public String getServiceMethod() 
	{
		return serviceMethod;
	}

	public void setTemplateName(String templateName) 
	{
		this.templateName = templateName;
	}
	
	public Integer getMethodType() {
		return methodType;
	}
	public void setMethodType(Integer methodType) {
		this.methodType = methodType;
	}
	/**
	 * 返回 模板名称
	 * @return
	 */
	public String getTemplateName() 
	{
		return templateName;
	}

	public void setTemplateId(String templateId) 
	{
		this.templateId = templateId;
	}
	/**
	 * 返回 模板标识
	 * @return
	 */
	public String getTemplateId() 
	{
		return templateId;
	}

	public void setTemplatePath(String templatePath) 
	{
		this.templatePath = templatePath;
	}
	/**
	 * 返回 模板路径
	 * @return
	 */
	public String getTemplatePath() 
	{
		return templatePath;
	}

	public void setColumnUrl(String columnUrl) 
	{
		this.columnUrl = columnUrl;
	}
	/**
	 * 返回 栏目路径
	 * @return
	 */
	public String getColumnUrl() 
	{
		return columnUrl;
	}


	public Integer getIsSys() {
		return isSys;
	}
	public void setIsSys(Integer isSys) {
		this.isSys = isSys;
	}
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof DesktopColumn)) 
		{
			return false;
		}
		DesktopColumn rhs = (DesktopColumn) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.columnName, rhs.columnName)
		.append(this.serviceMethod, rhs.serviceMethod)
		.append(this.templateName, rhs.templateName)
		.append(this.templateId, rhs.templateId)
		.append(this.templatePath, rhs.templatePath)
		.append(this.columnUrl, rhs.columnUrl)
		.append(this.isSys, rhs.isSys)
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
		.append(this.columnName) 
		.append(this.serviceMethod) 
		.append(this.templateName) 
		.append(this.templateId) 
		.append(this.templatePath) 
		.append(this.columnUrl) 
		.append(this.isSys) 
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
		.append("columnName", this.columnName) 
		.append("serviceMethod", this.serviceMethod) 
		.append("templateName", this.templateName) 
		.append("templateId", this.templateId) 
		.append("templatePath", this.templatePath) 
		.append("columnUrl", this.columnUrl) 
		.append("isSys", this.isSys) 
		.append("html", this.html) 
		.toString();
	}
   
  

}