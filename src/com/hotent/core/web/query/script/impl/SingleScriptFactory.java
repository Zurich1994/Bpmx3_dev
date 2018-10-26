package com.hotent.core.web.query.script.impl;

import com.hotent.core.web.query.script.ISingleScript;

/**
 * 单值工厂
 * 
 * @author zxh
 * 
 */
public class SingleScriptFactory {
	public final static int OPT_TYPE_NUMBER = 1; // 数值
	public final static int OPT_TYPE_STRING = 2; // 字符串
	public final static int OPT_TYPE_DATE = 3; // 日期
	public final static int OPT_TYPE_DIC = 4; // 字典
	public final static int OPT_TYPE_SELECTOR = 5; // 选择器

	public static ISingleScript getQueryScript(Integer optType) {
		switch (optType) {
		case OPT_TYPE_NUMBER:
		case OPT_TYPE_DATE:
			return new NumberScript();
		case OPT_TYPE_STRING:
		case OPT_TYPE_DIC:
			return new StringScript();
		case OPT_TYPE_SELECTOR:
			return new InScript();
		default:
			return new NumberScript();
		}

	}
}
