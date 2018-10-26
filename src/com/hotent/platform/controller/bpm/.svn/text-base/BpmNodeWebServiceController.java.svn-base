package com.hotent.platform.controller.bpm;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.hotent.core.annotion.Action;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.core.wsdl.OperationInfo;
import com.hotent.core.wsdl.ParameterInfo;
import com.hotent.core.wsdl.ServiceInfo;
import com.hotent.core.wsdl.WSDLParser;
import com.hotent.deviceQuotaRel.model.deviceQuotaRelPac.DeviceQuotaRel;
import com.hotent.platform.model.bpm.BpmDefVar;
import com.hotent.platform.model.bpm.BpmNodeWebService;
import com.hotent.platform.service.bpm.BpmDefVarService;
import com.hotent.platform.service.bpm.BpmNodeWebServiceService;

/**
 * 对象功能: WebService节点的控制器类 开发公司:广州宏天软件有限公司 开发人员:wuzh 创建时间:2012-12-15
 */
@Controller
@RequestMapping("/platform/bpm/bpmNodeWebService/")
public class BpmNodeWebServiceController extends BaseController {
	@Resource
	private BpmNodeWebServiceService bpmNodeWebServiceService;
	@Resource
	private BpmDefVarService bpmDefVarService;

	/**
	 * 获取WSDL树
	 * 
	 * @param request
	 * 
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("getTreeData")
	@ResponseBody
	public String getTreeData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String wsdlUrl = RequestUtil.getString(request, "wsdlUrl");

		if (StringUtils.isEmpty(wsdlUrl))
			return null;
		WSDLParser parser = new WSDLParser(wsdlUrl);
		request.getSession().setAttribute("parser", parser);//设置到session中供下次使用.
		Map<String, ServiceInfo> services = parser.getServices();

		JSONArray treeList = new JSONArray();
		for (String key : services.keySet()) {
			ServiceInfo serviceInfo = services.get(key);
			JSONObject oneService = new JSONObject();
			oneService.accumulate("name", serviceInfo.getName()).accumulate("wsdlUrl", serviceInfo.getWsdlUrl())
					.accumulate("namespace", serviceInfo.getTargetNamespace()).accumulate("open", true);
			JSONArray children = new JSONArray();
			for (String operationKey : serviceInfo.getOperations().keySet()) {
				OperationInfo operation = serviceInfo.getOperations().get(operationKey);
				JSONObject oneOperation = new JSONObject();
				oneOperation.accumulate("name", operation.getOperationName())
						.accumulate("wsdlUrl", serviceInfo.getWsdlUrl())
						.accumulate("namespace", serviceInfo.getTargetNamespace());
				children.add(oneOperation);
			}
			oneService.accumulate("children", children);
			treeList.add(oneService);
		}
		return treeList.toString();
	}

	/**
	 * 获取函数信息
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("get")
	@ResponseBody
	public String get(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String nodeName = RequestUtil.getString(request, "nodeName");
		String serviceName = RequestUtil.getString(request, "serviceName");
		String wsdlUrl = RequestUtil.getString(request, "wsdlUrl");
		
		WSDLParser parser;
		try{
			parser = new WSDLParser(wsdlUrl);
		}
		catch(Exception ex){
			if (!wsdlUrl.matches(".*\\?(?i)wsdl$"))
				wsdlUrl = wsdlUrl+"?wsdl";
			parser = new WSDLParser(wsdlUrl);
		}
		request.getSession().setAttribute("parser", parser);//设置到session中供下次使用.
		Map<String, ServiceInfo> services = parser.getServices();
		ServiceInfo serviceInfo = services.get(serviceName);
		JSONObject jsonObject = new JSONObject();

		if (serviceInfo == null) {
			jsonObject.accumulate("success", false).accumulate("msg", "方法[" + nodeName + "]不存在.请重新初始化WSDL数据.");
		} else {
			OperationInfo operationInfo = serviceInfo.getOperations().get(nodeName);
			if (operationInfo == null) {
				jsonObject.accumulate("success", false).accumulate("msg", "方法[" + nodeName + "]不存在.请重新初始化WSDL数据.");
			} else {
				jsonObject.accumulate("success", true).accumulate("msg", "获取成功.");
				jsonObject.accumulate("wsdl", serviceInfo.getWsdlUrl());
				jsonObject.accumulate("namespace", serviceInfo.getTargetNamespace());
				jsonObject.accumulate("invokeUrl",serviceInfo.getHttpAddress());
				
				jsonObject.accumulate("method", operationInfo.getOperationName());
				jsonObject.accumulate("soapaction", operationInfo.getInputAction());

				// 处理入参树
				JSONArray inputTree = new JSONArray();
				
				List<?> inputParams = operationInfo.getInputParams();
				
				if(BeanUtils.isNotEmpty(inputParams)){
					for(Object inputParamObj : inputParams){
						ParameterInfo inputParameterInfo = (ParameterInfo)inputParamObj;
						//非复杂结构
						if(ParameterInfo.COMPLEX_NO.equals(inputParameterInfo.getIsComplext())){
							JSONObject paramJson = new JSONObject();
							setParamInfoIntoJson(inputParameterInfo.getName(), inputParameterInfo, paramJson);
							inputTree.add(paramJson);
						}
						//复杂结构
						else{
							Map<String, ParameterInfo> complextParams = inputParameterInfo.getComplextParams();
							if(complextParams.size()>0){
								ParameterInfo inputRoot = complextParams.values().iterator().next();
								for (String key : inputRoot.getComplextParams().keySet()) {
									ParameterInfo parameterInfo = inputRoot.getComplextParams().get(key);
									JSONObject paramJson = new JSONObject();
									setParamInfoIntoJson(key, parameterInfo, paramJson);
									inputTree.add(paramJson);
								}
							}
						}
					}
				}
				
				JSONObject inputTreeRoot = new JSONObject();
				inputTreeRoot.accumulate("name", "输入参数").accumulate("children", inputTree);
				jsonObject.accumulate("inputParams", inputTreeRoot);

				// 处理出参树
				JSONArray outputTree = new JSONArray();
				List<?> outputParams = operationInfo.getOutputParams();
				if(BeanUtils.isNotEmpty(outputParams)){
					Map<String, ParameterInfo> complextParams = ((ParameterInfo)outputParams.get(0)).getComplextParams();
					if(complextParams.size()>0){
						ParameterInfo outputRoot = complextParams.values().iterator().next();
						for (String key : outputRoot.getComplextParams().keySet()) {
							ParameterInfo parameterInfo = outputRoot.getComplextParams().get(key);
							JSONObject paramJson = new JSONObject();
							setParamInfoIntoJson(key, parameterInfo, paramJson);
							outputTree.add(paramJson);
						}
					}
				}
				JSONObject outputTreeRoot = new JSONObject();
				outputTreeRoot.accumulate("name", "输出参数").accumulate("children", outputTree);
				jsonObject.accumulate("outputParams", outputTreeRoot);
			}
		}
		return jsonObject.toString();
	}

	/**
	 * 递归设置参数json值
	 * 
	 * @param parameterInfo
	 * @param paramJson
	 */
	private void setParamInfoIntoJson(String name, ParameterInfo parameterInfo, JSONObject paramJson) {
		if (parameterInfo.getIsComplext() == ParameterInfo.COMPLEX_YES) {// 复杂类型
			String type = "bean";
			if(parameterInfo.isList()){
				type = "List{bean}";
			}
			paramJson.accumulate("name", name)
					 .accumulate("type", type)
					 .accumulate("nocheck", true);
			JSONArray children = new JSONArray();
			Map<String, ParameterInfo> complextParams = parameterInfo.getComplextParams();
			for (String key : complextParams.keySet()) {
				JSONObject childJson = new JSONObject();
				setParamInfoIntoJson(key, complextParams.get(key), childJson);
				children.add(childJson);
			}
			paramJson.accumulate("children", children);
		} else {
			paramJson.accumulate("name", parameterInfo.getName())
					 .accumulate("type", parameterInfo.getType())
					 .accumulate("nocheck", false);
		}
	}

	/**
	 * 编辑流程WebService节点
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description = "编辑流程WebService节点")
	public ModelAndView edit(HttpServletRequest request) throws Exception {
		String actDefId = RequestUtil.getString(request, "actDefId");
		System.out.println("@actDefId:"+actDefId);
		String nodeId = RequestUtil.getString(request, "nodeId");
		System.out.println("@nodeId:"+nodeId);
		Long defId = RequestUtil.getLong(request, "defId");
		System.out.println("@defId:"+defId);
		
		
		/////////////////////////////////////
		System.out.println("come in!");
		BpmNodeWebService bpmNodeWebService = bpmNodeWebServiceService.getByNodeIdActDefId(nodeId, actDefId);
		/////////////////////////////////////
		
		Long setId = 0L;
		String document = "";
		if(bpmNodeWebService!=null){
			System.out.println("bpmNodeWebService不为空");
			setId = bpmNodeWebService.getId();
			System.out.println("@setId"+setId);
			document = bpmNodeWebService.getDocument();
			System.out.println("@document:"+document);
		}
		System.out.println(document);
		/*
		JSONArray jsonArray=JSONArray.fromObject(document);
		if(jsonArray.size()==0)
			System.out.println("The jsonArray is Empty!");
		else
		{
			for(Object obj:jsonArray){
				JSONObject jObject = (JSONObject)obj;
				/////////////////////////////////////////
				JSONArray inputs =null;
				if(jObject.containsKey("inputs"))
					 inputs = jObject.getJSONArray("inputs");
				System.out.println("inputs:"+inputs.toString());
				
				if(inputs.size()!=0)
				{
					System.out.println("into inputs:");
					for(Object small_obj:inputs){
						JSONObject small_Object = (JSONObject)small_obj;
						String name = small_Object.getString("name");
						System.out.println("name:"+name);
						String bindingVal = small_Object.getString("bindingVal");
						System.out.println("bindingVal:"+bindingVal);
					}
				}
				////////////////////////////////////
				JSONArray outputs =null;
				if(jObject.containsKey("outputs"))
					outputs = jObject.getJSONArray("outputs");
				System.out.println("outputs:"+outputs.toString());
				
				if(outputs.size()!=0)
				{
					System.out.println("into outputs:");
					for(Object small_obj:outputs){
						JSONObject small_Object = (JSONObject)small_obj;
						String name = small_Object.getString("name");
						System.out.println("name:"+name);
						String bindingVal = small_Object.getString("bindingVal");
						System.out.println("bindingVal:"+bindingVal);
					}
				}
				//////////////////////////////////
				JSONArray inputParams =null;
				if(jObject.containsKey("inputParams"))
					inputParams = jObject.getJSONArray("inputParams");
				System.out.println("inputParams:"+inputParams.toString());
				if(inputParams.size()!=0)
				{
					System.out.println("into inputParams:");
					for(Object small_obj:inputParams){
						JSONObject small_Object = (JSONObject)small_obj;
						String name = small_Object.getString("name");
						System.out.println("name:"+name);
					}
				}
				///////////////////////////////////
				JSONArray outputParams =null;
				if(jObject.containsKey("outputParams"))
					outputParams = jObject.getJSONArray("outputParams");
				System.out.println("outputParams:"+outputParams.toString());
				if(outputParams.size()!=0)
				{
					System.out.println("into outputParams:");
					for(Object small_obj:outputParams){
						JSONObject small_Object = (JSONObject)small_obj;
						String name = small_Object.getString("name");
						System.out.println("name:"+name);
					}
				}
				///////////////////////////////////
				String url = jObject.getString("url");
				System.out.println("url:"+url);
				String namespace = jObject.getString("namespace");
				System.out.println("namespace:"+namespace);
				String method = jObject.getString("method");
				System.out.println("method:"+method);
				String soapaction = "";
				if(jObject.containsKey("soapaction")){
					soapaction = jObject.getString("soapaction");
				}
				System.out.println("soapaction:"+soapaction);
				String serviceName = "";
				if(jObject.containsKey("serviceName")){
					serviceName = jObject.getString("serviceName");
				}
				System.out.println("serviceName:"+serviceName);
			}
		}
		*/
		return getAutoView().addObject("setId", setId)
							.addObject("document",document)
							.addObject("actDefId", actDefId)
							.addObject("nodeId", nodeId)
							.addObject("defId", defId);

	}

	/**
	 * 获取变量名
	 * @param bpmDefVarlist
	 * @param varId
	 * @return
	 */
	private String getVarName(List<BpmDefVar> bpmDefVarlist, Long varId) {
		for (BpmDefVar bpmDefVar : bpmDefVarlist) {
			if (bpmDefVar.getVarId().longValue() == varId.longValue()) {
				return bpmDefVar.getVarName();
			}
		}
		return null;
	}

	/**
	 * 添加或更新流程WebService节点。
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@ResponseBody
	public String save(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String json = RequestUtil.getString(request, "json");
		String nodeId = RequestUtil.getString(request, "nodeId");
		Long setId = RequestUtil.getLong(request, "setId");
		String actDefId = RequestUtil.getString(request, "actDefId");
		JSONObject jsonObject = new JSONObject();
		try {
			if(setId>0){
				BpmNodeWebService bpmNodeWebService = bpmNodeWebServiceService.getById(setId);
				if(bpmNodeWebService!=null){
					bpmNodeWebService.setDocument(json);
					bpmNodeWebServiceService.update(bpmNodeWebService);
				}
				else{
					bpmNodeWebServiceService.save(nodeId, actDefId, json);
				}
			}
			else
				bpmNodeWebServiceService.save(nodeId, actDefId, json);
			jsonObject.accumulate("success", true).accumulate("msg", "保存成功!");
		} catch (Exception e) {
			jsonObject.accumulate("success", false).accumulate("msg", "保存失败!"+e.getMessage());
		}
		return jsonObject.toString();
	}
	/**
	 * @author wb
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * 保存dbFunc已绑定参数
	 */
	@RequestMapping("savewb")
	@ResponseBody
	public String savewb(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String json = RequestUtil.getString(request, "json");
		System.out.println("json:11111111111111111111"+json);
		String nodeId = RequestUtil.getString(request, "nodeId");
		Long setId = RequestUtil.getLong(request, "setId");
		String actDefId = RequestUtil.getString(request, "actDefId");
		JSONObject jsonObject = new JSONObject();
		try {
			System.out.println("set id.."+setId+"nodeid.."+nodeId+"actid"+actDefId);
			if(setId>0){
				//BpmNodeWebService bpmNodeWebService = bpmNodeWebServiceService.getById(setId);
				BpmNodeWebService bpmNodeWebService = bpmNodeWebServiceService.getByNodeIdActDefId(nodeId, actDefId);
				System.out.println("node"+bpmNodeWebService);
				if(bpmNodeWebService!=null){
					bpmNodeWebService.setDocument(json);
					bpmNodeWebServiceService.update(bpmNodeWebService);
				}
				else{
					bpmNodeWebServiceService.save(nodeId, actDefId, json);
				}
			}
			else
				bpmNodeWebServiceService.save(nodeId, actDefId, json);
			jsonObject.accumulate("success", true).accumulate("msg", "保存成功!");
		} catch (Exception e) {
			jsonObject.accumulate("success", false).accumulate("msg", "保存失败!"+e.getMessage());
		}
		return jsonObject.toString();
	}

	/**
	 * 获取dbfunc已绑定的参数
	 */
	@RequestMapping("getExist")
	@ResponseBody
	public String getExist(HttpServletRequest request) throws Exception {
//		String actDefId = RequestUtil.getString(request, "tableName");
//		String nodeId = RequestUtil.getString(request, "methodId");
//		String nodeId ="wb";
//		Long defId = RequestUtil.getLong(request, "defId");
//		Long subdefId = RequestUtil.getLong(request, "subdefId");
//		String type = RequestUtil.getString(request, "type");
//		
		String actDefId = RequestUtil.getString(request, "actDefId");
		String nodeId =RequestUtil.getString(request, "nodeId");
		System.out.println("tablename"+actDefId+"  methodid"+nodeId);
		BpmNodeWebService bpmNodeWebService = bpmNodeWebServiceService.getByNodeIdActDefId(nodeId, actDefId);
		System.out.println(bpmNodeWebService);
		Long setId = 0L;
		String document = "";
		if(bpmNodeWebService!=null){
			setId = bpmNodeWebService.getId();
			document = bpmNodeWebService.getDocument();
		}
		System.out.println("document.."+document);
		return document;
	}
	
	
}
