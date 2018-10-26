
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>选择工作日历</title>
<%@include file="/commons/include/get.jsp" %>
<script type="text/javascript">
	$(function(){
		$("tr.odd,tr.even").unbind("hover");
		$("tr.odd,tr.even").click(function(){
			$(this).siblings().removeClass("over").end().addClass("over");
		});
	}); 
</script>
<style type="text/css">
	div.bottom{text-align: center;padding-top: 10px;}
	body {overflow: hidden;}
</style>
</head>
<body>

	<div  class="panel-top">
		<div class="panel-search">
			<form id="searchForm" method="post" action="${ctx}/platform/ats/atsWorkCalendar/selector.ht" >
				<ul class="row">
					<input type="hidden" name="isSingle" value="${isSingle }">
					<li><span class="label" >名称:</span><input size="14" type="text" name="Q_name_SL"  class="inputText" style="width:60%;" value="${param['Q_fullname_SL']}"/>
					&nbsp;<a href="javascript:;" onclick="$('#searchForm').submit()" class='button'><span>查询</span></a></li>
				</ul>
			</form>
		</div>
	</div>
	    <display:table name="atsWorkCalendarList" id="atsWorkCalendarItem" requestURI="selector.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
		<display:column title="选择" media="html" style="width:30px;">
			<input type="radio" class="pk" name="data" value="${atsWorkCalendarItem.id}#${atsWorkCalendarItem.name}">
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
	</display:table>
	<hotent:paging tableId="atsWorkCalendarItem" showExplain="false"/>
</body>
</html>


