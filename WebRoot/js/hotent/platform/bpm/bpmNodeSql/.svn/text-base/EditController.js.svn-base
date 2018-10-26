var app = angular.module('app',['baseServices','SysDataSourceService','BpmNodeSqlService','commonListService','arrayToolService']);
app.controller("EditController",['$scope','BaseService','SysDataSource','BpmNodeSql','CommonListService','ArrayToolService',function($scope,BaseService,SysDataSource,BpmNodeSql,CommonListService,ArrayToolService){
	$scope.CommonList=CommonListService;
	$scope.ArrayTool=ArrayToolService;
	$scope.prop={};//prop 等于一个bpmnodesql对象
	
	//初始化
	$scope.prop.dsAlias="dataSource_Default";
	$scope.prop.actdefId=actdefId;
	$scope.prop.nodeId=nodeId;
	$scope.prop.sql="";
	
	SysDataSource.getAllSysDS(function(data){
		$scope.allSysDS = data;
	});
	
	//初始化
	var json ={};
	json.actdefId=actdefId;
	json.nodeId=nodeId;
	json.id=id;
	
	//获取table
	BpmNodeSql.getTable(json,function(data){
		if(data){
			var json={};
			if(data.isExternal==0){
				json.fieldDesc="主键";
				json.dbFieldName="id";
				data.fieldList.push(json);
			}
			$scope.table=data;
		}
	});
	
	//判断节点的类型，取对应的action列表
	BpmNodeSql.getNodeType(json,function(data){
		if(data){
			if(data.nodeType=="startEvent"){
				$scope.actionList=startActionList;
				$scope.prop.action="submit";
			}else if(data.nodeType=="endEvent"){
				$scope.actionList=endActionList;
				$scope.prop.action="end";
			}else{
				$scope.actionList=normalActionList;
				$scope.prop.action="agree";
			}
			
		}
	});
	
	//有ID才初始化
	if(id!=""){
		BpmNodeSql.detail(json,function(data){
			if(data){
				$scope.prop=data;
			}
		});
	}
	
	$scope.rollback=true;
	//检查sql语句的合法性
	$scope.checkSqlValidity=function(){
		SqlUtil.checkValidity($scope.prop.sql,$scope.prop.dsAlias,$scope.rollback);
	}
	
	//保存
	$scope.save=function(){
		var frm=$('#frmSubmit').form();
		if(!frm.valid()) return;
		BpmNodeSql.save($scope.prop,function(data){
			if(data.result==1){
				$.ligerDialog.confirm(data.message+",是否继续操作", "提示信息",
					function(rtn) {
						if (rtn) {
							window.location.reload();
						} else {
							closeWin();
						}
					});
			}else{
				$.ligerDialog.err(data.message, "提示信息");
			}
		});
	}
	
	//点击主表字段时添加到sql
	$scope.appendSql=function(field){
		var temp="";
		if(field.dbFieldName){
			temp="<#"+field.dbFieldName+"#>";
		}
		if(field.value){
			temp="<#"+field.value+"#>";
		}
		insertText(document.getElementById('sqlText'),temp);
		$scope.prop.sql=document.getElementById('sqlText').value;//改变ngmodel对应的值
	}
	
	//触发时机的列表
	//普通节点
	normalActionList=[
	   {
		   key:"同意",
		   value:"agree"
	   },
	   {
		   key:"反对",
		   value:"opposite"
	   },
	   {
		   key:"驳回",
		   value:"reject"
	   },
	   {
		   key:"删除",
		   value:"delete"
	   }
	];
	
	//开始节点列表
	startActionList=[
 	   {
 		   key:"提交",
 		   value:"submit"
 	   },
 	   {
 		   key:"保存",
 		   value:"save"
 	   }
 	];
	//结束节点列表
	endActionList=[
	   {
		   key:"结束",
		   value:"end"
	   }
	];
	//流程字段
	$scope.flowFieldList=[
	   {
		   key:"流程defID",
		   value:"defId"
	   },
	   {
		  key:"表单Key",
		  value:"formKey"
	   },
	   {
		  key:"当前用户名称",
		  value:"curUserName"
	   },
	   {
		   key:"当前用户ID",
		   value:"curUserId"
	   },
	   {
		   key:"流程actDefId	",
		   value:"actDefId"
	   },
	   {
		   key:"流程运行runId",
		   value:"runId"
	   },
	   {
		   key:"流程任务taskID",
		   value:"taskId"
	   }
	];
}]);