<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>日程展示</title>
<%@include file="/commons/include/form.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/styles/common/css/fullcalendar/fullcalendar.min.css"></link>
<link href="${ctx}/styles/default/css/jquery.qtip.css" rel="stylesheet" />
<script type="text/javascript" src="${ctx}/js/jquery/plugins/jquery.qtip.js" ></script>
<script type="text/javascript" src="${ctx}/js/fullcalendar/moment.min.js"></script>
<script type="text/javascript" src="${ctx}/js/fullcalendar/fullcalendar.min.js"></script>
<script type="text/javascript" src="${ctx}/js/lang/fullcalendar/zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/js/fullcalendar/FullcalendarControl.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysPlanShowScript.js"></script>

<script type="text/javascript">
	var calendar;
	var calendarObj;
	$(function() {
		var queryDataUrl = __ctx + "/platform/system/sysPlan/listByChargeAndMonth.ht";
		var openPlanUrl = __ctx + "/platform/system/sysPlan/exchange.ht?type=charge";
		var queryParams={
				queryDataUrl:queryDataUrl,
				openPlanUrl:openPlanUrl
			};
		//开始创建控件
		calendarObj = new FullcalendarControl();
		calendar = calendarObj.renderTo("calendar",{queryParams:queryParams});
		
		//初始化跳转事件和有指定的视图日期
		initGoToTheDate();
		
	});
	
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">日程展示</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="l-bar-separator"></div>
					<div class="group">
						日期：<input type="text" name="theDate"  class="inputText date"  value="${currentViweDate}" />						
					</div>
					<div class="l-bar-separator"></div>
					<div class="group">					
						<a class="link ok" id="goToTheDate" href="#" ><span></span>跳转到指定日期</a>
					</div>
				</div>
			</div>
		</div>

		<div class="panel-body">
			<br />

			<div id='calendar'></div>

			<br />
		</div>
	</div>
</body>
</html>


