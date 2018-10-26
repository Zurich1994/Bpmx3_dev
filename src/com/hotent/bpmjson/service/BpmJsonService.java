package com.hotent.bpmjson.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.hotent.SysDefNodeErgodic.service.SysDefNodeErgodic.SysdefnodeergodicService;
import com.hotent.bpmjson.model.H5Model;
import com.hotent.bpmjson.util.BpmJsonUtil;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.platform.dao.bpm.BpmDao;
import com.hotent.platform.model.bpm.BpmDefinition;
import com.hotent.platform.service.bpm.BpmDefinitionService;
import com.hotent.platform.service.bpm.BpmService;

/**
 * Bpm转化Json的服务
 * 
 * @author Administrator
 */
@Service
public class BpmJsonService {
	@Resource
	private BpmDefinitionService bpmDefinitionService;

	@Resource
	private BpmService bpmService;
	//那晓旭
	@Resource
	private SysdefnodeergodicService sysdefnodeergodicService;
	
	public String  getFlowChart(long defId, String parentActDefId,
			String typeName) {
		BpmDefinition bpmDefinition = bpmDefinitionService.getById(defId);
		String actDefId=bpmDefinition.getActDefId();
		List<H5Model> sModelList = new ArrayList<H5Model>();
		if (bpmDefinition.getActDeployId() != null) {
			String bpmnXml = bpmService.getDefXmlByDeployId(bpmDefinition
					.getActDeployId().toString());
			String designXml = bpmDefinition.getDefXml();
			// 得到流程图节点和连线集合
			 sModelList = BpmJsonUtil
					.parseBpmnXml2ModelList(bpmnXml, designXml);;
				// 组装JSON数据
			
			 for (H5Model h5Model : sModelList) {
					if (BpmJsonUtil.Q_EDGE.equals(h5Model.get_className()))
						continue;
					
					h5Model.getJson().put("defId",defId);
					h5Model.getJson().put("actDefId",bpmDefinition.getActDefId());
					h5Model.getJson().put("deployId",bpmDefinition.getActDeployId());
					h5Model.getJson().put("parentActDefId",parentActDefId);
					h5Model.getJson().put("defKey",bpmDefinition.getActDefKey());
					h5Model.getJson().put("typeName",typeName);
					 
				}
			List<H5Model> modelList = null;
			List<List<H5Model>> modelListList=new ArrayList<List<H5Model>>();
			for (H5Model model : sModelList) {
				if(BpmJsonUtil.Q_GROUP.equals(model.get_className())){// 组节点
					Map<String, Object> jsonMap = model.getJson();
					String subDefKey = (String) jsonMap.get("subDefKey");
					System.out.println("subDefKey:"+subDefKey);
					
					List<BpmDefinition> listbpm=bpmDefinitionService.getByDefKey(subDefKey);
					BpmDefinition bpm=listbpm.get(listbpm.size()-1);
					String designXml_c=bpm.getDefXml();
					
					
					if(StringUtil.isNotEmpty(bpm.getActDefId()))
					{
						String bpmnXml_c  = bpmService.getDefXmlByDeployId(bpm
							.getActDeployId().toString());
							modelList = BpmJsonUtil.parseBpmnXml2ModelList(bpmnXml_c, designXml_c);
							for (H5Model h5Model : modelList) {
								if ("Q.Edge".equals(h5Model.get_className()))
									continue;
								h5Model.getJson().put("defId",bpm.getDefId());
								h5Model.getJson().put("actDefId",bpm.getActDefId());
								h5Model.getJson().put("deployId",bpm.getActDeployId());
								h5Model.getJson().put("parentActDefId",actDefId);
								h5Model.getJson().put("defKey",bpm.getActDefKey());
								String subTypeName=null;//绑定图的typeName
								if("flowChart".equals(typeName))
								{
									subTypeName="operationChart";//流程图上绑定操作图
								}
								else if("operationChart".equals(typeName))
								{
									subTypeName="transactionChart";//操作图上绑定事物图
								}
								else if("transactionChart".equals(typeName))
								{
									subTypeName="transactionChart";//事物图上绑定事物图
								}
								h5Model.getJson().put("typeName",subTypeName);
								 
							}
						BpmJsonUtil.addNodesToGroup(model, modelList);
						
						modelListList.add(modelList);
					}
				}
			}
			for(List<H5Model> list:modelListList)
			{
				sModelList.addAll(list);
			}
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("version", "2.0");
		jsonObject.put("datas", sModelList);
		jsonObject.put("scale", 1);// 比例
		return jsonObject.toString();
	}

	public String getOperationChart(long defId, String parentActDefId,
			String typeName) {

		BpmDefinition bpmDefinition = bpmDefinitionService.getById(defId);
		String actDefId=bpmDefinition.getActDefId();
		List<H5Model> sModelList = new ArrayList<H5Model>();
		if (bpmDefinition.getActDeployId() != null) {
			String bpmnXml = bpmService.getDefXmlByDeployId(bpmDefinition
					.getActDeployId().toString());
			String designXml = bpmDefinition.getDefXml();
			// 得到流程图节点和连线集合
			 sModelList = BpmJsonUtil
					.parseBpmnXml2ModelList(bpmnXml, designXml);;
				
				
			 for (H5Model h5Model : sModelList) {
					if (BpmJsonUtil.Q_EDGE.equals(h5Model.get_className()))
						continue;
					h5Model.getJson().put("defId",defId);
					h5Model.getJson().put("actDefId",bpmDefinition.getActDefId());
					h5Model.getJson().put("deployId",bpmDefinition.getActDeployId());
					h5Model.getJson().put("parentActDefId",parentActDefId);
					h5Model.getJson().put("defKey",bpmDefinition.getActDefKey());
					h5Model.getJson().put("typeName",typeName);
					 
				}
			List<H5Model> modelList = null;
			List<List<H5Model>> modelListList=new ArrayList<List<H5Model>>();
			for (H5Model model : sModelList) {
				if(BpmJsonUtil.Q_GROUP.equals(model.get_className())){// 组节点
					Map<String, Object> jsonMap = model.getJson();
					String subDefKey = (String) jsonMap.get("subDefKey");
					System.out.println("subDefKey:"+subDefKey);
					
					List<BpmDefinition> listbpm=bpmDefinitionService.getByDefKey(subDefKey);
					BpmDefinition bpm=listbpm.get(listbpm.size()-1);
					String designXml_c=bpm.getDefXml();
					Map<String,Object> nodeType1=sysdefnodeergodicService.findNodeTypeByDefKey(bpm.getDefKey());//根据事务图defkey查找事务图中读写页面节点和读写的表
					
					if(StringUtil.isNotEmpty(bpm.getActDefId()))
					{
						String bpmnXml_c  = bpmService.getDefXmlByDeployId(bpm
							.getActDeployId().toString());
							modelList = BpmJsonUtil.parseBpmnXml2ModelListAndNodeType(bpmnXml_c, designXml_c, nodeType1);
							for (H5Model h5Model : modelList) {
								if ("Q.Edge".equals(h5Model.get_className()))
									continue;
								h5Model.getJson().put("defId",bpm.getDefId());
								h5Model.getJson().put("actDefId",bpm.getActDefId());
								h5Model.getJson().put("deployId",bpm.getActDeployId());
								h5Model.getJson().put("parentActDefId",actDefId);
								h5Model.getJson().put("defKey",bpm.getActDefKey());
								String subTypeName=null;//绑定图的typeName
								if("flowChart".equals(typeName))
								{
									subTypeName="operationChart";//流程图上绑定操作图
								}
								else if("operationChart".equals(typeName))
								{
									subTypeName="transactionChart";//操作图上绑定事物图
								}
								else if("transactionChart".equals(typeName))
								{
									subTypeName="transactionChart";//事物图上绑定事物图
								}
								h5Model.getJson().put("typeName",subTypeName);
								 
							}
						BpmJsonUtil.addNodesToGroup(model, modelList);
						
						modelListList.add(modelList);
					}
				}
			}
			for(List<H5Model> list:modelListList)
			{
				sModelList.addAll(list);
			}
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("version", "2.0");
		jsonObject.put("datas", sModelList);
		jsonObject.put("scale", 1);// 比例
		return jsonObject.toString();
	}

	public String getTransactionChart(long defId, String parentActDefId,
			String typeName) {
		BpmDefinition bpmDefinition = bpmDefinitionService.getById(defId);
		String actDefId=bpmDefinition.getActDefId();
		List<H5Model> sModelList = new ArrayList<H5Model>();
		if (bpmDefinition.getActDeployId() != null) {
			String bpmnXml = bpmService.getDefXmlByDeployId(bpmDefinition
					.getActDeployId().toString());
			String designXml = bpmDefinition.getDefXml();
			// 得到流程图节点和连线集合
			Map<String,Object> nodeType=sysdefnodeergodicService.findNodeTypeByDefKey(bpmDefinition.getDefKey());//根据事务图defkey查找事务图中读写页面节点和读写的表
			 sModelList = BpmJsonUtil
					.parseBpmnXml2ModelListAndNodeType(bpmnXml, designXml, nodeType);
			
			 for (H5Model h5Model : sModelList) {
					if (BpmJsonUtil.Q_EDGE.equals(h5Model.get_className()))
						continue;
					h5Model.getJson().put("defId",defId);
					h5Model.getJson().put("actDefId",bpmDefinition.getActDefId());
					h5Model.getJson().put("deployId",bpmDefinition.getActDeployId());
					h5Model.getJson().put("parentActDefId",parentActDefId);
					h5Model.getJson().put("defKey",bpmDefinition.getActDefKey());
					h5Model.getJson().put("typeName",typeName);
					 
				}
			List<H5Model> modelList = null;
			List<List<H5Model>> modelListList=new ArrayList<List<H5Model>>();
			for (H5Model model : sModelList) {
				if(BpmJsonUtil.Q_GROUP.equals(model.get_className())){// 组节点
					Map<String, Object> jsonMap = model.getJson();
					String subDefKey = (String) jsonMap.get("subDefKey");
					System.out.println("subDefKey:"+subDefKey);
					
					List<BpmDefinition> listbpm=bpmDefinitionService.getByDefKey(subDefKey);
					BpmDefinition bpm=listbpm.get(listbpm.size()-1);
					String designXml_c=bpm.getDefXml();
					Map<String,Object> nodeType1=sysdefnodeergodicService.findNodeTypeByDefKey(bpm.getDefKey());//根据事务图defkey查找事务图中读写页面节点和读写的表
					
					if(StringUtil.isNotEmpty(bpm.getActDefId()))
					{
						String bpmnXml_c  = bpmService.getDefXmlByDeployId(bpm
							.getActDeployId().toString());
							modelList = BpmJsonUtil.parseBpmnXml2ModelListAndNodeType(bpmnXml_c, designXml_c, nodeType1);
							for (H5Model h5Model : modelList) {
								if ("Q.Edge".equals(h5Model.get_className()))
									continue;
								h5Model.getJson().put("defId",bpm.getDefId());
								h5Model.getJson().put("actDefId",bpm.getActDefId());
								h5Model.getJson().put("deployId",bpm.getActDeployId());
								h5Model.getJson().put("parentActDefId",actDefId);
								h5Model.getJson().put("defKey",bpm.getActDefKey());
								String subTypeName=null;//绑定图的typeName
								if("flowChart".equals(typeName))
								{
									subTypeName="operationChart";//流程图上绑定操作图
								}
								else if("operationChart".equals(typeName))
								{
									subTypeName="transactionChart";//操作图上绑定事物图
								}
								else if("transactionChart".equals(typeName))
								{
									subTypeName="transactionChart";//事物图上绑定事物图
								}
								h5Model.getJson().put("typeName",subTypeName);
								 
							}
						BpmJsonUtil.addNodesToGroup(model, modelList);
						
						modelListList.add(modelList);
					}
				}
			}
			for(List<H5Model> list:modelListList)
			{
				sModelList.addAll(list);
			}
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("version", "2.0");
		jsonObject.put("datas", sModelList);
		jsonObject.put("scale", 1);// 比例
		return jsonObject.toString();
		
	}
	//获得马尔可夫链展现用的图（没有包含子图）
	public String getMarkovchainChart(long defId, String parentActDefId,
			String typeName) {
		BpmDefinition bpmDefinition = bpmDefinitionService.getById(defId);
		String actDefId=bpmDefinition.getActDefId();
		List<H5Model> sModelList = new ArrayList<H5Model>();
		if (bpmDefinition.getActDeployId() != null) {
			String bpmnXml = bpmService.getDefXmlByDeployId(bpmDefinition
					.getActDeployId().toString());
			String designXml = bpmDefinition.getDefXml();
			// 得到流程图节点和连线集合
			
			 String markovchain="true";;//如果是马尔可夫链就不用group
			
			sModelList = BpmJsonUtil.parseBpmnXml2ModelListAndMarkovchain(bpmnXml, designXml, markovchain);
			
			 for (H5Model h5Model : sModelList) {
					if (BpmJsonUtil.Q_EDGE.equals(h5Model.get_className()))
						continue;
					h5Model.getJson().put("defId",defId);
					h5Model.getJson().put("actDefId",bpmDefinition.getActDefId());
					h5Model.getJson().put("deployId",bpmDefinition.getActDeployId());
					h5Model.getJson().put("parentActDefId",parentActDefId);
					h5Model.getJson().put("defKey",bpmDefinition.getActDefKey());
					h5Model.getJson().put("typeName",typeName);
					 
			
				}
			
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("version", "2.0");
		jsonObject.put("datas", sModelList);
		jsonObject.put("scale", 1);// 比例
		return jsonObject.toString();
		
	}

}
