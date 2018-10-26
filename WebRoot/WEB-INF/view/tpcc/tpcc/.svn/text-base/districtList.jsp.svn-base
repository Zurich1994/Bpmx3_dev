<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>district管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">district管理列表</span>
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
						<span class="label">d_id:</span><input type="text" name="Q_d_id_L"  class="inputText" />
						<span class="label">d_w_id:</span><input type="text" name="Q_d_w_id_L"  class="inputText" />
						<span class="label">d_name:</span><input type="text" name="Q_d_name_S"  class="inputText" />
						<span class="label">d_street_1:</span><input type="text" name="Q_d_street_1_S"  class="inputText" />
						<span class="label">d_street_2:</span><input type="text" name="Q_d_street_2_S"  class="inputText" />
						<span class="label">d_city:</span><input type="text" name="Q_d_city_S"  class="inputText" />
						<span class="label">d_state:</span><input type="text" name="Q_d_state_S"  class="inputText" />
						<span class="label">d_zip:</span><input type="text" name="Q_d_zip_S"  class="inputText" />
						<span class="label">d_tax:</span><input type="text" name="Q_d_tax_L"  class="inputText" />
						<span class="label">d_ytd:</span><input type="text" name="Q_d_ytd_L"  class="inputText" />
						<span class="label">d_next_o_id:</span><input type="text" name="Q_d_next_o_id_L"  class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="districtList" id="districtItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${districtItem.d_id}">
				</display:column>
				<display:column property="d_w_id" title="d_w_id" sortable="true" sortName="D_W_ID"></display:column>
				<display:column property="d_name" title="d_name" sortable="true" sortName="D_NAME"></display:column>
				<display:column property="d_street_1" title="d_street_1" sortable="true" sortName="D_STREET_1"></display:column>
				<display:column property="d_street_2" title="d_street_2" sortable="true" sortName="D_STREET_2"></display:column>
				<display:column property="d_city" title="d_city" sortable="true" sortName="D_CITY"></display:column>
				<display:column property="d_state" title="d_state" sortable="true" sortName="D_STATE"></display:column>
				<display:column property="d_zip" title="d_zip" sortable="true" sortName="D_ZIP"></display:column>
				<display:column property="d_tax" title="d_tax" sortable="true" sortName="D_TAX"></display:column>
				<display:column property="d_ytd" title="d_ytd" sortable="true" sortName="D_YTD"></display:column>
				<display:column property="d_next_o_id" title="d_next_o_id" sortable="true" sortName="D_NEXT_O_ID"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${districtItem.d_id}" class="link del"><span></span>删除</a>
					<a href="edit.ht?id=${districtItem.d_id}" class="link edit"><span></span>编辑</a>
					<a href="get.ht?id=${districtItem.d_id}" class="link detail"><span></span>明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="districtItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


