
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>组织架构查询列表</title>
<%@include file="/commons/include/get.jsp"%>
<script type="text/javascript">
/*KILLDIALOG*/
var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)

	var orgId=0;
	function setLocation(id){
		//window.returnValue={orgId:id};
		dialog.get('sucCall')({orgId:id});
		dialog.close();
	}
	
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">组织架构列表</span>
			</div>
		</div>
		<div style="height:500px;">
			<iframe id="sysOrgListFrame" name="sysOrgListFrame" height="90%"
				width="100%" frameborder="0"
				src="${ctx}/platform/system/sysOrg/search.ht"></iframe>
		</div>
	</div>
	<!-- end of panel -->
</body>
</html>


