var bpmDataTemplateApp = angular.module('bpmDataTemplateApp', [ 'baseServices','DataRightsApp' ]);

bpmDataTemplateApp.controller('bpmDataTemplateCtrl',['$scope','BaseService','dataRightsService','$timeout',function($scope,BaseService,dataRightsService,$timeout){
	var service = dataRightsService;
	$scope.service = service;
	$timeout(function(){
		$scope.tab =$("#tab").ligerTab({});	
		$scope.hasInitTab = 124;
	},100)
	service.init($scope);
	$scope.filterUrl = __ctx + "/platform/form/bpmDataTemplate/filterDialog.ht?tableId=";
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
	$scope.savesher = function(){
		if (1) {
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
		//if(service.manageFieldValid($scope.manageFields)){
		//	$.ligerDialog.error("功能按钮出现重复的类型，请检查！","提示信息");
		//	$scope.tab.selectTabItem("manageSetting");
		//	return false;
	//	}
		if($scope.dataRightsJson.templateAlias=="" || $scope.dataRightsJson.needPage ==""){
			$scope.tab.selectTabItem("baseSetting");
			$scope.form.valid();
			return false;	
		}
		return true;
	}
	//预览
	$scope.preview = function (){
		var alias = $scope.dataRightsJson.id;
		if($.isEmpty(alias)){
			$.ligerDialog.error("请设置完信息保存后预览!","提示信息");
			return ;
		}
		var url=__ctx+ "/platform/form/bpmDataTemplate/dataListbyKey_"+ alias+".ht";
		url=url.getNewUrl();
		$.openFullWindow(url);
	}
	//生成jsp
	$scope.loadJsp = function (){
		var id = $scope.dataRightsJson.id;
		var params={id:id};
		var sbtitle=document.getElementById("download");  
		var url=__ctx+ "/platform/form/bpmDataTemplate/loadJsp.ht";
			$.get(url,params,function(result){
				if(result){
					 $.ligerDialog.waitting('正在为您匹配jsp源码,请稍候...');
                     setTimeout(function ()
                     {
                         $.ligerDialog.closeWaitting();
                     }, 2000);
                     setTimeout(function (){
                    	 $.ligerDialog.success('匹配成功！',function(){
                    		 $("#downloadJsp").attr("href",__ctx+"/platform/form/bpmDataTemplate/downloadJsp.ht");
                    		 sbtitle.style.display='block'; 
                    	 });
                      }, 2000);
				}else{
					$.ligerDialog.warning('匹配失败！');
				}
		});
	}
	//编辑模板
	$scope.editTemplate = function (){
		var id = $scope.dataRightsJson.id;
		if($.isEmpty(id)){
			$.ligerDialog.error("请设置完信息保存后编辑模板!","提示信息");
			return ;
		}
		var url=__ctx+ "/platform/form/bpmDataTemplate/editTemplate.ht?id="+id;
		url=url.getNewUrl();
		$.openFullWindow(url);
	}
	//添加菜单
	$scope.addToResource = function (){
		var alias = $scope.dataRightsJson.formKey;
		var url="/platform/form/bpmDataTemplate/dataList_"+ alias+".ht";
		AddResourceDialog({addUrl:url});
	}
	
	/**
	* 选择流程
	*/
	$scope.selectFlow = function (){
		BpmDefinitionDialog({isSingle:true,callback:function(defIds,subjects){
			$scope.dataRightsJson.defId = defIds;
			$scope.dataRightsJson.subject = subjects;
			$("#subjectsher").attr("value",subjects);
		}});
	};
	$scope.cancel = function (){
		$scope.dataRightsJson.defId = "";
		$scope.dataRightsJson.subject = "";
	}
}]);
