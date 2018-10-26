package com.hotent.platform.model.system;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;
/**
 * 对象功能:webservice数据模板展示 Model对象
 */
public class WsDataTemplate extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *列表名称
	 */
	protected String  name;
	/**
	 *对应serviceId
	 */
	protected Long  serviceId;
	/**
	 *展示数据的模版
	 */
	protected String  template;
	/**
	 * 解析返回的XML的脚本
	 */
	protected String  script;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	/**
	 * 返回 列表名称
	 * @return
	 */
	public String getName() 
	{
		return this.name;
	}
	public void setServiceId(Long serviceId) 
	{
		this.serviceId = serviceId;
	}
	/**
	 * 返回 对应serviceId
	 * @return
	 */
	public Long getServiceId() 
	{
		return this.serviceId;
	}
	public void setTemplate(String template) 
	{
		this.template = template;
	}
	/**
	 * 返回 展示数据的模版
	 * @return
	 */
	public String getTemplate() 
	{
		return this.template;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof WsDataTemplate)) 
		{
			return false;
		}
		WsDataTemplate rhs = (WsDataTemplate) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.name, rhs.name)
		.append(this.serviceId, rhs.serviceId)
		.append(this.template, rhs.template)
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
		.append(this.name) 
		.append(this.serviceId) 
		.append(this.template) 
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
		.append("name", this.name) 
		.append("serviceId", this.serviceId) 
		.append("template", this.template) 
		.append("script", this.script) 
		.toString();
	}
	public String getScript() {
		return script;
	}
	public void setScript(String script) {
		this.script = script;
	}

}
