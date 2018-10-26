var shareRightsApp = angular.module('shareRightsApp', [ 'baseServices' ]);

shareRightsApp.controller('shareRightsCtrl',['$scope','BaseService','ShareRightsService','$timeout','$filter',function($scope,BaseService,ShareRightsService,$timeout,$filter){
	var service = ShareRightsService;
	service.init($scope);
	$scope.resetShareItem = function(){
		$scope.shareRights.shareItem = {
				type:$scope.shareRights.shareItem.type
		}
	}
	$scope.setShareRule = function(index){
		if($scope.shareRights.isAll==1){
			$.ligerDialog.warn('全部分享模式下不需要设置此项',"提示信息");
			return ;
		}
		if(!$scope.shareRights.source.ids){
			$.ligerDialog.warn('请选择具体的【共享原】',"提示信息");
			return;	
		}
		switch($scope.shareRights.shareItem.type){
			case "roleDF":
			case "formDTDF":
			case "onLineFormDF":
			case "offLineFormDF":
				service.setShareRule($scope,index);
				break;
		}
	};
	$scope.save = function(){
		if($scope.form.valid()){
			if($scope.shareRights.isAll==1)
				$scope.shareRights.shares = "";
			if(!$scope.shareRights.source.ids){
				$.ligerDialog.warn('请选择具体的【共享原】',"提示信息");
				return;
			}
			if(!$scope.shareRights.shareItem.names){
				$.ligerDialog.warn('请选择具体的【共享条目】',"提示信息");
				return;
			}
			if(!$scope.shareRights.target.ids){
				if($scope.shareRights.target.v!='everyone'&&$scope.shareRights.target.v!='script'){
					$.ligerDialog.warn('请选择具体的【共享目标】',"提示信息");
					return;
				}
				
			}
			$scope.shareRights.source = JSON.stringify($scope.shareRights.source);
			$scope.shareRights.target = JSON.stringify($scope.shareRights.target);
			service.delShareItem($scope.shareRights.shareItem,true);
			$scope.shareRights.shareItem.ids = $scope.shareRights.shareItem.ids.join(",");
			$scope.shareRights.shareItem.names = $scope.shareRights.shareItem.names.join(",");
			$scope.shareRights.shareItem = JSON.stringify($scope.shareRights.shareItem);
			BaseService.post(__ctx +'/platform/share/sysShareRights/save.ht',{"sysShareRights":JSON.stringify($scope.shareRights)},function(data){
				if(data.result){
					$.ligerDialog.confirm(data.message + " ,是否继续操作", "提示信息",function(rtn) {
						if (rtn) {
							window.location.href = window.location.href;
						} else {
							window.location.href = __ctx+"/platform/share/sysShareRights/list.ht";
						}
					});
				}else
					$.ligerDialog.error(data.message,"提示信息");
			});
		}else
			$.ligerDialog.error("表单验证不成功","提示信息");
	};
	$scope.remove = function(i,$event){
		$scope.shareRights.shareItem.names.splice(i,1);
		var id = $scope.shareRights.shareItem.ids.splice(i,1);
		delete $scope.shareRights.shareItem["v_"+id]
		$event.stopPropagation();
	}
	$scope.chooseShareObject =function(){
		if(!$scope.shareRights.source||!$scope.shareRights.source.ids){
			$.ligerDialog.warn('请先设置【共享原】',"提示信息");
			return;
		}
		if(!$scope.shareRights.shareItem.type){
			$.ligerDialog.warn('请先设置【共享类型】',"提示信息");
			return;
		}
		switch($scope.shareRights.shareItem.type){
			case "formDTDF":
			case "onLineFormDF":
				FormDialog({
					condition:"?dataTemplate=true&isMutl=true",
					callback : function(ids, names, tableId) {
						service.getPermissionData(ids,$scope);
						$scope.shareRights.shareItem['ids']=ids.split(",");
						$scope.shareRights.shareItem['names']=names.split(",");
						$scope.$digest();
					}});
				break;
			case "offLineFormDF":
				CommonDialog("bdsjqxgx",function(data){
					  //data返回 Object { ORGNAME = "参数值", ORGTYPE = "参数值"}，多个则返回 Object 数组
						var ids = [],names=[];
						for(var i in data){
							if(data[i].id){
								ids.push(data[i].id);
								names.push(data[i].tablename);
							}
						}
						service.getPermissionData(ids.join(","),$scope);
						$scope.shareRights.shareItem['ids']=ids;
						$scope.shareRights.shareItem['names']=names;
						$scope.$digest();
					});
				break;
		}
	}
}]);
shareRightsApp.controller('shareItemCtrl',['$scope','BaseService','ShareRightsService','$timeout','$filter','$compile',function($scope,BaseService,ShareRightsService,$timeout,$filter,$compile){
	var v = dialog.get('v'),service = ShareRightsService;
	
	$scope.display = service.mergeField(v?v.display:"",service.parseJson(display));//v?v.display:service.parseJson(display);
	$scope.manager = service.mergeField(v?v.manager:"",service.parseJson(manager));//v?v.manager:service.parseJson(manager);
	$scope.filter =service.mergeField(v?v.filter:"",service.parseJson(filter));//v?v.filter:service.parseJson(filter);
	$scope.exports = service.mergeField(v?v.exports:"",service.parseJson(exports));//v?v.exports:service.parseJson(exports);
	var tab = "";
	$scope.$on('ngRepeatFinished', function (ngRepeatFinishedEvent) {
		if(!tab){
			tab =$("#tab").ligerTab({});
			$('[type="checkbox"]').each(function(){
				var val = getValByScope($(this));
				if(val==true||val=="true")
					$(this)[0].checked = "checked";
			})
		}
	});
	$scope.isEmpty = function(o){
		return $.isEmptyObject(o);
	}
	$scope.save = function(){
		dialog.get('sucCall')({
			display:$scope.display,
			manager:$scope.manager,
			filter :$scope.filter ,
			exports:$scope.exports
		});
		dialog.close();
	}
}]);
shareRightsApp.service('ShareRightsService', ['$http','$jsonToFormData','BaseService', function($http,$jsonToFormData,BaseService) {
    var service = {
    		init:function($scope){
    			$scope.shareRights = sr?parseToJson(sr.replaceAll("\"{","\'{").replaceAll("}\"","}\'")):{};
    			$scope.allTypes = parseToJson(allTypes);
    			if(!$.isEmptyObject($scope.shareRights)&&$scope.shareRights.shareItem){
    				$scope.shareRights.source = parseToJson($scope.shareRights.source);
    				$scope.shareRights.target = parseToJson($scope.shareRights.target);
    				$scope.shareRights.shareItem = parseToJson($scope.shareRights.shareItem);
    				$scope.shareRights.shareItem.ids = $scope.shareRights.shareItem.ids.split(",");
    				$scope.shareRights.shareItem.names = $scope.shareRights.shareItem.names.split(",");
    			}
    			$scope.form = $("#sysShareRightsForm").form();
    			window.setTimeout(function(){
    				$scope.form.valid();
    			},1000);
    		},
    		parseJson:function(str) {
    			var json = parseToJson(str.replaceAll("\"{","\'{").replaceAll("}\"","}\'"));
    			return this.delFalseEl(json);
    		},
    		delFalseEl : function(json){
    			for(var i in json ){
    				json[i] = typeof(json[i])=='object'?json[i]:parseToJson(json[i]);
    				if(json[i].r == false||json[i].r == "false") 
    					delete json[i]
    			}
    			return json;
    		},
    		delAllFalseEl : function(data){
    			for(var i in data){
    				this.delFalseEl(data[i]);
    			}
    		},
    		delShareItem : function(shareItem,notAll){
    				var ids = shareItem.ids;
    				for(var i in shareItem){
        				if(i.indexOf("v_")==0){
        					if(notAll){
        						var id = i.substring(2);
            					if($.inArray(id,ids)==-1)
            						delete shareItem[i]
        					}else
        						delete shareItem[i]
        				}
    			}
    		},
    		getSourceType:function(v){
    			var sourceType = 0;
				switch(v){
					case "user":
						sourceType = 0;
						break;
					case "role":
						sourceType = 1;
						break;
					case "org":
						sourceType = 2;
						break;
					case "pos":
						sourceType = 3;
						break;
				}
				return sourceType;
    		},
    		getPermissionData:function(ruleIds,$scope){
    			var params = {
    					ruleIds:ruleIds,
    					ids:$scope.shareRights.source.ids,
    					sourceType:service.getSourceType($scope.shareRights.source.v),
    					shareType:$scope.shareRights.shareItem.type
    				}
    			BaseService.post(__ctx +'/platform/share/sysShareRights/getPermissionByRuleIds.ht',params,function(data){
    				$scope._permissions = $scope._permissions || {};
    				if(typeof data == "string")
    					return;
    				for(var i in data){
    					service.delAllFalseEl(data[i]);
    					$scope._permissions[i] = $.extend( true, {},data[i]);
    					$scope._permissions[i].ids = $scope.shareRights.source.ids;
    					if(!$scope.shareRights.shareItem[i]){
    						$scope.shareRights.shareItem[i] = $.extend( true, {},data[i]);
    						$scope.shareRights.shareItem[i].ids = $scope.shareRights.source.ids;
    					}
    				}
    			});
    		},
    		setShareRule : function($scope,index){
    			var service = this ,
    				ruleId = $scope.shareRights.shareItem.ids[index],
    				params = {
						ruleId:ruleId,
						ids:$scope.shareRights.source.ids,
						sourceType:service.getSourceType($scope.shareRights.source.v),
						shareType:$scope.shareRights.shareItem.type,
						hasPermission : false,
						callBack : function(obj){
							service.delShareItem($scope.shareRights.shareItem);
							$scope.shareRights.shareItem["v_"+ruleId] = obj;
							$scope.shareRights.shareItem["v_"+ruleId].ids = $scope.shareRights.source.ids;
						}
					}
				if($scope.shareRights.shareItem["v_"+ruleId]){
					if($scope.shareRights.shareItem["v_"+ruleId].ids == params.ids)
						params.v_ = $.extend( true, {},$scope.shareRights.shareItem["v_"+ruleId]);
				}else if($scope._permissions){
					if($scope._permissions["v_"+ruleId].ids == params.ids)
						params.v_ = $.extend( true, {},$scope._permissions["v_"+ruleId]);
					
				}
				if(params.v_)
					params.hasPermission = true;
				var url= __ctx +'/platform/share/sysShareRights/items.ht?ruleId='+params.ruleId+"&ids="+params.ids+"&sourceType="+params.sourceType+"&shareType="+params.shareType+"&hasPermission="+params.hasPermission;
				DialogUtil.open({
					title : "共享条目",
					url : url,
					height : 350,
					width : 550,
					isResize : false,
					pwin : window,
					v : params.v_,
			        sucCall:params.callBack
				});	
    		},
    		mergeField : function(source,target){
    			if(!source) return target;
    			for(var i in target){
    				if(source[i]){
    					source[i].desc=target[i].desc;
    				}else
    					source[i] = target[i];
    			}
    			for(var i in source)
    				if(!target[i]) delete source[i];
    			return source;
    		}
    }
    return service;
}])