<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>SYS_DATA_SOURCE_DEF管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">SYS_DATA_SOURCE_DEF管理列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link add" href="edit.ht"><span></span>添加</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link reset" onclick="$.clearQueryForm()"><span></span>重置</a></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<div class="row">
						<span class="label">名称:</span><input type="text" name="Q_name_SL"  class="inputText" value="${param['Q_name_SL'] }" />
						<span class="label">类路径:</span><input type="text" name="Q_classPath_SL"  class="inputText" value="${param['Q_classPath_SL'] }" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="sysDataSourceDefList" id="sysDataSourceDefItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${sysDataSourceDefItem.id}">
				</display:column>
				<display:column property="name" title="名称" sortable="true" sortName="NAME_"></display:column>
				<display:column property="classPath" title="类路径" sortable="true" sortName="CLASS_PATH_"></display:column>
				
				<display:column property="isSystem" title="系统默认" sortable="true" sortName="IS_SYSTEM_"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="edit.ht?id=${sysDataSourceDefItem.id}" class="link edit">编辑</a>
					<a href="del.ht?id=${sysDataSourceDefItem.id}" class="link del">删除</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="sysDataSourceDefItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


