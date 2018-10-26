package com.hotent.platform.model.system;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;


/**
 * 对象功能:sys_trans_def Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:liyj
 * 创建时间:2015-04-16 11:15:54
 */
public class SysTransDef extends BaseModel{
	// ID
	protected Long  id;
	// NAME
	protected String  name;
	// SELECTSQL
	protected String  selectSql;
	// UPDATESQL
	protected String  updateSql;
	// STATE
	protected Short  state;
	// CREATORID
	protected Long  creatorId;
	// CREATOR
	protected String  creator;
	// CREATETIME
	protected Date  createTime;
	//日志内容模板
	protected String logContent;
	
	public void setId(Long id){
		this.id = id;
	}
	/**
	 * 返回 ID
	 * @return
	 */
	public Long getId() {
		return this.id;
	}
	public void setName(String name){
		this.name = name;
	}
	/**
	 * 返回 NAME
	 * @return
	 */
	public String getName() {
		return this.name;
	}
	public void setSelectSql(String selectSql){
		this.selectSql = selectSql;
	}
	/**
	 * 返回 SELECTSQL
	 * @return
	 */
	public String getSelectSql() {
		return this.selectSql;
	}
	public void setUpdateSql(String updateSql){
		this.updateSql = updateSql;
	}
	/**
	 * 返回 UPDATESQL
	 * @return
	 */
	public String getUpdateSql() {
		return this.updateSql;
	}
	public void setState(Short state){
		this.state = state;
	}
	/**
	 * 返回 STATE
	 * @return
	 */
	public Short getState() {
		return this.state;
	}
	public void setCreatorId(Long creatorId){
		this.creatorId = creatorId;
	}
	/**
	 * 返回 CREATORID
	 * @return
	 */
	public Long getCreatorId() {
		return this.creatorId;
	}
	public void setCreator(String creator){
		this.creator = creator;
	}
	/**
	 * 返回 CREATOR
	 * @return
	 */
	public String getCreator() {
		return this.creator;
	}
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
	/**
	 * 返回 CREATETIME
	 * @return
	 */
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	
   	/**
	 * logContent
	 * @return  the logContent
	 * @since   1.0.0
	 */
	
	public String getLogContent() {
		return logContent;
	}
	/**
	 * @param logContent the logContent to set
	 */
	public void setLogContent(String logContent) {
		this.logContent = logContent;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof SysTransDef)) 
		{
			return false;
		}
		SysTransDef rhs = (SysTransDef) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.name, rhs.name)
		.append(this.selectSql, rhs.selectSql)
		.append(this.updateSql, rhs.updateSql)
		.append(this.state, rhs.state)
		.append(this.creatorId, rhs.creatorId)
		.append(this.creator, rhs.creator)
		.append(this.createTime, rhs.createTime)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id) 
		.append(this.name) 
		.append(this.selectSql) 
		.append(this.updateSql) 
		.append(this.state) 
		.append(this.creatorId) 
		.append(this.creator) 
		.append(this.createTime) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("name", this.name) 
		.append("selectSql", this.selectSql) 
		.append("updateSql", this.updateSql) 
		.append("state", this.state) 
		.append("creatorId", this.creatorId) 
		.append("creator", this.creator) 
		.append("createTime", this.createTime) 
		.toString();
	}
   
  

}