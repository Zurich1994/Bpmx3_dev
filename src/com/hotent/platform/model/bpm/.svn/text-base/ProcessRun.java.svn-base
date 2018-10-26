package com.hotent.platform.model.bpm;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;

/**
 * 对象功能:流程实例扩展Model对象 
 * 开发公司:广州宏天软件有限公司 
 * 开发人员:csx 
 * 创建时间:2011-12-03 09:33:06
 */
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
@XmlRootElement
public class ProcessRun extends BaseModel  implements Cloneable {
	/** 挂起状态 */
	public static final Short STATUS_SUSPEND = 0;
	/** 运行状态 */
	public static final Short STATUS_RUNNING = 1;
	/** 结束状态 */
	public static final Short STATUS_FINISH = 2;
	/** 人工终止 */
	public static final Short STATUS_MANUAL_FINISH = 3;
	/** 草稿状态 */
	public static final Short STATUS_FORM=4;
	/** 已撤销 */
	public static final Short STATUS_RECOVER = 5;
	/** 已驳回 */
	public static final Short STATUS_REJECT = 6;
	/** 已追回 */
	public static final Short STATUS_REDO = 7;
	/** 逻辑删除 */
	public static final Short STATUS_DELETE = 10;
	
	
	/**试运行**/
	public static final Short TEST_RUNNING=0;
	/**正常运行**/
	public static final Short FORMAL_RUNNING=1;
	/**不追回*/
	public static final Short RECOVER_NO = 0;
	/**追回*/
	public static final Short RECOVER = 1;
	

	// runId
	protected Long runId;
	// 流程定义ID
	protected Long defId;
	// 流程实例标题
	protected String subject;
	// 创建人ID
	protected Long creatorId;
	// 创建人
	protected String creator;
	// 创建时间
	protected java.util.Date createtime;
	// 业务表单简述
	protected String busDescp;
	// 状态
	protected Short status;
	// ACT流程实例ID
	protected String actInstId;
	// ACT流程定义ID
	protected String actDefId;
	// businessKey
	protected String businessKey;
	//主键为数字。
	protected Long businessKeyNum=0L;
	// businessUrl
	protected String businessUrl;

	// 结束时间
	protected Date endTime;
	// 执行持续时间总长（毫秒)
	protected Long duration;

	// 流程定义名称
	protected String processName;
	// 主键名称
	protected String pkName = "";
	// 表名
	protected String tableName = "";
	// 父流程运行的ID。
	protected Long parentId = 0L;
	// 发起人所在组织名称
	protected String startOrgName = "";
	// 发起人所在组织Id。
	protected Long startOrgId = 0L;
	// 追回状态
	protected Short recover = RECOVER_NO;
	//表单定义
	protected Long formDefId=0L;
	//数据源
	protected String dsAlias;

	// 表单Key或Url
	protected String formKeyUrl;
	// 表单类型
	protected Short formType;
	/**
	 * 权限等级。
	 * 1.  标题2.  显示流程实例信息3.  可以干预
	 */
	private Short grade=1;

	// 基于最后一次提交，执行持续时间总长（毫秒)
	protected Long lastSubmitDuration;
	
	private Long typeId;
	
	private String typeName;
	
	
	private String tagids;
	
	//组织名称
	protected String orgName;
	
	/**
	 * 是否允许回退到发起人，不对应数据库字段。
	 */
	protected boolean allowBackToStart=false;
	
	//是否允许我的办结转发
	protected Integer allowFinishedDivert=1;
	
	protected Long copyId;
	
	private String flowKey="";
	
	private String startNode = "";
	
	/**
	 * 是否正式流程实例
	 * 1：正式
	 * 0：测试数据
	 */
	protected Short isFormal=1;
	
	//流程实例归档后是否允许打印表单
	protected	Short	isPrintForm=0;
	
	/**
	 * 关联ID。
	 */
	protected	Long	relRunId=0L;
	
	
	// 对应流程分管授权的实例权限分配
	protected AuthorizeRight authorizeRight;
	
	// 全局流水号
	private String globalFlowNo;
	
	
	public Long getCopyId() {
		return copyId;
	}

	public void setCopyId(Long copyId) {
		this.copyId = copyId;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public void setRunId(Long runId) {
		this.runId = runId;
	}

	
	/**
	 * 返回 runId
	 * 
	 * @return
	 */
	public Long getRunId() {
		return runId;
	}

	public void setDefId(Long defId) {
		this.defId = defId;
	}
	
	public Long getFormDefId() {
		return formDefId;
	}

	public void setFormDefId(Long formDefId) {
		this.formDefId = formDefId;
	}

	/**
	 * 返回 流程定义ID
	 * 
	 * @return
	 */
	public Long getDefId() {
		return defId;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * 返回 流程实例标题
	 * 
	 * @return
	 */
	public String getSubject() {
		return subject;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	/**
	 * 返回 创建人ID
	 * 
	 * @return
	 */
	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}
	
	public Short getGrade() {
		return grade;
	}

	public void setGrade(Short grade) {
		this.grade = grade;
	}

	/**
	 * 返回 创建人
	 * 
	 * @return
	 */
	public String getCreator() {
		return creator;
	}

	public void setCreatetime(java.util.Date createtime) {
		this.createtime = createtime;
	}
	
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * 返回 创建时间
	 * 
	 * @return
	 */
	public java.util.Date getCreatetime() {
		return createtime;
	}

	public void setBusDescp(String busDescp) {
		this.busDescp = busDescp;
	}

	/**
	 * 返回 业务表单简述
	 * 
	 * @return
	 */
	public String getBusDescp() {
		return busDescp;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	/**
	 * 返回 状态
	 * 
	 * @return
	 */
	public Short getStatus() {
		return status;
	}

	public void setActInstId(String actInstId) {
		this.actInstId = actInstId;
	}

	/**
	 * 返回 ACT流程实例ID
	 * 
	 * @return
	 */
	public String getActInstId() {
		return actInstId;
	}

	public void setActDefId(String actDefId) {
		this.actDefId = actDefId;
	}

	/**
	 * 返回 ACT流程定义ID
	 * 
	 * @return
	 */
	public String getActDefId() {
		return actDefId;
	}

	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}

	/**
	 * 返回 businessKey
	 * 
	 * @return
	 */
	public String getBusinessKey() {
		return businessKey;
	}
	
	public Long getBusinessKeyNum() {
		return businessKeyNum;
	}

	public void setBusinessKeyNum(Long businessKeyNum) {
		this.businessKeyNum = businessKeyNum;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getBusinessUrl() {
		return businessUrl;
	}

	public void setBusinessUrl(String businessUrl) {
		this.businessUrl = businessUrl;
	}

	public String getPkName() {
		return pkName;
	}

	public void setPkName(String pkName) {
		this.pkName = pkName;
	}
	
	public Short getIsFormal() {
		return isFormal;
	}

	public void setIsFormal(Short isFormal) {
		this.isFormal = isFormal;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof ProcessRun)) {
			return false;
		}
		ProcessRun rhs = (ProcessRun) object;
		return new EqualsBuilder().append(this.runId, rhs.runId)
				.append(this.defId, rhs.defId)
				.append(this.subject, rhs.subject)
				.append(this.creatorId, rhs.creatorId)
				.append(this.creator, rhs.creator)
				.append(this.createtime, rhs.createtime)
				.append(this.busDescp, rhs.busDescp)
				.append(this.status, rhs.status)
				.append(this.actInstId, rhs.actInstId)
				.append(this.actDefId, rhs.actDefId)
				.append(this.businessKey, rhs.businessKey)
				.append(this.businessUrl, rhs.businessUrl).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.runId)
				.append(this.defId).append(this.subject).append(this.creatorId)
				.append(this.creator).append(this.createtime)
				.append(this.busDescp).append(this.status)
				.append(this.actInstId).append(this.actDefId)
				.append(this.businessKey).append(this.businessUrl).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("runId", this.runId)
				.append("defId", this.defId).append("subject", this.subject)
				.append("creatorId", this.creatorId)
				.append("creator", this.creator)
				.append("createtime", this.createtime)
				.append("busDescp", this.busDescp)
				.append("status", this.status)
				.append("actInstId", this.actInstId)
				.append("actDefId", this.actDefId)
				.append("businessKey", this.businessKey)
				.append("businessUrl", this.businessUrl).toString();
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getStartOrgName() {
		return startOrgName;
	}

	public void setStartOrgName(String startOrgName) {
		this.startOrgName = startOrgName;
	}

	public Long getStartOrgId() {
		return startOrgId;
	}

	public void setStartOrgId(Long startOrgId) {
		this.startOrgId = startOrgId;
	}

	public Short getRecover() {
		return recover;
	}

	public void setRecover(Short recover) {
		this.recover = recover;
	}

	public String getDsAlias() {
		return dsAlias;
	}

	public void setDsAlias(String dsAlias) {
		this.dsAlias = dsAlias;
	}

	public static Short getStatusSuspend() {
		return STATUS_SUSPEND;
	}

	public static Short getStatusRunning() {
		return STATUS_RUNNING;
	}

	public static Short getStatusFinish() {
		return STATUS_FINISH;
	}

	public static Short getStatusManualFinish() {
		return STATUS_MANUAL_FINISH;
	}

	public static Short getStatusForm() {
		return STATUS_FORM;
	}

	public static Short getRecoverNo() {
		return RECOVER_NO;
	}

	public Object clone() {
		ProcessRun obj = null;
		try {
			obj = (ProcessRun) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return obj;
	}

	public String getFormKeyUrl() {
		return formKeyUrl;
	}

	public void setFormKeyUrl(String formKeyUrl) {
		this.formKeyUrl = formKeyUrl;
	}

	public Short getFormType() {
		return formType;
	}

	public void setFormType(Short formType) {
		this.formType = formType;
	}


	public Long getLastSubmitDuration() {
		return lastSubmitDuration;
	}

	public void setLastSubmitDuration(Long lastSubmitDuration) {
		this.lastSubmitDuration = lastSubmitDuration;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}


	public String getTagids() {
		return tagids;
	}

	public void setTagids(String tagids) {
		this.tagids = tagids;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public boolean isAllowBackToStart() {
		return allowBackToStart;
	}

	public void setAllowBackToStart(boolean allowBackToStart) {
		this.allowBackToStart = allowBackToStart;
	}

	public Integer getAllowFinishedDivert() {
		return allowFinishedDivert;
	}

	public void setAllowFinishedDivert(Integer allowFinishedDivert) {
		this.allowFinishedDivert = allowFinishedDivert;
	}


	public String getFlowKey() {
		return flowKey;
	}

	public void setFlowKey(String flowKey) {
		this.flowKey = flowKey;
	}

	public Short getIsPrintForm() {
		return isPrintForm;
	}

	public void setIsPrintForm(Short isPrintForm) {
		this.isPrintForm = isPrintForm;
	}

	public String getStartNode() {
		return startNode;
	}

	public void setStartNode(String startNode) {
		this.startNode = startNode;
	}
	

	public Long getRelRunId() {
		if(relRunId==null) return 0L;
		return relRunId;
	}

	public void setRelRunId(Long relRunId) {
		this.relRunId = relRunId;
	}

	public AuthorizeRight getAuthorizeRight()
	{
		return authorizeRight;
	}

	public void setAuthorizeRight(AuthorizeRight authorizeRight)
	{
		this.authorizeRight = authorizeRight;
	}

	public String getGlobalFlowNo() {
		return globalFlowNo;
	}

	public void setGlobalFlowNo(String globalFlowNo) {
		this.globalFlowNo = globalFlowNo;
	}
	
	
}