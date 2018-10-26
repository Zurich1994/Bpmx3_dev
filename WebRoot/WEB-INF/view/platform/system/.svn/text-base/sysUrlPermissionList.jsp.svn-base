<%--
	time:2012-03-16 10:53:20
	desc:edit the 拦截URL
--%>
<%@page language="java" pageEncoding="UTF-8" import="java.util.*"%>
<%@include file="/commons/include/html_doctype.html"%>

<html>
<head>
	<%@include file="/commons/include/get.jsp" %>
	<title>拦截URL管理列表</title>
	<script type="text/javascript">
	
	</script>
</head>
<body>
<div class="panel">
	<div class="panel-toolbar">
		<div class="toolBar">
				<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>  
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link add" id="btnadd" href="edit.ht"><span></span>添加</a></div> 
				<div class="l-bar-separator"></div>
				<div class="group"><a href="javascript:;" class="link del"  action="del.ht" ><span></span>删除</a></div>
				<div class="l-bar-separator"></div>
			    <div class="group"><a class="link reset" onclick="$.clearQueryForm()"><span></span>重置</a></div>
		</div>
	</div>
	<div class="panel-search">
		<form id="searchForm" method="post" action="list.ht">
			<div class="row">
				<span class="label">拦截器名称:</span><input type="text" name="Q_descp_SL"  class="inputText" style="width:120px;" value="${param['Q_descp_SL'] }"/>
				<span class="label">是否启用:</span>
				<select name="Q_enable_L" class="select" value="${param['Q_enable_L']}">
					<option value="">全部</option>
					<option value="0" <c:if test="${param['Q_enable_L'] ==0}">selected</c:if> >禁用</option>
					<option value="1" <c:if test="${param['Q_enable_L'] ==1}">selected</c:if> >启用</option>
				</select>
			</div>
		</form>
	</div>
	<div class="panel-body">
			<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="sysUrlPermissionList" id="sysUrlPermissionItems" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1"  class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${sysUrlPermissionItems.id}">
				</display:column>
				<display:column  title="拦截器名称" sortable="true" sortName="descp" property="descp"/>
				<display:column title="URL地址" sortable="true" sortName="url" property="url"/>
				<display:column  title="参数名称" sortable="true" sortName="params" property="params"/>
				<display:column  title="是否启用" sortable="true"  >
					<c:choose>
						<c:when test="${sysUrlPermissionItems.enable==0}">禁用</c:when>
						<c:when test="${sysUrlPermissionItems.enable==1}">启用</c:when>
					</c:choose>
				</display:column>
				<display:column title="管理" media="html" style="width:180px;line-height:21px;">
					<a href="get.ht?id=${sysUrlPermissionItems.id}" class="link edit">明细</a>
					<a href="edit.ht?id=${sysUrlPermissionItems.id}" class="link edit">编辑</a>
					<a href="del.ht?id=${sysUrlPermissionItems.id}" class="link del">删除</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="sysUrlPermissionItem"/>
	</div>
</div>
</body>
</html>
