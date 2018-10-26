package com.hotent.platform.model.form;

import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:表单验证规则 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:phl
 * 创建时间:2012-01-12 10:29:41
 */
public class BpmFormRule extends BaseModel
{
	// id
	protected Long id;
	// 规则名
	protected String name;
	// 规则
	protected String rule;
	// 描述
	protected String memo;
	// 错误提示信息
	protected String tipInfo;

	public void setId(Long id) 
	{
		this.id = id;
	}
	/**
	 * 返回 id
	 * @return
	 */
	public Long getId() 
	{
		return id;
	}

	public void setName(String name) 
	{
		this.name = name;
	}
	/**
	 * 返回 规则名
	 * @return
	 */
	public String getName() 
	{
		return name;
	}

	public void setRule(String rule) 
	{
		this.rule = rule;
	}
	/**
	 * 返回 规则
	 * @return
	 */
	public String getRule() 
	{
		return rule;
	}

	public void setMemo(String memo) 
	{
		this.memo = memo;
	}
	/**
	 * 返回 描述
	 * @return
	 */
	public String getMemo() 
	{
		return memo;
	}

	public void setTipInfo(String tipInfo) 
	{
		this.tipInfo = tipInfo;
	}
	/**
	 * 返回 错误提示信息
	 * @return
	 */
	public String getTipInfo() 
	{
		return tipInfo;
	}

   
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof BpmFormRule)) 
		{
			return false;
		}
		BpmFormRule rhs = (BpmFormRule) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.name, rhs.name)
		.append(this.rule, rhs.rule)
		.append(this.memo, rhs.memo)
		.append(this.tipInfo, rhs.tipInfo)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id) 
		.append(this.name) 
		.append(this.rule) 
		.append(this.memo) 
		.append(this.tipInfo) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("name", this.name) 
		.append("rule", this.rule) 
		.append("memo", this.memo) 
		.append("tipInfo", this.tipInfo) 
		.toString();
	}
   
  

}