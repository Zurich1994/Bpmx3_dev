
<%--
	time:2013-11-28 16:17:48
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>职务表明细</title>
<%@include file="/commons/include/get.jsp"%>
<script type="text/javascript">
	//放置脚本
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">职务表详细信息</span>
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
				<th width="20%">职务名称:</th>
				<td>${job.jobname}</td>
			</tr>
			<tr>
				<th width="20%">职务代码:</th>
				<td>${job.jobcode}</td>
			</tr>
			<tr>
				<th width="20%">职务描述:</th>
				<td>${job.jobdesc}</td>
			</tr>
			<%-- 
			<tr>
				<th width="20%">设置码:</th>
				<td>${job.setid}</td>
			</tr>
		      --%>
			<tr>
				<th width="20%">是否删除:</th>
				<td>
					<c:choose>
					  	<c:when test="${job.isdelete==1 }">
					  		删除
					  	</c:when>
					  	<c:otherwise>
					  		未删除
					  	</c:otherwise>
		            </c:choose>
				</td>
			</tr>
		</table>
		</div>
	</div>
</body>
</html>

