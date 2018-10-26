var app = angular.module('app',['baseServices','BpmFormDialogCombinateService','commonListService','arrayToolService']);
app.controller("ShowController",['$scope','BaseService','BpmFormDialogCombinate','CommonListService','ArrayToolService',function($scope,BaseService,BpmFormDialogCombinate,CommonListService,ArrayToolService){
	$scope.CommonList=CommonListService;
	$scope.ArrayTool=ArrayToolService;
	
	//对象
	$scope.prop={};
	$scope.prop.field=[];
	
	//页面用到的参数或临时变量
	$scope.param={};
	$scope.param.param=dialog.get("param");//获取条件参数：是自定义对话框的条件
	var treeParamVal="";
	if($scope.param.param&&$scope.param.param.tree){
		treeParamVal="&"+$scope.param.param.tree;
	}
	var listParamVal="";
	if($scope.param.param&&$scope.param.param.list){
		listParamVal="&"+$scope.param.param.list;
	}
	
	//初始化
	if(id!=""){
		var json={};
		json.id=id;
		BpmFormDialogCombinate.getObject(json,function(data){
			data.field=JSON.parse(data.field);
			$scope.prop=data;
			
			$("#treeFrame").attr("src",__ctx+"/platform/form/bpmFormDialog/showFrame.ht?id="+data.treeDialogId+treeParamVal);
			$("#listFrame").attr("src",__ctx+"/platform/form/bpmFormDialog/show.ht?id="+data.listDialogId+listParamVal);
			
			//改标题，暴力改元素
			$('.l-layout-center').find(".l-layout-header").text(data.listDialogName);
			$('.l-layout-left').find('.l-layout-header-inner').text(data.treeDialogName);
		});
	}
	
	$scope.treeDataChange=function(data){
		//console.info(data);
		//console.info($scope.prop);
		var paramUrl="";
		
		var field=$scope.prop.field;
		for(var i=0;i<field.length;i++){
			var t = field[i].tree;
			var l = field[i].list;
			var val = eval("data."+t.fieldName);
			if(!val) continue;
			paramUrl+="&"+l.fieldName+"="+val;
		}
		
		$("#listFrame").attr("src",__ctx+"/platform/form/bpmFormDialog/show.ht?id="+$scope.prop.listDialogId+paramUrl+listParamVal);
	}
	
	
}]);