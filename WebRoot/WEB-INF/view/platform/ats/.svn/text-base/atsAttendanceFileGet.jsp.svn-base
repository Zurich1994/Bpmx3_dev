
<%--
	time:2015-05-20 09:11:05
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>考勤档案明细</title>
<%@include file="/commons/include/get.jsp"%>
<script type="text/javascript">
	//放置脚本
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">考勤档案详细信息</span>
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
				<th width="20%">用户:</th>
				<td>${atsAttendanceFile.userId}</td>
			</tr>
			<tr>
				<th width="20%">考勤卡号:</th>
				<td>${atsAttendanceFile.cardNumber}</td>
			</tr>
			<tr>
				<th width="20%">是否参与考勤:</th>
				<td>${atsAttendanceFile.isAttendance}</td>
			</tr>
			<tr>
				<th width="20%">考勤制度:</th>
				<td>${atsAttendanceFile.attencePolicy}</td>
			</tr>
			<tr>
				<th width="20%">假期制度:</th>
				<td>${atsAttendanceFile.holidayPolicy}</td>
			</tr>
			<tr>
				<th width="20%">默认班次:</th>
				<td>${atsAttendanceFile.defaultShift}</td>
			</tr>
		</table>
		</div>
	</div>
</body>
</html>

