<%--
	time:2012-01-09 16:35:25
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>表单模板明细</title>
	<%@include file="/commons/include/getById.jsp" %>
	<script type="text/javascript" src="${ctx}/js/javacode/codemirror.js"></script>
	<script type="text/javascript" src="${ctx}/js/javacode/InitMirror.js"></script>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">表单模板详细信息</span>
			</div>
			<c:if test="${canReturn==0}">
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link back" href="list.ht"><span></span>返回</a></div>
				</div>
			</div>
			</c:if>
		</div>
		<div class="panel-body">
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<th width="20%">模板名:</th>
					<td>${bpmFormTemplate.templateName}</td>
				</tr>
				<tr>
					<th width="20%">模板类型</th>
					<td>
						${bpmFormTemplate.templateType}
					</td>
				</tr>
				<tr>
					<th width="20%">模板html:</th>
					<td><textarea id="html" name="html" codemirror="true" mirrorheight="400px" cols=100 rows=20 readonly>${fn:escapeXml(bpmFormTemplate.html)}</textarea></td>
				</tr>
				<tr>
					<th width="20%">说明:</th>
					<td><c:out escapeXml="true" value="${bpmFormTemplate.templateDesc}"></c:out></td>
				</tr>
			</table>
		</div>
</div>
</body>
</html>
