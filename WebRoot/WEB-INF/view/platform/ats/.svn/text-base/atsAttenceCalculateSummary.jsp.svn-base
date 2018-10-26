
<%--
	time:2015-06-03 14:46:19
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>考勤计算汇总明细</title>
<%@include file="/commons/include/get.jsp"%>
<script type="text/javascript">
	//放置脚本
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">考勤计算汇总明细</span>
			</div>
		</div>
		<div>
		<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
			<c:forEach items="${calculateList }" var="calculate" varStatus="status">
				<c:if test="${status.index%2==0}">
					<tr>	
				</c:if>
					<th width="20%"><fmt:formatDate value="${calculate.attenceTime}" pattern="yyyy-MM-dd"/></th>
					<td>
						${calculate.shouldAttenceHours}&nbsp;&nbsp;${calculate.unit}
					</td>
					
				<c:if test="${status.index%2!=0}">
					</tr>
				</c:if>
			</c:forEach>
		</table>
		</div>
	</div>
</body>
</html>

