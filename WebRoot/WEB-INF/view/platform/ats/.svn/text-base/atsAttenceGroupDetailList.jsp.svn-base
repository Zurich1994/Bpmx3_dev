<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>考勤组明细管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">考勤组明细管理列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link add" href="edit.ht"><span></span>添加</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link update" id="btnUpd" action="edit.ht"><span></span>修改</a></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<div class="row">
						<span class="label">考勤组:</span><input type="text" name="Q_groupId_SL"  class="inputText" />
						<span class="label">考勤档案:</span><input type="text" name="Q_fileId_SL"  class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="atsAttenceGroupDetailList" id="atsAttenceGroupDetailItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${atsAttenceGroupDetailItem.id}">
				</display:column>
				<display:column property="groupId" title="考勤组" sortable="true" sortName="GROUP_ID"></display:column>
				<display:column property="fileId" title="考勤档案" sortable="true" sortName="FILE_ID"></display:column>
				<display:column title="管理" media="html"  class="rowOps">
					<a href="del.ht?id=${atsAttenceGroupDetailItem.id}" class="link del">删除</a>
					<a href="edit.ht?id=${atsAttenceGroupDetailItem.id}" class="link edit">编辑</a>
					<a href="get.ht?id=${atsAttenceGroupDetailItem.id}" class="link detail">明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="atsAttenceGroupDetailItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


