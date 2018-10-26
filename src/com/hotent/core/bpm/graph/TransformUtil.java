package com.hotent.core.bpm.graph;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hotent.core.bpm.graph.Link;
import com.hotent.core.bpm.graph.OrthogonalType;
import com.hotent.core.bpm.graph.Point;
import com.hotent.core.bpm.graph.Port;
import com.hotent.core.bpm.graph.PortType;
import com.hotent.core.bpm.graph.Shape;
import com.hotent.core.bpm.graph.ShapeType;
import com.hotent.core.util.StringUtil;

/**
 * 流程坐标转换计算类。
 * 
 * @author sf
 * 
 */
/**
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */
public class TransformUtil {

	public static int Offset = 24;

	public static int minLen = 4;
	
	protected static Logger logger = LoggerFactory.getLogger(TransformUtil.class);

	/**
	 * 计算表接连的坐标点
	 * @param link 连接
	 * @return 接连的坐标点列表
	 */
	private static List<Point> calcLinkPoints(Link link) {
		List<Point> points = new ArrayList<Point>();
		switch (link.getShapeType()) {
		case STRAIGHT:
			points =  calcStraightLinkPoints(link);
			break;
		case FREE:
			points =  calcFreeLinkPoints(link);
			break;
		case OBLIQUE:
			points =  calcOrthogonalLinkPoints(link);
			break;
		case ORTHOGONAL:
			points =  calcOrthogonalLinkPoints(link);
			break;
		default:
			points =  calcOrthogonalLinkPoints(link);
		}
		return points;
	}
	
	/**
	 * 计算表接连类型为com.hotent.core.bpm.graph.ShapeType.ORTHOGONAL的连接的坐标点
	 * @param link 连接
	 * @return 接连的坐标点列表
	 */
	private static List<Point> calcOrthogonalLinkPoints(Link link){
		List<Point> points = new ArrayList<Point>();
		Shape startNode=link.getStartNode();
		Shape endNode=link.getEndNode();
		Port startPort = link.getStartPort();
		Port endPort = link.getEndPort();
		OrthogonalType type = calcOrthogonalLinkRelativPosition(startNode, endNode, startPort, endPort);
		
		switch (type) {
		case TopTop:
			logger.info("Top Top");
			points = caculateTopTop(startNode, endNode ,startPort, endPort);
			break;
		case TopLeft:
			logger.info("Top Left");
			points = caculateTopLeft(startNode, endNode,startPort, endPort);
			break;
		case TopRight:
			logger.info("Top Right");
			points = caculateTopRight(startNode, endNode,startPort,endPort);
			break;
		case TopBottom:
			logger.info("Top Bottom");
			points = caculateTopBottom(startNode, endNode,startPort, endPort);
			break;
		case LeftTop:
			logger.info(" Left Top ");
			points = caculateLeftTop(startNode, endNode,startPort, endPort);
			break;
		case LeftLeft:
			logger.info(" Left Left ");
			points = caculateLeftLeft(startNode, endNode,startPort, endPort);
			break;
		case LeftRight:
			logger.info(" Left Right ");
			points = caculateLeftRight(startNode, endNode,startPort, endPort);
			break;
		case LeftBottom:
			logger.info(" Left Bottom ");
			points = caculateLeftBottom(startNode, endNode,startPort, endPort);
			break;
		case RightTop:
			logger.info(" Right Top ");
			points = caculateRightTop(startNode, endNode,startPort, endPort);
			break;
		case RightLeft:
			logger.info(" Right Left ");
			points = caculateRightLeft(startNode, endNode,startPort, endPort);
			break;
		case RightRight:
			logger.info(" Right Right ");
			points = caculateRightRight(startNode, endNode,startPort, endPort);
			break;
		case RightBottom:
			logger.info(" Right Bottom ");
			points = caculateRightBottom(startNode, endNode,startPort, endPort);
			break;
		case BottomTop:
			logger.info(" Bottom Top ");
			points = caculateBottomTop(startNode, endNode,startPort, endPort);
			break;
		case BottomLeft:
			logger.info(" Bottom Left ");
			points = caculateBottomLeft(startNode, endNode,startPort, endPort);
			break;
		case BottomRight:
			logger.info(" Bottom Right ");
			points = caculateBottomRight(startNode, endNode,startPort, endPort);
			break;
		case BottomBottom:
			logger.info(" Bottom Bottom ");
			points = caculateBottomBottom(startNode, endNode,startPort, endPort);
			break;
		}
		return points;
	}
	
	/**
	 * 计算表接连类型为com.hotent.core.bpm.graph.ShapeType.ORTHOGONAL的连接的正交连接类型
	 * @param source 开始节点
	 * @param target 结束节点
	 * @param startPort 开始端口
	 * @param endPort 结束端口
	 * @return 正交类型
	 */
	private static OrthogonalType calcOrthogonalLinkRelativPosition(Shape source,Shape target,Port startPort,Port endPort){
		double startx =source.getX()+ source.getW()*startPort.getX()+startPort.getHorizontalOffset();
		double starty = source.getY()+source.getH()*startPort.getY()+startPort.getVerticalOffset();
		Point start = new Point((float)startx, (float)starty);
		
		double endx = target.getX()+target.getW()*endPort.getX()+endPort.getHorizontalOffset();
		double endy = target.getY()+target.getH()*endPort.getY()+endPort.getVerticalOffset();
		Point end = new Point((float)endx, (float)endy);
		
		Point splt = new Point(source.getX(), source.getY());
		Point sprt = new Point(source.getX()+source.getW(), source.getY());
		Point sprb = new Point(source.getX()+source.getW(), source.getY()+source.getH());
		Point splb = new Point(source.getX(), source.getY()+source.getH());
		Point spc = new Point(source.getX()+source.getW()/2, source.getY()+source.getH()/2);
		
		Point tplt = new Point(target.getX(), target.getY());
		Point tprt = new Point(target.getX()+target.getW(), target.getY());
		Point tprb = new Point(target.getX()+target.getW(), target.getY()+target.getH());
		Point tplb = new Point(target.getX(), target.getY()+target.getH());
		Point tpc = new Point(target.getX()+target.getW()/2, target.getY()+target.getH()/2);
		
		if(isInsideTriangle(splt,sprt,spc,start)){
			if(isInsideTriangle(tplt,tprt,tpc,end)){
				return OrthogonalType.TopTop;
			}else if(isInsideTriangle(tprt,tprb,tpc,end)){
				return OrthogonalType.TopRight;
			}else if(isInsideTriangle(tprb,tplb,tpc,end)){
				return OrthogonalType.TopBottom;
			}else if(isInsideTriangle(tplb,tplt,tpc,end)){
				return OrthogonalType.TopLeft;
			}
			
		}else if(isInsideTriangle(sprt,sprb,spc,start)){
			if(isInsideTriangle(tplt,tprt,tpc,end)){
				return OrthogonalType.RightTop;
			}else if(isInsideTriangle(tprt,tprb,tpc,end)){
				return OrthogonalType.RightRight;
			}else if(isInsideTriangle(tprb,tplb,tpc,end)){
				return OrthogonalType.RightBottom;
			}else if(isInsideTriangle(tplb,tplt,tpc,end)){
				return OrthogonalType.RightLeft;
			}
		}else if(isInsideTriangle(sprb,splb,spc,start)){
			if(isInsideTriangle(tplt,tprt,tpc,end)){
				return OrthogonalType.BottomTop;
			}else if(isInsideTriangle(tprt,tprb,tpc,end)){
				return OrthogonalType.BottomRight;
			}else if(isInsideTriangle(tprb,tplb,tpc,end)){
				return OrthogonalType.BottomBottom;
			}else if(isInsideTriangle(tplb,tplt,tpc,end)){
				return OrthogonalType.BottomLeft;
			}
		}else if(isInsideTriangle(splb,splt,spc,start)){
			if(isInsideTriangle(tplt,tprt,tpc,end)){
				return OrthogonalType.LeftTop;
			}else if(isInsideTriangle(tprt,tprb,tpc,end)){
				return OrthogonalType.LeftRight;
			}else if(isInsideTriangle(tprb,tplb,tpc,end)){
				return OrthogonalType.LeftBottom;
			}else if(isInsideTriangle(tplb,tplt,tpc,end)){
				return OrthogonalType.LeftLeft;
			}
		}
		return OrthogonalType.RightLeft;
	}
	
	/**
	 * 判断一点是否在三角形内，A,B,C 表示三角形，P表示要判断的点
	 * @param A
	 * @param B
	 * @param C
	 * @param P
	 * @return true 表示点在三角形内，false表示不在三角形内
	 */
	private static boolean isInsideTriangle(Point A, Point B, Point C, Point P) {
		float ab = (A.getX() - P.getX()) * (B.getY() - P.getY()) - (B.getX() - P.getX()) * (A.getY() - P.getY());
		float bc = (B.getX() - P.getX()) * (C.getY() - P.getY()) - (C.getX() - P.getX()) * (B.getY() - P.getY());
		float ca = (C.getX() - P.getX()) * (A.getY() - P.getY()) - (A.getX() - P.getX()) * (C.getY() - P.getY());
		// return sign(ab)==sign(planeBC) && sign(planeBC)==sign(planeCA);
		return ((ab >= 0 && bc >= 0) || (ab <= 0 && bc <= 0)) && ((bc >= 0 && ca >= 0) || (bc <= 0 && ca <= 0));
	}
	
	
	
	/**
	 * 计算表接连类型为com.hotent.core.bpm.graph.ShapeType.STRAIGHT的连接的坐标点
	 * @param link 连接
	 * @return 接连的坐标点列表
	 */
	private static List<Point> calcStraightLinkPoints(Link link) {
		link.getIntermediatePoints().clear();
		return calcFreeLinkPoints(link);
	}
	/**
	 * 计算表接连类型为com.hotent.core.bpm.graph.ShapeType.FREE的连接的坐标点
	 * @param link 连接
	 * @return 接连的坐标点列表
	 */
	private static List<Point> calcFreeLinkPoints(Link link){
		List<Point> points = new ArrayList<Point>();
		Shape startNode=link.getStartNode();
		Shape endNode=link.getEndNode();
		Port startPort = link.getStartPort();
		Port endPort = link.getEndPort();
		List<Point> intermediatePoints = link.getIntermediatePoints();
		
		double firstX = startNode.getX() + startNode.getW()*startPort.getX()+startPort.getHorizontalOffset();
		double firstY = startNode.getY() + startNode.getH()*startPort.getY()+startPort.getVerticalOffset();
		Point first = new Point((float)firstX, (float)firstY);
		double lastX = endNode.getX() + endNode.getW()*endPort.getX()+endPort.getHorizontalOffset();
		double lastY = endNode.getY() + endNode.getH()*endPort.getY()+endPort.getVerticalOffset();
		Point last = new Point((float)lastX, (float)lastY);
		
		Point temp1=intermediatePoints.size()>0?intermediatePoints.get(0):last;
		Point temp2=intermediatePoints.size()>0?intermediatePoints.get(intermediatePoints.size()-1):first;
		Point temp3=null,temp4=null;
		temp3 = handleOverlap(startNode,first,temp1);
		temp4 = handleOverlap(endNode,last,temp2);
		points.add(temp3==null?first:temp3);
		points.addAll(intermediatePoints);
		points.add(temp4==null?last:temp4);
		
		return points;
	}
	
	
	/**
	 * 处理节点覆盖了连接的情况。
	 * @param shape
	 * @param p1
	 * @param p2
	 * @return
	 */
	private static Point handleOverlap(Shape shape,Point p1,Point p2){
		
		Point a=new Point(shape.getX(), shape.getY());
		Point b=new Point(shape.getX()+shape.getW(), shape.getY());
		Point c=new Point(shape.getX()+shape.getW(), shape.getY()+shape.getH());
		Point d=new Point(shape.getX(), shape.getY()+shape.getH());
		
//		if (!isInsideLine(a, b, p1)) {
//			Point cp = getCrosspoint(p1, p2, a, b);
//			if(cp!=null && !isInsideLine(a, b, cp)){
//				return cp;
//			}
//		}
//		if (!isInsideLine(b, c, p1)) {
//			Point cp  = getCrosspoint(p1, p2, b, c);
//			if(cp!=null && !isInsideLine(b, c, cp)){
//				return cp;
//			}
//		}
//		if (!isInsideLine(c, d, p1)) {
//			Point cp  = getCrosspoint(p1, p2, c, d);
//			if(cp!=null && !isInsideLine(c, d, cp)){
//				return cp;
//			}
//		}
//		if (!isInsideLine(d, a, p1)) {
//			Point cp  = getCrosspoint(p1, p2, d, a);
//			if(cp!=null && !isInsideLine(d, a, cp)){
//				return cp;
//			}
//		}
//		
		
		
		Point[] cps=new Point[4];
		
//		cps[0] = getCrosspoint(p1, p2, a, b);
//		cps[1] = getCrosspoint(p1, p2, b, c);
//		cps[2] = getCrosspoint(p1, p2, c, d);
//		cps[3] = getCrosspoint(p1, p2, d, a);
//		
		if (!isInsideLine(a, b, p1)) {
			cps[0] = getCrosspoint(p1, p2, a, b);
		}
		if (!isInsideLine(b, c, p1)) {
			cps[1] = getCrosspoint(p1, p2, b, c);
		}
		if (!isInsideLine(c, d, p1)) {
			cps[2] = getCrosspoint(p1, p2, c, d);
		}
		if (!isInsideLine(d, a, p1)) {
			cps[3] = getCrosspoint(p1, p2, d, a);
		}
		for(Point p:cps){
			if(p!=null){
				return p;
			}
		}
//		for(Point p:cps){
//			if(p==null
//					||(Math.abs(p.getX()-p1.getX())<0.05 && Math.abs(p.getY()-p1.getY())<0.05)
//					||(Math.abs(p.getX()-p2.getX())<0.05 && Math.abs(p.getY()-p2.getY())<0.05)){
//				continue;
//			}else{
//				return p;
//			}
//		}
//		for(Point p:cps){
//			if(p!=null){
//				if(!(isInsideLine(a, b, p1)&&isInsideLine(a, b, p))){
//					return p;
//				}
//				if(!(isInsideLine(b, c, p1)&&isInsideLine(b, c, p))){
//					return p;
//				}
//				if(!(isInsideLine(c, d, p1)&&isInsideLine(c, d, p))){
//					return p;
//				}
//				if(!(isInsideLine(d, a, p1)&&isInsideLine(d, a, p))){
//					return p;
//				}
//			}
//		}
		return null;
	}
	
	/**
	 * 判断一点是否在直线上
	 * @param p1
	 * @param p2
	 * @param p
	 * @return
	 */
	private static boolean isInsideLine(Point p1,Point p2,Point p){
		double  x=p.getX(),
				y = p.getY(),
				x1 = p1.getX(),
				y1 = p1.getY(),
				x2 = p2.getX(),
				y2 = p2.getY();
		if(Double.compare(x1, x2)==0){//垂直
			if(Double.compare(x, x2)==0){
				return true;
			}else{
				return false;
			}
		}
		if(Double.compare(y1, y2)==0){//水平
			if(Double.compare(y, y2)==0){
				return true;
			}else{
				return false;
			}
		}
		double s1 = x - x1, t1 = y - y1; 
		double s2 = x1 - x2, t2 = y1 - y2;
		return Double.compare(s1/s2, t1/t2)==0;
	}
	
	
	/**
	 * 计算两条线段的交点
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 * @return
	 */
	private static Point getCrosspoint(Point a, Point b, Point c, Point d) {
		float ax = a.getX();
		float ay = a.getY();
		float bx = b.getX();
		float by = b.getY();
		float cx = c.getX();
		float cy = c.getY();
		float dx = d.getX();
		float dy = d.getY();

		if (Math.abs(by - ay) + Math.abs(bx - ax) + Math.abs(dy - cy) + Math.abs(dx - cx) == 0) {
			if ((cx - ax) + (cy - ay) == 0) {
				// ABCD是同一个点！
				return new Point(ax, ay);
			} else {
				// AB是一个点，CD是一个点，且AC不同
				return null;
			}
		} else if (Math.abs(by - ay) + Math.abs(bx - ax) == 0) {
			if ((ax - dx) * (cy - dy) - (ay - dy) * (cx - dx) == 0) {
				// A、B是一个点，且在CD线段上
				return new Point(ax, ay);
			} else {
				// A、B是一个点，且不在CD线段上
				return null;
			}
		} else if (Math.abs(dy - cy) + Math.abs(dx - cx) == 0) {
			if ((dx - bx) * (ay - by) - (dy - by) * (ax - bx) == 0) {
				// C、D是一个点，且在AB线段上
				return new Point(cx, cy);
			} else {
				// C、D是一个点，且不在AB线段上
				return null;
			}
		} else if ((by - ay) * (cx - dx) - (bx - ax) * (cy - dy) == 0) {
			 //线段平行，无交点
			return null;
		}

		float x = ((bx - ax) * (cx - dx) * (cy - ay) - cx * (bx - ax) * (cy - dy) + ax * (by - ay) * (cx - dx))
				/ ((by - ay) * (cx - dx) - (bx - ax) * (cy - dy));
		float y = ((by - ay) * (cy - dy) * (cx - ax) - cy * (by - ay) * (cx - dx) + ay * (bx - ax) * (cy - dy))
				/ ((bx - ax) * (cy - dy) - (by - ay) * (cx - dx));

		if ((x - ax) * (x - bx) <= 0 && (x - cx) * (x - dx) <= 0 && (y - ay) * (y - by) <= 0 && (y - cy) * (y - dy) <= 0) {
			// 线段相交
			return new Point(x, y);
		} else {
			// 线段不相交
			return null;
		}
	}

	/**
	 * 添加函数
	 * 
	 * @param para1
	 * @param para2
	 * @return
	 */
	public static String add(String para1, String para2) {
		double d1 = 0;
		double d2 = 0;
		if (StringUtil.isNotEmpty(para1)) {
			d1 = Double.parseDouble(para1);
		}
		if (StringUtil.isNotEmpty(para2)) {
			d2 = Double.parseDouble(para2);
		}
		d1 = d1 + d2;

		return String.valueOf(d1);
	}

	public static String bold(String str) {
		return "<b>" + str + "</b>";
	}

	/**
	 * 用于将""或"NaN"转为数值0。
	 * 
	 * @param str
	 * @return
	 */
	public static double nan2Zero(String str) {
		if (StringUtil.isEmpty(str) || str.equalsIgnoreCase("NaN")) {
			return 0;
		} else {
			return Double.parseDouble(str);
		}
	}

	/**
	 * 添加函数
	 * 
	 * @param para1
	 * @param para2
	 * @return
	 */
	// public static String add(String para1, double para2) {
	// double d1 = 0;
	//
	// if (StringUtil.isNotEmpty(para1)) {
	// d1 = Double.parseDouble(para1);
	// }
	//
	// d1 = d1 + para2;
	//
	// return String.valueOf(d1);
	// }

	public static void display(String str) {
		logger.info(str);
	}

	public static int splitLength(String str, String regex) {
		int len = str.split(regex).length;
		return len;
	}

	public static String accumulate(String childrenY, String parentY) {
		double retVal = 0;
		if (!StringUtil.isEmpty(childrenY)) {
			String[] childrenY_ = childrenY.split(",");
			for (String y : childrenY_) {
//				retVal += Double.parseDouble(y);
				retVal += nan2Zero(y);
			}
		}
		if (StringUtil.isNotEmpty(parentY)) {
			retVal += nan2Zero(parentY);
		}
		return String.valueOf(retVal);
	}

	public static String min(String para1, String para2) {

		double d1 = 0;
		double d2 = 0;
		if (StringUtil.isNotEmpty(para1)) {
			d1 = Double.parseDouble(para1);
		}
		if (StringUtil.isNotEmpty(para2)) {
			d2 = Double.parseDouble(para2);
		}
		if (d1 < d2)
			return String.valueOf(d1);
		else
			return String.valueOf(d2);
	}
	
	
	public static String max(String para1, String para2) {

		Integer d1 = 0;
		Integer d2 = 0;
		if (StringUtil.isNotEmpty(para1)) {
			d1 = Integer.parseInt(para1);
		}
		if (StringUtil.isNotEmpty(para2)) {
			d2 = Integer.parseInt(para2);
		}
		if (d1 > d2)
			return String.valueOf(d1);
		else
			return String.valueOf(d2);
	}

	/**
	 * x=1 线条向右 x=0 线条向左 y=0 线条向上 y=1 线条向下 horizontalOffset 为负数向左偏，正数向右偏
	 * verticalOffset 为负数向上偏移，正数向下偏移
	 * 
	 * @param fX
	 * @param fY
	 * @param fW
	 * @param fH
	 * @param fHOffset
	 * @param fVOffset
	 * @param fDirX
	 * @param fDirY
	 * @param tX
	 * @param tY
	 * @param tW
	 * @param tH
	 * @param tHOffset
	 * @param tVOffset
	 * @param tDirX
	 * @param tDirY
	 * @return
	 */
//	public static String calc(String fName, String fX, String fY, String fW, String fH, String fHOffset, String fVOffset, String fDirX, String fDirY,
//			String tName, String tX, String tY, String tW, String tH, String tHOffset, String tVOffset, String tDirX, String tDirY) {
//		float fromX = (float) nan2Zero(fX);
//		float fromY = (float) nan2Zero(fY);
//		float fromWidth = (float) nan2Zero(fW);
//		float fromHeight = (float) nan2Zero(fH);
//
//		float toX = (float) nan2Zero(tX);
//		float toY = (float) nan2Zero(tY);
//		float toWidth = (float) nan2Zero(tW);
//		float toHeight = (float) nan2Zero(tH);
//
//		Shape fromShape = new Shape(fName, fromX, fromY, fromWidth, fromHeight);
//		fromShape.setDirectory(fDirX, fDirY);
//		fromShape.setHV(fHOffset, fVOffset);
//
//		Shape toShape = new Shape(tName, toX, toY, toWidth, toHeight);
//		toShape.setDirectory(tDirX, tDirY);
//		toShape.setHV(tHOffset, tVOffset);
//
//		String str = caculate(fromShape, toShape);
//		return str;
//	}


	/**
	 * 计算连接的BPM XML 的坐标表示
	 * @param shapeType
	 * @param fName
	 * @param fX
	 * @param fY
	 * @param fW
	 * @param fH
	 * @param sPortType
	 * @param sPortX
	 * @param sPortY
	 * @param sPortHOffset
	 * @param sPortVOffset
	 * @param tName
	 * @param tX
	 * @param tY
	 * @param tW
	 * @param tH
	 * @param tPortType
	 * @param tPortX
	 * @param tPortY
	 * @param tPortHOffset
	 * @param tPortVOffset
	 * @param points
	 * @return
	 */
	public static String calc(String shapeType, 
			String fName, String fX, String fY, String fW, String fH,
			String sPortType,String sPortX, String sPortY,String sPortHOffset, String sPortVOffset, 
			String tName, String tX, String tY, String tW, String tH,
			String tPortType,String tPortX, String tPortY,String tPortHOffset, String tPortVOffset, 
			String points) {
		//默认值处理
		sPortX=("".equals(sPortX)||sPortX==null)?"0.5":sPortX;
		sPortY=("".equals(sPortY)||sPortY==null)?"0.5":sPortY;
		tPortX=("".equals(tPortX)||tPortX==null)?"0.5":tPortX;
		tPortY=("".equals(tPortY)||tPortY==null)?"0.5":tPortY;
		
		Link link = new Link();
		
		if(ShapeType.FREE.getText().equalsIgnoreCase(shapeType)){
			link.setShapeType(ShapeType.FREE);
		}else if(ShapeType.STRAIGHT.getText().equalsIgnoreCase(shapeType)){
			link.setShapeType(ShapeType.STRAIGHT);
		}else if(ShapeType.OBLIQUE.getText().equalsIgnoreCase(shapeType)){
			link.setShapeType(ShapeType.OBLIQUE);
		}else{
			link.setShapeType(ShapeType.ORTHOGONAL);
		}
		
		
		float startX = (float) nan2Zero(fX);
		float startY = (float) nan2Zero(fY);
		float startW = (float) nan2Zero(fW);
		float startH = (float) nan2Zero(fH);
		Shape startShape = new Shape(fName, startX, startY, startW, startH);
		float endX = (float) nan2Zero(tX);
		float endY = (float) nan2Zero(tY);
		float endW = (float) nan2Zero(tW);
		float endH = (float) nan2Zero(tH);
		Shape endShape = new Shape(tName, endX, endY, endW, endH);
		
		PortType startPortType;
		if(PortType.NODE_PART_REFERENCE.getText().equalsIgnoreCase(sPortType)){
			startPortType= PortType.NODE_PART_REFERENCE;
		}else if(PortType.AUTOMATIC_SIDE.getText().equalsIgnoreCase(sPortType)){
			startPortType= PortType.AUTOMATIC_SIDE;
		}else{
			startPortType= PortType.POSITION;
		}
		float startPortX = (float) nan2Zero(sPortX);
		float startPortY = (float) nan2Zero(sPortY);
		float startPortVOffset = (float) nan2Zero(sPortVOffset);
		float startPortHOffset = (float) nan2Zero(sPortHOffset);
		Port startPort = new Port(startPortType, startPortX,startPortY,startPortHOffset, startPortVOffset, null, false);
		
		PortType endPortType;
		if(PortType.NODE_PART_REFERENCE.getText().equalsIgnoreCase(tPortType)){
			endPortType= PortType.NODE_PART_REFERENCE;
		}else if(PortType.AUTOMATIC_SIDE.getText().equalsIgnoreCase(tPortType)){
			endPortType= PortType.AUTOMATIC_SIDE;
		}else{
			endPortType= PortType.POSITION;
		}
		float endPortX = (float) nan2Zero(tPortX);
		float endPortY = (float) nan2Zero(tPortY);
		float endPortVOffset = (float) nan2Zero(tPortVOffset);
		float endPortHOffset = (float) nan2Zero(tPortHOffset);
		Port endPort = new Port(endPortType, endPortX,endPortY,endPortHOffset, endPortVOffset, null, false);
		
		List<Point> intermediatePoints = new ArrayList<Point>();
		if (points!=null && !"".equals(points) ) {
			String[] pointAry = points.split(",");
			for (String p : pointAry) {
				String[] point = p.split(":");
				double x = nan2Zero(point[0]);
				double y = nan2Zero(point[1]);
				intermediatePoints.add(new Point((float)x,(float) y));
			}
		}
		
		link.setStartNode(startShape);
		link.setEndNode(endShape);
		link.setStartPort(startPort);
		link.setEndPort(endPort);
		link.setIntermediatePoints(intermediatePoints);
		List<Point> linkPoints = calcLinkPoints(link);
		return getPointXml(linkPoints);
	}

	public static List<Point> caculatePoints(Shape fromShape, Shape toShape,Port fromPort, Port toPort) {
		ArrayList<Point> list = null;
		switch (fromShape.getDirectory()) {
		case Top:
			switch (toShape.getDirectory()) {
			case Top:
				logger.info("Top Top");
				list = caculateTopTop(fromShape, toShape,fromPort,toPort);
				break;
			case Left:
				logger.info("Top Left");
				list = caculateTopLeft(fromShape, toShape,fromPort,toPort);
				break;
			case Right:
				logger.info("Top Right");
				list = caculateTopRight(fromShape, toShape,fromPort,toPort);
				break;
			case Bottom:
				logger.info("Top Bottom");
				list = caculateTopBottom(fromShape, toShape,fromPort,toPort);
				break;
			}
			break;
		case Left:
			switch (toShape.getDirectory()) {
			case Top:
				logger.info(" Left Top ");
				list = caculateLeftTop(fromShape, toShape,fromPort,toPort);
				break;
			case Left:
				logger.info(" Left Left ");
				list = caculateLeftLeft(fromShape, toShape,fromPort,toPort);
				break;
			case Right:
				logger.info(" Left Right ");
				list = caculateLeftRight(fromShape, toShape,fromPort,toPort);
				break;
			case Bottom:
				logger.info(" Left Bottom ");
				list = caculateLeftBottom(fromShape, toShape,fromPort,toPort);
				break;
			}
			break;
		case Right:
			switch (toShape.getDirectory()) {
			case Top:
				logger.info(" Right Top ");
				list = caculateRightTop(fromShape, toShape,fromPort,toPort);
				break;
			case Left:
				logger.info(" Right Left ");
				list = caculateRightLeft(fromShape, toShape,fromPort,toPort);
				break;
			case Right:
				logger.info(" Right Right ");
				list = caculateRightRight(fromShape, toShape,fromPort,toPort);
				break;
			case Bottom:
				logger.info(" Right Bottom ");
				list = caculateRightBottom(fromShape, toShape,fromPort,toPort);
				break;
			}
			break;
		case Bottom:
			switch (toShape.getDirectory()) {
			case Top:
				logger.info(" Bottom Top ");
				list = caculateBottomTop(fromShape, toShape,fromPort,toPort);
				break;
			case Left:
				logger.info(" Bottom Left ");
				list = caculateBottomLeft(fromShape, toShape,fromPort,toPort);
				break;
			case Right:
				logger.info(" Bottom Right ");
				list = caculateBottomRight(fromShape, toShape,fromPort,toPort);
				break;
			case Bottom:
				logger.info(" Bottom Bottom ");
				list = caculateBottomBottom(fromShape, toShape,fromPort,toPort);
				break;
			}
			break;
		}
		return list;
	}

	/**
	 * 计算xml
	 * 
	 * @param fromShape
	 * @param toShape
	 * @return
	 */
	public static String caculate(Shape fromShape, Shape toShape) {
//		List<Point> list = caculatePoints(fromShape, toShape);
//		String xml = getPointXml(list);
//		return xml;
		return null;
	}

	/**
	 * TOP TOP 检查完成
	 * 
	 * @param fromShape
	 * @param toShape
	 * @return
	 */
	public static ArrayList<Point> caculateTopTop(Shape fromShape, Shape toShape,Port fromPort,Port toPort) {
		ArrayList<Point> list = new ArrayList<Point>();
		
		float fromX = (float) (fromShape.getX()+fromShape.getW()*fromPort.getX()+fromPort.getHorizontalOffset());
		float fromY = (float) (fromShape.getY()+fromShape.getH()*fromPort.getY()+fromPort.getVerticalOffset());
		float toX = (float) (toShape.getX()+toShape.getW()*toPort.getX()+toPort.getHorizontalOffset());
		float toY = (float) (toShape.getY()+toShape.getH()*toPort.getY()+toPort.getVerticalOffset());

		// 目标在源的 上方
		if (toShape.getBottomRightY()+minLen < fromShape.getY() - minLen) {//top
			// 左右边
			if (toShape.getX() - minLen > fromX  || toShape.getBottomRightX() + minLen < fromX) { //top left or top right
				float tmpy = toShape.getY() - Offset;
//				if (toShape.getY() < fromShape.getY()) {
//					tmpy = toShape.getY() - Offset;
//				} else {
//					tmpy = fromShape.getY() - Offset;
//				}
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(fromX, tmpy);
				Point p3 = new Point(toX, tmpy);
				Point p4 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
			}else {// top center
				float tmpx = 0;
				float tmpy = (fromShape.getY() + toShape.getBottomRightY()) / 2;
				if (toX <= fromX) {
					tmpx = toShape.getBottomRightX() + Offset;
				} else {
					tmpx = toShape.getX() - Offset;
				}
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(fromX, tmpy);
				Point p3 = new Point(tmpx, tmpy);
				Point p4 = new Point(tmpx, toY - Offset);
				Point p5 = new Point(toX, toY - Offset);
				Point p6 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
				list.add(p5);
				list.add(p6);
			}
		}else if(toShape.getY()-minLen<fromShape.getBottomRightY()+minLen){//center
			float tmpy = toShape.getY() - Offset;
			if (toShape.getY() < fromShape.getY()) {
				tmpy = toShape.getY() - Offset;
			} else {
				tmpy = fromShape.getY() - Offset;
			}
			Point p1 = new Point(fromX, fromY);
			Point p2 = new Point(fromX, tmpy);
			Point p3 = new Point(toX, tmpy);
			Point p4 = new Point(toX, toY);

			list.add(p1);
			list.add(p2);
			list.add(p3);
			list.add(p4);
			
		}else{
			// 左右边
			if (fromShape.getX()-minLen > toX   || fromShape.getBottomRightX() + minLen < toX) {//bottom right or bottom left
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(fromX, fromY - Offset);
				Point p3 = new Point(toX, fromY - Offset);
				Point p4 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
			} else { //bottom center
				float tmpy = (fromShape.getBottomRightY() + toShape.getY()) / 2;
				float tmpx = 0;
				if (fromX <= toX) {
					tmpx = fromShape.getBottomRightX() + Offset;
				} else {
					tmpx = fromShape.getX() - Offset;
				}
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(fromX, fromY - Offset);
				Point p3 = new Point(tmpx, fromY - Offset);
				Point p4 = new Point(tmpx, tmpy);
				Point p5 = new Point(toX, tmpy);
				Point p6 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
				list.add(p5);
				list.add(p6);
			}
		}
		return list;
	}

	/**
	 * top right 检查完成
	 * 
	 * @param fromShape
	 * @param toShape
	 * @return
	 */
	public static ArrayList<Point> caculateTopRight(Shape fromShape, Shape toShape,Port fromPort,Port toPort) {
		ArrayList<Point> list = new ArrayList<Point>();
		
		float fromX = (float) (fromShape.getX()+fromShape.getW()*fromPort.getX()+fromPort.getHorizontalOffset());
		float fromY = (float) (fromShape.getY()+fromShape.getH()*fromPort.getY()+fromPort.getVerticalOffset());
		float toX = (float) (toShape.getX()+toShape.getW()*toPort.getX()+toPort.getHorizontalOffset());
		float toY = (float) (toShape.getY()+toShape.getH()*toPort.getY()+toPort.getVerticalOffset());
		if (fromX >= toX-minLen) {
			// 在源节点中点的左边
			if (toY < fromY-minLen) {
				//上方
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(fromX, toY);
				Point p3 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
			} else {
				if (toX + minLen < fromShape.getX()-minLen) {
					//在源左边的左边
					float tmpx = (fromShape.getX() + toX) / 2;
					Point p1 = new Point(fromX, fromY);
					Point p2 = new Point(fromX, fromY - Offset);
					Point p3 = new Point(tmpx, fromY - Offset);
					Point p4 = new Point(tmpx, toY);
					Point p5 = new Point(toX, toY);

					list.add(p1);
					list.add(p2);
					list.add(p3);
					list.add(p4);
					list.add(p5);
				} else {
					//在源左边的右边
					float tmpy = 0;
					if (toShape.getY()<fromY) {
						tmpy = toShape.getY() - Offset;
					} else {
						tmpy = fromY - Offset;
					}
					float tmpx = fromShape.getBottomRightX() + Offset;

					Point p1 = new Point(fromX, fromY);
					Point p2 = new Point(fromX, tmpy);
					Point p3 = new Point(tmpx, tmpy);
					Point p4 = new Point(tmpx, toY);
					Point p5 = new Point(toX, toY);

					list.add(p1);
					list.add(p2);
					list.add(p3);
					list.add(p4);
					list.add(p5);
				}
			}
		} else {
			// 在源节点中点的右边
			if (toShape.getBottomRightY() + minLen < fromY-minLen) {
				//上方
				float tmpy = (fromY + toShape.getBottomRightY()) / 2;

				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(fromX, tmpy);
				Point p3 = new Point(toX + Offset, tmpy);
				Point p4 = new Point(toX + Offset, toY);
				Point p5 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
				list.add(p5);
			} else {
				//下方
				float tmpy = 0;
				float tmpx = 0;
				Point p1 = new Point(fromX, fromY);

				// 目标的x坐标在源对象的右边
				if (toX + Offset >= fromShape.getBottomRightX()+Offset) {
					tmpx = toX + Offset;
				} else {
					tmpx = fromShape.getBottomRightX()+Offset;
				}
				// 目标在源的上方
				if (toShape.getY() - Offset<= fromY -Offset) {
					tmpy = toShape.getY() - Offset;
				} else {
					tmpy = fromY - Offset;
				}
				Point p2 = new Point(fromX, tmpy);
				Point p3 = new Point(tmpx, tmpy);
				Point p4 = new Point(tmpx, toY);
				Point p5 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
				list.add(p5);
			}
		}
		return list;
	}

	/**
	 * top bottom 检查完成
	 * 
	 * @param fromShape
	 * @param toShape
	 * @return
	 */
	public static ArrayList<Point> caculateTopBottom(Shape fromShape, Shape toShape,Port fromPort,Port toPort) {
		ArrayList<Point> list = new ArrayList<Point>();
		
		float fromX = (float) (fromShape.getX()+fromShape.getW()*fromPort.getX()+fromPort.getHorizontalOffset());
		float fromY = (float) (fromShape.getY()+fromShape.getH()*fromPort.getY()+fromPort.getVerticalOffset());
		float toX = (float) (toShape.getX()+toShape.getW()*toPort.getX()+toPort.getHorizontalOffset());
		float toY = (float) (toShape.getY()+toShape.getH()*toPort.getY()+toPort.getVerticalOffset());

		if (fromX < toX) {
			//右边
			if (toY + minLen <= fromY - minLen) {
				//上边
				float tmpy = (fromY + toY) / 2;
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(fromX, tmpy);
				Point p3 = new Point(toX, tmpy);
				Point p4 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
			} else {
				//下面
				if (fromShape.getBottomRightX() + minLen < toShape.getX()-minLen) {
					//右边
					float tmpx = (toShape.getX() + fromShape.getBottomRightX()) / 2;
					Point p1 = new Point(fromX, fromY);
					Point p2 = new Point(fromX, fromY - Offset);
					Point p3 = new Point(tmpx, fromY - Offset);
					Point p4 = new Point(tmpx, toY + Offset);
					Point p5 = new Point(toX, toY + Offset);
					Point p6 = new Point(toX, toY);

					list.add(p1);
					list.add(p2);
					list.add(p3);
					list.add(p4);
					list.add(p5);
					list.add(p6);
				} else {
					//左边
					float tmpy;
					if (fromShape.getY() <= toShape.getY()) {
						//上方
						tmpy = fromShape.getY() -Offset;
					} else {
						//下方
						tmpy = toShape.getY() - Offset;
					}
					Point p1 = new Point(fromX, fromY);
					Point p2 = new Point(fromX, tmpy);
					Point p3 = new Point(toShape.getBottomRightX() + Offset, tmpy);
					Point p4 = new Point(toShape.getBottomRightX() + Offset, toY + Offset);
					Point p5 = new Point(toX, toY + Offset);
					Point p6 = new Point(toX, toY);

					list.add(p1);
					list.add(p2);
					list.add(p3);
					list.add(p4);
					list.add(p5);
					list.add(p6);
				}
			}
		} else if (fromX == toX) {
			//在同一直线上
			if (toY+minLen < fromY-minLen) {
				// 目标在源对象上方
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(toX, toY);
				list.add(p1);
				list.add(p2);
			} else{
				float tmpy;
				float tmpx;
				if (fromShape.getY() < toShape.getY()) {
					tmpy = fromShape.getY()-Offset; 
				} else {
					tmpy = toShape.getY()-Offset; 
				}
				
				if (fromShape.getX() < toShape.getX()) {
					tmpx = fromShape.getX()-Offset; 
				} else {
					tmpx = toShape.getX()-Offset; 
				}

				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(fromX, tmpy);
				Point p3 = new Point(tmpx, tmpy);
				Point p4 = new Point(tmpx, toY + Offset);
				Point p5 = new Point(toX, toY + Offset);
				Point p6 = new Point(toX, toY);
				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
				list.add(p5);
				list.add(p6);
			}
		}
		// 左边
		else {
			// 上方
			if (toY + minLen < fromY-minLen) {
				float tmpy = (fromY + toY) / 2;
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(fromX, tmpy);
				Point p3 = new Point(toX, tmpy);
				Point p4 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
			}
		
			else {
				// 下方
				if (toShape.getBottomRightX() + minLen < fromShape.getX()-minLen) {
					//左边
					float tmpx = (fromShape.getX() + toShape.getBottomRightX()) / 2;
					Point p1 = new Point(fromX, fromY);
					Point p2 = new Point(fromX, fromY - Offset);
					Point p3 = new Point(tmpx, fromY - Offset);
					Point p4 = new Point(tmpx, toY + Offset);
					Point p5 = new Point(toX, toY + Offset);
					Point p6 = new Point(toX, toY);

					list.add(p1);
					list.add(p2);
					list.add(p3);
					list.add(p4);
					list.add(p5);
					list.add(p6);
				}
				else {
					//右边
					float tmpy;
					float tmpx;
					if (fromShape.getY() < toShape.getY()) {
						tmpy = fromShape.getY()-Offset; 
					} else {
						tmpy = toShape.getY()-Offset; 
					}
					
					if (fromShape.getX() < toShape.getX()) {
						tmpx = fromShape.getX()-Offset; 
					} else {
						tmpx = toShape.getX()-Offset; 
					}
					
					Point p1 = new Point(fromX, fromY);
					Point p2 = new Point(fromX, tmpy);
					Point p3 = new Point(tmpx, tmpy);
					Point p4 = new Point(tmpx, toY + Offset);
					Point p5 = new Point(toX, toY + Offset);
					Point p6 = new Point(toX, toY);

					list.add(p1);
					list.add(p2);
					list.add(p3);
					list.add(p4);
					list.add(p5);
					list.add(p6);
				}
			}
		}
		return list;
	}

	/**
	 * top left 完成
	 * 
	 * @param fromShape
	 * @param toShape
	 * @return
	 */
	public static ArrayList<Point> caculateTopLeft(Shape fromShape, Shape toShape,Port fromPort,Port toPort) {
		ArrayList<Point> list = new ArrayList<Point>();
		
		float fromX = (float) (fromShape.getX()+fromShape.getW()*fromPort.getX()+fromPort.getHorizontalOffset());
		float fromY = (float) (fromShape.getY()+fromShape.getH()*fromPort.getY()+fromPort.getVerticalOffset());
		float toX = (float) (toShape.getX()+toShape.getW()*toPort.getX()+toPort.getHorizontalOffset());
		float toY = (float) (toShape.getY()+toShape.getH()*toPort.getY()+toPort.getVerticalOffset());

		if (toShape.getX() - minLen > fromX ) {//right
			if (toY < fromShape.getY() - minLen) {//right top
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(fromX, toY);
				Point p3 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
			} else {//right bottom
				if (fromShape.getBottomRightX() + minLen < toShape.getX()-minLen) {
					float tmpx = (toShape.getX() + fromShape.getBottomRightX()) / 2;
					Point p1 = new Point(fromX, fromY);
					Point p2 = new Point(fromX, fromY - Offset);
					Point p3 = new Point(tmpx, fromY - Offset);
					Point p4 = new Point(tmpx, toY);
					Point p5 = new Point(toX, toY);

					list.add(p1);
					list.add(p2);
					list.add(p3);
					list.add(p4);
					list.add(p5);
				} else {
					float tmpy = 0;
					float tmpx = 0;
					if (toShape.getY() <= fromShape.getY()) {
						tmpy = toShape.getY()  - Offset;
					} else {
						tmpy = fromShape.getY() - Offset;
					}
					
					if (toShape.getX() <= fromShape.getX()) {
						tmpx = toShape.getX()  - Offset;
					} else {
						tmpx = fromShape.getX() - Offset;
					}
					
					Point p1 = new Point(fromX, fromY);
					Point p2 = new Point(fromX, tmpy);
					Point p3 = new Point(tmpx, tmpy);
					Point p4 = new Point(tmpx, toY);
					Point p5 = new Point(toX, toY);

					list.add(p1);
					list.add(p2);
					list.add(p3);
					list.add(p4);
					list.add(p5);
				}
			}
		}
		// 左边
		else {//left
			// 左上方
			if (fromShape.getY()-minLen >= toShape.getBottomRightY() + minLen) {//left top
				float tmpy = (fromShape.getY() + toShape.getBottomRightY()) / 2;
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(fromX, tmpy);
				Point p3 = new Point(toX - Offset, tmpy);
				Point p4 = new Point(toX - Offset, toY);
				Point p5 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
				list.add(p5);
			}
			// 左下方
			else {//left bottom
				float tmpy = 0;
				float tmpx = 0;
				// 目标在源对象上面
				if (toShape.getY() < fromShape.getY()) {
					tmpy = toShape.getY() - Offset;
				} else {
					tmpy = fromShape.getY() - Offset;
				}
				if (toShape.getX() < fromShape.getX()) {
					tmpx = toShape.getX() - Offset;
				} else {
					tmpx = fromShape.getX() - Offset;
				}
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(fromX, tmpy);
				Point p3 = new Point(tmpx, tmpy);
				Point p4 = new Point(tmpx, toY);
				Point p5 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
				list.add(p5);
			}
		}
		return list;
	}

	// right

	/**
	 * right top 检查完毕
	 * 
	 * @param fromShape
	 * @param toShape
	 * @return
	 */
	public static ArrayList<Point> caculateRightTop(Shape fromShape, Shape toShape,Port fromPort,Port toPort) {
		ArrayList<Point> list = new ArrayList<Point>();
		
		float fromX = (float) (fromShape.getX()+fromShape.getW()*fromPort.getX()+fromPort.getHorizontalOffset());
		float fromY = (float) (fromShape.getY()+fromShape.getH()*fromPort.getY()+fromPort.getVerticalOffset());
		float toX = (float) (toShape.getX()+toShape.getW()*toPort.getX()+toPort.getHorizontalOffset());
		float toY = (float) (toShape.getY()+toShape.getH()*toPort.getY()+toPort.getVerticalOffset());


		// 目标在源对象右边
		if (fromShape.getBottomRightX() + minLen < toShape.getX()-minLen) {//right
			// 上方
			if (fromY > toShape.getY() + minLen) {//right top
				float tmpx = (toShape.getX() + fromX) / 2;
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(tmpx, fromY);
				Point p3 = new Point(tmpx, toY - Offset);
				Point p4 = new Point(toX, toY - Offset);
				Point p5 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
				list.add(p5);
			}
			// 下方
			else {//right bottom
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(toX, fromY);
				Point p3 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
			}
		} else {//left
			if (toShape.getY() - minLen < fromY ) {//left top
				// 上方
				float tmpy = 0;
				float tmpx = 0;
				// 上方
				if (toShape.getY() < fromShape.getY()) {
					tmpy = toShape.getY() - Offset;
				} else {
					tmpy = fromShape.getY() - Offset;
				}
				
				if (toShape.getBottomRightX() >= fromShape.getBottomRightX()) {
					tmpx = toShape.getBottomRightX() + Offset;
				} else {
					tmpx = fromShape.getBottomRightX() + Offset;
				}

				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(tmpx, fromY);
				Point p3 = new Point(tmpx, tmpy);
				Point p4 = new Point(toX, tmpy);
				Point p5 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
				list.add(p5);
			}
			// 下方
			else {//left bottom
				if(toShape.getY()-minLen<fromShape.getBottomRightY()+minLen){
					if(toX>fromShape.getBottomRightX()+minLen){
						Point p1 = new Point(fromX, fromY);
						Point p2 = new Point(toX, fromY);
						Point p3 = new Point(toX, toY);

						list.add(p1);
						list.add(p2);
						list.add(p3);
					}else{
						// 上方
						float tmpy = 0;
						float tmpx = 0;
						// 上方
						if (toShape.getY() < fromShape.getY()) {
							tmpy = toShape.getY() - Offset;
						} else {
							tmpy = fromShape.getY() - Offset;
						}
						if (toShape.getBottomRightX() >= fromShape.getBottomRightX()) {
							tmpx = toShape.getBottomRightX() + Offset;
						} else {
							tmpx = fromShape.getBottomRightX() + Offset;
						}

						Point p1 = new Point(fromX, fromY);
						Point p2 = new Point(tmpx, fromY);
						Point p3 = new Point(tmpx, tmpy);
						Point p4 = new Point(toX, tmpy);
						Point p5 = new Point(toX, toY);

						list.add(p1);
						list.add(p2);
						list.add(p3);
						list.add(p4);
						list.add(p5);
					}
				}else{
					if(toX>fromShape.getBottomRightX()+minLen){
						Point p1 = new Point(fromX, fromY);
						Point p2 = new Point(toX, fromY);
						Point p3 = new Point(toX, toY);

						list.add(p1);
						list.add(p2);
						list.add(p3);
						
					}else{
						float tmpy = (fromShape.getBottomRightY() + toShape.getY()) / 2;
						Point p1 = new Point(fromX, fromY);
						Point p2 = new Point(fromX + Offset, fromY);
						Point p3 = new Point(fromX + Offset, tmpy);
						Point p4 = new Point(toX, tmpy);
						Point p5 = new Point(toX, toY);

						list.add(p1);
						list.add(p2);
						list.add(p3);
						list.add(p4);
						list.add(p5);
					}
				}
			}
		}
		return list;
	}

	/**
	 * right right 完成
	 * 
	 * @param fromShape
	 * @param toShape
	 * @return
	 */
	public static ArrayList<Point> caculateRightRight(Shape fromShape, Shape toShape,Port fromPort,Port toPort) {
		ArrayList<Point> list = new ArrayList<Point>();
		
		float fromX = (float) (fromShape.getX()+fromShape.getW()*fromPort.getX()+fromPort.getHorizontalOffset());
		float fromY = (float) (fromShape.getY()+fromShape.getH()*fromPort.getY()+fromPort.getVerticalOffset());
		float toX = (float) (toShape.getX()+toShape.getW()*toPort.getX()+toPort.getHorizontalOffset());
		float toY = (float) (toShape.getY()+toShape.getH()*toPort.getY()+toPort.getVerticalOffset());

		// 目标在源对象的右边
		if (toShape.getX() - minLen >= fromShape.getBottomRightX() + minLen) {//right
			// 目标对象的底部坐标大于 源对象的Y坐标
			if (toShape.getBottomRightY() + minLen < fromY   || toShape.getY() - minLen > fromY) {//right top or right bottom
				
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(toX + Offset, fromY);
				Point p3 = new Point(toX + Offset, toY);
				Point p4 = new Point(toX, toY);
				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
			} else {
				float tmpx = (toShape.getX() + fromX) / 2;
				float tmpy = 0;
				if (fromY > toY) {
					tmpy = toShape.getBottomRightY() + Offset;
				} else {
					tmpy = toShape.getY() - Offset;
				}
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(tmpx, fromY);
				Point p3 = new Point(tmpx, tmpy);
				Point p4 = new Point(toX + Offset, tmpy);
				Point p5 = new Point(toX + Offset, toY);
				Point p6 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
				list.add(p5);
				list.add(p6);
			}
		}
		// 中部判断
		else if (toShape.getBottomRightX()+minLen > fromShape.getX() - minLen){
			float tmpx = 0;
			if (toShape.getBottomRightX() > fromShape.getBottomRightX()) {
				tmpx = toShape.getBottomRightX() + Offset;
			} else {
				tmpx = fromShape.getBottomRightX() + Offset;
			}

			if (fromY == toY) {
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(tmpx, fromY);
				Point p3 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
			} else {
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(tmpx, fromY);
				Point p3 = new Point(tmpx, toY);
				Point p4 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
			}
		}
		// 目标对象在左边
		else {
			// 上方 下方
			if (toShape.getBottomRightY()+minLen <= fromShape.getY() - minLen ||toShape.getY()-minLen> fromShape.getBottomRightY() + minLen) {//left top or left bottom
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(fromX + Offset, fromY);
				Point p3 = new Point(fromX + Offset, toY);
				Point p4 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
			}
			// 中部
			else {//left center
				float tmpx = (fromShape.getX() + toShape.getBottomRightX()) / 2;
				float tmpy = 0;
				if (fromY <= toY) {
					tmpy = fromShape.getBottomRightY() + Offset;
				} else {
					tmpy = fromShape.getY() - Offset;
				}

				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(fromX + Offset, fromY);
				Point p3 = new Point(fromX + Offset, tmpy);
				Point p4 = new Point(tmpx, tmpy);
				Point p5 = new Point(tmpx, toY);
				Point p6 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
				list.add(p5);
				list.add(p6);
			}
		}
		return list;
	}

	/**
	 * right bottom 完毕
	 * 
	 * @param fromShape
	 * @param toShape
	 * @return
	 */
	public static ArrayList<Point> caculateRightBottom(Shape fromShape, Shape toShape,Port fromPort,Port toPort) {
		ArrayList<Point> list = new ArrayList<Point>();
		
		float fromX = (float) (fromShape.getX()+fromShape.getW()*fromPort.getX()+fromPort.getHorizontalOffset());
		float fromY = (float) (fromShape.getY()+fromShape.getH()*fromPort.getY()+fromPort.getVerticalOffset());
		float toX = (float) (toShape.getX()+toShape.getW()*toPort.getX()+toPort.getHorizontalOffset());
		float toY = (float) (toShape.getY()+toShape.getH()*toPort.getY()+toPort.getVerticalOffset());

		// 目标对象在源对象的右边
		if (toX> fromShape.getBottomRightX() + minLen) {
			// 上方
			if (fromY > toY + minLen) {
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(toX, fromY);
				Point p3 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
			}
			// 下方
			else {
				float tmpx = (fromShape.getBottomRightX() + toShape.getX()) / 2;
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(tmpx, fromY);
				Point p3 = new Point(tmpx, toY + Offset);
				Point p4 = new Point(toX, toY + Offset);
				Point p5 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
				list.add(p5);
			}
		} else {//left 
			// 上方
			if (toShape.getY() + minLen < fromShape.getY()-minLen) {//left top
				float tmpy = (fromShape.getY() + toShape.getBottomRightY()) / 2;
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(fromX + Offset, fromY);
				Point p3 = new Point(fromX + Offset, tmpy);
				Point p4 = new Point(toX, tmpy);
				Point p5 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
				list.add(p5);
			} else {//left bottom
				float tmpx = 0;
				float tmpy = 0;
				if (toShape.getBottomRightX() > fromShape.getBottomRightX()) {
					tmpx = toShape.getBottomRightX() + Offset;
				} else {
					tmpx = fromShape.getBottomRightX() + Offset;
				}
				if (toShape.getBottomRightY() < fromShape.getBottomRightY()) {
					tmpy = fromShape.getBottomRightY() + Offset;
				} else {
					tmpy = toShape.getBottomRightY() + Offset;
				}
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(tmpx, fromY);
				Point p3 = new Point(tmpx, tmpy);
				Point p4 = new Point(toX, tmpy);
				Point p5 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
				list.add(p5);
			}
		}
		return list;
	}

	/**
	 * right left 完毕
	 * 
	 * @param fromShape
	 * @param toShape
	 * @return
	 */
	public static ArrayList<Point> caculateRightLeft(Shape fromShape, Shape toShape,Port fromPort,Port toPort) {
		ArrayList<Point> list = new ArrayList<Point>();
		
		float fromX = (float) (fromShape.getX()+fromShape.getW()*fromPort.getX()+fromPort.getHorizontalOffset());
		float fromY = (float) (fromShape.getY()+fromShape.getH()*fromPort.getY()+fromPort.getVerticalOffset());
		float toX = (float) (toShape.getX()+toShape.getW()*toPort.getX()+toPort.getHorizontalOffset());
		float toY = (float) (toShape.getY()+toShape.getH()*toPort.getY()+toPort.getVerticalOffset());

		// 右边
		if (toShape.getX()-minLen > fromShape.getBottomRightX() + minLen) {//right
			// 直线
			if (toY == fromY) {
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(toX, toY);
				list.add(p1);
				list.add(p2);
			}
			// 纵坐标不相同
			else {//right top or bottom
				float tmpx = (fromShape.getBottomRightX() + toShape.getX()) / 2;
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(tmpx, fromY);
				Point p3 = new Point(tmpx, toY);
				Point p4 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
			}
		} else {//left
			// 上方,下方
			if (toShape.getBottomRightY() + minLen < fromShape.getY() - minLen || fromShape.getBottomRightY() + minLen < toShape.getY() - minLen) {//left top ro left buttom
				float tmpy = 0;
				if (toShape.getBottomRightY() < fromShape.getBottomRightY()) {
					tmpy = (toShape.getBottomRightY() + fromShape.getY()) / 2;
				} else {
					tmpy = (toShape.getY() + fromShape.getBottomRightY()) / 2;
				}
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(fromX + Offset, fromY);
				Point p3 = new Point(fromX + Offset, tmpy);
				Point p4 = new Point(toX - Offset, tmpy);
				Point p5 = new Point(toX - Offset, toY);
				Point p6 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
				list.add(p5);
				list.add(p6);
			}
			// 中部
			else {
				float tmpx = 0;
				float tmpy = 0;
				if (toShape.getBottomRightX()> fromShape.getBottomRightX()) {
					tmpx = toShape.getBottomRightX() + Offset;
				} else {
					tmpx = fromShape.getBottomRightX() + Offset;
				}
				
				if(fromY>=toY){
					if(toShape.getY() < fromShape.getY()){
						tmpy = toShape.getY()-Offset;
					}else{
						tmpy = fromShape.getY()-Offset;
					}
				}else{
					if(toShape.getBottomRightY() > fromShape.getBottomRightY()){
						tmpy = toShape.getBottomRightY()+Offset;
					}else{
						tmpy = fromShape.getBottomRightY()+Offset;
					}
				}
				
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(tmpx, fromY);
				Point p3 = new Point(tmpx, tmpy);
				Point p4 = new Point(toX - Offset, tmpy);
				Point p5 = new Point(toX - Offset, toY);
				Point p6 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
				list.add(p5);
				list.add(p6);
			}
		}
		return list;
	}

	// left

	/**
	 * left top 完成
	 * 
	 * @param fromShape
	 * @param toShape
	 * @return
	 */
	public static ArrayList<Point> caculateLeftTop(Shape fromShape, Shape toShape,Port fromPort,Port toPort) {
		ArrayList<Point> list = new ArrayList<Point>();
		
		float fromX = (float) (fromShape.getX()+fromShape.getW()*fromPort.getX()+fromPort.getHorizontalOffset());
		float fromY = (float) (fromShape.getY()+fromShape.getH()*fromPort.getY()+fromPort.getVerticalOffset());
		float toX = (float) (toShape.getX()+toShape.getW()*toPort.getX()+toPort.getHorizontalOffset());
		float toY = (float) (toShape.getY()+toShape.getH()*toPort.getY()+toPort.getVerticalOffset());

		
		if (toShape.getBottomRightX() + minLen >= fromShape.getX()-minLen) {	//right
			if (toShape.getY()-minLen >= fromShape.getBottomRightY() + minLen ) { //right bottom
				if(toX>fromShape.getX()){//right bottom right
					float tmpy = (fromShape.getBottomRightY() + toShape.getY()) / 2;
					Point p1 = new Point(fromX, fromY);
					Point p2 = new Point(fromX - Offset, fromY);
					Point p3 = new Point(fromX - Offset, tmpy);
					Point p4 = new Point(toX, tmpy);
					Point p5 = new Point(toX, toY);
	
					list.add(p1);
					list.add(p2);
					list.add(p3);
					list.add(p4);
					list.add(p5);
				}else{//right bottom left
					Point p1 = new Point(fromX, fromY);
					Point p2 = new Point(toX, fromY);
					Point p3 = new Point(toX, toY);

					list.add(p1);
					list.add(p2);
					list.add(p3);
				}
			} else {//right top
				if(toShape.getY()-minLen>fromY){
					if(toX>fromShape.getX()){//right bottom right
						float tmpx = 0;
						float tmpy = 0;
						if ( toShape.getX() >= fromShape.getX()) {
							tmpx = fromShape.getX() - Offset;
						} else {
							tmpx = toShape.getX() - Offset;
						}
						if (toShape.getY() < fromShape.getY()) {
							tmpy = toShape.getY() - Offset;
						} else {
							tmpy = fromShape.getY() - Offset;
						}
						Point p1 = new Point(fromX, fromY);
						Point p2 = new Point(tmpx, fromY);
						Point p3 = new Point(tmpx, tmpy);
						Point p4 = new Point(toX, tmpy);
						Point p5 = new Point(toX, toY);
		
						list.add(p1);
						list.add(p2);
						list.add(p3);
						list.add(p4);
						list.add(p5);
					}else{//right bottom left
						Point p1 = new Point(fromX, fromY);
						Point p2 = new Point(toX, fromY);
						Point p3 = new Point(toX, toY);

						list.add(p1);
						list.add(p2);
						list.add(p3);
					}
				}else{
					float tmpx = 0;
					float tmpy = 0;
					if ( toShape.getX() >= fromShape.getX()) {
						tmpx = fromShape.getX() - Offset;
					} else {
						tmpx = toShape.getX() - Offset;
					}
					if (toShape.getY() < fromShape.getY()) {
						tmpy = toShape.getY() - Offset;
					} else {
						tmpy = fromShape.getY()- Offset;
					}
					Point p1 = new Point(fromX, fromY);
					Point p2 = new Point(tmpx, fromY);
					Point p3 = new Point(tmpx, tmpy);
					Point p4 = new Point(toX, tmpy);
					Point p5 = new Point(toX, toY);
	
					list.add(p1);
					list.add(p2);
					list.add(p3);
					list.add(p4);
					list.add(p5);
				}
			}
		} else { // left
			if (toShape.getY()-minLen >= fromY) { //left bottom
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(toX, fromY);
				Point p3 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
			} else { //left top
				float tmpx = (toShape.getBottomRightX() + fromShape.getX()) / 2;
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(tmpx, fromY);
				Point p3 = new Point(tmpx, toY - Offset);
				Point p4 = new Point(toX, toY - Offset);
				Point p5 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
				list.add(p5);
			}
		}
		return list;
	}

	/**
	 * left right 完毕
	 * 
	 * @param fromShape
	 * @param toShape
	 * @return
	 */
	public static ArrayList<Point> caculateLeftRight(Shape fromShape, Shape toShape,Port fromPort,Port toPort) {
		ArrayList<Point> list = new ArrayList<Point>();
		
		float fromX = (float) (fromShape.getX()+fromShape.getW()*fromPort.getX()+fromPort.getHorizontalOffset());
		float fromY = (float) (fromShape.getY()+fromShape.getH()*fromPort.getY()+fromPort.getVerticalOffset());
		float toX = (float) (toShape.getX()+toShape.getW()*toPort.getX()+toPort.getHorizontalOffset());
		float toY = (float) (toShape.getY()+toShape.getH()*toPort.getY()+toPort.getVerticalOffset());

		// 目标在源对象的左边
		if (toShape.getBottomRightX() + minLen <= fromX-minLen) { //left 
			// 纵坐标相等
			if (fromY == toY) { //left center
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(toX, toY);
				list.add(p1);
				list.add(p2);
			} else { // left top and bottom
				float tmpx = (toShape.getBottomRightX() + fromShape.getX()) / 2;
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(tmpx, fromY);
				Point p3 = new Point(tmpx, toY);
				Point p4 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
			}
		}
		// 目标在源对象的右边
		else {// right
			// 上方下方
			if (toShape.getBottomRightY() + minLen <= fromShape.getY() - minLen || toShape.getY() >= fromShape.getBottomRightY() + minLen) {//right top and bottom
				float tmpy = 0;
				if (toShape.getBottomRightY() <= fromShape.getY()) {
					tmpy = (toShape.getBottomRightY() + fromShape.getY()) / 2;
				} else {
					tmpy = (toShape.getY() + fromShape.getBottomRightY()) / 2;
				}
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(fromX - Offset, fromY);
				Point p3 = new Point(fromX - Offset, tmpy);
				Point p4 = new Point(toX + Offset, tmpy);
				Point p5 = new Point(toX + Offset, toY);
				Point p6 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
				list.add(p5);
				list.add(p6);
			} else { // right center
				float tmpx = 0;
				float tmpy = 0;
				// 目标在源的左边就以目标的横坐标为准。
				if (toShape.getX() < fromShape.getX()) {
					tmpx = toShape.getX() - Offset;
				} else {
					tmpx = fromX - Offset;
				}
				// 线条在上方
				if (fromY > toY) { // right center top
					if (toShape.getY() < fromShape.getY()) {
						tmpy = toShape.getY() - Offset;
					} else {
						tmpy = fromShape.getY() - Offset;
					}
				}
				// 线条在下方
				else {
					if (toShape.getBottomRightY() > fromShape.getBottomRightY()) {
						tmpy = toShape.getBottomRightY() + Offset;
					} else {
						tmpy = fromShape.getBottomRightY() + Offset;
					}
				}
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(tmpx, fromY);
				Point p3 = new Point(tmpx, tmpy);
				Point p4 = new Point(toX + Offset, tmpy);
				Point p5 = new Point(toX + Offset, toY);
				Point p6 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
				list.add(p5);
				list.add(p6);
			}
		}
		return list;
	}

	/**
	 * Left Bottom 完毕
	 * 
	 * @param fromShape
	 * @param toShape
	 * @return
	 */
	public static ArrayList<Point> caculateLeftBottom(Shape fromShape, Shape toShape,Port fromPort,Port toPort) {
		ArrayList<Point> list = new ArrayList<Point>();
		
		float fromX = (float) (fromShape.getX()+fromShape.getW()*fromPort.getX()+fromPort.getHorizontalOffset());
		float fromY = (float) (fromShape.getY()+fromShape.getH()*fromPort.getY()+fromPort.getVerticalOffset());
		float toX = (float) (toShape.getX()+toShape.getW()*toPort.getX()+toPort.getHorizontalOffset());
		float toY = (float) (toShape.getY()+toShape.getH()*toPort.getY()+toPort.getVerticalOffset());

		// 目标对像在源对象的左边
		if (toShape.getBottomRightX() + minLen < fromShape.getX()-minLen) {//left
			// 左上部
			if (toShape.getBottomRightY() + minLen <= fromY) {//left top
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(toX, fromY);
				Point p3 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
			}
			// 左下部
			else {//left bottom
				float tmpx = (toShape.getBottomRightX() + fromShape.getX()) / 2;
				float tmpy = toShape.getBottomRightY() + Offset;
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(tmpx, fromY);
				Point p3 = new Point(tmpx, tmpy);
				Point p4 = new Point(toX, tmpy);
				Point p5 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
				list.add(p5);
			}

		}
		// 目标对象在源对象右边
		else {//right
			// 目标对象在源对象的上方
			if (toShape.getBottomRightY() + minLen < fromShape.getY()-minLen) {//right top
				if(toX<=fromShape.getX()){ // right top left
					Point p1 = new Point(fromX, fromY);
					Point p2 = new Point(toX, fromY);
					Point p3 = new Point(toX, toY);

					list.add(p1);
					list.add(p2);
					list.add(p3);
				}else{// right top right
					float tmpy = (toShape.getBottomRightY() + fromShape.getY()) / 2;
					Point p1 = new Point(fromX, fromY);
					Point p2 = new Point(fromX - Offset, fromY);
					Point p3 = new Point(fromX - Offset, tmpy);
					Point p4 = new Point(toX, tmpy);
					Point p5 = new Point(toX, toY);
	
					list.add(p1);
					list.add(p2);
					list.add(p3);
					list.add(p4);
					list.add(p5);
				}
			} else {
				if(toShape.getBottomRightY()+minLen<=fromY && toX<fromShape.getX()-minLen){
					Point p1 = new Point(fromX, fromY);
					Point p2 = new Point(toX, fromY);
					Point p3 = new Point(toX, toY);

					list.add(p1);
					list.add(p2);
					list.add(p3);
				}else{
					float tmpx = 0;
					float tmpy = 0;
					// 目标的左边界在源坐标的左边
					if (toShape.getX() < fromShape.getX()) {
						tmpx = toShape.getX() - Offset;
					} else {
						tmpx = fromShape.getX() - Offset;
					}
					if (toShape.getBottomRightY() < fromShape.getBottomRightY()) {
						tmpy = fromShape.getBottomRightY() + Offset;
					} else {
						tmpy = toShape.getBottomRightY() + Offset;
					}
					Point p1 = new Point(fromX, fromY);
					Point p2 = new Point(tmpx, fromY);
					Point p3 = new Point(tmpx, tmpy);
					Point p4 = new Point(toX, tmpy);
					Point p5 = new Point(toX, toY);

					list.add(p1);
					list.add(p2);
					list.add(p3);
					list.add(p4);
					list.add(p5);
				}
			}
		}
		return list;
	}

	/**
	 * left left 完成
	 * 
	 * @param fromShape
	 * @param toShape
	 * @return
	 */
	public static ArrayList<Point> caculateLeftLeft(Shape fromShape, Shape toShape,Port fromPort,Port toPort) {
		ArrayList<Point> list = new ArrayList<Point>();
		
		float fromX = (float) (fromShape.getX()+fromShape.getW()*fromPort.getX()+fromPort.getHorizontalOffset());
		float fromY = (float) (fromShape.getY()+fromShape.getH()*fromPort.getY()+fromPort.getVerticalOffset());
		float toX = (float) (toShape.getX()+toShape.getW()*toPort.getX()+toPort.getHorizontalOffset());
		float toY = (float) (toShape.getY()+toShape.getH()*toPort.getY()+toPort.getVerticalOffset());

		// 目标在源对象的左边
		if (toShape.getBottomRightX() + minLen < fromShape.getX()-minLen) {//left
			// 上方下方
			if (toShape.getBottomRightY() + minLen < fromY || toShape.getY()-minLen > fromY ) {//left top and bottom
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(toX - Offset, fromY);
				Point p3 = new Point(toX - Offset, toY);
				Point p4 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
			} else {//left center
				float tmpx = (toShape.getBottomRightX() + fromShape.getX()) / 2;
				float tmpy = 0;

				if (toY >= fromY) {
					tmpy = toShape.getY() - Offset;
				} else {
					tmpy = toShape.getBottomRightY() + Offset;
				}
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(tmpx, fromY);
				Point p3 = new Point(tmpx, tmpy);
				Point p4 = new Point(toX - Offset, tmpy);
				Point p5 = new Point(toX - Offset, toY);
				Point p6 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
				list.add(p5);
				list.add(p6);
			}
		}
		// 中部
		else if (toShape.getX()- minLen < fromShape.getBottomRightX()+minLen) {//center
			float tmpx = 0;
			if (toShape.getX() < fromShape.getX()) {
				tmpx = toShape.getX() - Offset;
			} else {
				tmpx = fromShape.getX() - Offset;
			}
			Point p1 = new Point(fromX, fromY);
			Point p2 = new Point(tmpx, fromY);
			Point p3 = new Point(tmpx, toY);
			Point p4 = new Point(toX, toY);
			list.add(p1);
			list.add(p2);
			list.add(p3);
			list.add(p4);
		}
		// 右边
		else {//right
			// 上方下方
			if (toY < fromShape.getY() - minLen || toY > fromShape.getBottomRightY() + minLen) {//right top or bottom
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(fromX - Offset, fromY);
				Point p3 = new Point(fromX - Offset, toY);
				Point p4 = new Point(toX, toY);
				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
			}
			// 纵向中部
			else { //right center
				float tmpx = (fromShape.getBottomRightX() + toShape.getX()) / 2;
				float tmpy = 0;
				// 目标在源的中上
				if (fromY > toY) {
					tmpy = fromShape.getY() - Offset;
				} else {
					tmpy = fromShape.getBottomRightY()+ Offset;
				}
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(fromX - Offset, fromY);
				Point p3 = new Point(fromX - Offset, tmpy);
				Point p4 = new Point(tmpx, tmpy);
				Point p5 = new Point(tmpx, toY);
				Point p6 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
				list.add(p5);
				list.add(p6);
			}
		}
		return list;
	}

	// bottom

	/**
	 * bottom top 完毕
	 * 
	 * @param fromShape
	 * @param toShape
	 * @return
	 */
	public static ArrayList<Point> caculateBottomTop(Shape fromShape, Shape toShape,Port fromPort,Port toPort) {
		ArrayList<Point> list = new ArrayList<Point>();
		
		float fromX = (float) (fromShape.getX()+fromShape.getW()*fromPort.getX()+fromPort.getHorizontalOffset());
		float fromY = (float) (fromShape.getY()+fromShape.getH()*fromPort.getY()+fromPort.getVerticalOffset());
		float toX = (float) (toShape.getX()+toShape.getW()*toPort.getX()+toPort.getHorizontalOffset());
		float toY = (float) (toShape.getY()+toShape.getH()*toPort.getY()+toPort.getVerticalOffset());

		// 上方
		if (toShape.getY()-minLen < fromShape.getBottomRightY() + minLen) {
			// 左边和右边
			if (toShape.getBottomRightX() + minLen < fromShape.getX() - minLen || toShape.getX() - minLen > fromShape.getBottomRightX() + minLen) {//top right or left
				float tmpx = 0;
				if (toShape.getBottomRightX() < fromShape.getX()) {
					tmpx = (toShape.getBottomRightX() + fromShape.getX()) / 2;
				} else {
					tmpx = (toShape.getX() + fromShape.getBottomRightX()) / 2;
				}
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(fromX, fromY + Offset);
				Point p3 = new Point(tmpx, fromY + Offset);
				Point p4 = new Point(tmpx, toY - Offset);
				Point p5 = new Point(toX, toY - Offset);
				Point p6 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
				list.add(p5);
				list.add(p6);
			}
			// 中部
			else {// top center
				float tmpx = 0;
				float tmpy = 0;
				// 目标在源的中偏左
				if (toX > fromX) {
					if (toShape.getBottomRightX() > fromShape.getBottomRightX()) {
						tmpx = toShape.getBottomRightX() + Offset;
					} else {
						tmpx = fromShape.getBottomRightX() + Offset;
					}
				} else {
					if (toShape.getX() < fromShape.getX()) {
						tmpx = toShape.getX() - Offset;
					} else {
						tmpx = fromShape.getX() - Offset;
					}
				}
				if (toShape.getBottomRightY()> fromShape.getBottomRightY()) {
					tmpy = toShape.getBottomRightY() + Offset;
				} else {
					tmpy = fromShape.getBottomRightY() + Offset;
				}
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(fromX, tmpy);
				Point p3 = new Point(tmpx, tmpy);
				Point p4 = new Point(tmpx, toY - Offset);
				Point p5 = new Point(toX, toY - Offset);
				Point p6 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
				list.add(p5);
				list.add(p6);

			}
		}
		// 目标在源的下方
		else {//bottom center
			if (toX == fromX) {
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(toX, toY);
				list.add(p1);
				list.add(p2);
			} else {//bottom left or right
				float tmpy = (fromShape.getBottomRightY() + toShape.getY()) / 2;
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(fromX, tmpy);
				Point p3 = new Point(toX, tmpy);
				Point p4 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
			}
		}

		return list;
	}

	/**
	 * bottom right 完成
	 * 
	 * @param fromShape
	 * @param toShape
	 * @return
	 */
	public static ArrayList<Point> caculateBottomRight(Shape fromShape, Shape toShape,Port fromPort,Port toPort) {
		ArrayList<Point> list = new ArrayList<Point>();
		
		float fromX = (float) (fromShape.getX()+fromShape.getW()*fromPort.getX()+fromPort.getHorizontalOffset());
		float fromY = (float) (fromShape.getY()+fromShape.getH()*fromPort.getY()+fromPort.getVerticalOffset());
		float toX = (float) (toShape.getX()+toShape.getW()*toPort.getX()+toPort.getHorizontalOffset());
		float toY = (float) (toShape.getY()+toShape.getH()*toPort.getY()+toPort.getVerticalOffset());

		// 目标在源对象的下方
		if (toY >= fromShape.getBottomRightY() + minLen) {//bottom
			if (toShape.getBottomRightX()+minLen <= fromX) {//bottom left
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(fromX, toY);
				Point p3 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
			} else { //bottom right
				// 下方
				if (toShape.getY()-minLen > fromShape.getBottomRightY() + minLen) {//bottom right bottom
					float tmpy = (fromShape.getBottomRightY() + toShape.getY()) / 2;
					Point p1 = new Point(fromX, fromY);
					Point p2 = new Point(fromX, tmpy);
					Point p3 = new Point(toX + Offset, tmpy);
					Point p4 = new Point(toX + Offset, toY);
					Point p5 = new Point(toX, toY);

					list.add(p1);
					list.add(p2);
					list.add(p3);
					list.add(p4);
					list.add(p5);

				} else {//bottom right top
					float tmpy = 0;
					if (toShape.getBottomRightY() > fromShape.getBottomRightY()) {
						tmpy = toShape.getBottomRightY() + Offset;
					} else {
						tmpy = fromShape.getBottomRightY() + Offset;
					}
					Point p1 = new Point(fromX, fromY);
					Point p2 = new Point(fromX, tmpy);
					Point p3 = new Point(toX + Offset, tmpy);
					Point p4 = new Point(toX + Offset, toY);
					Point p5 = new Point(toX, toY);

					list.add(p1);
					list.add(p2);
					list.add(p3);
					list.add(p4);
					list.add(p5);
				}
			}
		} else {//top
			if (toShape.getBottomRightX()+minLen < fromShape.getX()-minLen) {//top left
				float tmpx = (toShape.getBottomRightX() + fromShape.getX()) / 2;
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(fromX, fromY + Offset);
				Point p3 = new Point(tmpx, fromY + Offset);
				Point p4 = new Point(tmpx, toY);
				Point p5 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
				list.add(p5);
			} else {//top right
				float tmpx = 0;
				float tmpy = 0;
				if (toShape.getBottomRightX() > fromShape.getBottomRightX()) {
					tmpx = toShape.getBottomRightX() + Offset;
				} else {
					tmpx = fromShape.getBottomRightX() + Offset;
				}
				if (toShape.getBottomRightY() > fromShape.getBottomRightY()) {
					tmpy = toShape.getBottomRightY() + Offset;
				} else {
					tmpy = fromShape.getBottomRightY() + Offset;
				}
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(fromX, tmpy);
				Point p3 = new Point(tmpx, tmpy);
				Point p4 = new Point(tmpx, toY);
				Point p5 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
				list.add(p5);
			}
		}
		return list;
	}

	/**
	 * bottom bottom 完毕
	 * 
	 * @param fromShape
	 * @param toShape
	 * @return
	 */
	public static ArrayList<Point> caculateBottomBottom(Shape fromShape, Shape toShape,Port fromPort,Port toPort) {
		ArrayList<Point> list = new ArrayList<Point>();
		
		float fromX = (float) (fromShape.getX()+fromShape.getW()*fromPort.getX()+fromPort.getHorizontalOffset());
		float fromY = (float) (fromShape.getY()+fromShape.getH()*fromPort.getY()+fromPort.getVerticalOffset());
		float toX = (float) (toShape.getX()+toShape.getW()*toPort.getX()+toPort.getHorizontalOffset());
		float toY = (float) (toShape.getY()+toShape.getH()*toPort.getY()+toPort.getVerticalOffset());

		// 上方
		if (toY + minLen < fromShape.getY()-minLen) {//top left or top right 
			// 左方右方
			if (toX < fromShape.getX()-minLen || toX > fromShape.getBottomRightX() + minLen) {
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(fromX, fromY + Offset);
				Point p3 = new Point(toX, fromY + Offset);
				Point p4 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
			}
			// 中部
			else {//top center
				float tmpy = (toShape.getBottomRightY() + fromShape.getY()) / 2;
				float tmpx = 0;
				if (toX < fromX) {
					tmpx = fromShape.getX() - Offset;
				} else {
					tmpx = fromShape.getBottomRightX() + Offset;
				}
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(fromX, fromY + Offset);
				Point p3 = new Point(tmpx, fromY + Offset);
				Point p4 = new Point(tmpx, tmpy);
				Point p5 = new Point(toX, tmpy);
				Point p6 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
				list.add(p5);
				list.add(p6);
			}
		}
		// 下方
		else {//bottom
			// 左右边
			if (toShape.getX() - minLen > fromX || toShape.getBottomRightX() + minLen < fromX) {
				float tmpy = 0;
				if (toShape.getBottomRightY() > fromShape.getBottomRightY()) {
					tmpy = toShape.getBottomRightY() + Offset;
				} else {
					tmpy = fromShape.getBottomRightY()+ Offset;
				}
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(fromX, tmpy);
				Point p3 = new Point(toX, tmpy);
				Point p4 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
			}
			// 中部
			else {
				if(toShape.getY()-minLen<=fromShape.getBottomRightY()+minLen){
					float tmpy = 0;
					if (toShape.getBottomRightY() > fromShape.getBottomRightY()) {
						tmpy = toShape.getBottomRightY() + Offset;
					} else {
						tmpy = fromShape.getBottomRightY()+ Offset;
					}
					Point p1 = new Point(fromX, fromY);
					Point p2 = new Point(fromX, tmpy);
					Point p3 = new Point(toX, tmpy);
					Point p4 = new Point(toX, toY);

					list.add(p1);
					list.add(p2);
					list.add(p3);
					list.add(p4);
				}else{
					float tmpx = 0;
					float tmpy = (toShape.getY() + fromY) / 2;
					if (toX < fromX) {
						tmpx = toShape.getBottomRightX() + Offset;
					} else {
						tmpx = toShape.getX() - Offset;
					}
					Point p1 = new Point(fromX, fromY);
					Point p2 = new Point(fromX, tmpy);
					Point p3 = new Point(tmpx, tmpy);
					Point p4 = new Point(tmpx, toY + Offset);
					Point p5 = new Point(toX, toY + Offset);
					Point p6 = new Point(toX, toY);
	
					list.add(p1);
					list.add(p2);
					list.add(p3);
					list.add(p4);
					list.add(p5);
					list.add(p6);
				}
			}
		}

		return list;
	}

	/**
	 * bottom left
	 * 
	 * @param fromShape
	 * @param toShape
	 * @return
	 */
	public static ArrayList<Point> caculateBottomLeft(Shape fromShape, Shape toShape,Port fromPort,Port toPort) {
		ArrayList<Point> list = new ArrayList<Point>();
		
		float fromX = (float) (fromShape.getX()+fromShape.getW()*fromPort.getX()+fromPort.getHorizontalOffset());
		float fromY = (float) (fromShape.getY()+fromShape.getH()*fromPort.getY()+fromPort.getVerticalOffset());
		float toX = (float) (toShape.getX()+toShape.getW()*toPort.getX()+toPort.getHorizontalOffset());
		float toY = (float) (toShape.getY()+toShape.getH()*toPort.getY()+toPort.getVerticalOffset());

		// 目标在源对象的下方
		if (toY >= fromShape.getBottomRightY() + minLen) {//bottom
			if (toShape.getX()- minLen >= fromX) {//bottom right
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(fromX, toY);
				Point p3 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
			} else { //bottom left
				// 下方
				if (toShape.getY()-minLen > fromShape.getBottomRightY() + minLen) {//bottom left bottom
					float tmpy = (fromShape.getBottomRightY() + toShape.getY()) / 2;
					Point p1 = new Point(fromX, fromY);
					Point p2 = new Point(fromX, tmpy);
					Point p3 = new Point(toX - Offset, tmpy);
					Point p4 = new Point(toX - Offset, toY);
					Point p5 = new Point(toX, toY);

					list.add(p1);
					list.add(p2);
					list.add(p3);
					list.add(p4);
					list.add(p5);

				} else {//bottom left top
					float tmpy = 0;
					if (toShape.getBottomRightY() > fromShape.getBottomRightY()) {
						tmpy = toShape.getBottomRightY() + Offset;
					} else {
						tmpy = fromShape.getBottomRightY() + Offset;
					}
					Point p1 = new Point(fromX, fromY);
					Point p2 = new Point(fromX, tmpy);
					Point p3 = new Point(toX - Offset, tmpy);
					Point p4 = new Point(toX - Offset, toY);
					Point p5 = new Point(toX, toY);

					list.add(p1);
					list.add(p2);
					list.add(p3);
					list.add(p4);
					list.add(p5);
				}
			}
		} else {//top
			if (toShape.getX() -minLen > fromShape.getBottomRightX()+minLen) {//top right
				float tmpx = (fromShape.getBottomRightX() + toShape.getX()) / 2;
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(fromX, fromY + Offset);
				Point p3 = new Point(tmpx, fromY + Offset);
				Point p4 = new Point(tmpx, toY);
				Point p5 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
				list.add(p5);
			} else {
				float tmpx = 0;
				float tmpy = 0;
				if (toShape.getX() > fromShape.getX()) {
					tmpx = fromShape.getX() - Offset;
				} else {
					tmpx = toShape.getX() - Offset;
				}
				if (toShape.getBottomRightY() > fromShape.getBottomRightY()) {
					tmpy = toShape.getBottomRightY() + Offset;
				} else {
					tmpy = fromShape.getBottomRightY() + Offset;
				}
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(fromX, tmpy);
				Point p3 = new Point(tmpx, tmpy);
				Point p4 = new Point(tmpx, toY);
				Point p5 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
				list.add(p5);
			}
		}
		return list;
	}

	private static String getPointXml(List<Point> list) {
		StringBuffer sb = new StringBuffer();
		for (Point p : list) {
			sb.append("\n<omgdi:waypoint x=\"" + p.getX() + "\" y=\""
					+ p.getY() + "\"></omgdi:waypoint>\n");
		}
		return sb.toString();
	}

	public static String calcLabelPosition(String label) {
		float labelLen=0;
		for(int i=0;i<label.length();i++){
			if(label.charAt(i)>255){
				labelLen+=2;
			}else if(Character.isUpperCase(label.charAt(i))){
				labelLen+=1.5;
			}else{
				labelLen+=1;
			}
			
		}
		int x = (int) (labelLen>16?-50:-(labelLen/16+1)*100/2);
		int y = 0;
		int width = 100;
		int height = (int) ((labelLen/16+1)*14);
		StringBuffer position = new StringBuffer();
		position.append(" <omgdc:Bounds ");
		position.append("x=\"" + x + "\" ");
		position.append("y=\"" + y + "\" ");
		position.append("width=\"" + width + "\" ");
		position.append("height=\"" + height + "\">");
		position.append("</omgdc:Bounds>");
		
		return position.toString();
	}
	//<!--计算连线的x轴偏移值 -->
	public static String getPreVlanSumX(String contacts){
		String[] xy= contacts.split(",");
		int pointx=0;
		for(String str:xy){
			if(StringUtil.isNotEmpty(str)){
				pointx+=Integer.parseInt(str);
			}
		}
		return pointx+"";
	}
}
