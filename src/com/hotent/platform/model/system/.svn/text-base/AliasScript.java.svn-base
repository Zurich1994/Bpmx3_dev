package com.hotent.platform.model.system;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;
/**
 * 对象功能:自定义别名脚本表 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2013-12-19 11:26:03
 */
public class AliasScript extends BaseModel
{
	// ID
	protected Long  id;
	// 脚本的别名
	protected String  aliasName;
	// 脚本的描叙
	protected String  aliasDesc;
	// 调用类的路径
	protected String  className;
	// 调用类的对象名
	protected String  classInsName;
	// 调用的方法名
	protected String  methodName;
	// 调用的方法的描叙
	protected String  methodDesc;
	// 自定义脚本内容
	protected String  scriptComten;
	// 方法返回类型
	protected String  returnType;
	// 脚本类型（自定义：custom  系统默认：default）
	protected String  scriptType;
	// 方法相关设置
	protected String  argument;
	// 是否禁用（禁用：1   启用：0）
	protected Long  enable;
	// 是否配置返回值（是：1   否：0）
	protected Short isReturnValue = 0;
	// 返回值参数设置
	protected String returnParamJson;
	
	public void setId(Long id) 
	{
		this.id = id;
	}
	/**
	 * 返回 ID
	 * @return
	 */
	public Long getId() 
	{
		return this.id;
	}
	public void setAliasName(String aliasName) 
	{
		this.aliasName = aliasName;
	}
	/**
	 * 返回 脚本的别名
	 * @return
	 */
	public String getAliasName() 
	{
		return this.aliasName;
	}
	public void setAliasDesc(String aliasDesc) 
	{
		this.aliasDesc = aliasDesc;
	}
	/**
	 * 返回 脚本的描叙
	 * @return
	 */
	public String getAliasDesc() 
	{
		return this.aliasDesc;
	}
	public void setClassName(String className) 
	{
		this.className = className;
	}
	/**
	 * 返回 调用类的路径
	 * @return
	 */
	public String getClassName() 
	{
		return this.className;
	}
	public void setClassInsName(String classInsName) 
	{
		this.classInsName = classInsName;
	}
	/**
	 * 返回 调用类的对象名
	 * @return
	 */
	public String getClassInsName() 
	{
		return this.classInsName;
	}
	public void setMethodName(String methodName) 
	{
		this.methodName = methodName;
	}
	/**
	 * 返回 调用的方法名
	 * @return
	 */
	public String getMethodName() 
	{
		return this.methodName;
	}
	public void setMethodDesc(String methodDesc) 
	{
		this.methodDesc = methodDesc;
	}
	/**
	 * 返回 调用的方法的描叙
	 * @return
	 */
	public String getMethodDesc() 
	{
		return this.methodDesc;
	}
	public void setReturnType(String returnType) 
	{
		this.returnType = returnType;
	}
	/**
	 * 返回 方法返回类型
	 * @return
	 */
	public String getReturnType() 
	{
		return this.returnType;
	}
	public void setArgument(String argument) 
	{
		this.argument = argument;
	}
	/**
	 * 返回 方法相关设置
	 * @return
	 */
	public String getArgument() 
	{
		return this.argument;
	}
	public void setEnable(Long enable) 
	{
		this.enable = enable;
	}
	/**
	 * 返回 是否禁用
	 * @return
	 */
	public Long getEnable() 
	{
		return this.enable;
	}
	
   	public String getScriptComten()
	{
		return scriptComten;
	}
   	/**
	 * 返回 自定义脚本内容
	 * @return
	 */
   	public void setScriptComten(String scriptComten)
	{
		this.scriptComten = scriptComten;
	}
	public String getScriptType()
	{
		return scriptType;
	}
	
	/**
	 * 返回 脚本类型（自定义：custom  系统默认：default）
	 * @return
	 */
	public void setScriptType(String scriptType)
	{
		this.scriptType = scriptType;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof AliasScript)) 
		{
			return false;
		}
		AliasScript rhs = (AliasScript) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.aliasName, rhs.aliasName)
		.append(this.aliasDesc, rhs.aliasDesc)
		.append(this.className, rhs.className)
		.append(this.classInsName, rhs.classInsName)
		.append(this.methodName, rhs.methodName)
		.append(this.methodDesc, rhs.methodDesc)
		.append("scriptComten", rhs.scriptComten) 
		.append("returnType", rhs.returnType)
		.append("scriptType", rhs.scriptType) 
		.append(this.argument, rhs.argument)
		.append(this.enable, rhs.enable)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id) 
		.append(this.aliasName) 
		.append(this.aliasDesc) 
		.append(this.className) 
		.append(this.classInsName) 
		.append(this.methodName) 
		.append(this.methodDesc) 
		.append(this.scriptComten) 
		.append(this.returnType) 
		.append(this.scriptType) 
		.append(this.argument) 
		.append(this.enable) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("aliasName", this.aliasName) 
		.append("aliasDesc", this.aliasDesc) 
		.append("className", this.className) 
		.append("classInsName", this.classInsName) 
		.append("methodName", this.methodName) 
		.append("methodDesc", this.methodDesc) 
		.append("scriptComten", this.scriptComten) 
		.append("returnType", this.returnType)
		.append("scriptType", this.scriptType) 
		.append("argument", this.argument) 
		.append("enable", this.enable) 
		.toString();
	}
	public Short getIsReturnValue() {
		return isReturnValue;
	}
	public void setIsReturnValue(Short isReturnValue) {
		this.isReturnValue = isReturnValue;
	}
	public String getReturnParamJson() {
		return returnParamJson;
	}
	public void setReturnParamJson(String returnParamJson) {
		this.returnParamJson = returnParamJson;
	}
   
  

}