package com.hotent.deviceQuotaRel.model.deviceQuotaRelPac;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:设备指标关联表 Model对象
 */
public class DeviceQuotaRel extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *设备编号
	 */
	protected String  device_id;
	/**
	 *指标编号
	 */
	protected String  quota_id;
	/**
	 *监控频率
	 */
	protected Long  monitor_freq;
	
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
	public void setMonitor_freq(Long monitor_freq) 
	{
		this.monitor_freq = monitor_freq;
	}
	/**
	 * 返回 监控频率
	 * @return
	 */
	public Long getMonitor_freq() 
	{
		return this.monitor_freq;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof DeviceQuotaRel)) 
		{
			return false;
		}
		DeviceQuotaRel rhs = (DeviceQuotaRel) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.device_id, rhs.device_id)
		.append(this.quota_id, rhs.quota_id)
		.append(this.monitor_freq, rhs.monitor_freq)
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
		.append(this.monitor_freq) 
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
		.append("monitor_freq", this.monitor_freq) 
		.toString();
	}

}
