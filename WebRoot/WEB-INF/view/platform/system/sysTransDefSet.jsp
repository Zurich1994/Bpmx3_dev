<%--
	time:2015-04-09 17:19:23
	desc:edit the 对象权限表
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<%@ taglib prefix="ht" tagdir="/WEB-INF/tags/wf"%>
<html>
<head>
	<title>编辑 对象权限表</title>
	<%@include file="/commons/include/form.jsp" %>
	<!-- zTree引入 -->
	<f:link href="tree/zTreeStyle.css"></f:link>
	<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerMenu.js"></script>
	<script type="text/javascript" src="${ctx}/js/tree/jquery.ztree.js"></script>
	<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerLayout.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/GlobalMenu.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/form/GlobalType.js"></script>
	<script type="text/javascript" src="${ctx}/js/util/ZtreeCreator.js"></script>
	<!-- ngjs引入 -->
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
	<script type="text/javascript" src="${ctx}/js/angular/angular.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/angular/service/baseServices.js"></script>
	<script type="text/javascript" src="${ctx}/js/angular/service/arrayToolService.js"></script>
	<script type="text/javascript" src="${ctx}/js/angular/service/commonListService.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/sysTransDef/SysTransDefService.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/sysTransDef/SetController.js"></script>
	<script type="text/javascript">
		$(function() {
			$("#defLayout").ligerLayout({leftWidth:210,height: '100%',allowLeftResize:false});
		});
		
		var id="";
		function zTreeOnClick(event,treeId,treeNode) {
			var scope=getScope();
			id=treeNode.id;
			scope.excuteSelectSql();
		};
	</script>
</head>
<body ng-app="app" ng-controller="SetController">
<form id="frmSubmit>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
		        <span class="tbar-label">权限转移</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					被转移人：<input ng-model="param.author.name" type="text" readonly="true" class="inputText" validate="{required:true,maxlength:64}"/>
					<input type="button" value="选择" ng-click="selectUser(param.author,authorChange)" />
				</div>
			</div>
		</div>
		
		<div id="defLayout" >
			<div position="left" title="权限转移管理" style="overflow: auto;float:left;width:100%">
			<ul id="glTypeTree" class="ztree"></ul>
			</div>
			<div position="center" title="权限明细">
				<div class="panel-toolbar">
					<div class="toolBar">
						<div class="group">转移到：<input readonly="true" ng-model="param.targetPerson.name"/></div>
						<div class="group"><a class="link search" ng-click="selectUser(param.targetPerson)"><span></span>人员选择</a></div>
						<div class="l-bar-separator"></div>
						<div class="group"><a class="link ok" ng-click="excuteUpdateSql()"><span></span>确认</a></div>
						<!-- <div class="l-bar-separator"></div>
						<div class="group"><a class="link back" href="list.ht"><span></span>返回</a></div> -->
					</div>
				</div>
				
				<div class="panel-body" style="overflow-y:scroll;height: 650px;">
					<table class="table-detail" cellpadding="0" cellspacing="0" border="0" type="main">
						<tr>
							<th width="2%"><input type="checkbox" ng-model="param.selectAll" ng-click="clickAll()"/></th>
							<th width="20%" ng-repeat="(id,value) in param.showList[0]">{{id}} </th>
						</tr>
						<tr ng-repeat="item in param.showList">
							<td><input ng-model="param.list[$index].selected" type="checkbox"/></td>
							<td height="30px" ng-repeat="(id,value) in item" ng-click="clickItem(param.list[$parent.$index])">{{value}}</td>
						</tr>
					</table>
				</div>
			</div>
	     </div>
	</div>
</form>
</body>
</html>
