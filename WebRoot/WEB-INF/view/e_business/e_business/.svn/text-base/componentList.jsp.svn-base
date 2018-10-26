<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>用户定制管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<ol><li><span class="tbar-label">用户定制管理列表</span></li></ol>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link add" href="edit.ht"><span></span>添加</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link update" id="btnUpd" action="edit.ht"><span></span>修改</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link del"  action="del.ht"><span></span>删除</a></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<div class="row">
						<span class="label">组件ID:</span><input type="text" name="Q_COMPONENTID_S"  class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="componentList" id="componentItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${componentItem.id}">
				</display:column>
				<display:column property="PRODUCTID" title="ITEM ID" sortable="true" sortName="F_PRODUCTID"></display:column>
				<display:column property="PAGE" title="页数" sortable="true" sortName="F_PAGE"></display:column>
				<display:column property="COMPONENTTYPE" title="组件类型" sortable="true" sortName="F_COMPONENTTYPE"></display:column>
				<display:column property="COMPONENTNAME" title="组件名" sortable="true" sortName="F_COMPONENTNAME"></display:column>
				<display:column property="COMPONENTID" title="组件ID" sortable="true" sortName="F_COMPONENTID"></display:column>
				<display:column property="PRICE" title="价格" sortable="true" sortName="F_PRICE"></display:column>
				<display:column property="CURRENCY" title="货币" sortable="true" sortName="F_CURRENCY"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${componentItem.id}" class="link del"><span></span>删除</a>
					<a href="edit.ht?id=${componentItem.id}" class="link edit"><span></span>编辑</a>
					<a href="get.ht?id=${componentItem.id}" class="link detail"><span></span>明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="componentItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


