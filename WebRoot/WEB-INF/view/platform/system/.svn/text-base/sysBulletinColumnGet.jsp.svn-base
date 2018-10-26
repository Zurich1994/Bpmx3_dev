<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>公告栏目明细</title>
<%@include file="/commons/include/form.jsp"%>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">公告栏目明细</span>
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
			<form id="sysBulletinColumnForm" method="post" action="save.ht">
				<table class="table-detail" cellpadding="0" cellspacing="0"
					border="0">
					<tr>
						<th width="20%">栏目名字:</th>
						<td>
							${sysBulletinColumn.name}
						</td>
					</tr>
					<tr>
						<th width="20%">栏目别名:</th>
						<td>
							${sysBulletinColumn.alias}
						</td>
					</tr>
					<tr>
						<th width="20%">所属分公司:</th></th>
						<td>
							<c:choose>
								<c:when test="${sysBulletinColumn.tenantid eq 0}">所有公司</c:when>
								<c:otherwise>${sysBulletinColumn.tenantname}</c:otherwise>
							</c:choose>	
						</td>
					</tr>
					<tr>
						<th width="20%">是否可用:</th>
						<td>
							<c:choose>
								<c:when test="${sysBulletinColumn.status eq 1}">可用</c:when>
								<c:otherwise>不可用</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<th width="20%">创建人:</th></th>
						<td>
							${sysBulletinColumn.creator}
						</td>
					</tr>
					<tr>
						<th width="20%">创建时间:</th></th>
						<td>
							<fmt:formatDate value="${sysBulletinColumn.createtime}" pattern="yyyy-MM-dd" />
						</td>
					</tr>
					
				</table>
			</form>
		</div>
	</div>
</body>
</html>
