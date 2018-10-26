<#import "function.ftl" as func>
<#assign package=model.variables.package>
<#assign class=model.variables.class>
<#assign system=vars.system>
<#assign subtables=model.subTableList>
package ${vars.packagePre}.${system}.model.${package};

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
<#if func.supportFlow(model)>
import ${vars.packagePre}.core.model.WfBaseModel;
<#else>
import ${vars.packagePre}.core.model.BaseModel;
</#if>
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

<#if subtables?size!=0>
    <#list subtables as subtable>
	<#assign vars=subtable.variable>
import ${vars.packagePre}.${system}.model.${vars.package}.${vars.class};
    </#list>
    </#if>

/**
 * 对象功能:${model.tabComment} Model对象
 <#if vars.company?exists>
 * 开发公司:${vars.company}
 </#if>
 <#if vars.developer?exists>
 * 开发人员:${vars.developer}
 </#if>
 * 创建时间:${date?string("yyyy-MM-dd HH:mm:ss")}
 */
public class ${class} extends <#if func.supportFlow(model)>WfBaseModel<#else>BaseModel</#if>{
<#list model.columnList as col>
	// ${col.comment}
	protected ${func.getColType(col.colType)} ${func.convertUnderLine(col.columnName)};
</#list>
<#if func.isSubTableExist( subtables)>
	<#list subtables as table>
	<#assign vars=table.variables>
	//${table.tabComment}列表
	protected List<${vars.class}> ${vars.classVar}List=new ArrayList<${vars.class}>();
	</#list>
</#if>

<#list model.columnList as col>
	<#assign colName=func.convertUnderLine(col.columnName)>
	public void set${colName?cap_first}(${func.getColType(col.colType)} ${colName}){
		this.${colName} = ${colName};
	}
	/**
	 * 返回 ${col.comment}
	 * @return
	 */
	public ${func.getColType(col.colType)} get${colName?cap_first}() {
		return this.${colName};
	}
</#list>
<#if func.isSubTableExist( subtables)>
<#list subtables as table>
	<#assign vars=table.variables>
	public void set${vars.class}List(List<${vars.class}> ${vars.classVar}List){
		this.${vars.classVar}List = ${vars.classVar}List;
	}
	/**
	 * 返回 ${table.tabComment}列表
	 * @return
	 */
	public List<${vars.class}> get${vars.class}List(){
		return this.${vars.classVar}List;
	}
</#list>
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
		<#list model.columnList as col>
		<#assign colName=func.convertUnderLine(col.columnName)>
		.append(this.${colName}, rhs.${colName})
		</#list>
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		<#list model.columnList as col>
		<#assign colName=func.convertUnderLine(col.columnName)>
		.append(this.${colName}) 
		</#list>
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		<#list model.columnList as col>
		<#assign colName=func.convertUnderLine(col.columnName)>
		.append("${colName}", this.${colName}) 
		</#list>
		.toString();
	}
   
  

}