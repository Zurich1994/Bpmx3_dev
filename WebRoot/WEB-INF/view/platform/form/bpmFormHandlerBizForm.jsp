
<%--
	time:2011-11-16 16:34:16
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>编辑表单</title>
<%@include file="/commons/include/customForm.jsp" %>
<script type="text/javascript">
$(function() {
	function showRequest(formData, jqForm, options) { 
		return true;
	} 
	initSubForm();
	$("a.save").click(saveHandler);
	
});

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
	}
}

function initSubForm(opitons){
	opitons=$.extend({},{success:showResponse },opitons);
	$('#frmData').ajaxForm(opitons); 
}

function showResponse(responseText){
	var obj=new com.hotent.form.ResultMessage(responseText);
	if(obj.isSuccess()){
		location.href="${returnUrl}";
	}else{
		alert(obj.getMessage());
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
					<a class="link save" id="dataFormSave" href="javascript:;">保存</a>
					<a class="link back" href="javascript:history.back();">返回</a>
				</div>
			</div>
		</div>
	</div>
	<form id="frmData" name="frmData" method="post" action="${ctx}/platform/form/bpmFormHandler/save.ht">
		<input type="hidden" name="formData" id="formData" />
		<input type="hidden" name="${pkField }"  id="${pkField }"  value="${id }" />
		<div type="custform">
			${bpmFormDef.html}
		</div>
	</form>
	
</body>
</html>

