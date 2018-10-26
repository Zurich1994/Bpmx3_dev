package com.hotent.platform.model.bpm;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;
/**
 * 对象功能:监控流程项目 Model对象
 * 开发公司:SF
 * 开发人员:xxx
 * 创建时间:2013-06-08 11:14:50
 */
public class MonGroupItem extends BaseModel
{
	// 主键
	protected Long  id;
	// 流程分组Id
	protected Long  groupid;
	// 流程定义KEY
	protected String  flowkey;
	//流程名称
	protected String flowName="";
	
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
	public void setGroupid(Long groupid) 
	{
		this.groupid = groupid;
	}
	/**
	 * 返回 流程分组Id
	 * @return
	 */
	public Long getGroupid() 
	{
		return this.groupid;
	}
	public void setFlowkey(String flowkey) 
	{
		this.flowkey = flowkey;
	}
	/**
	 * 返回 流程定义KEY
	 * @return
	 */
	public String getFlowkey() 
	{
		return this.flowkey;
	}


   	public String getFlowName() {
		return flowName;
	}
	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof MonGroupItem)) 
		{
			return false;
		}
		MonGroupItem rhs = (MonGroupItem) object;
		return new EqualsBuilder()
		.append(this.groupid, rhs.groupid)
		.append(this.flowkey, rhs.flowkey)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.groupid) 
		.append(this.flowkey) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("groupid", this.groupid) 
		.append("flowkey", this.flowkey) 
		.toString();
	}
   
  

}