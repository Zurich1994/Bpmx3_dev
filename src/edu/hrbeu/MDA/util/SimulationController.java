package edu.hrbeu.MDA.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hotent.core.annotion.Action;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.util.AppUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.ResultMessage;
import com.hotent.eventgraphrelation.model.eventgraphrelation.Eventgraphrelation;

import edu.hrbeu.MDA.DBAccess.DataResourceImp2;
import edu.hrbeu.MDA.bean.NodeSet;

public class SimulationController
{
	/**
	 * 存入测试所用数据
	 * @return List<Node>
	 * @throws 
	 * @author wyx
	 */
//	public List<NodeSet> readTestFile(){
//		List<NodeSet> nodelist = new ArrayList<NodeSet>();
//		  
//		NodeSet node1 = new NodeSet();
//		node1.setDefId(10000007370004L);
//		node1.setNodeId("StartEvent1");
//		node1.setNodename("开始");
//		node1.setNodetype("StartEvent");
//		String[][] timeandcount1 = {{"9:00","11"},{"9:10","24"},{"9:22","35"},{"9:32","53"}};
//		node1.setTimeandcount(timeandcount1);    
//		nodelist.add(node1);
//		
//		Node node2 = new Node();
//		node2.setDefid("10000007370004");
//		node2.setNodeid("UserTask1");
//		node2.setNodename("用户任务1");
//		node2.setNodetype("UserTask");
//		String[][] timeandcount2 = {{"9:02","11"},{"9:12","24"},{"9:24","35"},{"9:34","53"}};
//		node2.setTimeandcount(timeandcount2); 
//		nodelist.add(node2);
//		
//		Node node3 = new Node();
//		node3.setDefid("10000007370004");
//		node3.setNodeid("ScriptTask1");
//		node3.setNodename("脚本任务1");
//		node3.setNodetype("ScriptTask");
//		String[][] timeandcount3 = {{"9:02","11"},{"9:12","24"},{"9:24","35"},{"9:34","53"}};
//		node3.setTimeandcount(timeandcount3); 
//		nodelist.add(node3);
//		
//		Node node4 = new Node();
//		node4.setDefid("10000007370004");
//		node4.setNodeid("MessageTask1");
//		node4.setNodename("消息任务1");
//		node4.setNodetype("MessageTask");
//		String[][] timeandcount4 = {{"9:04","11"},{"9:14","24"},{"9:26","35"},{"9:36","53"}};
//		node4.setTimeandcount(timeandcount4);
//		nodelist.add(node4);
//		
//		Node node5 = new Node();
//		node5.setDefid("10000007370004");
//		node5.setNodeid("ServiceTask1");
//		node5.setNodename("WebService任务1");
//		node5.setNodetype("ServiceTask");
//		String[][] timeandcount5 = {{"9:04","11"},{"9:14","24"},{"9:26","35"},{"9:36","53"}};
//		node5.setTimeandcount(timeandcount5);
//		nodelist.add(node5);
//		
//		Node node6 = new Node();
//		node6.setDefid("10000007370004");
//		node6.setNodeid("SignTask1");
//		node6.setNodename("会签任务1");
//		node6.setNodetype("SignTask");
//		String[][] timeandcount6 = {{"9:04","11"},{"9:14","24"},{"9:26","35"},{"9:36","53"}};
//		node6.setTimeandcount(timeandcount6);
//		nodelist.add(node6);
//		
//		Node node7 = new Node();
//		node7.setDefid("10000007370004");
//		node7.setNodeid("EndEvent1");
//		node7.setNodename("结束1");
//		node7.setNodetype("EndEvent");
//		String[][] timeandcount7 = {{"9:06","11"},{"9:16","24"},{"9:28","35"},{"9:38","53"}};
//		node7.setTimeandcount(timeandcount7); 
//		nodelist.add(node7);
//		return nodelist;
//    }
		
	/**
	 * 读取输入数据
	 * @return String[][] array
	 * @throws Exception
	 * @author wyx
	 */
	public String[][] readin(String txt) throws IOException{
		BufferedReader br = null;
		String s = new String();
		String[][] array = new String[100][5];
		int lin=0;
		InputStreamReader isr;
		try {
			isr = new InputStreamReader(new FileInputStream(txt), "UTF-8"); 
			br = new BufferedReader(isr);
			while ((s = br.readLine()) != null) {    
				String arr[]=s.split("[\\t \\n]+");
				for (int i = 0; i < arr.length; i++) {
					array[lin][i]=java.lang.String.valueOf(arr[i]);
				}
				lin++;
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		return array;		
	}
	
	/**
	 * 处理节点信息
	 * @param 流程id（defid），节点id（nodeid）,节点名称（nodename），节点类型（nodetype），节点发生时间和发生量的二维数组（String[][]）   
	 * @return 
	 * @throws Exception
	 * @author wyx
	 */
	public void handleNode(String[][] temp) throws IOException{
System.out.println();		
		SimulationController sc = new SimulationController();
		DataResourceImp2 dataResource = new DataResourceImp2();
		//获取节点信息
		ParseXML pxml = new ParseXML();
		List<NodeSet> nodeset = pxml.computeAll(temp);
		for (NodeSet node : nodeset) {
			Long defid = node.getDefId();
			String nodeid = node.getNodeId();
			String nodename = node.getNodeName();
			String nodetype = node.getNodeType();
	        String[][] timeandcount = node.getTimeAndCount();
	        String subdefkey = node.getDefKey();
	       
	        //判断节点类型
	        
	        //外部子流程节点（还有待考虑）
	        if(nodetype.equalsIgnoreCase("callActivity")){
	        	System.out.println("该节点类型是外部子流程节点");
	        	//获取子流程id
	        	String subDefKey=subdefkey;	
	        	String subDefId = dataResource.getDefId(subDefKey);
	        	//将子流程图信息存入二维数组，进行处理
	        	sc.subDef("1", subDefId, timeandcount);
	        }
	        //开始节点
	        if(nodetype.equalsIgnoreCase("StartEvent")){
	        	System.out.println("该节点类型是开始节点");
	        	//发生时刻和发生量的取值
	        	System.out.println("发生时间和发生量是:");
		        for(int j=0;j<timeandcount.length;j++){
		        	String time = timeandcount[j][0];
		        	String count = timeandcount[j][1];
		        	System.out.print(time+" "+count+",");
		        }
		        System.out.println();		
	        }
	        //结束节点
	        if(nodetype.equalsIgnoreCase("EndEvent")){
	        	System.out.println("该节点类型是结束节点");
	        	//发生时刻和发生量的取值
	        	System.out.println("发生时间和发生量是:");
		        for(int j=0;j<timeandcount.length;j++){
		        	String time = timeandcount[j][0];
		        	String count = timeandcount[j][1];
		        	System.out.print(time+" "+count+",");
		        }
		        System.out.println();
			
	        }
	        //用户节点
	        if(nodetype.equalsIgnoreCase("UserTask")){
	        	System.out.println("该节点类型是用户节点");
	        	//查找表单信息FORMKEY
	        	List<String> formKey = dataResource.getFormKey(defid);
	        	if(formKey.size()!=0){
	        		for(int i = 0; i< formKey.size(); i++){
		        		formKey.get(i);
		        		if(formKey.get(i).equals("0") == false){
		        			String formdefid = dataResource.getForms(formKey.get(i));
		        			System.out.println("表单信息formdefid："+formdefid);
		        		}
		        	}	
	        	}
	        	//发生时刻和发生量的取值
	        	System.out.println("发生时间和发生量是:");
		        for(int j=0;j<timeandcount.length;j++){
		        	String time = timeandcount[j][0];
		        	String count = timeandcount[j][1];
		        	System.out.print(time+" "+count+",");
		        }
		        System.out.println();
	        				
	        }
	        //脚本节点
	        if(nodetype.equalsIgnoreCase("script")){
	        	System.out.println("该节点类型是脚本节点");
	        	//发生时刻和发生量的取值
	        	System.out.println("发生时间和发生量是:");
		        for(int j=0;j<timeandcount.length;j++){
		        	String time = timeandcount[j][0];
		        	String count = timeandcount[j][1];
		        	System.out.print(time+" "+count+",");
		        }
		        System.out.println();
	        }
	        //消息节点
	        if(nodetype.equalsIgnoreCase("email")){
	        	System.out.println("该节点类型是消息节点");
	        	//发生时刻和发生量的取值
	        	System.out.println("发生时间和发生量是:");
		        for(int j=0;j<timeandcount.length;j++){
		        	String time = timeandcount[j][0];
		        	String count = timeandcount[j][1];
		        	System.out.print(time+" "+count+",");
		        }
		        System.out.println();
	        }
	        //WebService节点
	        if(nodetype.equalsIgnoreCase("WebService")){
	        	System.out.println("该节点类型是WebService节点");
	        	//发生时刻和发生量的取值
	        	System.out.println("发生时间和发生量是:");
		        for(int j=0;j<timeandcount.length;j++){
		        	String time = timeandcount[j][0];
		        	String count = timeandcount[j][1];
		        	System.out.print(time+" "+count+",");
		        }
		        System.out.println();
	        }
	        //会签节点
	        if(nodetype.equalsIgnoreCase("multiUserTask")){
	        	System.out.println("该节点类型是会签节点");
	        	//发生时刻和发生量的取值
	        	System.out.println("发生时间和发生量是:");
		        for(int j=0;j<timeandcount.length;j++){
		        	String time = timeandcount[j][0];
		        	String count = timeandcount[j][1];
		        	System.out.print(time+" "+count+",");
		        }
		        System.out.println();
	        }		

		}
		
		 //处理节点绑定事件按钮
		for (NodeSet node : nodeset) {
			Long defid = node.getDefId();
			String nodeid = node.getNodeId();
			//String nodename = node.getNodeName();
			//String nodetype = node.getNodeType();
	        String[][] timeandcount = node.getTimeAndCount();	        
	        //处理节点绑定事件按钮
	        List eventInfo = dataResource.getEventInfo(defid, nodeid);
	        if(eventInfo.size() != 0){ 
	        	for(int i = 0;i<eventInfo.size();i++){
	        		Eventgraphrelation eventgraphrelation = (Eventgraphrelation)eventInfo.get(i);
	        		//事件按钮F_eventID,事件发生概率F_probability,事件绑定的事物图F_defbID
	        		String F_eventID = eventgraphrelation.getEventID();
	        		String F_probability = eventgraphrelation.getProbability();
	        		String F_defbID = eventgraphrelation.getDefbID();		
	        		//判断是否有事物图
	        		if(F_defbID != ""){
	        			System.out.println();
	        			System.out.println("存在绑定事件按钮及事物图！");
			        	System.out.println("节点defid=" + defid + " nodeid= "+ nodeid+"存在事件按钮相关信息，事件按钮id="+F_eventID+"发生概率probability="+F_probability+"事物图defid= "+F_defbID);
	        			//存在事物图，将事物图信息存入二维数组，进行处理
			        	sc.subDef("1", F_defbID, timeandcount);
	        		}
	        	}
	        }
		}
		
		
	}
	
	/**
	 * 将子图或事物图信息存入二维数组
	 * @param projectid,defid, nodeid,String[][] timeandcount
	 * @return 
	 * @throws Exception
	 * @author wyx
	 * @throws IOException 
	 */
	public void subDef(String projectid,String defid,String[][] timeandcount) throws IOException{
		SimulationController sc = new SimulationController();
		DataResourceImp2 dataResource = new DataResourceImp2();
        			String[][] transactionArray = new String[timeandcount.length+2][2];
        			transactionArray[0][0]=projectid;
        			transactionArray[1][0]=defid.toString();       			
        			int k=1;
        			for(int j=0;j<timeandcount.length;j++){
        				k++;
        				transactionArray[k][0]=timeandcount[j][0];
						transactionArray[k][1]=timeandcount[j][1];
        			}
        			//测试数组是否正确
        			System.out.println("projectid="+transactionArray[0][0]);
        			System.out.println("F_defbID="+transactionArray[1][0]);
        			for(int j=2;j<transactionArray.length;j++){
        				System.out.println(transactionArray[j][0]+" "+transactionArray[j][1]+",");		
        			}
        			//处理事物图或子流程信息
        			sc.handleNode(transactionArray);		        	      
	}
	
		
	
	/**
	 * 总入口
	 * @param    
	 * @return 
	 * @throws Exception
	 * @author wyx
	 */
	public  void mainEntrance(String[] args) throws IOException {
		SimulationController sc = new SimulationController();
		//读取输入数据
		String[][] array=new String[400][400];
		array=sc.readin("C:\\test.txt");
		//处理节点信息
		sc.handleNode(array);
	}	
	
	/**
	 * 测试
	 * @param    
	 * @return 
	 * @throws Exception
	 * @author wyx
	 */
	public  static void main(String[] args) throws IOException {
		SimulationController sc = new SimulationController();
		//读取输入数据
		String[][] array=new String[400][5];
		array=sc.readin("C:\\test.txt");
		System.out.println("读取C:\\test.txt");
		//处理节点信息
		sc.handleNode(array);
	}	
}
