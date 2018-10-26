
<%--
	time:2015-05-19 17:42:20
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>假期制度明细</title>
<%@include file="/commons/include/get.jsp"%>
<script type="text/javascript">
	//放置脚本
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">假期制度详细信息</span>
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
				<td>${atsHolidayPolicy.code}</td>
			</tr>
			<tr>
				<th width="20%">名称:</th>
				<td>${atsHolidayPolicy.name}</td>
			</tr>
			<tr>
				<th width="20%">所属组织:</th>
				<td>${atsHolidayPolicy.orgId}</td>
			</tr>
			<tr>
				<th width="20%">是否默认:</th>
				<td>
						<c:choose>
							<c:when test="${atsHolidayPolicy.isDefault==1}">
								<span class="red">是</span>
							</c:when>
							<c:otherwise>
								<span class="green">否</span>
							</c:otherwise>
						</c:choose></td>
			</tr>
			<tr>
				<th width="20%">是否启动半天假:</th>
				<td>
					<c:choose>
							<c:when test="${atsHolidayPolicy.isHalfDayOff==1}">
								<span class="red">是</span>
							</c:when>
							<c:otherwise>
								<span class="green">否</span>
							</c:otherwise>
						</c:choose></td>
			</tr>
			<tr>
				<th width="20%">描述:</th>
				<td>${atsHolidayPolicy.memo}</td>
			</tr>
		</table>
		</div>
	</div>
</body>
</html>

