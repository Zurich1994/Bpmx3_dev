<%@page import="com.hotent.platform.model.system.GlobalType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/commons/include/html_doctype.html"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>表单定义管理</title>
<%@include file="/commons/include/form.jsp" %>
<f:link href="tree/zTreeStyle.css"></f:link>
<script type="text/javascript" src="${ctx}/js/tree/jquery.ztree.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/GlobalType.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/FormDefMenu.js"></script>
<style type="text/css">
	.tree-title{overflow:hidden;width:100%;}
	html,body{ padding:0px; margin:0; width:100%;height:100%;overflow: hidden;} 
</style>	
	<script type="text/javascript">
		var tab = "";
		$(function() {
			//Tag Layout
			tab = $("#tab").ligerTab({
				onBeforeSelectTabItem:function(tabId){ 
					chageTabByTabId(tabId);
				},
				contextmenu:false
			});
		});
	
	
		var currTabId = 'formDefTab'; // 设置当前Tab
		var currCategoryId = ""; // 设置当前分类id
	
		var catKey="<%=GlobalType.CAT_FORM%>";
		var curMenu=null;
		
		var globalType=new GlobalType(catKey,"glTypeTree",{onClick:onClick,onRightClick:zTreeOnRightClick,expandByDepth:1});
		var formDefMenu=new FormDefMenu();
				
		function onClick(treeNode){
			if(treeNode.isRoot==undefined){
				currCategoryId = treeNode.typeId;
			}else{
				currCategoryId = "";
			}
			var url = getCurrUrl();
			// 刷新当前tab;
			refreshCurrTab(url);  
		}
		/* 
		* 改变tab
		*
		*/
		function chageTabByTabId(tabId){
			currTabId = tabId;
			var url = getCurrUrl();
			// 刷新当前tab;
			refreshCurrTab(url);
		}
		/*
		 * 获取当前url
		 *
		 */
		function getCurrUrl(){
			var url = '${ctx}/platform/form/bpmFormDef/list.ht';
			if(currTabId == 'querySqlTab'){
				url = '${ctx}/platform/system/sysQuerySql/list.ht';
			}
			if(currCategoryId != ""){
				url+="?categoryId="+currCategoryId;
			}
			return url;
		}
		/* 
		 * 刷新当前tab	
		 *
 		*/		
 		function refreshCurrTab(url){
			if(currTabId == 'querySqlTab'){
				$("#querySqlIframe").attr('src',url);
			}else{
	 			$("#FormDefIframe").attr('src',url);  
			}
		}
 		
 		
 		function hiddenMenu(){
			if(curMenu){
				curMenu.hide();
			}
		}
		
		$(function (){
		  	//布局
		    $("#defLayout").ligerLayout({ leftWidth:210,height: '98%',allowLeftResize:false});
		  	$(document).click(hiddenMenu);
			globalType.loadGlobalTree();
		});
		        
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
		/**
		 * 树右击事件
		 */
		function zTreeOnRightClick(event, treeId, treeNode) {
			hiddenMenu();
			if (treeNode) {
				globalType.currentNode=treeNode;
				globalType.glTypeTree.selectNode(treeNode);
				curMenu=formDefMenu.getMenu(treeNode, handler);
				curMenu.show({ top: event.pageY, left: event.pageX });
			}
		};
		//展开收起
		function treeExpandAll(type){
			globalType.treeExpandAll(type);
		};
		
	</script> 
</head>
<body>
   	<div id="defLayout" >
         <div position="left" title="表单分类管理" style="overflow: auto;float:left;width:100%">
         	<div class="tree-toolbar">
				<span class="toolBar">
					<div class="group"><a class="link reload" id="treeFresh" href="javascript:globalType.loadGlobalTree();" ></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link expand" id="treeExpandAll" href="javascript:treeExpandAll(true)" ></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link collapse" id="treeCollapseAll" href="javascript:treeExpandAll(false)" ></a></div>
				</span>
			</div>
			<ul id="glTypeTree" class="ztree"></ul>
         </div>
         <div position="center">
        	<div id="tab" >
				<!-- 基本信息  start-->
				<div tabid="formDefTab" id="table" title="自定义表单">
					<div>
						<iframe id="FormDefIframe" style="height: 600px;" width="100%" frameborder="0" src="${ctx}/platform/form/bpmFormDef/list.ht"></iframe>
					</div>
				</div>
				<div tabid="querySqlTab" id="table" title="自定义列表">
					<div>
						<iframe id="querySqlIframe" style="height: 600px;" width="100%" frameborder="0" src=""></iframe>
					</div>
				</div>
			</div>
         </div>
     </div>
</body>
</html>
