<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>容器表管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">容器表管理列表</span>
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
						<span class="label">容器名:</span><input type="text" name="Q_containername_S"  class="inputText" />
						<span class="label">类型:</span><input type="text" name="Q_Type_S"  class="inputText" />
						<span class="label">可计算性:</span><input type="text" name="Q_computability_S"  class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="containerList" id="containerItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${containerItem.id}">
				</display:column>
				<display:column property="containername" title="容器名" sortable="true" sortName="F_CONTAINERNAME"></display:column>
				<display:column property="type" title="类型" sortable="true" sortName="F_TYPE"></display:column>
				<display:column property="computability" title="可计算性" sortable="true" sortName="F_COMPUTABILITY"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${containerItem.id}" class="link del"><span></span>删除</a>
					<a href="edit.ht?id=${containerItem.id}" class="link edit"><span></span>编辑</a>
					<a href="assemble.ht?id=${containerItem.id}" class="link edit"><span></span>组装</a>
					<a href="get.ht?id=${containerItem.id}" class="link detail"><span></span>明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="containerItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


