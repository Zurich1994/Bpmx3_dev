
<%--
	time:2011-11-16 16:34:16
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>表单预览</title>
<%@include file="/commons/include/customForm.jsp" %>
<script type="text/javascript">
$(function() {
	function showRequest(formData, jqForm, options) { 
		return true;
	} 
	
	var win;
	$("a.save").click(function() {
		var rtn=CustomForm.validate();
		if(rtn){
			var data=CustomForm.getData();
			//设置表单数据
			$("#txtJsonData").val(data);
			if(!win){
				var obj=$("#divJsonData");
				win= $.ligerDialog.open({ target:obj , height: 300,width:510, modal :true}); 
			}
			win.show();
		}
		
	});
});

function closeWin(){
	
	window.close();
}
</script>


</head>
<body>
	<div>
		<div class="tbar-title">
			<span class="tbar-label">表单预览</span>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group">
					<a class="link save" id="dataFormSave" href="javascript:;"><span></span>查看数据</a>
				</div>
				<div class="l-bar-separator"></div>
				<div class="group">
					<a class="link close" href="javascript:closeWin()"><span></span>关闭</a>
				</div>
			</div>
		</div>
	</div>
	<div id="divJsonData" style="display: none;">
		<textarea id="txtJsonData" rows="10" cols="80" style="height: 225px;width:480px"></textarea>
	</div>
	<form >
		<div type="custform">
			<div class="panel-detail">
				${html}
			</div>
		</div>
		
	</form>
	
</body>
</html>

