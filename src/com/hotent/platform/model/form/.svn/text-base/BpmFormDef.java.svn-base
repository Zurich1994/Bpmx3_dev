package com.hotent.platform.model.form;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:bpm_form_def Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:xwy
 * 创建时间:2012-02-13 14:02:45
 */
@SuppressWarnings("serial")
@XmlRootElement(name = "bpmFormDef")
@XmlAccessorType(XmlAccessType.NONE)
public class BpmFormDef extends BaseModel implements Cloneable
{
	//在同一个页面中编辑
	public static final String EditInline="edit";
	//在窗口中编辑
	public static final String EditForm="form";
	/**
	 * 设计类型：通过表生成
	 */
	public static final int DesignType_FromTable = 0;
	/**
	 * 设计类型：自定义
	 */
	public static final int DesignType_CustomDesign = 1;	
	//在窗口中编辑
	
	public static final int DesignType_FormTableParcel = 2;//数据包类型
	public static final String EditWindow="window";
	// 分页
	public static String PageSplitor = "#page#";
	/** 不是默认版本 */
	public static final Short IS_NOT_DEFAULT = 0;
	/** 是默认版本 */
	public static final Short IS_DEFAULT = 1;
	/** 未发布 */
	public static final Short IS_NOT_PUBLISHED = 0;
	/** 发布*/
	public static final Short IS_PUBLISHED = 1;
	
	// 表单ID
	@XmlAttribute
	protected Long formDefId=0L;
	// key
	@XmlAttribute
	protected String formKey="";
	// 表单分类
	@XmlAttribute
	protected Long categoryId =0L;
	//流程分类名称。
	protected String categoryName="";
	// 表单标题
	@XmlAttribute
	protected String subject = "";
	// 描述
	@XmlAttribute
	protected String formDesc = "";
	//tab选项卡标题
	@XmlAttribute
	protected String tabTitle="";
	// 定义html
	@XmlAttribute
	protected String html;
	// freemarker模板
	@XmlAttribute
	protected String template;
	// 是否缺省
	@XmlAttribute
	protected Short isDefault = 0;
	// 对应tableId
	@XmlAttribute
	protected Long tableId=0L;
	// 版本号
	@XmlAttribute
	protected Integer versionNo;
	// isPublished
	@XmlAttribute
	protected Short isPublished = 0;
	// publishedBy
	protected String publishedBy;
	// publishTime
	protected java.util.Date publishTime;
	//表名。
	protected String tableName="";
	//设计类型(0,通过表生成，1，通过表单设计)
	@XmlAttribute
	protected int designType = 0;
	// 是否主表
	protected Short isMain = 0;
	
	//模板表对应id
	protected String templatesId;
	
	protected String tableid;
	
	public String getTableid() {
		return tableid;
	}
	public void setTableid(String tableid) {
		this.tableid = tableid;
	}


	/**
	 * 创建人
	 */
	protected String creator;

	public void setFormDefId(Long formDefId) 
	{
		this.formDefId = formDefId;
	}
	/**
	 * 返回 表单ID
	 * @return
	 */
	public Long getFormDefId() 
	{
		return formDefId;
	}

	public void setFormKey(String formKey) 
	{
		this.formKey = formKey;
	}
	/**
	 * 返回 key
	 * @return
	 */
	public String getFormKey() 
	{
		return formKey;
	}

	public Long getCategoryId() {
		return categoryId;
	}
	
	/**
	 * 分类名称
	 * @return
	 */
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	/**
	 * 分类id
	 * @param categoryId
	 */
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public void setSubject(String subject) 
	{
		this.subject = subject;
	}
	/**
	 * 返回 表单标题
	 * @return
	 */
	public String getSubject() 
	{
		return subject;
	}

	public void setFormDesc(String formDesc) 
	{
		this.formDesc = formDesc;
	}
	/**
	 * 返回 描述
	 * @return
	 */
	public String getFormDesc() 
	{
		return formDesc;
	}

	public void setHtml(String html) 
	{
		this.html = html;
	}
	/**
	 * 返回 定义html
	 * @return
	 */
	public String getHtml() 
	{
		return html;
	}

	public void setTemplate(String template) 
	{
		this.template = template;
	}
	/**
	 * 返回 freemarker模板
	 * @return
	 */
	public String getTemplate() 
	{
		return template;
	}

	public void setIsDefault(Short isDefault) 
	{
		this.isDefault = isDefault;
	}
	/**
	 * 返回 是否缺省
	 * @return
	 */
	public Short getIsDefault() 
	{
		return isDefault;
	}

	public void setTableId(Long tableId) 
	{
		this.tableId = tableId;
	}
	/**
	 * 返回 对应tableId
	 * @return
	 */
	public Long getTableId() 
	{
		return tableId;
	}

	public void setVersionNo(Integer versionNo) 
	{
		this.versionNo = versionNo;
	}
	/**
	 * 返回 版本号
	 * @return
	 */
	public Integer getVersionNo() 
	{
		return versionNo;
	}

	public void setIsPublished(Short isPublished) 
	{
		this.isPublished = isPublished;
	}
	/**
	 * 返回 isPublished
	 * @return
	 */
	public Short getIsPublished() 
	{
		return isPublished;
	}

	public void setPublishedBy(String publishedBy) 
	{
		this.publishedBy = publishedBy;
	}
	/**
	 * 返回 publishedBy
	 * @return
	 */
	public String getPublishedBy() 
	{
		return publishedBy;
	}

	public void setPublishTime(java.util.Date publishTime) 
	{
		this.publishTime = publishTime;
	}
	/**
	 * 返回 publishTime
	 * @return
	 */
	public java.util.Date getPublishTime() 
	{
		return publishTime;
	}

   
   	public String getTabTitle() {
		return tabTitle;
	}
	public void setTabTitle(String tabTitle) {
		this.tabTitle = tabTitle;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public int getDesignType() {
		return designType;
	}
	public void setDesignType(int designType) {
		this.designType = designType;
	}
	
	public void setIsMain(Short isMain) {
		this.isMain = isMain;
	}
	
	public Short getIsMain() {
		return isMain;
	}
	
	/**
	 * 模板表对应id
	 * @return
	 */
	public String getTemplatesId() {
		return templatesId;
	}
	/**
	 * 模板表对应id
	 * @param templatesId
	 */
	public void setTemplatesId(String templatesId) {
		this.templatesId = templatesId;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof BpmFormDef)) 
		{
			return false;
		}
		BpmFormDef rhs = (BpmFormDef) object;
		return new EqualsBuilder()
		.append(this.formDefId, rhs.formDefId)
		.append(this.formKey, rhs.formKey)
	
		.append(this.subject, rhs.subject)
		.append(this.formDesc, rhs.formDesc)
		.append(this.html, rhs.html)
		.append(this.template, rhs.template)
		.append(this.isDefault, rhs.isDefault)
		.append(this.tableId, rhs.tableId)
		.append(this.versionNo, rhs.versionNo)
		.append(this.isPublished, rhs.isPublished)
		.append(this.publishedBy, rhs.publishedBy)
		.append(this.publishTime, rhs.publishTime)
		.append(this.templatesId, rhs.templatesId)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.formDefId) 
		.append(this.formKey) 
	
		.append(this.subject) 
		.append(this.formDesc) 
		.append(this.html) 
		.append(this.template) 
		.append(this.isDefault) 
		.append(this.tableId) 
		.append(this.versionNo) 
		.append(this.isPublished) 
		.append(this.publishedBy) 
		.append(this.publishTime) 
		.append(this.templatesId)
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("formDefId", this.formDefId) 
		.append("formKey", this.formKey) 
	
		.append("subject", this.subject) 
		.append("formDesc", this.formDesc) 
		.append("html", this.html) 
		.append("template", this.template) 
		.append("isDefault", this.isDefault) 
		.append("tableId", this.tableId) 
		.append("versionNo", this.versionNo) 
		.append("isPublished", this.isPublished) 
		.append("publishedBy", this.publishedBy) 
		.append("publishTime", this.publishTime) 
		.append("templatesId",this.templatesId)
		.toString();
	}
   
	
	public Object clone()
	{
		BpmFormDef obj=null;
		try{
			obj=(BpmFormDef)super.clone();
		}catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return obj;
	}
  

}