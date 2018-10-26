<%@page language="java" pageEncoding="UTF-8" import="com.hotent.platform.model.system.SysUser"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>状态设置</title>
<%@include file="/commons/include/form.jsp" %>
<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=sysUser"></script>
<script type="text/javascript">
		$(function() {
			function showRequest(formData, jqForm, options) { 
				return true;
			} 							
			valid(showRequest,showResponse);
			$("a.save").click(function() {
				$('#sysUserForm').submit(); 
			});
		});
				
		
	</script>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">设置【${sysUser.fullname}】状态</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link save" id="dataFormSave"><span></span>保存</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link back" href="${returnUrl}"><span></span>返回</a></div>
				</div>
			</div>
		</div>
		<div class="panel-body">
				<form id="sysUserForm" method="post" action="editStatus.ht">
					<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<th width="20%">是否可用:</th>
							<td >
                            <select name="isLock" class="select" style="width:245px !important">
									<option value="<%=SysUser.UN_LOCKED %>" <c:if test="${sysUser.isLock==0}">selected</c:if> >未锁定</option>
									<option value="<%=SysUser.LOCKED %>" <c:if test="${sysUser.isLock==1}">selected</c:if> >已锁定</option>
							</select>
                            </td>
						</tr>
						<tr>
							<th width="20%">当前状态:</th>
							<td >
                             <select name="status"  class="select" style="width:245px !important">
									<option value="<%=SysUser.STATUS_OK %>" <c:if test="${sysUser.status==1}">selected</c:if> >激活</option>
									<option value="<%=SysUser.STATUS_NO %>" <c:if test="${sysUser.status==0}">selected</c:if> >禁用</option>
									<option value="<%=SysUser.STATUS_Del %>" <c:if test="${sysUser.status==-1}">selected</c:if>>删除</option>
							 </select>
                            </td>
						</tr>
					</table>
					<input type="hidden" name="userId" value="${sysUser.userId}" />
				</form>
		</div>
</div>
</body>
</html>