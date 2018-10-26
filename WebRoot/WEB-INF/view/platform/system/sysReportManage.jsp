
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.hotent.platform.model.system.GlobalType"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>报表模板管理</title>
<%@include file="/commons/include/get.jsp" %>
<f:link href="tree/zTreeStyle.css"></f:link>
<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerMenu.js"></script>
<script type="text/javascript" src="${ctx}/js/tree/jquery.ztree.js"></script>
<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerLayout.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/GlobalMenu.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/GlobalType.js"></script>

	<style type="text/css"> 
    html,body{ padding:0px; margin:0; width:100%;height:100%;overflow: hidden;}  
 </style>	

<script type="text/javascript">
	var catKey="<%=GlobalType.CAT_REPORT%>";
	var curMenu;
	var reportTypeMenu=new ReportTypeMenu();
	var globalType=new GlobalType(catKey,"glTypeTree",
			{
				onClick:treeClick,
				onRightClick:zTreeOnRightClick,
				expandByDepth:1
			}
	);
	
	$(function() {

		$("#defLayout").ligerLayout({ leftWidth:210,height: '100%',allowLeftResize:false});
		globalType.loadGlobalTree();

	});

	//展开收起
	function treeExpandAll(type) {
		globalType.treeExpandAll(type);
	};
	
	function treeClick(treeNode) {
		var typeId=treeNode.typeId;
		var parentId = treeNode.parentId;
		var url = "${ctx}/platform/system/sysReport/list.ht";
		if(parentId!=null){
			url += "?typeId=" + typeId;
		}
		$("#reportFrame").attr("src", url);
	}
	
	/**
	 * 树右击事件
	 */
	function zTreeOnRightClick(event, treeId, treeNode) {
		hiddenMenu();
		if (treeNode) {
			globalType.currentNode=treeNode;
			globalType.glTypeTree.selectNode(treeNode);
			curMenu=reportTypeMenu.getMenu(treeNode, handler);
			curMenu.show({ top: event.pageY, left: event.pageX });
		}
	};
	
	 function hiddenMenu(){
		if(curMenu){
			curMenu.hide();
		}
	 }
     
     function handler(item){
     	hiddenMenu();
     	var txt=item.text;
     	switch(txt){
     		case "增加分类":
     			globalType.openGlobalTypeDlg(true);
     			break;
     		case "编辑分类":
     			globalType.openGlobalTypeDlg(false);
     			break;
     		case "删除分类":
     			globalType.delNode();
     			break;
     	}
     }
</script>
</head>
<body>
    <div id="defLayout" >
		<div position="left" title="报表分类管理" style="overflow: auto;float:left;width:100%">
		<div class="tree-toolbar">
			<span class="toolBar">
				<div class="group"><a class="link reload" id="treeFresh" href="javascript:globalType.loadGlobalTree()" ></a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link expand" id="treeExpandAll" href="javascript:treeExpandAll(true)" ></a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link collapse" id="treeCollapseAll" href="javascript:treeExpandAll(false)" ></a></div>
			</span>
		</div>
		<ul id="glTypeTree" class="ztree"></ul>
		</div>
		<div position="center" title="报表管理">
			<iframe id="reportFrame" height="100%" width="100%" frameborder="0" src="${ctx}/platform/system/sysReport/list.ht"></iframe>
		</div>
     </div>
</body>
</html>


