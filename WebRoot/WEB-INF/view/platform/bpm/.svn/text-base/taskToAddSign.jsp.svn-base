<%@ page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>选择补签人员</title>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript">
	/*KILLDIALOG*/
	var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)
	
	var taskId="${param.taskId}";
	function selectSignUsers(){
		var uIds=$("#addSignUserIds");
		var uNames=$('#addSignFullnames');
		UserDialog({callback:function(userIds,fullnames){
						uIds.val(userIds);
						uNames.val(fullnames);
					}
		});
	}
	
	function save(){
		var rtn=$("#frmAddSign").form().valid();
		if(!rtn) return;
		 var signUserIds=$("#addSignUserIds").val();
		 var opinion=$("#opinion").val();
		 var informType=$.getChkValue("informType");
		 var params={
				 taskId:taskId,
				 signUserIds:signUserIds,
				 opinion:opinion,
				 informType:informType
				 };
		 if(signUserIds=='') return;
   		 $.post(__ctx+'/platform/bpm/task/addSign.ht',params,
       		 function(){
				 $.ligerDialog.success('你成功进行了补签!','操作成功',function(){
					 //this.window.returnValue={};
					 dialog.get('sucCall')({});
					 this.dialog.close();
				 });
       		 }
   		 ); 
	}
	
	$(function(){
		$("#dataFormSave").click(save);
	});
	</script>
</head>
<body>


<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
			<span class="tbar-label">任务补签</span>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group">
					<a class="link save" id="dataFormSave" href="javascript:;"><span></span>补签</a>
				</div>
				<div class="l-bar-separator"></div>
				<div class="group">
					<a class="link del" href="javascript:;" onclick="dialog.close()"><span></span>关闭</a>
				</div>
			</div>
		</div>
	</div>
	<div class="panel-detail">
	<form id="frmAddSign">
		<table class="table-detail">
			<tr>
				<th nowrap="nowrap">选择补签人员</th>
				<td>
					<input type="hidden" id="addSignUserIds"/>
					<input type="hidden" id="taskId" value="${param.taskId}"/>
					<input type="text" size="60" value="" readonly="readonly" id="addSignFullnames" class="inputText"/> <input type="button" onclick="selectSignUsers()" value="选择.."/>
				</td>
			</tr>
			<tr>
				<th>提醒消息方式:</th>
				<td>
					<input type="checkbox" name="informType" value="3" checked="checked">站内消息
					<input type="checkbox" name="informType" value="1" checked="checked">邮件
					<input type="checkbox" name="informType" value="2">手机短信
				</td>
			</tr>
			<tr>
				<th>补签原因: </th>
				<td>
					<textarea rows="4" cols="50" style="width:300px" id="opinion" name="opinion" validate="{required:true,maxLength:1000}" maxLength="1000"></textarea>
				</td>
			</tr>
		</table>
		</form>
	</div>
</div>
</body>
</html>