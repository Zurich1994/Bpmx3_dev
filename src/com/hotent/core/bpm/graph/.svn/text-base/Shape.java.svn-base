package com.hotent.core.bpm.graph;

import java.util.List;

/**
 * 流程图中的图形对象，比如起始节点，人工任务等等。
 * 
 * @author hotent
 * 
 */
public class Shape {

	private float x = 0;
	private float y = 0;
	private float w = 0;
	private float h = 0;
	private String name="";
	
	private List<Port> ports;
	
	
	private DirEnum dir;
	// 偏移
	private float offset = 0;

//	private float fHOffset = 0;
//	private float fVOffset = 0;
	

	/**
	 * 构造图形元素。
	 * @param name
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public Shape(String name, float x, float y, float w, float h) {
		this.h = h;
		if (name.equals("bg:StartEvent") || name.equals("bg:EndEvent")) {
			this.h = w;
		}
		
		this.name=name;

		this.x = x;
		this.y = y;
		this.w = w;

	}

	public float getOffset() {
		return offset;
	}

	public void setOffset(float offset) {
		this.offset = offset;
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	/**
	 * x=1 线条向右 x=0 线条向左 y=0 线条向上 y=1 线条向下
	 * 
	 * @param dirx
	 * @param diry
	 */
//	public void setDirectory(String dirx, String diry) {
//		if (!dirx.equals("")) {
//			if (dirx.equals("0"))
//				dir = DirEnum.Left;
//			if (dirx.equals("1"))
//				dir = DirEnum.Right;
//		}
//		if (!diry.equals("")) {
//			if (diry.equals("0"))
//				dir = DirEnum.Top;
//			if (diry.equals("1"))
//				dir = DirEnum.Bottom;
//		}
//	}

	/**
	 * 取得方向
	 * 
	 * @return
	 */
	public DirEnum getDirectory() {
		return dir;
	}

	/**
	 * 设置水平方向和垂直方向的坐标偏移。
	 * @param hOffset
	 * @param vOffset
	 */
//	public void setHV(String hOffset, String vOffset) {
//		if (!hOffset.equals(""))
//			fHOffset = Float.parseFloat(hOffset);
//		if (!vOffset.equals(""))
//			fVOffset = Float.parseFloat(vOffset);
//	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getW() {
		return w;
	}

	public void setW(float w) {
		this.w = w;
	}

	public float getH() {
		return h;
	}

	public void setH(float h) {
		this.h = h;
	}
	
	public List<Port> getPorts() {
		return ports;
	}

	public void setPorts(List<Port> ports) {
		this.ports = ports;
	}

	/**
	 * 图形中部坐标点。
	 * @return
	 */
//	public Point getTopCenter() {
//		float tmpx = this.x + this.w / 2;
//		Point p = new Point(tmpx, this.y);
//
//		setHV(p);
//		return p;
//	}
	
	/**
	 * 图形左边坐标点。
	 * @return
	 */
//	public Point getLeftCenter() {
//		float tmpy = this.y + this.h / 2;
//		Point p = new Point(this.x, tmpy);
//		setHV(p);
//		return p;
//	}

	/**
	 * 图形底部中间坐标点。
	 * @return
	 */
//	public Point getBottomCenter() {
//		float tmpx = this.x + this.w / 2;
//		float tmpy = this.y + h;
//		Point p = new Point(tmpx, tmpy);
//
//		setHV(p);
//		return p;
//	}

	/**
	 * 图形右边中间坐标点。
	 * @return
	 */
//	public Point getRightCenter() {
//		float tmpx = this.x + this.w;
//		float tmpy = this.y + h / 2;
//		Point p = new Point(tmpx, tmpy);
//
//		setHV(p);
//		return p;
//	}

	/**
	 * 根据方向和偏移获取连线的坐标点。
	 * @return
	 */
//	public Point getPoint() {
//		Point p;
//		if (dir == DirEnum.Top)
//			p = getTopCenter();
//		else if (dir == DirEnum.Bottom)
//			p = getBottomCenter();
//		else if (dir == DirEnum.Left)
//			p = getLeftCenter();
//		else
//			p = getRightCenter();
//
//		return p;
//	}

	/**
	 * 设置水平和垂直方向的偏移。
	 * @param p
	 */
//	private void setHV(Point p) {
//		p.setX(p.getX() + fHOffset);
//		p.setY(p.getY() + fVOffset);
//	}
//	
	public float getCenterX(){
		return (x+w)/2;
	}
	public float getCenterY(){
		return (y+h)/2;
	}
	public float getBottomRightX(){
		return x+w;
	}
	public float getBottomRightY(){
		return y+h;
	}
}
