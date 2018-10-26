<%--
	time:2012-02-08 16:45:57
	desc:edit the 消息设置
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 消息设置</title>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=message"></script>
	<script type="text/javascript">
		$(function() {
			function showRequest(formData, jqForm, options) { 
				return true;
			} 
			valid(showRequest,showResponse);
			$("a.save").click(function() {
				$('#messageForm').submit(); 
			});
		});
	</script>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
			    <c:choose>
			        <c:when test="${message.messageId !=null }">
			            <span class="tbar-label">编辑消息设置</span>
			        </c:when>
			        <c:otherwise>
			            <span class="tbar-label">添加消息设置</span>
			        </c:otherwise>
			    </c:choose>			
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
				<form id="messageForm" method="post" action="save.ht">
					<div class="panel-detail">
							<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
								<tr>
									<th width="20%">主题: </th>
									<td><input type="text" id="subject" name="subject" value="${message.subject}"  class="inputText"/></td>
								</tr>
								<tr>
									<th width="20%">收件人: </th>
									<td><input type="text" id="receiver" name="receiver" value="${message.receiver}"  class="inputText"/></td>
								</tr>
								<tr>
									<th width="20%">抄送: </th>
									<td><input type="text" id="copyTo" name="copyTo" value="${message.copyTo}"  class="inputText"/></td>
								</tr>
								<tr>
									<th width="20%">秘密抄送: </th>
									<td><input type="text" id="bcc" name="bcc" value="${message.bcc}"  class="inputText"/></td>
								</tr>
								<tr>
									<th width="20%">发件人: </th>
									<td><input type="text" id="fromUser" name="fromUser" value="${message.fromUser}"  class="inputText"/></td>
								</tr>
								<tr>
									<th width="20%">内容模版: </th>
									<td><input type="text" id="templateId" name="templateId" value="${message.templateId}"  class="inputText"/></td>
								</tr>
								<tr>
									<th width="20%">消息类型: </th>
									<td><input type="text" id="messageType" name="messageType" value="${message.messageType}"  class="inputText"/></td>
								</tr>
							</table>
						</div>
					<input type="hidden" name="messageId" value="${message.messageId}" />
				</form>
		</div>
</div>
</body>
</html>
