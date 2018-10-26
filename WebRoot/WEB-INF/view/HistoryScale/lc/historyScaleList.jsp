<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>历史数据发生比例表管理</title>
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
				<span class="tbar-label">历史数据发生比例表管理列表</span>
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
					<div class="group"><a class="link back"  onclick="goBack()"><span></span>返回</a></div>
				</div>	
			</div>
			<div class="panel-search">
			<input id="processId" name="processId" type="hidden" id="processId" value="${processId }"/>
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
		    <display:table name="historyScaleList" id="historyScaleItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${historyScaleItem.id}">
				</display:column>
				<%-- 
				<display:column property="historyProportionId" title="历史比例编号" sortable="true" sortName="F_HISTORYPROPORTIONID"></display:column>
				--%>
				<display:column property="processtId" title="流程编号" sortable="true" sortName="F_PROCESSTID"></display:column>
				<display:column property="proportionTimeType" title="比例时间类别" sortable="true" sortName="F_PROPORTIONTIMETYPE">
				</display:column>	
				<display:column property="proportion" title="比例" sortable="true" sortName="F_PROPORTION"></display:column>
			    <display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${historyScaleItem.id}" class="link del"><span></span>删除</a>
					<a href="edit.ht?id=${historyScaleItem.id}" class="link edit"><span></span>编辑</a>
					<a href="get.ht?id=${historyScaleItem.id}" class="link detail"><span></span>明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="historyScaleItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


