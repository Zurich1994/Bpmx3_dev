<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html ng-app="shareRightsApp">
<head>
<%@include file="/commons/include/customForm.jsp"%>
<script type="text/javascript" src="${ctx}/js/angular/angular.min.js"></script>
<script type="text/javascript"
	src="${ctx}/js/angular/service/baseServices.js"></script>
<script type="text/javascript" src="${ctx}/js/angular/controller/sysShareRightsController.js"></script>
<script type="text/javascript">
	var dialog = frameElement.dialog; 

	var display = '${display}';
	var manager = '${manager}';
	var filter = '${filter}';
	var exports = '${exports}';
	var hasPermission = '${hasPermission}';
</script>
<style>
	
	input[type='checkbox']{
		width: 0px;
		height: 0px;
		display:none;
	}
	input[type='checkbox']:checked~label{
		color: #fff;
		background-color: #5cb85c;
		border-color: #4cae4c;
	}
	input[type='checkbox']:not(:checked)~label{
		background-color: #D3D3D3;
		color: white;
	}
	.label-sm{
		padding: 10px;
	    font-size: 16px;
	    min-width: 30px;
	    border-radius: 8px;
	    margin-left: 5px;
	 }
	 .pull-left {
	   float: left;
	   padding-top: 30px;
  	   height: 40px;
	 }
</style>
</head>
<body ng-controller="shareItemCtrl">
	<div class="panel-top">
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link ok" ng-click="save()"><span></span>确定</a>
					</div>
					<div class="l-bar-separator"></div>
					<div class="group">
						<a class="link del" onclick="dialog.close()" href="javascript:;"><span></span>关闭</a>
					</div>
				</div>
			</div>
	</div>
	<div class="panel-body">
		<div id="tab">
			<div tabid="display" title="显示权限">
				<div class="pull-left" ng-repeat="(k,v) in display" on-finish-render-filters>
					<input type="checkbox" id="d_{{k}}" ng-model="v.r" checked="checked" >
					<label for="d_{{k}}"  class="btn label-sm">{{v.desc}}</label>
				</div>
					<div ng-if="isEmpty(display)">暂无权限可提供共享</div>
			</div>
			<div tabid="manager" title="功能按钮权限">
				<div class="pull-left" ng-repeat="(k,v) in manager" on-finish-render-filters>
					<input type="checkbox" id="m_{{k}}" ng-model="v.r" checked="checked" >
					<label for="m_{{k}}"  class="btn label-sm">{{v.desc}}</label>
				</div>
				<div ng-if="isEmpty(manager)">暂无权限可提供共享</div>
			</div>
			<div tabid="filter" title="过滤权限">
				<div class="pull-left" ng-repeat="(k,v) in filter" on-finish-render-filters>
					<input type="checkbox" id="f_{{k}}" ng-model="v.r" checked="checked" >
					<label for="f_{{k}}"  class="btn label-sm">{{v.desc}}</label>
				</div>
				<div ng-if="isEmpty(filter)">暂无权限可提供共享</div>
			</div>
			<div tabid="exports" title="导出权限">
				<div class="pull-left" ng-repeat="(k,v) in exports" on-finish-render-filters>
					<input type="checkbox" id="e_{{k}}" ng-model="v.r" checked="checked" >
					<label for="e_{{k}}"  class="btn label-sm">{{v.desc}}</label>
				</div>
				<div ng-if="isEmpty(exports)">暂无权限可提供共享</div>
			</div>
		</div>
	</div>
</body>
</html>

	