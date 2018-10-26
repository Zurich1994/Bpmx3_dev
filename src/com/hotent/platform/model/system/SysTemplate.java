package com.hotent.platform.model.system;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import com.hotent.core.model.BaseModel;
/**
 * 对象功能:模版管理 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:pkq
 * 创建时间:2011-12-28 14:04:30
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class SysTemplate extends BaseModel
{
	
	public static Integer IS_DEFAULT_YES=1;
	public static Integer IS_DEFAULT_NO=0;

	/** 模板用途类型静态变量  **/
	/**
	 * 终止提醒=1
	 */
	public static Integer USE_TYPE_TERMINATION = 1;
	/**
	 * 催办提醒=2
	 */
	public static Integer USE_TYPE_URGE = 2;
	/**
	 * 审批提醒（任务通知）=3
	 */
	public static Integer USE_TYPE_NOTIFY = 3;
	/**
	 * 撤销提醒=4
	 */
	public static Integer USE_TYPE_REVOKED = 4;
	/**
	 * 取消转办=5
	 */
	public static Integer USE_TYPE_CANCLE_DELEGATE = 5;
	/**
	 * 沟通提醒=6
	 */
	public static Integer USE_TYPE_COMMUNICATION = 6;
	/**
	 * 归档提醒（通知发起人）=7
	 */
	public static Integer USE_TYPE_NOTIFY_STARTUSER = 7;
	/**
	 * 转办提醒=8
	 */
	public static Integer USE_TYPE_DELEGATE = 8;
	/**
	 * 退回提醒(驳回 ,驳回到发起人)=9
	 */
	public static Integer USE_TYPE_REJECT = 9;
	/**
	 * 被沟通人提交（沟通反馈）=10
	 */
	public static Integer USE_TYPE_FEEDBACK = 10;
	/**
	 * 取消代理=11
	 */
	public static Integer USE_TYPE_CANCLE_AGENT = 11;
	/**
	 * 抄送提醒=12
	 */
	public static Integer USE_TYPE_COPYTO = 12;
	/**
	 * 流程节点无人员=13
	 */
	public static Integer USE_TYPE_NOBODY = 13;
	/**
	 * 逾期提醒=19
	 */
	public static Integer USE_TYPE_OVERDUE = 19;
	/**
	 * 代理提醒=22
	 */
	public static Integer USE_TYPE_AGENT = 22;
	
	/**
	 * 消息转发
	 */
	public static Integer USE_TYPE_FORWARD = 23;
	
	/**
	 *	重启任务
	 */
	public static Integer USE_TYPE_RESTARTTASK = 24;

	/**
	 * 通知任务所属人(代理)
	 */
	public static Integer USE_TYPE_NOTIFYOWNER_AGENT=25;
	
	/**
	 *	加签提醒
	 */
	public static Integer USE_TYPE_TRANSTO = 26;
	
	/**
	 *	被加签人提交(加签反馈)
	 */
	public static Integer USE_TYPE_TRANSTO_FEEDBACK = 27;
	
	/**
	 *	取消流转
	 */
	public static Integer USE_TYPE_CANCLE_TRANSTO = 28;
	
	/**
	 * 补签提醒
	 */
	public static Integer USE_TYPE_RETROACTIVE = 29;
	
	/**
	 * 发流程状态信息给创建人
	 */
	public static Integer USE_TYPE_SENDTOCREATOR = 30;
	
	/**
	 * 模板类型=html消息
	 */
	public static String TEMPLATE_TYPE_HTML="html";
	/**
	 * 模板类型=plain消息
	 */
	public static String TEMPLATE_TYPE_PLAIN="plain";

	/**
	 * 模板标题=流程标题
	 */
	public static String TEMPLATE_TITLE ="title";
	/**
	 * 模板标题=邮件标题
	 */
	public static String TEMPLATE_TITLE_MAIL ="mailtitle";
	/**
	 * 模板标题=站内标题
	 */
	public static String TEMPLATE_TITLE_INNER ="innertitle";
	// 主键Id
	@XmlElement
	protected Long templateId;
	// 模版名称
	@XmlElement
	protected String name;
//	// 模版站内信内容
//	@XmlElement
//	protected String innerContent;
//	//模板邮件内容
//	@XmlElement
//	protected String mailContent;
//	//模板短信内容
//	@XmlElement
//	protected String smsContent;
	
	// 模版站html内容
	@XmlElement
	protected String htmlContent;
	//模板普通内容
	@XmlElement
	protected String plainContent;	
	//是否默认模板
	@XmlElement
	protected Integer isDefault;
	//模板用途类型
	@XmlElement
	protected Integer useType;
	//标题
	@XmlElement
	protected String title;
	
	public void setTemplateId(Long templateId) 
	{
		this.templateId = templateId;
	}
	/**
	 * 返回 主键Id
	 * @return
	 */
	public Long getTemplateId() 
	{
		return templateId;
	}

	public void setName(String name) 
	{
		this.name = name;
	}
	/**
	 * 返回 模版名称
	 * @return
	 */
	public String getName() 
	{
		return name;
	}

   	public Integer getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}
	public Integer getUseType() {
		return useType;
	}
	public void setUseType(Integer useType) {
		this.useType = useType;
	}
//	public String getInnerContent() {
//		return innerContent;
//	}
//	public void setInnerContent(String innerContent) {
//		this.innerContent = innerContent;
//	}
//	public String getMailContent() {
//		return mailContent;
//	}
//	public void setMailContent(String mailContent) {
//		this.mailContent = mailContent;
//	}
//	public String getSmsContent() {
//		return smsContent;
//	}
//	public void setSmsContent(String smsContent) {
//		this.smsContent = smsContent;
//	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof SysTemplate)) 
		{
			return false;
		}
		SysTemplate rhs = (SysTemplate) object;
		return new EqualsBuilder()
		.append(this.templateId, rhs.templateId)
		.append(this.name, rhs.name)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.templateId) 
		.append(this.name) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("templateId", this.templateId) 
		.append("name", this.name) 
		.toString();
	}
	public String getHtmlContent() {
		return htmlContent;
	}
	public void setHtmlContent(String htmlContent) {
		this.htmlContent = htmlContent;
	}
	public String getPlainContent() {
		return plainContent;
	}
	public void setPlainContent(String plainContent) {
		this.plainContent = plainContent;
	}
   
  

}