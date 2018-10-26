<%--
	time:2012-08-29 11:26:00
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>电子印章明细</title>
	<%@include file="/commons/include/getById.jsp" %>
	<%@include file="/commons/include/get.jsp" %>
	<script type="text/javascript" src="${ctx}/js/ntkosign/NtkoSignManage.js"></script>
	<style type="text/css">
		.displaynone{
			display:none;
		}
	</style>
	<script type="text/javascript">
		$(function(){
			$('body').addClass("displaynone");
			var nktoSignManage=new NtkoSignManage();
			var fileId = '${seal.attachmentId}';
			nktoSignManage.load('divSeal',fileId);
			$('body').removeClass("displaynone");
		});
	</script>
</head>
<body>
<div class="panel">
<div class="hide-panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">电子印章详细信息</span>
			</div>
			<c:if test="${canReturn==0}">
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link back" href="list.ht"><span></span>返回</a></div>
				</div>
			</div>
			</c:if>
		</div>
		</div>
		<div class="panel-body">
			<div class="panel-detail">
				<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
					<tr>
						<th width="20%">印章名:</th>
						<td>${seal.sealName}</td>
					</tr>
					<tr>
						<th width="20%">印章持有者ID:</th>
						<td>${seal.belongId}</td>
					</tr>
					<tr>
						<th width="20%">印章所属单位或个人:</th>
						<td>${seal.belongName}</td>
					</tr>
					<tr>
						<th width="20%">印章：</th>
						<td><div id="divSeal"></div></td>
					<tr>
				</table>
			</div>
		</div>
</div>

</body>
</html>
