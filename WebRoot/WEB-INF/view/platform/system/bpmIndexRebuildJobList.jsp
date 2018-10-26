<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>索引重建管理</title>
<%@include file="/commons/include/get.jsp"%>
<script type="text/javascript">
	
</script>

</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">任务列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
					</div>
				</div>
			</div>
		</div>
		<div class="panel-body">
			<div class="panel-data">
				<c:set var="checkAll">
					<input type="checkbox" id="chkall" />
				</c:set>
				<table class="table-grid">
					<thead>
						<tr>
							<th  width="5%">序号</th>
							<th  width="15%">任务名称</th>
							<th width="40%">任务描述</th>
							<th>表名、索引名</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${jobDetailMapList}" var="jobMap" varStatus="status">
						<tr>
							<td>${status.index +1}</td>
							<td>${jobMap['jobName'] }</td>
							<td >${jobMap['jobDescription'] }</td>
							<td style="padding:0;margin:0">
							<table class="table-detail">
								<thead>
									<tr>
										<td>表名</td>
										<c:if test="${dbType!='mysql' }">
											<td>索引名</td>
										</c:if>
									</tr>
								</thead>
								<c:forEach items="${jobMap['jobDataMapList'] }" var="data">
									<tr>
										<td>${data['tableName']}</td>
										<c:if test="${dbType!='mysql' }">
											<td>${data['indexName']}</td>
										</c:if>
									</tr>
								</c:forEach>
							</table>
							</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<!-- end of panel-body -->
	</div>
	<!-- end of pane-->
</body>
</html>


