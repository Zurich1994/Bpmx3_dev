<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
	<%@include file="/commons/include/form.jsp" %>
	<title>脚本预览</title>
	<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerComboBox.js"></script>
	<script type="text/javascript" src="${ctx}/js/lg/plugins/htDicCombo.js"></script>
	<script type="text/javascript" src="${ctx}/js/javacode/codemirror.js"></script>
	<script type="text/javascript" src="${ctx}/js/javacode/InitMirror.js"></script>
	<script type="text/javascript"src="${ctx}/js/hotent/platform/system/ScriptDialog.js"></script>
	<script type="text/javascript">
	/*KILLDIALOG*/
	var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)    
	
	    $(function(){
	    	var script = '';
	    	var type = 'list';
	    	//confObj = window.dialogArguments;
	    	confObj = dialog.get('conf');
	    	if(confObj!=undefined && confObj!=null && confObj!=""){   
	    		script = confObj.scriptComten;
		    	$("#scriptComten").val(script);
		    	type = confObj.type;
                //是编辑进入的，可以有返回修改的代码 
		    	if(type=='edit'){
                	$('a.ok').css("display","block");
                }
	    	}
	    	
	    	//初始化脚本编辑器
			$("#script_editor").click(function(){
				ScriptDialog({
					callback:function(obj){
						InitMirror.editor.setCode(obj);
					}
				});
			});
	    	
	    	//自动执行脚本预览
	    	preView("init");

		});
		
		function preView(str){
			var scriptComten = '';
			$("#resultView").html("");
			if(str!='init'){
				//先保存脚本编辑器内容到scriptComten
				InitMirror.save();
			}
			scriptComten = $("#scriptComten").val();
			if(scriptComten==undefined||scriptComten.length<=0){
				$.ligerDialog.warn("脚本内容不能为空！");
				return;
			}
			var url=__ctx +"/platform/system/aliasScript/customPreview.ht";
			$.post(url,{scriptComten:scriptComten},function(data){
				var json = eval("("+data+")");
				if(json.isSuccess==0){
					$("#resultView").html(data);
				}else{
					$.ligerDialog.warn(json.msg);
				}
			});
		}
		
		function returnScriptComten(){
			//先保存脚本编辑器内容到scriptComten
			InitMirror.save();
			//再获取脚本内容scriptComten，并返回
			var scriptComten = $("#scriptComten").val();
			//window.returnValue = scriptComten;
			dialog.get('sucCall')(scriptComten);
			dialog.close();
		}
		
		
	</script>
	
	<style type="text/css">
		thead th{
			text-align: left!important;
			padding-left: 5px;
		}
	</style>
</head>

<body style="overflow-x: hidden;">
	<div class="panel">
		<div class="hide-panel">
			<div class="panel-top">
				<div class="tbar-title">
					<span class="tbar-label">脚本预览</span>
				</div>
				<div class="panel-toolbar">
					<div class="toolBar">
						<div class="group">
							<a class="link search" id="btnSearch" onclick="preView('preView');"><span></span>预览</a>
						</div>
						<div class="l-bar-separator"></div>
						<div class="group">
							<a class="link ok" id="btnSearch" style="display:none;" onclick="returnScriptComten();" ><span></span>确认</a>
						</div>
						<div class="l-bar-separator"></div>
						<div class="group">
							<a class="link close"  href="javascript:;" onclick="dialog.close();"><span></span>关闭</a>
						</div>
						<div class="l-bar-separator"></div>
					    <div class="group">
							<a class="link update" id="script_editor" href="javascript:;"><span></span>脚本编辑器</a>
					    </div>
					</div>	
				</div>
			</div>
		</div>
		
		<div class="panel-body">	
			<table id="tabPreView"  class="table-detail">
				<tr>
				    <td>
						脚本内容：
						<div id="scriptComtenView" style="height:250px;width:780px;border: 1px solid silver;overflow: auto;">
						     <textarea type="text" id="scriptComten" codemirror="true"
											mirrorheight="200px" name="scriptComten" rows="10" cols="200"></textarea>
						</div>
						
			   		</td>
                </tr>
				
                <tr>
				    <td>
				   		返回结果：
				   		<div id="resultView" style="height:200px;width:780px;border: 1px solid silver;overflow: auto;"><br/></div>
		   			</td>
                </tr>
                <tr>
				    <td>
				   		<div id="notice" style="height:50px;width:650px;">
						     <font color="red">
							               返回结果说明：返回的结果是以JSON字符串格式返回的内容，其分为isSuccess、msg和result三个部分；
							               其中isSuccess=0为成功返回信息，为其它值时获取信息失败；msg为信息提示的内容；
							     result为脚本执行后返回的数据内容。
						     </font>
						</div>
		   			</td>
                </tr>
		   	</table>
		</div>
	</div>
	
		
</body>
</html>


