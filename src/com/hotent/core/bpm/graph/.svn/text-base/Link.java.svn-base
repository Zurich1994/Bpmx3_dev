package com.hotent.core.bpm.graph;

import java.util.List;

import com.hotent.core.bpm.graph.Point;
import com.hotent.core.bpm.graph.Port;
import com.hotent.core.bpm.graph.Shape;
import com.hotent.core.bpm.graph.ShapeType;

/**
 * 对象功能:程序设计器 连接 实体类，表示两个节点之间的连接
 * 开发公司:广州宏天软件有限公司 
 * 开发人员:raise
 * 创建时间:2013-02-01 08:50:46
 */
public class Link {
	
	//	 连接类型
	//	 "straight". Defines a straight link made of one line segment
	//	 "free". Defines a polyline line. The points defining the polyline can be specified through the intermediatePoints property.
	//	 "orthogonal". Defines a link that automatically computes its shape made of orthogonal segments.
	//	 "oblique". Defines a link that automatically computes its shape with straight segments at start and end of the link and an oblique segment in the middle
	private ShapeType shapeType;
	//开始节点
	private Shape startNode;
	//结束节点
	private Shape endNode;
	//开始端口
	private Port startPort;
	//结束端口
	private Port endPort;
	
	private Point fallbackStartPoint;
	
	private Point fallbackEndPoint;
	//中间点
	private List<Point> intermediatePoints;
	
	public ShapeType getShapeType() {
		return shapeType;
	}
	public void setShapeType(ShapeType shapeType) {
		this.shapeType = shapeType;
	}
	public Shape getStartNode() {
		return startNode;
	}
	public void setStartNode(Shape startNode) {
		this.startNode = startNode;
	}
	public Shape getEndNode() {
		return endNode;
	}
	public void setEndNode(Shape endNode) {
		this.endNode = endNode;
	}
	public Port getStartPort() {
		return startPort;
	}
	public void setStartPort(Port startPort) {
		this.startPort = startPort;
	}
	public Port getEndPort() {
		return endPort;
	}
	public void setEndPort(Port endPort) {
		this.endPort = endPort;
	}
	public Point getFallbackStartPoint() {
		return fallbackStartPoint;
	}
	public void setFallbackStartPoint(Point fallbackStartPoint) {
		this.fallbackStartPoint = fallbackStartPoint;
	}
	public Point getFallbackEndPoint() {
		return fallbackEndPoint;
	}
	public void setFallbackEndPoint(Point fallbackEndPoint) {
		this.fallbackEndPoint = fallbackEndPoint;
	}
	public List<Point> getIntermediatePoints() {
		return intermediatePoints;
	}
	public void setIntermediatePoints(List<Point> intermediatePoints) {
		this.intermediatePoints = intermediatePoints;
	}
}
