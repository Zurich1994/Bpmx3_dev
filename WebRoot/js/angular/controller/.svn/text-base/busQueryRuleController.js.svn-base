var dataRightsJsonApp = angular.module('busQueryRuleApp', [ 'baseServices','DataRightsApp' ]);
dataRightsJsonApp.controller('busQueryRuleCtrl',['$scope','BaseService','dataRightsService','$timeout',function($scope,BaseService,dataRightsService,$timeout){
	var service = dataRightsService;
	$scope.service = service;
	$timeout(function(){
		$scope.tab =$("#tab").ligerTab({});	
		$scope.hasInitTab = 124;
		$scope.$digest();
	},100)
	service.init($scope);
	$scope.filterUrl = __ctx + "/platform/bus/busQueryRule/filterDialog.ht?tableName=";
	$scope.save = function(){
		if ($scope._validForm()) {
			service.customFormSubmit();
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
		return true;
	}
}]);
