package com.hotent.platform.model.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * 对象功能:SYS_OBJ_LOG Model对象 开发公司:广州宏天软件有限公司 开发人员:liyj 创建时间:2015-04-27 11:09:44
 */
public class SysObjLog extends BaseModel {
	// ID
	protected Long id;
	// 操作人ID
	protected Long operatorId;
	// OPERATOR
	protected String operator;
	// CREATE_TIME
	protected Date createTime;
	// NAME
	protected String name;
	// CONTENT
	protected String content;
	// 日志对象类型
	protected String objType;
	// 一些附带参数，json格式，为了适应各种对象而设
	protected String param;

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 返回 ID
	 * 
	 * @return
	 */
	public Long getId() {
		return this.id;
	}

	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
	}

	/**
	 * 返回 操作人ID
	 * 
	 * @return
	 */
	public Long getOperatorId() {
		return this.operatorId;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	/**
	 * 返回 OPERATOR
	 * 
	 * @return
	 */
	public String getOperator() {
		return this.operator;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 返回 CREATE_TIME
	 * 
	 * @return
	 */
	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 返回 NAME
	 * 
	 * @return
	 */
	public String getName() {
		return this.name;
	}

	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 返回 CONTENT
	 * 
	 * @return
	 */
	public String getContent() {
		return this.content;
	}

	public void setObjType(String objType) {
		this.objType = objType;
	}

	/**
	 * 返回 日志对象类型
	 * 
	 * @return
	 */
	public String getObjType() {
		return this.objType;
	}

	public void setParam(String param) {
		this.param = param;
	}

	/**
	 * 返回 一些附带参数，json格式，为了适应各种对象而设
	 * 
	 * @return
	 */
	public String getParam() {
		return this.param;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof SysObjLog)) {
			return false;
		}
		SysObjLog rhs = (SysObjLog) object;
		return new EqualsBuilder().append(this.id, rhs.id).append(this.operatorId, rhs.operatorId).append(this.operator, rhs.operator).append(this.createTime, rhs.createTime).append(this.name, rhs.name).append(this.content, rhs.content).append(this.objType, rhs.objType).append(this.param, rhs.param).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.id).append(this.operatorId).append(this.operator).append(this.createTime).append(this.name).append(this.content).append(this.objType).append(this.param).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("id", this.id).append("operatorId", this.operatorId).append("operator", this.operator).append("createTime", this.createTime).append("name", this.name).append("content", this.content).append("objType", this.objType).append("param", this.param).toString();
	}

}