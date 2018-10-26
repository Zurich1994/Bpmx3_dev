package com.hotent.platform.model.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hotent.core.model.BaseModel;
import com.hotent.core.util.StringUtil;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:系统条件脚本 Model对象
 * 开发公司:hotent
 * 开发人员:heyifan
 * 创建时间:2013-04-05 11:34:56
 */
public class ConditionScript extends BaseModel
{
	static Map<String,String> paraTypeMap=new HashMap<String, String>();
	static {
		paraTypeMap.put("int", "java.lang.Integer");
		paraTypeMap.put("short", "java.lang.Short");
		paraTypeMap.put("long", "java.lang.Long");
		paraTypeMap.put("float", "java.lang.Float");
		paraTypeMap.put("double", "java.lang.Double");
		paraTypeMap.put("char", "java.lang.Character");
		paraTypeMap.put("byte", "java.lang.Byte");
		paraTypeMap.put("boolean", "java.lang.Boolean");
		
	}
	// 主键
	protected Long  id;
	// 脚本所在类的类名
	protected String  className;
	// 类实例名
	protected String  classInsName;
	// 方法名
	protected String  methodName;
	// 方法描述
	protected String  methodDesc;
	// 返回值类型
	protected String  returnType;
	/**
	 *  参数信息
	 * [
	 * 	{
	 * 		"paraName":"arg0",
	 * 		"paraType":"org.activiti.engine.impl.persistence.entity.TaskEntity",
	 * 		"paraDesc":"任务实体",
	 * 		"paraCt":"18"
	 *  }
	 * ]
	 */
	protected String  argument;
	// 是否有效
	protected Long  enable;
	public void setId(Long id) 
	{
		this.id = id;
	}
	/**
	 * 返回 主键
	 * @return
	 */
	public Long getId() 
	{
		return this.id;
	}
	public void setClassName(String className) 
	{
		this.className = className;
	}
	/**
	 * 返回 脚本所在类的类名
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
	 * 返回 类实例名
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
	 * 返回 方法名
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
	 * 返回 方法描述
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
	 * 返回 返回值类型
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
	 * 返回 参数信息
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
	 * 返回 是否有效
	 * @return
	 */
	public Long getEnable() 
	{
		return this.enable;
	}


   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof ConditionScript)) 
		{
			return false;
		}
		ConditionScript rhs = (ConditionScript) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.className, rhs.className)
		.append(this.classInsName, rhs.classInsName)
		.append(this.methodName, rhs.methodName)
		.append(this.methodDesc, rhs.methodDesc)
		.append(this.returnType, rhs.returnType)
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
		.append(this.className) 
		.append(this.classInsName) 
		.append(this.methodName) 
		.append(this.methodDesc) 
		.append(this.returnType) 
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
		.append("className", this.className) 
		.append("classInsName", this.classInsName) 
		.append("methodName", this.methodName) 
		.append("methodDesc", this.methodDesc) 
		.append("returnType", this.returnType) 
		.append("argument", this.argument) 
		.append("enable", this.enable) 
		.toString();
	}
	
	
	
	
	/**
	 * 取得条件节点的
	 * @return
	 * @throws ClassNotFoundException
	 */
	public List<Class<?>> getArgumentsClass() throws ClassNotFoundException{
		List<Class<?>> argClass = new ArrayList<Class<?>>();
		if(StringUtil.isEmpty(argument)){
			return argClass;
		}
		
		JSONArray jsonArray = JSONArray.parseArray(argument);
		for(int i=0;i<jsonArray.size();i++){
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			String className = jsonObject.getString("paraType");
			if(paraTypeMap.containsKey(className)){
				className=paraTypeMap.get(className);
			}
			argClass.add(Class.forName(className));
		}
		
		
		/***
		 * 不用反射，是因为有方法重载的情况
		 */
		/*
		Class<?> t = Class.forName(className);
		Method[] methods = t.getDeclaredMethods();
		for(Method method : methods){
			if(method.getName().equals(methodName)){
				
			}
		}
		 */
		
		return argClass;
	}
   
  

}