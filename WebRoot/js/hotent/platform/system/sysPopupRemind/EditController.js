var app = angular.module('app',['baseServices','SysPopupRemindService','SysDataSourceService','commonListService','arrayToolService']);
app.controller("EditController",['$scope','BaseService','SysPopupRemind','SysDataSource','CommonListService','ArrayToolService',function($scope,BaseService,SysPopupRemind,SysDataSource,CommonListService,ArrayToolService){
	$scope.CommonList=CommonListService;
	$scope.ArrayTool=ArrayToolService;
	
	$scope.prop={};//prop 等于一个数据源初始化
	$scope.prop.dsalias="dataSource_Default";
	$scope.prop.enabled=1;
	$scope.prop.sn=1;
	
	//初始化
	if(id!=""){
		var json={};
		json.id=id;
		SysPopupRemind.detail(json,function(data){
			if(data){
				$scope.prop=data;
				var date = $scope.prop.createTime;
				$scope.prop.createTime=new Date((date).replace(new RegExp("-","gm"),"/")).getTime()
			}
		});
	}
	
	//保存
	$scope.save=function(){
		var frm=$('#frmSubmit').form();
		if(!frm.valid()) return;
		SysPopupRemind.save($scope.prop,function(data){
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
				$.ligerDialog.err(data.message, "提示信息");
			}
		});
	}
	
	//点击主表字段时添加到sql
	$scope.appendSql=function(val){
		var temp="";
		insertText(document.getElementById('sqlText'),val);
		$scope.prop.sql=document.getElementById('sqlText').value;//改变ngmodel对应的值
	}
	
	//获取数据源列表
	SysDataSource.getAllSysDS(function(data){
		$scope.allSysDS = data;
	});
	
	$scope.rollback=true;
	//检查sql语句的合法性
	$scope.checkSqlValidity=function(){
		SqlUtil.checkValidity($scope.prop.sql.replace(/({curUserId})/g,'1'),$scope.prop.dsAlias,$scope.rollback);
	}
}]);