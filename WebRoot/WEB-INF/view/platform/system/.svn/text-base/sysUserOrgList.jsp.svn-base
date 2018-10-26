
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>用户所属组织或部门管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
			<div class="panel">
			<div class="hide-panel">
				<div class="panel-top">
					<div class="tbar-title">
						<span class="tbar-label">用户所属组织或部门管理列表</span>
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
				</div>
				</div>
				<div class="panel-body">
					<div class="panel-search">
							<form id="searchForm" method="post" action="list.ht">
									<ul class="row">
												<li><span class="label">组织ID:</span><input type="text" name="Q_orgId_S"  class="inputText" value="${param['Q_orgId_S']}"/></li>
											
												<li><span class="label">userId:</span><input type="text" name="Q_userId_S"  class="inputText" value="${param['Q_userId_S']}"/></li>
											
												<li><span class="label">是否为主要:</span><input type="text" name="Q_isPrimary_SN"  class="inputText" value="${param['Q_isPrimary_SN']}"/></li>
											
												<li><span class="label">组织负责人ID:</span><input type="text" name="Q_isCharge_S"  class="inputText" value="${param['Q_isCharge_S']}"/></li>
											
									</ul>
							</form>
					</div>
					<br/>
					<div class="panel-data">
				    	<c:set var="checkAll">
							<input type="checkbox" id="chkall"/>
						</c:set>
					    <display:table name="sysUserOrgList" id="sysUserOrgItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" export="true"  class="table-grid">
							<display:column title="${checkAll}" media="html" style="width:30px;">
								  	<input type="checkbox" class="pk" name="userOrgId" value="${sysUserOrgItem.userOrgId}">
							</display:column>
							<display:column property="orgId" title="组织ID" sortable="true" sortName="orgId"></display:column>
							<display:column property="userId" title="userId" sortable="true" sortName="userId"></display:column>
							<display:column property="isPrimary" title="是否为主要" sortable="true" sortName="isPrimary"></display:column>
							<display:column property="isCharge" title="组织负责人ID" sortable="true" sortName="isCharge"></display:column>
							<display:column title="管理" media="html" style="width:180px;text-align:center">
								<a href="del.ht?userOrgId=${sysUserOrgItem.userOrgId}" class="link del">删除</a>
								<a href="edit.ht?userOrgId=${sysUserOrgItem.userOrgId}" class="link edit">编辑</a>
								<a href="get.ht?userOrgId=${sysUserOrgItem.userOrgId}" class="link detail">明细</a>
							</display:column>
						</display:table>
						<hotent:paging tableId="sysUserOrgItem"/>
					</div>
				</div><!-- end of panel-body -->				
			</div> <!-- end of panel -->
</body>
</html>


