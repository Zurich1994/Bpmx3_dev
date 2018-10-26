/**
 * 
 */
package com.hotent.core.soap.type;

import javax.xml.soap.SOAPElement;

/**
 * 字符串转换器
 * 
 * @author wwz
 */
public class StringSoapType extends BaseSoapType {

	@Override
	public Class<?>[] getBeanTypes() {
		return new Class[] { String.class };
	}

	@Override
	public String[] getSoapTypes() {
		return new String[] { "string" };
	}

	@Override
	void setCurrentValue(SOAPElement element, Object obj, Class<?> klass) {
		element.setTextContent(obj.toString());
	}

	@Override
	Object convertCurrent(Class<?> klass, SOAPElement element) {
		return element.getTextContent();
	}

}
