<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 分级管理员</title>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript" src="${ctx }/js/hotent/platform/system/SysDialog.js"></script>
	<script type="text/javascript">
		$(function() {
			$("a.save").click(function() {
				$("#orgAuthForm").attr("action","save.ht");
				submitForm();
			});
		});
		//提交表单
		function submitForm(){
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#orgAuthForm').form();
			frm.ajaxForm(options);
			if(frm.valid()){
				frm.submit();
			}
		}
		
		function showResponse(responseText) {
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {
				$.ligerDialog.success(obj.getMessage(),"提示信息", function(rtn) {
					if(rtn){
						if(window.opener){
							window.opener.location.reload();
							window.close();
						}else{
							this.close();
							window.location.href="${isGrade==true ? 'gradeList':'list'}.ht?orgId=${orgId}";
						}
					}
				});
			} else {
				$.ligerDialog.error(obj.getMessage(),"提示信息");
			}
		}
		
		function editUser(){
			if('${isGrade}'!='true'){  
				UserDialog({callback:fullUserMsg,isSingle:true});
			}else{
				GradeUserDialog({callback : fullUserMsg,isSingle:true});
			}
		}; 
		function fullUserMsg(userIds,fullNames){
			$("#userId").val(userIds);
			$("#userName").val(fullNames);	
		} 
		
		function resetUser(){
			$("#userId").val("");
			$("#userName").val("");	
		}
		function editRole(isGrade){
			if(isGrade) isGrade = true;  else isGrade = false;
			RoleDialog({callback:function(userIds,fullnames){
				$("#roleIds").val(userIds);
				$("#roleNames").val(fullnames);	
			},isSingle:false,isGrade:isGrade}); 
		};
		function resetRole(){
			$("#roleIds").val("");
			$("#roleNames").val("");	
		}
	</script>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
		    <c:choose>
			    <c:when test="${orgAuth.id !=null}">
			        <span class="tbar-label"><span></span>编辑分级管理员</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加分级管理员</span>
			    </c:otherwise>			   
		    </c:choose>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" href="javascript:;"><span></span>保存</a></div>
				<div class="l-bar-separator"></div>
				<div class="group">
				<a class="link back" href="${isGrade==true ? 'gradeList':'list'}.ht?orgId=${orgId}">
				<span></span>返回</a></div>
			</div>
		</div>
	</div>
	<div class="panel-body">
		<form id="orgAuthForm" method="post" action="save.ht">
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0" type="main">
				<tr>
					<th width="20%">管理员: <span class="required">*</span></th>
					<td>
					 	<input type="text" class="inputText" readonly="readonly"id="userName" value="${orgAuth.userName}" validate="{required:true}">
						<input type="hidden" id="userId" name="userId" value="${orgAuth.userId}"  class="inputText" />
					    <a href="javascript:;" onclick="editUser()" class="link get">选择</a>
					    <a href="javascript:;" onclick="resetUser()" class="link clean">清空</a>
					</td>
				</tr>
				<tr>
					<th width="20%">组织:<span class="required">*</span></th>
					<td>
					${empty sysOrg ? orgAuth.orgName : sysOrg.orgName}
					<input type="hidden" id="orgId" name="orgId" value="${empty sysOrg ? orgAuth.orgId : sysOrg.orgId}" />
					<input type="hidden" id="dimId" name="dimId" value="${empty sysOrg ? orgAuth.dimId : sysOrg.demId}" /></td> 
				</tr>
				<tr>
					<th width="20%">子组织权限: </th>
					<td>
						<input type="checkbox" name="orgPerms" value="add" id="orgAdd" <c:if test="${fn:contains(orgAuth.orgPerms, 'add')}">checked="checked" </c:if>> 
						<label for="orgAdd">增加</label>　
						<input type="checkbox" name="orgPerms" value="delete" id="orgDelete" <c:if test="${fn:contains(orgAuth.orgPerms, 'delete')}">checked="checked" </c:if>> 
						　<label for="orgDelete">删除</label>　
						<input type="checkbox" name="orgPerms" value="edit" id="orgEdit" <c:if test="${fn:contains(orgAuth.orgPerms, 'edit')}">checked="checked" </c:if>> 
						<label for="orgEdit">修改</label>　
					</td>
				</tr>
				<tr>
					<th width="20%">用户权限: </th>
					<td>
						<input type="checkbox" name="userPerms" value="add" id="userAdd" <c:if test="${fn:contains(orgAuth.userPerms, 'add')}">checked="checked" </c:if>>
						<label for="userAdd">增加</label>
						<input type="checkbox" name="userPerms" value="delete" id="userDelete" <c:if test="${fn:contains(orgAuth.userPerms, 'delete')}">checked="checked" </c:if>> 
						<label for="userDelete">删除</label>
						<input type="checkbox" name="userPerms" value="edit" id="userEdit" <c:if test="${fn:contains(orgAuth.userPerms, 'edit')}">checked="checked" </c:if>>
						<label for="userEdit">修改</label>
		        </tr>
		        <tr>
					<th width="20%">可分配角色: </th>
					<td>
					<textarea id="roleNames" rows="1" cols="1" readonly="readonly"><c:forEach items="${roleList}" var="role">${role.roleName},</c:forEach></textarea>
					<input type="hidden" name="roleIds" id="roleIds" value="<c:forEach items="${roleList}" var="role">${role.roleId},</c:forEach>"> 
					 <a href="javascript:;" onclick="editRole(${isGrade})" class="link get">选择</a>
					 <a href="javascript:;" onclick="resetRole()" class="link clean">清空</a>
					</td>
		        </tr>
			</table>
			<input type="hidden" name="id" value="${orgAuth.id}" />
		</form>
		
	</div>
</div>
</body>
</html>
