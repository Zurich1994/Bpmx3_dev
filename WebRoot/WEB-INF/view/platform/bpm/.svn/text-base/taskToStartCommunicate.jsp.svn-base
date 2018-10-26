<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<%@include file="/commons/include/form.jsp" %>
<title>任务沟通</title>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
<script type="text/javascript">
/*KILLDIALOG*/
var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)

var taskId=${param.taskId};
//var formData = window.dialogArguments.data;
var formData = dialog.get("obj").data;

function callBack(rtn) {
	if(!rtn) return;
	var cmpIds=$("#cmpIds");
	var cmpNames=$("#cmpNames");
	var taskOpinion=$("#opinion").val();
	var informType=$.getChkValue("informType");
	
	var params= {cmpIds:cmpIds.val(),
				 cmpNames:cmpNames.val(),
				 opinion:taskOpinion,
				 informType:informType,
				 taskId:taskId,
				 formData:formData};
	var url="${ctx}/platform/bpm/task/toStartCommunication.ht";
	$.post(url,params,function(msg){
		var obj=new com.hotent.form.ResultMessage(msg);
		if(obj.isSuccess()){
			 $.ligerDialog.success("任务沟通意见成功！",function(){
	    		 dialog.close(); 
	    	 });
		}else{
			 $.ligerDialog.error("任务沟通意见失败！"+obj.getMessage());
		}
	});
}


function save(){
	var rtn=$("#frmComm").form().valid();
	if(!rtn) return;
 	$.ligerDialog.confirm("确定发送沟通意见？",callBack);
 }
 
function dlgCallBack(userIds, fullnames) {
 	if (userIds.length > 0) {
		var cmpIds=$("#cmpIds");
		var cmpNames=$("#cmpNames");
		cmpIds.val(userIds);
		cmpNames.val(fullnames);
	}
};

function add() {
	UserDialog({
		selectUserIds:$("#cmpIds").val(),
	    selectUserNames:$("#cmpNames").val(),
		callback : dlgCallBack,
		isSingle : false
	});
}

</script>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
			<span class="tbar-label">任务沟通</span>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link run" id="dataFormSave" href="javascript:;" onclick="save()"><span></span>沟通</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link close" href="javascript:;" onclick="dialog.close();"><span></span>关闭</a></div>
			</div>
		</div>
	</div>
	<div class="panel-body">
		<form id="frmComm">
		<table class="table-detail" cellpadding="0" cellspacing="0" border="0">

			<tr>
				<th nowrap="nowrap">沟通人员:</th>
				<td>
					<input type="hidden" id="cmpIds" /> 
					<textarea id="cmpNames"  cols="50" style="width:300px"  rows="2" class="textarea" readonly="readonly" validate="{required:true}"></textarea>
					<a class="link grant" onclick="add(this);"><span>选择人员</span></a>
				</td>
			</tr>
			<tr>
				<th>提醒消息方式:</th>
				<td>
						<c:forEach items="${handlersMap}" var="item">
					   <input type="checkbox" name="informType" value="${item.key }"  <c:if test="${item.value.isDefaultChecked}">checked="checked"</c:if> />
			            ${item.value.title }
					</c:forEach>
				</td>
			</tr>
			<tr>
				<th>沟通内容:</th>
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