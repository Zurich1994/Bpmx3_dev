
<%--
	time:2014-08-08 09:08:59
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>SYS_ORG_AUTH明细</title>
<%@include file="/commons/include/get.jsp"%>
<script type="text/javascript">
	//放置脚本
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">SYS_ORG_AUTH详细信息</span>
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
				<th width="20%">管理员ID:</th>
				<td>${orgAuth.userId}</td>
			</tr>
			<tr>
				<th width="20%">组织ID:</th>
				<td>${orgAuth.orgId}</td>
			</tr>
			<tr>
				<th width="20%">维度ID:</th>
				<td>${orgAuth.dimId}</td>
			</tr>
			<tr>
				<th width="20%">针对子用户组的添加、更新和删除:</th>
				<td>${orgAuth.orgPerms}</td>
			</tr>
			<tr>
				<th width="20%">针对用户与组关系的添加、更新和删除:</th>
				<td>${orgAuth.userPerms}</td>
			</tr>
		</table>
		</div>
	</div>
</body>
</html>

