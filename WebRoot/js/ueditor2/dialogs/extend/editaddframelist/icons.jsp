<%@page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" >
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
<title>选择图标</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style type="text/css">
	body{
		overflow:hidden;
	}
</style>
<script type="text/javascript">
	/*KILLDIALOG*/
	var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)
	
	function selectIcon(t){
		if(t){
			//window.returnValue = t;
			dialog.get("sucCall")(t);
		}
		dialog.close();
	}
</script>
</head>
<body>
	<iframe src="icon.jsp" width="100%" frameborder="0" height="100%"></iframe>
</body>
</html>