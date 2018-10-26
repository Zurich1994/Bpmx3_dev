package com.hotent.devicein.model.deviceinpack;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:设备基本信息表 Model对象
 */
public class DeviceInfomation extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *设备编号
	 */
	protected String  dev_id;
	/**
	 *设备名称
	 */
	protected String  dev_Name;
	/**
	 *机房编号
	 */
	protected String  room_ID;
	/**
	 *机柜编号
	 */
	protected String  cabinet_num;
	/**
	 *设备归属
	 */
	protected String  sbgs;
	/**
	 *设备类型
	 */
	protected String  dev_type;
	/**
	 *设备品牌
	 */
	protected String  dev_brand;
	/**
	 *设备型号
	 */
	protected String  dev_model;
	/**
	 *设备序列号
	 */
	protected String  dev_sequence;
	/**
	 *设备配置
	 */
	protected String  dev_config;
	/**
	 *功能描述
	 */
	protected String  fun_Info;
	/**
	 *设备使用状态
	 */
	protected String  state;
	/**
	 *设备出厂日期
	 */
	protected java.util.Date  exFactory_Date;
	/**
	 *设备投入使用日期
	 */
	protected java.util.Date  using_Date;
	/**
	 *设备报废日期
	 */
	protected java.util.Date  retirement_Date;
	/**
	 *使用人
	 */
	protected String  responsible_Person;
	/**
	 *使用人联系方式
	 */
	protected String  contact;
	/**
	 *项目名称
	 */
	protected String  project_Name;
	/**
	 *录入时间
	 */
	protected java.util.Date  insert_Time;
	/**
	 *备注
	 */
	protected String  remark;
	/**
	 *流程定义ID
	 */
	protected String  actdefid;
	/**
	 *流程节点ID
	 */
	protected String  nodeid;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setDev_id(String dev_id) 
	{
		this.dev_id = dev_id;
	}
	/**
	 * 返回 设备编号
	 * @return
	 */
	public String getDev_id() 
	{
		return this.dev_id;
	}
	public void setDev_Name(String dev_Name) 
	{
		this.dev_Name = dev_Name;
	}
	/**
	 * 返回 设备名称
	 * @return
	 */
	public String getDev_Name() 
	{
		return this.dev_Name;
	}
	public void setRoom_ID(String room_ID) 
	{
		this.room_ID = room_ID;
	}
	/**
	 * 返回 机房编号
	 * @return
	 */
	public String getRoom_ID() 
	{
		return this.room_ID;
	}
	public void setCabinet_num(String cabinet_num) 
	{
		this.cabinet_num = cabinet_num;
	}
	/**
	 * 返回 机柜编号
	 * @return
	 */
	public String getCabinet_num() 
	{
		return this.cabinet_num;
	}
	public void setSbgs(String sbgs) 
	{
		this.sbgs = sbgs;
	}
	/**
	 * 返回 设备归属
	 * @return
	 */
	public String getSbgs() 
	{
		return this.sbgs;
	}
	public void setDev_type(String dev_type) 
	{
		this.dev_type = dev_type;
	}
	/**
	 * 返回 设备类型
	 * @return
	 */
	public String getDev_type() 
	{
		return this.dev_type;
	}
	public void setDev_brand(String dev_brand) 
	{
		this.dev_brand = dev_brand;
	}
	/**
	 * 返回 设备品牌
	 * @return
	 */
	public String getDev_brand() 
	{
		return this.dev_brand;
	}
	public void setDev_model(String dev_model) 
	{
		this.dev_model = dev_model;
	}
	/**
	 * 返回 设备型号
	 * @return
	 */
	public String getDev_model() 
	{
		return this.dev_model;
	}
	public void setDev_sequence(String dev_sequence) 
	{
		this.dev_sequence = dev_sequence;
	}
	/**
	 * 返回 设备序列号
	 * @return
	 */
	public String getDev_sequence() 
	{
		return this.dev_sequence;
	}
	public void setDev_config(String dev_config) 
	{
		this.dev_config = dev_config;
	}
	/**
	 * 返回 设备配置
	 * @return
	 */
	public String getDev_config() 
	{
		return this.dev_config;
	}
	public void setFun_Info(String fun_Info) 
	{
		this.fun_Info = fun_Info;
	}
	/**
	 * 返回 功能描述
	 * @return
	 */
	public String getFun_Info() 
	{
		return this.fun_Info;
	}
	public void setState(String state) 
	{
		this.state = state;
	}
	/**
	 * 返回 设备使用状态
	 * @return
	 */
	public String getState() 
	{
		return this.state;
	}
	public void setExFactory_Date(java.util.Date exFactory_Date) 
	{
		this.exFactory_Date = exFactory_Date;
	}
	/**
	 * 返回 设备出厂日期
	 * @return
	 */
	public java.util.Date getExFactory_Date() 
	{
		return this.exFactory_Date;
	}
	public void setUsing_Date(java.util.Date using_Date) 
	{
		this.using_Date = using_Date;
	}
	/**
	 * 返回 设备投入使用日期
	 * @return
	 */
	public java.util.Date getUsing_Date() 
	{
		return this.using_Date;
	}
	public void setRetirement_Date(java.util.Date retirement_Date) 
	{
		this.retirement_Date = retirement_Date;
	}
	/**
	 * 返回 设备报废日期
	 * @return
	 */
	public java.util.Date getRetirement_Date() 
	{
		return this.retirement_Date;
	}
	public void setResponsible_Person(String responsible_Person) 
	{
		this.responsible_Person = responsible_Person;
	}
	/**
	 * 返回 使用人
	 * @return
	 */
	public String getResponsible_Person() 
	{
		return this.responsible_Person;
	}
	public void setContact(String contact) 
	{
		this.contact = contact;
	}
	/**
	 * 返回 使用人联系方式
	 * @return
	 */
	public String getContact() 
	{
		return this.contact;
	}
	public void setProject_Name(String project_Name) 
	{
		this.project_Name = project_Name;
	}
	/**
	 * 返回 项目名称
	 * @return
	 */
	public String getProject_Name() 
	{
		return this.project_Name;
	}
	public void setInsert_Time(java.util.Date insert_Time) 
	{
		this.insert_Time = insert_Time;
	}
	/**
	 * 返回 录入时间
	 * @return
	 */
	public java.util.Date getInsert_Time() 
	{
		return this.insert_Time;
	}
	public void setRemark(String remark) 
	{
		this.remark = remark;
	}
	/**
	 * 返回 备注
	 * @return
	 */
	public String getRemark() 
	{
		return this.remark;
	}
	public void setActdefid(String actdefid) 
	{
		this.actdefid = actdefid;
	}
	/**
	 * 返回 流程定义ID
	 * @return
	 */
	public String getActdefid() 
	{
		return this.actdefid;
	}
	public void setNodeid(String nodeid) 
	{
		this.nodeid = nodeid;
	}
	/**
	 * 返回 流程节点ID
	 * @return
	 */
	public String getNodeid() 
	{
		return this.nodeid;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof DeviceInfomation)) 
		{
			return false;
		}
		DeviceInfomation rhs = (DeviceInfomation) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.id, rhs.id)
		.append(this.dev_id, rhs.dev_id)
		.append(this.dev_Name, rhs.dev_Name)
		.append(this.room_ID, rhs.room_ID)
		.append(this.cabinet_num, rhs.cabinet_num)
		.append(this.sbgs, rhs.sbgs)
		.append(this.dev_type, rhs.dev_type)
		.append(this.dev_brand, rhs.dev_brand)
		.append(this.dev_model, rhs.dev_model)
		.append(this.dev_sequence, rhs.dev_sequence)
		.append(this.dev_config, rhs.dev_config)
		.append(this.fun_Info, rhs.fun_Info)
		.append(this.state, rhs.state)
		.append(this.exFactory_Date, rhs.exFactory_Date)
		.append(this.using_Date, rhs.using_Date)
		.append(this.retirement_Date, rhs.retirement_Date)
		.append(this.responsible_Person, rhs.responsible_Person)
		.append(this.contact, rhs.contact)
		.append(this.project_Name, rhs.project_Name)
		.append(this.insert_Time, rhs.insert_Time)
		.append(this.remark, rhs.remark)
		.append(this.actdefid, rhs.actdefid)
		.append(this.nodeid, rhs.nodeid)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id)
		.append(this.id) 
		.append(this.dev_id) 
		.append(this.dev_Name) 
		.append(this.room_ID) 
		.append(this.cabinet_num) 
		.append(this.sbgs) 
		.append(this.dev_type) 
		.append(this.dev_brand) 
		.append(this.dev_model) 
		.append(this.dev_sequence) 
		.append(this.dev_config) 
		.append(this.fun_Info) 
		.append(this.state) 
		.append(this.exFactory_Date) 
		.append(this.using_Date) 
		.append(this.retirement_Date) 
		.append(this.responsible_Person) 
		.append(this.contact) 
		.append(this.project_Name) 
		.append(this.insert_Time) 
		.append(this.remark) 
		.append(this.actdefid) 
		.append(this.nodeid) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("id", this.id) 
		.append("dev_id", this.dev_id) 
		.append("dev_Name", this.dev_Name) 
		.append("room_ID", this.room_ID) 
		.append("cabinet_num", this.cabinet_num) 
		.append("sbgs", this.sbgs) 
		.append("dev_type", this.dev_type) 
		.append("dev_brand", this.dev_brand) 
		.append("dev_model", this.dev_model) 
		.append("dev_sequence", this.dev_sequence) 
		.append("dev_config", this.dev_config) 
		.append("fun_Info", this.fun_Info) 
		.append("state", this.state) 
		.append("exFactory_Date", this.exFactory_Date) 
		.append("using_Date", this.using_Date) 
		.append("retirement_Date", this.retirement_Date) 
		.append("responsible_Person", this.responsible_Person) 
		.append("contact", this.contact) 
		.append("project_Name", this.project_Name) 
		.append("insert_Time", this.insert_Time) 
		.append("remark", this.remark) 
		.append("actdefid", this.actdefid) 
		.append("nodeid", this.nodeid) 
		.toString();
	}

}
