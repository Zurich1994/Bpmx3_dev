
<%--
	time:2015-06-08 16:02:04
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>视图自定义设定明细</title>
<%@include file="/commons/include/get.jsp"%>
<script type="text/javascript">
	//放置脚本
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">视图自定义设定详细信息</span>
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
				<th width="20%">视图ID:</th>
				<td>${sysQueryFieldSetting.viewId}</td>
			</tr>
			<tr>
				<th width="20%">字段ID:</th>
				<td>${sysQueryFieldSetting.fieldId}</td>
			</tr>
			<tr>
				<th width="20%">允许排序:</th>
				<td>${sysQueryFieldSetting.sortAble}</td>
			</tr>
			<tr>
				<th width="20%">默认排序:</th>
				<td>${sysQueryFieldSetting.defaultSort}</td>
			</tr>
			<tr>
				<th width="20%">默认排序方向:</th>
				<td>${sysQueryFieldSetting.sortSeq}</td>
			</tr>
			<tr>
				<th width="20%">对齐方式:</th>
				<td>${sysQueryFieldSetting.align}</td>
			</tr>
			<tr>
				<th width="20%">是否冻结:</th>
				<td>${sysQueryFieldSetting.frozen}</td>
			</tr>
			<tr>
				<th width="20%">列连接地址:</th>
				<td>${sysQueryFieldSetting.url}</td>
			</tr>
			<tr>
				<th width="20%">计算类型:</th>
				<td>${sysQueryFieldSetting.summaryType}</td>
			</tr>
			<tr>
				<th width="20%">计算模版:</th>
				<td>${sysQueryFieldSetting.summaryTemplate}</td>
			</tr>
			<tr>
				<th width="20%">报警设定:</th>
				<td>${sysQueryFieldSetting.alertSetting}</td>
			</tr>
			<tr>
				<th width="20%">自定义格式函数:</th>
				<td>${sysQueryFieldSetting.formatter}</td>
			</tr>
		</table>
		</div>
	</div>
</body>
</html>

