<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<%@include file="/commons/include/form.jsp"%>
<title>脚本设置</title>
<script type="text/javascript"src="${ctx}/js/hotent/platform/system/PersonScriptAddDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/javacode/codemirror.js"></script>
<script type="text/javascript" src="${ctx}/js/javacode/InitMirror.js"></script>
<script type="text/javascript">
/*KILLDIALOG*/
var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)
var defId =${defId};
//var cmpNames = window.dialogArguments.cmpNames;
var cmpNames =dialog.get("cmpNames");
$(function(){
	handFlowVars();
	$("textarea#txtScriptData").val(cmpNames);
});

	function selectScript() {
		var valueTemp = InitMirror.editor.getCode();
		//window.returnValue={returnVal:valueTemp};
		var rtn={returnVal:valueTemp};
		dialog.get("sucCall")(rtn);
		dialog.close();
	}
	
	function addPersonScript(obj){
		var _this = $(obj);
		PersonScriptAddDialog({
			data:{
				defId:defId
			},
			callback:addScriptCallBack
		});
	};

	function addScriptCallBack(data){
		var str=PersonScriptParser(data);
		InitMirror.editor.insertCode(str);
	};
	
	function handFlowVars(){
		$("select[name='selFlowVar']").change(function(){
			var val=$(this).val();
			InitMirror.editor.insertCode(val);
		});
	}
</script>
</head>
<body>
	<div class="panel">
		<div class="hide-panel">
			<div class="panel-top">

				<div class="panel-toolbar">
					<div class="toolBar">
						<div class="group">
							<a class="link save" onclick="selectScript()"><span></span>选择</a>
						</div>
						<div class="l-bar-separator"></div>
						<div class="group">
							<a class="link del" onclick="javasrcipt:dialog.close()"><span></span>关闭</a>
						</div>
						<div class="l-bar-separator"></div>
					</div>
				</div>
			</div>
		</div>
		<div class="panel-body">
			<div id="divScriptData" >
				<a href="javascript:;" id="btnScript" class="link var"
					title="常用脚本"
					onclick="addPersonScript()">常用脚本</a>
					&nbsp;&nbsp;表单变量:<f:flowVar defId="${defId}" controlName="selFlowVar" parentActDefId="${parentActDefId}"></f:flowVar>
				<ul>
					<li>表达式必须返回Set&lt;String&gt;集合类型的数据,数据项为用户ID。</li>
				</ul>
				<textarea id="txtScriptData" codemirror="true" mirrorheight="200px"
					name="txtScriptData" rows="20" cols="80" style="height: 95%;width:98%" class="inputText">${cmpNames}</textarea>
			</div>
		</div>
		<input type="hidden" id="defId" value="${defId}" />
	</div>
</body>
</html>