<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<%@include file="/commons/include/form.jsp"%>
<title>设置条件脚本</title>
<script type="text/javascript"src="${ctx}/js/hotent/platform/bpm/FlowVarWindow.js"></script>
<script type="text/javascript"src="${ctx}/js/hotent/platform/system/ScriptDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/RuleDialog.js" ></script>
<script type="text/javascript" src="${ctx}/js/javacode/codemirror.js"></script>
<script type="text/javascript"src="${ctx}/js/hotent/platform/system/ConditionScriptAddDialog.js"></script>
<script type="text/javascript"src="${ctx}/js/hotent/platform/system/ConditionScriptDialog.js"></script>

<link  rel="stylesheet" type="text/css" href="${ctx}/js/codemirror/lib/codemirror.css" >
<script type="text/javascript" src="${ctx}/js/codemirror/lib/codemirror.js"></script>
<script type="text/javascript" src="${ctx}/js/codemirror/mode/groovy/groovy.js"></script>
<style type="text/css">
	.CodeMirror{
		font-size:medium;
		_position:relative;
	}
</style>
<script type="text/javascript">
	/*KILLDIALOG*/
	var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)
		
	//var winArgs = window.dialogArguments;
	var winArgs = dialog.get("data");
	
	var defId = winArgs.defId;
	var editor=null;
	
	$(function(){
		var width = $("#scritp").width();
		var height = $("#script").height();
		editor = CodeMirror.fromTextArea(document.getElementById("script"), {
			mode: "text/x-groovy",
			lineWrapping:true,
			lineNumbers: true
		 });
		editor.setSize(width,height);
		if(winArgs.script){
			editor.setValue(winArgs.script);
		}
	});

	function addCondScript(obj){
		var _this = $(obj);
		ConditionScriptAddDialog({
			data:{
				defId:defId
			},
			callback:addScriptCallBack
		});
	};
	function addScript(obj){
		var _this = $(obj);
		ScriptDialog({
			callback:function(data){
				var pos=editor.getCursor();
				editor.replaceRange(data,pos);
			}
		});
	};

	function addScriptCallBack(data){
		var script = data.script;
		var inst = script.classInsName;
		var method = script.methodName;
		var str = "return "+inst +"."+method+ "( ";
		var paramStr="";
		for(var i=0 ; i<script.argument.length; i++){
			var p=script.argument[i];
			switch(p.paraValType){
			case '1':
				paramStr += p.paraVal+" , " ;
				break;
			case '2':
				if(p.paraType.indexOf("String")>0){
					paramStr += "\"" + p.paraVal+ "\" , " ;
				}else{
					paramStr +=  p.paraVal+ " , " ;
				}
				break;
			}
		}
		if(paramStr){
			paramStr = paramStr.substring(0,paramStr.length-2);
		}
		str += paramStr+");" ;

		var pos=editor.getCursor();
		editor.replaceRange(str,pos);
	};
	
	/**
	* OK事件处理
	*/
	function onOk(obj){
		var _this = $(obj);
		var condition = getConditionContent();
		var winRtn = {
			status:1,
			condition:condition
		};
		//window.returnValue = winRtn;
		dialog.get("sucCall")(winRtn);
		
		dialog.close();
		
	};
	/**
	* 获取脚本内容
	*/
	function getConditionContent(){
		editor.save();
		var condition = $("#script").val();
		return condition;
	};
	
</script>
</head>
<body>
	<div class="panel" >
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">设置条件脚本</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link ok" onclick="onOk(this)"><span></span>确定</a>
					</div>
					<div class="l-bar-separator"></div>
					<div class="group">
						<a class="link del" href="javascript:;" onclick="dialog.close()"><span></span>关闭</a>
					</div>

				</div>
			</div>
		</div>
		<div class="panel-body">
			<form id="bpmNodeRuleForm" method="post" action="save.ht">
				
					<table class="table-detail" cellpadding="0" cellspacing="0" border="0"  >
						<tr>
							<th rowspan="2" width="20%">脚本:</th>
							<td>
								<div style="padding: 5px">
									<a href="javascript:;" onclick="addCondScript(this)" class="link var" title="常用条件脚本">常用条件脚本</a>
									<a href="javascript:;" onclick="addScript(this)" class="link var" title="常用脚本">常用脚本</a>
								</div>
							</td>
						</tr>
						<tr >
							<td>
								<textarea  id="script" style="width: 100px;height: 100px" ></textarea>
							</td>
						</tr>
					</table>
			</form>
		</div>
	</div>
</body>
</html>
