/**
 * 
 */
package com.hotent.core.soap.type;

import java.math.BigDecimal;

import javax.xml.soap.SOAPElement;

/**
 * @author wwz
 * 
 */
public class NumberSoapType extends BaseSoapType {

	@Override
	public Class<?>[] getBeanTypes() {
		return new Class[] { int.class, Integer.class ,long.class,Long.class,short.class,Short.class,double.class,Double.class,float.class,Float.class,BigDecimal.class};
	}

	@Override
	public String[] getSoapTypes() {
		return new String[] { "int", "long", "short", "integer"};
	}

	@Override
	void setCurrentValue(SOAPElement element, Object obj, Class<?> klass) {
		element.setTextContent(obj.toString());
	}

	@Override
	Object convertCurrent(Class<?> klass, SOAPElement element) {
		String value = element.getTextContent();
		if (klass == Integer.class) {
			return Integer.valueOf(value);
		} else if (klass == Long.class) {
			return Long.valueOf(value);
		} else if (klass == Short.class) {
			return Short.valueOf(value);
		} else if (klass == Double.class) {
			return Double.valueOf(value);
		} else if (klass == Float.class) {
			return Float.valueOf(value);
		} else if (klass == BigDecimal.class) {
			return BigDecimal.valueOf(Double.valueOf(value));
		} else {
			return value;
		}
	}

}
