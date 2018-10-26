var DataRightsApp = angular.module('DataRightsApp', [ 'baseServices' ]);
DataRightsApp.service('dataRightsService', ['$http','$jsonToFormData','BaseService', function($http,$jsonToFormData,BaseService) {
	var service = {
		init:function($scope){
			/**
			 * 控件类型。 16是隐藏域 4 用户单选,8,用户多选, 17,角色单选,5,角色多选, 18,组织单选,6,组织多选 19,岗位单选,7,岗位多选
			 */
			this.scope = $scope;
			$scope.controlList = [{key : '1',value : '单行文本框'}, {key : '15',value : '日期控件'}, {key : '3',value : '数据字典'}, {key : '11',value : '下拉选项'}, {key : '4',value : '人员选择器(单选)'}, {key : '17',value : '角色选择器(单选)'},  {key : '18',value : '组织选择器(单选)'},  {key : '19',value : '岗位选择器(单选)'}];
			$scope.bpmFormTable = [];
			$scope.displayFields = [];
			$scope.conditionFields =  [];
			$scope.sortFields =  [];
			$scope.filterFields =  [];
			$scope.manageFields =  [];
			$scope.exportFileds =  [];
			$scope.dataRightsJson = DataRightsJson;
			this.initDataRightsJson(DataRightsJson);
			switch($scope.type){
				case "sysQueryView":
					this.initSysQueryViewData(bpmFormTableJSON);
				break; 
				default :
					$scope.bpmFormTable = bpmFormTableJSON;
				break;
			}
			if($scope.dataRightsJson.displayField)
				$scope.displayFields = parseToJson($scope.dataRightsJson.displayField);
			if($scope.dataRightsJson.conditionField)
				$scope.conditionFields = parseToJson($scope.dataRightsJson.conditionField);
			else if($scope.dataRightsJson.conditions)
				$scope.conditionFields = parseToJson($scope.dataRightsJson.conditions);
			if($scope.dataRightsJson.sortField)
				$scope.sortFields = parseToJson($scope.dataRightsJson.sortField);
			if($scope.dataRightsJson.filterField)
				$scope.filterFields = parseToJson($scope.dataRightsJson.filterField);
			if($scope.dataRightsJson.manageField)
				$scope.manageFields = parseToJson($scope.dataRightsJson.manageField);
			else if($scope.dataRightsJson.buttons)
				$scope.manageFields = parseToJson($scope.dataRightsJson.buttons);
			if($scope.dataRightsJson.exportField)
				$scope.exportFileds = parseToJson($scope.dataRightsJson.exportField);
			$scope.ctlOptions = [ {k : "1",v : "单行文本框"},{k : "15",v : "日期控件"} ,{k : "3",v : "数据字典"} ,{k : "11",v : "下拉选项"} ,{k : "4",v : "人员选择器(单选)"},{k : "17",v : "角色选择器(单选)"},{k : "18",v : "组织选择器(单选)"},{k : "19",v : "岗位选择器(单选)"} ];
			$scope.managerButtons = [{k:"add",v:"新增"},{k:"edit",v:"编辑"},{k:"delall",v:"批量删除"},{k:"del",v:"删除"},{k:"detail",v:"明细"},{k:"export",v:"导出"},{k:"import",v:"导入"},{k:"start",v:"单行启动流程"},{k:"reStart",v:"单行重启流程"},{k:"starts",v:"批量启动流程"},{k:"reStarts",v:"批量重启流程"},{k:"diyoutside",v:"行外自定义"},{k:"diyinside",v:"行内自定义"}];
			$scope.managerButtonsinner = [{k:"edit",v:"编辑"},{k:"del",v:"删除"},{k:"detail",v:"明细"},{k:"start",v:"单行启动流程"},{k:"reStart",v:"单行重启流程"},{k:"diyinside",v:"行内自定义"}];
			$scope.managerButtonsoutside = [{k:"add",v:"增加"},{k:"delall",v:"批量删除"},{k:"export",v:"导出"},{k:"import",v:"导入"},{k:"print",v:"打印"},{k:"starts",v:"批量启动流程"},{k:"reStarts",v:"批量重启流程"},{k:"diyoutside",v:"行外自定义"}];
			$scope.getOptions = function(ty){
				var ops = [];
				switch (ty) {
					case 'varchar' :
						ops = [{k : "1",v : "="},{k : "2",v : "!="},{k : "3",v : "like"}];
						break;
					case 'number' :
					case 'int' :
						ops = [{k : "1",v : "="},{k : "2",v : ">"},{k : "3",v : "<"},{k : "4",v : ">="},{k : "5",v : "<="}];
						break;
					case 'date' :
						ops = [{k : "1",v : "="},{k : "2",v : ">"},{k : "3",v : "<"},{k : "4",v : ">="},{k : "5",v : "<="},{k : "6",v : "日期范围"}];
						break;
					default :
						ops = [{k : "1",v : "="},{k : "2",v : ">"},{k : "3",v : "<"},{k : "4",v : ">="},{k : "5",v : "<="}];
						break;
				}
				return ops;
			}
		},
		getOperateOptions : function(type){
			var ops = [];
			switch (type) {
				case 'varchar' :
					ops = [{k : "1",v : "等于"},{k : "2",v : "不等于"},{k : "4",v : "like"},{k : "5",v : "like_l"},{k : "6",v : "like_r"},{k:"7",v:"in"}];
					break;
				case 'number' :
				case 'int' :
					ops = [{k : "1",v : "等于"},{k : "2",v : "不等于"},{k : "3",v : "大于"},{k : "4",v : "大于等于"},{k : "5",v : "小于"},{k : "6",v : "小于等于"},{k:"7",v:"in"},{k : "8",v : "between"},{k:"9",v:"not in"}];
					break;
				case 'date' :
					ops = [{k : "1",v : "等于"},{k : "2",v : "不等于"},{k : "3",v : "大于"},{k : "4",v : "大于等于"},{k : "5",v : "小于"},{k : "6",v : "小于等于"},{k : "8",v : "between"}];
					break;
				default :
					ops = [{k : "1",v : "等于"},{k : "2",v : "不等于"},{k : "3",v : "大于"},{k : "4",v : "大于等于"},{k : "5",v : "小于"},{k : "6",v : "小于等于"}];
					break;
			}
			return ops;
		},
		initSysQueryViewData:function(bpmFormTableJSON){
			var scope = this.scope,
				fieldList = [],
				displayList = [];
			for(var i = 0 ; i < bpmFormTableJSON.length ; i++){
				if(bpmFormTableJSON[i].isSearch)
					fieldList.push(bpmFormTableJSON[i]);
				if(bpmFormTableJSON[i].isShow)
					displayList.push({
						name:bpmFormTableJSON[i].name,
						fieldName:bpmFormTableJSON[i].fieldName,
						desc:bpmFormTableJSON[i].fieldDesc,
						fieldId :bpmFormTableJSON[i].id
					});
			}
			scope.bpmFormTable = {
					fieldList : fieldList
			}
			var tempDiaplayFields = "";
			if(displayFields&&displayFields.length>0){
				for(var i = 0 ; i < displayFields.length ; i++){
					var df = displayFields[i];
					if(!df) continue;
					var hasShowRights = false;
					for(var j = 0 ; j < displayList.length ; j++) {
						if(df.fieldId == displayList[j].fieldId) {
							df.name = displayList[j].name;
							df.fieldName = displayList[j].fieldName;
							df.desc = displayList[j].desc;
							hasShowRights = true;
							break;
						}
					}
					if(hasShowRights){
						tempDiaplayFields = tempDiaplayFields ||[];
						tempDiaplayFields.push(df);
					}
				}
			}
			scope.filterFields = {
					type:DataRightsJson.filterType||2,
					sql:DataRightsJson.filterType==2?DataRightsJson.filter:"",
					conditions:DataRightsJson.filterType==1?parseToJson(DataRightsJson.filter):""
			}
			if(DataRightsJson.supportGroup == 1){
				var groupingView = parseToJson(DataRightsJson.groupSetting),
					groupSummary = parseToJson(groupingView.groupSummary),
					groupField = this.parseToArray(groupingView.groupField),
					groupColumnShow = parseToJson(groupingView.groupColumnShow),
					groupText = this.parseToArray(groupingView.groupText),
					groupOrder = this.parseToArray(groupingView.groupOrder);
				scope.inLineSummary = groupSummary[0]?1:0;
				scope.allSummary = groupSummary[1]?1:0;
				scope.groupingView = [];
				for(var i = 0 ; i < groupField.length ; i++){
					var isInDisplayFields = false;
					for(var j = 0 ; j < displayFields.length ; j++){
						if(displayFields[j].fieldName == groupField[i]){
							displayFields[j].gchecked = true;
							isInDisplayFields = true;
							break;
						}
					}
					if(isInDisplayFields)
						scope.groupingView.push({
							groupField:groupField[i],
							groupColumnShow:groupColumnShow[i]?1:0,
							groupText:groupText[i],
							groupOrder:groupOrder[i]
						})
				}
			}else{
				scope.inLineSummary = 0;
				scope.allSummary = 0;
			}
			scope.displayFields = tempDiaplayFields||displayList;
			scope.sysQueryMetaFields = bpmFormTableJSON;
		},
		parseToArray : function(str){
			if(str)
				return str.substring(1,str.length-1).split(",");
			return [];
		},
		isCheckboxNull : function(data){
			return data!= 0 && data!= 1;
		},
		initDataRightsJson : function(dataRightsJson){
			if(!dataRightsJson.pageSize)
				dataRightsJson.pageSize = 20;
			if(this.isCheckboxNull(dataRightsJson.isQuery))
				dataRightsJson.isQuery = 0;
			if(this.isCheckboxNull(dataRightsJson.initQuery))
				dataRightsJson.initQuery = 0;
			if(this.isCheckboxNull(dataRightsJson.showRowsNum))
				dataRightsJson.showRowsNum = 0;
		},
		showResponse : function(responseText){
			$.ligerDialog.closeWaitting();
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {
				$.ligerDialog.confirm( obj.getMessage()+",是否继续操作","提示信息", function(rtn) {
					if(rtn){
						window.location.href =  location.href.getNewUrl();
					}else{
						var urlshe =__ctx +"/platform/bpm/bpmNodeButton/getByNode.ht?&defId="+defIdsher+"&nodeId="+nodeIdsher+"&buttonFlag="+buttonFlagsher;
						window.location.href = urlshe;
					}
				});
			} else {
				$.ligerDialog.error(obj.getMessage(),"提示信息");
			}
		},
		manageFieldValid : function(list){
			if(!list) return false;
			var name =new Array();
			for(var i = 0 ; i < list.length ; i++){
				name.push(list[i].name);
			}
			return this.isRepeat(name);
		},
		isRepeat : function (arr) {
		    var hash = {};
		    for(var i in arr) {
		        if(hash[arr[i]]) {
		            return true;
		        }
		        hash[arr[i]] = true;
		    }
		    return false;
		},
		selectCondition : function(type) {
			var _self = this.scope,
				list = _self.bpmFormTable.fieldList,
				service = _self.service;
			if(!_self.conditionFields) _self.conditionFields=[];
			switch(type){
				case 'sysQueryView':
					for(var f in list){
						if(list[f].checked&&!this.hasInList(list[f].fieldName||list[f].name,_self.conditionFields,'name')){
							var condition = {
									name : list[f].fieldName|| list[f].name,
									type : list[f].fieldType|| list[f].type||list[f].dataType,
									operate : "1",
									valueFrom : "1",
									comment : ""
							};
							if(condition.type == "date"){
								condition.format = list[f].dateFormat||"yyyy-MM-dd"
							}
							_self.conditionFields.push(condition);
						}
					}
					break;
					default:
						for(var f in list){
							if(list[f].checked&&!this.hasInList(list[f].fieldName||list[f].name,_self.conditionFields,'na')){
								var condition = {
									na : list[f].fieldName|| list[f].name,
									ty : list[f].fieldType|| list[f].type,
									op : 1,
									cm : list[f].fieldDesc,
									va : "",
									vf : 1,
									ct : list[f].controlType,
									qt : this.getQueryType(list[f].fieldType|| list[f].type,1)
								};
								_self.conditionFields.push(condition);
							}
						}
					break;
			}
			
		},
		hasInList:function(na,list,tag){
			for(var i in list){
				if(list[i][tag]==na)
					return true;
			}
			return false;
		},
		/**
		 * 选择排序的字段
		 */
		selectSort : function(listKey,nameKey,descKey) {
			var _self = this.scope,
				list = _self.bpmFormTable.fieldList,
				service = _self.service;
			if(listKey)
				list = _self.bpmFormTable[listKey];
			nameKey = nameKey||"fieldName";
			descKey = descKey||"fieldDesc";
			if(!_self.sortFields) _self.sortFields = [];
			for(var f in list){
				if(list[f].checked&&!this.hasInList(list[f][nameKey],_self.sortFields,'name')){
					var condition = {
							name : list[f][nameKey],
							desc : list[f][descKey]
						};
					_self.sortFields.push(condition);
				}
			}
		},
		writein : function(btnId,pageindex) {
			var obj = [{k:"add",v:"新增"},{k:"edit",v:"编辑"},{k:"delall",v:"批量删除"},{k:"del",v:"删除"},{k:"detail",v:"明细"},{k:"export",v:"导出"},{k:"import",v:"导入"},{k:"start",v:"单行启动流程"},{k:"reStart",v:"单行重启流程"},{k:"starts",v:"批量启动流程"},{k:"reStarts",v:"批量重启流程"},{k:"diyoutside",v:"行外自定义"},{k:"diyinside",v:"行内自定义"}];
			list = this.scope.manageFields;
			var bangIdlist = new Array();
			for(var i = 0;i<manageField.length;i++){
				bangIdlist[i] = manageField[i].btnId.toString();
			}
			var index = $.inArray(btnId.toString(),bangIdlist);
			var name = "";
			var that =this;
			DialogUtil.open({
 		 		title:"人机交互参数绑定", 
 		 		url: "/mas/platform/form/bpmDataTemplate/sherdatabangnew.ht?btnId="+btnId+"&defIdsher="+defIdsher+"&bpmFormTableIdsher="+bpmFormTableIdsher+"&actdefIdsher="+actdefIdsher+"&scansher="+list[index].scan+"&indexsher="+index, 
 		 		height:400,
 		 		width:300,
 				isResize: false,
 				pwin:window,
 		 		sucCall:function(snode,urlsher){
				$("#snode"+pageindex).val(snode);
				fdesc = $("#fdesc").val();
				fname = $("#fname option:selected").text();
				angular.forEach(obj,function(item,index){
						var butlist=angular.fromJson(item);
						if(fname == butlist.v){
							name = butlist.k;
						}
				});
					list[index].url = urlsher.toString().replaceAll("&","%26");
					list[index].snode = snode.toString();
					var urlshe =__ctx +"/platform/form/bpmDataTemplate/yucansave.ht?templateIdsher="+templateIdsher+"&list="+JSON2.stringify(list)+"&btnId="+btnId+"&snode="+snode;
					$.get(urlshe,{},function(){
					});
				}
			});
		},
		checkurl : function(btnId){
			list = this.scope.manageFields;
			var bangIdlist = new Array();
			for(var i = 0;i<manageField.length;i++){
				bangIdlist[i] = manageField[i].btnId.toString();
			}
			var index = $.inArray(btnId.toString(),bangIdlist);
			alert("[按钮ID]："+list[index].btnId+"\n"+"[按钮名称]:"+list[index].desc+"\n"+"[按钮属性]:"+list[index].btntype+"\n"+"[按钮类型]:"+list[index].name+"\n"+"[所绑节点]:"+list[index].snode+"\n"+"[所绑参数]:"+list[index].scan);
		},
		delManage:function(id){
			this.scope.manageFields = this.scope.manageFields;
			var list = this.scope.manageFields;
			for(var i=0;i<=list.length;i++){
				if(list[i].id==id&&i==list.length){
					list.length()= list.length-1;  
				}
				else
				{
					 list[i]=list[i+1];
			           list.length= list.length-1;
				}
			}
			customFormSubmit();
		},
		addManage : function() {
			this.scope.manageFields = this.scope.manageFields||[];
			var list = this.scope.manageFields;
			var time = new Date();
			var btnId = time.getTime().toString();
			var mf = {desc:"请选择",name:"请选择",url:"",snode:"",scan:"",btnId:btnId};
			if(!this.noRights)
				mf.right = [{type:"everyone"}];
			list.push(mf);
		},
		/**
		 * 增加过滤条件
		 */
		addFilter : function() {
			var _self = this,bt = this.scope.dataRightsJson;
			var right = {type : 'none',id : '',name : '',script : ''};
			this.filterDialog({
				key : bt.tableId||bt.tableName,
				source : bt.source,
				callback : function(rtn) {
					if (rtn) {
						var filter = {},type= rtn.type,condition = (type==2?rtn.condition:JSON2.stringify(rtn.condition));
						filter.name = rtn.name;
						filter.key = rtn.key;
						filter.type = type;
						filter.condition = condition;
						filter.right = [right];
						if(!_self.scope.filterFields) _self.scope.filterFields = [];
						_self.scope.filterFields.push(filter);
						_self.scope.$digest();
					}
				}
			});
		},
		/**
		 * 过滤的窗口
		 */
		filterDialog : function(conf) {
			var dialogWidth = 750;
			var dialogHeight = 500;
				conf = $.extend({}, {dialogWidth : dialogWidth,dialogHeight : dialogHeight,help : 0,status : 0,scroll : 0,center : 1}, conf);
			var winArgs = "dialogWidth=" + conf.dialogWidth + "px;dialogHeight="+ conf.dialogHeight + "px;help=" + conf.help + ";status="+ conf.status + ";scroll=" + conf.scroll + ";center="+ conf.center;
			var url = this.scope.filterUrl + conf.key;
				url = url.getNewUrl();
			var that =this;
			DialogUtil.open({
				height:conf.dialogHeight,
				width: conf.dialogWidth,
				title : '过滤的窗口',
				url: url, 
				isResize: true,
				//自定义参数
				conf: conf,
				sucCall:function(rtn){
					if (rtn && conf.callback) {
						conf.callback.call(that, rtn);
					}
				}
			});
		},
		/**
		 * 编辑过滤条件窗口
		 */
		editFilterDialog : function(list,idx) {
			var _self = this,bt = this.scope.dataRightsJson;
			this.filterDialog({
				key : bt.tableId||bt.tableName,
				source : bt.source,
				filter : list[idx],
				callback : function(rtn) {
					if (rtn) {
						var condition = (rtn.type==2?rtn.condition:JSON2.stringify(rtn.condition));
						list[idx].name = rtn.name;
						list[idx].key = rtn.key;
						list[idx].type = rtn.type;
						list[idx].condition = rtn.condition;
						this.scope.$digest();
					}
				}
			});
		},
		delTr : function(list,idx) {
			list.splice(idx,1);
		},
		moveTr : function(list,idx,isUp) {
			idx = parseInt(idx);
			if((isUp&&idx==0)||(!isUp&&idx==list.length-1)) return;
			if (isUp) {
	    		var t=list[idx-1];
	    		list[idx-1]=list[idx];
	    		list[idx]=t;
			} else {
				var t=list[idx+1];
	    		list[idx+1]=list[idx];
	    		list[idx]=t;
			}
		},
		getQueryType : function(type, op) {
			var qt = "S";
			switch (type) {
				case 'varchar' :
					if (op) {
						switch (op) {
							case 1 :
							case 2 :
								qt = 'S';
								break
							case 3 :
								qt = 'SL';
								break
							case 4 :
								qt = 'SLL';
								break
							case 5 :
								qt = 'SLR';
								break
							default :
								qt = 'S';
								break
						}
					}
					break;
				case 'number' :
					qt = 'L';
					break;
				case 'int' :
					qt = 'N';
					break;
				case 'date' :
					if (op == 6)
						qt = 'DR';
					else 
						qt = 'DL';
					break;
				default :
					qt = 'S';
					break;
			}
			return qt;
		},
		setSysQueryViewJson:function(json){
			if(this.scope.type == 'sysQueryView'){
				var obj = this.scope.dataRightsJson;
				json.conditions = json.conditionField;
				json.buttons = json.manageField;
				json.initQuery = obj.initQuery;
				json.showRowsNum = obj.showRowsNum;
				json.filterType = this.scope.filterFields.type;
				//过滤条件
				if(json.filterType == 2){
					editor.save();
					json.filter = $('#sql').val();
				}else
					json.filter = JSON.stringify($("#ruleDiv").linkdiv("getData"));
				
				//分组数据
				json.supportGroup = obj.supportGroup;
				if(obj.supportGroup == 1){
					var scope = this.scope,
						list = scope.groupingView;
					var groupSummary = [scope.inLineSummary == 1,scope.allSummary == 1];
					var groupField = [];
					var groupColumnShow = [];
					var groupText = [];
					var groupOrder = [];
					for(var i = 0 ; i < list.length ; i++) {
						groupField.push(list[i].groupField);
						groupColumnShow.push(list[i].groupColumnShow==1);
						groupText.push(list[i].groupText);
						groupOrder.push(list[i].groupOrder);
					}
					json.groupSetting = {
							groupField:'['+groupField.join(",")+']',
							groupColumnShow:'['+groupColumnShow.join(",")+']',
							groupText:'['+groupText.join(",")+']',
							groupOrder:'['+groupOrder.join(",")+']',
							groupSummary:'['+groupSummary.join(",")+']'
					}
					json.groupSetting = JSON.stringify(json.groupSetting);
				}
				json.filterField = this.scope.dataRightsJson.showRowsNum;
			}
		},
		customFormSubmit : function (){
			var dr = this.scope.dataRightsJson;
			var json={
				id:dr.id,
				tableId:dr.tableId,
				sqlId:dr.sqlId,
				name:dr.name,
				formKey:dr.formKey,
				source:dr.source,
				defId:dr.defId,
				alias:dr.alias,
				templateAlias:dr.templateAlias,
				sqlAlias:dr.sqlAlias,
				isQuery:dr.isQuery,
				isFilter:dr.isFilter,
				needPage:dr.needPage,
				pageSize:dr.pageSize,
				displayField:this.listToString(this.scope.displayFields),
				conditionField:this.listToString(this.scope.conditionFields),
				sortField:this.listToString(this.scope.sortFields),
				filterField:this.listToString(this.scope.filterFields),
				manageField:this.listToString(this.scope.manageFields),
				exportField:this.listToString(this.scope.exportFileds),
				templateAlias:dr.templateAlias
			};
			this.setSysQueryViewJson(json);
			var form = $('<form method="post" action="savesher.ht?defIdsher='+defIdsher+'&nodeIdsher='+nodeIdsher+'&buttonFlagsher='+buttonFlagsher+'"></form>');
			var input = $("<input type='hidden' name='json'/>");
			var jsonStr=JSON2.stringify(json);
			input.val(jsonStr);
			form.append(input);
			form.ajaxForm({success:success});
			form.submit();
		},
		listToString: function(list){
			for(var i = 0 ; i < list.length ; i++){
				if(list[i].hasOwnProperty("$$hashKey"))
					delete list[i].$$hashKey;
			}
			return JSON.stringify(list);
		}
	};
	return service;
}])
.directive('rightSelect', function($compile) {
	return {
		restrict : 'E',
		replace : true,
		scope:{
			list:"="
		},
		template : '<select  ng-model="r" ng-change="setPermision(list)">'+
						 '<option value="">请选择</option>'+
						 '<option value="none">无</option>'+
						 '<option value="everyone">所有人</option>'+
				   '</select>',
		link : function(scope, elm, attrs) {
			scope.setPermision = function(list) {
					if(scope.r=="") return;
					for(var i = 0 ; i < list.length ; i++){
						if(list[i].fields&&list[i].fields.length>0){
							scope.setPermision(list[i].fields);
						}else{
							if(list[i].right[0].hasOwnProperty("v"))
								list[i].right[0] = {"v":scope.r};
							else
								list[i].right[0] = {"type":scope.r};
						}
						
					}
			}
		}
	};
})
.directive('toolButtons', ['dataRightsService',function(dataRightsService) {
	return {
		restrict : 'E',
		replace : true,
		scope:{type:"@",list:"=",index:"@"},
		template :  '<div>'+
						'&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<a class="link moveup" href="javascript:;" ng-click="service.moveTr(list,index,true)"></a>'+
						'&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<a class="link movedown" href="javascript:;" ng-click="service.moveTr(list,index,false)"></a>'+
						'<a class="link edit" ng-if="type==3" href="javascript:;" title="编辑" ng-click="service.editFilterDialog(list,index)"></a>'+
						'&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<a class="link del" ng-if="type==4||type==3"  href="javascript:;" ng-click="service.delTr(list,index)"></a>'+
					'</div>',
		link : function(scope, elm, attrs) {
			scope.service = dataRightsService;
		}
	};
}])
//显示字段，显示列字段
.directive('displaySetting', function() {
	return {
		restrict : 'E',
		replace : true,
		template :  '<div>'+
						'<table id="displayFieldTbl"  class="table-grid">'+
							'<thead>'+
								'<tr>'+
									'<th width="5%">序号</th>'+
									'<th width="15%">列名</th>'+
									'<th width="20%">注释</th>'+
									'<th width="20%" ng-if="!service.noRights">'+
										'显示权限'+
										'<right-select list="displayFields"></right-select>'+
									'</th>'+
									'<th width="20%">排序</th>'+
								'</tr>'+
							'</thead>'+
							'<tbody>'+
								'<tr var="displayFieldTr" ng-repeat="f in displayFields track by $index">'+
									'<td var="index">{{$index}}</td>'+
									'<td var="name">{{f.name}}</td>'+
									'<td >'+
										'<input type="text"  var="desc"  ng-model="f.desc" />'+
									'</td>'+
									'<td ng-if="!service.noRights">'+
										'<div right="f.right[0]" choose-target></div>'+
									'</td>'+
									'<td>'+
										'<tool-buttons list="displayFields" index="{{$index}}" type="4"></tool-buttons>'+
									'</td>'+
								'</tr>'+
							'</tbody>'+
						'</table>'+
					'</div>'
	};
})
//显示字段,显示列字段 <field-setting ></field-setting> for  SYS_QUERY_FIELDSETTING
.directive('fieldSetting', function() {
	return {
		restrict : 'E',
		replace : true,
		template :  '<div>'+
						'<div class="table-top-left" >'+
							'<div class="toolBar" style="margin:0;">'+
								'<div class="group">'+
									'<a class="link reset" ng-click="resetDiaplayFields()">重置列表</a>'+
								'</div>'+
							'</div>'+
						'</div>'+
						'<table class="table-grid">'+
							'<thead>'+
								'<tr>'+
									'<th width="5%">序号</th>'+
									'<th width="5%">列名</th>'+
									'<th width="5%">注释</th>'+
									'<th width="5%">支持排序</th>'+
									'<th width="5%">默认排序</th>'+
									'<th width="5%">是否冻结</th>'+
									'<th width="5%">是否隐藏</th>'+
									'<th width="5%">排序方向</th>'+
									'<th width="5%">对齐方式</th>'+
									'<th width="5%">统计类型</th>'+
									'<th width="20%">统计模板'+
										'<div class="tipbox">'+
											'<a href="javascript:;" class="tipinfo"><span>这里显示分组统计使用的模版，{0}中的内容为统计结果，示例:小计:&lt;span style="color:red;">{0}&lt;/span>,如果使用需要最此字段定义分组。</span></a>'+
										'</div>'+
									'</th>'+
									'<th width="20%" ng-if="!service.noRights">'+
										'显示权限'+
										'<right-select list="displayFields"></right-select>'+
									'</th>'+
									'<th width="10%">排序</th>'+
								'</tr>'+
							'</thead>'+
							'<tbody class="fieldSetting">'+
								'<tr ng-repeat="f in displayFields track by $index">'+
									'<td var="index">{{$index+1}}</td>'+
									'<td var="name">{{f.fieldName}}</td>'+
									'<td var="name">{{f.desc}}</td>'+
									'<td >'+
										'<input ng-model="f.sortAble" ng-if="f.sortAble==0"  type="checkbox" ng-true-value=1 ng-false-value=0 />'+
										'<input ng-model="f.sortAble" ng-if="f.sortAble==1"  type="checkbox" ng-true-value=1 ng-false-value=0 ng-checked="true"/>'+
									'</td>'+
									'<td >'+
										'<input ng-model="f.defaultSort" ng-if="f.defaultSort==0"  type="checkbox" ng-true-value=1 ng-false-value=0 ng-change="changeDefaultSort($index)"/>'+
										'<input ng-model="f.defaultSort" ng-if="f.defaultSort==1"  type="checkbox" ng-true-value=1 ng-false-value=0 ng-checked="true" ng-change="changeDefaultSort($index)"/>'+
									'</td>'+
									'<td >'+
										'<input ng-model="f.frozen" ng-if="f.frozen==0"  type="checkbox" ng-true-value=1 ng-false-value=0 />'+
										'<input ng-model="f.frozen" ng-if="f.frozen==1"  type="checkbox" ng-true-value=1 ng-false-value=0 ng-checked="true"/>'+
									'</td>'+
									'<td >'+
										'<input ng-model="f.hidden" ng-if="f.hidden==0"  type="checkbox" ng-true-value=1 ng-false-value=0 />'+
										'<input ng-model="f.hidden" ng-if="f.hidden==1"  type="checkbox" ng-true-value=1 ng-false-value=0 ng-checked="true"/>'+
									'</td>'+
									'<td >'+
										'<select ng-model="f.sortSeq" class="ht-input" style="width:60px;">'+
											'<option value="asc">asc</option>'+
											'<option value="desc">desc</option>'+
										'</select>'+
									'</td>'+
									'<td >'+
										'<select ng-model="f.align" class="ht-input" style="width:60px;">'+
											'<option value="left">居左</option>'+
											'<option value="right">居右</option>'+
											'<option value="center">居中</option>'+
										'</select>'+
									'</td>'+
									'<td >'+
										'<select ng-model="f.summaryType" class="ht-input" style="width:60px;">'+
											'<option value="">请选择</option>'+
											'<option value="count">计数</option>'+
											'<option value="sum">求和</option>'+
											'<option value="max">最大</option>'+
											'<option value="min">最小</option>'+
										'</select>'+
									'</td>'+
									'<td >'+
										'<textarea rows="3" ng-model="f.summaryTemplate" style="padding: 15px 0 0 0px;resize: none" class="w100  border-none margin-none "></textarea>'+
									'</td>'+
									'<td ng-if="!service.noRights">'+
										'<div right="f.right[0]" choose-target></div>'+
									'</td>'+
									'<td>'+
										'<tool-buttons list="displayFields" index="{{$index}}" type="4"></tool-buttons>'+
									'</td>'+
								'</tr>'+
							'</tbody>'+
						'</table>'+
					'</div>',
					link:function(scope,element,attrs){
						scope.changeDefaultSort = function(idx){
							for ( var i = 0; i < scope.displayFields.length; i++) {
								if(i!=idx)
									scope.displayFields[i].defaultSort = 0;
							}
						}
						for(var i = 0 ;i<scope.displayFields.length ; i++){
							var df = scope.displayFields[i];
							if(scope.service.isCheckboxNull(df.sortAble))
								df.sortAble = 0;
							if(scope.service.isCheckboxNull(df.defaultSort))
								df.defaultSort = 0;
							if(!df.sortSeq)
								df.sortSeq = 'asc';
							if(!df.align)
								df.align = 'left';
							if(scope.service.isCheckboxNull(df.frozen))
								df.frozen = 0;
							if(scope.service.isCheckboxNull(df.hidden))
								df.hidden = 0;
						}
						scope.oldDisplayFields = $.extend(true,[],scope.displayFields);
						scope.resetDiaplayFields = function(){
							scope.displayFields = $.extend(true,[],scope.oldDisplayFields);
						}
					}
						
	};
})
//功能按钮<manage-setting ></manage-setting>
.directive('manageSetting', function() {
	return {
		restrict : 'E',
		replace : true,
		template :  '<div>'+
						'<div class="table-top-left" ng-if="!service.noRights">'+
							'<div class="toolBar" style="margin:0;">'+
								'<div class="group">'+
									'<a class="link add" id="btnSearch" ng-click="service.addManage()">添加</a>'+
								'</div>'+
							'</div>'+
						'</div>'+
						'<table id="manageTbl"  class="table-grid">'+
							'<thead>'+
								'<tr>'+
									'<th width="5%">选择</th>'+
									'<th width="15%">名称</th>'+
									'<th width="10%">类型</th>'+
									'<th ng-if="!service.noRights">'+
										'权限'+
										'<right-select list="manageFields"></right-select>'+
									'</th>'+
									'<th width="10%">管理</th>'+
								'</tr>'+
							'</thead>'+
							'<tbody>'+
								'<tr var="manageTr" ng-repeat="f in manageFields">'+
									'<td var="index">'+
										'<input class="pk" type="checkbox" name="select"></td>'+
									'<td >'+
										'<input type="text" ng-model="f.desc" class="ht-input"></td>'+
									'<td>'+
										'<select ng-model="f.name" ng-options="m.k as m.v for m in managerButtons" class="ht-input">'+
											'<option value="">请选择</option>'+
										'</select>'+
									'</td>'+
									'<td ng-if="!service.noRights">'+
										'<div right="f.right[0]" choose-target></div>'+
									'</td>'+
									'<td>'+
										'<tool-buttons list="manageFields" index="{{$index}}" type="4"></tool-buttons>'+
									'</td>'+
								'</tr>'+
							'</tbody>'+
						'</table>'+
					'</div>'
	};
})
//功能按钮<manage-setting-view ></manage-setting-view>
.directive('manageSettingView', function() {
	return{
		restrict : 'E',
		replace : true,
		template :  '<div>'+
						'<div class="table-top-left" >'+
							'<div class="toolBar" style="margin:0;">'+
								'<div class="group">'+
									'<a class="link reset" id="btnSearch" ng-click="resetFields()">重置按钮</a>'+
								'</div>'+
							'</div>'+
						'</div>'+
						'<table id="manageTbl"  class="table-grid">'+
							'<thead>'+
								'<tr>'+
									'<th width="15%">名称</th>'+
								'<th width="15%">显示名称</th>'+
									'<th width="10%">类型</th>'+
									'<th width="10%">URL路径</th>'+
									'<th width="10%">管理</th>'+
								'</tr>'+
							'</thead>'+
							'<tbody>'+
								'<tr var="manageTr" ng-repeat="f in manageFields">'+
								'<td >'+
										'{{f.name}}'+
									'</td>'+
									'<td >'+
										'{{f.desc}}'+
									'</td>'+
									'<td >'+
										'{{f.inRow==1?"行内":"页头"}}'+
									'</td>'+
									'<td >'+
										'{{f.urlPath}}'+
									'</td>'+
									'<td>'+
										'<tool-buttons list="manageFields" index="{{$index}}" type="4"></tool-buttons>'+
									'</td>'+
								'</tr>'+
							'</tbody>'+
						'</table>'+
					'</div>',
					link:function(scope,el,attrs){
						scope.oldManageFields = $.extend(true,[],scope.manageFields);
						scope.resetFields = function(){
							scope.manageFields = $.extend(true,[],scope.oldManageFields);
						}
					}
	};
})
//导出字段
.directive('exportSetting', function() {
	return {
		restrict : 'E',
		replace : true,
		template :  '<div>'+
						'<table id="exportFieldTbl"  class="table-grid">'+
							'<thead>'+
								'<tr>'+
									'<th width="5%">序号</th>'+
									'<th width="15%">列名</th>'+
									'<th width="20%">注释</th>'+
									'<th width="20%" ng-if="!service.noRights">'+
										'导出权限'+
										'<right-select list="exportFileds"></right-select>'+
									'</th>'+
								'</tr>'+
							'</thead>'+
							'<tbody ng-repeat="table in exportFileds" ng-if="table.fields">'+
								'<tr var="exportTableTr">'+
									'<td var="table" colspan="6">{{table.tableDesc}}({{table.tableName}})</td>'+
								'</tr>'+
								'<tr var="exportFieldTr" ng-repeat="f in table.fields">'+
									'<td var="index">{{$index}}</td>'+
									'<td var="name">{{f.name}}</td>'+
									'<td>'+
										'<input type="text" ng-model="f.desc" ></td>'+
									'<td ng-if="!service.noRights">'+
										'<div right="f.right[0]" choose-target></div>'+
									'</td>'+
								'</tr>'+
							'</tbody>'+
							'<tr var="exportFieldTr" ng-repeat="f in exportFileds" ng-if="!f.fields">'+
									'<td var="index">{{$index}}</td>'+
									'<td var="name">{{f.name}}</td>'+
									'<td>'+
										'<input type="text" ng-model="f.desc" ></td>'+
									'<td  ng-if="!service.noRights">'+
										'<div right="f.right[0]" choose-target></div>'+
									'</td>'+
							'</tr>'+
						'</table>'+
					'</div>'
	};
})
//查询条件字段 <condition-setting ></condition-setting>
.directive('conditionSetting', function() {
	return {
		restrict : 'E',
		replace : true,
		template :  '<div>'+
						'<div  class="condition-cols">'+
							'<div >'+
								'<div class="condition-cols-div">'+
									'<table id="condition-columnsTbl" cellpadding="0" cellspacing="0" border="0" class="table-detail">'+
										'<thead>'+
											'<tr class="leftHeader">'+
												'<th>列名</th>'+
												'<th>注释</th>'+
												'<th>类型</th>'+
											'</tr>'+
										'</thead>'+
										'<tbody>'+
											'<tr ng-repeat="field in bpmFormTable.fieldList" ng-if="field.isVirtual!=1" ng-class="{odd:$index%2==0,even:$index%2!=0,\'tr-select\':field.checked}" ng-click="field.checked=!field.checked">'+
												'<td>{{field.fieldName||field.name}}</td>'+
												'<td>{{field.fieldDesc}}</td>'+
												'<td>{{field.fieldType||field.type||field.dataType}}</td>'+
											'</tr>'+
										'</tbody>'+
									'</table>'+
								'</div>'+
							'</div>'+
						'</div>'+
						'<div class="condition-conds" ng-if="type!=\'sysQueryView\'">'+
							'<div class="condition-conds-div condition-conds-build" id="condition-build-div">'+
								'<div class="condition-conds-div-left">'+
									'<div class="condition-conds-div-left-div" style="padding-top: 180px;">'+
										'<a  href="javascript:;" class="button" ng-click="service.selectCondition()">'+
											'<span>==></span>'+
										'</a>'+
									'</div>'+
								'</div>'+
								'<div class="condition-conds-div-right">'+
									'<div class="condition-conds-div-right-div">'+
										'<table id="conditionTbl" cellpadding="0" cellspacing="0" border="0" class="table-detail">'+
											'<thead>'+
												'<tr class="leftHeader">'+
													'<th  nowrap="nowrap">列名</th>'+
													'<th  nowrap="nowrap">显示名</th>'+
													'<th  nowrap="nowrap">控件类型</th>'+
													'<th  nowrap="nowrap">条件</th>'+
													'<th  nowrap="nowrap">值来源</th>'+
													'<th  nowrap="nowrap">管理</th>'+
												'</tr>'+
											'</thead>'+
											'<tbody>'+
												'<tr ng-repeat="cd in conditionFields">'+
													'<td var="name">{{cd.na}}</td>'+
													'<td>'+
														'<input ng-model="cd.cm"  type="text" class="ht-input w100 h100 border-none margin-none "/>'+
													'</td>'+
													'<td>'+
														'<select ng-model="cd.ct" style="width:70px;" class="ht-input w100   margin-none " ng-options="c.k as c.v for c in ctlOptions">'+
															'<option value="">请选择</option>'+
														'</select>'+
													'</td>'+
													'<td>'+
														'<select ng-model="cd.op" style="width:70px;" class="ht-input w100   margin-none "  ng-options="c.k as c.v for c in getOptions(cd.ty)">'+
															'<option value="">请选择</option>'+
														'</select>'+
													'</td>'+
													'<td>'+
														'<select ng-model="cd.vf" style="width:70px;" class="ht-input w100   margin-none " >'+
															'<option value="" >请选择</option>'+
															'<option value="1" >表单输入</option>'+
															'<option value="5" >动态传入</option>'+
														'</select>'+
													'</td>'+
													'<td>'+
														'<tool-buttons list="conditionFields" index="{{$index}}" type="4"></tool-buttons>'+
													'</td>'+
												'</tr>'+
											'</tbody>'+
										'</table>'+
									'</div>'+
								'</div>'+
							'</div>'+
						'</div>'+
						'<div class="condition-conds"  ng-if="type==\'sysQueryView\'">'+
							'<div class="condition-conds-div condition-conds-build" id="condition-build-div">'+
								'<div class="condition-conds-div-left">'+
									'<div class="condition-conds-div-left-div" style="padding-top: 180px;">'+
										'<a  href="javascript:;" class="button" ng-click="service.selectCondition(type)">'+
											'<span>==></span>'+
										'</a>'+
									'</div>'+
								'</div>'+
								'<div class="condition-conds-div-right">'+
									'<div class="condition-conds-div-right-div">'+
										'<table id="conditionTbl" cellpadding="0" cellspacing="0" border="0" class="table-detail fieldSetting">'+
											'<thead>'+
												'<tr class="leftHeader">'+
													'<th  width="2%">列名</th>'+
													'<th  width="2%">数据类型</th>'+
													'<th  width="2%">操作符</th>'+
													'<th  width="2%">值来源</th>'+
													'<th  width="5%">格式化</th>'+
													'<th  width="5%">管理</th>'+
												'</tr>'+
											'</thead>'+
											'<tbody>'+
												'<tr ng-repeat="cd in conditionFields">'+
													'<td >{{cd.name}}</td>'+
													'<td >{{cd.type}}</td>'+
													'<td>'+
														'<select ng-model="cd.operate" class="ht-input w100   margin-none " style="width:70px;" ng-options="c.k as c.v for c in service.getOperateOptions(cd.type)">'+
														'</select>'+
													'</td>'+
													'<td>'+
														'<select ng-model="cd.valueFrom" class="ht-input w100  margin-none ">'+
															'<option value="1" >表单输入</option>'+
															'<option value="5" >动态传入</option>'+
														'</select>'+
													'</td>'+
													'<td>'+
														'<select ng-model="cd.format"  class="ht-input w100  margin-none " ng-if="cd.type==\'date\'">'+
															'<option value="yyyy-MM-dd">yyyy-MM-dd</option>'+
															'<option value="yyyy-MM-dd HH:mm:ss">yyyy-MM-dd HH:mm:ss</option>'+
															'<option value="yyyy-MM-dd HH:mm:00">yyyy-MM-dd HH:mm:00</option>'+
															'<option value="HH:mm:ss">HH:mm:ss</option>'+
														'</select>'+
													'</td>'+
													'<td>'+
														'<tool-buttons list="conditionFields" index="{{$index}}" type="4"></tool-buttons>'+
													'</td>'+
												'</tr>'+
											'</tbody>'+
										'</table>'+
									'</div>'+
								'</div>'+
							'</div>'+
						'</div>'+
					'</div>',
					link:function(scope,el,attrs){
					}
	};
})
//过滤条件<filter-setting ></filter-setting>
.directive('filterSetting', function() {
	return {
		restrict : 'E',
		replace : true,
		template :  '<div>'+
						'<div class="table-top-left">'+
							'<div class="toolBar" style="margin:0;">'+
								'<div class="group">'+
									'<a class="link add" id="btnSearch" ng-click="service.addFilter(filterUrl)">添加</a>'+
								'</div>'+
							'</div>'+
						'</div>'+
						'<table id="filterTbl"  class="table-grid">'+
							'<thead>'+
								'<tr>'+
									'<th width="5%">选择</th>'+
									'<th width="10%">名称</th>'+
									'<th width="10%">Key</th>'+
									'<th width="10%">类型</th>'+
									'<th  ng-if="!service.noRights">'+
										'权限'+
										'<right-select list="filterFields"></right-select>'+
									'</th>'+
									'<th width="10%">管理</th>'+
								'</tr>'+
							'</thead>'+
							'<tbody>'+
								'<tr var="filterTr" ng-repeat="f in filterFields">'+
									'<td var="index">'+
										'<input class="pk" type="checkbox" name="select"></td>'+
									'<td >{{f.name}}</td>'+
									'<td >{{f.key}}</td>'+
									'<td >{{f.type==2?"SQL":"条件脚本"}}</td>'+
									'<td  ng-if="!service.noRights">'+
										'<div right="f.right[0]" choose-target></div>'+
									'</td>'+
									'<td>'+
										'<tool-buttons list="filterFields" index="{{$index}}" type="3" ></tool-buttons>'+
									'</td>'+
								'</tr>'+
							'</tbody>'+
						'</table>'+
					'</div>'
	};
})
//基本信息<manage-setting ></manage-setting>
.directive('baseSetting', function() {
	return {
		restrict : 'E',
		replace : true,
		template :  '<div >'+
	'<div class="tbar-title">'+
		'<span class="tbar-label">基本信息</span>'+
	'</div>'+
	'<table class="table-detail" cellpadding="0" cellspacing="0" border="0" type="main" style="border-width: 0!important;">'+
		'<tr>'+
			'<th  width="10%">表单别名:</th>'+
			'<td>'+
				'<input type="text" ng-model="dataRightsJson.formKey"  readonly="readonly" class="inputText" validate="{required:true}" style="width:210px;margin-right:2px;" />'+
			'</td>'+
		'</tr>'+
		'<tr>'+
			'<th  width="10%">绑定流程:</th>'+
			'<td>'+
				'<input type="text"  ng-model="dataRightsJson.subject" readonly="readonly"  class="inputText"  style="width:210px;margin-right:2px;" />'+
				'<a style="margin-right:5px;" ng-click="selectFlow()" class="button" href="javascript:;">'+
					'<span class="icon ok"></span>'+
					'<span>选择</span>'+
				'</a>'+
				'<a  ng-click="cancel()" class="button" href="javascript:;">'+
					'<span class="icon cancel"></span>'+
					'<span>重置</span>'+
				'</a>'+
			'</td>'+
		'</tr>'+
		'<tr>'+
			'<th >是否分页:</th>'+
				'<td>'+
					'<input type="radio" ng-model="dataRightsJson.needPage" value="0" >'+
						'不分页'+
					'<input type="radio" ng-model="dataRightsJson.needPage" value="1" >'+
						'分页'+
					'<span style="color:red;" ng-if="dataRightsJson.needPage==1">'+
						'分页大小：'+
					'<select ng-model="dataRightsJson.pageSize" >'+
					'<option value="5"  >5</option>'+
					'<option value="10" >10</option>'+
					'<option value="15" >15</option>'+
					'<option value="20" >20</option>'+
					'<option value="50" >50</option>'+
					'</select>'+
					'</span>'+
				'</td>'+
		'</tr>'+
		'<tr>'+
			'<th>是否初始查询:</th>'+
			'<td>'+
			'<select ng-model="dataRightsJson.isQuery"  validate="{required:true}">'+
			   ' <option value="0"  >是</option>'+
			    '<option value="1"  >否</option>'+
		    '</select>'+
	        '</td>'+
        '</tr>'+
		'<tr>'+
			'<th>'+
				'没有过滤条件'+
				'<br/>'+
				'是否需要默认过滤:'+
			'</th>'+
			'<td>'+
				'<select ng-model="dataRightsJson.isFilter"  validate="{required:true}">'+
					'<option value="0"  >是</option>'+
					'<option value="1"  >否</option>'+
				'</select>'+
			'</td>'+
		'</tr>'+
		'<tr>'+
			'<th>数据模板:</th>'+
			'<td>'+
				'<select ng-model="dataRightsJson.templateAlias"  validate="{required:true}">'+
					'<option value="">--请选择数据模板--</option>'+
					'<c:forEach items="${templates}" var="template">'+
					'<option value="${template.alias}">${template.templateName}</option>'+
					'</c:forEach>'+
				'</select>'+
				'<div class="tipbox">'+
					'<a href="javascript:;" class="tipinfo">'+
						'<span>添加更多数据模板，请到自定义表单模板中添加类型为"业务数据模板"的模板</span>'+
					'</a>'+
				'</div>'+
			'</td>'+
		'</tr>'+
	'</table>'+
'</div>'
	};
})

//排序字段
.directive('sortSetting', function() {
	return {
		restrict : 'E',
		replace : true,
		scope:true,
		template :  '<div>'+
						'<div class="sort-cols">'+
							'<div class="sort-cols-div">'+
								'<table id="sort-columnsTbl" cellpadding="0" cellspacing="0" border="0" class="table-detail">'+
									'<thead>'+
										'<tr class="leftHeader">'+
											'<th>选择</th>'+
											'<th>列名</th>'+
											'<th>注释</th>'+
										'</tr>'+
									'</thead>'+
									'<tbody>'+
										'<tr  ng-repeat="field in bpmFormTable.tableFieldList||bpmFormTable.fieldList" ng-class="{odd:$index%2==0,even:$index%2!=0}" ng-click="field.checked=!field.checked">'+
											'<td>'+
												'<input class="pk" type="checkbox" ng-model="field.checked">'+
												'<td>{{field.name||field.fieldName}}</td>'+
												'<td>{{field.desc||field.fieldDesc}}</td>'+
											'</tr>'+
										'</tbody>'+
									'</table>'+
								'</div>'+
							'</div>'+
							'<div class="sort-conds">'+
								'<div class="sort-conds-div sort-conds-build" id="sort-build-div">'+
									'<div class="sort-conds-div-left">'+
										'<div class="sort-conds-div-left-div">'+
											'<a style="margin-top: 180px;" ng-click="service.selectSort(listkey,namekey,desckey)" href="javascript:;" class="button">'+
												'<span>==></span>'+
											'</a>'+
										'</div>'+
									'</div>'+
									'<div class="sort-conds-div-right">'+
										'<div class="sort-conds-div-right-div">'+
											'<table id="sortTbl" cellpadding="0" cellspacing="0" border="0" class="table-detail">'+
												'<thead>'+
													'<tr class="leftHeader">'+
														'<th  nowrap="nowrap">列名</th>'+
														'<th  nowrap="nowrap">注释</th>'+
														'<th  nowrap="nowrap">排序</th>'+
														'<th  nowrap="nowrap">管理</th>'+
													'</tr>'+
												'</thead>'+
												'<tbody>'+
													'<tr var="sortTr" ng-repeat="sd in sortFields">'+
														'<td >{{sd.name}}</td>'+
														'<td >{{sd.desc}}</td>'+
														'<td>'+
															'<select ng-model="sd.sort">'+
																'<option value="">请选择</option>'+
																'<option value="ASC">升序</option>'+
																'<option value="DESC">降序</option>'+
															'</select>'+
														'</td>'+
														'<td>'+
															'<tool-buttons list="sortFields" index="{{$index}}" type="4"></tool-buttons>'+
														'</td>'+
													'</tr>'+
												'</tbody>'+
											'</table>'+
										'</div>'+
									'</div>'+
								'</div>'+
							'</div>'+
						'</div>'+
					'</div>',
					link:function(scope,element,attrs){
						scope['listkey'] = attrs['listkey'];
						scope['namekey'] = attrs['namekey'];
						scope['desckey'] = attrs['desckey'];
					}
	};
})