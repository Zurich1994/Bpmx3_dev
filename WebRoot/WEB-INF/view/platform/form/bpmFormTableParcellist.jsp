<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
	<head>
		<title>table_parcel管理</title>
		<%@include file="/commons/include/get.jsp"%>
	</head>
	<body>
		<div class="panel">
			<div class="panel-top">
				<div class="tbar-title">
					<span class="tbar-label">table_parcel管理列表</span>
				</div>
				<div class="panel-toolbar">
					<div class="toolBar">
						<!-- <div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
					<div class="group"><a class="link update" id="btnUpd" action="edit.ht"><span></span>修改</a></div>
						<div class="l-bar-separator"></div>
						 -->


						<div class="group">
							<a class="link add"
								href="editwb.ht?tableId=<%=request.getParameter("tableId")%>"><span></span>添加</a>
						</div>
						<div class="l-bar-separator"></div>


						<div class="group">
							<a class="link del" action="delparcel.ht"><span></span>删除</a>
						</div>
						<div class="l-bar-separator"></div>
						<div class="group">
							<a class="link back" href="list.ht"><span></span>返回</a>
						</div>
					</div>
				</div>
				<!-- 
			<div class="panel-search">
				<form id="searchForm" method="post" action="parcellist.ht">
					<div class="row">
					 	<span class="label">表名:</span><input type="text" name="Q_table_name_S"  class="inputText" />
						 
						<span class="label">数据包名:</span><input type="text" name="Q_parcel_name_S"  class="inputText" />
						<span class="label">数据包注释:</span><input type="text" name="Q_parcel_alias_S"  class="inputText" />
					 
						
						<span class="label">数据包值:</span><input type="text" name="Q_parcel_value_S"  class="inputText" />
					
					</div>
				</form>
			</div>
			 -->
			</div>
			<div class="panel-body">
				<c:set var="checkAll">
					<input type="checkbox" id="chkall" />
				</c:set>
				<display:table name="tableParcelList" id="tableParcelItem"
					requestURI="list.ht" sort="external" cellpadding="1"
					cellspacing="1" class="table-grid">
					<display:column title="${checkAll}" media="html"
						style="width:30px;">
						<input type="checkbox" class="pk" name="id"
							value="${tableParcelItem.id}">
					</display:column>
					<display:column property="table_name" title="表名" sortable="true"
						sortName="F_TABLE_NAME"></display:column>
					<display:column property="parcel_name" title="数据包名" sortable="true"
						sortName="F_PARCEL_NAME"></display:column>
					<display:column property="parcel_alias" title="数据包注释"
						sortable="true" sortName="F_PARCEL_ALIAS"></display:column>
					<display:column property="parcel_value" title="数据包值"
						sortable="true" sortName="F_PARCEL_VALUE"></display:column>
					<display:column title="管理" media="html" style="width:120px">
						<a href="delparcel.ht?id=${tableParcelItem.id}" class="link del"><span></span>删除</a>
						<a href="editwb.ht?id=${tableParcelItem.id}&&tableId=<%=request.getParameter("tableId")%>" class="link edit"><span></span>编辑</a>
						
					</display:column>
				</display:table>
				<hotent:paging tableId="tableParcelItem" />
			</div>
			<!-- end of panel-body -->
		</div>
		<!-- end of panel -->
	</body>
</html>


