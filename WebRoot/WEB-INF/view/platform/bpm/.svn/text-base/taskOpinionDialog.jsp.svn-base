<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<%@include file="/commons/include/form.jsp" %>
<title>沟通反馈</title>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
<script type="text/javascript">
/*KILLDIALOG*/
var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)

var taskId=${param.taskId};
var formData=${param.formData};
function callBack(rtn) {
	if(!rtn) return;
	var content=$("#opinion").val();
	var informType=$.getChkValue("informType");
	
	var params= {opinion:content,informType:informType,taskId:taskId,formData:JSON.stringify(formData)};
	var url=__ctx +"/platform/bpm/task/saveOpinion.ht";
	$.post(url,params,function(msg){
		var obj=new com.hotent.form.ResultMessage(msg);
		if(obj.isSuccess()){
			 $.ligerDialog.success("意见反馈成功","提示信息",function(){
				 //window.returnValue="ok";
				 dialog.get("sucCall")("ok");
	    		 dialog.close(); 
	    	 });
		}else{
			 $.ligerDialog.err("提示信息","意见反馈失败",obj.getMessage());
		}
	});
}


function save(){
	var rtn=$("#frmComm").form().valid();
	if(!rtn) return;
 	$.ligerDialog.confirm('确认发送反馈吗？','提示',callBack);
 }
 
</script>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
			<span class="tbar-label">意见反馈</span>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link run" id="dataFormSave" href="javascript:;" onclick="save()"><span></span>提交</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link close" href="javascript:;" onclick="dialog.close()"><span></span>关闭</a></div>
			</div>
		</div>
	</div>
	<div class="panel-body">
		<form id="frmComm">
		<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
			<tr>
				<th>提醒方式:</th>
				<td>
					<input type="checkbox" name="informType" value="3" checked="checked">站内消息
					<input type="checkbox" name="informType" value="1">邮件
					<input type="checkbox" name="informType" value="2">短信
				</td>
			</tr>
			<tr>
				<th>反馈意见:</th>
				<td>
					<textarea rows="4" cols="50" style="width:300px" id="opinion" name="opinion" validate="{required:true,maxLength:500}" maxLength="500"></textarea>
				</td>
			</tr>
		</table>
		</form>
	</div>
</div>
</body>
</html>