package com.hotent.platform.model.system;

import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:电子印章 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:raise
 * 创建时间:2012-08-29 11:25:59
 */
public class Seal extends BaseModel
{
	// sealId
	protected Long  sealId;
	// 印章名
	protected String  sealName;
	// 印章路径
	protected String  sealPath;
	// 印章持有者ID
	protected Long  belongId;
	// 印章所属
	protected String  belongName;
	// 印章附件
	protected Long  attachmentId;
	//印章显示图片附件ID
	protected Long  showImageId;
	
	public void setSealId(Long sealId) 
	{
		this.sealId = sealId;
	}
	/**
	 * 返回 sealId
	 * @return
	 */
	public Long getSealId() 
	{
		return this.sealId;
	}
	public void setSealName(String sealName) 
	{
		this.sealName = sealName;
	}
	/**
	 * 返回 印章名
	 * @return
	 */
	public String getSealName() 
	{
		return this.sealName;
	}
	public void setSealPath(String sealPath) 
	{
		this.sealPath = sealPath;
	}
	/**
	 * 返回 印章路径
	 * @return
	 */
	public String getSealPath() 
	{
		return this.sealPath;
	}
	public void setBelongId(Long belongId) 
	{
		this.belongId = belongId;
	}
	/**
	 * 返回 印章持有者ID
	 * @return
	 */
	public Long getBelongId() 
	{
		return this.belongId;
	}
	public void setBelongName(String belongName) 
	{
		this.belongName = belongName;
	}
	/**
	 * 返回 印章所属单位或个人
	 * @return
	 */
	public String getBelongName() 
	{
		return this.belongName;
	}
	public void setAttachmentId(Long attachmentId) 
	{
		this.attachmentId = attachmentId;
	}
	/**
	 * 返回 印章附件
	 * @return
	 */
	public Long getAttachmentId() 
	{
		return this.attachmentId;
	}
   
   	public Long getShowImageId()
	{
		return showImageId;
	}
	public void setShowImageId(Long showImageId)
	{
		this.showImageId = showImageId;
	}
	
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof Seal)) 
		{
			return false;
		}
		Seal rhs = (Seal) object;
		return new EqualsBuilder()
		.append(this.sealId, rhs.sealId)
		.append(this.sealName, rhs.sealName)
		.append(this.sealPath, rhs.sealPath)
		.append(this.belongId, rhs.belongId)
		.append(this.belongName, rhs.belongName)
		.append(this.attachmentId, rhs.attachmentId)
		.append(this.showImageId, rhs.showImageId)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.sealId) 
		.append(this.sealName) 
		.append(this.sealPath) 
		.append(this.belongId) 
		.append(this.belongName) 
		.append(this.attachmentId) 
		.append(this.showImageId) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("sealId", this.sealId) 
		.append("sealName", this.sealName) 
		.append("sealPath", this.sealPath) 
		.append("belongId", this.belongId) 
		.append("belongName", this.belongName) 
		.append("attachmentId", this.attachmentId) 
		.append("showImageId", this.showImageId)
		.toString();
	}
   
  

}