<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>事件类型表管理</title>
<%@include file="/commons/include/get.jsp" %>
<script type="text/javascript">
		var eventId="";
		var eventName="";
		
		$(function(){
			$("#layoutMain").ligerLayout({ rightWidth: 350, height: '100%'});
		});
		
		function selectTable(eventId,EventName)
		{
			eventId=eventId;
			EventName=EventName;
			window.returnValue= {eventId:eventId,EventName:EventName};	
			window.close();
		}

		
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">事件类型表管理列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="listSelect.ht">
					<div class="row">
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="eventTypeList" id="eventTypeItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${eventTypeItem.id}">
				</display:column>
				<display:column property="eventName" title="事件名称" sortable="false" sortName="F_EVENTNAME"></display:column>
				<display:column property="spare1" title="备用字段1" sortable="false" sortName="F_SPARE1"></display:column>
				<display:column property="spare2" title="备用字段2" sortable="false" sortName="F_SPARE2"></display:column>
				<display:column property="spare3" title="备用字段3" sortable="false" sortName="F_SPARE3"></display:column>
				<display:column property="spare4" title="备用字段4" sortable="false" sortName="F_SPARE4"></display:column>
				<display:column property="spare5" title="备用字段5" sortable="false" sortName="F_SPARE5"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href='#' class='button'  onclick="selectTable('${eventTypeItem.id}','${eventTypeItem.eventName}')" ><span class="icon ok"></span><span>选择</span></a>
				</display:column>
			</display:table>
			<hotent:paging tableId="eventTypeItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


