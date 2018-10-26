<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>redirectionresource管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">redirectionresource管理列表</span>
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
						<span class="label">redirectionno:</span><input type="text" name="Q_redirectionno_L"  class="inputText" />
						<span class="label">url:</span><input type="text" name="Q_url_S"  class="inputText" />
						<span class="label">name:</span><input type="text" name="Q_name_S"  class="inputText" />
						<span class="label">alias:</span><input type="text" name="Q_alias_S"  class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="redirectionresourceList" id="redirectionresourceItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${redirectionresourceItem.redirectionno}">
				</display:column>
				<display:column property="url" title="url" sortable="true" sortName="URL"></display:column>
				<display:column property="name" title="name" sortable="true" sortName="NAME"></display:column>
				<display:column property="alias" title="alias" sortable="true" sortName="ALIAS"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${redirectionresourceItem.redirectionno}" class="link del"><span></span>删除</a>
					<a href="edit.ht?id=${redirectionresourceItem.redirectionno}" class="link edit"><span></span>编辑</a>
					<a href="get.ht?id=${redirectionresourceItem.redirectionno}" class="link detail"><span></span>明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="redirectionresourceItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


