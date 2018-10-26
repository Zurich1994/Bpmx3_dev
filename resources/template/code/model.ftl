<#assign package=table.variable.package>
<#assign class=table.variable.class>
<#assign package=table.variable.package>
<#assign tabComment=table.tableDesc>
<#assign subtables=table.subTableList>
<#assign fieldList=table.fieldList>
<#function getJavaType dataType>
<#assign dbtype=dataType?lower_case>
<#assign rtn>
<#if  dbtype=="number" >
Long
<#elseif (dbtype=="varchar"||dbtype=="clob")  >
String
<#elseif (dbtype=="date")>
java.util.Date
</#if></#assign>
 <#return rtn?trim>
</#function>
package com.hotent.${system}.model.${package};

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
<#if flowKey?exists>
import com.hotent.core.model.WfBaseModel;
<#else>
import com.hotent.core.model.BaseModel;
</#if>
/**
 * 对象功能:${tabComment} Model对象
 */
public class ${class} extends <#if flowKey?exists>WfBaseModel<#else>BaseModel</#if>
{
	//主键
	<#if table.isExternal==0>
	protected Long id;
	<#else>
	protected Long ${table.pkField?lower_case};
	</#if>
	<#if table.isExternal==0>
	<#if table.isMain!=1>
	protected Long refId;
	</#if>
	</#if>
	<#list fieldList as field>
	<#if field.fieldName?lower_case!=table.pkField?lower_case>
	/**
	 *${field.fieldDesc}
	 */
	protected ${getJavaType(field.fieldType)}  ${field.fieldName};
	</#if>
	</#list>
	<#if flowKey?exists&&table.isMain==1>
	protected Long  runId=0L;
	</#if>
	
<#if subtables?size!=0>
	<#list subtables as subtable>
	<#assign vars=subtable.variable>
	/**
	 *${table.tableDesc}列表
	 */
	protected List<${vars.class}> ${vars.classVar}List=new ArrayList<${vars.class}>();
	</#list>
	</#if>
	<#if table.isExternal==0> 
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	<#else>
	public Long get${table.pkField?cap_first}() {
		return ${table.pkField};
	}
	public void set${table.pkField?cap_first}(Long ${table.pkField}) {
		this.${table.pkField} = ${table.pkField};
	}
	</#if>
	<#if table.isMain!=1&& table.isExternal==0>
	public Long getRefId() {
		return refId;
	}
	public void setRefId(Long refId) {
		this.refId = refId;
	}
	</#if>
	
<#list fieldList as field>
	<#if field.fieldName?lower_case!=table.pkField?lower_case>
	public void set${(field.fieldName)?cap_first}(${getJavaType(field.fieldType)} ${field.fieldName}) 
	{
		this.${field.fieldName} = ${field.fieldName};
	}
	/**
	 * 返回 ${field.fieldDesc}
	 * @return
	 */
	public ${getJavaType(field.fieldType)} get${(field.fieldName)?cap_first}() 
	{
		return this.${field.fieldName};
	}
	</#if>
</#list>
<#if subtables?exists && subtables?size!=0>
<#list subtables as subtable>
<#assign vars=subtable.variable>
	public void set${vars.classVar?cap_first}List(List<${vars.class}> ${vars.classVar}List) 
	{
		this.${vars.classVar}List = ${vars.classVar}List;
	}
	/**
	 * 返回 ${table.tableDesc}列表
	 * @return
	 */
	public List<${vars.class}> get${vars.classVar?cap_first}List() 
	{
		return this.${vars.classVar}List;
	}
</#list>
</#if>
	<#if flowKey?exists&&table.isMain==1>
	public Long getRunId() {
		return runId;
	}
	public void setRunId(Long runId) {
		this.runId = runId;
	}
   	</#if>
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof ${class})) 
		{
			return false;
		}
		${class} rhs = (${class}) object;
		return new EqualsBuilder()
		<#if table.isExternal==0>
		.append(this.id, rhs.id)
		<#list fieldList as field>
		.append(this.${field.fieldName}, rhs.${field.fieldName})
		</#list>
		<#else>
		.append(this.${table.pkField},rhs.${table.pkField})
		<#list fieldList as field>
		<#if field.fieldName?lower_case!=table.pkField?lower_case>
		.append(this.${field.fieldName}, rhs.${field.fieldName})
		</#if>
		</#list>
		</#if>
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		<#if table.isExternal==0>
		.append(this.id)
		<#list fieldList as field>
		.append(this.${field.fieldName}) 
		</#list>
		<#else>
		.append(this.${table.pkField})
		<#list fieldList as field>
		<#if field.fieldName?lower_case!=table.pkField?lower_case>
		.append(this.${field.fieldName}) 
		</#if>
		</#list>
		</#if>
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		<#if table.isExternal==0>
		.append("id",this.id)
		<#list fieldList as field>
		.append("${field.fieldName}", this.${field.fieldName}) 
		</#list>
		<#else>
		.append("${table.pkField}",this.${table.pkField})
		<#list fieldList as field>
		<#if field.fieldName?lower_case!=table.pkField?lower_case>
		.append("${field.fieldName}", this.${field.fieldName}) 
		</#if>
		</#list>
		</#if>
		.toString();
	}

}