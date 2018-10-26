<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>projectInfo管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">projectInfo管理列表</span>
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
		    <display:table name="projectinfoList" id="projectinfoItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${projectinfoItem.id}">
				</display:column>
				<display:column property="projectName" title="项目名称" sortable="true" sortName="F_PROJECTNAME"></display:column>
				<display:column property="description" title="description" sortable="true" sortName="F_DESCRIPTION"></display:column>
				<display:column  title="开始时间" sortable="true" sortName="F_STIME">
					<fmt:formatDate value="${projectinfoItem.stime}" pattern="yyyy-MM-dd"/>
				</display:column>
				<display:column  title="结束时间" sortable="true" sortName="F_ETIME">
					<fmt:formatDate value="${projectinfoItem.etime}" pattern="yyyy-MM-dd"/>
				</display:column>
				<display:column  title="实际开始时间" sortable="true" sortName="F_ASTIME">
					<fmt:formatDate value="${projectinfoItem.astime}" pattern="yyyy-MM-dd"/>
				</display:column>
				<display:column  title="实际结束时间" sortable="true" sortName="F_AETIME">
					<fmt:formatDate value="${projectinfoItem.aetime}" pattern="yyyy-MM-dd"/>
				</display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${projectinfoItem.id}" class="link del"><span></span>删除</a>
					<a href="edit.ht?id=${projectinfoItem.id}" class="link edit"><span></span>编辑</a>
					<a href="get.ht?id=${projectinfoItem.id}" class="link detail"><span></span>明细</a>
					<a href="design.ht?id=${projectinfoItem.id}" class="link design"><span></span>进度管理</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="projectinfoItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


