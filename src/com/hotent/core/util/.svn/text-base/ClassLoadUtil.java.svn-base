package com.hotent.core.util;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContextEvent;
import javax.xml.transform.TransformerFactoryConfigurationError;

/**
 * 从动态链接库中获取相应的类
 * @author guojh
 *
 */
public class ClassLoadUtil {
	
	/**
	 * 根据流程的key，流程名称和流程设计器的xml通过转换，转换成流程activiti的XML。
	 * 
	 * @param id
	 *            流程key。
	 * @param name
	 *            流程名称。
	 * @param xml
	 *            设计器输出的流程定义xml。
	 * @return 返回转换后的xml。
	 * @throws TransformerFactoryConfigurationError
	 * @throws Exception
	 */
	public static String transform(String id, String name, String xml)
			throws TransformerFactoryConfigurationError, Exception {
		if(StringUtil.isEmpty(xml)) return "";
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("name", name);

		String xlstPath = FileUtil.getClassesPath()
				+ "com/hotent/core/bpm/graph/transform.xsl".replace("/",
						File.separator);
		xml = xml.trim();
		String str = Dom4jUtil.transXmlByXslt(xml, xlstPath, map);
		str = str.replace("&lt;", "<").replace("&gt;", ">")
				.replace("xmlns=\"\"", "").replace("&amp;", "&");

		Pattern regex = Pattern.compile("name=\".*?\"");
		Matcher regexMatcher = regex.matcher(str);
		while (regexMatcher.find()) {
			String strReplace = regexMatcher.group(0);
			String strReplaceWith = strReplace.replace("&", "&amp;")
					.replace("<", "&lt;").replace(">", "&gt;");
			str = str.replace(strReplace, strReplaceWith);
		}

		return str;
	}

	/**
	 * 初始化AppUtil
	 */
	public static void contextInitialized(ServletContextEvent event) {
		AppUtil.init(event.getServletContext());
	}
	
}