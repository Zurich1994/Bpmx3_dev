package com.hotent.core.soap;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import com.hotent.core.engine.GroovyScriptEngine;
import com.hotent.core.soap.type.SoapType;
import com.hotent.core.soap.type.SoapTypes;
import com.hotent.core.util.AppConfigUtil;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.Dom4jUtil;
import com.hotent.core.util.StringUtil;
import edu.emory.mathcs.backport.java.util.Arrays;

/**
 * SOAP调用工具类
 * 
 * @author heyifan
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class SoapUtil {

	private static Logger logger = LoggerFactory.getLogger(SoapUtil.class);
	//连接超时时间
	private static Integer _connTimeout = 0;
	//读取超时时间
	private static Integer _readTimeout = 0;
	
	private static GroovyScriptEngine engine = new GroovyScriptEngine();

	/**
	 * 调用出错异常
	 * 
	 */
	@SuppressWarnings("serial")
	public static class InvokeException extends Exception {

		/**
		 * 调用webservice出错时返回的{@link SOAPFault#getFaultCode()}值.
		 */
		private String code;

		/**
		 * 调用webservice出错时返回的{@link SOAPFault#getFaultString()}值.
		 */
		private String msg;

		public InvokeException(String code, String msg) {
			this(code, msg, null);
		}

		public InvokeException(String code, String msg, Throwable e) {
			super("[" + code + "]" + msg, e);
			this.code = code;
			this.msg = msg;
		}

		public String getCode() {
			return code;
		}

		public String getMsg() {
			return msg;
		}
	}
	
	/**
	 * 通过绑定的key获取到变量map中的根级流程变量
	 * @param variables
	 * @param binding
	 * @return
	 * @throws Exception
	 */
	private static Object getWholeObject(Map variables,String binding) throws Exception{
		Object obj = null;
		Pattern regex = Pattern.compile("(\\w*)\\..*");
		Matcher regexMatcher = regex.matcher(binding);
		if (regexMatcher.find()) {
			String varKey = regexMatcher.group(1);
			obj = variables.get(varKey);
		}
		//否则可以直接取值
		else{
			obj = PropertyUtils.getProperty(variables, binding);
		}					
		return obj;
	}

	/**
	 * webservice入参处理器
	 * @author heyifan
	 *
	 */
	private static class RequestBuilder {

		public static SOAPMessage build(JSONArray jarray,JSONArray inputParams,String namespace,String method, Map variables,String soapaction) throws Exception{
			if(StringUtil.isNotEmpty(soapaction)){
				soapaction = namespace + method;
			}
			return buildRequest(createRequest(jarray,inputParams,namespace,method ,variables,soapaction),soapaction);
		}

		private static SOAPMessage buildRequest(SOAPElement element,String soapaction) throws SOAPException {
			// 创建消息工厂
			MessageFactory messageFactory = MessageFactory.newInstance();
			// 根据消息工厂创建SoapMessage
			SOAPMessage message = messageFactory.createMessage();
			
			if(StringUtil.isNotEmpty(soapaction)){
				MimeHeaders mHers=message.getMimeHeaders();  
				mHers.setHeader("SOAPAction", soapaction);
			}
             
			// 创建soap消息主体
			SOAPPart soapPart = message.getSOAPPart();// 创建soap部分
			SOAPEnvelope envelope = soapPart.getEnvelope();
			// 可以通过SoapEnvelope有效的获取相应的Body和Header等信息
			SOAPBody body = envelope.getBody();

			body.addChildElement(element);
			// Save the message
			message.saveChanges();
			return message;
		}
		
		private static void buildSoapElementValue(SOAPElement soapElement, JSONObject jobject, Map variables)
				throws Exception {

			if(jobject==null)return;
			// 设值
			String binding = jobject.getString("bindingVal");// 绑定参数
			String soapType = jobject.getString("soapType");// soap类型
			String javaType = jobject.getString("javaType");// java类型
			Integer bindingType = jobject.getInt("bindingType");//绑定类型
			binding = StringUtil.jsonUnescape(binding);
			
			String listObj = "";
			String elementStr = "";
			Pattern regex = Pattern.compile("^.*\\.(\\w+)\\[i\\](\\.\\w+)?$");
			Matcher regexMatcher = regex.matcher(binding);
			if (regexMatcher.find()) {
				listObj = regexMatcher.group(1);
				elementStr = regexMatcher.group(2);
			}
			try{
				Object obj = null;
				switch(bindingType){
					//固定值
					case 1:
						soapElement.setTextContent(binding);
						break;
					//来自流程变量
					case 2:
						if (StringUtil.isNotEmpty(binding)) {
							obj = PropertyUtils.getProperty(variables, binding);
						}
						break;
					//脚本获取
					case 3:
						GroovyScriptEngine scriptEngine = new GroovyScriptEngine();
						String scriptContent = binding;
						obj = scriptEngine.executeObject(scriptContent, variables);
						break;
				}
				//有值时才设置
				if (obj != null) {
					SoapType converter = null;
					Class klass = null;
					//webservice入参
					if (soapType != null) {
						converter = SoapTypes.getTypeBySoap(soapType);
					}
					//流程变量的类型
					else if(javaType != null) {
						klass = Class.forName(javaType);
						converter = SoapTypes.getTypeByBean(klass);
					}
					//该值不为空 说明 该webservice入参为 List类型
					if(StringUtil.isNotEmpty(listObj)){
						if(obj instanceof List){
							List list = (List)obj;
							String elementName =  soapElement.getLocalName();
							SOAPElement parentElement = soapElement;
							//判断绑定设置是 针对 List对象 还是 List对象下的一个属性
							if(StringUtil.isNotEmpty(elementStr))
								parentElement = soapElement.getParentElement();
							//如果列表的个数为0，则从soapElement结构中 移除该节点
							if(list.size()==0){
								parentElement.detachNode();
								return;
							}
							
							SOAPElement grandpaElement = parentElement.getParentElement();
							listObj = parentElement.getTagName();
							NodeList fieldNodeList = grandpaElement.getElementsByTagName(listObj);
							if(fieldNodeList==null)return;
							int nodeCount = fieldNodeList.getLength();
							int listSize = list.size();
							int diffCount = listSize - nodeCount;
							//根据List类型的个数  补齐 缺少的 xml节点
							for(int i=0;i<diffCount;i++){
								SOAPElement cloneElement = (SOAPElement)parentElement.cloneNode(true);
								grandpaElement.addChildElement(cloneElement);
							}
							fieldNodeList = grandpaElement.getElementsByTagName(listObj);
							for(int i = 0;i<listSize;i++){
								Object item = list.get(i);
								SOAPElement listElement = (SOAPElement)fieldNodeList.item(i);
								SOAPElement itemElement = listElement;
								if(StringUtil.isNotEmpty(elementStr))
									itemElement = (SOAPElement)listElement.getElementsByTagName(elementName).item(0);
								if(item==null){
									itemElement.detachNode();
									continue;
								}
								if(converter!=null){
									converter.setValue(itemElement, item, klass);
								}
								else {
									itemElement.setTextContent(item.toString());
								}
							}
						}
					}
					else{
						if(converter!=null){
							converter.setValue(soapElement, obj, klass);
						}
						else {
							soapElement.setTextContent(obj.toString());
						}
					}
				}
				String textContext = soapElement.getTextContent();
				boolean hasChild = soapElement.hasChildNodes();
				//如果节点既没有子节点  也没有值  则移除该节点（否则调用时 会报错）
				if(StringUtil.isEmpty(textContext)&&!hasChild){
					soapElement.detachNode();
				}
			}
			catch (Exception e) {
				logger.error("动态设值出错.", e);
				throw e;
			}
		}

		/**
		 * 根据模板创建{@link SOAPElement}对象.<br>
		 * 
		 * @param document
		 * @param variables
		 * @return
		 * @throws SOAPException
		 * @throws SAXException
		 * @throws IOException
		 * @throws ParserConfigurationException
		 * @throws IllegalAccessException
		 * @throws InvocationTargetException
		 * @throws NoSuchMethodException
		 * @throws ClassNotFoundException
		 */
		private static SOAPElement createRequest(JSONArray jarray,JSONArray inputParams,String namespace,String method ,Map variables,String soapaction) throws Exception {
			String prefix = "api";
			if(StringUtil.isNotEmpty(soapaction)){
				prefix = "";
			}
			SOAPFactory factory = SOAPFactory.newInstance();
			SOAPElement bodyElement = factory.createElement(method, prefix, namespace);
			
			if(BeanUtils.isNotEmpty(inputParams)){
				Map<JSONObject,SOAPElement> map = new HashMap<JSONObject, SOAPElement>();
				for (Object obj : inputParams) {
					JSONObject jobject = (JSONObject)obj;
					if(jobject==null)
						continue;
					String rootName = jobject.getString("name");
					SOAPElement rootElement = bodyElement.addChildElement(rootName);
					//递归构建soapElement
					setRequestStruct(jobject,rootElement,1);
					//绑定值
					setBindingValue(jarray,rootElement,1,rootName,variables,map);
				}
				
				for(Iterator<JSONObject> it = map.keySet().iterator();it.hasNext();){
					JSONObject bindingJobject = it.next();
					SOAPElement soapElement = map.get(bindingJobject);
					buildSoapElementValue(soapElement, bindingJobject, variables);
				}
			}
			else{
				for (Object obj : jarray) {
					JSONObject jobject = (JSONObject)obj;
					if(jobject==null)
						continue;
					String paramName = jobject.getString("name");
					SOAPElement element = bodyElement.addChildElement(paramName);
					// 递归绑定
					buildSoapElementValue(element, jobject, variables);
				}
			}
			return bodyElement;
		}
		
		/**
		 * 构建输入参数的结构
		 * @param jobject
		 * @param soapElement
		 * @param level 结构中的层级
		 * @throws SOAPException
		 */
		private static void setRequestStruct(JSONObject jobject,SOAPElement soapElement,int level) throws SOAPException{
			String paramName = jobject.getString("name");
			String type = jobject.getString("type");
			SOAPElement element = null;
			//根节点
			if(level==1){
				element = soapElement;
			}
			else{
				element = soapElement.addChildElement(paramName);
			}
			//复合类型需要添加到结构中
			if("bean".equals(type)){
				if(jobject.containsKey("children")){
					JSONArray children = jobject.getJSONArray("children");
					level++;
					for(Object obj : children){
						JSONObject childObject = (JSONObject)obj;
						if(childObject==null)
							continue;
						setRequestStruct(childObject,element,level);
					}
				}
			}
		}
		
		/**
		 * 设置输入参数的值绑定
		 * @param jarry 值绑定设置json
		 * @param soapElment
		 * @param level soapElement级别
		 * @param rootName 根节点名称
		 * @throws Exception 
		 */
		private static void setBindingValue(JSONArray jarray,SOAPElement soapElment,int level,String rootName,Map variables,Map<JSONObject,SOAPElement> map) throws Exception{
			String nodeName = soapElment.getNodeName();
			JSONObject bindingJobject = getBindingJObject(jarray, level, rootName, nodeName);
			Iterator<SOAPElement> it = soapElment.getChildElements();
			level++;
			if(bindingJobject==null){
				if(!it.hasNext()){
					soapElment.detachNode();
				}
				else{
					while(it.hasNext()){
						SOAPElement child = it.next();
						setBindingValue(jarray, child, level, rootName + "." + child.getNodeName(), variables,map);
					}
				}
			}
			else{
				map.put(bindingJobject,soapElment);
			}
		}
		
		/**
		 * 获取绑定设置对象
		 * @param jarray
		 * @param level
		 * @param rootName
		 * @param nodeName
		 * @return
		 */
		private static JSONObject getBindingJObject(JSONArray jarray,int level,String rootName,String nodeName){
			JSONObject reJobject = null;
			for (Object obj : jarray) {
				JSONObject jobject = (JSONObject)obj;
				if(jobject==null)
					continue;
				String paramName = jobject.getString("name");
				if(paramName.equals(nodeName)){
					String fullpath = "";
					if(jobject.containsKey("fullpath")){
						fullpath = jobject.getString("fullpath");
					}
					if(StringUtil.isNotEmpty(fullpath)){
						//全路径匹配
						if(fullpath.equals(rootName)){
							reJobject = jobject;
						}
					}
					else{
						List<String> pathAry = Arrays.asList(jobject.getString("bindingVal").split("\\."));
						//未获取到 绑定设置中的全路径时，通过 层级是否相等来判断
						if(level == pathAry.size()){
							reJobject = jobject;
						}
					}
				}
			}
			return reJobject;
		}
	}

	/**
	 * webservice出参处理器
	 * @author heyifan
	 *
	 */
	private static class ResponseBuilder {
		/**
		 * 
		 * @param variables
		 * @param document
		 * @param message
		 * @throws SOAPException
		 * @throws InvokeException
		 */
		public static void build(Map variables, JSONArray jarray, SOAPMessage message) throws Exception {
			// 校验是否失败
			checkFault(message);
			// 获取返回对象
			NodeList nodeList = message.getSOAPBody().getChildNodes();
			
			if (nodeList == null || nodeList.getLength() < 1) {// 无返回,什么都不用处理
				return;
			}

			// 准备好返回数据
			SOAPElement[] elements = new SOAPElement[nodeList.getLength()];
			for (int i = 0; i < elements.length; i++) {
				elements[i] = (SOAPElement) nodeList.item(i);
			}

			for(Object obj : jarray){
				JSONObject jobject = (JSONObject)obj;
				build(variables, elements, jobject);
			}
		}
		
		private static SOAPElement getElementByPath(SOAPElement[] elements, String fullpath){
			if(StringUtil.isEmpty(fullpath)){
				return elements[0];
			}
			String[] names = fullpath.split("\\.");
			int size = names.length;
			
			SOAPElement root = null;
			Node node = elements[0].getFirstChild();
			if(node!=null){
				root = (SOAPElement)node;
			}
			
			if(root==null){
				return null;
			}
			
			for(int i=1;i<size;i++){
				String name = names[i];
				root = getElement(root.getChildElements(), name);
			}
			return root;
		}
		
		private static SOAPElement getElement(Iterator<SOAPElement> it,String name){
			while(it.hasNext()){
				SOAPElement element = it.next();
				String tagName = element.getTagName();
				if(tagName.equals(name)){
					return element;
				}
			}
			return null;
		}
		
		private static void build(Map variables, SOAPElement[] roots, JSONObject jobject) throws Exception{
			if(jobject==null)return;
			// 设值
			String binding = jobject.getString("bindingVal");// 绑定参数
			String soapType = jobject.getString("soapType");// soap类型
			String beanType = jobject.getString("javaType");// java类型
			Integer bindingType = jobject.getInt("bindingType");//绑定类型
			String fullpath = "";
			if(jobject.containsKey("fullpath")){
				fullpath = jobject.getString("fullpath");
			}
			SOAPElement elements = getElementByPath(roots, fullpath);
			binding = StringUtil.jsonUnescape(binding);
			
			if(StringUtil.isEmpty(binding))return;
			//返回值对象
			Object obj = null;
			SoapType converter;
			//webservice返回值类型
			if (StringUtil.isNotEmpty(soapType)) {
				try{
					Class kclass;
					if(soapType.matches("List\\{\\w*\\}")){
						kclass = List.class;
					}
					else{
						kclass = Class.forName(soapType);
					}
					converter = SoapTypes.getTypeBySoap(soapType);
					obj = converter.convertToBean(kclass,elements);
				}
				catch(Exception ex){
					converter = SoapTypes.getTypeBySoap("string");
					obj = converter.convertToBean(elements);
				}
			}
			//流程变量中
			else if (StringUtil.isNotEmpty(beanType)&&bindingType.intValue()==2) {
				Class klass = Class.forName(beanType);
				converter = SoapTypes.getTypeByBean(klass);
				obj = converter.convertToBean(klass, elements);
			}
			//2个类型都没有时直接赋值
			else {
				obj = elements.getTextContent();
			}
			
			switch(bindingType){
				//webservice返回值回填到流程变量中
				case 2:						
					if(obj!=null){
						if(!(obj instanceof List)){
							if(binding.indexOf("[i]")>-1){
								List list = new ArrayList();
								list.add(obj);
								obj = list;
							}
						}
						PropertyUtils.setProperty(variables, binding, obj);
					}
					break;
				//执行脚本
				case 3:
					//将返回值放到map中用作执行脚本
					variables.put("returnObj", obj);
					engine.executeObject(binding, variables);
					//执行完脚本以后移除返回值
					variables.remove("returnObj");
					break;
			}
		}
	}
	
	/**
	 * 校验是否调用失败
	 * 
	 * @param message
	 * @throws SOAPException
	 * @throws InvokeException
	 */
	private static void checkFault(SOAPMessage message) throws SOAPException, InvokeException {
		SOAPEnvelope envelope = message.getSOAPPart().getEnvelope();
		SOAPBody body = envelope.getBody();
		SOAPFault fault = body.getFault();
		if (fault != null && fault.getFaultCode() != null) {// 出现异常
			throw new InvokeException(fault.getFaultCode(), fault.getFaultString());
		}
	}

	/**
	 * 节点设值
	 * 
	 * @param node
	 * @param name
	 * @return
	 */
	private static String getAttribute(Node node, String name) {
		Node tmp = node.getAttributes().getNamedItem(name);
		return tmp != null ? tmp.getTextContent() : null;
	}

	/**
	 * webservice 调用
	 * 
	 * @param invokeURL
	 * @param request
	 * @return
	 * @throws SOAPException
	 * @throws UnsupportedOperationException
	 * @throws MalformedURLException 
	 * @throws Exception
	 */
	private static SOAPMessage invoke(URL invokeURL, SOAPMessage request) throws Exception {
		// 创建连接
		SOAPConnectionFactory soapConnFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection connection = null;
		try {
			URL endPoint = new URL(null, invokeURL.toString(), new URLStreamHandler() {
				@Override
				protected URLConnection openConnection(URL u) throws IOException {
					URL clone_url = new URL(u.toString());
					HttpURLConnection clone_urlconnection = (HttpURLConnection) clone_url.openConnection();
					//从配置文件中获取超时时间设置
					if(_connTimeout==0){
						_connTimeout=AppConfigUtil.getInt("webservice.connTimeout",3000);
					}
					if(_readTimeout==0){
						_readTimeout = AppConfigUtil.getInt("webservice.readTimeout",3000);
					}
						
					clone_urlconnection.setConnectTimeout(_connTimeout);
					clone_urlconnection.setReadTimeout(_readTimeout);
					return(clone_urlconnection);
				}
			});
			connection = soapConnFactory.createConnection();
			// 响应消息
			SOAPMessage reply = connection.call(request, endPoint);
			// 设置字符编码
			// reply.setProperty(SOAPMessage.CHARACTER_SET_ENCODING, "UTF-8");
			return reply;
		}catch(Exception ex){
			throw ex;
		}
		finally {
			if (connection != null)
				connection.close();
		}
	}
	
	public static SOAPMessage execute(Map variables, JSONObject jObject) throws Exception {
		JSONArray inputs = jObject.getJSONArray("inputs");
		JSONArray inputParams =null;
		if(jObject.containsKey("inputParams"))
			inputParams = jObject.getJSONArray("inputParams");
		String url = jObject.getString("url");
		String namespace = jObject.getString("namespace");
		String method = jObject.getString("method");
		String soapaction = "";
		if(jObject.containsKey("soapaction")){
			soapaction = jObject.getString("soapaction");
		}
		if(StringUtil.isEmpty(url)||StringUtil.isEmpty(namespace)||StringUtil.isEmpty(method)){
			throw new Exception("没有获取到webservice的调用地址、名称空间或调用方法.");
		}
		SOAPMessage requestMessage = RequestBuilder.build(inputs,inputParams,namespace,method,variables,soapaction);
		// 调用
		SOAPMessage responseMessage = invoke(new URL(url), requestMessage);
		
		checkFault(responseMessage);
		
		return responseMessage;
	}

	/**
	 * 根据配置模板调用webservice	
	 * @param variables 变量值
	 * @param jArray webservice绑定设置
	 * @throws Exception
	 */
	public static void invoke(Map variables, JSONArray jArray) throws Exception {
		if (jArray.size() == 0) {
			logger.warn("没有找到webservice的调用配置.", jArray);
			return;
		}
		try{
			//遍历所有的webservice调用
			for(Object obj:jArray){
				JSONObject jObject = (JSONObject)obj;
				JSONArray inputs = jObject.getJSONArray("inputs");
				JSONArray outputs = jObject.getJSONArray("outputs");
				JSONArray inputParams =null;
				if(jObject.containsKey("inputParams"))
					inputParams = jObject.getJSONArray("inputParams");
				String url = jObject.getString("url");
				String namespace = jObject.getString("namespace");
				String method = jObject.getString("method");
				String soapaction = "";
				if(jObject.containsKey("soapaction")){
					soapaction = jObject.getString("soapaction");
				}
				if(StringUtil.isEmpty(url)||StringUtil.isEmpty(namespace)||StringUtil.isEmpty(method)){
					logger.warn("没有获取到webservice的调用地址、名称空间或调用方法.", jObject);			
					continue;
				}
				SOAPMessage requestMessage = RequestBuilder.build(inputs,inputParams,namespace,method,variables,soapaction);
				// 调用
				SOAPMessage responseMessage = invoke(new URL(url), requestMessage);
				// 构造response
				ResponseBuilder.build(variables, outputs, responseMessage);
			}
		}catch (Exception e) {
			logger.error("调用webservice出错.", e);
			throw e;
		}
	}

	private static String getXFDLString() throws IOException{
		File file =new File("D:\\dev\\bpmx3\\contract.xfdl");		 
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		byte[] temp = new byte[1024];
		byte[] buf = null;
		int length = 0;
		while ((length = fis.read(temp, 0, 1024)) != -1) {		    
			bout.write(temp, 0, length);
		}						 
		buf = bout.toByteArray();		 
	    sun.misc.BASE64Encoder sunEncoder=new sun.misc.BASE64Encoder();	    
		String str =sunEncoder.encodeBuffer(buf).toString();
		//str = str.replaceAll("\r\n","");		
		return str;
	}
}
