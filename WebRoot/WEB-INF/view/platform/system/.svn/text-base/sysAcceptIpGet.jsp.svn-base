<%--
	time:2012-02-20 17:35:46
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>可访问地址明细</title>
	<%@include file="/commons/include/getById.jsp" %>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">可访问地址详细信息</span>
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
							<td>${sysAcceptIp.title}</td>
						</tr>
						<tr>
							<th width="20%">开始地址:</th>
							<td>${sysAcceptIp.startIp}</td>
						</tr>
						<tr>
							<th width="20%">结束地址:</th>
							<td>${sysAcceptIp.endIp}</td>
						</tr>				
						<tr>
							<th width="20%">备注:</th>
							<td>${sysAcceptIp.remark}</td>
						</tr>
					</table>
				</div>
		</div>
</div>

</body>
</html>
