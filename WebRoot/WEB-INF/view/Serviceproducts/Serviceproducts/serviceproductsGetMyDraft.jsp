<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>服务产品表管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">服务产品表管理列表</span>
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
		    <display:table name="serviceproductsList" id="serviceproductsItem" requestURI="getMyDraftJson.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${serviceproductsItem.id}">
				</display:column>
				<display:column property="name" title="服务产品名" sortable="true" sortName="F_NAME"></display:column>
				<display:column property="serviceID" title="服务编号" sortable="true" sortName="F_SERVICEID"></display:column>
				<display:column property="serviceaddress" title="服务地址" sortable="true" sortName="F_SERVICEADDRESS"></display:column>
				<display:column property="serviceport" title="服务端口号" sortable="true" sortName="F_SERVICEPORT"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${serviceproductsItem.id}" class="link del"><span></span>删除</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="serviceproductsItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


