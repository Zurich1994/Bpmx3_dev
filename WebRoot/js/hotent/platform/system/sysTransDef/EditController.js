var app = angular.module('app',['baseServices','SysTransDefService','commonListService','arrayToolService']);
app.controller("EditController",['$scope','BaseService','SysTransDef','CommonListService','ArrayToolService',function($scope,BaseService,SysTransDef,CommonListService,ArrayToolService){
	$scope.CommonList=CommonListService;
	$scope.ArrayTool=ArrayToolService;
	
	//对象
	$scope.prop={};
	$scope.prop.state=1;
	
	//页面用到的参数或临时变量
	$scope.param={};
	
	//检查sql语句的合法性
	$scope.checkSqlValidity=function(sql){
		sql=sql.replace(/{authorId}/g,"1");//先给当前用户随便赋值
		SqlUtil.checkValidity(sql,"LOCAL",false);
	}
	
	//初始化
	if(id!=""){
		SysTransDef.detail({id:id},function(data){
			$scope.prop=data;
			
			//初始化脚本编辑器
			InitMirror.getById("selectSql").insertCode(data.selectSql);
			InitMirror.getById("updateSql").insertCode(data.updateSql);
			InitMirror.getById("logContent").insertCode(data.logContent);
		});
	}
	
	//保存
	$scope.save=function(){
		var frm=$('#frmSubmit').form();
		if(!frm.valid()) return;
		
		$scope.prop.selectSql = InitMirror.getById("selectSql").getCode();
		$scope.prop.updateSql = InitMirror.getById("updateSql").getCode();
		$scope.prop.logContent = InitMirror.getById("logContent").getCode();
		
		SysTransDef.save($scope.prop,function(data){
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
				$.ligerDialog.err("操作结果","出错信息",data.message);
			}
		});
	}
	
	//点击常用变量
	$scope.clickField=function(field){
		InitMirror.editor.insertCode(field.value);
	}
	
	//设置常用字段
	$scope.commonField=[
	   {
		   key:"授权人ID",
		   value:"{authorId}"
	   },
	   {
		  key:"授权人名称",
		  value:"{authorName}"
	   },
	   {
		  key:"目的人ID",
		  value:"{targetPersonId}"
	   },
	   {
		   key:"目的人名称",
		   value:"{targetPersonName}"
	   },
	   {
		   key:"所选id列表','分割",
		   value:"{ids}"
	   },
	   {
		   key:"所选name列表','分割",
		   value:"{names}"
	   }
	];
}]);