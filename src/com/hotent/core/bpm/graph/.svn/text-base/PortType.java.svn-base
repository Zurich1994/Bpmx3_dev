package com.hotent.core.bpm.graph;

import com.hotent.core.bpm.graph.PortType;

/**
 * 对象功能:程序设计器 端口类型
 * 开发公司:广州宏天软件有限公司 
 * 开发人员:raise
 * 创建时间:2013-02-01 08:50:46
 */
public enum PortType {
	POSITION("position"),
	NODE_PART_REFERENCE("nodePartReference"),
	AUTOMATIC_SIDE("automaticSide");

	private String text;

	PortType(String text) {
		this.text = text;
	}

	public String getText() {
		return this.text;
	}
	
	@Override
	public String toString() {
		return this.text;
	}

	public static PortType fromString(String text) {
		if (text != null) {
			for (PortType type : PortType.values()) {
				if (text.equalsIgnoreCase(type.text)) {
					return type;
				}
			}
		}
		return null;
	}
}
