package com.hotent.bpmjson.model;

import java.util.HashMap;
import java.util.Map;

public class BaseH5Model {
	private String _className;
	private Map<String, Object> json = new HashMap<String, Object>();

	public BaseH5Model() {
		super();
	}

	public BaseH5Model(String className, Map<String, Object> json) {
		super();
		this._className = className;
		this.json = json;
	}

	public String get_className() {
		return _className;
	}

	public void set_className(String className) {
		this._className = className;
	}

	public Map<String, Object> getJson() {
		return json;
	}

	/**
	 * 覆盖Json数据
	 * 
	 * @param json
	 */
	public void setJson(Map<String, Object> json) {
		this.json = json;
	}

	/**
	 * 追加Json数据
	 * 
	 * @param json
	 */
	public void putJson(Map<String, Object> json) {
		this.json.putAll(json);
	}
}
