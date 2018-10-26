<#import "function.ftl" as func>
<#assign comment=model.tabComment>
<#assign classVar=model.variables.classVar>
# ${comment} 属性
 <#list model.columnList as col>
	<#assign colName=func.convertUnderLine(col.columnName)>
	<#if !col.isPK && colName!=model.foreignKey?lower_case>
${classVar}.${colName}=${col.comment}
	</#if>
</#list>