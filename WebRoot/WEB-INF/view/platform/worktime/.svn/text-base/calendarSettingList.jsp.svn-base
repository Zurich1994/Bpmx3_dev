
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>日历设置管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
			<div class="panel">
			<div class="hide-panel">
				<div class="panel-top">
					<div class="tbar-title">
						<span class="tbar-label">日历设置管理列表</span>
					</div>
					<div class="panel-toolbar">
						<div class="toolBar">
							<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
							<div class="l-bar-separator"></div>
							<div class="group"><a class="link add href="edit.ht"><span></span>添加</a></div>
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
												<li><span class="label">日历ID:</span><input type="text" name="Q_calendarId_S"  class="inputText" value="${param['Q_calendarId_S']}"/></li>
											
												<li><span class="label">年份:</span><input type="text" name="Q_years_SN"  class="inputText" value="${param['Q_years_SN']}"/></li>
											
												<li><span class="label">月份:</span><input type="text" name="Q_months_SN"  class="inputText" value="${param['Q_months_SN']}"/></li>
											
												<li><span class="label">1号:</span><input type="text" name="Q_days_SN"  class="inputText" value="${param['Q_days_SN']}"/></li>
											
												<li><span class="label">上班类型:</span><input type="text" name="Q_type_SN"  class="inputText" value="${param['Q_type_SN']}"/></li>
											
												<li><span class="label">workTimeId:</span><input type="text" name="Q_workTimeId_S"  class="inputText" value="${param['Q_workTimeId_S']}"/></li>
											
									</ul>
							</form>
					</div>
					<br/>
					<div class="panel-data">
				    	<c:set var="checkAll">
							<input type="checkbox" id="chkall"/>
						</c:set>
					    <display:table name="calendarSettingList" id="calendarSettingItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" export="true"  class="table-grid">
							<display:column title="${checkAll}" media="html" style="width:30px;">
								  	<input type="checkbox" class="pk" name="id" value="${calendarSettingItem.id}">
							</display:column>
							<display:column property="calendarId" title="日历ID" sortable="true" sortName="calendarId"></display:column>
							<display:column property="years" title="年份" sortable="true" sortName="years"></display:column>
							<display:column property="months" title="月份" sortable="true" sortName="months"></display:column>
							<display:column property="days" title="1号" sortable="true" sortName="days"></display:column>
							<display:column property="type" title="上班类型" sortable="true" sortName="type"></display:column>
							<display:column property="workTimeId" title="workTimeId" sortable="true" sortName="workTimeId"></display:column>
							<display:column title="管理" media="html" style="width:180px">
								<a href="del.ht?id=${calendarSettingItem.id}" class="link del">删除</a>
								<a href="edit.ht?id=${calendarSettingItem.id}" class="link edit">编辑</a>
								<a href="get.ht?id=${calendarSettingItem.id}" class="link detail">明细</a>
							</display:column>
						</display:table>
						<hotent:paging tableId="calendarSettingItem"/>
					</div>
				</div><!-- end of panel-body -->				
			</div> <!-- end of panel -->
</body>
</html>


