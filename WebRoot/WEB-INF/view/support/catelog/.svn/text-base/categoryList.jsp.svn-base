<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>Support类别表管理</title>
<%@include file="/commons/include/get.jsp" %>

</head>
<body>

	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">Support类别表管理列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<div class="row">
						<span class="label">产品目录ID:</span><input type="text" name="Q_CATEGORYID_S"  class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
		    <display:table name="categoryList" id="categoryItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column property="CATEGORYID" title="产品目录ID" sortable="true" sortName="F_CATEGORYID"></display:column>
				<display:column property="CATEGORYNAME" title="产品目录" sortable="true" sortName="F_CATEGORYNAME"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="/mas/support/catelog/products/list.ht?id=${categoryItem.id}"><span></span>明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="categoryItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


