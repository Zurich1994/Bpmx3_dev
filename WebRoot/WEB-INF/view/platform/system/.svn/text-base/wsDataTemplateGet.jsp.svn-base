<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<%@include file="/commons/include/form.jsp"%>
<title>WEB服务数据模板明细</title>
<script type="text/javascript" src="${ctx}/js/javacode/codemirror.js"></script>
<script type="text/javascript" src="${ctx}/js/javacode/InitMirror.js"></script>
<style type="text/css">
	html,body{
		overflow:auto;
	}
	.para-info-table th, .para-info-table td{
		text-align: center;
		height: 32px;
	}
	.para-info-table td{
		padding:5px;
	}
	input{
		width:180px;
	}
	select{
		min-width:185px;
		height:24px;
	}
</style>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">WEB服务数据模板明细</span>
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
		<div class="panel-detail">
		<table class="table-detail" cellpadding="0" cellspacing="0" border="0">				 
			<tr>
				<th width="20%">名称:</th>
				<td>${wsDataTemplate.name}</td>
			</tr>
			<tr>
				<th width="20%">别名:</th>
				<td>
					<c:forEach items="${bpmCommonWsSetList}" var="bpmCommonWsSet">
						<c:if test='${bpmCommonWsSet.id == wsDataTemplate.serviceId}'>${bpmCommonWsSet.alias}</c:if>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<th width="20%">解析代码:</th>
				<td>
					<textarea id="parseScript" codemirror="true" mirrorheight="300px" rows="26" cols="70">${wsDataTemplate.script}</textarea>
				</td>
			</tr>
			<tr>
				<th width="20%">模板:</th>
				<td>
					<textarea id="templateScript" codemirror="true" mirrorheight="300px" rows="26" cols="70">${wsDataTemplate.template}</textarea>
				</td>
			</tr> 
		</table>
		</div>
		</div>
	</div>
	
</body>
</html>