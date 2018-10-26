<#import "function.ftl" as func>
<#assign comment=model.tabComment>
<#assign class=model.variables.class>
<#assign package=model.variables.package>
<#assign comment=model.tabComment>
<#assign classVar=model.variables.classVar>
<#assign system=vars.system>
<#assign commonList=model.commonList>
<#assign pkModel=model.pkModel>
<#assign pk=func.getPk(model) >
<#assign pkVar=func.convertUnderLine(pk) >
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>${comment }管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">${comment }管理列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link add" href="edit.ht"><span></span>添加</a></div>
				</div>	
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="${classVar}List" id="${classVar}Item" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="<#noparse>${checkAll}</#noparse>" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="${pkVar}" value="<#noparse>${</#noparse>${classVar}Item.${pkVar}}">
				</display:column>
				<#list model.commonList as col>
				<#assign colName=func.convertUnderLine(col.columnName)>
				<#if (col.colType=="java.util.Date")>
				<display:column  title="${col.getComment()}" sortable="true" sortName="${col.columnName}">
					<fmt:formatDate value="<#noparse>${</#noparse>${classVar}Item.${colName}}" pattern="yyyy-MM-dd"/>
				</display:column>
				<#elseif (col.length > 256) >
				<display:column property="${colName}" title="${col.getComment()}" sortable="true" sortName="${col.columnName}" maxLength="80"></display:column>
				<#else>
				<display:column property="${colName}" title="${col.getComment()}" sortable="true" sortName="${col.columnName}"></display:column>
				</#if>
				</#list>
				<display:column title="管理" media="html" style="width:220px">
					<a href='get.ht?${pkVar}=<#noparse>${</#noparse>${classVar}Item.${pkVar}}&runId=<#noparse>${</#noparse>${classVar}Item.runId}' class="link detail">明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="${classVar}Item"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


