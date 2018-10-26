angular.module('BpmNodeSqlService', ['baseServices'])
.service('BpmNodeSql', ['$rootScope','BaseService', '$http',function($rootScope,BaseService,$http) {
    var service = {
    		//获取BpmNodeSql的详情
    		detail : function(json,callback){
    			BaseService.post(__ctx +'/platform/bpm/bpmNodeSql/getObject.ht',json,function(data){
    				if(callback){
	    				callback(data);
	    			 }
    			});
    		},
    		//保存
    		save:function(BpmNodeSql,callback){
    			$http.post(__ctx +'/platform/bpm/bpmNodeSql/save.ht',BpmNodeSql).success(function(data, status, headers, config){
    				if(callback){
	    				callback(data);
	    			 }
    			});
    		},
    		//根据defId获取
    		getTable:function(json,callback){
    			BaseService.post(__ctx +'/platform/bpm/bpmNodeSql/getTable.ht',json,function(data){
    				if(callback){
	    				callback(data);
	    			 }
    			});
    		},
    		//根据actdefId跟nodeId获取节点数据，主要是为了判断是否是开始节点
    		getNodeType:function(json,callback){
    			BaseService.post(__ctx +'/platform/bpm/bpmNodeSql/getNodeType.ht',json,function(data){
    				if(callback){
	    				callback(data);
	    			 }
    			});
    		},
        }
    return service;
}]);