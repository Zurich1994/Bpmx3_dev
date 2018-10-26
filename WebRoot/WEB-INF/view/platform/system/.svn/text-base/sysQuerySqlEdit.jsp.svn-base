<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html ng-app="sysQuerySqlApp">
<head>
	<title>数据模板设置</title>
	<%@include file="/commons/include/form.jsp"%>
	<script type="text/javascript" src="${ctx}/js/lang/view/platform/form/zh_CN.js"></script>
	<link href="${ctx}/styles/default/css/hotent/dataRights.css" rel="stylesheet" />
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript" src="${ctx}/js/angular/angular.min.js"></script>
	<script type="text/javascript"src="${ctx}/js/angular/service/baseServices.js"></script>
	<script type="text/javascript" src="${ctx}/js/angular/module/DataRightsApp.js"></script>
	<script type="text/javascript" src="${ctx}/js/angular/service/sysQuerySqlService.js"></script>
	<script type="text/javascript" src="${ctx}/js/angular/controller/sysQuerySqlController.js"></script>
	<script>
		var dsList = ${dsList};
		var sysQuerySql = ${sysQuerySql};
		var globalTypeCATFORMList = ${globalTypeCATFORMList};
		var globalTypesDICList = ${globalTypesDICList};
		var sysQueryFields = ${sysQueryFields};
	</script>
</head>
<body ng-controller="sysQuerySqlCtrl">
	<div class="panel" ng-show="hasInitTab">
		<div class="hide-panel">
			<div class="panel-top">
				<div class="panel-top">
					<div class="tbar-title">
						<span class="tbar-label">数据模板设置</span>
					</div>
					<div class="panel-toolbar">
						<div class="toolBar">
							<div class="group">
								<a class="link save" ng-click="save()" href="javascript:;">
									<span></span>
									保存
								</a>
							</div>
							<div class="group">
								<a class="link back" href="list.ht">
									<span></span>
									返回
								</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="panel-body">
			<form id="sysQuerySqlForm">
				<div id="tab">
					<!-- 基本信息  start-->
					<div tabid="baseSetting" id="table" title="基本信息">
						<div>
							<div class="tbar-title">
								<span class="tbar-label">基本信息</span>
							</div>
							<table class="table-detail" cellpadding="0" cellspacing="0" border="0" type="main" style="border-width: 0 !important;">
								<tr>
									<th width="20%">
										名称：
										<span class="required">*</span>
									</th>
									<td>
										<input type="text" ng-model="sysQuerySql.name" validate="{required:true}" class="inputText" />
									</td>
									<th width="20%">数据源:</th>
									<td>
										<select ng-model="sysQuerySql.dsalias" ng-options="c.alias as c.name for c in dsList">
											<option value="">请选择</option>
										</select>
									</td>
								</tr>
								<tr>
									<th width="20%">分类：</th>
									<td colspan="3">
										<select id="categoryId" ng-model="sysQuerySql.categoryId" ng-options="c.typeId as c.typeName for c in globalTypeList">
											<option value="">无</option>
										</select>
									</td>
								</tr>
								<tr>
									<th width="20%">
										SQL语句：
										<span class="required">*</span>
									</th>
									<td colspan="3" valign="top">
										<textarea ng-if="sysQuerySql.id" disabled="disabled" ng-model="sysQuerySql.sql"
											rows="12" style="width: 80%" validate="{required:true}"></textarea>
										<textarea ng-if="!sysQuerySql.id" ng-model="sysQuerySql.sql"
											rows="12" style="width: 80%" validate="{required:true}"></textarea>
										<a class="button" ng-click="service.validSql(true)">
											<span class="icon valid"></span>
											<span>验证查询语句</span>
										</a>
									</td>
								</tr>
							</table>
						</div>
					</div>
					<!-- 基本信息  end-->
					<!-- 设置按钮  start-->
					<div tabid="urlSetting" id="table" title="设置按钮" ng-if="sysQuerySql.id">
						<div class="table-top-left">
							<div class="toolBar" style="margin: 0;">
								<div class="group">
									<a class="link add" ng-click="service.addUrl()">
										<span></span>
										添加
									</a>
								</div>
								<div class="l-bar-separator"></div>
								<div class="group">
									<a class="link del " ng-click="service.delUrl(sysQuerySql.urlParams);">
										<span></span>
										删除
									</a>
								</div>
							</div>
						</div>
						<table class="table-grid fieldSetting">
							<thead>
								<tr>
									<th width="5%">选择</th>
									<th width="15%">名称</th>
									<th width="10%">显示名称</th>
									<th>url路径</th>
									<th width="10%">管理</th>
								</tr>
							</thead>
							<tbody>
								<tr ng-repeat="sq in sysQuerySql.urlParams" ng-click="sq.checked = !sq.checked" ng-class="{even:$index%2==0,odd:$index%2!=0}">
									<td var="index">
										<input class="pk" type="checkbox" ng-model="sq.checked"></td>
									<td>
										<input type="text" class="ht-input" ng-model="sq.name" validate="{required:true}"></td>
									<td>
										<input type="text" class="ht-input" ng-model="sq.desc" validate="{required:true}"></td>
									<td>
										<input type="text" class="ht-input w100" ng-model="sq.urlPath" validate="{required:true}"></td>
									<td>
										<a class="link moveup" href="javascript:;" ng-click="service.util.moveTr(sysQuerySql.urlParams,$index,true)"></a>
										<a class="link movedown" href="javascript:;" ng-click="service.util.moveTr(sysQuerySql.urlParams,$index,false)"></a>
										<a class="link del" href="javascript:;" ng-click="service.util.delTr(sysQuerySql.urlParams,$index)"></a>
										<a class="link edit" href="javascript:;" ng-click="service.addOrEditUrlParamsDialog(sq)"></a>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<!-- 设置url  end-->
					<!-- 设置field start -->
					<div tabid="fieldSetting" id="table" title="设置字段" ng-if="sysQuerySql.id">
						<table cellpadding="1" cellspacing="1" class="table-detail fieldSetting">
							<tr>
									<th width="5%">序号</th>
									<th width="10%">列名</th>
									<th width="5%">类型</th>
									<th width="10%">描叙</th>
									<th width="5%">控件类型</th>
									<th width="5%">是否显示
										<input type="checkbox" ng-model="isAllShow" ng-click="service.changeCheckBox(sysQueryFields,'isShow',isAllShow)"/>
									</th>
									<th width="5%">是否查询
										<input type="checkbox" ng-model="isAllSearch" ng-click="service.changeCheckBox(sysQueryFields,'isSearch',isAllSearch)"/>
									</th>
									<th width="5%">管理</th>
								</tr>
							<tr ng-repeat="field in sysQueryFields" ng-class="{even:$index%2==0,odd:$index%2!=0}">
								<td width="5%" class="sequence">{{$index+1}}</td>
								<td width="10%">{{field.name }}</td>
								<td width="5%" >{{field.type }}</td>
								<td width="10%" class="padding-none">
									<textarea type="text" style="padding: 15px 0 0 0px;text-align: center;resize: none" ng-model="field.fieldDesc" class="w100 h100 border-none margin-none "></textarea>
								</td>
								<td>
									{{service.getControlType(field.controlType)}}
								</td>
								<td width="1%" >
									<input ng-model="field.isShow" ng-if="field.isShow==0"  type="checkbox" ng-true-value=1 ng-false-value=0 />
									<input ng-model="field.isShow" ng-if="field.isShow==1"  type="checkbox" ng-true-value=1 ng-false-value=0 ng-checked="true"/>
								</td>
								<td width="1%" >
									<input ng-model="field.isSearch" type="checkbox" ng-if="field.isSearch==0" ng-true-value=1 ng-false-value=0 />
									<input ng-model="field.isSearch" type="checkbox" ng-if="field.isSearch==1" ng-true-value=1 ng-false-value=0 ng-checked="true" />
								</td>
								<td width="5%">
									<a href="javascript:;" ng-click="service.ctrlTypeSetting(field,'sysQueryField')">控件与格式化</a>
								</td>
							</tr>
						</table>
					</div>
					<!-- 设置field end -->
				</div>
			</form>
		</div>
	</div>
</body>
</html>