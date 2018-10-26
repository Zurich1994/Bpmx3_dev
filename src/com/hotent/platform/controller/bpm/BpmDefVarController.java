package com.hotent.platform.controller.bpm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.annotion.ActionExecOrder;
import com.hotent.core.bpm.model.NodeCache;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ExceptionUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.bpm.BpmDefVar;
import com.hotent.platform.model.bpm.BpmDefinition;
import com.hotent.platform.model.form.BpmFormField;
import com.hotent.platform.service.bpm.BpmDefVarService;
import com.hotent.platform.service.bpm.BpmDefinitionService;
import com.hotent.platform.service.bpm.BpmNodeWebServiceService;
import com.hotent.platform.service.bpm.BpmService;
import com.hotent.platform.service.bpm.thread.MessageUtil;
import com.hotent.platform.service.form.BpmFormFieldService;
import com.hotent.platform.service.system.SubSystemService;
import com.hotent.platform.model.system.SysAuditModelType;

import edu.emory.mathcs.backport.java.util.Collections;

/**
 * 对象功能:流程变量定义 控制器类 开发公司:广州宏天软件有限公司 开发人员:phl 创建时间:2011-12-01 16:50:07
 */
@Controller
@RequestMapping("/platform/bpm/bpmDefVar/")
@Action(ownermodel=SysAuditModelType.PROCESS_MANAGEMENT)
public class BpmDefVarController extends BaseController {
	@Resource
	private BpmDefVarService bpmDefVarService;
	@Resource
	private BpmService bpmService;
	@Resource
	public BpmDefinitionService bpmDefinitionService;
	@Resource
	private BpmFormFieldService bpmFormFieldService;
	@Resource
	private BpmNodeWebServiceService bpmNodeWebServiceService;
	@Resource
	private SubSystemService subSystemService;
	
	/**
	 * 取得流程变量定义分页列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description = "查看流程变量定义分页列表")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long defId = RequestUtil.getLong(request, "defId");
		BpmDefinition bpmDefinition = bpmDefinitionService.getById(defId);
		String actDefId = bpmDefinition.getActDefId();
		Long actDeployId = bpmDefinition.getActDeployId();
		QueryFilter q = new QueryFilter(request, "bpmDefVarItem", false);
		if (defId != 0) {
			q.getFilters().put("defId", defId);
		}

		List<BpmDefVar> list = bpmDefVarService.getAll(q);
		ModelAndView mv = this.getAutoView().addObject("bpmDefVarList", list)
				.addObject("defId", defId)
				.addObject("actDeployId", actDeployId)
				.addObject("actDefId", actDefId)
				.addObject("bpmDefinition", bpmDefinition);

		return mv;
	}

	/**
	 * 删除流程变量定义
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description = "删除流程变量定义",
			execOrder=ActionExecOrder.BEFORE,
			detail="<#list StringUtils.split(varId,\",\") as item>" +
						"<#assign entity = bpmDefVarService.getById(Long.valueOf(item))/>" +
						"<#if item_index==0>" +
							"删除流程定义【${SysAuditLinkService.getBpmDefinitionLink(entity.defId)}】" +
						"</#if>" +
						"节点"+
						"<#if !StringUtil.isEmpty(entity.nodeName)>" +
							"【${entity.nodeName}】" +
						"</#if>" +
						"的变量:【 ${entity.varName}】、" +
				   "</#list>"
	)
	public void del(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ResultMessage resultMessage = null;
		String preUrl = RequestUtil.getPrePage(request);
		try {
			Long[] lAryId = RequestUtil.getLongAryByStr(request, "varId");
			bpmDefVarService.delByIds(lAryId);
			resultMessage = new ResultMessage(ResultMessage.Success, "删除流程变量成功!");
		} catch (Exception ex) {
			resultMessage = new ResultMessage(ResultMessage.Fail, "流程变量删除失败:"+ex.getMessage());
		}
		addMessage(resultMessage, request);
		response.sendRedirect(preUrl);
	}

	@RequestMapping("getVars")
	@ResponseBody
	public List<BpmDefVar> getVars(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long defId = RequestUtil.getLong(request, "defId");
		List<BpmDefVar> list = bpmDefVarService.getVarsByFlowDefId(defId);
		return list;
	}

	@RequestMapping("edit")
	@Action(description = "编辑流程变量定义")
	public ModelAndView edit(HttpServletRequest request) throws Exception {
		Long defId = RequestUtil.getLong(request, "defId");
		Long varId = RequestUtil.getLong(request, "varId");
		BpmDefinition bpmDefinition = bpmDefinitionService.getById(defId);
		String actDefId = bpmDefinition.getActDefId();
		Long actDeployId = bpmDefinition.getActDeployId();

		String returnUrl = RequestUtil.getPrePage(request);
		BpmDefVar bpmDefVar = null;
		if (varId != 0) {
			bpmDefVar = bpmDefVarService.getById(varId);
		} else {
			bpmDefVar = new BpmDefVar();
		}
		Map<String, String> nodeMap = bpmService.getExecuteNodesMap(actDefId,
				true);
		return getAutoView().addObject("bpmDefVar", bpmDefVar)
				.addObject("returnUrl", returnUrl).addObject("defId", defId)
				.addObject("nodeMap", nodeMap)
				.addObject("actDeployId", actDeployId)
				.addObject("actDefId", actDefId);
	}

	/**
	 * 取得流程变量定义明细
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description = "查看流程变量定义明细")
	public ModelAndView get(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String actDefId = RequestUtil.getString(request, "actDefId");
		long id = RequestUtil.getLong(request, "varId");
		BpmDefVar bpmDefVar = bpmDefVarService.getById(id);
		return getAutoView().addObject("bpmDefVar", bpmDefVar).addObject(
				"actDefId", actDefId);
	}

	/**
	 * 根据流程的deployId和节点ID获取流程变量。
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getByDeployNode")
	public ModelAndView getByDeployNode(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String deployId = RequestUtil.getString(request, "deployId");
		String nodeId = RequestUtil.getString(request, "nodeId");
		List<BpmDefVar> varList = null;
		Long defId = RequestUtil.getLong(request, "defId");
		if (defId != 0) {
			varList = bpmDefVarService.getVarsByFlowDefId(defId);
		} else {
			varList = bpmDefVarService.getByDeployAndNode(deployId, nodeId);
		}
		return getAutoView().addObject("bpmDefVarList", varList);
	}

	/**
	 * 获取流程变量树
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getTree")
	@ResponseBody
	public String getTree(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long defId = RequestUtil.getLong(request, "defId");
		
		String nodeId = RequestUtil.getString(request, "nodeId");
		
		String actDefId = RequestUtil.getString(request, "actDefId");
	
		System.out.println("看一下可以获得nodeId:"+nodeId+"看一下actDefId:"+actDefId+"defId++++++"+defId);
		//调用解析方法获得当前节点的前节点
		List<Map<String,String>> nodeList = NodeCache.getpreNodes(nodeId,actDefId);
	    System.out.println("map+++++++++"+nodeList.size());

	    JSONArray jarray = new JSONArray();
	     
	    if(BeanUtils.isNotEmpty(nodeList)){
			JSONObject jobject = new JSONObject().accumulate("name", "前序引擎")
			                                     .accumulate("children",convertListPoe(nodeList,actDefId));
			System.out.println("jobject==========="+jobject.toString());                       
			jarray.add(jobject);
		}
	    
	    
	    
		List<BpmDefVar> list = bpmDefVarService.getVarsByFlowDefId(defId);
		
		if(BeanUtils.isNotEmpty(list)){
			JSONObject jobject = new JSONObject().accumulate("name", "自定义变量")
												 .accumulate("children", convertList2Json(list));
			jarray.add(jobject);
		}
		
		List<BpmDefVar> list2 = getFormVars(defId);
		if(BeanUtils.isNotEmpty(list2)){
			JSONObject jobject = new JSONObject().accumulate("name", "表单变量")
												 .accumulate("children", convertList2Json(list2));
			jarray.add(jobject);
		}
		return jarray.toString();
	}
	/**
	 * 结果集状态集
	 */
	private JSONArray convertListPoe0(Map<String,String> nodeMap ,Map<String,String> nodeMap1){
		JSONArray jarray = new JSONArray();
	 	JSONObject jobject = new JSONObject().accumulate("name", "结果集")
                                             .accumulate("children", convertListPoe1(nodeMap1));
	 	

         jarray.add(jobject);
         JSONObject jobject1 = new JSONObject().accumulate("name", "状态集")
                                               .accumulate("children", convertListPoe1(nodeMap));
         jarray.add(jobject1);
		return jarray;		
	}
	
	/**
	 * 前序节点 进行编排
	 * 
	 */
	
	private JSONArray convertListPoe(List<Map<String,String>> nodeList,String actDefId){
		JSONArray jarray = new JSONArray();
		//遍历前序节点
		for(Map<String,String> list1 :nodeList){    
			Set<Map.Entry<String,String>> entryset = list1.entrySet();
			Iterator<Map.Entry<String,String>> it = entryset.iterator();
			while(it.hasNext()){
				 Entry entry = (Entry) it.next();
		            String key = (String) entry.getKey();
		            String value = (String) entry.getValue();
		            System.out.println(key + "===============" + value);
                    if(key.contains("StartEvent")){
                    	System.out.println("去除开始节点");
             
                    }else {     	
                    	//解析节点获得结果集
                    	Map<String,String> nodeMap =  bpmNodeWebServiceService.condition(actDefId, key);
                    	Map<String,String> nodeMap1 =  bpmNodeWebServiceService.results(actDefId, key);
                    	JSONObject jobject = new JSONObject().accumulate("name", value+"("+key+")")
                    	                                     .accumulate("varKey", key)
                    	                                     .accumulate("children", convertListPoe0(nodeMap,nodeMap1));                  	
                    	jarray.add(jobject);
                    }
			}
		}
		return jarray;
	}
	/**
	 * 字段
	 * 再次进行遍历
	 * */
	private JSONArray convertListPoe1(Map<String,String> nodeMap){
		

		JSONArray jarray = new JSONArray();
		
		
		  for (Map.Entry<String, String> entry : nodeMap.entrySet()) {
			   String key = entry.getKey().toString();
			   String value = entry.getValue().toString();
			   System.out.println("key=" + key + " value=" + value);
			   
			   String[] fields = value.split(",");
			   
			   for(int i = 0 ; i<fields.length;i++){
				   System.out.println("字段和数据包:"+fields[i]);
				   if(fields[i].contains("(")){
					   JSONObject jobject = new JSONObject();
					   jobject.accumulate("name", fields[i])
					          .accumulate("varKey", key)
					          .accumulate("children", convertListPoe2(fields[i]));
					   jarray.add(jobject);
				   }else{  
					   JSONObject jobject = new JSONObject();

					   System.out.println("name:到底是什么"+fields[i]);
					   jobject.accumulate("name", fields[i])
				              .accumulate("varKey", key);
				          
					   jarray.add(jobject);
				   }
			   }
			   
			  }
       
		return jarray;
	}
	/**
	 * 数据包部分遍历
	 * @param 
	 * @return
	 */
	private JSONArray convertListPoe2(String parcel){
		JSONArray jarray = new JSONArray();
		List<String> fields = bpmNodeWebServiceService.resolveParcle(parcel);
		for(String x :fields){
			JSONObject jobject = new JSONObject();
			jobject.accumulate("name", x);

			jarray.add(jobject);
		}
			
		return jarray;
	}
	
	private JSONArray convertList2Json(List<BpmDefVar> list){
		JSONArray jarray = new JSONArray();
		String name;
		JSONObject jobject;
		for (BpmDefVar bpmDefVar : list) {
			name = bpmDefVar.getVarName();
			if (StringUtil.isNotEmpty(bpmDefVar.getVarDataType())) {
				name += "(" + bpmDefVar.getVarDataType() + ")";
			}
			System.out.println("id"+bpmDefVar.getVarId()+"varName"+bpmDefVar.getVarName()+"varKey"+ bpmDefVar.getVarKey()+"type"+bpmDefVar.getVarDataType()+"name"+name);
			jobject = new JSONObject().accumulate("id", bpmDefVar.getVarId())
									  .accumulate("varName", bpmDefVar.getVarName())
									  .accumulate("varKey", bpmDefVar.getVarKey())
									  .accumulate("type", bpmDefVar.getVarDataType())
									  .accumulate("name", name);
			jarray.add(jobject);
		}
		return jarray;
	}
	
	/**
	 * 获取表单变量
	 * @param defId
	 * @return
	 */
	private List<BpmDefVar> getFormVars(Long defId){
		List<BpmFormField> fieldList= bpmFormFieldService.getFlowVarByFlowDefId(defId);
		List<BpmDefVar> list = new ArrayList<BpmDefVar>();
		
		for(BpmFormField bpmFormField:fieldList){
			BpmDefVar bpmDefVar = new BpmDefVar();
			bpmDefVar.setVarId(bpmFormField.getFieldId());
			bpmDefVar.setVarName(bpmFormField.getFieldDesc());
			bpmDefVar.setVarKey(bpmFormField.getFieldName());
			bpmDefVar.setVarDataType(bpmFormField.getFieldType());
			list.add(bpmDefVar);
		}
		return list;
	}
	
	
	
	/**
	 * 获取流程变量树
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	/*
	@RequestMapping("getBinding")
	@ResponseBody
	public String getBinding(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
        Long defId = RequestUtil.getLong(request, "defId");
		
		String nodeId = RequestUtil.getString(request, "nodeId");
		
		String actDefId = RequestUtil.getString(request, "actDefId");
	
		System.out.println("nodeId:"+nodeId+"actDefId:"+actDefId+"defId++"+defId);
		
		String subString = subSystemService.findScriptNodeDefKey11(actDefId,nodeId);
		System.out.println("subString:"+subString);
		JSONArray jarray = new JSONArray();
		List<BpmDefinition> list = bpmDefinitionService.getByDefKey(subString);
		//查找最新版本
		int i=0 ;
		for(int x=0;x<list.size();x++){
			if(list.get(x).getVersionNo()>i){
				i=list.get(x).getVersionNo();
				i=x;
			}	
			
		}
		Long defId1 = list.get(i).getDefId();
		List<BpmDefVar> list1 = bpmDefVarService.getVarsByFlowDefId(defId1);
		
		for(BpmDefVar bd :list1){
			System.out.println("id:"+bd.getVarId()+"varName:"+bd.getVarName()+"varKey:"+bd.getVarKey()+"type:"+bd.getVarType());
			
			JSONObject jobject = new JSONObject().accumulate("id", bd.getVarId())
			.accumulate("varName", bd.getVarName())
			.accumulate("varKey", bd.getVarKey())
			.accumulate("type", bd.getVarDataType());
			jarray.add(jobject);
		}
		System.out.println("jarray:"+jarray.toString());
		//convertList2Json(list1);
		return jarray.toString();
	}
	*/
	


}
