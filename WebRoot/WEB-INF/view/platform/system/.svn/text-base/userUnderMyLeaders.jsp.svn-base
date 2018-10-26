<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.hotent.platform.model.system.SysUser"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
	<title>上级信息查看</title>
	<%@include file="/commons/include/get.jsp" %>
	<script type="text/javascript" src="${ctx }/js/lg/plugins/ligerWindow.js" ></script>
</head>
<body>
	<div class="panel">
	<div class="hide-panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">上级信息列表</span>
			</div>					
		</div>
		</div>
		<div class="panel-body">					
			<div class="panel-data">
			    <display:table name="sysUserList" id="sysUserItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1"   class="table-grid">
					<display:column property="fullname" title="姓名" sortable="true" sortName="fullname" style="text-align:left"></display:column>
					<display:column property="account" title="帐号" sortable="true" sortName="account" style="text-align:left"></display:column>
					<display:column  title="创建时间" sortable="true" sortName="createtime">
						<fmt:formatDate value="${sysUserItem.createtime}" pattern="yyyy-MM-dd"/>
					</display:column>						        	
				</display:table>
			</div>
		</div>
	</div>
</body>
</html>


