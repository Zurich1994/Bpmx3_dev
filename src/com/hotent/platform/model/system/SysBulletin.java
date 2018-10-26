package com.hotent.platform.model.system;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import com.hotent.core.model.BaseModel;

/**
 * 对象功能:公告表 Model对象
 */
public class SysBulletin extends BaseModel {
	// 主键
	protected Long id;
	/**
	 * 主题
	 */
	protected String subject;
	/**
	 * 栏目ID
	 */
	protected Long columnid;
	
	/**
	 * 栏目名称
	 */
	protected String columnname;
	
	/**
	 * 内容
	 */
	protected String content;
	/**
	 * creatorId
	 */
	protected Long creatorid;
	/**
	 * Creator
	 */
	protected String creator;
	/**
	 * createTime
	 */
	protected java.util.Date createtime;
	/**
	 * attachMent
	 */
	protected String attachment;
	
	protected Long tenantid;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * 返回 主题
	 * 
	 * @return
	 */
	public String getSubject() {
		return this.subject;
	}

	public void setColumnid(Long columnid) {
		this.columnid = columnid;
	}

	/**
	 * 返回 栏目ID
	 * 
	 * @return
	 */
	public Long getColumnid() {
		return this.columnid;
	}

	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 返回 内容
	 * 
	 * @return
	 */
	public String getContent() {
		return this.content;
	}

	public void setCreatorid(Long creatorid) {
		this.creatorid = creatorid;
	}

	/**
	 * 返回 creatorId
	 * 
	 * @return
	 */
	public Long getCreatorid() {
		return this.creatorid;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	/**
	 * 返回 Creator
	 * 
	 * @return
	 */
	public String getCreator() {
		return this.creator;
	}

	public void setCreatetime(java.util.Date createtime) {
		this.createtime = createtime;
	}

	/**
	 * 返回 createTime
	 * 
	 * @return
	 */
	public java.util.Date getCreatetime() {
		return this.createtime;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	/**
	 * 返回 attachMent
	 * 
	 * @return
	 */
	public String getAttachment() {
		return this.attachment;
	}

	public String getColumnname() {
		return columnname;
	}

	public void setColumnname(String columnname) {
		this.columnname = columnname;
	}

	public Long getTenantid() {
		return tenantid;
	}

	public void setTenantid(Long tenantid) {
		this.tenantid = tenantid;
	}
	
}