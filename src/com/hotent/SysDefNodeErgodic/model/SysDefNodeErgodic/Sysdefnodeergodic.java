package com.hotent.SysDefNodeErgodic.model.SysDefNodeErgodic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import com.hotent.core.model.BaseModel;
import com.hotent.platform.model.bpm.AuthorizeRight;
/**
 * 对象功能:子系统流程节点遍历 Model对象
 */
public class Sysdefnodeergodic extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *主键
	 */
	protected Long  zj;
	/**
	 *子系统名字
	 */
	protected String  sysName;
	/**
	 *子系统id
	 */
	protected String  sysId;
	/**
	 *子系统下未设置节点个数
	 */
	protected int  sysNotSetNum;
	/**
	 *流程名字
	 */
	protected String  defName;
	/**
	 *流程id
	 */
	protected String  defId;
	/**
	 *流程版本id
	 */
	protected String  actDefId;
	/**
	 *流程typename
	 */
	protected String typeName;
	/**
	 *流程status
	 */
	/* 流程状态
	 * 0:未发布
	 * 1:发布
	 * 2.禁用
	 * 3.禁用,并禁用实例
	 * 4.测试状态
	 */
	
	protected Short  status;
	
	
	
	/**
	 *流程下未设置节点个数
	 */
	protected int  defNotSetNum;
	
	private double defExenum;
	private double defDependExenum;
	private double defOperateExenum;//mark
	private double defOperateDependExenum;//依赖mark、
	
	
	
	/**
	 *节点具体id
	 */	
	
	protected String  nodeIdNum;//Long 一串数字的节点id  取nodeset表中的 setId 字段
	/**
	 *节点名字
	 */	
	protected String  nodeName;//汉语名字
	/**
	 *节点id
	 */
	protected String  nodeId;//英文名字
	/**
	 *节点作业名
	 */
	protected String  nodeWorkName;//节点作业名字
	/**
	 *判断是否设置
	 */
	protected String setJudge;//"否"或""
	/**
	 *设置
	 */
	protected String setUp;//“设置”
	
	
	private double nodeExenum;
	private double nodeDependExenum;
	private double nodeOperateExenum;//mark
	private double nodeOperateDependExenum;//依赖mark、
	
	
    //流程分管授权权限对象
  	protected AuthorizeRight authorizeRight;
  	
  	
  	/**
	 *操作图id
	 */
  	//子事务图（操作图）
  	protected String  operateDefId;//操作图id
	/**
	 *操作图名字
	 */
	protected String  operateName;//操作图名字
	/**
	 *操作图状态
	 */
	protected Short  operateStatus;//操作图状态
	
	private double operateExenum;//mark
	private double operateDependExenum;//依赖mark、
	
	/**
	 *操作图节点英文名字	
	 */
	protected String operateNodeId;//操作图节点英文名字	
	/**
	 *操作图节点汉语名字
	 */
	protected String operateNodeName;//操作图节点汉语名字
  
	private double operateNodeExenum;//mark
	private double operateNodeDependExenum;//依赖mark、
	
	
	
	
	/**
	 *事务图id
	 */
  	//子事务图（事务图）
  	protected String  transactionDefId;//事务图id
	/**
	 *事务图名字
	 */
	protected String  transactionName;//事务图名字    表单
	/**
	 *事务图状态
	 */
	protected Short  transactionStatus;//事务图状态
	
	/**
	 *事务图节点英文名字	或表单id或数据包id
	 */
	protected String transactionNodeId;//事务图节点英文名字	  
	/**
	 *事务图节点汉语名字   或表单或数据包
	 */
	protected String transactionNodeName;//事务图节点汉语名字       或表单或数据包
  	
	protected String tableParcelId; //数据包下表名id

	protected String tableParcelName;//数据包下表名名字
	
	protected String tableParcelNodeId;//数据包下表名下字段名字
	
	protected String tableParcelNodeName;//数据包下表名下字段名字
	
	
	
	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Short getOperateStatus() {
		return operateStatus;
	}

	public void setOperateStatus(Short operateStatus) {
		this.operateStatus = operateStatus;
	}

	public Short getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(Short transactionStatus) {
		this.transactionStatus = transactionStatus;
	}
	
	
	public String getTableParcelNodeId() {
		return tableParcelNodeId;
	}

	public void setTableParcelNodeId(String tableParcelNodeId) {
		this.tableParcelNodeId = tableParcelNodeId;
	}

	public String getTableParcelNodeName() {
		return tableParcelNodeName;
	}

	public void setTableParcelNodeName(String tableParcelNodeName) {
		this.tableParcelNodeName = tableParcelNodeName;
	}
	
	public String getTableParcelId() {
		return tableParcelId;
	}

	public void setTableParcelId(String tableParcelId) {
		this.tableParcelId = tableParcelId;
	}

	public String getTableParcelName() {
		return tableParcelName;
	}

	public void setTableParcelName(String tableParcelName) {
		this.tableParcelName = tableParcelName;
	}

	public String getTransactionDefId() {
		return transactionDefId;
	}

	public void setTransactionDefId(String transactionDefId) {
		this.transactionDefId = transactionDefId;
	}

	public String getTransactionName() {
		return transactionName;
	}

	public void setTransactionName(String transactionName) {
		this.transactionName = transactionName;
	}

	public String getTransactionNodeId() {
		return transactionNodeId;
	}

	public void setTransactionNodeId(String transactionNodeId) {
		this.transactionNodeId = transactionNodeId;
	}

	public String getTransactionNodeName() {
		return transactionNodeName;
	}

	public void setTransactionNodeName(String transactionNodeName) {
		this.transactionNodeName = transactionNodeName;
	}

	public String getOperateNodeId() {
		return operateNodeId;
	}

	public void setOperateNodeId(String operateNodeId) {
		this.operateNodeId = operateNodeId;
	}

	public String getOperateNodeName() {
		return operateNodeName;
	}

	public void setOperateNodeName(String operateNodeName) {
		this.operateNodeName = operateNodeName;
	}

	public String getOperateDefId() {
		return operateDefId;
	}

	public void setOperateDefId(String operateDefId) {
		this.operateDefId = operateDefId;
	}

	public String getOperateName() {
		return operateName;
	}

	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}

	public AuthorizeRight getAuthorizeRight()
	{
		return authorizeRight;
	}

	public void setAuthorizeRight(AuthorizeRight authorizeRight)
	{
		this.authorizeRight = authorizeRight;
	}

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setZj(Long zj) 
	{
		this.zj = zj;
	}
	/**
	 * 返回 主键
	 * @return
	 */
	public Long getZj() 
	{
		return this.zj;
	}
	public void setSysName(String sysName) 
	{
		this.sysName = sysName;
	}
	/**
	 * 返回 子系统名字
	 * @return
	 */
	public String getSysName() 
	{
		return this.sysName;
	}
	public void setSysId(String sysId) 
	{
		this.sysId = sysId;
	}
	/**
	 * 返回 子系统id
	 * @return
	 */
	public String getSysId() 
	{
		return this.sysId;
	}
	
	public void setSysNotSetNum(int SysNotSetNum) 
	{
		this.sysNotSetNum = SysNotSetNum;
	}
	/**
	 * 返回 子系统内为设置的节点个数
	 * @return
	 */
	public int getSysNotSetNum() 
	{
		return this.sysNotSetNum;
	}
	
	public void setDefName(String defName) 
	{
		this.defName = defName;
	}
	/**
	 * 返回 流程名字
	 * @return
	 */
	public String getDefName() 
	{
		return this.defName;
	}
	public void setDefId(String defId) 
	{
		this.defId = defId;
	}
	/**
	 * 返回 流程id
	 * @return
	 */
	public String getDefId() 
	{
		return this.defId;
	}	
	public void setActDefId(String actDefId) 
	{
		this.actDefId = actDefId;
	}
	/**
	 * 返回 流程版本id
	 * @return
	 */
	public String getActDefId() 
	{
		return this.actDefId;
	}
	public void setTypeName(String typeName) 
	{
		this.typeName = typeName;
	}
	/**
	 * 返回 流程typeName
	 * @return
	 */
	public String getTypeName() 
	{
		return this.typeName;
	}
	public void setstatus(Short status) 
	{
		this.status = status;
	}
	/**
	 * 返回 流程status
	 * @return
	 */
	public Short getstatus() 
	{
		return this.status;
	}
	
	public void setDefNotSetNum(int DefNotSetNum) 
	{
		this.defNotSetNum = DefNotSetNum;
	}
	/**
	 * 返回 子系统内为设置的节点个数
	 * @return
	 */
	public int getDefNotSetNum() 
	{
		return this.defNotSetNum;
	}
	
	
	public void setNodeIdNum(String nodeIdNum) 
	{
		this.nodeIdNum = nodeIdNum;
	}
	/**
	 * 返回 节点名字
	 * @return
	 */
	public String getNodeIdNum() 
	{
		return this.nodeIdNum;
	}
	
	public void setNodeName(String nodeName) 
	{
		this.nodeName = nodeName;
	}
	/**
	 * 返回 节点名字
	 * @return
	 */
	public String getNodeName() 
	{
		return this.nodeName;
	}
	public void setNodeId(String nodeId) 
	{
		this.nodeId = nodeId;
	}
	/**
	 * 返回 节点Id
	 * @return
	 */
	public String getNodeId() 
	{
		return this.nodeId;
	}
	public void setNodeWorkName(String nodeWorkName) 
	{
		this.nodeWorkName = nodeWorkName;
	}
	/**
	 * 返回 节点作业名
	 * @return
	 */
	public String getNodeWorkName() 
	{
		return this.nodeWorkName;
	}
	public void setSetJudge(String setJudge) 
	{
		this.setJudge = setJudge;
	}
	/**
	 * 返回 判断设置
	 * @return
	 */
	public String getSetJudge() 
	{
		return this.setJudge;
	}
	public void setSetUp(String setUp) 
	{
		this.setUp = setUp;
	}
	/**
	 * 返回 判断设置
	 * @return
	 */
	public String getSetUp() 
	{
		return this.setUp;
	}
	
   	public double getDefExenum() {
		return defExenum;
	}

	public void setDefExenum(double defExenum) {
		this.defExenum = defExenum;
	}

	public double getDefDependExenum() {
		return defDependExenum;
	}

	public void setDefDependExenum(double defDependExenum) {
		this.defDependExenum = defDependExenum;
	}

	public double getDefOperateExenum() {
		return defOperateExenum;
	}

	public void setDefOperateExenum(double defOperateExenum) {
		this.defOperateExenum = defOperateExenum;
	}

	public double getDefOperateDependExenum() {
		return defOperateDependExenum;
	}

	public void setDefOperateDependExenum(double defOperateDependExenum) {
		this.defOperateDependExenum = defOperateDependExenum;
	}

	public double getNodeExenum() {
		return nodeExenum;
	}

	public void setNodeExenum(double nodeExenum) {
		this.nodeExenum = nodeExenum;
	}

	public double getNodeDependExenum() {
		return nodeDependExenum;
	}

	public void setNodeDependExenum(double nodeDependExenum) {
		this.nodeDependExenum = nodeDependExenum;
	}

	public double getNodeOperateExenum() {
		return nodeOperateExenum;
	}

	public void setNodeOperateExenum(double nodeOperateExenum) {
		this.nodeOperateExenum = nodeOperateExenum;
	}

	public double getNodeOperateDependExenum() {
		return nodeOperateDependExenum;
	}

	public void setNodeOperateDependExenum(double nodeOperateDependExenum) {
		this.nodeOperateDependExenum = nodeOperateDependExenum;
	}

	public double getOperateExenum() {
		return operateExenum;
	}

	public void setOperateExenum(double operateExenum) {
		this.operateExenum = operateExenum;
	}

	public double getOperateDependExenum() {
		return operateDependExenum;
	}

	public void setOperateDependExenum(double operateDependExenum) {
		this.operateDependExenum = operateDependExenum;
	}

	public double getOperateNodeExenum() {
		return operateNodeExenum;
	}

	public void setOperateNodeExenum(double operateNodeExenum) {
		this.operateNodeExenum = operateNodeExenum;
	}

	public double getOperateNodeDependExenum() {
		return operateNodeDependExenum;
	}

	public void setOperateNodeDependExenum(double operateNodeDependExenum) {
		this.operateNodeDependExenum = operateNodeDependExenum;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof Sysdefnodeergodic)) 
		{
			return false;
		}
		Sysdefnodeergodic rhs = (Sysdefnodeergodic) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.zj, rhs.zj)
		.append(this.sysName, rhs.sysName)
		.append(this.sysId, rhs.sysId)
		.append(this.sysNotSetNum, rhs.sysNotSetNum)
		.append(this.defName, rhs.defName)
		.append(this.defId, rhs.defId)
		.append(this.actDefId, rhs.actDefId)
		.append(this.typeName, rhs.typeName)
		.append(this.status, rhs.status)
		.append(this.defNotSetNum, rhs.defNotSetNum)
		.append(this.nodeIdNum, rhs.nodeIdNum)
		.append(this.nodeName, rhs.nodeName)
		.append(this.nodeId, rhs.nodeId)
		.append(this.nodeWorkName, rhs.nodeWorkName)
		
		.append(this.operateDefId, rhs.operateDefId)
		.append(this.operateName, rhs.operateName)
		.append(this.operateStatus, rhs.operateStatus)
		
		.append(this.operateNodeId, rhs.operateNodeId)
		.append(this.operateNodeName, rhs.operateNodeName)
		
		.append(this.transactionDefId, rhs.transactionDefId)
		.append(this.transactionName, rhs.transactionName)
		.append(this.transactionStatus, rhs.transactionStatus)
		
		.append(this.transactionNodeId, rhs.transactionNodeId)
		.append(this.transactionNodeName, rhs.transactionNodeName)
		
		.append(this.tableParcelId, rhs.tableParcelId)
		.append(this.tableParcelName, rhs.tableParcelName)
		
		.append(this.tableParcelNodeId, rhs.tableParcelNodeId)
		.append(this.tableParcelNodeName, rhs.tableParcelNodeName)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id)
		.append(this.zj) 
		.append(this.sysName) 
		.append(this.sysId) 
		.append(this.sysNotSetNum) 
		.append(this.defName) 
		.append(this.defId) 
		.append(this.actDefId) 
		.append(this.typeName)
		.append(this.status) 
		.append(this.defNotSetNum) 
		.append(this.nodeIdNum) 
		.append(this.nodeName) 
		.append(this.nodeId) 
		.append(this.setJudge) 
		.append(this.setUp) 
		.append(this.nodeWorkName) 
		.append(this.operateDefId) 
		.append(this.operateName) 
		.append(this.operateStatus) 
		.append(this.operateNodeId) 
		.append(this.operateNodeName) 
		.append(this.transactionDefId) 
		.append(this.transactionName) 
		.append(this.transactionStatus) 
		.append(this.transactionNodeId) 
		.append(this.transactionNodeName) 
		.append(this.tableParcelId) 
		.append(this.tableParcelName) 
		.append(this.tableParcelNodeId) 
		.append(this.tableParcelNodeName) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("zj", this.zj) 
		.append("sysName", this.sysName) 
		.append("sysId", this.sysId) 
		.append("sysNotSetNum", this.sysNotSetNum) 
		.append("defName", this.defName) 
		.append("defId", this.defId) 
		.append("actDefId", this.actDefId) 
		.append("typeName", this.typeName) 
		.append("status", this.status) 
		.append("defNotSetNum", this.defNotSetNum) 
		.append("nodeIdNum", this.nodeIdNum) 
		.append("nodeName", this.nodeName) 
		.append("nodeId", this.nodeId) 
		.append("setJudge", this.setJudge) 
		.append("setUp", this.setUp) 
		.append("nodeWorkName", this.nodeWorkName) 
		.append("operateDefId", this.operateDefId) 
		.append("operateName", this.operateName) 
		.append("operateStatus", this.operateStatus) 
		.append("operateNodeId", this.operateNodeId) 
		.append("operateNodeName", this.operateNodeName) 
		.append("transactionDefId", this.transactionDefId) 
		.append("transactionName", this.transactionName) 
		.append("transactionStatus", this.transactionStatus) 
		.append("transactionNodeId", this.transactionNodeId) 
		.append("transactionNodeName", this.transactionNodeName) 
		.append("tableParcelId", this.tableParcelId) 
		.append("tableParcelName", this.tableParcelName) 
		.append("tableParcelNodeId", this.tableParcelNodeId) 
		.append("tableParcelNodeName", this.tableParcelNodeName) 
		.toString();
	}
	public Sysdefnodeergodic(
			int id1,
			int zj1,
			String sysName1,
			String sysId1,
			int sysNotSetNum1,
			String defName1,
			String defId1,
			String actDefId1,
			String typeName1,
			Short status1,
			int defNotSetNum1,
			double defExenum1,
			double defDependExenum1,
			double defOperateExenum1,//mark
			double defOperateDependExenum1,//依赖mark、
			
			String nodeIdNum1,
			String nodeName1,
			String nodeId1,
			String setJudge1,
			String setUp1,
			String nodeWorkName1,
			
			double nodeExenum1,
			double nodeDependExenum1,
			double nodeOperateExenum1,//mark
			double nodeOperateDependExenum1,//依赖mark、
			
			String operateDefId1,
			String operateName1,
			Short  operateStatus1,
			
			double operateExenum1,//mark
			double operateDependExenum1,//依赖mark、
			
			String operateNodeId1,
			String operateNodeName1,
			
			double operateNodeExenum1,//mark
			double operateNodeDependExenum1,//依赖mark、
			
			String transactionDefId1,
			String transactionName1,
			Short  transactionStatus1,
			String transactionNodeId1,
			String transactionNodeName1,
			String tableParcelId1,
			String tableParcelName1,
			String tableParcelNodeId1,
			String tableParcelNodeName1){		
		id=Long.valueOf(id1);
		zj=Long.valueOf(zj1);	
		sysName=sysName1;	
		sysId=sysId1;
		sysNotSetNum=sysNotSetNum1;
		defName=defName1;	
		defId=defId1;	
		actDefId=actDefId1;	
		typeName=typeName1;	
		status=status1;	
		defNotSetNum=defNotSetNum1;
		
		defExenum=defExenum1;
		defDependExenum= defDependExenum1;
		defOperateExenum=defOperateExenum1;//mark
		defOperateDependExenum=defOperateDependExenum1;//依赖mark、
		
		
		nodeIdNum=nodeIdNum1;
		nodeName=nodeName1;
		nodeId=nodeId1;
		setJudge=setJudge1;
		setUp=setUp1;
		nodeWorkName=nodeWorkName1;
		
		nodeExenum=nodeExenum1;
		nodeDependExenum= nodeDependExenum1;
		nodeOperateExenum=nodeOperateExenum1;//mark
		nodeOperateDependExenum=nodeOperateDependExenum1;//依赖mark、
		
		operateDefId=operateDefId1;
		operateName=operateName1;
		operateStatus=operateStatus1;
		
		operateExenum=operateExenum1;//mark
		operateDependExenum=operateDependExenum1;//依赖mark、
		
		operateNodeId=operateNodeId1;
		operateNodeName=operateNodeName1;
		
		operateNodeExenum=operateNodeExenum1;//mark
		operateNodeDependExenum=operateNodeDependExenum1;//依赖mark、
		
		transactionDefId=transactionDefId1;
		transactionName=transactionName1;
		transactionStatus=transactionStatus1;
		transactionNodeId=transactionNodeId1;
		transactionNodeName=transactionNodeName1;
		tableParcelId=tableParcelId1;
		tableParcelName=tableParcelName1;
		tableParcelNodeId=tableParcelNodeId1;
		tableParcelNodeName=tableParcelNodeName1;
	}
	
}