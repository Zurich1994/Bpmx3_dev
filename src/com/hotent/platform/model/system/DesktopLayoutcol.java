package com.hotent.platform.model.system;

import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;
/**
 * 对象功能:桌面栏目管理表 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-03-20 16:39:01
 */
public class DesktopLayoutcol extends BaseModel
{
	// 主键
	protected Long id;
	// 布局ID
	protected Long layoutId;
	// 栏目ID
	protected Long columnId;
	// 列
	protected Integer col;
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
	
	//栏目列表
	protected List<DesktopColumn> desktopColumnList;
	
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

	public String getColumnUrl() {
		return columnUrl;
	}
	public void setColumnUrl(String columnUrl) {
		this.columnUrl = columnUrl;
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

	public void setCol(Integer col) 
	{
		this.col = col;
	}
	/**
	 * 返回 列
	 * @return
	 */
	public Integer getCol() 
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

   
   	public String getLayoutName() {
		return layoutName;
	}
	public void setLayoutName(String layoutName) {
		this.layoutName = layoutName;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getWidths() {
		return widths;
	}
	public void setWidths(String widths) {
		this.widths = widths;
	}
	
	
	public List<DesktopColumn> getDesktopColumnList() {
		return desktopColumnList;
	}
	public void setDesktopColumnList(List<DesktopColumn> desktopColumnList) {
		this.desktopColumnList = desktopColumnList;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof DesktopLayoutcol)) 
		{
			return false;
		}
		DesktopLayoutcol rhs = (DesktopLayoutcol) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
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
		.append("layoutId", this.layoutId) 
		.append("columnId", this.columnId) 
		.append("col", this.col) 
		.append("sn", this.sn) 
		.toString();
	}
   
  

}