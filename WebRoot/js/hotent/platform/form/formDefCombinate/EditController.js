var app = angular.module('app',['baseServices','FormDefCombinateService','commonListService','arrayToolService','BpmFormDialogCombinateService']);
app.controller("EditController",['$scope','BaseService','FormDefCombinate','CommonListService','ArrayToolService','BpmFormDialogCombinate',function($scope,BaseService,FormDefCombinate,CommonListService,ArrayToolService,BpmFormDialogCombinate){
	$scope.CommonList=CommonListService;
	$scope.ArrayTool=ArrayToolService;
	
	//对象
	$scope.prop={};
	$scope.prop.field=[];
	
	//页面用到的参数或临时变量
	$scope.param={};
	
	//初始化
	if(id!=""){
		var json={};
		json.action="getById";
		json.id=id;
		
		FormDefCombinate.ngjs(json,function(data){
			data.field=JSON.parse(data.field);
			$scope.prop=data;
			
			//初始化树对话框
			BpmFormDialogCombinate.getObject({dialogId:data.treeDialogId},function(data){
				$scope.param.treeDialog=data;
			});
			
			//初始化表单的表bpmFormTable数据
			FormDefCombinate.ngjs({action:"getTemplateByFormDefId",formDefId:data.formDefId},function(data){
				var list=[];
				$(JSON.parse(data.conditionField)).each(function(i){
					if(this.vf==5){
						list.push(this);
					}
				});
				$scope.param.dataTemplate=list;
			});
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
				});
			}); 
		},"STYLE=1");
	}
	
	//选择列表对话框
	$scope.selectFormDef=function(){
		CommonDialog("ywsjmb",function(data){
			$scope.$apply(function(){
				console.info(data);
				$scope.prop.formDefId=data.FORMDEFID;
				$scope.prop.formDefName=data.SUBJECT;
				
				var list=[];
				$(JSON.parse(data.CONDITIONFIELD)).each(function(i){
					if(this.vf==5){
						list.push(this);
					}
				});
				$scope.param.dataTemplate=list;
				//console.info(JSON.parse(data.CONDITIONFIELD));
			}); 
		});
		
		
	}
	
	//保存
	$scope.save=function(){
		var frm=$('#frmSubmit').form();
		if(!frm.valid()) return;
		if(!$scope.prop.treeDialogId||!$scope.prop.formDefId||$scope.prop.field.length<=0){
			$.ligerDialog.error("请正确配置组合规则","提示信息");
			return;
		}
		//因为别名alias可能是自动生成的ngjs会获取不到
		$scope.prop.alias=$("#alias").val();
		
		
		FormDefCombinate.save($scope.prop,function(data){
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
	
	
	//添加
	$scope.createField=function(){
		var json={};
		json.tree=$scope.param.treeColumn;
		
		json.temp=$scope.param.templateColumn;
		if(!json.tree||!json.temp) return;
		$scope.prop.field.push(json);
	}
}]);