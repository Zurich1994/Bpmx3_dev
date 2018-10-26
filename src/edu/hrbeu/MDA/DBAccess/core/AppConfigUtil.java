package edu.hrbeu.MDA.DBAccess.core;

import java.util.Properties;

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
	}
	
	
}
