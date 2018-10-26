package com.hotent.platform.webservice.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * 对应TaskOpinion
 * @author csx
 *
 */
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class BpmTaskOpinion {
		// opinionId
		protected Long opinionId;
		// actInstId
		protected String actInstId;
		// 任务名称
		protected String taskName;
		//任务Key
		protected String taskKey;
		//任务令牌
		protected String taskToken;
		// taskId
		protected Long taskId;
		// 执行开始时间
		protected java.util.Date startTime;
		// 结束时间
		protected java.util.Date endTime;
		// 持续时间
		protected Long durTime;
		// 执行人ID
		protected Long exeUserId;
		// 执行人名
		protected String exeFullname;
		// 审批意见
		protected String opinion;
		// 审批状态
		protected Short checkStatus;
		//表单定义Id
		protected Long formDefId=0L;
		//意见表单名称
		protected String fieldName;
	    //流程定义ID
		
		public BpmTaskOpinion() {
			
		}
		
		protected String actDefId;
		public Long getOpinionId() {
			return opinionId;
		}
		public void setOpinionId(Long opinionId) {
			this.opinionId = opinionId;
		}
		public String getActInstId() {
			return actInstId;
		}
		public void setActInstId(String actInstId) {
			this.actInstId = actInstId;
		}
		public String getTaskName() {
			return taskName;
		}
		public void setTaskName(String taskName) {
			this.taskName = taskName;
		}
		public String getTaskKey() {
			return taskKey;
		}
		public void setTaskKey(String taskKey) {
			this.taskKey = taskKey;
		}
		public String getTaskToken() {
			return taskToken;
		}
		public void setTaskToken(String taskToken) {
			this.taskToken = taskToken;
		}
		public Long getTaskId() {
			return taskId;
		}
		public void setTaskId(Long taskId) {
			this.taskId = taskId;
		}
		public java.util.Date getStartTime() {
			return startTime;
		}
		public void setStartTime(java.util.Date startTime) {
			this.startTime = startTime;
		}
		public java.util.Date getEndTime() {
			return endTime;
		}
		public void setEndTime(java.util.Date endTime) {
			this.endTime = endTime;
		}
		public Long getDurTime() {
			return durTime;
		}
		public void setDurTime(Long durTime) {
			this.durTime = durTime;
		}
		public Long getExeUserId() {
			return exeUserId;
		}
		public void setExeUserId(Long exeUserId) {
			this.exeUserId = exeUserId;
		}
		public String getExeFullname() {
			return exeFullname;
		}
		public void setExeFullname(String exeFullname) {
			this.exeFullname = exeFullname;
		}
		public String getOpinion() {
			return opinion;
		}
		public void setOpinion(String opinion) {
			this.opinion = opinion;
		}
		public Short getCheckStatus() {
			return checkStatus;
		}
		public void setCheckStatus(Short checkStatus) {
			this.checkStatus = checkStatus;
		}
		public Long getFormDefId() {
			return formDefId;
		}
		public void setFormDefId(Long formDefId) {
			this.formDefId = formDefId;
		}
		public String getFieldName() {
			return fieldName;
		}
		public void setFieldName(String fieldName) {
			this.fieldName = fieldName;
		}
		public String getActDefId() {
			return actDefId;
		}
		public void setActDefId(String actDefId) {
			this.actDefId = actDefId;
		}
		
		
}
