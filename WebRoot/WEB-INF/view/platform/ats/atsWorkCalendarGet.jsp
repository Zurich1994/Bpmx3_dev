
<%--
	time:2015-05-17 15:46:19
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>工作日历明细</title>
<%@include file="/commons/include/get.jsp"%>
<script type="text/javascript">
	//放置脚本
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">工作日历详细信息</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link back" href="list.ht"><span></span>返回</a>
					</div>
				</div>
			</div>
		</div>
		<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
			<tr>
				<th width="20%">编码:</th>
				<td>${atsWorkCalendar.code}</td>
			</tr>
			<tr>
				<th width="20%">名称:</th>
				<td>${atsWorkCalendar.name}</td>
			</tr>
			<tr>
				<th width="20%">开始时间:</th>
				<td>
				<fmt:formatDate value="${atsWorkCalendar.startTime}" pattern="yyyy-MM-dd"/>
				</td>
			</tr>
			<tr>
				<th width="20%">结束时间:</th>
				<td>
				<fmt:formatDate value="${atsWorkCalendar.endTime}" pattern="yyyy-MM-dd"/>
				</td>
			</tr>
			<tr>
				<th width="20%">日历模版:</th>
				<td>${atsWorkCalendar.calendarTempl}</td>
			</tr>
			<tr>
				<th width="20%">日历模版:</th>
				<td>${atsWorkCalendar.legalHoliday}</td>
			</tr>
			<tr>
				<th width="20%">是否系统预置:</th>
				<td>${atsWorkCalendar.isDefault}</td>
			</tr>
			<tr>
				<th width="20%">描述:</th>
				<td>${atsWorkCalendar.memo}</td>
			</tr>
		</table>
		</div>
	</div>
</body>
</html>

