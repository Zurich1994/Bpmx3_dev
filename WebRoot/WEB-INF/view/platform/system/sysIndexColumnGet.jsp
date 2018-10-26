
<%--
	time:2015-03-18 11:22:46
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>首页栏目明细</title>
<%@include file="/commons/include/get.jsp"%>
<script type="text/javascript">
	//放置脚本
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">首页栏目详细信息</span>
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
				<th width="20%">栏目名称:</th>
				<td>${sysIndexColumn.name}</td>
			</tr>
			<tr>
				<th width="20%">栏目别名:</th>
				<td>${sysIndexColumn.alias}</td>
			</tr>
			<tr>
				<th width="20%">栏目类别:</th>
				<td>${sysIndexColumn.catalog}</td>
			</tr>
			<tr>
				<th width="20%">栏目类型(0:服务方法;1:自定义查询):</th>
				<td>${sysIndexColumn.colType}</td>
			</tr>
			<tr>
				<th width="20%">数据别名:</th>
				<td>${sysIndexColumn.dsAlias}</td>
			</tr>
			<tr>
				<th width="20%">数据源名称:</th>
				<td>${sysIndexColumn.dsName}</td>
			</tr>
			<tr>
				<th width="20%">数据来源:</th>
				<td>${sysIndexColumn.dataFrom}</td>
			</tr>
			<tr>
				<th width="20%">栏目高度:</th>
				<td>${sysIndexColumn.colHeight}</td>
			</tr>
			<tr>
				<th width="20%">栏目url:</th>
				<td>${sysIndexColumn.colUrl}</td>
			</tr>
			<tr>
				<th width="20%">栏目模版:</th>
				<td>${sysIndexColumn.templateHtml}</td>
			</tr>
			<tr>
				<th width="20%">是否公共栏目:</th>
				<td>${sysIndexColumn.isPublic}</td>
			</tr>
			<tr>
				<th width="20%">所属组织ID:</th>
				<td>${sysIndexColumn.orgId}</td>
			</tr>
			<tr>
				<th width="20%">是否支持刷新:</th>
				<td>${sysIndexColumn.supportRefesh}</td>
			</tr>
			<tr>
				<th width="20%">刷新时间:</th>
				<td>${sysIndexColumn.refeshTime}</td>
			</tr>
			<tr>
				<th width="20%">展示效果:</th>
				<td>${sysIndexColumn.showEffect}</td>
			</tr>
			<tr>
				<th width="20%">描述:</th>
				<td>${sysIndexColumn.memo}</td>
			</tr>
		</table>
		</div>
	</div>
</body>
</html>

