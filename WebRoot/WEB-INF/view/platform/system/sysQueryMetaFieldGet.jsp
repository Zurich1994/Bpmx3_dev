
<%--
	time:2015-06-08 16:02:04
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>自定义SQL字段定义明细</title>
<%@include file="/commons/include/get.jsp"%>
<script type="text/javascript">
	//放置脚本
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">自定义SQL字段定义详细信息</span>
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
				<td>${sysQueryMetaField.sqlId}</td>
			</tr>
			<tr>
				<th width="20%">字段名:</th>
				<td>${sysQueryMetaField.name}</td>
			</tr>
			<tr>
				<th width="20%">实际字段名:</th>
				<td>${sysQueryMetaField.fieldName}</td>
			</tr>
			<tr>
				<th width="20%">字段备注:</th>
				<td>${sysQueryMetaField.fieldDesc}</td>
			</tr>
			<tr>
				<th width="20%">是否可见:</th>
				<td>${sysQueryMetaField.isShow}</td>
			</tr>
			<tr>
				<th width="20%">是否搜索:</th>
				<td>${sysQueryMetaField.isSearch}</td>
			</tr>
			<tr>
				<th width="20%">控件类型:</th>
				<td>${sysQueryMetaField.controlType}</td>
			</tr>
			<tr>
				<th width="20%">数据类型:</th>
				<td>${sysQueryMetaField.dataType}</td>
			</tr>
			<tr>
				<th width="20%">是否衍生列:</th>
				<td>${sysQueryMetaField.isVirtual}</td>
			</tr>
			<tr>
				<th width="20%">衍生列来自列:</th>
				<td>${sysQueryMetaField.virtualFrom}</td>
			</tr>
			<tr>
				<th width="20%">来自类型:</th>
				<td>${sysQueryMetaField.resultFromType}</td>
			</tr>
			<tr>
				<th width="20%">衍生列配置:</th>
				<td>${sysQueryMetaField.resultFrom}</td>
			</tr>
		</table>
		</div>
	</div>
</body>
</html>

