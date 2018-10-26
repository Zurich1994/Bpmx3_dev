package com.hotent.Markovchain.model.Markovchain;

/**
 * 存放流程中的节点之间的关系
 * @author 张静
 *
 */
public class SequenceFlow {
	/**
	 * id 节点关系主键
	 * sourceRef 源节点
	 * targetRef 目标节点
	 */
	private String id;
	private String sourceRef;
	private String targetRef;
	
	
	public SequenceFlow() {
		super();
	}
	public SequenceFlow(String id, String sourceRef, String targetRef) {
		super();
		this.id = id;
		this.sourceRef = sourceRef;
		this.targetRef = targetRef;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSourceRef() {
		return sourceRef;
	}
	public void setSourceRef(String sourceRef) {
		this.sourceRef = sourceRef;
	}
	public String getTargetRef() {
		return targetRef;
	}
	public void setTargetRef(String targetRef) {
		this.targetRef = targetRef;
	}
	
	
}
