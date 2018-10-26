<#import "function.ftl" as func>
<#assign comment=model.tabComment>
<#assign class=model.variables.class>
<#assign package=model.variables.package>
<#assign comment=model.tabComment>
<#assign classVar=model.variables.classVar>
<#assign system=vars.system>
<#assign commonList=model.commonList>
<#assign pkModel=model.pkModel>
<#assign pk=func.getPk(model) >
<#assign pkVar=func.convertUnderLine(pk) >
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>${comment }管理</title>
<%@include file="/commons/include/get.jsp" %>
<#noparse><script type="text/javascript" src="${ctx}/js/hotent/platform/bus/BusQueryRuleUtil.js" ></script></#noparse>
<#if model.variables.flowKey?exists>
<script type="text/javascript">
	function startFlow(id){
		$.post("run.ht?isList=1&${pkVar}="+id,function(responseText){
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {
				$.ligerDialog.success("启动流程成功！", "成功", function(rtn) {
					if(rtn){
						this.close();
					}
					window.location.href = "<#noparse>${ctx}</#noparse>/${system}/${package}/${classVar}/list.ht";
				});
			} else {
				$.ligerDialog.error(obj.getMessage(),"提示信息");
			}
		});
	}
</script>
</#if>
</head>
<body>
	<div class="panel">
		<#noparse>
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
		</#noparse>
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">${comment }管理列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link add" href="edit.ht"><span></span>添加</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link update" id="btnUpd" action="edit.ht"><span></span>修改</a></div>
					<#if !model.variables.flowKey?exists>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link del"  action="del.ht"><span></span>删除</a></div>
					</#if>
					<#noparse>
					<div class="group" style="float: right;">
						<f:a onclick="BusQueryRuleUtil.saveFilter({tableName:'${busQueryRule.tableName}',filterKey:'${busQueryRule.filterKey}',filterFlag:'${busQueryRule.filterFlag}'})" alias="saveFilter_${busQueryRule.tableName}" css="link save"  showNoRight="false"><span></span>保存条件</f:a>
						<f:a onclick="BusQueryRuleUtil.myFilter({tableName:'${busQueryRule.tableName}',url:'${busQueryRule.url}'})" alias="myFilter_${busQueryRule.tableName}" css="link ok"  showNoRight="false"><span></span>过滤器</f:a>
						<f:a onclick="BusQueryRuleUtil.eidtDialog({tableName:'${busQueryRule.tableName}'})" alias="customQuery_${busQueryRule.tableName}" css="link setting" showNoRight="false" ><span></span>高级查询</f:a>
					</div>
					</#noparse>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht?__FILTERKEY__=<#noparse>${busQueryRule.filterKey}</#noparse>&__IS_QUERY__=0">
					<div class="row">
						<#list commonList as col>
						<#assign colName=func.convertUnderLine(col.columnName)>
						<#if (col.colType=="java.util.Date")>
						<span class="label">${col.comment} 从:</span> <input  name="Q_begin${colName}_${func.getDataType("Date","1")}"  class="inputText date" />
						<span class="label">至: </span><input  name="Q_end${colName}_${func.getDataType("Date","0")}" class="inputText date" />
						<#else>
						<span class="label">${col.comment}:</span><input type="text" name="Q_${colName}_${func.getDataType("${col.colType}","0")}"  class="inputText" />
						</#if>
						</#list>
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="${classVar}List" id="${classVar}Item" requestURI="list.ht?__FILTERKEY__=<#noparse>${busQueryRule.filterKey}&__FILTER_FLAG__=${busQueryRule.filterFlag}</#noparse>" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<f:col name="${pkVar}">
					<display:column title="<#noparse>${checkAll}</#noparse>" media="html" style="width:30px;">
				  		<input type="checkbox" class="pk" name="${pkVar}" value="<#noparse>${</#noparse>${classVar}Item.${pkVar}}">
					</display:column>
				</f:col>
				<#list model.commonList as col>
				<#assign colName=func.convertUnderLine(col.columnName)>
				<#if (col.colType=="java.util.Date")>
				<f:col name="${colName}">
					<display:column  title="${col.getComment()}" sortable="true" sortName="${col.columnName}">
						<fmt:formatDate value="<#noparse>${</#noparse>${classVar}Item.${colName}}" pattern="yyyy-MM-dd"/>
					</display:column>
				</f:col>
				<#elseif (col.length > 256) >
				<f:col name="${colName}">
					<display:column property="${colName}" title="${col.getComment()}" sortable="true" sortName="${col.columnName}" maxLength="80"></display:column>
				</f:col>
				<#else>
				<f:col name="${colName}">
					<display:column property="${colName}" title="${col.getComment()}" sortable="true" sortName="${col.columnName}"></display:column>
				</f:col>
				</#if>
				</#list>
				<display:column title="管理" media="html" style="width:220px">
					<#if model.variables.flowKey?exists>
					<c:if test="<#noparse>${</#noparse>${classVar}Item.runId==0}">
						<a href="#" onclick="startFlow('<#noparse>${</#noparse>${classVar}Item.${pkVar}}')" class="link run"><span></span>提交</a>
						<a href="del.ht?${pkVar}=<#noparse>${</#noparse>${classVar}Item.${pkVar}}" class="link del">删除</a>
					</c:if>
					<#else>
					<a href="del.ht?${pkVar}=<#noparse>${</#noparse>${classVar}Item.${pkVar}}" class="link del">删除</a>
					</#if>
					<a href="edit.ht?${pkVar}=<#noparse>${</#noparse>${classVar}Item.${pkVar}}" class="link edit">编辑</a>
					<a href="get.ht?${pkVar}=<#noparse>${</#noparse>${classVar}Item.${pkVar}}" class="link detail">明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="${classVar}Item"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


