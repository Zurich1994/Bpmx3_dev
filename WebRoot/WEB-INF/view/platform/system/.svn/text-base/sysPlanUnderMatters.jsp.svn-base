<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/commons/include/html_doctype.html" %>
<html>
    <head>
		<%@include file="/commons/include/get.jsp" %>
        <title>待办事宜</title>
		<f:link href="tree/zTreeStyle.css"></f:link>
		<script type="text/javascript" src="${ctx}/js/tree/jquery.ztree.js"></script>
		<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerLayout.js"></script>
		<script type="text/javascript">
		
		var url = __ctx + "/platform/system/sysPlan/underUserList.ht";
		var underUserTree = null;
		$(function() {
			//布局
			$("#underUserLayout").ligerLayout({ leftWidth:210,height: '100%',allowLeftResize:false});
			//树菜单
			loadTree();
		});
		 
		
		function loadTree(){
			var setting = {
					data: {
						key : {
							name: "name",
							title: "name"
						},
					
						simpleData: {
							enable: true,
							idKey: "id",
							pIdKey: "parentId",
							rootPId: '0'
						}
					},
					async: {
						enable: true,
						url:url,
						autoParam:["id","parentId"]
					},
					view: {
						selectedMulti: true
					},
					callback:{
						beforeClick:beforeClick,
					    onClick:zTreeOnLeftClick,
					    onRightClick:zTreeOnRightClick
					}
			};
		    underUserTree = $.fn.zTree.init($("#underUserTree"), setting);
		}
		
		
		function beforeClick(){
			//alert("beforeClick");
		}
		
		function zTreeOnLeftClick(){
			var node = getSelectNode();
			var href = __ctx + "/platform/system/sysPlan/underList.ht?queryUserId="+node.id;	
			$("#underListFrame").attr("src",href);
		}
		
		function zTreeOnRightClick(){
			//alert("zTreeOnRightClick");
		}
		
		//通过id获取节点。
		function getNodeById(id){
			var node = underUserTree.getNodeByParam("id",id); 
			return node;
		};

		//选择资源节点。
		function getSelectNode(){
			var nodes = underUserTree.getSelectedNodes();
			var node = nodes[0];
			return node;
		};
		
		//展开或者收起
		function treeExpandAll(type){
			underUserTree.expandAll(type);
		};
		
		</script>
		<style type="text/css">
			.tree-title{overflow:hidden;width:100%;}
			html,body{ padding:0px; margin:0; width:100%;height:100%;overflow: hidden;}
		</style>
    </head>
    <body>
      	<div id="underUserLayout" >
            <div position="left" title="下属列表" style="overflow: auto;float:left;width:100%;height:96%;">
            	<div class="tree-toolbar">
					<span class="toolBar">
						<div class="group"><a class="link reload" id="treeFresh" href="javascript:loadTree();"  title="刷新"></a></div>
						<div class="l-bar-separator"></div>
						<div class="group"><a class="link expand" id="treeExpandAll" href="javascript:treeExpandAll(true)"  title="展开"></a></div>
						<div class="l-bar-separator"></div>
						<div class="group"><a class="link collapse" id="treeCollapseAll" href="javascript:treeExpandAll(false)"  title="收起"></a></div>
					</span>
				</div>
				<ul id="underUserTree" class="ztree" ></ul>
            </div>
            <div position="center" >
          		<iframe id="underListFrame" height="100%" width="100%" frameborder="0" src="${ctx}/platform/system/sysPlan/underList.ht"></iframe>
            </div>
        </div>
    </body>
</html>
