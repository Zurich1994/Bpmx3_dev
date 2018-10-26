<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>子系统服务表管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">子系统服务表管理列表</span>
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
		    <display:table name="sysserviceList" id="sysserviceItem" requestURI="getMyDraftJson.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${sysserviceItem.id}">
				</display:column>
				<display:column property="systemId" title="子系统id" sortable="true" sortName="F_SYSTEMID"></display:column>
				<display:column property="service" title="服务类别" sortable="true" sortName="F_SERVICE"></display:column>
				<display:column property="serviceNum" title="服务数量" sortable="true" sortName="F_SERVICENUM"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${sysserviceItem.id}" class="link del"><span></span>删除</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="sysserviceItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


