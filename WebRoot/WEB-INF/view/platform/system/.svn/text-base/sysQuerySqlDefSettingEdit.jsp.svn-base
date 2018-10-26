<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html ng-app="sysQuerySqlDefSettingApp">
<head>
	<title>查询数据模板设置</title>
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
	<script type="text/javascript" src="${ctx}/js/angular/module/DataRightsApp.js"></script>
	<script type="text/javascript" src="${ctx}/js/angular/controller/sysQuerySettingController.js"></script>
	<script type="text/javascript">
	var DataRightsJson=${DataRightsJson};
	var bpmFormTableJSON=${bpmFormTableJSON};
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
								<a class="link close" href="javascript:;" onclick="window.close();">
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
									<th  width="10%">名称:</th>
									<td>
										<input type="text" ng-model="dataRightsJson.name"   class="inputText" validate="{required:true}" style="width:210px;margin-right:2px;" />
									</td>
								</tr>
								<tr>
									<th  width="10%">别名:</th>
									<td>
										<input type="text" ng-model="dataRightsJson.alias"   class="inputText" validate="{required:true}" style="width:210px;margin-right:2px;" />
									</td>
								</tr>
								<tr>
									<th >是否分页:</th>
									<td>
										<input type="radio" ng-model="dataRightsJson.needPage" value="0" >
										不分页
										<input type="radio" ng-model="dataRightsJson.needPage" value="1" >
										分页
										<span style="color:red;" ng-if="dataRightsJson.needPage==1">
											分页大小：
											<select ng-model="dataRightsJson.pageSize" >
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
										<select ng-model="dataRightsJson.isQuery"  validate="{required:true}">
											<option value="0"  >是</option>
											<option value="1"  >否</option>
										</select>
									</td>
								</tr>
								<tr>
									<th>数据模板:</th>
									<td>
										<select ng-model="dataRightsJson.templateAlias"  validate="{required:true}">
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
						<sort-setting namekey="name"></sort-setting>
					</div>
					<div tabid="filterSetting"  title="过滤条件">
						<filter-setting ></filter-setting>
					</div>
					<div tabid="exportSetting"  title="导出字段">
						<export-setting ></export-setting>
					</div>
					<div tabid="manageSetting"  title="功能按钮">
						<manage-setting ></manage-setting>
					</div>
				</div>
			</form>
		</div>
		<!-- end of panel-body -->
	</div>
</body>
</html>