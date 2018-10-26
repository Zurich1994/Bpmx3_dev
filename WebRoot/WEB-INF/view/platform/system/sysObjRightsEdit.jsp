<%--
	time:2015-04-09 17:19:23
	desc:edit the 对象权限表
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<%@ taglib prefix="ht" tagdir="/WEB-INF/tags/wf"%>
<html>
<head>
	<title>编辑 对象权限表</title>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript" src="${ctx}/js/angular/angular.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/angular/service/baseServices.js"></script>
	<script type="text/javascript" src="${ctx}/js/angular/service/arrayToolService.js"></script>
	<script type="text/javascript" src="${ctx}/js/angular/service/commonListService.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/sysObjRights/SysObjRightsService.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/sysObjRights/EditController.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
	<script type="text/javascript">
		var objectId="${param.objectId}";
		var objType="${param.objType}";
		var beanId="${param.beanId}";
	</script>
</head>
<body  ng-app="app" ng-controller="EditController">
<form id="frmSubmit>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
		    <c:choose>
			    <c:when test="${sysObjRights.id !=null}">
			        <span class="tbar-label">编辑对象权限表</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label">添加对象权限表</span>
			    </c:otherwise>			   
		    </c:choose>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" ng-click="save()"><span></span>保存</a></div>
				<div class="group"><a class="link back" href="javaScript:frameElement.dialog.close();"><span></span>关闭</a></div>
			</div>
		</div>
	</div>
	<div class="panel-body">
		<table class="table-detail" cellpadding="0" cellspacing="0" border="0" type="main">
			<tr ng-repeat="item in param.typeList">
				<th width="20%">{{item.value}} </th>
				<td>
					<input ng-if="item.key=='all'" type="checkbox" ng-model="param.selectAll" ng-click="showSeletor(item)"/>
					<span ng-if="item.key!='all'&&!param.selectAll">
						<textarea ng-if="item.key!='script'" readonly="readonly" rows="3" ng-model="item.names"></textarea>
						<textarea ng-if="item.key=='script'" rows="3" ng-model="item.names"></textarea>
						<button ng-if="item.key!='script'" ng-click="showSeletor(item)">选择</button>
					</span>
				</td>
			</tr>
		</table>
	</div>
</div>
</form>
</body>
</html>
