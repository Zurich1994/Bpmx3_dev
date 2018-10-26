<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="sysQueryFieldApp">
<head>
<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="${ctx}/js/angular/angular.min.js"></script>
	<script type="text/javascript"src="${ctx}/js/angular/service/baseServices.js"></script>
	<script type="text/javascript" src="${ctx}/js/angular/module/DataRightsApp.js"></script>
	<script type="text/javascript" src="${ctx}/js/angular/service/sysQueryFieldService.js"></script>
	<script type="text/javascript" src="${ctx}/js/angular/controller/sysQueryFieldController.js"></script>
	<title>设置字段信息 </title>
</head>
<body ng-controller="sysQueryFieldCtrl">
<div class="panel">
	<div class="hide-panel">
		<div class="panel-top">
				<div class="tbar-title">
						修改字段信息 
				</div>
				<div class="panel-toolbar">
					<div class="toolBar">
						<div class="group">
							<a class="link save" href="javascript:;" ng-click="save()"><span></span>保存</a>
						</div>
						<div class="group">
							<a class="link del" href="javascript:;" ng-click="close()"><span></span>关闭</a>
						</div>
					</div>
				</div>
		</div>
	</div>
	<form id="frmFields" action="">
		<div class="panel-detail">
			<input type="hidden" id="fieldId" name="fieldId"/>
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<th width=100>字段描述:</th>
					<td>{{sysQueryField.fieldDesc}}</td>
					<th width=100>字段名称:</th>
					<td>{{sysQueryField.name}}</td>
				</tr>
				<tr>
					<th width=100>字段类型:</th>
					<td colspan="3">
						{{sysQueryField.type}}
					</td>
				</tr>
				
				<tr id="trControlType" >
					<th width=100>控件类型:</th>
					<td colspan="4">
						<select id="controlType" ng-model="sysQueryField.controlType" >
							<option value ="1" >单行文本框</option>
							<!-- 只有日期类型才能选这个 -->
								<option value ="15" ng-if="sysQueryField.type=='DATE'||sysQueryField.type=='TIMESTAMP'">日期控件</option>
							<option value ="3" >数据字典</option>
							<option value ="11" >下拉选项</option>
							<option value ="4" >人员选择器(单选)</option>
							<option value ="17" >角色选择器(单选)</option>
							<option value ="18" >组织选择器(单选)</option>
							<option value ="19" >岗位选择器(单选)</option>
							<option value ="8" >人员选择器(多选)</option>
							<option value ="5" >角色选择器(多选)</option>
							<option value ="6" >组织选择器(多选)</option>
							<option value ="7" >岗位选择器(多选)</option>
						</select>
					</td>
				</tr>
				<tr id="trDict" ng-if="sysQueryField.controlType==3">
					<th width=100>数据字典类型:</th>
					<td colspan="3">
						<select ng-model="sysQueryField.dicType" ng-options="c.typeId as c.typeName for c in globalTypes">
							<option value ="">请选择</option>
						</select>
					</td>
				</tr>
				<tr id="trOption" ng-if="sysQueryField.controlType==11">
					<th width=100>下拉选项:</th>
					<td colspan="3">
						<div id="panel" class="s">			
							<table id="option-table">
								<thead>
									<tr>
										<td colspan="2">
											<a href="javascript:;" class="link add" ng-click="selectList.push({})">添加</a>
										</td>
									</tr>
								</thead>
								<tbody>
									<tr class="editable-tr" ng-repeat="sl in selectList">
					                        <td>
					                            <div class="editable-left-div">
					                                 <label>值: <input ng-model="sl.optionKey" type="text" style="height:21px;"/></label>
	              	 									 <label style="margin:0 0 0 10px;">选项: <input ng-model="sl.optionValue" class="long" type="text"/></label>
					                            </div>
					                        </td>
					                        <td>
					                            <div class="editable-right-div">
					                            	<tool-buttons type="4" list="selectList" index="{{$index}}" ></tool-buttons>
					                            </div>
					                        </td>
				                    	</tr>
								</tbody>
							</table>
						</div>
					</td>
				</tr>
			</table>
		</div>		
	</form>
</div>
</body>
</html>