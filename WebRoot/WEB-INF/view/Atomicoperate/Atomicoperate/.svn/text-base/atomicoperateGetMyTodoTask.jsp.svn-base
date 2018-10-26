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
					<div class="group"><a class="link add" href="edit.ht"><span></span>添加</a></div>
				</div>	
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="atomicoperateList" id="atomicoperateItem" requestURI="getMyDraftJson.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${atomicoperateItem.id}">
				</display:column>
				<display:column property="name" title="原子操作名称" sortable="true" sortName="F_NAME"></display:column>
				<display:column property="serviceID" title="服务编号" sortable="true" sortName="F_SERVICEID"></display:column>
				<display:column title="管理" media="html" style="width:220px">
				   <a href="edit.ht?id=${atomicoperateItem.id}&taskId=${atomicoperateItem.taskId}" class="link edit"><span></span>编辑</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="atomicoperateItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


