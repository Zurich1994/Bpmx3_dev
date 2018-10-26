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


<#function getPk model>
<#assign rtn><#if (model.pkModel??) >${model.pkModel.columnName}<#else>"id"</#if></#assign>
 <#return rtn>
</#function>

<#--获取主键类型-->
<#function getPkType model>
<#list model.columnList as col>
<#if col.isPK>
<#if (col.colType=="Integer")><#assign rtn>"Long"</#assign><#return rtn>
<#else><#assign pkType=col.colType ></#if>
</#if>
</#list>
<#assign rtn>${pkType}</#assign>
<#return rtn>
</#function>

<#--获取外键类型 没有则返回Long-->
<#function getFkType model>
<#assign fk=model.foreignKey>
<#list model.columnList as col>
<#if (col.columnName?lower_case)==(fk?lower_case)>
	<#if (col.colType=="Integer")><#assign rtn>Long</#assign><#return rtn><#else><#assign rtn>${col.colType}</#assign><#return rtn></#if>
</#if>
</#list>
<#assign rtn>Long</#assign><#return rtn>
</#function>

<#function getPkVar model>
<#assign pkModel=model.pkModel>
<#assign rtn><#if (model.pkModel??) ><#noparse>${</#noparse>${model.pkModel.columnName}<#noparse>}</#noparse><#else>"id"</#if></#assign>
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

<#function supportFlow model>
	<#assign rtn=false>
	<#if model.variables.flowKey?exists>
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
<#-- 解释字段类型 -->
<#function getColType type>
	<#if (type=="Integer")>
		<#return "Long">
	<#elseif type=="java.util.Date"||type=="timestamp">
		<#return "Date">
	<#else> <#return type>
	</#if>	
</#function>

<#function getJdbcDataType dataType>
<#assign dbtype=dataType?lower_case>
<#assign rtn>
<#if  dbtype?ends_with("int") || (dbtype=="double") || (dbtype=="float") || (dbtype=="decimal") || dbtype?ends_with("number")||dbtype?starts_with("numeric") >
FieldPool.DATATYPE_NUMBER
<#elseif (dbtype?index_of("char")>-1)  >
FieldPool.DATATYPE_VARCHAR
<#elseif (dbtype=="date") || (dbtype=="datetime") >
FieldPool.DATATYPE_DATE
<#elseif (dbtype?index_of("timestamp")>-1)>
FieldPool.DATATYPE_DATE
<#elseif (dbtype?ends_with("text") || dbtype?ends_with("clob")) >
FieldPool.DATATYPE_CLOB
</#if></#assign>
 <#return rtn?trim>
</#function>



