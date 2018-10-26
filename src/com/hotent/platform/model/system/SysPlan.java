package com.hotent.platform.model.system;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.hotent.core.model.BaseModel;
import com.hotent.core.util.DateUtil;
/**
 * 对象功能:自定义别名脚本表 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2013-12-19 11:26:03
 */
public class SysPlan extends BaseModel
{
	// ID
	protected Long  id;
	//任务名称
	protected String  taskName;
	//提交人ID
	protected Long  submitId;
	// 提交人
	protected String  submitor;
	//负责人ID
	protected Long  chargeId;
	// 负责人
	protected String  charge;
	//开始时间
	protected Date  startTime;
	//结束时间
	protected Date  endTime;
	// 项目名称
	protected String  projectName;
	// 相关文档
	protected String  doc;
	// 相关客户ID
	protected Long  customerId;
	// 相关客户
	protected String  customer;
	// 相关工单ID
	protected Long  runId;
	// 相关工单名称
	protected String  runName;
	// 完成进度
	protected Long  rate = 0L;
	// 创建时间
	protected Date  createTime;
	// 任务内容
	protected String  description;
	//被订阅的ID(只用于存放数据)
	protected Long subscribeId;
	// 两个日期的时间差，不做持久化
	protected Integer daysBetween; 
	//开始时间String，不做持久化
	protected String startTimeStr;
	//结束时间String，不做持久化
	protected String endTimeStr;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public Long getSubmitId() {
		return submitId;
	}
	public void setSubmitId(Long submitId) {
		this.submitId = submitId;
	}
	public String getSubmitor() {
		return submitor;
	}
	public void setSubmitor(String submitor) {
		this.submitor = submitor;
	}
	public Long getChargeId() {
		return chargeId;
	}
	public void setChargeId(Long chargeId) {
		this.chargeId = chargeId;
	}
	public String getCharge() {
		return charge;
	}
	public void setCharge(String charge) {
		this.charge = charge;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getDoc() {
		return doc;
	}
	public void setDoc(String doc) {
		this.doc = doc;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public Long getRunId() {
		return runId;
	}
	public void setRunId(Long runId) {
		this.runId = runId;
	}
	public String getRunName() {
		return runName;
	}
	public void setRunName(String runName) {
		this.runName = runName;
	}
	public Long getRate() {
		return rate;
	}
	public void setRate(Long rate) {
		this.rate = rate;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getSubscribeId() {
		return subscribeId;
	}
	public void setSubscribeId(Long subscribeId) {
		this.subscribeId = subscribeId;
	}
	
	/**
	 * 求结束时间跟现在时间差，如果小于0，则返回0
	 * @return
	 */
	public Integer getDaysBetween() {
		Date startTime = new Date();
		Date endTime = this.getEndTime();
		if(endTime == null)
			return 0;
		Integer daysBetween = DateUtil.daysBetween(startTime,endTime);
		if(daysBetween < 0) 
			return 0;
		return daysBetween;
	}
	public void setDaysBetween(Integer daysBetween) {
		this.daysBetween = daysBetween;
	}
	
	/**
	 * string型开始时间
	 * @return
	 */
	public String getStartTimeStr() {
		Date startTime = this.getStartTime();
		if(startTime == null)
			return "";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		return formatter.format(startTime);
	}
	public void setStartTimeStr(String startTimeStr) {
		this.startTimeStr = startTimeStr;
	}
	/**
	 * string型结束时间
	 * @return
	 */
	public String getEndTimeStr() {
		Date endTime = this.getEndTime();
		if(endTime == null)
			return "";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		return formatter.format(endTime);
	}
	public void setEndTimeStr(String endTimeStr) {
		this.endTimeStr = endTimeStr;
	}
	
	

}