<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>item管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">item管理列表</span>
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
						<span class="label">i_id:</span><input type="text" name="Q_i_id_L"  class="inputText" />
						<span class="label">i_im_id:</span><input type="text" name="Q_i_im_id_L"  class="inputText" />
						<span class="label">i_name:</span><input type="text" name="Q_i_name_S"  class="inputText" />
						<span class="label">i_price:</span><input type="text" name="Q_i_price_L"  class="inputText" />
						<span class="label">i_data:</span><input type="text" name="Q_i_data_S"  class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="itemList" id="itemItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${itemItem.i_id}">
				</display:column>
				<display:column property="i_im_id" title="i_im_id" sortable="true" sortName="I_IM_ID"></display:column>
				<display:column property="i_name" title="i_name" sortable="true" sortName="I_NAME"></display:column>
				<display:column property="i_price" title="i_price" sortable="true" sortName="I_PRICE"></display:column>
				<display:column property="i_data" title="i_data" sortable="true" sortName="I_DATA"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${itemItem.i_id}" class="link del"><span></span>删除</a>
					<a href="edit.ht?id=${itemItem.i_id}" class="link edit"><span></span>编辑</a>
					<a href="get.ht?id=${itemItem.i_id}" class="link detail"><span></span>明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="itemItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


