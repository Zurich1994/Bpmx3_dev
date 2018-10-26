<%--
	time:2012-03-10 10:18:37
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>系统分类键定义明细</title>
	<%@include file="/commons/include/getById.jsp" %>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">系统分类键定义详细信息</span>
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
							<th width="20%">系统分类Key  该值唯一:</th>
							<td>${sysTypeKey.typeKey}</td>
						</tr>
						<tr>
							<th width="20%">系统分类名称:</th>
							<td>${sysTypeKey.typeName}</td>
						</tr>
						<tr>
							<th width="20%">序号:</th>
							<td>${sysTypeKey.sn}</td>
						</tr>
					</table>
				</div>
		</div>
</div>

</body>
</html>
