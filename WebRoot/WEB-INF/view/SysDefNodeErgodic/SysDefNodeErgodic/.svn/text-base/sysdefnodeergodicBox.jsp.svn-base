<%@page import="com.hotent.platform.model.system.GlobalType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/commons/include/html_doctype.html" %>
<html>
    <head>
        <title>统计</title>
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
		
        <script type="text/javascript"><!--
        		var catKey="<%=GlobalType.CAT_FLOW%>";
        		var flowTypeMenu=new FlowTypeMenu();
        		var curMenu=null;
        		var globalType=new GlobalType(catKey,"glTypeTree",
        				{
        					onClick:onClick,
        					onRightClick:zTreeOnRightClick,
        					url:'${ctx}/platform/system/globalType/getByCatKey7.ht?typeName='+'操作图',  //子系统 维度 管理 
        					expandByDepth:0
        				});
        				
        				
        		var globalType1=new GlobalType(catKey,"glTypeTree",
        				{
        					onClick:onClick1,
        					onRightClick:zTreeOnRightClick,
        					url:'${ctx}/platform/system/globalType/getByCatKey8.ht?typeName='+'流程图',   //节点 维度 管理 
        					expandByDepth:0
        				});		
        		var globalType2=new GlobalType(catKey,"glTypeTree",
        				{
        					onClick:onClick2,
        					onRightClick:zTreeOnRightClick,
        					url:'${ctx}/platform/system/globalType/getByCatKey10.ht?ids='+'${ids}',   //节点 维度 管理 
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
            		if(typeId==100010){
          		     // alert(typeId);
            		var url=__ctx + "/sysinfomation/sysinfomation/sysinfomation/information.ht?";    //信息 规范 程度 
            		}else if(typeId==100011){
            		
            		var url=__ctx + "/sysinfomation/sysinfomation/sysinfomation/knowledge.ht?";    //知识 结构化 程度 
            		}else if(typeId==100012){
            		
            		var url=__ctx + "/sysinfomation/sysinfomation/sysinfomation/business.ht?";    //业务 逻辑 教研  程度 
            		}else if(typeId==100013){
            		
            		var url=__ctx + "/sysinfomation/sysinfomation/sysinfomation/source.ht?";    //信息 资源 开放   程度 
            		}else if(typeId==100014){
            		   
            		var url=__ctx + "/sysinfomation/sysinfomation/sysinfomation/degree.ht?";    //作业集约 度 
            		}
            		$("#defFrame").attr("src",url);
            	
            	};
            	function onClick1( treeNode){
            		var typeId=treeNode.typeId;
            		
                    // var url=__ctx + "/activityDetail/activityDetail/activityDetail/operation.ht?id="+typeId;  
            		if(typeId==100020){
            		
            		var url=__ctx + "/activityDetail/activityDetail/activityDetail/operation.ht?";    //业务操作分析表 1 
            		}else if(typeId==100021){
            		
            		var url=__ctx + "/activityDetail/activityDetail/activityDetail/table.ht?";    //业务 操作分析表 2 
            		}else if(typeId==100022){
            		
            		var url=__ctx + "/activityDetail/activityDetail/activityDetail/analysis.ht?";    //业务操作分析表3
            		}else if(typeId==100023){
            		
            		var url=__ctx + "/activityDetail/activityDetail/activityDetail/service.ht?";       //业务操作分析表4
            		}  else if(typeId==100024) {
            		        var url=__ctx + "/activityDetail/activityDetail/activityDetail/handle.ht?";   //业务操作分析表5
            		}
            		else if(typeId==10003){
            		        var url=__ctx + "/activityDetail/activityDetail/activityDetail/work.ht?";      //作业 与 作业 实例对照表 
            		}else if(typeId==10004){
            		         var url=__ctx + "/activityDetail/activityDetail/activityDetail/job.ht?";         //操作与操作实例对照表           		         
            		}
            		
            		else if(typeId==100050){
            		         var url=__ctx + "/activityDetail/activityDetail/activityDetail/operating.ht?";          //操作级 负载 分析 表 1          		         
            		}else if(typeId==100051){
            		         var url=__ctx + "/activityDetail/activityDetail/activityDetail/load.ht?";         //操作级 负载 分析 表 2           		         
            		}else if(typeId==100052){
            		         var url=__ctx + "/activityDetail/activityDetail/activityDetail/three.ht?";         //操作级 负载 分析 表3           		         
            		}
            		
            		else if(typeId==100060){
            		         var url=__ctx + "/activityDetail/activityDetail/activityDetail/work1.ht?";         //作业 级 负载 分析 表 1           		         
            		}else if(typeId==100061){
            		         var url=__ctx + "/activityDetail/activityDetail/activityDetail/work2.ht?";         //作业 级 负载 分析 表 2           		         
            		}else if(typeId==100062){ 
            		         var url=__ctx + "/activityDetail/activityDetail/activityDetail/work3.ht?";         //作业 级 负载 分析 表 3           		         
            		}
            		
            		else if(typeId==100070){
            		         var url=__ctx + "/activityDetail/activityDetail/activityDetail/power1.ht?";         //作业级 能力 需求 计算 表 1         		         
            		}else if(typeId==100071){
            		         var url=__ctx + "/activityDetail/activityDetail/activityDetail/power2.ht?";         //作业级 能力 需求 计算 表2        		         
            		}
            		
            		else if(typeId==10008){
            		         var url=__ctx + "/activityDetail/activityDetail/activityDetail/system.ht?";         //子系统 能力 需求 表          		         
            		}
            		
            		else if(typeId==10009){
            				var url=__ctx + "/activityDetail/activityDetail/activityDetail/thing.ht?";     //实体 交易 能力 
            		}else if(typeId==10010){           		
            			var url=__ctx + "/activityDetail/activityDetail/activityDetail/data.ht?"; //数据 交易 能力 
            		}              		
            		$("#defFrame").attr("src",url);
            	};
            	
            		function onClick2( treeNode){
            		var typeId=treeNode.typeId; //作业 量 信息 量 分析 表 
            		var typehigh=treeNode.depth; 
            		if(typehigh==1){  
            		//alert("进入高度1");
            			var url="${ctx}/activityDetail/activityDetail/activityDetail/find.ht?ids="+"${ids}";
            		}else
            		if(typehigh==2){ // alert("进入高度2");
            			var url="${ctx}/activityDetail/activityDetail/activityDetail/find.ht?ids="+typeId;
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
                    else if(selectvalue=='3'){   //节点 维度 管理界面
               
                    $("#defLayout").ligerLayout({ leftWidth:210,height: '100%',allowLeftResize:false});
                	//加载菜单 
                    globalType2.loadGlobalTree();
                	$(document).click(hiddenMenu)           
                 }
            	}
            
    
         </script> 
    </head>
    <body>
      	<div id="defLayout" class="panel-top">
            <div position="left" title="统计管理" style="overflow-x:scroll;overflow-y:auto;float:left;width:100%;height:96%">
             <div style="width:100%;">
				<select id="select"  onchange="show()" style="width: 99.8% !important;">
					
					<option value="1" >业务应用发展能力分析 </option>
					<option value="2" >需求能力分析</option>
					<option value="3" >作业量与信息量计算</option>
					</select>
			</div>
			
            	<div class="tree-toolbar">
					<span class="toolBar">
						<div class="group"><a class="link expand" id="treeFresh" href onclick="/platform/system/subSystem/verification.ht?ids=${ids}&status=${2}" ><span></span></a></div>
						<div class="l-bar-separator"></div>
						
					</span>
			   </div>  <ul id="glTypeTree" class="ztree"></ul>
          </div>
            <div position="center" >
          		<iframe id="defFrame" height="100%" width="100%" frameborder="0" src="${ctx}/platform/system/subSystem/verification.ht?ids=${ids}&status=${2}"></iframe>
            
            </div>
        </div>
	
		
    </body>
    </html>
