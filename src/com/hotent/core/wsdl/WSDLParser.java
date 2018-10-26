package com.hotent.core.wsdl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import javax.wsdl.Binding;
import javax.wsdl.BindingOperation;
import javax.wsdl.Definition;
import javax.wsdl.Input;
import javax.wsdl.Message;
import javax.wsdl.Operation;
import javax.wsdl.Output;
import javax.wsdl.Part;
import javax.wsdl.Port;
import javax.wsdl.Types;
import javax.wsdl.WSDLException;
import javax.wsdl.extensions.ExtensibilityElement;
import javax.wsdl.extensions.http.HTTPAddress;
import javax.wsdl.extensions.soap.SOAPAddress;
import javax.wsdl.extensions.soap.SOAPOperation;
import javax.wsdl.factory.WSDLFactory;
import javax.wsdl.xml.WSDLReader;
import javax.xml.namespace.QName;
import org.apache.ws.commons.schema.XmlSchema;
import org.apache.ws.commons.schema.XmlSchemaCollection;
import org.apache.ws.commons.schema.XmlSchemaComplexContentExtension;
import org.apache.ws.commons.schema.XmlSchemaComplexType;
import org.apache.ws.commons.schema.XmlSchemaContent;
import org.apache.ws.commons.schema.XmlSchemaContentModel;
import org.apache.ws.commons.schema.XmlSchemaElement;
import org.apache.ws.commons.schema.XmlSchemaImport;
import org.apache.ws.commons.schema.XmlSchemaObject;
import org.apache.ws.commons.schema.XmlSchemaParticle;
import org.apache.ws.commons.schema.XmlSchemaSequence;
import org.apache.ws.commons.schema.XmlSchemaSequenceMember;
import org.apache.ws.commons.schema.XmlSchemaType;
import org.apache.ws.commons.schema.utils.XmlSchemaRef;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;

import com.hotent.core.util.StringUtil;
import com.ibm.wsdl.ImportImpl;
import com.ibm.wsdl.ServiceImpl;
import com.ibm.wsdl.extensions.schema.SchemaImpl;
import com.hotent.core.util.BeanUtils;

/**
 * @author haijun.zhang
 * 
 */
public class WSDLParser {
	
	protected static Logger logger = LoggerFactory.getLogger(WSDLParser.class);
	/**
	 * 测试辅助
	 * 
	 * @param operationInfo
	 * @param output
	 */
	private static void getParam(ParameterInfo parameterInfo) {

		if (parameterInfo.getIsComplext() == ParameterInfo.COMPLEX_YES) {
			if (!"parameters".equals(parameterInfo.getName())) {
				logger.info("--" + parameterInfo.getType() + "复杂类型开始:--");
			}
			Map<String, ParameterInfo> tempMap = parameterInfo.getComplextParams();
			Set<?> keys = tempMap.keySet();
			for (Iterator<?> otheriterator = keys.iterator(); otheriterator.hasNext();) {
				Object key = otheriterator.next();
				ParameterInfo parameter = tempMap.get(key);// 上面key对应的value
				getParam(parameter);
			}
			if (!"parameters".equals(parameterInfo.getName())) {
				logger.info("--" + parameterInfo.getType() + "复杂类型end:--");
			}
		} else {
			logger.info("  --inputparamName:" + parameterInfo.getName() + "  --inputparamType:"
					+ parameterInfo.getType());
		}
	}
	
	/**
	 * 测试main函数
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String wsdlURI = "http://www.webxml.com.cn/WebServices/WeatherWebService.asmx?wsdl";
//		String wsdlURI = "http://127.0.0.1:8080/moi/service/ProcessService?wsdl";
		try {
			WSDLParser parser = new WSDLParser(wsdlURI);
			
			Collection<ServiceInfo> serviceInfos = parser.getServices().values(); 
			Iterator<?> it = serviceInfos.iterator();
			while(it.hasNext()){
				ServiceInfo serviceInfo = (ServiceInfo)it.next();
				Map<String, OperationInfo> operationList = serviceInfo.getOperations();
				Set<?> keys = operationList.keySet();
				for (Iterator<?> iterator = keys.iterator(); iterator.hasNext();) {
					Object key = iterator.next();
					System.out.println(key);
					OperationInfo info = operationList.get(key);
					@SuppressWarnings("unchecked")
					List<ParameterInfo> inputParams = (List<ParameterInfo>) info.getInputParams();
					Iterator<ParameterInfo> it1 = inputParams.iterator();
					while (it1.hasNext()) {
						ParameterInfo tempinfo = it1.next();
						WSDLParser.getParam(tempinfo);
					}
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}

	private Map<String, XmlSchemaType> complexTypes = new HashMap<String, XmlSchemaType>();

	private String currentNameSpace = "";

	private Map<String, ServiceInfo> services = new HashMap<String, ServiceInfo>();

	public WSDLParser(String wsdlURI) throws WSDLException {
		this.parseWSDL(wsdlURI);
	}
	
	/**
	 * 函数已加入判断
	 * 
	 * @param serviceInfo
	 * @param operationInfo
	 * @return
	 */
	private boolean containOperation(ServiceInfo serviceInfo, OperationInfo operationInfo) {
		Map<String, OperationInfo> operations = serviceInfo.getOperations();
		if (operations.get(operationInfo.getOperationName()) != null) {
			return true;
		}
		return false;
	}

	/**
	 * 函数已加入判断
	 * 
	 * @param serviceInfo
	 * @param operationInfo
	 * @return
	 */
	private boolean containService(String serviceName) {
		if (services.get(serviceName) != null) {
			return true;
		}
		return false;
	}

	/**
	 * 函数已加入判断
	 * 
	 * @param serviceInfo
	 * @param operationInfo
	 * @return
	 */
	private boolean containType(XmlSchemaType type) {
		String name = type.getName();
		if (name != null && complexTypes.get(name) != null) {
			return true;
		}
		return false;

	}

	public Map<String, XmlSchemaType> getComplexTypes() {
		return complexTypes;
	}

	public String getCurrentNameSpace() {
		return currentNameSpace;
	}

	public Map<String, ServiceInfo> getServices() {
		return services;
	}

	/**
	 * 根据wsdlURL获取并处理服务函数
	 * 
	 * @param wsdlURI
	 * @throws WSDLException
	 */
	private void parseWSDL(String wsdlURI) throws WSDLException {
		WSDLFactory wsdlFactory = WSDLFactory.newInstance();
		WSDLReader reader = wsdlFactory.newWSDLReader();
		Definition defintion = reader.readWSDL(wsdlURI);
		processImport(defintion);// 处理import
		processTypes(defintion); // 处理类型
		Map<?, ?> servicesMap = defintion.getAllServices();
		Set<?> serviceKeys = servicesMap.keySet();
		for (Iterator<?> iterator = serviceKeys.iterator(); iterator.hasNext();) {
			Object key = iterator.next();
			ServiceImpl service = (ServiceImpl) servicesMap.get(key);
			processService(service, wsdlURI);// 处理函数服务
		}
	}

	/**
	 * 参数为复杂类型处理处理函数
	 * 
	 * @param params
	 * @param typeName
	 * @return
	 */
	private boolean processComplex(ParameterInfo parameterInfo, String typeName, String partName, String attrName,Boolean isList) {
		if (typeName == null)
			return false; // typeName作为key值获取复杂类型
		XmlSchemaType xmlSchemaType = complexTypes.get(typeName);
		if (xmlSchemaType != null) {
			ParameterInfo complexType = new ParameterInfo();
			complexType.setType(typeName); // 变量名称
			complexType.setIsComplext(ParameterInfo.COMPLEX_YES);
			complexType.setIsList(isList);
			if (typeName.contains("Response") || (partName.equals(typeName))) {
				complexType.setIsShow(ParameterInfo.SHOW_NO);
			}
			// 处理复杂类型
			XmlSchemaParticle xmlSchemaParticle = ((XmlSchemaComplexType) xmlSchemaType).getParticle();
			if (xmlSchemaParticle == null)
				return false;
			List<XmlSchemaSequenceMember> xmlSchemaObjectCollection = ((XmlSchemaSequence) xmlSchemaParticle)
					.getItems(); // 获取当前复杂类型参数
			int count = xmlSchemaObjectCollection.size();
			for (int j = 0; j < count; j++) {
				XmlSchemaSequenceMember xmlSchemaObject = xmlSchemaObjectCollection.get(j);
				if (xmlSchemaObject instanceof XmlSchemaElement) {
					XmlSchemaElement xmlSchemaElement = (XmlSchemaElement) xmlSchemaObject;
					
					long max = xmlSchemaElement.getMaxOccurs();
					String elementName = xmlSchemaElement.getName();
				
					XmlSchemaType xmlSType = xmlSchemaElement.getSchemaType();
					if (xmlSType instanceof XmlSchemaComplexType) {
						String xmlSTypeName = xmlSType.getName();
						
						
						complexType.setParentComplext(parameterInfo.getParentComplext());
						if (StringUtil.isNotEmpty(xmlSTypeName)) {
							if (parameterInfo.getParentComplext().get(xmlSType.getName()) != null)
								break; // 防死循环
							processComplex(complexType, xmlSType.getName(), partName, elementName, max > 1);
						} else { // 如果复杂类型中没有的直接扩展的话需再次处理
							complexType.getParentComplext().put(elementName, elementName);
							xmlSType.setName(elementName);
							processComplexType(xmlSType);
							processComplex(complexType, elementName, partName, elementName,false);
						}
					} else if (xmlSchemaElement.isRef()) {
						XmlSchemaRef<?> xmlSchemaRef = xmlSchemaElement.getRef();
						String type = xmlSchemaRef.getTargetQName().getLocalPart();
						processComplex(complexType, type, partName, elementName,false);
					} else { // 如果复杂类型中的不是复杂类型，直接加入
						ParameterInfo simpleType = new ParameterInfo();
						simpleType.setName(elementName); // 变量名称
						String t = xmlSType.getName();
						if(max>1){
							t = "List{" + t +"}";
						}
						simpleType.setType(t);
						complexType.getComplextParams().put(elementName, simpleType);
					}
				}
			}
			// 遍历完后加入到ParameterInfo中
			if(StringUtil.isEmpty(attrName)){
				parameterInfo.getComplextParams().put(typeName, complexType);
			}
			else{
				parameterInfo.getComplextParams().put(attrName, complexType);
			}
			return true;
		}
		return false;
	}

	/**
	 * 复杂类型记录处理
	 * 
	 * @param type
	 */
	private void processComplexType(XmlSchemaType type) {
		String typeName = type.getName();
		if (!(type instanceof XmlSchemaComplexType))
			return;
		if (containType(type))
			return;
		complexTypes.put(typeName, type);
		XmlSchemaComplexType xmlSchemaComplexType = (XmlSchemaComplexType) type;
		XmlSchemaContentModel xmlSchemaContentModel = xmlSchemaComplexType.getContentModel();
		XmlSchemaParticle xmlSchemaParticle = xmlSchemaComplexType.getParticle(); // 取定义type
		if (xmlSchemaParticle == null && xmlSchemaContentModel != null) { // 若定义的参数包含扩展的属性
			XmlSchemaContent xmlSchemaContent = xmlSchemaContentModel.getContent();
			if (xmlSchemaContent instanceof XmlSchemaComplexContentExtension) {
				XmlSchemaComplexContentExtension xmlSchemaComplexContentExtension = (XmlSchemaComplexContentExtension) xmlSchemaContent;
				XmlSchemaParticle xmlSchemaParticleExtion = xmlSchemaComplexContentExtension.getParticle();
				xmlSchemaComplexType.setParticle(xmlSchemaParticleExtion);
				type = xmlSchemaComplexType;
			}
		}
		if (!(xmlSchemaParticle instanceof XmlSchemaSequence))
			return;
		XmlSchemaSequence xmlSchemaSequence = (XmlSchemaSequence) xmlSchemaParticle;
		List<XmlSchemaSequenceMember> xmlSchemaObjectCollection = xmlSchemaSequence.getItems(); // 取type中Sequence
		int count = xmlSchemaObjectCollection.size();
		for (int i = 0; i < count; i++) {
			XmlSchemaSequenceMember xmlSchemaObject = xmlSchemaObjectCollection.get(i);
			if (!(xmlSchemaObject instanceof XmlSchemaElement))
				continue;

			XmlSchemaElement xmlSchemaElement = (XmlSchemaElement) xmlSchemaObject;
			XmlSchemaType xmlSchemaType = xmlSchemaElement.getSchemaType();
			if (xmlSchemaType != null && xmlSchemaType instanceof XmlSchemaComplexType) {
				processComplexType(xmlSchemaType); // 如是复杂类型继续递归调用
			}
		}
	}

	/**
	 * 处理import类型标签
	 * 
	 * @param defintion
	 * @throws WSDLException
	 */
	private void processImport(Definition defintion) throws WSDLException {
		currentNameSpace = defintion.getTargetNamespace();
		Map<?, ?> impMap = defintion.getImports();
		Iterator<?> keys = impMap.keySet().iterator();
		while (keys.hasNext()) {
			Object key = keys.next();// key
			@SuppressWarnings("unchecked")
			Vector<ImportImpl> importImpls = (Vector<ImportImpl>) impMap.get(key);// 上面key对应的value
			ImportImpl imp = importImpls.elementAt(0);
			currentNameSpace = imp.getNamespaceURI();
			parseWSDL(imp.getLocationURI());
		}
	}

	/**
	 * 输入参数处理
	 * 
	 * @param operationInfo
	 * @param input
	 */
	private void processInputParam(OperationInfo operationInfo, Input input) {
		@SuppressWarnings("unchecked")
		List<ParameterInfo> inputParams = (List<ParameterInfo>) operationInfo.getInputParams();
		Message message = input.getMessage();
		Map<?, ?> partMap = message.getParts();
		processParam(operationInfo, partMap, inputParams);
	}

	/**
	 * 函数处理
	 * 
	 * @param serviceInfo
	 * @param operation
	 */
	private void processOperation(ServiceInfo serviceInfo, BindingOperation bindingOperation) {
		Operation operation = bindingOperation.getOperation();
		String operationName = operation.getName();
		OperationInfo operationInfo = new OperationInfo();
		operationInfo.setOperationName(operationName);

		if (!containOperation(serviceInfo, operationInfo)) {
			serviceInfo.getOperations().put(operationName, operationInfo); // addOperation(operationInfo);
		}
		List extensions = bindingOperation.getExtensibilityElements();
		if(extensions!=null){
			for (int i = 0; i < extensions.size(); i++) {
				ExtensibilityElement extElement = (ExtensibilityElement) extensions.get(i);
				if(extElement instanceof SOAPOperation){
					SOAPOperation soapOp = (SOAPOperation) extElement;
					String soapUri = soapOp.getSoapActionURI();
					operationInfo.setInputAction(soapUri);
				}
			}
		}
		Input input = operation.getInput();
		processInputParam(operationInfo, input);
		Output output = operation.getOutput();
		processOutputParam(operationInfo, output);
	}

	/**
	 * 输出参数处理函数
	 * 
	 * @param operationInfo
	 * @param output
	 */
	private void processOutputParam(OperationInfo operationInfo, Output output) {
		if(output==null)return;
		@SuppressWarnings("unchecked")
		List<ParameterInfo> outputParams = (List<ParameterInfo>) operationInfo.getOutputParams();
		Message message = output.getMessage();
		Map<?, ?> partMap = message.getParts();
		processParam(operationInfo, partMap, outputParams);
	}

	/**
	 * 参数通用处理函数
	 * 
	 * @param operationInfo
	 * @param partMap
	 * @param params
	 */
	private void processParam(OperationInfo operationInfo, Map<?, ?> partMap, List<ParameterInfo> params) {
		Collection<?> parts = partMap.values();
		for (Iterator<?> iterator = parts.iterator(); iterator.hasNext();) {
			Part part = (Part) iterator.next();
			String partName = part.getName();
			ParameterInfo parameterInfo = new ParameterInfo();
			String typeName = null;
			QName qName = part.getTypeName();
			if (qName != null) {
				typeName = qName.getLocalPart();

			} else {
				typeName = part.getElementName().getLocalPart();

			}
			parameterInfo.setName(partName);
			parameterInfo.setType(typeName);
			if ("parameters".equals(partName)) {
				parameterInfo.setIsShow(ParameterInfo.SHOW_NO);
			}
			String operationName = operationInfo.getOperationName();
			if (processComplex(parameterInfo, typeName, operationName, null, false)) {
				parameterInfo.setIsComplext(ParameterInfo.COMPLEX_YES);
				// 复杂类型的话应该存到parameterInfo的复杂类型中
			}
			params.add(parameterInfo);
		}
	}

	/**
	 * 服务处理函数
	 * 
	 * @param service
	 */
	private void processService(ServiceImpl service, String wsdlUrl) {
		String serviceName = service.getQName().getLocalPart();
		if (containService(serviceName))
			return;
		ServiceInfo serviceInfo = new ServiceInfo();
		serviceInfo.setWsdlUrl(wsdlUrl);
		String invokeUrl = wsdlUrl;
		if(wsdlUrl.matches(".*\\?(?i)wsdl$")) 
			invokeUrl = wsdlUrl.substring(0,wsdlUrl.lastIndexOf("?"));
		serviceInfo.setHttpAddress(invokeUrl);
		serviceInfo.setName(serviceName);
		serviceInfo.setTargetNamespace(currentNameSpace);
		services.put(serviceName, serviceInfo);
		Collection<?> ports = service.getPorts().values();
		for (Iterator<?> iterator = ports.iterator(); iterator.hasNext();) { // 遍历文档中的服务
			Port port = (Port) iterator.next();
			List<?> list = port.getExtensibilityElements();
			for(Object obj:list){
				if(obj instanceof HTTPAddress){
					HTTPAddress address = (HTTPAddress)obj;
					String location = address.getLocationURI();
					serviceInfo.setHttpAddress(location);
				}
				if(obj instanceof SOAPAddress){
					SOAPAddress address = (SOAPAddress)obj;
					String location = address.getLocationURI();
					serviceInfo.setHttpAddress(location);
				}
			}
			Binding binding = port.getBinding();
			List<?> operations = binding.getBindingOperations();
			for (Iterator<?> iterator2 = operations.iterator(); iterator2.hasNext();) { // 遍历服务中的函数
				BindingOperation bindingOperation = (BindingOperation) iterator2.next();
				processOperation(serviceInfo, bindingOperation);
			}
		}
	}

	/**
	 * 类型处理函数
	 * 
	 * @param defintion
	 */
	private void processTypes(Definition defintion) {
		XmlSchemaCollection xmlSchemaCollection = new XmlSchemaCollection();
		Types types = defintion.getTypes();
		if (types == null)
			return;
		List<?> list = types.getExtensibilityElements();
		for (Iterator<?> iterator = list.iterator(); iterator.hasNext();) {
			SchemaImpl schemaImpl = (SchemaImpl) iterator.next();
			Element element = schemaImpl.getElement();
			XmlSchema xmlSchema = xmlSchemaCollection.read(element);
			processXmlSchema(xmlSchema);
		}
	}
	
	/**
	 * 递归处理 XmlSchema
	 * <pre>
	 * 	因为XmlSchma中可能存在import
	 * </pre>
	 * @param xmlSchema
	 */
	private void processXmlSchema(XmlSchema xmlSchema){
		if(BeanUtils.isEmpty(xmlSchema))return;
		Map<QName, XmlSchemaType> smlSchemaObjectTable = xmlSchema.getSchemaTypes();
		List<XmlSchemaObject> xmlSchemaObjs = xmlSchema.getItems();
		Set<QName> smlSchemaKeys = smlSchemaObjectTable.keySet();
		for (Iterator<QName> otheriterator = smlSchemaKeys.iterator(); otheriterator.hasNext();) {
			Object key = otheriterator.next();
			XmlSchemaType typevalue = smlSchemaObjectTable.get(key);// 上面key对应的value
			processComplexType(typevalue);
		}
		for(XmlSchemaObject xmlSchemaObj : xmlSchemaObjs){
			if(xmlSchemaObj instanceof XmlSchemaImport){
				XmlSchemaImport xmlSchemaImport = (XmlSchemaImport)xmlSchemaObj;
				XmlSchema importXmlSchema = xmlSchemaImport.getSchema();
				processXmlSchema(importXmlSchema);
			}
			if(xmlSchemaObj instanceof XmlSchemaElement){
				XmlSchemaElement xmlSchemaElement = (XmlSchemaElement)xmlSchemaObj;
				String schemaName = xmlSchemaElement.getName();
				XmlSchemaType typevalue = xmlSchemaElement.getSchemaType();
				typevalue.setName(schemaName);
				processComplexType(typevalue);
			}
		}
	}
}
