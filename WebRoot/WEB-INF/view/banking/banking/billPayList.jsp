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
				<span class="tbar-label">用户ID管理列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link add" href="edit.ht"><span></span>添加</a></div>
			</div>
					
				</div>	
			</div>
			</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="billPayList" id="billPayItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column property="PAYEEID" title="收款人" sortable="true" sortName="F_PAYEEID"></display:column>
				<display:column  title="日期" sortable="true" sortName="F_DATA">
					<fmt:formatDate value="${billPayItem.DATA}" pattern="yyyy-MM-dd HH-mm-ss"/>
				</display:column>
				<display:column property="PAYMENT" title="付款金额" sortable="true" sortName="F_PAYMENT"></display:column>
			
			</display:table>
			<hotent:paging tableId="billPayItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


