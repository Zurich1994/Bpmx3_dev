package com.haiya.produce.script;

import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.jaxen.dom4j.Dom4jXPath;
import org.springframework.stereotype.Component;

import com.hotent.core.engine.IScript;
import com.hotent.core.exception.BusDataException;
import com.hotent.core.util.Dom4jUtil;
import com.hotent.platform.service.bpm.WebserviceHelper;

/**
 *<pre>
 * 对象功能:海雅专用Script
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ouxb
 * 创建时间:2015-6-8 14:30:51
 *</pre>
 */
@Component
public class HaiyaScript implements IScript {
	
	/**
	 * 商友接口数据对接公共处理方法
	 * @return
	 * @throws Exception 
	 */
	public Map<String,Object> webserviceScript(String alias,Map<String,Object> map) throws Exception{
		String xml = WebserviceHelper.executeXml(alias, map);
		Document doc= Dom4jUtil.loadXml(xml);
		/*Dom4jXPath path = new Dom4jXPath("//ns:CYDataPorcessResult");
		path.addNamespace("ns", "http://WebXml.com.cn/");
		List<Node> nodes = path.selectNodes(doc);*/

		List<Node> nodes = doc.selectNodes("//CYDataPorcessResult");
		if(1==1){
			throw new BusDataException("数据已经存在,请检查表单数据!");
		}
		return null;
	}
	
	public static void main(String[] args) {
		String path = "//soap:Body/CYDataPorcessResponse/CYDataPorcessResult";
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><soap:Body><CYDataPorcessResponse xmlns=\"http://tempuri.org/\"><CYDataPorcessResult>{\"Type\":-1,\"Message\":\"操作代码异常，请检查传入参数\"}</CYDataPorcessResult></CYDataPorcessResponse></soap:Body></soap:Envelope>";
		//String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><do-config><do path=\"User/adduser\" type=\"UserPackage.UserServlet\"><forward name=\"Success\">AddSuccess.jsp</forward></do></do-config>  ";
		Document doc= Dom4jUtil.loadXml(xml);
		try {
			// 获取根节点  
            Element rootElt = doc.getRootElement();   
            
            Element envelopeElt = rootElt.element("//soap:Envelope");
            
			Element bodyElt = envelopeElt.element("soap:Body");

			Element cyDataElt = bodyElt.element("CYDataPorcessResponse");

			Element resultElt = cyDataElt.element("CYDataPorcessResult");
			
			if(resultElt == null){
				System.out.println("1111");
			}
			String value = resultElt.getText();
			System.out.println(value);
			
		} catch (Exception e) {
		}
	}
	
}
