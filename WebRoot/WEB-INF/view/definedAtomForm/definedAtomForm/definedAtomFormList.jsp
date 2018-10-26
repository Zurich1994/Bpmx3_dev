<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>defined_atom_form管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">defined_atom_form管理列表</span>
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
						<span class="label">setId:</span><input type="text" name="Q_setId_L"  class="inputText" />
						<span class="label">defId:</span><input type="text" name="Q_defId_L"  class="inputText" />
						<span class="label">nodeId:</span><input type="text" name="Q_nodeId_L"  class="inputText" />
						<span class="label">formKey:</span><input type="text" name="Q_formKey_L"  class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="definedAtomFormList" id="definedAtomFormItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${definedAtomFormItem.id}">
				</display:column>
				<display:column property="setId" title="setId" sortable="true" sortName="F_SETID"></display:column>
				<display:column property="defId" title="defId" sortable="true" sortName="F_DEFID"></display:column>
				<display:column property="nodeId" title="nodeId" sortable="true" sortName="F_NODEID"></display:column>
				<display:column property="formKey" title="formKey" sortable="true" sortName="F_FORMKEY"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${definedAtomFormItem.id}" class="link del"><span></span>删除</a>
					<a href="edit.ht?id=${definedAtomFormItem.id}" class="link edit"><span></span>编辑</a>
					<a href="get.ht?id=${definedAtomFormItem.id}" class="link detail"><span></span>明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="definedAtomFormItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


