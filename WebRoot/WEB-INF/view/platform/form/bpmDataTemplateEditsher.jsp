<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html ng-app="bpmDataTemplateApp">
<head>
	<title>数据模板设置</title>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="${ctx}/js/lang/view/platform/form/zh_CN.js"></script>
	<link href="${ctx}/styles/default/css/jquery.qtip.css" rel="stylesheet" />
	<link href="${ctx}/styles/default/css/hotent/dataRights.css" rel="stylesheet" />
	<script type="text/javascript" src="${ctx}/js/jquery/plugins/jquery.fix.clone.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/formdata.js"></script>
	<script type="text/javascript" src="${ctx}/js/util/easyTemplate.js" ></script>
	<script type="text/javascript" src="${ctx}/js/codemirror/lib/codemirror.js"></script>
	<script type="text/javascript" src="${ctx}/js/codemirror/lib/util/matchbrackets.js"></script>
	<script type="text/javascript" src="${ctx}/js/codemirror/mode/groovy/groovy.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery/plugins/jquery.qtip.js" ></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/ScriptDialog.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/AddResourceDialog.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/BpmDefinitionDialog.js"></script>
	<script type="text/javascript" src="${ctx}/js/angular/angular.min.js"></script>
	<script type="text/javascript"	src="${ctx}/js/angular/service/baseServices.js"></script>
	<script type="text/javascript" src="${ctx}/js/angular/module/DataRightsAppsher.js"></script>
	<script type="text/javascript" src="${ctx}/js/angular/controller/bpmDataTemplateController.js"></script>
	<script type="text/javascript"><!--
	var list ;
	var lasttime;
	var indexsher;
	var o;
	var cansher;
	var len;
	var op ;
	var DataRightsJson=${DataRightsJson};
	var bpmFormTableJSON=${bpmFormTableJSON};
	var actdefIdsher = '${actdefIdsher}';
	var defIdsher = ${defIdsher};
	var nodeIdsher = '${nodeIdsher}';
	var buttonFlagsher =' ${buttonFlagsher}';
	var bpmFormTableIdsher = ${bpmFormTableIdsher};
	var manageField = ${manageField};
	$(function() {
		for(var i = 0;i<manageField.length;i++){
		 	  cansher = manageField[i].scan.toString().split(",");
			  o = document.getElementById("scan"+i);
		      len = cansher.length;
		 	  for(var j = 0; j < len; j++){
			    op = document.createElement("optgroup");
			    op.setAttribute("label",cansher[j]);
			    o.appendChild(op);
		 	  }
		 }
	});
	function radiochange(obj){
		indexsher = obj.value;
		if(indexsher!=lasttime){
			$("button[value="+lasttime+"]").attr("disabled","true");
			$("button[value="+lasttime+"]").attr("class","inputText");
			$("input[name=butype"+lasttime+"]").attr("disabled","true");
		}
		$("button[value="+indexsher+"]").removeAttr("disabled"); 
		$("button[value="+indexsher+"]").attr("class","bt-select");
		lasttime = indexsher;
	};
	function butype(flag){
		if(flag==1){
			$("#finname"+indexsher).attr("disabled","true");
		  	$("#foutname"+indexsher).removeAttr("disabled"); 
		}else if(flag==2){
			$("#foutname"+indexsher).attr("disabled","true");
			$("#finname"+indexsher).removeAttr("disabled");
		}
		if(indexsher!=lasttime){
			$("#finname"+indexsher).attr("disabled","true");
			$("#foutname"+indexsher).attr("disabled","true");
		}
	}
</script>
</head>
<body ng-controller="bpmDataTemplateCtrl" >
	<div class="panel" ng-show="hasInitTab">
		<div class="hide-panel">
			<div class="panel-top">
				<div class="panel-top">
					<div class="tbar-title">
						<span class="tbar-label">数据模板设置</span>
					</div>
					<div class="panel-toolbar">
						<div class="toolBar">
							<div class="group">
								<a class="link save"  href="javascript:;" ng-click="savesher();">
									<span></span>
									保存
								</a>
							</div>
							<c:if test="${!empty bpmDataTemplate.id}">
								<div class="l-bar-separator"></div>
								<div class="group">
									<a class="link preview" href="javascript:;" ng-click="preview()">
										<span></span>
										预览
									</a>
								</div>
								<div class="l-bar-separator"></div>
								<div class="group">
									<a class="link edit" href="javascript:;" ng-click="editTemplate()">
										<span></span>
										编辑模板
									</a>
								</div>
								<div class="l-bar-separator"></div>
								<!--<div class="group">
									<a class="link collapse" href="javascript:;" ng-click="addToResource()">
										<span></span>
										添加为菜单
									</a>
								</div>
							--></c:if>
							<div class="l-bar-separator"></div>
							<div class="group">
								<a class="link close" href="${ctx}/platform/bpm/bpmNodeButton/getByNode.ht?&defId=${defIdsher}&nodeId=${nodeIdsher}&buttonFlag=${buttonFlagsher}">
									<span></span>
									关闭
								</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="panel-body">
			<form id="dataRightsForm" >
					<!-- 功能按钮  start-->
					<div tabid="manageSetting"  title="功能按钮">
						<div>
						<div class="table-top-left" ng-if="!service.noRights">
							<div class="toolBar" style="margin:0;">
								<!--<div class="group">
								<a class="link add"  ng-click="service.addManage();">添加按钮</a>
								</div>
							--></div>
						</div>
						<table id="manageTbl"  class="table-grid">
							<thead>
								<tr>
									<th width="5%">选择</th>
									<th width="15%">名称</th>
									<th width="7%">按钮属性</th>
									<th width="15%">按钮类型</th>
									<th width="6%">操作</th>
									<th width="6%">参数</th>
									<!--<th ng-if="!service.noRights">
										权限
										<right-select list="manageFields"></right-select>
									</th>
									-->
									<th width="15%">管理</th>
								</tr>
							</thead>
							<tbody>
								<tr var="manageTr" ng-repeat="f in manageFields">
									<td var="index">
										<input class="pk"  type="radio" name="select" id="selectradio" value = "{{$index}}" onclick = "radiochange(this);"></td>
									<td >
										<input type="text" disabled="disabled" ng-model="f.desc" id="fdesc" value="{{f.desc}}" class="ht-input" style= "text-align:center; font-size:16px;"></td>
									<td>
										<input type="text" disabled="disabled" ng-model="f.btntype" id="btntype" value="{{f.btntype}}" style= "text-align:center; font-size:16px;"></td>
									</td>
									<td>
										<select ng-model="f.name" ng-options="m.k as m.v for m in managerButtons" class="ht-input" disabled="disabled" style= "text-align:center; font-size:16px;">
										<option value="">未设置按钮类型</option>
									</td>
									<td style="text-decoration: none;text-align:center">
										<button class="inputText" id="bangdingcanshu"  ng-click = "service.writein()" value="{{$index}}" disabled = "false" >
											编●辑
										</button>
									</td>
									<td>
										<select  id="scan{{$index}}" readonly="readonly" class="ht-input" style="text-align:center;">
										<option >—————查看已选模板参数集—————</option></select>
										&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp节点参数：<input type="text" ng-model="f.snode" id="thenode{{$index}}" name="{{$index}}" value="所绑节点" width="100%" disabled="true" onFocus="if(value==defaultValue){value='';this.style.color='#000'}" onBlur="if(!value){value=defaultValue;this.style.color='#999'}" style="color:#999999">
									</td>
									<td style="text-decoration: none;text-align:center">
										<tool-buttons list="manageFields" index="{{$index}}" type="4"></tool-buttons>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					</div>
				</form>
			</div>
		</div>
		<!-- end of panel-body -->
	</div>
</body>
</html>