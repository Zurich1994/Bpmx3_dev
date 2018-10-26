
<%--
	time:2013-12-09 14:19:29
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>高级查询规则明细</title>
<%@include file="/commons/include/get.jsp"%>
<script type="text/javascript">
	//放置脚本
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">高级查询规则详细信息</span>
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
				<th width="20%">表名:</th>
				<td>${busQueryRule.tablename}</td>
			</tr>
			<tr>
				<th width="20%">是否需要分页：0-不分页，1-分页:</th>
				<td>${busQueryRule.needpage}</td>
			</tr>
			<tr>
				<th width="20%">分页大小:</th>
				<td>${busQueryRule.pagesize}</td>
			</tr>
			<tr>
				<th width="20%">初始是否进行查询：0-是，1-否:</th>
				<td>${busQueryRule.isquery}</td>
			</tr>
			<tr>
				<th width="20%">显示字段:</th>
				<td>${busQueryRule.displayfield}</td>
			</tr>
			<tr>
				<th width="20%">过滤器字段:</th>
				<td>${busQueryRule.filterfield}</td>
			</tr>
			<tr>
				<th width="20%">排序字段:</th>
				<td>${busQueryRule.orderfield}</td>
			</tr>
			<tr>
				<th width="20%">导出字段:</th>
				<td>${busQueryRule.exportfield}</td>
			</tr>
			<tr>
				<th width="20%">打印字段:</th>
				<td>${busQueryRule.printfield}</td>
			</tr>
			<tr>
				<th width="20%">创建时间:</th>
				<td>
				<fmt:formatDate value="${busQueryRule.createtime}" pattern="yyyy-MM-dd"/>
				</td>
			</tr>
			<tr>
				<th width="20%">创建人ID:</th>
				<td>${busQueryRule.createby}</td>
			</tr>
			<tr>
				<th width="20%">更新时间:</th>
				<td>
				<fmt:formatDate value="${busQueryRule.updatetime}" pattern="yyyy-MM-dd"/>
				</td>
			</tr>
			<tr>
				<th width="20%">更新人ID:</th>
				<td>${busQueryRule.updateby}</td>
			</tr>
		</table>
		</div>
	</div>
</body>
</html>

