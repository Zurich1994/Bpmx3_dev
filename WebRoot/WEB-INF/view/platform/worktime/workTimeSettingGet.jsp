<%--
	time:2012-02-20 14:57:31
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>班次设置明细</title>
	<%@include file="/commons/include/getById.jsp" %>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">班次设置详细信息</span>
			</div>
			<c:if test="${canReturn==0}">
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link back" href="list.ht"><span></span>返回</a></div>
				</div>
			</div>
			</c:if>
		</div>
		<div class="panel-body">
			<div class="panel-detail">
				<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
					<tr>
						<th width="20%">班次名:</th>
						<td>${workTimeSetting.name}</td>
					</tr>
					<tr>
						<th width="20%">描述:</th>
						<td>${workTimeSetting.memo}</td>
					</tr>
				</table>
					
				<br>
				
				<div class="tbar-title">
					<span class="tbar-label">班次时间</span>
				</div>
				
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
									${workTimeItem.startTime}
								</td>
								<td style="text-align: center;">
									${workTimeItem.endTime}
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
