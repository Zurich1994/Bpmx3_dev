package com.hotent.platform.model.bpm;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:代理的流程列表 Model对象
 * 开发公司:SF
 * 开发人员:xxx
 * 创建时间:2013-04-29 11:15:10
 */
public class AgentDef extends BaseModel
{
	// 主键
	protected Long  id;
	// 代理设定ID
	protected Long  settingid;
	// 流程KEY
	protected String  flowkey;
	// 流程名称
	protected String  flowname;
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
	public void setSettingid(Long settingid) 
	{
		this.settingid = settingid;
	}
	/**
	 * 返回 代理设定ID
	 * @return
	 */
	public Long getSettingid() 
	{
		return this.settingid;
	}
	public void setFlowkey(String flowkey) 
	{
		this.flowkey = flowkey;
	}
	/**
	 * 返回 流程KEY
	 * @return
	 */
	public String getFlowkey() 
	{
		return this.flowkey;
	}
	public void setFlowname(String flowname) 
	{
		this.flowname = flowname;
	}
	/**
	 * 返回 流程名称
	 * @return
	 */
	public String getFlowname() 
	{
		return this.flowname;
	}


   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof AgentDef)) 
		{
			return false;
		}
		AgentDef rhs = (AgentDef) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.settingid, rhs.settingid)
		.append(this.flowkey, rhs.flowkey)
		.append(this.flowname, rhs.flowname)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id) 
		.append(this.settingid) 
		.append(this.flowkey) 
		.append(this.flowname) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("settingid", this.settingid) 
		.append("flowkey", this.flowkey) 
		.append("flowname", this.flowname) 
		.toString();
	}
   
  

}