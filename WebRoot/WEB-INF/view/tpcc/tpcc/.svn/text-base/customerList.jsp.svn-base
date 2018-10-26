<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>customer管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">customer管理列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
					<div class="l-bar-separator"></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<div class="row">
						<span class="label">客户号:</span><input type="text" name="Q_c_id_L"  class="inputText" />
						<span class="label">地区号:</span><input type="text" name="Q_c_d_id_L"  class="inputText" />
						<span class="label">仓库号:</span><input type="text" name="Q_c_w_id_L"  class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="customerList" id="customerItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${customerItem.c_id}">
				</display:column>
				<display:column property="c_id" title="客户号" sortable="true" sortName="C_ID"></display:column>
				<display:column property="c_d_id" title="客户所在地区号" sortable="true" sortName="C_D_ID"></display:column>
				<display:column property="c_w_id" title="客户所在仓库号" sortable="true" sortName="C_W_ID"></display:column>
				<display:column property="c_last" title="客户姓氏" sortable="true" sortName="C_LAST"></display:column>
				<display:column property="c_credit" title="客户信用评价" sortable="true" sortName="C_CREDIT"></display:column>
				<display:column property="c_discount" title="客户折扣" sortable="true" sortName="C_DISCOUNT"></display:column>
				<display:column property="w_tax" title="仓库税率" sortable="true" sortName="W_TAX"></display:column>
				<display:column property="d_tax" title="销售税率" sortable="true" sortName="D_TAX"></display:column>
				<display:column property="d_next_o_id" title="订单号" sortable="true" sortName="D_NEXT_O_ID"></display:column>
				
				<display:column title="管理" media="html" style="width:220px">
				</display:column>
			</display:table>
			<hotent:paging tableId="customerItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


