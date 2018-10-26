package com.hotent.Markovchain.model.Markovchain;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.hotent.platform.dao.bpm.BpmDao;
import com.hotent.platform.model.bpm.BpmDefinition;
import com.hotent.platform.service.bpm.BpmDefinitionService;
import com.hotent.platform.service.bpm.BpmService;

//import com.hotent.core.util.Dom4jUtil;
import edu.hrbeu.MDA.DBAccess.DataResourceImp;
import com.hotent.Markovchain.*;

/**
 * 解析BPM流程xml类
 * 
 * @author zl
 * 
 */
public class ParseXML {
	/**
	 * BPM的XML的命名空间
	 */
	private final static String BPM_XML_NS = "xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\"";
	// 存储defXml
	private static String defXml = null;
	// 存储节点的各种信息
	private List<NodeSet> nodeList = new ArrayList<NodeSet>();
	// 创建dao对象
	private static DataResourceImp dao = new DataResourceImp();
	// 开始节点类型
	private static final String START_TYPE = "startEvent";
	// 存储节点名与节点映射
	private Map<String, NodeSet> idToNodeSet = new HashMap<String, NodeSet>();
	// 整个图的发生时间与发生量映射
	private Map<String, Integer> timeToCount = new HashMap<String, Integer>();
	// 发生时间列表
	private List<String> timeList = new ArrayList<String>();
	@Resource
	private BpmDao bpmDao;
	
	@Resource
	private BpmService bpmservice;

	@Resource
	private BpmDefinitionService bpmdefinitionservice;
	/**
	 * 将符合格式的xml字符串 转化成 Document
	 * @param s
	 * @return
	 */
	public static Document loadXml(String s)
	{
		Document document = null;
		try
		{
			document = DocumentHelper.parseText(s);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return document;
	}
	/**
	 * @author zl
	 * @说明 解析XML产生节点列表并保存节点关系
	 * @param defId
	 *            流程定义id
	 */
	@SuppressWarnings("unchecked")
	public void analysisXml(Long defid) {
		// 根据BPM_DEFINITION表中的ACTDEPLOYID得到格式化后的defXml
		String defId = defid.toString();
		defXml = bpmDao.getDefXmlByDeployId(defId);
		defXml = defXml.replace(BPM_XML_NS, "");
		Document doc = loadXml(defXml);
		Element root = doc.getRootElement();
		List sequenceFlows = root.selectNodes("//sequenceFlow");
		// 存储流程节点之间的关系
		List<SequenceFlow> seqList = new ArrayList<SequenceFlow>();
		for (Object flow : sequenceFlows) {
			String id = ((Element) flow).attributeValue("id");
			String sourceRef = ((Element) flow).attributeValue("sourceRef");
			String targetRef = ((Element) flow).attributeValue("targetRef");
			seqList.add(new SequenceFlow(id, sourceRef, targetRef));
		}
		List list = root.selectNodes("//bpmndi:BPMNShape");
		for (int i = 0; i < list.size(); i++) {
			Element el = (Element) list.get(i);
			// Exclude Pool and Lane components
			String id = el.attributeValue("bpmnElement");
			Element procEl = (Element) root
					.selectSingleNode("//process/descendant::*[@id='" + id
							+ "']");
			if (procEl != null) {
				String type = procEl.getName();
				if (type.equals("serviceTask")) {
					Element ext = procEl.element("extensionElements");
					Element service = ext.element("serviceType");
					type = service.attributeValue("value");
				}
				if (!"subProcess".equals(type) && !"callActivity".equals(type)) {
					Element multiObj = procEl
							.element("multiInstanceLoopCharacteristics");
					if (multiObj != null && !"subProcess".equals(type))
						type = "multiUserTask";
				}
				String name = procEl.attributeValue("name");
				String defKey = procEl.attributeValue("calledElement");
				NodeSet nodeSet = new NodeSet(defid, id, name, type);
				if(defKey!=null)
					nodeSet.setDefKey(defKey);
				nodeList.add(nodeSet);
				idToNodeSet.put(id, nodeSet);
			}
		}
		// 保存节点之间的前后序列关系
		for (SequenceFlow seq : seqList) {
			for (NodeSet node : nodeList) {
				String sourceId = seq.getSourceRef();
				String targetId = seq.getTargetRef();
				String nodeId = node.getNodeId();
				if (sourceId.equals(nodeId)) {
					// 设置该节点的后续节点
					node.getNextNode().add(idToNodeSet.get(targetId));
					// 设置该节点的前序节点
					idToNodeSet.get(targetId).getPreNode().add(node);
					break;
				}
			}

		}
	}

	/**
	 * @author 史欣慧
	 * @说明 解析XML产生节点列表并保存节点关系
	 * @param defId
	 *            流程定义id
	 */
	@SuppressWarnings("unchecked")
	public List<NodeSet> analysisXmlReturnList(Long defid) {
		// 根据BPM_DEFINITION表中的ACTDEPLOYID得到格式化后的defXml
		String defId = defid.toString();
		BpmDefinition bpmdefinition = bpmdefinitionservice.getById(defid);
		String deployid = bpmdefinition.getActDeployId().toString();
		String xml = bpmdefinition.getDefXml();
		
		defXml = bpmservice.getDefXmlByDeployId(defId);
		defXml = defXml.replace(BPM_XML_NS, "");
		Document doc = loadXml(defXml);
		Element root = doc.getRootElement();
		List sequenceFlows = root.selectNodes("//sequenceFlow");
		// 存储流程节点之间的关系
		List<SequenceFlow> seqList = new ArrayList<SequenceFlow>();
		for (Object flow : sequenceFlows) {
			String id = ((Element) flow).attributeValue("id");
			String sourceRef = ((Element) flow).attributeValue("sourceRef");
			String targetRef = ((Element) flow).attributeValue("targetRef");
			seqList.add(new SequenceFlow(id, sourceRef, targetRef));
		}
		List list = root.selectNodes("//bpmndi:BPMNShape");
		for (int i = 0; i < list.size(); i++) {
			Element el = (Element) list.get(i);
			// Exclude Pool and Lane components
			String id = el.attributeValue("bpmnElement");
			Element procEl = (Element) root
					.selectSingleNode("//process/descendant::*[@id='" + id
							+ "']");
			if (procEl != null) {
				String type = procEl.getName();
				if (type.equals("serviceTask")) {
					Element ext = procEl.element("extensionElements");
					Element service = ext.element("serviceType");
					type = service.attributeValue("value");
				}
				if (!"subProcess".equals(type) && !"callActivity".equals(type)) {
					Element multiObj = procEl
							.element("multiInstanceLoopCharacteristics");
					if (multiObj != null && !"subProcess".equals(type))
						type = "multiUserTask";
				}
				String name = procEl.attributeValue("name");
				String defKey = procEl.attributeValue("calledElement");
				NodeSet nodeSet = new NodeSet(defid, id, name, type);
				if(defKey!=null)
					nodeSet.setDefKey(defKey);
				
					nodeList.add(nodeSet);
					idToNodeSet.put(id, nodeSet);
				
				
			}
		}
		// 保存节点之间的前后序列关系
		for (SequenceFlow seq : seqList) {
			for (NodeSet node : nodeList) {
				String sourceId = seq.getSourceRef();
				String targetId = seq.getTargetRef();
				String nodeId = node.getNodeId();
				if (sourceId.equals(nodeId)) {
					// 设置该节点的后续节点
					node.getNextNode().add(idToNodeSet.get(targetId));
					// 设置该节点的前序节点
					idToNodeSet.get(targetId).getPreNode().add(node);
					break;
				}
			}

		}
		List<NodeSet> result = new ArrayList<NodeSet>();
		for(NodeSet node : nodeList){
			if(node.getNodeName().indexOf("开始")==-1&&node.getNodeName().indexOf("结束")==-1)
				result.add(node);
		}
	
		return result;
	}

	public int getUserTaskCount(String xmlStr){
		try {
			Document document = DocumentHelper.parseText(xmlStr);
			Element root = document.getRootElement();
			System.out.println(root.getName());
			List<Element> elementList=root.elements();
			System.out.println(elementList.size());
			for(Element element : elementList){
				System.out.println(element.getName());
				//List list = element.attributes();
				String nodeName = element.attribute("id").getText();
				//System.out.println(nodeName);
				//System.out.println(nodeName.substring(0, nodeName.length()-1));
				String subNodeName = nodeName.substring(0, nodeName.length()-1);
				if(subNodeName.equals("UserTask")){
					System.out.println("创建操作图");
				}
				
				
				
			}
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return 7;
	}
	/**
	 * @author zl
	 * @param array二维数组
	 * @说明 计算指定流程图所有节点的发生时间和发生量
	 */
	public List<NodeSet> computeAll(String[][] array) {

		String projectId = array[0][0];
		Long defId = Long.parseLong(array[1][0]);
		ConvertTimeToMap(array);
		analysisXml(defId);
		initNodesTC();
		int startLocal = getStartIndex();
		NodeSet startNode = nodeList.get(startLocal);
		String time = null;
		List<NodeSet> nextList = new ArrayList<NodeSet>();
		int count = 0;
		for (int i = 0; i < timeToCount.size(); ++i) {
			time = timeList.get(i);
			count = timeToCount.get(time);
			for (int j = 0; j < count; ++j) {
				initHappen();
				nextList = computeTimeAndCount(time, startNode);
				computeList(time, nextList);
			}
		}
		return nodeList;
	}

	/**
	 * @author zl
	 * @说明 初始化各节点发生状态
	 */
	public void initHappen() {
		for (NodeSet node : nodeList) {
			node.setHappened(false);
		}
	}

	/**
	 * @author zl
	 * @param time
	 * @param nodes
	 * @return
	 * @说明 计算节点列表中所有节点及其后续节点的发生量
	 */
	public List<NodeSet> computeList(String time, List<NodeSet> nodes) {

		List<NodeSet> nodesList = new ArrayList<NodeSet>();
		// List<String> nodeIds = new ArrayList<String>();
		for (NodeSet node : nodes) {
			List<NodeSet> tempList = computeTimeAndCount(time, node);
			if (tempList != null)
				nodesList.addAll(tempList);
		}

		// 删除ArrayList中重复元素
		HashSet<NodeSet> hSet = new HashSet<NodeSet>(nodesList);
		nodesList.clear();
		nodesList.addAll(hSet);
		if (nodesList.size() != 0) {
			computeList(time, nodesList);
		}
		return nodesList;
	}

	/**
	 * @param time
	 *            发生时间
	 * @param nodeIndex
	 *            节点序号
	 * @return 该节点后续节点的列表
	 * @author zl
	 * @说明 计算该节点后续节点的发生时间和发生量，亦可计算开始节点发生量
	 */
	public List<NodeSet> computeTimeAndCount(String time, NodeSet node) {

		int thiscount = 0;
		// 开始节点发生量
		if (node.getPreNode().size() == 0) {
			thiscount = node.getOccMap().get(time) + 1;
			node.getOccMap().put(time, thiscount);
			node.setHappened(true);
		}
		// 本节点并发生，才计算后续节点
		if (node.isHappened()) {
			// 节点后续节点个数
			int num = node.getNextNode().size();
			double rand = Math.random();
			// 计算下一个节点的发生量
			//结束节点无后续节点
			if(num==0)return null;
			//后续节点为一个
			if(num==1){
				thiscount = node.getNextNode().get(0).getOccMap().get(time) + 1;
				node.getNextNode().get(0).getOccMap().put(time, thiscount);
				node.getNextNode().get(0).setHappened(true);
				return node.getNextNode();
			}
			//后续多个节点的情况
			if(num > 1){
			double tempa,tempb = 0;
			for(int i = 0;i<num;i++){
				tempa = tempb;
				tempb+=node.getNextNode().get(i).getRate();
				if(rand>=tempa&&rand<tempb){
					thiscount = node.getNextNode().get(i).getOccMap().get(time) + 1;
					node.getNextNode().get(i).getOccMap().put(time, thiscount);
					node.getNextNode().get(i).setHappened(true);
					break;
				}
			}
			if(rand==1.0){
				thiscount = node.getNextNode().get(num - 1).getOccMap().get(time) + 1;
				node.getNextNode().get(num - 1).getOccMap().put(time, thiscount);
				node.getNextNode().get(num - 1).setHappened(true);
			}
			}
			
			return node.getNextNode();
		}
		return null;
	}

	/**
	 * @author zl
	 * @说明 ：初始化各个节点的发生时间和发生量并生成发生时间列表
	 */
	public void initNodesTC() {
		if (timeToCount.size() != 0) {
			Set<String> keySet = timeToCount.keySet();
			for (NodeSet node : nodeList) {
				Iterator<String> it = keySet.iterator();
				while (it.hasNext()) {
					String key = it.next();
					node.getOccMap().put(key, 0);
					timeList.add(key);
				}
			}
		}
	}

	/**
	 * @author zl
	 * @return 开始节点在nodeList列表中的位置
	 */
	public int getStartIndex() {
		int index = 0;
		for (NodeSet node : nodeList) {
			if (START_TYPE.equals(node.getNodeType()))
				break;
			++index;
		}
		return index;
	}

	/**
	 * @author zl
	 * @说明：将二维数组中的发生时间和发生量保存为map映射
	 * @param array
	 */
	public void ConvertTimeToMap(String[][] array) {
		for (int i = 2; i < array.length; i++) {
			if(array[i][0]==null)break;
			timeToCount.put(array[i][0], Integer.parseInt(array[i][1]));
		}
	}
	
	public static void main(String[] args) {
//		ParseXML pt = new ParseXML();
//		// 测试张静图
//		Long defId = 10000008130033L;
//		// 测试张静2图
//		//defId = 10000007850004L;
//		// 发生量计算测试图
//		// defId = 10000008020004L;
//		Long projectId = 1L;
//		String[][] array = { { projectId.toString() }, { defId.toString() },
//				{ "9:10", "10" }, { "9:20", "20" }, { "9:30", "20" } };
//		long startTime = System.currentTimeMillis();
//		List<NodeSet> listTo王乙闲 = pt.computeAll(array);
//		System.out.println(listTo王乙闲);
//		long endTime = System.currentTimeMillis();
//		System.out.println("计算时间:" + (endTime - startTime));
//		for (NodeSet node : pt.nodeList) {
//			System.out.println();
//			System.out.println(node.getNodeId());
//			System.out.println(node.getNodeName());
//			String arr[][] = node.getTimeAndCount();
//			for (int i = 0; i < node.getOccMap().size(); i++) {
//				for (int j = 0; j < 2; j++) {
//					System.out.print(arr[i][j] + " ");
//				}
//				System.out.println();
//			}
//
//		}
//		String resource = "org/mybatis/example/mybatis-config.xml";
//		InputStream inputStream;
//		try {
//			inputStream = Resources.getResourceAsStream(resource);
//			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		ApplicationContext ctx  = new FileSystemXmlApplicationContext("/src/edu/hrbeu/MDA/DBAccess/conf/app-resources.xml"); 
		SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) ctx.getBean("sqlSessionFactory");
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Configuration configuration= sqlSession.getConfiguration();

		System.out.println(configuration);
		
	}
}
