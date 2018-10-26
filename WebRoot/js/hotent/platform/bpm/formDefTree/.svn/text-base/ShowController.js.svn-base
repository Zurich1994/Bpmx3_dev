var app = angular.module('app',['baseServices','FormDefTreeService','commonListService','arrayToolService']);
app.controller("ShowController",['$scope','BaseService','FormDefTree','CommonListService','ArrayToolService',function($scope,BaseService,FormDefTree,CommonListService,ArrayToolService){
	$scope.CommonList=CommonListService;
	$scope.ArrayTool=ArrayToolService;
	
	$scope.prop={};//prop
	
	$scope.param={};
	
	//初始化对象
	FormDefTree.getObject({alias:alias},function(data){
		if(data){
			$scope.prop=data;
			formDefId=data.formDefId;//初始化全局变量  formDefId
			
			if(data.loadType==0){//同步加载
				ztreeCreator = new ZtreeCreator('tree',"getObject.ht?getTreeByFormDefId="+formDefId);
			}else{//异步加载
				var conf={
					enable: true,
					url: "getObject.ht?getTreeByFormDefId="+formDefId,
					autoParam: ["id"]
				}; 
				ztreeCreator = new ZtreeCreator('tree')
				.setAsync(conf);
			}
			ztreeCreator.setCallback({
				onRightClick:zTreeOnRightClick,
				onClick: zTreeOnClick
			}).initZtree();
			
			//获取dataTemplate别名
			FormDefTree.getObject({getFormKeyByFormDefId:formDefId},function(data){
				$scope.param.formKey=data.alias;
			});
			
			//暴力改树标题
			$('.l-layout-left').find('.l-layout-header-inner').text(data.name);
			$('.l-layout-left').css("overflow","auto");//强行设置滚动条
		}
	});
	
	
	//树左击事件
	function zTreeOnClick(event, treeId, treeNode) {
		var url=__ctx +"/platform/form/bpmDataTemplate/detailData_"+$scope.param.formKey+".ht?__pk__="+treeNode.id;
		$("#centerFrame").attr("src",url);
	};
	
	//右击菜单_焦点在treeNode上
	var treeNodeMenu=$.ligerMenu({ top: 100, left: 100, width: 120, items:
        [{ text: '新增', click:clickHandler },
	        { text: '编辑', click:clickHandler},
	        {text:'删除',click:clickHandler},
	        {text:'级联删除',click:clickHandler},
	        { text: '刷新树', click:clickHandler}]
	});
	
	//右击菜单_焦点在空白处
	var emptyMenu=$.ligerMenu({ top: 100, left: 100, width: 120, items:
        [{ text: '新增', click:clickHandler },
         { text: '刷新树', click:clickHandler}]
	});
	//右击菜单_焦点在空白处绑定时间
	$(document).bind("contextmenu", function (e){
		selectedTreeNode=null;
		treeNodeMenu.hide();
		emptyMenu.show({ top: e.pageY, left: e.pageX });
        return false;
    });
	
	var selectedTreeNode=null;//右击菜单选中的node
	//树右击事件
	function zTreeOnRightClick(e, treeId, treeNode) {
		emptyMenu.hide();
		selectedTreeNode=treeNode;
		treeNodeMenu.show({ top: e.pageY, left: e.pageX });
	};
	
	//右击菜单项点击事件
	function clickHandler(item){
		var txt=item.text;
		if(txt=="新增"){
			addNode();
		}else if(txt=="编辑"){
			editNode();
		}else if(txt=="删除"){
			delNode();
		}else if(txt=="级联删除"){
			cascadeDelNode();
		}else if(txt=="刷新树"){
			$scope.refreshTheTree();
		}
	}
	
	/**
	 * 下面是右击菜单项的对应事件
	 */
	//新增
	function addNode(){
		//获取parentId字段名
		FormDefTree.getObject({getFieldById:$scope.prop.parentId},function(data){
			var url=__ctx +"/platform/form/bpmDataTemplate/editData_"+$scope.param.formKey+".ht";
			$("#centerFrame").attr("src",url);
			var pid=$scope.prop.rootId;
			if(selectedTreeNode){//是没节点新增就不用设父节点
				pid=selectedTreeNode.id;
			}
			$("#centerFrame").on("load",function(){
				$(this.contentWindow.document).find("[name$=':"+data.fieldName+"']").val(pid);
				$(this).unbind("load");//超级关键，绑定后需要取消绑定！！不然在编辑的时候都会赋值
			});
		});
	}
	//编辑
	function editNode(){
		var url=__ctx +"/platform/form/bpmDataTemplate/editData_"+$scope.param.formKey+".ht?__pk__="+selectedTreeNode.id;
		$("#centerFrame").attr("src",url);
	}
	//删除
	function delNode(){
		$.ligerDialog.confirm("您确定要删除?","提示信息",function(rtn){
			if(!rtn) return;
			FormDefTree.delData(selectedTreeNode.id,$scope.param.formKey,function(data){
				selectedTreeNode=selectedTreeNode.getParentNode();//焦点选择到他的父节点
				$scope.refreshTheTree();
			});
		});
	}
	//级联删除
	function cascadeDelNode(){
		$.ligerDialog.confirm("您确定要删除?","提示信息",function(rtn){
			if(!rtn) return;
			var ids=""+selectedTreeNode.id;
			//递归出所有IDS
			recursionChildren(selectedTreeNode,function(data){
				ids+=","+data.id;
			});
			
			FormDefTree.delData(ids,$scope.param.formKey,function(data){
				selectedTreeNode=selectedTreeNode.getParentNode();//焦点选择到他的父节点
				$scope.refreshTheTree();
			});
		});
	}
	
	//递归所有节点，为级联删除做准备
	function recursionChildren(node,callBack){
		$(node.children).each(function(i){
			callBack(this);
			recursionChildren(this,callBack);
		});
	}
	
	//刷新树
	$scope.refreshTheTree=function(){
		if(!selectedTreeNode||$scope.prop.loadType==0){//没有选择节点，和同步加载只能刷新整棵树
			ztreeCreator.initZtree();
		}else{//异步加载
			var node=selectedTreeNode;
			if(selectedTreeNode.getParentNode()){
				node=selectedTreeNode.getParentNode();
			}
			ztreeCreator.getTreeObj().reAsyncChildNodes(node, "refresh", false);
		}
	}
	
}]);