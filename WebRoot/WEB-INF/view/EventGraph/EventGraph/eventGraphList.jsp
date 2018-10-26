<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>事件绑定管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">事件绑定管理列表</span>
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
						<span class="label">元素Id:</span><input type="text" name="Q_DomId_L"  class="inputText" />
						<span class="label">元素类型:</span><input type="text" name="Q_DomType_S"  class="inputText" />
						<span class="label">FormDefId:</span><input type="text" name="Q_FormDefId_L"  class="inputText" />
						<span class="label">元素事件:</span><input type="text" name="Q_Domevent_S"  class="inputText" />
						<span class="label">defId:</span><input type="text" name="Q_defId_L"  class="inputText" />
						<span class="label">datatemplateId:</span><input type="text" name="Q_datatemplateId_L"  class="inputText" />
						<span class="label">formKey:</span><input type="text" name="Q_formKey_S"  class="inputText" />
						<span class="label">xkey:</span><input type="text" name="Q_xkey_S"  class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="eventGraphList" id="eventGraphItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${eventGraphItem.id}">
				</display:column>
				<display:column property="DomId" title="元素Id" sortable="true" sortName="F_DOMID"></display:column>
				<display:column property="DomType" title="元素类型" sortable="true" sortName="F_DOMTYPE"></display:column>
				<display:column property="FormDefId" title="FormDefId" sortable="true" sortName="F_FORMDEFID"></display:column>
				<display:column property="Domevent" title="元素事件" sortable="true" sortName="F_DOMEVENT"></display:column>
				<display:column property="defId" title="defId" sortable="true" sortName="F_DEFID"></display:column>
				<display:column property="InputDom" title="读元素" sortable="true" sortName="F_INPUTDOM"></display:column>
				<display:column property="OutputDom" title="写元素" sortable="true" sortName="F_OUTPUTDOM"></display:column>
				<display:column property="datatemplateId" title="datatemplateId" sortable="true" sortName="F_DATATEMPLATEID"></display:column>
				<display:column property="formKey" title="formKey" sortable="true" sortName="F_FORMKEY"></display:column>
				<display:column property="xkey" title="xkey" sortable="true" sortName="F_XKEY"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${eventGraphItem.id}" class="link del"><span></span>删除</a>
					<a href="edit.ht?id=${eventGraphItem.id}" class="link edit"><span></span>编辑</a>
					<a href="get.ht?id=${eventGraphItem.id}" class="link detail"><span></span>明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="eventGraphItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


