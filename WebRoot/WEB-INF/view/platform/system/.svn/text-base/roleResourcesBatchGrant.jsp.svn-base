<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" import="com.hotent.platform.model.system.Resources"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
	<title>角色资源分配</title>
	<%@include file="/commons/include/form.jsp" %>
	<base target="_self"/> 
	<%-- <link href="${ctx}/styles/ligerUI/ligerui-all.css" rel="stylesheet"	type="text/css" /> --%>
	<f:link href="Aqua/css/ligerui-all.css"></f:link>
	<link rel="stylesheet" href="${ctx }/js/tree/zTreeStyle.css" type="text/css" />
	<script type="text/javascript" src="${ctx}/js/tree/jquery.ztree.js"></script>
	<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerComboBox.js"></script>
	<script type="text/javascript">
		/*KILLDIALOG*/
		var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)
	
		//当前访问系统
		var systemId=null;
		//树展开层数
		var expandDepth = 2; 
		$(function()
		{
			
			//加载树
			systemId=$("#subSystem").val();
			if(systemId!=null){
				loadResTree();
				loadRoleTree();
			}
				
			//改变子系统
			$("#subSystem").change(function(){
				systemId=$("#subSystem").val();
				loadResTree();
				loadRoleTree();
			});
			
			  $("a.save").click(save);
		});
	  
		//树
		var resourcesTree;
		//加载树
		function loadResTree(){
		
			var setting = {
				data: {
					key : {
						name: "resName",
						title: "resName"
					},
					simpleData: {
						enable: true,
						idKey: "resId",
						pIdKey: "parentId",
						rootPId: <%=Resources.ROOT_PID%>
					}
				},
				view: {
					selectedMulti: true
				},
				check: {
					enable: true,
					chkboxType: { "Y": "ps", "N": "s" }
				}
				
			};
			//一次性加载
			var url="${ctx}/platform/system/resources/getSysRolResTreeChecked.ht?systemId="+systemId;
			
			$.post(url,function(result){
				
				resourcesTree=$.fn.zTree.init($("#resourcesTree"), setting,eval(result));
				if(expandDepth!=0)
				{
					resourcesTree.expandAll(false);
					var nodes = resourcesTree.getNodesByFilter(function(node){
						return (node.level < expandDepth);
					});
					if(nodes.length>0){
						for(var i=0;i<nodes.length;i++){
							resourcesTree.expandNode(nodes[i],true,false);
						}
					}
				}else resourcesTree.expandAll(true);
			})
		};
		
		var roleTree;
		
		//加载树
		function loadRoleTree(){
		
			var setting = {
				data: {
					key : {
						name: "roleName",
						title: "roleName"
					},
					simpleData: {
						enable: true,
						idKey: "roleId",
						pIdKey: "systemId",
						rootPId: null
					}
				},
				view: {
					selectedMulti: true
				},
				check: {
					enable: true,
					chkboxType: { "Y": "ps", "N": "s" }
				}
				
			};
			//一次性加载
			var url="${ctx}/platform/system/sysRole/getRoleTree.ht?systemId="+systemId;
			
			$.post(url,function(result){
				
				roleTree=$.fn.zTree.init($("#roleTree"), setting,eval(result));
				if(expandDepth!=0)
				{
					roleTree.expandAll(false);
					var nodes = roleTree.getNodesByFilter(function(node){
						return (node.level < expandDepth);
					});
					if(nodes.length>0){
						for(var i=0;i<nodes.length;i++){
							roleTree.expandNode(nodes[i],true,false);
						}
					}
				}else roleTree.expandAll(true);
			})
		};
		
		//保存
		function save(){
			var resourcesTree = $.fn.zTree.getZTreeObj("resourcesTree");
			var resNodes = resourcesTree.getCheckedNodes(true);
			var resIds="";
			$.each(resNodes,function(i,n){
				if(n.resId!=<%=Resources.ROOT_ID%>)resIds+=n.resId+",";
			});
			
		
			resIds=resIds.substring(0,resIds.length-1);
			if(resIds==""){
				$.ligerDialog.confirm('请选择资源','提示信息');
				return;
			}
			var roleTree = $.fn.zTree.getZTreeObj("roleTree");
			var roleNodes = roleTree.getCheckedNodes(true);
			var roleIds="";
			$.each(roleNodes,function(i,n){
				if(n.resId!=<%=Resources.ROOT_ID%>)roleIds+=n.roleId+",";
			});
			
			roleIds=roleIds.substring(0,roleIds.length-1);
			
			if(roleIds==""){
				$.ligerDialog.confirm('请选择角色','提示信息');
				return;
			}
			var url="${ctx}/platform/system/roleResources/saveBatch.ht";
			var data= "systemId="+systemId+"&resIds="+resIds+"&roleIds="+roleIds;
			 $.post(url,data,function(result){
				var obj=new com.hotent.form.ResultMessage(result);
				if(obj.isSuccess()){
					$.ligerDialog.confirm('角色资源分配成功,是否继续?','提示信息',function(rtn){
						if(!rtn){
							dialog.close();
						}
					});
				}else{
					$.ligerDialog.warn('角色资源分配出错!');
				}
			}) 
		
		};
	</script>
<style type="text/css">
body{overflow:hidden;}
.ztree{overflow: auto;<c:if test="${systemId!=null}">height: 400px;</c:if><c:if test="${systemId==null}">height: 350px;</c:if> }
html { overflow-x: hidden; }
</style>
</head>
<body>
	<div class="panel-top">
		<div class="tbar-title">
			<span class="tbar-label">资源分配</span>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="btnSearch"><span></span>保存</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link del" onclick="javasrcipt:dialog.close()"><span></span>关闭</a></div>
			</div>	
		</div>
	</div>
	<div class="row" >
		<div class="panel-detail">
			<table id="disSys" border="0" cellspacing="0" cellpadding="0"  class="table-detail">
				<tr>
					<td width="30%" style="text-align: right;">
						<span class="label">选择系统:</span>
					</td>
					<td>
						<select id="subSystem" style="width:150px !important">  
							<c:forEach var="subSystemItem" items="${subSystemList}"> 
							 <option value="${subSystemItem.systemId}">${subSystemItem.sysName}</option>  
					        </c:forEach>  
						</select>
					</td>
				</tr>
			</table>
		</div>
	</div>
	 <div class="panel-detail">
	  <div position="left"  style="width: 49%;float:left;border: solid 1px #cacaca;margin-right: 5px;">
	  	<div class="treeTitle">资源树</div>
		<div id="resourcesTree" class="ztree" style="height: 325px;"></div>
		</div>
		 <div id="fieldSetting" position="right"   style="width: 49%;float:left;border: solid 1px #cacaca;">
		 <div class="treeTitle">角色树</div>
		<div id="roleTree" class="ztree" style="height: 325px;"></div>
		</div>
	</div> 
	 
</body>
</html>

