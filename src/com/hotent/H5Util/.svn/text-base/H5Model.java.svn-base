package com.hotent.H5Util;

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

	public H5Model(String className, String refId, Map<String, Object> json,
			Map<String, Object> params) {
		super(className, json);
		this._refId = refId;
		this.extraParams.putAll(params);
	}

	public String get_refId() {
		return _refId;
	}

	public void set_refId(String refId) {
		_refId = refId;
	}

	public Map<String, Object> getExtraParams() {
		return extraParams;
	}

	public void setExtraParams(Map<String, Object> extraParams) {
		this.extraParams.putAll(extraParams);
	}

}
