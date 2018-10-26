package com.hotent.core.util;

import java.util.Enumeration;
import java.util.Properties;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.util.ObjectUtils;

import com.hotent.core.encrypt.EncryptUtil;

/**
 * 加密属性密码。
 * <pre>
 * 扩展PropertyPlaceholderConfigurer用于加密密码。
 * 配置在：
 * &lt;bean id="propertyConfigurer"  class="com.hotent.core.util.ExtpPropertyPlaceholderConfiger">
 *        &lt;property name="properties" ref="configproperties"/>
 *  &lt;/bean>
 * </pre>
 * 
 * @author ray
 *
 */
public class ExtpPropertyPlaceholderConfiger extends PropertyPlaceholderConfigurer {
	
	private String[] encryptNames={"jdbc.password"};
	
	@Override
	protected void convertProperties(Properties props) {
		Enumeration propertyNames = props.propertyNames();
		while (propertyNames.hasMoreElements()) {
			String propertyName = (String) propertyNames.nextElement();
			String propertyValue = props.getProperty(propertyName);
			
			
			String convertedValue = convertPropertyValue(propertyValue);
			if(isEncryptProp(propertyName)){
				try {
					convertedValue=EncryptUtil.decrypt(convertedValue);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (!ObjectUtils.nullSafeEquals(propertyValue, convertedValue)) {
				
				props.setProperty(propertyName, convertedValue);
			}
		}
	}
	
	

	private boolean isEncryptProp(String propertyName){
		for(String name :encryptNames){
			if(name.equals(propertyName)){
				return true;
			}
		}
		return false;
	}
}
