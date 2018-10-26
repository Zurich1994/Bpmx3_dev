<%@page import="com.hotent.core.web.util.RequestUtil,
				org.springframework.security.access.AccessDeniedException"  pageEncoding="UTF-8" isErrorPage="true" %>
<%
	String basePath=request.getContextPath();
	AccessDeniedException ex=(AccessDeniedException)request.getAttribute("ex");
	
%>
<html>
	<head>
		<%@include file="/commons/include/get.jsp" %>
		<title>页面出错了</title>
			<style type="text/css">
			.STYLE10 {font-family: "微软雅黑";font-size: 20px;}
			</style>
	</head>
	<body>
	
	<table  border="0" align="center" cellpadding="0" cellspacing="0" width="450">
		  <tr>
	    	<td><img src="${ctx}/styles/default/images/error/error_top.jpg" /></td>
	  	  </tr>
		  <tr>
		    <td height="200" align="center" valign="top" background="${ctx}/styles/default/images/error/error_bg.jpg">
		    	<table width="80%" border="0" cellspacing="0" cellpadding="0">
		    	<tr>
		    		<td style="text-align: center;"><img src="${ctx}/styles/default/images/error/error_sorry.png" /></td>
		    	</tr>
		        <tr>
		          <td width="66%" valign="top" >
		          	<table width="100%" style="text-align: center;">
		          		<tr height="25">
		          			<td>
		          			<span class="STYLE10">访问被拒绝!</span>
		          			</td>
		          		</tr>
		          		<tr height="70">
		          			<td style="color: #FF5F02; font-size: 14px; font-family: '微软雅黑';">
		          			 <%=ex.getMessage() %>
		          			</td>
		          		</tr>
		          	</table>
		          	
		     	 </td>
		      </table>
		      </td>
		  </tr>    	 
		  <tr>
	    	<td><img src="${ctx}/styles/default/images/error/error_bootom.jpg" /></td>
	      </tr>
		</table>
	</body>
</html>