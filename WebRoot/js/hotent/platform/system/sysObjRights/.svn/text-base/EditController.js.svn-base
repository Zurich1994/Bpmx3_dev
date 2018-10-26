var app = angular.module('app',['baseServices','SysObjRightsService','commonListService','arrayToolService']);
app.controller("EditController",['$scope','BaseService','SysObjRights','CommonListService','ArrayToolService',function($scope,BaseService,SysObjRights,CommonListService,ArrayToolService){
	$scope.CommonList=CommonListService;
	$scope.ArrayTool=ArrayToolService;
	
	//对象
	$scope.prop={};
	
	//页面用到的参数或临时变量
	$scope.param={};
	
	SysObjRights.getRightType(beanId,function(typeList){
		$scope.param.typeList=typeList;
		
		if(objectId!=""&&objType!=""){
			SysObjRights.getByObjTypeAndObjectId(objType,objectId,function(data){
				//console.info(data);
				for(var i=0;i<data.length;i++){
					var item=data[i];
					if(item.rightType=="all"){
						$scope.param.selectAll=true;
						break;
					}
					var temp=getItemByKey(item.rightType);
					if(!temp.ids) temp.ids="";
					if(!temp.names) temp.names="";
					
					if(temp.ids!=""){
						temp.ids+=",";
					}
					temp.ids+=item.ownerId;
					if(temp.names!=""){
						temp.names+=",";
					}
					temp.names+=item.owner;
				}
			});
		}
	});
	
	//保存
	$scope.save=function(){
		var frm=$('#frmSubmit').form();
		if(!frm.valid()) return;
		
		var sysObjRights=explainType();//把typeList解释成一个一个的right
		
		var json={};
		json.sysObjRights=JSON.stringify(sysObjRights);
		json.objType=objType;
		json.objectId=objectId;
		SysObjRights.save(json,function(data){
			if(data.result==1){
				$.ligerDialog.confirm(data.message+",是否继续操作", "提示信息",
					function(rtn) {
						if (rtn) {
							window.location.reload();
						} else {
							frameElement.dialog.close();
						}
					});
			}else{
				$.ligerDialog.err("提示信息","保存出错了",data.message );
			}
		});
	}
	
	$scope.showSeletor=function(item){
		$scope.param.selectItem=item;
		switch(item.key)
		{
			case "all":
				break;
			case "user":
				UserDialog({
					selectUserIds:item.ids,
		    	    selectUserNames:item.names,
					callback:selectorCallBack
				});
				break;
			case "org":
				OrgDialog({
					ids:item.ids,
			    	names:item.names,
					callback:selectorCallBack
				});
				break;
			case "orgSub":
				OrgDialog({
					ids:item.ids,
			    	names:item.names,
					callback:selectorCallBack
				});
				break;
			case "role":
				RoleDialog({
					ids:item.ids,
			    	names:item.names,
					callback:selectorCallBack
				});
				break;
			case "pos":
				PosDialog({
					ids:item.ids,
			    	names:item.names,
					callback:selectorCallBack
				});
				break;
		}
	}
	
	//选择器的回调函数
	function selectorCallBack(ids,names){
		//立即生效
		$scope.$apply(function(){
			$scope.param.selectItem.names=names;
		});
		$scope.param.selectItem.ids=ids;
	}
	
	//把typeList解释成一个一个的right
	function explainType(){
		var sysObjRights=[];//把typeList解释成一个一个的right
		if(!$scope.param.selectAll){
			for(var i=0;i<$scope.param.typeList.length;i++){
				var type=$scope.param.typeList[i];
				
				if(!type.ids||!type.names) continue;
				var ids = type.ids.split(",");
				var names = type.names.split(",");
				for(var j=0;j<ids.length;j++){
					var right={};
					right.objType=objType;
					right.objectId=objectId;
					right.rightType=type.key;
					right.ownerId=ids[j];
					right.owner=names[j];
					sysObjRights.push(right);
				}
			}
		}else{
			var right={};
			right.objType=objType;
			right.objectId=objectId;
			right.rightType="all";
			right.ownerId="1";
			right.owner="";
			sysObjRights.push(right);
		}
		return sysObjRights;
	}
	
	function getItemByKey(key){
		var list=$scope.param.typeList;
		for(var i=0;i<list.length;i++){
			if(list[i].key==key){
				return list[i];
			}
		}
		return null;
	}
}]);