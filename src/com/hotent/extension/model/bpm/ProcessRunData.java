package com.hotent.extension.model.bpm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ProcessRunData {
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
		// 状态
		protected Short status;
		// ACT流程实例ID
		protected String actInstId;
		// ACT流程定义ID
		protected String actDefId;
		// businessKey
		protected String businessKey;
		// businessUrl
		protected String businessUrl;
		// 结束时间
		protected Date endTime;
		// 执行持续时间总长（毫秒)
		protected Long duration;
		// 流程定义名称
		protected String processName;
		// 父流程运行的ID。
		protected Long parentId = 0L;
		/**
		 * 流程实例对应的业务数据
		 */
		private Map<String,Object> data=new HashMap<String, Object>();
		
		public Long getRunId() {
			return runId;
		}
		public void setRunId(Long runId) {
			this.runId = runId;
		}
		public Long getDefId() {
			return defId;
		}
		public void setDefId(Long defId) {
			this.defId = defId;
		}
		public String getSubject() {
			return subject;
		}
		public void setSubject(String subject) {
			this.subject = subject;
		}
		public Long getCreatorId() {
			return creatorId;
		}
		public void setCreatorId(Long creatorId) {
			this.creatorId = creatorId;
		}
		public String getCreator() {
			return creator;
		}
		public void setCreator(String creator) {
			this.creator = creator;
		}
		public java.util.Date getCreatetime() {
			return createtime;
		}
		public void setCreatetime(java.util.Date createtime) {
			this.createtime = createtime;
		}
		public Short getStatus() {
			return status;
		}
		public void setStatus(Short status) {
			this.status = status;
		}
		public String getActInstId() {
			return actInstId;
		}
		public void setActInstId(String actInstId) {
			this.actInstId = actInstId;
		}
		public String getActDefId() {
			return actDefId;
		}
		public void setActDefId(String actDefId) {
			this.actDefId = actDefId;
		}
		public String getBusinessKey() {
			return businessKey;
		}
		public void setBusinessKey(String businessKey) {
			this.businessKey = businessKey;
		}
		public String getBusinessUrl() {
			return businessUrl;
		}
		public void setBusinessUrl(String businessUrl) {
			this.businessUrl = businessUrl;
		}
		public Date getEndTime() {
			return endTime;
		}
		public void setEndTime(Date endTime) {
			this.endTime = endTime;
		}
		public Long getDuration() {
			return duration;
		}
		public void setDuration(Long duration) {
			this.duration = duration;
		}
		public String getProcessName() {
			return processName;
		}
		public void setProcessName(String processName) {
			this.processName = processName;
		}
		public Long getParentId() {
			return parentId;
		}
		public void setParentId(Long parentId) {
			this.parentId = parentId;
		}
		public Map<String, Object> getData() {
			return data;
		}
		public void setData(Map<String, Object> data) {
			this.data = data;
		}
		
		
}
