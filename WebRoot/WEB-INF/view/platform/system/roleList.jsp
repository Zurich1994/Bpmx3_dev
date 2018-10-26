<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="hotent" uri="http://www.jee-soft.cn/paging" %>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>角色列表</title>
	<%@include file="/commons/include/form.jsp" %>
</head>
<body>
	    <h2>角色列表</h2>
		<div class="panel-search">
			<div class="tbar-title"><span class="tbar-label search-icon">搜索</span></div>
			<form method="post" action="role.ht">
			<table>
				<tr>
					<td>
						角色名称:<input type="text" name="Q_name_S" value="${params['name']}" class="inputText" width="100"/>
					</td>
					<td>
						<input type="submit" value="submit" onclick="this.form.submit()"/>
					</td>
				<tr>
			</table>
			</form>
		</div>
		<br/>
	    <div class="panel-data">
	    	<div class="tbar-title"><span class="tbar-label grid-icon">角色列表</span></div>
	    	<c:set var="checkAll">
				<input type="checkbox" onclick="checkAll(this,'roleId');"/>
			</c:set>
		    <display:table name="roleList" id="roleItem" requestURI="role.ht" sort="external" cellpadding="0" cellspacing="1" export="true"  class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
					  	<input type="checkbox" name="roleId" value="${roleItem.id}">
				</display:column>
				<display:column property="name" title="名称" sortable="true" sortName="name"></display:column>
				<display:column property="appid"  title="应用Id"  sortable="true" sortName="appid"></display:column>
				<display:column property="memo"  title="备注" sortable="true" sortName="memo"></display:column>
				<display:column property="allowdel"  title="允许删除"  sortable="true" sortName="allowdel"></display:column>
				<display:column property="allowedit"  title="允许编辑"  sortable="true" sortName="allowedit"></display:column>
				<display:column title="管理" media="html" style="width:120px;">
					<a href="role/delete.jp?id=${roleItem.id}" class="link del">删除</a>
					<a href="role/get.jp?id=${roleItem.id}" class="link edit">编辑</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="roleItem"/>
		</div>
</body>
</html>