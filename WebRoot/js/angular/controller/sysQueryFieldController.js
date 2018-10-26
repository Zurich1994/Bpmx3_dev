var sysQueryFieldApp = angular.module('sysQueryFieldApp', [ 'baseServices','DataRightsApp','sysQuerySqlFieldServiceApp','sysQuerySqlServiceApp']);

sysQueryFieldApp.controller('sysQueryMetaFieldCtrl',['$scope','dataRightsService','sysQuerySqlFieldService','sysQuerySqlService',function($scope,dataRightsService,sysQuerySqlFieldService,sysQuerySqlService){
	var service = sysQuerySqlFieldService;
	$scope.service = service;
	var conf = frameElement.dialog.get('conf');
	$scope.type = conf.type;
	$scope.util = sysQuerySqlService;
	$scope.close = function(){
		frameElement.dialog.close();
	}
}])
.controller('sysQueryMetaFieldCtrlTypeCtrl',['$scope',function($scope){
	$scope.save = function(){
		$scope.sysQueryField.controlContent = $scope.service.getCtrCon($scope.sysQueryField.controlType);
		frameElement.dialog.get('sucCall')($scope.sysQueryField);
		frameElement.dialog.close();
	}
	$scope.changeDialog = function (){
		$scope.currentDialog=$scope.getCurrentDialog();
		$scope.sysQueryField.ctrlCon.resultField = $scope.currentDialog.resultfield[0].field;
	}
	$scope.getCurrentDialog = function(){
		for(var i = 0 ; i < $scope.dialogList.length ; i++){
			if($scope.dialogList[i].alias == $scope.sysQueryField.ctrlCon.dialog){
				return $scope.dialogList[i];
			}
		}
		return $scope.dialogList[0];
	}
	$scope.service.initCtrlTypeSetting($scope);
	$scope.sysQueryField.controlType=$scope.sysQueryField.controlType+"";
}])
.controller('sysQueryMetaFieldVirtualCtrl',['$scope',function($scope){
	$scope.save = function(){
		frameElement.dialog.get('sucCall')($scope.sysQueryField);
		frameElement.dialog.close();
	}
	$scope.service.initVirtual($scope);
}])
.controller('sysQueryMetaFieldAlarmCtrl',['$scope',function($scope){
	$scope.save = function() {
		$scope.sysQueryField.alarmSetting  = $scope.alarmSetting;
		frameElement.dialog.get('sucCall')($scope.sysQueryField);
		frameElement.dialog.close();
	}
	$scope.addCondition = function (list){
		list.push({op:">",val:""});
	}
	$scope.addAlarmSetting = function (){
		$scope.alarmSetting.push({
 				condition:[{
 					op:">",
 					val:""
 				}],
 				color:"red"
 			});
	}
	$scope.service.initAlarm($scope);
}])

