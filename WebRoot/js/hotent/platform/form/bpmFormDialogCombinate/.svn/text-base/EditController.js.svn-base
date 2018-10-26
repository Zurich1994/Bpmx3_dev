var app = angular.module('app',['baseServices','BpmFormDialogCombinateService','commonListService','arrayToolService']);
app.controller("EditController",['$scope','BaseService','BpmFormDialogCombinate','CommonListService','ArrayToolService',function($scope,BaseService,BpmFormDialogCombinate,CommonListService,ArrayToolService){
	$scope.CommonList=CommonListService;
	$scope.ArrayTool=ArrayToolService;
	
	//对象
	$scope.prop={};
	$scope.prop.field=[];
	
	//页面用到的参数或临时变量
	$scope.param={};
	
	if(id!=""){
		var json={};
		json.id=id;
		BpmFormDialogCombinate.getObject(json,function(data){
			$scope.prop=data;
			$scope.prop.field=JSON.parse(data.field);
			
			//初始化树对话框和列表对话框
			BpmFormDialogCombinate.getObject({dialogId:data.treeDialogId},function(data){
				$scope.param.treeDialog=data;
				$scope.param.treeDialog.resultfield=data.returnList;
			});
			BpmFormDialogCombinate.getObject({dialogId:data.listDialogId},function(data){
				$scope.param.listDialog=data;
				var clist=data.conditionList;
				$scope.param.listDialog.conditionfield=[];
				for(var i=0;i<clist.length;i++){
					var c=clist[i];
					if(c.defaultType=='4'){
						$scope.param.listDialog.conditionfield.push(c);
					}
				}
			});
		});
	}
	
	//保存
	$scope.save=function(){
		var frm=$('#frmSubmit').form();
		if(!frm.valid()) return;
		if(!$scope.prop.treeDialogId||!$scope.prop.listDialogId||$scope.prop.field.length<=0){
			$.ligerDialog.error("请正确配置组合规则","提示信息");
			return;
		}
		
		BpmFormDialogCombinate.save($scope.prop,function(data){
			if(data.result==1){
				$.ligerDialog.confirm(data.message+",是否继续操作", "提示信息",
					function(rtn) {
						if (rtn) {
							window.location.reload();
						} else {
							window.location.href="list.ht";
						}
					});
			}else{
				$.ligerDialog.err("提示信息","保存出错了",data.message);
			}
		});
	}
	
	//选择树对话框
	$scope.selectTreeDialog=function(){
		CommonDialog("zdydhklb",function(data){
			$scope.$apply(function(){
				$scope.prop.treeDialogId=data.ID;
				$scope.prop.treeDialogName=data.NAME;
				var map={};
				map.dialogId=data.ID;
				BpmFormDialogCombinate.getObject(map,function(data){
					$scope.param.treeDialog=data;
					$scope.param.treeDialog.resultfield=data.returnList;
				});
			}); 
		},"STYLE=1");
	}
	
	//选择列表对话框
	$scope.selectListDialog=function(){
		CommonDialog("zdydhklb",function(data){
			$scope.$apply(function(){
				$scope.prop.listDialogId=data.ID;
				$scope.prop.listDialogName=data.NAME;
				var map={};
				map.dialogId=data.ID;
				BpmFormDialogCombinate.getObject(map,function(data){
					$scope.param.listDialog=data;
					$scope.param.listDialog.conditionfield=[];
					var clist=data.conditionList;
					for(var i=0;i<clist.length;i++){
						var c=clist[i];
						if(c.defaultType=='4'){
							$scope.param.listDialog.conditionfield.push(c);
						}
					}
				});
			}); 
		},"STYLE=0");
	}
	
	$scope.createField=function(){
		var json={};
		json.tree=$scope.param.treeColumn;
		json.list=$scope.param.listColumn;
		if(!json.tree||!json.list) return;
		$scope.prop.field.push(json);
	}
	
}]);