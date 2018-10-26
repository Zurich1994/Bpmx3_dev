
<%--
	time:2015-05-17 15:44:41
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>日历模版明细</title>
<%@include file="/commons/include/get.jsp"%>
<script type="text/javascript">
	//放置脚本
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">日历模版详细信息</span>
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
				<td>${atsCalendarTempl.code}</td>
			</tr>
			<tr>
				<th width="20%">名称:</th>
				<td>${atsCalendarTempl.name}</td>
			</tr>
			<tr>
				<th width="20%">是否系统预置:</th>
				<td>${atsCalendarTempl.isSys}</td>
			</tr>
			<tr>
				<th width="20%">状态:</th>
				<td>${atsCalendarTempl.status}</td>
			</tr>
			<tr>
				<th width="20%">描述:</th>
				<td>${atsCalendarTempl.memo}</td>
			</tr>
		</table>
		</div>
	</div>
</body>
</html>

