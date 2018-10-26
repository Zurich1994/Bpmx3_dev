<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>SYS_OBJ_LOG管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">SYS_OBJ_LOG管理列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link back" href="${param.rtnUrl}"><span></span>返回</a></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht?Q_objType_S=${param.Q_objType_S}&rtnUrl=${param.rtnUrl}">
					<div class="row">
						<span class="label">操作人:</span><input type="text" name="Q_operator_SL"  class="inputText" />
						<span class="label">名称:</span><input type="text" name="Q_name_SL"  class="inputText" />
						<span class="label">创建时间 从:</span> <input  name="Q_begincreateTime_DL"  class="inputText date" />
						<span class="label">至: </span><input  name="Q_endcreateTime_DG" class="inputText date" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="sysObjLogList" id="sysObjLogItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${sysObjLogItem.id}">
				</display:column>
				<display:column property="operator" title="操作人" sortable="true" sortName="OPERATOR" maxLength="80"></display:column>
				<display:column property="name" title="名称" sortable="true" sortName="NAME" maxLength="80"></display:column>
				<display:column  title="创建时间" sortable="true" sortName="CREATE_TIME">
					<fmt:formatDate value="${sysObjLogItem.createTime}" pattern="yyyy-MM-dd"/>
				</display:column>
				<display:column property="objType" title="日志对象类型" sortable="true" sortName="OBJ_TYPE" maxLength="80"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${sysObjLogItem.id}" class="link del">删除</a>
					<a href="get.ht?id=${sysObjLogItem.id}" class="link detail">明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="sysObjLogItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


