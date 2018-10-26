package com.hotent.platform.model.bpm;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:代理设定 Model对象
 * 开发公司:hotent
 * 开发人员:xxx
 * 创建时间:2013-04-29 11:15:10
 */
public class AgentSetting extends BaseModel
{
	
	/**
	 * 代理类型：全权代理
	 */
	public static int AUTHTYPE_GENERAL   	=0;
	/**
	 * 代理类型：部分代理
	 */
	public static int AUTHTYPE_PARTIAL   	=1;
	/**
	 * 代理类型：条件代理
	 */
	public static int AUTHTYPE_CONDITION   =2;
	
	
	
	/**
	 * 状态：启用
	 */
	public static Long ENABLED_YES 				= 1L;
	/**
	 * 状态：禁用
	 */
	public static Long ENABLED_NO 				= 0L;
	// 主键
	protected Long  id;
	// 授权人ID
	protected Long  authid;
	// 授权人
	protected String  authname;
	// 授权时间
	protected String  createtime;
	// 开始时间
	protected java.util.Date  startdate;
	// 结束时间
	protected java.util.Date  enddate;
	// 是否启用(0,禁止,1,启用)
	protected Short  enabled=1;
	// 授权类型(0,全权,1,部分流程,2,条件代理)
	protected Short  authtype=0;
	// 代理人ID
	protected Long  agentid;
	// 代理人
	protected String  agent;
	// 流程key(条件代理时填写)
	protected String  flowkey;
	// 流程名称
	protected String  flowname;
	
	// 部分代理  代理的流程
	protected List<AgentDef> agentDefList = new ArrayList<AgentDef>();
	
	// 条件代理  条件
	protected List<AgentCondition> agentConditionList = new ArrayList<AgentCondition>();	
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
	public void setAuthid(Long authid) 
	{
		this.authid = authid;
	}
	/**
	 * 返回 授权人ID
	 * @return
	 */
	public Long getAuthid() 
	{
		return this.authid;
	}
	public void setAuthname(String authname) 
	{
		this.authname = authname;
	}
	/**
	 * 返回 授权人
	 * @return
	 */
	public String getAuthname() 
	{
		return this.authname;
	}
	
	public void setStartdate(java.util.Date startdate) 
	{
		this.startdate = startdate;
	}
	/**
	 * 返回 开始时间
	 * @return
	 */
	public java.util.Date getStartdate() 
	{
		return this.startdate;
	}
	public void setEnddate(java.util.Date enddate) 
	{
		this.enddate = enddate;
	}
	/**
	 * 返回 结束时间
	 * @return
	 */
	public java.util.Date getEnddate() 
	{
		return this.enddate;
	}
	public void setEnabled(Short enabled) 
	{
		this.enabled = enabled;
	}
	/**
	 * 返回 是否启用(0,禁止,1,启用)
	 * @return
	 */
	public Short getEnabled() 
	{
		return this.enabled;
	}
	public void setAuthtype(Short authtype) 
	{
		this.authtype = authtype;
	}
	/**
	 * 返回 授权类型(0,全权,1,部分流程,2,条件代理)
	 * @return
	 */
	public Short getAuthtype() 
	{
		return this.authtype;
	}
	public void setAgentid(Long agentid) 
	{
		this.agentid = agentid;
	}
	/**
	 * 返回 代理人ID
	 * @return
	 */
	public Long getAgentid() 
	{
		return this.agentid;
	}
	public void setAgent(String agent) 
	{
		this.agent = agent;
	}
	/**
	 * 返回 代理人
	 * @return
	 */
	public String getAgent() 
	{
		return this.agent;
	}
	public void setFlowkey(String flowkey) 
	{
		this.flowkey = flowkey;
	}
	/**
	 * 返回 流程key(条件代理时填写)
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

	public List<AgentDef> getAgentDefList() {
		return agentDefList;
	}
	
	public void setAgentDefList(List<AgentDef> agentDefList) {
		this.agentDefList = agentDefList;
	}
	
	public void setAgentConditionList(List<AgentCondition> agentConditionList) 
	{
		this.agentConditionList = agentConditionList;
	}
	/**
	 * 返回 条件代理的配置列表
	 * @return
	 */
	public List<AgentCondition> getAgentConditionList() 
	{
		return this.agentConditionList;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof AgentSetting)) 
		{
			return false;
		}
		AgentSetting rhs = (AgentSetting) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.authid, rhs.authid)
		.append(this.authname, rhs.authname)
		.append(this.createtime, rhs.createtime)
		.append(this.startdate, rhs.startdate)
		.append(this.enddate, rhs.enddate)
		.append(this.enabled, rhs.enabled)
		.append(this.authtype, rhs.authtype)
		.append(this.agentid, rhs.agentid)
		.append(this.agent, rhs.agent)
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
		.append(this.authid) 
		.append(this.authname) 
		.append(this.createtime) 
		.append(this.startdate) 
		.append(this.enddate) 
		.append(this.enabled) 
		.append(this.authtype) 
		.append(this.agentid) 
		.append(this.agent) 
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
		.append("authid", this.authid) 
		.append("authname", this.authname) 
		.append("createtime", this.createtime) 
		.append("startdate", this.startdate) 
		.append("enddate", this.enddate) 
		.append("enabled", this.enabled) 
		.append("authtype", this.authtype) 
		.append("agentid", this.agentid) 
		.append("agent", this.agent) 
		.append("flowkey", this.flowkey) 
		.append("flowname", this.flowname) 
		.toString();
	}
   
  

}