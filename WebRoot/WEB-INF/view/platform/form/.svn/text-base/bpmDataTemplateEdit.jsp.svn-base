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
	<script type="text/javascript" src="${ctx}/js/angular/module/DataRightsAppNewSherlock.js"></script>
	<script type="text/javascript" src="${ctx}/js/angular/controller/bpmDataTemplateController.js"></script>
	<script type="text/javascript">
	var list ;
	var lasttime;
	var indexsher;
	var o;
	var cansher;
	var len;
	var op ;
	var DataRightsJson=${DataRightsJson};
	var bpmFormTableIdsher = ${bpmFormTableIdsher};
	var bpmFormTableJSON=${bpmFormTableJSON};
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
		$("input[name=butype"+indexsher+"]").removeAttr("disabled"); 
		$("input[name=butype"+indexsher+"]").removeAttr("disabled"); 
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
		function setStyle(name){
			
			var rtn=$('#name').valid();
			if(!rtn) return;
			var name=$("#name").val();
			if(name.length==0) return;
			validExist(name,function(rtn){
				if(rtn>0){
					$.ligerDialog.error("您填写的模板名称已存在!","提示信息");
				}
				else{
					$.ligerDialog.success("模板名称可用!","提示信息");
					$("#myId").attr("style","visibility:visible"); 
					$("#id1").removeAttr('disabled');
					$("#subjectsher").removeAttr('disabled');
					$("#id3").removeAttr('disabled');
					$("#id4").removeAttr('disabled');
					$("#id5").removeAttr('disabled');
					$("#id6").removeAttr('disabled');
					$("#id7").removeAttr('disabled');
					$("#id8").removeAttr('disabled');					
					document.getElementById('name').disabled='disabled';
				}
			})
		}
	
	function validExist(name,callBack){
		var url= __ctx + "/platform/form/bpmDataTemplate/getCountByName.ht";
		var obj={};
		obj.name=name;
		$.post(url, obj,function(data){
			callBack(data) ;
		});
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
								<a class="link save"  href="javascript:;" ng-click="save()">
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
								<div class="group">
									<a class="link collapse" href="javascript:;" ng-click="addToResource()">
										<span></span>
										添加为菜单
									</a>
								</div>
							</c:if>
							<div class="l-bar-separator"></div>
							<div class="group">
								<a class="link close" href="javascript:window.close()">
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


				<div id="tab"> 

					<!-- 基本信息  start-->
					<div tabid="baseSetting"  title="基本信息">
						<div >
							<div class="tbar-title">
								<span class="tbar-label">基本信息</span>
							</div>
							<table class="table-detail" cellpadding="0" cellspacing="0" border="0" type="main" style="border-width: 0!important;">
	
								<tr>
									<th  width="10%">模版名称:</th>
									<td>
										<input type="text" ng-model="dataRightsJson.name" onblur="setStyle(this.id)" id="name" class="inputText" value="${name}" style="width:210px;margin-right:2px;" />
										<!--  <input type="button" value="验证模板是否处在" id="checkNameButton">-->
									</td>
								</tr>
								<tr>
									<th  width="10%">表单别名:</th>
									<td>
										<input type="text" ng-model="dataRightsJson.formKey"  disabled="disabled" class="inputText" validate="{required:true}" style="width:210px;margin-right:2px;" id="id1"/>
									</td>
								</tr>
								<tr>
									<th  width="10%">绑定流程:</th>
									<td>
										<input type="text" name = "subjectsher" disabled="disabled" id="subjectsher" value="${DataRightsJson.subject}" readonly="readonly"  class="inputText"  style="width:210px;margin-right:2px;" />
										<span style="visibility:hidden" id="myId">
										<a style="margin-right:5px;" ng-click="selectFlow()" class="button" href="javascript:;" >
											<span class="icon ok" ></span>
											<span   >选择</span>
										</a>
										<a  ng-click="cancel()" class="button" href="javascript:;" >
											<span class="icon cancel"></span>
											<span >重置</span>
										</a>
										</span>
									</td>
								</tr>
								<tr>
									<th >是否分页:</th>
									<td>
										<input type="radio" ng-model="dataRightsJson.needPage" value="0"  disabled="disabled" id="id3">
										不分页
										<input type="radio" ng-model="dataRightsJson.needPage" value="1" disabled="disabled" id="id4">
										分页
										<span style="color:red;" ng-if="dataRightsJson.needPage==1">
											分页大小：
											<select ng-model="dataRightsJson.pageSize"  disabled="disabled" id="id5">
												<option value="5"  >5</option>
												<option value="10" >10</option>
												<option value="15" >15</option>
												<option value="20" >20</option>
												<option value="50" >50</option>
											</select>
										</span>
									</td>
								</tr>
								<tr>
									<th>是否初始查询:</th>
									<td>
										<select ng-model="dataRightsJson.isQuery"  validate="{required:true}" disabled="disabled" id="id6">
											<option value="0"  >是</option>
											<option value="1"  >否</option>
										</select>
									</td>
								</tr>
								<tr>
									<th>
										没有过滤条件
										<br/>
										是否需要默认过滤:
									</th>
									<td>
										<select ng-model="dataRightsJson.isFilter"  validate="{required:true}" disabled="disabled" id="id7">
											<option value="0"  >是</option>
											<option value="1"  >否</option>
										</select>
									</td>
								</tr>
								<tr>
									<th>数据模板:</th>
									<td>
										<select ng-model="dataRightsJson.templateAlias"  validate="{required:true}" disabled="disabled" id="id8">
											<option value="">--请选择数据模板--</option>
											<c:forEach items="${templates}" var="template">
												<option value="${template.alias}">${template.templateName}</option>
											</c:forEach>
										</select>
										<div class="tipbox">
											<a href="javascript:;" class="tipinfo">
												<span>添加更多数据模板，请到自定义表单模板中添加类型为"业务数据模板"的模板</span>
											</a>
										</div>
									</td>
								</tr>
							</table>
						</div>
					</div>
					<div tabid="displaySetting"  title="显示列字段">
						<display-setting ></display-setting>
					</div>
					<div tabid="conditionSetting"  title="查询条件字段">
						<condition-setting ></condition-setting>
					</div>
					<div tabid="sortSetting"  title="排序字段">
						<sort-setting></sort-setting>
					</div>
					<div tabid="filterSetting"  title="过滤条件">
						<filter-setting ></filter-setting>
					</div>
					<div tabid="exportSetting"  title="导出字段">
						<export-setting ></export-setting>
					</div>
					<c:if test="${templateAlias eq 'dataTemplateList'}" >
					<div tabid="manageSettingFz"  title="功能按钮">
						<manage-setting-fz></manage-setting-fz>
					</div>
					</c:if>
					<c:if test="${templateAlias eq 'gendataTemplate'}" >
					<div tabid="manageSetting"  title="功能按钮">
						<manage-setting ></manage-setting>
					</div>
					</c:if>
				</div>
			</form>
		</div>
		<!-- end of panel-body -->
	</div>
</body>
</html>