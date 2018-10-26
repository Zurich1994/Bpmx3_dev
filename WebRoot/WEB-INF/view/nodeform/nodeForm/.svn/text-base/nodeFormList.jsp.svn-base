<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>节点表单对应表管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">节点表单对应表管理列表</span>
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
						<span class="label">查询条件1:</span><input type="text" name="Q_condition1_S"  class="inputText" />
						<span class="label">查询条件3:</span><input type="text" name="Q_condition3_S"  class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="nodeFormList" id="nodeFormItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${nodeFormItem.id}">
				</display:column>
				<display:column property="condition1" title="查询条件1" sortable="true" sortName="F_CONDITION1"></display:column>
				<display:column property="condition2" title="查询条件2" sortable="true" sortName="F_CONDITION2"></display:column>
				<display:column property="condition3" title="查询条件3" sortable="true" sortName="F_CONDITION3"></display:column>
				<display:column property="formID" title="表单id" sortable="true" sortName="F_FORMID"></display:column>
				<display:column property="formName" title="表单名称" sortable="true" sortName="F_FORMNAME"></display:column>
				<display:column  title="getFormID" sortable="true" > <a href="getFormID.ht?condition1=${nodeFormItem.condition1}&condition2=${nodeFormItem.condition2}&condition3=${nodeFormItem.condition3}">test</a> </display:column>
				<display:column  title="getTableID" sortable="true" > <a href="getTableID.ht?condition1=${nodeFormItem.condition1}&condition2=${nodeFormItem.condition2}&condition3=${nodeFormItem.condition3}">test</a> </display:column>
				<display:column  title="getTableName" sortable="true" > <a href="getTableName.ht?condition1=${nodeFormItem.condition1}&condition2=${nodeFormItem.condition2}&condition3=${nodeFormItem.condition3}">test</a> </display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${nodeFormItem.id}" class="link del"><span></span>删除</a>
					<a href="edit.ht?id=${nodeFormItem.id}" class="link edit"><span></span>编辑</a>
					<a href="get.ht?id=${nodeFormItem.id}" class="link detail"><span></span>明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="nodeFormItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


