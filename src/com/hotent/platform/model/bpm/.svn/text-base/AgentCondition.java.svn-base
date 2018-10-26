package com.hotent.platform.model.bpm;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;
/**
 * 对象功能:条件代理的配置 Model对象
 * 开发公司:广州宏天软件
 * 开发人员:raise zyg
 * 创建时间:2013-04-29 11:15:10
 */
public class AgentCondition extends BaseModel
{
	//等于
	public static final  short OPERATE_TYPE_EQUAL= 0; 
	//不等于
	public static final  short OPERATE_TYPE_UNEQUAL= 1; 
	//大于
	public static final  short OPERATE_TYPE_MORE_THAN = 2; 
	//大于等于
	public static final  short OPERATE_TYPE_MORE_EQUAL_THAN = 3; 
	//小于
	public static final  short OPERATE_TYPE_LESS_THAN = 4; 
	//小于
	public static final  short OPERATE_TYPE_LESS_EQUAL_THAN = 5; 
	//字符串的等于
	public static final  short OPERATE_TYPE_LIKE = 6;
	//字符串的等于
	public static final  short OPERATE_TYPE_UNLIKE = 7;
	
	//变量为数字类型
	public static final  String VARIABLE_TYPE_NUMBER = "number";
	//变量为日期类型
	public static final  String VARIABLE_TYPE_DATE = "date";
	//变量为日期类型
	public static final  String VARIABLE_TYPE_STRING= "varchar";
	
	// 主键
	protected Long  id;
	// 流程设定ID
	protected Long  settingid;
	// 条件
	protected String  condition;
	// 备注
	protected String  memo;
	// 代理人ID
	protected Long  agentid;
	// 代理人
	protected String  agent;
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
	 * 返回 流程设定ID
	 * @return
	 */
	public Long getSettingid() 
	{
		return this.settingid;
	}
	public void setCondition(String condition) 
	{
		this.condition = condition;
	}
	/**
	 * 返回 条件
	 * @return
	 */
	public String getCondition() 
	{
		return this.condition;
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


   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof AgentCondition)) 
		{
			return false;
		}
		AgentCondition rhs = (AgentCondition) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.settingid, rhs.settingid)
		.append(this.condition, rhs.condition)
		.append(this.memo, rhs.memo)
		.append(this.agentid, rhs.agentid)
		.append(this.agent, rhs.agent)
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
		.append(this.condition) 
		.append(this.memo) 
		.append(this.agentid) 
		.append(this.agent) 
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
		.append("condition", this.condition) 
		.append("memo", this.memo) 
		.append("agentid", this.agentid) 
		.append("agent", this.agent) 
		.toString();
	}
   
  

}