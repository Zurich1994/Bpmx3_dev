package com.hotent.platform.service.bpm;

import java.io.ByteArrayOutputStream;
import java.util.Map;

import javax.xml.soap.SOAPMessage;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hotent.core.soap.SoapUtil;
import com.hotent.core.util.AppUtil;
import com.hotent.platform.model.bpm.BpmCommonWsSet;


public class WebserviceHelper {
	private static BpmCommonWsSetService bpmCommonWsSetService;
	private static Logger logger = LoggerFactory.getLogger(WebserviceHelper.class);
	
	private static BpmCommonWsSet getSet(String alias){
		if(bpmCommonWsSetService==null){
			bpmCommonWsSetService = (BpmCommonWsSetService)AppUtil.getBean(BpmCommonWsSetService.class);
		}
		return bpmCommonWsSetService.getByAlias(alias);
	}
	
	
	private static SOAPMessage execute(String alias,Map<String,Object> map) throws Exception{
		BpmCommonWsSet bpmCommonWsSet = getSet(alias);
		if(bpmCommonWsSet==null)return null;
		String document = bpmCommonWsSet.getDocument();
		JSONObject jObject = JSONObject.fromObject(document);
		return SoapUtil.execute(map, jObject);
	}
	
	/**
	 * 通过通用webservice设置的别名调用对应的服务
	 * @param alias webservice设置别名
	 * @param map 调用方法的入参
	 * @return SOAPMessage格式的返回值
	 * @throws Exception
	 */
	public static SOAPMessage executeObj(String alias,Map<String,Object> map) throws Exception{
		return execute(alias, map);
	}
	
	/**
	 * 通过通用webservice设置的别名调用对应的服务
	 * @param alias webservice设置别名
	 * @param map 调用方法的入参
	 * @return xml格式的返回值
	 * @throws Exception
	 */
	public static String executeXml(String alias,Map<String,Object> map) throws Exception{
		SOAPMessage responseMessage = execute(alias, map);
		Source source = responseMessage.getSOAPPart().getContent();
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		ByteArrayOutputStream myOutStr = new ByteArrayOutputStream();
		StreamResult res = new StreamResult();
		res.setOutputStream(myOutStr);
		transformer.transform(source, res);
		String xml = myOutStr.toString().trim();
		return xml;
	}
}
