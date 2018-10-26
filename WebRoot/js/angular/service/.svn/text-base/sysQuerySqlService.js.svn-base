var  sysQuerySqlServiceApp = angular.module('sysQuerySqlServiceApp',[]);
sysQuerySqlServiceApp.service('sysQuerySqlService', ['$http','$jsonToFormData', 'BaseService','dataRightsService', function($http, $jsonToFormData, BaseService,dataRightsService) {
	var service = {
			init: function(scope) {
				this.scope = scope;
				scope.dsList = dsList;
				scope.sysQuerySql = sysQuerySql||{};
				if(scope.sysQuerySql&&scope.sysQuerySql[this.scope.buttonKey])
					scope.sysQuerySql[this.scope.buttonKey] = parseToJson(scope.sysQuerySql[this.scope.buttonKey]);
				if(scope.sysQuerySql.categoryid)
					scope.sysQuerySql.categoryid = parseInt(scope.sysQuerySql.categoryid);
				scope.globalTypesDICList = globalTypesDICList;
				scope.globalTypeCATFORMList = globalTypeCATFORMList;
				scope.sysQueryFields = sysQueryFields;
				scope.oldSysQueryFields = $.extend(true,[],scope.sysQueryFields);
				this.util = dataRightsService;
				this.showResponse = this.util.showResponse;
				for(var i = 0 ,c;c=scope.sysQueryFields[i++];){
					if(!c.fieldName)
						c.fieldName = c.name;
					if(c.dataType=='date'&&!c.dateFormat)
						c.dateFormat = "yyyy-MM-dd";
				}
			},
			addUrl: function() {
				this.scope.sysQuerySql[this.scope.buttonKey] = this.scope.sysQuerySql[this.scope.buttonKey]||[];
				this.scope.sysQuerySql[this.scope.buttonKey].push({
					inRow:1
				})
			},
			delUrl: function(list,k) {
				if(k < 0 ) k = 0;
				for(var i = k||0 ; i < list.length ; i++){
					if(list[i].checked){
						list.splice(i,1);
						this.delUrl(list,i-1);
						break;
					}
				}
			},
			addOrEditUrlParamsDialog: function(field) {
				this.urlParamsDialog({
					urlParams: field[this.scope.buttonKey],
					sysQueryFields:this.scope.oldSysQueryFields,
					callback: function(rtn) {
						field[this.scope.buttonKey] = rtn;
					}
				});
			},
			getControlTypeJson : function (dataType){
	 			var json =  {1:"单行文本框",12:"自定义对话框",3:"数据字典",11:"下拉选项",4:"人员选择器(单选)",17:"角色选择器(单选)",18:"组织选择器(单选)",19:"岗位选择器(单选)",8:"人员选择器(多选)",5:"角色选择器(多选)",6:"组织选择器(多选)",7:"岗位选择器(多选)"};
	 			if(dataType == "date" || dataType == "timestamp")
	 				json[15] = "日期控件";
	 			return json;
	 		},
			getControlType:function(id,dataType){
				return this.getControlTypeJson(dataType)[id];
			},
			urlParamsDialog: function(conf) {
				var dialogWidth = 900;
				var dialogHeight = 500;
				conf = $.extend({}, {
					dialogWidth: dialogWidth,
					dialogHeight: dialogHeight,
					help: 0,
					status: 0,
					scroll: 1,
					center: 1
				}, conf);
				var url = __ctx + "/platform/system/sysQuerySql/urlParamsDialog.ht?id=" + this.scope.sysQuerySql.id;
				url = url.getNewUrl();
				var that = this;
				/* KILLDIALOG */
				DialogUtil.open({
					height: conf.dialogHeight,
					width: conf.dialogWidth,
					url: url,
					isResize: true,
					// 自定义参数
					conf: conf,
					sucCall: function(rtn) {
						if (rtn && conf.callback) {
							conf.callback.call(that, rtn);
						}
					}
				});
			},
			validSql : function(remind) {
				var obj =this.scope.sysQuerySql;
				var params = {
					sql : obj.sql,
					dsalias : obj.dsalias
				};
				var flag = true;
				$.ajax({
				      url: 'validSql.ht',
				      type: 'POST',
				      dataType: 'json',
				      data:params,
				      async:false,
				      success: function(data) {
			    	    if (data) {
			    		  	if(remind) {
								$.ligerDialog.success('<p><font color="green">验证通过!<br></font></p>');
			    		  	}
						} else {
							$.ligerDialog.error('<p><font color="red">验证不通过!<br></font></p>');
							flag = false;
						}
				      }
		      	});
				return flag;
			},
			ctrlTypeSetting : function(field,urlTag) {
				var url=  __ctx + "/platform/system/"+urlTag+"/editDetail.ht";
				var that = this;
				DialogUtil.open({
					height:400,
					width: 700,
					title : '控件设置',
					url: url, 
					conf : {
						type : "ctrlType",
						field:$.extend(true,{},field),
						dicList:this.scope.globalTypesDICList
					},
					isResize: true,
					sucCall:function(rtn){
						field.dateFormat = rtn.dateFormat||"";
						field.format = rtn.dateFormat||"";
						field.controlType = rtn.controlType;
						field.controlContent = rtn.controlContent;
						that.scope.$digest();
					}
				});
			},
			alarmSetting : function(field,urlTag) {
				var url=  __ctx + "/platform/system/"+urlTag+"/editDetail.ht";
				var that = this;
				DialogUtil.open({
					height:600,
					width: 800,
					title : '报警设置',
					url: url,
					conf : {
						type : "alarmSetting",
						field:$.extend(true,{},field)
					},
					isResize: true,
					sucCall:function(rtn){
						field.alarmSetting = rtn.alarmSetting;
						field.formater = rtn.formater;
						that.scope.$digest();
					}
				});
			},
			virtualSetting : function(field,urlTag,idx) {
				var url=  __ctx + "/platform/system/"+urlTag+"/editDetail.ht";
				var that = this;
				var vitualField = {};
				vitualField.virtualFrom = field.fieldName;
				vitualField.fieldDesc = "";
				vitualField.fieldName = field.fieldName;
				vitualField.sqlId = field.sqlId;
				vitualField.name = "";
				vitualField.isShow = 0;
				vitualField.isSearch = 0;
				vitualField.isVirtual = 1; 
				vitualField.dataType = "varchar"; 
				vitualField.controlType = 1; 
				if(field.controlType == 3) { //数据字典
					vitualField.resultFromType = 1;
					vitualField.resultFrom = field.controlContent;
				}else if(field.controlType == 11) {
					vitualField.resultFromType = 3;
					vitualField.resultFrom = field.controlContent;
				}else {
					vitualField.resultFromType = 2;
				}
				DialogUtil.open({
					height:400,
					width: 700,
					title : '虚拟列设置',
					url: url,
					conf : {
						type : "virtualSetting",
						field:vitualField,
						dicList:this.scope.globalTypesDICList
					},
					isResize: true,
					sucCall:function(rtn){
						$.insert(that.scope.sysQueryFields,rtn,idx+1);
						that.scope.$digest();
					}
				});
			},
			customFormSubmit : function(buttonKey) {
				var obj = this.scope.sysQuerySql;
				var json = {
					id : obj.id,
					name : obj.name,
					alias : obj.alias,
					sql : obj.sql,
					dsalias : obj.dsalias,
					dsname : obj.dsname,
					categoryid: obj.categoryid
				};
				json[buttonKey] = obj[buttonKey]?JSON.stringify(obj[buttonKey]):null;
				var $form = $('<form method="post" action="save.ht"></form>');
				var $input = $("<input type='hidden' name='jsonStr'/>");
				var $input2 = $("<input type='hidden' name='fieldJson'/>");
				$input.val(JSON2.stringify(json));
				$input2.val(this.getFieldListStr(this.scope.sysQueryFields));
				$form.append($input).append($input2);
				$form.ajaxForm({success:service.showResponse});
				$form.submit();
			},
			getFieldListStr : function(list){
				for(var i = 0 ; i < list.length ; i++){
					if(list[i].alarmSetting&&list[i].alarmSetting.length>0)
						for(var j = 0 ; j < list[i].alarmSetting.length ; j++){
							if(list[i].alarmSetting[j].hasOwnProperty("$$hashKey"))
								delete list[i].alarmSetting[j].$$hashKey;
						}
				}
				return this.util.listToString(list);
			},
			changeCheckBox : function(list,key,isCheck){
				for(var i = 0 ; i < list.length ; i++){
					list[i][key] = isCheck?0:1;
				}
			},
			hasVirtual : function(field){
				if(field.isVirtual==1 || field.isVirtual == undefined) return true;
				var list = this.scope.sysQueryFields;
				for(var i = 0 ; i < list.length ;i++){
					if(list[i].fieldName == field.fieldName && list[i].id != field.id)
						return true;
				}
				return false;
			}
		};
	 return service;
   }
])