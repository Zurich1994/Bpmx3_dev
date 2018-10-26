
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>系统日历管理</title>
	<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
	<div class="hide-panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">系统日历管理列表</span>
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
					<ul class="row">
						<li><span class="label">日历名称:</span><input type="text" name="Q_name_SL"  class="inputText" value="${param['Q_name_SL']}"/></li>
					</ul>
				</form>
			</div>
		</div>
		</div>
		<div class="panel-body">
			
		    	<c:set var="checkAll">
					<input type="checkbox" id="chkall"/>
				</c:set>
			    <display:table name="sysCalendarList" id="sysCalendarItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1"  class="table-grid">
					<display:column title="${checkAll}" media="html" style="width:30px;">
						  	<input type="checkbox" class="pk" name="id" value="${sysCalendarItem.id}">
					</display:column>
					<display:column property="name" title="日历名称" sortable="true" sortName="name"></display:column>
					<display:column title="默认" sortable="true" sortName="name" style="text-align:center;">
						<c:choose>
							<c:when test="${sysCalendarItem.isDefault==0 }">
								<span class="red">非默认</span>
							</c:when>
							<c:otherwise>
								<span class="green">默认</span>
							</c:otherwise>
						</c:choose>
					</display:column>
					<display:column property="memo" title="描述" sortable="true" sortName="memo" maxLength="80"></display:column>
					<display:column title="管理" media="html" style="width:180px;text-align:center" >
						<a href="edit.ht?id=${sysCalendarItem.id}" class="link edit">编辑</a>
						<c:if test="${sysCalendarItem.isDefault==0}">
							<a href="setDefault.ht?id=${sysCalendarItem.id}" class="link detail">设置默认</a>
							<a href="del.ht?id=${sysCalendarItem.id}" class="link del">删除</a>
						</c:if>
						<!--<a href="get.ht?id=${sysCalendarItem.id}" class="link detail">明细</a>-->
					</display:column>
				</display:table>
				<hotent:paging tableId="sysCalendarItem"/>
			
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


