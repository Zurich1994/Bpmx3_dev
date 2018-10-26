<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>原子运行次数管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">原子运行次数管理列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link add" href="edit.ht"><span></span>添加</a></div>
				</div>	
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="atomiccountList" id="atomiccountItem" requestURI="getMyDraftJson.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${atomiccountItem.id}">
				</display:column>
				<display:column property="bpmid" title="流程id" sortable="true" sortName="F_BPMID"></display:column>
				<display:column property="operaterid" title="操作图id" sortable="true" sortName="F_OPERATERID"></display:column>
				<display:column property="transactionid" title="事务图id" sortable="true" sortName="F_TRANSACTIONID"></display:column>
				<display:column property="atomicid" title="原子操作id" sortable="true" sortName="F_ATOMICID"></display:column>
				<display:column property="runs" title="运行次数" sortable="true" sortName="F_RUNS"></display:column>
				<display:column  title="时间" sortable="true" sortName="F_TIME">
					<fmt:formatDate value="${atomiccountItem.time}" pattern="yyyy-MM-dd"/>
				</display:column>
				<display:column title="管理" media="html" style="width:220px">
				   <a href="edit.ht?id=${atomiccountItem.id}&taskId=${atomiccountItem.taskId}" class="link edit"><span></span>编辑</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="atomiccountItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


