<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://www.jee-soft.cn/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>分级组织管理</title>
<%@include file="/commons/include/get.jsp"%>
<f:link href="tree/zTreeStyle.css"></f:link>
<script type="text/javascript" src="${ctx}/js/tree/jquery.ztree.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/OrgManage.js"></script>
<script type="text/javascript">
		var contextMenu=null;
		$(function() {
			$("#layout").ligerLayout({
				leftWidth : 220,
				height : '100%',
				allowLeftResize : false
			});
			height = $('#layout').height();
			$("#viewFrame").height(height - 25);
			
			$("#treeReFresh").click(function() {
				OrgTree.loadTree();
			});
		
			$("#treeExpand").click(function() {
				OrgTree.orgTree.expandAll(true);
			});
			$("#treeCollapse").click(function() {
				OrgTree.orgTree.expandAll(false);
			});
			getMenu();
			OrgTree.loadTree();
		});
		
		//右键菜单
		function getMenu() {
			contextMenu = $.ligerMenu({
				top : 100,
				left : 100,
				width : 100,
				items: getMenuItem()
			});
		};
		function getMenuItem(){
			var orgPerms = $("#orgAuth").children('option:selected').attr("orgPerms");
			var items=[];
			if(orgPerms.indexOf("add")>-1) {
				items.push({text : '增加',click: OrgTree.addOrg,alias:'gradeOrgEdit'});
			}
			if(orgPerms.indexOf("edit")>-1) {
				items.push({text : '编辑',click: OrgTree.editOrg,alias:'11'}); 
			}
			if(orgPerms.indexOf("del")>-1) {
				items.push({text : '删除',click: OrgTree.delNode,alias:'delOrg'});
			}
			items.push({text : '参数属性',click: OrgTree.orgParam,alias:''});
			items.push({text : '设置分级管理员',click: OrgTree.orgAuth,alias:''}); 
			return items;
		}
</script>
<style type="text/css">
html {height: 100%}
body {padding: 0px;margin: 0;overflow: auto;}
#layout {width: 99.5%;margin: 0;padding: 0;}
</style>
</head>
<body>
	<div id="layout" style="bottom: 1; top: 1">
	<c:choose>
		<c:when test="${not empty orgAuthList}">
		<div position="left" title="组织机构管理" id="rogTree" style="height: 100%; width: 100% !important;">
				<div style="width:100%;">
			        <select id="orgAuth" style="width:99.8% !important;" onchange="javascript:OrgTree.loadTree();">  
			              <c:forEach var="orgAuth" items="${orgAuthList}">  
			         			<option value="${orgAuth.orgId}" authId="${orgAuth.id}" dimId="${orgAuth.dimId}" orgPerms="${orgAuth.orgPerms}" <c:if test="${orgAuth.dimId==1}">selected="selected"</c:if>>${orgAuth.orgName}—[${orgAuth.dimName}]</option>  
			        	  </c:forEach>  
			        </select>
				</div>
			<div class="tree-toolbar" id="pToolbar">
				<div class="toolBar"
					style="text-overflow: ellipsis; overflow: hidden; white-space: nowrap">
					<div class="group">
						<a class="link reload" id="treeReFresh" ></a>
					</div>
					<div class="l-bar-separator"></div>
					<div class="group">
						<a class="link expand" id="treeExpand" ></a>
					</div>
					<div class="l-bar-separator"></div>
					<div class="group">
						<a class="link collapse" id="treeCollapse" ></a>
					</div>
				</div>
			</div>
			<ul id="orgTree" class="ztree" style="width: 180px; margin: 0; padding: 0;"></ul>
		</div>
		<div position="center" id="orgView" style="height: 100%;">
			<div class="l-layout-header">组织架构信息</div>
			<iframe id="viewFrame"  frameborder="0" width="100%" height="100%"></iframe>
		</div>
	</c:when>
	<c:otherwise><div style="width:100%;text-align: center;margin-top: 10%;">您不是分级管理员，或者尚未授权管理任何组。<div></c:otherwise>
	</c:choose>
	</div>

</body>
</html>


