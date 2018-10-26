<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>ORDER_BILLING管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">ORDER_BILLING管理列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link add" href="edits.ht"><span></span>添加</a></div>
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
		    <display:table name="orderBillingList" id="orderBillingItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${orderBillingItem.id}">
				</display:column>
				<display:column property="EMAIL" title="EMAIL" sortable="true" sortName="F_EMAIL"></display:column>
				<display:column property="FIRSTNAME" title="FIRSTNAME" sortable="true" sortName="F_FIRSTNAME"></display:column>
				<display:column property="LASTNAME" title="LASTNAME" sortable="true" sortName="F_LASTNAME"></display:column>
				<display:column property="ADDRESS" title="ADDRESS" sortable="true" sortName="F_ADDRESS"></display:column>
				<display:column property="CITY" title="CITY" sortable="true" sortName="F_CITY"></display:column>
				<display:column property="STATE" title="STATE" sortable="true" sortName="F_STATE"></display:column>
				<display:column property="ZIP" title="ZIP" sortable="true" sortName="F_ZIP"></display:column>
				<display:column property="PHONE" title="PHONE" sortable="true" sortName="F_PHONE"></display:column>
				<display:column property="CC_TYPE" title="CC_TYPE" sortable="true" sortName="F_CC_TYPE"></display:column>
				<display:column property="CC_NUM" title="CC_NUM" sortable="true" sortName="F_CC_NUM"></display:column>
				<display:column property="CC_EXPMONTH" title="CC_EXPMONTH" sortable="true" sortName="F_CC_EXPMONTH"></display:column>
				<display:column property="CC_EXPYEAR" title="CC_EXPYEAR" sortable="true" sortName="F_CC_EXPYEAR"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${orderBillingItem.id}" class="link del"><span></span>删除</a>
					<a href="edit.ht?id=${orderBillingItem.id}" class="link edit"><span></span>编辑</a>
					<a href="get.ht?id=${orderBillingItem.id}" class="link detail"><span></span>明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="orderBillingItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


