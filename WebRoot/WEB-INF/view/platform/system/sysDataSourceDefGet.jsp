
<%--
	time:2014-08-20 11:10:07
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>SYS_DATA_SOURCE_DEF明细</title>
<%@include file="/commons/include/get.jsp"%>
<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/FlowDetailWindow.js"></script>
<script type="text/javascript">
	//放置脚本
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">SYS_DATA_SOURCE_DEF详细信息</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<c:if test="${runId!=0}">
						<div class="group"><a class="link detail"  onclick="FlowDetailWindow({runId:${runId}})" href="javascript:;" ><span></span>流程明细</a></div>
						<div class="l-bar-separator"></div>
						</c:if>
						<a class="link back" href="list.ht"><span></span>返回</a>
					</div>
				</div>
			</div>
		</div>
		<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
			<tr>
				<th width="20%">名称:</th>
				<td>${sysDataSourceDef.name}</td>
			</tr>
			<tr>
				<th width="20%">类路径:</th>
				<td>${sysDataSourceDef.classPath}</td>
			</tr>
			<tr>
				<th width="20%">设置字段JSON:</th>
				<td>${sysDataSourceDef.settingJson}</td>
			</tr>
			<tr>
				<th width="20%">初始化方法，可为空:</th>
				<td>${sysDataSourceDef.initMethod}</td>
			</tr>
			<tr>
				<th width="20%">是否是系统内部，是的话生成了的数据源在服务器开启时就自动加载到服务器:</th>
				<td>${sysDataSourceDef.isSystem}</td>
			</tr>
			<tr>
				<th width="20%">在关闭数据源还需要调用的方法，可为空:</th>
				<td>${sysDataSourceDef.closeMethod}</td>
			</tr>
		</table>
		</div>
	</div>
</body>
</html>

