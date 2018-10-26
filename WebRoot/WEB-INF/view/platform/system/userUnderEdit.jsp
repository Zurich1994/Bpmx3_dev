<%--
	time:2012-07-05 10:08:09
	desc:edit the 下属管理
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 下属管理</title>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=userUnder"></script>
	<script type="text/javascript">
		$(function() {
			function showRequest(formData, jqForm, options) { 
				return true;
			} 
			valid(showRequest,showResponse);
			$("a.save").click(function() {
				$('#userUnderForm').submit(); 
			});
		});
	</script>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">编辑下属管理</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link save" id="dataFormSave" href="javascript:;"><span></span>保存</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link back" href="list.ht"><span></span>返回</a></div>
				</div>
			</div>
		</div>
		<div class="panel-body">
			<div class="panel-detail">
				<form id="userUnderForm" method="post" action="save.ht">
					<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<th width="20%">用户ID: </th>
							<td><input type="text" id="userid" name="userid" value="${userUnder.userid}"  class="inputText"/></td>
						</tr>
						<tr>
							<th width="20%">下属用户ID: </th>
							<td><input type="text" id="underuserid" name="underuserid" value="${userUnder.underuserid}"  class="inputText"/></td>
						</tr>
						<tr>
							<th width="20%">下属用户名: </th>
							<td><input type="text" id="underusername" name="underusername" value="${userUnder.underusername}"  class="inputText"/></td>
						</tr>
					</table>
					<input type="hidden" name="id" value="${userUnder.id}" />
				</form>
			</div>
		</div>
</div>
</body>
</html>
