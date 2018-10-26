/**
 * 
 */
package com.hotent.core.soap.type;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;

/**
 * soap类型转换
 * 
 * @author wwz
 * 
 */
public interface SoapType {

	/**
	 * 将soap返回值转换成java类型
	 * 
	 * @param klass
	 * @param elements
	 * @return
	 */
	public Object convertToBean(Class<?> klass, SOAPElement... elements) throws SOAPException;

	public Object convertToBean(SOAPElement... elements) throws SOAPException;

	/**
	 * 将对象转换后设置到paren中
	 * 
	 * @param element
	 * @param obj
	 * @param klass
	 */
	public void setValue(SOAPElement element, Object obj, Class<?> klass) throws SOAPException;

	public void setValue(SOAPElement element, Object obj) throws SOAPException;

	/**
	 * 当前转换器支持的java类型列表
	 * 
	 * @return
	 */
	public Class<?>[] getBeanTypes();

	/**
	 * 当前转换器支持的soap类型列表
	 * 
	 * @return
	 */
	public String[] getSoapTypes();
}
