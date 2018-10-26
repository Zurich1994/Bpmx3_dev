<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>自定义SQL字段定义管理</title>
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
				<span class="tbar-label">自定义SQL字段定义管理列表</span>
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
						<span class="label">SQLID:</span><input type="text" name="Q_sqlId_SL"  class="inputText" />
						<span class="label">字段名:</span><input type="text" name="Q_name_SL"  class="inputText" />
						<span class="label">实际字段名:</span><input type="text" name="Q_fieldName_SL"  class="inputText" />
						<span class="label">字段备注:</span><input type="text" name="Q_fieldDesc_SL"  class="inputText" />
						<span class="label">是否可见:</span><input type="text" name="Q_isShow_SN"  class="inputText" />
						<span class="label">是否搜索:</span><input type="text" name="Q_isSearch_SN"  class="inputText" />
						<span class="label">控件类型:</span><input type="text" name="Q_controlType_SN"  class="inputText" />
						<span class="label">数据类型:</span><input type="text" name="Q_dataType_SL"  class="inputText" />
						<span class="label">是否衍生列:</span><input type="text" name="Q_isVirtual_SN"  class="inputText" />
						<span class="label">衍生列来自列:</span><input type="text" name="Q_virtualFrom_SL"  class="inputText" />
						<span class="label">来自类型:</span><input type="text" name="Q_resultFromType_SL"  class="inputText" />
						<span class="label">衍生列配置:</span><input type="text" name="Q_resultFrom_SL"  class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="sysQueryMetaFieldList" id="sysQueryMetaFieldItem" requestURI="list.ht?__FILTERKEY__=${busQueryRule.filterKey}&__FILTER_FLAG__=${busQueryRule.filterFlag}" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<f:col name="id">
					<display:column title="${checkAll}" media="html" style="width:30px;">
				  		<input type="checkbox" class="pk" name="id" value="${sysQueryMetaFieldItem.id}">
					</display:column>
				</f:col>
				<f:col name="sqlId">
					<display:column property="sqlId" title="SQLID" sortable="true" sortName="SQL_ID"></display:column>
				</f:col>
				<f:col name="name">
					<display:column property="name" title="字段名" sortable="true" sortName="NAME"></display:column>
				</f:col>
				<f:col name="fieldName">
					<display:column property="fieldName" title="实际字段名" sortable="true" sortName="FIELD_NAME"></display:column>
				</f:col>
				<f:col name="fieldDesc">
					<display:column property="fieldDesc" title="字段备注" sortable="true" sortName="FIELD_DESC" maxLength="80"></display:column>
				</f:col>
				<f:col name="isShow">
					<display:column property="isShow" title="是否可见" sortable="true" sortName="IS_SHOW"></display:column>
				</f:col>
				<f:col name="isSearch">
					<display:column property="isSearch" title="是否搜索" sortable="true" sortName="IS_SEARCH"></display:column>
				</f:col>
				<f:col name="controlType">
					<display:column property="controlType" title="控件类型" sortable="true" sortName="CONTROL_TYPE"></display:column>
				</f:col>
				<f:col name="dataType">
					<display:column property="dataType" title="数据类型" sortable="true" sortName="DATA_TYPE"></display:column>
				</f:col>
				<f:col name="isVirtual">
					<display:column property="isVirtual" title="是否衍生列" sortable="true" sortName="IS_VIRTUAL"></display:column>
				</f:col>
				<f:col name="virtualFrom">
					<display:column property="virtualFrom" title="衍生列来自列" sortable="true" sortName="VIRTUAL_FROM"></display:column>
				</f:col>
				<f:col name="resultFromType">
					<display:column property="resultFromType" title="来自类型" sortable="true" sortName="RESULT_FROM_TYPE"></display:column>
				</f:col>
				<f:col name="resultFrom">
					<display:column property="resultFrom" title="衍生列配置" sortable="true" sortName="RESULT_FROM" maxLength="80"></display:column>
				</f:col>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${sysQueryMetaFieldItem.id}" class="link del">删除</a>
					<a href="edit.ht?id=${sysQueryMetaFieldItem.id}" class="link edit">编辑</a>
					<a href="get.ht?id=${sysQueryMetaFieldItem.id}" class="link detail">明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="sysQueryMetaFieldItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


