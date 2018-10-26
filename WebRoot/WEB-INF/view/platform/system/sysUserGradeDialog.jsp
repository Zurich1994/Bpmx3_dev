<%@page pageEncoding="UTF-8" import="com.hotent.platform.model.system.SysUser"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>选择用户 </title>
	<%@include file="/commons/include/form.jsp" %>
	<f:link href="tree/zTreeStyle.css"></f:link>
	<script type="text/javascript" 	src="${ctx}/js/tree/jquery.ztree.js"></script>
	<script type="text/javascript">
		var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)
		var isSingle=${isSingle};
		var org_Tree=null; 
		var accordion = null;
		//树展开层数
		var expandDepth = 1; 
		forbidF5("Chrome");//禁止刷新页面
		$(function(){
			//布局
			$("#defLayout").ligerLayout({
				 leftWidth: 220,
				 rightWidth: 170,
				 bottomHeight :40,
				 height: '90%',
				 allowBottomResize:false,
				 allowLeftCollapse:false,
				 allowRightCollapse:false,
				 onHeightChanged: heightChanged,
				 minLeftWidth:200,
				 allowLeftResize:false
			});
			
			var findStr = '';
			//快速查找
			$("input.quick-find").bind('keyup',function(){
				var str = $(this).val();
				if(!str)return;
				if(str==findStr)return;
				findStr = str;
				var  tbody = $("#userList"), 	
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
			
			var height = $(".l-layout-center").height();
			$("#leftMemu").ligerAccordion({ height: height-28, speed: null });
		    accordion = $("#leftMemu").ligerGetAccordionManager();
		    
		    load_Org_Tree();
		    
		    heightChanged();
		    
		    handleSelects();
		});
		function heightChanged(options){
			if(options){   
			    if (accordion && options.middleHeight - 28 > 0){
			    	$("#SEARCH_BY_ROL").height(options.middleHeight - 183);
				    $("#SEARCH_BY_ORG").height(options.middleHeight - 163);
				    $("#SEARCH_BY_POS").height(options.middleHeight - 140);
				    $("#SEARCH_BY_ONL").height(options.middleHeight -163);
			        accordion.setHeight(options.middleHeight - 28);
			    }
			}else{
			    var height = $(".l-layout-center").height();
				$("#SEARCH_BY_ROL").height(height - 183);
			    $("#SEARCH_BY_ORG").height(height - 163);
			    $("#SEARCH_BY_POS").height(height - 140);
			    $("#SEARCH_BY_ONL").height(height - 163);
		    }
		}
		
		function setCenterTitle(title){
			
			$("#centerTitle").empty();
			$("#centerTitle").append(title);
			
		};
		
		function load_Org_Tree(){
			var orgId=$("#orgAuth").val();
			var setting = {
					data: {
						key : {
							name: "orgName",
							title: "orgName"
						}
						/* simpleData: { 
							enable: true,
							idKey: "orgId",
							pIdKey: "orgSupId",
							rootPId: -1
						} */
					},
					callback:{
						onClick: function(event, treeId, treeNode){
							var url="${ctx}/platform/system/sysUser/selector.ht";
							var includSub=($("#includSub").attr("checked"))?1:0;
							var p="?isSingle=${isSingle}&searchBy=<%=SysUser.SEARCH_BY_ORG%>&orgId="+treeNode.orgId+"&includSub="+includSub+"&path="+treeNode.path;
							$("#userListFrame").attr("src",url+p);
							setCenterTitle("按组织查找:"+treeNode.orgName);
						},
						//onAsyncSuccess: orgTreeOnAsyncSuccess
					}
					
				};
			
			var url=__ctx + "/platform/system/grade/getOrgJsonByAuthOrgId.ht?orgId="+orgId;
			
			$.post(url,function(result) {
				if($.isEmpty(result)) return;
				var zNodes=eval("(" +result +")");
				org_Tree = $.fn.zTree.init($("#orgTreeId"),setting,zNodes);
				if(expandDepth!=0)
				{
					var nodes = org_Tree.getNodesByFilter(function(node){
						return (node.level < expandDepth);
					});
					if(nodes.length>0){
						for(var i=0;i<nodes.length;i++){
							org_Tree.expandNode(nodes[i],true,false);
						}
					}
				}else{
					org_Tree.expandAll(true);
				}
			});
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
		
				
			function dellAll() {
				$("#sysUserList").empty();
			};
			function del(obj) {
				var tr = $(obj).parents("tr");
				$(tr).remove();
			};
			
			function add(data) {
				
				var aryTmp=data.split("#");
				var userId=aryTmp[0];
				var len= $("#user_" + userId).length;
				if(len>0) return;
				
				var aryData=['<tr id="user_'+userId+'">',
					'<td>',
					'<input type="hidden" class="pk" name="userData" value="'+data +'"><span> ',
					aryTmp[1],
					'</span></td>',
					'<td><a onclick="javascript:del(this);" class="link del" ></a> </td>',
					'</tr>'];
				$("#sysUserList").append(aryData.join(""));
			};
		
			function selectMulti(obj) {
				if ($(obj).attr("checked") == "checked"){
					var data = $(obj).val();
					add(data);
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
						var data = $(this).val();
						add(data);
					}
				});
			};
			
			function clearUser(){
				/* window.returnValue = {
					userIds: '',
					fullnames: '',
					emails: '',
					mobiles: ''
				};
				window.close(); */
				
				var rtn={
					userIds: '',
					fullnames: '',
					emails: '',
					mobiles: ''
				};
				dialog.get("sucCall")(rtn);
				dialog.close();
			}
			
			function selectUser(){
				var chIds;
				if(isSingle==true){
					chIds = $('#userListFrame').contents().find(":input[name='userData'][checked]");
				
				}else{
					chIds = $("#sysUserList").find(":input[name='userData']");
				}
				
				var aryuserIds=new Array();
				var aryfullnames=new Array();
				var aryemails=new Array();
				var arymobiles=new Array();
				
				$.each(chIds,function(i,ch){
					var aryTmp=$(ch).val().split("#");
					aryuserIds.push(aryTmp[0]);
					aryfullnames.push(aryTmp[1]);
					aryemails.push(aryTmp[2]);
					arymobiles.push(aryTmp[3]);
					
				});
				
				var obj={userIds:aryuserIds.join(","),fullnames:aryfullnames.join(","),
						emails:aryemails.join(","),mobiles:arymobiles.join(",")};
				try{
					dialog.get("sucCall")(obj);
				}catch(e){
					
				}
				dialog.close();
			}
			
			var handleSelects=function(){
				var    selectUsers    =dialog.get("selectUsers");
				if(selectUsers.selectUserIds && selectUsers.selectUserNames){
					var ids=selectUsers.selectUserIds.split(","); 
					var names=selectUsers.selectUserNames.split(","); 
					for(var i=0;i<ids.length;i++){
						if(names[i]!=undefined&&names[i]!="undefined"&&names[i]!=null&&names[i]!=""){
							add(ids[i]+"#"+names[i]+"##");
						}
					}
				}
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
.quick-find {
	width:35px;
}
html { overflow-x: hidden; }
.l-layout-right{left:511px;}
.l-layout-left, .l-layout-center, .l-layout-right { height:90%;}
.l-accordion-content { height:324px;}
.l-accordion-content .ztree { height:285px;}	
</style>
</head>
<body>
	<div id="defLayout" style="height:100%;">
		<div id="leftMemu" position="left" title="查询条件<input type='checkbox' id='includSub'/>包含子分类" style="overflow: auto; float:left;width: 100%; height:95%">
			<div title="授权组" style="overflow: hidden;">
				<table border="0" width="100%" class="table-detail">
					<tr >
						<td style="width:100%;">
							<select id="orgAuth" onchange="load_Org_Tree()">
							<c:forEach var="orgAuth" items="${orgAuthList}">  
			         			<option value="${orgAuth.orgId}"  <c:if test="${orgAuth.dimId==1}">selected="selected"</c:if>>${orgAuth.orgName}—[${orgAuth.dimName}]</option>  
			        	  	</c:forEach>
							</select>
						</td>
					</tr>
				</table>
				<div id="orgTreeId" class='ztree'></div>
			</div>

		</div>
		<c:forEach var="orgAuth" items="${orgAuthList}">  <c:if test="${orgAuth.dimId==1}"><c:set var="mainOrgId" value="${orgAuth.orgId}"></c:set></c:if></c:forEach>
		<div position="center">
			<div id="centerTitle" class="l-layout-header">全部用户</div>
			<iframe id="userListFrame" name="userListFrame" height="95%" width="100%" frameborder="0" 
			src="${ctx}/platform/system/sysUser/selector.ht?isSingle=${isSingle}&searchBy=<%=SysUser.SEARCH_BY_ORG%>&orgId=${mainOrgId}&includSub=1"></iframe>
		</div>
		<c:if test="${isSingle==false}">
			<div position="right" style="overflow:auto;height:95%;width:170px;"  title="<span><a onclick='javascript:dellAll();' class='link del'>清空 </a><input type='text' class='quick-find' title='查找'/></span>">
				<table width="145" id="sysUserList" class="table-grid table-list" 	id="0" cellpadding="1" cellspacing="1">
					<tbody id="userList">
       					<tr class="hidden"></tr>
       				</tbody>
				</table>
			</div>
		</c:if>
	</div>
	<div position="bottom"  class="bottom" style="margin-top:10px;">
		<a href="javascript:;" class="button"  onclick="selectUser()" style="margin-right:10px;" ><span class="icon ok"></span><span >选择</span></a>
		<a href="javascript:;" class="button" style="margin-left:10px;"  onclick="dialog.close()"><span class="icon cancel"></span><span >取消</span></a>
	</div>
</body>
</html>


