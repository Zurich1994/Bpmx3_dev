angular.module('SysDataSourceService', ['baseServices'])
.service('SysDataSource', ['$rootScope','BaseService', '$http',function($rootScope,BaseService,$http) {
    var service = {    		
    		detail : function(id,callback){
    			//获取SysDataSource的详情
    			BaseService.post(__ctx +'/platform/system/sysDataSource/getById.ht',{id:id},function(data){
    				if(callback){
	    				callback(data);
	    			 }
    			});
    		},
    		//保存
    		save:function(SysDataSource,callback){
    			$http.post(__ctx +'/platform/system/sysDataSource/save.ht',SysDataSource).success(function(data, status, headers, config){
    				if(callback){
	    				callback(data);
	    			 }
    			});
    		},
    		//保存
    		checkConnection:function(SysDataSource,callback){
    			$http.post(__ctx +'/platform/system/sysDataSource/checkConnection.ht',SysDataSource).success(function(data, status, headers, config){
    				if(callback){
	    				callback(data);
	    			 }
    			});
    		},
    		//获取系统所有数据源
    		getAllSysDS:function(callback){
    			$http.post(__ctx +'/platform/system/sysDataSource/getAll.ht').success(function(data, status, headers, config){
    				if(callback){
	    				callback(data);
	    			 }
    			});
    		}
        }
    return service;
}]);