<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<%@include file="/commons/include/form.jsp"%>
<title>脚本规则</title>
<style type="text/css">
html,body {
	overflow: auto;
}

.para-info-table thead tr th {
	text-align: center;
}

.para-info-table tbody tr td {
	padding: 5px;
}

input {
	width: 180px;
	height: 21px;
}
</style>
<script type="text/javascript" src="${ctx}/js/javacode/codemirror.js"></script>
<script type="text/javascript" src="${ctx}/js/javacode/InitMirror.js"></script>
<script type="text/javascript">
	/*KILLDIALOG*/
	var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)
	//var param = window.dialogArguments.param;
	var param = dialog.get("data").param;
	
	//var jsondata = window.dialogArguments.data;
	var jsondata = dialog.get("data").data;
	$(function(){
		if(typeof(jsondata)!='undefined'){
			$("#descp").val(jsondata.descp);
			$("#script").text(jsondata.script);
			$("#enable").val(jsondata.enable);
			$("#sort").val(jsondata.sort);
			$("#id").val(jsondata.id);
		} 
	});
	
	
	function saveData(){
		InitMirror.save();
		var tempScript=InitMirror.editor.getCode();
		var json={};
		json.descp=$("#descp").val();
		json.script=tempScript;
		json.enable=$("option:selected",enable).val();
		json.sort=$("#sort").val();
		json.id=$("#id").val();
		//window.returnValue={sysUrlRule:JSON2.stringify(json)};
		dialog.get("sucCall")({sysUrlRule:JSON2.stringify(json)});
		
		dialog.close();
	};
	function backUrl(){
		window.location.href = "${returnUrl}";
	};
	//验证脚本是否正确
	function testScript(){
		var script=InitMirror.editor.getCode();
		var winArgs = "dialogWidth=650px;dialogHeight=500px;help=0;status=0;scroll=1;center=1";
		var url = __ctx + '/platform/system/sysUrlRules/testScript.ht';
		url = url.getNewUrl();
		//var rtn = window.showModalDialog(url, { param : param ,script:script}, winArgs);
		
		/*KILLDIALOG*/
		DialogUtil.open({
			height:300,
			width: 650,
			title : '验证脚本是否正确',
			url: url, 
			isResize: true,
			//自定义参数
			data: { param : param ,script:script}
		});
	};
	
	
</script>
</head>
<body>
	<div position="center">
		<div class="panel">
			<div class="panel-top">
				<div class="tbar-title">
					<span class="tbar-label">脚本规则</span>
				</div>
				<div class="panel-toolbar">
					<div class="toolBar">
						<div class="group">
							<a class="link save" id="dataFormSave" href="javascript:saveData();"><span></span>保存</a>
						</div>
						<div class="l-bar-separator"></div>
						<div class="group">
							<a class="link back" onclick="javasrcipt:dialog.close();"><span></span>返回</a>
						</div>
						<div class="l-bar-separator"></div>
						<div class="group">
							<a class="link update" id="script_editor" href="javascript:testScript();"><span></span>脚本验证</a>
						</div>
						<div class="l-bar-separator"></div>
					</div>
				</div>
			</div>
			<div class="panel-body">
					<table class="table-detail" cellpadding="0" cellspacing="0"
						border="0">
						<input type="hidden" id="id" name="id"/>
						<tr>
							<th width="20%">脚本规则名称:</th>
							<td><input type="text" id="descp" name="descp" size="80"
								 class="inputText" validate="{required:true}" /></td>
						</tr>
						<tr>
							<th width="20%">脚本规则:</th>
							<td><textarea type="text" id="script" codemirror="true"
								mirrorheight="190px" name="script" rows="10" cols="80" validate="{required:true}"></textarea></td>
						</tr>
						<tr>
							<th width="20%">是否启用:</th>
							<td><select name="enable" id="enable" class="select"
								style="width: 50px !important">
									<option value="0">
										禁用
									</option>
									<option value="1">
										启用
									</option>
							</select></td>
						</tr>
						<tr>
							<th width="20%">优先级:</th>
							<td><input type="text" id="sort" name="sort" value="1" /></td>
						</tr>
					</table>
			</div>
		</div>
	</div>
</body>
</html>

