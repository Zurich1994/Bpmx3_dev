<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>系统岗位表，实际是部门和职务的对应关系表明细</title>
<%@include file="/commons/include/get.jsp"%>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">系统岗位表，实际是部门和职务的对应关系表详细信息</span>
			</div>
			<c:if test="${canReturn!=2}">
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link back" href="list.ht"><span></span>返回</a>
					</div>
				</div>
			</div>
			</c:if>
		</div>
		<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
			<tr>
				<th width="20%">组织名称:</th>
				<td>${position.orgName} </td>
			</tr>
			<tr>
				<th width="20%">职务:</th>
				<td>${position.jobName}</td>
			</tr>
			<tr>
				<th width="20%">岗位名称:</th>
				<td>${position.posName}</td>
			</tr>
			<tr>
				<th width="20%">岗位代码:</th>
				<td>${position.posCode}</td>
			</tr>
			<tr>
				<th width="20%">岗位描述:</th>
			<td>${position.posDesc}</td>
			</tr>
			<tr>
				<th width="20%">是否删除:</th>
				<td>
			<c:choose>
			    <c:when test="${position.isDelete ==1}">
			          <span class="red">删除</span>
			    </c:when>
			    <c:otherwise>
			           <span class="green">否</span>
			    </c:otherwise>			   
		    </c:choose>
				</td>
			</tr>
		</table>
		</div>
	</div>
</body>
</html>

