<%--
	time:2011-11-28 11:31:14
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>系统角色明细</title>
	<%@include file="/commons/include/getById.jsp" %>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">系统角色详细信息</span>
			</div>
			<c:if test="${isOtherLink==0}">
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link back" href="../sysRole/list.ht"><span></span>返回</a></div>
				</div>
			</div>
			</c:if>
		</div>
		<div class="panel-body">
			<div class="panel-detail">
				<form id="sysRoleForm" method="post" action="add2.ht">
					<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
					    <tr>
							<th width="20%">角色名称:</th>
							<td>${sysRole.roleName}</td>
						</tr>
						<tr>
							<th width="20%">子系统名称:</th>
							<td>
								<c:if test="${not empty subsystem}">
									${subsystem.sysName}
								</c:if>
							</td>
						</tr>
						<tr>
							<th width="20%">角色别名:</th>
							<td>${sysRole.alias}</td>
						</tr>
						<tr>
							<th width="20%">备注:</th>
							<td>${sysRole.memo}</td>
						</tr>
						<tr>
							<th width="20%">允许删除:</th>
							<td>
							<c:choose>
							<c:when test="${sysRole.allowDel eq 1}">允许</c:when>
							<c:when test="${sysRole.allowDel eq 0}">不允许</c:when>
							<c:otherwise>未设定</c:otherwise>
							</c:choose>
							</td>
						</tr>
						<tr>
							<th width="20%">允许编辑:</th>
							<td>
							<c:choose>
							<c:when test="${sysRole.allowEdit eq 1}">允许</c:when>
							<c:when test="${sysRole.allowEdit eq 0}">不允许</c:when>
							<c:otherwise>未设定</c:otherwise>
							</c:choose>
							</td>
						</tr>
						<tr>
							<th width="20%">是否启用:</th>
							<td>
							<c:choose>
							<c:when test="${sysRole.enabled eq 1}">启用</c:when>
							<c:when test="${sysRole.enabled eq 0}">禁用</c:when>
							<c:otherwise>未设定</c:otherwise>
							</c:choose>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
</div>

</body>
</html>
