<%--
	time:2011-11-28 10:17:09
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/commons/include/html_doctype.html"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>修改密码</title>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=sysUser"></script>
	<script type="text/javascript">
		$(function() {
			function showRequest(formData, jqForm, options) {
				var newPassword=$("#newPassword").val();
				var repeatPassword=$("#repeatPassword").val();
				if(newPassword!=repeatPassword){
					$.ligerDialog.error("两次输入的密码不一致",'出错了');
					return false;
				}
				return true;

			}			
			
			function showResponse(responseText, statusText)  { 
				var obj=new com.hotent.form.ResultMessage(responseText);
				if(obj.isSuccess()){//成功
					$.ligerDialog.success(obj.getMessage(),'提示信息');					
			    }else{//失败
			    	$.ligerDialog.err('出错信息',"修改密码失败",obj.getMessage());
			    }
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
				<span class="tbar-label">修改密码</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link save" id="dataFormSave"><span></span>保存</a></div>
				</div>
			</div>
		</div>
		<div class="panel-body">
				<form id="sysUserForm" method="post" action="modifyPwd.ht">
					<div class="panel-detail">
						<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
							<tr>
								<th width="20%">帐号:<span> </span></th>
								<td>${sysUser.account}</td>
							</tr>
							<tr>
								<th width="20%">用户名:<span> </span></th>
								<td>${sysUser.fullname}</td>
							</tr>
							<tr>
								<th width="20%">原始密码:  <span class="required">*</span></th>
								<td ><input type="password" id="primitivePassword" autocomplete="off" name="primitivePassword" style="width:255px !important" class="inputText"/></td>
							</tr> 
							<tr>
								<th width="20%">修改密码:  <span class="required">*</span></th>
								<td ><input type="password" autocomplete="off" id="newPassword" name="newPassword" style="width:255px !important" class="inputText"/></td>
							</tr>
							<tr>
								<th width="20%">重复密码:  <span class="required">*</span></th>
								<td ><input type="password" autocomplete="off" id="repeatPassword" name="repeatPassword" style="width:255px !important" class="inputText"/></td>
							</tr>
						</table>
						<input type="hidden" name="userId" value="${userId}" />
					</div>
				</form>
		</div>
</div>
</body>
</html>
