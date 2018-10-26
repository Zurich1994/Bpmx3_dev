<%--
	time:2012-05-25 10:16:17
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>系统模版明细</title>
	<%@include file="/commons/include/getById.jsp" %>
	<script type="text/javascript">
		//放置脚本
	</script>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">系统模版详细信息</span>
			</div>
			
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link back" href="list.ht"><span></span>返回</a>
					</div>
					
				</div>
			</div>
			
		</div>
		<div class="panel-body">
					<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<th width="20%">主题:</th>
							<td>${sysOfficeTemplate.subject}</td>
						</tr>
						<tr>
							<th width="20%">模版类型:</th>
							<td>
								<c:if test="${sysOfficeTemplate.templatetype==1}">普通模版</c:if>
								<c:if test="${sysOfficeTemplate.templatetype==2}">套红模版</c:if>
							</td>
						</tr>
						<tr>
							<th width="20%">创建人:</th>
							<td>${sysOfficeTemplate.creator}</td>
						</tr>
						<tr>
							<th width="20%">创建时间:</th>
							<td>
								<fmt:formatDate value="${sysOfficeTemplate.createtime}"/>
							</td>
						</tr>
						<tr>
							<th width="20%">路径:</th>
							<td>${sysOfficeTemplate.path}</td>
						</tr>
						<tr>
							<th width="20%">备注:</th>
							<td>${sysOfficeTemplate.memo}</td>
						</tr>
					</table>
				</div>
		</div>

</body>
</html>
