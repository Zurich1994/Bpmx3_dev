<#assign class=table.variable.class>
<#assign package=table.variable.package>
<#assign comment=table.tableDesc>
<#assign classVar=table.variable.classVar>
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
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="${classVar}List" id="${classVar}Item" requestURI="getMyDraftJson.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="<#noparse>${checkAll}</#noparse>" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="<#noparse>${</#noparse>${classVar}Item.<#if table.isExternal==0>id<#else>${table.pkField}</#if>}">
				</display:column>
				<#list fieldList as field>
				<#if field.isList==1&&(field.fieldName?lower_case!=table.pkField?lower_case)&&field.isHidden==0>
				<#if (field.fieldType=="date")>
				<display:column  title="${field.fieldDesc}" sortable="true" sortName="<#if table.isExternal==0>F_</#if>${field.fieldName?upper_case}">
					<fmt:formatDate value="<#noparse>${</#noparse>${classVar}Item.${field.fieldName}}" pattern="yyyy-MM-dd"/>
				</display:column>
				<#else>
				<display:column property="${field.fieldName}" title="${field.fieldDesc}" sortable="true" sortName="<#if table.isExternal==0>F_</#if>${field.fieldName?upper_case}"></display:column>
				</#if>
				</#if>
				</#list>
				<display:column title="管理" media="html" style="width:220px">
				   <a href="get.ht?id=<#noparse>${</#noparse>${classVar}Item.<#if table.isExternal==0>id<#else>${table.pkField}</#if>}" class="link detail"><span></span>明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="${classVar}Item"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


