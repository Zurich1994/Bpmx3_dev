package com.hotent.platform.model.ats;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;


/**
 * 对象功能:考勤计算设置 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-06-03 14:46:19
 */
public class AtsAttenceCalculateSet extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4517182035375216160L;
	// 主键
	protected Long id;
	// 汇总设置
	protected String summary;
	// 明细设置
	protected String detail;
	
	protected Short type;

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
	public void setSummary(String summary){
		this.summary = summary;
	}
	/**
	 * 返回 汇总设置
	 * @return
	 */
	public String getSummary() {
		return this.summary;
	}
	public void setDetail(String detail){
		this.detail = detail;
	}
	/**
	 * 返回 明细设置
	 * @return
	 */
	public String getDetail() {
		return this.detail;
	}
	

   	public Short getType() {
		return type;
	}
	public void setType(Short type) {
		this.type = type;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof AtsAttenceCalculateSet)) 
		{
			return false;
		}
		AtsAttenceCalculateSet rhs = (AtsAttenceCalculateSet) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.summary, rhs.summary)
		.append(this.detail, rhs.detail)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id) 
		.append(this.summary) 
		.append(this.detail) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("summary", this.summary) 
		.append("detail", this.detail) 
		.toString();
	}
   
  

}