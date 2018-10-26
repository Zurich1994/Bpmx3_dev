<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>Excel模板管理</title>
<%@include file="/commons/include/get.jsp" %>
<script type="text/javascript" src="${ctx}/js/hotent/platform/bus/BusQueryRuleUtil.js" ></script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">Excel模板管理列表</span>
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
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<div class="row">
						<span class="label">模板代码:</span><input type="text" name="Q_tempCode_SL"  class="inputText" />
						<span class="label">模板名称:</span><input type="text" name="Q_tempName_SL"  class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="sysExcelTempList" id="sysExcelTempItem" requestURI="list.ht?__FILTERKEY__=${busQueryRule.filterKey}&__FILTER_FLAG__=${busQueryRule.filterFlag}" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<f:col name="id">
					<display:column title="${checkAll}" media="html" style="width:30px;">
				  		<input type="checkbox" class="pk" name="id" value="${sysExcelTempItem.id}">
					</display:column>
				</f:col>
				<f:col name="tempCode">
					<display:column property="tempCode" title="模板代码" sortable="true" sortName="TEMP_CODE"></display:column>
				</f:col>
				<f:col name="tempName">
					<display:column property="tempName" title="模板名称" sortable="true" sortName="TEMP_NAME"></display:column>
				</f:col>
				<f:col name="tempDes">
					<display:column property="tempDes" title="模板填写说明" sortable="true" sortName="TEMP_DES" maxLength="80"></display:column>
				</f:col>
				<f:col name="tempDesHeight">
					<display:column property="tempDesHeight" title="填写说明行高" sortable="true" sortName="TEMP_DES_HEIGHT"></display:column>
				</f:col>
				<f:col name="memo">
					<display:column property="memo" title="备注" sortable="true" sortName="MEMO" maxLength="80"></display:column>
				</f:col>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${sysExcelTempItem.id}" class="link del">删除</a>
					<a href="edit.ht?id=${sysExcelTempItem.id}" class="link edit">编辑</a>
					<a href="get.ht?id=${sysExcelTempItem.id}" class="link detail">明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="sysExcelTempItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


