package com.hotent.core.json;

import java.beans.PropertyEditorSupport;
import java.util.Date;

import com.hotent.core.util.DateUtil;

public class SmartDateEditor extends PropertyEditorSupport {
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text == null || text.length() == 0) {
			setValue(null);
		} else {
			try {
				setValue(DateUtil.parseDate(text));
			} catch (Exception ex) {
				throw new IllegalArgumentException(
						"转换日期失败: " + ex.getMessage(), ex);
			}
		}
	}

	/**
	 * 转换为日期字符串
	 */
	@Override
	public String getAsText() {
		Object obj = getValue();
		if (obj == null) {
			return "";
		} 
		Date value = (Date) obj;
		String dateStr = null;
		try {
			dateStr = DateUtil.formatEnDate(value);
			if (dateStr.endsWith(" 00:00:00")) {
				dateStr = dateStr.substring(0, 10);
			} else if (dateStr.endsWith(":00")) {
				dateStr = dateStr.substring(0, 16);
			}
			return dateStr;
		} catch (Exception ex) {
			throw new IllegalArgumentException("转换日期失败: " + ex.getMessage(), ex);
		}
	}
}
