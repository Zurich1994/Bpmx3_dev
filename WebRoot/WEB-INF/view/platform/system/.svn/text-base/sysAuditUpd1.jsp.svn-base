<%--
	time:2011-11-26 11:35:04
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>修改系统日志</title>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=sysAudit"></script>
	<script type="text/javascript">
		$(function() {
			function showRequest(formData, jqForm, options) { 
				return true;
			} 
			valid(showRequest,showResponse);
			$("a.save").click(function() {
				$('#sysAuditForm').submit(); 
			});
		});
	</script>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">系统日志修改</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link save" id="dataFormSave" href="javascript:;"><span></span>保存</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link back" href="list.ht"><span></span>返回</a></div>
				</div>
			</div>
		</div>
		<div class="panel-body">

				<form id="sysAuditForm" method="post" action="upd2.ht">
					<div class="panel-detail">
						<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
							<tr>
								<th width="20%">操作名称: </th>
								<td ><input type="text" id="opName" name="opName" value="${sysAudit.opName}"  class="inputText"/></td>
							</tr>
							<tr>
								<th width="20%">执行时间: </th>
								<td ><input type="text" id="exeTime" name="exeTime" value="${sysAudit.exeTime}"  class="inputText"/></td>
							</tr>
							<tr>
								<th width="20%">执行人ID: </th>
								<td ><input type="text" id="executorId" name="executorId" value="${sysAudit.executorId}"  class="inputText"/></td>
							</tr>
							<tr>
								<th width="20%">执行人: </th>
								<td ><input type="text" id="executor" name="executor" value="${sysAudit.executor}"  class="inputText"/></td>
							</tr>
							<tr>
								<th width="20%">IP: </th>
								<td ><input type="text" id="fromIp" name="fromIp" value="${sysAudit.fromIp}"  class="inputText"/></td>
							</tr>
							<tr>
								<th width="20%">执行方法: </th>
								<td ><input type="text" id="exeMethod" name="exeMethod" value="${sysAudit.exeMethod}"  class="inputText"/></td>
							</tr>
							<tr>
								<th width="20%">请求URL: </th>
								<td ><input type="text" id="requestURI" name="requestURI" value="${sysAudit.requestURI}"  class="inputText"/></td>
							</tr>
							<tr>
								<th width="20%">请求参数: </th>
								<td ><input type="text" id="reqParams" name="reqParams" value="${sysAudit.reqParams}"  class="inputText"/></td>
							</tr>
						</table>
					</div>
					<input type="hidden" name="auditId" value="${sysAudit.auditId}" />
				</form>
		</div>
</div>
</body>
</html>

