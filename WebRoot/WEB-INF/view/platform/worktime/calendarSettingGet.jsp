<%--
	time:2012-02-20 14:57:32
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>日历设置明细</title>
	<%@include file="/commons/include/getById.jsp" %>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">日历设置详细信息</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link back" href="list.ht"><span></span>返回</a></div>
				</div>
			</div>
		</div>
		<div class="panel-body">
				<div class="panel-detail">
					<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<th width="20%">日历ID:</th>
							<td>${calendarSetting.calendarId}</td>
						</tr>
						<tr>
							<th width="20%">年份:</th>
							<td>${calendarSetting.years}</td>
						</tr>
						<tr>
							<th width="20%">月份:</th>
							<td>${calendarSetting.months}</td>
						</tr>
						<tr>
							<th width="20%">1号:</th>
							<td>${calendarSetting.days}</td>
						</tr>
						<tr>
							<th width="20%">上班类型:</th>
							<td>${calendarSetting.type}</td>
						</tr>
						<tr>
							<th width="20%">workTimeId:</th>
							<td>${calendarSetting.workTimeId}</td>
						</tr>
					</table>
				</div>
		</div>
</div>

</body>
</html>
