<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>组织架构左边树</title>
<%@include file="/commons/include/get.jsp" %>
<base target="_self"/> 
<f:link href="tree/zTreeStyle.css"></f:link>
<script type="text/javascript" src="${ctx }/js/tree/jquery.ztree.js"></script>
<script type="text/javascript"	src="${ctx }/js/lg/plugins/ligerMenu.js"></script>
<script type="text/javascript">
        	var orgTree; //树
        	var menu;
        	var menu_root;
        	var treeNodelickAble=true;
            $(function ()
            {            	                                        	
            	loadTree(0);
                menu();	         	               
                menu_root();
            });           
                             	
        	//添加节点
        	function addNode()
        	{
        		orgTree = $.fn.zTree.getZTreeObj("orgTree");
       			var nodes  = orgTree.getSelectedNodes();
       			var treeNode   = nodes[0];		
       			var orgId=treeNode.orgId;//如果是新增时它就变成父节点Id	  
       			var demId=treeNode.demId;
         		var url="edit.ht?orgId="+orgId+"&demId="+demId+"&flag=TA";
         		parent.$("#viewFrame").attr("src", url);      		
        	};
        	//编辑节点
        	function editNode()
        	{
        		orgTree = $.fn.zTree.getZTreeObj("orgTree");
       			var nodes  = orgTree.getSelectedNodes();
       			var treeNode   = nodes[0];		
       			var orgId=treeNode.orgId;//如果是新增时它就变成父节点Id	      
       			var demId=treeNode.demId;
         		var url="edit.ht?orgId="+orgId+"&demId="+demId+"&flag=TE";
         		parent.$("#viewFrame").attr("src", url);          		
        	};
        	
        	//删除节点
       		function delNode(){
       			orgTree = $.fn.zTree.getZTreeObj("orgTree");
       			var nodes  = orgTree.getSelectedNodes();
       			var node   = nodes[0];  
       			var callback = function(rtn)
       			{
       				if(rtn)
       				{
					      $.ajax({ 
					       type: "POST", 
					       url: "orgdel.ht", 
					       data: "orgId="+node.orgId, 
					       success: function()
					       { 
						       orgTree.removeNode(node);
					       } 
					     });  
       				}
       			};
       			parent.sdel(callback);//为了窗口在框架外面弹出 ,方法在sysOrgList.jsp里      	
       		};

       	    //左击前
        	function zTreeBeforeClick(treeId, treeNode, clickFlag){
        		return treeNodelickAble;
        	};
       		
         	//左击事件
       		function zTreeOnLeftClick(event, treeId, treeNode){   
       			var orgId=treeNode.orgId;
       			var path=treeNode.path;
       			var demId=treeNode.demId;
       			parent.$("#viewFrame").attr("src","get.ht?path="+path+"&orgId="+orgId+"&demId="+demId);
       		};
       		
       		/**
       		 * 右击事件
       		 */
       		function zTreeOnRightClick(e, treeId, treeNode) {
       			if (treeNode&&!treeNode.notRight) {
       				orgTree.selectNode(treeNode);
       				if(treeNode.orgSupId==0 && treeNode.orgId==0){//根节点时，把删除和编辑隐藏掉
       					menu_root.show({ top: e.pageY, left: e.pageX });
       				}else{
       			        menu.show({ top: e.pageY, left: e.pageX });
       				}
       			}
       		};
       		
       		//展开收起
       		function treeExpandAll(type){//在sysOrgList.jsp调用
       			orgTree = $.fn.zTree.getZTreeObj("orgTree");
       			orgTree.expandAll(type);
       		};
       		//异步加载展开
       		function treeExpand() {
    			orgTree = $.fn.zTree.getZTreeObj("orgTree");
    			var treeNodes = orgTree.transformToArray(orgTree.getNodes());
    			for(var i=1;i<treeNodes.length;i++){
    				if(treeNodes[i].children){
    					orgTree.expandNode(treeNodes[i], true, false, false);
    				}
    			}
    		};
       	    //右键菜单
       		function menu(){       			
       			menu = $.ligerMenu({ top: 100, left: 100, width: 100, items:
       	        [
       	        { text: '增加', click: addNode },
       	        { text: '编辑', click: editNode },
       	        { text: '删除', click: delNode }
       	        ]
       	        });
       		};
       		
       	    //右键菜单,当点跟节点时
       		function menu_root(){       			
       			menu_root = $.ligerMenu({ top: 100, left: 100, width: 100, items:
       	        [
       	        { text: '增加', click: addNode }
       	        ]
       	        });
       		};
       		
           //生成树      		
       	   function loadTree(selid){
	       		var setting = {
						data: {
							key : {
								
								name: "orgName",
								title: "orgName"
							},
						
							simpleData: {
								enable: true,
								idKey: "orgId",
								pIdKey: "orgSupId",
								rootPId: 0
							}
						},
						view: {
	       					addHoverDom: addHoverDom,
	       					removeHoverDom: removeHoverDom,
	       					selectedMulti: true
	       				},
	       				// 拖动
	       				edit: {
	       					drag: {
	       						prev: dropEnable,
	       						inner: dropEnable,
	       						next: dropEnable
	       					},
	       					enable: true,
	       					showRemoveBtn: false,
	       					showRenameBtn: false
	       				},
	       				onRightClick: true,
						async: {
							enable: true,
							url:"${ctx}/platform/system/sysOrg/getTreeData.ht?demId="+selid,
							autoParam:["orgId","orgSupId"]
						},
						callback:{
							oonClick: zTreeOnLeftClick,
	       					onRightClick: zTreeOnRightClick,
	       					beforeClick:zTreeBeforeClick,
	       					beforeDrop:beforeDrop,
	       					onDrop: onDrop,
							onAsyncSuccess: orgTreeOnAsyncSuccess
						}
						
					};
	       			orgTree=$.fn.zTree.init($("#orgTree"), setting);
			};
			//判断是否为子结点,以改变图标	
			function orgTreeOnAsyncSuccess(event, treeId, treeNode, msg) {
				if(treeNode){
			  	 	var children=treeNode.children;
				  	 if(children.length==0){
				  		treeNode.isParent=true;
				  		orgTree = $.fn.zTree.getZTreeObj("orgTree");
				  		if(orgTree)//不为空时更新节点
				  		orgTree.updateNode(treeNode);
				  	 }
				}
			};
        	//保存排序后的顺序
        	function sortType(orgIds){
        		$.post("${ctx}/platform/system/sysOrg/sort.ht?orgIds="+orgIds,
        			function(result){
        				var selid=parent.$("#demensionId").children('option:selected').val();
        				loadTree(selid);
        			});
        	};
        	/**
        	*显示
        	*/
        	var newCount = 1;
        	function addHoverDom(treeId, treeNode){
        		//判断存在
        		var sObj = $("#" + treeNode.tId + "_span");
        		if ($("#upBtn_"+treeNode.id).length>0) return;

        		//加入
        		if(treeNode.getPreNode()!=null){
	        		var upStr = "<button  type='button' class='link-sortUp' id='upBtn_" + treeNode.id	+ "' title='向前' ></button>";
	        		sObj.append(upStr);
        		}
        		if ($("#downBtn_"+treeNode.id).length>0) return;
        		if(treeNode.getNextNode()!=null){
        		    var upStr = "<button  type='button' class='link-sortDown' id='downBtn_" + treeNode.id	+ "' title='向后' ></button>";
        		    sObj.append(upStr);
        		}

        		if ($("#topBtn_"+treeNode.id).length>0) return;
        		if(treeNode.getPreNode()!=null){
	        		var topBtnStr = "<button  type='button' class='link-sortTop' id='topBtn_" + treeNode.id	+ "' title='最前' ></button>";
	        		sObj.append(topBtnStr);
        		}
        		if ($("#bottomBtn_"+treeNode.id).length>0) return;
        		if(treeNode.getNextNode()!=null)
        		{
        		    var bottomBtnStr = "<button  type='button' class='link-sortBottom' id='bottomBtn_" + treeNode.id	+ "' title='最后' ></button>";
            		sObj.append(bottomBtnStr);
        		}

        		var topBtn_ = $("#topBtn_"+treeNode.id);
        		if (topBtn_) topBtn_.bind("click", function(){
        			treeNodelickAble=false;
        			//取父结点
        			var parentNode = treeNode.getParentNode();
        			//如果父结点存在
        			if(parentNode!=null){
        				
        				var childs=parentNode.children;
        				var orgIds=treeNode.orgId+",";
        				$.each( childs, function(i, c){
        					if(c.orgId!=treeNode.orgId)
        						orgIds+=c.orgId+",";	 
        				});
        				if(orgIds.length>1){
        					orgIds=orgIds.substring(0,orgIds.length-1);
        					sortType(orgIds);
        				}
        			}else{
        				alert("要排序的节点没有父节点，这是一个bug ，请联系作者优化！");
        			}
        		});
        		var bottomBtn_ = $("#bottomBtn_"+treeNode.id);
        		if (bottomBtn_) bottomBtn_.bind("click", function(){
        			treeNodelickAble=false;
        			//取父结点
        			var parentNode = treeNode.getParentNode();
        			//如果父结点存在
        			if(parentNode!=null){
        				var childs=parentNode.children;
        				var orgIds="";
        				$.each( childs, function(i, c){
        					if(c.orgId!=treeNode.orgId)
        						orgIds+=c.orgId+",";	 
        				});
        				
        				orgIds+=treeNode.orgId+",";
        				if(orgIds.length>1){
        					orgIds=orgIds.substring(0,orgIds.length-1);
        					sortType(orgIds);
        				}
        			}else{
        				alert("要排序的节点没有父节点，这是一个bug ，请联系作者优化！");
        			}
        		});
        		//绑定
        		var upBtn_ = $("#upBtn_"+treeNode.id);
        		
        		if (upBtn_) upBtn_.bind("click", function(){
        			treeNodelickAble=false;
        			//取前结点
        			var preNode = treeNode.getPreNode();
        			//如果前结点存在
        			if(preNode!=null){
        				//前后结点置换
        				var thisOrgId= treeNode.orgId;
        				var preOrgId= preNode.orgId;
        				treeNode.orgId=preOrgId;
        				preNode.orgId =thisOrgId;
        				//取当下同级结点所有ID
        				var parentNode=treeNode.getParentNode();
        				var childs=parentNode.children;
        				var orgIds="";
        				$.each( childs, function(i, c){
        					orgIds+=c.orgId+",";	 
        				});
        				if(orgIds.length>1){
        					orgIds=orgIds.substring(0,orgIds.length-1);
        					sortType(orgIds);
        				}
        			}
        		});

        		var downBtn_ = $("#downBtn_"+treeNode.id);
        		if (downBtn_) downBtn_.bind("click", function(){
        			treeNodelickAble=false;
        			//取后结点
        			var nextNode = treeNode.getNextNode();
        			//如果前结点存在
        			if(nextNode!=null){
        				//前后结点置换
        				var thisOrgId= treeNode.orgId;
        				var nextOrgId= nextNode.orgId;
        				treeNode.orgId =nextOrgId;
        				nextNode.orgId =thisOrgId;
        				//取当下同级结点所有ID
        				var parentNode=treeNode.getParentNode();
        				var childs=parentNode.children;
        				var orgIds="";
        				$.each( childs, function(i, c){
        					orgIds+=c.orgId+",";	 
        				});
        				if(orgIds.length>1){
        					orgIds=orgIds.substring(0,orgIds.length-1);
        					//保存排序后的顺序
        					sortType(orgIds);
        				}
        			}
        			
        		});
        		
        	};
        	/**
        	*隐藏
        	*/
        	function removeHoverDom(treeId, treeNode) {
        		$("#upBtn_"+treeNode.id).unbind().remove();
        		$("#downBtn_"+treeNode.id).unbind().remove();
        		$("#topBtn_"+treeNode.id).unbind().remove();
        		$("#bottomBtn_"+treeNode.id).unbind().remove();
        	};
        	
        	
        	//向前拖
        	function dropEnable(treeId,curDragNodes,treeNode) {
        		if (!treeNode) return false;
        		if(treeNode.isRoot)
        			return false;
        		return true;
        	};


        	//拖放 前准备
        	function beforeDrop(treeId, treeNodes, targetNode, moveType) {
        		if (!treeNodes) return false;
        		if(targetNode.isRoot)
        			return false;
        		return true;
        	};


        	//拖放 后动作
        	function onDrop(event, treeId, treeNodes, targetNode, moveType) {
        		if(targetNode==null || targetNode==undefined) return;
        		var targetId=targetNode.orgId;
        		var dragId=treeNodes[0].orgId;
        		var url=__ctx + "/platform/system/sysOrg/move.ht";
        		var params={targetId:targetId,dragId:dragId,moveType:moveType};

        		$.post(url,params,function(result){
        			if(moveType=="inner"){
        				reAsyncChild(targetNode);
        			}
        		});
        	}
        	
        	
     </script>      
    <style type="text/css"> 
	html{scroll:no;height:100%}
    body {scroll:no;height:100%; padding:0px; margin:0;}
    </style>
</head>
<body>         
      <ul id="orgTree" class="ztree" style="width:180px;margin:0; padding:0;" >         
      </ul>                               
</body>
</html>