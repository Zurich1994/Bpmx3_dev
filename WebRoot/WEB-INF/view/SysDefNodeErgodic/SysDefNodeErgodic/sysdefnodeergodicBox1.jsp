<%@page import="com.hotent.platform.model.system.GlobalType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/commons/include/html_doctype.html" %>
<html>
    <head>
        <title>业务逻辑校验</title>
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
        					url:'${ctx}/platform/system/globalType/getByCatKey9.ht?ids='+'${ids}',  //子系统 维度 管理 
        					expandByDepth:0
        				});
      		
                $(function (){
                	//布局
                    $("#defLayout").ligerLayout({ leftWidth:210,height: '100%',allowLeftResize:false});
                	//加载菜单 
                    globalType.loadGlobalTree();
                	$(document).click(hiddenMenu);
                	
   
                });
                
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
            		return;
            	};
            	
            	//左击
            	function onClick( treeNode){
            		var typeId=treeNode.typeId;
            		var typehigh=treeNode.depth;
            		if(typehigh==1){  
            		//alert("进入高度0");
            			var url="${ctx}/SysDefNodeErgodic/SysDefNodeErgodic/sysdefnodeergodic/systongji.ht?id="+typeId+"&sort="+0;
            		}else
            		if(typehigh==2){  
            		//alert("进入高度1");
            			var url="${ctx}/SysDefNodeErgodic/SysDefNodeErgodic/sysdefnodeergodic/deftongji.ht?id="+typeId+"&sort="+0;
            		}else
            		if(typehigh==3){ // alert("进入高度2");
            			var url="${ctx}/SysDefNodeErgodic/SysDefNodeErgodic/sysdefnodeergodic/nodetongji.ht?id="+typeId+"&sort="+0;
            		}else return;
            		$("#defFrame").attr("src",url);
            	};
            	//展开收起
            	function treeExpandAll(type){
            		globalType.treeExpandAll(type);
            	};
            	

            function show(){
                  var selectvalue=document.getElementById('select').value;
                  if(selectvalue=='1'){  //子系统 维度  管理界面
                    $("#defLayout").ligerLayout({ leftWidth:210,height: '100%',allowLeftResize:false});
                	//加载菜单 
                    globalType.loadGlobalTree();
                	$(document).click(hiddenMenu);
                  }else if(selectvalue=='2'){   //节点 维度 管理界面
               
                    $("#defLayout").ligerLayout({ leftWidth:210,height: '100%',allowLeftResize:false});
                	//加载菜单 
                    globalType1.loadGlobalTree();
                	$(document).click(hiddenMenu);
                               
                 }
            	}
            
    
         </script> 
    </head>
    <body>
      	<div id="defLayout" class="panel-top">
            <div position="left" title="业务逻辑校验" style="overflow-x:scroll;overflow-y:auto;float:left;width:100%;height:96%">
             <div style="width:100%;">
				<select id="select"  onchange="show()" style="width: 99.8% !important;">
					
					<option value="1" >业务逻辑校验 </option>
				
					</select>
			</div>
			
            	<div class="tree-toolbar">
					<span class="toolBar">
						<div class="group"><a class="link expand" id="treeFresh" href onclick="${ctx}/platform/system/subSystem/verification.ht?ids=${ids}&status=${3}" ><span></span></a></div>
						<div class="l-bar-separator"></div>
						
					</span>
			   </div>  <ul id="glTypeTree" class="ztree"></ul>
          </div>
            <div position="center" >
          		<iframe id="defFrame" height="100%" width="100%" frameborder="0" src="${ctx}/platform/system/subSystem/verification.ht?ids=${ids}&status=${3}"></iframe>
            
            </div>
        </div>
	
		
    </body>
    </html>
