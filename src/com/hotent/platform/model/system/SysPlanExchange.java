package com.hotent.platform.model.system;

import java.util.Date;

import com.hotent.core.model.BaseModel;
/**
 * 对象功能:订阅表 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2013-12-19 11:26:03
 */
public class SysPlanExchange extends BaseModel
{
	// ID
	protected Long  id;
	//日程ID
	protected Long  planId;
	//提交人ID
	protected Long  submitId;
	//提交人
	protected String  submitor;
	//相关文档
	protected String  doc;
	//交流内容
	protected String  content;
	//提交时间
	protected Date  createTime;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPlanId() {
		return planId;
	}
	public void setPlanId(Long planId) {
		this.planId = planId;
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
	public String getDoc() {
		return doc;
	}
	public void setDoc(String doc) {
		this.doc = doc;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}