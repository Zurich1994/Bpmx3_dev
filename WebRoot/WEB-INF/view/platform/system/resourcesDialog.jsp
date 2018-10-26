<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
	<title>设置图标</title>
	<%@include file="/commons/include/form.jsp" %>
	<f:link href="tree/zTreeStyle.css"></f:link>
	<script type="text/javascript" src="${ctx}/js/tree/jquery.ztree.js"></script>
	<script type="text/javascript">
		/*KILLDIALOG*/
		var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)
		
		//选中的图标
		var selected;
		var imgSrc="";
		var folderTree = null;
		var selectedNode=null;
		var menu=null;
		var expandByDepth = 0;
		$(function() {
			loadTree();
			loadMenu();
			//$("#listFrame").attr('src','icons.ht');
			
			
			$("#defLayout").ligerLayout({ leftWidth: 200,height: '90%',
				bottomHeight :50,
				 
				 allowBottomResize:false});

		});
		function selectIcon(){
			var selected=$("#listFrame").contents().find('img.selected');
			var imgSrc="";
			if(selected.length==0){
				$.ligerDialog.success("没有选择图标!","提示信息");
				return ;
			}
			imgSrc=selected.attr('src');
			
			var obj={srcValue:imgSrc};
			//window.returnValue=obj;
			dialog.get('sucCall')(obj);
			dialog.close();
		}
		
		function loadMenu(){
			menu= $.ligerMenu({top: 100, left: 100, width: 120, items:
				[{text:'新建文件夹',click:createFolder},
				 {text:'删除文件夹',click:delFolder}
				]
			});
		}
		
		function createFolder(){
			$("#listFrame").attr('src','folderEdit.ht');
			$("#listFrame").load(function(){
				$("#listFrame").contents().find('#parentFoler').text(selectedNode.folderName);
				$("#listFrame").contents().find('#path').val(selectedNode.folderPath);
			});
		}
		
		function openFolder(){
			$("#listFrame").attr('src','icons.ht?path='+selectedNode.folderPath);
		}
		
		function delFolder(){
			var path=selectedNode.folderPath;
			$.post('delFile.ht',{path:path},function(data){
				var obj=new com.hotent.form.ResultMessage(data);
				if(obj.isSuccess()){
					$.ligerDialog.success(obj.getMessage(),"提示信息",function(rtn){
						if(rtn){
							window.parent.location.reload();
						}
					});
				}else{
					$.ligerDialog.err('出错信息',"设置文件图标失败",obj.getMessage());
				}
			});
		}
		
		function loadTree(){
			var setting = {
					async: {enable: false},
					data : {
						key : {name : "folderName"},
						simpleData : {
							enable : true,
							idKey : "folderId",
							pIdKey : "parentId",
							rootPId : 0
							}
					},
					
					callback : {
						onRightClick: zTreeOnRightClick,
						onClick: zTreeOnLeftClick
					}
				};
				$.post("${ctx}/platform/system/resources/getFolderData.ht",function(result) {
					for(var i=0;i<result.length;i++){
						result[i].icon="${ctx}/styles/default/images/resicon/folderClosed.gif";
					}
					folderTree = $.fn.zTree.init($("#folderTree"), setting,result);
					
		            if(expandByDepth!=0)
		            {
		                var nodes = folderTree.getNodesByFilter(function(node){
		                    return (node.level==expandByDepth);
		                });
		                
		                if(nodes.length>0){
		                    for(var idx=0;idx<nodes.length;idx++){
		                    	folderTree.expandNode(nodes[idx],true,false);
		                    }
		                }
		            }
		            else
		            {
		            	folderTree.expandAll(true);
		            }
				});
		}
		
		//展开收起
		function treeExpandAll(type){
			resourcesTree = $.fn.zTree.getZTreeObj("folderTree");
			resourcesTree.expandAll(type);
		};
		
		function zTreeOnLeftClick(event, treeId, treeNode){
			selectedNode=treeNode;
			openFolder();
		}
		/**
		 * 树右击事件
		 */
		function zTreeOnRightClick(e, treeId, treeNode) {
			if (treeNode && treeId!=0) {
				folderTree.selectNode(treeNode);
				selectedNode=treeNode;
				
				var h=$(window).height() ;
				var w=$(window).width() ;
				var menuWidth=120;
				var menuHeight=75;
				
				var x=e.pageX,y=e.pageY;
				if(e.pageY+menuHeight>h){
					y=e.pageY-menuHeight;
				}
				if(e.pageX+menuWidth>w){
					x=e.pageX-menuWidth;
				}
				menu.show({ top: y, left: x });
			}
		}
	</script>
	<style type="text/css">
		html { overflow-x: hidden; overflow-y: hidden; }
		
		.tree-title{overflow:hidden;width:8000px;}
		.ztree{overflow: auto;}
	</style>
</head>
<body style="margin:0 0 0 0;padding: 0 0 0 0;">
	<div id="defLayout">
		<div  position="left" title="资源管理" style="overflow: auto; float: left;width: 100%;">
			<div class="tree-toolbar">
				<span class="toolBar">
					<div class="group">
						<a class="link reload" id="treeFresh" href="javascript:loadTree();"><span></span>刷新</a>
					</div>
					<div class="l-bar-separator"></div>
					<div class="group">
						<a class="link expand" id="treeExpandAll"
							href="javascript:treeExpandAll(true)"><span></span>展开</a>
					</div>
					<div class="l-bar-separator"></div>
					<div class="group">
						<a class="link collapse" id="treeCollapseAll" href="javascript:treeExpandAll(false)"><span></span>收起</a>
					</div>
				</span>
			</div>
			<ul id="folderTree" class="ztree"></ul>
		</div>
		<input type="hidden" id="src">	
		
		<div  position="center"  >
			<iframe id="listFrame"  src="icons.ht" frameborder="no" width="100%" height="100%"></iframe>
		</div>
	</div>
	<div position="bottom"  class="bottom" style='margin-top:5px;'>
		<a href='#' class='button' onclick="selectIcon()" ><span>选择</span></a>
		<a href='#' class='button' style='margin-left:10px;'  onclick="dialog.close()"><span>取消</span></a>
	</div>
</body>
</html>