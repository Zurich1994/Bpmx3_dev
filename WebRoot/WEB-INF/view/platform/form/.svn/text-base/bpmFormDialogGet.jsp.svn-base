<%--
	time:2012-06-25 11:05:09
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>通用表单对话框明细</title>
	<%@include file="/commons/include/getById.jsp" %>
	<script type="text/javascript">
		//放置脚本
	</script>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">通用表单对话框详细信息</span>
			</div>
			<c:if test="${canReturn==0}">
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="back bar-button" href="list.ht"><span></span>返回</a></div>
				</div>
			</div>
			</c:if>
		</div>
		<div class="panel-body">
				<div class="panel-detail">
				<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
					<tr>
						<th width="20%">对话框名称:</th>
						<td>${bpmFormDialog.name}</td>
					</tr>
					<tr>
						<th width="20%">对话框别名:</th>
						<td>${bpmFormDialog.alias}</td>
					</tr>
					<tr>
						<th width="20%">显示样式0,列表1,树形:</th>
						<td>${bpmFormDialog.style}</td>
					</tr>
					<tr>
						<th width="20%">对话框宽度:</th>
						<td>${bpmFormDialog.width}</td>
					</tr>
					<tr>
						<th width="20%">高度:</th>
						<td>${bpmFormDialog.height}</td>
					</tr>
					<tr>
						<th width="20%">是否单选0,多选1.单选:</th>
						<td>${bpmFormDialog.issingle}</td>
					</tr>
					<tr>
						<th width="20%">是否分页:</th>
						<td>${bpmFormDialog.needpage}</td>
					</tr>
					<tr>
						<th width="20%">是否为表0,视图1,数据库表:</th>
						<td>${bpmFormDialog.istable}</td>
					</tr>
					<tr>
						<th width="20%">对象名称:</th>
						<td>${bpmFormDialog.objname}</td>
					</tr>
					<tr>
						<th width="20%">需要显示的字段:</th>
						<td>${bpmFormDialog.displayfield}</td>
					</tr>
					<tr>
						<th width="20%">条件字段:</th>
						<td>${bpmFormDialog.conditionfield}</td>
					</tr>
					<tr>
						<th width="20%">显示结果字段:</th>
						<td>${bpmFormDialog.resultfield}</td>
					</tr>
					<tr>
						<th width="20%">数据源名称:</th>
						<td>${bpmFormDialog.dsname}</td>
					</tr>
					<tr>
						<th width="20%">数据源别名:</th>
						<td>${bpmFormDialog.dsalias}</td>
					</tr>
				</table>
			</div>
		</div>
</div>

</body>
</html>
