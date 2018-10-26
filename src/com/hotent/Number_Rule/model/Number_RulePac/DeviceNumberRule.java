package com.hotent.Number_Rule.model.Number_RulePac;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:设备编号规则表 Model对象
 */
public class DeviceNumberRule extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *前缀1
	 */
	protected String  prefix1;
	/**
	 *前缀1含义
	 */
	protected String  Implication1;
	/**
	 *前缀2
	 */
	protected String  prefix2;
	/**
	 *前缀2含义
	 */
	protected String  Implication2;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setPrefix1(String prefix1) 
	{
		this.prefix1 = prefix1;
	}
	/**
	 * 返回 前缀1
	 * @return
	 */
	public String getPrefix1() 
	{
		return this.prefix1;
	}
	public void setImplication1(String Implication1) 
	{
		this.Implication1 = Implication1;
	}
	/**
	 * 返回 前缀1含义
	 * @return
	 */
	public String getImplication1() 
	{
		return this.Implication1;
	}
	public void setPrefix2(String prefix2) 
	{
		this.prefix2 = prefix2;
	}
	/**
	 * 返回 前缀2
	 * @return
	 */
	public String getPrefix2() 
	{
		return this.prefix2;
	}
	public void setImplication2(String Implication2) 
	{
		this.Implication2 = Implication2;
	}
	/**
	 * 返回 前缀2含义
	 * @return
	 */
	public String getImplication2() 
	{
		return this.Implication2;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof DeviceNumberRule)) 
		{
			return false;
		}
		DeviceNumberRule rhs = (DeviceNumberRule) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.prefix1, rhs.prefix1)
		.append(this.Implication1, rhs.Implication1)
		.append(this.prefix2, rhs.prefix2)
		.append(this.Implication2, rhs.Implication2)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id)
		.append(this.prefix1) 
		.append(this.Implication1) 
		.append(this.prefix2) 
		.append(this.Implication2) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("prefix1", this.prefix1) 
		.append("Implication1", this.Implication1) 
		.append("prefix2", this.prefix2) 
		.append("Implication2", this.Implication2) 
		.toString();
	}

}
