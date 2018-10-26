package com.hotent.platform.model.system;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.activityDetail.model.activityDetail.ActivityDetail;
import com.hotent.core.model.BaseModel;
import com.hotent.platform.dao.bpm.BpmNodeSetDao;
import com.hotent.platform.model.bpm.BpmNodeSet;

public class WNodeInformation extends BaseModel
{
	//主键
	
	/**
	 *节点重要信息
	 */
	private double  exenum;//mark
	private double  dependExenum;//依赖mark、
	
	private double  operateExenum;//mark
	private double  operateDependExenum;//依赖mark、
	
	
	
	private String  actDefid;
	private String  activityid;
	public BpmNodeSet bpmNodeSet;//节点基本信息
	public ActivityDetail activityDetail;//节点需要统计的信息
	public WOperateInfo operateInfo;//节点下绑定的操作图
	
	private String  macWork;//机器作业
	private String  manWork;//人工作业
	private String  infoStatic;//静态信息
	private String  infoDynamic;//动态信息
	private String  n_a;//n/a
	
	private String  machOpeSta;
	private String  workOpeSta;
	
	private String  nodeId;
	private long runs;
	private long increaseTime = 0l;
	/**
	 *操作个数（总数）
	 */
	public WNodeInformation(String actDef_id1,String activity_id1){  		
		actDefid=actDef_id1;
		activityid=activity_id1;
		exenum=0;
		dependExenum=0;
		operateExenum=0;
		operateDependExenum=0;
	    bpmNodeSet=null;
	    activityDetail=null;
	    operateInfo=null;;
	}
	public WNodeInformation nodePrimaryStatistics(WOperateInfo operateInfo)
	{		
			this.operateExenum=this.exenum*operateInfo.getExeNum();		
		this.operateDependExenum=this.dependExenum*operateInfo.getDependExeNum();
		return this;
	}
	public String getActDefid() {
		return actDefid;
	}
	public void setActDefid(String actDefid) {
		this.actDefid = actDefid;
	}
	public String getActivityid() {
		return activityid;
	}
	public void setActivityid(String activityid) {
		this.activityid = activityid;
	}
	public double getExeNum() {
		return exenum;
	}
	public void setExeNum(double exenum) {
		this.exenum = exenum;
	}
	public double getDependExeNum() {
		return dependExenum;
	}
	public void setDependExeNum(double dependexenum) {
		this.dependExenum = dependexenum;
	}
	
	
	
	public String getMacWork() {
		return macWork;
	}
	public void setMacWork(String macWork) {
		this.macWork = macWork;
	}
	public String getManWork() {
		return manWork;
	}
	public void setManWork(String manWork) {
		this.manWork = manWork;
	}
	
	
	public String getInfoStatic() {
		return infoStatic;
	}
	public void setInfoStatic(String infoStatic) {
		this.infoStatic = infoStatic;
	}
	public String getInfoDynamic() {
		return infoDynamic;
	}
	public void setInfoDynamic(String infoDynamic) {
		this.infoDynamic = infoDynamic;
	}
	
	public String getn_a() {
		return n_a;
	}
	public void setn_a(String n_a) {
		this.n_a = n_a;
	}
	
    public String getmachOpeSta() {
	  return machOpeSta;
    }
    public void setmachOpeSta(String machOpeSta) {
	this.machOpeSta = machOpeSta;
    }
    public String getworkOpeSta() {
	return activityid;
    }
    public void setworkOpeSta(String workOpeSta) {
	this.workOpeSta = workOpeSta;
    }
    
    
    public String getnodeId() {
    	return nodeId;
    }
    public void setnodeId(String nodeId) {
    	this.nodeId = nodeId;
    }
    public WOperateInfo getOperateInfo() {
		return operateInfo;
}
public void setOperateInfo(WOperateInfo operateInfo) {
		this.operateInfo = operateInfo;
}
public long getIncreaseTime() {
	return increaseTime;
}
public void setIncreaseTime(long increaseTime) {
	this.increaseTime = increaseTime;
}
public double getOperateExeNum() {
	return operateExenum;
}
public void setOperateExeNum(double operateExenum) {
	this.operateExenum = operateExenum;
}
public double getOperateDependExeNum() {
	return operateDependExenum;
}
public void setOperateDependExeNum(double operateDependExenum) {
	this.operateDependExenum = operateDependExenum;
}
public long getRuns() {
	return runs;
}
public void setRuns(long runs) {
	this.runs = runs;
}
    
	
    }