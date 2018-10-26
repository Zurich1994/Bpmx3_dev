package com.hotent.application.model.application;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import com.hotent.core.model.BaseModel;
/**
 * 对象功能:应用表 Model对象
 */
public class Application extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *应用名
	 */
	protected String  applicationName;
	/**
	 *类型
	 */
	protected String  type;
	/**
	 *类型
	 */
	protected String  typeName;
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	/**
	 *可计算性
	 */
	protected String  computability;
	
	
	protected String equipmentStatus;
	
	public String getEquipmentStatus() {
		return equipmentStatus;
	}
	public void setEquipmentStatus(String equipmentStatus) {
		this.equipmentStatus = equipmentStatus;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setApplicationName(String applicationName) 
	{
		this.applicationName = applicationName;
	}
	/**
	 * 返回 应用名
	 * @return
	 */
	public String getApplicationName() 
	{
		return this.applicationName;
	}
	public void setType(String type) 
	{
		this.type = type;
	}
	/**
	 * 返回 类型
	 * @return
	 */
	public String getType() 
	{
		return this.type;
	}
	public void setComputability(String computability) 
	{
		this.computability = computability;
	}
	/**
	 * 返回 可计算性
	 * @return
	 */
	public String getComputability() 
	{
		return this.computability;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof Application)) 
		{
			return false;
		}
		Application rhs = (Application) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.applicationName, rhs.applicationName)
		.append(this.type, rhs.type)
		.append(this.typeName, rhs.typeName)
		.append(this.computability, rhs.computability)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id)
		.append(this.applicationName) 
		.append(this.type) 
		.append(this.typeName) 
		.append(this.computability) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("applicationName", this.applicationName) 
		.append("type", this.type) 
		.append("typeName", this.typeName) 
		.append("computability", this.computability) 
		.toString();
	}

}