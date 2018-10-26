package com.hotent.platform.xml.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <pre>
 * 对象功能:xml的Map转换器
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2012-12-29 13:59:56
 * </pre>
 */
@XmlType(name = "XmlMapConvertor")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlMapConvertor {
	private List<MapEntry> entries = new ArrayList<MapEntry>();

	public void addEntry(MapEntry entry) {
		entries.add(entry);
	}

	public static class MapEntry {
		public MapEntry() {
			super();
		}

		public MapEntry(Map.Entry<String, Object> entry) {
			super();
			this.key = entry.getKey();
			this.value = entry.getValue();
		}

		public MapEntry(String key, Object value) {
			super();
			this.key = key;
			this.value = value;
		}

		private String key;
		private Object value;

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public Object getValue() {
			return value;
		}

		public void setValue(Object value) {
			this.value = value;
		}
	}

	public List<MapEntry> getEntries() {
		return entries;
	}
}
