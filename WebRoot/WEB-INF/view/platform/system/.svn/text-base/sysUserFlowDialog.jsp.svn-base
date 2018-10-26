<%@page pageEncoding="UTF-8" import="com.hotent.platform.model.system.SysUser"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>选择用户 </title>
	<%@include file="/commons/include/form.jsp" %>
	<f:link href="tree/zTreeStyle.css"></f:link>
	<script type="text/javascript" 	src="${ctx}/js/tree/jquery.ztree.js"></script>	
	
	<script type="text/javascript">
		/*KILLDIALOG*/
		var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)
		var scope =$.parseJSON(dialog.get("scope"));
		var rol_Tree=null; 
		var org_Tree=null; 
		var pos_Tree=null; 
		var onl_Tree=null; 
		var accordion = null;
		
		var rolExpandDeth=0;
		var orgExpandDeth=1;
		var posExpandDeth=1;
		var onlExpandDeth=1;
		
		var type ="";
		var typeVal="";
		if(scope){
			type = scope.type;
			typeVal = scope.value; 
		}

		
		$(function(){
			//布局
			$("#defLayout").ligerLayout({
				 leftWidth: 220,
				 rightWidth: 170,
				 bottomHeight :40,
				 height: '100%',
				 allowBottomResize:false,
				 allowLeftCollapse:false,
				 allowRightCollapse:false,
				 onHeightChanged: heightChanged,
				 minLeftWidth:200,
				 allowLeftResize:false
			});
			
			var height = $(".l-layout-center").height();
			$("#leftMemu").ligerAccordion({ height: height-28, speed: null });
		    accordion = $("#leftMemu").ligerGetAccordionManager();
		    
		    
			
		    load_Org_Tree();
		    
		    if(${isShowRole}){
		    	load_Rol_Tree();
		    }
		    
		    if(${isShowPos}){
		    	load_Pos_Tree();
		    }
		    
		    if(${isShowOnlineUser}){
		    	load_Onl_Tree();
		    } 
		   
		    
		    heightChanged();
		    //var args = window.dialogArguments;
		    var args = dialog.get("selectUsers");
		    loadCheckedUserList(args);
		    
		    var findStr = '';
			//快速查找
			$("input.quick-find").bind('keyup',function(){
				var str = $(this).val();
				if(!str)return;
				if(str==findStr)return;
				findStr = str;
				var  tbody = $("#sysUserList"), 	
					 firstTr = $('tr.hidden',tbody);
				$("tr",tbody).each(function(){
					var me = $(this),
						span = $('span',me),
						spanStr = span.html();
					if(!spanStr)return true;						
					if(spanStr.indexOf(findStr)>-1){
						$(this).insertAfter(firstTr);
					}
				});
			});
			
			//修复bug
		    $('.easyui-accordion').height($('.easyui-accordion').height()+5)
		    var  src="${ctx}/platform/system/sysUser/selector.ht?isSingle=${isSingle}&type="+type+"&typeVal="+typeVal;
		    $("#userListFrame").attr("src",src);

			
		});
		
		/*列表显示出上次已经选中的多选框值
		[{type:'user',id:'',name:''}]
		*/
		function loadCheckedUserList(args){
			if(args==undefined ||  args==null || args.length==0)return;
			for(var i=0;i<args.length;i++){
				var userObj=args[i];
				addSelect(userObj.type,userObj.id,userObj.name)
			}
		}
			
		function heightChanged(options){
			if(options){   
			    if (accordion && options.middleHeight - 28 > 0){
			    	 $("#SEARCH_BY_ORG").height(options.middleHeight - 163);
			    	 
			    	if(${isShowRole}){
			    		$("#SEARCH_BY_ROL").height(options.middleHeight - 183);
			    	}
				    if(${isShowPos}) {
				    	$("#SEARCH_BY_POS").height(options.middleHeight - 140);
				    }
				    if(${isShowOnlineUser}){
				    	$("#SEARCH_BY_ONL").height(options.middleHeight - 120);
				    }
				    
			        accordion.setHeight(options.middleHeight - 28);
			    }
			}else{
			    var height = $(".l-layout-center").height();
			    $("#SEARCH_BY_ORG").height(height - 163);
			    if(${isShowRole}){
					$("#SEARCH_BY_ROL").height(height - 183);
			    }
			    if(${isShowPos}) {
			    	$("#SEARCH_BY_POS").height(height - 140);
			    }
			    if(${isShowOnlineUser}){
			    	$("#SEARCH_BY_ONL").height(height - 120);
			    }
		    }
		}
		
		function setCenterTitle(title){
			$("#centerTitle").empty();
			$("#centerTitle").append(title);
		};
	
		function load_Pos_Tree(){
			var demId=$("#demPos").val();
			var setting = {
		    		data: {
						key : {
							name: "posName",
							title: "posName"
						},
						simpleData: {
							enable: true,
							idKey: "posId",
							rootPId: -1
						}
					},
		    		callback: {
						onClick: function(event, treeId, treeNode){
						var url="${ctx}/platform/system/sysUser/selector.ht";
						var p="?isSingle=${isSingle}&searchBy=<%=SysUser.SEARCH_BY_POS%>&posId=" + treeNode.posId+"&type="+type+"&typeVal="+typeVal;
							$("#userListFrame").attr("src", url + p);
							setCenterTitle("按岗位查找:" + treeNode.posName);
						}
					}
				};
				var url="${ctx}/platform/system/position/getBySupOrgId.ht?type="+type+"&typeVal="+typeVal;
				var para= {demId : demId};
				$.post(url,para,function(result){
					pos_Tree = $.fn.zTree.init($("#SEARCH_BY_POS"), setting,result);
					if(expandDepth!=0)
					{
						pos_Tree.expandAll(false);
						var nodes = pos_Tree.getNodesByFilter(function(node){
							return (node.level < expandDepth);
						});
						if(nodes.length>0){
							for(var i=0;i<nodes.length;i++){
								pos_Tree.expandNode(nodes[i],true,false);
							}
						}
					}else pos_Tree.expandAll(true);
				});
		};
		
		
		//判断是否为子结点,以改变图标	
		function posTreeOnAsyncSuccess(event, treeId, treeNode, msg) {
			if(treeNode){
		  	 	var children=treeNode.children;
			  	 if(children.length==0){
			  		treeNode.isParent=true;
			  		org_Tree = $.fn.zTree.getZTreeObj("SEARCH_BY_ORG");
			  		org_Tree.updateNode(treeNode);
			  	 }
			}
		};	
		function handPosClick(event, treeId, treeNode){
			var rtn=$("#chkPosition").attr("checked")==undefined?false:true;
			if(rtn){
				addSelect("pos",treeNode.posId,treeNode.posName);
			}
			else{
				var includSub=($("#includSub").attr("checked"))?1:0;
				var url="${ctx}/platform/system/sysUser/selector.ht";
				var p="?isSingle=${isSingle}&searchBy=<%=SysUser.SEARCH_BY_POS%>&includSub="+includSub+"&posId="+treeNode.posId+"&nodePath="+treeNode.nodePath;
				$("#userListFrame").attr("src",url+p);
				setCenterTitle("按岗位查找:"+treeNode.posName);
			}
		}
		
		
		
		function handOrgClick(event, treeId, treeNode){
			var rtn=$("#chkOrg").attr("checked")==undefined?false:true;
			var includSub=($("#includSub").attr("checked"))?1:0;
			if(rtn){
				addSelect("org",treeNode.orgId,treeNode.orgName);
			}
			else{
				var url="${ctx}/platform/system/sysUser/selector.ht";
				var p="?isSingle=${isSingle}&searchBy=<%=SysUser.SEARCH_BY_ORG%>&includSub="+includSub+"&orgId="+treeNode.orgId+"&path="+treeNode.path;
				$("#userListFrame").attr("src",url+p);
				setCenterTitle("按组织查找:"+treeNode.orgName);
			}
		}
		
		function handRoleClick(event, treeId, treeNode){
			var rtn=$("#chkRole").attr("checked")==undefined?false:true;
			if(rtn){
				addSelect("role",treeNode.roleId,treeNode.roleName);
			}
			else{
				var url="${ctx}/platform/system/sysUser/selector.ht";
				var p="?isSingle=${isSingle}&searchBy=<%=SysUser.SEARCH_BY_ROL%>&roleId=" + treeNode.roleId+"&type="+type+"&typeVal="+typeVal;
				$("#userListFrame").attr("src", url + p);
				setCenterTitle("按角色查找:" + treeNode.roleName);
			}
			
		}
		
		function load_Org_Tree(){
			var value=$("#dem").val();
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
						rootPId: -1
					}
				},
				async: {
					enable: true,
					url:"${ctx}/platform/system/sysOrg/getTreeData.ht?demId="+value+"&type="+type+"&typeVal="+typeVal,
					autoParam:["orgId","orgSupId"]
				},
				callback:{
					onClick: handOrgClick,
					onAsyncSuccess: orgTreeOnAsyncSuccess
				}
			};
			org_Tree=$.fn.zTree.init($("#SEARCH_BY_ORG"), setting);
		};
		//判断是否为子结点,以改变图标	
		function orgTreeOnAsyncSuccess(event, treeId, treeNode, msg) {
			if(treeNode){
		  	 	var children=treeNode.children;
			  	 if(children.length==0){
			  		treeNode.isParent=true;
			  		org_Tree = $.fn.zTree.getZTreeObj("SEARCH_BY_ORG");
			  		org_Tree.updateNode(treeNode);
			  	 }
			}
		};	
		function load_Rol_Tree(){
			var systemId=$("#systemId").val();
			var roleName=$("#roleName").val();
			var setting = {
		    		data: {
						key : {name: "roleName",title: "roleName"},
						simpleData: {enable: true,idKey: "roleId",rootPId: -1}
					},
		    		callback: {
						onClick: handRoleClick
					}
				};
				var url="${ctx}/platform/system/sysRole/getAll.ht";
				var para= {systemId : systemId,roleName : roleName};
				$.post(url,para,function(result){
					rol_Tree = $.fn.zTree.init($("#SEARCH_BY_ROL"), setting,result);
					
		            if(rolExpandDeth!=0)
		            {
		                var nodes = rol_Tree.getNodesByFilter(function(node){
		                    return (node.level==rolExpandDeth);
		                });
		                
		                if(nodes.length>0){
		                    for(var idx=0;idx<nodes.length;idx++){
		                    	rol_Tree.expandNode(nodes[idx],false,false);
		                    }
		                }
		            }
		            else
		            {
		            	rol_Tree.expandAll(true);
		            }
					
				});
			};

			
			function load_Onl_Tree(){
				var value=$("#onl").val();
				var setting = {
			    		data: {
							key : {name: "orgName",title: "orgName"},
							simpleData: {enable: true,idKey: "orgId",pIdKey : "orgSupId",rootPId: -1}
						},
			    		callback: {
							onClick: function(event, treeId, treeNode){
								var url="${ctx}/platform/system/sysUser/selector.ht";
								var p="?isSingle=${isSingle}&searchBy=<%=SysUser.SEARCH_BY_ONL%>&path="+treeNode.path+"&type="+type+"&typeVal="+typeVal;
								$("#userListFrame").attr("src",url+p);
								setCenterTitle("按组织查找:"+treeNode.orgName);
							}
						}
			    };
				
				var url= "${ctx}/platform/system/sysOrg/getTreeOnlineData.ht?type="+type+"&typeVal="+typeVal;
				var para="demId=" + value;
				$.post(url,para,function(result){
					org_Tree = $.fn.zTree.init($("#SEARCH_BY_ONL"), setting,result);
					
		            if(onlExpandDeth!=0)
		            {
		                var nodes = org_Tree.getNodesByFilter(function(node){
		                    return (node.level==onlExpandDeth);
		                });
		                
		                if(nodes.length>0){
		                    for(var idx=0;idx<nodes.length;idx++){
		                    	org_Tree.expandNode(nodes[idx],true,false);
		                    }
		                }
		            }
		            else
		            {
						org_Tree.expandAll(true);
		            }
					
				});
				
			};
				
				
			function dellAll() {
				$("#sysUserList").empty();
			};
			function del(obj) {
				var tr = $(obj).parents("tr");
				$(tr).remove();
			};
			
			function addSelect(type,id,name){
				var objId=type +"_" + id;
				var len= $("#" + objId).length;
				if(len>0) return;
				
				var data=type +"#" + id +"#" +name;
				var aryData=['<tr id="'+objId+'">',
								'<td>',
								'<input type="hidden" class="pk" name="userData" value="'+data +'"> ',
								'<span>'+name+'</span>',
								'</td>',
								'<td><a onclick="javascript:del(this);" class="link del" ></a> </td>',
								'</tr>'];
				$("#sysUserList").append(aryData.join(""));
			};
			
			
			function add(ch) {
				var data = $(ch).val();
				var aryTmp=data.split("#");
				var userId=aryTmp[0];
				var userName=aryTmp[2];
				addSelect("user",userId,userName);
			};
		
			function selectMulti(obj) {
				if ($(obj).attr("checked") == "checked"){
					add(obj);
				}	
			};
		
			function selectAll(obj) {
				var state = $(obj).attr("checked");
				var rtn=state == undefined?false:true;
				checkAll(rtn);
			};
		
			function checkAll(checked) {
				$("#userListFrame").contents().find("input[type='checkbox'][class='pk']").each(function() {
					$(this).attr("checked", checked);
					if (checked) {
						add(this);
					}
				});
			};
			
			function selectUser(){
				var chIds = $("#sysUserList").find(":input[name='userData']");
				var aryType=[];
				var aryId=[];
				var aryName=[];
				
				$.each(chIds,function(i,ch){
					var aryTmp=$(ch).val().split("#");
					aryType.push(aryTmp[0]);
					aryId.push(aryTmp[1]);
					aryName.push(aryTmp[2]);
				});
				var obj={objType:aryType,objIds:aryId,objNames:aryName};
				//window.returnValue=obj;
				dialog.get("sucCall")(obj);
				dialog.close();
			}
	</script>

<style type="text/css">
.ztree {
	overflow: auto;
}

.label {
	color: #6F8DC6;
	text-align: right;
	padding-right: 6px;
	padding-left: 0px;
	font-weight: bold;
}
html { overflow-x: hidden; }
.l-layout-right{left:528px;}
.l-layout-left, .l-layout-center, .l-layout-right { height:90%;}
.l-accordion-content { height:324px;}
.l-accordion-content .ztree { height:285px;}

</style>
</head>
<body>
	<div id="defLayout">
		<div id="leftMemu" position="left" title="查询条件<input type='checkbox' id='includSub'/>包含子分类" style="overflow: auto; float: left;width: 100%;height:94%;">
			<div title="组织机构<input type='checkbox' id='chkOrg'><label for='chkOrg'>选择</label>" style="overflow: hidden;">
				<table border="0" width="100%" class="table-detail">
					<tr >
						<td width="30%" nowrap="nowrap"><span class="label">维度:</span>
						</td>
						<td style="width:70%;">
							<select id="dem" name="dem" onchange="load_Org_Tree()">
								<c:forEach var="demen" items="${demensionList}">
									<option  value="${demen.demId}" <c:if test="${demen.demId==1}">selected</c:if>>${ demen.demName}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
				</table>
				<div id="SEARCH_BY_ORG" class='ztree'></div>
			</div>
			<c:if test="${isShowPos}">
           	<div title="岗位<input type='checkbox' id='chkPosition'><label for='chkPosition'>选择</label>" style="overflow: hidden;">
				<table border="0" width="100%" class="table-detail">
					<tr >
						<td width="30%" nowrap="nowrap"><span class="label">维度:</span>
						</td>
						<td style="width:70%;">
							<select id="demPos" name="demPos" onchange="load_Pos_Tree()">
								<c:forEach var="demen" items="${demensionList}">
									<option  value="${demen.demId}" <c:if test="${demen.demId==1}">selected</c:if>>${ demen.demName}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
				</table>
				<div id="SEARCH_BY_POS" class='ztree'></div>
			</div>
			</c:if>
			<c:if test="${isShowRole}">
			<div title="角色<input type='checkbox' id='chkRole'><label for='chkRole'>选择</label>" style="overflow: hidden;">
				<div class="tree-title" style="width: 100%;">
					<div class="panel-detail">
						<table border="0" width="100%" class="table-detail">
							<tr>
								<td width="30%" nowrap="nowrap">
									<span class="label"style="width:60px;">系统:</span>
								</td>
								<td colspan="2">
									<select id="systemId" name="systemId" onchange="load_Rol_Tree()">
										<c:forEach var="sys" items="${subSystemList}">
											<option value="${sys.systemId}">${ sys.sysName}</option>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<td>
									<span class="label"style="width:60px;">角色:</span>
								</td>
								<td>
									<input id="roleName" name="roleName" type="text" size="10">
								</td>
								<td>
									<a class="link detail" href="javascript:load_Rol_Tree();">&ensp;</a>
								</td>
							</tr>
						</table>
					</div>
				</div>
				<div id="SEARCH_BY_ROL" class='ztree'></div>
			</div>
			</c:if>
			<c:if test="${isShowOnlineUser}">
			<div title="在线用户" style="overflow: hidden;">
				
				<table border="0" width="100%" class="table-detail">
					<tr >
						<td width="30%" nowrap="nowrap"><span class="label">维度:</span>
						</td>
						<td style="width:70%;">
							<select id="onl" name="onl" onchange="load_Onl_Tree()">
								<c:forEach var="demen" items="${demensionList}">
									<option  value="${demen.demId}" <c:if test="${demen.demId==1}">selected</c:if>>${ demen.demName}</option>
								</c:forEach>
							</select>								
						</td>							
					</tr>
				</table>
				
				<div id="SEARCH_BY_ONL" class='ztree'></div>
			</div>
			</c:if>

		</div>
		<div position="center" style="height:100%;">
			<div id="centerTitle" class="l-layout-header">全部用户</div>
			<iframe id="userListFrame" name="userListFrame" height="94%" width="100%" frameborder="0" ></iframe>
		</div>
		
		<div position="right" title="<span><a onclick='javascript:dellAll();' class='link del'>清空 </a><input type='text' class='quick-find' title='查找' style='width:60px;' /></span>"
			style="height:94%;overflow:auto;">
			<table width="145" id="sysUserList" class="table-grid table-list" 	id="0" cellpadding="1" cellspacing="1">
				<tr class="hidden"></tr>
			</table>
		</div>
		<div position="bottom"  class="bottom" >
			<a href="javascript:;" class="button"  onclick="selectUser()" ><span class="icon ok"></span><span >选择</span></a>
			<a href="javascript:;" class="button" style="margin-left:10px;"  onclick="dialog.close()"><span class="icon cancel"></span><span >取消</span></a>
		</div>
	</div>
</body>
</html>


