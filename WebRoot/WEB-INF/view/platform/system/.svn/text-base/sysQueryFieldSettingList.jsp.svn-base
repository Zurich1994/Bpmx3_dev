<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>视图自定义设定管理</title>
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
				<span class="tbar-label">视图自定义设定管理列表</span>
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
						<span class="label">视图ID:</span><input type="text" name="Q_viewId_SL"  class="inputText" />
						<span class="label">字段ID:</span><input type="text" name="Q_fieldId_SL"  class="inputText" />
						<span class="label">允许排序:</span><input type="text" name="Q_sortAble_SN"  class="inputText" />
						<span class="label">默认排序:</span><input type="text" name="Q_defaultSort_SN"  class="inputText" />
						<span class="label">默认排序方向:</span><input type="text" name="Q_sortSeq_SL"  class="inputText" />
						<span class="label">对齐方式:</span><input type="text" name="Q_align_SL"  class="inputText" />
						<span class="label">是否冻结:</span><input type="text" name="Q_frozen_SN"  class="inputText" />
						<span class="label">列连接地址:</span><input type="text" name="Q_url_SL"  class="inputText" />
						<span class="label">计算类型:</span><input type="text" name="Q_summaryType_SL"  class="inputText" />
						<span class="label">计算模版:</span><input type="text" name="Q_summaryTemplate_SL"  class="inputText" />
						<span class="label">报警设定:</span><input type="text" name="Q_alertSetting_SL"  class="inputText" />
						<span class="label">自定义格式函数:</span><input type="text" name="Q_formatter_SL"  class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="sysQueryFieldSettingList" id="sysQueryFieldSettingItem" requestURI="list.ht?__FILTERKEY__=${busQueryRule.filterKey}&__FILTER_FLAG__=${busQueryRule.filterFlag}" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<f:col name="id">
					<display:column title="${checkAll}" media="html" style="width:30px;">
				  		<input type="checkbox" class="pk" name="id" value="${sysQueryFieldSettingItem.id}">
					</display:column>
				</f:col>
				<f:col name="viewId">
					<display:column property="viewId" title="视图ID" sortable="true" sortName="VIEW_ID"></display:column>
				</f:col>
				<f:col name="fieldId">
					<display:column property="fieldId" title="字段ID" sortable="true" sortName="FIELD_ID"></display:column>
				</f:col>
				<f:col name="sortAble">
					<display:column property="sortAble" title="允许排序" sortable="true" sortName="SORT_ABLE"></display:column>
				</f:col>
				<f:col name="defaultSort">
					<display:column property="defaultSort" title="默认排序" sortable="true" sortName="DEFAULT_SORT"></display:column>
				</f:col>
				<f:col name="sortSeq">
					<display:column property="sortSeq" title="默认排序方向" sortable="true" sortName="SORT_SEQ"></display:column>
				</f:col>
				<f:col name="align">
					<display:column property="align" title="对齐方式" sortable="true" sortName="ALIGN"></display:column>
				</f:col>
				<f:col name="frozen">
					<display:column property="frozen" title="是否冻结" sortable="true" sortName="FROZEN"></display:column>
				</f:col>
				<f:col name="url">
					<display:column property="url" title="列连接地址" sortable="true" sortName="URL" maxLength="80"></display:column>
				</f:col>
				<f:col name="summaryType">
					<display:column property="summaryType" title="计算类型" sortable="true" sortName="SUMMARY_TYPE"></display:column>
				</f:col>
				<f:col name="summaryTemplate">
					<display:column property="summaryTemplate" title="计算模版" sortable="true" sortName="SUMMARY_TEMPLATE" maxLength="80"></display:column>
				</f:col>
				<f:col name="alertSetting">
					<display:column property="alertSetting" title="报警设定" sortable="true" sortName="ALERT_SETTING" maxLength="80"></display:column>
				</f:col>
				<f:col name="formatter">
					<display:column property="formatter" title="自定义格式函数" sortable="true" sortName="FORMATTER" maxLength="80"></display:column>
				</f:col>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${sysQueryFieldSettingItem.id}" class="link del">删除</a>
					<a href="edit.ht?id=${sysQueryFieldSettingItem.id}" class="link edit">编辑</a>
					<a href="get.ht?id=${sysQueryFieldSettingItem.id}" class="link detail">明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="sysQueryFieldSettingItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


