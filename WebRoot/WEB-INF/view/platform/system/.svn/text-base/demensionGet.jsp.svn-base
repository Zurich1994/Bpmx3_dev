<%--
	time:2011-11-08 12:04:22
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>维度信息明细</title>
	<%@include file="/commons/include/getById.jsp" %>
	<%@include file="/commons/include/form.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">维度信息明细</span>
			</div>
			<c:if test="${canReturn==0}">
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link back" href="list.ht"><span></span>返回</a>
					</div>
				</div>
			</div>
			</c:if>
		</div>
		<div class="panel-body">
			<form id="demensionForm" method="post" action="save.ht">

				<table class="table-detail" cellpadding="0" cellspacing="0"
					border="0">
					<tr>
						<th width="25%">维度名称:</th>
						<td>${demension.demName}</td>
					</tr>
					<tr height="40px">
						<th width="20%">维度描述:</th>
						<td>${demension.demDesc}</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>
