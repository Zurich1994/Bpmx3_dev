package com.hotent.core.web.query.entity;

import com.hotent.core.web.query.util.QueryConstants;

/**
 * 排序字段的数据封装
 * 
 * @author csx
 */
public class FieldSort extends FieldTable {

	/**
	 * 升或降序
	 */
	private String orderBy = QueryConstants.SORT_ASC;

	public FieldSort() {
	}

	public FieldSort(String tableName, String fieldName, String orderBy) {
		super();
		this.tableName = tableName;
		this.fieldName = fieldName;
		this.orderBy = orderBy;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
}
