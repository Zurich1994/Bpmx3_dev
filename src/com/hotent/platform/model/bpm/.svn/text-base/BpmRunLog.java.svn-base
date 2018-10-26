package com.hotent.platform.model.bpm;

import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:流程运行日志 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:heyifan
 * 创建时间:2012-08-06 13:56:42
 */
public class BpmRunLog extends BaseModel
{
	/**
	 * 操作类型：启动流程
	 */
	public static final Integer  OPERATOR_TYPE_START=0;
	
	/**
	 * 操作类型：交办
	 */
	public static final Integer  OPERATOR_TYPE_DELEGATE=1;
	
	/**
	 * 操作类型：撤销
	 */
	public static final Integer  OPERATOR_TYPE_RETRIEVE=2;
	
	/**
	 * 操作类型：删除流程实例
	 */
	public static final Integer  OPERATOR_TYPE_DELETEINSTANCE=3;
	
	/**
	 * 操作类型：删除流程草稿
	 */
	public static final Integer OPERATOR_TYPE_DELETEFORM=23;
	
	/**
	 * 操作类型：投票——同意
	 */
	public static final Integer  OPERATOR_TYPE_AGREE=4;
	
	/**
	 * 操作类型：投票——反对
	 */
	public static final Integer  OPERATOR_TYPE_OBJECTION=5;
	
	/**
	 * 操作类型：投票——弃权
	 */
	public static final Integer  OPERATOR_TYPE_ABSTENTION=6;
	
	/**
	 * 操作类型：补签
	 */
	public static final Integer  OPERATOR_TYPE_SIGN=7;
	
	/**
	 * 操作类型：驳回
	 */
	public static final Integer  OPERATOR_TYPE_REJECT=8;
	
	/**
	 * 操作类型：驳回到发起人
	 */
	public static final Integer  OPERATOR_TYPE_REJECT2SPONSOR=9;
	
	/**
	 * 操作类型：删除任务
	 */
	public static final Integer  OPERATOR_TYPE_DELETETASK=10;
	
	/**
	 * 执行代理任务
	 */
	public static final Integer OPERATOR_TYPE_AGENT=11;

	/**
	 * 锁定任务
	 */
	public static final Integer OPERATOR_TYPE_LOCK=13;
	
	/**
	 * 任务解锁
	 */
	public static final Integer OPERATOR_TYPE_UNLOCK=14;
	
	/**
	 * 添加意见。
	 */
	public static final Integer OPERATOR_TYPE_ADDOPINION=15;
	
	/**
	 * 任务指派。
	 */
	public static final Integer  OPERATOR_TYPE_ASSIGN=16;
	
	/**
	 * 修改任务所有人。
	 */
	public static final Integer  OPERATOR_TYPE_SETOWNER=17;
	
	/**
	 * 操作类型：结束任务
	 */
	public static final Integer  OPERATOR_TYPE_ENDTASK=18;
	
	/**
	 * 更改执行路径
	 */
	public static final Integer OPERATOR_TYPE_CHANGEPATH=19;
	
	/**
	 * 收回任务
	 */
	public static final Integer OPERATOR_TYPE_BACK=20;
	
	/**
	 * 操作类型：结束流程实例
	 */
	public static final Integer  OPERATOR_TYPE_ENDINSTANCE=21;
	/**
	 * 保存草稿
	 */
	public static final Integer OPERATOR_TYPE_SAVEFORM=22;
	
	/**
	 * 办结转发
	 */
	public static final Integer OPERATOR_TYPE_FINISHDIVERT=23;
	
	// 主键
	protected Long  id;
	// 用户ID
	protected Long  userid;
	// 用户名称
	protected String  username;
	// 操作时间
	protected java.util.Date createtime;
	// 操作类型
	protected Integer  operatortype;
	// 备注
	protected String  memo;
	//流程运行ID
	protected Long  runid;
	//流程标题
	protected String processSubject;

	public void setId(Long id) 
	{
		this.id = id;
	}
	/**
	 * 返回 主键
	 * @return
	 */
	public Long getId() 
	{
		return this.id;
	}
	public void setUserid(Long userid) 
	{
		this.userid = userid;
	}
	/**
	 * 返回 用户ID
	 * @return
	 */
	public Long getUserid() 
	{
		return this.userid;
	}
	public void setUsername(String username) 
	{
		this.username = username;
	}
	/**
	 * 返回 用户名称
	 * @return
	 */
	public String getUsername() 
	{
		return this.username;
	}
	public void setCreatetime(java.util.Date createtime) 
	{
		this.createtime = createtime;
	}
	/**
	 * 返回 操作时间
	 * @return
	 */
	public java.util.Date getCreatetime() 
	{
		return this.createtime;
	}
	public void setOperatortype(Integer operatortype) 
	{
		this.operatortype = operatortype;
	}
	/**
	 * 返回 操作类型
	 * @return
	 */
	public Integer getOperatortype() 
	{
		return this.operatortype;
	}
	public void setMemo(String memo) 
	{
		this.memo = memo;
	}
	/**
	 * 返回 备注
	 * @return
	 */
	public String getMemo() 
	{
		return this.memo;
	}

   
   	public Long getRunid() {
		return runid;
	}
	public void setRunid(Long runid) {
		this.runid = runid;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof BpmRunLog)) 
		{
			return false;
		}
		BpmRunLog rhs = (BpmRunLog) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.userid, rhs.userid)
		.append(this.username, rhs.username)
		.append(this.createtime, rhs.createtime)
		.append(this.operatortype, rhs.operatortype)
		.append(this.memo, rhs.memo)
		.append(this.processSubject, rhs.processSubject)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id) 
		.append(this.userid) 
		.append(this.username) 
		.append(this.createtime) 
		.append(this.operatortype) 
		.append(this.memo)
		.append(this.processSubject) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("userid", this.userid) 
		.append("username", this.username) 
		.append("createtime", this.createtime) 
		.append("operatortype", this.operatortype) 
		.append("memo", this.memo)
		.append("processSubject", this.processSubject) 
		.toString();
	}
	
	public String getProcessSubject() {
		return processSubject;
	}
	public void setProcessSubject(String processSubject) {
		this.processSubject = processSubject;
	}
  

}