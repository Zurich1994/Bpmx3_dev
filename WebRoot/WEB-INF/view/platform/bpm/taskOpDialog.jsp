<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<%@include file="/commons/include/form.jsp" %>
<title>审批意见</title>
<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
<script type="text/javascript">
/*KILLDIALOG*/
var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)

$().ready(function (){
	var isRequired = '${isRequired}';
	if(isRequired == '1'){
		$("#opinion").attr("validate","{required:true,maxLength:500}");
	}else{
		$("#opinion").attr("validate","{maxLength:500}");
	}
	// 设置初始值
	var voteContent = dialog.get("voteContent");
	$("#opinion").val(voteContent);
	
});

function save(){
	var rtn=$("#frmComm").form().valid();
	if(!rtn) return;
	var opinion = $("#opinion").val();
	var call = dialog.get("sucCall");
	call(opinion);
	dialog.close(); 
}

function setValue(val){
	$("#opinion").val(val);
}
 
</script>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
			<span class="tbar-label">审批意见</span>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link run" id="dataFormSave" href="javascript:;" onclick="save()"><span></span>确定</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link close" href="javascript:;" onclick="dialog.close()"><span></span>关闭</a></div>
			</div>
		</div>
	</div>
	<div class="panel-body">
		<form id="frmComm">
		<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
			<tr>
				<th>审批意见:</th>
				<td>
					<textarea rows="4" cols="50" style="width:300px" id="opinion" name="opinion"  maxLength="500"></textarea>
				</td>
			</tr>
			<tr>
				<th>常用语:</th>
				<td>
					<div style="word-break:break-all;overflow: auto;width:300px">
						<c:forEach var="item" items="${taskAppItems}">
							<a class="link reload" onclick="setValue('${item}')"><span></span>${item}</a>&nbsp;&nbsp;&nbsp;&nbsp;
						</c:forEach>
					</div>
				</td>
			</tr> 
		</table>
		</form>
	</div>
</div>
</body>
</html>