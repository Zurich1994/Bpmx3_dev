
package com.hotent.Markovchain.controller.Markovchain;


import java.math.BigDecimal;
import java.net.InetAddress;
import java.util.HashMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Stack;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import com.hotent.core.annotion.Action;
import com.hotent.core.bpm.util.BpmUtil;

import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.util.StringUtil;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.platform.model.bpm.BpmDefinition;
import com.hotent.platform.model.system.WDefInformation;
import com.hotent.platform.service.bpm.BpmDefinitionService;
import com.hotent.platform.service.bpm.BpmService;

import org.activiti.engine.repository.Deployment;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

//import com.ho
import com.hotent.Markovchain.model.Markovchain.MarkNode;
import com.hotent.Markovchain.model.Markovchain.Markovchain;
import com.hotent.Markovchain.model.Markovchain.NodeSet;
import com.hotent.Markovchain.model.Markovchain.SequenceFlow;
import com.hotent.Markovchain.service.Markovchain.MarkovchainService;
import com.hotent.SysDefNodeErgodic.service.SysDefNodeErgodic.SysdefnodeergodicService;
import com.hotent.core.web.ResultMessage;

/**
 * 对象功能:马尔科夫链表 控制器类
 */
@Controller
@RequestMapping("/Markovchain/Markovchain/markovchain/")
public class MarkovchainController extends BaseController
{

	private int i = 1;
	private final static String BPM_XML_NS = "xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\"";
	private  ArrayList<Integer> trace=new ArrayList<Integer>();//从出发节点到当前节点的轨迹
	@Resource
	private MarkovchainService markovchainService;
	@Resource
	private BpmDefinitionService definitionservice;
	@Resource
	private BpmService bpmservice;
	@Resource
	private SysdefnodeergodicService sysdefnodeergodicService;
	
	/**
	 * 添加或更新马尔科夫链表。
	 * @param request
	 * @param response
	 * @param markovchain 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新马尔科夫链表")
	public void save(HttpServletRequest request, HttpServletResponse response,Markovchain markovchain) throws Exception
	{
		String defId=request.getParameter("defid");
		
		String typeName=request.getParameter("typeName");
		
		String resultMsg=null;	
		markovchain.setDefid(defId);
		
		if(markovchain.getDependId() == null){
			System.out.println("getid" + markovchain.getId());
			markovchain.setDependId(markovchain.getId());
			markovchain.setDependmark(markovchain.getMarkovchainNAME());
		}
		// 获得本机IP
		InetAddress addr = InetAddress.getLocalHost();
		String ip = addr.getHostAddress().toString();
		System.out.println("ip=" + ip);
		// 跳转地址
		String newurl = "http://"
				+ ip
				+ ":8080/mas/Markovchain/Markovchain/markovchain/listbydefid.ht?defId="
				+ defId + "&typeName=" + typeName;
		ResultMessage message=null;
		try{
			if(markovchain.getId()==null){
				markovchainService.save(markovchain);
				resultMsg="添加马尔科夫链表";
			}else{
			    markovchainService.save(markovchain);
				resultMsg="更新马尔科夫链表";
			}
			message=new ResultMessage(ResultMessage.Success, resultMsg);
			
		}catch(Exception e){
			message=new ResultMessage(ResultMessage.Fail, resultMsg + e.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(newurl);
	}
	
	/**
	 * 取得马尔科夫链表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看马尔科夫链表分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<Markovchain> list=markovchainService.getAll(new QueryFilter(request,"markovchainItem"));
		
		ModelAndView mv=this.getAutoView().addObject("markovchainList",list);
		return mv;
	}
	/**
	 * 取得马尔科夫链表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("loadDetail")
	@Action(description="查看马尔科夫链表分页列表")
	public ModelAndView loadDetail(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		System.out.println("进来了!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		 Long markid=RequestUtil.getLong(request, "markid");
		 String markovchain=markovchainService.getmarkXmlById(markid);
			Document document = DocumentHelper.parseText(markovchain);
			Element root = document.getRootElement();
			List<Element> elementList=root.elements();
			System.out.println("elementList.size()"+elementList.size());
			List<MarkNode>  listnode=new ArrayList<MarkNode>();
			int num=1;
			for(Element element : elementList){
				MarkNode node=new MarkNode();
				node.setNodeId(element.attribute("id").getText());	
				node.setNum(num);
				num++;
				node.setLable(element.attribute("lable").getText());				
				node.setMessgae(element.attribute("message").getText());				
				node.setType(element.attribute("type").getText());				
				node.setAnimation(element.attribute("animation").getText());				
				node.setYellowcolor(element.attribute("yellowcolor").getText());				
				listnode.add(node);
				
			}
			for(MarkNode a:listnode)
			{
				System.out.println("测试数据："+a.getAnimation()+" "+a.getNodeId()+" "+a.getLable()+" "+a.getMessgae()+" "+a.getType()+" "+a.getYellowcolor()+" ");
			}

			
			ModelAndView mv = this.getAutoView().addObject("markovchain", listnode).addObject("markid", markid);
			return mv;
	}
	/**
	 * 删除马尔科夫链表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除马尔科夫链表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			markovchainService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除马尔科夫链表成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑马尔科夫链表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑马尔科夫链表")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		Markovchain markovchain=markovchainService.getById(id);
		
		return getAutoView().addObject("markovchain",markovchain)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得马尔科夫链表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看马尔科夫链表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		Markovchain markovchain=markovchainService.getById(id);
		return getAutoView().addObject("markovchain", markovchain);
	}
	/**
	 * 根据流程ID
	 * 取得马尔科夫链表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 * @author 魏嫚
	 */
	@RequestMapping("listbydefid")
	@Action(description="查看马尔科夫链表分页列表")
	public ModelAndView listbydefid(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		Long defId = Long.parseLong(request.getParameter("defId"));
		String subject=request.getParameter("subject");
		String typeName=request.getParameter("typeName");
		String status=request.getParameter("status");
		System.out.println(defId);
		countProbability(defId);
		//String bpmsubject=definitionservice.getById(defId).getSubject();
		List<Markovchain> list=markovchainService.getByDefId(defId);
		//Map<String,Integer> as=getNodeProbaility();
		//getNodeProbaility();
		ModelAndView mv=this.getAutoView().addObject("markovchainList",list)
										  .addObject("defId",defId)
										  .addObject("subject",subject)
										  .addObject("typeName", typeName)
										  .addObject("status", status);
										 // .addObject("as", as);
		return mv;
	}
	
	
	/**
	 * 根据流程ID
	 * 自动生成马尔科夫链
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 * @author 魏嫚String 
	 */
	@RequestMapping("recommended")
	@Action(description="自动生成马尔科夫链")
	public void recommended(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		Long defId = RequestUtil.getLong(request, "defId");
		//String subject=request.getParameter("subject");
		System.out.println(defId);
		//List<Markovchain> list=markovchainService.getByDefId(defId);
		BpmDefinition bpmdefinition = definitionservice.getById(defId);
   		Long deployId = bpmdefinition.getActDeployId();
		String actxml = bpmservice.getDefXmlByDeployId(deployId.toString());
		
		try {
			//解析xml文件，actxnl文件是activity生成的，！=defxml
			parexml(actxml,defId);
			message=new ResultMessage(ResultMessage.Success, "成功自动生成马尔科夫链!");
		} catch (Exception e) {
			// TODO: handle exception
			message=new ResultMessage(ResultMessage.Success, "自动生成马尔科夫链=====失败!");
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	/**
	 * 根据流程defid,actxml
	 * 解析xml文件，记录流程图的节点顺序信息，
	 * 以及节点的信息，分别以list方式存储
	 * @param xmlStr
	 * @param defid
	 * 
	 * @return
	 * @throws Exception
	 * @author 魏嫚String 
	 */
	@SuppressWarnings("unchecked")
	public int parexml(String xmlStr,Long defid){
		BpmDefinition bpm = definitionservice.getById(defid);
		String subject = bpm.getSubject();
		
		try {
			System.out.println(xmlStr);
			List<NodeSet> nodes = new ArrayList<NodeSet>();
			List<SequenceFlow> sequenceflow = new ArrayList<SequenceFlow>();
			Document document = DocumentHelper.parseText(xmlStr);
			Element root = document.getRootElement();
			List<Element> elementList=root.elements();
			System.out.println("elementList.size()"+elementList.size());
			int num=0;//节点标号
			for(Element element : elementList){
				System.out.println(element.getName());
				System.out.println(element.attribute("id").getText());
				
				List<Element> eles = element.elements();
					for(Element ele : eles){
						System.out.println(ele.getName());
						if(ele.getName().contains("sequenceFlow")){
								String id = ele.attribute("id").getText();			
								String sourceRef = ele.attributeValue("sourceRef");
								String targetRef = ele.attributeValue("targetRef");
								System.out.println("id:"+id+" sourceRef:"+sourceRef+" targetRef:"+targetRef);
								sequenceflow.add(new SequenceFlow(id, sourceRef, targetRef));
							}
						if(ele.getName().contains("startEvent")||ele.getName().contains("userTask")||ele.getName().contains("endEvent")||ele.getName().contains("serviceTask")||ele.getName().contains("Gateway")){
								String id = ele.attribute("id").getText();
								String label = ele.attributeValue("name");
								System.out.println("id:"+id+" label:"+label);
								NodeSet nodeset = new NodeSet(id,label,num);
								nodes.add(nodeset);	
								num++;
						}
					}
			}
			List<List<Integer>> nodesCycle =getCycle(sequenceflow,nodes,num);//这个链表里存的循环链表
			Map<String,Integer> amap=new HashMap<String,Integer>();
			amap=getCycleNodeMap(nodesCycle,nodes);
			for (String key : amap.keySet()) {
				   System.out.println("key= "+ key + " and value= " + amap.get(key));
				  }
		/*	for(int i=0;i<nodesCycle.size();i++)
			{
				int size=nodesCycle.get(i).size();//第i个链表的大小
				String sourceRef =nodes.get(nodesCycle.get(i).get(0)).getNodeId();
				String targetRef =nodes.get(nodesCycle.get(i).get(size-1)).getNodeId();
				for(SequenceFlow seq:sequenceflow)
				{
					System.out.println("source0:"+seq.getSourceRef()+" target0:"+seq.getTargetRef());
					if(seq.getSourceRef().equals(targetRef)&&seq.getTargetRef().equals(sourceRef))
						{
							sequenceflow.remove(seq);
							break;
						}
				}
			}*/
			Stack<String> stack = new Stack<String>();
			stack.push("StartEvent1");
			//根据实际需求，只能有一个开始节点，并且其ID为 StartEvent1，结束节点可以有多个
			int k = outmarkovchain("StartEvent1","EndEvent", sequenceflow,stack,nodes,defid,amap);
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return 7;
	}
	
	/**
	 * 获得node中ID对应的label
	 * ·@author 魏嫚
	 * @param nodes 节点信息
	 * @param nodeList 线路节点顺序
	 * @return  将线路信息，转化为 键值对的形式，ID 和 label 
	 * */
	private List<Map<String,String>> nodelabelbyid(List<NodeSet> nodes,List<String> nodeList){
		String label = null;
		List<NodeSet> nodeSets = nodes;
		List<String> nodelist = nodeList;
		List<Map<String,String>> nodeLabel = new ArrayList<Map<String,String>>();
		for(String node : nodelist){
			for(NodeSet nodeset : nodeSets){
				Map<String,String> map = new HashMap<String, String>();
				if(node.equals(nodeset.getNodeId())){
					label= nodeset.getLabel();
					map.put(node, label);
					nodeLabel.add(map);
					break;
				}
		   }
		
		}
		return nodeLabel;		
	}
	
	//输出栈内元素，并返回路线顺序
	private List<String> printStack(Stack<String> stack) {
		List<String> nodelist = new ArrayList<String>();
		for (String i : stack) {
			//System.out.println(stack.size());
			System.out.print(i+"  ");
			nodelist.add(i);
		}
		System.out.println("nodelist"+nodelist);
		return nodelist;
		//System.out.print(this.nodes[k] + ",");
	}

	/**
	 * @author Administrator魏嫚
	 * @param start 开始节点
	 * @param end 目标节点即结束节点
	 * @param sequenceFlow 节点顺序信息list
	 * @param stk 栈存储路线的节点
	 * @param node 节点的信息，ID 和 label
	 * @param defid 流程节点，主要是为了传递参数
	 * @return 
	 * 
	 * */
	private int outmarkovchain(String start,String end,List<SequenceFlow> sequenceFlow,Stack<String> stk,List<NodeSet> node,Long defid,Map<String,Integer> amap){
		
		Long defId = defid;
		BpmDefinition def = definitionservice.getById(defId);
		String subjeString = def.getSubject();
		System.out.println("OUTMARKOVCHAIN"+subjeString);
		Stack<String> stack = stk;
		String source = start;
		String target = end;
		List<SequenceFlow> sequence = sequenceFlow;
		List<NodeSet> nodes = node;
		System.out.println(source);
		if(source.contains(end)){
			System.out.println("============");
			List<String> nodelist = printStack(stack);
			//List<String>  lastnodelist=getlastnodelsit(nodelist,nodesCycle,nodes);
			List<Map<String,String>> nodelabel = nodelabelbyid(nodes, nodelist);
			String markxml = setmarkxml(nodelabel);
			System.out.println("新的markxml"+markxml);
			try {
				//Markovchain mark = new Markovchain(markovchainNAME, isSimulation, belongto, markovchainDes, defid, markovchainXML)
				markovchainService.save(new Markovchain(subjeString+"_"+this.i,"是",subjeString,"推荐马尔科夫链----"+subjeString,defid.toString(),markxml));
				System.out.println("添加数据库成功");
				this.i++;
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("添加数据库失败");
			}
			System.out.println("\n===========");
		}
		else{
			
			for(SequenceFlow seq : sequence){
				
				if(seq.getSourceRef().equals(source)){
					String seqtarget = seq.getTargetRef();//它的目标节点（下一个节点）
					int num=0;//计数标记
					int size=2;//允许栈中source的个数
					for(String s:stack)//遍历栈，统计stack中已经有几个source元素了
					{
						if(seq.getSourceRef().equals(s))
						{
							num++;
							//size=amap.get(seq.getSourceRef());
						}
					}
					
					if(num>size)//如果栈中source个数大于2 则不进入栈
					{
						System.out.println("它不让进了"+seqtarget);
						continue;
					}
					System.out.println("上一个节点是："+seq.getSourceRef()+" 入栈："+seqtarget);
					stack.push(seqtarget);
					//递归调用
					outmarkovchain(seqtarget, target, sequence,stack,nodes,defId,amap);
			    }
		   }
		
	   }
		System.out.println("出栈："+stack.pop());
		return 0;
	}
	
	//由马尔科夫链拼接成 xml 
	/**
	 * <flowdata>
	  
	<node id="StartEvent1" lable="开始" type="红色" message="null" yellowcolor="null" animation="null"/>
	  
	<node id="UserTask1" lable="任务书撰写" type="红色" message="null" yellowcolor="null" animation="null"/>
	  
	<node id="UserTask2" lable="任务书审核" type="蓝色" message="null" yellowcolor="null" animation="null"/>
	  
	<node id="UserTask3" lable="分配任务" type="红色" message="null" yellowcolor="null" animation="null"/>
	  
	<node id="UserTask4" lable="领取任务" type="蓝色" message="null" yellowcolor="null" animation="null"/>
	  
	<node id="UserTask5" lable="任务提交" type="蓝色" message="null" yellowcolor="null" animation="null"/>
	  
	<node id="EndEvent1" lable="结束" type="蓝色" message="null" yellowcolor="null" animation="null"/>

	</flowdata>*/
	private String setmarkxml(List<Map<String,String>> marks){
		
		String start = "<flowdata>";
		String end = "</flowdata>";
		String flowset = "";
		String string=" type="+"\""+"红色"+"\""+" message="+"\""+null+"\""+" yellowcolor="+"\""+null+"\""+ " animation="+"\""+null+"\""+"/>";
		String nodeid = "<node id=";
		String label = " lable=";
		List<Map<String,String>> mark = marks;
		System.out.println(mark.size());
		System.out.println(mark);
		for(Map<String,String> map : mark){
			for(String id : map.keySet()){
				flowset = flowset + nodeid + "\"" + id +"\""+ label + "\"" + map.get(id) +"\""+string;
				System.out.println("id:"+id+"  name:"+map.get(id));
				System.out.println("flowset:"+flowset);
			}
			
		}
		flowset = start + flowset + end;
		System.out.println(flowset);
		return flowset;
	}
	
	
		
	//遍历map
	/*private boolean getsign(Map<SequenceFlow, String> map){
		boolean sign = true;
		for(SequenceFlow seqkey : map.keySet()){
			if(map.get(seqkey).equals("1")){
				return true;
			}
		}
		return false;
	}*/
	
	/**
	 * 复制马尔科夫链表
	 * @param request
	 * @param response
	 * @throws Exception
	 * @author 魏嫚
	 */
	@RequestMapping("copy")
	@Action(description="复制马尔科夫链")
	public void copy(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			for(Long mid: lAryId){
				Markovchain mark=markovchainService.getById(mid);
				Markovchain markov=new Markovchain();
				markov.setDefid(mark.getDefid());
				markov.setFrequency(mark.getFrequency());
				markov.setProbability(mark.getProbability());
				markov.setDependId(mark.getDependId());
				markov.setMarkovchainDes(mark.getMarkovchainDes());
				markov.setMarkovchainNAME(mark.getMarkovchainNAME()+"_副本");
				markov.setMarkovchainXML(mark.getMarkovchainXML());
				markov.setIsSimulation(mark.getIsSimulation());
				markov.setBelongto(mark.getBelongto());
				markov.setDependmark(mark.getDependmark());
				//Markovchain markov=mark.clone();
				
				markovchainService.save(markov);
			}
			message=new ResultMessage(ResultMessage.Success, "复制马尔科夫链表成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "复制失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	/**
	 * 	根据流程id编辑马尔科夫链表
	 * @param request
	 * @param response
	 * @throws Exception
	 * @author 魏嫚
	 */
	@RequestMapping("editbyprocess")
	@Action(description="编辑马尔科夫链表")
	public ModelAndView editbyprocess(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		Long defId = RequestUtil.getLong(request, "defId");
		String typeName=RequestUtil.getString(request, "typeName");
		String status=RequestUtil.getString(request, "status");
		BpmDefinition bpm = definitionservice.getById(defId);
		String subject=bpm.getSubject();
		String returnUrl=RequestUtil.getPrePage(request);
		Markovchain markovchain=markovchainService.getById(id);
	
			return getAutoView().addObject("markovchain",markovchain)
								.addObject("returnUrl",returnUrl)
								.addObject("defId",defId)
								.addObject("subject",subject)
								.addObject("typeName", typeName)
								.addObject("status", status);
		
	}
	/**
	 * 	获得deployid
	 * @param request
	 * @param response
	 * @throws Exception
	 * @author 魏嫚
	 */
	 @RequestMapping("deploy")
		@Action(description = "发布流程")
		public void deploy(HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			String preUrl = RequestUtil.getPrePage(request);
			Long id=RequestUtil.getLong(request,"id");
			String markid=id.toString();
			Markovchain markovchain=markovchainService.getById(id);

			String markXML=markovchain.getMarkovchainXML();
			String actMarkXml = BpmUtil.transform("markovchain",
					markovchain.getMarkovchainNAME(), markXML);
			Deployment deployment=bpmservice.deploy(markovchain.getMarkovchainNAME(), actMarkXml);
			
			//ProcessDefinitionEntity ent= bpmservice.getProcessDefinitionByDeployId(deployment.getId());
			markovchain.setActDeployId(new Long(deployment.getId()));
			markovchain.setMarkovchainXML(actMarkXml);
			markovchainService.update(markovchain);
			
			
			ResultMessage message = new ResultMessage(ResultMessage.Success,
					"发布流程成功!");
			addMessage(message, request);
			response.sendRedirect(preUrl);
		}
	 //得到环节点
	 public List<List<Integer>>getCycle(List<SequenceFlow> sequenceflow,List<NodeSet> nodes,int num)
	 {
		 List<NodeSet> nodesCycle=new ArrayList<NodeSet>();
		 int[] visited=new int[num];
		 int[][] e=new int[num][num];
		 System.out.println("图的初始化");
		 for(int i=0;i<nodes.size();i++)
		 {
			 for(int j=0;j<nodes.size();j++)
			 {
				 for(SequenceFlow seq:sequenceflow)
				 {
					 //如果有从nodes[i]到nodes[j]的路线 就让图e[i][j]=1
					 if(seq.getSourceRef().equals(nodes.get(i).getNodeId())&&seq.getTargetRef().equals(nodes.get(j).getNodeId()))
					 {
						 e[i][j]=1;
					 }
				 }
			 }
		 }
		 //*****测试代码*****
		 System.out.println("nodes中节点对应的数字");
		 for(int i=0;i<nodes.size();i++)
		 {
			 System.out.print(nodes.get(i).getNodeId()+" ");
		 }
		 System.out.println();
		 for(int i=0;i<num;i++)
		 {
			 for(int j=0;j<num;j++)
			 {
				 System.out.print(e[i][j]+" ");
			 }
			 System.out.println();
		 }
		 
		 List<List<Integer>> cycleNode=new ArrayList<List<Integer>>();
		 cycleNode=findCycle(0,visited,e,num,cycleNode);//存循环节点链表
		 if(cycleNode.size()>1)//有循环节点
		 {
			 System.out.println("循环的节点是：");
			 for(int i=0;i<cycleNode.size();i++)
			 {
				 for(int j=0;j<cycleNode.get(i).size();j++)
					 System.out.print(cycleNode.get(i).get(j)+" ");
				 System.out.println();
			 }
			 System.out.println();
		 }
		
		 return cycleNode;
	 }
	 public List<List<Integer>> findCycle(int v,int[]visited,int[][] e,int num,List<List<Integer>> newNode)   //递归DFS找到图中所有的循环节点并将其存在链表中
	    {
	        if(visited[v]==1)
	        {
	            int j;
	            if((j=trace.indexOf(v))!=-1)
	            {
	                System.out.print("Cycle:");
	                List<Integer> temp=new ArrayList<Integer>();
	                while(j<trace.size())
	                {
	                    System.out.print(trace.get(j)+" ");
	                    temp.add(trace.get(j));
	                    j++;
	                }
	                newNode.add(temp);
	                System.out.print("\n");
	                return newNode;
	            }
	            return newNode;
	        }
	        visited[v]=1;
	        trace.add(v);
	        
	        for(int i=0;i<num;i++)
	        {
	            if(e[v][i]==1)
	                findCycle(i,visited,e,num,newNode);
	        }
	        trace.remove(trace.size()-1);
			return newNode;
	    }
	 public Map<String,Integer> getCycleNodeMap(List<List<Integer>> nodesCycle,List<NodeSet> nodes)
	 {
		 Map<String,Integer> map=new HashMap<String,Integer>();
		 for(int i=0;i<nodesCycle.size();i++)
		 {
			for(int j=0;j<nodesCycle.get(i).size();j++)
			{
				int temp=nodesCycle.get(i).get(j);
				if(map.containsKey(nodes.get(temp).getNodeId()))
				{
					int inttemp=map.get(nodes.get(temp).getNodeId());
					map.put(nodes.get(temp).getNodeId(), inttemp+1);
				}
				else
				{
					map.put(nodes.get(temp).getNodeId(), 2);
				}
			}
		 }
		 return map;
	 }
	 public List<String> getlastnodelsit(List<String> nodelist,List<List<Integer>> nodesCycle,List<NodeSet> nodes)//最后将处理后的链表和循环链表合并
	 {
		 for(int i=0;i<nodesCycle.size();i++)
			{
				int size=nodesCycle.get(i).size();//第i个链表的大小
				
				String targetRef =nodes.get(nodesCycle.get(i).get(size-1)).getNodeId();//循环的结束点的元素
				int a=nodelist.indexOf(targetRef);//找到循环的结束点
				for(int j=0;j<size;j++)//从循环结束点将循环该走的路径再加进去
				{
					String sourceRef =nodes.get(nodesCycle.get(i).get(j)).getNodeId();
					nodelist.add(++a, sourceRef);
				}	
				
			}
		 return nodelist;
	 }
	 //获得马尔科夫链各个节点的概率
	 public Map<String,Integer> getNodeProbaility() throws DocumentException
	 {
		 String defId="10000060520000";
		 Long defIdLong=Long.valueOf(defId);
		 int number=100;//发生总的次数
		 WDefInformation defInfo=sysdefnodeergodicService.count(defIdLong);
		 List<Markovchain> list=defInfo.getMarkovchainList();
		 
		 System.out.println("----------------------------List的长度:"+list.size());
		 Map<Long,Float> map1=new HashMap<Long,Float>();//存链发生的概率
		 for(Markovchain a:list)//将依赖计算在内，计算马尔科夫链每个的概率
		 {
			 if(a.getDependId()==null)//如果没有依赖，发生概率不变
			 {
				 map1.put(a.getId(), a.getProbability());
			 }
			 else
			 {
				 Float probablility=0f;
				
				 if(map1.containsKey(a.getDependId()))
				 {
					 probablility=map1.get(a.getDependId());//所依赖的马尔科夫链的概率
				 }
				 try{
				 Float temp=probablility+a.getProbability();
				 System.out.println(temp);
				 map1.put(a.getDependId(),temp);
				 }
				 catch(Exception e)
				 {
					 System.out.println(e.toString());
				 }
				 
			 }
					 
			 
		 }
		 Map<Long,Integer> map2=new HashMap<Long,Integer>();//存链发生的次数
		  for (Long key : map1.keySet()) {
			   map2.put(key, 0);//初始化各个链的发生次数为0
			   System.out.println("key= "+ key + " and value= " + map1.get(key));
			  }
		  float Max = 1.0f, Min = 0;   
		  //计算每個鏈發生的次數
	      for (int i = 0; i < number; i++) {  
	            BigDecimal db = new BigDecimal(Math.random() * (Max - Min) + Min);  
	         /*   System.out.println(db.setScale(4, BigDecimal.ROUND_HALF_UP)// 保留4位小数并四舍五入  
	                    .toString()); */ 
	            float random=Float.parseFloat(db.setScale(4, BigDecimal.ROUND_HALF_UP).toString());
	            float tempMin=0;
	            float tempMax=0;
	            for (Long key : map1.keySet()) {
	            	tempMax=map1.get(key)+tempMax;
	 			  if(random>tempMin&&random<=tempMax)
	 			  {
	 				  int temp=map2.get(key);
	 				  temp++;
	 				  map2.put(key, temp);
	 			  }
	 			  tempMin=tempMin+map1.get(key);
	        }
	        }
	        for (Long key : map2.keySet()) {
				   System.out.println("key= "+ key + " and value= " + map2.get(key));
				  }
	        Map<String,Integer> map=new HashMap<String,Integer>();//存放各个节点发生的次数
	        //計算每個節點發生的次數
	         for (Long key : map2.keySet()) {
	        	 for(Markovchain a:list)
	        	 {
	        		 if(a.getId().equals(key))
	        		 {
	        			 Document document = DocumentHelper.parseText(a.getMarkovchainXML());
	        			 Element root = document.getRootElement(); 
	        			 List<Element> elementList=root.elements();
	        			 for(Element element:elementList)
	        			 {
	        				 String nodeId=element.attributeValue("id");
	        				 int temp=0;
	        				 if(map.containsKey(nodeId))
	        				 {
	        					 temp=map.get(nodeId);
	        					  
	        				 }
	        				 temp=temp+map2.get(key);
	        				 map.put(nodeId, temp);
	        			 }
	        		 }
	        	 }
	        	   
	 		}   
	         for (String key : map.keySet()) {
				   System.out.println("key= "+ key + " and value= " + map.get(key));
				  } 
	        
		 return map;
	 }
	 
	 public  void countProbability(Long defId)//根据发生次数和依赖计算发生概率
	 {
		 List<Markovchain> markovchainList=markovchainService.getByDefId(defId);
		 Long totalNum=0L;//总的发生次数
		 for(Markovchain markovchain :markovchainList)
		 {
			 if(null!=markovchain.getFrequency())
				 totalNum +=markovchain.getFrequency();
			 
		 }
		 for(Markovchain markovchain :markovchainList)
		 {
			 if(0L!=totalNum&&null!=markovchain.getFrequency())
			 {
				Long id=markovchain.getId();
			 	float probablity=((float)markovchain.getFrequency())/totalNum;		 	
			 	BigDecimal   b   =   new   BigDecimal(probablity); 
			 	float   f1   =   b.setScale(4,   BigDecimal.ROUND_HALF_UP).floatValue();//四舍五入法保留四位小数
			 	markovchain.setProbability(f1);
			 }
		 }
		 Map<Long,Float> map1=new HashMap<Long,Float>();//存链发生的概率
		 for(Markovchain markovchain :markovchainList)
		 {
			 if(markovchain.getDependId()==null)//如果没有依赖，发生概率不变
			 {
				 map1.put(markovchain.getId(), markovchain.getProbability());
			 }
			 else
			 {
				 Float probablility=0f;
				
				 if(map1.containsKey(markovchain.getDependId()))
				 {
					 probablility=map1.get(markovchain.getDependId());//所依赖的马尔科夫链的概率
				 }
				 try{
				 Float temp=probablility+markovchain.getProbability();
				 System.out.println(temp);
				 map1.put(markovchain.getDependId(),temp);
				 }
				 catch(Exception e)
				 {
					 System.out.println(e.toString());
				 }
				 
			 }
			 
		 }
		 for(Markovchain markovchain :markovchainList)
		 {
			 try {
				 if(map1.containsKey(markovchain.getId()))
				 {
					 markovchain.setProbability(map1.get(markovchain.getId()));
					
				 }
				 else
				 {
					 markovchain.setProbability(0);
				 }
				 markovchainService.save(markovchain);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
		 }
		 
	 }
	 
	 /**
		 * 	獲得json存入
		 * @param request
		 * @param response
		 * @throws Exception
		 * @author 那曉旭
		 */
		@RequestMapping("getJson")
		@Action(description="编辑马尔科夫链表")
		public void getJson(HttpServletRequest request) throws Exception
		{
			String st=request.getParameter("name");
			   System.out.println("st"+st);

		}
}