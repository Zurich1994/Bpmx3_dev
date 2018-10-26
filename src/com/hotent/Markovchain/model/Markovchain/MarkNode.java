package com.hotent.Markovchain.model.Markovchain;

public class MarkNode {
	private String nodeId;
	private String lable;
	private String type;
	private String messgae;
	private String yellowcolor;
	private String animation;
	private int num;//节点个数
	/**
	 * @return the id
	 */
	public int getNum() {
		return num;
	}
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	public void setNum(int num) {
		this.num = num;
	}
	/**
	 * @param id the id to set
	 */
	
	/**
	 * @return the lable
	 */
	public String getLable() {
		return lable;
	}
	/**
	 * @param lable the lable to set
	 */
	public void setLable(String lable) {
		this.lable = lable;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the messgae
	 */
	public String getMessgae() {
		return messgae;
	}
	/**
	 * @param messgae the messgae to set
	 */
	public void setMessgae(String messgae) {
		this.messgae = messgae;
	}
	/**
	 * @return the yellowcolor
	 */
	public String getYellowcolor() {
		return yellowcolor;
	}
	/**
	 * @param yellowcolor the yellowcolor to set
	 */
	public void setYellowcolor(String yellowcolor) {
		this.yellowcolor = yellowcolor;
	}
	/**
	 * @return the animation
	 */
	public String getAnimation() {
		return animation;
	}
	/**
	 * @param animation the animation to set
	 */
	public void setAnimation(String animation) {
		this.animation = animation;
	}
	
}
