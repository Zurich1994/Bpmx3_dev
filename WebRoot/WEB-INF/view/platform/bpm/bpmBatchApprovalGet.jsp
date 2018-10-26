
<%--
	time:2015-04-17 15:34:17
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>流程批量审批定义设置明细</title>
<%@include file="/commons/include/get.jsp"%>
<script type="text/javascript">
	//放置脚本
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">流程批量审批定义设置详细信息</span>
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
				<th width="20%">流程定义ID:</th>
				<td>${bpmBatchApproval.defKey}</td>
			</tr>
			<tr>
				<th width="20%">节点ID:</th>
				<td>${bpmBatchApproval.nodeId}</td>
			</tr>
			<tr>
				<th width="20%">自定义表ID:</th>
				<td>${bpmBatchApproval.tableId}</td>
			</tr>
			<tr>
				<th width="20%">字段设置:</th>
				<td>${bpmBatchApproval.fieldJson}</td>
			</tr>
		</table>
		</div>
	</div>
</body>
</html>

