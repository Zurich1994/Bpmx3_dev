var app = angular.module('app',['baseServices','SysTransDefService','commonListService','arrayToolService']);
app.controller("SetController",['$scope','BaseService','SysTransDef','CommonListService','ArrayToolService',function($scope,BaseService,SysTransDef,CommonListService,ArrayToolService){
	$scope.CommonList=CommonListService;
	$scope.ArrayTool=ArrayToolService;
	
	//对象
	$scope.prop={};
	
	//页面用到的参数或临时变量
	$scope.param={};
	$scope.param.author={};//授权人
	$scope.param.targetPerson={};//目的人
	
	SysTransDef.detail({id:id},function(data){
		$scope.std=data;
	});
	
	//执行selectSql
	$scope.excuteSelectSql=function(){
		if(id=="1") return;//1是为显示好看的父节点，没意义的
		var json={};
		json.id=id;
		json.authorId=$scope.param.author.id;
		SysTransDef.excuteSelectSql(json,function(data){
			$scope.param.list=jQuery.extend(true,[], data);
			for(var i=0;i<data.length;i++){
				delete data[i].id;
				delete data[i].name;
			}
			$scope.param.showList=jQuery.extend(true,[], data);
		});
	}
	
	//选择人员
	$scope.selectUser=function(json,callback){
		UserDialog({
			selectUserIds:"",
    	    selectUserNames:"",
			isSingle:true,
			callback:function(ids,names){
				$scope.$apply(function(){
					json.name=names;
				});
				json.id=ids;
				if(callback){//回调函数
					callback();
				}
			}
		});
	}
	
	//授权人改变，刷新左边的树
	$scope.authorChange=function(){
		var aid=$scope.param.author.id;
		var ztreeCreator = new ZtreeCreator('glTypeTree',"treeListJson.ht?authorId="+aid)
		.setCallback({onClick:zTreeOnClick})
		.initZtree();
		$scope.$apply(function(){
			$scope.param.list=[];
			$scope.param.showList=[];
		});
	}
	
	//点击列
	$scope.clickItem=function(item){
		if(!item.selected) item.selected=false;
		item.selected=!item.selected;
	}
	
	//点击全选
	$scope.clickAll=function(){
		if(!$scope.param.selectAll) $scope.param.selectAll=false;
		$scope.param.selectAll=!$scope.param.selectAll;
		for(var i=0;i<$scope.param.list.length;i++){
			$scope.param.list[i].selected=$scope.param.selectAll;
		}
	}
	
	//执行updateSql
	$scope.excuteUpdateSql=function(){
		var param={};//发送到后台的参数
		param.id=id;
		param.selectedItem=[];//选中的列
		param.authorId=$scope.param.author.id;
		param.targetPersonId=$scope.param.targetPerson.id;
		
		if(id==""||!id){
			$.ligerDialog.warn("请选择权限转移的类型(左边的树)", "提示信息");
			return;
		}
		if(param.authorId==""||!param.authorId){
			$.ligerDialog.warn("请选择授权人", "提示信息");
			return;
		}
		if(param.targetPersonId==""||!param.targetPersonId){
			$.ligerDialog.warn("请选择转移到的目标人", "提示信息");
			return;
		}
		if(param.targetPersonId==param.authorId){
			$.ligerDialog.warn("授权人跟目标人不能为同一个人", "提示信息");
			return;
		}
		
		for(var i=0;i<$scope.param.list.length;i++){
			var item=$scope.param.list[i];
			if(item.selected){
				var json ={};
				json.id=item.id;
				json.name=item.name;
				param.selectedItem.push(json);
			}
		}
		
		if(param.selectedItem.length<=0){
			$.ligerDialog.warn("请选择数据列表", "提示信息");
			return;
		}
		param.selectedItem=JSON.stringify(param.selectedItem);
		
		SysTransDef.excuteUpdateSql(param,function(data){
			if(data.result==1){
				$.ligerDialog.success(data.message,"操作成功",function(){
					window.location.reload();
				});
			}else{
				$.ligerDialog.error(data.message,"操作失败");
			}
		});
	}
}]);