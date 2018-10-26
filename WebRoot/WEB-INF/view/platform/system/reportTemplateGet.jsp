<%--
	time:2012-04-12 09:59:47
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>报表模板明细</title>
	<%@include file="/commons/include/getById.jsp" %>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
			<span class="tbar-label">报表模板详细信息</span>
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
						<th width="20%">标题:</th>
						<td>${reportTemplate.title}</td>
					</tr>
					<tr>
						<th width="20%">描述:</th>
						<td>${reportTemplate.descp}</td>
					</tr>
					<tr>
						<th width="20%">报表模块的jasper文件的路径:</th>
						<td>${reportTemplate.reportLocation}</td>
					</tr>
					<tr>
						<th width="20%">创建时间:</th>
						<td><fmt:formatDate value="${reportTemplate.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
					</tr>
					<tr>
						<th width="20%">修改时间:</th>
						<td><fmt:formatDate value="${reportTemplate.updateTime}" pattern="yyyy-MM-dd HH:mm"/></td>
					</tr>
					<tr>
						<th width="20%">标识key:</th>
						<td>${reportTemplate.reportKey}</td>
					</tr>
					<tr>
						<th width="20%">是否缺省:</th>
						<td>${reportTemplate.isDefaultIn}</td>
					</tr>
				</table>
			</div>
	</div>
</div>

</body>
</html>
