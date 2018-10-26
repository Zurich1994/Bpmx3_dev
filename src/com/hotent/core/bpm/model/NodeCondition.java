package com.hotent.core.bpm.model;

/**
 * 条件节点类。<br>
 * 1.节点名称。
 * 2.节点ID。
 * 3.节点条件。
 * @author ray
 *
 */
public class NodeCondition {
	
	/**
	 * 流程节点名称
	 */
	private String nodeName="";
	
	/**
	 * 流程节点ID。
	 */
	private String nodeId="";
	/**
	 * 节点条件。
	 */
	private String condition="";
	
	public NodeCondition(String nodeName,String nodeId,String condition)
	{
		this.nodeName=nodeName;
		this.nodeId=nodeId;
		this.condition=condition;
	}
	
	/**
	 * 节点名称
	 * @return
	 */
	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	/**
	 * 节点ID 。
	 * @return
	 */
	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	/**
	 * 节点条件。
	 * @return
	 */
	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}
	
	

}
