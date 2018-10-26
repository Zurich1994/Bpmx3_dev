<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" import="com.hotent.platform.model.system.Resources"%>
<%@include file="/commons/include/html_doctype.html"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>资源管理</title>
<%@include file="/commons/include/form.jsp" %>
<f:link href="tree/zTreeStyle.css"></f:link>
<script type="text/javascript"	src="${ctx}/js/tree/jquery.ztree.js"></script>
<script type="text/javascript">

	var rootId=0;
	
	var foldMenu;
	var leafMenu;
	var systemMenu;
	
	//当前访问系统
	var systemId=null;
	
	$(function(){
		//布局
		loadLayout();
		//菜单
		loadMenu();
		//加载树
		systemId=$("#subSystem").val();
		loadTree();
		//改变子系统
		$("#subSystem").change(function(){
			systemId=$("#subSystem").val();
			loadTree();
		});
	});
	//菜单
	function loadMenu(){
		foldMenu = $.ligerMenu({ top: 100, left: 100, width: 120, items:
        [{ text: '增加节点', click: addNode },
        { text: '编辑节点', click: editNode  },
        { text: '删除节点', click: delNode },
        { text: '导入资源', click: importXml },
        { text: '导出资源', click: exportXml },
		{ text: '节点排序', click: sortNode },
		{ text: '移动节点', click: moveNode }]
        });
		systemMenu=$.ligerMenu({ top: 100, left: 100, width: 120, items:
	        [{ text: '增加节点', click: addNode },
	         { text: '导入资源', click: importXml },
	         { text: '节点排序', click: sortNode }]
	    });

		leafMenu = $.ligerMenu({ top: 100, left: 100, width: 120, items:
        [{ text: '编辑节点', click: editNode  },
        { text: '删除节点', click: delNode },
        { text: '编辑URL', click: editUrl } ]
        });
	};

	//布局
	function loadLayout(){
		$("#layout").ligerLayout( {
			leftWidth : 300,
			onHeightChanged: heightChanged,
			allowLeftResize:false
		});
		//取得layout的高度
        var height = $(".l-layout-center").height();
        $("#resourcesTree").height(height-90);
	};
	//布局大小改变的时候通知tab，面板改变大小
    function heightChanged(options){
     	$("#resourcesTree").height(options.middleHeight -90);
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
				onRightClick: zTreeOnRightClick,
				beforeDrop: null,
				onDrop: null
			}
		};
		var url="${ctx}/platform/system/resources/getSystemTreeData.ht";
		var params={systemId:systemId};
		$.post(url,params,function(result){
			resourcesTree=$.fn.zTree.init($("#resourcesTree"), setting,result);
			resourcesTree.expandAll(false);
			var node = resourcesTree.getNodeByParam("resId","0",null);
			resourcesTree.expandNode(node,true,false,false,false);
			/*
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
            	
            }
          	*/

		});
	
	};
	

	//拖放 前准备
	/*
	function beforeDrop(treeId, treeNodes, targetNode, moveType) {
		if (!treeNodes) return false;
		var drop=true;
		var isFolder=targetNode.isFolder;
		if(moveType=="inner"){
			//判断是否有子节点,没有子节点不充许拖放
			if(isFolder!=1){
				$.ligerDialog.warn(targetNode.resName+'没有子节点,不能接收!','提示信息');
				drop= false;
			}
		}
		return drop;
	};
	
	//拖放 后动作
	function onDrop(event, treeId, treeNodes, targetNode, moveType) {
		
		if(!targetNode) return;
		if(!treeNodes) return;
		var targetId=targetNode.resId;
		var sourceNode=treeNodes[0];
		var sourceId=sourceNode.resId;
		
		var url="${ctx}/platform/system/resources/move.ht";
		var params={targetId:targetId,sourceId:sourceId,moveType:moveType};
		
		$.post(url,params,function(result){
			//作为子节点。
			if("inner"==moveType){
				sourceNode.parentId=targetId;
			}
			else{
				sourceNode.parentId=targetNode.parentId;
			}
		});
	} */


	//左击
	function zTreeOnLeftClick(event, treeId, treeNode){
		var resId=treeNode.resId;
		if(resId==rootId){
			return;
		}
		var url="${ctx}/platform/system/resources/get.ht?resId="+treeNode.resId;
		$("#listFrame").attr("src",url);
	};
	/**
	 * 树右击事件
	 */
	function zTreeOnRightClick(e, treeId, treeNode) {
		foldMenu.hide();
		leafMenu.hide();
		if (treeNode) {
			resourcesTree.selectNode(treeNode);
			var isfolder=treeNode.isFolder;
			var h=$(window).height() ;
			var w=$(window).width() ;
			var menuWidth=120;
			var menuHeight=75;
			
			var menu=null;
			if(isfolder==1){
				if(treeNode.resId!=0){
					menu=foldMenu;
				}else{
					menu=systemMenu;
				}
			}
			else if(isfolder==0){
				menu=leafMenu;
			}
			var x=e.pageX,y=e.pageY;
			if(e.pageY+menuHeight>h){
				y=e.pageY-menuHeight;
			}
			if(e.pageX+menuWidth>w){
				x=e.pageX-menuWidth;
			}
			menu.show({ top: y, left: x });
		}
	};
	
	//展开收起
	function treeExpandAll(type){
		resourcesTree = $.fn.zTree.getZTreeObj("resourcesTree");
		resourcesTree.expandAll(type);
	};
	
	//添加资源
	function addNode(){
		var url="${ctx}/platform/system/resources/edit.ht?parentId="+getSelectNode().resId+"&systemId="+systemId;
		$("#listFrame").attr("src",url);
	};
	
	//移动节点
	function moveNode(){
		var selectNode=getSelectNode();
		if(!selectNode) return;
		var sourceId=selectNode.resId;
		if(sourceId==rootId){
			$.ligerDialog.warn('该节点为系统节点 ,不允许该操作');
			return;
		}
		var url="${ctx}/platform/system/resources/MoveTree.ht";
		var param = {systemId:systemId,sourceNode:selectNode};
		var winArgs="dialogWidth=230px;dialogHeight=450px;help=0;status=0;scroll=0;center=1";
		url=url.getNewUrl();
		/* var obj=window.showModalDialog(url,param,winArgs);
		if(!obj) return;
		var targetId = obj.targetNode.resId;
		url="${ctx}/platform/system/resources/move.ht";
		var params={targetId:targetId,sourceId:sourceId};
		$.post(url,params,function(result){
			loadTree();
		}); */ 
		
		/*KILLDIALOG*/
		DialogUtil.open({ 
			height:550,
			width: 250,
			title : '移动节点',
			url: url, 
			isResize: true,
			//自定义参数
			param: param,
			sucCall:function(obj){
				if(!obj) return;
				var targetId = obj.targetNode.resId;
				url="${ctx}/platform/system/resources/move.ht";
				var params={targetId:targetId,sourceId:sourceId};
				$.post(url,params,function(result){
					loadTree();
				});
			}
		});
	}
	//编辑资源
	function editNode(){
		var selectNode=getSelectNode();
		var resId=selectNode.resId;
		if(resId==rootId){
			$.ligerDialog.warn('该节点为系统节点 ,不允许该操作');
			return;
		}
		var url="${ctx}/platform/system/resources/edit.ht?resId="+selectNode.resId;
		$("#listFrame").attr("src",url);
		
	};
	
	//导入资源
	function importXml(){
		var selectNode=getSelectNode();
		var resId=selectNode.resId;
		
		var obj={resId:resId,systemId:systemId};
		var url=__ctx +"/platform/system/resources/import.ht";
		
		var conf=$.extend({},{dialogWidth:550 ,dialogHeight:200,help:0,status:0,scroll:0,center:1});
		var winArgs="dialogWidth="+conf.dialogWidth+"px;dialogHeight="+conf.dialogHeight
			+"px;help=" + conf.help +";status=" + conf.status +";scroll=" + conf.scroll +";center=" +conf.center;
		
		url=url.getNewUrl();
		//var rtn=window.showModalDialog(url,obj,winArgs);
		
		/*KILLDIALOG*/
		DialogUtil.open({
			height:conf.dialogHeight,
			width: conf.dialogWidth,
			title : '导入资源',
			url: url, 
			isResize: true,
			//自定义参数
			obj: obj
		});
	}
	
	//导出资源
	function exportXml(){
		var selectNode=getSelectNode();
		var resId=selectNode.resId;
		window.location.href="${ctx}/platform/system/resources/exportXml.ht?resId="+resId;
	}
	//节点排序
	function sortNode(){
		var selectNode=getSelectNode();
		var resId=selectNode.resId;
		var url=__ctx +'/platform/system/resources/sortList.ht?resId='+resId+'&systemId='+systemId;
		var winArgs="dialogWidth:600px;dialogHeight:300px";
	 	url=url.getNewUrl();
	 	/* var rtn=window.showModalDialog(url,"",winArgs);
	 	reFresh(); */
	 	
	 	/*KILLDIALOG*/
	 	DialogUtil.open({
	 		height:400,
	 		width: 600,
	 		title : '节点排序',
	 		url: url, 
	 		isResize: true,
	 		//自定义参数
	 		sucCall:function(rtn){
	 			reFresh();
	 		}
	 	});
		
	}
	//删除资源
	function delNode(){
		var selectNode=getSelectNode();
		var resId=selectNode.resId;
		if(resId==rootId){
			$.ligerDialog.warn('该节点为系统节点 ,不允许该操作');
			return;
		}
	 	var callback = function(rtn) {
	 		if(!rtn) return;
	 		var url="${ctx}/platform/system/resources/del.ht";
	 		var params={resId:resId};
	 		$.post(url,params,function(responseText){
	 			var obj=new com.hotent.form.ResultMessage(responseText);
	 			if(obj.isSuccess()){//成功
	 				resourcesTree.removeNode(selectNode);
	 				$("#listFrame").attr("src","about:blank");
	 			}
	 			else{
	 				$.ligerDialog.error( obj.getMessage(),"出错了");
	 			}
	 		});
		};
		$.ligerDialog.confirm('确认删除吗？','提示信息',callback);
	};
	//
	function editUrl(){
		var selectNode=getSelectNode();
		var resId=selectNode.resId;
		var url="${ctx}/platform/system/resourcesUrl/edit.ht?resId="+resId;
		$("#listFrame").attr("src",url);
	};

	//选择资源节点。
	function getSelectNode(){
		resourcesTree = $.fn.zTree.getZTreeObj("resourcesTree");
		var nodes  = resourcesTree.getSelectedNodes();
		var node   = nodes[0];
		return node;
	}
	//刷新
	function reFresh(){
		loadTree();
	};
	
	//添加完成后调用该函数
	function addResource(id,text,icon,isFolder){
		var parentNode=getSelectNode();
		resourcesTree = $.fn.zTree.getZTreeObj("resourcesTree");
		var treeNode= {resId:id,parentId:parentNode.resId,resName:text,icon:icon,isFolder:isFolder};
		resourcesTree.addNodes(parentNode,treeNode);
	}
	//编辑完成后调用该函数。
	function editResource(text,icon,isFolder){
		var selectNode=getSelectNode();
		selectNode.resName=text;
		selectNode.icon=icon;
		selectNode.isFolder=isFolder;
		resourcesTree = $.fn.zTree.getZTreeObj("resourcesTree");
		resourcesTree.updateNode(selectNode);
	}
</script>
<style type="text/css">
	html,body{ padding:0px; margin:0; width:100%;height:100%;overflow: hidden;} 
</style>
</head>
<body >
<div id="layout" >
	<div position="left" title="资源管理" style="text-align: left;" >
		<div style="width:100%;">
		        <select id="subSystem" style="width:99.8% !important;">  
		              <c:forEach var="subSystemItem" items="${subSystemList}">  
		         			<option value="${subSystemItem.systemId}"  <c:if test="${subSystemItem.systemId==currentSystemId}">selected="selected"</c:if> >${subSystemItem.sysName}</option>  
		        	  </c:forEach>  
		        </select>
		</div><div class="tree-toolbar" id="pToolbar">	
				<div class="group"><a class="link reload" id="treeFresh" href="javascript:reFresh();"><span></span></a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link expand" id="treeExpandAll" href="javascript:treeExpandAll(true)" ><span></span></a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link collapse" id="treeCollapseAll" href="javascript:treeExpandAll(false)" ><span></span></a></div>
			
		</div>
		<div id="resourcesTree" class="ztree" style="overflow:auto;clear:left"></div>
	</div>
	<div position="center">
		<iframe id="listFrame" src="" frameborder="no" width="100%" height="100%"></iframe>
	</div>
</div>
</body>
</html>

