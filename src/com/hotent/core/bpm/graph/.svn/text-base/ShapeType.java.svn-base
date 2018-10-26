package com.hotent.core.bpm.graph;

import com.hotent.core.bpm.graph.ShapeType;

/**
 * 对象功能:程序设计器 连接类型
 * 开发公司:广州宏天软件有限公司 
 * 开发人员:raise
 * 创建时间:2013-02-01 08:50:46
 */
public enum ShapeType {
	STRAIGHT("straight"),
	FREE("free"),
	ORTHOGONAL("orthogonal"),
	OBLIQUE("oblique");

	private String text;

	ShapeType(String text) {
		this.text = text;
	}

	public String getText() {
		return this.text;
	}
	
	@Override
	public String toString() {
		return this.text;
	}

	public static ShapeType fromString(String text) {
		if (text != null) {
			for (ShapeType type : ShapeType.values()) {
				if (text.equalsIgnoreCase(type.text)) {
					return type;
				}
			}
		}
		return null;
	}
}
