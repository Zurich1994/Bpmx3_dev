<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>服务器-软件表管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">服务器-软件表管理列表</span>
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
						<span class="label">服务器编号:</span><input type="text" name="Q_Server_ID_S"  class="inputText" />
						<span class="label">软件编号:</span><input type="text" name="Q_SoftWare_ID_S"  class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="serverSoftwareList" id="serverSoftwareItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${serverSoftwareItem.id}">
				</display:column>
				<display:column property="Server_ID" title="服务器编号" sortable="true" sortName="F_SERVER_ID"></display:column>
				<display:column property="SoftWare_ID" title="软件编号" sortable="true" sortName="F_SOFTWARE_ID"></display:column>
				<display:column property="Software_Path" title="软件部署路径" sortable="true" sortName="F_SOFTWARE_PATH"></display:column>
				<display:column property="Software_Port" title="软件监听端口" sortable="true" sortName="F_SOFTWARE_PORT"></display:column>
				<display:column property="Login_Type" title="软件登陆方式" sortable="true" sortName="F_LOGIN_TYPE"></display:column>
				<display:column property="DataBase" title="数据库名" sortable="true" sortName="F_DATABASE"></display:column>
				<display:column property="Login_UserName" title="软件登陆用户名" sortable="true" sortName="F_LOGIN_USERNAME"></display:column>
				<display:column property="Login_Password" title="软件登陆密码" sortable="true" sortName="F_LOGIN_PASSWORD"></display:column>
				<display:column property="Remark" title="备注" sortable="true" sortName="F_REMARK"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${serverSoftwareItem.id}" class="link del"><span></span>删除</a>
					<a href="edit.ht?id=${serverSoftwareItem.id}" class="link edit"><span></span>编辑</a>
					<a href="get.ht?id=${serverSoftwareItem.id}" class="link detail"><span></span>明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="serverSoftwareItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


