<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>对话框组合管理</title>
<%@include file="/commons/include/get.jsp" %>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/bpmFormDialogCombinate/CombinateDialogUtil.js"></script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">对话框组合管理列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link add" href="edit.ht"><span></span>添加</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link update" id="btnUpd" action="edit.ht"><span></span>修改</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link back" href="${ctx}/platform/form/bpmFormDialog/list.ht"><span></span>返回</a></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<div class="row">
						<span class="label">名称:</span><input type="text" name="Q_name_SL"  class="inputText" />
						<span class="label">别名:</span><input type="text" name="Q_alias_SL"  class="inputText" />
						<span class="label">左边树对话框名称:</span><input type="text" name="Q_treeDialogName_SL"  class="inputText" />
						<span class="label">右边列对话框名称:</span><input type="text" name="Q_listDialogName_SL"  class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="bpmFormDialogCombinateList" id="bpmFormDialogCombinateItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${bpmFormDialogCombinateItem.id}">
				</display:column>
				<display:column property="name" title="名称" sortable="true" sortName="NAME" maxLength="80"></display:column>
				<display:column property="alias" title="别名" sortable="true" sortName="ALIAS" maxLength="80"></display:column>
				<display:column property="width" title="宽度" sortable="true" sortName="WIDTH"></display:column>
				<display:column property="height" title="长度" sortable="true" sortName="HEIGHT"></display:column>
				<display:column property="treeDialogName" title="左边树对话框名称" sortable="true" sortName="TREE_DIALOG_NAME" maxLength="80"></display:column>
				<display:column property="listDialogName" title="右边列对话框名称" sortable="true" sortName="LIST_DIALOG_NAME" maxLength="80"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${bpmFormDialogCombinateItem.id}" class="link del">删除</a>
					<a href="edit.ht?id=${bpmFormDialogCombinateItem.id}" class="link edit">编辑</a>
					<a onclick="CombinateDialogUtil.openByAlias('${bpmFormDialogCombinateItem.alias}')" class="link detail">预览</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="bpmFormDialogCombinateItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


