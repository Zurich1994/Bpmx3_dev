<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>history2管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">history2管理列表</span>
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
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="history2List" id="history2Item" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${history2Item.id}">
				</display:column>
				<display:column property="h_c_id" title="h_c_id" sortable="true" sortName="F_H_C_ID"></display:column>
				<display:column property="h_c_d_id" title="h_c_d_id" sortable="true" sortName="F_H_C_D_ID"></display:column>
				<display:column property="h_d_id" title="h_d_id" sortable="true" sortName="F_H_D_ID"></display:column>
				<display:column property="h_w_id" title="h_w_id" sortable="true" sortName="F_H_W_ID"></display:column>
				<display:column  title="h_date" sortable="true" sortName="F_H_DATE">
					<fmt:formatDate value="${history2Item.h_date}" pattern="yyyy-MM-dd"/>
				</display:column>
				<display:column property="h_amount" title="h_amount" sortable="true" sortName="F_H_AMOUNT"></display:column>
				<display:column property="h_data" title="h_data" sortable="true" sortName="F_H_DATA"></display:column>
				<display:column property="h_c_w_id" title="h_c_w_id" sortable="true" sortName="F_H_C_W_ID"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${history2Item.id}" class="link del"><span></span>删除</a>
					<a href="edit.ht?id=${history2Item.id}" class="link edit"><span></span>编辑</a>
					<a href="get.ht?id=${history2Item.id}" class="link detail"><span></span>明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="history2Item"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


