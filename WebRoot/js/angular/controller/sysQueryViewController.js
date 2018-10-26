var sysQueryViewApp = angular.module('sysQueryViewApp', [ 'baseServices','DataRightsApp' ]);

sysQueryViewApp.controller('sysQueryViewCtrl',['$scope','BaseService','dataRightsService','$timeout',function($scope,BaseService,dataRightsService,$timeout){
	var service = dataRightsService;
	service.noRights = true;
	$scope.service = service;
	$scope.type = "sysQueryView";
	window.setTimeout(function(){
		$scope.tab =$("#tab").ligerTab({});
		$scope.hasInitTab = true;
		$scope.$digest();
		$scope.tab.selectTabItem("filterSetting");
		var width = $('div[tabid="filterSetting"]').width()- 50;
		var height = $("#sql").height();
		$scope.tab.selectTabItem("baseSetting");
		editor = CodeMirror.fromTextArea(document.getElementById("sql"), {
			mode: "text/x-mariadb",
			lineWrapping:true,
			lineNumbers: true
		 });
		editor.setSize(width,height);
		
		//初始化日期控件
		FormUtil.initCalendar();	
		$("#ruleDiv").linkdiv({data:$scope.filterFields.conditions,
							updateContent:updateContent,
							rule2json:rule2json});
		
		$("#sqlTip").qtip({
			content:{
				text:'<div>该脚本为 groovyScript脚本 ，返回值为可执行的sql语句,' +
					'<p>例：String sql ="select ID,field from table where 1=1";<br/>' +
					'Object field = map.get("field");<br/>' +
					'if(field!=null)<br/>' +
					'&nbsp;&nbsp;sql += " and field like \'%"+field+"%\'";<br/>' +
					'return sql; </p>' +
					'其中的map为系统所封装的一个参数;' +
					'<br/>在脚本中使用map.get("field")可以获取表单提交时所携带的field参数值，脚本应拼接并返回任意的可执行的sql语句.返回的sql中要含有ID</div>'
			}
		});
		$scope.filterFields.type = 1;
		$scope.$digest();
	},500);
	service.init($scope);
	$scope.filterUrl = __ctx + "/platform/system/sysQuerySetting/filterDialog.ht?tableId=";
	$scope.save = function(){
		if ($scope._validForm()) {
			if ($scope.dataRightsJson.id) {
				$.ligerDialog.confirm("保存会覆盖模板，如果修改了模板请手动保存模板后再进行保存业务数据模板，是否继续保存？","提示信息",function(rtn) {
						if (rtn) service.customFormSubmit();
					});
			} else {
				service.customFormSubmit();
			}
		}
	}
	
	$scope._validForm = function (){
		var form=$('#dataRightsForm');
		if(!form.valid()) return false;
		//判断排序字段太多报错问题
		if($scope.sortFields&&$scope.sortFields.length>3){
			$.ligerDialog.error("排序字段不能设置超过3个，请检查！","提示信息");
			$scope.tab.selectTabItem("sortSetting");
			return false;
		}
		//判断管理字段
		if(service.manageFieldValid($scope.manageFields)){
			$.ligerDialog.error("功能按钮出现重复的类型，请检查！","提示信息");
			$scope.tab.selectTabItem("manageSetting");
			return false;
		}
		if($scope.dataRightsJson.templateAlias==""){
			$scope.tab.selectTabItem("baseSetting");
			form.valid();
			return false;	
		}
		return true;
	}
	//预览
	$scope.preview = function (){
		var alias = $scope.dataRightsJson.sqlAlias;
		

		if($.isEmpty(alias)){
			$.ligerDialog.error("请设置完信息保存后预览!","提示信息");
			return ;
		}
		var url=__ctx+ "/platform/system/sysQueryView/"+alias+".ht";
		url=url.getNewUrl();
		$.openFullWindow(url);
	}
	//编辑模板
	$scope.editTemplate = function (){
		var id = $scope.dataRightsJson.id;
		if($.isEmpty(id)){
			$.ligerDialog.error("请设置完信息保存后编辑模板!","提示信息");
			return ;
		}
		var url=__ctx+ "/platform/system/sysQueryView/editTemplate.ht?id="+id;
		url=url.getNewUrl();
		$.openFullWindow(url);
	}
	//添加菜单
	$scope.addToResource = function () {
		var alias = $scope.dataRightsJson.sqlAlias;
		var url="/platform/system/sysQueryView/"+alias+"/"+$scope.dataRightsJson.alias+".ht";
		AddResourceDialog({addUrl:url});
	}
	$scope.selectFieldToGroup = function(field){
		$scope.groupingView = $scope.groupingView || [];
		if(!field.gchecked){
			for(var i = 0 ; i < $scope.groupingView.length ; i++){
				if($scope.groupingView[i].groupField == field.name){
					$scope.groupingView.splice(i,1);
					 return;
				}
			}
			 return;
		}
		for(var i = 0 ; i < $scope.groupingView.length ; i++){
			if($scope.groupingView[i].groupField == field.name){
				 return;
			}
		}
		$scope.groupingView.push({
			groupField:field.name,
			groupColumnShow : 1,
//			groupSummary : 0,
			groupOrder : "asc"
		})
	}
}])
//过滤条件隐藏属性<filter-hidden></filter-hidden>
.directive('filterHidden', function() {
	return {
		restrict : 'E',
		replace : true,
		template :  '<div class="hidden">'+
						'<!-- 数字的判断 -->'+
						'<span  id="judgeCon-1" class="judge-condition" >'+
							'<select  name="judgeCondition" class="ht-input" style="width:80px;  height: 30px;">'+
								'<option value="1">等于</option>'+
								'<option value="2">不等于</option>'+
								'<option value="3">大于</option>'+
								'<option value="4">大于等于</option>'+
								'<option value="5">小于</option>'+
								'<option value="6">小于等于</option>'+
							'</select>'+
						'</span>'+
						'<!-- 字符串的判断 -->'+
						'<span  id="judgeCon-2"  class="judge-condition">'+
							'<select name="judgeCondition" class="ht-input" style="width:80px;  height: 30px;">'+
								'<option value="1">等于</option>'+
								'<option value="3">等于(忽略大小写)</option>'+
								'<option value="2">不等于</option>'+
								'<option value="4">like</option>'+
								'<option value="5">like左</option>'+
								'<option value="6">like右</option>'+
							'</select>'+
						'</span>'+
						'<!-- 字典的判断 -->'+
						'<span  id="judgeCon-4"  class="judge-condition">'+
							'<select name="judgeCondition" class="ht-input" style="width:80px;  height: 30px;">'+
								'<option value="1">等于</option>'+
								'<option value="2">不等于</option>'+
							'</select>'+
						'</span>'+
						'<!-- 选择器的判断 -->'+
						'<span  id="judgeCon-5"   class="judge-condition">'+
							'<select  name="judgeCondition" onchange="judgeConditionChange.apply(this)" class="ht-input" style="width:80px;  height: 30px;">'+
								'<option value="1">包含</option>'+
								'<option value="2">不包含</option>'+
								'<option value="3">等于变量</option>'+
								'<option value="4">不等于变量</option>'+
							'</select>'+
						'</span>'+
						'<!-- 默认类型-->'+
						'<span id="normal-input" class="judge-value"  type="1">'+
							'<input class="short-input ht-input" name="judgeValue" type="text" style="width:100px;margin-left: 5px;"/>'+
						'</span>'+
						'<!-- 日期类型 -->'+
						'<span id="date-input" class="judge-value"  type="1">'+
							'<input id="date-input" type="text" class="Wdate ht-input" style="width:180px;"/>'+
						'</span>'+
					''+
						'<!-- 用户选择器 -->'+
						'<div id="user-div">'+
							'<span  class="judge-value" type="1">'+
								'<input type="hidden" value="" />'+
								'<input type="text" readonly="readonly" />'+
								'<a href="javascript:;" class="link users">选择</a>'+
							'</span>'+
						'</div>'+
					''+
						'<!-- 角色选择器 -->'+
						'<div id="role-div">'+
							'<span  class="judge-value"  type="1" >'+
								'<input type="hidden" value="" />'+
								'<input type="text" readonly="readonly" />'+
								'<a href="javascript:;" class="link roles">选择</a>'+
							'</span>'+
						'</div>'+
						'<!-- 组织选择器 -->'+
						'<div id="org-div">'+
							'<span  class="judge-value"  type="1">'+
								'<input type="hidden" value="">'+
								'<input type="text" readonly="readonly" />'+
								'<a href="javascript:;" class="link orgs">选择</a>'+
							'</span>'+
						'</div>'+
						'<!-- 岗位选择器 -->'+
						'<div id="position-div">'+
							'<span  class="judge-value"  type="1">'+
								'<input type="hidden" value="">'+
								'<input type="text" readonly="readonly" />'+
								'<a href="javascript:;" class="link positions">选择</a>'+
							'</span>'+
						'</div>'+
						'<!--常用变量-->'+
						'<span id="commonVar" class="judge-value"  type="2">'+
							'<select class="ht-input" style="width:80px">'+
								'<c:forEach items="${commonVars}" var="commonVar">'+
									'<option value="${commonVar.value}">${commonVar.name}</option>'+
								'</c:forEach>'+
							'</select>'+
						'</span>'+
						'<select id="flowVarsSelect" class="left margin-set ht-input" name="flowVars" onchange="flowVarChange.apply(this)" style="height: 30px; ">'+
							'<option value="">--请选择--</option>'+
							'<option value="{{f.fieldName}}" datefmt="{{f.dateFormat}}" ctltype="{{f.controlType}}"  ftype="{{f.dataType}}" ng-repeat="f in sysQueryMetaFields">{{f.fieldDesc}}</option>'+
						'</select>'+
					'</div>'
	};
})
//过滤条件 ， 嵌入式 <filter-include-setting></filter-include-setting>
.directive('filterIncludeSetting', function() {
	return {
		restrict : 'E',
		replace : true,
		template :  '<table style="margin: auto;width:100%;margin-top: 1px;" class="table-detail" cellpadding="0" cellspacing="0" border="0">'+
						'<thead>'+
							'<tr style="height: 50px;">'+
								'<td colspan="4">'+
									'脚本类型：'+
									'<select ng-model="filterFields.type" class="ht-input">'+
										'<option value="1" >条件脚本</option>'+
										'<option value="2" >SQL</option>'+
									'</select>'+
								'</td>'+
							'</tr>'+
						'</thead>'+
						'<tbody>'+
							'<tr>'+
								'<td colspan="4" >'+
									'<fieldset style="margin: 5px 0px 5px 0px;" id="filterSetting" ng-show="filterFields.type==1" >'+
										'<legend>'+
											'<span>条件设置</span>'+
										'</legend>'+
										'<div class="table-top">'+
											'<div class="table-top-right">'+
												'<div class="toolBar" style="margin:0;">'+
													'<div class="group">'+
														'<a class="link add" onclick="addDiv(1)">添加条件</a>'+
													'</div>'+
													'<div class="l-bar-separator"></div>'+
													'<div class="group">'+
														'<a class="link add" onclick="addDiv(2)">添加脚本</a>'+
													'</div>'+
													'<div class="l-bar-separator"></div>'+
													'<div class="group">'+
														'<a class="link switchuser" onclick="assembleDiv()">组合规则</a>'+
													'</div>'+
													'<div class="l-bar-separator"></div>'+
													'<div class="group">'+
														'<a class="link switchuser" onclick="splitDiv()">拆分规则</a>'+
													'</div>'+
													'<div class="l-bar-separator"></div>'+
													'<div class="group">'+
														'<a class="link del" onclick="removeDiv()">删除</a>'+
													'</div>'+
												'</div>'+
											'</div>'+
										'</div>'+
										'<div id="ruleDiv" style="border:2px solid #ccc;margin:5px 0 0 0;"></div>'+
									'</fieldset>'+
									'<fieldset style="margin: 5px 0px 5px 0px;" id="sqlSetting" ng-show="filterFields.type==2" >'+
										'<legend>'+
											'<span>SQL设置</span>'+
										'</legend>'+
										'<table  cellpadding="0" cellspacing="0" border="0" style="width: 100%;"  class="table-detail" >'+
											'<tr>'+
												'<td width="5%">'+
													'<div id="sqlTip">'+
														'<a href="javascript:;" class="tipinfo"></a>'+
													'</div>'+
													'<td width="10%">常用变量：</td>'+
													'<td>'+
														'<select id="varFieldSelect" class="left margin-set" name="varFields" onchange="varsChange.apply(this)">'+
															'<option value="">--请选择--</option>'+
															'<optgroup class="main-table-item" label="sql字段" ></optgroup>'+
															'<c:forEach items="${sysQueryFields}" var="field">'+
																'<option class="field-item"  value="${field.name}">${field.fieldDesc}</option>'+
															'</c:forEach>'+
															'<optgroup class="main-table-item" label="常用变量" ></optgroup>'+
															'<c:forEach items="${commonVars}" var="commonVar">'+
																'<option value="${commonVar.value}">${commonVar.name}</option>'+
															'</c:forEach>'+
														'</select>'+
													'</td>'+
												'</td>'+
											'</tr>'+
											'<tr>'+
												'<td colspan="7">'+
													'<textarea  id="sql" ng-model="filterFields.sql" style="height: 300px;" class="ht-input w100 border-none margin-none "></textarea>'+
												'</td>'+
											'</tr>'+
										'</table>'+
									'</fieldset>'+
								'</td>'+
							'</tr>'+
						'</tbody>'+
					'</table>'
	};
})
