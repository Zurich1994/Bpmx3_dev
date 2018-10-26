<%--
	time:2012-02-08 16:45:57
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>消息设置明细</title>
	<%@include file="/commons/include/getById.jsp" %>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">消息设置详细信息</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link back" href="list.ht"><span></span>返回</a></div>
				</div>
			</div>
		</div>
		<div class="panel-body">
				<div class="panel-detail">
					<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<th width="20%">主题:</th>
							<td>${message.subject}</td>
						</tr>
						<tr>
							<th width="20%">收件人:</th>
							<td>${message.receiver}</td>
						</tr>
						<tr>
							<th width="20%">抄送:</th>
							<td>${message.copyTo}</td>
						</tr>
						<tr>
							<th width="20%">秘密抄送:</th>
							<td>${message.bcc}</td>
						</tr>
						<tr>
							<th width="20%">发件人:</th>
							<td>${message.fromUser}</td>
						</tr>
						<tr>
							<th width="20%">内容模版:</th>
							<td>${message.templateId}</td>
						</tr>
						<tr>
							<th width="20%">消息类型:</th>
							<td>${message.messageType}</td>
						</tr>
					</table>
				</div>
		</div>
</div>

</body>
</html>
