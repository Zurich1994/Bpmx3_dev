package com.hotent.platform.model.bpm;

import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * 对象功能:bpm_node_sql Model对象 开发公司:广州宏天软件有限公司 开发人员:liyj 创建时间:2015-03-05 09:59:29
 */
public class BpmNodeSql extends BaseModel {
	/**
	 * 保存
	 */
	public static String ACTION_SAVE="save";
	/**
	 * 提交	
	 */
	public static String ACTION_SUBMIT="submit";
	/**
	 * 同意
	 */
	public static String ACTION_AGREE="agree";
	/**
	 * 反对
	 */
	public static String ACTION_OPPOSITE="opposite";
	/**
	 * 驳回
	 */
	public static String ACTION_REJECT="reject";
	/**
	 * 删除
	 */
	public static String ACTION_DELETE="delete";
	/**
	 * 结束
	 */
	public static String ACTION_END="end";
	
	// ID
	protected Long id;
	// NAME
	protected String name;
	// 数据源别名
	protected String dsAlias;
	// 流程id
	protected String actdefId;
	// 节点ID
	protected String nodeId;
	// 触发时机：
	protected String action;
	// SQL语句
	protected String sql;
	// 描叙
	protected String desc;

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

	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * 返回 触发时机：
	 * 
	 * @return
	 */
	public String getAction() {
		return this.action;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	/**
	 * 返回 SQL语句
	 * 
	 * @return
	 */
	public String getSql() {
		return this.sql;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * 返回 描叙
	 * 
	 * @return
	 */
	public String getDesc() {
		return this.desc;
	}

	/**
	 * dsAlias
	 * @return  the dsAlias
	 * @since   1.0.0
	 */
	
	public String getDsAlias() {
		return dsAlias;
	}

	/**
	 * @param dsAlias the dsAlias to set
	 */
	public void setDsAlias(String dsAlias) {
		this.dsAlias = dsAlias;
	}

	/**
	 * actdefId
	 * @return  the actdefId
	 * @since   1.0.0
	 */
	
	public String getActdefId() {
		return actdefId;
	}

	/**
	 * @param actdefId the actdefId to set
	 */
	public void setActdefId(String actdefId) {
		this.actdefId = actdefId;
	}

	/**
	 * nodeId
	 * @return  the nodeId
	 * @since   1.0.0
	 */
	
	public String getNodeId() {
		return nodeId;
	}

	/**
	 * @param nodeId the nodeId to set
	 */
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof BpmNodeSql)) {
			return false;
		}
		BpmNodeSql rhs = (BpmNodeSql) object;
		return new EqualsBuilder().append(this.id, rhs.id).append(this.name, rhs.name).append(this.dsAlias, rhs.dsAlias).append(this.actdefId, rhs.actdefId).append(this.nodeId, rhs.nodeId).append(this.action, rhs.action).append(this.sql, rhs.sql).append(this.desc, rhs.desc).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.id).append(this.name).append(this.dsAlias).append(this.actdefId).append(this.nodeId).append(this.action).append(this.sql).append(this.desc).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("id", this.id).append("name", this.name).append("dsAlias", this.dsAlias).append("actdefId", this.actdefId).append("nodeId", this.nodeId).append("action", this.action).append("sql", this.sql).append("desc", this.desc).toString();
	}

}