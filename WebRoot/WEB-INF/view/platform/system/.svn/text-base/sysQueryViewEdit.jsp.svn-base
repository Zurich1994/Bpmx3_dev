<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html ng-app="sysQueryViewApp">
<head>
	<title>查询数据模板设置</title>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="${ctx}/js/lang/view/platform/form/zh_CN.js"></script>
	<link href="${ctx}/styles/default/css/jquery.qtip.css" rel="stylesheet" />
	<link  rel="stylesheet" type="text/css" href="${ctx}/js/codemirror/lib/codemirror.css" >
	<script type="text/javascript" src="${ctx}/js/javacode/codemirror.js"></script>
	<script type="text/javascript" src="${ctx}/js/codemirror/lib/codemirror.js"></script>
	<script type="text/javascript" src="${ctx}/js/codemirror/mode/sql/sql.js"></script>
	<link href="${ctx}/styles/default/css/hotent/dataRights.css" rel="stylesheet" />
	<script type="text/javascript" src="${ctx}/js/jquery/plugins/jquery.fix.clone.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery/plugins/jquery.linkdiv.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/form/FormDate.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/form/FormUtil.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/form/SelectorInit.js"></script>
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
	<script type="text/javascript"	src="${ctx}/js/angular/service/sysQueryViewFilterSetting.js"></script>
	<script type="text/javascript" src="${ctx}/js/angular/module/DataRightsApp.js"></script>
	<script type="text/javascript" src="${ctx}/js/angular/controller/sysQueryViewController.js"></script>
	<script type="text/javascript">
	var DataRightsJson=${sysQueryViewJson};
	var bpmFormTableJSON=${sysQueryMetaFields};
	var displayFields=${displayFields}||"";
</script>

</head>
<body ng-controller="sysQueryViewCtrl" >
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
							<c:if test="${!empty sysQueryView.id}">
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
								<span class="tbar-label">基本信息<input type="text" class="hidden" id="sqlId" ng-model="dataRightsJson.id"/></span>
							</div>
							<table class="table-detail" cellpadding="0" cellspacing="0" border="0" type="main" style="border-width: 0!important;">
								<tr>
									<th  width="10%">名称:</th>
									<td>
										<input type="text" ng-model="dataRightsJson.name"   class="ht-input" validate="{required:true}" style="width:210px;margin-right:2px;" />
									</td>
								</tr>
								<tr>
									<th  width="10%">别名:</th>
									<td>
										<input type="text" ng-model="dataRightsJson.alias"   class="ht-input" validate="{required:true}" style="width:210px;margin-right:2px;" />
									</td>
								</tr>
								<tr>
									<th >是否分页:</th>
									<td>
										<input ng-model="dataRightsJson.needPage" type="checkbox" ng-if="dataRightsJson.needPage == 0" ng-true-value=1 ng-false-value=0 />
										<input ng-model="dataRightsJson.needPage" type="checkbox" ng-if="dataRightsJson.needPage == 1" ng-true-value=1 ng-false-value=0 ng-checked="true" />
										<span style="color:red;" ng-if="dataRightsJson.needPage==1" >
											分页大小：
											<select ng-model="dataRightsJson.pageSize" class="ht-input" style="width:50px;">
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
										<input ng-model="dataRightsJson.initQuery" type="checkbox" ng-if="dataRightsJson.initQuery == 0" ng-true-value=1 ng-false-value=0 />
										<input ng-model="dataRightsJson.initQuery" type="checkbox" ng-if="dataRightsJson.initQuery == 1" ng-true-value=1 ng-false-value=0 ng-checked="true" />
										
									</td>
								</tr>
								<tr>
									<th>是否显示行号:</th>
									<td>
										<input ng-model="dataRightsJson.showRowsNum" type="checkbox" ng-if="dataRightsJson.showRowsNum == 0" ng-true-value=1 ng-false-value=0 />
										<input ng-model="dataRightsJson.showRowsNum" type="checkbox" ng-if="dataRightsJson.showRowsNum == 1" ng-true-value=1 ng-false-value=0 ng-checked="true" />
										
									</td>
								</tr>
								<tr>
									<th>数据模板:</th>
									<td>
										<select ng-model="dataRightsJson.templateAlias"  validate="{required:true}" class="ht-input" >
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
					<div tabid="fieldSetting"  title="显示列字段">
						<field-setting ></field-setting>
					</div>
					<div tabid="conditionSetting"  title="查询条件字段">
						<condition-setting></condition-setting>
					</div>
					<div tabid="filterSetting"  title="过滤条件">
						<filter-include-setting></filter-include-setting>
					</div>
					<div tabid="manageSetting"  title="功能按钮">
						<manage-setting-view ></manage-setting-view>
					</div>
					<div tabid="groupSetting"  title="分组设置">
						<div  >
							<table style="margin: auto;width:100%;margin-top: 1px;;margin: auto;width:100%;margin-top: 1px;" class="table-detail fieldSetting" cellpadding="0" cellspacing="0" border="0">
								<thead>
									<tr style="height: 50px;">
										<th width="5%">启用分组：</th>
										<td colspan="4" style="text-align: left; width: 100%;">
											<div style="float: left;"> 
												<select ng-model="dataRightsJson.supportGroup"  class="ht-input" style="width:50px;">
													<option value="1">是</option>
													<option value="0">否</option>
												</select>
											</div>
											<div style="float: left;  margin-left: 20px;" ng-if="dataRightsJson.supportGroup!=0">
												<span>行内合计：</span>
												<select ng-model="inLineSummary" class="ht-input" >
													<option value="1"  >是</option>
													<option value="0"  >否</option>
												</select>
											</div>
											<div style="float: left;  margin-left: 20px;" ng-if="dataRightsJson.supportGroup!=0">
												<span>总合计：</span>
												<select ng-model="allSummary" class="ht-input" >
													<option value="1"  >是</option>
													<option value="0"  >否</option>
												</select>
											</div>
										</td>
									</tr>
								</thead>
								<tbody ng-if="dataRightsJson.supportGroup!=0">
									<tr>
										<td colspan="4">
											<fieldset style="margin: 5px 0px 5px 0px;  border: 2px dotted rgb(235, 235, 235);">
												<legend class="legend"  onclick="$(this).next().toggle()">
													<span>分组条件设置</span>
												</legend>
												<div>
													<table class="table-grid">
														<thead>
															<tr>
																<th width="5%">字段列表</th>
																<td style="text-align: left;">
																	<div style="margin:5px 0 0 0;display:inline-block;" class="checkbox_button_div">
																			<div class="pull-left" ng-repeat="field in displayFields" >
																				<input type="checkbox" id="e_{{field.name}}" ng-model="field.gchecked" checked="checked" ng-change="selectFieldToGroup(field)">
																				<label for="e_{{field.name}}"  class="btn label-sm">{{field.name}}</label>
																			</div>
																	</div>
																</td>
															</tr>
														</thead>
													</table>
													<table class="table-grid" ng-if="groupingView.length>0">
														<thead>
															<tr>
																<th width="1%">序号</th>
																<th width="5%">名称</th>
																<th width="2%">是否显示</th>
																<th width="2%">排序规则</th>
																<th width="30%">分组表头模版
																	<div class="tipbox">
																		<a href="javascript:;" class="tipinfo">
																			<span>这个显示分组表头模版。示例:<b> 国家: {0} {1} </b></span>
																		</a>
																	</div>
																</th>
																<th width="10%">管理</th>
															</tr>
														</thead>
														<tbody>
															<tr  ng-repeat="f in groupingView track by $index">
																<td >{{$index+1}}</td>
																<td >{{f.groupField}}</td>
																<td >
																	<input ng-model="f.groupColumnShow" ng-if="f.groupColumnShow==0"  type="checkbox" ng-true-value=1 ng-false-value=0 />
																	<input ng-model="f.groupColumnShow" ng-if="f.groupColumnShow==1"  type="checkbox" ng-true-value=1 ng-false-value=0 ng-checked="true"/>
																</td>
																<td >
																	<select ng-model="f.groupOrder" class="ht-input" >
																		<option value="asc"  >升序</option>
																		<option value="desc"  >降序</option>
																	</select>
																</td>
																<td >
																	<textarea rows="5" ng-model="f.groupText" style="padding: 15px 0 0 0px;resize: none" class="w100  border-none margin-none "></textarea>
																</td>
																<td>
																	<tool-buttons list="groupingView" index="{{$index}}" type="4"></tool-buttons>
																</td>
															</tr>
														</tbody>
													</table>
												</div>
											</fieldset>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</form>
		</div>
		<!-- end of panel-body -->
		<filter-hidden></filter-hidden>
	</div>
</body>
</html>