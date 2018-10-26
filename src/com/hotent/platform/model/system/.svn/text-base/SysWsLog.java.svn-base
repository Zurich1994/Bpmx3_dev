package com.hotent.platform.model.system;

import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:SYS_WS_LOG Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:cjj
 * 创建时间:2013-05-31 10:41:48
 */
public class SysWsLog extends BaseModel
{
	
	/**
	 * 成功
	 */
	public  static final Short SUCCESS=1;
	/**
	 * 失败
	 */
	public static final Short FAILTURE=0;
	
	// LOG_ID
	protected Long  logId;
	// CLS_NAME
	protected String  clsName;
	// CALL_NAME
	protected String  callName;
	// CALL_DESC
	protected String  callDesc;
	// IMP_DESC
	protected String  impDesc;
	// EXP_DESC
	protected String  expDesc;
	// IS_SUCCESS
	protected Short  isSuccess;
	// ERR_DETAIL
	protected String  errDetail;
	// cxf来源
	protected String sourceMsg;

	public void setLogId(Long logId) 
	{
		this.logId = logId;
	}
	/**
	 * 返回 LOG_ID
	 * @return
	 */
	public Long getLogId() 
	{
		return this.logId;
	}
	public void setClsName(String clsName) 
	{
		this.clsName = clsName;
	}
	/**
	 * 返回 CLS_NAME
	 * @return
	 */
	public String getClsName() 
	{
		return this.clsName;
	}
	public void setCallName(String callName) 
	{
		this.callName = callName;
	}
	/**
	 * 返回 CALL_NAME
	 * @return
	 */
	public String getCallName() 
	{
		return this.callName;
	}
	public void setCallDesc(String callDesc) 
	{
		this.callDesc = callDesc;
	}
	/**
	 * 返回 CALL_DESC
	 * @return
	 */
	public String getCallDesc() 
	{
		return this.callDesc;
	}
	public void setImpDesc(String impDesc) 
	{
		this.impDesc = impDesc;
	}
	/**
	 * 返回 IMP_DESC
	 * @return
	 */
	public String getImpDesc() 
	{
		return this.impDesc;
	}
	public void setExpDesc(String expDesc) 
	{
		this.expDesc = expDesc;
	}
	/**
	 * 返回 EXP_DESC
	 * @return
	 */
	public String getExpDesc() 
	{
		return this.expDesc;
	}
	public void setIsSuccess(Short isSuccess) 
	{
		this.isSuccess = isSuccess;
	}
	/**
	 * 返回 IS_SUCCESS
	 * @return
	 */
	public Short getIsSuccess() 
	{
		return this.isSuccess;
	}
	public void setErrDetail(String errDetail) 
	{
		this.errDetail = errDetail;
	}
	/**
	 * 返回 ERR_DETAIL
	 * @return
	 */
	public String getErrDetail() 
	{
		return this.errDetail;
	}
	
	public String getSourceMsg() {
		return sourceMsg;
	}
	public void setSourceMsg(String sourceMsg) {
		this.sourceMsg = sourceMsg;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof SysWsLog)) 
		{
			return false;
		}
		SysWsLog rhs = (SysWsLog) object;
		return new EqualsBuilder()
		.append(this.logId, rhs.logId)
		.append(this.createtime, rhs.createtime)
		.append(this.clsName, rhs.clsName)
		.append(this.callName, rhs.callName)
		.append(this.callDesc, rhs.callDesc)
		.append(this.impDesc, rhs.impDesc)
		.append(this.expDesc, rhs.expDesc)
		.append(this.isSuccess, rhs.isSuccess)
		.append(this.errDetail, rhs.errDetail)
		.append(this.sourceMsg,rhs.sourceMsg)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.logId) 
		.append(this.createtime) 
		.append(this.clsName) 
		.append(this.callName) 
		.append(this.callDesc) 
		.append(this.impDesc) 
		.append(this.expDesc) 
		.append(this.isSuccess) 
		.append(this.errDetail) 
		.append(this.sourceMsg) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("logId", this.logId) 
		.append("createtime", this.createtime) 
		.append("clsName", this.clsName) 
		.append("callName", this.callName) 
		.append("callDesc", this.callDesc) 
		.append("impDesc", this.impDesc) 
		.append("expDesc", this.expDesc) 
		.append("isSuccess", this.isSuccess) 
		.append("errDetail", this.errDetail) 
		.append("sourceMsg", this.sourceMsg) 
		.toString();
	}
   
  

}