
<%--
	time:2012-11-29 16:24:35
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>发送消息日志明细</title>
<%@include file="/commons/include/getById.jsp"%>
<script type="text/javascript">
	//放置脚本
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">发送消息日志详细信息</span>
			</div>
			<c:if test="${canReturn==0}">
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link back" href="list.ht"><span></span>返回</a>
					</div>
				</div>
			</div>
			</c:if>
		</div>
		<div class="panel-body">
		<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
			 
			<tr>
				<th width="20%">主题:</th>
				<td>${messageLog.subject}</td>
			</tr>
 
			<tr>
				<th width="20%">发送时间:</th>
				<td>
				<fmt:formatDate value="${messageLog.sendTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
			</tr>
 
			<tr>
				<th width="20%">接收者:</th>
				<td>${messageLog.receiver}</td>
			</tr>
 
			<tr>
				<th width="20%">消息类型:</th>
				<td><c:choose>
						<c:when test="${messageLog.messageType==1 }">邮件信息</c:when>
						<c:when test="${messageLog.messageType==2 }">手机短信</c:when>
						<c:when test="${messageLog.messageType==3 }">内部消息</c:when>
						<c:otherwise>
							未知类型
						</c:otherwise>
					</c:choose>
					
					
				</td>
			</tr>
 
			<tr>
				<th width="20%">发送状态:</th>
				<td>
					<c:choose>
							<c:when test="${messageLog.state eq 1 }">成功</c:when>
							<c:otherwise>失败</c:otherwise>
					</c:choose></td>
			</tr>
		</table>
		</div>
		
	</div>
</body>
</html>

