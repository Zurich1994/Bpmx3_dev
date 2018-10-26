package com.hotent.platform.model.system;

import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:office模版 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2012-05-25 10:16:16
 */
public class SysOfficeTemplate extends BaseModel
{
	// 主键
	protected Long  id;
	// 主题
	protected String  subject;
	// 模版类型(1,普通模版,2,套红模版)
	protected Integer  templatetype=1;
	// 备注
	protected String  memo;
	// 创建人ID
	protected Long  creatorid;
	// 创建人
	protected String  creator;
	// 创建时间
	protected java.util.Date  createtime;
	// 路径
	protected String  path;
	// 模板内容
	protected byte[]  templateBlob;
	
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
	public void setSubject(String subject) 
	{
		this.subject = subject;
	}
	/**
	 * 返回 主题
	 * @return
	 */
	public String getSubject() 
	{
		return this.subject;
	}
	public void setTemplatetype(Integer templatetype) 
	{
		this.templatetype = templatetype;
	}
	/**
	 * 返回 模版类型(1,普通模版,2,套红模版)
	 * @return
	 */
	public Integer getTemplatetype() 
	{
		return this.templatetype;
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
	public void setCreatetime(java.util.Date createtime) 
	{
		this.createtime = createtime;
	}
	/**
	 * 返回 创建时间
	 * @return
	 */
	public java.util.Date getCreatetime() 
	{
		return this.createtime;
	}
	public void setPath(String path) 
	{
		this.path = path;
	}
	/**
	 * 返回 路径
	 * @return
	 */
	public String getPath() 
	{
		return this.path;
	}

	
	/**
	 * 返回内容
	 * @return
	 */
   	public byte[] getTemplateBlob()
	{
		return templateBlob;
	}
	public void setTemplateBlob(byte[] templateBlob)
	{
		this.templateBlob = templateBlob;
	}
	
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof SysOfficeTemplate)) 
		{
			return false;
		}
		SysOfficeTemplate rhs = (SysOfficeTemplate) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.subject, rhs.subject)
		.append(this.templatetype, rhs.templatetype)
		.append(this.memo, rhs.memo)
		.append(this.creatorid, rhs.creatorid)
		.append(this.creator, rhs.creator)
		.append(this.createtime, rhs.createtime)
		.append(this.path, rhs.path)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id) 
		.append(this.subject) 
		.append(this.templatetype) 
		.append(this.memo) 
		.append(this.creatorid) 
		.append(this.creator) 
		.append(this.createtime) 
		.append(this.path) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("subject", this.subject) 
		.append("templatetype", this.templatetype) 
		.append("memo", this.memo) 
		.append("creatorid", this.creatorid) 
		.append("creator", this.creator) 
		.append("createtime", this.createtime) 
		.append("path", this.path) 
		.toString();
	}
   
  

}