package com.hotent.core.bpm.graph.activiti;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.dom4j.Document;
import org.dom4j.Element;

import com.hotent.core.util.Dom4jUtil;
import com.hotent.platform.model.bpm.BpmProStatus;

/**
 * 此类用于能过bpmn的xml文档生成相应的图片。此类中主要使用静态方法。
 * 
 * @author Raise
 */
public class ProcessDiagramGenerator {

	protected static final Map<BPMNShapType, GraphDrawInstruction> graphDrawInstructions = new HashMap<BPMNShapType, GraphDrawInstruction>();

	// 静态代码段，将实现画图的功能GraphDrawInstructions实现类实例存放到Map graphDrawInstructions中，使用时，直接通过Key从Map中取出相应的实例，并调用实例的draw方法进行画图操作。
	static {
		// StartEvent
		graphDrawInstructions.put(BPMNShapType.StartEvent, new GraphDrawInstruction() {
			@Override
			public void draw(ProcessDiagramCanvas processDiagramCanvas, BPMNShap shap) {
				processDiagramCanvas.drawNoneStartEvent(shap.getName(), (int) Math.round(shap.getX()), (int) Math.round(shap.getY()), (int) Math.round(shap.getWidth()),
						(int) Math.round(shap.getHeight()));
			}
		});
		// ErrorEvent
		graphDrawInstructions.put(BPMNShapType.ErrorEvent, new GraphDrawInstruction() {
			@Override
			public void draw(ProcessDiagramCanvas processDiagramCanvas, BPMNShap shap) {
				processDiagramCanvas.drawErrorEndEvent(shap.getName(), (int) Math.round(shap.getX()), (int) Math.round(shap.getY()), (int) Math.round(shap.getWidth()),
						(int) Math.round(shap.getHeight()));
			}
		});

		// EndEvent
		graphDrawInstructions.put(BPMNShapType.EndEvent, new GraphDrawInstruction() {
			@Override
			public void draw(ProcessDiagramCanvas processDiagramCanvas, BPMNShap shap) {
				processDiagramCanvas.drawNoneEndEvent(shap.getName(), (int) Math.round(shap.getX()), (int) Math.round(shap.getY()), (int) Math.round(shap.getWidth()),
						(int) Math.round(shap.getHeight()));
			}
		});
		// CancelEvent
		graphDrawInstructions.put(BPMNShapType.CancelEvent, new GraphDrawInstruction() {
			@Override
			public void draw(ProcessDiagramCanvas processDiagramCanvas, BPMNShap shap) {
				processDiagramCanvas.drawErrorEndEvent(shap.getName(), (int) Math.round(shap.getX()), (int) Math.round(shap.getY()), (int) Math.round(shap.getWidth()),
						(int) Math.round(shap.getHeight()));
			}
		});

		// Task
		graphDrawInstructions.put(BPMNShapType.Task, new GraphDrawInstruction() {
			@Override
			public void draw(ProcessDiagramCanvas processDiagramCanvas, BPMNShap shap) {
				processDiagramCanvas.drawTask(shap.getName(), (int) Math.round(shap.getX()), (int) Math.round(shap.getY()), (int) Math.round(shap.getWidth()), (int) Math.round(shap.getHeight()));
			}
		});

		// UserTask
		graphDrawInstructions.put(BPMNShapType.UserTask, new GraphDrawInstruction() {
			@Override
			public void draw(ProcessDiagramCanvas processDiagramCanvas, BPMNShap shap) {
				processDiagramCanvas.drawUserTask(shap.getName(), (int) Math.round(shap.getX()), (int) Math.round(shap.getY()), (int) Math.round(shap.getWidth()), (int) Math.round(shap.getHeight()));
			}
		});

		// ScriptTask
		graphDrawInstructions.put(BPMNShapType.ScriptTask, new GraphDrawInstruction() {
			@Override
			public void draw(ProcessDiagramCanvas processDiagramCanvas, BPMNShap shap) {
				processDiagramCanvas.drawScriptTask(shap.getName(), (int) Math.round(shap.getX()), (int) Math.round(shap.getY()), (int) Math.round(shap.getWidth()), (int) Math.round(shap.getHeight()));
			}
		});


		// ServiceTask
		graphDrawInstructions.put(BPMNShapType.ServiceTask, new GraphDrawInstruction() {
			@Override
			public void draw(ProcessDiagramCanvas processDiagramCanvas, BPMNShap shap) {
				processDiagramCanvas.drawServiceTask(shap.getName(), (int) Math.round(shap.getX()), (int) Math.round(shap.getY()), (int) Math.round(shap.getWidth()),
						(int) Math.round(shap.getHeight()));
			}
		});

		// ReceiveTask
		graphDrawInstructions.put(BPMNShapType.ReceiveTask, new GraphDrawInstruction() {
			@Override
			public void draw(ProcessDiagramCanvas processDiagramCanvas, BPMNShap shap) {
				processDiagramCanvas.drawReceiveTask(shap.getName(), (int) Math.round(shap.getX()), (int) Math.round(shap.getY()), (int) Math.round(shap.getWidth()),
						(int) Math.round(shap.getHeight()));
			}
		});

		// SendTask
		graphDrawInstructions.put(BPMNShapType.SendTask, new GraphDrawInstruction() {
			@Override
			public void draw(ProcessDiagramCanvas processDiagramCanvas, BPMNShap shap) {
				processDiagramCanvas.drawSendTask(shap.getName(), (int) Math.round(shap.getX()), (int) Math.round(shap.getY()), (int) Math.round(shap.getWidth()), (int) Math.round(shap.getHeight()));
			}
		});

		// ManualTask
		graphDrawInstructions.put(BPMNShapType.ManualTask, new GraphDrawInstruction() {
			@Override
			public void draw(ProcessDiagramCanvas processDiagramCanvas, BPMNShap shap) {
				processDiagramCanvas.drawManualTask(shap.getName(), (int) Math.round(shap.getX()), (int) Math.round(shap.getY()), (int) Math.round(shap.getWidth()), (int) Math.round(shap.getHeight()));
			}
		});

		// ExclusiveGateway
		graphDrawInstructions.put(BPMNShapType.ExclusiveGateway, new GraphDrawInstruction() {
			@Override
			public void draw(ProcessDiagramCanvas processDiagramCanvas, BPMNShap shap) {
				processDiagramCanvas.drawExclusiveGateway(shap.getName(), (int) Math.round(shap.getX()), (int) Math.round(shap.getY()), (int) Math.round(shap.getWidth()),
						(int) Math.round(shap.getHeight()));
			}
		});
		// InclusiveGateway
		graphDrawInstructions.put(BPMNShapType.InclusiveGateway, new GraphDrawInstruction() {
			@Override
			public void draw(ProcessDiagramCanvas processDiagramCanvas, BPMNShap shap) {
				processDiagramCanvas.drawInclusiveGateway(shap.getName(), (int) Math.round(shap.getX()), (int) Math.round(shap.getY()), (int) Math.round(shap.getWidth()),
						(int) Math.round(shap.getHeight()));
			}
		});

		// ParallelGateway
		graphDrawInstructions.put(BPMNShapType.ParallelGateway, new GraphDrawInstruction() {
			@Override
			public void draw(ProcessDiagramCanvas processDiagramCanvas, BPMNShap shap) {
				processDiagramCanvas.drawParallelGateway(shap.getName(), (int) Math.round(shap.getX()), (int) Math.round(shap.getY()), (int) Math.round(shap.getWidth()),
						(int) Math.round(shap.getHeight()));
			}
		});

		// SubProcess
		graphDrawInstructions.put(BPMNShapType.SubProcess, new GraphDrawInstruction() {
			@Override
			public void draw(ProcessDiagramCanvas processDiagramCanvas, BPMNShap shap) {

				if (shap.isExpanded() != null && shap.isExpanded() == false) {
					processDiagramCanvas.drawCollapsedSubProcess(shap.getName(), (int) Math.round(shap.getX()), (int) Math.round(shap.getY()), (int) Math.round(shap.getWidth()),
							(int) Math.round(shap.getHeight()));
				} else {
					processDiagramCanvas.drawExpandedSubProcess(shap.getName(), (int) Math.round(shap.getX()), (int) Math.round(shap.getY()), (int) Math.round(shap.getWidth()),
							(int) Math.round(shap.getHeight()));
				}
			}
		});

		// CallActivity
		graphDrawInstructions.put(BPMNShapType.CallActivity, new GraphDrawInstruction() {
			@Override
			public void draw(ProcessDiagramCanvas processDiagramCanvas, BPMNShap shap) {
				processDiagramCanvas.drawCollapsedCallActivity(shap.getName(), (int) Math.round(shap.getX()), (int) Math.round(shap.getY()), (int) Math.round(shap.getWidth()),
						(int) Math.round(shap.getHeight()));
			}
		});

		// HPool
		graphDrawInstructions.put(BPMNShapType.HPool, new GraphDrawInstruction() {
			@Override
			public void draw(ProcessDiagramCanvas processDiagramCanvas, BPMNShap shap) {
				processDiagramCanvas.drawHPool(shap.getName(), (int) Math.round(shap.getX()), (int) Math.round(shap.getY()), (int) Math.round(shap.getWidth()), (int) Math.round(shap.getHeight()));
			}
		});
		// HLane
		graphDrawInstructions.put(BPMNShapType.HLane, new GraphDrawInstruction() {
			@Override
			public void draw(ProcessDiagramCanvas processDiagramCanvas, BPMNShap shap) {
				processDiagramCanvas.drawHLane(shap.getName(), (int) Math.round(shap.getX()), (int) Math.round(shap.getY()), (int) Math.round(shap.getWidth()), (int) Math.round(shap.getHeight()));
			}
		});
		// VPool
		graphDrawInstructions.put(BPMNShapType.VPool, new GraphDrawInstruction() {
			@Override
			public void draw(ProcessDiagramCanvas processDiagramCanvas, BPMNShap shap) {
				processDiagramCanvas.drawVPool(shap.getName(), (int) Math.round(shap.getX()), (int) Math.round(shap.getY()), (int) Math.round(shap.getWidth()), (int) Math.round(shap.getHeight()));
			}
		});
		// VLane
		graphDrawInstructions.put(BPMNShapType.VLane, new GraphDrawInstruction() {
			@Override
			public void draw(ProcessDiagramCanvas processDiagramCanvas, BPMNShap shap) {
				processDiagramCanvas.drawVLane(shap.getName(), (int) Math.round(shap.getX()), (int) Math.round(shap.getY()), (int) Math.round(shap.getWidth()), (int) Math.round(shap.getHeight()));
			}
		});

	}

	/**
	 * 初始化画布。设定画布的大小。
	 * @param shaps 流程定义中的所有图形组件。
	 * @param edges 流程定义中的所有连线。
	 * @return 初始化后的画布。
	 */
	public static ProcessDiagramCanvas initProcessDiagramCanvas(List<BPMNShap> shaps,List<BPMNEdge> edges) {
		Point2D.Double[] points=caculateCanvasSize(shaps, edges);
		double shiftX=points[0].getX()<0?points[0].getX():0;
		double shiftY=points[0].getY()<0?points[0].getY():0;
		shiftProcessDefinition(shaps, edges, shiftX, shiftY);
		
		int width=(int) Math.round((points[1].getX() + 10-shiftX));
		int height=(int) Math.round((points[1].getY() + 10-shiftY));
		int minX=(int) Math.round((points[0].getX() -shiftX));;
		int minY=(int) Math.round((points[0].getY() -shiftY));
		return new ProcessDiagramCanvas(width, height, minX, minY);
	}

	/**
	 * 通过用xml表示的流程定义，生成PNG图片。
	 * @param bpmnXml xml表示的流程定义
	 * @return 生成的PNG图片
	 */
	public static InputStream generatePngDiagram(String bpmnXml) {
		return generateDiagram(bpmnXml, "png", Collections.<String> emptyList());
	}

	/**
	 * 通过用xml表示的流程定义，生成JPG图片。
	 * @param bpmnXml xml表示的流程定义
	 * @return 生成的PNG图片
	 */
	public static InputStream generateJpgDiagram(String bpmnXml) {
		return generateDiagram(bpmnXml, "jpg", Collections.<String> emptyList());
	}

	/**
	 * 通过用xml表示的流程定义和要求用高亮表示的图形组件，生成PNG图片。
	 * @param bpmnXml Xml表示Activiti流程定义
	 * @param highLightedActivities 要求用高亮表示的图形组件
	 * @return
	 */
	public static ProcessDiagramCanvas generateDiagram(String bpmnXml, List<String> highLightedActivities) {
		List<BPMNShap> bpmnShaps = extractBPMNShap(bpmnXml);
		List<BPMNEdge> bpmnEdges = extractBPMNEdge(bpmnXml);
		ProcessDiagramCanvas processDiagramCanvas = initProcessDiagramCanvas(bpmnShaps,bpmnEdges);
		for (BPMNShap bpmnShap : bpmnShaps) {
			drawActivity(processDiagramCanvas, bpmnShap, highLightedActivities);
		}
		drawSequenceFlows(processDiagramCanvas, bpmnEdges);
		return processDiagramCanvas;
	}

	/**
	 * 通过用xml表示的流程定义和要求用高亮表示的图形组件，生成PNG图片。
	 * @param bpmnXml xml表示的流程定义
	 * @param highLightedActivities 要求用高亮表示的图形组件
	 * @return 生成的PNG图片
	 */
	public static ProcessDiagramCanvas generateDiagram(Map<String,String> highLightedActivities, String bpmnXml) {
		List<BPMNEdge> bpmnEdges = extractBPMNEdge(bpmnXml);
		List<BPMNShap> bpmnShaps = extractBPMNShap(bpmnXml);
		ProcessDiagramCanvas processDiagramCanvas = initProcessDiagramCanvas(bpmnShaps, bpmnEdges);
		for (BPMNShap bpmnShap : bpmnShaps) {
			drawActivity(processDiagramCanvas, highLightedActivities, bpmnShap);
		}
		drawSequenceFlows(processDiagramCanvas, bpmnEdges);
		return processDiagramCanvas;
	}

	/**
	 * 通过用xml表示的流程定义、要求用高亮表示的图形组件和要求生成图片的类型，生成相应类型图片。
	 * 支持的图形类型，请参考<code>{@link javax.imageio.ImageIO}</code>
	 * @param bpmnXml xml表示的流程定义
	 * @param highLightedActivities 要求用高亮表示的图形组件
	 * @param imageType 要求生成图片的类型
	 * @return 生成的PNG图片
	 * @see javax.imageio.ImageIO
	 */
	public static InputStream generateDiagram(String bpmnXml,Map<String,String> highLightedActivities, String imageType) {
		return generateDiagram(highLightedActivities, bpmnXml).generateImage(imageType);
	}
	/**
	 * 通过用xml表示的流程定义、要求用高亮表示的图形组件和要求生成图片的类型，生成相应类型图片。
	 * 支持的图形类型，请参考<code>{@link javax.imageio.ImageIO}</code>
	 * @param bpmnXml xml表示的流程定义
	 * @param highLightedActivities 要求用高亮表示的图形组件
	 * @param imageType 要求生成图片的类型
	 * @return 生成的PNG图片
	 * @see javax.imageio.ImageIO
	 */
	public static InputStream generateDiagram(String bpmnXml, String imageType, List<String> highLightedActivities) {
		return generateDiagram(bpmnXml, highLightedActivities).generateImage(imageType);
	}
	/**
	 * 在指定画布上，画出指定的图形。如果指定的图形是要求高亮的图形组件highLightedActivities中的一个，刚将图形高亮表示。
	 * @param processDiagramCanvas 用于绘画图形的画布
	 * @param bpmnShap 要示绘画的图形
	 * @param highLightedActivities 要求用高亮表示的图形组件
	 */
	public static void drawActivity(ProcessDiagramCanvas processDiagramCanvas, BPMNShap bpmnShap, List<String> highLightedActivities) {

		GraphDrawInstruction drawInstruction = graphDrawInstructions.get(bpmnShap.getType());
		if (drawInstruction != null) {

			drawInstruction.draw(processDiagramCanvas, bpmnShap);

			// Gather info on the multi instance marker
			boolean multiInstanceSequential = false, multiInstanceParallel = false, collapsed = false;
			Properties properties = bpmnShap.getProperties();
			String isSequential = null;
			if (properties != null) {
				isSequential = (String) bpmnShap.getProperties().get("isSequential");
			}

			if (isSequential != null) {
				if ("true".equals(isSequential)) {
					multiInstanceSequential = true;
				} else {
					multiInstanceParallel = true;
				}
			}

			// Gather info on the collapsed marker
			Boolean expanded = bpmnShap.isExpanded();
			if (expanded != null) {
				collapsed = !expanded;
			}

			// Actually draw the markers
			processDiagramCanvas.drawActivityMarkers((int) Math.round(bpmnShap.getX()), (int) Math.round(bpmnShap.getY()), (int) Math.round(bpmnShap.getWidth()),
					(int) Math.round(bpmnShap.getHeight()), multiInstanceSequential, multiInstanceParallel, collapsed);

			// Draw highlighted activities
			if (highLightedActivities.contains(bpmnShap.getBpmnElement())) {
				drawHighLight(processDiagramCanvas, bpmnShap);
			}

		}
	}

	/**
	 * 在指定画布上，画出指定的图形。如果指定的图形是要求高亮的图形组件highLightedActivities中的一个，刚将图形高亮表示。
	 * @param processDiagramCanvas 用于绘画图形的画布
	 * @param highLightedActivities 要求用高亮表示的图形组件
	 * @param bpmnShap 要示绘画的图形
	 */
	public static void drawActivity(ProcessDiagramCanvas processDiagramCanvas,Map<String,String> highLightedActivities, BPMNShap bpmnShap) {
		GraphDrawInstruction drawInstruction = graphDrawInstructions.get(bpmnShap.getType());
		if (drawInstruction != null) {

			drawInstruction.draw(processDiagramCanvas, bpmnShap);

			// Gather info on the multi instance marker
			boolean multiInstanceSequential = false, multiInstanceParallel = false, collapsed = false;
			Properties properties = bpmnShap.getProperties();
			String isSequential = null;
			if (properties != null) {
				isSequential = (String) bpmnShap.getProperties().get("isSequential");
			}
			if (isSequential != null) {
				if ("true".equals(isSequential)) {
					multiInstanceSequential = true;
				} else {
					multiInstanceParallel = true;
				}
			}

			// Gather info on the collapsed marker
			Boolean expanded = bpmnShap.isExpanded();
			if (expanded != null) {
				collapsed = !expanded;
			}

			// Actually draw the markers
			processDiagramCanvas.drawActivityMarkers((int) Math.round(bpmnShap.getX()), (int) Math.round(bpmnShap.getY()), (int) Math.round(bpmnShap.getWidth()),
					(int) Math.round(bpmnShap.getHeight()), multiInstanceSequential, multiInstanceParallel, collapsed);

			// Draw highlighted activities
			if (highLightedActivities.containsKey(bpmnShap.getBpmnElement())) {
				String color = highLightedActivities.get(bpmnShap.getBpmnElement());
				//if(color!=null){
				drawHighLight(processDiagramCanvas, bpmnShap, color);
				//}
			}
//			else{
//				IFlowStatus iFlowStatus=(IFlowStatus) AppUtil.getBean(IFlowStatus.class); 
//				drawHighLight(processDiagramCanvas, bpmnShap, iFlowStatus.getDefaultColor());
//			}

		}
	}

	/**
	 * 在指定画布上，画出指定的连线。
	 * @param processDiagramCanvas 用于绘画图形的画布
	 * @param bpmnEdges 要示绘画的连线
	 */
	public static void drawSequenceFlows(ProcessDiagramCanvas processDiagramCanvas, List<BPMNEdge> bpmnEdges) {
		for (BPMNEdge bpmnEdge : bpmnEdges) {
			processDiagramCanvas.drawSequenceflowWidthLabel(bpmnEdge);
		}
	}


	private static Map<String, Short> getHighLightedMap(List<BpmProStatus> highLightedActivities) {
		Map<String, Short> map = new HashMap<String, Short>();
		for (BpmProStatus status : highLightedActivities) {
			map.put(status.getNodeid(), status.getStatus());
		}
		return map;
	}


	private static void drawHighLight(ProcessDiagramCanvas processDiagramCanvas, BPMNShap bpmnShap) {
		processDiagramCanvas.drawHighLight((int) Math.round(bpmnShap.getX()), (int) Math.round(bpmnShap.getY()), (int) Math.round(bpmnShap.getWidth()), (int) Math.round(bpmnShap.getHeight()));

	}


	private static void drawHighLight(ProcessDiagramCanvas processDiagramCanvas, BPMNShap bpmnShap, String color) {
		processDiagramCanvas.drawHighLight((int) Math.round(bpmnShap.getX()), (int) Math.round(bpmnShap.getY()), (int) Math.round(bpmnShap.getWidth()), (int) Math.round(bpmnShap.getHeight()), color);

	}
	

	
	private static void drawHighLight(ProcessDiagramCanvas processDiagramCanvas, BPMNShap bpmnShap, Short status) {
		processDiagramCanvas.drawHighLight((int) Math.round(bpmnShap.getX()), (int) Math.round(bpmnShap.getY()), (int) Math.round(bpmnShap.getWidth()), (int) Math.round(bpmnShap.getHeight()), status);

	}

	private static FontMetrics getFontMetrics(){
		BufferedImage processDiagram = new BufferedImage(2, 2, 2);
		Graphics2D g = processDiagram.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setPaint(Color.black);
		Font font = new Font("宋体", 1, 12);
		g.setFont(font);
		FontMetrics fontMetrics = g.getFontMetrics();
		return fontMetrics;
	}

	/**
	 * 根据所有将绘画的图形组件和连线，计算所需画布的大小。
	 * 返回用数组类型<code>Point2D.Double[]<code>表示的画布大小。Point2D.Double[0]<code>中的x,y分别表示所有将绘画的图形组件和连线
	 * 中的最小坐标。Point2D.Double[1]<code>中的x,y分别表示所有将绘画的图形组件和连线中的最大坐标。
	 * @param shaps 所有将绘画的图形
	 * @param edges 所有将绘画的连线
	 * @return 画布的大小。
	 */
	public static Point2D.Double[] caculateCanvasSize(List<BPMNShap> shaps, List<BPMNEdge> edges) {
		double minX = Double.MAX_VALUE;
		double minY = Double.MAX_VALUE;
		double maxX = 0;
		double maxY = 0;

		for (BPMNShap shap : shaps) {
			if (shap.getX() < minX) {
				minX = shap.getX();
			}
			if (shap.getY() < minY) {
				minY = shap.getY();
			}

			if (shap.getX()+shap.getWidth() > maxX) {
				maxX = shap.getX()+shap.getWidth();
			}

			if (shap.getY()+shap.getHeight() > maxY) {
				maxY = shap.getY()+shap.getHeight();
			}
		}

		for (BPMNEdge edge : edges) {
			for (Point2D.Double point : edge.getPoints()) {
				if (point.getX() < minX) {
					minX = point.getX();
				}
				if (point.getY() < minY) {
					minY = point.getY();
				}

				if (point.getX() > maxX) {
					maxX = point.getX();
				}

				if (point.getY() > maxY) {
					maxY = point.getY();
				}
			}
			
			//计算线上标签的最大最小坐标
			String label = edge.getName()==null?"":edge.getName();
			Point2D.Double midPoint = edge.getMidpoint();
			DirectionType directionType=edge.getDirection();
			FontMetrics fontMetrics = getFontMetrics();
			double labelMinX;
			double labelMinY;
			double labelMaxX;
			double labelMaxY;
			
			if(directionType==DirectionType.UpToDown){
				labelMinX = midPoint.getX() + fontMetrics.getHeight() / 2;
				labelMinY = midPoint.getY();
			}else if(directionType==DirectionType.DownToUp){
				labelMinX = midPoint.getX() - fontMetrics.stringWidth(label) - fontMetrics.getHeight() / 2;
				labelMinY = midPoint.getY() - fontMetrics.getHeight() / 2 - fontMetrics.getHeight();
			}else if(directionType==DirectionType.LeftToRight){
				labelMinX = midPoint.getX() - fontMetrics.stringWidth(label) / 2;
				labelMinY = midPoint.getY();
			}else{
				labelMinX = fontMetrics.stringWidth(label) / 2;
				labelMinY = midPoint.getY()+ fontMetrics.getHeight() - fontMetrics.getHeight();
			}
			
			labelMaxX=labelMinX+fontMetrics.stringWidth(label);
			labelMaxY=labelMinY+fontMetrics.getHeight();
			
			if(labelMinX<minX){
				minX=labelMinX;
			}
			if(labelMinY<minY){
				minY=labelMinY;
			}
			if(labelMaxX>maxX){
				maxX=labelMaxX;
			}
			if(labelMaxY>maxY){
				maxY=labelMaxY;
			}
			
		}
		
		return new Point2D.Double[]{
			new Point2D.Double(minX, minY),
			new Point2D.Double(maxX, maxY)
		};
	}

	/**
	 * 图形和连线的坐标点中存在负值时，就对整个流程定义中的图形和连线的进行移位。
	 * 
	 * @param processDefinition 所要进行移位的图形。
	 * @param x 图形中，最的x坐标点。
	 * @param y 图形中，最的y坐标点。
	 * @return
	 */
	private static void shiftProcessDefinition(List<BPMNShap> shaps,List<BPMNEdge> edges, double x, double y) {

			for (BPMNShap shap : shaps) {
				shap.setX(shap.getX() - x);
				shap.setY(shap.getY() - y);
			}
			for (BPMNEdge edge : edges) {
				for(Point2D.Double point:edge.getPoints()){
					point.x=point.getX()-x;
					point.y=point.getY()-y;
				}
				edge.getMidpoint().x=edge.getMidpoint().getX()-x;
				edge.getMidpoint().y=edge.getMidpoint().getY()-y;
			}
	}
	
	/**
	 * 获取定义流程的xml文档中的所有图形组件。
	 * @param bpmnXml 定义流程的xml文档
	 * @return 从xml文档中取得的组件的列表
	 */
	public static List<BPMNShap> extractBPMNShap(String bpmnXml) {
		bpmnXml = bpmnXml.replace("xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\"", "");
		Document doc = Dom4jUtil.loadXml(bpmnXml);
		Element root = doc.getRootElement();

		List<Element> shaps = root.selectNodes("//bpmndi:BPMNShape");
		List<BPMNShap> bpmnShaps = new ArrayList<BPMNShap>();
		for (Element shap : shaps) {
			BPMNShap bpmnShap = new BPMNShap();

			// BPMNShap节点属性
			bpmnShap.setBpmnElement(shap.attributeValue("bpmnElement"));
			bpmnShap.setChoreographyActivityShape(shap.attributeValue("choreographyActivityShape"));
			bpmnShap.setHorizontal(getBooleanAttr(shap, "isHorizontal"));
			bpmnShap.setExpanded(getBooleanAttr(shap, "isExpanded"));
			bpmnShap.setMarkerVisible(getBooleanAttr(shap, "isMarkerVisible"));
			bpmnShap.setMessageVisible(getBooleanAttr(shap, "isMessageVisible"));
			bpmnShap.setParticipantBandKind(shap.attributeValue("participantBandKind"));

			// Bounds节点属性，坐标点
			Element bound = (Element) shap.selectSingleNode("./omgdc:Bounds");
			bpmnShap.setX(Double.parseDouble(bound.attributeValue("x")));
			bpmnShap.setY(Double.parseDouble(bound.attributeValue("y")));
			bpmnShap.setWidth(Double.parseDouble(bound.attributeValue("width")));
			bpmnShap.setHeight(Double.parseDouble(bound.attributeValue("height")));

			// 组件类型
			Element component = (Element) root.selectSingleNode("//*[@id='" + bpmnShap.getBpmnElement() + "']");
			if(component==null) continue;
			BPMNShapType type = getBPMNShapType(component);
			
			bpmnShap.setType(type);
			// 组件标签名字
			bpmnShap.setName(component.attributeValue("name"));
			// 设置其它属性
			setBPMNShapProperties(component, bpmnShap);
			bpmnShaps.add(bpmnShap);
		}
		return bpmnShaps;
	}

	/**
	 * 获取xml文件中定义的所有连线。
	 * @param bpmnXml 定义流程的xml文档
	 * @return 从xml文档中取得的连线的列表
	 */
	public static List<BPMNEdge> extractBPMNEdge(String bpmnXml) {
		List<BPMNEdge> bpmnEdges = new ArrayList<BPMNEdge>();

		bpmnXml = bpmnXml.replace("xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\"", "");
		Document doc = Dom4jUtil.loadXml(bpmnXml);
		Element root = doc.getRootElement();

		List<Element> edges = root.selectNodes("//bpmndi:BPMNEdge");
		for (Element edge : edges) {
			BPMNEdge bpmnEdge = new BPMNEdge();
			// 取得所有点
			List<Point2D.Double> points = new ArrayList<Point2D.Double>();
			List<Element> waypoints = edge.selectNodes("./omgdi:waypoint");
			for (Element waypoint : waypoints) {
				double x = Double.parseDouble(waypoint.attributeValue("x"));
				double y = Double.parseDouble(waypoint.attributeValue("y"));
				Point2D.Double point = new Point2D.Double(x, y);
				points.add(point);
			}
			bpmnEdge.setPoints(points);
			// 取得标签名字
			String bpmnElement = edge.attributeValue("bpmnElement");
			Element component = (Element) root.selectSingleNode("//sequenceFlow[@id='" + bpmnElement + "']");
			bpmnEdge.setName(component.attributeValue("name"));
			// 计算中点
			double x = 0, y = 0;
			DirectionType directionType;
			List<Double> lens = new ArrayList<Double>();
			for (int i = 1; i < points.size(); i++) {
				lens.add(Math.abs(points.get(i - 1).getX() - points.get(i).getX()) + Math.abs(points.get(i - 1).getY() - points.get(i).getY()));
			}
			double halfLen = 0;
			for (double len : lens) {
				halfLen += len;
			}
			halfLen = halfLen / 2;

			double accumulativeLen = 0;

			int i;
			for (i = 0; i < lens.size(); i++) {
				accumulativeLen += lens.get(i);
				if (accumulativeLen > halfLen) {
					break;
				}
			}

			// x坐标相等，
			if (points.get(i).getX() == points.get(i + 1).getX()) {
				// 前一个点在后一个点的上边
				if (points.get(i).getY() < points.get(i + 1).getY()) {
					// accumulativeLen-halfLen=points.get(i+1).getY()-y;
					y = halfLen - accumulativeLen + points.get(i + 1).getY();
					directionType = DirectionType.UpToDown;
				} else {
					// accumulativeLen-halfLen=y-points.get(i+1).getY();
					y = accumulativeLen - halfLen + points.get(i + 1).getY();
					directionType = DirectionType.DownToUp;
				}
				x = points.get(i).getX();

			} else {// y坐标相等，
					// 前一个点在后一个点的左边
				if (points.get(i).getX() < points.get(i + 1).getX()) {
					// accumulativeLen-halfLen=points.get(i+1).getX()-x;
					x = halfLen - accumulativeLen + points.get(i + 1).getX();
					directionType = DirectionType.LeftToRight;
				} else {
					// accumulativeLen-halfLen=x-points.get(i+1).getX();
					x = accumulativeLen - halfLen + points.get(i + 1).getX();
					directionType = DirectionType.RightToLef;
				}
				y = points.get(i).getY();
			}
			Point2D.Double midpoint = new Point2D.Double(x, y);

			// 取得中点
			bpmnEdge.setMidpoint(midpoint);
			// 取得中点所有直线的方向
			bpmnEdge.setDirection(directionType);

			bpmnEdges.add(bpmnEdge);

		}

		return bpmnEdges;

	}

	/**
	 * 设置BPMNShap的properties属性。properties中存放的是与特定类型的图形组件相关的图形组件的属性。
	 * @param component 流程定义的xml文档中与<code>bpmnShap</code>对应的节点元素
	 * @param bpmnShap 将对其进行操作的图形组件
	 * @return 对其进行properties属性设置后的图形组件
	 */
	private static BPMNShap setBPMNShapProperties(Element component, BPMNShap bpmnShap) {
		BPMNShapType type = bpmnShap.getType();
		Properties properties = bpmnShap.getProperties();
		if (properties == null) {
			properties = new Properties();
		}

		if (type == BPMNShapType.Task 
				|| type == BPMNShapType.ScriptTask 
				|| type == BPMNShapType.ServiceTask 
				|| type == BPMNShapType.BusinessRuleTask
				|| type == BPMNShapType.ManualTask 
				|| type == BPMNShapType.UserTask
				|| type == BPMNShapType.CallActivity
				|| type == BPMNShapType.SubProcess) {
			Element multiInstanceLoopCharacteristics = (Element) component.selectSingleNode("./multiInstanceLoopCharacteristics");
			if (multiInstanceLoopCharacteristics != null) {
				String isSequential = multiInstanceLoopCharacteristics.attributeValue("isSequential");
				properties.put("isSequential", isSequential);
			}
		}
		
		if (type == BPMNShapType.ErrorEvent){
			Element errorEventDefinition=(Element) component.selectSingleNode("errorEventDefinition");
			String errorRef =errorEventDefinition.attributeValue("errorRef");
			properties.put("errorRef", errorRef);
		}

		bpmnShap.setProperties(properties);
		return bpmnShap;
	}

	/**
	 * 获取xml文档元素的Boolean类型的属性。
	 * @param element xml文档元素
	 * @param attr xml文档元素的属性
	 * @return
	 */
	private static Boolean getBooleanAttr(Element element, String attr) {
		String attrVal = element.attributeValue(attr);
		if (attrVal != null) {
			return attrVal.equalsIgnoreCase("true") ? true : false;
		} else {
			return null;
		}
	}

	/**
	 * 根据流程定义的xml文档中节点图形节点元素，获取相应的图形节点的类型。
	 * @param component 流程定义的xml文档中节点图形节点元素
	 * @return 图形节点元素的类型
	 */
	public static BPMNShapType getBPMNShapType(Element component) {
		BPMNShapType retVal = BPMNShapType.UnknowType;
		
		if (component.getName().equals("startEvent")) {
			retVal = BPMNShapType.StartEvent;
		} else if (component.getName().equals("endEvent")) {
			Element errorEventDefinition=(Element) component.selectSingleNode("errorEventDefinition");
			if(errorEventDefinition==null){
				retVal = BPMNShapType.EndEvent;
			}else{
				retVal = BPMNShapType.ErrorEvent;
			}
		} else if (component.getName().equals("exclusiveGateway")) {
			retVal = BPMNShapType.ExclusiveGateway;
		} else if (component.getName().equals("inclusiveGateway")) {
			retVal = BPMNShapType.InclusiveGateway;
		} else if (component.getName().equals("parallelGateway")) {
			retVal = BPMNShapType.ParallelGateway;
		} else if (component.getName().equals("scriptTask")) {
			retVal = BPMNShapType.ScriptTask;
		} else if (component.getName().equals("serviceTask")) {
			retVal = BPMNShapType.ServiceTask;
		} else if (component.getName().equals("businessRuleTask")) {
			retVal = BPMNShapType.BusinessRuleTask;
		} else if (component.getName().equals("task")) {
			retVal = BPMNShapType.Task;
		} else if (component.getName().equals("manualTask")) {
			retVal = BPMNShapType.ManualTask;
		} else if (component.getName().equals("userTask")) {
			retVal = BPMNShapType.UserTask;
		} else if (component.getName().equals("sendTask")) {
			retVal = BPMNShapType.SendTask;
		} else if (component.getName().equals("receiveTask")) {
			retVal = BPMNShapType.ReceiveTask;
		} else if (component.getName().equals("subProcess")) {
			retVal = BPMNShapType.SubProcess;
		} else if (component.getName().equals("callActivity")) {
			retVal = BPMNShapType.CallActivity;
		} else if (component.getName().equals("intermediateCatchEvent")) {
			retVal = BPMNShapType.IntermediateCatchEvent;
		} else if (component.getName().equals("adHocSubProcess")) {
			retVal = BPMNShapType.ComplexGateway;
		} else if (component.getName().equals("eventBasedGateway")) {
			retVal = BPMNShapType.EventBasedGateway;
		} else if (component.getName().equals("transaction")) {
			retVal = BPMNShapType.Transaction;
		} else if (component.getName().equals("participant")) {
			String id = component.attributeValue("id");
			String processRef = component.attributeValue("processRef");
			Element root = (Element) component.getDocument().getRootElement();
			Element process = (Element) root.selectSingleNode("//*[@id='" + processRef + "']");
			if (process.element("laneSet") != null) {
				Element shap = (Element) root.selectSingleNode("//*[@bpmnElement='" + id + "']");
				String isHorizontal = shap.attributeValue("isHorizontal");
				if (isHorizontal != null && isHorizontal.equalsIgnoreCase("false")) {
					retVal = BPMNShapType.VPool;
				} else {
					retVal = BPMNShapType.HPool;
				}
			}
		} else if (component.getName().equals("lane")) {
			String id = component.attributeValue("id");
			Element root = (Element) component.getDocument().getRootElement();
			Element shap = (Element) root.selectSingleNode("//*[@bpmnElement='" + id + "']");
			String isHorizontal = shap.attributeValue("isHorizontal");
			if (isHorizontal != null && isHorizontal.equalsIgnoreCase("false")) {
				retVal = BPMNShapType.VLane;
			} else {
				retVal = BPMNShapType.HLane;
			}
		}
		return retVal;
	}
}
