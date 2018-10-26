
<%--
	time:2015-05-26 10:07:59
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>考勤组明细明细</title>
<%@include file="/commons/include/get.jsp"%>
<script type="text/javascript">
	//放置脚本
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">考勤组明细详细信息</span>
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
				<th width="20%">考勤组:</th>
				<td>${atsAttenceGroupDetail.groupId}</td>
			</tr>
			<tr>
				<th width="20%">考勤档案:</th>
				<td>${atsAttenceGroupDetail.fileId}</td>
			</tr>
		</table>
		</div>
	</div>
</body>
</html>

