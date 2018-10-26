<%--
	time:2012-01-05 12:01:21
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>脚本管理明细</title>
	<%@include file="/commons/include/getById.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">脚本管理详细信息</span>
			</div>
			<c:if test="${canReturn==0}">
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link back" href="../script/list.ht"><span></span>返回</a></div>
				</div>
			</div>
			</c:if>
		</div>
		<div class="panel-body">
				<form id="scriptForm" method="post" action="add2.ht">
					<div class="panel-detail">
						<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
							<tr>
								<th width="20%">名称:</th>
								<td>${script.name}</td>
							</tr>
							<tr>
								<th width="20%">脚本:</th>
								<td>${script.script}</td>
							</tr>
							<tr>
								<th width="20%">脚本分类:</th>
								<td>${script.category}</td>
							</tr>
							<tr>
								<th width="20%">备注:</th>
								<td>${script.memo}</td>
							</tr>
						</table>
					</div>
				</form>
		</div>
	</div>
</body>
</html>
