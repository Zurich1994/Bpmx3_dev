<%--
	time:2012-02-22 16:58:15
	desc:edit the 班次时间
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 班次时间</title>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=workTime"></script>
	<script type="text/javascript">
		$(function() {
			function showRequest(formData, jqForm, options) { 
				return true;
			} 
			valid(showRequest,showResponse);
			$("a.save").click(function() {
				$('#workTimeForm').submit(); 
			});
		});
	</script>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
			    <c:choose>
			        <c:when test="${workTime.id !=null}">
			            <span class="tbar-label">编辑班次时间</span>
			        </c:when>
			        <c:otherwise>
			            <span class="tbar-label">添加班次时间</span>
			        </c:otherwise>
			    </c:choose>
				
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
					<form id="workTimeForm" method="post" action="save.ht">
						<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
							<tr>
								<th width="20%">设置ID: </th>
								<td><input type="text" id="settingId" name="settingId" value="${workTime.settingId}"  class="inputText"/></td>
							</tr>
							<tr>
								<th width="20%">开始时间: </th>
								<td><input type="text" id="startTime" name="startTime" value="${workTime.startTime}"  class="inputText"/></td>
							</tr>
							<tr>
								<th width="20%">结束时间: </th>
								<td><input type="text" id="endTime" name="endTime" value="${workTime.endTime}"  class="inputText"/></td>
							</tr>
							<tr>
								<th width="20%">备注: </th>
								<td><input type="text" id="memo" name="memo" value="${workTime.memo}"  class="inputText"/></td>
							</tr>
						</table>
						<input type="hidden" name="id" value="${workTime.id}" />
					</form>
				</div>
		</div>
</div>
</body>
</html>
