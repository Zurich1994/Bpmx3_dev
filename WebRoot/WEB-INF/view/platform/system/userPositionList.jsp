<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>SYS_USER_POS管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">SYS_USER_POS管理列表</span>
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
				<form id="searchForm" method="post" action="list.ht?orgId=${orgId}">
					<div class="row">
						<span class="label">组织ID:</span><input type="text" name="Q_orgid_SL"  class="inputText" />
						<span class="label">岗位ID:</span><input type="text" name="Q_posid_SL"  class="inputText" />
						<span class="label">用户ID:</span><input type="text" name="Q_userid_SL"  class="inputText" />
						<span class="label">是否主岗位:</span><input type="text" name="Q_isprimary_SL"  class="inputText" />
						<span class="label">是否负责人:</span><input type="text" name="Q_ischarge_SL"  class="inputText" />
						<span class="label">是否删除:</span><input type="text" name="Q_isdelete_SL"  class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="userPositionList" id="userPositionItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="userPosId" value="${userPositionItem.userPosId}">
				</display:column>
				<display:column property="orgId" title="组织ID" sortable="true" sortName="ORGID"></display:column>
				    <display:column property="orgName" title="组织名称" ></display:column>
				<display:column property="posId" title="岗位ID" sortable="true" sortName="POSID"></display:column>
					<display:column property="posName" title="岗位名称" ></display:column>
				<display:column property="userId" title="用户ID" sortable="true" sortName="USERID"></display:column>
					<display:column property="userName" title="用户名称" ></display:column>
				<display:column property="isPrimary" title="是否主岗位" sortable="true" sortName="ISPRIMARY"></display:column>
				<display:column property="isCharge" title="是否负责人" sortable="true" sortName="ISCHARGE"></display:column>
				<display:column property="isDelete" title="是否删除" sortable="true" sortName="ISDELETE"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?userPosId=${userPositionItem.userPosId}" class="link del">删除</a>
					<a href="edit.ht?userPosId=${userPositionItem.userPosId}" class="link edit">编辑</a>
					<a href="get.ht?userPosId=${userPositionItem.userPosId}" class="link detail">明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="userPositionItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


