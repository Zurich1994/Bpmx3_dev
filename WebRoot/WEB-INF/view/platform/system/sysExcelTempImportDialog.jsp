<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>导入 Excel数据</title>
<%@include file="/commons/include/form.jsp"%>
<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/formdata.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/subform.js"></script>
<script type="text/javascript">
	var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)
	function showResponse(responseText) {
		var obj = new com.hotent.form.ResultMessage(responseText);
		if (obj.isSuccess()) {
			$.ligerDialog.alert("导入成功！","提示信息", function(rtn) {
				var delOld = $("#delOld").val();
				dialog.get('sucCall')(delOld,obj.getMessage());
				dialog.close();
			});
		} else {
			$.ligerDialog.error(obj.getMessage(), "提示信息");
		}
	}
	
	// 导入excel数据模板
	function importTempData(){
		if($("#file").val()==""){
			$.ligerDialog.warn("请先选择模板Excel文件!","提示");
			return ;
		}
		var url = "${ctx}/platform/system/sysExcelTemp/importTempData.ht";
		url=url.getNewUrl();
		$("#sysExcelTempForm").attr("enctype","multipart/form-data");
		$("#sysExcelTempForm").attr("action",url);
		var options={};
		if(showResponse){
			options.success=showResponse;
		}
		var frm=$('#sysExcelTempForm').form();
		frm.ajaxForm(options);
		frm.submit();
	}
	
	
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link save" id="dataFormSave" href="#" onclick="importTempData();"><span></span>导入</a>
					</div>
					<div class="l-bar-separator"></div>
					<div class="group">
						<a class="link back" href="javascript:dialog.close();"><span></span>关闭</a>
					</div>
				</div>
			</div>
		</div>
		<div class="panel-body">
			<form id="sysExcelTempForm" method="post" action="save.ht">
				<table class="table-detail" cellpadding="0" cellspacing="0"
					border="0" type="main">
					<tr>
						<th width="20%">是否清除原来数据:</th>
						<td>
							<select id="delOld" class="inputText">
								<option value="true">是</option>
								<option value="false">否</option>
							</select>
						</td>
					</tr>
					<tr>
						<th width="20%">上传数据:</th>
						<td>
							<input type="file" name="file" id="file" class="inputText" style="width:350px;"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<p>
		<br/>
	</p>
	<p>
		<br/>
	</p>
</body>
</html>
