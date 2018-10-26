<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>原子操作-更新管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">原子操作-更新管理列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link add" href="edit.ht"><span></span>添加</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link update" id="btnUpd" action="edit.ht"><span></span>修改</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link del"  action="del.ht"><span></span>删除</a></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<div class="row">
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="formUpdateList" id="formUpdateItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${formUpdateItem.id}">
				</display:column>
				<display:column property="name" title="名称" sortable="true" sortName="F_NAME"></display:column>
				<display:column property="alias" title="别名" sortable="true" sortName="F_ALIAS"></display:column>
				<display:column property="objName" title="查询对象名称" sortable="true" sortName="F_OBJNAME"></display:column>
				<display:column property="needpage" title="是否分页" sortable="true" sortName="F_NEEDPAGE"></display:column>
				<display:column property="conditionfield" title="条件字段" sortable="true" sortName="F_CONDITIONFIELD"></display:column>
				<display:column property="updatefield" title="更新字段" sortable="true" sortName="F_UPDATEFIELD"></display:column>
				<display:column property="dsname" title="数据源名称" sortable="true" sortName="F_DSNAME"></display:column>
				<display:column property="dsalias" title="数据源别名" sortable="true" sortName="F_DSALIAS"></display:column>
				<display:column property="pagesize" title="分页条数" sortable="true" sortName="F_PAGESIZE"></display:column>
				<display:column property="isTable" title="是否为表" sortable="true" sortName="F_ISTABLE"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${formUpdateItem.id}" class="link del"><span></span>删除</a>
					<a href="edit.ht?id=${formUpdateItem.id}" class="link edit"><span></span>编辑</a>
					<a href="get.ht?id=${formUpdateItem.id}" class="link detail"><span></span>明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="formUpdateItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


