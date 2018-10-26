<%--
	time:2011-12-14 08:41:55
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>人员部门选择器设置</title>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="${ctx}/js/conf/orgUserConf.js"></script>
	<script type="text/javascript" src="${ctx}/js/javacode/codemirror.js"></script>
	<script type="text/javascript" src="${ctx}/js/javacode/InitMirror.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/ScriptDialog.js" ></script>
	<script type="text/javascript">
	/*KILLDIALOG*/
	var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)
	$(function(){ 
		handOrgUser();
		$("a.save").click(function(){
			InitMirror.save();
			var opt=$("select#fromType").find('option:selected');
			var type = opt.attr("type");
			var typeVal = null;
			if(type == 'script'){
				typeVal = $("#fromScript").val();
			}else{
				typeVal = opt.val();
			}
			var scope ={};
			scope.type = type;
			scope.value = typeVal;
			$("#scope").val(JSON2.stringify(scope).replaceAll("\"","#@"));
			$("#bpmNodeSetForm").ajaxForm({success:showResponse });
			if($("#bpmNodeSetForm").valid()){
				$("#bpmNodeSetForm").submit(); 
			} 
		});
		init();
	})
	
	function showResponse(responseText) {
		var obj = new com.hotent.form.ResultMessage(responseText);
		if (obj.isSuccess()) {
			$.ligerDialog.success( obj.getMessage(),$lang.tip.confirm, function(rtn) {
				if(rtn){
					dialog.close();
				}
			});
		} else {
			$.ligerDialog.error(obj.getMessage(),$lang.tip.error);
		}
	}
	
	//初始化
	function init(){
		var scope = $("#scope").val();
		scope=scope.replaceAll("#@","\"");
		if(scope ){
			var JsonObj = JSON2.parse(scope);
			var type = JsonObj.type;
			var typeVal=JsonObj.value;
			if(type == 'script'){
				$("#fromType").val("");
				$("#fromTypeScriptTr").show();
				$("#fromScript").val(typeVal);
			}else{
				if(typeVal){
					$("#fromType").val(typeVal);
				}
			}
		}
	}
	
	function changeValue(conf){
		var me = $(conf);
		var opt = me.find('option:selected');
		var type = opt.attr("type");
		if(type =='script'){
			var fromTypeScriptTr = $("#fromTypeScriptTr").show();
		}else{
			var fromTypeScriptTr = $("#fromTypeScriptTr").hide();
		}
	}
	function handOrgUser(){
		 var selectObj = $("#fromType");
		 for(var i=0 ; i<orgUserFlowJson.length;i++){
			 var json = orgUserFlowJson[i];
			 if(json){
			 var opt = $("<option>").val(json.value).text(json.title);
			 opt.attr("type",json.type);
			 selectObj.append(opt);
			}
		 }
 	}
	function selectScript() {
	    ScriptDialog({
	        callback: function(script) {
	            InitMirror.editor.insertCode(script);
	        }
	    });
	}
	//关闭页面
	function closeDialog(){
		dialog.close();
	};
	</script>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">人员部门选择器设置</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link save" href="#"><span></span>保存</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link close"  onclick="closeDialog();"  href="#"><span></span>返回</a></div>
				</div>
			</div>
		</div>
		<div class="panel-body">
			<div class="panel-detail">
				<form id="bpmNodeSetForm" method="post" action="savaScope.ht">
					<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
						<tr id="fromTypeTr" >
							<th width=120>
								人员选择范围:
							</th>
							<td colspan="3">
								<select id="fromType" name="fromType" onchange="changeValue(this)">
								</select>
							</th>
						</tr>
						<tr id="fromTypeScriptTr" style="display:none;">
							<th width=120>
								脚本:
							</th>
							<td colspan="3">
								<a href="javascript:;" onclick="selectScript()" id="btnScript" class="link var" title="常用脚本">常用脚本</a>
								<br/>脚本中要使用到其他字段参与运算， 请使用“[字段名]”方式引用。返回一个组织ID<br />
								<textarea id="fromScript" codemirror="true" name="fromScript" rows="6" cols="70"></textarea>
							</th>
							<input type="hidden" id="setId" name="setId" value="${bpmNodeSet.setId}" />
							<input type="hidden" id="scope" name="scope" value='${bpmNodeSet.scope}' />
						</tr>
					</table>
				</form>
			</div>
		</div>
</div>

</body>
</html>
