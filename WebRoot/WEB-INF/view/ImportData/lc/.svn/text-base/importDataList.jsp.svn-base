<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>导入数据管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">导入数据管理列表</span>
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
		    <display:table name="importDataList" id="importDataItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${importDataItem.id}">
				</display:column>
				<display:column property="year_year_date" title="年年" sortable="true" sortName="F_YEAR_YEAR_DATE"></display:column>
				<display:column property="year_month_date" title="年月" sortable="true" sortName="F_YEAR_MONTH_DATE"></display:column>
				<display:column property="month_day_date" title="月日" sortable="true" sortName="F_MONTH_DAY_DATE"></display:column>
				<display:column property="day_hour_date" title="日时" sortable="true" sortName="F_DAY_HOUR_DATE"></display:column>
				<display:column property="hour_minute_date" title="时分" sortable="true" sortName="F_HOUR_MINUTE_DATE"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${importDataItem.id}" class="link del"><span></span>删除</a>
					<a href="edit.ht?id=${importDataItem.id}" class="link edit"><span></span>编辑</a>
					<a href="get.ht?id=${importDataItem.id}" class="link detail"><span></span>明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="importDataItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


