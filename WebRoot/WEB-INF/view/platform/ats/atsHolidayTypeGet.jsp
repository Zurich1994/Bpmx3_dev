
<%--
	time:2015-05-16 20:47:17
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>假期类型明细</title>
<%@include file="/commons/include/get.jsp"%>
<script type="text/javascript">
	//放置脚本
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">假期类型详细信息</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link back" href="list.ht"><span></span>返回</a>
					</div>
				</div>
			</div>
		</div>
		<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
			<tr>
				<th width="20%">编码:</th>
				<td>${atsHolidayType.code}</td>
			</tr>
			<tr>
				<th width="20%">名称:</th>
				<td>${atsHolidayType.name}</td>
			</tr>
			<tr>
				<th width="20%">是否系统预置:</th>
				<td>
					<c:choose>
						<c:when test="${atsHolidayType.isSys==1}">
							<span class="red">是</span>
						</c:when>
						<c:otherwise>
							<span class="green">否</span>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<th width="20%">状态:</th>
				<td>
					<c:choose>
						<c:when test="${atsHolidayType.status==1}">
							<span class="green">启用</span>
						</c:when>
						<c:otherwise>
							<span class="red">禁用</span>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<th width="20%">描述:</th>
				<td>${atsHolidayType.memo}</td>
			</tr>
		</table>
		</div>
	</div>
</body>
</html>

