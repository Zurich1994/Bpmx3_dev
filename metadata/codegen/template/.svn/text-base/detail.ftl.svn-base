<#import "function.ftl" as func>
<#assign class=model.variables.class>
<#assign tabcomment=model.tabComment>
<#assign classVar=model.variables.classVar>
<#assign system=vars.system>
<#assign package=model.variables.package>
<#assign commonList=model.commonList>
<#assign pk=func.getPk(model) >
<#assign pkVar=func.convertUnderLine(pk) >
<#assign subtables=model.subTableList>
<#assign flowRunId="">
<#if model.variables.flowDefKey?exists>
<#assign flowRunId=func.convertUnderLine(model.variables.flowRunId)>
</#if>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>

<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="<#noparse>${pageContext.request.contextPath}</#noparse>"/>
<script type="text/javascript" src="<#noparse>${ctx}</#noparse>/js/hotent/scriptMgr.js"></script>
<script type="text/javascript">
	function afterOnload(){
		var afterLoadJs=[
		     			'<#noparse>${ctx}</#noparse>/js/hotent/formdata.js',
		     			'<#noparse>${ctx}</#noparse>/js/hotent/subform.js'
		 ];
		ScriptMgr.load({
			scripts : afterLoadJs
		});
	}
</script>
<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
	<#list commonList as col> 
	<#assign colName=func.convertUnderLine(col.columnName)>
	
	<#if colName!=flowRunId >
	<tr>
		<th width="20%">${col.comment}:</th>
		<#if col.colType=="java.util.Date">
		<td>
		<fmt:formatDate value="<#noparse>${</#noparse>${classVar}.${colName}}" pattern="yyyy-MM-dd"/>
		</td>
		<#else>
		<td><#noparse>${</#noparse>${classVar}.${colName}}</td>
		</#if>
	</tr>
	</#if>
	</#list>
</table>
<#if subtables?exists && subtables?size != 0>
<#list subtables as table>
<#assign foreignKey=func.convertUnderLine(table.foreignKey) >
<table class="table-grid table-list" cellpadding="1" cellspacing="1">
	<tr>
		<td colspan="${table.columnList?size-2}" style="text-align: center">${table.tableName } :${table.tabComment }</td>
	</tr>
	<tr>
		<#list table.columnList as col>
		<#assign colName=func.convertUnderLine(col.columnName?lower_case)>
		<#if !(col.isPK) && colName?lower_case!=foreignKey?lower_case>							                              
		<th>${col.comment}</th>
		</#if>									
		</#list>
	</tr>	
	<c:forEach items="<#noparse>${</#noparse>${table.variables.classVar}List}" var="${table.variables.classVar}Item" varStatus="status">
		<tr>
			<#list table.columnList as col>												
			<#assign colName=func.convertUnderLine(col.columnName)>
			<#if (col.isPK)>
				<input type="hidden" id="${colName}" name="${colName}" value="<#noparse>${</#noparse>${table.variables.classVar}Item.${colName}}"  class="inputText"/>
			<#elseif (col.colType=="java.util.Date")>
				<td style="text-align: center"><fmt:formatDate value='<#noparse>${</#noparse>${table.variables.classVar}Item.${colName}}' pattern='yyyy-MM-dd'/></td>								
			<#elseif colName?lower_case!=foreignKey?lower_case>
				<td style="text-align: center"><#noparse>${</#noparse>${table.variables.classVar}Item.${colName}}</td>								
			</#if>
			</#list>
		</tr>
	</c:forEach>
</table>
	</#list>
	</#if>
