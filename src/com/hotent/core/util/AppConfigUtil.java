package com.hotent.core.util;

import java.util.Properties;

/**
 * 系统参数读取。
 * @author ray
 *
 */
public class AppConfigUtil {
	
	/**
	 * 从配置文件中读取配置属性
	 * @param propertyKey 属性key
	 * @return
	 */
	public static String get(String propertyKey){
		Properties properties=(Properties)AppUtil.getBean("configproperties");
		return properties.getProperty(propertyKey);
	}
	
	/**
	 * 从参数文件中读取整形的配置参数。
	 * @param propertyKey
	 * @return
	 */
	public static int getInt(String propertyKey){
		String val=get(propertyKey);
		return Integer.parseInt(val);
		
	}
	
	/**
	 * 从参数文件中读取整形的配置参数，可以设置默认值。
	 * @param propertyKey	键值
	 * @param defaultValue	默认值
	 * @return
	 */
	public static int getInt(String propertyKey,int defaultValue){
		String val=get(propertyKey);
		if(StringUtil.isEmpty(val)) return defaultValue;
		return Integer.parseInt(val);
	}
	
	/**
	 * 从配置文件中读取配置属性
	 * @param propertyKey 属性key
	 * @param defultValue 如果为空，填充默认值。
	 * @return
	 */
	public static String get(String propertyKey,String defultValue){
		
		Properties properties=(Properties)AppUtil.getBean("configproperties");
		String propertie = properties.getProperty(propertyKey);
		
		if(StringUtil.isEmpty(propertie)) return defultValue;
		return propertie;
	
	/*
			String val= get(propertyKey);
		if(StringUtil.isEmpty(val)) return defultValue;
		return val;

	*/
	
	
	
	}
	
	/**
	 * 获取布尔值属性。
	 * @param propertyKey 属性key
	 * @return
	 */
	public static boolean getBoolean(String propertyKey){
		String val= get(propertyKey);
		return val.equalsIgnoreCase("true");
	}
}
