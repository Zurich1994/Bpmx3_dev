
<%--
	time:2012-11-27 09:55:21
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>组织结构类型明细</title>
<%@include file="/commons/include/getById.jsp"%>
<script type="text/javascript">
	//放置脚本
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">组织结构类型详细信息</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link back" href="list.ht"><span></span>返回</a>
					</div>
				</div>
			</div>
		</div>
		<div class="panel-body">
		<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
			 
			<tr>
				<th width="20%">维度ID:</th>
				<td>${sysOrgType.demid}</td>
			</tr>
 
			<tr>
				<th width="20%">名称:</th>
				<td>${sysOrgType.name}</td>
			</tr>
 
			<tr>
				<th width="20%">级别:</th>
				<td>${sysOrgType.levels}</td>
			</tr>
 
			<tr>
				<th width="20%">备注:</th>
				<td>${sysOrgType.memo}</td>
			</tr>
		</table>
		</div>
		
	</div>
</body>
</html>

