package com.hotent.platform.model.bpm;

/**
* <pre>
* 对象功能:流转人员状态对象
* 开发人员:helh
* 创建时间:2014-04-04 17:22:56
* </pre>
*/
public class BpmProTransToAssignee {
	/**
	 * 正在审批
	 */
	public static Integer STATUS_CHECKING = 1;
	/**
	 * 已审批
	 */
	public static Integer STATUS_CHECKED = 2;
	
	Long    userId;
	String  userName;
	//流转任务状态  1，正在审批  2，已审批
	Integer status;
	//初始流转人任务ID
	String  parentTaskId;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getParentTaskId() {
		return parentTaskId;
	}
	public void setParentTaskId(String parentTaskId) {
		this.parentTaskId = parentTaskId;
	}
	
}
