<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
	<title>桌面栏目管理表管理</title>
	<%@include file="/commons/include/get.jsp" %>
</head>
<body>
			<div class="panel">
			<div class="hide-panel">
				<div class="panel-top">
					<div class="tbar-title">
						<span class="tbar-label">桌面栏目管理表管理列表</span>
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
                            <div class="l-bar-separator"></div>
							<div class="group"><a class="link show"  action="show.ht"><span></span>图型显示</a></div>							
						</div>	
					</div>
				</div>
				</div>
				<div class="panel-body">
					<div class="panel-search">
							<form id="searchForm" method="post" action="list.ht">
									<ul class="row">
										<li><span class="label">布局名称:</span><input type="text" name="Q_layoutId_S"  class="inputText" value="${param['Q_layoutId_S']}"/></li>
									</ul>
							</form>
					</div>
					<br/>
					<div class="panel-data">
				    	<c:set var="checkAll">
							<input type="checkbox" id="chkall"/>
						</c:set>
					    <display:table name="desktopLayoutcolList" id="desktopLayoutcolItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" export="false"  class="table-grid">
							<display:column title="${checkAll}" media="html" style="width:30px;">
							<input type="checkbox" class="pk" name="id" value="${desktopLayoutcolItem.id}">
							</display:column>
							<display:column property="layoutName" title="布局名称" sortable="true" sortName="layoutName"></display:column>
							<display:column property="columnName" title="栏目名称" sortable="true" sortName="columnName"></display:column>
							<display:column property="col" title="列" sortable="true" sortName="col"></display:column>
							<display:column property="sn" title="序号" sortable="true" sortName="sn"></display:column>
							<display:column title="管理" media="html" style="width:180px;text-align:center">
							<a href="del.ht?id=${desktopLayoutcolItem.id}" class="link del">删除</a>
							<a href="edit.ht?id=${desktopLayoutcolItem.id}" class="link edit">编辑</a>
							<a href="get.ht?id=${desktopLayoutcolItem.id}" class="link detail">明细</a>
							</display:column>
						</display:table>
						<hotent:paging tableId="desktopLayoutcolItem"/>
					</div>
				</div><!-- end of panel-body -->				
			</div> <!-- end of panel -->
</body>
</html>


