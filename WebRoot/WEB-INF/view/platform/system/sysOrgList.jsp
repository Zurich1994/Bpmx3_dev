<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://www.jee-soft.cn/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>组织架构管理</title>
<%@include file="/commons/include/get.jsp"%>
<f:link href="tree/zTreeStyle.css"></f:link>

<script type="text/javascript" src="${ctx }/js/tree/jquery.ztree.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysOrgSearch.js"></script>
<script type="text/javascript">
	var orgTree; //树
	var menu;
	var menu_root;
	var height;
	var expandDepth = 2; 
	var type ="system";
	var typeVal="all";
	//左击的树节点
	var selectNode;
	
	$().ready(function (){
		$("#layout").ligerLayout({
			leftWidth : 225,
			height : '100%',
			allowLeftResize : false
		}); 
		
		height = $('#layout').height();
		$("#viewFrame").height(height - 25);
		$('#demensionId').change(function() {
			var demensionId = $(this).val();
			loadTree(demensionId);
		});
		$("#treeReFresh").click(function() {
			var demensionId = $("#demensionId").val();
			loadTree(demensionId);
		});

		$("#treeExpand").click(function() {
			orgTree = $.fn.zTree.getZTreeObj("orgTree");
			var treeNodes = orgTree.transformToArray(orgTree.getNodes());
			for(var i=1;i<treeNodes.length;i++){
				if(treeNodes[i].children){
					orgTree.expandNode(treeNodes[i], true, false, false);
				}
			}
		});
		$("#treeCollapse").click(function() {
			orgTree.expandAll(false);
		});
		//查询
		$("#treeSearch").click(function(){
			SysOrgSearch({callback:selectOrg});
		});
		//菜单
		getMenu();
		//首先加载行政维度
		loadTree(1);
		$("#demensionId").val(1);
		$("#orgTree").height(height-95);
	});
	
	function selectOrg(orgId){
		var node=orgTree.getNodeByParam("orgId", orgId, null);
		orgTree.selectNode(node);
		$("#viewFrame").attr("src", "get.ht?orgId=" + orgId);
	}
	
	//刷新
	function refreshNode(){
		var selectNode=getSelectNode();
		reAsyncChild(selectNode);
	};
	//刷新节点
	function reAsyncChild(targetNode){
		var orgId=targetNode.orgId;
		if(orgId==0){
			loadTree(selid);
		}else{
			orgTree = $.fn.zTree.getZTreeObj("orgTree");
			if(targetNode.isParent){
				orgTree.reAsyncChildNodes(targetNode, "refresh", false);
			}else{
				var targetParentNode = orgTree.getNodeByParam("orgId",targetNode.orgSupId, null);
				orgTree.reAsyncChildNodes(targetParentNode, "refresh", false);				
			}
		}
	};
	
	function loadTree(selid) {
		var setting = {
				data: {
					key : {
						name: "orgName" // orgPathname
					},
				
					simpleData: {
						enable: true,
						idKey: "orgId",
						pIdKey: "orgSupId",
						rootPId: 0
					}
				},
				// 拖动
				edit : {
					enable : true,
					showRemoveBtn : false,
					showRenameBtn : false,
					drag : {
						prev : true,
						inner : true,
						next : true,
						isMove : true,
						isCopy : true
					}
				},
				view : {
					selectedMulti : false
				},
				async: {
					enable: true,
					url:"${ctx}/platform/system/sysOrg/getTreeData.ht?demId="+selid+"&type="+type+"&typeVal="+typeVal,
					autoParam:["orgId","orgSupId"],
					dataFilter: filter
				},
				callback:{
					onClick : zTreeOnLeftClick,
					onRightClick : zTreeOnRightClick,
					beforeDrop : beforeDrop,
					onDrop : onDrop,
					onAsyncSuccess: zTreeOnAsyncSuccess
				}
				
			};
			orgTree=$.fn.zTree.init($("#orgTree"), setting);
	};
	
	//过滤节点
	function filter(treeId, parentNode, childNodes) {
			if (!childNodes) return null;
			for (var i=0, l=childNodes.length; i<l; i++) {
				var node = childNodes[i];
				if (node.isRoot == 1) {
					node.icon = __ctx + "/styles/default/images/icon/root.png";
				} else {
					if (node.ownUser == null || node.ownUser.length < 1) {
					//	node.orgName += "[未]";
					}
// 					setIcon(node);
				}
			}
			return childNodes;
	};
	
	//判断是否为子结点,以改变图标	
	function zTreeOnAsyncSuccess(event, treeId, treeNode, msg) {
		if(treeNode){
	  	 	var children=treeNode.children;
		  	 if(children.length==0){
		  		treeNode.isParent=true;
		  		orgTree = $.fn.zTree.getZTreeObj("orgTree");
		  		orgTree.updateNode(treeNode);
		  	 }
		}
	};
	
	function setIcon(node){
		if(node.iconPath.length>0)
			node.icon =node.iconPath ;
		
		/*
		switch (node.orgType) {
			case 1:
				node.icon = __ctx+ "/styles/default/images/icon/group.gif";
				break;
			case 2:
				node.icon = __ctx
						+ "/styles/default/images/icon/org.gif";
				break;
			case 3:
				node.icon = __ctx
						+ "/styles/default/images/icon/dep.gif";
				break;
			case 4:
				node.icon = __ctx
						+ "/styles/default/images/icon/unit.gif";
				break;
			case 5:
				node.icon = __ctx
						+ "/styles/default/images/icon/icon-reload.gif";
				break;
		}
		*/
	}

	//拖放 前准备
	function beforeDrop(treeId, treeNodes, targetNode, moveType) {

		if (!treeNodes)
			return false;
		if (targetNode.isRoot == 1)
			return false;
		return true;
	};

	//左击事件
	function zTreeOnLeftClick(event, treeId, treeNode) {
		var isRoot = treeNode.isRoot;
		if (isRoot == 1) {
			return;
		}
		selectNode=treeNode
		var orgId = treeNode.orgId;
		$("#viewFrame").attr("src", __ctx+"/platform/system/sysUserOrg/userList.ht?orgId=" + orgId);
	};

	/**
	 * 右击事件
	 */
	function zTreeOnRightClick(e, treeId, treeNode) {
		orgTree.selectNode(treeNode);
		menu.hide();
	 	menu_root.hide();
		if (treeNode.isRoot == 1) {//根节点时，把删除和编辑隐藏掉
			menu_root.show({
				top : e.pageY,
				left : e.pageX
			});
		} else {
			justifyRightClickPosition(e);
			menu.show({
				top : e.pageY,
				left : e.pageX
			});
		}
	};

	//右键菜单
	function getMenu() {
		menu = $.ligerMenu({
			top : 100,
			left : 100,
			width : 100,
			items:<f:menu>
				[ 
				  {
					text : '增加组织',
					click : 'addNode',
					alias:'addOrg'
				}, {
					text : '编辑',
					click : 'editNode',
					alias:'editOrg'
				}, {
					text : '管理员设置',
					click : 'orgAuth',
					alias:'editOrg'
				},{
					text : '参数属性',
					click : 'orgParam',
					alias:''
				}, {
					text : '排序',
					click : 'sortNode',
					alias:''
				}, {
					text : '删除',
					click : 'delNode',
					alias:'delOrg'
				}, {
					text : '刷新',
					click : 'refreshNode',
					alias:''
				},{
					text : '增加岗位',
					click : 'addPosNode',
					alias:''
				}
				]
				</f:menu>       
		});

		menu_root = $.ligerMenu({
			top : 100,
			left : 100,
			width : 100,
			items : [ {
				text : '增加',
				click : addNode
			},{
				text : '排序',
				click : sortNode
			} ]
		});
	};
	//编辑组织分级管理员
	function orgAuth() {
		orgTree = $.fn.zTree.getZTreeObj("orgTree");
		var nodes = orgTree.getSelectedNodes();
		var treeNode = nodes[0];
		var orgId = treeNode.orgId;
		var url = __ctx + "/platform/system/orgAuth/list.ht?orgId="+ orgId; 
		$("#viewFrame").attr("src", url);
	};
	//编辑组织参数属性
	function orgParam() {
		orgTree = $.fn.zTree.getZTreeObj("orgTree");
		var nodes = orgTree.getSelectedNodes();
		var treeNode = nodes[0];
		var orgId = treeNode.orgId;
		var url = __ctx + "/platform/system/sysOrgParam/editByOrgId.ht?orgId="+ orgId;
		$("#viewFrame").attr("src", url);
	};
	//排序
	function sortNode(){
		//关闭菜单
	 	menu.hide();
	 	menu_root.hide();
		var treeNode=getSelectNode();
		var orgId = treeNode.orgId;
		var demId = treeNode.demId;
		var url=__ctx +'/platform/system/sysOrg/sortList.ht?orgId='+orgId+"&demId="+demId;
		var winArgs="dialogWidth:600px;dialogHeight:300px";
	 	url=url.getNewUrl();
	 	/* var rtn=window.showModalDialog(url,"",winArgs);
	 	var demensionId = $("#demensionId").val();
		loadTree(demensionId); */
	 	/*KILLDIALOG*/
	 	DialogUtil.open({
	 		height:300,
	 		width: 600,
	 		title : '排序',
	 		url: url, 
	 		isResize: true,
	 		//自定义参数
	 		sucCall:function(rtn){
	 			var demensionId = $("#demensionId").val();
	 			loadTree(demensionId);
	 		}
	 	});
		
	}
	function getSelectNode(){
		orgTree = $.fn.zTree.getZTreeObj("orgTree");
		var nodes = orgTree.getSelectedNodes();
		var treeNode = nodes[0];
		return treeNode;
	}
	
	function gradeManage(){
		var treeNode=getSelectNode();
		var orgId = treeNode.orgId;
		var url = __ctx + "/platform/system/grade/manage.ht?orgId="+ orgId;
		$("#viewFrame").attr("src", url);
	}
	
	 //增加岗位
	function addPosNode() {
		var treeNode=getSelectNode();
		var orgId = treeNode.orgId;
		var demId = treeNode.demId;
		var url = __ctx + "/platform/system/position/edit.ht?orgId=" + orgId + "&demId=" + demId + "&action=add";
		$("#viewFrame").attr("src", url);
	};
	
     //增加组织
	function addNode() {
		var treeNode=getSelectNode();
		var orgId = treeNode.orgId;
		var demId = treeNode.demId;
		var url = "edit.ht?orgId=" + orgId + "&demId=" + demId + "&action=add";
		$("#viewFrame").attr("src", url);
	};

	//编辑节点
	function editNode() {
		var treeNode=getSelectNode();
		var orgId = treeNode.orgId;//如果是新增时它就变成父节点Id	      
		var demId = treeNode.demId;
		var url = "edit.ht?orgId=" + orgId + "&demId=" + demId + "&flag=upd";
		$("#viewFrame").attr("src", url);
	};

	function delNode() {
		var treeNode=getSelectNode();
		var callback = function(rtn) {
			if (!rtn) return;
			var params = "orgId=" + treeNode.orgId;
			$.post("orgdel.ht", params, function() {
				orgTree.removeNode(treeNode);
			});
		};
		$.ligerDialog.confirm("确认要删除此组织吗，其下组织也将被删除？", '提示信息', callback);

	};

	//拖放 后动作
	function onDrop(event, treeId, treeNodes, targetNode, moveType) {
		if (targetNode == null || targetNode == undefined)	return;
		var targetId = targetNode.orgId;
		var dragId = treeNodes[0].orgId;
		var url = __ctx + "/platform/system/sysOrg/move.ht";
		var params = {
			targetId : targetId,
			dragId : dragId,
			moveType : moveType
		};

		$.post(url, params, function(result) {
			var demensionId = $("#demensionId").val();
			loadTree(demensionId);
		});
	}
</script>
<style type="text/css">
	html,body{ padding:0px; margin:0; width:100%;height:100%;overflow: hidden;} 
</style>
</head>
<body>

	<div id="layout" style="bottom: 1; top: 1">
		<div position="left" title="组织机构管理" id="rogTree"
			style="height: 100%; width: 100% !important;">
			<div style="width: 100%;">
				<select id="demensionId" style="width: 99.8% !important;">
					<option value="0">---------全部---------</option>
					<c:forEach var="dem" items="${demensionList}">
						<option value="${dem.demId}">${dem.demName}</option>
					</c:forEach>
				</select>
			</div>
			<div class="tree-toolbar" id="pToolbar">
				<div class="toolBar"
					style="text-overflow: ellipsis; overflow: hidden; white-space: nowrap">
					<div class="group" >
						<a class="link reload" id="treeReFresh" ></a>
					</div>
					<div class="l-bar-separator"></div>
					<div class="group" >
						<a class="link expand" id="treeExpand" ></a>
					</div>
					<div class="l-bar-separator"></div>
					<div class="group" >
						<a class="link collapse" id="treeCollapse" ></a>
					</div>
					<div class="l-bar-separator"></div>
					<div class="group" >
						<a class="link search" id="treeSearch" ></a>
					</div>
				</div>
			</div>
			<ul id="orgTree" class="ztree" style="overflow:auto;"></ul>
		</div>
		<div position="center" id="orgView" style="height: 100%;">
			<div class="l-layout-header">组织架构信息</div>
			<iframe id="viewFrame" src="getEmpty.ht" frameborder="0" width="100%"
				height="100%" scrolling="auto"></iframe>
		</div>
	</div>

</body>
</html>


