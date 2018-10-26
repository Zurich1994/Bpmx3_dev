
<%--
	time:2013-11-27 10:19:23
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>SYS_USER_POS明细</title>
<%@include file="/commons/include/get.jsp"%>
<script type="text/javascript">
	//放置脚本
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">SYS_USER_POS详细信息</span>
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
				<th width="20%">ORGID:</th>
				<td>${userPosition.orgid}</td>
			</tr>
			<tr>
				<th width="20%">POSID:</th>
				<td>${userPosition.posid}</td>
			</tr>
			<tr>
				<th width="20%">USERID:</th>
				<td>${userPosition.userid}</td>
			</tr>
			<tr>
				<th width="20%">ISPRIMARY:</th>
				<td>${userPosition.isprimary}</td>
			</tr>
			<tr>
				<th width="20%">ISCHARGE:</th>
				<td>${userPosition.ischarge}</td>
			</tr>
			<tr>
				<th width="20%">ISDELETE:</th>
				<td>${userPosition.isdelete}</td>
			</tr>
		</table>
		</div>
	</div>
</body>
</html>

