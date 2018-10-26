<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>

<%@include file="/commons/include/get.jsp" %>
<title>查询规则管理</title>
<script type="text/javascript" src="${ctx}/js/hotent/platform/bus/BusQueryRuleUtil.js" ></script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">查询规则管理列表<</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
					<div class="l-bar-separator"></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<ul class="row">
						<li>
							<span class="label">表名:</span>
							<input type="text" name="Q_tableName_S"  class="inputText" value="${param['Q_tableName_S']}" />
						</li>
						<li>
							<span class="label">注释:</span>
							<input type="text" name="Q_comment_S"  class="inputText" value="${param['Q_comment_S']}"/>
						</li>
						</li>
					</ul>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="tableEntityList" id="tableEntityItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1"   class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="tableName" value="${tableEntityItem.tableName}">
				</display:column>
				<display:column property="tableName" title="表名" sortable="true" sortName="tableName"></display:column>
				<display:column property="comment" title="注释" sortable="true" sortName="comment"></display:column>
				<display:column  title="是否设置" >
					<c:choose>
						<c:when test="${queryRuleCounts[tableEntityItem.tableName] > 0}">
							<span class="green">已设置</span>
						</c:when>
						<c:otherwise>
							<span class="red">未设置</span>
						</c:otherwise>
					</c:choose>
				</display:column>
				<display:column title="管理" media="html" style="width:50px">	
					<a href="javascript:;" onclick="BusQueryRuleUtil.eidtDialog({tableName:'${tableEntityItem.tableName}'})" class="link edit">设置</a>
				</display:column>
			</display:table>
				<hotent:paging tableId="tableEntityItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


