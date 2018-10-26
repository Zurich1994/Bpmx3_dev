<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>bpmcount管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">bpmcount管理列表</span>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="bpmcountList" id="bpmcountItem" requestURI="getMyDraftJson.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${bpmcountItem.id}">
				</display:column>
				<display:column property="defid" title="流程id" sortable="true" sortName="F_DEFID"></display:column>
				<display:column property="nodeid" title="流程节点id" sortable="true" sortName="F_NODEID"></display:column>
				<display:column property="operaterid" title="操作图id" sortable="true" sortName="F_OPERATERID"></display:column>
				<display:column property="runs" title="运行次数" sortable="true" sortName="F_RUNS"></display:column>
				<display:column  title="时间" sortable="true" sortName="F_TIME">
					<fmt:formatDate value="${bpmcountItem.time}" pattern="yyyy-MM-dd"/>
				</display:column>
				<display:column title="管理" media="html" style="width:220px">
				   <a href="get.ht?id=${bpmcountItem.id}" class="link detail"><span></span>明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="bpmcountItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


