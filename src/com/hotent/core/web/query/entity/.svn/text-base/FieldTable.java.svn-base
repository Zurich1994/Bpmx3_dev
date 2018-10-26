package com.hotent.core.web.query.entity;


/**
 * 保存表名信息
 * 
 * @author win
 * 
 */
public abstract class FieldTable {
	/**
	 * 字段所属表名
	 */
	protected String tableName;

	/**
	 * 字段名
	 */
	protected String fieldName;
	/**
	 * 是否主表
	 */
	protected boolean isMain = true;

	/**
	 * 修正的字段名
	 */
	protected String fixFieldName;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public boolean isMain() {
		return isMain;
	}

	public void setMain(boolean isMain) {
		this.isMain = isMain;
	}

	public String getFixFieldName() {
		return FieldTableUtil.fixFieldName(fieldName, tableName);
	}

}
