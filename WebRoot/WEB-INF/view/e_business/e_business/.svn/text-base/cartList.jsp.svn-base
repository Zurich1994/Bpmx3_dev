<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>购物车管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
<%
 String EMAIL=request.getParameter("EMAIL");
 out.print(EMAIL);
 %>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">购物车管理列表</span>
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
						<span class="label">EMAIL地址:</span><input type="text" name="Q_EMAIL_S"  class="inputText" />
						<span class="label">ITEMID:</span><input type="text" name="Q_PRODUCTID_S"  class="inputText" />
						<span class="label">ITEM名称:</span><input type="text" name="Q_ITEMName_S"  class="inputText" />
						<span class="label">组件id:</span><input type="text" name="Q_COMPONENTID_S"  class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="cartList" id="cartItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${cartItem.id}">
				</display:column>
				<display:column property="EMAIL" title="EMAIL地址" sortable="true" sortName="F_EMAIL"></display:column>
				<display:column property="PRODUCTID" title="ITEMID" sortable="true" sortName="F_PRODUCTID"></display:column>
				<display:column property="ITEMName" title="ITEM名称" sortable="true" sortName="F_ITEMNAME"></display:column>
				<display:column property="COMPONENTTYPE" title="组件类型" sortable="true" sortName="F_COMPONENTTYPE"></display:column>
				<display:column property="COMPONENTID" title="组件id" sortable="true" sortName="F_COMPONENTID"></display:column>
				<display:column property="QUANTITY" title="数量" sortable="true" sortName="F_QUANTITY"></display:column>
				<display:column property="UNITPRICE" title="单价" sortable="true" sortName="F_UNITPRICE"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${cartItem.id}" class="link del"><span></span>删除</a>
					<a href="edit.ht?id=${cartItem.id}" class="link edit"><span></span>编辑</a>
					<a href="get.ht?id=${cartItem.id}" class="link detail"><span></span>结算</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="cartItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


