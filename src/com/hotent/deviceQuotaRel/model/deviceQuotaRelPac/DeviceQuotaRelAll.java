package com.hotent.deviceQuotaRel.model.deviceQuotaRelPac;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import com.hotent.monitorDevice.model.monitorDevice.MonitorDevice;
import com.hotent.monitorQuota.model.monitorQuotaPac.MonitorQuota;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:设备指标关联表 Model对象
 */
public class DeviceQuotaRelAll extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *设备编号
	 */
	protected MonitorDevice  device;
	/**
	 *指标编号
	 */
	protected MonitorQuota  quota;
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
	
	public void setDevice(MonitorDevice device) 
	{
		this.device = device;
	}
	/**
	 * 返回 设备编号
	 * @return
	 */
	public MonitorDevice getDevice() 
	{
		return this.device;
	}
	public void setQuota(MonitorQuota quota) 
	{
		this.quota = quota;
	}
	/**
	 * 返回 指标编号
	 * @return
	 */
	public MonitorQuota getQuota() 
	{
		return this.quota;
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

}
