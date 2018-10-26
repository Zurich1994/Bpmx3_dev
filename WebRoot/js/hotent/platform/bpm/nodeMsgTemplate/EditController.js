var app = angular.module('app',['baseServices','NodeMsgTemplateService','commonListService','arrayToolService']);
app.controller("EditController",['$scope','BaseService','NodeMsgTemplate','CommonListService','ArrayToolService',function($scope,BaseService,NodeMsgTemplate,CommonListService,ArrayToolService){
	$scope.CommonList=CommonListService;
	$scope.ArrayTool=ArrayToolService;
	
	$scope.prop={};//prop
	$scope.prop.defId=defId;
	$scope.prop.nodeId=nodeId;
	$scope.prop.parentDefId=parentDefId;
	
	$scope.param={};
	$scope.param.columnNum=1;
	
	if(defId!=""){//获取表
		NodeMsgTemplate.ngjs({action:"getTableByDefId",defId:defId},function(data){
			$scope.param.table=data;
			
			//开始拼装树数据
			var iconFolder = __ctx + '/styles/tree/';
			var treeData=[];
			for(var i=0,c;c=data.fieldList[i++];){//主表字段
				if(c.isHidden == 0){
					c.tableId = data.tableId;
					c.name = c.fieldDesc;
					c.id = c.fieldId;
					c.pId = 0;
					c.icon = iconFolder + c.fieldType + '.png';
					treeData.push(c);
				}
			}
			
			for(var i=0,c;c=data.subTableList[i];i++){//子表字段
				c.icon = iconFolder + 'table.png';
				c.pId = 0;
				c.id = c.tableId;
				c.name=c.tableDesc;
				treeData.push(c);
				for(var j = 0,m;m=c.fieldList[j++];){
					m.id = m.fieldId;
					m.pId = c.tableId;
					m.name = m.fieldDesc;
					m.icon = iconFolder + m.fieldType + '.png';
					m.tableName=c.tableName;//记录是第几张子表，后面取值有用
					treeData.push(m);
				}
			}
			
			var setting = {       				    					
					data: {
						key : {
							name: "name"
						},
						simpleData: {
							enable: true,
							idKey: "id",
							pIdKey: "pId",
							rootPId: 0
						}
					},
					
					callback : {
						beforeClick : function(treeId, treeNode, clickFlag) {
							 var tab = liger.get("tab");
							 
							 var text="";
							 if(!treeNode.dbFieldNameLow){//点击了是表节点，就增加一段
								text=eval("$scope.param.subTableTempMap."+treeNode.tableName);
							 }else if(treeNode.pId==0){//主表字段
								 text="${"+treeNode.dbFieldNameLow+"}";
							 }else{
								 text="${"+treeNode.tableName+"."+treeNode.dbFieldNameLow+"}";
							 }
							 
							 //按照目前焦点在哪里就添加在哪里
							 if(tab.getSelectedTabItemID()=="html"){
								 ue.execCommand("inserthtml",text);
							 }
							 if(tab.getSelectedTabItemID()=="text"){
								 insertText(document.getElementById('text'),text);
							 }
							return false;
						}
					}
				};
			
			fieldTree = $.fn.zTree.init($("#fieldTree"),setting, treeData);
		});
	}
	
	//初始化子表模板
	NodeMsgTemplate.ngjs({action:"getSubTableTemp",defId:defId},function(data){
		$scope.param.subTableTempMap=data;
	});
	
	//初始化
	if(defId!=""){
		NodeMsgTemplate.ngjs({action:"getObject",defId:defId,nodeId:nodeId,parentDefId:parentDefId},function(data){
			if(!data){//新增页面根据模板初始化html
				$scope.initTemp();
				//初始化title
				NodeMsgTemplate.ngjs({action:"getNode",defId:defId,nodeId:nodeId},function(data){
					var title="全局-";
					if(data.nodeName){
						title=data.nodeName+"节点-";
					}
					$scope.prop.title=title+"消息模板";
				});
				return;
			}
			$scope.prop=data;
			ue.ready(function () {
				ue.setContent(data.html);
			});
			$("#text").val(data.text);
		});
	}
	
	//保存
	$scope.save=function(){
		var frm=$('#frmSubmit').form();
		if(!frm.valid()) return;
		
		//获取ngjs model获取不到的内容
		$scope.prop.html=ue.getContent();
		$scope.prop.text=$("#text").val();
		
		if($scope.prop.html==""){
			$.ligerDialog.error("请正确输入html模板");
			return;
		}
		if($scope.prop.text==""){
			$.ligerDialog.error("请正确输入text模板");
			return;
		}
		
		NodeMsgTemplate.save($scope.prop,function(data){
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
				$.ligerDialog.err(data.message);
			}
		});
		
	}
	
	//初始化模板
	$scope.initTemp=function(){
		NodeMsgTemplate.ngjs({action:"getInitTemp",defId:defId,columnNum:$scope.param.columnNum},function(data){
			ue.ready(function () {
				ue.setContent(data.html);
				$("#text").val(data.text);
			});
		});
	}
}]);