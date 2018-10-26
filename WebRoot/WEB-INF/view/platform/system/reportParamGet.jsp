<%--
	time:2012-04-12 11:08:13
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>报表参数明细</title>
	<%@include file="/commons/include/getById.jsp" %>
	<script type="text/javascript">
		//放置脚本
	</script>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">报表参数详细信息</span>
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
						<th width="20%">所属报表:</th>
						<td>${reportParam.REPORTID}</td>
					</tr>
					<tr>
						<th width="20%">参数名称:</th>
						<td>${reportParam.PARAMNAME}</td>
					</tr>
					<tr>
						<th width="20%">参数Key:</th>
						<td>${reportParam.PARAMKEY}</td>
					</tr>
					<tr>
						<th width="20%">缺省值:</th>
						<td>${reportParam.DEFAULTVAL}</td>
					</tr>
					<tr>
						<th width="20%">类型:</th>
						<td>${reportParam.PARAMTYPE}</td>
					</tr>
					<tr>
						<th width="20%">系列号:</th>
						<td>${reportParam.SN}</td>
					</tr>
					<tr>
						<th width="20%">PARAMTYPESTR:</th>
						<td>${reportParam.PARAMTYPESTR}</td>
					</tr>
				</table>
			</div>
		</div>
</div>

</body>
</html>
