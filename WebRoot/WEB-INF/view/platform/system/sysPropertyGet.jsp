
<%--
	time:2015-04-16 11:20:41
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>系统配置参数表明细</title>
<%@include file="/commons/include/get.jsp"%>
<script type="text/javascript">
	//放置脚本
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">系统配置参数表详细信息</span>
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
				<th width="20%">名称:</th>
				<td>${sysProperty.name}</td>
			</tr>
			<tr>
				<th width="20%">别名:</th>
				<td>${sysProperty.alias}</td>
			</tr>
			<tr>
				<th width="20%">值:</th>
				<td>${sysProperty.value}</td>
			</tr>
			<tr>
				<th width="20%">分组:</th>
				<td>${sysProperty.group}</td>
			</tr>
			<tr>
				<th width="20%">排序:</th>
				<td>${sysProperty.sn}</td>
			</tr>
		</table>
		</div>
	</div>
</body>
</html>

