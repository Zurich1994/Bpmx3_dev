angular.module('SysTransDefService', ['baseServices'])
.service('SysTransDef', ['$rootScope','BaseService', '$http',function($rootScope,BaseService,$http) {
    var service = {    		
    		//获取SysTransDef的详情
    		detail : function(json,callback){
    			BaseService.post(__ctx +'/platform/system/sysTransDef/getObject.ht',json,function(data){
    				if(callback){
	    				callback(data);
	    			 }
    			});
    		},
    		//保存
    		save:function(SysTransDef,callback){
    			$http.post(__ctx +'/platform/system/sysTransDef/save.ht',SysTransDef).success(function(data, status, headers, config){
    				if(callback){
	    				callback(data);
	    			 }
    			});
    		},
    		//执行selectSql
    		excuteSelectSql:function(json,callback){
    			BaseService.post(__ctx +'/platform/system/sysTransDef/excuteSelectSql.ht',json,function(data){
    				if(callback){
	    				callback(data);
	    			 }
    			});
    		},
    		//执行updateSql
    		excuteUpdateSql:function(json,callback){
    			BaseService.post(__ctx +'/platform/system/sysTransDef/excuteUpdateSql.ht',json,function(data){
    				if(callback){
	    				callback(data);
	    			 }
    			});
    		},
        }
    return service;
}]);