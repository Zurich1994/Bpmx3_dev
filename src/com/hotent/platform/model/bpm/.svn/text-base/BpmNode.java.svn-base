package com.hotent.platform.model.bpm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 流程节点定义
 * 
 * @author csx
 * 
 */
@XmlRootElement(name = "bpmNode")
@XmlAccessorType(XmlAccessType.NONE)
public class BpmNode {
	//节点Id
	@XmlAttribute
	private String nodeId;
	//节点名称
	@XmlAttribute
	private String nodeName;
	//节点类型
	@XmlAttribute
	private String nodeType;
	//是否为多实例任务
	@XmlAttribute
	private Boolean isMultiple=false;
	//跳至节点的成立条件
	@XmlAttribute
	private String condition="";

	public BpmNode() {

	}

	public BpmNode(String nodeId, String nodeName, String nodeType,Boolean isMultiple) {
		this.nodeId = nodeId;
		this.nodeName = nodeName;
		this.nodeType = nodeType;
		this.isMultiple=isMultiple;
	}


	public String getNodeId() {
		return nodeId;
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

	public Boolean getIsMultiple() {
		return isMultiple;
	}

	public void setIsMultiple(Boolean isMultiple) {
		this.isMultiple = isMultiple;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}
	
	

}
