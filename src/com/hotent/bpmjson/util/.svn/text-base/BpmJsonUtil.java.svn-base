package com.hotent.bpmjson.util;

import java.io.File;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;

import com.hotent.bpmjson.model.H5Model;
import com.hotent.core.bpm.graph.activiti.BPMNShap;
import com.hotent.core.bpm.graph.activiti.ProcessDiagramGenerator;
import com.hotent.core.util.Dom4jUtil;
import com.hotent.core.util.StringUtil;

/**
 * Bpm的xml与Json转化的工具类
 * 
 * @author Administrator
 */
public class BpmJsonUtil {
	
	public static final String Q_GROUP = "Q.Group";// 组节点
	public static final String Q_NODE = "Q.Node";// 普通节点
	public static final String Q_EDGE = "Q.Edge";// 连线
	private static volatile long preIndex = 0;// 前缀索引，保证节点或连线id不重复
	private static final int DEFAULT_SCALE = 1;// 默认缩放比例
	private static final int DEFAULT_TX = 200;// 默认X坐标
	private static final int DEFAULT_TY = 200;// 默认Y坐标
	

	/**
	 * 转化BPMN-XML数据为指定格式的Map（仅用来H5展示）
	 * 
	 * @param bpmnXml
	 *            用来解析节点和连线
	 * @param designXml
	 *            用来解析节点上额外的属性
	 */
	public static Map<String, Object> parseBpmnXml2Map(String bpmnXml,
			String designXml) {
		List<H5Model> h5ObjList = parseBpmnXml2ModelList(bpmnXml, designXml);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("version", "2.0");
		map.put("datas", h5ObjList);
		map.put("scale", DEFAULT_SCALE);// 比例
		map.put("tx", DEFAULT_TX);// x坐标
		map.put("ty", DEFAULT_TY);// y坐标
		return map;
	}
	
	/**
	 * 把节点和连线（H5Model）转换为Map
	 * 
	 * @param h5ObjList
	 *            节点和连线集合
	 * @return
	 */
	public static Map<String, Object> parseH5Models2Map(List<H5Model> h5ObjList) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("version", "2.0");
		map.put("datas", h5ObjList);
		map.put("scale", DEFAULT_SCALE);// 比例
		map.put("tx", DEFAULT_TX);// x坐标
		map.put("ty", DEFAULT_TY);// y坐标
		return map;
	}

	/**
	 * 把bpmnXml封装成H5Model集合，用来拼接Json
	 * 
	 * @param bpmnXml
	 *            用来解析节点和连线
	 * @param designXml
	 *            用来解析节点上额外的属性
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<H5Model> parseBpmnXml2ModelList(String bpmnXml,
			String designXml) {
		synchronized (BpmJsonUtil.class) {
			preIndex++;
		}
		if (null == bpmnXml || bpmnXml.isEmpty())
			throw new InvalidParameterException("bpmnXml 不能为空！");
		if (null == designXml || designXml.isEmpty())
			throw new InvalidParameterException("designXml 不能为空！");

		// 加载bpmnXml文件
		Document bpmnDocument = Dom4jUtil.loadXml(bpmnXml);
		Element bpmnRootEle = bpmnDocument.getRootElement();
		// 加载designXml文件
		Document designDocument = Dom4jUtil.loadXml(designXml);
		Element designBpmnRootEle = designDocument.getRootElement();

		// 1 解析图形节点
		List<H5Model> h5ObjList = new ArrayList<H5Model>();
		List<BPMNShap> shapList = ProcessDiagramGenerator
				.extractBPMNShap(bpmnXml);
		Map<String, String> cache = new HashMap<String, String>();
		int len = (null == shapList) ? 0 : shapList.size();
		for (int i = 0; i < len; i++) {
			BPMNShap bpmnShap = shapList.get(i);
			String bpmElement = bpmnShap.getBpmnElement();
			String name = bpmnShap.getName();
			int x = (int) bpmnShap.getX();
			int y = (int) bpmnShap.getY();
			String _refId = preIndex + "_" + (i + 1);
			cache.put(bpmElement, _refId);

			// 组装JSON数据
			Map<String, Object> locationMap = new HashMap<String, Object>();
			locationMap.put("x", x);
			locationMap.put("y", y);

			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap=changeImage(jsonMap,bpmnShap.getType().toString());
				if(bpmnShap.getName().contains("脚本")){
					String image="脚本任务";
					jsonMap.put("image", image);
					}
			jsonMap.put("name", name);
			jsonMap.put("location", locationMap);
			jsonMap.put("bpmNodeType", bpmnShap.getType());
			Map<String, Object> properties = new HashMap<String, Object>();
			properties.put("bpmNodeId", bpmElement);
			jsonMap.put("properties", properties);

			// 获取id为bpmElement的节点的额外属性及特殊子标签值（如果包含特殊子标签，如子事务图）
			Map<String, Object> attrMap = getNodeAttributes(designBpmnRootEle,
					bpmElement);
			jsonMap.putAll(attrMap);

			H5Model h5Model = new H5Model();
			if (attrMap.containsKey("subDefKey")) {
				h5Model.set_className(Q_GROUP);// 含有子元素，则构建组节点
				jsonMap.put("padding", 30);// 内边距
				jsonMap.put("expanded", false);
				h5Model.setJson(jsonMap);
				h5Model.set_refId(_refId);
			} else {
				h5Model.set_className(Q_NODE);// 普通节点
				h5Model.setJson(jsonMap);
				h5Model.set_refId(_refId);
			}
			h5ObjList.add(h5Model);
		}

		// 2 解析连线
		// List<BPMNEdge> edges = ProcessDiagramGenerator.extractBPMNEdge(xml);
		List edgeList = bpmnRootEle.selectNodes("//bpmndi:BPMNEdge");
		for (int j = 0, length = edgeList.size(); j < length; j++) {
			Element edgeEle = (Element) edgeList.get(j);
			String id = edgeEle.attributeValue("bpmnElement");
			Element element = (Element) bpmnRootEle
					.selectSingleNode("//*[@id='" + id + "']");
			String sourceId = element.attributeValue("sourceRef");
			String targetId = element.attributeValue("targetRef");
			String name = element.attributeValue("name");
			String fromId = cache.get(sourceId);
			String toId = cache.get(targetId);

			// 组装JSON数据
			Map<String, Object> fromMap = new HashMap<String, Object>();
			fromMap.put("_ref", fromId);

			Map<String, Object> toMap = new HashMap<String, Object>();
			toMap.put("_ref", toId);

			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("name", name);
			jsonMap.put("from", fromMap);
			jsonMap.put("to", toMap);
			jsonMap.put("bpmEdgeId", id);

			H5Model h5Model = new H5Model();
			h5Model.set_className(Q_EDGE);
			h5Model.setJson(jsonMap);
			h5Model.set_refId(preIndex + "_edge_" + (j + 1));
			h5ObjList.add(h5Model);
		}
		return h5ObjList;
	}
	/**
	 * 把bpmnXml封装成H5Model集合，用来拼接Json
	 * 
	 * @param bpmnXml
	 *            用来解析节点和连线
	 * @param designXml
	 *            用来解析节点上额外的属性
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<H5Model> parseBpmnXml2ModelListAndMarkovchain(String bpmnXml,
			String designXml,String markovchain ) {
		synchronized (BpmJsonUtil.class) {
			preIndex++;
		}
		if (null == bpmnXml || bpmnXml.isEmpty())
			throw new InvalidParameterException("bpmnXml 不能为空！");
		if (null == designXml || designXml.isEmpty())
			throw new InvalidParameterException("designXml 不能为空！");

		// 加载bpmnXml文件
		Document bpmnDocument = Dom4jUtil.loadXml(bpmnXml);
		Element bpmnRootEle = bpmnDocument.getRootElement();
		// 加载designXml文件
		Document designDocument = Dom4jUtil.loadXml(designXml);
		Element designBpmnRootEle = designDocument.getRootElement();

		// 1 解析图形节点
		List<H5Model> h5ObjList = new ArrayList<H5Model>();
		List<BPMNShap> shapList = ProcessDiagramGenerator
				.extractBPMNShap(bpmnXml);
		Map<String, String> cache = new HashMap<String, String>();
		int len = (null == shapList) ? 0 : shapList.size();
		for (int i = 0; i < len; i++) {
			BPMNShap bpmnShap = shapList.get(i);
			String bpmElement = bpmnShap.getBpmnElement();
			String name = bpmnShap.getName();
			int x = (int) bpmnShap.getX();
			int y = (int) bpmnShap.getY();
			String _refId = preIndex + "_" + (i + 1);
			cache.put(bpmElement, _refId);

			// 组装JSON数据
			Map<String, Object> locationMap = new HashMap<String, Object>();
			locationMap.put("x", x);
			locationMap.put("y", y);

			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap=changeImage(jsonMap,bpmnShap.getType().toString());
				if(bpmnShap.getName().contains("脚本")){
					String image="脚本任务";
					jsonMap.put("image", image);
					}
			jsonMap.put("name", name);
			jsonMap.put("location", locationMap);
			jsonMap.put("bpmNodeType", bpmnShap.getType());
			Map<String, Object> properties = new HashMap<String, Object>();
			properties.put("bpmNodeId", bpmElement);
			jsonMap.put("properties", properties);

			// 获取id为bpmElement的节点的额外属性及特殊子标签值（如果包含特殊子标签，如子事务图）
			Map<String, Object> attrMap = getNodeAttributes(designBpmnRootEle,
					bpmElement);
			jsonMap.putAll(attrMap);

			H5Model h5Model = new H5Model();
			if (attrMap.containsKey("subDefKey")&&StringUtil.isEmpty(markovchain)) {
				h5Model.set_className(Q_GROUP);// 含有子元素，则构建组节点
				jsonMap.put("padding", 30);// 内边距
				jsonMap.put("expanded", false);
				h5Model.setJson(jsonMap);
				h5Model.set_refId(_refId);
			} else {
				h5Model.set_className(Q_NODE);// 普通节点
				h5Model.setJson(jsonMap);
				h5Model.set_refId(_refId);
			}
			h5ObjList.add(h5Model);
		}

		// 2 解析连线
		// List<BPMNEdge> edges = ProcessDiagramGenerator.extractBPMNEdge(xml);
		List edgeList = bpmnRootEle.selectNodes("//bpmndi:BPMNEdge");
		for (int j = 0, length = edgeList.size(); j < length; j++) {
			Element edgeEle = (Element) edgeList.get(j);
			String id = edgeEle.attributeValue("bpmnElement");
			Element element = (Element) bpmnRootEle
					.selectSingleNode("//*[@id='" + id + "']");
			String sourceId = element.attributeValue("sourceRef");
			String targetId = element.attributeValue("targetRef");
			String name = element.attributeValue("name");
			String fromId = cache.get(sourceId);
			String toId = cache.get(targetId);

			// 组装JSON数据
			Map<String, Object> fromMap = new HashMap<String, Object>();
			fromMap.put("_ref", fromId);

			Map<String, Object> toMap = new HashMap<String, Object>();
			toMap.put("_ref", toId);

			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("name", name);
			jsonMap.put("from", fromMap);
			jsonMap.put("to", toMap);
			jsonMap.put("bpmEdgeId", id);

			H5Model h5Model = new H5Model();
			h5Model.set_className(Q_EDGE);
			h5Model.setJson(jsonMap);
			h5Model.set_refId(preIndex + "_edge_" + (j + 1));
			h5ObjList.add(h5Model);
		}
		return h5ObjList;
	}
	/**
	 * 获取指定id的节点的属性（不包括id,width,height,x,y）
	 * 
	 * @param designRootElement
	 *            designXml的root元素
	 * @param id
	 *            指定元素的id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> getNodeAttributes(
			Element designRootElement, String id) {
		Map<String, Object> attrMap = new HashMap<String, Object>();
		// 根据id获取指定节点
		Element element = (Element) designRootElement
				.selectSingleNode("//*[@id='" + id + "']");
		if (null == element)
			throw new RuntimeException("id=\"" + id + "\"的节点不存在");

		List<Attribute> attrList = element.attributes();
		// 获取所有节点属性，不包括id,width,height,x,y
		for (Attribute attribute : attrList) {
			String name = attribute.getName();
			String value = attribute.getValue();
			if ("id".equals(name) || "width".equals(name)
					|| "height".equals(name) || "x".equals(name)
					|| "y".equals(name))
				continue;
			attrMap.put(name, value);
		}
		attrMap=changeImage1(attrMap);
		
		String user = (String) attrMap.get("user");// 用户任务标识
		
		String affair = (String) attrMap.get("affair");// 事务图标识
		
		String childaffair = (String) attrMap.get("childaffair");// 子事务标识
		
		String startSubFlow = (String) attrMap.get("startSubFlow");// 外部子图标识
		if ("true".equals(user) || "true".equals(affair)
				|| "true".equals(childaffair) || "true".equals(startSubFlow)) {
			Element subDefKeyElement = element.element("subDefKey");
			if (null != subDefKeyElement) {
				String subDefKey = subDefKeyElement.getTextTrim();
				if(StringUtil.isNotEmpty(subDefKey))
				{
					attrMap.put("subDefKey", subDefKey);// 获取子事务的defKey
				}
			}
		}

		return attrMap;
	}

	/**
	 * 把一组节点加入到一个Group中，用来拼接带Group的Json，以实现双击放大或缩小的效果
	 * 
	 * @param groupModel 组
	 * @param h5ModeList 需要加入组的节点集合
	 */
	public static void addNodesToGroup(H5Model groupModel,
			List<H5Model> modeList) {
		if (!Q_GROUP.equals(groupModel.get_className()))
			throw new RuntimeException("节点类型不是Q.Group类型，不能添加子节点");

		// 遍历节点列表，每个节点加入指定组中
		for (H5Model model : modeList) {
			
			if(model.getExtraParams().size()==0)
			{
			String _refId = groupModel.get_refId();// Json需要的节点ID
			Map<String, Object> parentMap = new HashMap<String, Object>();
			parentMap.put("_ref", _refId);
			Map<String, Object> jsonMap = model.getJson();
			jsonMap.put("parent", parentMap);
			}
		}
	}
	/**
	 * 把bpmnXml和nodetype封装成H5Model集合，用来拼接事务图Json
	 * 
	 * @param bpmnXml
	 *            用来解析节点和连线
	 * @param designXml
	 *            用来解析节点上额外的属性
	 * @param designXml
	 *            用来添加节点上绑定的表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<H5Model> parseBpmnXml2ModelListAndNodeType(String bpmnXml,
			String designXml,Map<String,Object> nodeType) {
		synchronized (BpmJsonUtil.class) {
			preIndex++;
		}
		if (null == bpmnXml || bpmnXml.isEmpty())
			throw new InvalidParameterException("bpmnXml 不能为空！");
		if (null == designXml || designXml.isEmpty())
			throw new InvalidParameterException("designXml 不能为空！");

		// 加载bpmnXml文件
		Document bpmnDocument = Dom4jUtil.loadXml(bpmnXml);
		Element bpmnRootEle = bpmnDocument.getRootElement();
		// 加载designXml文件
		Document designDocument = Dom4jUtil.loadXml(designXml);
		Element designBpmnRootEle = designDocument.getRootElement();

		// 1 解析图形节点
		List<H5Model> h5ObjList = new ArrayList<H5Model>();
		List<BPMNShap> shapList = ProcessDiagramGenerator
				.extractBPMNShap(bpmnXml);
		Map<String, String> cache = new HashMap<String, String>();
		int h=0;
		Map<String,String> tablemap=new HashMap<String,String>();
		 for (String key : nodeType.keySet()) {
			   
			   Map<String,Object> map=( Map<String,Object>)nodeType.get(key);
			   String tableName=(String)map.get("tableName");
			   tablemap.put(tableName, tableName);
			  }
		 for (String key : tablemap.keySet()) {
			   
			   
			   Map<String, Object> jsonMap = new HashMap<String, Object>();
			   jsonMap.put("name",  tablemap.get(key)+"表");
			   H5Model h5Model = new H5Model();
			   h5Model.setJson(jsonMap);
			   String _refId = preIndex + "_" + (++h + 1000);
			   h5Model.set_refId(_refId);
			   h5Model.set_className(Q_NODE);
			   
			   Map<String, Object> extraParams=new HashMap<String,Object>();
			   extraParams.put("table", true);
			   h5Model.setExtraParams(extraParams);
			   cache.put(tablemap.get(key), _refId);
			   h5ObjList.add(h5Model);
			  }
		 
		int len = (null == shapList) ? 0 : shapList.size();
		for (int i = 0; i < len; i++) {
			BPMNShap bpmnShap = shapList.get(i);
			String bpmElement = bpmnShap.getBpmnElement();
			String name = bpmnShap.getName();
			int x = (int) bpmnShap.getX();
			int y = (int) bpmnShap.getY();
			String _refId = preIndex + "_" + (i + 1);
			cache.put(bpmElement, _refId);

			// 组装JSON数据
			Map<String, Object> locationMap = new HashMap<String, Object>();
			locationMap.put("x", x);
			locationMap.put("y", y);

			Map<String, Object> jsonMap = new HashMap<String, Object>();
				if(bpmnShap.getName().contains("脚本")){
					String image="脚本任务";
					jsonMap.put("image", image);
					}
			jsonMap=changeImage(jsonMap,bpmnShap.getType().toString());
			jsonMap.put("name", name);
			jsonMap.put("location", locationMap);
			jsonMap.put("bpmNodeType", bpmnShap.getType());
			jsonMap.put("bpmNodeId", bpmElement);

			// 获取id为bpmElement的节点的额外属性及特殊子标签值（如果包含特殊子标签，如子事务图）
			Map<String, Object> attrMap = getNodeAttributes(designBpmnRootEle,
					bpmElement);
			jsonMap.putAll(attrMap);

			H5Model h5Model = new H5Model();
			if (attrMap.containsKey("subDefKey")) {
				h5Model.set_className(Q_GROUP);// 含有子元素，则构建组节点
				jsonMap.put("padding", 30);// 内边距
				jsonMap.put("expanded", false);
				h5Model.setJson(jsonMap);
				h5Model.set_refId(_refId);
			} else {
				h5Model.set_className(Q_NODE);// 普通节点
				h5Model.setJson(jsonMap);
				h5Model.set_refId(_refId);
			}
			h5ObjList.add(h5Model);
		}

		// 2 解析连线
		// List<BPMNEdge> edges = ProcessDiagramGenerator.extractBPMNEdge(xml);
		List edgeList = bpmnRootEle.selectNodes("//bpmndi:BPMNEdge");
		for (int j = 0, length = edgeList.size(); j < length; j++) {
			Element edgeEle = (Element) edgeList.get(j);
			String id = edgeEle.attributeValue("bpmnElement");
			Element element = (Element) bpmnRootEle
					.selectSingleNode("//*[@id='" + id + "']");
			String sourceId = element.attributeValue("sourceRef");
			String targetId = element.attributeValue("targetRef");
			String name = element.attributeValue("name");
			String fromId = cache.get(sourceId);
			String toId = cache.get(targetId);

			// 组装JSON数据
			Map<String, Object> fromMap = new HashMap<String, Object>();
			fromMap.put("_ref", fromId);

			Map<String, Object> toMap = new HashMap<String, Object>();
			toMap.put("_ref", toId);

			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("name", name);
			jsonMap.put("from", fromMap);
			jsonMap.put("to", toMap);
			jsonMap.put("bpmEdgeId", id);

			H5Model h5Model = new H5Model();
			h5Model.set_className(Q_EDGE);
			h5Model.setJson(jsonMap);
			h5Model.set_refId(preIndex + "_edge_" + (j + 1));
			h5ObjList.add(h5Model);
		}
		//组装将表和节点连起来的线的json串
		int j=0;
		for (String key : nodeType.keySet()) {
			   System.out.println("key= "+ key + " and value= " + nodeType.get(key));
			   Map<String,Object> map=( Map<String,Object>)nodeType.get(key);
			   String tableName=(String)map.get("tableName");
			   String type=(String)map.get("type");
			   
			   Map<String, Object> fromMap = new HashMap<String, Object>();
			   Map<String, Object> toMap = new HashMap<String, Object>();
			   if("writepage".equals(type))//写页面表指向节点
			   {
			   
			    String fromId = cache.get(tableName);
			    fromMap.put("_ref", fromId);
			    
				String toId = cache.get(key);
				toMap.put("_ref", toId);
			   }
			   else//读页面节点指向表
			   {
				   
				   String fromId = cache.get(key);
				   fromMap.put("_ref", fromId);
				   
				   String toId =cache.get(tableName); 
				   toMap.put("_ref", toId);
					
			   }
			  
			    Map<String, Object> jsonMap = new HashMap<String, Object>();
				jsonMap.put("from", fromMap);
				jsonMap.put("to", toMap);
			   
				H5Model h5Model = new H5Model();
				
				Map<String, Object> extraParams=new HashMap<String,Object>();
				extraParams.put("table", true);
				h5Model.setExtraParams(extraParams);
				
				h5Model.set_className(Q_EDGE);
				h5Model.setJson(jsonMap);
				h5Model.set_refId(preIndex + "_edge_" + (++j + 100));
				h5ObjList.add(h5Model);
			  }
		
		return h5ObjList;
	}
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		String bpmnXml_L = FileUtils.readFileToString(new File("C:\\Users\\Administrator\\Desktop\\HTML5\\bpmn_L.xml"));
		String designXml_L = FileUtils.readFileToString(new File("C:\\Users\\Administrator\\Desktop\\HTML5\\design_L.xml"));
		Map<String, Object> map = parseBpmnXml2Map(bpmnXml_L, designXml_L);

		String bpmnXml_C = FileUtils.readFileToString(new File("C:\\Users\\Administrator\\Desktop\\HTML5\\bpmn_C.xml"));
		String designXml_C = FileUtils.readFileToString(new File("C:\\Users\\Administrator\\Desktop\\HTML5\\design_C.xml"));

		List<H5Model> modelList = parseBpmnXml2ModelList(bpmnXml_C, designXml_C);
		List<H5Model> sModelList = (List<H5Model>) map.get("datas");
		for (H5Model model : sModelList) {
			if (Q_GROUP.equals(model.get_className())) {
				addNodesToGroup(model, modelList);
			}
		}
		sModelList.addAll(modelList);

		JSONObject jsonObject3 = new JSONObject();
		jsonObject3.put("version", "2.0");
		jsonObject3.put("datas", sModelList);
		jsonObject3.put("scale", 1);// 比例
		jsonObject3.put("tx", 200);// x坐标
		jsonObject3.put("ty", 200);// y坐标
		System.out.println(jsonObject3);
	}
	public static Map<String, Object> changeImage(Map<String, Object> jsonMap,String bpmnshapType){

		if("StartEvent".equals(bpmnshapType)){
		String image="开始";
		jsonMap.put("image", image);
		}
		if("EndEvent".equals(bpmnshapType)){
		String image="结束";
		jsonMap.put("image", image);
		}
		
		if("CallActivity".equals(bpmnshapType)){
			String image="外部子图";
			jsonMap.put("image", image);
			}
		if("ExclusiveGateway".equals(bpmnshapType)){
			String image="分支";
			jsonMap.put("image", image);
			}
		if("ParallelGateway".equals(bpmnshapType)){
			String image="同步";
			jsonMap.put("image", image);
			}
		if("InclusiveGateway".equals(bpmnshapType)){
			String image="条件同步";
			jsonMap.put("image", image);
			}
		
		if("true".equals(bpmnshapType)){
			String image="跳转页面";
			jsonMap.put("image", image);
			}

	return jsonMap;
	}
	public static Map<String, Object> changeImage1(Map<String, Object> attrMap)
	{
		String pageskip = (String) attrMap.get("pageskip");
		if("true".equals(pageskip)){
			String image="跳转页面";
			attrMap.put("image", image);
			}
		String mail = (String) attrMap.get("mail");
		if("true".equals(mail)){
			String image="消息任务";
			attrMap.put("image", image);
			}
		String webService = (String) attrMap.get("webService");
		if("true".equals(webService)){
				String image="webService服务";
				attrMap.put("image", image);
				}
		String readpage = (String) attrMap.get("readpage");
		if("true".equals(readpage)){
			String image="读页面";
			attrMap.put("image", image);
			}
		String writepage = (String) attrMap.get("writepage");
		if("true".equals(writepage)){
			String image="写页面";
			attrMap.put("image", image);
			}
		String datainquire = (String) attrMap.get("datainquire");
		if("true".equals(datainquire)){
			String image="查找数据";
			attrMap.put("image", image);
			}
		String dataadd = (String) attrMap.get("dataadd");
		if("true".equals(dataadd)){
			String image="增加数据";
			attrMap.put("image", image);
			}
		String datachange = (String) attrMap.get("datachange");
		if("true".equals(datachange)){
			String image="修改数据";
			attrMap.put("image", image);
			}
		String datadelate = (String) attrMap.get("datadelate");
		if("true".equals(datadelate)){
			String image="删除数据";
			attrMap.put("image", image);
			}
		String sendMailService = (String) attrMap.get("sendMailService");
		if("true".equals(sendMailService)){
			String image="发送邮件服务";
			attrMap.put("image", image);
			}
		String word = (String) attrMap.get("word");
		if("true".equals(word)){
			String image="文字";
			attrMap.put("image", image);
			}
		String multiInstance = (String) attrMap.get("multiInstance");
		if("true".equals(multiInstance)){
			String image="会签任务";
			attrMap.put("image", image);
			}
		String receiveMailService = (String) attrMap.get("receiveMailService");
		if("true".equals(receiveMailService)){
			String image="收邮件服务";
			attrMap.put("image", image);
			}
		String submitwork = (String) attrMap.get("submitwork");
		if("true".equals(submitwork)){
			String image="提交";
			attrMap.put("image", image);
			}
		
		String countShowLineService = (String) attrMap.get("countShowLineService");
		if("true".equals(countShowLineService)){
			String image="计算机显示路线服务";
			attrMap.put("image", image);
			}
		String downservice = (String) attrMap.get("downservice");
			if("true".equals(downservice)){
				String image="ftp下载服务";
				attrMap.put("image", image);
				}
		String upservice = (String) attrMap.get("upservice");
			if("true".equals(upservice)){
				String image="ftp上传服务";
				attrMap.put("image", image);
				}
		String locationService = (String) attrMap.get("locationService");
			if("true".equals(locationService)){
				String image="定位服务";
				attrMap.put("image", image);
				}
		String uploadMapService= (String) attrMap.get("uploadMapService");
			if("true".equals(uploadMapService)){
				String image="加载地图服务";
				attrMap.put("image", image);
				}
		String sendNoteService = (String) attrMap.get("sendNoteService");
			if("true".equals(sendNoteService)){
				String image="发送短信服务";
				attrMap.put("image", image);
				}
		String receiveNoteService = (String) attrMap.get("receiveNoteService");
			if("true".equals(receiveNoteService)){
				String image="接收短信服务";
				attrMap.put("image", image);
				}
		
		String user = (String) attrMap.get("user");// 用户任务标识
		if("true".equals(user)){
			String image="用户任务";
			attrMap.put("image", image);
			}
		String affair = (String) attrMap.get("affair");// 事务图标识
		if("true".equals(affair)){
			String image="事务图";
			attrMap.put("image", image);
			}
		String childaffair = (String) attrMap.get("childaffair");// 子事务标识
		if("true".equals(childaffair)){
			String image="子事务图";
			attrMap.put("image", image);
			}
		String startSubFlow = (String) attrMap.get("startSubFlow");// 外部子图标识
		return attrMap;
	}
}
