<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>form_def_combinate管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">组合表单管理列表</span>
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
						<span class="label">树对话框名称:</span><input type="text" name="Q_treeDialogName_SL"  class="inputText" />
						<span class="label">表单名称:</span><input type="text" name="Q_formDefName_SL"  class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="formDefCombinateList" id="formDefCombinateItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${formDefCombinateItem.id}">
				</display:column>
				<display:column property="name" title="名称" sortable="true" sortName="NAME" maxLength="80"></display:column>
				<display:column property="alias" title="别名" sortable="true" sortName="ALIAS" maxLength="80"></display:column>
				<display:column property="treeDialogName" title="树对话框名称" sortable="true" sortName="TREE_DIALOG_NAME" maxLength="80"></display:column>
				<display:column property="formDefName" title="表单名称" sortable="true" sortName="FORM_DEF_NAME" maxLength="80"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${formDefCombinateItem.id}" class="link del">删除</a>
					<a href="edit.ht?id=${formDefCombinateItem.id}" class="link edit">编辑</a>
					<a href="show_${formDefCombinateItem.alias}.ht" target="_blank" class="link detail">预览</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="formDefCombinateItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


