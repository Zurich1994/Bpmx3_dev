<%--
	time:2015-04-09 17:19:23
	desc:edit the 对象权限表
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<%@ taglib prefix="ht" tagdir="/WEB-INF/tags/wf"%>
<html>
<head>
	<title>流程信息模板编辑</title>
	<%@include file="/commons/include/form.jsp" %>
	<!-- zTree引入 -->
	<f:link href="tree/zTreeStyle.css"></f:link>
	<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerMenu.js"></script>
	<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerTab.js"></script>
	<script type="text/javascript" src="${ctx}/js/tree/jquery.ztree.js"></script>
	<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerLayout.js"></script>
	<!-- 引入ngjs -->
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript" src="${ctx}/js/angular/angular.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/angular/service/baseServices.js"></script>
	<script type="text/javascript" src="${ctx}/js/angular/service/arrayToolService.js"></script>
	<script type="text/javascript" src="${ctx}/js/angular/service/commonListService.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/nodeMsgTemplate/NodeMsgTemplateService.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/nodeMsgTemplate/EditController.js"></script>
	<!-- 引入ueditor -->
	<script type="text/javascript" charset="utf-8" src="${ctx}/js/ueditor/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8" src="${ctx}/js/ueditor/ueditor.all.js"></script>
	
	<script type="text/javascript">
		var defId="${param.defId}";
		var id="${param.id}";
		var nodeId="${param.nodeId}"==""?0:"${param.nodeId}";
		var parentDefId="${param.parentDefId}"==""?0:"${param.parentDefId}";
		var fieldTree=null;
		var ue;
		$(function() {
			$("#defLayout").ligerLayout({leftWidth:210,height: '100%',allowLeftResize:false});
			$("#tab").ligerTab();
		});
		
		var dialog =frameElement!=null?frameElement.dialog:null;
		function closeWin(){
			if(dialog){//弹出框打开
				dialog.close();
				dialog.get("reset")();//刷新父
			}else{
				window.location.href="";
			}
		}
	</script>
</head>
<body  ng-app="app" ng-controller="EditController">
<form id="frmSubmit">
<div class="panel">
	<div class="panel">
		<div class="panel-top">
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">标题：<input type="text" ng-model="prop.title" disabled="disabled" style="width: 200px" validate="{required:true,maxlength:64}" /></div>
					<div class="group"><a class="link save" ng-click="save()"><span></span>保存</a></div>
					<div class="group"><a class="link back" href="javaScript:closeWin()"><span></span>返回</a></div>
					<div class="group">列数：<input type="text" ng-model="param.columnNum" style="width: 50px" validate="{required:true,maxlength:64}" /></div>
					<div class="group"><a class="link reset" ng-click="initTemp()" ><span></span>初始化模板</a></div>
				</div>
			</div>
		</div>
		
		<div id="defLayout" >
			<div position="left" title="表字段" style="overflow: auto;float:left;width:100%">
				<ul id="fieldTree" class="ztree"></ul>
			</div>
			<div position="center" title="编辑器">
			    <div id="tab" class="liger-tab">
		        	<div tabid="html" title="html模板">
		        		<textarea id="html" style="width: 100%;height:485px">${taskOpinion.opinion}</textarea>
		        		<script type="text/javascript">
		        			ue = UE.getEditor('html');
		        		</script>
		        	</div>
		        	<div tabid="text" title="text模板">
		        		<textarea id="text" rows="30" cols="124"></textarea>
		         	</div>
     			</div>
			</div>
	     </div>
	</div>
</div>
</form>
</body>
</html>
