package com.hotent.core.bpm.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 流程定义中的节点定义属性及运行中的人员配置。
 * @author csx
 *
 */
public class FlowNode {
	
	public static final String TYPE_USERTASK="userTask";
	public static final String TYPE_SUBPROCESS="subProcess";
	public static final String TYPE_START_EVENT="startEvent";
	public static final String TYPE_EXCLUSIVEGATEWAY="exclusiveGateway";
	//节点ID
	private String nodeId=null;
	//节点名称
	private String nodeName=null;
	//值有userTask,subProcess,Gateway等.
	private String nodeType=null;
	//是否为多实例任务
	private Boolean isMultiInstance=false;
	
	//父节点，用于子流程中的任务节点
	private FlowNode parentNode=null;
	//是否为子流程中的第一个节点
	private boolean isFirstNode=false;
	
	//属性map。
	private Map<String, String> attrMap=new HashMap<String, String>();
	
	//子流程节点。
	private Map<String,FlowNode> subProcessNodes=new HashMap<String, FlowNode>();
	

	//前置节点列表
	private List<FlowNode> preFlowNodes=new ArrayList<FlowNode>();
	//后置节点列表
	private List<FlowNode> nextFlowNodes=new ArrayList<FlowNode>();
	
	//当nodeType为subProcess时，存放子流程下的所有的任务节点
	private List<FlowNode> subFlowNodes=new ArrayList<FlowNode>();
	//子流程中的第一个任务节点ID
	private FlowNode subFirstNode=null;
	

	public String getNodeId() {
		return nodeId;
	}

	public FlowNode() {
	
	}

	public FlowNode(String nodeId, String nodeName, String nodeType) {
		this.nodeId = nodeId;
		this.nodeName = nodeName;
		this.nodeType = nodeType;
	}
	
	public FlowNode(String nodeId, String nodeName, String nodeType,List<FlowNode> subFlowNodes) {
		this.nodeId = nodeId;
		this.nodeName = nodeName;
		this.nodeType = nodeType;
		this.subFlowNodes=subFlowNodes;
	}
	
	public FlowNode(String nodeId,String nodeName,String nodeType,boolean isMultiInstance){
		this.nodeId = nodeId;
		this.nodeName = nodeName;
		this.nodeType = nodeType;
		this.isMultiInstance=isMultiInstance;
	}
	
	public FlowNode(String nodeId,String nodeName,String nodeType,boolean isMultiInstance,FlowNode parentNode){
		this.nodeId = nodeId;
		this.nodeName = nodeName;
		this.nodeType = nodeType;
		this.isMultiInstance=isMultiInstance;
		this.parentNode=parentNode;
	}
	
	public FlowNode(String nodeId, String nodeName, String nodeType,List<FlowNode> subFlowNodes,boolean isMultiInstance) {
		this.nodeId = nodeId;
		this.nodeName = nodeName;
		this.nodeType = nodeType;
		this.subFlowNodes=subFlowNodes;
		this.isMultiInstance=isMultiInstance;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getNodeType() {
		return nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	public List<FlowNode> getPreFlowNodes() {
		return preFlowNodes;
	}

	public void setPreFlowNodes(List<FlowNode> preFlowNodes) {
		this.preFlowNodes = preFlowNodes;
	}

	public List<FlowNode> getNextFlowNodes() {
		return nextFlowNodes;
	}

	public void setNextFlowNodes(List<FlowNode> nextFlowNodes) {
		this.nextFlowNodes = nextFlowNodes;
	}

	public List<FlowNode> getSubFlowNodes() {
		return subFlowNodes;
	}

	public void setSubFlowNodes(List<FlowNode> subFlowNodes) {
		this.subFlowNodes = subFlowNodes;
	}

	public Boolean getIsMultiInstance() {
		return isMultiInstance;
	}

	public void setIsMultiInstance(Boolean isMultiInstance) {
		this.isMultiInstance = isMultiInstance;
	}

	public boolean isFirstNode() {
		return isFirstNode;
	}

	public void setFirstNode(boolean isFirstNode) {
		this.isFirstNode = isFirstNode;
	}

	public FlowNode getSubFirstNode() {
		return subFirstNode;
	}

	public void setSubFirstNode(FlowNode subFirstNode) {
		this.subFirstNode = subFirstNode;
	}

	public FlowNode getParentNode() {
		return parentNode;
	}

	public void setParentNode(FlowNode parentNode) {
		this.parentNode = parentNode;
	}
	
	public boolean getIsSubProcess(){
		return this.nodeType.equalsIgnoreCase("subProcess");
	}
	
	public boolean getIsCallActivity(){
		return this.nodeType.equalsIgnoreCase("callActivity");
	}

	/**
	 * 是否会签节点。
	 * @return
	 */
	public boolean getIsSignNode(){
		if(this.nodeType.equalsIgnoreCase(TYPE_USERTASK) && getIsMultiInstance()){
			return true;
		}
		return false;
	}
	
	public boolean getIsFirstNode(){
		if(this.getPreFlowNodes()!=null ){
			List<FlowNode> list=this.getPreFlowNodes();
			for(FlowNode node:list){
				if(node.getNodeType().equalsIgnoreCase(TYPE_START_EVENT)){
					return true;
				}
			}
			
		}
		return false;
	}

	
	/**
	 * 设置属性。
	 * @param name		属性名称。
	 * @param value		属性值。
	 */
	public void setAttribute(String name,String value){
		this.attrMap.put(name, value);
	}
	
	/**
	 * 获取属性。
	 * @param name
	 * @return
	 */
	public String getAttribute(String name){
		if(this.attrMap.containsKey(name)){
			return this.attrMap.get(name);
		}
		return "";
	}

	public Map<String, FlowNode> getSubProcessNodes() {
		return subProcessNodes;
	}

	public void setSubProcessNodes(Map<String, FlowNode> subProcessNodes) {
		this.subProcessNodes = subProcessNodes;
	}

	 
}
