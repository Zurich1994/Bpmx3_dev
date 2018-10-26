<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<%@include file="/commons/include/get.jsp" %>
<title>终止意见</title>
<script type="text/javascript">
/*KILLDIALOG*/
var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)

 function save(){
	 var opinion=$("#opinion").val();
	 if(opinion==""){
		 $.ligerDialog.error('终止意见不能为空!','提示');
		 return;
	 }
 	 $.ligerDialog.confirm('确认终止吗？','提示',function(rtn) {
		if(rtn) {
		 	//window.returnValue=opinion;
			dialog.get("sucCall")(opinion);
	   		dialog.close();
		}
	 });
 }
</script>
</head>

<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">终止意见</span>
			</div>
			<div class="panel-toolbar">
					<div class="toolBar">
						<div class="group"><a class="link save" id="dataFormSave" href="javascript:;" onclick="save()"><span></span>确定</a></div>
						<div class="l-bar-separator"></div>
						<div class="group"><a class="link close" href="javascript:;" onclick="dialog.close();"><span></span>关闭</a></div>
					</div>
				</div>
		</div>
		<div class="panel-body">
			终止原因：<textarea rows="6" cols="45" id="opinion" name="opinion"></textarea>
		</div>
	</div>
</body>
</html>