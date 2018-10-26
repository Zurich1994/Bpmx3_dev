package com.hotent.core.table;

import com.hotent.core.util.StringUtil;

/**
 * 列对象。 用于产生数据库列。
 */
public class ColumnModel {
	public static final String COLUMNTYPE_INT = "int";
	public static final String COLUMNTYPE_VARCHAR = "varchar";
	public static final String COLUMNTYPE_CLOB = "clob";
	public static final String COLUMNTYPE_NUMBER = "number";
	public static final String COLUMNTYPE_DATE = "date";
	public static final String COLUMNTYPE_TEXT = "text";

	// 列名
	private String name = "";
	// 列注释
	private String comment = "";
	// 是否主键
	private boolean isPk = false;
	// 是否外键
	private boolean isFk = false;
	// 是否可为空
	private boolean isNull = true;
	// 列类型
	private String columnType;
	// 字符串长度
	private int charLen = 0;
	// 小数位
	private int decimalLen = 0;
	// 整数位长度
	private int intLen = 0;
	// 外键reference table
	private String fkRefTable = "";
	// 外键reference column
	private String fkRefColumn = "";
	// 默认值
	private String defaultValue = "";
	// 列所有的表
	private String tableName = "";
	// Select 'x' as label的label
	private String label;
	//与ResutlSet的列索引等同。以1开始计数。
	private int index;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		if(StringUtil.isNotEmpty(comment)){
			comment=comment.replace("'", "''");
		}
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public boolean getIsPk() {
		return isPk;
	}

	public void setIsPk(boolean isPk) {
		this.isPk = isPk;
	}

	public boolean getIsNull() {
		return isNull;
	}

	public void setIsNull(boolean isNull) {
		this.isNull = isNull;
	}

	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	public int getCharLen() {
		return charLen;
	}

	public void setCharLen(int charLen) {
		this.charLen = charLen;
	}

	public int getDecimalLen() {
		return decimalLen;
	}

	public void setDecimalLen(int decimalLen) {
		this.decimalLen = decimalLen;
	}

	public int getIntLen() {
		return intLen;
	}

	public void setIntLen(int intLen) {
		this.intLen = intLen;
	}

	public boolean getIsFk() {
		return isFk;
	}

	public void setIsFk(boolean isFk) {
		this.isFk = isFk;
	}

	public String getFkRefTable() {
		return fkRefTable;
	}

	public void setFkRefTable(String fkRefTable) {
		this.fkRefTable = fkRefTable;
	}

	public String getFkRefColumn() {
		return fkRefColumn;
	}

	public void setFkRefColumn(String fkRefColumn) {
		this.fkRefColumn = fkRefColumn;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}
