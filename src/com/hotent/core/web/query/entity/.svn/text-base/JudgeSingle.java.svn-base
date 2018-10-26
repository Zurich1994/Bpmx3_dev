package com.hotent.core.web.query.entity;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 
 * <pre>
 * 	对象功能:查询条件的单值封装
 * 	开发公司:广州宏天软件有限公司
 * 	开发人员:win
 * 	创建时间:2013-10-16 16:38:00
 * </pre>
 * 
 */
public class JudgeSingle extends FieldTable {

	/**
	 * 值比较符
	 */
	protected String compare;
	/**
	 * 值
	 */
	protected String value;

	// ========== getter 和 setter ============


	public String getCompare() {
		return compare;
	}

	public void setCompare(String compare) {
		this.compare = compare;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("fieldName", fieldName)
				.append("compare", compare).append("value", value).toString();
	}

}
