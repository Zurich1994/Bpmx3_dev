
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>班次时间管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
			<div class="panel">
			<div class="hide-panel">
				<div class="panel-top">
					<div class="tbar-title">
						<span class="tbar-label">班次时间管理列表</span>
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
				</div>
				</div>
				<div class="panel-body">
					<div class="panel-search">
							<form id="searchForm" method="post" action="list.ht">
									<ul class="row">
												<li><span class="label">设置ID:</span><input type="text" name="Q_settingId_S"  class="inputText" value="${param['Q_settingId_S']}"/></li>
											
												<li><span class="label">开始时间:</span><input type="text" name="Q_startTime_S"  class="inputText" value="${param['Q_startTime_S']}"/></li>
											
												<li><span class="label">结束时间:</span><input type="text" name="Q_endTime_S"  class="inputText" value="${param['Q_endTime_S']}"/></li>
											
												<li><span class="label">备注:</span><input type="text" name="Q_memo_S"  class="inputText" value="${param['Q_memo_S']}"/></li>
											
									</ul>
							</form>
					</div>
					<br/>
					<div class="panel-data">
				    	<c:set var="checkAll">
							<input type="checkbox" id="chkall"/>
						</c:set>
					    <display:table name="workTimeList" id="workTimeItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" export="true"  class="table-grid">
							<display:column title="${checkAll}" media="html" style="width:30px;">
								  	<input type="checkbox" class="pk" name="id" value="${workTimeItem.id}">
							</display:column>
							<display:column property="settingId" title="设置ID" sortable="true" sortName="settingId"></display:column>
							<display:column property="startTime" title="开始时间" sortable="true" sortName="startTime"></display:column>
							<display:column property="endTime" title="结束时间" sortable="true" sortName="endTime"></display:column>
							<display:column property="memo" title="备注" sortable="true" sortName="memo"></display:column>
							<display:column title="管理" media="html" style="width:180px">
								<a href="del.ht?id=${workTimeItem.id}" class="link del">删除</a>
								<a href="edit.ht?id=${workTimeItem.id}" class="link edit">编辑</a>
								<a href="get.ht?id=${workTimeItem.id}" class="link detail">明细</a>
							</display:column>
						</display:table>
						<hotent:paging tableId="workTimeItem"/>
					</div>
				</div><!-- end of panel-body -->				
			</div> <!-- end of panel -->
</body>
</html>


