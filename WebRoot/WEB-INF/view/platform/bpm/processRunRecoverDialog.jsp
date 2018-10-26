<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<%@include file="/commons/include/get.jsp" %>
<title>撤销原因</title>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerMsg.js"></script>
<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerDialog.js" ></script>
<script type="text/javascript">
/*KILLDIALOG*/
var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)

var callBack=function(rtn){
	 if(!rtn) return;
	 var json=$("#frmRecover").serialize() ;
	
	 var url=__ctx +"/platform/bpm/processRun/recover.ht";
	 $.post(url,json,function(responseText){
		 var obj=new com.hotent.form.ResultMessage(responseText);
		 if(obj.isSuccess()){
			 $.ligerDialog.success(obj.getMessage(),'提示',function(){
				 //window.returnValue=1;
				 dialog.get('sucCall')(1);
				 dialog.close();
			 });
		 }
		 else{
			 $.ligerDialog.err('提示','保存失败!',obj.getMessage());
		 }
	 });
};

$(function(){
	 $("a.save").click(function(){	
		 if($.isEmpty( $('#opinion').val())){
			 $.ligerDialog.warn('请填写撤销原因！','提示');
			 $('#opinion').focus();
			 return;
		 }
		 $.ligerDialog.confirm('确认撤销此流程吗?','提示',callBack);
	 });
})
</script>
</head>
<body>
	<div class="panel">
		
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">撤销原因</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link save" id="dataFormSave" href="javascript:;"><span></span>撤销</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link close" href="javascript:;" onclick="dialog.close();"><span></span>关闭</a></div>
				</div>
			</div>
		</div>
		
		<div class="panel-body">
			<form id="frmRecover">
				<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
					<tr>
						<th nowrap="nowrap">发送方式:</th>
						<td>
						<c:forEach items="${handlersMap}" var="item">
							    <input type="checkbox" name="informType" value="${item.key }"  <c:if test="${item.value.isDefaultChecked}">checked="checked"</c:if> />
					            ${item.value.title }
						    </c:forEach>
						</td>
					</tr>
					<tr>
						<th>撤销原因:</th>
						<td>
							<textarea rows="7" cols="61" style="width:350px;" id="opinion" name="opinion"></textarea>
							<input type="hidden" name="runId" value="${runId }"/>
							<input type="hidden" name="backToStart" value="${backToStart }"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>