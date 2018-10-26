var baseServices = angular.module( "baseServices", [] );
baseServices.factory("$jsonToFormData",function() {
	function transformRequest( data, getHeaders ) {
		var headers = getHeaders();
		headers["Content-Type"] = "application/x-www-form-urlencoded; charset=utf-8";
		return $.param(data);
	}
	return( transformRequest );
})
.directive('htCheckbox', function() {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var key = attrs["htCheckbox"];
            var getValAry = function(){
                var val = getValByScope(element,key);
                if(val){
                	return val.split(",");
                }
                return [];
            };
            scope.$watch(key,function(newVal,oldVal){
            	var ary = getValAry();
                if(ary&&ary.length&&ary.join(",").indexOf(attrs["value"])!=-1){
                    element[0].checked = true;
                }
            });
            element.bind('change',function(){
                var elementVal = element[0].checked,
                    option = attrs["value"],
                    array = getValAry();
                if(elementVal){
                    array.push(option);
                }
                else{
                    array.remove(option);
                }
                setValToScope(element,array.join(','),null,key);
            });
        }
    };
})
.directive('htDate', function() {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
        	var ngModel = attrs.ngModel;
        	switch(attrs.htDate){
        		case "date":
        			$(element).on("focus",function(){
        				var me = $(this);
        				WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true});
        				me.blur();
    				   scope.$apply(function(){
                           eval("(scope." + ngModel + "='" + me.val() + "')");    
                       });
        			});
        		break;
         		case "datetime":
        			$(element).on("focus",function(){
        				var me = $(this);
        				WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true});
        				me.blur();
    				   scope.$apply(function(){
                           eval("(scope." + ngModel + "='" + me.val() + "')");    
                       });
    			});
        		break;
        		case "wdateTime":
        			$(element).on("focus",function(){
        				var me = $(this), dateFmt=  (me.attr('datefmt')?me.attr('datefmt'):'yyyy-MM-dd');
            			WdatePicker({dateFmt:dateFmt,alwaysUseStartDate:true});
            			me.blur();
        			   scope.$apply(function(){
                           eval("(scope." + ngModel + "='" + me.val() + "')");    
                       });
        			});
        		break;
        	}
        }
    };
})

.service('BaseService', ['$http','$jsonToFormData', function($http,$jsonToFormData) {
    var service = {
    		get:function(url,callback){
    			$http.get(url).success(function(data,status){
            		if(callback){
            			callback(data,status);
            		}
        		})
        		.error(function(data,status){
        			if(callback){
            			callback(data,status);
        			}
        			//TODO 根据返回的错误状态(status)显示对应的全局提示
        		});
    		},
    		post:function(url,param,callback){
    			$http.post(url,param,{transformRequest:$jsonToFormData})
				 .success(function(data,status){
					 if(callback){
	    				callback(data,status);
	    			 }
				 })
				 .error(function(data,status){
					 if(callback){
	    				callback(data,status);
	    			 }
					 //TODO 根据返回的错误状态(status)显示对应的全局提示
				 });
    		},
    		//m内容，b:true->alert输出；false:console
    		show:function(m,b){
    			if(b==null||b==false){
    				// console.info(m);
    			}else{
    				alert(m+"");
    			}
    		}
        }
    return service;
}])
.directive('chooseTarget', [function() { 
	return {
		restrict: 'EAC',
		scope:{
			right:'='
		},
		link: function(scope, element, attrs) {
		
			var opList = [{k:'',v:'请选择'}
							,{k:'none',v:'无'}
							,{k:'everyone',v:'所有人'}
							,{k:'user',v:'用户'}
							,{k:'role',v:'角色'}
							,{k:'org',v:'组织'}
							,{k:'orgMgr',v:'组织负责人'}
							,{k:'pos',v:'岗位'}
							,{k:'script',v:'脚本'}],
				opStr = "",
				opNo = attrs.opList?parseToJson(attrs.opList).no:"";
				opNo = opNo?opNo.split(","):"";
				
			scope.getOpition = function(){
				opStr= "";
				for(var i = 0 ; i < opList.length ; i++){
					if($.inArray(opList[i].k,opNo)==-1)
						opStr +='<option value="'+opList[i].k+'">'+opList[i].v+'</option>';
				}
				return opStr;
			};
			scope.v = "v";
			if(!scope.right) scope.right = {};
			if(!scope.right.hasOwnProperty("v"))
				scope.v = "type";
			scope.handClick = function(){
				// 选择回调
				var callback = function(ids, names) {
					scope.right.names =names;
					scope.right.ids = ids;
					scope.$digest();
				};
				switch (scope.right[scope.v]) {
					case "user" :
						UserDialog({callback : callback});
						break;
					case "role" :
						RoleDialog({callback : callback});
						break;
					case "org" :
					case "orgMgr" :
						OrgDialog({callback : callback});
						break;
					case "pos" :
						PosDialog({callback : callback});
						break;
					case "script" :
						ScriptDialog({
							callback : function(script) {
								scope.right.rightScript =script;
								scope.$digest();
							}
						});
						break;
				}
			};
			scope.resetRight = function(){
				var v = scope.v,
					val = scope.right[v];
				scope.right = {};
				scope.right[v] = val;
			};
			scope.remove = function(i){
				var ids = scope.right.ids.split(",");
				var names = scope.right.names.split(",");
				ids.splice(i,1);
				names.splice(i,1);
				scope.right.ids = ids.join(",");
				scope.right.names = names.join(",");
			};
		},
		template: '<select  class="ht-input w-120"   ng-model="right[v]" ht-bind-html="getOpition()" validate="{required:true}" ng-change="resetRight()">'
					+'</select>'
					+'<span ng-hide="right[v]==\'script\'||right[v]==\'everyone\'||right[v]==\'none\'||!right[v]||right.names==\'\'"  class="ht-input" style="width: initial;max-width: 260px;">' 
						+'<span class="owner-span" ng-repeat="item in right.names.split(\',\')">{{item}}'
							+'<a class="flootbutton" title="移除该项" ng-click="remove($index)">x</a>'
						+'</span>'
					+'</span>'
					+'<span class="bt-select" ng-click="handClick()" ng-show="right[v]&&right[v]!=\'none\'&&right[v]!=\'everyone\'">选择</span>'
					+'<span ng-show="right[v]==\'script\'">'
						+'<textarea  cols="40" rows="3" ng-model="right.script" class="ht-input" validate="{required:true}" ></textarea>'
						+'<a  href="javascript:;" class="bt-select" ng-click="handClick()">常用脚本</a>'
					+'</span>',
        replace: false
	};
}])
.directive('htBindHtml', function($compile) {
	return {
		restrict : 'A',
		link : function(scope, elm, attrs) {
			scope.unbindWatch = scope.$watch(attrs.htBindHtml, function(newVal, oldVal) {
                if (!elm.data('unbindWatchTag')) {
                    if(newVal){
                        elm.data('unbindWatchTag',true);
                        elm.html(newVal);
                        scope.htmlFn&&scope.htmlFn.call();
                        $compile(elm)(scope);
                    }
                    else{
                        //避免重复添加监视
                        elm.data('unbindWatchTag')&&scope.unbindWatch();
                    }
                }
            });
		}
	};
})
.directive('onFinishRenderFilters', function ($timeout) {
    return {
        restrict: 'A',
        link: function(scope, element, attr) {
            if (scope.$last === true) {
                $timeout(function() {
                    scope.$emit('ngRepeatFinished');
                });
            }
        }
    };
})
;

