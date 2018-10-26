
<%--
	time:2015-06-03 14:46:19
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>考勤计算日历明细</title>
<%@include file="/commons/include/get.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/styles/common/css/font-awesome/font-awesome.min.css"></link>
<f:link href="jqGrid/jquery-ui.css" ></f:link>
<f:link href="jqGrid/ui.jqgrid.css" ></f:link>
<link rel="stylesheet" type="text/css" href="${ctx}/styles/common/css/fullcalendar/fullcalendar.min.css"></link>
<!--[if lte IE 8]>
	<script type="text/javascript" src="${ctx}/js/bootstrap/html5shiv.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/bootstrap/respond.min.js"></script>
<![endif]-->
<script type="text/javascript" src="${ctx}/js/fullcalendar/moment.min.js"></script>
<script type="text/javascript" src="${ctx}/js/fullcalendar/fullcalendar.min.js"></script>
<script type="text/javascript" src="${ctx}/js/lang/fullcalendar/zh_CN.js"></script>
<script type="text/javascript">
	//放置脚本
	
	$(function(){
		var event = '${events}',events =$.parseJSON(event),date = formatDate('${startTime}');
		$('#calendar_info').fullCalendar({
		    header: {
				left: 'title',
				right: 'prev,next'
			},
			defaultDate:date,
			height: 600,
			editable : false,
			aspectRatio : 1.35,
			disableDragging : true,
			selectable: true,
			selectHelper: true,
			select: function(start, end) {
				var startDate = start.format('YYYY-MM-DD'),
					date1 = me.formatDate(startDate);
				var events  =  $('#calendar_info').fullCalendar('clientEvents');
				for (var j = 0; j < events.length; j++) {
					var event = events[j],
    					date = formatDate(event["start"].format('YYYY-MM-DD'));
					if (date.getTime() == date1.getTime()) {
						
					}
				}
			},
			eventClick : function(event, e) {
				var start = event.start.format('YYYY-MM-DD'),
					date1 = formatDate(start);
			},
			events:events
	    });
	});
	function formatDate(d){
		return new Date(d.replace(/-/g,"/"));
	}
	
</script>
</head>
<body>
	<div class="panel">
		
		  <div id="calendar_info" ></div>
	</div>
</body>
</html>

