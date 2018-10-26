package com.hotent.platform.xml.util;

import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.DateFormatUtil;


/**
 * <pre>
 * 对象功能:xml转换日期适配器
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2012-12-29 13:59:56
 * 使用方法：在Date属性加 @XmlJavaTypeAdapter(XmlMapAdapter.class) 
 * </pre>
 */
public class XmlDateAdapter extends XmlAdapter<String, Date> {
	@Override
	public Date unmarshal(String dateString) throws Exception {
		if (BeanUtils.isEmpty(dateString))
			return null;
		return DateFormatUtil.parse(dateString);
	}

	@Override
	public String marshal(Date date) throws Exception {
		if (BeanUtils.isEmpty(date))
			return null;
		return DateFormatUtil.format(date);
	}
}