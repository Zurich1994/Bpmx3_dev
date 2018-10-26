package com.hotent.support.model.catelog;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:文件信息 Model对象
 */
public class FileList extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *文件ID
	 */
	protected Long  fileid;
	/**
	 *产品ID
	 */
	protected Long  productid;
	/**
	 *操作系统
	 */
	protected String  os;
	/**
	 *下载目录
	 */
	protected String  filecatalog;
	/**
	 *语言
	 */
	protected String  language;
	/**
	 *文件名
	 */
	protected String  filename;
	/**
	 *文件日期
	 */
	protected java.util.Date  filedate;
	/**
	 *文件大小
	 */
	protected Long  filesize;
	/**
	 *文件描述
	 */
	protected String  description;
	/**
	 *额外信息
	 */
	protected String  additional;
	/**
	 *下载链接
	 */
	protected String  downloadurl;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setFileid(Long fileid) 
	{
		this.fileid = fileid;
	}
	/**
	 * 返回 文件ID
	 * @return
	 */
	public Long getFileid() 
	{
		return this.fileid;
	}
	public void setProductid(Long productid) 
	{
		this.productid = productid;
	}
	/**
	 * 返回 产品ID
	 * @return
	 */
	public Long getProductid() 
	{
		return this.productid;
	}
	public void setOs(String os) 
	{
		this.os = os;
	}
	/**
	 * 返回 操作系统
	 * @return
	 */
	public String getOs() 
	{
		return this.os;
	}
	public void setFilecatalog(String filecatalog) 
	{
		this.filecatalog = filecatalog;
	}
	/**
	 * 返回 下载目录
	 * @return
	 */
	public String getFilecatalog() 
	{
		return this.filecatalog;
	}
	public void setLanguage(String language) 
	{
		this.language = language;
	}
	/**
	 * 返回 语言
	 * @return
	 */
	public String getLanguage() 
	{
		return this.language;
	}
	public void setFilename(String filename) 
	{
		this.filename = filename;
	}
	/**
	 * 返回 文件名
	 * @return
	 */
	public String getFilename() 
	{
		return this.filename;
	}
	public void setFiledate(java.util.Date filedate) 
	{
		this.filedate = filedate;
	}
	/**
	 * 返回 文件日期
	 * @return
	 */
	public java.util.Date getFiledate() 
	{
		return this.filedate;
	}
	public void setFilesize(Long filesize) 
	{
		this.filesize = filesize;
	}
	/**
	 * 返回 文件大小
	 * @return
	 */
	public Long getFilesize() 
	{
		return this.filesize;
	}
	public void setDescription(String description) 
	{
		this.description = description;
	}
	/**
	 * 返回 文件描述
	 * @return
	 */
	public String getDescription() 
	{
		return this.description;
	}
	public void setAdditional(String additional) 
	{
		this.additional = additional;
	}
	/**
	 * 返回 额外信息
	 * @return
	 */
	public String getAdditional() 
	{
		return this.additional;
	}
	public void setDownloadurl(String downloadurl) 
	{
		this.downloadurl = downloadurl;
	}
	/**
	 * 返回 下载链接
	 * @return
	 */
	public String getDownloadurl() 
	{
		return this.downloadurl;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof FileList)) 
		{
			return false;
		}
		FileList rhs = (FileList) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.fileid, rhs.fileid)
		.append(this.productid, rhs.productid)
		.append(this.os, rhs.os)
		.append(this.filecatalog, rhs.filecatalog)
		.append(this.language, rhs.language)
		.append(this.filename, rhs.filename)
		.append(this.filedate, rhs.filedate)
		.append(this.filesize, rhs.filesize)
		.append(this.description, rhs.description)
		.append(this.additional, rhs.additional)
		.append(this.downloadurl, rhs.downloadurl)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id)
		.append(this.fileid) 
		.append(this.productid) 
		.append(this.os) 
		.append(this.filecatalog) 
		.append(this.language) 
		.append(this.filename) 
		.append(this.filedate) 
		.append(this.filesize) 
		.append(this.description) 
		.append(this.additional) 
		.append(this.downloadurl) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("fileid", this.fileid) 
		.append("productid", this.productid) 
		.append("os", this.os) 
		.append("filecatalog", this.filecatalog) 
		.append("language", this.language) 
		.append("filename", this.filename) 
		.append("filedate", this.filedate) 
		.append("filesize", this.filesize) 
		.append("description", this.description) 
		.append("additional", this.additional) 
		.append("downloadurl", this.downloadurl) 
		.toString();
	}

}
