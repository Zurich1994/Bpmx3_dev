<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>设备编号规则表管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">设备编号规则表管理列表</span>
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
						<span class="label">前缀1:</span><input type="text" name="Q_prefix1_S"  class="inputText" />
						<span class="label">前缀1含义:</span><input type="text" name="Q_Implication1_S"  class="inputText" />
						<span class="label">前缀2:</span><input type="text" name="Q_prefix2_S"  class="inputText" />
						<span class="label">前缀2含义:</span><input type="text" name="Q_Implication2_S"  class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="deviceNumberRuleList" id="deviceNumberRuleItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${deviceNumberRuleItem.id}">
				</display:column>
				<display:column property="prefix1" title="前缀1" sortable="true" sortName="F_PREFIX1"></display:column>
				<display:column property="Implication1" title="前缀1含义" sortable="true" sortName="F_IMPLICATION1"></display:column>
				<display:column property="prefix2" title="前缀2" sortable="true" sortName="F_PREFIX2"></display:column>
				<display:column property="Implication2" title="前缀2含义" sortable="true" sortName="F_IMPLICATION2"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${deviceNumberRuleItem.id}" class="link del"><span></span>删除</a>
					<a href="edit.ht?id=${deviceNumberRuleItem.id}" class="link edit"><span></span>编辑</a>
					<a href="get.ht?id=${deviceNumberRuleItem.id}" class="link detail"><span></span>明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="deviceNumberRuleItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


