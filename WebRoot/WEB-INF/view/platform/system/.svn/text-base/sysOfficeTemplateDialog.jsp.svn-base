<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>选择office模板</title>
<script type="text/javascript">
	/*KILLDIALOG*/
	var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)
	function getTemplate(templateId,subject,path){
		var obj={templateId:templateId,subject:subject,path:path};
		//window.returnValue=obj;
		dialog.get('sucCall')(obj);
		dialog.close();
	}
</script>
<style type="text/css">
	div.bottom{text-align: center;padding-top: 10px;}
	html,body{padding:0px; margin:0; width:100%;height:100%;overflow: hidden;}
</style>
</head>
<body>
	<iframe src="selector.ht?templatetype=${type}" width="100%" height="100%" frameborder="0"></iframe>
</body>
</html>


