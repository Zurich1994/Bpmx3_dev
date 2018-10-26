angular.module('SysPopupRemindService', ['baseServices'])
.service('SysPopupRemind', ['$rootScope','BaseService', '$http',function($rootScope,BaseService,$http) {
    var service = {    		
    		//获取SysPopupRemind的详情
    		detail : function(json,callback){
    			BaseService.post(__ctx +'/platform/system/sysPopupRemind/getObject.ht',json,function(data){
    				if(callback){
	    				callback(data);
	    			 }
    			});
    		},
    		//保存
    		save:function(SysPopupRemind,callback){
    			$http.post(__ctx +'/platform/system/sysPopupRemind/save.ht',SysPopupRemind).success(function(data, status, headers, config){
    				if(callback){
	    				callback(data);
	    			 }
    			});
    		},
        }
    return service;
}]);