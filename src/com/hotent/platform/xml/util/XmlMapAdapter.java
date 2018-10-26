package com.hotent.platform.xml.util;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * <pre>
 * 对象功能:xml转换map适配器
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2012-12-29 13:59:56
 * 使用方法：在Map属性加 @XmlJavaTypeAdapter(XmlMapAdapter.class)   
 * </pre>
 */
public class XmlMapAdapter extends
		XmlAdapter<XmlMapConvertor, Map<String, Object>> {
	@Override
	public XmlMapConvertor marshal(Map<String, Object> map) throws Exception {
		XmlMapConvertor convertor = new XmlMapConvertor();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			XmlMapConvertor.MapEntry e = new XmlMapConvertor.MapEntry(entry);
			convertor.addEntry(e);
		}
		return convertor;
	}

	@Override
	public Map<String, Object> unmarshal(XmlMapConvertor map) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		for (XmlMapConvertor.MapEntry e : map.getEntries()) {
			result.put(e.getKey(), e.getValue());
		}
		return result;
	}
}
