package com.hotent.monitorRecords.model.monitorRecordsPac;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:监控记录表 Model对象
 */
public class MonitorRecords extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *主机名
	 */
	protected String  device_name;
	/**
	 *指标名
	 */
	protected String  quota_name;
	/**
	 *设备编号
	 */
	protected String  device_id;
	/**
	 *指标编号
	 */
	protected String  quota_id;
	/**
	 *指标监控值
	 */
	protected String  monitorValue;
	/**
	 *监控时间
	 */
	protected java.util.Date  monitorTime;
	
	
	public String getDevice_name() {
		return device_name;
	}
	public void setDevice_name(String deviceName) {
		device_name = deviceName;
	}
	public String getQuota_name() {
		return quota_name;
	}
	public void setQuota_name(String quotaName) {
		quota_name = quotaName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setDevice_id(String device_id) 
	{
		this.device_id = device_id;
	}
	/**
	 * 返回 设备编号
	 * @return
	 */
	public String getDevice_id() 
	{
		return this.device_id;
	}
	public void setQuota_id(String quota_id) 
	{
		this.quota_id = quota_id;
	}
	/**
	 * 返回 指标编号
	 * @return
	 */
	public String getQuota_id() 
	{
		return this.quota_id;
	}
	public void setMonitorValue(String monitorValue) 
	{
		this.monitorValue = monitorValue;
	}
	/**
	 * 返回 指标监控值
	 * @return
	 */
	public String getMonitorValue() 
	{
		return this.monitorValue;
	}
	public void setMonitorTime(java.util.Date monitorTime) 
	{
		this.monitorTime = monitorTime;
	}
	/**
	 * 返回 监控时间
	 * @return
	 */
	public java.util.Date getMonitorTime() 
	{
		return this.monitorTime;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof MonitorRecords)) 
		{
			return false;
		}
		MonitorRecords rhs = (MonitorRecords) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.device_id, rhs.device_id)
		.append(this.quota_id, rhs.quota_id)
		.append(this.monitorValue, rhs.monitorValue)
		.append(this.monitorTime, rhs.monitorTime)
		.append(this.device_name, rhs.device_name)
		.append(this.quota_name, rhs.quota_name)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id)
		.append(this.device_id) 
		.append(this.quota_id) 
		.append(this.monitorValue) 
		.append(this.monitorTime) 
		.append(this.device_name)
		.append(this.quota_name)
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("device_id", this.device_id) 
		.append("quota_id", this.quota_id) 
		.append("monitorValue", this.monitorValue) 
		.append("monitorTime", this.monitorTime) 
		.append("device_name", this.device_name)
		.append("quota_name", this.quota_name)
		.toString();
	}

}
