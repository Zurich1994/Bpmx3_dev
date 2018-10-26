<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>虚拟机表管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">虚拟机表管理列表</span>
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
						<span class="label">服务器编号:</span><input type="text" name="Q_server_ID_S"  class="inputText" />
						<span class="label">IP:</span><input type="text" name="Q_ip_S"  class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="deviceVirtualList" id="deviceVirtualItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${deviceVirtualItem.id}">
				</display:column>
				<display:column property="virtual_ID" title="虚拟机编号" sortable="true" sortName="F_VIRTUAL_ID"></display:column>
				<display:column property="server_ID" title="服务器编号" sortable="true" sortName="F_SERVER_ID"></display:column>
				<display:column property="ip" title="IP" sortable="true" sortName="F_IP"></display:column>
				<display:column property="os_name" title="操作系统" sortable="true" sortName="F_OS_NAME"></display:column>
				<display:column property="user" title="使用人" sortable="true" sortName="F_USER"></display:column>
				<display:column property="use" title="用途" sortable="true" sortName="F_USE"></display:column>
				<display:column property="login_Username" title="用户名" sortable="true" sortName="F_LOGIN_USERNAME"></display:column>
				<display:column property="login_Password" title="密码" sortable="true" sortName="F_LOGIN_PASSWORD"></display:column>
				<display:column property="remark" title="备注" sortable="true" sortName="F_REMARK"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${deviceVirtualItem.id}" class="link del"><span></span>删除</a>
					<a href="edit.ht?id=${deviceVirtualItem.id}" class="link edit"><span></span>编辑</a>
					<a href="get.ht?id=${deviceVirtualItem.id}" class="link detail"><span></span>明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="deviceVirtualItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


