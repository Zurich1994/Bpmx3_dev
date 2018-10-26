<%
	//
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
	<title>系统角色表管理</title>
	<%@include file="/commons/include/get.jsp" %>
</head>
<body>
			<div class="panel">
			<div class="hide-panel">
				<div class="panel-top">
					<div class="tbar-title">
						<span class="tbar-label">系统角色表管理列表</span>
					</div>
					<div class="panel-toolbar">
						<div class="toolBar">
							<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
							<div class="l-bar-separator"></div>
							<div class="group"><a class="link add"  href="sysRole/add1.ht"><span></span>添加</a></div>
							<div class="l-bar-separator"></div>
							<div class="group"><a class="link  update" id="btnUpd" action="sysRole/upd1.ht"><span></span>修改</a></div>
							<div class="l-bar-separator"></div>
							<div class="group"><a class="link del"  action="sysRole/del.ht"><span></span>删除</a></div>
						</div>	
					</div>
				</div>
				</div>
				<div class="panel-body">
					<div class="panel-search">
							<form id="searchForm" method="post" action="sysRoles.ht">
									<ul class="row">
												<li><span class="label">系统ID:</span><input type="text" name="Q_systemId_L"  class="inputText" value="${param['Q_systemId_L']}"/></li>
												<li><span class="label">角色别名:</span><input type="text" name="Q_alias_S"  class="inputText" value="${param['Q_alias_S']}"/></li>
												<li><span class="label">角色名:</span><input type="text" name="Q_roleName_S"  class="inputText" value="${param['Q_roleName_S']}"/></li>
												<li><span class="label">备注:</span><input type="text" name="Q_memo_S"  class="inputText" value="${param['Q_memo_S']}"/></li>
												<li><span class="label">允许删除:</span><input type="text" name="Q_allowDel_SN"  class="inputText" value="${param['Q_allowDel_SN']}"/></li>
												<li><span class="label">允许编辑:</span><input type="text" name="Q_allowEdit_SN"  class="inputText" value="${param['Q_allowEdit_SN']}"/></li>
												<li><span class="label">是否启用</span><input type="text" name="Q_enabled_SN"  class="inputText" value="${param['Q_enabled_SN']}"/></li>
									</ul>
							</form>
					</div>
					<br/>
					<div class="panel-data">
				    	<c:set var="checkAll">
							<input type="checkbox" id="chkall"/>
						</c:set>
					    <display:table name="sysRoleList" id="sysRoleItem" requestURI="sysRoles.ht" sort="external" cellpadding="1" cellspacing="1" export="true"  class="table-grid">
							<display:column title="${checkAll}" media="html" style="width:30px;">
								  	<input type="checkbox" class="pk" name="roleId" value="${sysRoleItem.roleId}">
							</display:column>
							<display:column property="systemId" title="系统ID" sortable="true" sortName="systemId"></display:column>
							<display:column property="alias" title="角色别名" sortable="true" sortName="alias"></display:column>
							<display:column property="roleName" title="角色名" sortable="true" sortName="roleName"></display:column>
							<display:column property="memo" title="备注" sortable="true" sortName="memo"></display:column>
							<display:column property="allowDel" title="允许删除" sortable="true" sortName="allowDel"></display:column>
							<display:column property="allowEdit" title="允许编辑" sortable="true" sortName="allowEdit"></display:column>
							<display:column property="enabled" title="是否启用" sortable="true" sortName="enabled"></display:column>
							<display:column title="管理" media="html" style="width:120px;">
								<a href="sysRole/del.ht?roleId=${sysRoleItem.roleId}" class="link del">删除</a>
								<a href="sysRole/upd1.ht?roleId=${sysRoleItem.roleId}" class="linkedit">编辑</a>
							</display:column>
						</display:table>
						<hotent:paging tableId="sysRoleItem"/>
					</div>
				</div><!-- end of panel-body -->				
			</div> <!-- end of panel -->
</body>
</html>