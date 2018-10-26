package com.hotent.platform.webservice.api;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;

/**
 * 流程的相关接口
 * 
 */
@SOAPBinding(style = Style.DOCUMENT, parameterStyle = ParameterStyle.WRAPPED)
@WebService(endpointInterface = "com.hotent.platform.webservice.api.FlowService", targetNamespace = "http://impl.webservice.platform.hotent.com/")
public interface FlowService {

	/**
	 * 获取当前用户可使用的流程数据
	 * 
	 * @param account
	 *            :用户id
	 * @param json
	 *            :查询参数
	 * @return String
	 * @exception
	 * @since 1.0.0
	 */
	@WebMethod(operationName = "getMyFlowListJson")
	public String getMyFlowListJson(@WebParam(name = "json") String json);

	/**
	 * 获取指定UserAccount用户的已办事宜
	 * 
	 * @param account
	 *            :用户id
	 * @param json
	 *            :查询参数
	 * @return String
	 * @exception
	 * @since 1.0.0
	 */
	@WebMethod(operationName = "getAlreadyMattersListJson")
	public String getAlreadyMattersListJson(@WebParam(name = "json")String json);

	/**
	 * 获取指定useraccount用户的请求
	 * 
	 * @param account
	 *            :用户id
	 * @param json
	 *            :查询参数
	 * @return String
	 * @exception
	 * @since 1.0.0
	 */
	@WebMethod(operationName = "getMyRequestListJson")
	public String getMyRequestListJson(@WebParam(name = "json") String json);

	/**
	 * 获取指定useraccount用户的办结事宜
	 * 
	 * @param account
	 *            :用户id
	 * @param json
	 *            :查询参数
	 * @return String
	 * @exception
	 * @since 1.0.0
	 */
	@WebMethod(operationName = "getMyCompletedListJson")
	public String getMyCompletedListJson(@WebParam(name = "json") String json);

	/**
	 * 获取指定useraccount用户的草稿列表
	 * 
	 * @param account
	 *            :用户id
	 * @param json
	 *            :查询参数
	 * @return String
	 * @exception
	 * @since 1.0.0
	 */
	@WebMethod(operationName = "getMyDraftListJson")
	public String getMyDraftListJson(@WebParam(name = "json") String json);

	/**
	 * 通过表单保存草稿
	 */
	@WebMethod(operationName = "saveDraftByForm")
	public String saveDraftByForm(@WebParam(name = "json") String json) throws Exception;
	
	/**
	 * 根据runId删除草稿
	 * @param runIds
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	@WebMethod(operationName = "delDraftByRunIds")
	public String delDraftByRunIds(@WebParam(name = "runIds") String runIds);
	

}
