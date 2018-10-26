<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>子系统流程节点遍历管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">子系统流程节点遍历管理列表</span>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="sysdefnodeergodicList" id="sysdefnodeergodicItem" requestURI="getMyDraftJson.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${sysdefnodeergodicItem.id}">
				</display:column>
				<display:column property="zj" title="主键" sortable="true" sortName="F_ZJ"></display:column>
				<display:column property="sysName" title="子系统名字" sortable="true" sortName="F_SYSNAME"></display:column>
				<display:column property="sysId" title="子系统id" sortable="true" sortName="F_SYSID"></display:column>
				<display:column property="defName" title="流程名字" sortable="true" sortName="F_DEFNAME"></display:column>
				<display:column property="defId" title="流程id" sortable="true" sortName="F_DEFID"></display:column>
				<display:column property="actDefId" title="流程版本id" sortable="true" sortName="F_ACTDEFID"></display:column>
				<display:column property="nodeName" title="节点名字" sortable="true" sortName="F_NODENAME"></display:column>
				<display:column property="nodeWorkName" title="节点作业名" sortable="true" sortName="F_NODEWORKNAME"></display:column>
				<display:column title="管理" media="html" style="width:220px">
				   <a href="get.ht?id=${sysdefnodeergodicItem.id}" class="link detail"><span></span>明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="sysdefnodeergodicItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


