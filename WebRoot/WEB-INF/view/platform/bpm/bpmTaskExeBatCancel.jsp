<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<%@include file="/commons/include/form.jsp" %>

	<title>批量取消转办(代理)</title>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript">
	/*KILLDIALOG*/
	var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)
	
		var ids="${param.ids}";
	
		$(function() {
			$("#dataFormSave").click(save);
		});
		
		function callBack(rtn) {
			if(!rtn) return;
			var opinion=$("#opinion").val();
			var informType=$.getChkValue("informtype");
			var params= {ids:ids,opinion:opinion,informType:informType};
			$.post(__ctx+"/platform/bpm/bpmTaskExe/cancelBat.ht",params,function(msg){
				var obj=new com.hotent.form.ResultMessage(msg);
				if(obj.isSuccess()){
					$.ligerDialog.successExt('提示','批量取消转办(代理、流转)成功!',obj.getMessage(),function(){
						 //window.returnValue="ok";
						dialog.get('sucCall')("ok");
			    		 dialog.close(); 
			    	});
				}else{
					 $.ligerDialog.error(obj.getMessage(),'提示');
				}
			});
		}

		function save(){
			var rtn=$("#bpmTaskExeForm").form().valid();
			if(!rtn) return;
		 	$.ligerDialog.confirm('确认批量取消转办(代理、流转)吗？','提示',callBack);
		 }
	</script>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
			<span class="tbar-label">
				批量取消转办(代理)
			</span>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" href="javascript:;"><span></span>取消</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link close" href="javascript:;" onclick="dialog.close()" ><span></span>关闭</a></div>
			</div>
		</div>
	</div>
	<div class="panel-body">
		<form id="bpmTaskExeForm" method="post" >
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<th width="20%">发送提醒消息: </th>
					<td>
						<c:forEach items="${handlersMap}" var="item">
						   <input type="checkbox" name="informType" value="${item.key }"  <c:if test="${item.value.isDefaultChecked}">checked="checked"</c:if> />
				            ${item.value.title }
						</c:forEach>
					</td>
				</tr>
				<tr>
					<th width="20%">取消原因: </th>
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