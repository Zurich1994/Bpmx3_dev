<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
	<title>业务数据模板预览</title>
	<%@include file="/commons/include/get.jsp" %>
	<f:link href="tree/zTreeStyle.css"></f:link>
	<script type="text/javascript" src="${ctx}/js/tree/jquery.ztree.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/ajaxgrid.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/form/Export.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/form/SelectorUtil.js"></script>

	<script type="text/javascript">

	$(function(){
		handleAjaxSearchKeyPress();
		Export.initExportMenu();   	
	});

	</script>
</head>
<body>
	<div id="content">
		${html}
	</div>
</body>
</html>
