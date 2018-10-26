
<%--
	time:2015-06-08 16:02:04
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>自定义SQL定义明细</title>
<%@include file="/commons/include/get.jsp"%>
<script type="text/javascript">
	//放置脚本
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">自定义SQL定义详细信息</span>
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
				<td>${sysQuerySqlDef.name}</td>
			</tr>
			<tr>
				<th width="20%">sql语句:</th>
				<td>${sysQuerySqlDef.sql}</td>
			</tr>
			<tr>
				<th width="20%">数据源名称:</th>
				<td>${sysQuerySqlDef.dsname}</td>
			</tr>
			<tr>
				<th width="20%">按钮定义:</th>
				<td>${sysQuerySqlDef.buttonDef}</td>
			</tr>
		</table>
		</div>
	</div>
</body>
</html>

