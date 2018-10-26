package com.hotent.platform.model.bpm;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;

/**
 * <pre>
 * 对象功能:流程webservice节点参数 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2012-12-21 16:51:20
 * </pre>
 */
public class BpmNodeWsParams extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 为输入参数， INPUT_PARAMS=1;
	 */
	public final static Short INPUT_PARAMS = 1;
	/**
	 * 为输出参数， OUTPUT_PARAMS=0;
	 */
	public final static Short OUTPUT_PARAMS = 0;

	// 主键
	protected Long id;
	// webservice节点ID
	protected Long webserviceId;
	// 参数类型【1：表示输入参数，0：输出参数】
	protected Short paraType;
	// 变量id
	protected Long varId;
	// webservice变量名称
	protected String wsName;
	// 变量类型【变量类型，string long date,等】
	protected String type;
	// 变量名称
	protected String varName;

	public String getVarName() {
		return varName;
	}

	public void setVarName(String varName) {
		this.varName = varName;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 返回 主键
	 * 
	 * @return
	 */
	public Long getId() {
		return this.id;
	}

	public void setWebserviceId(Long webserviceId) {
		this.webserviceId = webserviceId;
	}

	/**
	 * 返回 webservice节点ID
	 * 
	 * @return
	 */
	public Long getWebserviceId() {
		return this.webserviceId;
	}

	public void setParaType(Short paraType) {
		this.paraType = paraType;
	}

	/**
	 * 返回 参数类型【1：表示输入参数，0：输出参数】
	 * 
	 * @return
	 */
	public Short getParaType() {
		return this.paraType;
	}

	public void setVarId(Long varId) {
		this.varId = varId;
	}

	/**
	 * 返回 变量id
	 * 
	 * @return
	 */
	public Long getVarId() {
		return this.varId;
	}

	public void setWsName(String wsName) {
		this.wsName = wsName;
	}

	/**
	 * 返回 webservice变量名称
	 * 
	 * @return
	 */
	public String getWsName() {
		return this.wsName;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 返回 变量类型【变量类型，string long date,等】
	 * 
	 * @return
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof BpmNodeWsParams)) {
			return false;
		}
		BpmNodeWsParams rhs = (BpmNodeWsParams) object;
		return new EqualsBuilder().append(this.id, rhs.id)
				.append(this.webserviceId, rhs.webserviceId)
				.append(this.paraType, rhs.paraType)
				.append(this.varId, rhs.varId).append(this.wsName, rhs.wsName)
				.append(this.type, rhs.type).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.id)
				.append(this.webserviceId).append(this.paraType)
				.append(this.varId).append(this.wsName).append(this.type)
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("id", this.id)
				.append("webserviceId", this.webserviceId)
				.append("paraType", this.paraType).append("varId", this.varId)
				.append("wsName", this.wsName).append("type", this.type)
				.toString();
	}

}