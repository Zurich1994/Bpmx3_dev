<%@page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<style type="text/css">
*{
	margin: 0;
	padding: 0;
}

.base {
	margin: 10px 10px 0 10px;
	border: 1px solid #ddd;
}

.base table {
	margin: 0 10px 0 10px;
}

.base table tr {
	height: 40px; 
}

.base table th {
	width: 120px;
	font-size: 13px;
}
</style>
<%@include file="/commons/include/form.jsp" %>
<script type="text/javascript" src="${ctx}/js/ueditor2/dialogs/internal.js"></script>
<script type="text/javascript">
	$(function() {
		$("#importForm").attr("action",__ctx + '/platform/form/bpmFormDef/importForm.ht');
	});

	dialog.onok = function() {
		var path = $('#importInput').val();
		if (/.*\.(html|htm|txt)$/gi.test(path)) {
			var options = {
				success : showResponse
			};
			$("#importForm").ajaxSubmit(options);
		} else {
			alert("请导入html、htm、txt文件!");
			return false;
		}
	};
	//初始化导入的内容
	function showResponse(responseText) {
		if(!parent.formContainer||!parent.tabControl)return;
		var formContainer=parent.formContainer,
			tabControl=parent.tabControl;
		result = responseText.split(/#content-title#/);
		
		var tabTitle=result[1],
			formData=result[0],
			aryForm = formData.split(formContainer.splitor);
		//没有tab标题，但是内容中有分页，则生成对应数目的标题
		if(!tabTitle){
			tabTitle=[];
			for(var i=0;i<aryForm.length;i++){
				tabTitle.push("页面"+(i+1));
			}
			tabTitle = tabTitle.join(formContainer.splitor);
		}
		
		var aryTitle = tabTitle.split(formContainer.splitor),			
			aryLen = aryTitle.length;
		formContainer.init(tabTitle, formData);
		tabControl.initPages(aryTitle);
		if (aryLen > 0) {
			editor.setContent(aryForm[0]);
		};
	};
</script>
</head>
<body>
	<fieldset class="base">
		<form id="importForm" name="importForm" method="post" enctype="multipart/form-data">
			<table>
				<tr>
					<th>选择表单文件:</th>
					<td><input type="file" id="importInput" name="importInput" size="20" /></td>
				</tr>
			</table>
		</form>
	</fieldset>
</body>
</html>