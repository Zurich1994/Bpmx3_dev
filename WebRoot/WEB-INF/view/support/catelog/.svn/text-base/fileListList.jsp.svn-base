<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>文件信息管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">文件信息管理列表</span>
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
						<span class="label">产品ID:</span><input type="text" name="Q_productid_L"  class="inputText" />
						<span class="label">操作系统:</span><input type="text" name="Q_os_S"  class="inputText" />
						<span class="label">下载目录:</span><input type="text" name="Q_filecatalog_S"  class="inputText" />
						<span class="label">语言:</span><input type="text" name="Q_language_S"  class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="fileListList" id="fileListItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${fileListItem.id}">
				</display:column>
				<display:column property="fileid" title="文件ID" sortable="true" sortName="F_FILEID"></display:column>
				<display:column property="filename" title="文件名" sortable="true" sortName="F_FILENAME"></display:column>
				<display:column  title="文件日期" sortable="true" sortName="F_FILEDATE">
					<fmt:formatDate value="${fileListItem.filedate}" pattern="yyyy-MM-dd"/>
				</display:column>
				<display:column property="filesize" title="文件大小" sortable="true" sortName="F_FILESIZE"></display:column>
				<display:column property="description" title="文件描述" sortable="true" sortName="F_DESCRIPTION"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="get.ht?id=${fileListItem.id}" class="link detail"><span></span>详细信息</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="fileListItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


