<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>orders管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">orders管理列表</span>
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
						<span class="label">o_id:</span><input type="text" name="Q_o_id_L"  class="inputText" />
						<span class="label">o_d_id:</span><input type="text" name="Q_o_d_id_L"  class="inputText" />
						<span class="label">o_w_id:</span><input type="text" name="Q_o_w_id_L"  class="inputText" />
						<span class="label">o_c_id:</span><input type="text" name="Q_o_c_id_L"  class="inputText" />
						<span class="label">o_entry_d 从:</span> <input  name="Q_begino_entry_d_DL"  class="inputText date" />
						<span class="label">至: </span><input  name="Q_endo_entry_d_DG" class="inputText date" />
						<span class="label">o_carrier_id:</span><input type="text" name="Q_o_carrier_id_L"  class="inputText" />
						<span class="label">o_ol_cnt:</span><input type="text" name="Q_o_ol_cnt_L"  class="inputText" />
						<span class="label">o_all_local:</span><input type="text" name="Q_o_all_local_L"  class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="ordersList" id="ordersItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${ordersItem.o_id}">
				</display:column>
				<display:column property="o_d_id" title="o_d_id" sortable="true" sortName="O_D_ID"></display:column>
				<display:column property="o_w_id" title="o_w_id" sortable="true" sortName="O_W_ID"></display:column>
				<display:column property="o_c_id" title="o_c_id" sortable="true" sortName="O_C_ID"></display:column>
				<display:column  title="o_entry_d" sortable="true" sortName="O_ENTRY_D">
					<fmt:formatDate value="${ordersItem.o_entry_d}" pattern="yyyy-MM-dd"/>
				</display:column>
				<display:column property="o_carrier_id" title="o_carrier_id" sortable="true" sortName="O_CARRIER_ID"></display:column>
				<display:column property="o_ol_cnt" title="o_ol_cnt" sortable="true" sortName="O_OL_CNT"></display:column>
				<display:column property="o_all_local" title="o_all_local" sortable="true" sortName="O_ALL_LOCAL"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${ordersItem.o_id}" class="link del"><span></span>删除</a>
					<a href="edit.ht?id=${ordersItem.o_id}" class="link edit"><span></span>编辑</a>
					<a href="get.ht?id=${ordersItem.o_id}" class="link detail"><span></span>明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="ordersItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


