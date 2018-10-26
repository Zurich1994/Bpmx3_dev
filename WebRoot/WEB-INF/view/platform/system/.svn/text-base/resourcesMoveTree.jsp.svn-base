<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" import="com.hotent.platform.model.system.Resources"%>
<%@include file="/commons/include/html_doctype.html"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>资源管理</title>
<%@include file="/commons/include/form.jsp" %>
<f:link href="tree/zTreeStyle.css"></f:link>
<script type="text/javascript"	src="${ctx}/js/tree/jquery.ztree.js"></script>
<script type="text/javascript">
	/*KILLDIALOG*/
	var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)
	
	var rootId=0;
	//当前访问系统
	//var arg = window.dialogArguments;
	var arg =dialog.get("param");
	
	var systemId=arg.systemId;
	var sourceNode=arg.sourceNode;
	var targetNode;
	
	$(function(){
		//布局
		loadLayout();
		//加载树
		loadTree();
	});
	
	//布局
	function loadLayout(){
		$("#layout").ligerLayout( {
			leftWidth : 200,
			height:'90%',
			onHeightChanged: heightChanged,
			allowLeftResize:false
		});
		//取得layout的高度
        var height = $(".l-layout-center").height();
        $("#resourcesTree").height(height-40);
	};
	//布局大小改变的时候通知tab，面板改变大小
    function heightChanged(options){
     	$("#resourcesTree").height(options.middleHeight-40);
    };
    //树
	var resourcesTree;
    var expandByDepth = 1;
	//加载树
	function loadTree(){
		var setting = {
			async: {enable: false},
			data: {
				key:{name:"resName"},
				simpleData: {
					enable: true,
					idKey: "resId",
					pIdKey: "parentId",
					rootPId: <%=Resources.ROOT_PID%>
				}
			},
			view: {
				selectedMulti: false
			},
			edit: {
				drag: {
					prev: false,inner: false,next: false,isMove:false
				},
				enable: true,
				showRemoveBtn: false,
				showRenameBtn: false
			},
			callback:{
				onClick: zTreeOnLeftClick,
				onRightClick: null,
				beforeDrop: null,
				onDrop: null
			}
		};
		var url="${ctx}/platform/system/resources/getSystemTreeData.ht";
		var params={"systemId":systemId};
		$.post(url,params,function(result){
			resourcesTree=$.fn.zTree.init($("#resourcesTree"), setting,result);
			resourcesTree.removeNode(sourceNode);
            if(expandByDepth!=0)
            {
                var nodes = resourcesTree.getNodesByFilter(function(node){
                    return (node.level==expandByDepth);
                });
                if(nodes.length>0){
                    for(var idx=0;idx<nodes.length;idx++){
                    	resourcesTree.expandNode(nodes[idx],false,false);
                    }
                }
            }
            else
            {
            	resourcesTree.expandAll(true);
            }

		});
	
	};

	//左击
	function zTreeOnLeftClick(event, treeId, treeNode){
		targetNode=treeNode;
	};
	
	function selectNode(){
		if(!targetNode){
			$.ligerDialog.warn('目标节点为空,请选择！');
			return;
		}
		var obj={targetNode:targetNode};
		//window.returnValue=obj;
		dialog.get("sucCall")(obj);
		dialog.close();
	}


</script>
<style type="text/css">
	html,body{ padding:0px; margin:0; width:100%;height:100%;overflow: hidden;} 
</style>
</head>
<body>
	<div id="layout">
		<div title="目标资源" position="center">
			<div id="resourcesTree" class="ztree" style="overflow:auto;"></div>
		</div>
	</div>
	<div position="bottom"  class="bottom" style="margin-top:10px;">
		<a href="javascript:;" class="button"  onclick="selectNode()" style="margin-right:10px;" ><span class="icon ok"></span><span >选择</span></a>
		<a href="javascript:;" class="button" style="margin-left:10px;"  onclick="dialog.close()"><span class="icon cancel"></span><span >取消</span></a>
	</div>
</body>
</html>

