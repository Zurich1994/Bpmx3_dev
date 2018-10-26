<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>order管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">order管理列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link back" href="${ctx}/tpcc/tpcc/customer/Query.ht"><span></span>返回</a></div>
					<div class="l-bar-separator"></div>
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
		    <display:table name="orderyanList" id="orderyanItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${orderyanItem.id}">
				</display:column>
				<display:column property="o_id" title="订单号" sortable="true" sortName="F_O_ID"></display:column>
				<display:column property="o_d_id" title="地区号" sortable="true" sortName="F_O_D_ID"></display:column>
				<display:column property="o_w_id" title="仓库号" sortable="true" sortName="F_O_W_ID"></display:column>
				<display:column property="o_c_id" title="用户号" sortable="true" sortName="F_O_C_ID"></display:column>
				<display:column  title="日期和时间" sortable="true" sortName="F_O_ENTRY_D">
					<fmt:formatDate value="${orderyanItem.o_entry_d}" pattern="yyyy-MM-dd"/>
				</display:column>
				<display:column property="o_carrier_id" title="承接人ID" sortable="true" sortName="F_O_CARRIER_ID"></display:column>
				<display:column property="o_ol_cnt" title="订单行数量" sortable="true" sortName="F_O_OL_CNT"></display:column>
				<display:column property="o_all_local" title="是否全是本地商品" sortable="true" sortName="F_O_ALL_LOCAL"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${orderyanItem.id}" class="link del"><span></span>删除</a>
					
					<a href="get.ht?id=${orderyanItem.id}" class="link detail"><span></span>明细</a>
					<a href="/mas/tpcc/tpcc/item/zf.ht?id=${orderyanItem.id}" class="link del"><span></span>支付</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="orderyanItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


