<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>历史数据流程依赖表管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">历史数据流程依赖表管理列表</span>
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
		    <display:table name="historyFlowRelyLcList" id="historyFlowRelyLcItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${historyFlowRelyLcItem.id}">
				</display:column>
				<display:column property="relyId" title="依赖编号" sortable="true" sortName="F_RELYID"></display:column>
				<display:column property="processId1" title="流程编号1" sortable="true" sortName="F_PROCESSID1"></display:column>
				<display:column property="processId2" title="流程编号2" sortable="true" sortName="F_PROCESSID2"></display:column>
				<display:column property="relyTimeType" title="依赖时间类别" sortable="true" sortName="F_RELYTIMETYPE"></display:column>
				<display:column property="relyNumber" title="依赖参数" sortable="true" sortName="F_RELYNUMBER"></display:column>
				<display:column property="relyOccurTime" title="依赖发生时间" sortable="true" sortName="F_RELYOCCURTIME"></display:column>
				<display:column property="relyEndTime" title="依赖结束时间" sortable="true" sortName="F_RELYENDTIME"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${historyFlowRelyLcItem.id}" class="link del"><span></span>删除</a>
					<a href="edit.ht?id=${historyFlowRelyLcItem.id}" class="link edit"><span></span>编辑</a>
					<a href="get.ht?id=${historyFlowRelyLcItem.id}" class="link detail"><span></span>明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="historyFlowRelyLcItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


