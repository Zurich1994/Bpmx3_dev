package com.hotent.Bpmcount.model.Bpmcount;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import com.hotent.core.model.BaseModel;
/**
 * 对象功能:bpmcount Model对象
 */
public class Bpmcount extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *流程id
	 */
	protected String  defid;
	/**
	 *流程节点id
	 */
	protected String  nodeid;
	/**
	 *操作图id
	 */
	protected String  operaterid;
	/**
	 *运行次数
	 */
	protected Long  runs;
	/**
	 *时间
	 */
	protected Long time;
	
	protected Long  sysId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setDefid(String defid) 
	{
		this.defid = defid;
	}
	/**
	 * 返回 流程id
	 * @return
	 */
	public String getDefid() 
	{
		return this.defid;
	}
	public void setNodeid(String nodeid) 
	{
		this.nodeid = nodeid;
	}
	/**
	 * 返回 流程节点id
	 * @return
	 */
	public String getNodeid() 
	{
		return this.nodeid;
	}
	public void setOperaterid(String operaterid) 
	{
		this.operaterid = operaterid;
	}
	/**
	 * 返回 操作图id
	 * @return
	 */
	public String getOperaterid() 
	{
		return this.operaterid;
	}
	public void setRuns(Long runs) 
	{
		this.runs = runs;
	}
	/**
	 * 返回 运行次数
	 * @return
	 */
	public Long getRuns() 
	{
		return this.runs;
	}
	public void setTime(Long time) 
	{
		this.time = time;
	}
	/**
	 * 返回 时间
	 * @return
	 */
	public Long getTime() 
	{
		return this.time;
	}
   	
	public Long getSysId() {
		return sysId;
	}
	public void setSysId(Long sysId) {
		this.sysId = sysId;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof Bpmcount)) 
		{
			return false;
		}
		Bpmcount rhs = (Bpmcount) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.defid, rhs.defid)
		.append(this.nodeid, rhs.nodeid)
		.append(this.operaterid, rhs.operaterid)
		.append(this.runs, rhs.runs)
		.append(this.time, rhs.time)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id)
		.append(this.defid) 
		.append(this.nodeid) 
		.append(this.operaterid) 
		.append(this.runs) 
		.append(this.time) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("defid", this.defid) 
		.append("nodeid", this.nodeid) 
		.append("operaterid", this.operaterid) 
		.append("runs", this.runs) 
		.append("time", this.time) 
		.toString();
	}

}