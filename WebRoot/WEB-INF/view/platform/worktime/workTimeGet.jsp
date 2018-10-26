<%--
	time:2012-02-22 16:58:15
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>班次时间明细</title>
	<%@include file="/commons/include/getById.jsp" %>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">班次时间详细信息</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link back" href="list.ht"><span></span>返回</a></div>
				</div>
			</div>
		</div>
		<div class="panel-body">
			<div class="panel-detail">
				<input type="hidden" id="settingId" name="settingId" value="${workTime.settingId}">
				<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
					<tr>
						<th width="20%">开始时间:</th>
						<td>${workTime.startTime}</td>
					</tr>
					<tr>
						<th width="20%">结束时间:</th>
						<td>${workTime.endTime}</td>
					</tr>
					<tr>
						<th width="20%">备注:</th>
						<td>${workTime.memo}</td>
					</tr>
				</table>
			</div>
				<br>
			<div class="panel-data">
					<table id="workTimeItem" class="table-grid table-list" id="0" cellpadding="1" cellspacing="1">
						<thead>
							<th width="20%">开始时间</th>
							<th width="20%">结束时间</th>
							<th width="50%">描述</th>
						</thead>
						<tbody>
							<c:forEach items="${workTimelist}" var="workTimeItem">
								<tr>
									<td style="text-align: center;">
										<input class="inputText" type="text" name="startTime" 
											style="width: 95%;" value="${workTimeItem.startTime}">
									</td>
									<td style="text-align: center;">
										<input class="inputText" type="text" name="endTime"	
											style="width: 95%;" value="${workTimeItem.endTime}" >
									</td>
									<td style="text-align: center;">
										${workTimeItem.memo}
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
			</div>
		</div>
	</div>
</body>
</html>
