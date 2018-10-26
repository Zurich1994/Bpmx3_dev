package com.hotent.core.web.query.entity;

/**
 * 过滤条件
 * 
 * @author zxh
 * 
 */
public class Filter {

	// 名字
	private String name;
	// 备注（显示名）
	private String desc;
	// key
	private String key;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
