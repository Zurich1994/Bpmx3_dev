
<%--
	time:2015-06-08 16:02:04
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>视图定义明细</title>
<%@include file="/commons/include/get.jsp"%>
<script type="text/javascript">
	//放置脚本
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">视图定义详细信息</span>
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
				<th width="20%">SQLID:</th>
				<td>${sysQueryView.sqlId}</td>
			</tr>
			<tr>
				<th width="20%">视图定义名称:</th>
				<td>${sysQueryView.name}</td>
			</tr>
			<tr>
				<th width="20%">视图别名:</th>
				<td>${sysQueryView.alias}</td>
			</tr>
			<tr>
				<th width="20%">查询条件:</th>
				<td>${sysQueryView.condition}</td>
			</tr>
			<tr>
				<th width="20%">过滤器:</th>
				<td>${sysQueryView.filter}</td>
			</tr>
			<tr>
				<th width="20%">过滤器类型:</th>
				<td>${sysQueryView.filterType}</td>
			</tr>
			<tr>
				<th width="20%">按钮定义:</th>
				<td>${sysQueryView.buttons}</td>
			</tr>
			<tr>
				<th width="20%">是否初始化查询:</th>
				<td>${sysQueryView.initQuery}</td>
			</tr>
			<tr>
				<th width="20%">模版定义:</th>
				<td>${sysQueryView.template}</td>
			</tr>
			<tr>
				<th width="20%">是否支持分组:</th>
				<td>${sysQueryView.supportGroup}</td>
			</tr>
			<tr>
				<th width="20%">分组设定:</th>
				<td>${sysQueryView.groupSetting}</td>
			</tr>
			<tr>
				<th width="20%">分页大小:</th>
				<td>${sysQueryView.pageSize}</td>
			</tr>
			<tr>
				<th width="20%">列方向:</th>
				<td>${sysQueryView.colDirectory}</td>
			</tr>
			<tr>
				<th width="20%">显示行号:</th>
				<td>${sysQueryView.showRowsNum}</td>
			</tr>
		</table>
		</div>
	</div>
</body>
</html>

