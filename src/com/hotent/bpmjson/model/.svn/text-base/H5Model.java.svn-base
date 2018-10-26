package com.hotent.bpmjson.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Refer to "http://demo.qunee.com" api for more json infomation<br>
 * <p>
 * "_className": "Q.Node", "json": {}, "_refId": "814", "params": {}
 * </p>
 * 
 * @author Administrator
 */
public class H5Model extends BaseH5Model {
	private String _refId;
	private Map<String, Object> extraParams = new HashMap<String, Object>();// 额外参数

	public H5Model() {
		super();
	}

	public H5Model(String className, String refId, Map<String, Object> json) {
		super(className, json);
		this._refId = refId;
	}

	public String get_refId() {
		return _refId;
	}

	public void set_refId(String refId) {
		this._refId = refId;
	}

	public Map<String, Object> getExtraParams() {
		return extraParams;
	}

	/**
	 * 覆盖额外参数
	 * 
	 * @param extraParams
	 */
	public void setExtraParams(Map<String, Object> extraParams) {
		this.extraParams = extraParams;
	}

	/**
	 * 追加额外参数
	 * 
	 * @param extraParams
	 */
	public void putExtraParams(Map<String, Object> extraParams) {
		this.extraParams.putAll(extraParams);
	}

}
