<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>系统配置参数表管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">系统配置参数表管理列表</span>
			</div>
		
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="sysPropertyList" id="sysPropertyItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
			
				<display:column property="name" title="名称" sortable="true" sortName="NAME_"></display:column>
				<display:column property="alias" title="别名" sortable="true" sortName="ALIAS_"></display:column>
				<display:column property="value" title="值" sortable="true" sortName="VALUE_"></display:column>
				<display:column property="group" title="分组" sortable="true" sortName="GROUP_"></display:column>
				<display:column property="sn" title="排序" sortable="true" sortName="SN_"></display:column>
				<display:column title="管理" media="html" style="width:180px">
					<a href="edit.ht?id=${sysPropertyItem.id}" class="link edit">编辑</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="sysPropertyItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


