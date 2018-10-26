package com.hotent.platform.model.mail;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;
/**
 * 对象功能:OUT_MAIL_ATTACHMENT Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:gjh
 * 创建时间:2014-03-04 13:49:25
 */
public class OutMailAttachment extends BaseModel
{
	// 文件ID
	protected Long  fileId;
	// 文件名
	protected String  fileName;
	// 文件存放路径
	protected String  filePath;
	// 邮件ID
	protected Long  mailId;
	public void setFileId(Long fileId) 
	{
		this.fileId = fileId;
	}
	/**
	 * 返回 文件ID
	 * @return
	 */
	public Long getFileId() 
	{
		return this.fileId;
	}
	public void setFileName(String fileName) 
	{
		this.fileName = fileName;
	}
	/**
	 * 返回 文件名
	 * @return
	 */
	public String getFileName() 
	{
		return this.fileName;
	}
	public void setFilePath(String filePath) 
	{
		this.filePath = filePath;
	}
	/**
	 * 返回 文件存放路径
	 * @return
	 */
	public String getFilePath() 
	{
		return this.filePath;
	}
	public void setMailId(Long mailId) 
	{
		this.mailId = mailId;
	}
	/**
	 * 返回 邮件ID
	 * @return
	 */
	public Long getMailId() 
	{
		return this.mailId;
	}

   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof OutMailAttachment)) 
		{
			return false;
		}
		OutMailAttachment rhs = (OutMailAttachment) object;
		return new EqualsBuilder()
		.append(this.fileId, rhs.fileId)
		.append(this.fileName, rhs.fileName)
		.append(this.filePath, rhs.filePath)
		.append(this.mailId, rhs.mailId)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.fileId) 
		.append(this.fileName) 
		.append(this.filePath) 
		.append(this.mailId) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("fileId", this.fileId) 
		.append("fileName", this.fileName) 
		.append("filePath", this.filePath) 
		.append("mailId", this.mailId) 
		.toString();
	}
   
  

}