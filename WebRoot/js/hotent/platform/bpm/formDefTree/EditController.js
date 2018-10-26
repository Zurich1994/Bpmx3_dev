var app = angular.module('app',['baseServices','FormDefTreeService','commonListService','arrayToolService']);
app.controller("EditController",['$scope','BaseService','FormDefTree','CommonListService','ArrayToolService',function($scope,BaseService,FormDefTree,CommonListService,ArrayToolService){
	$scope.CommonList=CommonListService;
	$scope.ArrayTool=ArrayToolService;
	
	$scope.prop={};//prop
	$scope.prop.formDefId=formDefId;
	$scope.prop.loadType=0;
	$scope.prop.rootId=0;
	
	$scope.param={};
	
	//初始化表数据
	FormDefTree.getObject({getTableByFormdefId:formDefId},function(data){
		if(!data.isExternal){//不是外部表就添加这个，因为内部表的field字段不包含其主键。。坑！
			data.fieldList.push({fieldId:0,fieldDesc:"ID"});
		}
		$scope.param.table=data;
	});
	
	//初始化对象
	FormDefTree.getObject({formDefId:formDefId},function(data){
		if(data){
			$scope.prop=data;
		}
	});
	
	//保存
	$scope.save=function(){
		var frm=$('#frmSubmit').form();
		if(!frm.valid()) return;
		
		if(!$scope.prop.treeId||!$scope.prop.parentId||!$scope.prop.displayField){
			$.ligerDialog.error("请正确选择树主键字段,树父主键字段,显示字段","提示信息");
			return;
		}
		
		FormDefTree.save($scope.prop,function(data){
			if(data.result==1){
				$.ligerDialog.confirm(data.message+",是否继续操作", "提示信息",
					function(rtn) {
						if (rtn) {
							window.location.reload();
						} else {
							dialog.close();
						}
					});
			}else{
				$.ligerDialog.err("提示信息","保存出错了",data.message);
			}
		});
	}
	
	//加载方式列表
	$scope.loadTypeList=[
	   {
		   key:"同步",
		   value:0
	   },
	   {
		   key:"异步",
		   value:1
	   }
	];
}]);