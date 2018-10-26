angular.module('SysDataSourceDefService', ['baseServices'])
.service('SysDataSourceDef', ['$rootScope','BaseService', '$http',function($rootScope,BaseService,$http) {
    var service = {    		
    		detail : function(id,callback){
    			//获取SysDataSourceDef的详情
    			BaseService.post(__ctx +'/platform/system/sysDataSourceDef/getById.ht',{id:id},function(data){
    				if(callback){
	    				callback(data);
	    			 }
    			});
    		},
    		//保存
    		save:function(SysDataSourceDef,callback){
    			$http.post(__ctx +'/platform/system/sysDataSourceDef/save.ht',SysDataSourceDef).success(function(data, status, headers, config){
    				if(callback){
	    				callback(data);
	    			 }
    			});
    		},
    		//获取字段信息
    		getAttr:function(classPath,callback){
    			BaseService.post(__ctx +'/platform/system/sysDataSourceDef/getSetterFields.ht',{classPath:classPath},function(data){
    				if(callback){
        				callback(data);
        			 }
    			});
    		},
    		//获取字段信息
    		getAll:function(callback){
    			BaseService.post(__ctx +'/platform/system/sysDataSourceDef/getAll.ht',{},function(data){
    				if(callback){
        				callback(data);
        			 }
    			});
    		}
        }
    return service;
}]);