package com.hotent.platform.model.mobile;
/**
 * 手机流程图
 * @author zxh
 *
 */
public class MoblieImage {
	/**
	 * 是否成功
	 */
	private boolean success = true;
	
	public static short type_1 =1;
	
	public static short type_0 =0;
	/**
	 * 错误的消息
	 */
	private String msg = "";

	/**
	 * 流程定义的div节点结合。
	 */
	private String xml = "";

	/**
	 * 流程图的宽。
	 */
	private int width = 1000;

	/**
	 * 流程图的高度。
	 */
	private int height = 1000;
	
	/**
	 * 类型，0：表示流程流程图，有审批历史
	 * 1：表示流程
	 */
	private short type = 0;
	
	private String actDefId;
	/**
	 * 父类的节点
	 */
	private String superInstanceId;
	/**
	 * 流程实例ID
	 */
	private String processInstanceId;

	public MoblieImage() {
		super();
	}

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getSuperInstanceId() {
		return superInstanceId;
	}

	public void setSuperInstanceId(String superInstanceId) {
		this.superInstanceId = superInstanceId;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	/**
	 * @return the type
	 */
	public short getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(short type) {
		this.type = type;
	}

	/**
	 * @return the actDefId
	 */
	public String getActDefId() {
		return actDefId;
	}

	/**
	 * @param actDefId the actDefId to set
	 */
	public void setActDefId(String actDefId) {
		this.actDefId = actDefId;
	}
	
	
}
