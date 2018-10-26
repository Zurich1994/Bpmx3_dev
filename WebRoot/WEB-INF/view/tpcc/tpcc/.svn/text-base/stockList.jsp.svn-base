<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>stock管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">stock管理列表</span>
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
						<span class="label">s_i_id:</span><input type="text" name="Q_s_i_id_L"  class="inputText" />
						<span class="label">s_w_id:</span><input type="text" name="Q_s_w_id_L"  class="inputText" />
						<span class="label">s_quantity:</span><input type="text" name="Q_s_quantity_L"  class="inputText" />
						<span class="label">s_dist_01:</span><input type="text" name="Q_s_dist_01_S"  class="inputText" />
						<span class="label">s_dist_02:</span><input type="text" name="Q_s_dist_02_S"  class="inputText" />
						<span class="label">s_dist_03:</span><input type="text" name="Q_s_dist_03_S"  class="inputText" />
						<span class="label">s_dist_04:</span><input type="text" name="Q_s_dist_04_S"  class="inputText" />
						<span class="label">s_dist_05:</span><input type="text" name="Q_s_dist_05_S"  class="inputText" />
						<span class="label">s_dist_06:</span><input type="text" name="Q_s_dist_06_S"  class="inputText" />
						<span class="label">s_dist_07:</span><input type="text" name="Q_s_dist_07_S"  class="inputText" />
						<span class="label">s_dist_08:</span><input type="text" name="Q_s_dist_08_S"  class="inputText" />
						<span class="label">s_dist_09:</span><input type="text" name="Q_s_dist_09_S"  class="inputText" />
						<span class="label">s_dist_10:</span><input type="text" name="Q_s_dist_10_S"  class="inputText" />
						<span class="label">s_ytd:</span><input type="text" name="Q_s_ytd_L"  class="inputText" />
						<span class="label">s_order_cnt:</span><input type="text" name="Q_s_order_cnt_L"  class="inputText" />
						<span class="label">s_remote_cnt:</span><input type="text" name="Q_s_remote_cnt_L"  class="inputText" />
						<span class="label">s_data:</span><input type="text" name="Q_s_data_S"  class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="stockList" id="stockItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${stockItem.s_i_id}">
				</display:column>
				<display:column property="s_w_id" title="s_w_id" sortable="true" sortName="S_W_ID"></display:column>
				<display:column property="s_quantity" title="s_quantity" sortable="true" sortName="S_QUANTITY"></display:column>
				<display:column property="s_dist_01" title="s_dist_01" sortable="true" sortName="S_DIST_01"></display:column>
				<display:column property="s_dist_02" title="s_dist_02" sortable="true" sortName="S_DIST_02"></display:column>
				<display:column property="s_dist_03" title="s_dist_03" sortable="true" sortName="S_DIST_03"></display:column>
				<display:column property="s_dist_04" title="s_dist_04" sortable="true" sortName="S_DIST_04"></display:column>
				<display:column property="s_dist_05" title="s_dist_05" sortable="true" sortName="S_DIST_05"></display:column>
				<display:column property="s_dist_06" title="s_dist_06" sortable="true" sortName="S_DIST_06"></display:column>
				<display:column property="s_dist_07" title="s_dist_07" sortable="true" sortName="S_DIST_07"></display:column>
				<display:column property="s_dist_08" title="s_dist_08" sortable="true" sortName="S_DIST_08"></display:column>
				<display:column property="s_dist_09" title="s_dist_09" sortable="true" sortName="S_DIST_09"></display:column>
				<display:column property="s_dist_10" title="s_dist_10" sortable="true" sortName="S_DIST_10"></display:column>
				<display:column property="s_ytd" title="s_ytd" sortable="true" sortName="S_YTD"></display:column>
				<display:column property="s_order_cnt" title="s_order_cnt" sortable="true" sortName="S_ORDER_CNT"></display:column>
				<display:column property="s_remote_cnt" title="s_remote_cnt" sortable="true" sortName="S_REMOTE_CNT"></display:column>
				<display:column property="s_data" title="s_data" sortable="true" sortName="S_DATA"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${stockItem.s_i_id}" class="link del"><span></span>删除</a>
					<a href="edit.ht?id=${stockItem.s_i_id}" class="link edit"><span></span>编辑</a>
					<a href="get.ht?id=${stockItem.s_i_id}" class="link detail"><span></span>明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="stockItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


