
<%--
	time:2015-05-17 15:48:22
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>法定节假日明细</title>
<%@include file="/commons/include/get.jsp"%>
<script type="text/javascript">
	//放置脚本
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">法定节假日详细信息</span>
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
				<td>${atsLegalHoliday.code}</td>
			</tr>
			<tr>
				<th width="20%">名称:</th>
				<td>${atsLegalHoliday.name}</td>
			</tr>
			<tr>
				<th width="20%">年度:</th>
				<td>${atsLegalHoliday.year}</td>
			</tr>
			<tr>
				<th width="20%">描述:</th>
				<td>${atsLegalHoliday.memo}</td>
			</tr>
		</table>
		</div>
	</div>
</body>
</html>

