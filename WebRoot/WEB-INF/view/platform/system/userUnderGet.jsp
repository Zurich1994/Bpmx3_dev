<%--
	time:2012-07-05 10:08:09
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>下属管理明细</title>
	<%@include file="/commons/include/getById.jsp" %>
	<script type="text/javascript">
		//放置脚本
	</script>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">下属管理详细信息</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="back bar-button" href="list.ht"><span></span>返回</a></div>
				</div>
			</div>
		</div>
		<div class="panel-body">
				<div class="panel-detail">
					<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<th width="20%">用户ID:</th>
							<td>${userUnder.userid}</td>
						</tr>
						<tr>
							<th width="20%">下属用户ID:</th>
							<td>${userUnder.underuserid}</td>
						</tr>
						<tr>
							<th width="20%">下属用户名:</th>
							<td>${userUnder.underusername}</td>
						</tr>
					</table>
				</div>
		</div>
</div>

</body>
</html>
