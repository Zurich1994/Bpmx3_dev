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
	<script type="text/javascript" src="${ctx}/js/hotent/platform/form/bpmFormDialogCombinate/BpmFormDialogCombinateService.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/form/bpmFormDialogCombinate/EditController.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/form/CommonDialog.js"></script>
	<script type="text/javascript">
		var id="${param.id}";
	</script>
</head>
<body  ng-app="app" ng-controller="EditController">
<form id="frmSubmit">
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
	        <span class="tbar-label">编辑对话框组合</span>
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
					<input type="text" validate="{required:true,maxlength:600}" class="inputText" ng-model="prop.name"/>
				</td>
				<th width="20%">别名: </th>
				<td>
					<input type="text" validate="{required:true,maxlength:600}" class="inputText" ng-model="prop.alias"/>
				</td>
			</tr>
			<tr>
				<th width="20%">宽度: </th>
				<td>
					<input type="text" validate="{required:true,number:true}" class="inputText" ng-model="prop.width"/>
				</td>
				<th width="20%">高度: </th>
				<td>
					<input type="text" validate="{required:true,number:true}" class="inputText" ng-model="prop.height"/>
				</td>
			</tr>
			
			<tr>
				<th width="20%">左边树对话框: </th>
				<td>
					{{prop.treeDialogName}}
					<a href="#" class="link search" ng-click="selectTreeDialog()">选择</a>
				</td>
				<th width="20%">右边列表对话框: </th>
				<td>
					{{prop.listDialogName}}
					<a href="#" class="link search" ng-click="selectListDialog()">选择</a>
				</td>
			</tr>
			
			<tr ng-if="param.treeDialog&&param.listDialog">
				<th width="20%">组合规则: </th>
				<td colspan="3">
					<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<th>
								树返回字段:
								<select ng-model="param.treeColumn" ng-options="m as m.comment for m in param.treeDialog.resultfield"></select>
							</th>
							<th>
								列表条件字段:
								<select ng-model="param.listColumn" ng-options="m as m.comment for m in param.listDialog.conditionfield"></select>
							</th>
							<th>
								<a href="#" class="link add" ng-click="createField()">添加</a>
							</th>
						</tr>
						<tr style="height: 35px" ng-repeat="item in prop.field">
							<td>{{item.tree.comment}}</td>
							<td>{{item.list.comment}}</td>
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
