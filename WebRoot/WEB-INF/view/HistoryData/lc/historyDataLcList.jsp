<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>历史数据信息表管理</title>
<%@include file="/commons/include/get.jsp" %>
	<script type="text/javascript">
		function goBack(){
			var processId = $("#processId").val();
			window.location.href = "${ctx}/ImportData/lc/importData/edit.ht?processId="+processId;
		}
	</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">历史数据信息表管理列表</span>
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
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link back" onclick="goBack()" ><span></span>返回</a></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
				<input id="processId" value="${processId }"/>
					<div class="row">
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="historyDataLcList" id="historyDataLcItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${historyDataLcItem.id}">
				</display:column>
				<display:column property="historyDataId" title="历史数据编号" sortable="true" sortName="F_HISTORYDATAID"></display:column>
				<display:column property="processId" title="流程编号" sortable="true" sortName="F_PROCESSID"></display:column>
				<display:column property="timeType" title="时间类别" sortable="true" sortName="F_TIMETYPE"></display:column>
				<display:column property="occurenceAmount" title="发生量" sortable="true" sortName="F_OCCURENCEAMOUNT"></display:column>
				<display:column property="occurenceTime" title="发生时间" sortable="true" sortName="F_OCCURENCETIME"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${historyDataLcItem.id}" class="link del"><span></span>删除</a>
					<a href="edit.ht?id=${historyDataLcItem.id}" class="link edit"><span></span>编辑</a>
					<a href="get.ht?id=${historyDataLcItem.id}" class="link detail"><span></span>明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="historyDataLcItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


