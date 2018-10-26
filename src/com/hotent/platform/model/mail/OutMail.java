package com.hotent.platform.model.mail;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;
/**
 * 对象功能:OUT_MAIL Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:james
 * 创建时间:2012-04-09 14:16:17
 */
public class OutMail extends BaseModel
{
	//已读
	public static Short Mail_IsRead=1;
	//未读
	public static Short Mail_IsNotRead=0;
	//已回复
	public static Integer Mail_IsReplay=1;
	//未回复
	public static Integer Mail_IsNotReplay=0;
	/**
	 * 收件箱
	 */
	public static Short Mail_InBox=1;
	/**
	 * 发件箱
	 */
	public static Short Mail_OutBox=2;
	/**
	 * 草稿箱
	 */
	public static Short Mail_DraftBox=3;
	/**
	 * 垃圾箱
	 */
	public static Short Mail_DumpBox=4;
	
	// mailId
	protected Long mailId;
	//
	protected Long setId;
	// 主题
	protected String title;
	// 内容
	protected String content;
	// 发件人地址
	protected String senderAddresses;
	// 发件人地址别名
	protected String senderName;
	// 收件人地址
	protected String receiverAddresses;
	// 收件人地址别名
	protected String receiverNames;
	// 抄送人地址
	protected String ccAddresses;
	// 抄送人地址别名
	protected String ccNames;
	// 暗送人地址
	protected String bcCAddresses;
	// 暗送人地址别名
	protected String bcCAnames;
	// 日期
	protected java.util.Date mailDate;
	// 附件ID
	protected String fileIds;
	// 是否已读(0： 未读，1 ：已读）
	protected Short isRead=Mail_IsNotRead;
	// 是否已读
	protected Integer isReply=Mail_IsNotReplay;
	// 邮件ID
	protected String emailId;
	//邮件类型  1:收件箱;2:发件箱;3:草稿箱;4:垃圾箱
    protected Integer types;
    //用户ID
    protected Long userId;
    
	public Integer getTypes() {
		return types;
	}
	public void setTypes(Integer types) {
		this.types = types;
	}
	public void setMailId(Long mailId) 
	{
		this.mailId = mailId;
	}
	/**
	 * 返回 mailId
	 * @return
	 */
	public Long getMailId() 
	{
		return mailId;
	}
	
	
	public Long getSetId() {
		return setId;
	}
	public void setSetId(Long setId) {
		this.setId = setId;
	}
	public void setTitle(String title) 
	{
		this.title = title;
	}
	/**
	 * 返回 主题
	 * @return
	 */
	public String getTitle() 
	{
		return title;
	}

	public void setContent(String content) 
	{
		this.content = content;
	}
	/**
	 * 返回 内容
	 * @return
	 */
	public String getContent() 
	{
		return content;
	}
	
	

	public Short getIsRead() {
		return isRead;
	}
	public void setIsRead(Short isRead) {
		this.isRead = isRead;
	}
	
	public Integer getIsReply() {
		return isReply;
	}
	public void setIsReply(Integer isReply) {
		this.isReply = isReply;
	}
	public void setSenderAddresses(String senderAddresses) 
	{
		this.senderAddresses = senderAddresses;
	}
	/**
	 * 返回 发件人地址
	 * @return
	 */
	public String getSenderAddresses() 
	{
		return senderAddresses;
	}

	public void setSenderName(String senderName) 
	{
		this.senderName = senderName;
	}
	/**
	 * 返回 发件人地址别名
	 * @return
	 */
	public String getSenderName() 
	{
		return senderName;
	}

	public void setReceiverAddresses(String receiverAddresses) 
	{
		this.receiverAddresses = receiverAddresses;
	}
	/**
	 * 返回 收件人地址
	 * @return
	 */
	public String getReceiverAddresses() 
	{
		return receiverAddresses;
	}

	public void setReceiverNames(String receiverNames) 
	{
		this.receiverNames = receiverNames;
	}
	/**
	 * 返回 收件人地址别名
	 * @return
	 */
	public String getReceiverNames() 
	{
		return receiverNames;
	}

	public void setCcAddresses(String ccAddresses) 
	{
		this.ccAddresses = ccAddresses;
	}
	/**
	 * 返回 抄送人地址
	 * @return
	 */
	public String getCcAddresses() 
	{
		return ccAddresses;
	}

	public void setCcNames(String ccNames) 
	{
		this.ccNames = ccNames;
	}
	/**
	 * 返回 抄送人地址别名
	 * @return
	 */
	public String getCcNames() 
	{
		return ccNames;
	}

	public void setBcCAddresses(String bcCAddresses) 
	{
		this.bcCAddresses = bcCAddresses;
	}
	/**
	 * 返回 暗送人地址
	 * @return
	 */
	public String getBcCAddresses() 
	{
		return bcCAddresses;
	}

	public void setBcCAnames(String bcCAnames) 
	{
		this.bcCAnames = bcCAnames;
	}
	/**
	 * 返回 暗送人地址别名
	 * @return
	 */
	public String getBcCAnames() 
	{
		return bcCAnames;
	}

	public void setMailDate(java.util.Date mailDate) 
	{
		this.mailDate = mailDate;
	}
	/**
	 * 返回 日期
	 * @return
	 */
	public java.util.Date getMailDate() 
	{
		return mailDate;
	}

	public void setFileIds(String fileIds) 
	{
		this.fileIds = fileIds;
	}
	/**
	 * 返回 附件ID
	 * @return
	 */
	public String getFileIds() 
	{
		return fileIds;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof OutMail)) 
		{
			return false;
		}
		OutMail rhs = (OutMail) object;
		return new EqualsBuilder()
		.append(this.mailId, rhs.mailId)
		.append(this.title, rhs.title)
		.append(this.content, rhs.content)
		.append(this.senderAddresses, rhs.senderAddresses)
		.append(this.senderName, rhs.senderName)
		.append(this.receiverAddresses, rhs.receiverAddresses)
		.append(this.receiverNames, rhs.receiverNames)
		.append(this.ccAddresses, rhs.ccAddresses)
		.append(this.ccNames, rhs.ccNames)
		.append(this.bcCAddresses, rhs.bcCAddresses)
		.append(this.bcCAnames, rhs.bcCAnames)
		.append(this.mailDate, rhs.mailDate)
		.append(this.fileIds, rhs.fileIds)
		.append(this.isRead, rhs.isRead)
		.append(this.isReply, rhs.isReply)
		.append(this.emailId, rhs.emailId)
		.append(this.types, rhs.types)
		.append(this.userId, rhs.userId)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.mailId) 
		.append(this.title) 
		.append(this.content) 
		.append(this.senderAddresses) 
		.append(this.senderName) 
		.append(this.receiverAddresses) 
		.append(this.receiverNames) 
		.append(this.ccAddresses) 
		.append(this.ccNames) 
		.append(this.bcCAddresses) 
		.append(this.bcCAnames) 
		.append(this.mailDate) 
		.append(this.fileIds) 
		.append(this.isRead) 
		.append(this.isReply) 
		.append(this.emailId) 
		.append(this.types) 
		.append(this.userId)
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("mailId", this.mailId) 
		.append("title", this.title) 
		.append("content", this.content) 
		.append("senderAddresses", this.senderAddresses) 
		.append("senderName", this.senderName) 
		.append("receiverAddresses", this.receiverAddresses) 
		.append("receiverNames", this.receiverNames) 
		.append("ccAddresses", this.ccAddresses) 
		.append("ccNames", this.ccNames) 
		.append("bcCAddresses", this.bcCAddresses) 
		.append("bcCAnames", this.bcCAnames) 
		.append("mailDate", this.mailDate) 
		.append("fileIds", this.fileIds) 
		.append("isRead", this.isRead) 
		.append("isReply", this.isReply)
		.append("emailId", this.emailId)
		.append("types", this.types)
		.append("userId",this.userId)
		.toString();
	}
   
  

}