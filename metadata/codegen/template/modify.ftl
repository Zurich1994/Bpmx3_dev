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

<table class="table-detail" cellpadding="0" cellspacing="0" border="0" type="main">
	<#list commonList as col>
	<#assign colName=func.convertUnderLine(col.columnName)>
	<#if colName!=flowRunId>
	<#if (col.colType=="java.util.Date") >
	<tr>
		<th width="20%">${col.comment}: <#if (col.isNotNull) > <span class="required">*</span></#if></th>
		<td><input type="text" id="${colName}" name="${colName}" value="<fmt:formatDate value='<#noparse>${</#noparse>${classVar}.${colName}}' pattern='yyyy-MM-dd'/>" class="inputText date" validate="{date:true<#if col.isNotNull>,required:true</#if>}" /></td>
	</tr>
	<#else>
	<tr>
		<th width="20%">${col.comment}: <#if (col.isNotNull) > <span class="required">*</span></#if></th>
		<td><input type="text" id="${colName}" name="${colName}" value="<#noparse>${</#noparse>${classVar}.${colName}}"  class="inputText" validate="{<#if col.isNotNull>required:true<#else>required:false</#if><#if col.colType=='String'&& col.length<1000>,maxlength:${col.length}</#if><#if col.colType=='Integer'|| col.colType=='Long'||col.colType=='Float'>,number:true </#if>}"  /></td>
	</tr>
	</#if>
	</#if>
	</#list>
</table>
<#if subtables?exists && subtables?size != 0>
<#list subtables as table>
<#assign foreignKey=func.convertUnderLine(table.foreignKey) >
<table class="table-grid table-list" cellpadding="1" cellspacing="1" id="${table.variables.classVar}" formtype="form" type="subtable">
	<tr>
		<td colspan="${table.columnList?size-1}">
			<div class="group" align="left">
	   			<a id="btnAdd" class="link add">添加</a>
    		</div>
    		<div align="center">
			${table.tableName } : ${table.tabComment }
    		</div>
		</td>
	</tr>
	<tr>
		<#list table.columnList as col>
		<#assign colName=func.convertUnderLine(col.columnName?lower_case)>
		<#if !(col.isPK)&& colName?lower_case!=(foreignKey)?lower_case>							                              
		<th>${col.comment}</th>
		</#if>									
		</#list>
	</tr>
	<c:forEach items="<#noparse>${</#noparse>${table.variables.classVar}List}" var="${table.variables.classVar}Item" varStatus="status">
	    <tr type="subdata">
	        <#list table.columnList as col>												
		    <#assign colName=func.convertUnderLine(col.columnName)>
		    <#if ( !col.isPK && colName?lower_case!=foreignKey?lower_case)>
		    <#if (col.colType=="java.util.Date")>
			<td style="text-align: center" name="${colName}"><fmt:formatDate value='<#noparse>${</#noparse>${table.variables.classVar}Item.${colName}}' pattern='yyyy-MM-dd'/></td>								
		    <#else >
		    <td style="text-align: center" name="${colName}"><#noparse>${</#noparse>${table.variables.classVar}Item.${colName}}</td>
		    </#if>
		  	</#if>
		    </#list>
		     <#list table.columnList as col>												
		    <#assign colName=func.convertUnderLine(col.columnName)>
		    <#if ( !col.isPK && colName?lower_case!=foreignKey?lower_case)>
		    <#if (col.colType=="java.util.Date")>
		    <input type="hidden" name="${colName}" value="<fmt:formatDate value='<#noparse>${</#noparse>${table.variables.classVar}Item.${colName}}' pattern='yyyy-MM-dd'/>" class="inputText date"/>
		    <#else >
			<input type="hidden" name="${colName}" value="<#noparse>${</#noparse>${table.variables.classVar}Item.${colName}}"/>
		    </#if>
		  	</#if>
		    </#list>
	    </tr>
	</c:forEach>
	<tr type="append" style="display:none;">
    <#list table.columnList as col>												
    <#assign colName=func.convertUnderLine(col.columnName)>
    <#assign foreignKey=func.convertUnderLine(table.foreignKey) >
	<#if ( !col.isPK && colName?lower_case!=foreignKey?lower_case)>
	<#if (col.colType=="java.util.Date")>
		<td style="text-align: center" name="${colName}"></td>								
    <#else >
    	<td style="text-align: center" name="${colName}"></td>
    </#if>
  	</#if>
    </#list>
    	<#list table.columnList as col>												
    <#assign colName=func.convertUnderLine(col.columnName)>
    <#assign foreignKey=func.convertUnderLine(table.foreignKey) >
	<#if ( !col.isPK && colName?lower_case!=foreignKey?lower_case)>
	<#if (col.colType=="java.util.Date")>
    	<input type="hidden" name="${colName}" value="" class="inputText date"/>
    <#else >
    	<input type="hidden" name="${colName}" value=""/>
    </#if>
  	</#if>
    </#list>
	</tr>
</table>
</#list>
</#if>
<input type="hidden" name="${pkVar}" value="<#noparse>${</#noparse>${classVar}.${pkVar}<#noparse>}</#noparse>" />
<#if subtables?exists && subtables?size != 0>
<#list subtables as table>
<#assign foreignKey=func.convertUnderLine(table.foreignKey) >
<form id="${table.variables.classVar}Form" style="display:none">
	<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
		<#list table.columnList as col>	
		<#assign colName=func.convertUnderLine(col.columnName)>
		<#if !col.isPK && colName?lower_case!=foreignKey?lower_case>
		<#if (col.colType=="java.util.Date")>
		<tr>
			<th width="20%">${col.comment}: <#if col.isNotNull> <span class="required">*</span></#if></th>
			<td><input type="text" name="${colName}" value="" class="inputText date" validate="{date:true<#if col.isNotNull>,required:true</#if>}"/></td>
		</tr>
		
		<#else>
		<tr>
			<th width="20%">${col.comment}: <#if col.isNotNull> <span class="required">*</span></#if></th>
			<td><input type="text" name="${colName}" value=""  class="inputText" validate="{<#if col.isNotNull>required:true<#else>required:false</#if><#if col.colType=='String' && col.length<1000>,maxlength:${col.length}</#if><#if col.colType=='Integer'|| col.colType=='Long'||col.colType=='Float'>,number:true </#if>}"/></td>
		</tr>
		</#if>
		</#if>
		</#list>
	</table>
</form>
</#list>
</#if>