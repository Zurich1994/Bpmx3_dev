package com.hotent.core.json;

import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.processors.DefaultValueProcessor;
import net.sf.json.util.JSONUtils;

public class DefaultDefaultValueProcessor implements DefaultValueProcessor {

	@SuppressWarnings("rawtypes")
	@Override
	public Object getDefaultValue(Class type) {
		if (JSONUtils.isArray(type)) {
			return new JSONArray();
		} else if (JSONUtils.isNumber(type)) {
			if (JSONUtils.isDouble(type)) {
				return new Double(0);
			} else {
				return new Integer(0);
			}
		} else if (JSONUtils.isBoolean(type)) {
			return Boolean.FALSE;
		} else if (JSONUtils.isString(type)) {
			return "";
		}
		return JSONNull.getInstance();
	}

}
