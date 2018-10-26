package com.hotent.platform.model.system;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:组织结构类型 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-11-27 09:55:21
 */
public class SysOrgType extends BaseModel
{
	// 主键
	protected Long  id;
	// 维度ID
	protected Long  demId;
	// 名称
	protected String  name;
	// 级别
	protected Long  levels;
	// 备注
	protected String  memo;
	// 头像
	protected String  icon;
	
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
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
		return this.id;
	}
	public void setDemId(Long demId) 
	{
		this.demId = demId;
	}
	/**
	 * 返回 维度ID
	 * @return
	 */
	public Long getDemId() 
	{
		return this.demId;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	/**
	 * 返回 名称
	 * @return
	 */
	public String getName() 
	{
		return this.name;
	}
	public void setLevels(Long levels) 
	{
		this.levels = levels;
	}
	/**
	 * 返回 级别
	 * @return
	 */
	public Long getLevels() 
	{
		return this.levels;
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
		return this.memo;
	}

   
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof SysOrgType)) 
		{
			return false;
		}
		SysOrgType rhs = (SysOrgType) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.demId, rhs.demId)
		.append(this.name, rhs.name)
		.append(this.levels, rhs.levels)
		.append(this.memo, rhs.memo)
		.append(this.icon, rhs.icon)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id) 
		.append(this.demId) 
		.append(this.name) 
		.append(this.levels) 
		.append(this.memo)
		.append(this.icon)
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("demId", this.demId) 
		.append("name", this.name) 
		.append("levels", this.levels) 
		.append("memo", this.memo) 
		.append("icon",this.icon)
		.toString();
	}
   
  

}