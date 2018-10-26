package com.hotent.platform.model.worktime;

import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:系统日历 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-02-20 14:57:32
 */
public class SysCalendar extends BaseModel
{
	// 主键
	protected Long id=0L;
	// 日历名称
	protected String name;
	// memo
	protected String memo;
	//是否默认日历
	protected Short isDefault=0;

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

	public void setName(String name) 
	{
		this.name = name;
	}
	/**
	 * 返回 日历名称
	 * @return
	 */
	public String getName() 
	{
		return name;
	}

	public void setMemo(String memo) 
	{
		this.memo = memo;
	}
	/**
	 * 返回 memo
	 * @return
	 */
	public String getMemo() 
	{
		return memo;
	}

	public void setIsDefault(Short isDefault) {
		this.isDefault = isDefault;
	}
	
	public Short getIsDefault() {
		return isDefault;
	}
	
	
   
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof SysCalendar)) 
		{
			return false;
		}
		SysCalendar rhs = (SysCalendar) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.name, rhs.name)
		.append(this.memo, rhs.memo)
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
		.append(this.memo) 
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
		.append("memo", this.memo) 
		.toString();
	}
   
  

}