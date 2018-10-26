var baseApp = angular.module('baseApp',['components','pascalprecht.translate']);
/**
 * 国际化配置
 */
baseApp.config(['$translateProvider',function($translateProvider){
		$translateProvider.useLoader('translateCustomLoader')
		.preferredLanguage(__lang)
		.fallbackLanguage(__lang);
}])
/**
 * 转换成html
 * 例子
 * <span ng-bind-html="item.status | trust_as_html"></span>
 */
.filter("trust_as_html",['$sce',function($sce){
	return function(text){
		return $sce.trustAsHtml(text);
	}
	
}])
/**
 * 转换附件格式的显示信息
 * 例子
 * <span ng-bind-html="item.status | trust_as_html"></span>
 */
.filter("turn_to_subdata",['$sce',function($sce){
	return function(text){
		if(text&&text.indexOf(HT.FILE_QUOTES)>=0){
			var jsonArr=eval(text.replaceAll(HT.FILE_QUOTES,"\""));
			text="";
			for(var i=jsonArr.length;i--;){
				text+=String.format('<span onclick="alert({0});">{1}</span>',"'"+jsonArr[i].id+"'",jsonArr[i].name);
			}
		}
		return $sce.trustAsHtml(text);
	}
	
}])
/**
 * 页面日期格式
 * 例子:
 * 1.{item.createtime |date_format}} 默认的
 * 2.{item.createtime |date_format:'yyyy-MM-dd'}} 按指定格式
 */
.filter("date_format",function(){
	return function(text,format){
		return HT.date(text,format);
	}	
})
//流程任务审批
.controller('taskInfoCtrl',['$scope','baseService',function($scope,baseService){
	//表单流程类型:0.新建流程表单、1.任务流程表单、2.流程实例表单
	var formFlowType = HT.getStorage("formFlowType"),
		defId = HT.getStorage("defId"),
		taskId = HT.getStorage("taskId"),
		runId =  HT.getStorage("runId"),
		//所有按钮
		allButtons = {'1':{'type':'1','name':$i18n$.FLOW.COMPLETE,'icon':'icon-checkmark'},
					  '2':{'type':'2','name':$i18n$.FLOW.OPPOSE,'icon':'icon-mail-reply'},
					  '3':{'type':'3','name':$i18n$.FLOW.ABSTENT,'icon':'icon-minus-square'},
					  '5':{'type':'5','name':$i18n$.FLOW.BACK,'icon':'icon-arrow-back'},
					  '6':{'type':'6','name':$i18n$.FLOW.ASSIGNEE,'icon':'icon-arrow-forward'},
					  '16':{'type':'16','name':$i18n$.FLOW.COMMU,'icon':'icon-comment'},
					  '18':{'type':'18','name':$i18n$.FLOW.FEEDBACK,'icon':'icon-back'},
					  "42":{type:"42",icon:"icon-bell-o",name:$i18n$.FLOW.REMINDER},
					  "43":{type:"43",icon:"icon-arrow-loop-outline",name:$i18n$.FLOW.RECOVER},
					  'more':{"type":"more","name":$i18n$.FLOW.MORE,"icon":"icon-eject2","subbnt":[]}};
					  
	$scope.buttons = [];
	$scope.showfooter=false;
	$scope.initButtons = function(){
		var detail_type = HT.getStorage("detail_type",true),
			buttonAry =[];
		if (detail_type == "undertake") {
			return;
		}else if(detail_type=="launch"){
			if(HT.getStorage("flow_status",true)!="1") return;
			var b= HT.getStorage("buttonsAry");
				b = b.split(",");
			angular.forEach(b, function(item){
				if(item==42||item==43)
					buttonAry.push(item);
			});
			if(buttonAry.length <= 0)
				buttonAry =[43];
		}else{
			buttonAry= HT.getStorage("buttonsAry");
			buttonAry = buttonAry.split(",");
		}
		var ary = [],
		moreBnt = {};
		angular.forEach(buttonAry, function(item){
			var button = allButtons[item];
			if (!button) 
				return true;
			ary.push(button);
		//			if(ary.length < 3){
		//				ary.push(button);
		//			}
		//			else if(ary.length == 3){
		//				moreBnt = {"type":"more","name":"更多","icon":"icon-eject2","subbnt":[]};
		//				ary.push(moreBnt);
		//			}
		//			else{
		//				moreBnt.subbnt.push(button);
		//			}
		});
		$scope.buttons = ary;
		if($scope.buttons.length>0){
			$scope.showfooter=true;
			$scope.$digest();
			HT.changePopHeight("form",-$$("footer").offsetHeight);
		}
	};
	$scope.changeTap = function(button){
		switch(button.type){
			case "1":
			HT.setStorage("flow_type",1);
			break;
			case "2":
			HT.setStorage("flow_type",2);
			break;
			case "3":
				HT.setStorage("flow_type",3);
			break;
			case "4":
				HT.setStorage("flow_type",5);
			break;
			case "5":
				HT.setStorage("flow_type",5);
			break;
			case "6":
				HT.setStorage("flow_type",6);
			break;
			case "16":
				HT.setStorage("flow_type",16);
			break;
			case "18":
				HT.setStorage("flow_type",18);
			break;
			case "42":
				HT.setStorage("flow_type",42);
			break;
			case "43":
				HT.setStorage("flow_type",43);
			break;
		}
		 uexWindow.evaluatePopoverScript("","form","isFormValid()");
	};
}])
.controller('flowCtrl',['$scope','baseService',function($scope,baseService){
	$scope.flowType=HT.getStorage("flow_type");
	$scope.title=$i18n$.FLOW.COMPLETE;
	$scope.param ={};
	$scope.showWrongMsg=function(){
		var msg=HT.getStorage("wrongmsg",true);
		baseService.openDialog({
			                title: $i18n$.COMMON.ERROR_MSG,
			                html: msg,
							digest:true,
							marTop:30,
			                buttons: [{
			                    label: $i18n$.COMMON.OK,
			                    callback: function(){
			                        baseService.closeDialog();
			                    }
			                }]
			            });
	}
	switch($scope.flowType){
		case "1" : //同意
			$scope.title=$i18n$.FLOW.COMPLETE;
			$scope.onlymsg=true;
			$scope.opinionValidate="";
			$scope.param.voteAgree =1;
			$scope.param.back =0;
			$scope.param.msg =$i18n$.FLOW.APPROVAL_SUCCESS;
		break;
		case "2" :
			$scope.title=$i18n$.FLOW.OPPOSE;
			$scope.onlymsg=true;
			$scope.param.voteAgree =2;
			$scope.param.back =0;
			$scope.param.msg =$i18n$.FLOW.OPPOSE_SUCCESS;
		break;
		case "3" ://弃权
			$scope.title=$i18n$.FLOW.ABSTENT;
			$scope.onlymsg=true;
			$scope.param.voteAgree =0;
			$scope.param.back =0;
			$scope.param.msg =$i18n$.FLOW.ABSTENT_SUCCESS;
		break;
		case "5" ://驳回都是驳回发起人
			$scope.title=$i18n$.FLOW.BACK;
			$scope.onlymsg=true;
			$scope.param.voteAgree =3;
			$scope.param.back =2;
			$scope.param.msg =$i18n$.FLOW.BACK_SUCCESS;
		break;
		case "16" : //沟通
			$scope.title=$i18n$.FLOW.COMMU;
			$scope.name=$i18n$.FLOW.COMMUNICATOR;
			$scope.reasontitle=$i18n$.FLOW.COMMU_REASON;
			$scope.reasonplaceholder=$i18n$.FLOW.ENTER_COMMU_REASON_PLEASE;
			$scope.onlymsg=false;
			$scope.param.voteAgree =15;
			$scope.param.msg =$i18n$.FLOW.COMMU_SUCCESS;
//			$scope.param.cmpNames = "万年飞";
//			$scope.param.cmpIds = "130000015589254";			
		break;
		case "18" : //沟通反馈
			$scope.title=$i18n$.FLOW.FEEDBACK;
			$scope.reasontitle=$i18n$.FLOW.FEEDBACK_OPINION;
			$scope.reasonplaceholder=$i18n$.FLOW.ENTER_FEEDBACK_OPINION_PLEASE;
			$scope.onlymsg=true;
			$scope.param.voteAgree =20;
			$scope.param.msg =$i18n$.FLOW.FEEDBACK_SUCCESS;
//			$scope.param.cmpNames = "万年飞";
//			$scope.param.cmpIds = "130000015589254";			
		break;
		case "6" ://转办
			$scope.title=$i18n$.FLOW.ASSIGNEE;
			$scope.name=$i18n$.FLOW.ASSIGNEES;
			$scope.reasontitle=$i18n$.FLOW.ASSIGNEE_REASON;
			$scope.reasonplaceholder=$i18n$.FLOW.ENTER_ASSIGNEE_REASON_PLEASE;
			$scope.onlymsg=false;
			$scope.param.voteAgree =21;
			$scope.param.msg =$i18n$.FLOW.ASSIGNEE_SUCCESS;
//			$scope.param.cmpNames = "郭梦恬";
//			$scope.param.cmpIds = "10000000571790";
		break;
		case "42"://催办
			$scope.title=$i18n$.FLOW.REMINDER;
			$scope.onlymsg=true;
			$scope.param.voteAgree =42;
//			$scope.param.back =0;
			$scope.param.msg =$i18n$.FLOW.REMINDER_SUCCESS;
		break;
		case "43" ://撤销
			$scope.title=$i18n$.FLOW.RECOVER;
			$scope.onlymsg=true;
			$scope.param.voteAgree =17;
//			$scope.param.back =0;
			$scope.param.msg =$i18n$.FLOW.RECOVER_SUCCESS;
		break;
	}
	
	/**
	 *  保存
	 */
	$scope.save = function (){
		switch($scope.flowType){
			case "2" :
			case "3" ://弃权
			case "5" ://驳回都是驳回发起人
				if(HT.isEmpty($scope.param.voteContent)){
					HT.hideModal();
					HT.alert($i18n$.FLOW.INPUT_OPINION);
					return false
				}
			break;
			case "16" : //沟通
				if(HT.isEmpty($scope.param.cmpIds)){
					HT.hideModal();
					HT.alert($i18n$.FLOW.SELET_COMM);
					return false
				}
				if(HT.isEmpty($scope.param.voteContent)){
					HT.hideModal();
					HT.alert($i18n$.FLOW.INPUT_OPINION);
					return false
				}	
			break;
			case "18" : //沟通反馈
				if(HT.isEmpty($scope.param.voteContent)){
					HT.hideModal();
					HT.alert($i18n$.FLOW.INPUT_OPINION);
					return false
				}			
			break;
			case "6" ://转办
				if(HT.isEmpty($scope.param.cmpIds)){
					HT.hideModal();
					HT.alert($i18n$.FLOW.SELET_ASSIGNEE);
					return false
				}
			break;
			case "42"://催办
				if(HT.isEmpty($scope.param.voteContent)){
					HT.hideModal();
					HT.alert($i18n$.FLOW.INPUT_OPINION);
					return false
				}			
			break;
			case "43" ://撤销
				if(HT.isEmpty($scope.param.voteContent)){
					HT.hideModal();
					HT.alert($i18n$.FLOW.INPUT_OPINION);
					return false
				}			
			break;
		}
        HT.setStorageJSON("param", $scope.param);
        return true;
	}
}]
)
//表单form
.controller('formCtrl',['$scope','baseService','$compile','$http',function($scope,baseService,$compile,$http){ 
	//表单流程类型:0.新建流程表单、1.任务流程表单、2.流程实例表单
	var formFlowType = HT.getStorage("formFlowType"),
		defId = HT.getStorage("defId"),
		taskId = HT.getStorage("taskId"),
		runId =  HT.getStorage("runId"),
		toastID = "flow_complete";
		actDefId =  HT.getStorage("actDefId");
		$scope.openSubPopver = function(tagetId){
			if($scope.$$childTail.isOnlyRead) return;
			HT.openSubPopver(tagetId);
		};
		$scope.editSubTableCol = function(subTableId,index){
			if($scope.$$childTail.isOnlyRead) return;
			var tagetScope=HT.$($$(subTableId)).scope();
//			tagetScope.item=$scope.sub[subTableId][index];
			angular.extend(tagetScope.item,$scope.sub[subTableId][index]);
			tagetScope.item.___index=index;
			$scope.openSubPopver(subTableId);
		};
	/**
	 * 获取新建流程表单
	 */
	$scope.getStartFlowForm = function(){
		//1.取得当前的表单ID
		baseService.post(__ctx+'/mobile/bpm/bpmMobileTask/startFlowForm.ht',
				{defId:defId},function(data){
					$scope.getFormTempteByData(data);
			},{__notshowtoast:true});
	}
	
	/**
	 * 获取任务的表单
	 */
	$scope.getTaskForm = function(){
		//1.取得当前的表单ID
		baseService.post(__ctx+'/mobile/bpm/bpmMobileTask/getTaskForm.ht',
				{taskId:taskId},function(data){
					$scope.getFormTempteByData(data);
			},{__notshowtoast:true});
	}
	/**
	 * 获取流程实例的表单
	 */
	$scope.getProcessForm = function(){
		//1.取得当前的表单ID
		baseService.post(__ctx+'/mobile/bpm/bpmMobileTask/getProcessForm.ht',
				{runId:runId},function(data){
					$scope.getFormTempteByData(data);
			},{__notshowtoast:true});
	}
	
	/**
	 * 通过数据获取表单模板及数据
	 */
	$scope.getFormTempteByData = function(data){
		if(!data.success){	
			HT.$($$("ngNullPage")).children().eq(1).html($i18n$.FORM.BIND_FORM);
			uexWindow.evaluateScript("newFlow_add",0,"HT.hideModal()");
			return;
		}
		var mobileFormId = data.mobileFormId;
		if(HT.isEmpty(mobileFormId)){//没有取到表单都是实时取表单模板
			$scope.formContent = data.template;
			$scope.getFormData();
			return;
		}
		//2.如果存在取出模板
		$scope.getLocalStorageTemplate(mobileFormId,function(formTemlate){
			if(!formTemlate || HT.isEmpty(formTemlate) || formTemlate.length <= 0){
				//3.不存在去数据库取
				$scope.getFormTempte({mobileFormId:mobileFormId});
			}else{
				//4.检查模板是否是最新版本
				$scope.checkFormVersion({mobileFormId:mobileFormId,formTemlate:formTemlate});
			}
		});
	}
	
	/**
	 * 检查版本
	 */
	$scope.checkFormVersion = function(conf){
		var mobileFormId=conf.mobileFormId,
			f = conf.formTemlate,
			guid = "",
			template ="",
			length = 0;
		if(f){
			guid = f.guid?f.guid:"";
			template = f.template;
			if(template)
				length = template.length;
		}
		baseService.post(__ctx+'/mobile/bpm/bpmMobileTask/checkFormVersion.ht',
			{mobileFormId:mobileFormId,guid:guid,length:length},function(data){
				if(!data.success){	
					HT.toast(data.msg);
					return;
				}
				var nguid = data.guid?data.guid:"",
					nlength = data.length;
				if (nguid != guid || nlength !== length) {//版本错误
					$scope.setLocalStorageTemplate(data, mobileFormId);
				}else {
					$scope.setFormContentAndgetFormData(template);
				}
			},{__notshowtoast:true});
	}
	
	/**
	 * 获取表单模板
	 */
	$scope.getFormTempte = function(conf){
		var mobileFormId=conf.mobileFormId;
		baseService.post(__ctx+'/mobile/bpm/bpmMobileTask/getFormTemplate.ht',
			{mobileFormId:mobileFormId},function(data){
				if(!data.success){	
					HT.toast(data.msg);
					return;
				}
				$scope.setLocalStorageTemplate(data,mobileFormId);
			},{__notshowtoast:true});
	};
	
	/**
	 * 设置表单页面并且获取数据
	 * @param {Object} template
	 */
	$scope.setFormContentAndgetFormData = function(template){
		$scope.formContent = template;
		//取数据
		$scope.getFormData();
		initHtml();
	}
	
	/**
	 * 根据数据设置模板的缓存
	 * 
	 */
	$scope.setLocalStorageTemplate = function(data,formid){
		var template = data.template,
			form = {};
		form.formid=formid;
		form.template = template;
		form.guid = data.guid?data.guid:"";
		if(HT.isMobile()){//手机
			try{
				HT.addUpdateCacheTemplate(form,function(data){
					$scope.setFormContentAndgetFormData(template);
				});
			} catch (e) {
				$scope.setFormContentAndgetFormData(template);
			}
		}else{
			$scope.setFormContentAndgetFormData(template);
		}
	}
	/**
	 * 	获取缓存的数据
	 * 
	 */
	$scope.getLocalStorageTemplate  = function(formid,callback){
		if(HT.isMobile()){
			try{
				HT.getCacheTemplate(formid,function(data){
					if (data && data.length > 0) {
						data[0].template = data[0].template.replaceAll(HT.FILE_QUOTES,"\"").replaceAll("#@@#","'");
						callback(data[0]);
					}
					else 
						callback(false);		
				});
			} catch (e) {
				callback(false);
			}
		}else{
			//pc存
			callback(false);
		}

	}
	/**
	 * 根据表单的流程类型正在获取表单数据
	 */
	$scope.getFormData = function(){
		//取数据
		if(formFlowType == 0){
			$scope.getStartFlowFormData();
		}else if(formFlowType == 1){
			$scope.getTaskFormData();
		}else if(formFlowType == 2){
			$scope.getProcessFormData();
		}
	}

	/**
	 * 获取新建表单数据
	 */
	$scope.getStartFlowFormData = function(conf){
	    baseService.post(__ctx+'/mobile/bpm/bpmMobileTask/getStartFlowData.ht',
				{defId:defId},function(data){
					if(data.success){
						var formData = data.data;
						if(HT.isMobile()){	
							HT.getCacheDraft(defId,function(drafts){
								if(drafts&&drafts.length>0){
									var draft =drafts[0];
									if(!HT.isEmpty(draft) && !HT.isEmpty(draft.data) ){
										baseService.openDialog({
							                title:  $i18n$.COMMON.WARN,
							                html:  $i18n$.FLOW.IS_LOAD_DRAFT_DATA,
											digest:true,
											inline:true,
							                buttons: [{
							                    label: $i18n$.COMMON.YES,
												marTop:30,
												forecolor:"red",
							                    callback: function(){
													formData = JSON.parse(draft.data);
													$scope.updateStartFlowFormData(data,formData);
													baseService.closeDialog();
							                    }
							                },{
							                    label: $i18n$.COMMON.CLEAR,
												marTop:30,
							                    callback: function(){
													HT.deleteCacheDraft(defId);
													baseService.closeDialog();
													$scope.updateStartFlowFormData(data,formData);
							                    }
							                },{
							                    label: $i18n$.COMMON.NO,
							                    callback: function(){
							                        baseService.closeDialog();
													$scope.updateStartFlowFormData(data,formData);
							                    }
							                }]
							            });
									}
								}else{
									$scope.updateStartFlowFormData(data,formData);
								}
							});
						}else{
							//数据
							$scope.updateStartFlowFormData(data,formData);
						}
					}else{
						HT.toast(data.msg);
					}
				},{___colseid:"newFlow_add",__notshowtoast:true});
	}
	
	$scope.updateStartFlowFormData = function (data,formData){
		//数据
		angular.extend($scope,formData);
		//权限permission.field.xxx
		angular.extend($scope,data.permission);
		initHtml();
		if(!$scope.$$phase) $scope.$digest();
		//联动
		HT.gangedSet({scope:$scope,gangedSet:data.gangedSet});
	}
	
	/**
	 * 获取任务表单数据
	 */
	$scope.getTaskFormData = function(conf){
	    baseService.post(__ctx+'/mobile/bpm/bpmMobileTask/getTaskFormData.ht',
			{taskId:taskId},function(data){
				if(data.success){
					//数据
					angular.extend($scope,data.data);
					//权限
					angular.extend($scope,data.permission);
					//按钮
					HT.setStorage("buttonsAry",data.buttons);
					angular.extend($scope,data.actDefId);
					angular.extend($scope,data.defId);
					initHtml();
					setTimeout(function(){
						uexWindow.evaluateScript("",0,"initButtons()");
					},100);
				}else{
					HT.toastInWindow("", "", data.msg, 2000);
					initHtml();
				}
			},{___colseid:"taskInfo",__notshowtoast:true});
	};

	/**
	 * 获取流程实例表单数据
	 */
	$scope.getProcessFormData = function (conf){
	    baseService.post(__ctx+'/mobile/bpm/bpmMobileTask/getProcessFormData.ht',
			{runId:runId},function(data){
				if(data.success){
					//数据
					angular.extend($scope,data.data);
					//权限
					angular.extend($scope,data.permission);
					//按钮
					HT.setStorage("buttonsAry",data.buttons);
					setTimeout(function(){
						uexWindow.evaluateScript("",0,"initButtons()");
					},100);
				}else{
					HT.toast(data.msg);
				}
			},{___colseid:"taskInfo",__notshowtoast:true});
	}
	
	//初始表单处理
	$scope.init = function(){
		if(formFlowType == 0){ //新建流程表单
			$scope.getStartFlowForm();
		}else if(formFlowType == 1){//任务流程表单
			$scope.getTaskForm();
		}else if(formFlowType == 2){//流程实例表单
			$scope.getProcessForm();
		}
	}

	/**
	 * 完成任务(包含同意【1,0】、驳回【3,1】、驳回发起人【3,2】、反对【2,0】、弃权【0、0】)
	 * 沟通【15】、沟通反馈【20】、转办【21】、转办取消【22】、 代理【26】、 取消代理【27】、 撤销 【17】、催办【42】
	 * 如果一个值的是voteAgree，2个值的第一个是voteAgree，另外一个是back
	 */
	$scope.complete = function(conf){
		/**
		 * @param {}  param 参数说明
		 * 	voteAgree	: 投票状态。如果不传，默认同意（1）
		 * 					0=弃权， 1=同意，2=反对， 3=驳回
		 * 					4=追回， 5=会签通过，6=会签不通过，34=重新提交；
		 *  back     	: 驳回。如果不传，默认不进行驳回（0）  
		 *					0. 不进行驳回 1，驳回，2，驳回发起人。【沟通、沟通反馈、转办、代理不需要传入这个参数】；
		 *  cmpIds   	： 沟通人ID或转办人ID或代理人ID；
		 *	cmpNames	： 沟通人姓名或转办人姓名或代理人姓名；
		 *  voteContent	: 意见
		 *  msg      	:  成功后的提示消息。默认是提交成功。
		 *  
		 */
		var param = HT.getStorageJSON("param",true);
		if(!param)
			param ={};
		var voteAgree = param.voteAgree?param.voteAgree:1,
			url = "",isValidateForm =false;
		try{
			if(conf&&(voteAgree==0||voteAgree==1||voteAgree==2||voteAgree==3||voteAgree==1000)&&conf.valid&&!$scope.valid({withoutdom:document.querySelectorAll(".scrollerH")})){
				HT.showmsg(baseService,$i18n$.FORM.FORM_VALID_ERROR)
				uexWindow.evaluateScript("newFlow_add",0,"HT.hideModal()");
				return;
			}
		}catch(e){
			HT.showmsg(baseService,$i18n$.FORM.BIND_FORM)
			uexWindow.evaluateScript("newFlow_add",0,"HT.hideModal()");
			return ;
		}
		switch (voteAgree) {
			case 0:
			case 1:
			case 2:
			case 3:
				url = __ctx+'/mobile/bpm/bpmMobileTask/complete.ht';
				param.actDefId = $scope.actDefId;
				param.defId = $scope.defId;
//				businessKey
//				formKey
				//获取表单是数据
				param.formData = $scope.getScopeFormData();
				param.taskId= taskId;
				isValidateForm = true;
				break;
			case 15://沟通
				url = __ctx+'/mobile/bpm/bpmMobileTask/toStartCommunication.ht';
				param.taskId= taskId;
				break;
			case  17://撤销
				url = __ctx+'/mobile/bpm/bpmMobileTask/recover.ht';
				param.runId= runId;
				break;
			case 20://沟通反馈
				url = __ctx+'/mobile/bpm/bpmMobileTask/saveOpinion.ht';
				param.taskId= taskId;
				break;
			case 21://转办
				url = __ctx+'/mobile/bpm/bpmMobileTask/assignSave.ht';
				param.taskId= taskId;
				break;
			case 22://取消转办
			case 27://取消代理
				url = __ctx+'/mobile/bpm/bpmMobileTask/cancelTaskExe.ht';
				param.taskId= taskId;
				break;	
			case 42://催办
				url = __ctx+'/mobile/bpm/bpmMobileTask/urgeSubmit.ht';
				param.runId= runId;
				break;
			case 1000://新建流程 启动
				url = __ctx+'/mobile/bpm/bpmMobileTask/startFlow.ht';
				param.defId= defId;
				param.actDefId= actDefId;
				param.formData = $scope.getScopeFormData();
				isValidateForm = true;
				toastID = "newFlow_add";
			default:
				break;
		}
		param.msg = param.msg?param.msg:$i18n$.FORM.SUBMIT_SUCCESS;
	   
	   var httpId = new Date().getTime().toString();
	   httpId=httpId.substring(httpId.length-3,httpId.length);
	   window.setTimeout(function(){
			HT.toastInWindow(toastID, "", $i18n$.FORM.WAITTING_MSG, 0);
		}, 1);
	   uexXmlHttpMgr.open(httpId,'post',url,30000);
		for (k in param) {
			if (!HT.isEmpty(param[k])) {
				var o = param[k];
				if(typeof param[k]=="object"){
					o = JSON.stringify(param[k])
				}
				uexXmlHttpMgr.setPostData(httpId, '0', k, o);
			}
		}

	   uexXmlHttpMgr.send(httpId);
       uexXmlHttpMgr.onData = function(inOpCode,inStatus,inResult){
	     HT.closeToastInWindow(toastID, "");
	     if(inStatus == 1){
		 	var data=eval(inResult);
		   if(data.success){
					HT.setStorage("__voteAgree",voteAgree);
					if(formFlowType ==0){
						uexWindow.evaluatePopoverScript("newFlow_add","form","HT.toast('"+param.msg+"')");
						setTimeout(function(){
							uexWindow.evaluatePopoverScript("index","mytask","reload();");
							uexWindow.evaluatePopoverScript("newFlow_add","form","HT.goback(-1);");
							uexWindow.evaluateScript("newFlow_add",0,"HT.goback(-1);");
							uexWindow.evaluateScript("index",0,"pendingCount();");
						},500)
					}else{
						uexWindow.evaluateScript("flow_complete",0,"HT.toast('"+param.msg+"')");
						uexWindow.evaluatePopoverScript("index","mytask","reload();");
						uexWindow.evaluateScript("index",0,"pendingCount()");
						if(voteAgree==17||voteAgree==42){
							uexWindow.evaluatePopoverScript("index","myLaunch","reload();");
						}
						window.setTimeout(function(){
							uexWindow.evaluateScript("flow_complete","0","HT.goback(-1);");
							uexWindow.evaluateScript("taskInfo",0,"HT.goback(-1);");
						},1200)
					}
					
				}else{
					var msg=data.msg.replaceAll('\"','').replaceAll(',',' ').replaceAll("\n","");
					if (formFlowType == 0) {
							baseService.openDialog({
						                title: $i18n$.COMMON.WARN,
						                html: msg.substring(0,60),
										marTop:30,
										digest:true,
						                buttons: [{
						                    label: $i18n$.COMMON.CANCEL,
						                    callback: function(){
						                        baseService.closeDialog();
												uexWindow.evaluateScript("newFlow_add",0,"HT.hideModal();");
						                    }
						                }]
						            })
					}else{
						HT.setStorage("wrongmsg",msg);
						uexWindow.evaluateScript("flow_complete", 0, "showWrongmsg()");
					}
				}
				window.setTimeout(function(){
					uexWindow.evaluateScript("flow_complete",0,"HT.hideModal()");
					uexWindow.evaluateScript("newFlow_add",0,"HT.hideModal();");
				},100)
		   
	       uexXmlHttpMgr.close(httpId);
	     }
	   }
	};
	$scope.backNewFlow = function(){
        if (HT.$($$("ngNullPage")).hasClass("uhide")) {
            baseService.openDialog({
                title: $i18n$.COMMON.WARN,
                html: $i18n$.FLOW.IS_SAVE_DRAFT_DATA,
                digest: true,
                inline: true,
                buttons: [{
                    label: $i18n$.COMMON.OK,
                    marTop: 30,
                    forecolor: "red",
                    callback: function(){
						baseService.closeDialog();
                        var d = {
                            main: $scope.main ? $scope.main : {},
                            sub: $scope.sub ? $scope.sub : {},
                            opinion: $scope.opinion ? $scope.opinion : []
                        };
                        var draft = {
                            defId: defId,
                            data: d
                        };
                        HT.addUpdateCacheDraft(draft);
                        uexWindow.evaluateScript("newFlow_add", 0, "back()");
                    }
                }, {
                    label: $i18n$.COMMON.CANCEL,
                    callback: function(){
                        baseService.closeDialog();
						uexWindow.evaluateScript("newFlow_add", 0, "back()");
                    }
                }]
            });
        }
        else {
            uexWindow.evaluateScript("newFlow_add", 0, "back()");
        }
	}

	/**
	 * 获取表单数据
	 */
	$scope.getScopeFormData = function(){
		
		var formData = {},
			fields = $scope.main?$scope.main:{},
			subdata = $scope.sub?$scope.sub:{},
			opinion = $scope.opinion?$scope.opinion:[],
			main = {
				fields:	fields
				},
			sub = [];
			if (!HT.isObjNull(subdata)) {
				for (i in subdata) {
					var s = {};
					s.tableName = i;
					s.fields = subdata[i];
					sub.push(s);
				}
			}
		formData.main = main;
		formData.sub = sub;
		formData.opinion = opinion;
		return formData;
	}
	
	$scope.showWrongMsg=function(msg){
		HT.showmsg(baseService,msg);
	}
}])
/**
 * 审批历史
 */
.controller('formhistoryCtrl',['$scope','baseService',function($scope,baseService){
	
	$scope.init=function(){
		var formFlowType = HT.getStorage("formFlowType"),
			taskId = HT.getStorage("taskId"),
			runId = HT.getStorage("runId"),
			params = {};
		if(formFlowType == 1)
			params.taskId = taskId;
		else
			params.runId = runId;
		baseService.post(__ctx+'/mobile/bpm/bpmMobileTask/taskOpinions.ht',
			params,function(data){
				$scope.lists = data.results;
				initHtml();
		}); 
	}
	$scope.displayDetail = function(user){
		HT.userInfo(baseService,user,'formhistory');
	};

}])
/**
 * 待办事宜
 */
.controller('myTaskCtrl', ['$scope','baseService', function($scope,baseService){
	$scope.showType=true;//HT.getStorage("__showType");
	//查询
 	$scope.search = function(isSearch){
		baseService.pageList(__ctx + '/mobile/bpm/bpmMobileTask/pendingMatters.ht', {
			userId: __curUserId,
			'Q_subject_SUPL': $scope.keywords,
			pageBean:$scope.pageBean
		}, function(data){
			$scope.tasks = data.results;
			$scope.issearched=$scope.keywords?true:false;
		},{pageLists:$scope.tasks,isSearch:isSearch});
	
 	};
	
	$scope.taskInfo = function(task){
		baseService.post(__ctx+'/mobile/bpm/bpmMobileTask/isAllowMobile.ht',
				{taskId:task.id},function(data){
					if(!data.success){
						HT.alert(data.msg);
						uexWindow.evaluatePopoverScript("index","mytask","reload();");
						uexWindow.evaluateScript("index",0,"pendingCount()");
					}else{
						if(data.type == 1){
							HT.alert(data.msg);	
						}else{
							HT.setStorage("formFlowType",1);
							HT.setStorage("taskId",task.id);
							HT.goToHtml("taskInfo");
						}
					}
	
		},{__notshowtoast:true});
	};
}])
/**
 * 历史-我的承接
 */
.controller('myUndertakeCtrl', ['$scope','baseService', function($scope,baseService){	
	//查询
	$scope.search = function(isSearch){
			if(!HT.getStorage("__firstToMyUndertake",true)){
				baseService.pageList(__ctx + '/mobile/bpm/bpmMobileTask/myUndertake.ht', {
					userId: __curUserId,
					'Q_subject_SUPL': $scope.keywords,
					pageBean:$scope.pageBean
				}, function(data){
						$scope.lists = data.results;
						$scope.issearched=$scope.keywords?true:false;
				},{pageLists:$scope.lists,isSearch:isSearch});	
			}
 	};

	 	
	$scope.processRunInfo = function(processRun){
		//判断是否有任务
		baseService.post(__ctx+'/mobile/bpm/bpmMobileTask/hashTask.ht',
				{actInstId:processRun.actInstId},function(data){
					if(data.success){
						var isTask =  data.isTask;
						HT.setStorage("formFlowType",data.isTask?1:2);
						if(isTask){
							var task  = data.task;
							HT.setStorage("taskId",task.id);
						}else{
							HT.setStorage("runId",processRun.runId);
						}
						HT.setStorage("detail_type","undertake");
						HT.goToHtml("taskInfo");
					}else{
						HT.alert(data.msg);
					}
			});
	};
}
])
/**
 * 历史-我的发起
 */
.controller('myLaunchCtrl', ['$scope','baseService', function($scope,baseService){
	//查询
	$scope.search = function(isSearch){
		if (!HT.getStorage("__firstToMyLaunch", true)) {
			baseService.pageList(__ctx + '/mobile/bpm/bpmMobileTask/myLaunch.ht', {
				userId: __curUserId,
				'Q_subject_SUPL': $scope.keywords,
				pageBean: $scope.pageBean
			}, function(data){
				$scope.lists = data.results;
				$scope.issearched = $scope.keywords ? true : false;
			}, {
				pageLists: $scope.lists,
				isSearch: isSearch
			});
		}
	};

	 	
	$scope.processRunInfo = function(processRun){
		//判断是否有任务
		baseService.post(__ctx+'/mobile/bpm/bpmMobileTask/hashTask.ht',
				{actInstId:processRun.actInstId},function(data){
					if(data.success){
						var isTask =  data.isTask;
						HT.setStorage("formFlowType",data.isTask?1:2);
						if(isTask){
							var task  = data.task;
							HT.setStorage("taskId",task.id);
						}else{
							HT.setStorage("runId",processRun.runId);
						}
						HT.setStorage("detail_type","launch");
						HT.setStorage("flow_status",processRun.status);
						HT.goToHtml("taskInfo");
					}else{
						HT.alert(data.msg);
					}
			});
	};
}
])
/**
 * 流程代理
 */
.controller('agentFlowCtrl',['$scope','baseService',function($scope,baseService){
		//查询
	$scope.search = function(isSearch){
			baseService.pageList(__ctx + '/mobile/bpm/bpmMobileAgent/list.ht', {
				userId: __curUserId,
				'Q_subject_SUPL': $scope.keywords,
				pageBean:$scope.pageBean
			}, function(data){
				$scope.lists = data.results;
			},{pageLists:$scope.lists,isSearch:isSearch});	
 	};
	$scope.angentFlow = function(agentSetting){
		//条件代理
		if(agentSetting.authtype=='2'){
			uexWindow.toast(1,5,$i18n$.AGENT_SETTING.SET_CONDITIONAL_AT_PC,1000);
			return;
		}
		HT.setStorage("agentSettingId",agentSetting.id);
		HT.goToHtml("agentFlow_setting");
	};
	
	$scope.updStatus = function(agentSetting,me){
		var status = agentSetting.enabled==1?0:1;
		agentSetting.enabled = status;
		baseService.post(__ctx+'/mobile/bpm/bpmMobileAgent/updStatus.ht',
				{id:agentSetting.id,status:status},function(data){
					if(!data.success){	
						angular.element(me).next().next().checked= (status==1?null:'checked');
						agentSetting.enabled = (status==1?0:1);
						HT.alert(data.msg);
						return;
					}
			});
		
	};
	$scope.confirmDelete=function(id,$index){
		baseService.openDialog({
			                title:  $i18n$.COMMON.WARN,
			                html:  $i18n$.AGENT_SETTING.IS_DELETE_AGENT,
							digest:true,
							inline:true,
			                buttons: [{
			                    label: $i18n$.COMMON.OK,
								marTop:30,
								forecolor:"red",
			                    callback: function(){
			                       baseService.post(__ctx+'/mobile/bpm/bpmMobileAgent/del.ht',
												{id:id},function(data){
												if(!data.success){	
													HT.alert(data.msg);
													return;
												}else{
													for (var i in $scope.lists) {
														if ($scope.lists[i].id == id) {
															$scope.lists.splice(i, 1);
														}
													}
													HT.toastInWindow("", "", $i18n$.COMMON.DEL_SUCCESS, 1500,0);
													baseService.closeDialog();
													
												}
											});
			                    }
			                },{
			                    label: $i18n$.COMMON.CANCEL,
			                    callback: function(){
			                        baseService.closeDialog();
			                    }
			                }]
			            });
	}
}])
/**
 * 流程代理设定
 */
.controller('agentFlowSettingCtrl',['$scope','baseService',function($scope,baseService){
	$scope.agentSetting ={
			authtype:"0",
			enabled:"1"
	};
	$scope.$watch('agentSetting.authtype', function(newVal, oldVal) {
	    if (newVal !== oldVal) {
			uexWindow.evaluateScript("agentFlow_setting",0,"rtClick("+$scope.agentSetting.authtype+")");
	    }
	  }, true);
	$scope.init = function(){
		var id = HT.getStorage("agentSettingId",true);
		baseService.post(__ctx+'/mobile/bpm/bpmMobileAgent/edit.ht',
				{id:id},function(data){
				if(!data.success){	
					HT.alert(data.msg);
					return;
				}
				$scope.agentSetting =data.agentSetting;
				$scope.agentSetting.agent = $scope.agentSetting.agent||{};
				//部分代理
				if($scope.agentSetting.authtype=="1"){
					var agentDefList = $scope.agentSetting.agentDefList;
					if(agentDefList&&agentDefList.length>0){
						var flows = [];
						angular.forEach(agentDefList,function(agentDef){
							flows.push({defKey:agentDef.flowkey,subject:agentDef.flowname,defId:agentDef.flowid});
						});
						HT.setStorageJSON("selectedFlow",flows);	
					}
				}
				$scope.valid();
			});
	}
	$scope.toNext = function(){
		var v =	$scope.valid();
		uexWindow.evaluateScript("agentFlow_setting",0,"HT.hideModal()");
		if(!v){
			HT.showmsg(baseService,$i18n$.FORM.FORM_VALID_ERROR);
			return;
		}
		HT.goToHtml("choseFlow");
	}
	//保存
	$scope.save = function(){
		var v =	$scope.valid();
		uexWindow.evaluateScript("agentFlow_setting",0,"HT.hideModal()");
		if(!v){
			HT.showmsg(baseService,$i18n$.FORM.FORM_VALID_ERROR);
			return;
		}
		var flows = HT.getStorageJSON("selectedFlow",true),
			agentSetting =  $scope.agentSetting,
			json ={
				id:agentSetting.id,
				authid:agentSetting.authid,
				createtime:HT.date(agentSetting.createtime),
				startdate:HT.date(agentSetting.startdate),
				enddate:HT.date(agentSetting.enddate),
				enabled:agentSetting.enabled,
				authtype:agentSetting.authtype,
				agentid:agentSetting.agent.userId
			},
			agentDefList = [];
		if(flows&&flows.length>0){
			angular.forEach(flows,function(flow){
				agentDefList.push({flowkey:flow.defKey,flowname:flow.subject,flowid:flow.defId});
			});
			json.agentDefList = agentDefList;
		}
		baseService.post(__ctx+'/mobile/bpm/bpmMobileAgent/save.ht',
				{json:json},function(data){
				if(!data.success){	
					HT.alert(data.msg);
					return;
				}
				uexWindow.evaluatePopoverScript("agentFlow","agentFlow_content","reload();")
				HT.toast($i18n$.COMMON.ADD_SUCCESS);
				window.setTimeout(function(){
					uexWindow.evaluateScript("choseFlow","0","HT.goback(-1);")	
					uexWindow.evaluateScript("agentFlow_setting_content","0","HT.goback(-1)");
					uexWindow.evaluateScript("agentFlow_setting","0","HT.goback(-1)");
				},1200)
			});
	}
	
}])
/**
 * 部分代理中选择代理流程
 */
.controller('choseFlowCtrl',['$scope','baseService',function($scope,baseService){
	$scope.lists = [];
	$scope.ischange="";
	$scope.remove = function($index){
		$scope.lists.splice($index,1);
	}
	
	$scope.save = function(){
		var lists =  $scope.lists;
		if(lists.length <=0){
			HT.alert($i18n$.AGENT_SETTING.ADD_FLOW);
			return;
		}
		HT.setStorageJSON("selectedFlow",$scope.lists);
		uexWindow.evaluatePopoverScript("agentFlow_setting","agentFlow_setting_content","save()");
	}
	$scope.$watch("ischange",function(newVal,oldVal){
		if(newVal){
			var lists =  HT.getStorageJSON("selectedFlow",true);
			if(!lists||lists.length==0)return;
	    		$scope.lists = lists;
				$scope.ischange="";
		}
	})
	$scope.ischange=true;
}])
/**
 * 流程选择器
 */
.controller('flowSelectorCtrl',['$scope','baseService',function($scope,baseService){
	var choseItem = [];
	$scope.flows = [];
	$scope.defkey="";
	//查询
	$scope.search = function(isSearch){
			baseService.pageList(__ctx + '/mobile/bpm/bpmMobileAgent/selector.ht', {
				"Q_subject_SUPL": $scope.keywords,
				pageBean:$scope.pageBean
			}, function(data){
				if (data) {
					$scope.flows = data.results;
					$scope.issearched=$scope.keywords?true:false;
				}
			},{pageLists:$scope.flows,isSearch:isSearch});	
	};
	
	$scope.mergeArray = function(a,b){
		if(!angular.isArray(a)||!angular.isArray(b))return;
		for(var i=0;i<a.length;i++){
			for(var j=0;j<b.length;j++){
				if(a[i].defKey==b[j].defKey){
					a.splice(i,1);
					break;
				}
			}
		}
		for(var i=0;i<b.length;i++){
			a.push(b[i]);
		}
		return a;
	}
	$scope.complete = function(){
		var keyAry = $scope.defkey.split(','),
			 defKey = '',
			oldLists =[],
			currentLists;
		if(!keyAry||keyAry.length==0) return;
		angular.forEach($scope.flows,function(flow){
			defKey = flow.defKey;
			if(keyAry.indexOf(defKey) > -1){
				choseItem.push(flow);
			}
		});
		oldLists = HT.getStorageJSON("selectedFlow",true);
		if(!oldLists) oldLists = [];
		currentLists = $scope.mergeArray(oldLists,choseItem);
		HT.setStorageJSON("selectedFlow",currentLists);
		uexWindow.evaluatePopoverScript("choseFlow","choseFlow_content","HT.reload();");
		uexWindow.evaluateScript("flow_selector",0,"HT.goback(-1);");
		HT.goToHtml("choseFlow");
	}
}])
.controller('mytimeEventCtrl', ['$scope','baseService', function($scope,baseService){
    $scope.update = function(cb){
		baseService.mytimeSwitch = true;
    }
}])

.controller('mytimeCtrl', ['$scope','baseService', function($scope,baseService){
	$scope.service = baseService;
	$scope.$watch('service', function(newVal, oldVal) {
	    if (newVal !== oldVal) {
	      if(baseService.mytimeSwitch){
		  	baseService.mytimeSwitch = false;
		  	$scope.update();
		  }
	    }
	  }, true);
	  
    $scope.update = function(cb){
		baseService.post(__ctx+'/mobile/bpm/bpmMobileTask/myAvgTime.ht',
				{userId: __curUserId},function(data){
					 var json = {},
					 	avg_hour  = 0,
					 	avg_minute = 0,
					 	org_avg_hour=0,
					 	org_avg_minute=0,
					 	level = 0,
					 	org_level=0,
					 	avg_order =0,
					 	task_count=0,
					 	count = 0;
					 if(data){
						 var avg_time = HT.parseInt(data.AVG_TIME),
						 	org_avg_time = data.ORG_AVG_TIME;
						 	avg_order=data.AVG_ORDER;
						 	count =data.COUNT;
						 	task_count =data.TASK_COUNT;
						 if(avg_time>0){
							 avg_hour = HT.parseInt(avg_time/3600000);
							 avg_minute = HT.parseInt(avg_time/60000)-avg_hour*60;
							
						 }
						 if(org_avg_time>0){
							 org_avg_hour = HT.parseInt(org_avg_time/3600000);
							 org_avg_minute = HT.parseInt(org_avg_time/60000)-org_avg_hour*60; 
						 }
						 
						 org_level = org_avg_time == avg_time?0:(org_avg_time >avg_time?1:-1);
						
						 if(avg_hour <=4)
							 level = 5;
						 else if(avg_hour <=8)
							 level = 4;
						 else if(avg_hour <=24)
							 level = 3;
						 else if(avg_hour <=48)
							 level = 2;
						 else if(avg_hour <=72)
							 level = 1;
					 }
				 	json = {
							  avg_time:avg_hour+$i18n$.MY_TIME.HOUR+avg_minute+$i18n$.MY_TIME.MINUTE,
							  org_avg_time:org_avg_hour+$i18n$.MY_TIME.HOUR+org_avg_minute+$i18n$.MY_TIME.MINUTE,
							  avg_order:avg_order,
							  count:count,
							  task_count: task_count,//任务数
							  level:level,
							  org_level:org_level
					 	}
					 $scope.mytime = json;
			});
    }
    $scope.update();
}
])
/**BpmDefinitionController.java
 * 新建流程
 */
.controller('newFlowCtrl', ['$scope','baseService', function($scope, baseService){
	baseService.post(__ctx+'/mobile/bpm/bpmMobileTask/newProcess.ht',
			{userId:__curUserId},function(data){
				$scope.lists = data.results;
				$scope.issearched=true;
	});
	$scope.startFlow = function(bpmDefinition){
		HT.setStorage("defId",bpmDefinition.defId);
		HT.setStorage("actDefId",bpmDefinition.actDefId);
		HT.setStorage("formFlowType",0);
		HT.goToHtml('newFlow_add');
	}
}])

.controller('indexCtrl',['$scope','$compile','baseService','$translate',function($scope,$compile,baseService,$translate){
	$scope.searchClass="ion-search";
	$scope.maintitle=true;
	$scope.historytitle=false;
	$scope.pendingCount="";
	HT.addIndexFooter($scope,$compile);
	$scope.getPendingCount = function(){
		baseService.post(__ctx+'/mobile/bpm/bpmMobileTask/pendingMattersCount.ht',
			{userId:__curUserId},function(data){
				$scope.pendingCount = data.count;
		});
	}
	$scope.getPendingCount();
	$scope.showSearch = function(me,i,title){
		if(i=="mytask"){
			$scope.plusClass="icon-plus5";//
			$scope.ltClass="";
			$scope.ltName="";
			$scope.maintitle=true;
			 HT.$($$("indexTab")).addClass("uhide");
			$scope.historytitle=false;
			$$("ifooter_"+i).checked=true;
			uexWindow.evaluatePopoverScript("","contactCommon","cancelDel()");
		}else if(i=="contactCommon"){
            $scope.plusClass = "icon-user-add2";
            $scope.ltClass = "contactMge";
            $scope.ltName = $i18n$.NEW_FLOW.MANAGEMENT;
            $scope.maintitle = true;
			HT.setStorage("isfromform","false");
			HT.setStorage("multi","1");
           	HT.$($$("indexTab")).addClass("uhide");
		   	$$("ifooter_"+i).checked=true;
		}else if(i=="myLaunch"){
			$scope.plusClass="";
			$scope.ltClass="";
			$scope.ltName="";
			$scope.maintitle=false;
			 HT.$($$("indexTab")).removeClass("uhide");
			 $$("ifooter_"+i).checked=true;
			 if(HT.$($$("indexTab")).children().children().eq(0).hasClass("ubb2")){
			 	i="myUndertake";
			 }
			 uexWindow.evaluatePopoverScript("","contactCommon","cancelDel()");
		}else{
            $scope.maintitle = true;
			$scope.plusClass="";
			$scope.ltClass="";
			$scope.ltName="";
            HT.$($$("indexTab")).addClass("uhide");
			$$("ifooter_"+i).checked=true;
			uexWindow.evaluatePopoverScript("","contactCommon","cancelDel()");
		}
		$scope.rtName ="";
		$translate(title).then(function(translation){
			$scope.title = translation;
		});
		HT.openPopFrame(i);
	}
	var isCheckedAll = 0;
	$scope.rtClick= function(){
		if($scope.plusClass=="icon-plus5"){
			HT.goToHtml('newFlow');
		}else if($scope.plusClass=="icon-user-add2"){
			$scope.ltClass = "contactMge";
            $scope.ltName = $i18n$.NEW_FLOW.MANAGEMENT;
			contact.cancel();
			HT.setStorage(HT_LS.selectorSelected,"[]");
			HT.setStorage(HT_LS.activeWin,"contactCommon");
			HT.setStorage("isfromform","false");
			HT.setStorage("isMulti",1);
			uexWindow.open("contactAll",0,"contactAll.html",0,"","",0);
		}else if($scope.plusClass=="contactSelAll"){
			if(isCheckedAll==0)
				isCheckedAll=1;
			else
				isCheckedAll=0;
			uexWindow.evaluatePopoverScript("","contactCommon","selAllUserChb('"+isCheckedAll+"')");
		}
	}
	$scope.ltClick = function(){
		if ($scope.ltClass == "contactMge") {
			$scope.ltClass ="contactDel";
			$scope.ltName =$i18n$.NEW_FLOW.DEL;
			$scope.plusClass="contactSelAll";
			$scope.rtName =$i18n$.NEW_FLOW.CHECK_ALL;
			contact.commonMge();
		}else if ($scope.ltClass == "contactDel") {
			$scope.ltClass = "contactMge";
            $scope.ltName = $i18n$.NEW_FLOW.MANAGEMENT;
			$scope.plusClass="icon-user-add2";
			$scope.rtName ="";
			contact.del();
		}
	}
	
	// 推送回调
	$scope.pushCallBack = function(type){
		uexWidget.cbGetPushInfo=function(opId,dataType,data){
			var json = JSON.parse(data);
			$scope.getPendingCount();
			if(!type){
				HT.toTaskInfo(baseService, json.taskid);
			}else{
			    uexWindow.cbConfirm=function(opId,dataType,data){
					if (data == 1) {
						HT.toTaskInfo(baseService, json.taskid);
					}
			    }
				uexWindow.confirm("待办事项",json.content,["取消","查看"]);
			}
		}
		uexWidget.getPushInfo();
	}
	
	$scope.title=$i18n$.TITLE.MY_TODO;
	$scope.plusClass="icon-plus5";
}])

.controller('contactOrgCtrl', ['$scope', 'baseService', function($scope,baseService){
	$scope.keywords="";
	$scope.getOrg = function(o,fullname,length,type,isNavi,isRefresh){
		var param = {
			orgid: o.id,
			username:fullname,
			type:type,
			isRefresh:isRefresh,
			orgCount: $scope.orgCount,
			start:length+1
		};
		baseService.pageList1(__ctx + '/mobile/contact/bpmMobileContact/_list.ht', param, function(data){
			var ds = data.results;
			if(ds.length>0){
				var us = [];
				if(type==1&&$scope.users!=null){
					us = $scope.users;
				}
				for(var i=0;i<ds.length;i++){
					us.push(ds[i]);
				}
				$scope.users = us;
				$scope.userCount = data.userCount;
				$scope.orgCount = data.orgCount!=null?data.orgCount:"0";
				if (!isRefresh) {
					HT.setSeletorNavi(o,isNavi,head,$scope);
				}
					
				if (__orgScroll) {
					setTimeout(function(){
						__orgScroll.refresh();
						if (type == 1) {
							__orgScroll.scrollTo(0, -__orgScroll.scrollerH, 0);
						}
						HT.checkSelected();
					}, 200);
				}
			}else if(ds.length==0&&type==0){
				$scope.users = [];
			}
		},{})
	}
	
	// 初始化
	$scope.navis = [];
	var head = {id: "0",name: $i18n$.ORG.ALL};
	$scope.headNavi = head;
	$scope.prevNavi = head;
	$scope.isMulti = HT.getStorage("isMulti");
	$scope.isfromform = HT.getStorage("isfromform")=="false"?false:true;
	$scope.getOrg({id:0},"",0,2);

	$scope.clickOrg = function(o){
		$scope._disOrgClick=true;
		$scope.getChooseUser();
		$scope.getOrg(o,"",0,2);
		setTimeout(function(){
			$scope._disOrgClick=false;
		}, 800);
	}
	
	$scope.navi = function(o){
		$scope.getChooseUser();
		$scope.getOrg(o, "", 0, 2, 1);
	}
	
	$scope.addToFrequentUser = function(){
		$scope.getChooseUser();
		HT.addContact();
	}
	
	$scope.getChooseUser = function(){
		var choseItem = HT.getChoseItem($scope.users);
		var multi = HT.getStorage("isMulti");
		if(multi!="1"){
			if(choseItem.length>0)
				HT.setStorageJSON(HT_LS.selectorSelected, choseItem);
		}else{
			var sels = HT.mergeUsers(HT.getStorageJSON(HT_LS.selectorSelected), choseItem, $scope.users);
			HT.setStorageJSON(HT_LS.selectorSelected, sels);
		}
	}
	
	$scope.showSelected = function(){
		$scope.getChooseUser();
		HT.setStorage(HT_LS.selectorToSelected,"1");
		uexWindow.open("contactSelected",0,"contactSelected.html",0,"","",0);
	}
	
	//查询
	$scope.search = function(){
		var search = $scope.keywords;
		var o = $scope.lastNavi;
		if(o==null){
			o = $scope.headNavi;
		}
		$scope.getOrg(o,search.trim(),0,0); 
		$scope.issearched=$scope.keywords?true:false;
	}
}])

// 已选联系人
.controller('contactSelectedCtrl', ['$scope', function($scope){
	var us = HT.getStorageJSON(HT_LS.selectorSelected);
	$scope.users= us;
	$scope.remove=function($index){
		$scope.users.splice($index,1);
		HT.setStorageJSON(HT_LS.selectorSelected,$scope.users);
	}
}])

// 常用联系人
.controller('contactCommonCtrl', ['$scope','baseService', function($scope,baseService){
	$scope.keywords="";
	$scope.initUsers = function(){
		HT.getAllFrequent(function(rs){
			if(rs){
				var us = [];
				if(rs.length>0){
					var zms = [];
					angular.forEach(rs, function(u){
						var zm = makePy(u.name)[0].substring(0,1);
						u.fk = zm+"0";
						u.position = u.position!=null?u.position:"";
						us.push(u);
						if (zms.indexOf(zm) == undefined) {
							zms.push(zm);
						}
					});
					angular.forEach(zms, function(zm){
						var groupName = zm.substring(0, 1);
						us.push({
							id: zm,
							name: groupName,
							fk: groupName,
							t: 1
						});
					});
				}
				$scope.users = us;
				var isMulti = HT.getStorage("isMulti");
				$scope.isMulti = isMulti;
				var isfromform = HT.getStorage("isfromform");
				$scope.isfromform = isfromform;
				var chbHide = true;
				var radHide = true;
				if (isMulti == "0" && isfromform == "true") {
					chbHide = true;
					radHide = false;
				}
				else if (isMulti == "1" && isfromform == "true") {
					chbHide = false;
					radHide = true;
				}
				$scope.chbHide = chbHide;
				$scope.radHide = radHide;
				$scope.zmArea = true;
				$scope.$digest();
				__myScroll = new iScroll('wrapper', {
					vScrollbar: false
				});
			}
		});
	}
	
	// 删除常用联系人
    $scope.del = function(){
		var choseItem = [];
 	    var userChbs = angular.element(document.getElementsByName("userChb"));
		for(var i=0;i<userChbs.length;i++){
			var u = angular.element(userChbs[i]);
			if(u.prop("checked")){
				var o = u.val().split("_");
				choseItem.push({
					id:o[0],
					name:o[1]
				});
			}
		}
		if (choseItem.length == 0) {
			hideUserChb();
			return;
		}
		
		var ids ="";
		angular.forEach(choseItem,function(user){
			ids+=user.id+","
		});
		ids = ids.length>0?ids.substring(0,ids.length-1):"";
		var sql = "delete from frequent_contacts where id in("+ids+") and refid='"+__curUserId+"'";
		HT.openDataBase();
		HT.executeSql(sql,function(rs){
			$scope.initUsers();
		});
    }
	$scope.confirmDelete=function(id){
		baseService.openDialog({
			                title:  $i18n$.COMMON.WARN,
			                html:  $i18n$.AGENT_SETTING.IS_DELETE_COMMON,
							digest:true,
							inline:true,
			                buttons: [{
			                    label: $i18n$.COMMON.OK,
								marTop:30,
								forecolor:"red",
			                    callback: function(){
                                    var sql = "delete from frequent_contacts where id in("+id+") and refid='"+__curUserId+"'";
									HT.openDataBase();
									HT.executeSql(sql,function(rs){
										$scope.initUsers();
									});
                                    baseService.closeDialog();
			                    }
			                },{
			                    label: $i18n$.COMMON.CANCEL,
			                    callback: function(){
			                        baseService.closeDialog();
			                    }
			                }]
			            });
	}
	
	$scope.getChooseUser = function(){
		var userChbs = angular.element(document.getElementsByName("userChb"));
		var choseItem = [];
		for(var i=0;i<userChbs.length;i++){
			var u = angular.element(userChbs[i]);
			if(u.prop("checked")){
				var o = u.val().split("_");
				choseItem.push({
					id:o[0],
					name:o[1],
				});
			}
		}
		var multi = HT.getStorage("isMulti");
		if(multi!="1"){
			if(choseItem.length>0)
				HT.setStorageJSON(HT_LS.selectorSelected, choseItem);
		}else{
			var sels = HT.mergeUsers(HT.getStorageJSON(HT_LS.selectorSelected), choseItem, $scope.users);
			HT.setStorageJSON(HT_LS.selectorSelected, sels);
		}
	}
	
}])
.controller('loginCtrl',['$scope','baseService','$translate',function($scope,baseService,$translate){
	$scope.user ={
			username:'admin',//217107
			password:'sf#ecp123'
		}
	//登陆处理
	 $scope.login = function(user){
		if (HT.isEmpty(user)){
			HT.alert($i18n$.LOGIN.USERNAME_OR_PASSWORD);
			return;
		}
		 if (HT.isEmpty(user.username)&& HT.isEmpty(user.password)) {
			 HT.alert($i18n$.LOGIN.USERNAME_OR_PASSWORD);
			return;
		}
		baseService.post(__ctx+'/mobile/system/mobileLogin.ht',
				{username:user.username,password:user.password,lang:__lang},function(data){
				if(data.success){
					var curUserInfo = {},user = data.user;	
					// 用户ID
					curUserInfo.userId = user.userId;
					// 账号
					curUserInfo.account = user.account;
					//用户名
					curUserInfo.fullname = user.fullname;
					//组织ID
					curUserInfo.orgId = user.orgId;
					//组织名称
					curUserInfo.orgName = user.orgName;
					//权限
					curUserInfo.rights = user.rights;
					//设置当前登陆信息
                    try {
                        uexWidget.setPushInfo(curUserInfo.account, curUserInfo.fullname);
                    } 
                    catch (e) {
                    
                    }

					HT.setStorageJSON("__curUserInfo",curUserInfo);
					HT.goToHtml("index");
				}else{
					HT.alert(data.msg);
				}
			},{___waitmsg:$i18n$.LOGIN.LOGINING+"·····"});
	 };
	 
	 $scope.hasLogin = false;
	 $scope.json = "";

	 $scope.loadByOtherAppHandler = function(){
			if (!$scope.json){
		 		__hasLogin = false;
				if(HT.isAndroid()){//安卓的系统
					HT.toastInWindow("","","从移动门户获取授权",2000,0);
					uexLoadAPP.cbGetTokenSuccess = function(action,data,userId,Token){
						var result = "成功获得！ " + "action:" + action + " data:" + data + " userId:" + userId + " Token:" + Token;
						alert(result);
					};
					
					uexLoadAPP.getToken();
				}
			}
			else{
				var data =  $scope.json.data;
			 	var token = JSON.parse(data).token;
			 	if(HT.isEmpty(token)){
			 		__hasLogin = false;
			 		HT.toastInWindow("","","传入的参数不对",2000,0);
			 		 uexWindow.close(-1);
			 		uexLoadAPP.getToken();
			 		return;
			 	}
				var casToken = {
						token:token
					};
			 	$scope.toLogin(casToken);
			}
		 }
			 
		//健全登录信息
		 $scope.toLogin = function(casToken){
				baseService.post(__ctx+'/mobile/mdmapps/mdmMobileData/getUserInfo.ht',
						{cas_token:casToken},function(data){
							if(data.success){
								var curUserInfo = {},user = data.user;	
								// 用户ID
								curUserInfo.userId = user.userId;
								// 账号
								curUserInfo.account = user.account;
								//用户名
								curUserInfo.fullname = user.fullname;
								//组织ID
								curUserInfo.orgId = user.orgId;
								//组织名称
								curUserInfo.orgName = user.orgName;
								//权限
								curUserInfo.rights = user.rights;
								//设置当前登陆信息
								uexWidget.setPushInfo(curUserInfo.account,curUserInfo.fullname);
								HT.setStorageJSON("__curUserInfo",curUserInfo);
								
								HT.setStorageJSON("otherAppParams", $scope.json);
							 	HT.goToHtml("index");
							}else{
								HT.toastInWindow("","","认证失败，重新从移动门户获取授权",2000,0);
								uexLoadAPP.openLogin();
							}
					},{___waitmsg:"登录中·····"});
		 }
 
	$scope.plusClass="icon-plus5";
}])

/**
 * 国际化
 * @param {Object} $scope
 * @param {Object} $translate
 * @param {Object} baseService
 */
.controller('languageCtrl', ['$scope', '$translate','baseService', function($scope, $translate,baseService){
    $scope.languages = [{
        'value': 'zh_cn',
        'lable': '简体中文',
		'langkey':$i18n$_zh_cn
    }, {
        'value': 'zh_tw',
        'lable': '繁體中文',
		'langkey':$i18n$_zh_tw
    }, {
        'value': 'en_us',
        'lable': 'English',
		'langkey':$i18n$_en_us
    }];

    $scope.lang = {
        l: __lang
    };
    $scope.save = function(){
    	var lang = $scope.lang.l;
			baseService.post(__ctx+'/mobile/system/lang/changLang.ht',
					{lang:lang},function(data){
				angular.forEach($scope.languages,function(language){
					if(language.value ==lang){
						$i18n$ = language.langkey;
						HT.setStorage("__lang", lang);
				        $translate.use(lang);
						HT.toast($i18n$.LANGUAGE.CHANG_LANG);
						HT.clearPages("index");
						uexWindow.evaluateScript("setting",0,"HT.goback(-1)");
						window.setTimeout(function(){
							HT.goToHtml("index");
							HT.goback(-1);
						},1200)
						
					}
			});
		});
    }
}
])

.controller('selectorCtrl',['$scope',function($scope){
	// 定位人员选择器回填
}])
/**
 * 更多
 */
.controller('moreCtrl',['$scope','baseService',function($scope,baseService){
	$scope.fullname = __curUserInfo?__curUserInfo.fullname:$i18n$.MORE.NOT_LOGIN;
	//获取用户信息
	$scope.userInfo = function(){
		HT.goToHtml('userInfo');
	}
	$scope.newFlow = function(){
		HT.goToHtml('newFlow');
	}
	$scope.agentFlow = function(){
		HT.goToHtml('agentFlow');
	}
	$scope.mytime = function(){
		HT.goToHtml('mytime');
	}
	$scope.setting = function(){
		HT.goToHtml('setting');
	}
	$scope.logout = function(){
		baseService.post(__ctx+'/mobile/system/mobileLogout.ht',
				{},function(data){
					HT.toast($i18n$.MORE.LOGOUT_SUCCESS);
					uexWindow.evaluateScript("login",0,"HT.goback(-1);");
					window.setTimeout(function(){
						HT.goToHtml("login");
						uexWindow.evaluateScript("index",0,"HT.goback(-1);");
					},1200);
					
		});
	}
}])
/**
 * 用户信息
 */
.controller('userInfoCtrl',['$scope','baseService',function($scope,baseService){
	$scope.user ={};
	$scope.init = function(){
		baseService.post(__ctx+'/mobile/system/user/get.ht',
				{userId:__curUserId},function(data){
					$scope.user = data.user;
		});
	};
	$scope.init();
}])
.controller('subTableCtrl',['$scope','baseService',function($scope,baseService){
	$scope.item = {};
}])
.controller('orgCtrl', ['$scope', 'baseService', function($scope,baseService){
	$scope.keywords = "" ;
	
	var getOrg = function(o,isNavi,orgName){
		var param = {
			orgid: o.id,
			orgName:orgName
		};
		baseService.post(__ctx + '/mobile/contact/bpmMobileContact/orglist.ht', param, function(data){
			if(data.results&&data.results.length>0){
				$scope.orgs = data.results;
				HT.setSeletorNavi(o,isNavi,head,$scope);
			}
			if(orgScroll){
				setTimeout(function(){
					orgScroll.refresh();
					HT.checkSelected();
				}, 200);
			}
		})
	}
	
	// 初始化
	$scope.navis = [];
	var head = {id: "0",name: $i18n$.ORG.ALL};
	$scope.headNavi = head;
	$scope.prevNavi = head;
	$scope.isMulti = HT.getStorage("isMulti");
	getOrg(head);
	
	$scope.clickOrg = function(o){
		$scope._disOrgClick=true;
		$scope.getChooseOrg();
		getOrg(o,0);
		setTimeout(function(){
			$scope._disOrgClick=false;
		}, 800);
	}
	
	$scope.navi = function(o){
		$scope.getChooseOrg();
		getOrg(o,1);
	}
	
	$scope.addToOrg = function(){
		$scope.getChooseOrg();
		HT.goback('-1');
	}
	
	$scope.getChooseOrg = function(){
		var choseItem = HT.getChoseItem($scope.orgs);
		var multi = HT.getStorage("isMulti");
		if(multi!="1"){
			if(choseItem.length>0)
				HT.setStorageJSON(HT_LS.selectorSelected, choseItem);
		}else{
			var sels = HT.mergeUsers(HT.getStorageJSON(HT_LS.selectorSelected), choseItem, $scope.orgs);
			HT.setStorageJSON(HT_LS.selectorSelected, sels);
		}
	}
	
	$scope.showSelected = function(){
		$scope.getChooseOrg();
		HT.setStorage(HT_LS.selectorToSelected,"1");
		uexWindow.open("orgSelected",0,"orgSelected.html",0,"","",0);
	}
	
	//查询
	$scope.search = function(){
		var o = $scope.lastNavi;
		if(o==null){
			o = $scope.headNavi;
		}
		getOrg(o,0,$scope.keywords);
		
 	};
}])

// 已选组织
.controller('orgSelectedCtrl', ['$scope', function($scope){
	$scope.orgs = HT.getStorageJSON(HT_LS.selectorSelected);
	$scope.remove=function($index){
		$scope.orgs.splice($index,1);
		HT.setStorageJSON(HT_LS.selectorSelected,$scope.orgs);
	}
}])
.controller('testCtrl', ['$scope', function($scope){
	$scope.enddate="1990-10-11";
}])
.controller('aboutCtrl', ['$scope', function($scope){
	$scope.version=__version;
	$scope.help=function(){
		HT.goToHtml("help");
	}
	$scope.feedback=function(){
		HT.goToHtml("feedback");
	}
	$scope.checkupdate=function(){
		$scope.hasNewVersion="已是最新版本";
	}
}])
/**
 * 设置
 */
.controller('settingCtrl', ['$scope', function($scope){
	var setting =  HT.getStorageJSON("SETTING");
	if(HT.isEmpty(setting)){
		setting = {
			openService:1,
			synchTime:"3",
			harassMode:1,
			sound:1,
			shock:1,
			quit:1
		};
	}
	$scope.setting = setting;
	
	$scope.updSetting = function(){
		HT.setStorageJSON("SETTING",$scope.setting);
	}
	
	$scope.remove = function(type){

        uexWindow.cbConfirm = function(opId, dataType, data){
			if(data==0){
				switch (type) {
					case 1://清除表单
							HT.clearCacheTemplate(function(){
								HT.alert($i18n$.SETTING.CLEAR_FORMSUCCESS);
							});
							
						break;
					case 2://清除联系人
							HT.clearFrequent(function(){
								uexWindow.evaluatePopoverScript("index","contactCommon","HT.reload()");
								HT.alert($i18n$.SETTING.CLEAR_CONTACT_SUCCESS);
							});
						
						break;
					case 3://清除流程草稿
							HT.clearCacheDraft(function(){
								HT.alert($i18n$.SETTING.CLEAR_DRAFT_SUCCESS);
							});
						break;
					default:
						break;
				}
			}
        }
        uexWindow.confirm($i18n$.COMMON.WARN, $i18n$.SETTING.CONFIRM_CLEAR, [$i18n$.COMMON.OK, $i18n$.COMMON.CANCEL]);	
	};
	
	$scope.language = function(){
        HT.goToHtml("language");
	};
	$scope.about = function(){
		 HT.goToHtml("about");
	};
	$scope.version = function(){
		HT.alert(__version);
	};
	$scope.features = function(){
		   HT.goToHtml("features");
	};
	
	$scope.feedback = function(){
		alert($i18n$.FEEDBACK.FEEDBACK_SUCCESS);
	};

}])
;