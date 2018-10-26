<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>绑定表管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">绑定表管理列表</span>
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
						<span class="label">按钮名称:</span><input type="text" name="Q_btn_name_S"  class="inputText" />
						<span class="label">按钮类型:</span><input type="text" name="Q_btn_type_S"  class="inputText" />
						<span class="label">按钮概率:</span><input type="text" name="Q_btn_probability_S"  class="inputText" />
						<span class="label">在线流程流程定义Id:</span><input type="text" name="Q_defbId_S"  class="inputText" />
						<span class="label">表单Id:</span><input type="text" name="Q_formId_S"  class="inputText" />
						<span class="label">流程定义Id:</span><input type="text" name="Q_defId_S"  class="inputText" />
						<span class="label">节点Id:</span><input type="text" name="Q_nodeId_S"  class="inputText" />
						<span class="label">自定义按钮Id:</span><input type="text" name="Q_xId_S"  class="inputText" />
						<span class="label">表单定义Id:</span><input type="text" name="Q_formDefId_S"  class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="bpmFormBangList" id="bpmFormBangItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${bpmFormBangItem.id}">
				</display:column>
				<display:column property="btn_name" title="按钮名称" sortable="true" sortName="F_BTN_NAME"></display:column>
				<display:column property="btn_type" title="按钮类型" sortable="true" sortName="F_BTN_TYPE"></display:column>
				<display:column property="btn_probability" title="按钮概率" sortable="true" sortName="F_BTN_PROBABILITY"></display:column>
				<display:column property="defbId" title="在线流程流程定义Id" sortable="true" sortName="F_DEFBID"></display:column>
				<display:column property="formId" title="表单Id" sortable="true" sortName="F_FORMID"></display:column>
				<display:column property="defId" title="流程定义Id" sortable="true" sortName="F_DEFID"></display:column>
				<display:column property="nodeId" title="节点Id" sortable="true" sortName="F_NODEID"></display:column>
				<display:column property="xId" title="自定义按钮Id" sortable="true" sortName="F_XID"></display:column>
				<display:column property="formDefId" title="表单定义Id" sortable="true" sortName="F_FORMDEFID"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${bpmFormBangItem.id}" class="link del"><span></span>删除</a>
					<a href="edit.ht?id=${bpmFormBangItem.id}" class="link edit"><span></span>编辑</a>
					<a href="get.ht?id=${bpmFormBangItem.id}" class="link detail"><span></span>明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="bpmFormBangItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


