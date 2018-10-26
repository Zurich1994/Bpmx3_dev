angular.module('FormDefCombinateService', ['baseServices'])
.service('FormDefCombinate', ['$rootScope','BaseService', '$http',function($rootScope,BaseService,$http) {
    var service = {    		
    		//保存
    		save:function(json,callback){
     			$http.post(__ctx +'/platform/form/formDefCombinate/save.ht',json).success(function(data, status, headers, config){
    				if(callback){
	    				callback(data);
	    			 }
    			});
    		},
    		//ngjs请求
    		ngjs : function(json,callback){
    			BaseService.post(__ctx +'/platform/form/formDefCombinate/ngjs.ht',json,function(data){
    				if(callback){
	    				callback(data);
	    			 }
    			});
    		},
        }
    return service;
}]);