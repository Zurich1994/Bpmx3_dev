<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>假期制度明细管理</title>
<%@include file="/commons/include/get.jsp" %>
<script type="text/javascript" src="${ctx}/js/hotent/platform/bus/BusQueryRuleUtil.js" ></script>
</head>
<body>
	<div class="panel">
		<c:if test="${!empty busQueryRule.filterList && fn:length(busQueryRule.filterList) >1}">
			<div class="l-tab-links">
				<ul style="left: 0px; ">
					<c:forEach items="${busQueryRule.filterList}" var="filter">
						<li tabid="${filter.key}" <c:if test="${busQueryRule.filterKey ==filter.key}"> class="l-selected"</c:if>>
							<a href="list.ht?__FILTERKEY__=${filter.key}" title="${filter.name}">${filter.desc}</a>
						</li>
					</c:forEach>
				</ul>
			</div>
		</c:if>
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">假期制度明细管理列表</span>
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
					<div class="group" style="float: right;">
						<f:a onclick="BusQueryRuleUtil.saveFilter({tableName:'${busQueryRule.tableName}',filterKey:'${busQueryRule.filterKey}',filterFlag:'${busQueryRule.filterFlag}'})" alias="saveFilter_${busQueryRule.tableName}" css="link save"  showNoRight="false"><span></span>保存条件</f:a>
						<f:a onclick="BusQueryRuleUtil.myFilter({tableName:'${busQueryRule.tableName}',url:'${busQueryRule.url}'})" alias="myFilter_${busQueryRule.tableName}" css="link ok"  showNoRight="false"><span></span>过滤器</f:a>
						<f:a onclick="BusQueryRuleUtil.eidtDialog({tableName:'${busQueryRule.tableName}'})" alias="customQuery_${busQueryRule.tableName}" css="link setting" showNoRight="false" ><span></span>高级查询</f:a>
					</div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht?__FILTERKEY__=${busQueryRule.filterKey}&__IS_QUERY__=0">
					<div class="row">
						<span class="label">假期制度ID:</span><input type="text" name="Q_holidayId_SL"  class="inputText" />
						<span class="label">假期类型:</span><input type="text" name="Q_holidayType_SL"  class="inputText" />
						<span class="label">假期单位:</span><input type="text" name="Q_holidayUnit_SN"  class="inputText" />
						<span class="label">启动周期:</span><input type="text" name="Q_enablePeriod_SN"  class="inputText" />
						<span class="label">周期长度:</span><input type="text" name="Q_periodLength_SL"  class="inputText" />
						<span class="label">周期单位:</span><input type="text" name="Q_periodUnit_SN"  class="inputText" />
						<span class="label">控制单位额度:</span><input type="text" name="Q_enableMinAmt_SN"  class="inputText" />
						<span class="label">单位额度:</span><input type="text" name="Q_minAmt_SL"  class="inputText" />
						<span class="label">是否允许补请假:</span><input type="text" name="Q_isFillHoliday_SN"  class="inputText" />
						<span class="label">补请假期限:</span><input type="text" name="Q_fillHoliday_SL"  class="inputText" />
						<span class="label">补请假期限单位:</span><input type="text" name="Q_fillHolidayUnit_SN"  class="inputText" />
						<span class="label">是否允许销假:</span><input type="text" name="Q_isCancelLeave_SN"  class="inputText" />
						<span class="label">销假期限:</span><input type="text" name="Q_cancelLeave_SL"  class="inputText" />
						<span class="label">销假期限单位:</span><input type="text" name="Q_cancelLeaveUnit_SN"  class="inputText" />
						<span class="label">是否控制假期额度:</span><input type="text" name="Q_isCtrlLimit_SN"  class="inputText" />
						<span class="label">假期额度规则:</span><input type="text" name="Q_holidayRule_SL"  class="inputText" />
						<span class="label">是否允许超额请假:</span><input type="text" name="Q_isOver_SN"  class="inputText" />
						<span class="label">超出额度下期扣减:</span><input type="text" name="Q_isOverAutoSub_SN"  class="inputText" />
						<span class="label">是否允许修改额度:</span><input type="text" name="Q_isCanModifyLimit_SN"  class="inputText" />
						<span class="label">包括公休日:</span><input type="text" name="Q_isIncludeRest_SN"  class="inputText" />
						<span class="label">包括法定假日:</span><input type="text" name="Q_isIncludeLegal_SN"  class="inputText" />
						<span class="label">描述:</span><input type="text" name="Q_memo_SL"  class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="atsHolidayPolicyDetailList" id="atsHolidayPolicyDetailItem" requestURI="list.ht?__FILTERKEY__=${busQueryRule.filterKey}&__FILTER_FLAG__=${busQueryRule.filterFlag}" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<f:col name="id">
					<display:column title="${checkAll}" media="html" style="width:30px;">
				  		<input type="checkbox" class="pk" name="id" value="${atsHolidayPolicyDetailItem.id}">
					</display:column>
				</f:col>
				<f:col name="holidayId">
					<display:column property="holidayId" title="假期制度ID" sortable="true" sortName="HOLIDAY_ID"></display:column>
				</f:col>
				<f:col name="holidayType">
					<display:column property="holidayType" title="假期类型" sortable="true" sortName="HOLIDAY_TYPE"></display:column>
				</f:col>
				<f:col name="holidayUnit">
					<display:column property="holidayUnit" title="假期单位" sortable="true" sortName="HOLIDAY_UNIT"></display:column>
				</f:col>
				<f:col name="enablePeriod">
					<display:column property="enablePeriod" title="启动周期" sortable="true" sortName="ENABLE_PERIOD"></display:column>
				</f:col>
				<f:col name="periodLength">
					<display:column property="periodLength" title="周期长度" sortable="true" sortName="PERIOD_LENGTH"></display:column>
				</f:col>
				<f:col name="periodUnit">
					<display:column property="periodUnit" title="周期单位" sortable="true" sortName="PERIOD_UNIT"></display:column>
				</f:col>
				<f:col name="enableMinAmt">
					<display:column property="enableMinAmt" title="控制单位额度" sortable="true" sortName="ENABLE_MIN_AMT"></display:column>
				</f:col>
				<f:col name="minAmt">
					<display:column property="minAmt" title="单位额度" sortable="true" sortName="MIN_AMT"></display:column>
				</f:col>
				<f:col name="isFillHoliday">
					<display:column property="isFillHoliday" title="是否允许补请假" sortable="true" sortName="IS_FILL_HOLIDAY"></display:column>
				</f:col>
				<f:col name="fillHoliday">
					<display:column property="fillHoliday" title="补请假期限" sortable="true" sortName="FILL_HOLIDAY"></display:column>
				</f:col>
				<f:col name="fillHolidayUnit">
					<display:column property="fillHolidayUnit" title="补请假期限单位" sortable="true" sortName="FILL_HOLIDAY_UNIT"></display:column>
				</f:col>
				<f:col name="isCancelLeave">
					<display:column property="isCancelLeave" title="是否允许销假" sortable="true" sortName="IS_CANCEL_LEAVE"></display:column>
				</f:col>
				<f:col name="cancelLeave">
					<display:column property="cancelLeave" title="销假期限" sortable="true" sortName="CANCEL_LEAVE"></display:column>
				</f:col>
				<f:col name="cancelLeaveUnit">
					<display:column property="cancelLeaveUnit" title="销假期限单位" sortable="true" sortName="CANCEL_LEAVE_UNIT"></display:column>
				</f:col>
				<f:col name="isCtrlLimit">
					<display:column property="isCtrlLimit" title="是否控制假期额度" sortable="true" sortName="IS_CTRL_LIMIT"></display:column>
				</f:col>
				<f:col name="holidayRule">
					<display:column property="holidayRule" title="假期额度规则" sortable="true" sortName="HOLIDAY_RULE"></display:column>
				</f:col>
				<f:col name="isOver">
					<display:column property="isOver" title="是否允许超额请假" sortable="true" sortName="IS_OVER"></display:column>
				</f:col>
				<f:col name="isOverAutoSub">
					<display:column property="isOverAutoSub" title="超出额度下期扣减" sortable="true" sortName="IS_OVER_AUTO_SUB"></display:column>
				</f:col>
				<f:col name="isCanModifyLimit">
					<display:column property="isCanModifyLimit" title="是否允许修改额度" sortable="true" sortName="IS_CAN_MODIFY_LIMIT"></display:column>
				</f:col>
				<f:col name="isIncludeRest">
					<display:column property="isIncludeRest" title="包括公休日" sortable="true" sortName="IS_INCLUDE_REST"></display:column>
				</f:col>
				<f:col name="isIncludeLegal">
					<display:column property="isIncludeLegal" title="包括法定假日" sortable="true" sortName="IS_INCLUDE_LEGAL"></display:column>
				</f:col>
				<f:col name="memo">
					<display:column property="memo" title="描述" sortable="true" sortName="MEMO" maxLength="80"></display:column>
				</f:col>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${atsHolidayPolicyDetailItem.id}" class="link del">删除</a>
					<a href="edit.ht?id=${atsHolidayPolicyDetailItem.id}" class="link edit">编辑</a>
					<a href="get.ht?id=${atsHolidayPolicyDetailItem.id}" class="link detail">明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="atsHolidayPolicyDetailItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


