package com.hotent.platform.model.bpm;

import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:流程节点状态 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-07-19 11:42:55
 */
public class BpmProStatus extends BaseModel
{
	
	
	// 主键
	protected Long  id=0L;
	// 流程实例ID
	protected Long  actinstid=0L;
	// 流程节点ID
	protected String  nodeid="";
	// 节点名称
	protected String  nodename="";
	// 状态
	//-1:正在审批，0：弃权,1:同意,2:反对,3:驳回,4,追回,5:会签通过,6:会签不通过
   	//14,终止,33.提交,34,重新提交,37.执行过(脚本使用)
	protected Short  status=TaskOpinion.STATUS_INIT;
	// 最后更新时间
	protected java.util.Date  lastupdatetime;
	// 流程定义ID
	protected String  actdefid="";
	//流程定义ID
	protected Long defId=0L;
	
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
	public void setActinstid(Long actinstid) 
	{
		this.actinstid = actinstid;
	}
	/**
	 * 返回 流程实例ID
	 * @return
	 */
	public Long getActinstid() 
	{
		return this.actinstid;
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
	public void setNodename(String nodename) 
	{
		this.nodename = nodename;
	}
	/**
	 * 返回 节点名称
	 * @return
	 */
	public String getNodename() 
	{
		return this.nodename;
	}
	public void setStatus(Short status) 
	{
		this.status = status;
	}
	/**
	 * 返回 状态
	 * @return
	 */
	public Short getStatus() 
	{
		return this.status;
	}
	public void setLastupdatetime(java.util.Date lastupdatetime) 
	{
		this.lastupdatetime = lastupdatetime;
	}
	/**
	 * 返回 最后更新时间
	 * @return
	 */
	public java.util.Date getLastupdatetime() 
	{
		return this.lastupdatetime;
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

   
   	public Long getDefId() {
		return defId;
	}
	public void setDefId(Long defId) {
		this.defId = defId;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof BpmProStatus)) 
		{
			return false;
		}
		BpmProStatus rhs = (BpmProStatus) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.actinstid, rhs.actinstid)
		.append(this.nodeid, rhs.nodeid)
		.append(this.nodename, rhs.nodename)
		.append(this.status, rhs.status)
		.append(this.lastupdatetime, rhs.lastupdatetime)
		.append(this.actdefid, rhs.actdefid)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id) 
		.append(this.actinstid) 
		.append(this.nodeid) 
		.append(this.nodename) 
		.append(this.status) 
		.append(this.lastupdatetime) 
		.append(this.actdefid) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("actinstid", this.actinstid) 
		.append("nodeid", this.nodeid) 
		.append("nodename", this.nodename) 
		.append("status", this.status) 
		.append("lastupdatetime", this.lastupdatetime) 
		.append("actdefid", this.actdefid) 
		.toString();
	}
   
  

}