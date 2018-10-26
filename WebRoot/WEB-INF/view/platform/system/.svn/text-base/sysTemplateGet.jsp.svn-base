<%--
	time:2011-12-28 14:04:30
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>模版管理明细</title>
	<%@include file="/commons/include/getById.jsp" %>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">模版管理详细信息</span>
			</div>
			<c:if test="${canReturn==0}">
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link back" href="../sysTemplate/list.ht"><span></span>返回</a></div>
				</div>
			</div>
			</c:if>
		</div>
		<div class="panel-body">
			<div class="panel-detail">
				<form id="sysTemplateForm" method="post" action="add2.ht">
					<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<th width="20%">模板名称:</th>
							<td>${sysTemplate.name}</td>
						</tr>
						<tr>
							<th width="20%">模板用途:</th>
							<td>
								<c:choose>
									<c:when test="${sysTemplate.useType eq 1}">终止提醒</c:when>
									<c:when test="${sysTemplate.useType eq 2}">催办提醒</c:when>
									<c:when test="${sysTemplate.useType eq 3}">审批提醒</c:when>
									<c:when test="${sysTemplate.useType eq 4}">撤销提醒</c:when>
									<c:when test="${sysTemplate.useType eq 5}">取消转办</c:when>
									<c:when test="${sysTemplate.useType eq 6}">沟通提醒</c:when>
									<c:when test="${sysTemplate.useType eq 7}">归档提醒</c:when>
									<c:when test="${sysTemplate.useType eq 8}">转办提醒</c:when>
									<c:when test="${sysTemplate.useType eq 9}">退回提醒</c:when>
									<c:when test="${sysTemplate.useType eq 10}">沟通反馈</c:when>
									<c:when test="${sysTemplate.useType eq 11}">取消代理</c:when>
									<c:when test="${sysTemplate.useType eq 12}">抄送提醒</c:when>
									<c:when test="${sysTemplate.useType eq 13}">流程节点无人员</c:when>
									<c:when test="${sysTemplate.useType eq 19}">逾期提醒</c:when>
									<c:when test="${sysTemplate.useType eq 22}">代理提醒</c:when>
									<c:when test="${sysTemplate.useType eq 23}">消息转发</c:when>
									<c:when test="${sysTemplate.useType eq 24}">重启任务</c:when>
									<c:when test="${sysTemplate.useType eq 25}">通知任务所属人(代理)</c:when>
									<c:when test="${sysTemplate.useType eq 26}">加签提醒</c:when>
									<c:when test="${sysTemplate.useType eq 27}">加签反馈</c:when>
									<c:when test="${sysTemplate.useType eq 28}">取消流转</c:when>
									<c:when test="${sysTemplate.useType eq 29}">补签提醒</c:when>
									<c:otherwise>其他</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>
							<th width="20%">是否默认:</th>
							<td>
								<c:choose>
									<c:when test="${sysTemplate.isDefault==1}">是</c:when>
									<c:otherwise>否</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>
							<th>标题: </th>
							<td>
								${sysTemplate.title}				
							</td>
						</tr>
						<tr>
							<th width="20%">html内容:</th>
							<td>${sysTemplate.htmlContent}</td>
						</tr>
						<tr>
							<th width="20%">普通内容:</th>
							<td>${sysTemplate.plainContent}</td>
						</tr>
						
					</table>
				</form>
			</div>
		</div>
</div>

</body>
</html>
