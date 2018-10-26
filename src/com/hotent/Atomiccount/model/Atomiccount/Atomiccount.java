package com.hotent.Atomiccount.model.Atomiccount;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import com.hotent.core.model.BaseModel;
/**
 * 对象功能:原子操作运行次数 Model对象
 */
public class Atomiccount extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *流程id
	 */
	protected String  bpmid;
	/**
	 *操作图id
	 */
	protected String  operaterid;
	/**
	 *事务图id
	 */
	protected String  transactionid;
	/**
	 *节点id
	 */
	protected String  nodeid;
	/**
	 *原子操作
	 */
	protected String  atomicid;
	/**
	 *运行次数
	 */
	protected Long  runs;
	/**
	 *时间
	 */
	protected Long  time;
	protected Long  sysId;
	protected String  atomictype;
	protected String  servicetype;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public void setBpmid(String bpmid) 
	{
		this.bpmid = bpmid;
	}
	/**
	 * 返回 流程id
	 * @return
	 */
	public String getBpmid() 
	{
		return this.bpmid;
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
	public void setTransactionid(String transactionid) 
	{
		this.transactionid = transactionid;
	}
	/**
	 * 返回 事务图id
	 * @return
	 */
	public String getTransactionid() 
	{
		return this.transactionid;
	}
	public void setNodeid(String nodeid) 
	{
		this.nodeid = nodeid;
	}
	/**
	 * 返回 节点id
	 * @return
	 */
	public String getNodeid() 
	{
		return this.nodeid;
	}
	public void setAtomicid(String atomicid) 
	{
		this.atomicid = atomicid;
	}
	/**
	 * 返回 原子操作
	 * @return
	 */
	public String getAtomicid() 
	{
		return this.atomicid;
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
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof Atomiccount)) 
		{
			return false;
		}
		Atomiccount rhs = (Atomiccount) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.bpmid, rhs.bpmid)
		.append(this.operaterid, rhs.operaterid)
		.append(this.transactionid, rhs.transactionid)
		.append(this.nodeid, rhs.nodeid)
		.append(this.atomicid, rhs.atomicid)
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
		.append(this.bpmid) 
		.append(this.operaterid) 
		.append(this.transactionid) 
		.append(this.nodeid) 
		.append(this.atomicid) 
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
		.append("bpmid", this.bpmid) 
		.append("operaterid", this.operaterid) 
		.append("transactionid", this.transactionid) 
		.append("nodeid", this.nodeid) 
		.append("atomicid", this.atomicid) 
		.append("runs", this.runs) 
		.append("time", this.time) 
		.toString();
	}
	public Long getSysId() {
		return sysId;
	}
	public void setSysId(Long sysId) {
		this.sysId = sysId;
	}
	public String getAtomictype() {
		return atomictype;
	}
	public void setAtomictype(String atomictype) {
		this.atomictype = atomictype;
	}
	public String getServicetype() {
		return servicetype;
	}
	public void setServicetype(String servicetype) {
		this.servicetype = servicetype;
	}
	

}