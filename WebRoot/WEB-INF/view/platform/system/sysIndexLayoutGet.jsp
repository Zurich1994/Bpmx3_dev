
<%--
	time:2015-03-18 17:30:22
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>首页布局明细</title>
<%@include file="/commons/include/get.jsp"%>
<script type="text/javascript">
	//放置脚本
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">首页布局详细信息</span>
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
				<th width="20%">布局名称:</th>
				<td>${sysIndexLayout.name}</td>
			</tr>
			<tr>
				<th width="20%">布局描述:</th>
				<td>${sysIndexLayout.memo}</td>
			</tr>
			<tr>
				<th width="20%">模版内容:</th>
				<td><pre>${fn:escapeXml(sysIndexLayout.templateHtml)}</pre></td>
			</tr>
			<tr>
				<th width="20%">排序:</th>
				<td>${sysIndexLayout.sn}</td>
			</tr>
		</table>
		</div>
	</div>
</body>
</html>

