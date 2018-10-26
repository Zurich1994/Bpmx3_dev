package com.hotent.platform.model.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;


/**
 * 对象功能:sys_bus_event Model对象
 * 开发公司:宏天软件
 * 开发人员:ray
 * 创建时间:2015-05-22 11:14:30
 */
public class SysBusEvent extends BaseModel{
	// ID
	protected Long id;
	// FORMKEY
	protected String formkey;
	// JS_PRE_SCRIPT
	protected String jsPreScript;
	// js后置脚本
	protected String jsAfterScript;
	// PRE_SCRIPT
	protected String preScript;
	// AFTER_SCRIPT
	protected String afterScript;

	public void setId(Long id){
		this.id = id;
	}
	/**
	 * 返回 ID
	 * @return
	 */
	public Long getId() {
		return this.id;
	}
	public void setFormkey(String formkey){
		this.formkey = formkey;
	}
	/**
	 * 返回 FORMKEY
	 * @return
	 */
	public String getFormkey() {
		return this.formkey;
	}
	public void setJsPreScript(String jsPreScript){
		this.jsPreScript = jsPreScript;
	}
	/**
	 * 返回 JS_PRE_SCRIPT
	 * @return
	 */
	public String getJsPreScript() {
		return this.jsPreScript;
	}
	public void setJsAfterScript(String jsAfterScript){
		this.jsAfterScript = jsAfterScript;
	}
	/**
	 * 返回 js后置脚本
	 * @return
	 */
	public String getJsAfterScript() {
		return this.jsAfterScript;
	}
	public void setPreScript(String preScript){
		this.preScript = preScript;
	}
	/**
	 * 返回 PRE_SCRIPT
	 * @return
	 */
	public String getPreScript() {
		return this.preScript;
	}
	public void setAfterScript(String afterScript){
		this.afterScript = afterScript;
	}
	/**
	 * 返回 AFTER_SCRIPT
	 * @return
	 */
	public String getAfterScript() {
		return this.afterScript;
	}
	

   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof SysBusEvent)) 
		{
			return false;
		}
		SysBusEvent rhs = (SysBusEvent) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.formkey, rhs.formkey)
		.append(this.jsPreScript, rhs.jsPreScript)
		.append(this.jsAfterScript, rhs.jsAfterScript)
		.append(this.preScript, rhs.preScript)
		.append(this.afterScript, rhs.afterScript)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id) 
		.append(this.formkey) 
		.append(this.jsPreScript) 
		.append(this.jsAfterScript) 
		.append(this.preScript) 
		.append(this.afterScript) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("formkey", this.formkey) 
		.append("jsPreScript", this.jsPreScript) 
		.append("jsAfterScript", this.jsAfterScript) 
		.append("preScript", this.preScript) 
		.append("afterScript", this.afterScript) 
		.toString();
	}
   
  

}