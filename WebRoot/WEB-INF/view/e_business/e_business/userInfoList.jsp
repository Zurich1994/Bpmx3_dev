<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>用户信息表管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">用户信息表管理列表</span>
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
						<span class="label">用户E-mail地址:</span><input type="text" name="Q_EMAIL_S"  class="inputText" />
						<span class="label">用户密码:</span><input type="text" name="Q_PASSWORD_S"  class="inputText" />
						<span class="label">用户名字:</span><input type="text" name="Q_FIRSTNAME_S"  class="inputText" />
						<span class="label">用户姓氏:</span><input type="text" name="Q_LASTNAME_S"  class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="userInfoList" id="userInfoItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${userInfoItem.id}">
				</display:column>
				<display:column property="EMAIL" title="用户E-mail地址" sortable="true" sortName="F_EMAIL"></display:column>
				<display:column property="PASSWORD" title="用户密码" sortable="true" sortName="F_PASSWORD"></display:column>
				<display:column property="FIRSTNAME" title="用户名字" sortable="true" sortName="F_FIRSTNAME"></display:column>
				<display:column property="LASTNAME" title="用户姓氏" sortable="true" sortName="F_LASTNAME"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${userInfoItem.id}" class="link del"><span></span>删除</a>
					<a href="edit.ht?id=${userInfoItem.id}" class="link edit"><span></span>编辑</a>
					<a href="get.ht?id=${userInfoItem.id}" class="link detail"><span></span>明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="userInfoItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


