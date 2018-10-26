/**
 * 
 */
package com.hotent.core.soap.type;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.soap.SOAPElement;
import org.apache.log4j.Logger;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.hotent.core.util.StringUtil;

/**
 * 列表类型转换器
 * 
 * @author wwz
 */
public class ListSoapType extends BaseSoapType {
	private static Logger logger = Logger.getLogger(BaseSoapType.class);
	@Override
	public Class<?>[] getBeanTypes() {
		return new Class[] { List.class };
	}

	@Override
	public String[] getSoapTypes() {
		return new String[] { "List" };
	}
	
	private Class<?> currentClass;

	@Override
	void setCurrentValue(SOAPElement element, Object obj, Class<?> klass) {
		String tagName = element.getTagName();
		try{
			List list = (List)obj;
			if(list==null)return;
			String elementName =  element.getLocalName();
			SOAPElement parentElement = element.getParentElement();
			NodeList fieldNodeList = parentElement.getElementsByTagName(elementName);
			if(fieldNodeList==null)return;
			int nodeCount = fieldNodeList.getLength();
			if(nodeCount==list.size()){
				for(int i = 0;i<nodeCount;i++){
					Object item = list.get(i);
					currentClass = item.getClass();
					SOAPElement itemElement = (SOAPElement)fieldNodeList.item(i);
					SoapTypes.getTypeByBean(currentClass).setValue(itemElement,item,currentClass);
				}
			}
			else{
				Node tempElement = element.cloneNode(true);
				element.detachNode();
				for(Object item : list){
					currentClass = item.getClass();
					SOAPElement itemElement = (SOAPElement)tempElement.cloneNode(true);
					parentElement.addChildElement(itemElement);
					SoapTypes.getTypeByBean(currentClass).setValue(itemElement,item,currentClass);
				}
			}
		}
		catch(Exception ex){
			// 设置失败,跳过.
			logger.warn("字段[" + tagName + "]设置失败.", ex);
		}
	}
	
	@Override
	Object convertCurrent(Class<?> klass, SOAPElement element) {
		String tagName = element.getTagName();
		try{
			SOAPElement parentElement = element.getParentElement();
			NodeList nodeList = parentElement.getElementsByTagName(tagName);
			int size = nodeList.getLength();
			List list = new ArrayList();
			for(int i = 0;i<size;i++){
				SOAPElement node = (SOAPElement)nodeList.item(i);
				String text = node.getTextContent();
				if(StringUtil.isEmpty(text)){
					SoapType convert = SoapTypes.getTypeByBean(null);
					Class c = Object.class;
					try{
						c = Class.forName(node.getTagName());
					}
					catch(Exception e){}
					Object obj = convert.convertToBean(c, element);
					list.add(obj);
				}
				else{
					list.add(text);
				}
			}
			return list;
		}
		catch(Exception ex){
			// 设置失败,跳过.
			logger.warn("字段[" + tagName + "]设置失败.", ex);
			return null;
		}
	}
}
