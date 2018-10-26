<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>业务曲线信息表管理</title>
<%@include file="/commons/include/get.jsp" %>
<script type="text/javascript">
	function goBack(){
		//alert("save");
		var processId = $("#processId").val();
		//alert(processId);
		
	}
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">业务曲线信息表管理列表</span>
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
					<div class="group"><a class="link del"  action="" onclick=""><span></span>返回</a></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
				<input type="text" name="processId" id="processId" value="${processId}">
					<div class="row">
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="businessCurveList" id="businessCurveItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${businessCurveItem.id}">
				</display:column>
				<display:column property="curveDataId" title="曲线数据编号" sortable="true" sortName="F_CURVEDATAID"></display:column>
				<display:column property="curveId" title="曲线编号" sortable="true" sortName="F_CURVEID"></display:column>
				<display:column property="curveType" title="曲线类别" sortable="true" sortName="F_CURVETYPE"></display:column>
				<display:column property="unknownExp" title="待求公式" sortable="true" sortName="F_UNKNOWNEXP"></display:column>
				<display:column property="basicExpression" title="基本公式" sortable="true" sortName="F_BASICEXPRESSION"></display:column>
				
				<display:column property="curveStartTime" title="曲线开始时间" sortable="true" sortName="F_CURVESTARTTIME"></display:column>
				<display:column property="curveEndTime" title="曲线结束时间" sortable="true" sortName="F_CURVEENDTIME"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${businessCurveItem.id}" class="link del"><span></span>删除</a>
					<a href="edit.ht?id=${businessCurveItem.id}" class="link edit"><span></span>编辑</a>
					<a href="get.ht?id=${businessCurveItem.id}" class="link detail"><span></span>明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="businessCurveItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


