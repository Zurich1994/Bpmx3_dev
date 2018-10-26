<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>浏览邮件</title>
	<%@include file="/commons/include/getById.jsp" %>
	<script type="text/javascript">
		window.onbeforeunload = function() {
			window.opener.location.reload();
		}
	</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">浏览邮件</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<c:if test="${outMail.types==1}">
						<div class="group"><a class="link update" href="reply.ht?mailId=${outMail.mailId}&isReply=1&outMailSetId=${outMailSetId}"><span></span>回复</a></div>
						<div class="l-bar-separator"></div>
					</c:if>
					<div class="group">
						<a class="link back" href="list.ht?id=${outMailSetId}&types=${outMail.types}"><span></span>返回</a>
					</div>
				</div>
			</div>
		</div>
		<div class="panel-body">
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<th width="15%">主题:</th>
					<td>${outMail.title}</td>
				</tr>
				<tr>
					<th width="15%">发件人:</th>
					<td>(${outMail.senderName})${outMail.senderAddresses}</td>
				</tr>
				<tr>
					<th width="15%">收件人:</th>
					<td>${outMail.receiverAddresses}</td>
				</tr>
				<tr>
					<th width="15%">日期:</th>
					<td><fmt:formatDate value='${outMail.mailDate}' type="both"/></td>
				</tr>	
			</table>
			<div style="border:solid 1px #7babcf;margin:2px;padding:4px;">
				<c:if test="${not empty attachments}">
				<h2>附件（${fn:length(attachments)}个）</h2>
				<c:forEach items="${attachments}" var="file">
					<div style="font-size:15px;">
						<font color="green">${file.fileName}</font>
						<a href="downLoadAttach.ht?fileId=${file.fileId}" id='downLoadAttach'>下载此附件</a>
					</div>
   				</c:forEach>
				<hr>
				<br>
   				</c:if>
				${outMail.content}
			</div>
		</div>
	</div>
</body>
</html>
