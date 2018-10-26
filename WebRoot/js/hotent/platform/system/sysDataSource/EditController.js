var app = angular.module('app',['baseServices','SysDataSourceDefService','SysDataSourceService','commonListService','arrayToolService']);
app.controller("EditController",['$scope','BaseService','SysDataSourceDef','SysDataSource','CommonListService','ArrayToolService',function($scope,BaseService,SysDataSourceDef,SysDataSource,CommonListService,ArrayToolService){
	$scope.CommonList=CommonListService;
	$scope.ArrayTool=ArrayToolService;
	
	$scope.prop={};//prop 等于一个数据源初始化
	
	//如果id为空，获取数据源模板
	if(id==""){
		SysDataSourceDef.getAll(function(data){
			$scope.sysDataSourceDefs=data;
		});
	}else{
		SysDataSource.detail(id,function(data){
			$scope.prop=data;
			$scope.prop.settingJson=JSON.parse(data.settingJson);
		});
	}
	
	$scope.save=function(prop){
		var frm=$('#sysDataSourceForm').form();
		if(!frm.valid()) return;
		SysDataSource.save(prop,function(data){
			if(data.result==1){
				$.ligerDialog.confirm(data.message+",是否继续操作", "提示信息",
					function(rtn) {
						if (rtn) {
							window.location.reload();
						} else {
							window.location.href = "list.ht";
						}
					});
			}
			else{
				$.ligerDialog.err(data.message, "提示信息");
			}
		});
	}
	
	//监听这个数据源对应的数据源模板，改变了就设置
	$scope.sysDataSourceDefChange=function(newVal){
		$scope.prop.settingJson=JSON.parse(newVal.settingJson);
		$scope.prop.classPath=newVal.classPath;
		$scope.prop.initMethod=newVal.initMethod;
		$scope.prop.closeMethod=newVal.closeMethod;
		
		$scope.dbTypeChange($scope.prop.dbType);//刷新一次数据库类型
		$scope.aliasChange($scope.prop.alias);//刷新一次别名
	}
	
	$scope.aliasChange=function(val){
		if($scope.prop.settingJson==null) return;
		for(var i=0;i<$scope.prop.settingJson.length;i++){
			var attr = $scope.prop.settingJson[i];
			if(attr.name.toLowerCase().indexOf("alias")!=-1){
				attr.value=val;
			} 
		}
	}
	
	//数据库类型改变，那么就初始化对应的字段
	$scope.dbTypeChange=function(dbType){
		for(var i =0;i<$scope.dbTypeList.length;i++){
			if($scope.prop.settingJson==null) return;
			
			var d = $scope.dbTypeList[i];
			if(d.value!=dbType) continue;
			for(var j=0;j<$scope.prop.settingJson.length;j++){
				var attr = $scope.prop.settingJson[j];
				if(attr.name.toLowerCase().indexOf("url")!=-1){
					attr.value=d.url;
				}
				else if(attr.name.toLowerCase().indexOf("driver")!=-1){
					attr.value=d.driverName;
				}
			}
		}
	}
	
	//测试连接
	$scope.checkConnection=function(prop){
		SysDataSource.checkConnection(prop,function(data){
			alert(data.message);
		});	
	}
	
	//数据库类型数组
	$scope.dbTypeList=[
		{
			value:'mysql',
			driverName:'com.mysql.jdbc.Driver',
			url:'jdbc:mysql://主机:3306/数据库名?useUnicode=true&characterEncoding=utf-8'
		},
		{
			value:'oracle',
			driverName:'oracle.jdbc.OracleDriver',
			url:'jdbc:oracle:thin:@主机:1521:数据库实例'
		},
		{
			value:'mssql',
			driverName:'com.microsoft.sqlserver.jdbc.SQLServerDriver',
			url:'jdbc:sqlserver://主机:1433;databaseName=数据库名;'
		}
	];
}]);