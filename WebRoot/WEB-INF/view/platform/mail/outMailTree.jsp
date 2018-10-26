<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" import="com.hotent.platform.model.system.Position" import="com.hotent.platform.model.system.Resources"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
	<title>邮件</title>
	<%@include file="/commons/include/form.jsp" %>
	<base target="_self"/> 
	<f:link href="tree/zTreeStyle.css"></f:link>
	<script type="text/javascript"	src="${ctx }/js/tree/jquery.ztree.js"></script>
	<script type="text/javascript">
		//树节点是否可点击
		var treeNodelickAble=true;
		var aryIcon=new Array();
		aryIcon.push("${ctx}/styles/default/images/icon/email.png");
		aryIcon.push("${ctx}/styles/tree/mail_inbox.png");
		aryIcon.push("${ctx}/styles/tree/mail_outbox.png");
		aryIcon.push("${ctx}/styles/tree/mail_drafts.png");
		aryIcon.push("${ctx}/styles/tree/mail_trash.png");
		$(function()
		{
			loadTree();
			layout();
			$('#listFrame').attr('src','${ctx}/platform/mail/outMail/list.ht');
		});
		//布局
		function layout(){
			$("#layout").ligerLayout( {
				leftWidth : 220,
				allowLeftResize :false
			});
			//取得layout的高度
	        var height = $(".l-layout-center").height();
	        $("#treeObject").height(height-60);
		};
		//树
		var treeObject;
		//加载树
		function loadTree(){
			var setting = {
				data: {
					key : {
						name: "userName",
						title: "userName"
					},
					simpleData: {
						enable: true,
						idKey: "id",
						pIdKey: "parentId",
						rootPId: 0
					}
				},
				view: {
					selectedMulti: false,
					showLine : false
				},
				callback:{
					onClick: zTreeOnLeftClick
				}
				
			};
			
			$.post("${ctx}/platform/mail/outMail/getMailTreeData.ht",
				 function(result){
					for(var i=0;i<result.length;i++){
						var n=result[i];
						n.types==null?n.icon=aryIcon[0]:n.icon=aryIcon[n.types];
					}
					treeObject= $.fn.zTree.init($("#treeObject"), setting, result);
					treeObject.expandAll(true);     
				});
		};
		
		//左击
		function zTreeOnLeftClick(event, treeId, treeNode){
			if(treeNode.parentId!=0){
				returnUrl="${ctx}/platform/mail/outMail/list.ht?id="+treeNode.parentId+"&types="+treeNode.types;
				$("#listFrame").attr("src",returnUrl);
			}
		};
		
		//展开收起
		function treeExpandAll(type){
			treeObject = $.fn.zTree.getZTreeObj("treeObject");
			treeObject.expandAll(type);
		};

</script>
<style type="text/css">
	html,body{ padding:0px; margin:0; width:100%;height:100%;overflow: hidden;}
	.tree-title{overflow:hidden;width:8000px;}
	.ztree{overflow: auto;}
	</style>
</head>

<body>
	<div id="layout">
		<div position="left" title="邮箱">
			<div class="tree-toolbar">
				<span class="toolBar">
					<div class="group"><a class="link reload" id="treeFresh" href="javascript:loadTree();" ></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link expand" id="treeExpandAll" href="javascript:treeExpandAll(true)" ></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link collapse" id="treeCollapseAll" href="javascript:treeExpandAll(false)" ></a></div>
				</span>
			</div>
			<div id="treeObject" class="ztree"></div>
		</div>
		<div position="center">
			<iframe id="listFrame" frameborder="no" width="100%" height="100%"></iframe>
		</div>
	</div>
</body>
</html>

