package com.hotent.core.json;

import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import com.hotent.core.util.DateFormatUtil;

/**
 * 日期JSON格式化
 * <pre> 
 * 作者：hugh zhuang
 * 邮箱:zhuangxh@jee-soft.cn
 * 日期:2014-3-20-下午2:57:36
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public class DateJsonValueProcessor implements JsonValueProcessor {
	//格式化的日期格式
	private String format = "yyyy-MM-dd HH:mm:ss";

	public DateJsonValueProcessor(String format) {
		super();
		this.format = format;
	}

	public DateJsonValueProcessor() {
		super();
	}

	@Override
	public Object processArrayValue(Object value, JsonConfig jsonConfig) {
		return this.process(value);
	}

	@Override
	public Object processObjectValue(String key, Object value,
			JsonConfig jsonConfig) {
		return this.process(value);
	}

	private Object process(Object value) {
		if (value == null)
			return "";
		else if (value instanceof Date) {
			try {
				return DateFormatUtil.format((Date) value, format);
			} catch (Exception ex) {
			}
		}
		return value.toString();
	}

}
