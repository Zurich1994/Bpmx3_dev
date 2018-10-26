package com.hotent.monitorQuota.model.monitorQuotaPac;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:设备监控指标表 Model对象
 */
public class MonitorQuota extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *指标名称
	 */
	protected String  name;
	/**
	 *设备类型
	 */
	protected String  deviceType;
	/**
	 *指标单位
	 */
	protected String  unit;
	/**
	 *关联属性名
	 */
	protected String  propertyName;
	/**
	 *关联属性值
	 */
	protected String  propertyValue;
	/**
	 *监控类型
	 */
	protected String  monitorType;
	/**
	 *对象标识符
	 */
	protected String  OID;
	/**
	 *监控类名
	 */
	protected String  className;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	/**
	 * 返回 指标名称
	 * @return
	 */
	public String getName() 
	{
		return this.name;
	}
	public void setDeviceType(String deviceType) 
	{
		this.deviceType = deviceType;
	}
	/**
	 * 返回 设备类型
	 * @return
	 */
	public String getDeviceType() 
	{
		return this.deviceType;
	}
	public void setUnit(String unit) 
	{
		this.unit = unit;
	}
	/**
	 * 返回 指标单位
	 * @return
	 */
	public String getUnit() 
	{
		return this.unit;
	}
	public void setPropertyName(String propertyName) 
	{
		this.propertyName = propertyName;
	}
	/**
	 * 返回 关联属性名
	 * @return
	 */
	public String getPropertyName() 
	{
		return this.propertyName;
	}
	public void setPropertyValue(String propertyValue) 
	{
		this.propertyValue = propertyValue;
	}
	/**
	 * 返回 关联属性值
	 * @return
	 */
	public String getPropertyValue() 
	{
		return this.propertyValue;
	}
	public void setMonitorType(String monitorType) 
	{
		this.monitorType = monitorType;
	}
	/**
	 * 返回 监控类型
	 * @return
	 */
	public String getMonitorType() 
	{
		return this.monitorType;
	}
	public void setOID(String OID) 
	{
		this.OID = OID;
	}
	/**
	 * 返回 对象标识符
	 * @return
	 */
	public String getOID() 
	{
		return this.OID;
	}
	public void setClassName(String className) 
	{
		this.className = className;
	}
	/**
	 * 返回 监控类名
	 * @return
	 */
	public String getClassName() 
	{
		return this.className;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof MonitorQuota)) 
		{
			return false;
		}
		MonitorQuota rhs = (MonitorQuota) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.name, rhs.name)
		.append(this.deviceType, rhs.deviceType)
		.append(this.unit, rhs.unit)
		.append(this.propertyName, rhs.propertyName)
		.append(this.propertyValue, rhs.propertyValue)
		.append(this.monitorType, rhs.monitorType)
		.append(this.OID, rhs.OID)
		.append(this.className, rhs.className)
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
		.append(this.deviceType) 
		.append(this.unit) 
		.append(this.propertyName) 
		.append(this.propertyValue) 
		.append(this.monitorType) 
		.append(this.OID) 
		.append(this.className) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("name", this.name) 
		.append("deviceType", this.deviceType) 
		.append("unit", this.unit) 
		.append("propertyName", this.propertyName) 
		.append("propertyValue", this.propertyValue) 
		.append("monitorType", this.monitorType) 
		.append("OID", this.OID) 
		.append("className", this.className) 
		.toString();
	}

}
