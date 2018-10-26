<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.hotent.platform.model.system.SysUser"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
	<title>AD用户管理</title>
	<%@include file="/commons/include/get.jsp" %>
	<script type="text/javascript" src="${ctx }/js/lg/plugins/ligerWindow.js" ></script>
	<script type="text/javascript">
	</script>
</head>
<body>
	<div class="panel-body">
		<table class="table-detail">
			<tr>
				<th width="10%">时间</th>
				<td><fmt:formatDate value="${lastSyncTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
			</tr>
			<tr>
				<th>耗时(ms)</th>
				<td>${lastSyncTakeTime }</td>
			</tr>
			<tr>
				<th>添加用户数</th>
				<td>${fn:length(newFromLdapUserList)}</td>
			</tr>
			<tr>
				<th>删除用户数</th>
				<td>${fn:length(deleteLocalUserList)}</td>
			</tr>
			<tr>
				<th>更新用户数</th>
				<td>${fn:length(updateLocalUserList)}</td>
			</tr>
		</table>
	</div>
</body>
</html>


