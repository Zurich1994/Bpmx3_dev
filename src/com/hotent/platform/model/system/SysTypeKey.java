package com.hotent.platform.model.system;

import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:系统分类键定义 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2012-03-10 10:18:36
 */
public class SysTypeKey extends BaseModel
{
	
	// typeId
	protected Long typeId=0L;
	// 系统分类Key  该值唯一
	protected String typeKey;
	// 系统分类名称
	protected String typeName;
	// 序号
	protected Integer sn=0;
	// 是否可以修改
	protected Integer flag=0;//1为不可以修改，其他为可以修改
	// 是否可以修改
	protected Integer type=0;//0为平铺，1为树形
	
	public Integer getFlag()
	{
		return flag;
	}
	public void setFlag(Integer flag)
	{
		this.flag = flag;
	}
	public Integer getType()
	{
		return type;
	}
	public void setType(Integer type)
	{
		this.type = type;
	}
	public void setTypeId(Long typeId) 
	{
		this.typeId = typeId;
	}
	/**
	 * 返回 typeId
	 * @return
	 */
	public Long getTypeId() 
	{
		return typeId;
	}

	public void setTypeKey(String typeKey) 
	{
		this.typeKey = typeKey;
	}
	/**
	 * 返回 系统分类Key  该值唯一
	 * @return
	 */
	public String getTypeKey() 
	{
		return typeKey;
	}

	public void setTypeName(String typeName) 
	{
		this.typeName = typeName;
	}
	/**
	 * 返回 系统分类名称
	 * @return
	 */
	public String getTypeName() 
	{
		return typeName;
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

   
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof SysTypeKey)) 
		{
			return false;
		}
		SysTypeKey rhs = (SysTypeKey) object;
		return new EqualsBuilder()
		.append(this.typeId, rhs.typeId)
		.append(this.typeKey, rhs.typeKey)
		.append(this.typeName, rhs.typeName)
		.append(this.sn, rhs.sn)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.typeId) 
		.append(this.typeKey) 
		.append(this.typeName) 
		.append(this.sn) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("typeId", this.typeId) 
		.append("typeKey", this.typeKey) 
		.append("typeName", this.typeName) 
		.append("sn", this.sn) 
		.toString();
	}
   
  

}