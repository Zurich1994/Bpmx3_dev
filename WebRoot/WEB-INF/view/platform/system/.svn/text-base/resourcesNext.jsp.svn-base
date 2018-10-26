<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.hotent.platform.model.system.SysTab"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>我的流程定义列表</title>
<%@include file="/commons/include/get.jsp" %>
	<script type="text/javascript" src="${ctx}/js/util/form.js"></script>
	<script type="text/javascript" src="${ctx}/js/plugins/ligerGrid.js"></script>
	
<style type="text/css">
html,body {
	padding: 0px;
	margin: 0;
	width: 100%;
	height: 100%;
}
</style>
</head>

<body>
		<div class="panel-body">
		 <c:forEach var="resNext" items="${sysTabNext}">
			<div style="width: 90px; margin: 10px 5px; cursor: pointer; padding: 10px 6px; display: inline-block;">
				<div style="height: 64px; text-align: center;" title="${resNext.resName}" onclick="jQuery.openFullWindow('${resNext.defaultUrl}')">
					<img id="iconImg" alt="" src="/bpm33${resNext.icon}" width="64px;" height="64px;">
				</div>
				<div class="title" style="text-align: center;margin-top: 6px; height: 35px; float: left; width: 90px;">${resNext.resName}</div>
			</div>
			</c:forEach>
		</div><!-- end of panel-body -->		
	</div> <!-- end of panel -->

</body>
</html>


