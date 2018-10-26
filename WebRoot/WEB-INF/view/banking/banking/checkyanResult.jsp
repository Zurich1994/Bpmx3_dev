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
					<div class="group"><a class="link back" href="list.ht?name=<%=name %>"><span></span>返回</a></div>
					
				</div>	
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="checkyanList" id="checkyanItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${checkyanItem.id}">
				</display:column>
				<display:column property="checkno" title="支票号" sortable="true" sortName="F_CHECKNO"></display:column>
				
				<display:column property="account" title="账号" sortable="true" sortName="F_ACCOUNT"></display:column>
				
				<display:column title="管理" media="html" style="width:220px">
				
					<a href="get.ht?id=${checkyanItem.id}&name=<%=name %>" class="link detail"><span></span>明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="checkyanItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


