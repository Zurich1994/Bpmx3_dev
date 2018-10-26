<%@page import="com.hotent.platform.service.system.SysPropertyService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"
    import="com.hotent.core.util.AppUtil,
    java.util.Properties,
    org.springframework.security.authentication.AuthenticationServiceException,
    org.springframework.security.authentication.BadCredentialsException,
    org.springframework.security.web.WebAttributes"
    %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>    
<%
	String validCodeEnabled=SysPropertyService.getByAlias("validCodeEnabled");
	String appName=SysPropertyService.getByAlias("appName");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><%= appName%></title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<script type="text/javascript" src="${ctx}/js/jquery/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/js/dynamic.jsp"></script>
	<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/haiya/login.css"></link>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/haiya/custom_login.css"></link>
	<script type="text/javascript">
		if(top!=this){//当这个窗口出现在iframe里，表示其目前已经timeout，需要把外面的框架窗口也重定向登录页面
			  top.location='${ctx}/login.jsp';
		}
		
		function reload(){
			var url="${ctx}/servlet/ValidCode?rand=" +new Date().getTime();
			document.getElementById("validImg").src=url;
		}
		
		$(function(){
			$('.main_login').find('input').keydown(function(event){
				if(event.keyCode==13){
					$('#form-login').submit();
				}
			});
		});
	</script>
</head>
<body>
	<div class="second_body">
		<form id="form-login" action="${ctx}/login.ht" method="post">
			<center>
				<div class="main_login">
					<span class="bpmlogo_login"></span>
					<div class="content_login">
						<div class="column">
							<span>用户名:</span>
							<input type="text" name="username" class="login" /><br>	
						</div>
						<div class="column">
							<span>密&nbsp;码:</span>
							<input type="password" name="password"/>
						</div>	
						<%if(validCodeEnabled!=null && "true".equals(validCodeEnabled)){ %>
						<div class="column" >
							<span>验证码:</span>
							<input type="text" name="validCode" style="width: 40px"/>
							<img id="validImg" style="vertical-align:middle;"  src="${ctx}/servlet/ValidCode" />
							<a onclick="reload()">看不清，换一张</a>
						</div>
						 <%} %>
						 
						<div class="confirm" >
							<a class="reset_btn btn r" onclick="document.getElementById('form-login').reset();">重置</a>
							<a class="login_btn btn r" onclick="document.getElementById('form-login').submit();">登录</a>
						</div>
						
					</div>
				</div>
			</center>
			
		</form>
		<%
		Object loginError=(Object)request.getSession().getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		String msg="";
		if(loginError!=null ){
			if(loginError instanceof BadCredentialsException){
				msg="密码输入错误";
			}
			else if(loginError instanceof AuthenticationServiceException){
				AuthenticationServiceException ex=(AuthenticationServiceException)loginError;
				msg=ex.getMessage();
			}
			else{
				msg=loginError.toString();
			}
		}
		%>
		<div align="center" class="printmsg" ><%=msg%></div>
	</div>
</body>
<script type="text/javascript">
if(top!=this){//当这个窗口出现在iframe里，表示其目前已经timeout，需要把外面的框架窗口也重定向登录页面
	  top.location='${ctx}/login.jsp';
}
</script>
</html>
