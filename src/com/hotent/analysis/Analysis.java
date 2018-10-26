package com.hotent.analysis;

import java.io.File;
import java.io.StringReader;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

public class Analysis {
    /**
 *  获取文件的document对象，然后获取对应的根节点
 *  @author 颜凡腾
	**/
	public static void nodeAnalysis(String xml) throws Exception{
	
	
		InputSource source = new InputSource(new StringReader(xml));
		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			document = reader.read(source);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Element root = document.getRootElement();
		getNodes(root);//从根节点开始遍历所有节点

}

	
    /**
 * 从指定节点开始,递归遍历所有子节点
 * @author 颜凡腾
 */
	public static void getNodes(Element node){
		System.out.println("--------------------");
	
		//当前节点的名称、文本内容和属性
		System.out.println("当前节点名称："+node.getName());//当前节点名称
		System.out.println("当前节点的内容："+node.getTextTrim());//当前节点名称
		List<Attribute> listAttr=node.attributes();//当前节点的所有属性的list
		for(Attribute attr:listAttr){//遍历当前节点的所有属性
			String name=attr.getName();//属性名称
			String value=attr.getValue();//属性的值ֵ
			System.out.println("属性名称："+name+"属性值："+value);
		}
	
	//递归遍历当前节点所有的子节点
		List<Element> listElement=node.elements();//所有一级子节点的list
		for(Element e:listElement){//遍历所有一级子节点
			getNodes(e);//递归
		}
	}
	
	
	public static void main (String arg[]) throws Exception
	{
		Analysis a=new Analysis();
		String xml = "<request> <param name='service' ID='tt'>single_trade_query </param><param name='_input_charset'>utf-8 </param><param name='partner'>2088001513232645 </param><param name='out_trade_no'>20090422577264 </param></request>";
		a.nodeAnalysis(xml);
	}

	}
