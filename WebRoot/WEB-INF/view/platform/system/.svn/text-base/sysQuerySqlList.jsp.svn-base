<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>自定义多表查询管理</title>
<%@include file="/commons/include/get.jsp" %>
<script type="text/javascript">
//配置
function setting(sqlId){
	var url=__ctx+"/platform/system/sysQuerySetting/edit.ht?sqlId="+sqlId;
	$.openFullWindow(url);
}
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">自定义多表查询管理</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link add" href="edit.ht"><span></span>添加</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link update" id="btnUpd" action="edit.ht"><span></span>修改</a></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<div class="row">
						<span class="label">名称:</span><input type="text" name="Q_name_SL"  class="inputText" value="${param['Q_name_SL']}"/>
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="sysQuerySqlList" id="sysQuerySqlItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${sysQuerySqlItem.id}">
				</display:column>
				<display:column property="categoryName" title="分类" ></display:column>
				<display:column property="name" title="名称" sortable="true" sortName="NAME" maxLength="80"></display:column>
				<display:column property="sql" title="SQL语句" sortable="true" sortName="SQL" maxLength="80"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${sysQuerySqlItem.id}" class="link del">删除</a>
					<a href="edit.ht?id=${sysQuerySqlItem.id}" class="link edit">编辑</a>
					<%-- <a href="get.ht?id=${sysQuerySqlItem.id}" class="link detail">明细</a> --%>
					<a href="javascript:;" onclick="setting(${sysQuerySqlItem.id})" class="link preview">配置</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="sysQuerySqlItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


