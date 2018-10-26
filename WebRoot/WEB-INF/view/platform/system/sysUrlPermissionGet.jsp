<%--
	time:2012-03-25 9:00:00
	拦截URL
--%>
<%@page language="java" pageEncoding="UTF-8" import="java.util.*;"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<%@include file="/commons/include/form.jsp"%>
<title>URL拦截器</title>
<style type="text/css">
tr.ruleTR th, tr.sysUrlruleTr td {
	text-align: center;
}
</style>
<script type="text/javascript">

</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"></span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link back" href="list.ht"><span></span>返回</a>
					</div>
				</div>
			</div>
		</div>
		<div class="panel-body">
			<form id="sysUrlPermissionItems" method="post" action="save.ht">
				<input type="hidden" name="id" id="id" value="${sysUrlPermission.id}">
				<input type="hidden" name="subData" id="subData" >
				<table class="table-detail" cellpadding="0" cellspacing="0"
					border="0">
					<tr>
						<th width="20%">拦截器名称:</th>
						<td>${sysUrlPermission.descp}</td>
					</tr>
					<tr>
						<th width="20%">URL地址:</th>
						<td>${sysUrlPermission.url}</td>
					</tr>
					<tr>
						<th width="20%">参数名称:</th>
						<td>
							${sysUrlPermission.params}
						</td>
					</tr>
					<tr>
						<th width="20%">是否启用:</th>
						<td>
							<c:if test="${sysUrlPermission.enable==0}">禁用</c:if>
							<c:if test="${sysUrlPermission.enable==1}">启用</c:if>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div class="panel-body">
		<div type="subtable" tablename="sys_url_rules" id="sysUrlRules" formtype="form">
			<table class="table-detail" cellpadding="0" cellspacing="0"
				border="0">
				<tbody>
					<tr class="ruleTR">
						<th>脚本名称</th>
						<th>是否启用</th>
						<th>优先级</th>
						<th  class="hidden">脚本</th>
					</tr>               
					<c:forEach items="${sysUrlRulesList}" var="sysUrlRules">
						<tr   class="sysUrlruleTr" name="sysUrlruleTr">
							<input type="hidden" name="ruleid" value="${sysUrlRules.id}">
							<td >${ sysUrlRules.descp}</td>
							<td >
								<c:if test="${sysUrlRules.enable==0}">禁用</c:if>
								<c:if test="${sysUrlRules.enable==1}">启用</c:if>
							</td>
							<td >${ sysUrlRules.sort}</td>
							<td  class="hidden">
								<textarea type="text"  name="rulescript" rows="10" cols="80">${sysUrlRules.script}</textarea>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			</div>
		</div>
	</div>
</body>
</html>
