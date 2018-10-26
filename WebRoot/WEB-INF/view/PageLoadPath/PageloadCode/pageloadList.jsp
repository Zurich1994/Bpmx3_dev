<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>页面加载管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">页面加载管理列表</span>
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
		    <display:table name="pageloadList" id="pageloadItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${pageloadItem.id}">
				</display:column>
				<display:column property="formname" title="表单名称" sortable="true" sortName="F_FORMNAME"></display:column>
				<display:column property="defID" title="流程ID" sortable="true" sortName="F_DEFID"></display:column>
				<display:column property="nodeID" title="节点ID" sortable="true" sortName="F_NODEID"></display:column>
				<display:column property="element" title="页面元素" sortable="true" sortName="F_ELEMENT"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${pageloadItem.id}" class="link del"><span></span>删除</a>
					<a href="edit.ht?id=${pageloadItem.id}" class="link edit"><span></span>编辑</a>
					<a href="get.ht?id=${pageloadItem.id}" class="link detail"><span></span>明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="pageloadItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


