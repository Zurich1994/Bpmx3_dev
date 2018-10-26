package com.hotent.platform.model.system;

import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:桌面布局 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-03-20 16:39:01
 */
public class DesktopLayout extends BaseModel
{
	// 布局ID
	protected Long id;
	// 布局名
	protected String name;
	// 列数
	protected Short cols;
	// 宽度
	protected String width;
	// 备注
	protected String memo;
	
	protected String width1;
	protected String width2;
	protected String width3;
	// 默认布局
	protected Integer isDefault;

	public void setId(Long id) 
	{
		this.id = id;
	}
	/**
	 * 返回 布局ID
	 * @return
	 */
	public Long getId() 
	{
		return id;
	}

	public void setName(String name) 
	{
		this.name = name;
	}
	/**
	 * 返回 布局名
	 * @return
	 */
	public String getName() 
	{
		return name;
	}

	public void setCols(Short cols) 
	{
		this.cols = cols;
	}
	/**
	 * 返回 列数
	 * @return
	 */
	public Short getCols() 
	{
		return cols;
	}

	public void setWidth(String width) 
	{
		this.width = width;
	}
	/**
	 * 返回 宽度
	 * @return
	 */
	public String getWidth() 
	{
		return width;
	}

	public void setMemo(String memo) 
	{
		this.memo = memo;
	}
	/**
	 * 返回 备注
	 * @return
	 */
	public String getMemo() 
	{
		return memo;
	}

	public void setIsDefault(Integer isDefault) 
	{
		this.isDefault = isDefault;
	}
	/**
	 * 返回 默认布局
	 * @return
	 */
	public Integer getIsDefault() 
	{
		return isDefault;
	}

	public String getWidth1() {
		return width1;
	}
	public void setWidth1(String width1) {
		this.width1 = width1;
	}
	public String getWidth2() {
		return width2;
	}
	public void setWidth2(String width2) {
		this.width2 = width2;
	}
	public String getWidth3() {
		return width3;
	}
	public void setWidth3(String width3) {
		this.width3 = width3;
	}
   
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof DesktopLayout)) 
		{
			return false;
		}
		DesktopLayout rhs = (DesktopLayout) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.name, rhs.name)
		.append(this.cols, rhs.cols)
		.append(this.width, rhs.width)
		.append(this.memo, rhs.memo)
		.append(this.isDefault, rhs.isDefault)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id) 
		.append(this.name) 
		.append(this.cols) 
		.append(this.width) 
		.append(this.memo) 
		.append(this.isDefault) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("name", this.name) 
		.append("cols", this.cols) 
		.append("width", this.width) 
		.append("memo", this.memo) 
		.append("isDefault", this.isDefault) 
		.toString();
	}
   
  

}