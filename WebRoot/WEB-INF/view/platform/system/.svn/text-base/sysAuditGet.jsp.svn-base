<%--
	time:2011-11-26 11:35:04
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>详细信息</title>
	<%@include file="/commons/include/getById.jsp" %>
</head>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysAuditLink.js"></script>
<script type="text/javascript">
	//系统日志的详细信息的超链接
	$(function(){
		SysAuditLink.initLink();
	});
</script>
<body>
<div class="panel">
<div class="hide-panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">详细信息</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link back" href="../sysAudit/list.ht"><span></span>返回</a></div>
				</div>
			</div>
		</div>
	</div>
		<div class="panel-body">
				<form id="sysAuditForm" method="post" action="add2.ht">
					<div class="panel-detail">
						<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
							<tr>
								<th width="20%">操作名称:</th>
								<td>${sysAudit.opName}</td>
							</tr>
							<tr>
								<th width="20%">执行时间:</th>
								<td><fmt:formatDate value="${sysAudit.exeTime}" pattern="yyyy-MM-dd HH:mm"/></td>
							</tr>
							<tr>
								<th width="20%">执行人:</th>
								<td><a href="javascript:;" hrefLink="${ctx}/platform/system/sysUser/getByUserId.ht?userId=${sysAudit.executorId}">${sysAudit.executor}</a></td>
							</tr>
							<tr>
								<th width="20%">IP:</th>
								<td>${sysAudit.fromIp}</td>
							</tr>
							<tr>
								<th width="20%">归属模块:</th>
								<td>${sysAudit.ownermodel}</td>
							</tr>
							<tr>
								<th width="20%">日志类型:</th>
								<td>${sysAudit.exectype}</td>
							</tr>
							<tr>
								<th width="20%">执行方法:</th>
								<td>${sysAudit.exeMethod}</td>
							</tr>
							<tr>
								<th width="20%">请求URL:</th>
								<td>${sysAudit.requestURI}</td>
							</tr>
							<tr>
								<th width="20%">详细信息:</th>
								<td>${sysAudit.detail}</td>
							</tr>
						</table>
					</div>
				</form>
		</div>
</div>

</body>
</html>
