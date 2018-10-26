<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>节点发生时间与发生量表管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">节点发生时间与发生量表管理列表</span>
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
						<span class="label">项目id:</span><input type="text" name="Q_projectId_S"  class="inputText" />
						<span class="label">流程定义id:</span><input type="text" name="Q_defId_S"  class="inputText" />
						<span class="label">节点Id:</span><input type="text" name="Q_nodeId_S"  class="inputText" />
						<span class="label">发生时间与发生量:</span><input type="text" name="Q_timeAndCount_S"  class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="nodetimeandcountList" id="nodetimeandcountItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${nodetimeandcountItem.id}">
				</display:column>
				<display:column property="projectId" title="项目id" sortable="true" sortName="F_PROJECTID"></display:column>
				<display:column property="defId" title="流程定义id" sortable="true" sortName="F_DEFID"></display:column>
				<display:column property="nodeId" title="节点Id" sortable="true" sortName="F_NODEID"></display:column>
				<display:column property="timeAndCount" title="发生时间与发生量" sortable="true" sortName="F_TIMEANDCOUNT"></display:column>
				<display:column property="remain1" title="预留字段1" sortable="true" sortName="F_REMAIN1"></display:column>
				<display:column property="remain2" title="预留字段2" sortable="true" sortName="F_REMAIN2"></display:column>
				<display:column property="remain3" title="预留字段3" sortable="true" sortName="F_REMAIN3"></display:column>
				<display:column property="remain4" title="预留字段4" sortable="true" sortName="F_REMAIN4"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${nodetimeandcountItem.id}" class="link del"><span></span>删除</a>
					<a href="edit.ht?id=${nodetimeandcountItem.id}" class="link edit"><span></span>编辑</a>
					<a href="get.ht?id=${nodetimeandcountItem.id}" class="link detail"><span></span>明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="nodetimeandcountItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


