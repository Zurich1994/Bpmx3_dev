<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>new_orders管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">new_orders管理列表</span>
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
						<span class="label">no_o_id:</span><input type="text" name="Q_no_o_id_L"  class="inputText" />
						<span class="label">no_d_id:</span><input type="text" name="Q_no_d_id_L"  class="inputText" />
						<span class="label">no_w_id:</span><input type="text" name="Q_no_w_id_L"  class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="newOrdersList" id="newOrdersItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${newOrdersItem.no_o_id}">
				</display:column>
				<display:column property="no_d_id" title="地区号" sortable="true" sortName="NO_D_ID"></display:column>
				<display:column property="no_w_id" title="仓库号" sortable="true" sortName="NO_W_ID"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${newOrdersItem.no_o_id}" class="link del"><span></span>删除</a>
					<a href="edit.ht?id=${newOrdersItem.no_o_id}" class="link edit"><span></span>编辑</a>
					<a href="get.ht?id=${newOrdersItem.no_o_id}" class="link detail"><span></span>明细</a>
					<a href="/mas/tpcc/tpcc/orders/cxkhh.ht?id=${newOrdersItem.no_o_id}" class="link detail"><span></span>查询客户号</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="newOrdersItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


