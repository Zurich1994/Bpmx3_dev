package com.hotent.core.bpm.graph;

/**
 * 流程图形坐标点。
 * @author sf
 *
 */
public class Point {
	private float x=0;
	private float y=0;
	
	public Point(float x,float y)
	{
		this.x=x;
		this.y=y;
	}
	
	public float getX() {
		return x;
	}
	/**
	 * x坐标。
	 * @param x
	 */
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	/**
	 * y坐标。
	 * @param y
	 */
	public void setY(float y) {
		this.y = y;
	}
}
