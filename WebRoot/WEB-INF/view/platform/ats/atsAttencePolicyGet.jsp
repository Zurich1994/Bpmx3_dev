
<%--
	time:2015-05-17 20:54:19
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>考勤制度明细</title>
<%@include file="/commons/include/get.jsp"%>
<script type="text/javascript">
	//放置脚本
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">考勤制度详细信息</span>
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
				<td>${atsAttencePolicy.code}</td>
			</tr>
			<tr>
				<th width="20%">名称:</th>
				<td>${atsAttencePolicy.name}</td>
			</tr>
			<tr>
				<th width="20%">工作日历:</th>
				<td>${atsAttencePolicy.workCalendar}</td>
			</tr>
			<tr>
				<th width="20%">考勤周期:</th>
				<td>${atsAttencePolicy.attenceCycle}</td>
			</tr>
			<tr>
				<th width="20%">所属组织:</th>
				<td>${atsAttencePolicy.orgId}</td>
			</tr>
			<tr>
				<th width="20%">是否默认:</th>
				<td>${atsAttencePolicy.isDefault}</td>
			</tr>
			<tr>
				<th width="20%">描述:</th>
				<td>${atsAttencePolicy.memo}</td>
			</tr>
			<tr>
				<th width="20%">每周工作时数(小时):</th>
				<td>${atsAttencePolicy.weekHour}</td>
			</tr>
			<tr>
				<th width="20%">每天工作时数(小时):</th>
				<td>${atsAttencePolicy.daysHour}</td>
			</tr>
			<tr>
				<th width="20%">月标准工作天数(天):</th>
				<td>${atsAttencePolicy.monthDay}</td>
			</tr>
			<tr>
				<th width="20%">每段早退允许值(分钟):</th>
				<td>${atsAttencePolicy.leaveAllow}</td>
			</tr>
			<tr>
				<th width="20%">每段迟到允许值(分钟):</th>
				<td>${atsAttencePolicy.lateAllow}</td>
			</tr>
			<tr>
				<th width="20%">旷工起始值(分钟):</th>
				<td>${atsAttencePolicy.absentAllow}</td>
			</tr>
			<tr>
				<th width="20%">加班起始值(分钟):</th>
				<td>${atsAttencePolicy.otStart}</td>
			</tr>
			<tr>
				<th width="20%">班前无需加班单:</th>
				<td>${atsAttencePolicy.preNotBill}</td>
			</tr>
			<tr>
				<th width="20%">班后无需加班单:</th>
				<td>${atsAttencePolicy.afterNotBill}</td>
			</tr>
		</table>
		</div>
	</div>
</body>
</html>

