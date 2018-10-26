<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>排班向导</title>
	<%@include file="/commons/include/form.jsp" %>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/common/css/font-awesome/font-awesome.min.css"></link>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/common/css/bootstrap/bootstrap.min.css"></link>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/common/css/wizard/fuelux.wizard.css"></link>
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
	<script type="text/javascript" src="${ctx}/js/bootstrap/fuelux.wizard.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jqGrid/jquery.jqGrid.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jqGrid/i18n/grid.locale-cn.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/ats/AtsDialog.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/ats/jquery.wizard.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/ats/AtsTurnShiftWizard.js"></script>
	<style type="text/css">
		.user-list{
			width: 100%; 
			height: 600px; 
			overflow:scroll;
			overflow-y:scroll;
			overflow-x:hidden;
			border: 1px solid #aaaaaa;
		}
		.text-tag {
			width:100%;
			text-overflow: ellipsis;
			white-space: nowrap;
			overflow: hidden;
			text-align: left;
			cursor: pointer;
		}
		.text-tag {
			display: inline-block;
			position: relative;
			background: #D9EDF7;
			padding: 0px 20px 0px 10px;
			margin-right: 5px;
			vertical-align: text-bottom;
			border-bottom:2px solid #FFFFFF;
			top: 2px;
		}
		.text-label {
			color: #666;
		}
		.text-remove {
			position: absolute;
			top: 3px;
			right: 3px;
			color: #8786B7;
			text-align: center;
			cursor: pointer;
			text-shadow: 0 1px 1px white;
			line-height: 16px;
			width: 12px;
		}
		.calendar-info{
			width: 100%;
			 height: 600px;
			 border: 1px solid #aaaaaa;
		}
	</style>
</head>
<body>
	<div id="fuelux-wizard" data-target="#step-container">
		<!-- #section:plugins/fuelux.wizard.steps -->
		<ul class="wizard-steps">
			<li data-target="#step1" class="active">
				<span class="step">1</span>
				<span class="title">选择排班方式</span>
			</li>

			<li data-target="#step2">
				<span class="step">2</span>
				<span class="title">设置时间</span>
			</li>

			<li data-target="#step3">
				<span class="step">3</span>
				<span class="title">选择人员</span>
			</li>

			<li data-target="#step4">
				<span class="step">4</span>
				<span class="title">排班设置</span>
			</li>
			<li data-target="#step5">
				<span class="step">5</span>
				<span class="title">完成</span>
			</li>
		</ul>
	</div>
	<div class="wizard-actions">
		<button class="btn btn-prev">
			<i class="ht-icon fa fa-arrow-left"></i>
			上一步
		</button>
		<button class="btn btn-success btn-next" data-last="完　成">
			下一步
			<i class="ht-icon fa fa-arrow-right icon-on-right"></i>
		</button>
	</div>
	<hr />
	<div class="step-content pos-rel" id="step-container">
	
		<div class="step-pane active" id="step1">
			<div style="width:auto;text-align:left;margin-left:37.5%;">
				<div>
					<label><input type="radio" id="shiftType1" name="shiftType" value="1" checked="checked"> &nbsp;&nbsp;日历式排班 </label>
					<span class="help-block" var="shiftType1">适合的排班场景为所选员工使用相同轮班或排班规则。</span>
				</div>
				<div>
					<label><input type="radio" id="shiftType2" name="shiftType" value="2" > &nbsp;&nbsp;列表式排班 </label>
					<span class="help-block" var="shiftType2"> 
						<ul>
							<li>适用的排班场景为所选员工使用相同或不同的轮班或排班规则。 </li>
							<li>可支持所选人员使用相同的轮班或排班规则。</li>
							<li>可支持不同人员使用相同的轮班规则但不同的起始点。 </li>
							<li>可支持不同人员使用不同的轮班或排班规则。</li>
						</ul>
					</span>
				</div> 
			</div>
		
		</div>
		<!-- 第2步 -->
		<div class="step-pane" id="step2">
			<div style="width:auto;text-align:left;margin-left:30%;">
			    <label>开始时间:</label>
			    <input type="text" class="inputText datePicker" dateType="1" id="startTime"  value="${startTime }"> &nbsp;&nbsp;&nbsp;&nbsp;
			    <label>结束时间:</label>
			    <input type="text" class="inputText datePicker" dateType="2"  id="endTime"  value="${endTime }">
			 </div>
		</div>
		<!-- 第2步 -->
		<div class="step-pane" id="step3">
			<div class="panel-query" style="text-align:center;">
				<span>考勤制度:</span>
					<input type="hidden" id="attencePolicy"  value="${atsAttencePolicy.id }"/>
					<input type="text" id="attencePolicyName"  class="inputText" value="${atsAttencePolicy.name }"/>
					<a href="javascript:;" onclick="selectAttencePolicy()" class="button"><span>选 择</span></a>
				<span>考勤组:</span>
					<input type="hidden" id="attenceGroup" />
					<input type="text" id="attenceGroupName"  class="inputText" />
					<a href="javascript:;" onclick="selectAttenceGroup()" class="button"><span>选 择</span></a>
				<span>用户:</span>
					<input type="hidden" id="userId" />
					<input type="text" id="userName"  class="inputText" />
					<a href="javascript:;" onclick="selectUser()" class="button"><span>选 择</span></a>
				<a href="javascript:void(0);" id="btn_query" class='btn btn-primary'><span>查询</span></a>
			</div>
			<hr />
			<table id="userGrid" ></table>
			<div id="gridPager"></div>
			
		</div>
		<!-- 第4步 -->
		<div class="step-pane" id="step4">
			<div class="col-md-12">
				<a href="javascript:addShiftRule();"  class='btn btn-sm btn-primary fa fa-plus'><span>新增轮班规则</span></a>
			</div>
			<!-- 日历式排班 -->
			<div id="scheduleCalendar">
				<div class="col-md-3">
				  	<div  id="userlist" class="user-list"> </div>
				</div>
				 <div class="col-md-9">
				  	<div class="calendar-info">
			           <div id="calendar_info" ></div>
				 	</div>
			  	</div>
			</div>
			<!-- 列表式排班 -->
			<div  id="scheduleList" class="col-md-12">
				<table  id="list_info" > </table>
			</div>

		 </div>
		<!-- 第5步 -->
		<div class="step-pane" id="step5">
			<table id="scheduleShiftGrid" ></table>
			<div id="scheduleShiftGridPager"></div>
			
		</div>
		  	
	</div>
		  
</body>
</html>