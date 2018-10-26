package com.hotent.platform.model.bpm;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;
/**
 * 对象功能:流程抄送转发 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:heyifan
 * 创建时间:2013-03-27 15:07:58
 */
public class BpmProCopyto extends BaseModel
{
	/**表示已读 =1*/
	public final static Short READ = 1;
	/**表示未读=0*/
	public final static Short NOT_READ = 0;
	
	public static Long CPTYPE_COPY = 1L;
	public static Long CPTYPE_SEND = 2L;
	// 抄送主键ID
	protected Long  copyId;
	// ACT流程实例ID
	protected Long  actInstId;
	// PRO流程实例ID
	protected Long  runId;
	// 发送人ID
	protected Long  sendUid;
	// 发送人名
	protected String  sendUname;
	// 抄送人ID
	protected Long  ccUid;
	// 抄送人名
	protected String  ccUname;
	// 抄送时间
	protected java.util.Date  ccTime;
	// 是否已阅 0=未读 1=已读
	protected Long  isReaded;
	// 填写意见
	protected String  fillOpinion;
	// 流程标题
	protected String  subject;
	// 阅读时间
	protected java.util.Date  readTime;
	// 1=抄送   在任务处理时，可以指定抄送人员进行任务抄送转发2=转发  在流程归档后，进行转发处理 
	protected Long  cpType;
	//发起人ID
	protected Long createId;
	//发起人名
	protected String creator;
	//流程定义类型ID
	protected Long defTypeId;
	
	//组织名
	protected String orgName="";
	//岗位名
	protected String posName="";
	
	protected Date createtime ;
	
	public void setCopyId(Long copyId) 
	{
		this.copyId = copyId;
	}
	/**
	 * 返回 抄送主键ID
	 * @return
	 */
	public Long getCopyId() 
	{
		return this.copyId;
	}
	public void setActInstId(Long actInstId) 
	{
		this.actInstId = actInstId;
	}
	/**
	 * 返回 ACT流程实例ID
	 * @return
	 */
	public Long getActInstId() 
	{
		return this.actInstId;
	}
	public void setRunId(Long runId) 
	{
		this.runId = runId;
	}
	/**
	 * 返回 PRO流程实例ID
	 * @return
	 */
	public Long getRunId() 
	{
		return this.runId;
	}
	public void setSendUid(Long sendUid) 
	{
		this.sendUid = sendUid;
	}
	/**
	 * 返回 发送人ID
	 * @return
	 */
	public Long getSendUid() 
	{
		return this.sendUid;
	}
	public void setSendUname(String sendUname) 
	{
		this.sendUname = sendUname;
	}
	/**
	 * 返回 发送人名
	 * @return
	 */
	public String getSendUname() 
	{
		return this.sendUname;
	}
	public void setCcUid(Long ccUid) 
	{
		this.ccUid = ccUid;
	}
	/**
	 * 返回 抄送人名ID
	 * @return
	 */
	public Long getCcUid() 
	{
		return this.ccUid;
	}
	public void setCcUname(String ccUname) 
	{
		this.ccUname = ccUname;
	}
	/**
	 * 返回 抄送人名
	 * @return
	 */
	public String getCcUname() 
	{
		return this.ccUname;
	}
	public void setCcTime(java.util.Date ccTime) 
	{
		this.ccTime = ccTime;
	}
	/**
	 * 返回 抄送时间
	 * @return
	 */
	public java.util.Date getCcTime() 
	{
		return this.ccTime;
	}
	public void setIsReaded(Long isReaded) 
	{
		this.isReaded = isReaded;
	}
	/**
	 * 返回 是否已阅 0=未读 1=已读
	 * @return
	 */
	public Long getIsReaded() 
	{
		return this.isReaded;
	}
	public void setFillOpinion(String fillOpinion) 
	{
		this.fillOpinion = fillOpinion;
	}
	/**
	 * 返回 填写意见
	 * @return
	 */
	public String getFillOpinion() 
	{
		return this.fillOpinion;
	}
	public void setSubject(String subject) 
	{
		this.subject = subject;
	}
	/**
	 * 返回 流程标题
	 * @return
	 */
	public String getSubject() 
	{
		return this.subject;
	}
	public void setReadTime(java.util.Date readTime) 
	{
		this.readTime = readTime;
	}
	/**
	 * 返回 阅读时间
	 * @return
	 */
	public java.util.Date getReadTime() 
	{
		return this.readTime;
	}
	public void setCpType(Long cpType) 
	{
		this.cpType = cpType;
	}
	/**
	 * 返回 1=抄送   在任务处理时，可以指定抄送人员进行任务抄送转发2=转发  在流程归档后，进行转发处理 
	 * @return
	 */
	public Long getCpType() 
	{
		return this.cpType;
	}


   	public Long getCreateId() {
		return createId;
	}
	public void setCreateId(Long createId) {
		this.createId = createId;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Long getDefTypeId() {
		return defTypeId;
	}
	public void setDefTypeId(Long defTypeId) {
		this.defTypeId = defTypeId;
	}
	
	
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getPosName() {
		return posName;
	}
	public void setPosName(String posName) {
		this.posName = posName;
	}
	
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof BpmProCopyto)) 
		{
			return false;
		}
		BpmProCopyto rhs = (BpmProCopyto) object;
		return new EqualsBuilder()
		.append(this.copyId, rhs.copyId)
		.append(this.actInstId, rhs.actInstId)
		.append(this.runId, rhs.runId)
		.append(this.sendUid, rhs.sendUid)
		.append(this.sendUname, rhs.sendUname)
		.append(this.ccUid, rhs.ccUid)
		.append(this.ccUname, rhs.ccUname)
		.append(this.ccTime, rhs.ccTime)
		.append(this.isReaded, rhs.isReaded)
		.append(this.fillOpinion, rhs.fillOpinion)
		.append(this.subject, rhs.subject)
		.append(this.readTime, rhs.readTime)
		.append(this.cpType, rhs.cpType)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.copyId) 
		.append(this.actInstId) 
		.append(this.runId) 
		.append(this.sendUid) 
		.append(this.sendUname) 
		.append(this.ccUid) 
		.append(this.ccUname) 
		.append(this.ccTime) 
		.append(this.isReaded) 
		.append(this.fillOpinion) 
		.append(this.subject) 
		.append(this.readTime) 
		.append(this.cpType) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("copyId", this.copyId) 
		.append("actInstId", this.actInstId) 
		.append("runId", this.runId) 
		.append("sendUid", this.sendUid) 
		.append("sendUname", this.sendUname) 
		.append("ccUid", this.ccUid) 
		.append("ccUname", this.ccUname) 
		.append("ccTime", this.ccTime) 
		.append("isReaded", this.isReaded) 
		.append("fillOpinion", this.fillOpinion) 
		.append("subject", this.subject) 
		.append("readTime", this.readTime) 
		.append("cpType", this.cpType) 
		.toString();
	}
   
  

}