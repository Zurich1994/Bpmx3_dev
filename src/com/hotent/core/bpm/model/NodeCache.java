package com.hotent.core.bpm.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Branch;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;

import com.hotent.core.util.AppUtil;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.Dom4jUtil;
import com.hotent.platform.dao.bpm.BpmDao;
import com.hotent.platform.dao.bpm.BpmDefinitionDao;
import com.hotent.platform.model.bpm.BpmDefinition;
import com.hotent.tableParcel.model.tableParcel.TableParcel;

/**
 * 流程定义节点Cache,系统维护两份Cache,一份是activiti本身维护的Cache，主要是用于流程跳转用，由Activiti本身引擎使用，
 * 而另一套流程定义的Cache则由 本系统维护，用于取得每个节点的前后连接及父子关系等 。
 * 
 * @author csx
 * 
 */
public class NodeCache {
	private static final Log logger = LogFactory.getLog(NodeCache.class);
	/**
	 * 键为流程定义ID。 值为流程节点map对象。 paraTypeMap 的键为 节点id，值为节点对象。
	 */
	private static Map<String, Map<String, FlowNode>> actNodesMap = new HashMap<String, Map<String, FlowNode>>();



	/**
	 * 根据流程定义id获取流程的节点。
	 * 
	 * <pre>
	 * 	1.首先去缓存中查找如果找到直接返回该流程的节点集合。
	 *  2.不存在直接在流程中查询xml并解析成节点结合，并放到缓存中进行返回。
	 * </pre>
	 * 
	 * @param actDefId
	 * @return
	 */
	public static synchronized Map<String, FlowNode> getByActDefId(
			String actDefId) {
		if (actNodesMap.containsKey(actDefId)) {
			return actNodesMap.get(actDefId);
		} else {
			Map<String, FlowNode> map = readFromXml(actDefId);
			actNodesMap.put(actDefId, map);
			return map;
		}
	}

	/**
	 * 获取流程的起始节点。
	 * 
	 * @param actDefId
	 *            流程定义ID。
	 * @return 返回流程的节点。
	 */
	public static FlowNode getStartNode(String actDefId) {
		// 确保流程取得流程定义节点。
		getByActDefId(actDefId);
		Map<String, FlowNode> nodeMap = actNodesMap.get(actDefId);
		return getStartNode(nodeMap);
	}

	/**
	 * 获取任务节点的起始节点。
	 * 只获取主流程和外部子流程的开始节点。
	 * @param nodeMap
	 * @return
	 */
	public static FlowNode getStartNode(Map<String, FlowNode> nodeMap) {
		for (FlowNode flowNode : nodeMap.values()) {
			
			if("startEvent".equals(flowNode.getNodeType())){
				FlowNode parentNode=flowNode.getParentNode();
				if(parentNode==null || "callActivity".equals(parentNode.getNodeType())){
					return flowNode;
				}
			}
		}
		return null;
	}
	
	/**
	 * 获取第一个节点。
	 * @param actDefId
	 * @return
	 */
	public static List<FlowNode> getFirstNode(String actDefId){
		FlowNode startNode= getStartNode(actDefId);
		if(startNode==null) return new ArrayList<FlowNode>();
		return startNode.getNextFlowNodes();
		
	}
	
	/**
	 * 获取第一个节点，
	 * @param actDefId
	 * @return
	 * @throws Exception 
	 */
	public static FlowNode getFirstNodeId(String actDefId) throws Exception{
		FlowNode startNode= getStartNode(actDefId);
		if(startNode==null) return null;
		List<FlowNode> list= startNode.getNextFlowNodes();
		if(list.size()>1) {
			return null;
		}
		if(list.size()==0){
			throw new  java.lang.Exception("流程定义:" + actDefId +",起始节点没有后续节点，请检查流程图设置!");
		}
		return list.get(0);
	}
	
	/**
	 * 判断起始节点后是否有多个节点
	 * @param actDefId
	 * @return
	 * @throws Exception 
	 * 开发人员   helh
	 */
	public static boolean isMultipleFirstNode(String actDefId) throws Exception{
		FlowNode startNode= getStartNode(actDefId);
		if(startNode==null) return false;
		List<FlowNode> list= startNode.getNextFlowNodes();
		if(list.size()==0){
			throw new  java.lang.Exception("流程定义:" + actDefId +",起始节点没有后续节点，请检查流程图设置!");
		}
		if(list.size() > 1) {
			return true;
		}
		return false;
	}
	
	/**
	 * 判断是否是第一个节点
	 * @param actDefId
	 * @param nodeId
	 * @return
	 */
	public static boolean isFirstNode(String actDefId,String nodeId){
		List<FlowNode>  list = getFirstNode(actDefId);
		for (FlowNode flowNode : list) {
			if(nodeId.equals(flowNode.getNodeId()))
				return true;
		}
		return false;
	}
	
	/**
	 * 判断节点是否是会签节点。
	 * @param actDefId
	 * @param nodeId
	 * @return
	 */
	public static boolean isSignTaskNode(String actDefId,String nodeId){
		getByActDefId(actDefId);
		Map<String, FlowNode> nodeMap = actNodesMap.get(actDefId);
		FlowNode flowNode=nodeMap.get(nodeId);
		if(flowNode.getIsMultiInstance() && flowNode.getNodeType().equals("userTask")){
			return true;
		}
		return false;
		
	}

	/**
	 * 获取流程的结束节点。
	 * 
	 * @param actDefId
	 *            流程定义ID。
	 * @return 返回流程的节点。
	 */
	public static List<FlowNode> getEndNode(String actDefId) {
		// 确保流程取得流程定义节点。
		getByActDefId(actDefId);
		Map<String, FlowNode> nodeMap = actNodesMap.get(actDefId);
		return getEndNode(nodeMap);
	}
	
	/**
	 * 根据流程定义Id和节点Id。
	 * @param actDefId
	 * @param nodeId
	 * @return
	 */
	public static FlowNode getNodeByActNodeId(String actDefId,String nodeId){
		getByActDefId(actDefId);
		Map<String, FlowNode> nodeMap = actNodesMap.get(actDefId);
		return nodeMap.get(nodeId);
	}

	/**
	 * 获取流程的结束节点
	 * 
	 * @param nodeMap
	 * @return
	 */
	private static List<FlowNode> getEndNode(Map<String, FlowNode> nodeMap) {
		List<FlowNode> flowNodeList = new ArrayList<FlowNode>();
		for (FlowNode flowNode : nodeMap.values()) {
			if ("endEvent".equals(flowNode.getNodeType())
					&& BeanUtils.isEmpty(flowNode.getNextFlowNodes())) {
				flowNodeList.add(flowNode);
			}
		}
		return flowNodeList;
	}

	/**
	 * 根据流程定义id获取流程是否有外部子流程。
	 * 
	 * @param actDefId
	 *            流程定义ID。
	 * @return
	 */
	public static boolean hasExternalSubprocess(String actDefId) {
		getByActDefId(actDefId);
		Map<String, FlowNode> nodeMap = actNodesMap.get(actDefId);
		for (FlowNode flowNode : nodeMap.values()) {
			if ("callActivity".equals(flowNode.getNodeType())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 根据流程定义id删除缓存数据。
	 * 
	 * @param actDefId
	 */
	public static void clear(String actDefId) {
		actNodesMap.remove(actDefId);
	}

	/**
	 * 根据流程定义返回流程节点关系。
	 * 
	 * @param actDefId
	 * @return
	 */
	public static Map<String, FlowNode> readFromXml(String actDefId) {
		BpmDao dao = (BpmDao) AppUtil.getBean(BpmDao.class);
		BpmDefinitionDao bpmDefinitionDao = (BpmDefinitionDao) AppUtil
				.getBean(BpmDefinitionDao.class);
		BpmDefinition bpmDefinition = bpmDefinitionDao.getByActDefId(actDefId);
		if(bpmDefinition==null){
			return new HashMap<String, FlowNode>();
		}
		Long deployId = bpmDefinition.getActDeployId();
		String xml = dao.getDefXmlByDeployId(deployId.toString());
		return parseXml(xml,null);
	}

	/**
	 * 根据流程键获取流程的xml数据。
	 * 
	 * @param actDefkey
	 * @return
	 */
	private static String getXmlByDefKey(String actDefkey) {
		BpmDao dao = (BpmDao) AppUtil.getBean(BpmDao.class);
		BpmDefinitionDao bpmDefinitionDao = (BpmDefinitionDao) AppUtil
				.getBean(BpmDefinitionDao.class);
		BpmDefinition bpmDefinition = bpmDefinitionDao
				.getByActDefKeyIsMain(actDefkey);
		if(bpmDefinition == null) throw new RuntimeException("未查找到当前流程的子流程！");
		
		Long deployId = bpmDefinition.getActDeployId();
		String xml = dao.getDefXmlByDeployId(deployId.toString());
		return xml;
	}

	/**
	 * 解析流程中的所有节点。
	 * 
	 * @param xml
	 * @return
	 */
	private static Map<String, FlowNode> parseXml(String xml,FlowNode parentNode) {
		xml = xml.replace(
				"xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\"", "");
		Document document = Dom4jUtil.loadXml(xml);
		Element el = document.getRootElement();
		boolean accessOrder = false;
		Map<String, FlowNode> map = new LinkedHashMap<String, FlowNode>(20,0.80f,accessOrder);
		Element processEl = (Element) el.selectSingleNode("./process");
		Iterator<Element> its = processEl.elementIterator();
        //遍历子节点
		while (its.hasNext()) {
			Element nodeEl = its.next();
			String nodeType = nodeEl.getName();
            String nodeId = nodeEl.attributeValue("id");
			String nodeName = nodeEl.attributeValue("name");
            // 是否多实例节点
			boolean isMultiInstance = nodeEl
					.selectSingleNode("./multiInstanceLoopCharacteristics") == null ? false
					: true;
			// 节点类型
			// 开始节点，用户任务，并行网关，条件网关，分支网关，结束节点，自动任务节点。
			if ("startEvent".equals(nodeType) || FlowNode.TYPE_USERTASK .equals(nodeType)
					|| "parallelGateway".equals(nodeType)
					||  "inclusiveGateway".equals(nodeType)
					||  FlowNode.TYPE_EXCLUSIVEGATEWAY.equals(nodeType)
					|| "endEvent".equals(nodeType)
					|| "serviceTask".equals(nodeType)) {
				FlowNode flowNode = new FlowNode(nodeId, nodeName, nodeType,
						isMultiInstance);
				if(FlowNode.TYPE_START_EVENT.equalsIgnoreCase(nodeType)){
					flowNode.setParentNode(parentNode);
			     }
				map.put(nodeId, flowNode);
			}
			// 子流程
			else if ("subProcess".equals(nodeType)) {
				map=getSubInnerProFlowNode(map,nodeId,nodeName,nodeType,isMultiInstance,nodeEl);
			 }
			// 节点为外部子流程的情况。
			else if ("callActivity".equals(nodeType)) {
				String flowKey = nodeEl.attributeValue("calledElement");
				// 获取子流程的xml数据。
				String subProcessXml = getXmlByDefKey(flowKey);
				// 解析子流程的xml数据。
				FlowNode flowNode = new FlowNode(nodeId, nodeName, nodeType,
						isMultiInstance);
				Map<String, FlowNode> subChildNodes = parseXml(subProcessXml,flowNode);
				flowNode.setAttribute("subFlowKey", flowKey);
				map.put(nodeId, flowNode);
				// 设置子流程的节点。
				flowNode.setSubProcessNodes(subChildNodes);
			}
		}

		// 遍历流程中节点之间的关联。
		List<Node> seqFlowList = document
				.selectNodes("/definitions/process//sequenceFlow");
		for (int i = 0; i < seqFlowList.size(); i++) {
			Element seqFlow = (Element) seqFlowList.get(i);
			String sourceRef = seqFlow.attributeValue("sourceRef");
			String targetRef = seqFlow.attributeValue("targetRef");
            //取得源节点和目标节点
			FlowNode sourceFlowNode = map.get(sourceRef);
			FlowNode targetFlowNode = map.get(targetRef);
			//源节点与目标节点都不为空的情况
			if (sourceFlowNode != null && targetFlowNode != null) {
				//取得源节点与目标节点的名称
				logger.debug("sourceRef:" + sourceFlowNode.getNodeName()
						+ " targetRef:" + targetFlowNode.getNodeName());
				//目标节点的父节点不为空的情况
				if (targetFlowNode.getParentNode() != null) {
					//取得目标节点的父节点的名称
					logger.debug("parentNode:"
							+ targetFlowNode.getParentNode().getNodeName());
				}
				// 设置子流程中的第一个任务节点
				if ("startEvent".equals(sourceFlowNode.getNodeType())
						&& sourceFlowNode.getParentNode() != null) {
					sourceFlowNode.setFirstNode(true);
					sourceFlowNode.getParentNode().setSubFirstNode(
							sourceFlowNode);
					// 当子流程为多实例时，也认为子流程内的其第一个任务节点也是为多实例
					if (targetFlowNode.getParentNode()!=null && targetFlowNode.getParentNode().getIsMultiInstance()) {
						targetFlowNode.setIsMultiInstance(true);
					}
				}
				sourceFlowNode.getNextFlowNodes().add(targetFlowNode);
				targetFlowNode.getPreFlowNodes().add(sourceFlowNode);
			}
		}
		return map;
	}
	@SuppressWarnings("unchecked")
	private static Map<String, FlowNode> getSubInnerProFlowNode(Map<String, FlowNode> map, String nodeId, String nodeName, String nodeType, boolean isMultiInstance, Element nodeEl){
		//主流程的process state节点要映射变量，作为主流程和子流程的调用参数
		//其实子流程结束后会返回父流程中中接续执行
		//该流程对象中并没有父流程对象中已经设置存在的那些变量参数值如果想让子流程中也具有这些变量那么必须做一个映射
		//<parameter-out>元素表示从子流程SubCheckProcess中流出到MainProcess中，将数据又传回主流程中，继续后续流程的操作。
		// 产生子流程的映射 
		//当nodeType为subProcess时， new ArrayList<FlowNode>()存放子流程下的所有的任务节点
		FlowNode subProcessNode = new FlowNode(nodeId, nodeName,
				nodeType, new ArrayList<FlowNode>(), isMultiInstance);
		// 将子流程节点放到map对象中。
		map.put(nodeId, subProcessNode);
		// 遍历子流程的所有节点。
		List<Element> subElements = nodeEl.elements();
		for (Element subEl : subElements) {
			String subNodeType = subEl.getName();
			// 是否多实例节点 
			boolean subIsMultiInstance = nodeEl
					.selectSingleNode("./multiInstanceLoopCharacteristics") == null ? false
					: true;
			String subNodeName = subEl.attributeValue("name");
			System.out.println("该节点的名字是："+subNodeName);
			String subNodeId = subEl.attributeValue("id");
			if ("startEvent".equals(subNodeType)
					|| "userTask".equals(subNodeType)
					|| "parallelGateway".equals(subNodeType)
					|| "inclusiveGateway".equals(subNodeType)
					|| "exclusiveGateway".equals(subNodeType)
					|| "endEvent".equals(subNodeType)
					|| "serviceTask".equals(subNodeType)) {
				FlowNode flowNode = new FlowNode(subNodeId,
						subNodeName, subNodeType, false, subProcessNode);//subProcessNode为父节点
				//把该节点添加到父节点的列表中
				subProcessNode.getSubFlowNodes().add(flowNode);
				map.put(subNodeId, flowNode);
			}// 节点为外部子流程的情况。
			else if ("callActivity".equals(subNodeType)) {
				String flowKey = subEl.attributeValue("calledElement");
				// 获取子流程的xml数据。
				String subProcessXml = getXmlByDefKey(flowKey);
				// 解析子流程的xml数据。
				FlowNode flowNode = new FlowNode(subNodeId, subNodeName, subNodeType, subIsMultiInstance);
				subProcessNode.getSubFlowNodes().add(flowNode);
				flowNode.setAttribute("subFlowKey", flowKey);
				// 设置子流程的节点。
				Map<String, FlowNode> subChildNodes = parseXml(subProcessXml,flowNode);
				flowNode.setSubProcessNodes(subChildNodes);
				map.put(subNodeId, flowNode);
			}else if("subProcess".equals(subNodeType)){
				map=getSubInnerProFlowNode(map, subNodeId, subNodeName, subNodeType, subIsMultiInstance, subEl);
			}
		}
		return map;
	
	}
	public static Set<String> getSubKeysByXml(String xml) {
		Set<String> keySet = new HashSet<String>();
		getSubKeysByXml(xml,keySet);
		return keySet;
	}

	/**
	 * 解析XMl获得 流程Key
	 * 
	 * @param xml
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static void getSubKeysByXml(String xml,Set<String> keySet) {
		xml = xml.replace(
				"xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\"", "");
		Document document = Dom4jUtil.loadXml(xml);
		Element el = document.getRootElement();
		Element processEl = (Element) el.selectSingleNode("./process");
		if (BeanUtils.isEmpty(processEl))
			return;
		Iterator<Element> its = processEl.elementIterator();
		
		while (its.hasNext()) {
			Element nodeEl = its.next();
			String nodeType = nodeEl.getName();
			// 节点为外部子流程的情况。
			if ("callActivity".equals(nodeType)) {
				String flowKey = nodeEl.attributeValue("calledElement");
				keySet.add(flowKey);
				// 获取子流程的xml数据。
				String subProcessXml = getXmlByDefKey(flowKey);
				// 解析子流程的xml数据。
				getSubKeysByXml(subProcessXml,keySet);
			}
		}
	}
	//根据当前节点，获得后续节点
	public static Map<String,String> getNextNodes(String nodeid,String actDefid){
		Map<String, FlowNode> map = readFromXml(actDefid);
		Map<String,String> flownodes = new HashMap<String, String>();
		for(String key : map.keySet()){
			if(key.equals(nodeid)){
				FlowNode flownode = map.get(nodeid);
				List<FlowNode> nextflownodes = flownode.getNextFlowNodes();
				for(FlowNode next:nextflownodes){
					flownodes.put(next.getNodeId(),next.getNodeName());
				}
			}
		}
		return flownodes;
	}
	
	//根据当前节点，获得后续所有用户任务节点
	//王钊
	public static List<Map<String,String>> getNextTaskNodes(String nodeid,String actDefid){
		Map<String, FlowNode> map = readFromXml(actDefid);
		List<Map<String,String>> flownodes = new ArrayList<Map<String,String>>();
		Map<String,String> map1 = new HashMap<String, String>();
		for(String key : map.keySet()){
			System.out.println("nodeid:"+nodeid+"key:"+key);
			if(key.equals(nodeid)){
				FlowNode flownode = map.get(nodeid);
				List<FlowNode> nextflownodes = flownode.getNextFlowNodes();
				//System.out.println("后续所有节点：：："+nextflownodes);
				System.out.println("nextflownodes:"+nextflownodes.size());
				for(FlowNode next:nextflownodes){
					if(next.getNodeId().contains("EndEvent")){
						System.out.println("----------*********!!!!!!!!:"+flownodes.size());
						return flownodes;
					}
					else{
						map1.put(next.getNodeId(),next.getNodeName());
						flownodes =getNextTaskNodes(next.getNodeId(),actDefid);
						System.out.println("原来是这样的 map1："+map1+"::"+next.getNodeId());
						if(next.getNodeId().contains("UserTask")){
							flownodes.add(map1);
						}
						//flownodes =getNextTaskNodes(next.getNodeId(),actDefid);
					}
					
					//flownodes.put(next.getNodeId(),next.getNodeName());
					System.out.println("后续所有节点：：："+next.getNodeId()+":"+next.getNodeName());
				}
			}
		}
		return flownodes;
	}
	
	
	//根据当前节点，获得后续所有事物图节点
	//王钊
	public static List<Map<String,String>> getNextScriptNodes(String nodeid,String actDefid){
		Map<String, FlowNode> map = readFromXml(actDefid);
		List<Map<String,String>> flownodes = new ArrayList<Map<String,String>>();
		Map<String,String> map2 = new HashMap<String, String>();
		for(String key : map.keySet()){
			System.out.println("nodeid:"+nodeid+"key:"+key);
			if(key.equals(nodeid)){
				FlowNode flownode = map.get(nodeid);
				List<FlowNode> nextflownodes = flownode.getNextFlowNodes();
				//System.out.println("后续所有节点：：："+nextflownodes);
				System.out.println("nextflownodesssssssss:"+nextflownodes.size());
				for(FlowNode next:nextflownodes){
					if(next.getNodeId().contains("EndEvent")){
						System.out.println("----------*********!!!!!!!!:"+flownodes.size());
						return flownodes;
					}
					else{
						map2.put(next.getNodeId(),next.getNodeName());
						flownodes =getNextScriptNodes(next.getNodeId(),actDefid);
						System.out.println("原来是这样的 map2："+map2+"::"+next.getNodeId());
						if(next.getNodeId().contains("ScriptTask")){
							flownodes.add(map2);
						}
						//flownodes =getNextTaskNodes(next.getNodeId(),actDefid);
					}
					
					//flownodes.put(next.getNodeId(),next.getNodeName());
					System.out.println("后续所有ScriptTask节点：：："+next.getNodeId()+":"+next.getNodeName());
				}
			}
		}
		return flownodes;
	}
	
	//根据当前节点，获得所有前置节点
	public static List<Map<String,String>> getpreNodes(String nodeid,String actDefid){
		//getNextNodes(nodeid, actDefid);
		Map<String, FlowNode> map = readFromXml(actDefid);
		System.out.println("map:"+map.size()+nodeid);
		List<Map<String,String>> flownodes = new ArrayList<Map<String,String>>();
		Map<String,String> map1 = new HashMap<String, String>();
		for(String key : map.keySet()){
			System.out.println("nodeid:"+nodeid+"key:"+key);
			if(key.equals(nodeid)){
				FlowNode flownode = map.get(nodeid);
				List<FlowNode> preflownodes = flownode.getPreFlowNodes();
				System.out.println("preflownodes:"+preflownodes.size());
				for(FlowNode next:preflownodes){
					System.out.println("next.toString():"+next.getNodeId());
					if(next.getNodeId().contains("StartEvent")){
						System.out.println("----------*********:"+flownodes.size());
						return flownodes;
					}
					else{
						flownodes =getpreNodes(next.getNodeId(),actDefid);
						map1.put(next.getNodeId(),next.getNodeName());
						flownodes.add(map1);
						
					}
					
					
				}
			}
		}
		return flownodes;
	}
	//根据当前节点获得前序节点
	public static Map<String,String> getPreNodes(String nodeid,String actDefid){
		Map<String, FlowNode> map = readFromXml(actDefid);
		Map<String,String> flownodes = new HashMap<String, String>();
		for(String key : map.keySet()){
			if(key.equals(nodeid)){
				FlowNode flownode = map.get(nodeid);
				List<FlowNode> preflownodes = flownode.getPreFlowNodes();
				for(FlowNode pre:preflownodes){
					flownodes.put(pre.getNodeId(),pre.getNodeName());
				}
			}
		}
		return flownodes;
	}


	
	
	



}
