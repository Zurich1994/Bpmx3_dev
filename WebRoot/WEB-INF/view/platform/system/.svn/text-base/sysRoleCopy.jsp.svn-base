<%--
	time:2011-11-28 11:31:14
	desc:edit the 系统角色表
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>复制角色</title>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript">
	/*KILLDIALOG*/
	var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)
	
	var roleId=${sysRole.roleId};
	
	
	function validInput(){
		var __valid__=$("#frmCopy").validate({
			rules: {
				newRoleName:{
					required:true,
					maxlength:20
				},
				newAlias:{
					required:true,
					maxlength:20
				}
			},
			messages: {
				newRoleName:{
					required:"角色名必填",
					maxlength:"角色名最多 20 个字符."
				},
				newAlias:{
					required:"别名必填",
					maxlength:"别名最多 20 个字符."
				}
			}
		});
		return __valid__;
	}

	
	$(function(){
		validInput();
		
	});
	function save(){
		var rtn=$("#frmCopy").valid();
		if(!rtn){
			return;
		}
		var newRoleName=$("#newRoleName").val();
		var newAlias=$("#newAlias").val();
		var alias=$("#alias").val();
		var oldRoleName=$("#roleName").val();
		
		if(newRoleName==oldRoleName){
			$.ligerDialog.error('角色名不能相同！','提示信息');
			return ;
	    }
		
		if(newAlias==alias){
			$.ligerDialog.error('别名不能相同！','提示信息');
			return ;
	    }
		var url="copyRole.ht";
		var para="roleId="+roleId+"&newRoleName="+newRoleName +"&newAlias=" +newAlias;
		
	    $.post(url,para,function(responseText){
	    	var obj=new com.hotent.form.ResultMessage(responseText);
	    	if(obj.isSuccess()){//成功
	    		$.ligerDialog.success(obj.getMessage(),'提示信息',function(){
	    			dialog.get('sucCall')();
	    			dialog.close();
	    		});	
	        }else{//失败
	        	$.ligerDialog.err('出错信息',"保存角色信息失败",obj.getMessage());
	        }
	    });
		
		
	}
	</script>
	<style type="text/css">
		html { overflow-x: hidden; }
	</style>
</head>
<body>
<div class="panel">
  <div class="panel-top">
    <div class="tbar-title">
	    <span class="tbar-label">复制角色</span>
	</div>
	<div class="panel-toolbar">
			<div class="toolBar">
			<div class="group"><a class="link save" id="btnSearch" onclick="save()"><span></span>保存</a></div>
			<div class="l-bar-separator"></div>
			<div class="group"><a class="link del" onclick="javasrcipt:dialog.close()"><span></span>关闭</a></div>
	</div>	
	</div>
</div>
	<div class="panel-body">
		<form id="frmCopy" name="frmCopy">
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<th width="15%">原角色名称: </th>
					<td><input type="text" id="roleName" name="roleName" class="inputText" disabled="disabled" value="${sysRole.roleName }"/></td>
					<th width="15%">新角色名称: </th>
					<td><input type="text" id="newRoleName" name="newRoleName"class="inputText"/></td>
				</tr>
				<tr>
					<th width="15%">原别名: </th>
					<td><input type="text" id="alias" name="alias" class="inputText" disabled="disabled" value="${sysRole.alias }"/></td>
					<th width="15%">新别名: </th>
					<td><input type="text" id="newAlias" name="newAlias"class="inputText"/></td>
				</tr>
			</table>
		</form>
					
	</div>
</body>
</html>
