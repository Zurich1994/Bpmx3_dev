(function (){
    var paths  = [
              ];
    for (var i=0,pi;pi = paths[i++];) {
        document.write('<script type="text/javascript" src="'+ __ctx + pi +'"></script>');
    }
})();
var baseServices = angular.module( "baseServices", ["authApp",'ComplexService'] );
baseServices.factory("$jsonToFormData",function() {
	function transformRequest( data, getHeaders ) {
		var headers = getHeaders();
		headers["Content-Type"] = "application/x-www-form-urlencoded; charset=utf-8";
		return $.param(data);
	}
	return( transformRequest );
})
.directive('htCheckbox', function() {
	return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var key = attrs["htCheckbox"];
            var getValAry = function(){
                var val = getValByScope(element,key);
                if(val){
                    if(typeof val == "string"){
                        return {type:"string",data:val.split(",")};
                    }
                    else{
                        return {type:"object",data:val};
                    }
                }
                return {type:"string",data:[]};
            };
            scope.$watch(key,function(newVal,oldVal){
                if(!newVal)return;
                if(newVal!==oldVal || !element.data("notFirstTime")){
                    element.data("notFirstTime",true);
                    if(typeof newVal == "string"){
                        if(newVal==element.val()||newVal.split(",").indexOf(element.val())!=-1){
                            element[0].checked = true;
                        }
                        else{
                            element[0].checked = false;
                        }
                    }
                    else{
                        if(newVal.indexOf(element.val())!=-1){
                            element[0].checked = true;
                        }
                        else{
                            element[0].checked = false;
                        }
                    }
                }
            },false);
            element.bind('change',function(){
                var elementVal = element[0].checked,
                    option = attrs["value"],
                    array = getValAry();
                if(elementVal){
                    if(!isInArray(array.data,option)){
                        array.data.push(option);   
                    }
                }
                else{
                    array.data.remove(option);
                }
                if(array.data.length > 1){
                    array.data.remove("");
                }
                else if(array.data.length == 0){
                    array.data.push("");
                }
                if(array.type=="string"){
                    setValToScope(element,array.data.join(','),null,key);    
                }
                else{
                    setValToScope(element,array.data,null,key);       
                }
            });
        }
    };
})
.provider('DemotionPermission',function(){
	//是否需要权限降级
	var _demotion = false;
	this.setDemotion = function(t){
		_demotion = !!t;
	}
	this.$get = function(){
		return {
			demotion : _demotion
		};
	}
})
/**
 * 表单权限控制
 */
.directive('htPermission', ['$compile','DemotionPermission',function($compile,DemotionPermission) {
	return {
		priority:5,
		link:function(scope,elm,attrs){
			scope.handlerPermission = function(newVal,el,attr){
				//需要权限降级时  将必填、编辑 权限降级为只读
				if(DemotionPermission.demotion){
					if(newVal!="y"||newVal!="r"){
						newVal = "r";
					}
				}
				var	ngModel=attr.ngModel,scopeVal = '';
				if(el.attr("type") == "subGroupTr"){
					scope.subFieldPermission(newVal,el);
				}
				else if(el.attr("type") == "subGroup"){
	    			scope.subFieldPermission(newVal,el);
	    		}else {
	    			scopeVal= getValByScope(el,ngModel,scope) || '';
	    			scope.fieldPermission(newVal,el,scopeVal);
	    		}
			};
	    	scope.$watch(attrs.htPermission,function(newVal,oldVal){
	    		if(!newVal)return;
	    		if (newVal !== oldVal || !elm.data("notFirstTime")) {
	    			elm.data("notFirstTime", true);
	    			if((attrs.htSelectorDef&&parseToJson(attrs.htSelectorDef)['showCurrent'])||(elm.hasClass("selector-home")&&attrs.selectorType))
	    				window.setTimeout(function(){ 
	    					scope.handlerPermission(newVal,elm,attrs);
	    				},100);
	    			else
	    				scope.handlerPermission(newVal,elm,attrs);
	    		}
	    	});
	    	/**
	    	 * 复合字段（子表）权限
	    	 */
	    	scope.subFieldPermission = function(val,elm){
	    		switch (val) {
					case 'b': //必填
						var temp = attrs.htPermission.split("."),
							ngModel = "data."+temp[temp.length-1];
						scope.$watch(ngModel.replaceAll("\\$\\$","."),function(n,o){
							if(n.length==0){
								elm.addClass("field-home");
								elm.validMe({text:"",rule:"{'required':true}"});
							}else{
								elm.qtipSuccess();
							}
						},true);
						break;
					case 'r': //只读
						var inputList = elm.find("[ht-permission]");
						inputList.each(function(){
							var scopeVal = getValByScope($(this),"","");
							$(this).after("<span>"+scopeVal+"</span>");
							$(this).remove();
						});
						elm.children(".group-title").find("a[ng-click]").remove();
						elm.children(".owner-div").find("a[ng-click]").remove();
						break;
					case 'y': //隐藏
						elm.remove();
						break;
					default://编辑没修改（w）
						break;
				}
	    	};
	    	/**
    		 * 字段权限
    		 */
	    	function setToReadCall(elm,scopeVal){
	    		if(isComplexTag(elm)){
					elm.parent().html("<span>"+scopeVal+"</span>");
				}else if(elm.hasClass("file-div")){
					if(scopeVal){
						elm.next().remove();
						elm.next().remove();
						var temp = [];
						for(var i =0 ;i<scopeVal.length;i++){
							temp.push("<a onclick='downLoadFileById(\""+scopeVal[i].fileId+"\")' class='file-down' title='点击下载附件'>"+scopeVal[i].fileName+"</a>");
						}
						elm.after("<span>"+temp.join(",")+"</span>");
					}else{
						elm.closest(".s-closest").remove();
					}
					
				}else{
					elm.after("<span>"+scopeVal+"</span>");
				}
				elm.remove();
	    	}
	    	function setToRead(elem,scopeVal){
	    		if(scopeVal){
	    			setToReadCall(elem,scopeVal);
	    			return;
	    		}
	    		var showCuEl = elem.attr("selector-def-div");
	    		if(!showCuEl){
	    			setToReadCall(elem,scopeVal);
	    			return;
	    		}
	    		showCuEl = parseToJson(showCuEl);
	    		if(!showCuEl.showCurrent){
	    			setToReadCall(elem,scopeVal);
	    			return;
	    		}
				window.setTimeout(function(){
					setToRead(elem,getValByScope(elem));
				},1000);
	    	}
	    	scope.fieldPermission = function(val,elm,scopeVal){
	    		var elmParent = elm.parent();
	    		switch (val) {
					case 'b': //必填
						//让字段必填 ht-field-valid="{'required':true}"
						var tempValid = elm.attr("ht-field-valid");
						if(tempValid){
							tempValid = parseToJson(tempValid);
						}else{
							tempValid = {};
							elm.addClass("field-home");
						}
						tempValid.required=true;
						var ngModelVal = elm.attr("ng-model"); 
						elm.removeAttr("ng-model");
						elm.attr("ht-field-valid",JSON.stringify(tempValid));
						$compile(elm)(elm.scope());
						ngModelVal&&elm.attr("ng-model",ngModelVal);
						break;
					case 'r': //只读
						setToRead(elm,scopeVal);
						break;
					case 'y': //隐藏
						
						if(isComplexTag(elm)||elm.attr("ht-file")){
							elm = elm.closest(".field-home");
							elm.prev('th').remove();
						}else{
							elm.parent().prev('th').remove();
						}
						var closestTR = elm.closest("tr") ;
						var closestSubGroup = elm.closest("[type='subGroup']") ;
						var tdths=closestTR.children();
						elm.remove();
						var isNull = true;
						tdths.each(function(){
							if($(this).html()){
								isNull=false;
								return true;
							}
						});
						if(isNull) closestTR.remove();
						//如果是子表，那么当字表中的tbody为空时 就隐藏
						if(closestSubGroup[0]&&!closestSubGroup.find('tbody').html()) closestSubGroup.remove();
						break;
					default://编辑没修改（w）
						break;
				}
	    	};
	    	//在需要权限降级时，如果没有配置字段权限，默认为只读权限
	    	if(DemotionPermission.demotion){
				scope.handlerPermission('r',elm,attrs);
			}
		}
	};
}])
.directive('htDate', function() {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
        	var ngModel = attrs.ngModel;
        	switch(attrs.htDate){
        		case "date":
        			$(element).on("focus",function(){
        				var me = $(this);
        				WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true});
        				me.blur();
    				   scope.$apply(function(){
                           eval("(scope." + ngModel + "='" + me.val() + "')");    
                       });
        			});
        		break;
         		case "datetime":
        			$(element).on("focus",function(){
        				var me = $(this);
        				WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true});
        				me.blur();
    				   scope.$apply(function(){
                           eval("(scope." + ngModel + "='" + me.val() + "')");    
                       });
    			});
        		break;
        		case "wdateTime":
        			$(element).on("focus",function(){
        				var me = $(this), dateFmt=  (me.attr('datefmt')?me.attr('datefmt'):'yyyy-MM-dd');
            			WdatePicker({dateFmt:dateFmt,alwaysUseStartDate:true});
            			me.blur();
        			   scope.$apply(function(){
                           eval("(scope." + ngModel + "='" + me.val() + "')");    
                       });
        			});
        		break;
        	}
        }
    };
})
/**
 * 对时间进行格式化处理  控件格式如下
 * <input type="text" 
 * 	ng-model="data.person.born" 
 * 	class="inputText" 
 * 	ht-date-format="
 * 		{'currentTime':true,'exp':'yyyy-MM-dd HH:mm:ss'}
 * 	">
 * currentTime：表示是否显示当前日期
 * exp：格式化表达式
 */
.directive('htDateFormat', function($injector) {
	var link = function(scope, element, attrs, $ctrl) {
		var json = parseToJson(attrs['htDateFormat']);
		element.addClass("dateformat");
		if(json.currentTime){
			var now=new Date().Format(json.exp);
			setValToScope(element,now);
		}
	};
	return {
		restrict : 'A',
		require : "ngModel",
		compile : function() {
			return link;
		}
	};
})
.directive('htDic', function(){
	return {
		restrict: 'EAC',
		require : "ngModel",
		link: function(scope, element, attrs) {
			if(!attrs.htDic)return;
			scope.$watch(attrs.ngModel,function(newVal,oldVal){
				if (newVal !== oldVal || !element.data('dictionary')) {
	    			element.data('dictionary', true);
	    			scope.update(newVal);
				}
			},false);
			
			//ngModel的值变化 与 数据字典的初始化 是两条生命周期线，要进行赋值需要在两者都完成的时候进行
			scope.update = function(val){
				var dicReady = element.data("dictionaryReady"); 
				if(typeof dicReady=='undefined'){
					element.data("dictionaryReady",(typeof val=='undefined')?true:val);
				}
				else{
					var relVal = (typeof val=='undefined')?dicReady:val;
					element.combotree("clear");
					//选中数据字典中key与ngModel值相同的节点
					element.eachComboNode(function(node){
						if(node.key&&node.key==relVal){
							element.combotree('setValue',node.id);
							return false;
						}
						return true;
					});
				}
			}
			
			var url = __ctx +"/platform/system/dataDict/getByTypeKeyForComBo.ht?typeKey="+attrs.htDic;
			element.combotree({
				url:url,
				onLoadSuccess:function(node,data){
					scope.update();
				},
				onClick:function(node){
					setValToScope(element,node.key);
				}
			});
		}
	};
})
.directive('htFileUpload', [function() {
	return {
		restrict: 'EAC',
		scope:{
			htFileUpload:'='
		},
		link: function(scope, element, attrs) {
			scope.choose = function(){
				UploadDialog(scope.setting);
			}
			
			scope.download = function(item){
				window.open(__ctx + "/platform/system/file/download.ht?id="+item.id);
			}
			
			//移除某项
			scope.remove = function(index){
				scope.file.splice(index,1);
				scope.updateValue(false);
			}
			
			scope.updateValue = function(digest){
				var v = scope.file.length==0?"":angular.toJson(scope.file);
				//更新数据到父作用域
				if("ng"==scope.setting.bindType){
					var ngModel = attrs['ngModel'];
					if(!ngModel)return;
					eval('scope.$parent.' + ngModel + '=' + (v?v:"''"));
					digest&&scope.$parent.$digest();
				}
				//更新数据到对象元素
				else if("jq"==scope.setting.bindType){
					if(!scope.bindObj)return;
					scope.bindObj.val(v);
				}
			}
			
			var setting = {
				download:true,	//true：允许下载，false：不允许下载
				bindType:'ng'	//ng:ng-model模式，jq:jquery模式,jq模式下需要配置bind属性
			};
			
			if($.extend(true,setting,scope.htFileUpload||{})){
				scope.setting = setting;
				
				scope.setting.callback = function(data){
					!angular.isArray(scope.file)&&(scope.file==[]);
					scope.file = scope.file.concat(data);
					scope.updateValue(true);
				}
				
				if("ng"==scope.setting.bindType){
					var ngModel = attrs['ngModel'];
					if(!ngModel)return;
					scope.$parent.$watch(ngModel,function(newVal,oldVal){
			    		if (newVal !== oldVal || !element.data('file')) {
			    			element.data('file', true);
							scope.file = newVal || [];
						}
					},false);
				}
				else if("jq"==scope.setting.bindType){
					var bindObj = angular.element(scope.setting.bind);
					if(!bindObj)return;
					scope.bindObj = bindObj;
					scope.file = parseToJson(bindObj.val()||'');
				}
			}
		},
		template: '<span><div class="ht-input"><span class="span-user owner-span" ng-repeat="item in file">'
				 +'<a title="下载该文件" ng-if="setting.download" ng-click="download(item)">{{item.name}}</a><span ng-if="!setting.download">{{item.name}}</span>'
				 +'<a class="btn btn-xs fa-remove" style="margin-bottom:4px;" title="移除该项" ng-click="remove($index)"></a></span>'
				 +'</div><a class="btn btn-sm btn-primary fa-upload" ng-click="choose()"><span>上传</span></a></span>',
        replace: true
	};
}])
/**
 * ht-select-ajax  动态加载select的options数据  
 *  列如： <select ng-model="form.typeId" ng-options="(m.id) as m.text for m in formTypeList"
 *			  ht-select-ajax="{url:'${ctx}/platform/system/sysType/getByGroupKey.ht?groupKey=FORM_TYPE',field:'formTypeList'}">
 *		 		 <option value="">请选择</option>
 *		 </select>
 *	传入参数 
 *		url ： 请求地址 
 *		field ： formTypeList 对应于 ng-options 中的 formTypeList （两者必须相同）
 */
.directive('htSelectAjax', function($injector) {
	return {
		restrict: 'A',
		link: function(scope, element, attrs) {
			var BaseService = $injector.get("BaseService");
			var option=attrs["htSelectAjax"];
			option=eval("("+option+")");
			if(scope.$root.$$childHead[option.field]) return;
			BaseService.get(option.url,function(data){
				if(option.dataRoot){
					data = data[option.dataRoot];
				}
				scope[option.field] = data;
				scope.$root.$$childHead[option.field] = scope[option.field];
			});
		}
	};
})
.directive('htTip', function($injector) {
	return {
		restrict: 'A',
		scope:{
			htTip:"="
		},
		link: function(scope, element, attrs) {
			var defaultSetting = {
					hide: {
						event:'mouseleave',
   			        	leave: false,
   			        	fixed:true,
   			        	delay:100
			        },
					style: {
						classes: 'qtip-default  qtip qtip-bootstrap qtip-shadow'
				    }
				  };
			var setting = angular.extend(scope.htTip || {},defaultSetting);
			element.qtip(setting);
		}
	};
})
.directive('htZtree', ['BaseService',function(BaseService) {
    return {
        restrict: 'A',
        scope:{
        	htZtree:"=",
        	htCallback:"=",
        	htDataKey:"=",
        	htCheck:"="
        },
        link: function(scope, element, attrs) {
        	element.addClass("ztree");
        	if(!element.prop("id")){
        		//ztree所在ul标签必须有唯一的id属性,否则当页面有两个ztree时回调函数会出现问题
        		element.prop("id",BaseService.guid());
        	}
        	scope.setting = {
        			view:{
        				dblClickExpand:false
        			},
        			data:{
        				key:scope.htDataKey||{}
        			},
        			check:scope.htCheck||{},
		        	callback: scope.htCallback||{}
        	};
        	
        	scope.$watch("htZtree",function(newVal,oldVal){
        		if(newVal!==oldVal){
        			$.fn.zTree.init($(element),scope.setting, newVal);
        		}
        	},true);
        }
    };
}])
.directive('htBindHtml', function($compile) {
	return {
		restrict : 'A',
		link : function(scope, elm, attrs) {
			scope.unbindWatch = scope.$watch(attrs.htBindHtml, function(newVal, oldVal) {
                if (newVal !== oldVal) {
                    if(newVal){
                        elm.data('unbindWatchTag',true);
                        elm.html(newVal);
                        scope.htmlFn&&scope.htmlFn.call();
                        $compile(elm)(scope);
                    }
                    else{
                        elm.html('');
                        //避免重复添加监视
                        elm.data('unbindWatchTag')&&scope.unbindWatch();
                    }
                }
            });
		}
	};
})
.directive('htInit', function($compile) {
	return {
		restrict : 'A',
		link : function(scope, elm, attrs) {
			var json = parseToJson(attrs["htInit"]);
			for(var i in json){
				setValToScope(null,json[i],null,i,scope);
			}
		}
	};
})
//显示用户的指令,使用示例:<input ht-user-tag="1" />
.directive('htUserTag', ["BaseService",function(BaseService) {
	return {
		restrict : 'A',
		scope:{
			//userId
			htUserTag:"=",
		},
		controller: function($scope,$element){
			$scope.showDetail = function(userId){
				new UserInfoDialog(userId).show();
			}
		},
		link : function(scope, elm, attrs) {
			if(scope.htUserTag){
				BaseService.post(__ctx + "/platform/org/user/userInfo.ht",{id:scope.htUserTag},function(data){
					scope.user = data;
				});
			}
		},
		template:'<span class="owner-span" ng-hide="user | isEmpty"><a href="#" ng-click="showDetail(user.id)">{{user.name}}</a></span>',
		replace:true
	};
}])
.directive('onFinishRenderFilters', function ($timeout) {
    return {
        restrict: 'A',
        link: function(scope, element, attr) {
            if (scope.$last === true) {
                $timeout(function() {
                    scope.$emit('ngRepeatFinished');
                });
            }
        }
    };
})
.service('BaseService', ['$http','$jsonToFormData', function($http,$jsonToFormData) {
    var service = {
    		get:function(url,callback){
    			$http.get(url).success(function(data,status){
            		if(callback){
            			callback(data,status);
            		}
        		})
        		.error(function(data,status){
        			if(callback){
            			callback(data,status);
        			}
        			//TODO 根据返回的错误状态(status)显示对应的全局提示
        		});
    		},
    		post:function(url,param,callback){
    			$http.post(url,param,{transformRequest:$jsonToFormData})
				 .success(function(data,status){
					 if(callback){
	    				callback(data,status);
	    			 }
				 })
				 .error(function(data,status){
					 if(callback){
	    				callback(data,status);
	    			 }
					 //TODO 根据返回的错误状态(status)显示对应的全局提示
				 });
    		},
    		//m内容，b:true->alert输出；false:console
    		show:function(m,b){
    			if(b==null||b==false){
    				console.info(m);
    			}else{
    				alert(m+"");
    			}
    		},
    		//生成ID
    		guid:function(){
    			return Math.random().toString(36).substring(2, 15) +
    	        	   Math.random().toString(36).substring(2, 15);
    		}
        };
    return service;
}])
.filter('isEmpty', function () {
        var bar = "";
        return function (obj) {
            for (bar in obj) {
                if (obj.hasOwnProperty(bar)) {
                    return false;
                }
            }
            return true;
        };
})
.filter('htTime', function () {
	//毫秒转换成 **天**小时**分**秒的格式
	return function (input) {
        var day = (input / 1000 / 60 / 60 / 24) << 0
        	hour = (input / 1000 / 60 /60) % 24 << 0,
        	min = (input / 1000 / 60) % 60 << 0,
            sec = Math.round((input / 1000) % 60),
            result = [];
        
        if(day){
        	result.push(day + '天');
        }
        if(hour){
        	result.push(hour+'小时');
        }
        if(min){
        	result.push(min+'分');
        }
        if(!isNaN(sec)&&sec){
        	result.push(sec+'秒');
        }
        return result.join('');
    };
});

