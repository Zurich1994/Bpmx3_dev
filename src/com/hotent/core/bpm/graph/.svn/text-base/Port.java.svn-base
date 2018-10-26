package com.hotent.core.bpm.graph;

import com.hotent.core.bpm.graph.PortType;

/**
 * 对象功能:程序设计器 表示节点与连接之间的端口
 * 开发公司:广州宏天软件有限公司 
 * 开发人员:raise
 * 创建时间:2013-02-01 08:50:46
 */
public class Port {
	//端口类型
	private PortType portType;
	//端口在节点中的x坐标，(x,y)=(0,0)表左上角，(x,y)=(1,1)表右下角
	private double x;
	//端口在节点中的y坐标
	private double y;
	//垂直位移
	private double verticalOffset;
	//垂直位移
	private double horizontalOffset;
	private String nodePartReference;
	private boolean clipOnShape;

	public Port() {
	}
	
	public Port(PortType portType,double x,double y,double horizontalOffset, double verticalOffset,String nodePartReference,boolean clipOnShape){
		this.portType=portType;
		this.x=x;
		this.y=y;
		this.verticalOffset=verticalOffset;
		this.horizontalOffset=horizontalOffset;
		this.nodePartReference=nodePartReference;
		this.clipOnShape=clipOnShape;
	}
	
	public PortType getPortType() {
		return portType;
	}

	public void setPortType(PortType portType) {
		this.portType = portType;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getVerticalOffset() {
		return verticalOffset;
	}

	public void setVerticalOffset(double verticalOffset) {
		this.verticalOffset = verticalOffset;
	}

	public double getHorizontalOffset() {
		return horizontalOffset;
	}

	public void setHorizontalOffset(double horizontalOffset) {
		this.horizontalOffset = horizontalOffset;
	}

	public String getNodePartReference() {
		return nodePartReference;
	}

	public void setNodePartReference(String nodePartReference) {
		this.nodePartReference = nodePartReference;
	}

	public boolean isClipOnShape() {
		return clipOnShape;
	}

	public void setClipOnShape(boolean clipOnShape) {
		this.clipOnShape = clipOnShape;
	}

}
