<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>warehouse管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">warehouse管理列表</span>
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
						<span class="label">w_id:</span><input type="text" name="Q_w_id_L"  class="inputText" />
						<span class="label">w_name:</span><input type="text" name="Q_w_name_S"  class="inputText" />
						<span class="label">w_street_1:</span><input type="text" name="Q_w_street_1_S"  class="inputText" />
						<span class="label">w_street_2:</span><input type="text" name="Q_w_street_2_S"  class="inputText" />
						<span class="label">w_city:</span><input type="text" name="Q_w_city_S"  class="inputText" />
						<span class="label">w_state:</span><input type="text" name="Q_w_state_S"  class="inputText" />
						<span class="label">w_zip:</span><input type="text" name="Q_w_zip_S"  class="inputText" />
						<span class="label">w_tax:</span><input type="text" name="Q_w_tax_L"  class="inputText" />
						<span class="label">w_ytd:</span><input type="text" name="Q_w_ytd_L"  class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="warehouseList" id="warehouseItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${warehouseItem.w_id}">
				</display:column>
				<display:column property="w_name" title="w_name" sortable="true" sortName="W_NAME"></display:column>
				<display:column property="w_street_1" title="w_street_1" sortable="true" sortName="W_STREET_1"></display:column>
				<display:column property="w_street_2" title="w_street_2" sortable="true" sortName="W_STREET_2"></display:column>
				<display:column property="w_city" title="w_city" sortable="true" sortName="W_CITY"></display:column>
				<display:column property="w_state" title="w_state" sortable="true" sortName="W_STATE"></display:column>
				<display:column property="w_zip" title="w_zip" sortable="true" sortName="W_ZIP"></display:column>
				<display:column property="w_tax" title="w_tax" sortable="true" sortName="W_TAX"></display:column>
				<display:column property="w_ytd" title="w_ytd" sortable="true" sortName="W_YTD"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${warehouseItem.w_id}" class="link del"><span></span>删除</a>
					<a href="edit.ht?id=${warehouseItem.w_id}" class="link edit"><span></span>编辑</a>
					<a href="get.ht?id=${warehouseItem.w_id}" class="link detail"><span></span>明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="warehouseItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


