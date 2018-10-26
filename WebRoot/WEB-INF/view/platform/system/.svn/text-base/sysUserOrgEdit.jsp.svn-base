<%--
	time:2011-12-07 18:23:24
	desc:edit the 用户所属组织或部门
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 用户所属组织或部门</title>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=sysUserOrg"></script>
	<script type="text/javascript">
		$(function() {
			function showRequest(formData, jqForm, options) { 
				return true;
			} 
			valid(showRequest,showResponse);
			$("a.save").click(function() {
				$('#sysUserOrgForm').submit(); 
			});
		});
	</script>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
			    <c:choose>
			        <c:when test="${sysUserOrg.userOrgId !=null }">
			            <span class="tbar-label">编辑用户所属组织或部门</span>
			        </c:when>
			        <c:otherwise>
			            <span class="tbar-label">添加用户所属组织或部门</span>
			        </c:otherwise>
			    </c:choose>	
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link save" id="dataFormSave" href="javascript:;"><span></span>保存</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link back" href="list.ht"><span></span>返回</a></div>
				</div>
			</div>
		</div>
		<div class="panel-body">
			<div class="panel-detail">
				<form id="sysUserOrgForm" method="post" action="save.ht">
					<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<th width="20%">组织ID: </th>
							<td><input type="text" id="orgId" name="orgId" value="${sysUserOrg.orgId}"  class="inputText"/></td>
						</tr>
						<tr>
							<th width="20%">userId: </th>
							<td><input type="text" id="userId" name="userId" value="${sysUserOrg.userId}"  class="inputText"/></td>
						</tr>
						<tr>
							<th width="20%">是否为主要:  <span class="required">*</span></th>
							<td><input type="text" id="isPrimary" name="isPrimary" value="${sysUserOrg.isPrimary}"  class="inputText"/></td>
						</tr>
						<tr>
							<th width="20%">组织负责人ID: </th>
							<td><input type="text" id="isCharge" name="isCharge" value="${sysUserOrg.isCharge}"  class="inputText"/></td>
						</tr>
					</table>
					<input type="hidden" name="userOrgId" value="${sysUserOrg.userOrgId}" />
				</form>
				</div>
		</div>
</div>
</body>
</html>
