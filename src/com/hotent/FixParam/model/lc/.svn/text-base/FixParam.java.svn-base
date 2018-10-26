package com.hotent.FixParam.model.lc;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:固定指标参数表 Model对象
 */
public class FixParam extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *固定指标名
	 */
	protected String  fix_param_name;
	/**
	 *固定指标值
	 */
	protected String  fix_param_value;
	
	protected String  fix_param_type;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setFix_param_name(String fix_param_name) 
	{
		this.fix_param_name = fix_param_name;
	}
	/**
	 * 返回 固定指标名
	 * @return
	 */
	public String getFix_param_name() 
	{
		return this.fix_param_name;
	}
	public void setFix_param_value(String fix_param_value) 
	{
		this.fix_param_value = fix_param_value;
	}
	/**
	 * 返回 固定指标值
	 * @return
	 */
	public String getFix_param_value() 
	{
		return this.fix_param_value;
	}
	
	
	
   	public String getFix_param_type() {
		return fix_param_type;
	}
	public void setFix_param_type(String fixParamType) {
		fix_param_type = fixParamType;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof FixParam)) 
		{
			return false;
		}
		FixParam rhs = (FixParam) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.fix_param_name, rhs.fix_param_name)
		.append(this.fix_param_value, rhs.fix_param_value)
		.append(this.fix_param_type, rhs.fix_param_type)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id)
		.append(this.fix_param_name) 
		.append(this.fix_param_value) 
		.append(this.fix_param_type)
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("fix_param_name", this.fix_param_name) 
		.append("fix_param_value", this.fix_param_value) 
		.append("fix_param_type", this.fix_param_type)
		.toString();
	}

}
