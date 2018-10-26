package com.hotent.core.wsdl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.ws.commons.schema.XmlSchemaType;


public class ServiceInfo {
	private String name;
	private String wsdlUrl;
	//ws的http请求地址
	private String httpAddress;
	private Map<String,OperationInfo> operations = new TreeMap<String, OperationInfo>() ;
	/*类型数组*/
	private List<XmlSchemaType> complexTypes = new ArrayList<XmlSchemaType>();
	private String targetNamespace;
	
	public String getTargetNamespace() {
		return targetNamespace;
	}
	public void setTargetNamespace(String targetNamespace) {
		this.targetNamespace = targetNamespace;
	}
	public void addComplexTypes(XmlSchemaType complexType) {
		complexTypes.add(complexType);
	}
	public List<XmlSchemaType> getComplexTypes() {
		return complexTypes;
	}

	public void setComplexTypes(List<XmlSchemaType> complexTypes) {
		this.complexTypes = complexTypes;
	}

	public String getWsdlUrl() {
		return wsdlUrl;
	}
	public void setWsdlUrl(String wsdlUrl) {
		this.wsdlUrl = wsdlUrl;
	}
	public String toString() {
		return getName();
	}

	public Map<String, OperationInfo> getOperations() {
		return operations;
	}
	public void setOperations(Map<String, OperationInfo> operations) {
		this.operations = operations;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getHttpAddress() {
		return httpAddress;
	}
	public void setHttpAddress(String httpAddress) {
		this.httpAddress = httpAddress;
	}
}

