<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>产品模型表管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">产品模型表管理列表</span>
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
			
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
			
		    <display:table name="productModelList" id="productModelItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${productModelItem.id}">
				</display:column>
				
				<display:column property="PRODUCTID" title="产品编号" sortable="true" sortName="F_PRODUCTID"></display:column>
				<display:column property="FEATURE0" title="产品特征1" sortable="true" sortName="F_FEATURE0"></display:column>
				<display:column property="FEATURE1" title="产品特征2" sortable="true" sortName="F_FEATURE1"></display:column>
				<display:column property="FEATURE2" title="产品特征3" sortable="true" sortName="F_FEATURE2"></display:column>
				<display:column property="FEATURE3" title="产品特征4" sortable="true" sortName="F_FEATURE3"></display:column>
				<display:column  title="详细信息" sortable="true" sortName="F_URL" ><a href="/bpmx3_dev/details/details/productDetail/get.ht?id=${productModelItem.PRODUCTID}&EMAIL=<%=request.getParameter("EMAIL")%>" class="link detail"">商品细节</a></display:column>
				 
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${productModelItem.id}" class="link del"><span></span>删除</a>
					<a href="edit.ht?id=${productModelItem.id}" class="link edit"><span></span>编辑</a>
					<a href="get.ht?id=${productModelItem.id}" class="link detail"><span></span>明细</a>
					<a href="/mas/e_business/e_business/component/Modify.ht?id=${productModelItem.PRODUCTID}&EMAIL=<%=request.getParameter("EMAIL")%>" class="link detail"><span></span>用户定制</a>
				</display:column>
				
			</display:table>
			<hotent:paging tableId="productModelItem"/>
		
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


