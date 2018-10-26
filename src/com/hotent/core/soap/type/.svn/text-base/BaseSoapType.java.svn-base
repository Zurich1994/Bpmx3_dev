/**
 * 
 */
package com.hotent.core.soap.type;

import java.util.ArrayList;
import java.util.List;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;

import org.w3c.dom.Node;

import com.hotent.core.util.StringUtil;

/**
 * @author wwz
 * 
 */
@SuppressWarnings("rawtypes")
abstract class BaseSoapType implements SoapType {

	@Override
	public abstract Class<?>[] getBeanTypes();

	@Override
	public abstract String[] getSoapTypes();

	/**
	 * 获取默认类型
	 * 
	 * @return
	 */
	private final Class<?> getDefaultClass() {
		Class[] klasses = getBeanTypes();
		if (klasses == null || klasses.length == 0) {
			return Object.class;// 默认值
		}

		return klasses[0];
	}

	abstract Object convertCurrent(Class<?> klass, SOAPElement element);

	@SuppressWarnings("unchecked")
	@Override
	public final Object convertToBean(Class<?> klass, SOAPElement... elements) throws SOAPException {
		if (elements == null || elements.length < 1) {
			return null;
		}

		if (elements.length > 1) {
			List list = new ArrayList();
			for (SOAPElement element : elements) {
				Object obj = convertCurrent(klass, element);
				list.add(obj);
			}
			return list;
		} else {
			return convertCurrent(klass, elements[0]);
		}
	}

	abstract void setCurrentValue(SOAPElement element, Object obj, Class<?> klass);

	@Override
	public final void setValue(SOAPElement element, Object obj, Class<?> klass) throws SOAPException {
		if (obj == null) {
			return;
		}
		if(klass==null)
			klass = getDefaultClass();
		setCurrentValue(element, obj, klass);
	}

	@Override
	public final Object convertToBean(SOAPElement... elements) throws SOAPException {
		return convertToBean(getDefaultClass(), elements);
	}

	@Override
	public final void setValue(SOAPElement element, Object obj) throws SOAPException {
		setValue(element, obj, getDefaultClass());
	}

}
