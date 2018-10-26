var sysQuerySqlDefSettingApp = angular.module('sysQuerySqlDefSettingApp', [ 'baseServices','DataRightsApp' ]);

sysQuerySqlDefSettingApp.controller('bpmDataTemplateCtrl',['$scope','BaseService','dataRightsService','$timeout ',function($scope,BaseService,dataRightsService,$timeout){
	var service = dataRightsService;
	$scope.service = service;
	/*$timeout(function(){
		$scope.tab =$("#tab").ligerTab({});	
		$scope.hasInitTab sysQueryViewEdit.jsp = 124;
	},100);*/
	service.init($scope);
	$scope.filterUrl = __ctx + "/platform/system/sysQuerySetting/filterDialog.ht?tableId=";
	$scope.save = function(){
		if ($scope._validForm()) {
			if ($scope.dataRightsJson.id) {
				$.ligerDialog.confirm("保存会覆盖模板，如果修改了模板请手动保存模板后再进行保存业务数据模板，是否继续保存？","提示信息",function(rtn) {
						if (rtn) service.customFormSubmit();
					});
			} else {
				service.customFormSubmit();
			}
		}
	}
	
	$scope._validForm = function (){
		var form=$('#dataRightsForm');
		if(!form.valid()) return false;
		//判断排序字段太多报错问题
		if($scope.sortFields&&$scope.sortFields.length>3){
			$.ligerDialog.error("排序字段不能设置超过3个，请检查！","提示信息");
			$scope.tab.selectTabItem("sortSetting");
			return false;
		}
		//判断管理字段
		if(service.manageFieldValid($scope.manageFields)){
			$.ligerDialog.error("功能按钮出现重复的类型，请检查！","提示信息");
			$scope.tab.selectTabItem("manageSetting");
			return false;
		}
		if($scope.dataRightsJson.templateAlias=="" || $scope.dataRightsJson.needPage ==""){
			$scope.tab.selectTabItem("baseSetting");
			$scope.form.valid();
			return false;	
		}
		return true;
	}
	//预览
	$scope.preview = function (){
		var alias = $scope.dataRightsJson.alias;
		if($.isEmpty(alias)){
			$.ligerDialog.error("请设置完信息保存后预览!","提示信息");
			return ;
		}
		var url=__ctx+ "/platform/system/sysQuerySetting/show/"+alias+".ht";
		url=url.getNewUrl();
		$.openFullWindow(url);
	}
	//编辑模板
	$scope.editTemplate = function (){
		var id = $scope.dataRightsJson.id;
		if($.isEmpty(id)){
			$.ligerDialog.error("请设置完信息保存后编辑模板!","提示信息");
			return ;
		}
		var url=__ctx+ "/platform/system/sysQuerySetting/editTemplate.ht?id="+id;
		url=url.getNewUrl();
		$.openFullWindow(url);
	}
	//添加菜单
	$scope.addToResource = function () {
		var alias = $scope.dataRightsJson.alias;
		var url="/platform/system/sysQuerySetting/show/"+alias+".ht";
		AddResourceDialog({addUrl:url});
	}
}])
.controller('sysQuerySqlUrlCtrl',['$scope','sysQuerySqlService','dataRightsService',function($scope,sysQuerySqlService,dataRightsService){
	var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)
	var conf =dialog.get('conf');
	var service = sysQuerySqlService;
	$scope.service = service;
	$scope.service.util = dataRightsService;
	$scope.sysQueryFields = conf.sysQueryFields;
	$scope.urlParams = conf.urlParams?parseToJson(conf.urlParams):[];
	$scope.save = function(){
		dialog.get('sucCall')(JSON2.stringify($scope.urlParams));
		dialog.close();
	}
	$scope.close = function(){
		dialog.close();
	}
	$scope.add = function(){
		$scope.urlParams.push({});
	}
}])
