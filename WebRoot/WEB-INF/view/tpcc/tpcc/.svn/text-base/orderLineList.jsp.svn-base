<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>order_line管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">order_line管理列表</span>
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
						<span class="label">ol_o_id:</span><input type="text" name="Q_ol_o_id_L"  class="inputText" />
						<span class="label">ol_d_id:</span><input type="text" name="Q_ol_d_id_L"  class="inputText" />
						<span class="label">ol_w_id:</span><input type="text" name="Q_ol_w_id_L"  class="inputText" />
						<span class="label">ol_number:</span><input type="text" name="Q_ol_number_L"  class="inputText" />
						<span class="label">ol_i_id:</span><input type="text" name="Q_ol_i_id_L"  class="inputText" />
						<span class="label">ol_supply_w_id:</span><input type="text" name="Q_ol_supply_w_id_L"  class="inputText" />
						<span class="label">ol_delivery_d 从:</span> <input  name="Q_beginol_delivery_d_DL"  class="inputText date" />
						<span class="label">至: </span><input  name="Q_endol_delivery_d_DG" class="inputText date" />
						<span class="label">ol_quantity:</span><input type="text" name="Q_ol_quantity_L"  class="inputText" />
						<span class="label">ol_amount:</span><input type="text" name="Q_ol_amount_L"  class="inputText" />
						<span class="label">ol_dist_info:</span><input type="text" name="Q_ol_dist_info_S"  class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="orderLineList" id="orderLineItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${orderLineItem.ol_o_id}">
				</display:column>
				<display:column property="ol_d_id" title="ol_d_id" sortable="true" sortName="OL_D_ID"></display:column>
				<display:column property="ol_w_id" title="ol_w_id" sortable="true" sortName="OL_W_ID"></display:column>
				<display:column property="ol_number" title="ol_number" sortable="true" sortName="OL_NUMBER"></display:column>
				<display:column property="ol_i_id" title="ol_i_id" sortable="true" sortName="OL_I_ID"></display:column>
				<display:column property="ol_supply_w_id" title="ol_supply_w_id" sortable="true" sortName="OL_SUPPLY_W_ID"></display:column>
				<display:column  title="ol_delivery_d" sortable="true" sortName="OL_DELIVERY_D">
					<fmt:formatDate value="${orderLineItem.ol_delivery_d}" pattern="yyyy-MM-dd"/>
				</display:column>
				<display:column property="ol_quantity" title="ol_quantity" sortable="true" sortName="OL_QUANTITY"></display:column>
				<display:column property="ol_amount" title="ol_amount" sortable="true" sortName="OL_AMOUNT"></display:column>
				<display:column property="ol_dist_info" title="ol_dist_info" sortable="true" sortName="OL_DIST_INFO"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${orderLineItem.ol_o_id}" class="link del"><span></span>删除</a>
					<a href="edit.ht?id=${orderLineItem.ol_o_id}" class="link edit"><span></span>编辑</a>
					<a href="get.ht?id=${orderLineItem.ol_o_id}" class="link detail"><span></span>明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="orderLineItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


