package com.hotent.platform.model.bpm;

import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import com.alibaba.fastjson.JSONArray;
import com.hotent.core.model.BaseModel;
import com.hotent.core.util.StringUtil;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:任务节点催办时间设置 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-02-17 13:56:53
 */
@XmlRootElement(name = "taskReminder")
@XmlAccessorType(XmlAccessType.NONE)
public class TaskReminder extends BaseModel
{
	// 时间设置ID
	@XmlAttribute
	protected Long taskDueId;
	// 流程定义ID
	@XmlAttribute
	protected String actDefId;
	// 流程节点ID
	@XmlAttribute
	protected String nodeId;
	// 催办开始时间(相对于任务创建时间，多少工作日)
	@XmlAttribute
	protected Integer reminderStart;
	// 催办持续时间(相对于催办开始时间)
	@XmlAttribute
	protected Integer reminderEnd;
	// 催办次数
	@XmlAttribute
	protected Integer times;
	// 催办提醒短信内容
	@XmlAttribute
	protected String mailContent;
	// 催办邮件模板内容
	@XmlAttribute
	protected String msgContent;
	// 催办邮件模板内容
	@XmlAttribute
	protected String smsContent;
	// 任务到期处理动作
	@XmlAttribute
	protected Integer action;
	// script
	@XmlAttribute
	protected String script;
	// completeTime
	@XmlAttribute
	protected Integer completeTime;
	// 条件表达式
	@XmlAttribute
	protected String condExp;
	// 名称
	@XmlAttribute
	protected String name;
	//相对节点的节点ID
	@XmlAttribute
	protected String relativeNodeId;
	//相对节点的动作
	@XmlAttribute
	protected Integer relativeNodeType;
	//相对时间类型
	@XmlAttribute
	protected Integer relativeTimeType;
	//指定的交办人ID
	@XmlAttribute
	protected Long assignerId;
	
	//指定的交办人名称
	@XmlAttribute
	protected String assignerName;
	//预警设置json
	protected String warningSetJson;
	//预警项列表
	protected List<TaskWarningSet> taskWarningSetList;
	

	public String getWarningSetJson() {
		return warningSetJson;
	}
	public void setWarningSetJson(String warningSetJson) {
		this.warningSetJson = warningSetJson;
	}
	public List<TaskWarningSet> getTaskWarningSetList() {
		if(taskWarningSetList!=null) return taskWarningSetList;
		if(StringUtil.isEmpty(warningSetJson)) return Collections.emptyList();
		
		return JSONArray.parseArray(warningSetJson, TaskWarningSet.class);
	}
	public void setTaskDueId(Long taskDueId) 
	{
		this.taskDueId = taskDueId;
	}
	/**
	 * 返回 时间设置ID
	 * @return
	 */
	public Long getTaskDueId() 
	{
		return taskDueId;
	}

	public void setActDefId(String actDefId) 
	{
		this.actDefId = actDefId;
	}
	/**
	 * 返回 流程定义ID
	 * @return
	 */
	public String getActDefId() 
	{
		return actDefId;
	}

	public void setNodeId(String nodeId) 
	{
		this.nodeId = nodeId;
	}
	/**
	 * 返回 流程节点ID
	 * @return
	 */
	public String getNodeId() 
	{
		return nodeId;
	}

	public void setReminderStart(Integer reminderStart) 
	{
		this.reminderStart = reminderStart;
	}
	/**
	 * 返回 催办开始时间(相对于任务创建时间，多少工作日)
	 * @return
	 */
	public Integer getReminderStart() 
	{
		return reminderStart;
	}

	public void setReminderEnd(Integer reminderEnd) 
	{
		this.reminderEnd = reminderEnd;
	}
	/**
	 * 返回 催办结束时间(相对于催办开始时间)
	 * @return
	 */
	public Integer getReminderEnd() 
	{
		return reminderEnd;
	}

	public void setTimes(Integer times) 
	{
		this.times = times;
	}
	/**
	 * 返回 催办次数
	 * @return
	 */
	public Integer getTimes() 
	{
		return times;
	}

	public void setMailContent(String mailContent) 
	{
		this.mailContent = mailContent;
	}
	/**
	 * 返回 催办提醒短信内容
	 * @return
	 */
	public String getMailContent() 
	{
		return mailContent;
	}

	public void setMsgContent(String msgContent) 
	{
		this.msgContent = msgContent;
	}
	/**
	 * 返回 催办邮件模板内容
	 * @return
	 */
	public String getMsgContent() 
	{
		return msgContent;
	}

	public void setSmsContent(String smsContent) 
	{
		this.smsContent = smsContent;
	}
	/**
	 * 返回 催办邮件模板内容
	 * @return
	 */
	public String getSmsContent() 
	{
		return smsContent;
	}

	public void setAction(Integer action) 
	{
		this.action = action;
	}
	/**
	 * 返回 任务到期处理动作
	 * @return
	 */
	public Integer getAction() 
	{
		return action;
	}

	public void setScript(String script) 
	{
		this.script = script;
	}
	/**
	 * 返回 script
	 * @return
	 */
	public String getScript() 
	{
		return script;
	}

	public void setCompleteTime(Integer completeTime) 
	{
		this.completeTime = completeTime;
	}
	/**
	 * 返回 completeTime
	 * @return
	 */
	public Integer getCompleteTime() 
	{
		return completeTime;
	}

   
   	public String getCondExp() {
		return condExp;
	}
	public void setCondExp(String condExp) {
		this.condExp = condExp;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRelativeNodeId() {
		return relativeNodeId;
	}
	public void setRelativeNodeId(String relativeNodeId) {
		this.relativeNodeId = relativeNodeId;
	}
	public Integer getRelativeNodeType() {
		return relativeNodeType;
	}
	public void setRelativeNodeType(Integer relativeNodeType) {
		this.relativeNodeType = relativeNodeType;
	}
	public Integer getRelativeTimeType() {
		return relativeTimeType;
	}
	public void setRelativeTimeType(Integer relativeTimeType) {
		this.relativeTimeType = relativeTimeType;
	}
	public Long getAssignerId() {
		return assignerId;
	}
	public void setAssignerId(Long assignerId) {
		this.assignerId = assignerId;
	}
	public String getAssignerName() {
		return assignerName;
	}
	public void setAssignerName(String assignerName) {
		this.assignerName = assignerName;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof TaskReminder)) 
		{
			return false;
		}
		TaskReminder rhs = (TaskReminder) object;
		return new EqualsBuilder()
		.append(this.taskDueId, rhs.taskDueId)
		.append(this.actDefId, rhs.actDefId)
		.append(this.nodeId, rhs.nodeId)
		.append(this.reminderStart, rhs.reminderStart)
		.append(this.reminderEnd, rhs.reminderEnd)
		.append(this.times, rhs.times)
		.append(this.mailContent, rhs.mailContent)
		.append(this.msgContent, rhs.msgContent)
		.append(this.smsContent, rhs.smsContent)
		.append(this.action, rhs.action)
		.append(this.script, rhs.script)
		.append(this.completeTime, rhs.completeTime)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.taskDueId) 
		.append(this.actDefId) 
		.append(this.nodeId) 
		.append(this.reminderStart) 
		.append(this.reminderEnd) 
		.append(this.times) 
		.append(this.mailContent) 
		.append(this.msgContent) 
		.append(this.smsContent) 
		.append(this.action) 
		.append(this.script) 
		.append(this.completeTime) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("taskDueId", this.taskDueId) 
		.append("actDefId", this.actDefId) 
		.append("nodeId", this.nodeId) 
		.append("reminderStart", this.reminderStart) 
		.append("reminderEnd", this.reminderEnd) 
		.append("times", this.times) 
		.append("mailContent", this.mailContent) 
		.append("msgContent", this.msgContent) 
		.append("smsContent", this.smsContent) 
		.append("action", this.action) 
		.append("script", this.script) 
		.append("completeTime", this.completeTime) 
		.toString();
	}
   
  public class TimeDay{
	  int day;
	  int hour;
	  int minute;
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public int getMinute() {
		return minute;
	}
	public void setMinute(int minute) {
		this.minute = minute;
	}
  }
  
  public TimeDay getReminderStartTimeDay(){
	  TimeDay timeDay=new TimeDay();
	  int reminderStartDay=reminderStart/(60*24);
	  int reminderStartHour=(reminderStart-reminderStartDay*(60*24))/60;
	  int reminderStartMinute=reminderStart-reminderStartDay*(60*24)-reminderStartHour*60;
	  timeDay.setDay(reminderStartDay);
	  timeDay.setHour(reminderStartHour);
	  timeDay.setMinute(reminderStartMinute);
	  return timeDay;
  }
  
  
  public TimeDay getCompleteTimeTimeDay(){
	  TimeDay timeDay=new TimeDay();
	  int completeTimeDay=completeTime/(60*24);
	  int completeTimeHour=(completeTime-completeTimeDay*(60*24))/60;
      int completeTimeMinute=completeTime-completeTimeDay*(60*24)-completeTimeHour*60;
	  timeDay.setDay(completeTimeDay);
	  timeDay.setHour(completeTimeHour);
	  timeDay.setMinute(completeTimeMinute);
	  return timeDay;
  }
  
  public TimeDay getReminderEndTimeDay(){
	  TimeDay timeDay=new TimeDay();
	  int reminderEndDay=reminderEnd/(60*24);
	  int reminderEndHour=(reminderEnd-reminderEndDay*(60*24))/60;
	  int reminderEndMinute=reminderEnd-reminderEndDay*(60*24)-reminderEndHour*60;
	  timeDay.setDay(reminderEndDay);
	  timeDay.setHour(reminderEndHour);
	  timeDay.setMinute(reminderEndMinute);
	  return timeDay;
  }

}