<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>桌面个人栏目明细</title>
	<%@include file="/commons/include/getById.jsp" %>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
			<span class="tbar-label">桌面个人栏目详细信息</span>
		</div>
		<c:if test="${canReturn==0}">
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link back" href="list.ht"><span></span>返回</a></div>
			</div>
		</div>
		</c:if>
	</div>
	<div class="panel-body">
		<div class="panel-detail">
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<th width="20%">用户ID:</th>
					<td>${desktopMycolumn.userId}</td>
				</tr>
				<tr>
					<th width="20%">布局ID:</th>
					<td>${desktopMycolumn.layoutId}</td>
				</tr>
				<tr>
					<th width="20%">栏目ID:</th>
					<td>${desktopMycolumn.columnId}</td>
				</tr>
				<tr>
					<th width="20%">列:</th>
					<td>${desktopMycolumn.col}</td>
				</tr>
				<tr>
					<th width="20%">序号:</th>
					<td>${desktopMycolumn.sn}</td>
				</tr>
			</table>
		</div>
	</div>
</div>
</body>
</html>
