<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/commons/include/get.jsp"%>
<f:js pre="hotent/lang/view/platform/system" ></f:js>
<title>人员脚本选择对话框</title>
<script type="text/javascript">
		/*KILLDIALOG*/
		var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)
		$(function(){
			$('#btnSelect').click(function(){
					if($("#scriptListFrame").contents().find("input:[name='id']:radio:checked").length == 0){
						$.ligerDialog.warn($lang_system.personScript.dialog.validate_msg_select,'提示');
						return false;
					}else{
						var selected = $("#scriptListFrame").contents().find("input:[name='id']:radio:checked");
						var id = selected.val();
						var name = selected.attr("methodDesc");
						var retVal={
								id:id,
								name:name
						};
						//window.returnValue=retVal;
						
						dialog.get("sucCall")(retVal);
						dialog.close();
					}
			});
			
			$('#btnClose').click(function(){
				dialog.close();
			});
		});
	</script>
</head>
<body>
	<div class="panel">
		<div style="height:450px;">
			<iframe id="scriptListFrame" name="scriptListFrame" height="90%"
				width="100%" frameborder="0"
				src="${ctx}/platform/system/personScript/selector.ht"></iframe>
		</div>

		<div position="bottom"  class="bottom" style="height:30px;" >
			<a href="javascript:;" id="btnSelect" class="button"  style="margin-right:10px;" ><span class="icon ok"></span><span class="chosen">选择</span></a>
			<a href="javascript:;" id="btnClose"  class="button"  onclick="dialog.close()" style="margin-left:10px;" ><span class="icon cancel" ></span><span class="chosen" >取消</span></a>
		</div>
	</div>
	<!-- end of panel -->
</body>
</html>


