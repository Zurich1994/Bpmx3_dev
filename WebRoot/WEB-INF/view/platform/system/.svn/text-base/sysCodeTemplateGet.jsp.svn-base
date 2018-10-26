
<%--
	time:2012-12-19 15:38:01
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>自定义表代码模版明细</title>
<%@include file="/commons/include/getById.jsp"%>
<script type="text/javascript">
	//放置脚本
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">自定义表代码模版详细信息</span>
			</div>
			<c:if test="${canReturn==0}">
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link back" href="list.ht"><span></span>返回</a>
					</div>
				</div>
			</div>
			</c:if>
		</div>
		<div class="panel-body">
		<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
			 
			<tr>
				<th width="20%">模版名称:</th>
				<td>${sysCodeTemplate.templateName}</td>
			</tr>
 
			<tr>
				<th width="20%">模版备注:</th>
				<td>${sysCodeTemplate.memo}</td>
			</tr>
 
			<tr>
				<th width="20%">别名:</th>
				<td>${sysCodeTemplate.templateAlias}</td>
			</tr>
			<tr>
				<th width="20%">从表也生成:</th>
				<td>
					<c:if test="${sysCodeTemplate.isSub eq 0 or empty sysCodeTemplate.isSub}"> 否 </c:if>
					<c:if test="${sysCodeTemplate.isSub eq 1 }"> 是 </c:if>
				</td>
			</tr>
			
			<tr>
				<th width="20%">模版生成的文件名称:</th>
				<td>${sysCodeTemplate.fileName}</td>
			</tr>
			<tr>
				<th width="20%">模版生成的文件路径:</th>
				<td>${sysCodeTemplate.fileDir}</td>
			</tr>
			
			<tr>
				<th width="20%">模版HTML:</th>
				<td>
					<textarea name="html" cols="120" rows="15" readonly="readonly" >${fn:escapeXml(sysCodeTemplate.html)}</textarea>
				</td>
			</tr>
		</table>
		</div>
		
	</div>
</body>
</html>

