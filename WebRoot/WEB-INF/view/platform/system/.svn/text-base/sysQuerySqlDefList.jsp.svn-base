<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>自定义SQL定义管理</title>
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
				<span class="tbar-label">自定义SQL定义管理列表</span>
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
						<span class="label">名称:</span><input type="text" name="Q_name_SL"  class="inputText" />
						<span class="label">sql语句:</span><input type="text" name="Q_sql_SL"  class="inputText" />
						<span class="label">数据源名称:</span><input type="text" name="Q_dsname_SL"  class="inputText" />
						<span class="label">按钮定义:</span><input type="text" name="Q_buttonDef_SL"  class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="sysQuerySqlDefList" id="sysQuerySqlDefItem" requestURI="list.ht?__FILTERKEY__=${busQueryRule.filterKey}&__FILTER_FLAG__=${busQueryRule.filterFlag}" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<f:col name="id">
					<display:column title="${checkAll}" media="html" style="width:30px;">
				  		<input type="checkbox" class="pk" name="id" value="${sysQuerySqlDefItem.id}">
					</display:column>
				</f:col>
				<f:col name="name">
					<display:column property="name" title="名称" sortable="true" sortName="name"></display:column>
				</f:col>
				<f:col name="alias">
					<display:column property="alias" title="别名" sortable="true" sortName="alias"></display:column>
				</f:col>
				<f:col name="sql">
					<display:column property="sql" title="sql语句" sortable="true" sortName="sql" maxLength="80"></display:column>
				</f:col>
				<f:col name="dsname">
					<display:column property="dsname" title="数据源名称" sortable="true" sortName="dsName"></display:column>
				</f:col>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${sysQuerySqlDefItem.id}" class="link del">删除</a>
					<a href="edit.ht?id=${sysQuerySqlDefItem.id}" class="link edit">编辑</a>
					<a href="get.ht?id=${sysQuerySqlDefItem.id}" class="link detail">明细</a>
					<a href="${ctx}/platform/system/sysQueryView/list.ht?sqlId=${sysQuerySqlDefItem.id}&Q_sqlAlias_S=${sysQuerySqlDefItem.alias}" class="link detail">视图列表</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="sysQuerySqlDefItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


