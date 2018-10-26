package com.hotent.platform.model.system;

import java.sql.Blob;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.hotent.core.model.BaseModel;
import com.hotent.platform.xml.util.XmlDateAdapter;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:附件 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2011-11-26 18:19:16
 */
@SuppressWarnings("serial")
@XmlRootElement(name="sysFile")
@XmlAccessorType(XmlAccessType.NONE)
public class SysFile extends BaseModel{
	
	/**
	 * 文件已经删除 [value=1]
	 */
	public static Short FILE_DEL = 1;
	
	/**
	 * 文件存在[value=0]
	 */
	public static Short FILE_NOT_DEL = 0;
	
	/**
	 * 匿名用户
	 */
	public static String FILE_UPLOAD_UNKNOWN = "unknown";
	
	/**
	 * 其他分类
	 */
	public static String FILETYPE_OTHERS="others";
	

	public static final String ATTACHMENT_DIRECTORY="attachFiles";

	// fileId
	@XmlAttribute
	protected Long fileId;
	// 分类ID
	@XmlAttribute
	protected Long typeId;
	// 文件名
	@XmlAttribute
	protected String fileName;
	// 文件路径
	@XmlAttribute
	protected String filePath;
	// 创建时间
	@XmlJavaTypeAdapter(XmlDateAdapter.class)
	@XmlAttribute
	protected java.util.Date createtime;
	// 扩展名
	@XmlAttribute
	protected String ext;
	// 附件类型
	@XmlAttribute
	protected String fileType;
	// 说明
	@XmlAttribute
	protected String note;
	// creatorId
	@XmlAttribute
	protected Long creatorId;
	// 上传者
	@XmlAttribute
	protected String creator;
	// totalBytes
	@XmlAttribute
	protected Long totalBytes;
	// 1=已删除
	@XmlAttribute
	protected Short delFlag;
	// 附件内容
	@XmlAttribute
	protected byte[]  fileBlob;
	
	protected String typeName;
    
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}
	/**
	 * 返回 fileId
	 * @return
	 */
	public Long getFileId() {
		return fileId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
	/**
	 * 返回 分类ID
	 * @return
	 */
	public Long getTypeId() {
		return typeId;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * 返回 文件名
	 * @return
	 */
	public String getFileName() {
		return fileName;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	/**
	 * 返回 文件路径
	 * @return
	 */
	public String getFilePath() {
		return filePath;
	}

	public void setCreatetime(java.util.Date createtime) {
		this.createtime = createtime;
	}
	/**
	 * 返回 创建时间
	 * @return
	 */
	public java.util.Date getCreatetime() {
		return createtime;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}
	/**
	 * 返回 扩展名
	 * @return
	 */
	public String getExt() {
		return ext;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	/**
	 * 返回 附件类型
	 * @return
	 */
	public String getFileType() {
		return fileType;
	}

	public void setNote(String note) {
		this.note = note;
	}
	/**
	 * 返回 说明
	 * @return
	 */
	public String getNote() {
		return note;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}
	/**
	 * 返回 creatorId
	 * @return
	 */
	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}
	/**
	 * 返回 上传者
	 * @return
	 */
	public String getCreator() {
		return creator;
	}

	public void setTotalBytes(Long totalBytes) {
		this.totalBytes = totalBytes;
	}
	/**
	 * 返回 totalBytes
	 * @return
	 */
	public Long getTotalBytes() {
		return totalBytes;
	}

	public void setDelFlag(Short delFlag) {
		this.delFlag = delFlag;
	}
	/**
	 * 返回 1=已删除
	 * @return
	 */
	public Short getDelFlag() {
		return delFlag;
	}

	/**
	 * 返回附件二进制流
	 * @return
	 */
	public byte[]  getFileBlob()
	{
		return fileBlob;
	}
	public void setFileBlob(byte[] fileBlob)
	{
		this.fileBlob = fileBlob;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof SysFile)) {
			return false;
		}
		SysFile rhs = (SysFile) object;
		return new EqualsBuilder()
		.append(this.fileId, rhs.fileId)
		.append(this.typeId, rhs.typeId)
		.append(this.fileName, rhs.fileName)
		.append(this.filePath, rhs.filePath)
		.append(this.createtime, rhs.createtime)
		.append(this.ext, rhs.ext)
		.append(this.fileType, rhs.fileType)
		.append(this.note, rhs.note)
		.append(this.creatorId, rhs.creatorId)
		.append(this.creator, rhs.creator)
		.append(this.totalBytes, rhs.totalBytes)
		.append(this.delFlag, rhs.delFlag)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.fileId) 
		.append(this.typeId) 
		.append(this.fileName) 
		.append(this.filePath) 
		.append(this.createtime) 
		.append(this.ext) 
		.append(this.fileType) 
		.append(this.note) 
		.append(this.creatorId) 
		.append(this.creator) 
		.append(this.totalBytes) 
		.append(this.delFlag) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("fileId", this.fileId) 
		.append("typeId", this.typeId) 
		.append("fileName", this.fileName) 
		.append("filePath", this.filePath) 
		.append("createtime", this.createtime) 
		.append("ext", this.ext) 
		.append("fileType", this.fileType) 
		.append("note", this.note) 
		.append("creatorId", this.creatorId) 
		.append("creator", this.creator) 
		.append("totalBytes", this.totalBytes) 
		.append("delFlag", this.delFlag) 
		.toString();
	}
   
  

}