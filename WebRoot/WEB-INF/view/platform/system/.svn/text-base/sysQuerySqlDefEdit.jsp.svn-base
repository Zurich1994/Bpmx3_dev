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
<body ng-controller="sysQuerySqlDefCtrl">
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
										<input type="text" ng-model="sysQuerySql.name" validate="{required:true}" class="ht-input" />
									</td>
									<th width="20%">别名：</th>
									<td >
										<input type="text" ng-model="sysQuerySql.alias" validate="{required:true}" class="ht-input" />
									</td>
								</tr>
								<tr>
									<th width="20%">数据源:</th>
									<td>
										<select ng-model="sysQuerySql.dsname" ng-options="c.alias as c.name for c in dsList" class="ht-input">
											<option value="">请选择</option>
										</select>
									</td>
									<th width="20%">分类：</th>
									<td >
										<select ng-model="sysQuerySql.categoryid" ng-options="c.typeId as c.typeName for c in globalTypeCATFORMList" class="ht-input">
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
											rows="12" style="width: 80%;resize: none" validate="{required:true}"></textarea>
										<textarea ng-if="!sysQuerySql.id" ng-model="sysQuerySql.sql"
											rows="12" style="width: 80%;resize: none" validate="{required:true}"></textarea>
										<br>
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
									<a class="link del " ng-click="service.delUrl(sysQuerySql.buttonDef);">
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
									<th width="10%">名称</th>
									<th width="10%">行内按钮</th>
									<th width="15%">url路径
										<div class="tipbox">
											<a href="javascript:;" class="tipinfo">
												<span>URL路径可以使用列表的字段对象，例如：/platform/system/sysQueryView.ht?userid={USERID}&staus={STATUS},这里可以使用多个参数,每个参数使用大括号将字段名括起来。</span>
											</a>
										</div>
									</th>
									<th width="10%">管理</th>
								</tr>
							</thead>
							<tbody>
								<tr ng-repeat="sq in sysQuerySql.buttonDef track by $index"  ng-class="{even:$index%2==0,odd:$index%2!=0}">
									<td >
										<input class="pk" type="checkbox" ng-model="sq.checked" ></td>
									<td>
										<input type="text" class="ht-input w100" ng-model="sq.name" validate="{required:true}">
									</td>
									<td >
										<input ng-model="sq.inRow" type="checkbox" ng-if="sq.inRow == 0" ng-true-value=1 ng-false-value=0 />
										<input ng-model="sq.inRow" type="checkbox" ng-if="sq.inRow == 1" ng-true-value=1 ng-false-value=0 ng-checked="true" />
									</td>
									<td>
										<input type="text" class="ht-input w100" ng-model="sq.urlPath" validate="{required:true}"></td>
									<td>
										<a class="link del" href="javascript:;" ng-click="service.util.delTr(sysQuerySql.buttonDef,$index)"></a>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<!-- 设置url  end-->
					<!-- 设置field start -->
					<div tabid="fieldSetting" id="table" title="设置字段" ng-if="sysQuerySql.id">
						<table cellpadding="1" cellspacing="1" class="table-detail fieldSetting">
							<thead>
								<tr>
									<th width="1%">序号</th>
									<th width="2%">列名</th>
									<th width="2%">类型</th>
									<th width="5%">实际列名</th>
									<th width="10%">描叙</th>
									<th width="5%">控件类型</th>
									<th width="7%">
									
										<span>URL</span>
										<span style="position: relative;">
											<div class="tipbox" style="position: absolute;  margin: -2px 0px;"> 
												<a href="javascript:;" class="tipinfo">
													<span>url 写法规则如下/platform/system/sysQueryView.ht?USERID={USERID},当前字段为USERID使用大括号括起来。</span>
												</a>
											</div>
										</span>
										
									</th>
									<th width="3%">
										<span>是否显示</span>
										<span style="position: relative;">
											<input type="checkbox" ng-model="isAllShow"  style="position: absolute;  margin: 2px 5px;"
												ng-click="service.changeCheckBox(sysQueryFields,'isShow',isAllShow)"/>
										</span>
									</th>
									<th width="3%">
										<span>是否查询</span>
										<span style="position: relative;">
											<input type="checkbox" ng-model="isAllSearch"  style="position: absolute;  margin: 2px 5px;"
												ng-click="service.changeCheckBox(sysQueryFields,'isSearch',isAllSearch)"/>
										</span>
									</th>
									<th width="3%">虚拟列
									</th>
									<th width="3%">管理</th>
								</tr>
							</thead>
							<tr ng-repeat="field in sysQueryFields" ng-class="{even:$index%2==0,odd:$index%2!=0}">
								<td  class="sequence" ng-class="{virtual_field:field.isVirtual==1}">{{$index+1}}</td>
								<td >{{field.name }}</td>
								<td >
									{{field.dataType}}
								</td>
								<td class="padding-none">
									<span ng-if="field.isVirtual==1">{{field.fieldName}}</span>
									<textarea ng-if="field.isVirtual==0" type="text" ng-model="field.fieldName" style="padding: 15px 0 0 0px;text-align: center;resize: none" class="w100 h100 border-none margin-none "></textarea>
								</td>
								<td width="10%" class="padding-none">
									<textarea type="text" ng-model="field.fieldDesc" style="padding: 15px 0 0 0px;text-align: center;resize: none" class="w100 h100 border-none margin-none "></textarea>
								</td>
								<td>
									{{service.getControlType(field.controlType,field.dataType)}}
								</td>
								<td class="padding-none">
									<input type="text" ng-model="field.url" class="ht-input" />
								</td>
								<td >
									<input ng-model="field.isShow" ng-if="field.isShow==0"  type="checkbox" ng-true-value=1 ng-false-value=0 />
									<input ng-model="field.isShow" ng-if="field.isShow==1"  type="checkbox" ng-true-value=1 ng-false-value=0 ng-checked="true"/>
								</td>
								<td  >
									<input ng-model="field.isSearch" type="checkbox" ng-if="field.isSearch==0&&field.isVirtual==0" ng-true-value=1 ng-false-value=0 />
									<input ng-model="field.isSearch" type="checkbox" ng-if="field.isSearch==1&&field.isVirtual==0" ng-true-value=1 ng-false-value=0 ng-checked="true" />
								</td>
								<td >
									<span style='color:red;cursor: auto;' ng-if="field.isVirtual==0">否</span>
									<span style='color:green;cursor: auto;' ng-if="field.isVirtual==1">是</span>
								</td>
								<td >
									<div >
										<span class="plus_button_info pull-left" style="margin-left:5px;"
											ng-if="field.isVirtual!=1" ng-click="service.ctrlTypeSetting(field,'sysQueryMetaField')" title="控件类型设置">控</span>
										<span class=" pull-left" ng-class="{plus_button_gray:!field.alarmSetting,plus_button_warning:field.alarmSetting}" style="margin-left:5px;"
											 ng-click="service.alarmSetting(field,'sysQueryMetaField')" title="报警与格式化设置">报</span>
										<span class="plus_button_success pull-left" style="margin-left:5px;"
											 ng-if="!service.hasVirtual(field)" ng-click="service.virtualSetting(field,'sysQueryMetaField',$index)" title="虚拟字段设置">虚</span>
										<span class="pull-left plus_button_danger" style="margin-left:5px;"
											ng-if="field.isVirtual==1" ng-click="service.util.delTr(sysQueryFields,$index)" title="删除">X</span>
									</div>
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