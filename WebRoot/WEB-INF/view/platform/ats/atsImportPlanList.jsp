<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>打卡导入方案管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">打卡导入方案管理列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link add" href="edit.ht"><span></span>添加</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link update" id="btnUpd" action="edit.ht"><span></span>修改</a></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<div class="row">
						<span class="label">编码:</span><input type="text" name="Q_code_SL"  class="inputText" />
						<span class="label">名称:</span><input type="text" name="Q_name_SL"  class="inputText" />
						<span class="label">描述:</span><input type="text" name="Q_memo_SL"  class="inputText" />
						<span class="label">打卡对应关系:</span><input type="text" name="Q_pushCardMap_SL"  class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="atsImportPlanList" id="atsImportPlanItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${atsImportPlanItem.id}">
				</display:column>
				<display:column property="code" title="编码" sortable="true" sortName="CODE" maxLength="80"></display:column>
				<display:column property="name" title="名称" sortable="true" sortName="NAME" maxLength="80"></display:column>
				<display:column property="memo" title="描述" sortable="true" sortName="MEMO" maxLength="80"></display:column>
				<display:column property="pushCardMap" title="打卡对应关系" sortable="true" sortName="PUSH_CARD_MAP" maxLength="80"></display:column>
				<display:column title="管理" media="html"  class="rowOps">
					<a href="del.ht?id=${atsImportPlanItem.id}" class="link del">删除</a>
					<a href="edit.ht?id=${atsImportPlanItem.id}" class="link edit">编辑</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="atsImportPlanItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


