<#assign class=table.variable.class>
<#assign classVar=table.variable.classVar> 
<#assign tabComment=table.tableDesc> 
<#assign fieldList=table.fieldList>
<#assign subtables=table.subTableList>

<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>${tabComment}明细</title>
<%@include file="/commons/include/customForm.jsp"%>
<script type="text/javascript" src="<#noparse>${ctx}</#noparse>/js/hotent/subform.js"></script>
<#if flowKey?exists>
<script type="text/javascript" src="<#noparse>${ctx}</#noparse>/js/hotent/platform/bpm/FlowDetailWindow.js"></script>
</#if>
<script type="text/javascript">
	//放置脚本
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">${tabComment}详细信息</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<#if flowKey?exists>
						<div class="group"><a class="link detail"  onclick="FlowDetailWindow({runId:<#noparse>${</#noparse>runId}})" href="javascript:;" ><span></span>流程明细</a></div>
						<div class="l-bar-separator"></div>
						</#if>
						<a class="link back" href="list.ht"><span></span>返回</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	${html}
</body>
</html>

