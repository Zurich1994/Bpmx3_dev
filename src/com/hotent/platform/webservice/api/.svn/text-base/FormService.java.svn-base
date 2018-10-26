package com.hotent.platform.webservice.api;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;

@SOAPBinding(style = Style.DOCUMENT, parameterStyle = ParameterStyle.WRAPPED)
@WebService(endpointInterface = "com.hotent.platform.webservice.api.FormService", targetNamespace = "http://impl.webservice.platform.hotent.com/")
public interface FormService {
	/***
	 * <p>通过流程实例，或者流程定义获取表单 </p>
	 * @param formInfo : 表单的相关信息 <br>{instancId:"",actDefId:"",ctxPath："",account:""}
	 * @return
	 */
	@WebMethod(operationName="getFormHtml")
	String getFormHtml(@WebParam(name = "formInfo")String formInfo);
	
}
