<%--
	time:2012-01-14 15:10:58
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<%@include file="/commons/include/get.jsp" %>
</head>
<body style="height:90%">
<div class="panel">
	<c:if test="${canReply==1}">
		<f:tab curTab="readDetail" tabName="msgSend"/>
	</c:if>
	<div class="panel-detail">
		<display:table name="readlist" id="readItem" cellpadding="1" cellspacing="1" class="table-grid">						
			<display:column property="receiver" title="读信人"></display:column>
			<display:column title="读信时间 " style="text-align:center;">
				<fmt:formatDate value="${readItem.receiveTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</display:column>
		</display:table>
	</div>
</div>

</body>
</html>
