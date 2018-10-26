package com.hotent.platform.model.system;

import com.hotent.core.model.BaseModel;

/**
 * 对象功能:Excel模板明细 Model对象 
 * 开发公司:广州宏天软件有限公司 
 * 开发人员:ouxb 
 * 创建时间:2015-06-13 14:36:35
 */
public class SysExcelTempDetail extends BaseModel {
	// 主键
	protected Long id;
	// 模板ID
	protected Long tempId;
	// 列名称
	protected String columnName;
	// 数据类型
	protected Integer columnType;
	// 列长
	protected Integer columnLen;
	// 显示顺序
	protected Integer showIndex;

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

	public void setTempId(Long tempId) {
		this.tempId = tempId;
	}

	/**
	 * 返回 模板ID
	 * 
	 * @return
	 */
	public Long getTempId() {
		return this.tempId;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	/**
	 * 返回 列名称
	 * 
	 * @return
	 */
	public String getColumnName() {
		return this.columnName;
	}

	public void setColumnType(Integer columnType) {
		this.columnType = columnType;
	}

	/**
	 * 返回 数据类型
	 * 
	 * @return
	 */
	public Integer getColumnType() {
		return this.columnType;
	}

	public void setColumnLen(Integer columnLen) {
		this.columnLen = columnLen;
	}

	/**
	 * 返回 列长
	 * 
	 * @return
	 */
	public Integer getColumnLen() {
		return this.columnLen;
	}

	public void setShowIndex(Integer showIndex) {
		this.showIndex = showIndex;
	}

	/**
	 * 返回 显示顺序
	 * 
	 * @return
	 */
	public Integer getShowIndex() {
		return this.showIndex;
	}

}