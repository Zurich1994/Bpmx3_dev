package com.hotent.platform.model.form;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;

/**
 * 对象功能:树形自定义对话框和业务数据模板组合
 * 开发公司:广州宏天软件有限公司 
 * 开发人员:liyj 
 * 创建时间:2015-05-20 09:55:36
 */
public class FormDefCombinate extends BaseModel {
	// ID
	protected Long id;
	// NAME
	protected String name;
	// ALIAS
	protected String alias;
	// TREE_DIALOG_ID
	protected Long treeDialogId;
	// TREE_DIALOG_NAME
	protected String treeDialogName;
	// FORM_DEF_ID
	protected Long formDefId;
	// FORM_DEF_NAME
	protected String formDefName;
	// 树数据返回数据对应列表数据的查询条件
	protected String field;

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

	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * 返回 ALIAS
	 * 
	 * @return
	 */
	public String getAlias() {
		return this.alias;
	}

	public void setTreeDialogId(Long treeDialogId) {
		this.treeDialogId = treeDialogId;
	}

	/**
	 * 返回 TREE_DIALOG_ID
	 * 
	 * @return
	 */
	public Long getTreeDialogId() {
		return this.treeDialogId;
	}

	public void setTreeDialogName(String treeDialogName) {
		this.treeDialogName = treeDialogName;
	}

	/**
	 * 返回 TREE_DIALOG_NAME
	 * 
	 * @return
	 */
	public String getTreeDialogName() {
		return this.treeDialogName;
	}

	public void setFormDefId(Long formDefId) {
		this.formDefId = formDefId;
	}

	/**
	 * 返回 FORM_DEF_ID
	 * 
	 * @return
	 */
	public Long getFormDefId() {
		return this.formDefId;
	}

	public void setFormDefName(String formDefName) {
		this.formDefName = formDefName;
	}

	/**
	 * 返回 FORM_DEF_NAME
	 * 
	 * @return
	 */
	public String getFormDefName() {
		return this.formDefName;
	}

	public void setField(String field) {
		this.field = field;
	}

	/**
	 * 返回 树数据返回数据对应列表数据的查询条件
	 * 
	 * @return
	 */
	public String getField() {
		return this.field;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof FormDefCombinate)) {
			return false;
		}
		FormDefCombinate rhs = (FormDefCombinate) object;
		return new EqualsBuilder().append(this.id, rhs.id).append(this.name, rhs.name).append(this.alias, rhs.alias).append(this.treeDialogId, rhs.treeDialogId).append(this.treeDialogName, rhs.treeDialogName).append(this.formDefId, rhs.formDefId).append(this.formDefName, rhs.formDefName).append(this.field, rhs.field).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.id).append(this.name).append(this.alias).append(this.treeDialogId).append(this.treeDialogName).append(this.formDefId).append(this.formDefName).append(this.field).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("id", this.id).append("name", this.name).append("alias", this.alias).append("treeDialogId", this.treeDialogId).append("treeDialogName", this.treeDialogName).append("formDefId", this.formDefId).append("formDefName", this.formDefName).append("field", this.field).toString();
	}

}