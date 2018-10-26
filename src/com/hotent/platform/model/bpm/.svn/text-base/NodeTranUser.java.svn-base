package com.hotent.platform.model.bpm;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * 当前节点的下一步所有的任务节点的用户映射
 * 用于为当前任务显示下一步的所有任务节点及用户选择使用
 * @author csx
 *
 */
public class NodeTranUser {
	/**
	 * 节点名
	 */
	private String nodeName;
	/**
	 * 节点ID
	 */
	private String nodeId;
	
	//当前任务节点的跳出节点人员列表
	Set<NodeUserMap> nodeUserMapSet=new LinkedHashSet<NodeUserMap>();
	
	//后续节点,遇到网关节点不递归
	Map<String,String> nextPathMap = new HashMap<String,String>();
	
	public Map<String, String> getNextPathMap() {
		return nextPathMap;
	}

	public void setNextPathMap(Map<String, String> nextPathMap) {
		this.nextPathMap = nextPathMap;
	}

	public String getNodeName() {
		return nodeName;
	}
	
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	
	public NodeTranUser() {
	}
	
	public NodeTranUser(String nodeId,String nodeName,
			Set<NodeUserMap> nodeUserMapSet) {
		super();
		this.nodeId=nodeId;
		this.nodeName = nodeName;
		this.nodeUserMapSet = nodeUserMapSet;
	}
	
	
	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public Set<NodeUserMap> getNodeUserMapSet() {
		return nodeUserMapSet;
	}

	public void setNodeUserMapSet(Set<NodeUserMap> nodeUserMapSet) {
		this.nodeUserMapSet = nodeUserMapSet;
	}

}
