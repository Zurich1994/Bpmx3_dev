<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>文件下载操作管理</title>
<%@include file="/commons/include/get.jsp"%>
<script type="text/javascript">
	
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">文件下载操作管理</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link search" id="btnSearch"><span></span>查询</a>
					</div>
				</div>
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="downloadOperList.ht">
					<ul class="row">
						<li><span class="label">文件名:</span><input type="text"
							name="Q_fileName_SL" class="inputText"
							value="${param['Q_fileName_SL']}" /></li>
						<li><span class="label">下载者:</span><input type="text"
							name="Q_operation_SL" class="inputText"
							value="${param['Q_operation_SL']}" />
						</li>
						<li><span class="label">下载者部门:</span><input type="text"
							name="Q_operationDept_SL" class="inputText"
							value="${param['Q_operationDept_SL']}" />
						</li>
						<div class="row_date">
							<li><span class="label">下载时间从:</span><input type="text"
								name="Q_beginoperateTime_DL" class="inputText date"
								value="${param['Q_beginoperateTime_DL']}" />
							</li>
							<li><span class="label">至: </span><input type="text"
								name="Q_endoperateTime_DG" class="inputText date"
								value="${param['Q_endoperateTime_DG']}" />
							</li>
						</div>
					</ul>
				</form>
			</div>
		</div>
		<div class="panel-body">
			<display:table name="fileManageLogList" id="fileManageLogItem"
				requestURI="downloadOperList.ht" sort="external" cellpadding="1"
				cellspacing="1" class="table-grid">
				<display:column property="categoryName" title="分类"></display:column>
				<display:column property="fileName" title="文件名" sortable="true"
					sortName="fileName"></display:column>
				<display:column property="operation" title="下载者"
					sortName="operation" sortable="true"></display:column>
				<display:column property="operationDept" title="下载者部门"
					sortable="true" sortName="operationDept"></display:column>
				<display:column title="最后下载时间" sortable="true" sortName="operateTime">
					<fmt:formatDate value="${fileManageLogItem.operateTime}"
						pattern="yyyy-MM-dd HH:mm:ss" />
				</display:column>
				<display:column property="operateNum" title="下载次数" sortable="true"
					sortName="operateNum"></display:column>
			</display:table>
			<hotent:paging tableId="fileManageLogItem" />
		</div>
		<!-- end of panel-body -->
	</div>
	<!-- end of panel -->
</body>
</html>


