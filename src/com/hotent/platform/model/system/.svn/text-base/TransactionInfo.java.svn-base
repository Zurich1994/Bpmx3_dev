package com.hotent.platform.model.system;

import java.util.ArrayList;
import java.util.List;

import com.hotent.platform.model.bpm.BpmDefinition;

public class TransactionInfo {
	public BpmDefinition bpmtransaction = new BpmDefinition();
	public List<TransactionNodeInfo> transactionNodeInfoList;
	public List<String> allMethodList;
	public List<String> getAllMethodList() {
		return allMethodList;
	}
	public void setAllMethodList(List<String> allMethodList) {
		this.allMethodList = allMethodList;
	}
	public String  getTNodeList(int index){
		
		return transactionNodeInfoList.get(index).nodeId;
	}
	public String  setTNodeListNodeId(int i,String nodeName){
		
		return transactionNodeInfoList.get(i).nodeName = nodeName;
	}
	public void outTNodeList(){
		for(int i=0;i<transactionNodeInfoList.size();i++){
			
			System.out.println("事务图节点："+transactionNodeInfoList.get(i).nodeName);
		}
	}
	public String getTNode(int index){
		
		return transactionNodeInfoList.get(index).nodeName;
	}
	public int getTNodeListsize(){
		return transactionNodeInfoList.size();
	}
	public List<TransactionNodeInfo> getTransactionNodeInfoList() {
		return transactionNodeInfoList;
	}
	public void setTransactionNodeInfoList(
			List<TransactionNodeInfo> transactionNodeInfoList) {
		this.transactionNodeInfoList = transactionNodeInfoList;
	}
	
}
