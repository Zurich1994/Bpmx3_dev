package com.hotent.platform.webservice.api;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
/**
 * 取得与子系统相关的资源。如子系统URL对应角色的映谢，子系统功能ID对应角色的映谢。
 */
@SOAPBinding(style=Style.DOCUMENT,parameterStyle=ParameterStyle.WRAPPED)
@WebService(endpointInterface="com.hotent.platform.webservice.api.SystemResourcesService",
			targetNamespace="http://impl.webservice.platform.hotent.com/")
public interface SystemResourcesService {
	
	/**
	 * 根据系统名和资源名获取资源和角色的映射。
	 * @param alias	子系统别名
	 * @return 返回子系统和URL的映射。
	 */
	@WebMethod(operationName="getRoleByUrl")
	public String getRoleByUrl(@WebParam(name="sysAlias")String sysAlias,@WebParam(name="url")String url);
	/**
	 * 根据系统别名和资源名称获取资源和角色的映射。
	 * @param alias	子系统别名
	 * @return List<ResourcesUrlExt>	子系统功能ID对应角色的映的谢对像集合。
	 */
	@WebMethod(operationName="getRoleByFunc")
	public String getRoleByFunc(@WebParam(name="sysAlias")String sysAlias,@WebParam(name="func")String func );

}
