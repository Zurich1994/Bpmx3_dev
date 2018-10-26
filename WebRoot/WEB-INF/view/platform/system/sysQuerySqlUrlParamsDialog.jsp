<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html ng-app="sysQuerySqlApp">
<head>
	<title>url参数设置</title>
	<%@include file="/commons/include/form.jsp"%>
	<script type="text/javascript" src="${ctx}/js/lang/view/platform/form/zh_CN.js"></script>
	<link href="${ctx}/styles/default/css/hotent/dataRights.css" rel="stylesheet" />
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript" src="${ctx}/js/angular/angular.min.js"></script>
	<script type="text/javascript"src="${ctx}/js/angular/service/baseServices.js"></script>
	<script type="text/javascript" src="${ctx}/js/angular/module/DataRightsApp.js"></script>
	<script type="text/javascript" src="${ctx}/js/angular/controller/sysQuerySqlController.js"></script>
	<script type="text/javascript" src="${ctx}/js/angular/service/sysQuerySqlService.js"></script>
</head>
<body ng-controller="sysQuerySqlUrlCtrl">
	<div class="panel">
		<div class="panel-top">
			<div class="panel-top">
				<div class="tbar-title">
					<span class="tbar-label">url参数设置</span>
				</div>
				<div class="panel-toolbar">
					<div class="toolBar">
						<div class="group">
							<a class="link save" ng-click="save()" href="javascript:;">
								<span></span>保存
							</a>
						</div>
						<div class="group">
							<a class="link close" href="javascript:;" ng-click="close()">
								<span></span>关闭
							</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="panel-body">
			<form id="urlParamsForm" method="post" action="save.ht">
				<!-- 设置按钮  start-->
				<div tabid="urlParamsSetting" id="table" title="设置按钮">
					<div class="table-top-left">
						<div class="toolBar" style="margin: 0;">
							<div class="group">
								<a class="link add" ng-click="add()">
									<span></span>
									添加
								</a>
							</div>
							<div class="l-bar-separator"></div>
							<div class="group">
								<a class="link del " ng-click="service.delUrl(urlParams);">
									<span></span>
									删除
								</a>
							</div>
						</div>
					</div>
					<br/>
					<table id="urlParamsTbl" class="table-grid fieldSetting">
						<thead>
							<tr>
								<th width="5%">选择</th>
								<th width="5%">自定义参数</th>
								<th width="15%">key鍵</th>
								<th width="10%">value值</th>
								<th width="5%">管理</th>
							</tr>
						</thead>
						<tbody>
							<tr var="urlParamsTr" ng-repeat="u in urlParams">
								<td  ng-click="u.checked=!u.checked">
									<input class="pk" type="checkbox" ng-model="u.checked"></td>
								<td >
									<input type="checkbox" ng-model="u.isCustomParam"  ng-true-value=1  ng-false-value=0></td>
								<td>
									<input type="text" ng-model="u.key" class="ht-input"></td>
								<td>
									<input type="text" ng-model="u.value"  class="ht-input" ng-if="u.isCustomParam==1"/>
									<select ng-model="u.value"  class="ht-input" ng-options="c.name as c.name for c in sysQueryFields" ng-if="u.isCustomParam!=1">
										<option value="" >请选择</option>
									</select>
								</td>
								<td>
									<a class="link del" href="javascript:;" ng-click="service.util.delTr(urlParams,$index)"></a>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</form>
		</div>
	</div>
</body>
</html>