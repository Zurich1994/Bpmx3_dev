<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="sysQueryFieldApp">
<head>
<%@include file="/commons/include/form.jsp" %>
	<link href="${ctx}/styles/default/css/hotent/dataRights.css" rel="stylesheet" />
	<script type="text/javascript" src="${ctx}/js/angular/angular.min.js"></script>
	<script type="text/javascript"src="${ctx}/js/angular/service/baseServices.js"></script>
	<script type="text/javascript" src="${ctx}/js/angular/module/DataRightsApp.js"></script>
	<script type="text/javascript" src="${ctx}/js/angular/service/sysQuerySqlService.js"></script>
	<script type="text/javascript" src="${ctx}/js/angular/service/sysQueryFieldService.js"></script>
	<script type="text/javascript" src="${ctx}/js/angular/controller/sysQueryFieldController.js"></script>
	<title>设置字段信息 </title>
	<style>
		tr{
			height:40px;
		}
	</style>
</head>
<body ng-controller="sysQueryMetaFieldCtrl">
	<div class="panel" ng-if="type=='ctrlType'" ng-controller="sysQueryMetaFieldCtrlTypeCtrl">
		<dialog-buttons></dialog-buttons>
		<form >
			<div class="panel-detail">
				<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
					<tr>
						<th >字段描述:</th><td>{{sysQueryField.fieldDesc}}</td>
						<th >字段名称:</th><td>{{sysQueryField.name}}</td>
					</tr>
					<tr>
						<th >字段类型:</th><td >{{sysQueryField.dataType}}</td>
						<th >控件类型:</th>
						<td >
							<select ng-model="sysQueryField.controlType" class="ht-input" style="width: 150px;" ng-options="k as v for (k,v) in util.getControlTypeJson(sysQueryField.dataType)" >
								<option value="">请选择</option>
							</select>
						</td>
					</tr>
					<tr  ng-if="sysQueryField.controlType==12">
						<th >自定义对话框:</th>
						<td >
							<select ng-model="sysQueryField.ctrlCon.dialog"  ng-options="c.alias as c.name for c in dialogList" ng-change="changeDialog(c)" class="ht-input" style="width: 150px;"> </select>
						</td>
						<th >对话框返回字段选择:</th>
						<td >
							<select ng-model="sysQueryField.ctrlCon.resultField" ng-options="c.field as c.comment for c in currentDialog.resultfield" class="ht-input" style="width: 150px;"></select>
						</td>
					</tr>
					<tr  ng-if="sysQueryField.controlType==3">
						<th >数据字典类型:</th>
						<td colspan="3">
							<select ng-model="sysQueryField.dicType" ng-options="c.nodeKey as c.typeName for c in globalTypes" class="ht-input" style="width: 150px;">
								<option value = "">请选择</option>
							</select>
						</td>
					</tr>
					<tr  ng-if="sysQueryField.controlType==15">
						<th >日期格式:</th>
						<td colspan="3">
							<select ng-model="sysQueryField.dateFormat"  class="ht-input" style="width:180px">
								<option value ="">请选择</option>
								<option value="yyyy-MM-dd">yyyy-MM-dd</option>
								<option value="yyyy-MM-dd HH:mm:ss">yyyy-MM-dd HH:mm:ss</option>
								<option value="yyyy-MM-dd HH:mm:00">yyyy-MM-dd HH:mm:00</option>
								<option value="HH:mm:ss">HH:mm:ss</option>
							</select>
						</td>
					</tr>
					<tr ng-if="sysQueryField.controlType==11">
						<th >下拉选项:</th>
						<td colspan="5">
								<select-table list="selectList"></select-table>
						</td>
					</tr>
				</table>
			</div>		
		</form>
	</div>
	<div class="panel" ng-if="type=='alarmSetting'" ng-controller="sysQueryMetaFieldAlarmCtrl">
		<dialog-buttons></dialog-buttons>
		<form>
			<div class="panel-detail">
				<table class="table-detail" cellpadding="0" cellspacing="0"
					border="0">
					<tr>
						<th width=100>字段描述:</th>
						<td>{{sysQueryField.fieldDesc}}</td>
						<th width=100>字段名称:</th>
						<td>{{sysQueryField.name}}</td>
					</tr>
					<tr>
						<th width=100>字段类型:</th>
						<td colspan="3">{{sysQueryField.dataType}}</td>
					</tr>
				</table>
				<fieldset>
					<legend class="legend" onclick="$(this).next().toggle()" title="单击展开/收缩"> 预警规则 </legend>
					<table class="table-detail" cellpadding="0" cellspacing="0"
						border="0">
						<tr ng-repeat="as in alarmSetting" style="height: 50px;">
							<th width="20%" style="text-align:center;color:{{as.color}};font-weight:bolder;">颜色
								<input type="color" class="ht-input" ng-model="as.color" style="width:50px;"/>
							</th>
							<td>
								<div ng-repeat="cd in as.condition track by $index" style="float: left;margin:5px 0 5px 5px;">
									<select ng-model="cd.op" class="ht-input" style="width:70px;height: 30px;float: left;">
										<option value=">">大于</option>
										<option value="<">小于</option>
										<option value="=">等于</option>
										<option value=">=">大于等于</option>
										<option value="<=">小于等于</option>
									</select>
									<input type="text" ng-model="cd.val" class="ht-input" style="width:50px;float: left;  margin-left: 5px; text-align: center;"/>
									<span ng-if="as.condition.length>0&&!$last" style="float: left;" class="and_button">and</span>
									<span ng-if="$last" ng-click="addCondition(as.condition)" class="plus_button_default" style="margin-left: 10px;float: left;">+</span>
								</div>
								<span class="plus_button_info" ng-if="$last" style="float: right;margin-top: 7px;" ng-click="addAlarmSetting()">+</span>
								<span class="plus_button_danger" ng-if="alarmSetting.length>1"  style="float: right;margin-top: 7px;margin-right: 5px;" ng-click="service.util.delTr(alarmSetting,$index)">-</span>
							</td>
						</tr>
					</table>
				</fieldset>
				<fieldset style="margin-top: 10px;padding: 0px 15px 20px 10px ;">
					<legend class="legend" onclick="$(this).next().toggle()" title="单击展开/收缩"> 表格格式设置 </legend>
					<textarea rows="8" ng-model="sysQueryField.formater"
						 class="w100 h100 margin-none" style="resize: none;"></textarea>
				</fieldset>
			</div>
		</form>
	</div>
	<div class="panel" ng-if="type=='virtualSetting'" ng-controller="sysQueryMetaFieldVirtualCtrl">
		<dialog-buttons></dialog-buttons>
		<form >
			<div class="panel-detail">
				<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
					<tr>
						<th width=100>字段名称:</th>
						<td>
							<input type="text" ng-model="sysQueryField.name" class="ht-input" />
						</td>
					</tr>
					<tr>
						<th width=100>字段描述:</th>
						<td>
							<input type="text" ng-model="sysQueryField.fieldDesc" class="ht-input" />
						</td>
					</tr>
					<tr>
						<th width=100>关联字段:</th>
						<td >
							{{sysQueryField.virtualFrom}}
						</td>
					</tr>
					<tr>
						<th width=100>数据来自于:</th>
						<td >
							{{service.getVirtualFromType(sysQueryField.resultFromType)}}
							<div class="tipbox" ng-if="sysQueryField.resultFromType==2">
								<a href="javascript:;" class="tipinfo">
									<span>SQL语句的写法,实例如下：<br/>
										select userName from userId=#CON#<br/>
										#CON#:表示源字段的值<br/>
										这个语句返回一个值。<br/>
									</span>
								</a>
							</div>
						</td>
					</tr>
					<tr  ng-if="sysQueryField.resultFromType==2">
						<th width=100>来源配置:</th>
						<td >
							<textarea type="text" ng-model="sysQueryField.resultFrom" rows="5"
								style="padding: 15px 0 0 0px;resize: none" class="w100 h100 border-none margin-none "></textarea>
						</td>
					</tr>
				</table>
			</div>		
		</form>
	</div>
</body>
</html>