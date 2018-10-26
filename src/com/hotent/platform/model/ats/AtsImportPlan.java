package com.hotent.platform.model.ats;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;


/**
 * 对象功能:打卡导入方案 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-05-26 13:50:13
 */
public class AtsImportPlan extends BaseModel{
	// 主键
	protected Long id;
	// 编码
	protected String code;
	// 名称
	protected String name;
	//分割符
	protected Short separate;
	// 描述
	protected String memo;
	// 打卡对应关系
	protected String pushCardMap;

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
	public void setPushCardMap(String pushCardMap){
		this.pushCardMap = pushCardMap;
	}
	/**
	 * 返回 打卡对应关系
	 * @return
	 */
	public String getPushCardMap() {
		return this.pushCardMap;
	}
	
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof AtsImportPlan)) 
		{
			return false;
		}
		AtsImportPlan rhs = (AtsImportPlan) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.code, rhs.code)
		.append(this.name, rhs.name)
		.append(this.memo, rhs.memo)
		.append(this.pushCardMap, rhs.pushCardMap)
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
		.append(this.memo) 
		.append(this.pushCardMap) 
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
		.append("memo", this.memo) 
		.append("pushCardMap", this.pushCardMap) 
		.toString();
	}
   
  

}