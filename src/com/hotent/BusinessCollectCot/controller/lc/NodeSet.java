package com.hotent.BusinessCollectCot.controller.lc;

public class NodeSet {
	
	private String nodeId;
	private String nodeProbability;
	
	
	

	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	public String getNodeProbability() {
		return nodeProbability;
	}
	public void setNodeProbability(String nodeProbability) {
		this.nodeProbability = nodeProbability;
	}
	
	public String toString(){
		return " nodeId:"+nodeId+" nodeProbability:"+nodeProbability;
	}
}
