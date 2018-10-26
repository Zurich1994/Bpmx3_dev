package com.hotent.core.excel.reader;

public class FieldEntity {
	
	/** 主键=1 */
	public final static Short IS_KEY = 1;
	/** 不是主键=0 */
	public final static Short NOT_KEY = 0;
	/**
	 * 字段名
	 */
	private String name;
	/**
	 * 字段值
	 */
	private String value;
	/**
	 * 是否是主键或外键
	 */
	private Short isKey =NOT_KEY;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Short getIsKey() {
		return isKey;
	}
	public void setIsKey(Short isKey) {
		this.isKey = isKey;
	}
	
	
}
