package com.hotent.platform.model.form;

import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:表单模板 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:xwy
 * 创建时间:2012-01-10 14:41:17
 */
public class BpmFormTemplate extends BaseModel
{
	//模版类型
	//主表模版
	public static final String MAIN_TABLE="main";
	//子表模版
	public static final String SUB_TABLE="subTable";
	//宏模版
	public static final String MACRO="macro";
	//列表模版
	public static final String LIST="list";
	//明细模版
	public static final String DETAIL="detail";
	/**
	 *	表管理模板 
	 */
	public static final String TABLE_MANAGE="tableManage";
	/**
	 *	表管理模板 
	 */
	public static final String DATA_TEMPLATE="dataTemplate";
	
	/**
	 *	表管理模板 
	 */
	public static final String GEN_TEMPLATE="gendataTemplate";
	
	/**
	 * 查询数据模块
	 */
	public static final String QUERY_DATA_TEMPLATE="queryDataTemplate";
	
	// 模板Id
	protected Long templateId;
	// 模板名
	protected String templateName;
	//模板别名
	protected String alias;
	// 模板类型 1-主表模板 2-子表模板 3-宏模板
	protected String templateType;
	// 使用宏模板别名
	protected String macroTemplateAlias;
	// 模板html
	protected String html;
	// 描述
	protected String templateDesc;
	//是否可以被修改 0-不可修改 1-可以修改
	protected int canEdit;
	
	
	/**
	 * 返回 模板别名
	 * @return
	 */
	public String getAlias() {
		return alias;
	}
	
	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	/**
	 * 是否可以修改
	 * @return
	 */
	public int getCanEdit() {
		return canEdit;
	}
	
	public void setCanEdit(int canEdit) {
		this.canEdit = canEdit;
	}
	
	public void setTemplateId(Long templateId) 
	{
		this.templateId = templateId;
	}
	/**
	 * 返回 模板Id
	 * @return
	 */
	public Long getTemplateId() 
	{
		return templateId;
	}

	public void setTemplateName(String templateName) 
	{
		this.templateName = templateName;
	}
	/**
	 * 返回 模板名
	 * @return
	 */
	public String getTemplateName() 
	{
		return templateName;
	}

	
	/**
	 * 返回 模板类型
	 * @return
	 */
	public String getTemplateType() {
		return templateType;
	}

	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}

	
	/**
	 * 返回宏模板别名
	 * @param macroTemplateAlias
	 */
	public String getMacroTemplateAlias() {
		return macroTemplateAlias;
	}
	public void setMacroTemplateAlias(String macroTemplateAlias) {
		this.macroTemplateAlias = macroTemplateAlias;
	}

	public void setHtml(String html) 
	{
		this.html = html;
	}
	/**
	 * 返回 模板html
	 * @return
	 */
	public String getHtml() 
	{
		return html;
	}

	public void setTemplateDesc(String templateDesc) 
	{
		this.templateDesc = templateDesc;
	}
	/**
	 * 返回 描述
	 * @return
	 */
	public String getTemplateDesc() 
	{
		return templateDesc;
	}

   
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof BpmFormTemplate)) 
		{
			return false;
		}
		BpmFormTemplate rhs = (BpmFormTemplate) object;
		return new EqualsBuilder()
		.append(this.templateId, rhs.templateId)
		.append(this.templateName, rhs.templateName)
		.append(this.templateType, rhs.templateType)
		.append(this.macroTemplateAlias, rhs.macroTemplateAlias)
		.append(this.html, rhs.html)
		.append(this.templateDesc, rhs.templateDesc)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.templateId) 
		.append(this.templateName) 
		.append(this.templateType) 
		.append(this.macroTemplateAlias) 
		.append(this.html) 
		.append(this.templateDesc) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("templateId", this.templateId) 
		.append("templateName", this.templateName) 
		.append("templateType", this.templateType) 
		.append("macroTemplateId", this.macroTemplateAlias) 
		.append("html", this.html) 
		.append("templateDesc", this.templateDesc) 
		.toString();
	}
   
  

}