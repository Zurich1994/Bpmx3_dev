<%@page import="com.hotent.core.web.util.RequestUtil" isErrorPage="true" pageEncoding="UTF-8"%>
<%
	String basePath=request.getContextPath();
%>
<html>
	<head>
		<title>会话过期了</title>
		<%@include file="/commons/include/get.jsp" %>
			<style type="text/css">
			<!--
			.STYLE10 {
				font-family: "黑体";
				font-size: 36px;
			}
			-->  
			</style>
		
	</head>
	<body>
	 <table width="510" border="0" align="center" cellpadding="0" cellspacing="0">
	  <tr>
    	<td><img src="<%=basePath%>/styles/default/images/error/error_top.jpg" width="510" height="80" /></td>
  	  </tr>
	  <tr>
	    <td height="200" align="center" valign="top" background="<%=basePath%>/styles/default/images/error/error_bg.jpg">
	    	<table width="80%" border="0" cellspacing="0" cellpadding="0">
	        <tr>
	          <td width="34%" align="right"><img src="<%=basePath%>/styles/default/images/error/error.gif" width="128" height="128"></td>
	          <td width="66%" valign="top" align="center">
	          	<table width="100%">
	          		<tr height="25">
	          			<td>
	          			<span class="STYLE10">会话过期了</span>
	          			</td>
	          		</tr>
	          		<tr height="70">
	          			<td colspan="1" >
	          			<div style="overflow:auto;height:100px;width:300px;">
	          				会话过期或者当前用户从其他的地方登录了。
	          			</div>
	          		
	          			</td>
	          		</tr>
	          		
	          		<tr height="25">
		          		<td>
		          		  <a href="javascript:;" onclick="javascript:location.href='<%=basePath%>/login.jsp';">重新登录</a> 
			        	 
		          		</td>
	          		</tr>
	          		
	          	</table>
	          	
	     	 </td>
	      </table>
	      </td>
	  </tr>    	 
	  <tr>
    	<td><img src="<%=basePath%>/styles/default/images/error/error_bootom.jpg" width="510" height="32" /></td>
      </tr>
	</table>
	</body>
</html>