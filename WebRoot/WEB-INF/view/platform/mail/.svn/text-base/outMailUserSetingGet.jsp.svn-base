<%--
	time:2012-04-09 13:43:51
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>邮箱明细</title>
	<%@include file="/commons/include/getById.jsp" %>
	<script type="text/javascript">
		//放置脚本
	</script>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">邮箱详细信息</span>
			</div>
			<c:if test="${canReturn==0}">
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link back " href="list.ht"><span></span>返回</a></div>
				</div>
			</div>
			</c:if>
		</div>
		<div class="panel-body">
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<th width="20%">账号名称:</th>
					<td>${outMailUserSeting.userName}</td>
				</tr>
				<tr>
					<th width="20%">邮箱地址:</th>
					<td>${outMailUserSeting.mailAddress}</td>
				</tr>
				<tr>
					<th width="20%">邮箱类型:</th>
					<td>${outMailUserSeting.mailType}</td>
				</tr>
				<tr >
					<th width="20%">smtp主机:</th>
					<td>${outMailUserSeting.smtpHost}</td>
				</tr>
				<tr>
					<th width="20%">smtp端口:</th>
					<td>${outMailUserSeting.smtpPort}</td>
				</tr>
				<tr <c:if test="${outMailUserSeting.mailType=='imap'}">style="display: none"</c:if>>
					<th width="20%">pop主机:</th>
					<td>${outMailUserSeting.popHost}</td>
				</tr>
				<tr <c:if test="${outMailUserSeting.mailType=='imap'}">style="display: none"</c:if>>
					<th width="20%">pop端口:</th>
					<td>${outMailUserSeting.popPort}</td>
				</tr>
				<tr <c:if test="${outMailUserSeting.mailType=='pop3'}">style="display: none"</c:if>>
					<th width="20%">imap主机:</th>
					<td>${outMailUserSeting.imapHost}</td>
				</tr>
				<tr <c:if test="${outMailUserSeting.mailType=='pop3'}">style="display: none"</c:if>>
					<th width="20%">imap端口:</th>
					<td>${outMailUserSeting.imapPort}</td>
				</tr>
				<tr>
					<th width="20%">是否默认:</th>
					<td>
						<c:choose>
							<c:when test="${outMailUserSeting.isDefault==1}">是</c:when>
							<c:otherwise>否</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</table>
		</div>
</div>

</body>
</html>
