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
	if(frameElement){
		dialog = frameElement.dialog;
	}
	
	//dialog 为null时，删除关闭按钮,主要是树设置页面用到formDefTreeShow.jsp
	if(!dialog){
		$('.close').remove();
	}
});

//window.returnValue = "close";
function beforeSubmit(){
	var jsPreScript = $("#jsPreScript").val();
	if(jsPreScript != null && $.trim(jsPreScript) != ""){
		${sysBusEvent.jsPreScript}
	}
}

function saveHandler(){
	var rtn=CustomForm.validate();
	if(rtn){
		var rtn=beforeSubmit();
		if( rtn==false){
			return;
		}
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
	var jsAfterScript =  $("#jsAfterScript").val();
	if(jsAfterScript != null && $.trim(jsAfterScript) != ""){
		${sysBusEvent.jsAfterScript}
	}else{
	if(obj.isSuccess()){
			//有对话框才这样
			if(dialog){
		$.ligerDialog.confirm( obj.getMessage()+",是否继续操作","提示信息", function(rtn) {
			if(!rtn){
				dialog.get("sucCall")();
				dialog.close();
					}else{
						window.location.href = window.location.href;
			}
		});
			}else{//没对话框,目前就是formDefTreeShow.jsp
				$.ligerDialog.success(obj.getMessage(),"提示信息",function(){
					window.parent.refreshTheTree();//刷新左侧树
				});
			}
		}else{
			$.ligerDialog.err("出错了","系统保存数据错误!",obj.getMessage());
		}
	}
}

</script>


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
				<div class="group">
					<a class="link save" id="dataFormSave" href="javascript:;"><span></span>保存</a>
				</div>
				<div class="l-bar-separator"></div>
				<div class="group">
					<a class="link close" href="javascript:dialog.get('sucCall')();dialog.close();"><span></span>关闭</a>
				</div>
			</div>
		</div>
	</div>
	<form id="frmData" name="frmData" method="post" action="${ctx}/platform/form/bpmFormHandler/save.ht">
	
		<div type="custform" style="overflow:auto;width: 80%;margin: auto;">
			${bpmFormDef.html}
		</div>
		<input type="hidden" id="jsPreScript" value="${sysBusEvent.jsPreScript }" />
		<input type="hidden" id="jsAfterScript" value="${sysBusEvent.jsAfterScript }" />
		<input type="hidden" name="formData" id="formData" />
		<input type="hidden" name="${pkField}"  id="${pkField}"  value="${id}" />
		<input id='tableId' name='tableId' type='hidden' value='${tableId}' />
		<input id='alias' name='alias' type='hidden' value='${alias}' />
		<input id='tableName' name='tableName' type='hidden' value='${tableName}'/>
	</form>
	
</body>
</html>

