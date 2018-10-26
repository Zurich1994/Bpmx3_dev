<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 桌面栏目管理表</title>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=desktopLayoutcol"></script>
	<script type="text/javascript">
		$(function() {
			function showRequest(formData, jqForm, options) { 
				return true;
			} 
			valid(showRequest,showResponse);
			$("a.save").click(function() {
				$('#desktopLayoutcolForm').submit(); 
			});
		});
	</script>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
			    <c:choose>
			        <c:when test="${desktopLayoutcol.id !=null }">
			            <span class="tbar-label">编辑桌面栏目管理表</span>
			        </c:when>
			        <c:otherwise>
			            <span class="tbar-label">添加桌面栏目管理表</span>
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
				<form id="desktopLayoutcolForm" method="post" action="save.ht">
					<div class="panel-detail">
						<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
							<tr>
								<th width="20%">栏目ID:</th>
								<td>
									<select name="columnId" style="width: 20%;">
										<c:forEach items="${desktopColumnmap}" var="t">
										    <option value="${t.key}"  <c:if test="${t.key==desktopLayoutcol.columnId}">selected='selected'</c:if>>${t.value}</option>
									    </c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<th width="20%">布局ID: </th>
								<td>
									<select name="layoutId" style="width: 20%;">
										<c:forEach items="${desktopLayoutmap}" var="t">
										    <option value="${t.key}"  <c:if test="${t.key==desktopLayoutcol.layoutId}">selected='selected'</c:if>>${t.value}</option>
									    </c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<th width="20%">列: </th>
								<td><input type="text" id="col" name="col" value="${desktopLayoutcol.col}"  class="inputText"/></td>
							</tr>
							<tr>
								<th width="20%">序号: </th>
								<td><input type="text" id="sn" name="sn" value="${desktopLayoutcol.sn}"  class="inputText"/></td>
							</tr>
						</table>
						<input type="hidden" name="id" value="${desktopLayoutcol.id}" />
					</div>
				</form>
		</div>
</div>
</body>
</html>
