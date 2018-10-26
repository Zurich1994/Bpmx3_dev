<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>工作日历管理</title>
<%@include file="/commons/include/get.jsp" %>
<f:link href="listEdit.css"></f:link>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">工作日历管理列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link add" href="edit.ht"><span></span>添加</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link update" id="btnUpd" action="edit.ht"><span></span>修改</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link back" href="${ctx}/platform/ats/atsBaseItem/list.ht"><span></span>返回</a></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<ul class="row">
						<li>
							<span class="label">编码:</span><input type="text" name="Q_code_SL"  class="inputText" value="${param['Q_code_SL']}"/>
						</li>
						<li>
							<span class="label">名称:</span><input type="text" name="Q_name_SL"  class="inputText" value="${param['Q_name_SL']}"/>
						</li>
						<li>
							<span class="label">是否默认:</span>
							<select name="Q_isDefault_S" value="${param['Q_isDefault_S']}"  class="inputText">
								<option value="">请选择</option>
								<option value="1" <c:if test="${param['Q_isDefault_S'] == '1'}">selected</c:if>>是</option>	
								<option value="0" <c:if test="${param['Q_isDefault_S'] == '0'}">selected</c:if>>否</option>
							</select>
						</li>
					</ul>
					<ul class="row">
						<li>
							<span class="label">开始时间 从:</span> <input  name="Q_beginstartTime_DL"  class="inputText date" value="${param['Q_beginstartTime_DL']}"/>
						</li>
						<li>
							<span class="label">至: </span><input  name="Q_endstartTime_DG" class="inputText date" value="${param['Q_endstartTime_DG']}" />
						</li>
					</ul>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="atsWorkCalendarList" id="atsWorkCalendarItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${atsWorkCalendarItem.id}">
				</display:column>
				<display:column property="code" title="编码" sortable="true" sortName="CODE" maxLength="80"></display:column>
				<display:column property="name" title="名称" sortable="true" sortName="NAME" maxLength="80"></display:column>
				<display:column  title="开始时间" sortable="true" sortName="START_TIME">
					<fmt:formatDate value="${atsWorkCalendarItem.startTime}" pattern="yyyy-MM-dd"/>
				</display:column>
				<display:column  title="结束时间" sortable="true" sortName="END_TIME">
					<fmt:formatDate value="${atsWorkCalendarItem.endTime}" pattern="yyyy-MM-dd"/>
				</display:column>
				<display:column property="calendarTemplName" title="日历模版" sortable="true" sortName="CALENDAR_TEMPL"></display:column>
				<display:column property="legalHolidayName" title="法定假日" sortable="true" sortName="LEGAL_HOLIDAY"></display:column>
				<display:column title="是否默认" sortable="true" sortName="IS_DEFAULT">
						<c:choose>
							<c:when test="${atsWorkCalendarItem.isDefault==1}">
								<span class="red">是</span>
							</c:when>
							<c:otherwise>
								<span class="green">否</span>
							</c:otherwise>
						</c:choose>
				</display:column>
				<display:column title="管理" media="html" class="rowOps">
					<a href="del.ht?id=${atsWorkCalendarItem.id}" class="link del">删除</a>
					<a href="edit.ht?id=${atsWorkCalendarItem.id}" class="link edit">编辑</a>
					<a href="get.ht?id=${atsWorkCalendarItem.id}" class="link detail">明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="atsWorkCalendarItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


