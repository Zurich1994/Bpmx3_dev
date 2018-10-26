<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<%@include file="/commons/include/form.jsp" %>
	<title>取消流转</title>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/formdata.js"></script>
	<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerDialog.js"></script>
	
	<script type="text/javascript">
		/*KILLDIALOG*/
		var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)
	
		//var userId = window.dialogArguments.userId;
		//var	taskId = window.dialogArguments.taskId;\
		var userId = dialog.get("params").userId;
		var	taskId = dialog.get("params").taskId;
			
		$(function() {
			$("#dataFormSave").click(save);
		});
		
		function callBack(rtn) {
			if(!rtn) return;
			var taskOpinion=$("#opinion").val();
			var informType=$.getChkValue("informType");
			var params= {taskId:taskId,userId:userId,opinion:taskOpinion,informType:informType};
			$.post("${ctx}/platform/bpm/bpmProTransTo/cancel.ht",params,function(msg){
				var obj=new com.hotent.form.ResultMessage(msg);
				if(obj.isSuccess()){
					 $.ligerDialog.success('取消流转任务成功!','提示',function(){
						 //window.returnValue="ok";
						 dialog.get("sucCall")("ok");
						 
			    		 dialog.close(); 
			    	 });
				}else{
					 $.ligerDialog.err('提示','取消流转任务失败!',obj.getMessage());
				}
			});
		}


		function save(){
			var rtn=$("#bpmProTransToForm").form().valid();
			if(!rtn) return;
		 	$.ligerDialog.confirm('确认取消流转任务吗？','提示',callBack);
		 }
	</script>
</head>
<body>
<div class="panel">
<div class="hide-panel">
	<div class="panel-top">
		<div class="tbar-title">
			<span class="tbar-label">取消流转</span>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" href="javascript:;"><span></span>取消</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link close" href="javascript:;" onclick="dialog.close()" ><span></span>关闭</a></div>
			</div>
		</div>
	</div>
	</div>
	<div class="panel-body">
		<form id="bpmProTransToForm" method="post" >
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<th width="20%" nowrap="nowrap">发送提醒消息： </th>
					<td>
						<input type="hidden" name="informType" />
					<c:forEach items="${handlersMap}" var="item">
						   <input type="checkbox" name="informType" value="${item.key }"  <c:if test="${item.value.isDefaultChecked}">checked="checked"</c:if> />
				            ${item.value.title }
						</c:forEach>
					</td>
				</tr>
				<tr>
					<th width="20%">取消原因： </th>
					<td>
						<textarea rows="5" cols="50" id="opinion"  validate="{required:true}" ></textarea>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>
</body>
</html>