angular.module('BpmFormDialogCombinateService', ['baseServices'])
.service('BpmFormDialogCombinate', ['$rootScope','BaseService', '$http',function($rootScope,BaseService,$http) {
    var service = {    		
    		//获取想要的详情
    		getObject : function(json,callback){
    			BaseService.post(__ctx +'/platform/form/bpmFormDialogCombinate/getObject.ht',json,function(data){
    				if(callback){
	    				callback(data);
	    			 }
    			});
    		},
    		//保存
    		save:function(json,callback){
     			$http.post(__ctx +'/platform/form/bpmFormDialogCombinate/save.ht',json).success(function(data, status, headers, config){
    				if(callback){
	    				callback(data);
	    			 }
    			});
    		}
        }
    return service;
}]);