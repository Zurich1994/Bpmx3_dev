<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>原子操作表管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">原子操作表管理列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link add" href="edit.ht?serviceId=<%=request.getParameter("id")%>"><span></span>添加</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link update" id="btnUpd" action="edit.ht?serviceId=<%=request.getParameter("id")%>"><span></span>修改</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link del"  action="del.ht"><span></span>删除</a></div>
				</div>	
			</div>
			<div style="display:none" class="row">
						<span  class="label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;服务ID:</span><input type="text" name="serviceId"  class="inputText" value="<%=request.getParameter("id")%>"  readonly="readonly"  />
					</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht?id=<%=request.getParameter("id")%>&status=2"</form>
					
					<div class="row">
						<span class="label">原子操作名称:</span><input type="text" name="Q_name_S"  class="inputText" />
						<span style="display:none" class="label" >服务ID:</span><input type="hidden" name="Q_serviceID_S" value="<%=request.getParameter("id")%>" class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="atomicoperateList" id="atomicoperateItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${atomicoperateItem.id}">
				</display:column>
				<display:column property="name" title="原子操作名称" sortable="true" sortName="F_NAME"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${atomicoperateItem.id}" class="link del"><span></span>删除</a>
					<a href="edit.ht?id=${atomicoperateItem.id}&serviceId=<%=request.getParameter("id")%>" class="link edit"><span></span>编辑</a>
					<a href="get.ht?id=${atomicoperateItem.id}" class="link detail"><span></span>明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="atomicoperateItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


