package com.hotent.platform.model.system;

import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:SYS_ACCEPT_IP Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2012-02-20 17:35:46
 */
public class SysAcceptIp extends BaseModel
{
	// acceptId
	protected Long acceptId;
	// 标题
	protected String title;
	// 开始地址
	protected String startIp;
	// 结束地址
	protected String endIp;	
	// 备注
	protected String remark;

	public void setAcceptId(Long acceptId) 
	{
		this.acceptId = acceptId;
	}
	/**
	 * 返回 acceptId
	 * @return
	 */
	public Long getAcceptId() 
	{
		return acceptId;
	}

	public void setTitle(String title) 
	{
		this.title = title;
	}
	/**
	 * 返回 标题
	 * @return
	 */
	public String getTitle() 
	{
		return title;
	}

	public void setStartIp(String startIp) 
	{
		this.startIp = startIp;
	}
	/**
	 * 返回 开始地址
	 * @return
	 */
	public String getStartIp() 
	{
		return startIp;
	}

	public void setEndIp(String endIp) 
	{
		this.endIp = endIp;
	}
	/**
	 * 返回 结束地址
	 * @return
	 */
	public String getEndIp() 
	{
		return endIp;
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
		return remark;
	}

   
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof SysAcceptIp)) 
		{
			return false;
		}
		SysAcceptIp rhs = (SysAcceptIp) object;
		return new EqualsBuilder()
		.append(this.acceptId, rhs.acceptId)
		.append(this.title, rhs.title)
		.append(this.startIp, rhs.startIp)
		.append(this.endIp, rhs.endIp)		
		.append(this.remark, rhs.remark)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.acceptId) 
		.append(this.title) 
		.append(this.startIp) 
		.append(this.endIp)		
		.append(this.remark) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("acceptId", this.acceptId) 
		.append("title", this.title) 
		.append("startIp", this.startIp) 
		.append("endIp", this.endIp)
		.append("remark", this.remark) 
		.toString();
	}
   
  

}