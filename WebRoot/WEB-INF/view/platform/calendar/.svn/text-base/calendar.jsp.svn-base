<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" import="java.util.List,com.hotent.core.util.AppUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/commons/include/get.jsp" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
	// 日程显示模式 . 3:月, 2:周, 1:工作周, 0:天
	String calendarMode = "3";
%>
<html>
    <head>    
        <title>我的日程</title>         
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<style type="text/css" media="screen"> 
			html, body	{ height:100%; }
			body { margin:0; padding:0; overflow:auto; text-align:center;  background-color: #ffffff; }   
			#flashContent { visibility: hidden; }
        </style>
        <script type="text/javascript" src="${ctx}/media/swf/calendar/swfobject.js"></script>
		<script type="text/javascript">
			/*
			* 显示选中项目的详细信息。
			* 这个函数的名称是固定的，有flex来进行调用。
			* id:选中项id。
			* type:选中项类型,可根据类型判断作相应处理。
			*/
			function goToDetail(id, type){
				 var url="${ctx}/platform/bpm/task/toStart.ht?taskId="+id;
				 jQuery.openFullWindow(url);
			}
			
		</script>
		<script type="text/javascript">
        
            var swfVersionStr = "10.0.0";
            var xiSwfUrlStr = "${ctx}/media/swf/calendar/playerProductInstall.swf";
            // 把 flex 控件所需参数进行传递
            // calDataUrl: 数据源路径
            // calendarMode: 日程显示模式
            var flashvars = {url:'${ctx}/platform/bpm/task/myEvent.ht',calendarMode:'<%=calendarMode%>'};
            var params = {};
            params.quality = "high";
            params.bgcolor = "#ffffff";
            params.allowscriptaccess = "sameDomain";
            params.allowfullscreen = "true";
            params.wmode = "transparent";
            var attributes = {};
            attributes.id = "calendar";
            attributes.name = "calendar";
            attributes.align = "middle";

            swfobject.embedSWF(
                "${ctx}/media/swf/calendar/calendar.swf", "flashContent", 
                "100%", "100%", 
                swfVersionStr, xiSwfUrlStr, 
                flashvars, params, attributes);
            
			swfobject.createCSS("#flashContent", "visibility:visible;text-align:left;");
        </script>
    </head>
    <body>
		<div id="flashContent"></div>
   </body>
</html>
