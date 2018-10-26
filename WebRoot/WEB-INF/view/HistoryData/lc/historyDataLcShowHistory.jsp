<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
//String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<%

	String basePath = request.getContextPath(); 
	String timeType = request.getParameter("timeType");
	String postUrl1 = basePath + "/HistoryData/lc/historyDataLc/flexShow.ht?isAjaxRequest=true"+"&timeType="+timeType;
%>	
 	<script type="text/javascript">
 		function getCtxPath1(){
 			//var timeType = $("#timeType").val();
 			var path = document.getElementById("postUrl1").value;
 			//var newPath = path +"&timeType="+ timeType;
 			//alert(newPath);
			return document.getElementById("postUrl1").value;
			//return newPath;
		}
 	</script>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'historyDataLcShowHistory.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	<input type="hidden" id="timeType" value="<%=request.getParameter("timeType") %>"> 
  
   <param name="movie" value="<%=basePath%>/media/swf/bpm/lineshow.swf" />
<object type="application/x-shockwave-flash" data="<%=basePath%>/media/swf/bpm/lineshow.swf" width="100%" height="80%">
	<input id="postUrl1" type="hidden" value="<%=postUrl1%>" />
	 
    This is my JSP page. <br>
  </body>
</html>
