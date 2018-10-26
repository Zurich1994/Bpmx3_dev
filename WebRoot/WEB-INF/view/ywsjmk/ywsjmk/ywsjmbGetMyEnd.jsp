<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>业务数据模板管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">业务数据模板管理列表</span>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="ywsjmbList" id="ywsjmbItem" requestURI="getMyDraftJson.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${ywsjmbItem.id}">
				</display:column>
				<display:column property="subject" title="表单标题" sortable="true" sortName="F_SUBJECT"></display:column>
				<display:column property="formkey" title="表单别名" sortable="true" sortName="F_FORMKEY"></display:column>
				<display:column property="tablename" title="对应表" sortable="true" sortName="F_TABLENAME"></display:column>
				<display:column property="categoryid" title="表单分类" sortable="true" sortName="F_CATEGORYID"></display:column>
				<display:column title="管理" media="html" style="width:220px">
				   <a href="get.ht?id=${ywsjmbItem.id}" class="link detail"><span></span>明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="ywsjmbItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


