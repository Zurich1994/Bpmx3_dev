<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List,com.hotent.core.util.AppUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String basePath = request.getContextPath();
	String loadDateUrl = ""; // flex中获取加载数据的地址
	Object idObj = request.getAttribute("id"); 
	String id="";
	if(idObj != null && !idObj.toString().equals("0")){
		id = idObj.toString(); // 流程id
		loadDateUrl = basePath + "/projectinfo/projectinfo/projectinfo/getProject.ht?id=" + id;
	}
	String postUrl = basePath + "/projectinfo/projectinfo/projectinfo/saveProject.ht?id=" + id;
	//String xmlRecord = request.getAttribute("xmlRecord").toString();
	String xmlRecord = "samplexml";
%>
<head>
	<title>
		项目进度跟踪管理系统
	</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<style type="text/css" media="screen"> 
	html, body	{ height:100%; }
	body { margin:0; padding:0; overflow:auto; text-align:center; 
	       background-color: #ffffff; }   
	#flashContent { display:none; }
</style>
<script type="text/javascript">
	
	function loadData(){
		var str = document.getElementById('xmlRecord').value;
		return str;
	}
	
	//供flex方向调用,获取提交数据的地址
	function getSaveProjectPath(){
		return document.getElementById("postUrl").value;
	}

	// 供flex方向调用,关闭flex页面
	function closeFlexWindow(){
		window.close();
		opener.location.reload();
	}
	
	// 获取flex中加载流程设计图对应的xml数据，请求地址
	function getLoadDataUrl(){
		return document.getElementById("flexLoadDataUrl").value;
	}
</script>
<div>
	<input id="postUrl" type="hidden" value="<%=postUrl%>" />

</div>
<param name="movie" value="<%=basePath%>/media/swf/projectManagement/projecteditor.swf" />
<object type="application/x-shockwave-flash" data="<%=basePath%>/media/swf/projectManagement/projecteditor.swf" width="100%" height="100%">
   	<input id="flexLoadDataUrl" type="hidden" value="<%=loadDateUrl%>" />
   	<input id="postUrl" type="hidden" value="<%=postUrl%>" />
   	<textarea id="xmlRecord" style="display:none"><%=xmlRecord%></textarea>
   	<input id="flexLoadDataUrl" type="hidden" value="<%=loadDateUrl%>" />
<body>
</body>
