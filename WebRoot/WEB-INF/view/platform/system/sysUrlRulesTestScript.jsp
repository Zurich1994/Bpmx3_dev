<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<%@include file="/commons/include/form.jsp"%>
<title>脚本验证</title>
<script type="text/javascript">
	/*KILLDIALOG*/
	var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)

	//var param = window.dialogArguments.param;
	//var script =window.dialogArguments.script;
	var param = dialog.get("data").param;
	var script =dialog.get("data").script;
	
	$(function(){
		if(param){
			var params= new Array();
			params=param.split(",");
			for(var i=0;i<params.length;i++){
				var trObj=$("tr.hidden").clone(true);
				trObj.removeClass('hidden');
				trObj.attr("name","paramTr");
				var paramName=$(".hiddenlabel",trObj);
				paramName.removeClass("hiddenlabel");
				paramName.attr("name","paramName");
				paramName.text(params[i]);
				var paramValue=$("input[name='param']",trObj);
				paramValue.removeClass("hiddeninput");
				paramValue.attr("name","paramValue");
				$("tr.hidden").after($(trObj));
			}
		} 
	});
	
	function test(){
		var paramTr=$("tr[name='paramTr']");
		var paramJson={};
		for(var j=0;j<paramTr.length;j++){
			var me=$(paramTr[j]);
			var paramName=$("label[name='paramName']",me).text();
			var paramValue=$("input[name='paramValue']",me).val();
			paramJson[paramName]=paramValue;
		}
		$.post("testRule.ht",{params:JSON2.stringify(paramJson),script:script},function(data){
			var obj=new com.hotent.form.ResultMessage(data);
			if(obj.isSuccess()){
				var msg="";
				if(obj.getMessage()=='false'){
					msg='验证不通过';
				}else{
					
					msg='验证通过';
				}
				$("#resultTextarea").text(msg);
			}else{
				$.ligerDialog.error(obj.getMessage(),'提示');
			}
		});
	}
</script>
</head>
<body>
	<div position="center">
		<div class="panel">
			<div class="panel-top">
				<div class="tbar-title">
					<span class="tbar-label">脚本验证</span>
				</div>
				<div class="panel-toolbar">
					<div class="toolBar">
						
						<div class="l-bar-separator"></div>
						<div class="group">
							<a class="link back" onclick="javasrcipt:dialog.close();"><span></span>返回</a>
						</div>
						<div class="l-bar-separator"></div>
						<div class="group">
							<a class="link update" id="script_editor" href="javascript:test();"><span></span>验证</a>
						</div>
						<div class="l-bar-separator"></div>
					</div>
				</div>
			</div>
			<div class="panel-body">
					<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
						<tr class="hidden">
							<th width="20%"><label name="param" class="hiddenlabel"></label>:</th>
							<td><input type="text"  name="param" class="hiddeninput" /></td>
						</tr>
					</table>
					<span class="resultSpan">
						<textarea type="text" id="resultTextarea"  name="resultTextarea" style="width:99%" ></textarea>
					</span>
			</div>
		</div>
	</div>
</body>
</html>

