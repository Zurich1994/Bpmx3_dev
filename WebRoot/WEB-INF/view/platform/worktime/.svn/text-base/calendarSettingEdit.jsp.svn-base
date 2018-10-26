<%--
	time:2012-02-20 14:57:32
	desc:edit the 日历设置
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 日历设置</title>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=calendarSetting"></script>
	<script type="text/javascript">
		$(function() {
			function showRequest(formData, jqForm, options) { 
				return true;
			} 
			valid(showRequest,showResponse);
			$("a.save").click(function() {
				$('#calendarSettingForm').submit(); 
			});
		});
	</script>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
			    <c:choose>
			        <c:when test="${calendarSetting.id !=null }">
			            <span class="tbar-label">编辑日历设置</span>
			        </c:when>
			        <c:otherwise>
			            <span class="tbar-label">添加日历设置</span>
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
				<form id="calendarSettingForm" method="post" action="save.ht">
					<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<th width="20%">日历ID: </th>
							<td><input type="text" id="calendarId" name="calendarId" value="${calendarSetting.calendarId}"  class="inputText"/></td>
						</tr>
						<tr>
							<th width="20%">年份: </th>
							<td><input type="text" id="years" name="years" value="${calendarSetting.years}"  class="inputText"/></td>
						</tr>
						<tr>
							<th width="20%">月份: </th>
							<td><input type="text" id="months" name="months" value="${calendarSetting.months}"  class="inputText"/></td>
						</tr>
						<tr>
							<th width="20%">1号: </th>
							<td><input type="text" id="days" name="days" value="${calendarSetting.days}"  class="inputText"/></td>
						</tr>
						<tr>
							<th width="20%">上班类型: </th>
							<td><input type="text" id="type" name="type" value="${calendarSetting.type}"  class="inputText"/></td>
						</tr>
						<tr>
							<th width="20%">workTimeId: </th>
							<td><input type="text" id="workTimeId" name="workTimeId" value="${calendarSetting.workTimeId}"  class="inputText"/></td>
						</tr>
					</table>
					<input type="hidden" name="id" value="${calendarSetting.id}" />
				</form>
			</div>
		</div>
</div>
</body>
</html>
