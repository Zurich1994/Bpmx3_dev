<%--
	time:2011-11-21 12:22:07
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>子系统管理明细</title>
<%@include file="/commons/include/getById.jsp"%>
</head>
<body>
	<div class="panel">
	<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">子系统管理明细</span>
			</div>
			<c:if test="${canReturn==0}">
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link back" href="getSubSystem.ht"><span></span>返回</a></div>
				</div>
			</div>
			</c:if>
		</div>
		
		<div class="panel-body">
			<div class="panel-detail">
				<table class="table-detail" border="0" cellspacing="0"
					cellpadding="0">
					<tr>
						<td width="20%">系统名称:</td>
						<td>${subSystem.sysName}</td>
					</tr>
					<tr>
						<td width="20%">别名(系统中唯一):</td>
						<td>${subSystem.alias}</td>
					</tr>
					<tr>
						<td width="20%">系统的图标:</td>
						<td>${subSystem.logo}</td>
					</tr>
					<tr>
						<td width="20%">系统首页地址:</td>
						<td>${subSystem.defaultUrl}</td>
					</tr>
					<tr>
						<td width="20%">备注:</td>
						<td>${subSystem.memo}</td>
					</tr>
					<tr>
						<td width="20%">创建时间:</td>
						<td><fmt:formatDate value="${subSystem.createtime}" pattern="yyyy-MM-dd HH:ss"/></td>
					</tr>
					<tr>
						<td width="20%">创建人:</td>
						<td>${subSystem.creator}</td>
					</tr>
					<tr>
						<td width="20%">允许删除:</td>
						<td>${subSystem.allowDel}</td>
					</tr>
					<tr>
						<td width="20%">选择组织架构:</td>
						<td>${subSystem.needOrg}</td>
					</tr>
					<tr>
						<td width="20%">是否激活:</td>
						<td>${subSystem.isActive}</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</body>
</html>
