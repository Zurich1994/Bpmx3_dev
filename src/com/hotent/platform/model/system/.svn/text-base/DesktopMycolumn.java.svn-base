package com.hotent.platform.model.system;

import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:桌面个人栏目 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-03-20 16:39:01
 */
public class DesktopMycolumn extends BaseModel
{
	// 主键
	protected Long id;
	// 用户ID
	protected Long userId;
	// 布局ID
	protected Long layoutId;
	// 栏目ID
	protected Long columnId;
	// 列
	protected Short col;
	// 序号
	protected Integer sn;

	//栏目名称
	protected String columnName;
	//布局名称
	protected String layoutName;
	//栏目宽度
	protected String widths;
	//栏目链接
	protected String columnUrl;
	//栏目获取数据的方法名
	protected String servicemethod;
	//栏目数据html模板
	protected String columnHtml;
	//自定义查询的别名
	protected String queryAlias;
	//数据加载方式
	protected Integer methodType=0;
	
	public void setId(Long id) 
	{
		this.id = id;
	}
	/**
	 * 返回 主键
	 * @return
	 */
	public Long getId() 
	{
		return id;
	}

	public void setUserId(Long userId) 
	{
		this.userId = userId;
	}
	/**
	 * 返回 用户ID
	 * @return
	 */
	public Long getUserId() 
	{
		return userId;
	}

	public void setLayoutId(Long layoutId) 
	{
		this.layoutId = layoutId;
	}
	/**
	 * 返回 布局ID
	 * @return
	 */
	public Long getLayoutId() 
	{
		return layoutId;
	}

	public void setColumnId(Long columnId) 
	{
		this.columnId = columnId;
	}
	/**
	 * 返回 栏目ID
	 * @return
	 */
	public Long getColumnId() 
	{
		return columnId;
	}

	public void setCol(Short col) 
	{
		this.col = col;
	}
	/**
	 * 返回 列
	 * @return
	 */
	public Short getCol() 
	{
		return col;
	}

	public void setSn(Integer sn) 
	{
		this.sn = sn;
	}
	/**
	 * 返回 序号
	 * @return
	 */
	public Integer getSn() 
	{
		return sn;
	}
	
   	public String getQueryAlias() {
		return queryAlias;
	}
	public void setQueryAlias(String queryAlias) {
		this.queryAlias = queryAlias;
	}
	public Integer getMethodType() {
		return methodType;
	}
	public void setMethodType(Integer methodType) {
		this.methodType = methodType;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getLayoutName() {
		return layoutName;
	}
	public void setLayoutName(String layoutName) {
		this.layoutName = layoutName;
	}
	public String getWidths() {
		return widths;
	}
	public void setWidths(String widths) {
		this.widths = widths;
	}
	public String getColumnUrl() {
		return columnUrl;
	}
	public void setColumnUrl(String columnUrl) {
		this.columnUrl = columnUrl;
	}	
	public String getServicemethod() {
		return servicemethod;
	}
	public void setServicemethod(String servicemethod) {
		this.servicemethod = servicemethod;
	}
	public String getColumnHtml() {
		return columnHtml;
	}
	public void setColumnHtml(String columnHtml) {
		this.columnHtml = columnHtml;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof DesktopMycolumn)) 
		{
			return false;
		}
		DesktopMycolumn rhs = (DesktopMycolumn) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.userId, rhs.userId)
		.append(this.layoutId, rhs.layoutId)
		.append(this.columnId, rhs.columnId)
		.append(this.col, rhs.col)
		.append(this.sn, rhs.sn)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id) 
		.append(this.userId) 
		.append(this.layoutId) 
		.append(this.columnId) 
		.append(this.col) 
		.append(this.sn) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("userId", this.userId) 
		.append("layoutId", this.layoutId) 
		.append("columnId", this.columnId) 
		.append("col", this.col) 
		.append("sn", this.sn) 
		.toString();
	}
}