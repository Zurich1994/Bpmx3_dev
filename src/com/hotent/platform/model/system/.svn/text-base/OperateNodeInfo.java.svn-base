package com.hotent.platform.model.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.hotent.core.model.BaseModel;
import com.hotent.formParcel.model.formParcel.FormParcel;
import com.hotent.platform.model.form.BpmFormDef;
import com.hotent.platform.model.form.BpmFormField;
import com.hotent.platform.model.form.BpmFormTable;
import com.hotent.tableParcel.model.tableParcel.TableParcel;

public class OperateNodeInfo extends BaseModel{
	
	
	public String nodeId;
	public String nodeName;
	
	
	private double  exeNum=0; 
	private double  dependexeNum=0;
	private long runs;
	private long delayTime = 1l;//延迟时间
	private long thinkTime = 5l;//思考时间
	private long increaseTime = 0l;
	
	
	public long getIncreaseTime() {
		return increaseTime;
	}
	public void setIncreaseTime(long increaseTime) {
		this.increaseTime = increaseTime;
	}
	public TransactionInfo transactionInfo=null; //添加事务图
	public BpmFormDef bpmFormDef=null;//表单
	public BpmFormTable bpmFormTable = new BpmFormTable();//生成表单的表
	public FormParcel formParcel = new FormParcel();//生成表单的数据包
	public List<FormParcel> formParcelList = new ArrayList<FormParcel>();//数据包List
	public List<TableParcel> tableParcelList;//生成数据包的表List
	public List<BpmFormField> bpmFormFieldList = new ArrayList<BpmFormField>();
	public List<List<Map>> ListMap = new ArrayList<List<Map>>();

	
	//public List<TransactionInfo> TransactionInfoList = new ArrayList<TransactionInfo>();
	public double getExeNum() {  //***节点标准运行次数开始
		return exeNum;
	}
	public void setExeNum(double exeNum) {
		this.exeNum = exeNum;
	}                          //***结束
	
	public double getDependExeNum() {  //***节点依赖运行次数开始
		return dependexeNum;
	}
	public void setDependExeNum(double dependexeNum) {
		this.dependexeNum = dependexeNum;
	}                          //***结束
	public long getRuns() {
		return runs;
	}
	public void setRuns(long runs) {
		this.runs = runs;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	public long getDelayTime() {
		return delayTime;
	}
	public void setDelayTime(long delayTime) {
		this.delayTime = delayTime;
	}
	public long getThinkTime() {
		return thinkTime;
	}
	public void setThinkTime(long thinkTime) {
		this.thinkTime = thinkTime;
	}
}
