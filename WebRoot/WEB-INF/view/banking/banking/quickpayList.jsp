<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>用户ID管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">用户<%=request.getParameter("name")%>支付表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<div class="row">
						<span class="label">用户:</span><input type="text" name="USERID"  class="inputText" />
					</div>
				</form>
			</div>
			</div>
		<div class="panel-body">
		    <display:table name="quickpayList" id="quickpayItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column property="USERID" title="客户" sortable="true" sortName="F_USERID"></display:column>
				<display:column property="PAYEEID" title="收款人" sortable="true" sortName="F_PAYEEID"></display:column>
				<display:column  title="日期" sortable="true" sortName="F_DATA">
					<fmt:formatDate value="${quickpayItem.DATA}" pattern="yyyy-MM-dd"/>
				</display:column>
				<display:column property="PAYMENT" title="付款金额" sortable="true" sortName="F_PAYMENT"></display:column>
			
			</display:table>
			<hotent:paging tableId="quickpayItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


