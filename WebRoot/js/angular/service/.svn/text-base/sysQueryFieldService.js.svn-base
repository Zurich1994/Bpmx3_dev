 var sysQuerySqlFieldServiceApp = angular.module('sysQuerySqlFieldServiceApp', []);
 sysQuerySqlFieldServiceApp.service('sysQuerySqlFieldService', ['$http', 'dataRightsService', function($http, dataRightsService) {
 	var service = {
 		initCtrlTypeSetting:function(scope){
 				this.scope = scope;
 				scope.dialog = frameElement.dialog;
 				var conf =scope.dialog.get('conf');
 				scope.sysQueryField = conf.field;
 				scope.globalTypes = conf.dicList;
 				scope.selectList = [];
 				scope.sysQueryField.ctrlCon = {};
 				if(scope.sysQueryField.controlType == 11){
 					scope.selectList = parseToJson(scope.sysQueryField.controlContent);
 				}else if(scope.sysQueryField.controlType == 3){
 					scope.sysQueryField.dicType = scope.sysQueryField.controlContent;
 				}else if(scope.sysQueryField.controlType == 12){
 					scope.sysQueryField.ctrlCon = parseToJson(scope.sysQueryField.controlContent);
 				}
 				var url = __ctx + '/platform/form/bpmFormDialog/getAllDialogs.ht';
 				$.ajax({
 				    type:"get",
 				    async:false,
 				    url:url,
 				    success:function(data){
 				    	for(var i = 0 ; i < data.length ; i++){
 				    		data[i].resultfield = parseToJson(data[i].resultfield);
 				    	}
 				    	scope.dialogList = data;
 				    	scope.currentDialog=scope.getCurrentDialog();
 				    	if(!scope.sysQueryField.ctrlCon.dialog){
 	 				    	scope.sysQueryField.ctrlCon.dialog = data[0].alias;
 	 				    	scope.sysQueryField.ctrlCon.resultField = data[0].resultfield[0].field;
 				    	}
 				    	scope.$$phase?"":scope.$digest();
 				    }
 				});
 		},
 		initVirtual:function(scope){
 			this.scope = scope;
 			scope.dialog = frameElement.dialog;
 			var conf =scope.dialog.get('conf');
 			scope.sysQueryField = conf.field;
 			scope.sysQueryFields = conf.sysQueryFields;
				scope.globalTypes = conf.dicList;
 			scope.selectList = [];
 		},
 		initAlarm:function(scope){
 			this.scope = scope;
 			this.util = dataRightsService;
 			scope.dialog = frameElement.dialog;
 			var conf =scope.dialog.get('conf');
 			scope.sysQueryField = conf.field;
 			scope.alarmSetting = scope.sysQueryField.alarmSetting;
 			if(scope.alarmSetting){
 				if(typeof scope.alarmSetting =="string")
 					scope.alarmSetting = parseToJson(scope.sysQueryField.alarmSetting)
 			}else{
 				scope.alarmSetting = [{condition:[{op:">",val:""}],color:"red"}];
 			}
 		},
		getVirtualFromType : function(type){
			var list = {1:"数据字典",2:"sql",3:"下拉框"};
			return list[type];
		},
 		getCtrCon:function(ctrlType){
 			if(ctrlType == 3)
 				return this.scope.sysQueryField.dicType;
 			else if(ctrlType == 11)
 				return dataRightsService.listToString(this.scope.selectList);
 			else if(ctrlType == 12 )
 				return dataRightsService.listToString(this.scope.sysQueryField.ctrlCon);
 		}
 	};
 	return service;
 }])
 // <dialog-buttons></dialog-buttons>
 .directive('dialogButtons', function($compile) {
	return {
		restrict : 'E',
		replace : true,
		template : '<div class="hide-panel">'+
						'<div class="panel-top">'+
							'<div class="panel-toolbar">'+
								'<div class="toolBar">'+
									'<div class="group">'+
										'<a class="link save" href="javascript:;" ng-click="save();">'+
											'<span></span>'+
											'保存'+
										'</a>'+
									'</div>'+
									'<div class="group">'+
										'<a class="link del" href="javascript:;" ng-click="close()">'+
											'<span></span>'+
											'关闭'+
										'</a>'+
									'</div>'+
								'</div>'+
							'</div>'+
						'</div>'+
					'</div>'
	};
})
//<select-table></select-table>
 .directive('selectTable', function($compile) {
	return {
		restrict : 'E',
		replace : true,
		scope:{
			list:"="
		},
		template : '<table style="width:100%;">'+
					'<thead>'+
						'<tr>'+
							'<td colspan="4">'+
								'<a href="javascript:;" class="link add" ng-click="list.push({})">添加</a>'+
							'</td>'+
						'</tr>'+
					'</thead>'+
					'<tbody>'+
						'<tr ng-repeat="sl in list">'+
							'<td>'+
								'<div >'+
									'<label>'+
										'值:'+
										'<input ng-model="sl.optionKey" type="text" style="height:21px;width:150px" class="ht-input"/>'+
									'</label>'+
									'<label style="margin:0 0 0 10px;">'+
										'选项:'+
										'<input ng-model="sl.optionValue" style="height:21px;width:150px" class="ht-input" type="text"/>'+
									'</label>'+
								'</div>'+
							'</td>'+
							'<td>'+
								'<div >'+
									'<tool-buttons type="4" list="list" index="{{$index}}" ></tool-buttons>'+
								'</div>'+
							'</td>'+
						'</tr>'+
					'</tbody>'+
				'</table>'
	};
})