package com.hotent.core.bpm.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 分支节点对象。<br>
 * <pre>
 * 1.是否多实例。
 * 2.前置节点。
 * 3.节点ID。
 * 4.后置节点列表。
 * </pre>
 * @author ray
 *
 */
public class ForkNode {
	
	/**
	 * 是否会多实例节点
	 */
	private boolean isMulti=false;
	/**
	 * 节点ID
	 */
	private String forkNodeId="";
	/**
	 * 前一节点ID
	 */
	private String preNodeId="";
	
	/**
	 * 后置节点列表。
	 */
	private List<NodeCondition> list=new ArrayList<NodeCondition>();
	
	public String getPreNodeId() {
		return preNodeId;
	}

	public void setPreNodeId(String preNodeId) {
		this.preNodeId = preNodeId;
	}

	
	
	public void addNode(NodeCondition condition){
		this.list.add(condition);
	}
	
	public boolean getIsMulti() {
		return isMulti;
	}

	public void setMulti(boolean isMulti) {
		this.isMulti = isMulti;
	}

	public String getForkNodeId() {
		return forkNodeId;
	}

	public void setForkNodeId(String forkNodeId) {
		this.forkNodeId = forkNodeId;
	}

	public List<NodeCondition> getList() {
		return list;
	}

	public void setList(List<NodeCondition> list) {
		this.list = list;
	}

}
