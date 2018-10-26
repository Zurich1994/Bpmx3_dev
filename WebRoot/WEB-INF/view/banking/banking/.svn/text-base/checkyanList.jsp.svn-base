<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>支票查看表管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
<%String name=request.getParameter("name");  %>
<h3 align="center">欢迎您<%=name %></h3>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">支票查看表管理列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
					<a class="link back" href="/mas/banking/banking/userTab/success.ht?name=<%=name %>"><span></span>返回</a>
					
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="Result.ht?name=<%=name%>">
					<div class="row">
						<span class="label">支票号:</span><input type="text" name="Q_checkno_S"  class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


