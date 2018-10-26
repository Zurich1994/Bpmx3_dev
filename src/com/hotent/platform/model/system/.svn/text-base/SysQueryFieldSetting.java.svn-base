package com.hotent.platform.model.system;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;


/**
 * 对象功能:视图自定义设定 Model对象
 * 开发公司:宏天软件
 * 开发人员:ray
 * 创建时间:2015-06-08 16:02:04
 */
public class SysQueryFieldSetting extends BaseModel{
	// 主键
	protected Long id;
	// 视图ID
	protected Long viewId;
	// 字段ID
	protected String fieldName;
	// 允许排序
	protected Short sortAble;
	// 默认排序
	protected Short defaultSort;
	// 默认排序方向
	protected String sortSeq;
	// 对齐方式
	protected String align;
	// 是否冻结
	protected Short frozen;
	// 计算类型
	protected String summaryType;
	// 计算模版
	protected String summaryTemplate;
	//隐藏列
	protected Short hidden=0;
	
	
	//原字段。
	protected SysQueryMetaField metaField=new SysQueryMetaField();

	public void setId(Long id){
		this.id = id;
	}
	/**
	 * 返回 主键
	 * @return
	 */
	public Long getId() {
		return this.id;
	}
	public void setViewId(Long viewId){
		this.viewId = viewId;
	}
	/**
	 * 返回 视图ID
	 * @return
	 */
	public Long getViewId() {
		return this.viewId;
	}
	
	public void setSortAble(Short sortAble){
		this.sortAble = sortAble;
	}
	/**
	 * 返回 允许排序
	 * @return
	 */
	public Short getSortAble() {
		return this.sortAble;
	}
	public void setDefaultSort(Short defaultSort){
		this.defaultSort = defaultSort;
	}
	/**
	 * 返回 默认排序
	 * @return
	 */
	public Short getDefaultSort() {
		return this.defaultSort;
	}
	public void setSortSeq(String sortSeq){
		this.sortSeq = sortSeq;
	}
	/**
	 * 返回 默认排序方向
	 * @return
	 */
	public String getSortSeq() {
		return this.sortSeq;
	}
	public void setAlign(String align){
		this.align = align;
	}
	/**
	 * 返回 对齐方式
	 * @return
	 */
	public String getAlign() {
		return this.align;
	}
	public void setFrozen(Short frozen){
		this.frozen = frozen;
	}
	/**
	 * 返回 是否冻结
	 * @return
	 */
	public Short getFrozen() {
		return this.frozen;
	}
	
	public void setSummaryType(String summaryType){
		this.summaryType = summaryType;
	}
	/**
	 * 返回 计算类型
	 * @return
	 */
	public String getSummaryType() {
		return this.summaryType;
	}
	public void setSummaryTemplate(String summaryTemplate){
		this.summaryTemplate = summaryTemplate;
	}
	/**
	 * 返回 计算模版
	 * @return
	 */
	public String getSummaryTemplate() {
		return this.summaryTemplate;
	}
	

   	public SysQueryMetaField getMetaField() {
		return metaField;
	}
	public void setMetaField(SysQueryMetaField metaField) {
		this.metaField = metaField;
	}
	
	public Short getHidden() {
		return hidden;
	}
	public void setHidden(Short hidden) {
		this.hidden = hidden;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof SysQueryFieldSetting)) 
		{
			return false;
		}
		SysQueryFieldSetting rhs = (SysQueryFieldSetting) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.viewId, rhs.viewId)
		.append(this.sortAble, rhs.sortAble)
		.append(this.defaultSort, rhs.defaultSort)
		.append(this.sortSeq, rhs.sortSeq)
		.append(this.align, rhs.align)
		.append(this.frozen, rhs.frozen)
		.append(this.summaryType, rhs.summaryType)
		.append(this.summaryTemplate, rhs.summaryTemplate)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id) 
		.append(this.viewId) 
		.append(this.sortAble) 
		.append(this.defaultSort) 
		.append(this.sortSeq) 
		.append(this.align) 
		.append(this.frozen) 
		.append(this.summaryType) 
		.append(this.summaryTemplate) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("viewId", this.viewId) 
		.append("sortAble", this.sortAble) 
		.append("defaultSort", this.defaultSort) 
		.append("sortSeq", this.sortSeq) 
		.append("align", this.align) 
		.append("frozen", this.frozen) 
		.append("summaryType", this.summaryType) 
		.append("summaryTemplate", this.summaryTemplate) 
		.toString();
	}
   
  

}