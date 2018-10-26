package com.hotent.ParamRely.model.lc;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:参数依赖 Model对象
 */
public class ParamRely extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *依赖方式
	 */
	protected String  relyMethod;
	/**
	 *依赖Key
	 */
	protected String  relyKey;
	/**
	 *依赖参数
	 */
	protected String  relyValue;
	/**
	 *脚本
	 */
	protected String  script;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setRelyMethod(String relyMethod) 
	{
		this.relyMethod = relyMethod;
	}
	/**
	 * 返回 依赖方式
	 * @return
	 */
	public String getRelyMethod() 
	{
		return this.relyMethod;
	}
	public void setRelyKey(String relyKey) 
	{
		this.relyKey = relyKey;
	}
	/**
	 * 返回 依赖Key
	 * @return
	 */
	public String getRelyKey() 
	{
		return this.relyKey;
	}
	public void setRelyValue(String relyValue) 
	{
		this.relyValue = relyValue;
	}
	/**
	 * 返回 依赖参数
	 * @return
	 */
	public String getRelyValue() 
	{
		return this.relyValue;
	}
	public void setScript(String script) 
	{
		this.script = script;
	}
	/**
	 * 返回 脚本
	 * @return
	 */
	public String getScript() 
	{
		return this.script;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof ParamRely)) 
		{
			return false;
		}
		ParamRely rhs = (ParamRely) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.relyMethod, rhs.relyMethod)
		.append(this.relyKey, rhs.relyKey)
		.append(this.relyValue, rhs.relyValue)
		.append(this.script, rhs.script)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id)
		.append(this.relyMethod) 
		.append(this.relyKey) 
		.append(this.relyValue) 
		.append(this.script) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("relyMethod", this.relyMethod) 
		.append("relyKey", this.relyKey) 
		.append("relyValue", this.relyValue) 
		.append("script", this.script) 
		.toString();
	}

}
