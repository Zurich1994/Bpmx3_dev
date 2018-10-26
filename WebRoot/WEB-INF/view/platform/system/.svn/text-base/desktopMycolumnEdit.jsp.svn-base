<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head> 
	<title>编辑 桌面个人栏目</title>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=desktopMycolumn"></script>
	<script type="text/javascript">
		$(function() {
			function showRequest(formData, jqForm, options) { 
				return true;
			} 
			valid(showRequest,showResponse);
			$("a.save").click(function() {
				$('#desktopMycolumnForm').submit(); 
			});
		});
	</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
			    <c:choose>
			        <c:when test="${desktopMycolumn.id !=null }">
			            <span class="tbar-label">编辑桌面个人栏目</span>
			        </c:when>
			        <c:otherwise>
			            <span class="tbar-label">添加桌面个人栏目</span>
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
			<form id="desktopMycolumnForm" method="post" action="save.ht">
				<div class="panel-detail">
				<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
					<tr>
						<th width="20%">用户ID: </th>
						<td><input type="text" id="userId" name="userId" value="${desktopMycolumn.userId}"  class="inputText"/></td>
					</tr>
					<tr>
						<th width="20%">布局ID: </th>
						<td><input type="text" id="layoutId" name="layoutId" value="${desktopMycolumn.layoutId}"  class="inputText"/></td>
					</tr>
					<tr>
						<th width="20%">栏目ID: </th>
						<td><input type="text" id="columnId" name="columnId" value="${desktopMycolumn.columnId}"  class="inputText"/></td>
					</tr>
					<tr>
						<th width="20%">列: </th>
						<td><input type="text" id="col" name="col" value="${desktopMycolumn.col}"  class="inputText"/></td>
					</tr>
					<tr>
						<th width="20%">序号: </th>
						<td><input type="text" id="sn" name="sn" value="${desktopMycolumn.sn}"  class="inputText"/></td>
					</tr>
				</table>
				</div>
				<input type="hidden" name="id" value="${desktopMycolumn.id}" />
			</form>
		</div>
	</div>
</body>
</html>
