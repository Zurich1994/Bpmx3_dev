<%@page import="com.hotent.platform.model.system.GlobalType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/commons/include/html_doctype.html" %>
<html>
    <head>
        <title>流程定义管理</title>
		<%@include file="/commons/include/form.jsp" %>
		<f:link href="tree/zTreeStyle.css" ></f:link>
	    <script type="text/javascript" src="${ctx}/js/lg/plugins/ligerMenu.js"></script>
		<script type="text/javascript" src="${ctx}/js/tree/jquery.ztree.js"></script>
		<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerLayout.js"></script>
		<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
		<script type="text/javascript" src="${ctx}/js/hotent/platform/form/GlobalType.js"></script>
		<script type="text/javascript" src="${ctx}/js/hotent/platform/system/GlobalMenu.js"></script>
		<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/FlowRightDialog.js"></script>
		<style type="text/css">
			.tree-title{overflow:hidden;width:100%;}
			body{overflow: hidden;}
		</style>	
        <script type="text/javascript">
        		var catKey="<%=GlobalType.CAT_FLOW%>";
        		var flowTypeMenu=new FlowTypeMenu();
        		var curMenu=null;
        		var globalType=new GlobalType(catKey,"glTypeTree",
        				{
        					onClick:onClick,
        					onRightClick:zTreeOnRightClick,
        					url:'${ctx}/platform/system/globalType/getByCatKey2.ht?typeName='+"流程图",
        					expandByDepth:1
        				});
        				
        	  var globalType1=new GlobalType(catKey,"glTypeTree",
        				{
        					onClick:onClick1,
        					onRightClick:zTreeOnRightClick,
        					url:'${ctx}/platform/system/globalType/getByCatKey4.ht?typeName='+"流程图",
        					expandByDepth:1
        					
        				});
        		
                $(function (){
                	//布局
                    $("#defLayout").ligerLayout({ leftWidth:210,height: '100%',allowLeftResize:false});
                	//加载菜单 
                    globalType.loadGlobalTree();
                	$(document).click(hiddenMenu);
                });
                
                
                function show(){
                  var selectvalue=document.getElementById('select').value;
                  if(selectvalue=='1'){
                    $("#defLayout").ligerLayout({ leftWidth:210,height: '100%',allowLeftResize:false});
                	//加载菜单 
                    globalType.loadGlobalTree();
                	$(document).click(hiddenMenu);
                 
                  }else if(selectvalue=='2'){
               
                    $("#defLayout").ligerLayout({ leftWidth:210,height: '100%',allowLeftResize:false});
                	//加载菜单 
                    globalType1.loadGlobalTree();
                	$(document).click(hiddenMenu);
                   
                 }
            	}
                
                
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

            	/**
            	 * 树右击事件
            	 */
            	function zTreeOnRightClick(event, treeId, treeNode) {
            		hiddenMenu();
            		if (treeNode) {
            			globalType.currentNode=treeNode;
            			globalType.glTypeTree.selectNode(treeNode);
            			curMenu=flowTypeMenu.getMenu(treeNode, handler);
            			justifyRightClickPosition(event);
            			curMenu.show({ top: event.pageY, left: event.pageX });
            		}
            	};
            	
            	//左击
            	function onClick( treeNode){
            		var typeId=treeNode.typeId;
            		if(treeNode.sysdefnode!=1)
            		var url="${ctx}/platform/bpm/bpmDefinition/list.ht?typeId="+typeId+"&typeName="+"flowChart";          		
            		$("#defFrame").attr("src",url);
            	};
            	function onClick1(treeNode){
            	var typeId=treeNode.typeId;
            		if(treeNode.depth==1)//深度为1
            		{
            			if(treeNode.typeId!=10001)
            			{	//alert("进入深度1   tongji11  "+treeNode.typeId);//子系统
            				var url="${ctx}/platform/system/subSystem/list.ht?";    
            			} 
            			else  
	            		{	//alert("进入深度1   tongji12   "+treeNode.typeId);//未设置的流程
	            			var url="${ctx}/platform/bpm/bpmDefinition/tongji.ht?id="+typeId;     
            			}    		
            		}else
            		if(treeNode.depth==2)//深度為2流程统计
            		{//alert("进入深度2   tongji 2  "+typeId);
            		
						if(treeNode.parentId==10001) //如果是未设置流程
						{  //alert("进入深度2   tongji 21  "+treeNode.parentId);       		
            			var url="${ctx}/SysDefNodeErgodic/SysDefNodeErgodic/sysdefnodeergodic/deftongji.ht?id="+typeId;
            			}
            			else//是子系统
            			{////alert("进入深度2   tongji 22  "+treeNode.parentId);
            			var url="${ctx}/SysDefNodeErgodic/SysDefNodeErgodic/sysdefnodeergodic/tongji.ht?id="+typeId; 
            			}
            		}          		
            		$("#defFrame").attr("src",url);
            	};
            	
            	//展开收起
            	function treeExpandAll(type){
            		globalType.treeExpandAll(type);
            	};
            	
            /* 	//对流程分类分配权限。
            	function assignRights(){
            		var node=globalType.currentNode;
            		if(node!=null && node!=undefined){
            			if(node.isRoot==undefined){
            				var typeId=node.typeId;
            				FlowRightDialog(typeId,1,'',node.children?1:0);
            			}
            		}
            	} */
            
         </script> 
    </head>
    <body>
      	<div id="defLayout" class="panel-top">
            <div position="left" title="流程图管理" style="overflow-x:scroll;overflow-y:auto;float:left;width:100%;height:96%">
          <div style="width: 100%;">
				<select id="select"  onchange="show()" style="width: 99.8% !important;">
						
	                       
						<option value="1">---通过流程分类管理流程------</option>
						<option value="2">---子系统维度管理流程---</option>
					</select>
			</div>
            	<div class="tree-toolbar">
					<span class="toolBar">
						<div class="group"><a class="link reload" id="treeFresh" href="javascript:globalType.loadGlobalTree();" ><span></span></a></div>
						<div class="l-bar-separator"></div>
						<div class="group"><a class="link expand" id="treeExpandAll" href="javascript:treeExpandAll(true)" ><span></span></a></div>
						<div class="l-bar-separator"></div>
						<div class="group"><a class="link collapse" id="treeCollapseAll" href="javascript:treeExpandAll(false)" ><span></span></a></div>
					</span>
			   </div>  <ul id="glTypeTree" class="ztree"></ul>
          </div>
            <div position="center" >
          		<iframe id="defFrame" height="100%" width="100%" frameborder="0" src="${ctx}/platform/bpm/bpmDefinition/list.ht?typeName=flowChart&typeId=2"></iframe>
            </div>
        </div>
	
		
    </body>
    </html>
