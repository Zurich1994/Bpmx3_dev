<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>组织管理员</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">组织管理员列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
					<div class="group"><a class="link add" href="edit.ht?orgId=${orgId}&isGrade=${grade}"><span></span>添加</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link update" id="btnUpd" action="edit.ht?orgId=${orgId}&isGrade=${grade}"><span></span>修改</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link del"  action="del.ht"><span></span>删除</a></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<div class="row">
						<span class="label">管理员:</span><input type="text" name="Q_userName_SL"  class="inputText" style="width:13%;" value="${param['Q_userName_SL']}"/>
						<span class="label">管理组织:</span><input type="text" name="Q_orgName_SL"  class="inputText" style="width:13%;" value="${param['Q_orgName_SL']}"/>
					</div>
				</form>
			</div>
		</div>
		
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="orgAuthList" id="orgAuthItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${orgAuthItem.id}">
				</display:column>
				<display:column property="userName" title="管理员" sortable="true" sortName="USER_ID"></display:column>
				<display:column property="orgName" title="管理组织" sortable="true" sortName="ORG_ID"></display:column>
				<display:column property="orgPerms" title="子组织操作权限" sortable="true" sortName="ORG_PERMS"></display:column>
				<display:column property="userPerms" title="用户操作权限" sortable="true" sortName="USER_PERMS"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${orgAuthItem.id}" class="link del">删除</a>
					<a href="edit.ht?id=${orgAuthItem.id}&orgId=${orgId}&isGrade=${grade}" class="link edit">编辑</a>
				</display:column>
			</display:table>
		<hotent:paging tableId="orgAuthItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


