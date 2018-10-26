
<%--
	time:2015-04-27 15:19:27
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>SYS_OBJ_LOG明细</title>
<%@include file="/commons/include/get.jsp"%>
<script type="text/javascript">
	//放置脚本
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">日志详细信息</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link back" href="javaScript:history.go(-1)"><span></span>返回</a>
					</div>
				</div>
			</div>
		</div>
		<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
			<tr>
				<th width="20%">名称:</th>
				<td>${sysObjLog.name}</td>
			</tr>
			<tr>
				<th width="20%">操作人</th>
				<td>${sysObjLog.operator}</td>
			</tr>
			<tr>
				<th width="20%">创建时间:</th>
				<td>
				<fmt:formatDate value="${sysObjLog.createTime}" pattern="yyyy-MM-dd"/>
				</td>
			</tr>
			<tr>
				<th width="20%">内容:</th>
				<td>${sysObjLog.content}</td>
			</tr>
			<tr>
				<th width="20%">日志对象类型:</th>
				<td>${sysObjLog.objType}</td>
			</tr>
		</table>
		</div>
	</div>
</body>
</html>

