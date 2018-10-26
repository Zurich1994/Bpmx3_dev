<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>redirection管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">redirection管理列表</span>
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
						<span class="label">defID:</span><input type="text" name="Q_defid_S"  class="inputText" />
						<span class="label">nodeID:</span><input type="text" name="Q_nodeid_S"  class="inputText" />
						<span class="label">redirectionURL:</span><input type="text" name="Q_redirectionurl_S"  class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="redirectionList" id="redirectionItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${redirectionItem.defid}">
				</display:column>
				<display:column property="nodeid" title="nodeID" sortable="true" sortName="NODEID"></display:column>
				<display:column property="redirectionurl" title="redirectionURL" sortable="true" sortName="REDIRECTIONURL"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${redirectionItem.defid}" class="link del"><span></span>删除</a>
					<a href="edit.ht?id=${redirectionItem.defid}" class="link edit"><span></span>编辑</a>
					<a href="get.ht?id=${redirectionItem.defid}" class="link detail"><span></span>明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="redirectionItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


