package com.hotent.platform.model.mobile;

import java.util.List;
import java.util.Map;

import com.hotent.platform.model.bpm.BpmNodeButton;
import com.hotent.platform.model.bpm.BpmNodeSign;
import com.hotent.platform.model.bpm.TaskSignData;
import com.hotent.platform.model.system.SysFile;
import com.hotent.platform.model.system.SysUser;
/**
 * 流程的数据
 * @author sfhq284
 *
 */
public class MoblieFlowData {
	/**
	 * 当前用户
	 */
	private SysUser curUser;
	/**
	 * 流程表单
	 */
	private MobileFormData form;
	/**
	 * 流程按钮
	 */
	private Map<String, List<BpmNodeButton>> mapButton ;
	/**
	 * 流程图
	 */
	private MoblieImage moblieImage;
	/**
	 * 任务Id
	 */
	private String taskId;
	/**
	 *  是否沟通
	 */
	private Boolean isTaskNotify = false;

	/**
	 * 是否会签
	 */
	private Boolean isSignTask = false;
	
	/**
	 * 是否能驳回
	 */
	private Boolean isCanBack = false;
	/**
	 * 是否能转办
	 */
	private Boolean isCanAssignee = false;
	
	/**
	 * 流程附件
	 */
	private SysFile sysFile;
	
	/**
	 * 会签任务数据
	 */
	private List<TaskSignData> signDataList;
	/**
	 * 会签规则
	 */
	private BpmNodeSign bpmNodeSign;
	/**
	 * "允许直接处理"特权
	 */
	private Boolean isAllowDirectExecute = false;
	/**
	 * "允许补签"特权
	 */
	private Boolean isAllowRetoactive = false;
	/**
	 * "一票决断"特权
	 */
	private Boolean isAllowOneVote = false;
	
	/**
	 * @return the curUser
	 */
	public SysUser getCurUser() {
		return curUser;
	}
	/**
	 * @param curUser the curUser to set
	 */
	public void setCurUser(SysUser curUser) {
		this.curUser = curUser;
	}
	/**
	 * @return the form
	 */
	public MobileFormData getForm() {
		return form;
	}
	/**
	 * @param form the form to set
	 */
	public void setForm(MobileFormData form) {
		this.form = form;
	}
	/**
	 * @return the mapButton
	 */
	public Map<String, List<BpmNodeButton>> getMapButton() {
		return mapButton;
	}
	/**
	 * @param mapButton the mapButton to set
	 */
	public void setMapButton(Map<String, List<BpmNodeButton>> mapButton) {
		this.mapButton = mapButton;
	}
	/**
	 * @return the moblieImage
	 */
	public MoblieImage getMoblieImage() {
		return moblieImage;
	}
	/**
	 * @param moblieImage the moblieImage to set
	 */
	public void setMoblieImage(MoblieImage moblieImage) {
		this.moblieImage = moblieImage;
	}
	/**
	 * @return the taskId
	 */
	public String getTaskId() {
		return taskId;
	}
	/**
	 * @param taskId the taskId to set
	 */
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	/**
	 * @return the isTaskNotify
	 */
	public Boolean getIsTaskNotify() {
		return isTaskNotify;
	}
	/**
	 * @param isTaskNotify the isTaskNotify to set
	 */
	public void setIsTaskNotify(Boolean isTaskNotify) {
		this.isTaskNotify = isTaskNotify;
	}
	/**
	 * @return the isSignTask
	 */
	public Boolean getIsSignTask() {
		return isSignTask;
	}
	/**
	 * @param isSignTask the isSignTask to set
	 */
	public void setIsSignTask(Boolean isSignTask) {
		this.isSignTask = isSignTask;
	}
	/**
	 * @return the isCanBack
	 */
	public Boolean getIsCanBack() {
		return isCanBack;
	}
	/**
	 * @param isCanBack the isCanBack to set
	 */
	public void setIsCanBack(Boolean isCanBack) {
		this.isCanBack = isCanBack;
	}
	/**
	 * @return the isCanAssignee
	 */
	public Boolean getIsCanAssignee() {
		return isCanAssignee;
	}
	/**
	 * @param isCanAssignee the isCanAssignee to set
	 */
	public void setIsCanAssignee(Boolean isCanAssignee) {
		this.isCanAssignee = isCanAssignee;
	}
	/**
	 * @return the sysFile
	 */
	public SysFile getSysFile() {
		return sysFile;
	}
	/**
	 * @param sysFile the sysFile to set
	 */
	public void setSysFile(SysFile sysFile) {
		this.sysFile = sysFile;
	}
	/**
	 * @return the signDataList
	 */
	public List<TaskSignData> getSignDataList() {
		return signDataList;
	}
	/**
	 * @param signDataList the signDataList to set
	 */
	public void setSignDataList(List<TaskSignData> signDataList) {
		this.signDataList = signDataList;
	}
	/**
	 * @return the bpmNodeSign
	 */
	public BpmNodeSign getBpmNodeSign() {
		return bpmNodeSign;
	}
	/**
	 * @param bpmNodeSign the bpmNodeSign to set
	 */
	public void setBpmNodeSign(BpmNodeSign bpmNodeSign) {
		this.bpmNodeSign = bpmNodeSign;
	}
	/**
	 * @return the isAllowDirectExecute
	 */
	public Boolean getIsAllowDirectExecute() {
		return isAllowDirectExecute;
	}
	/**
	 * @param isAllowDirectExecute the isAllowDirectExecute to set
	 */
	public void setIsAllowDirectExecute(Boolean isAllowDirectExecute) {
		this.isAllowDirectExecute = isAllowDirectExecute;
	}
	/**
	 * @return the isAllowRetoactive
	 */
	public Boolean getIsAllowRetoactive() {
		return isAllowRetoactive;
	}
	/**
	 * @param isAllowRetoactive the isAllowRetoactive to set
	 */
	public void setIsAllowRetoactive(Boolean isAllowRetoactive) {
		this.isAllowRetoactive = isAllowRetoactive;
	}
	/**
	 * @return the isAllowOneVote
	 */
	public Boolean getIsAllowOneVote() {
		return isAllowOneVote;
	}
	/**
	 * @param isAllowOneVote the isAllowOneVote to set
	 */
	public void setIsAllowOneVote(Boolean isAllowOneVote) {
		this.isAllowOneVote = isAllowOneVote;
	}

}
