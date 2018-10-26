package com.hotent.platform.model.system;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
/**

import com.hotent.core.model.BaseModel;
/**
 * 对象功能:维度信息 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:pkq
 * 创建时间:2011-11-08 12:04:21
 */
public class Demension extends BaseModel{
	//行政维度。
	public static Long  ADMINSTRATION=1L;
	//岗位维度
	public static Demension positionDem=new Demension();
	static{
		positionDem.setDemId(0L);
		positionDem.setDemName("岗位维度");
	}
	// 维度编号
	private Long demId;
	// 维度名称
	private String demName;
	// 维度描述
	private String demDesc;
	//属于该维度的最高层级组织PATH
	private String demOrgPath;

	public void setDemId(Long demId) {
		this.demId = demId;
	}
	/**
	 * 返回 维度编号
	 * @return
	 */
	public Long getDemId() {
		return demId;
	}

	public void setDemName(String demName) {
		this.demName = demName;
	}
	/**
	 * 返回 维度名称
	 * @return
	 */
	public String getDemName() {
		return demName;
	}

	public void setDemDesc(String demDesc) {
		this.demDesc = demDesc;
	}
	/**
	 * 返回 维度描述
	 * @return
	 */
	public String getDemDesc() {
		return demDesc;
	}

	public String getDemOrgPath() {
		return demOrgPath;
	}
	public void setDemOrgPath(String demOrgPath) {
		this.demOrgPath = demOrgPath;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof Demension)) 
		{
			return false;
		}
		Demension rhs = (Demension) object;
		return new EqualsBuilder()
		.append(this.demId, rhs.demId)
		.append(this.demName, rhs.demName)
		.append(this.demDesc, rhs.demDesc)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.demId) 
		.append(this.demName) 
		.append(this.demDesc) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("demId", this.demId) 
		.append("demName", this.demName) 
		.append("demDesc", this.demDesc) 
		.toString();
	}
}