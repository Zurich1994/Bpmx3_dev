<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>电子商务搜索表管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">电子商务搜索表管理列表</span>
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
						<span class="label">地区名:</span><input type="text" name="Q_REGION_S"  class="inputText" />
						<span class="label">类别:</span><input type="text" name="Q_CATEGORY_S"  class="inputText" />
						<span class="label">关键字:</span><input type="text" name="Q_KEYWORDS_S"  class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="productModelsList" id="productModelsItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${productModelsItem.id}">
				</display:column>
				<display:column property="REGION" title="地区名" sortable="true" sortName="F_REGION"></display:column>
				<display:column property="CUSTOMER_TYPE_SHORT" title="客户类型简称" sortable="true" sortName="F_CUSTOMER_TYPE_SHORT"></display:column>
				<display:column property="CATEGORY" title="类别" sortable="true" sortName="F_CATEGORY"></display:column>
				<display:column property="PRODUCTID" title="ITEM ID" sortable="true" sortName="F_PRODUCTID"></display:column>
				<display:column property="PRODUCTNAME" title="产品名称" sortable="true" sortName="F_PRODUCTNAME"></display:column>
				<display:column property="FEATURE0" title="产品特征1" sortable="true" sortName="F_FEATURE0"></display:column>
				<display:column property="FEATURE1" title="产品特征2" sortable="true" sortName="F_FEATURE1"></display:column>
				<display:column property="FEATURE2" title="产品特征3" sortable="true" sortName="F_FEATURE2"></display:column>
				<display:column property="FEATURE3" title="产品特征4" sortable="true" sortName="F_FEATURE3"></display:column>
				<display:column property="KEYWORDS" title="关键字" sortable="true" sortName="F_KEYWORDS"></display:column>
				<display:column property="URL" title="链接" sortable="true" sortName="F_URL"></display:column>
				<display:column property="DESCRIPTION" title="产品描述" sortable="true" sortName="F_DESCRIPTION"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${productModelsItem.id}" class="link del"><span></span>删除</a>
					<a href="edit.ht?id=${productModelsItem.id}" class="link edit"><span></span>编辑</a>
					<a href="get.ht?id=${productModelsItem.id}" class="link detail"><span></span>明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="productModelsItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


