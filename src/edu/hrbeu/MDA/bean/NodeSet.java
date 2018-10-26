package edu.hrbeu.MDA.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 存放流程中的节点信息
 * @author 张静
 *
 */
public class NodeSet {
	/**
	 * nodeId 节点id
	 * nodeName 节点名称
	 * nodeType 节点类型
	 * defIf 流程id
	 * nextNodeId 下个节点
	 * preNodeId 上个节点
	 * occMap 节点发生量
	 * rate 节点发生概率
	 * timeAndCount 给王乙闲的发生时间和发生量
	 * defKey 获取外部子流程用
	 */
	private String nodeId;
	private String nodeName;
	private String nodeType;
	private Long defId;
	private List<NodeSet> nextNode = new ArrayList<NodeSet>();
	private Map<String,Integer> occMap = new HashMap<String, Integer>();
	//zl
	private List<NodeSet> preNode = new ArrayList<NodeSet>();
	private String[][] timeAndCount;
	private String defKey;
	//发生概率
	private double rate = 0.5;
	//在一次流程中是否发生了
	private boolean happened = false;
	
	
	public Map<String, Integer> getOccMap() {
		return occMap;
	}

	public void setOccMap(Map<String, Integer> occMap) {
		this.occMap = occMap;
	}

	public NodeSet() {
		super();
		
	}
	
	public NodeSet(Long defId,String nodeId, String nodeName, String nodeType) {
		super();
		this.nodeId = nodeId;
		this.nodeName = nodeName;
		this.nodeType = nodeType;
		this.defId = defId;
	}
	public NodeSet(Long defId,String nodeId, String nodeName, String nodeType, List<NodeSet> nextNode, List<NodeSet> preNode) {
		super();
		this.nodeId = nodeId;
		this.nodeName = nodeName;
		this.nodeType = nodeType;
		this.defId = defId;
		this.nextNode = nextNode;
		this.preNode = preNode;
	}
	public Long getDefId() {
		return defId;
	}
	public void setDefId(Long defId) {
		this.defId = defId;
	}
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public String getNodeType() {
		return nodeType;
	}
	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	public void setTimeAndCount(String[][] timeAndCount) {
		this.timeAndCount = timeAndCount;
	}

	public String[][] getTimeAndCount() {
		timeAndCount = new String[occMap.size()][2];
		Set<String> set = occMap.keySet();
		Iterator<String> it = set.iterator();
		for(int i = 0;i < occMap.size();++i){
			timeAndCount[i][0] = it.next();
			timeAndCount[i][1] = occMap.get(timeAndCount[i][0]).toString();
		}
		return timeAndCount;
	}

	public void setNextNode(List<NodeSet> nextNode) {
		this.nextNode = nextNode;
	}

	public List<NodeSet> getNextNode() {
		return nextNode;
	}

	public void setPreNode(List<NodeSet> preNode) {
		this.preNode = preNode;
	}

	public List<NodeSet> getPreNode() {
		return preNode;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public double getRate() {
		return rate;
	}

	public void setHappened(boolean happened) {
		this.happened = happened;
	}

	public boolean isHappened() {
		return happened;
	}

	public void setDefKey(String defKey) {
		this.defKey = defKey;
	}

	public String getDefKey() {
		return defKey;
	}
	
	
}
