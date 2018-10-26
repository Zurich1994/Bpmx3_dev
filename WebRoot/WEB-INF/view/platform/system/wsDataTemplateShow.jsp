<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<%@include file="/commons/include/get.jsp"%>
<title>WEB服务数据模板展示</title>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
			<span class="tbar-label">WEB服务数据模板展示</span>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group">
					<a class="link search"><span></span>查询</a>
				</div>
			</div>
		</div>
		<div class="panel-search">
			<form id="searchForm" method="post" action="">
				<div class="row">
				<c:forEach items="${list}" var="obj">
					<input type="hidden" name="javaType" value="${obj.javaType}"/>
					<span name="bindingVal" class="label">${obj.bindingVal}</span>
					<c:choose>
						<c:when test="${obj.javaType == 1||obj.javaType == 2}">
							<input type="text" name="${obj.bindingVal}" value="${param[obj.bindingVal]}"/>
						</c:when>
						<c:otherwise>
							<input type="text" name="${obj.bindingVal}" class="inputText date" value="${param[obj.bindingVal]}"/>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				</div>
			</form>
		</div>
	</div>
	<div class="panel-body">
		${html}
	</div>
</div>
</body>
</html>