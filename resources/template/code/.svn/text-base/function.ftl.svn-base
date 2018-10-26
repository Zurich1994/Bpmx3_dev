<#--获取查询数据类型-->
<#function getDataType colType start>
<#if (colType=="long") > <#return "L">
<#elseif (colType=="int")><#return "N">
<#elseif (colType=="double")><#return "BD">
<#elseif (colType=="Short")><#return "SN">
<#elseif (colType=="Date" && start=="1")><#return "DL">
<#elseif (colType=="Date" && start=="0")><#return "DG">
<#else><#return "SL"></#if>
</#function>

<#--将字符串 user_id 转换为 类似userId-->
<#function convertUnderLine field>
<#assign rtn><#list field?split("_") as x><#if (x_index==0)><#if x?length==1>${x?upper_case}<#else>${x?lower_case}</#if><#else>${x?lower_case?cap_first}</#if></#list></#assign>
 <#return rtn>
</#function>


<#function getPk table>
<#assign rtn><#if (table.pkModel??) >${table.pkModel.columnName}<#else>id</#if></#assign>
 <#return rtn>
</#function>

<#--获取主键类型-->
<#function getPkType table>
<#list table.columnList as col>
<#if col.isPK>
<#if (col.colType=="Integer")><#assign rtn>"Long"</#assign><#return rtn>
<#else><#assign pkType=col.colType ></#if>
</#if>
</#list>
<#assign rtn>${pkType}</#assign>
<#return rtn>
</#function>

<#--获取外键类型 没有则返回Long-->
<#function getFkType table>
<#assign fk=table.foreignKey>
<#list table.fieldList as col>
<#if (col.fieldName?lower_case)==(fk?lower_case)>
	<#if (col.fieldType=="Integer")><#assign rtn>Long</#assign><#return rtn><#else><#assign rtn>${col.fieldType}</#assign><#return rtn></#if>
</#if>
</#list>
<#assign rtn>Long</#assign><#return rtn>
</#function>

<#function getPkVar table>
<#assign pkField=table.pkField>
<#assign rtn><#if (table.pkModel??) ><#noparse>${</#noparse>${table.pkModel.columnName}<#noparse>}</#noparse><#else>"id"</#if></#assign>
 <#return rtn>
</#function>


<#function getJdbcType dataType>
<#assign dbtype=dataType?lower_case>
<#assign rtn>
<#if  dbtype?ends_with("int") || (dbtype=="double") || (dbtype=="float") || (dbtype=="decimal") || dbtype?ends_with("number")||dbtype?starts_with("numeric") >
NUMERIC
<#elseif (dbtype?index_of("char")>-1)  >
VARCHAR
<#elseif (dbtype=="date") || (dbtype=="datetime") >
DATE
<#elseif (dbtype?index_of("timestamp")>-1)>
TIMESTAMP
<#elseif (dbtype?ends_with("text") || dbtype?ends_with("clob")) >
CLOB
</#if></#assign>
 <#return rtn?trim>
</#function>

<#function isSubTableExist subtables>
<#assign rtn=false>
<#if subtables?exists && subtables?size!=0>
	<#assign rtn=true>
</#if>
<#return rtn>
</#function>

<#function supportFlow table>
	<#assign rtn=false>
	<#if table.variable.flowKey?exists>
		<#assign rtn=true>
	</#if>
	<#return rtn>
</#function>

<#function getComment table>
	<#assign comment=table.tabComment>
	<#if !comment?exists>
		<#assign comment=table.tableName>
	</#if>
	<#return comment>
</#function>




