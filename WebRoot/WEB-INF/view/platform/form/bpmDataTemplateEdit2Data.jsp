<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>编辑表单</title>
<%@include file="/commons/include/customForm.jsp" %>
<script type="text/javascript">
/*KILLDIALOG*/
var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)、

$(function() {
	function showRequest(formData, jqForm, options) { 
		return true;
	} 
	initSubForm();
	$("a.save").click(saveHandler);
	
});

//window.returnValue = "close";

function saveHandler(){
	var rtn=CustomForm.validate();
	if(rtn){
		//Office控件提交。
		OfficePlugin.submit();
		//WebSign控件提交。
		WebSignPlugin.submit();
		var data=CustomForm.getData();
		//设置表单数据
		$("#formData").val(data);
		$('#frmData').submit();
	}else{
		$.ligerDialog.warn("表单验证不成功,请检查表单是否正确填写!","提示信息");
	}
}

function initSubForm(opitons){
	opitons=$.extend({},{success:showResponse },opitons);
	$('#frmData').ajaxForm(opitons); 
}

function showResponse(responseText){
	var obj=new com.hotent.form.ResultMessage(responseText);
	if(obj.isSuccess()){
		$.ligerDialog.confirm( obj.getMessage()+",是否继续操作","提示信息", function(rtn) {
			if(!rtn){
				dialog.get("sucCall")();
				dialog.close();
			}
		});
	}else{
		alert(obj.getMessage());
	}
}

	
	/*//刷新原来的当前的页面信息
	locationPrarentPage();*/
	
	/*if(rtn!=undefined){
		location.href=location.href.getNewUrl();
	}*/
}
</script>
<script type="text/javascript" src="${ctx}/js/hotent/ajaxgrid.js"></script>

</head>
<body>
	<div class="panel-top">
		<div class="tbar-title">
			<span class="tbar-label">
				<c:choose>
					<c:when test="${hasPk}">
						编辑${tableName}
					</c:when>
					<c:otherwise>
						添加${tableName}
					</c:otherwise>
				</c:choose>
			</span>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">

				<div class="group"><a class="link add" href="javascript:;" action="${eurl}" onclick="openLinkDialog({scope:this,isFull:true})"><span></span>设备属性管理</a></div>
				<div class="l-bar-separator"></div>
				<div class="group">
					<a class="link close" href="javascript:close();"><span></span>关闭</a>
				</div>
			</div>
		</div>
	</div>
	<form id="frmData" name="frmData" method="post" action="${ctx}/platform/form/bpmFormHandler/save.ht">
	<!-- 
		<div type="custform" style="overflow:auto">
			${bpmFormDef.html}
		</div> -->
		<input type="hidden" name="formData" id="formData" />
		<input type="hidden" name="${pkField}"  id="${pkField}"  value="${id}" />
		<input id='tableId' name='tableId' type='hidden' value='${tableId}' />
		<input id='tableName' name='tableName' type='hidden' value='${tableName}'/>
	</form>
	
</body>
</html>

