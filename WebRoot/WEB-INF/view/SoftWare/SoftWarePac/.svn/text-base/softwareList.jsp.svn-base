<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>软件表管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">软件表管理列表</span>
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
						<span class="label">软件名称:</span><input type="text" name="Q_software_Name_S"  class="inputText" />
						<span class="label">软件类型:</span><input type="text" name="Q_software_Type_S"  class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="softwareList" id="softwareItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${softwareItem.id}">
				</display:column>
				<display:column property="softWare_ID" title="软件编号" sortable="true" sortName="F_SOFTWARE_ID"></display:column>
				<display:column property="software_Name" title="软件名称" sortable="true" sortName="F_SOFTWARE_NAME"></display:column>
				<display:column property="software_Type" title="软件类型" sortable="true" sortName="F_SOFTWARE_TYPE"></display:column>
				<display:column property="developer" title="开发商" sortable="true" sortName="F_DEVELOPER"></display:column>
				<display:column property="stage" title="发行阶段" sortable="true" sortName="F_STAGE"></display:column>
				<display:column property="version" title="版本" sortable="true" sortName="F_VERSION"></display:column>
				<display:column property="remark" title="备注" sortable="true" sortName="F_REMARK"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${softwareItem.id}" class="link del"><span></span>删除</a>
					<a href="edit.ht?id=${softwareItem.id}" class="link edit"><span></span>编辑</a>
					<a href="get.ht?id=${softwareItem.id}" class="link detail"><span></span>明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="softwareItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


