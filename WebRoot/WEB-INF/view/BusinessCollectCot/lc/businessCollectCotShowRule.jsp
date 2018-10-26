<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
//String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<% 
	String basePath = request.getContextPath(); 
	String timeType = request.getParameter("timeType");
	String processId = request.getParameter("processId");
	String postUrl1 = basePath + "/BusinessCollectCot/lc/businessCollectCot/regularShow.ht?isAjaxRequest=true&timeType="+timeType+"&processId="+processId;
%>
  <head>
  
    <base href="<%=basePath%>">
    
    <title>My JSP 'businessCollectCotShow.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		function getCtxPath1(){
			return document.getElementById("postUrl1").value;
		}
	</script>
  </head>
  
  <body>
  <input type="hidden" id="timeType" value="<%=request.getParameter("timeType") %>"/> 
   <input type="hidden" id="timeType" value="<%=request.getParameter("processId") %>"/> 
  <param name="movie" value="<%=basePath%>/media/swf/bpm/lineshow.swf" />
<object type="application/x-shockwave-flash" data="<%=basePath%>/media/swf/bpm/lineshow.swf" width="100%" height="80%">
	<input id="postUrl1" type="hidden" value="<%=postUrl1%>" />
    This is my JSP page. <br>
  </body>
</html>
