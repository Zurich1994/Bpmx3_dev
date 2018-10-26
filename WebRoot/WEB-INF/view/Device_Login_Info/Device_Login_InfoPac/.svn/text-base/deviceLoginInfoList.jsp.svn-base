<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>硬件登录信息表管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">硬件登录信息表管理列表</span>
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
						<span class="label">IP地址:</span><input type="text" name="Q_ip_address_S"  class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="deviceLoginInfoList" id="deviceLoginInfoItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${deviceLoginInfoItem.id}">
				</display:column>
				<display:column property="dev_ID" title="设备编号" sortable="true" sortName="F_DEV_ID"></display:column>
				<display:column property="login_manner" title="登录方式" sortable="true" sortName="F_LOGIN_MANNER"></display:column>
				<display:column property="ip_address" title="IP地址" sortable="true" sortName="F_IP_ADDRESS"></display:column>
				<display:column property="port" title="端口号" sortable="true" sortName="F_PORT"></display:column>
				<display:column property="username" title="用户名" sortable="true" sortName="F_USERNAME"></display:column>
				<display:column property="password" title="密码" sortable="true" sortName="F_PASSWORD"></display:column>
				<display:column property="remark" title="备注" sortable="true" sortName="F_REMARK"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${deviceLoginInfoItem.id}" class="link del"><span></span>删除</a>
					<a href="edit.ht?id=${deviceLoginInfoItem.id}" class="link edit"><span></span>编辑</a>
					<a href="get.ht?id=${deviceLoginInfoItem.id}" class="link detail"><span></span>明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="deviceLoginInfoItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


