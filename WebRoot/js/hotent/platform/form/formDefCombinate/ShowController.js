var app = angular.module('app',['baseServices','FormDefCombinateService','commonListService','arrayToolService']);
app.controller("ShowController",['$scope','BaseService','FormDefCombinate','CommonListService','ArrayToolService',function($scope,BaseService,FormDefCombinate,CommonListService,ArrayToolService){
	$scope.CommonList=CommonListService;
	$scope.ArrayTool=ArrayToolService;
	
	//对象
	$scope.prop={};
	
	//页面用到的参数或临时变量
	$scope.param={};
	$scope.param.listInitialUrl="";//list页面的初始的url 无参数
	$scope.param.treeInitialUrl="";//tree页面的初始的url 无参数
	
	if(alias!=""){
		var json={};
		FormDefCombinate.ngjs({action:"getByAlias",alias:alias},function(data){
			data.field=JSON.parse(data.field);
			$scope.prop=data;
			//console.info(data);
			FormDefCombinate.ngjs({action:"getTemplateByFormDefId",formDefId:data.formDefId},function(data1){
				$scope.param.listInitialUrl=__ctx+"/platform/form/bpmDataTemplate/dataList_"+data1.formKey+".ht";
				$scope.param.treeInitialUrl=__ctx+"/platform/form/bpmFormDialog/showFrame.ht?id="+data.treeDialogId;
				$("#treeFrame").attr("src",$scope.param.treeInitialUrl);
				$("#formDefFrame").attr("src",$scope.param.listInitialUrl);
			});
			
			//改标题，暴力改元素
			$('.l-layout-center').find(".l-layout-header").text(data.formDefName);
			$('.l-layout-left').find('.l-layout-header-inner').text(data.treeDialogName);
		});
	}
	
	//树点击触发事件
	$scope.treeDataChange=function(data){
		console.info(data);
		//console.info($scope.prop);
		var param="";
		$($scope.prop.field).each(function(i){
			console.info(this);
			var tree=this.tree;
			var temp=this.temp;
			var val=eval("data."+tree.fieldName);
			if(!val) return;
			if(param!=""){
				param+="&";
			}
			param+=temp.na+"="+val;
		});
		$("#formDefFrame").attr("src",$scope.param.listInitialUrl+"?"+param);
	}
	
}]);