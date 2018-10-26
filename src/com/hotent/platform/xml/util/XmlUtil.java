package com.hotent.platform.xml.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;

import com.hotent.core.util.BeanUtils;


/**
 * 导入导出帮助类
 * @author zxh
 *
 */
public class XmlUtil {
	/**
	 * 验证格式是否正确
	 * @param root
	 * @param firstName 第一个节点名
	 * @param nextName 第二个节点名
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static void checkXmlFormat(Element root,String firstName,String nextName) throws Exception {
		String msg = "导入文件格式不对";
		if (!root.getName().equals(firstName))
			throw new Exception(msg);
		List<Element> itemLists = root.elements();
		for (Element elm : itemLists) {
			if (!elm.getName().equals(nextName))
				throw new Exception(msg);
		}

	}
	
	
	/**
	 * 获取自定义表默认的导出的Map
	 * 
	 * @param paraTypeMap
	 * @return
	 */
	public static Map<String, Boolean> getTableDefaultExportMap(Map<String, Boolean> map) {
		if (BeanUtils.isEmpty(map)) {
			map = new HashMap<String, Boolean>();
			map.put("bpmFormTable", true);
			map.put("bpmFormField", true);
			map.put("subTable", true);
			map.put("identity", true);
		}
		return map;
	}

	
	/**
	 * 获取自定义表单默认的导出的Map
	 * 
	 * @param paraTypeMap
	 * @return
	 */
	public static Map<String, Boolean> getFormDefaultExportMap(Map<String, Boolean> map){
		if(BeanUtils.isEmpty(map)){
			 map = new HashMap<String, Boolean>();
			map.put("bpmFormDef", true);
			map.put("bpmFormTable", false);
			map.put("bpmFormDefOther", false);
			map.put("bpmFormRights",true);
			map.put("bpmTableTemplate", true);
		}
		return map;
	}
	
}
