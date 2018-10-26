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
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/sysTransDef/SysTransDefService.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/sysTransDef/EditController.js"></script>
	<script type="text/javascript" src="${ctx}/js/util/sqlUtil.js"></script>
	<script type="text/javascript" src="${ctx}/js/javacode/codemirror.js"></script>
	<script type="text/javascript" src="${ctx}/js/javacode/InitMirror.js"></script>
	<script type="text/javascript">
		var id="${param.id}";
	</script>
</head>
<body  ng-app="app" ng-controller="EditController">
<form id="frmSubmit>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
	        <span class="tbar-label">权限转移</span>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" ng-click="save()"><span></span>保存</a></div>
				<div class="group"><a class="link back" href="list.ht"><span></span>返回</a></div>
			</div>
		</div>
	</div>
	<div class="panel-body">
		<table class="table-detail" cellpadding="0" cellspacing="0" border="0" type="main">
			<tr>
				<th width="20%">名称: </th>
				<td><input ng-model="prop.name" type="text" class="inputText" validate="{required:true,maxlength:64}"/></td>
			</tr>
			<tr>
				<th width="20%">是否启用: </th>
				<td>
					<select class="inputText" ng-model="prop.state" style="width: 70px" ng-options="m.value as m.key for m in CommonList.yesOrNoList2">
					</select>
				</td>
			</tr>
			<tr>
				<th width="20%">常用变量: </th>
				<td>
					<input ng-repeat="field in commonField" type="button" value="{{field.key}}" ng-click="clickField(field)"/>
				</td>
			</tr>
			<tr>
				<th width="20%">SELECTSQL语句: </th>
				<td>
					<textarea id="selectSql" codemirror="true" rows="12" validate="{required:true}"></textarea>
				</td>
			</tr>
			<tr>
				<th width="20%">UPDATESQL语句: </th>
				<td>
					<textarea id="updateSql" codemirror="true" rows="12" validate="{required:true}"></textarea>
				</td>
			</tr>
			<tr>
				<th width="20%">日志内容模板: </th>
				<td>
					<textarea id="logContent" codemirror="true" rows="12" validate="{required:true}"></textarea>
				</td>
			</tr>
		</table>
	</div>
</div>
</form>
</body>
</html>
