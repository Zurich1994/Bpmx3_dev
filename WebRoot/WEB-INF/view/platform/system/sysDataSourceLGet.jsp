
<%--
	time:2014-08-21 10:50:18
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>SYS_DATA_SOURCE明细</title>
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
				<span class="tbar-label">SYS_DATA_SOURCE详细信息</span>
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
				<td>${sysDataSourceL.name}</td>
			</tr>
			<tr>
				<th width="20%">别名:</th>
				<td>${sysDataSourceL.alias}</td>
			</tr>
			<tr>
				<th width="20%">数据源的类型-mysql,oracle,sqlserver:</th>
				<td>${sysDataSourceL.dbType}</td>
			</tr>
			<tr>
				<th width="20%">设置字段:</th>
				<td>${sysDataSourceL.settingJson}</td>
			</tr>
			<tr>
				<th width="20%">开始服务器时启动:</th>
				<td>${sysDataSourceL.initOnStart}</td>
			</tr>
			<tr>
				<th width="20%">是否可用:</th>
				<td>${sysDataSourceL.enabled}</td>
			</tr>
			<tr>
				<th width="20%">类路径:</th>
				<td>${sysDataSourceL.classPath}</td>
			</tr>
			<tr>
				<th width="20%">初始化方法:</th>
				<td>${sysDataSourceL.initMethod}</td>
			</tr>
			<tr>
				<th width="20%">关闭方法:</th>
				<td>${sysDataSourceL.closeMethod}</td>
			</tr>
		</table>
		</div>
	</div>
</body>
</html>

