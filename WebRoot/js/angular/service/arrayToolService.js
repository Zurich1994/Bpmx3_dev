angular.module('arrayToolService', [])
.service('ArrayToolService', [function() {
    var service = {
    		//上移按钮
	    	up:function(idx,list){
	    		if(idx<1){
	    			return;
	    		}
	    		var t=list[idx-1];
	    		list[idx-1]=list[idx];
	    		list[idx]=t;
	    	},
	    	//下移按钮
	    	down:function(idx,list){
	    		if(idx>=list.length-1){
	    			return;
	    		}
	    		var t=list[idx+1];
	    		list[idx+1]=list[idx];
	    		list[idx]=t;
	    	},
	    	//删除按钮
	    	del:function(idx,list){
	    		list.splice(idx,1);
	    	},
	    	//找到指定元素的未知
	    	indexOf:function(val,list){
	    		for (var i = 0; i < list.length; i++) {  
	    	        if (list[i] == val) return i;  
	    	    }  
	    	    return -1; 
	    	},
	    	//删除指定元素
	    	remove:function(val,list){
	    		var index = list.indexOf(val);  
	    	    if (index > -1) {  
	    	    	list.splice(index, 1);  
	    	    }  
	    	}
    }
    return service;
}]);