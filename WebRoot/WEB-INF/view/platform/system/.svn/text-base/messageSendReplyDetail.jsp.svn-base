<%--
	time:2012-01-14 15:10:58
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<%@include file="/commons/include/get.jsp" %>
</head>
<body>
<div class="panel">
	<f:tab curTab="replyDetai" tabName="msgSend"/>
	<display:table name="replylist" id="replyItem" cellpadding="1" cellspacing="1" class="table-grid">
		<display:column property="reply" title="回复人"></display:column>
		<display:column  title="回复时间"  style="text-align:center;">
			<fmt:formatDate value="${replyItem.replyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
		</display:column>
		<display:column  title="回复内容" style="text-align:center; width:60%">
			${replyItem.content}
		</display:column>
	</display:table>
</div>

</body>
</html>
