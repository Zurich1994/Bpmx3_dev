package com.hotent.platform.model.ats;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;


/**
 * 对象功能:日历模版 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-05-17 15:44:41
 */
public class AtsCalendarTempl extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3964045689489744845L;
	// 主键
	protected Long id;
	// 编码
	protected String code;
	// 名称
	protected String name;
	// 是否系统预置
	protected Short isSys = AtsConstant.NO;
	// 状态
	protected Short status =AtsConstant.YES;
	// 描述
	protected String memo;
	
	//明细
	protected String detailList;

	public void setId(Long id){
		this.id = id;
	}
	/**
	 * 返回 主键
	 * @return
	 */
	public Long getId() {
		return this.id;
	}
	public void setCode(String code){
		this.code = code;
	}
	/**
	 * 返回 编码
	 * @return
	 */
	public String getCode() {
		return this.code;
	}
	public void setName(String name){
		this.name = name;
	}
	/**
	 * 返回 名称
	 * @return
	 */
	public String getName() {
		return this.name;
	}
	public void setIsSys(Short isSys){
		this.isSys = isSys;
	}
	/**
	 * 返回 是否系统预置
	 * @return
	 */
	public Short getIsSys() {
		return this.isSys;
	}
	public void setStatus(Short status){
		this.status = status;
	}
	/**
	 * 返回 状态
	 * @return
	 */
	public Short getStatus() {
		return this.status;
	}
	public void setMemo(String memo){
		this.memo = memo;
	}
	/**
	 * 返回 描述
	 * @return
	 */
	public String getMemo() {
		return this.memo;
	}
	

   	public String getDetailList() {
		return detailList;
	}
	public void setDetailList(String detailList) {
		this.detailList = detailList;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof AtsCalendarTempl)) 
		{
			return false;
		}
		AtsCalendarTempl rhs = (AtsCalendarTempl) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.code, rhs.code)
		.append(this.name, rhs.name)
		.append(this.isSys, rhs.isSys)
		.append(this.status, rhs.status)
		.append(this.memo, rhs.memo)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id) 
		.append(this.code) 
		.append(this.name) 
		.append(this.isSys) 
		.append(this.status) 
		.append(this.memo) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("code", this.code) 
		.append("name", this.name) 
		.append("isSys", this.isSys) 
		.append("status", this.status) 
		.append("memo", this.memo) 
		.toString();
	}
   
  

}