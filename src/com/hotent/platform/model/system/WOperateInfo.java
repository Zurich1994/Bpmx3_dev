package com.hotent.platform.model.system;

import java.util.ArrayList;
import java.util.List;

import com.hotent.Markovchain.model.Markovchain.Markovchain;
import com.hotent.core.model.BaseModel;
import com.hotent.platform.model.bpm.BpmDefinition;
import com.hotent.platform.model.form.BpmFormDef;

public class WOperateInfo extends BaseModel{
	public BpmDefinition bpmDefinition;
	public List<OperateNodeInfo> operateNodeInfoList ;//操作图中的节点列表
	public List<Markovchain> markovchainList;//操作图的马尔科夫链
	private double  exeNum=0;
	private double  dependexeNum=0;
	public List<String> allMTList;
	public List<String> allMethodList;
	public List<String> allTableList;
	public List<BpmFormDef> allBpmFormDefList;
	public List<String> getAllMethodList() {
		return allMethodList;
	}
	public void setAllMenthodList(List<String> allMenthodList) {
		this.allMethodList = allMenthodList;
	}
	public List<String> getAllTableList() {
		return allTableList;
	}
	public void setAllTableList(List<String> allTableList) {
		this.allTableList = allTableList;
	}
	public WOperateInfo operatePrimaryStatistics(OperateNodeInfo operateNode)
	{
		this.exeNum=exeNum+operateNode.getExeNum();
		this.dependexeNum=dependexeNum+operateNode.getDependExeNum();
		return this;
	}
	public double getExeNum() {
		return exeNum;
	}
	public void setExeNum(double exeNum) {
		this.exeNum = exeNum;
	}
	public double getDependExeNum() {
		return dependexeNum;
	}
	public void setDependExeNum(double dependexeNum) {
		this.dependexeNum = dependexeNum;
	}
	
	
	public List<Markovchain> getMarkovchainList() {
		return markovchainList;
	}
	public void setMarkovchainList(List<Markovchain> markovchainList) {
		this.markovchainList = markovchainList;
	}
	public WOperateInfo(){};
	public WOperateInfo(BpmDefinition bpmDefinition1){
		bpmDefinition=bpmDefinition1;
		operateNodeInfoList=new ArrayList<OperateNodeInfo>();
		markovchainList=new ArrayList<Markovchain>();
	}
	
	public OperateNodeInfo getOperateNOdeInfo(int i){
		return operateNodeInfoList.get(i);
		
	}
	public List<OperateNodeInfo> getOperateNodeInfoList() {
		return operateNodeInfoList;
	}
	public void setOperateNodeInfoList(int index,OperateNodeInfo operateNodeInfo) {
		this.operateNodeInfoList.add(index,operateNodeInfo);
	}
//	public OperateNodeInfo getOperateNodeInfo() {
//		return operateNodeInfo;
//	}
//	public void setOperateNodeInfo(OperateNodeInfo operateNodeInfo) {
//		this.operateNodeInfo = operateNodeInfo;
//	}
	public int oNodeListSize(){
		return operateNodeInfoList.size();
		
	}
	

	
	public void setBpmDefinition(BpmDefinition bpmDefinition){
		 
		this.bpmDefinition = bpmDefinition;
	}
	public BpmDefinition getBpmDefinition(){
		 return new BpmDefinition();
	}
	public void outList(){
	     for(int i=0;i<operateNodeInfoList.size();i++){
	    	 
	    	 System.out.println("								操作图节点"+operateNodeInfoList.get(i).nodeId);
	     }
	}
	public String  getList(int index){
	     
   	 return operateNodeInfoList.get(index).nodeId;
    
	
	
}
	
}

