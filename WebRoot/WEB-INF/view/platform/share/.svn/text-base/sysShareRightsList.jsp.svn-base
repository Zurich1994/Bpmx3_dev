<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>数据功能权限分享管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">数据功能权限分享管理列表</span>
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
						<span class="label">主键ID:</span><input type="text" name="Q_id_L"  class="inputText" />
						<span class="label">名称:</span><input type="text" name="Q_name_S"  class="inputText" />
						<span class="label">是否启用:</span><input type="text" name="Q_enable_L"  class="inputText" />
						<span class="label">是否同步:</span><input type="text" name="Q_sync_L"  class="inputText" />
						<span class="label">是否全部:</span><input type="text" name="Q_isAll_L"  class="inputText" />
						<span class="label">关联数据的ID:</span><input type="text" name="Q_pkid_L"  class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="sysShareRightsList" id="sysShareRightsItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${sysShareRightsItem.id}">
				</display:column>
				<display:column property="name" title="名称" sortable="true" sortName="NAME"></display:column>
				<display:column property="shareItem" title="共享类型" sortable="true" sortName="SHAREITEM"></display:column>
				<display:column  title="是否启用" sortable="true" sortName="ENABLE">
					<c:choose>
						<c:when test="${sysShareRightsItem.enable==1 }">是</c:when>
						<c:when test="${sysShareRightsItem.enable==0 }">否</c:when>
					</c:choose>
				</display:column>
				<display:column  title="是否同步" sortable="true" sortName="SYNC">
					<c:choose>
						<c:when test="${sysShareRightsItem.sync==1 }">是</c:when>
						<c:when test="${sysShareRightsItem.sync==0 }">否</c:when>
					</c:choose>
				</display:column>
				<display:column title="是否全部" sortable="true" sortName="ISALL">
					<c:choose>
						<c:when test="${sysShareRightsItem.isAll==1 }">是</c:when>
						<c:when test="${sysShareRightsItem.isAll==0 }">否</c:when>
					</c:choose>
				</display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${sysShareRightsItem.id}" class="link del"><span></span>删除</a>
					<a href="edit.ht?id=${sysShareRightsItem.id}" class="link edit"><span></span>编辑</a>
					<a href="get.ht?id=${sysShareRightsItem.id}" class="link detail"><span></span>明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="sysShareRightsItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


