package com.hotent.Operatercount.model.Operatercount;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import com.hotent.core.model.BaseModel;
/**
 * 对象功能:操作运行次数 Model对象
 */
public class Operatercount extends BaseModel
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
	 *节点id
	 */
	protected String  nodeid;
	/**
	 *事务图id
	 */
	protected String  transactionid;
	/**
	 *页面
	 */
	protected String  ym;
	/**
	 *次数
	 */
	protected Long  runs;
	/**
	 *时间
	 */
	protected Long  time;
	protected Long  sysId;
	
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
	public void setYm(String ym) 
	{
		this.ym = ym;
	}
	/**
	 * 返回 页面
	 * @return
	 */
	public String getYm() 
	{
		return this.ym;
	}
	public void setRuns(Long runs) 
	{
		this.runs = runs;
	}
	/**
	 * 返回 次数
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
		if (!(object instanceof Operatercount)) 
		{
			return false;
		}
		Operatercount rhs = (Operatercount) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.bpmid, rhs.bpmid)
		.append(this.operaterid, rhs.operaterid)
		.append(this.nodeid, rhs.nodeid)
		.append(this.transactionid, rhs.transactionid)
		.append(this.ym, rhs.ym)
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
		.append(this.nodeid) 
		.append(this.transactionid) 
		.append(this.ym) 
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
		.append("nodeid", this.nodeid) 
		.append("transactionid", this.transactionid) 
		.append("ym", this.ym) 
		.append("runs", this.runs) 
		.append("time", this.time) 
		.toString();
	}

}