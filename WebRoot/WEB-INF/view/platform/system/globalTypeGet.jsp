<%--
	time:2011-11-23 11:07:27
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>总分类表明细</title>
	<%@include file="/commons/include/getById.jsp" %>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">总分类表详细信息</span>
			</div>
			<c:if test="${canReturn==0}">
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class=" link back" href="../globalType/list.ht"><span></span>返回</a></div>
				</div>
			</div>
			</c:if>
		</div>
		<div class="panel-body">
				<form id="globalTypeForm" method="post" action="add2.ht">
				<div class="panel-detail">
						<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
							<tr>
								<th width="20%">名称:</th>
								<td>${globalType.typeName}</td>
							</tr>
							<tr>
								<th width="20%">节点Key:</th>
								<td>${globalType.nodeKey}</td>
							</tr>
							<tr>
								<th width="20%">分类Key:</th>
								<td>${globalType.catKey}</td>
							</tr>
							<tr>
								<th width="20%">结构:</th>
								<td>
									<c:if test="${globalType.type==0}">平铺结构</c:if> 
									<c:if test="${globalType.type==1}">树形结构</c:if> 
								
								</td>
							</tr>
							<tr>
								<th width="20%">序号:</th>
								<td>${globalType.sn}</td>
							</tr>						
							<tr>
								<th width="20%">父节点:</th>
								<td>${globalType.parentId}</td>
							</tr>
							<tr>
								<th width="20%">层次:</th>
								<td>${globalType.depth}</td>
							</tr>
							<tr>
								<th width="20%">nodePath:</th>
								<td>${globalType.nodePath}</td>
							</tr>
							<tr>
								<th width="20%">所属用户:</th>
								<td>${globalType.userId}</td>
							</tr>
							<tr>
								<th width="20%">depId:</th>
								<td>${globalType.depId}</td>
							</tr>
							
						</table>
					</div>
				</form>
		</div>
</div>

</body>
</html>
