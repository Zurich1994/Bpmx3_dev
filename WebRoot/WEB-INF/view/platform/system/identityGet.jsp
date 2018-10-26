<%--
	time:2012-02-03 14:40:59
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>流水号生成明细</title>
	<%@include file="/commons/include/getById.jsp" %>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">流水号生成详细信息</span>
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
			<div class="panel-detail">
				<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
					<tr>
						<th width="20%">名称:</th>
						<td>${identity.name}</td>
					</tr>
					<tr>
						<th width="20%">别名:</th>
						<td>${identity.alias}</td>
					</tr>
					<tr>
						<th width="20%">规则:</th>
						<td>${identity.rule}</td>
					</tr>
					<tr>
						<th width="20%">生成类型:</th>
						<td> 
							<c:choose>
								<c:when test="${identity.genType==1}">
									每天生成
								</c:when>
								<c:when test="${identity.genType==2}">
									每月生成
								</c:when>
								<c:when test="${identity.genType==3}">
									每年生成
								</c:when>
								<c:otherwise>
									递增
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<th width="20%">流水号长度:</th>
						<td>${identity.noLength}</td>
					</tr>
					<tr>
						<th width="20%">初始值:</th>
						<td>${identity.initValue}</td>
					</tr>
					<tr>
						<th width="20%">当前值:</th>
						<td>${identity.curValue}</td>
					</tr>
				</table>
			</div>
		</div>
</div>

</body>
</html>
