<%--
	time:2012-02-20 09:25:49
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>法定假期设置明细</title>
	<%@include file="/commons/include/getById.jsp" %>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">法定假期设置详细信息</span>
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
						<th width="20%">假日名称:</th>
						<td>${vacation.name}</td>
					</tr>
					<tr>
						<th width="20%">年份:</th>
						<td>${vacation.years}</td>
					</tr>
					<tr>
						<th width="20%">开始时间:</th>
						<td>${vacation.sTime}</td>
					</tr>
					<tr>
						<th width="20%">结束时间:</th>
						<td>${vacation.eTime}</td>
					</tr>
				</table>
			
		</div>
</div>

</body>
</html>
