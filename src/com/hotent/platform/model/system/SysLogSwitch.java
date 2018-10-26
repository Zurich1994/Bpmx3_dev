package com.hotent.platform.model.system;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;
/**
 * 对象功能:日志开关 Model对象
 * 开发公司:广州宏天
 * 开发人员:Raise
 * 创建时间:2013-06-24 11:12:26
 */
public class SysLogSwitch extends BaseModel
{
	/**
	 * 开启
	 */
	public static final short STATUS_CLOSE 							= 0;
	
	/**
	 * 关闭
	 */
	public static final short STATUS_OPEN 								= 1;
	// 主键
	protected Long  id;
	// 模块名
	protected String  model;
	// 状态
	protected Short  status = STATUS_CLOSE;
	// 创建时间
	protected Date  createtime;
	// 创建人
	protected String  creator;
	// 创建人ID
	protected Long  creatorid;
	// 更新人
	protected String  updby;
	// 更新人ID
	protected Long  updbyid;
	// 备注
	protected String  memo;
	// 最后更新时间
	protected String  lastuptime;

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
	public void setModel(String model) 
	{
		this.model = model;
	}
	/**
	 * 返回 模块名
	 * @return
	 */
	public String getModel() 
	{
		return this.model;
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
	public void setCreatetime(Date createtime) 
	{
		this.createtime = createtime;
	}
	/**
	 * 返回 创建时间
	 * @return
	 */
	public Date getCreatetime() 
	{
		return this.createtime;
	}
	public void setCreator(String creator) 
	{
		this.creator = creator;
	}
	/**
	 * 返回 创建人
	 * @return
	 */
	public String getCreator() 
	{
		return this.creator;
	}
	public void setCreatorid(Long creatorid) 
	{
		this.creatorid = creatorid;
	}
	/**
	 * 返回 创建人ID
	 * @return
	 */
	public Long getCreatorid() 
	{
		return this.creatorid;
	}
	public void setUpdby(String updby) 
	{
		this.updby = updby;
	}
	/**
	 * 返回 更新人
	 * @return
	 */
	public String getUpdby() 
	{
		return this.updby;
	}
	public void setUpdbyid(Long updbyid) 
	{
		this.updbyid = updbyid;
	}
	/**
	 * 返回 更新人ID
	 * @return
	 */
	public Long getUpdbyid() 
	{
		return this.updbyid;
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
	public void setLastuptime(String lastuptime) 
	{
		this.lastuptime = lastuptime;
	}
	/**
	 * 返回 最后更新时间
	 * @return
	 */
	public String getLastuptime() 
	{
		return this.lastuptime;
	}


   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof SysLogSwitch)) 
		{
			return false;
		}
		SysLogSwitch rhs = (SysLogSwitch) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.model, rhs.model)
		.append(this.status, rhs.status)
		.append(this.createtime, rhs.createtime)
		.append(this.creator, rhs.creator)
		.append(this.creatorid, rhs.creatorid)
		.append(this.updby, rhs.updby)
		.append(this.updbyid, rhs.updbyid)
		.append(this.memo, rhs.memo)
		.append(this.lastuptime, rhs.lastuptime)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id) 
		.append(this.model) 
		.append(this.status) 
		.append(this.createtime) 
		.append(this.creator) 
		.append(this.creatorid) 
		.append(this.updby) 
		.append(this.updbyid) 
		.append(this.memo) 
		.append(this.lastuptime) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("model", this.model) 
		.append("status", this.status) 
		.append("createtime", this.createtime) 
		.append("creator", this.creator) 
		.append("creatorid", this.creatorid) 
		.append("updby", this.updby) 
		.append("updbyid", this.updbyid) 
		.append("memo", this.memo) 
		.append("lastuptime", this.lastuptime) 
		.toString();
	}
   
  

}