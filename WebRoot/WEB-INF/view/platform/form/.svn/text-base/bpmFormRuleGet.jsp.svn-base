<%--
	time:2012-01-11 10:53:15
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>表单验证规则明细</title>
	<%@include file="/commons/include/getById.jsp" %>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">表单验证规则详细信息</span>
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
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<th width="20%">规则名:</th>
					<td>${bpmFormRule.name}</td>
				</tr>
				<tr>
					<th width="20%">规则:</th>
					<td>${bpmFormRule.rule}</td>
				</tr>
					<tr>
					<th width="20%">错误提示信息:</th>
					<td>${bpmFormRule.tipInfo}</td>
				</tr>
				<tr>
					<th width="20%">描述:</th>
					<td>${bpmFormRule.memo}</td>
				</tr>
			</table>
		</div>
</div>

</body>
</html>
