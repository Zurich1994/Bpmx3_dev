<%--
	time:2015-04-09 17:19:23
	desc:edit the 对象权限表
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<%@ taglib prefix="ht" tagdir="/WEB-INF/tags/wf"%>
<html>
<head>
	<title>树形对话框和表单组合设置</title>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript" src="${ctx}/js/angular/angular.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/angular/service/baseServices.js"></script>
	<script type="text/javascript" src="${ctx}/js/angular/service/arrayToolService.js"></script>
	<script type="text/javascript" src="${ctx}/js/angular/service/commonListService.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/form/formDefCombinate/FormDefCombinateService.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/form/bpmFormDialogCombinate/BpmFormDialogCombinateService.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/form/formDefCombinate/EditController.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/form/CommonDialog.js"></script>
	<script type="text/javascript">
		var id="${param.id}";	
	
		$(function() {
			$("#name").blur(function () {
				Share.setPingyin($(this),$("#alias"));
			});
		});
	</script>
</head>
<body  ng-app="app" ng-controller="EditController">
<form id="frmSubmit">
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
	        <span class="tbar-label">树形对话框和表单组合设置</span>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" ng-click="save()"><span></span>保存</a></div>
				<div class="group"><a class="link back" href="list.ht"><span></span>返回</a></div>
			</div>
		</div>
	</div>
	<div class="panel-body">
		<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
			<tr>
				<th width="20%">名称: </th>
				<td>
					<input type="text" id="name" validate="{required:true,maxlength:600}" class="inputText" ng-model="prop.name"/>
				</td>
				<th width="20%">别名: </th>
				<td>
					<input type="text" id="alias" validate="{required:true,maxlength:600}" class="inputText" ng-model="prop.alias"/>
				</td>
			</tr>
			
			<tr>
				<th width="20%">左边树对话框: </th>
				<td>
					{{prop.treeDialogName}}
					<a href="#" class="link search" ng-click="selectTreeDialog()">选择</a>
				</td>
				<th width="20%">表单: </th>
				<td>
					{{prop.formDefName}}
					<a href="#" class="link search" ng-click="selectFormDef()">选择</a>
				</td>
			</tr>
			
			<tr ng-if="param.treeDialog&&param.dataTemplate">
				<th width="20%">组合规则: </th>
				<td colspan="3">
					<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<th> 
								树返回字段:
								<select ng-model="param.treeColumn" ng-options="m as m.comment for m in param.treeDialog.returnList"></select>
							</th>
							<th>
								业务数据模板条件字段:
								<select ng-model="param.templateColumn" ng-options="m as m.cm for m in param.dataTemplate"></select>
							</th>
							<th>
								<a href="#" class="link add" ng-click="createField()">添加</a>
							</th>
						</tr>
						<tr style="height: 35px" ng-repeat="item in prop.field">
							<td>{{item.tree.comment}}</td>
							<td>{{item.temp.cm}}</td>
							<td>
								<a href="#" class="link del" ng-click="ArrayTool.del($index,prop.field)">删除</a>
							</td>
						</tr>
					</table>
				</td>				
			</tr>
			
		</table>
	</div>
</div>
</form>
</body>
</html>
