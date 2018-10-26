angular.module('components', [])
.factory('baseService', function ($rootScope,$http,$compile,$rootElement) {
	$rootScope.modalShown = false;
	var touchmoveHandler= function(e){e.preventDefault();};
	$rootScope.$watch('modalShown',function(newVal,oldVal){
		if(newVal!==oldVal){
			if(newVal){
				 document.addEventListener('touchmove',touchmoveHandler, false);
			}else{
				 document.removeEventListener('touchmove',touchmoveHandler, false);
			}
		}
	});
	
	var baseService = {
		mytimeSwitch:false,
		agentType:"all"
	};
	
	/**
	 * 获取服务器的JSONP数据
	 * @param {Object} 
	 * 		url 待载入页面的URL地址
	 *  @param {Object} conf 配置文件
	 *  	params 待发送数据 Key/value 参数。
	 *  	callback  载入成功时回调函数。
	 */
	baseService.jsonp = function(url,conf){
		var params = conf.params,
			callback = conf.callback,
			isCloseToast = true,
			type = true;
		if(!params){
			params = {};
		}
		//附加回调函数
		params.callback = 'JSON_CALLBACK';
		conf.params = params;
		conf.params.__rnd = new Date().getTime();
		if( conf.isCloseToast)
			type = false;
		if (!conf.__notshowtoast) {
			window.setTimeout(function(){
				HT.toastInWindow(conf.___id, conf.___popid, conf.___waitmsg, conf.___waittime);
			}, 1);
		}
		function successFn(data){
            if (isCloseToast && type) {//如果不需要关闭请传false
                HT.closeToastInWindow(conf.___id, conf.___popid);
                if (conf.___colseid) 
                    HT.closeToastInWindow(conf.___colseid);
            }
            callback(data);
		}
		function errorFn(data, status, headers, config, statusText){
            if (!HT.networkStatus()) 
                return;
            if (headers && headers('__timeout')) {
                window.setTimeout(function(){
                    HT.toastInWindow(conf.___id, conf.___popid, $i18n$.LOGIN.LOGIN_TIME_OUT, 2000, 0)
                }, 1);
				HT.goback(-1);
                HT.goToHtml("login");
            }
            else {
                if (url.indexOf('getUserInfo') > 0) {
                    alert("xxxx");
                }
                else 
                    if (url.indexOf('mobileLogin') > 0) {
                        window.setTimeout(function(){
                            HT.toastInWindow(conf.___id, conf.___popid, $i18n$.COMMON.GET_REMOTE_DATA_FAIL, 2000, 0);
                        }, 1);
                    }
                    else {
                        window.setTimeout(function(){
                            HT.toastInWindow(conf.___id, conf.___popid, $i18n$.COMMON.GET_NOT_LOGIN, 2000, 0);
                            window.setTimeout(function(){
                                HT.goback("-1");
                                HT.goToHtml("login");
                            }, 1000);
                        }, 1);
                    }
                
            }
        }
		if(conf.post){
			$http.jsonp(url, conf).success(successFn).error(errorFn);// $http.post(url, conf.params).success(successFn).error(errorFn);
		}else{
			 $http.jsonp(url, conf).success(successFn).error(errorFn);
		}
    };
	
	baseService.post = function(url,params,callback,conf){
		if(!conf)
			conf = {};
		conf.params = params;
		conf.callback = callback;
		baseService.jsonp(url,conf);
    };

	baseService.pageList1 = function(url, params, callback, conf){
		
		var pageSize = HT.pageSize,
			start    = params.start,
			p        = HT.parseInt(start/pageSize);
		var page     = start%pageSize>0?(p+1):p;
			
		params.page = page;
		params.pageSize = pageSize;
		params.start = start;
		var type = params.type;
		//附加回调函数
		params.callback = 'JSON_CALLBACK';
		conf.params = params;
		try{uexWindow.toast(1,5,conf.waitmsg?conf.waitmsg:$i18n$.COMMON.WATTING_MSG,0);}catch(e){}
		$http.jsonp(url,conf)
			.success(function(data){
				if (type == 0) {
					callback(data);
					uexWindow.toast('0', '5', $i18n$.COMMON.QUERY_SUCCESS, '1000');
				}
				else {
					if (conf.params.start >= data.totalCounts) {
						uexWindow.toast('0', '5', $i18n$.COMMON.REACH_THE_END, '1000');
						return;
					}
					else if(data.results.length>0){
						callback(data);
						uexWindow.toast('0', '5', $i18n$.COMMON.LOAD_SUCCESS, '1000');
					}
					else if(data.results.length==0){
						uexWindow.toast('0', '5', $i18n$.COMMON.NULL_PAGE, '1000');
					}
				}
			})
			.error(function(data,status,headers,config,statusText){
			 	try{
			 		uexWindow.closeToast();
			 		uexWindow.toast(0,5,$i18n$.COMMON.GET_REMOTE_DATA_FAIL,1000);
					window.setTimeout(function(){HT.goToHtml("login");},1000);
			 	}catch(e){}
			 });
	};
	/**
	 * 分页查询
	 */
	baseService.pageList = function(url,params,callback,conf){
		if(!conf)
			conf = {};
		//是否查询
		var isSearch = conf.isSearch?conf.isSearch:false;
		if(isSearch)
			params.pageBean = HT.getDefaultPage();
		var flag = params.pageBean?(params.pageBean._flag?params.pageBean._flag:0):2;
		conf.params = HT.trustPageBean(params);
		//附加回调函数
		conf.params.callback = 'JSON_CALLBACK';
		conf.params = params;
		conf.params.__rnd = new Date().getTime();
		try{uexWindow.toast(1,5,conf.waitmsg?conf.waitmsg:$i18n$.COMMON.WATTING_MSG,0);}catch(e){}
		$http.jsonp(url,conf)
			 .success(function(data){
				 if(flag == 1 && !isSearch && conf.params.start > data.totalCounts){
						try{
					   	uexWindow.resetBounceView(flag);
						uexWindow.toast('0', '5', $i18n$.COMMON.REACH_THE_END,'1000');
						}catch(e){}
						return;
				 }
				if (flag == 1)
					data.results  = HT.merge(conf.pageLists,data.results);		 
				try{
					uexWindow.closeToast();
			  	   	if(flag !=2){
			  	   		uexWindow.resetBounceView(flag);
			  	   		uexWindow.toast('0', '5', flag==0?$i18n$.COMMON.RELOAD_SUCCESS:$i18n$.COMMON.LOAD_SUCCESS, '1000');
			  	   	}else{
			  	   		uexWindow.resetBounceView(0);
			  	   	}
				}catch(e){}
				
				callback(data);
			 })
			 .error(function(data,status,headers,config,statusText){
			 	try{
			 		uexWindow.closeToast();
			 	   	if(flag !=2)
			 	   		uexWindow.resetBounceView(flag);
			 		uexWindow.toast(0,5,$i18n$.COMMON.GET_REMOTE_DATA_FAIL,1000);
					window.setTimeout(function(){HT.goToHtml("login");},1000);
			 	}catch(e){}
			 });
	};
	/**
	 * 打开一个模态弹出窗口
	 * @param {Object} param
	 */
	baseService.openDialog = function(param){
		baseService.hasModal = false;
		param = param||{};
		if(!$rootScope.modalElement){
			var modal = angular.element("<modal-dialog show='modalShown'></modal-dialog>");
			var el = $compile(modal)($rootScope);
			$rootElement.find("body").append(el);
			$rootScope.modalElement = angular.element(el[0].lastChild);
		}
		var content = angular.element('<div></div>');
		//标题部分
		if(param.title){
			var title = angular.element('<div></div>');
			title.addClass('ng-modal-title');
			title.text(param.title);
			content.append(title);
		}
		//内容部分
		if(param.html){
			var contentDiv = angular.element('<div style="margin:1em;text-align: center;"></div>');
			contentDiv.append(param.html);
			content.append(contentDiv);
		}
		//按钮部分
		if(param.buttons){
			var type=param.inline?"":"ub-ver",style=param.inline?"":"padding: 0.5em 0 0 0 ;",uba=param.inline?"border:1px solid #eee;":"";
			var btnDiv = angular.element('<div class="ub ub-ac '+type+' brd-t1sc" style="'+style+'"></div>');
			angular.forEach(param.buttons,function(button,i){
				var temp=i==(param.buttons.length-1)?'':'ubb';
				var div='<div class="ub-f1 ng-modal-btn '+temp+' b-ddd w100" style="height: 1.5em;padding: 0.5em 0  0.5em 0;'+uba+'"></div>';
				var btn = angular.element(div);
				btn.text(button.label);
				if(button.backcolor){
					btn.css("background-color",button.backcolor);
				}
				if(button.forecolor){
					btn.css("color",button.forecolor);
				}
				if(button.bordercolor){
					btn.css("border-color",button.bordercolor);
				}
				btn.bind('click',button.callback);
				btnDiv.append(btn);
			});
			content.append(btnDiv);
		}
		$rootScope.modalElement.empty().append(content);
			$rootScope.modalShown = true;
			if (param.digest) {
				$rootScope.$digest();
			}
//		var location=window.document.body.scrollTop/parseInt(document.body.style.fontSize)+window.document.body.clientHeight/parseInt(document.body.style.fontSize)/3
		if(param.marTop){
			HT.$(document.querySelectorAll(".ng-modal-dialog")[0]).attr({"style":"top:"+param.marTop+"% !important;"})
		}
		HT.addModalToParent();
	}
	/**
	 * 关闭模态窗口
	 */
	baseService.closeDialog = function(){
		HT.hideModalFromParent();
			baseService.hasModal = true;
			$rootScope.modalShown = false;
			$rootScope.$digest();
	}
  	return baseService;
})
/**
 * 国际化工厂
 */
.factory('translateCustomLoader', function ($q) {
  return function (options) {
	//修改当前语言，则加载对应的语言 
	$i18n$ = HT.getI18n(options.key);
    var def = $q.defer();
    def.resolve($i18n$);
 
    return def.promise;
  };
})
/**
 * 选择器(人员、组织选择器)
 * <pre>
 *    <selector type="user" write="true" multi="false" name="user" ng-model="user"/>
 *    属性：
 *    	type：选择器类型（可选）“user”、“org”等 默认 user
 *    	write：是否可写 默认false（只读）
 *      multi：是否多选 默认false（单选）
 *    	name： 标示值
 *    	ng-model:
 *  </pre>
 */
.directive('selector', function() {
	return {
	    restrict: 'E',
		transclude: true,
		scope: {},
		controller: function($scope,$attrs,$element,$http,baseService) {
			$scope.users = [];
			$scope.flag = $attrs.name;
		    $scope.readonly = true;
		    if($attrs.write&&$attrs.write=='true')
		    	$scope.readonly = false;
		    
			var type = $attrs.type?$attrs.type:'user';
	
			/**
			 * 显示明细(首先从常用联系人中获取，没有则从服务器获取)
			 * @param info
			 * @returns
			 */
			$scope.displayDetail = function(info){
				switch(type){
					case "user":
						HT.userInfo(baseService,info);
						break;
					case "org":
					case "pos":
						var html = '<ul ontouchstart="zy_touch()" class="ub ubb b-ddd t-bla ub-ac lis h275"><li class="ut-s t-111 f-sz10625">'+$i18n$.ORG.NAME+'</li><li class="ub-f1 ulev-app1 t-888 t-right">' + info.un
                            			   +'</li></ul><ul ontouchstart="zy_touch()" class=" ub  t-bla ub-ac lis h275"><li class="ut-s t-111 f-sz10625">'+$i18n$.ORG.TITLE+'ID</li><li class="ub-f1 ulev-app1 t-888 t-right">'+ info.id
                            			   +'</li></ul>';
						baseService.openDialog({title:$i18n$.ORG.ORG_INFO,html:html,buttons:[{
									label:$i18n$.COMMON.CLOSE,
									callback:function(){
										baseService.closeDialog();
									}
								}]});
						break;
					default :
						alert($i18n$.COMMON.NO_MATCH_TYPE);
						break;
				}
			};
			/**
			 * 选择用户
			 * @returns
			 */
			$scope.chooseUser = function(){
				var us = $scope.users;
				if(us==null)
					us = [];
				var items=[];
				angular.forEach(us,function(item){
					items.push({id:item.id,
						name:item.un?
							item.un:
							item.name});
				});
				HT.setStorageJSON(HT_LS.selectorSelected,items);
				HT.setStorage("isfromform","true");
				HT.setStorage("isMulti",$attrs.multi!="false"?1:0);
				HT.setStorage(HT_LS.formToSeletor,$scope.flag);
		
				switch(type){
					case "user":
						uexWindow.open("contact", 0, "contact.html", 0, "", "", 0);
						break;
					case "org":
						uexWindow.open("org", 0, "org.html", 0, "", "", 0);
						break;
					default :
						alert($i18n$.COMMON.NO_MATCH_TYPE);
						break;
				}
			};
			
			$scope.wrap  = function(){
				$element.parent().addClass("user-c-bd").addClass("ub-f1").addClass("w95").removeClass("t-right").removeClass("umar-l1").removeClass("mar-t-03");
				var pui = $element.parent().parent();
				pui.addClass("ub-ver");
				var preli = $element.parent().parent().find("li").eq(0);
				preli.addClass("ub-f1").addClass("w100");
			};
			
			if ($attrs.wrap) 
				$scope.wrap();
		},
		template:'<div id="{{flag}}" class="ub  uc-a1 b-ddd  ub-ac pad040" selector="selector"><div class="mar-rl0a ub ub-ac w100">'
				+'<div class="ub ub-ver ub-f1 ut-s t-left  mar-r0125" >'
				+'<span class="info-span ut-s ub-f1 filespan mar-r0125 mar-t0125 userStyle" ng-repeat="user in users"  ng-click="displayDetail(user)">'
				+'<span ontouchstart="zy_touch()" ontouchstart="zy_touch(\'c-gra\') class="umar-r05 no-break ut-s">'
				+'{{user.un}}</span>'
				+'</span></div>'
				+'<div class="h256 w3" ontouchstart="zy_touch(\'\')" ng-click="chooseUser()" ><div class="filec ub f-r"  ng-class="{uhide:readonly}" ><div class="icon-plus5 ub-f1 center mar-t05"></div></div></div>',
		    
					
		replace: true
	};
})
.directive('tabs', function() {
    return {
        restrict: 'E',
        transclude: true,
        scope: {},
        controller: function($scope,$element) {
        	var panes = $scope.panes = [];
			
			$scope.select = function(pane){
				if(!pane){
					if(!panes||panes.length==0)return;
					angular.forEach(panes,function(p){
						if(p.show){
							pane = p;
						}
					});
				}
				else{
					angular.forEach(panes,function(pane){
						pane.selected = false;
					});
					pane.selected = true;
				}
				var callfun = pane.callfun;
				if(callfun){
					eval(callfun);
				}
				createContent(pane);
			}
			createContent = function(pane){
				var param = window.location.search;
				url = pane.url;
				setTimeout(function(){
					HT.openPopFrame(url,null,param);
					uexWindow.bringPopoverToFront(url);
				},500);
			}
			
			this.select = function(){
				$scope.select();
			}
			
			this.addPane = function(pane){
				if(pane.show){
					angular.forEach(panes,function(pane){
						pane.selected = false;
					});
					pane.selected = true;
				}
				panes.push(pane);
			}
        },
		template:'<div class="ub ub-pc ub-f1 nav-content" style="height: 100%;">'
				 +'<div ng-repeat="pane in panes" ontouchstart="zy_touch()"  class="ub-f1 tabs " ng-class="{ubb1:pane.selected,ubb2:!pane.selected,\'t-51aeee\':pane.selected}" ng-click="select(pane)">'
				 +'<span class="center">{{pane.title}}</span>'
				 +'</div><div class="uhide" ng-transclude></div>'
				 +'</div>',
        replace: true
    };
})
.directive('pane', function() {
    return {
		require:'^tabs',
        restrict: 'E',
        transclude: true,
        scope: {
			title:'@',
			url:'@',
			show:'@',
			noCotent:"@",
			callfun:"@"
		},
        link:function(scope,element,attrs,tabsCtrl){
			tabsCtrl.addPane(scope);
            if (!attrs.nocotent) {
				var onloadedHandler =  window.uexOnload;
                window.uexOnload = function(type){
					if(onloadedHandler)onloadedHandler(type);
                    if (!type) {
                        tabsCtrl.select();
                    }
                }
            }
			
		},
		template: '<div class="uhide" ng-transclude></div>',
        replace: true
    };
})

/**
 * 表单展示html
 */
.directive('formHtmlBind', function($compile) {
    return function(scope,elm,attrs){
    	scope.$watch(attrs.formHtmlBind,function(newVal,oldVal){
    		if(newVal&&newVal!==oldVal){
    			elm.html(newVal);
    			$compile(elm.parent().contents())(scope);
//				__scxmainwindow=HT.scrollH('mainWindow');
    		}
    	});
    };
})
/**
 * 权限控制
 * 例子：permission-bind这个属性 
 * <li class="ub-f1 ulev-app1 t-888 t-right umar-l1 mar-t-03">
 *			 <input controltype="input"  type="text" permission-bind="permission.field.test1" ng-model="main.test1"  name="test1"  validate="{}" class="uc-a1 mr42">  
 *	</li>
 */
.directive('permissionBind', function($compile) {
	return {
		priority:5,
		link:function(scope,elm,attrs){
	    	scope.$watch(attrs.permissionBind,function(newVal,oldVal){
	    		if(HT.isEmpty(newVal))//如果值为空返回
	    			return;
	    		var	ngModel=attrs.ngModel,
	    			scopeVal = '',
	    			formtype =attrs.formtype;
	    		try{
					if(attrs.controltype=="select"){
						scopeVal=HT.getSelectedOption(elm);
					}else{
						scopeVal= eval('( scope.'+ngModel + ')')||'';
					}
				} catch (e) {
					scopeVal = "";
				}
				if(HT.isEmpty(formtype) || formtype == 'field'){//字段权限
					scope.fieldPermission(newVal,elm,scopeVal);
				}else if(formtype == 'subTable'){//子表权限
					scope.subTablePermission(newVal,elm,scopeVal);
				}else if(formtype == 'opinion'){//意见权限
					scope.opinionPermission(newVal,elm,scopeVal);
				}	
	    	});
	    	/**
	    	 * 字段权限
	    	 */
	    	scope.fieldPermission = function(val,elm,scopeVal){
	    		switch (val) {
					case 'b': //必填
						//让字段必填
						break;
					case 'r': //只读
			  			var parent=elm.parent().parent();
						var type=elm.attr("type");
						if(elm.attr("selector")=="selector"||elm.attr("selector")=="file"){
							elm.children().find("div").eq(1).remove();
						}else if(type=="checkbox"||type=="radio"){
							elm.parent().parent().parent().attr("onclick",""); 
							if(type=="checkbox"&&scopeVal&&HT.checkInArr(scopeVal.split(","),elm.val())){
								 elm.next().find("div").eq(1).removeClass("icon-checkbox").addClass("icon-disable-checkbox");
							}else if(type=="checkbox"){
								elm.next().find("div").eq(1).removeClass("icon-checkbox").addClass("icon-checkbox-eee");
							}else if(scopeVal&&scopeVal==elm.val()){
							  elm.next().find("div").eq(1).removeClass("icon-radio").addClass("icon-disable-radio");
							  }
						}else{
							elm.parent().remove();
							parent.html("<span class='left'>"+scopeVal+"</span>")
						}
		    			
						break;
					case 'rp': //只读提交
		    			var parent = elm.parent();
		    			//然后加个隐藏
		    			parent.addClass('uhide');
		    			//设置
		    			parent.parent().append(scopeVal);
						break;
					default://编辑没修改（w）
						break;
				}
	    	};
	    	/**
	    	 * 子表权限
	    	 */
	    	scope.subTablePermission = function(val,elm,scopeVal){
	      		switch (val) {
	      			case 'b': //必填
	      				//让子表必填
	      				break;
	      			case 'r': //只读
	      				elm.children().find("li").removeClass("icon-plus-square");
						scope.$$childTail.isOnlyRead=true;
//						elm.find("tr").attr("ng-click","");
//						elm.children().attr("ng-click","");
						break;
	    			default://编辑没修改（w）
						break;
	      		};
		    	/**
		    	 * 意见权限
		    	 */
		    	scope.opinionPermission = function(val,elm,scopeVal){
		      		switch (val) {
		      			case 'b': //必填
		      				//让字段必填
		      				break;
		      			case 'r': //只读
				  			var parent=elm.parent().parent();
			    			elm.parent().remove();
			    			parent.text(scopeVal);
							break;
		    			default://编辑没修改（w）
							break;
		      		}
		    	}
	    	}
		}
	};
})
/**
 * 表单验证
 */
.directive('validate', function($compile) {
	return {
		require:'?ngModel',
		priority:6,
		link:function(scope,elm,attrs,ctrl){	
			if(!ctrl) return ;	
			scope._validRules = function(conf,me){
				var  _valid = true,
					rules = conf.rules,//规则json
					ruleName = conf.ruleName,// 规则名称
					validValue = conf.validValue,//验证的值
					value =conf.value;//实际的值
				for (var m = 0; m < rules.length; m++) {
					// 取得验证规则。
					var rule = rules[m];
					if (ruleName.toLowerCase() != rule.name.toLowerCase()) continue;
					// 验证规则如下：
					// email:true,url:true. 或者数组类型，
					//验证规则是boolean类型
					if (typeof validValue  === "boolean") 
						_valid = (!rule.rule(value) && validValue == true) ? false:true; 	
					else 
						_valid = rule.rule(value, validValue);
					
					if (!_valid) //验证不通过返回消息
						return me._format(rule.msg, validValue);
				}
				return "";
			};
			/**
			 * 消息格式化
			 **/
			scope._format = function(msg,args){
				//boolean类型的直接返回
				if (typeof args === 'boolean') 
					return  msg;
				if (!angular.isArray(args)) //不是数组类型的
					args = [args];
				//数组类型的
				angular.forEach(args,function(val, key) {
					msg = msg.replace(RegExp("\\{" + key + "\\}", "g"), val)
				});
				return msg;		
			};
			
			scope._rules = [{
				name : "required",
				rule : function(v) {
		          if( v === null || v === undefined ||  v === '')
		        	  return false;
		          if( v.length == 0)
		        	  return false;
		          return true;
				},
				msg : $i18n$.RULES.REQUIRED
			}, {
				name : "number",
				rule : function(v) {
					return /^((\d{1,3}(,\d{3})+?|\d+)(\.\d{1,5})?)$/
							.test(v.trim());
				},
				msg : $i18n$.RULES.ENTER_A_REGAL_NUMBER
			}, {
				name : "variable",
				rule : function(v) {
					return /^[A-Za-z_0-9]*$/gi.test(v.trim());
				},
				msg : $i18n$.RULES.ONLY_LETTER_AND_UNDERLINE
			}, {
				name : "fields",
				rule : function(v){
					return /^[A-Za-z]{1}([a-zA-Z0-9_]{1,17})?$/gi.test(v.trim());
				},
				msg : $i18n$.RULES.LETTER_FIRST_AND_MAX_18
			},{
				name : "minLength",
				rule : function(v, b) {
					return (v.length >= b);
				},
				msg : $i18n$.RULES.MIN_LENGTH
			}, {
				name : "maxLength",
				rule : function(v, b) {
					return (v.trim().length <= b);
				},
				msg : $i18n$.RULES.MAX_LENGTH
			}, {
				name : "rangeLength",
				rule : function(v, args) {
					return (v.trim().length >= args[0] && v.trim().length <= args[1]);
				},
				msg : $i18n$.RULES.RANGE_LENGTH
			}, {
				name : "email",
				rule : function(v) {
					return /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))$/i
							.test(v.trim());
				},
				msg : $i18n$.RULES.ENTER_A_REGAL_EMAIL
			}, {
				name : "url",
				rule : function(v) {
					return /^(https?|ftp):\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(\#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/i
							.test(v.trim());
				},
				msg : $i18n$.RULES.ENTER_A_REGAL_URL
			}, {
				name : "date",
				rule : function(v) {
					var re = /^[\d]{4}-[\d]{1,2}-[\d]{1,2}\s*[\d]{1,2}:[\d]{1,2}:[\d]{1,2}|[\d]{4}-[\d]{1,2}-[\d]{1,2}|[\d]{1,2}:[\d]{1,2}:[\d]{1,2}$/g
							.test(v.trim());
					return re;
				},
				msg : $i18n$.RULES.ENTER_A_REGAL_DATE
			}, {
				name : "digits",
				rule : function(v) {
					return /^\d+$/.test(v.trim());
				},
				msg : $i18n$.RULES.ENTER_DIGITS
			}, {
				name : "equalTo",
				rule : function(v, b) {
					var a = $("#" + b).val();
					return (v.trim() == a.trim());
				},
				msg : $i18n$.RULES.NOT_EQUAL_TWICE
			}, {
				name : "range",
				rule : function(v, args) {
					return v <= args[1] && v >= args[0];
				},
				msg : $i18n$.RULES.ENTER_NUM_RANGE
			}, {
				name : "maxvalue",
				rule : function(v, max) {
					return v <= max;
				},
				msg : $i18n$.RULES.MAX_VALUE
			},{
				name : "minvalue",
				rule : function(v, min) {
					return v >= min;
				},
				msg : $i18n$.RULES.MIN_VALUE
			},{
				// 判断数字整数位
				name : "maxIntLen",
				rule : function(v, b) {
					return (v + '').split(".")[0].replaceAll(",","").length <= b;
				},
				msg : $i18n$.RULES.MAX_INT_LEN
			}, {
				// 判断数字小数位
				name : "maxDecimalLen",
				rule : function(v, b) {
					return (v + '').replace(/^[^.]*[.]*/, '').length <= b;
				},
				msg : $i18n$.RULES.MAX_DECIMAL_LEN
			}, {
				// 判断日期范围{dateRangeStart:'xx'} xx：结束时间的日期的ID
				name : "dateRangeStart",
				rule : function(v, b) {
					var end = $("#" + b).val();
					return daysBetween(v, end);
				},
				msg : $i18n$.RULES.DATE_RANGE_START
			}, {
				// 判断日期范围{dateRangeEnd:'xx'} xx：开始时间的日期的ID
				name : "dateRangeEnd",
				rule : function(v, b) {
					var start = $("#" + b).val();
					return daysBetween(start, v);
				},
				msg : $i18n$.RULES.DATE_RANGE_END
			}];
			
			/**
			 * 验证每个元素
			 * @param me
			 * @param el
			 * @param validRule
			 * @param ngModel
			 * @param viewValue
			 * @returns
			 */		
			scope._validEach = function (me,el,validRule,ngModel,viewValue){
				var value = '';
				try{
					value= eval('( me.'+ngModel + ')');
					if(value === null || value === undefined || value === '')
						value = '';
				} catch (e) {
				}
					value =viewValue?viewValue: value;
				if(!validRule)
					return '';
				
				
				// 获取json。
				var json = eval('(' + validRule + ')');	
				//必填
				var isRequired = json.required;
				// 非必填的字段且值为空 那么直接返回成功。
				if ((isRequired == false || isRequired == undefined) && value == "" )
					return '';
				for (var name in json) {
					var validValue = json[name];
					var msg = me._validRules({
						rules:me._rules,//规则json
						ruleName:name,// 规则名称
						validValue:validValue,//验证的值
						value:value//实际的值
					},me);
					if (msg != '') 
						return msg;
				}
				return '';
			} 
			/**
			 * 判断元素是否在不需要校验的范围内。
			 */
			scope._isInNotValid= function(el) {		
				var nodeName ,
					type = el.attr('type');
				if(el)
					nodeName = el[0].nodeName.toLowerCase();
					
				//判断是否隐藏
				if(type== 'checkbox' ||
						type == 'radio')//单选、复选
					el = el.parent().parent().parent().parent().parent();
				else if(nodeName && nodeName == 'select')//下拉
					el = el.parent();
			
				if(el.hasClass('uhide'))
					return true;
				//if($(obj).is(":hidden"))return true;
//				if (!this.excludes)
//					return false;
//				var scope = $(this.excludes, this);
//				var aryInput = $(
//						"input:text,input:hidden,textarea,select,input:checkbox,input:radio",
//						scope);
//				for (var i = 0, len = aryInput.length; i < len; i++) {
//					var tmp = aryInput.get(i);
//					if (obj == tmp) {
//						return true;
//					}
//				}
				return false;
			};
			/**
			 * 错误提示
			 * @param el
			 * @param msg
			 * @returns
			 */
			scope._error = function(el,msg){
				var parent = el.parent(),isdate= false,controltype=el.attr("controltype") ;
				if(el.attr('type') == 'checkbox' ||
				  el.attr('type') == 'radio'){//单选、复选
				 	parent = parent.parent().parent().parent().parent();
					if(!parent.hasClass("has-error")) {
						parent.addClass('has-error');
					}
					//日期格式是yyyy-MM-dd HH:mm:ss 有bug
				}else if(el.attr('datefmt') && (el.attr('datefmt') == 'yyyy-MM-dd HH:mm:ss'||el.attr('datefmt') == 'yyyy-MM-dd HH:mm:00'  )){
					isdate = true;
					parent = parent.parent();
					if(!parent.hasClass("has-error")) {
						parent.addClass('has-error');
					}
				}else if(controltype=="input"||controltype=="textarea"){
					if(!parent.hasClass("has-error")) {
						parent.addClass('has-error');
					}
				}else if(!el.hasClass("has-error")) {
					el.addClass('has-error');
				}
				if(parent.parent()[0].querySelectorAll(".__cvToolTip").length==0){
					var tips=angular.element('<div class="__cvToolTip " style="float:right !important;" ng-show="!!text" > <div class="__cvToolTipDIV"> <span class="__cvBorderSpan"></span> <span class="__cvBackSpan"></span> </div> <div class="__cvText"> '+msg+' </div> </div>');
					if(controltype=="textarea"||controltype=="input"||controltype=="select" || (!isdate&&(controltype=="date" ||controltype=="time")) ){
						parent.parent().append(tips);
						tips.css({"float":"right"})
					}else{
						parent.append(tips);
					}
				}
	
			};
			/**
			 * 正确提示
			 * @param el
			 * @returns
			 */
			scope._success = function(el){
				var parent = el.parent(),isdate= false,controltype=el.attr("controltype") ;
				if(el.attr('type') == 'checkbox' ||
				 	 el.attr('type') == 'radio'){//单选、复选
				 	parent = parent.parent().parent().parent();
					if (parent.parent().hasClass("has-error")) {
						parent.parent().removeClass('has-error');
					}
				}
				if(el.attr('datefmt') && (el.attr('datefmt') == 'yyyy-MM-dd HH:mm:ss'||el.attr('datefmt') == 'yyyy-MM-dd HH:mm:00'  )){
					isdate = true;
					if (parent.parent().hasClass("has-error")) {
						parent.parent().removeClass('has-error');
					}
					if(controltype=="date"&&parent.next().next('div').hasClass("__cvToolTip")){
						parent.next().next('div').remove();
					}
					if (parent.hasClass("has-error")) {
						parent.removeClass('has-error');
					}
				}
				
				if (el.hasClass("has-error")) {
					el.removeClass('has-error');
				}
				if (parent.hasClass("has-error")) {
					parent.removeClass('has-error');
				}
				if (parent.parent().hasClass("has-error")) {
					parent.parent().removeClass('has-error');
				}
				if(controltype=="dicCombo"||el.attr("selector")=="selector"||el.attr("selector")=="file" ){
					parent = el;
				}
				if((!isdate&&(controltype=="date" ||controltype=="time"))){
					parent = el.parent();
				}
				if(el.next('div').hasClass("__cvToolTip"))
					el.next('div').remove();
				if(parent.next('div').hasClass("__cvToolTip"))
					parent.next('div').remove();
				if(parent.parent().next('div').hasClass("__cvToolTip"))
					parent.parent().next('div').remove();
			
			};
			scope._handValidResult = function(me,el,validRule,ngModel,viewValue) {
				// 是否在不需要验证的范围内，在的话就不需要验证。
				if (me._isInNotValid(el)){
					me._success(el);
					return true;
				}
				var msg = me._validEach(me,el,validRule,ngModel,viewValue);
				me._success(el);
				if (msg != '') {
					me._error(el,msg);
					return false;
				} else {
					me._success(el);
					return true;
				}
			};

			/**
			 * 验证表单
			 * @param conf
			 * {
			 * 	ignoreRequired： 是否忽略必填
			 * 	element:angular的节点
			 * 	dom: dom属性
			 * 	withoutdom：
			 * }
			 * @returns
			 */
			scope.valid = function(conf){
			
				scope.ignoreRequired= conf?((conf.ignoreRequired==undefined)?false:conf.ignoreRequired):false;
				
				var _v = true,doms=[];
				
				if(conf&&conf.element){
					var el =conf.element,
						me =el.scope(),
						validate = el.attr("validate"),
						ngModel = el.attr("ng-model");
					var rtn = scope._handValidResult(me,el,validate,ngModel);
					if (!rtn)
						_v = false;
				}else{
					if(conf&&conf.dom){
						doms = conf.dom.querySelectorAll("[validate]");
					}else if(conf&&conf.withoutdom){
						doms = document.querySelectorAll("[validate]");
						for(var i=0;i<conf.withoutdom.length;i++){
							doms = HT.getDomWithSubDom(doms,conf.withoutdom[i].querySelectorAll("[validate]"));
						}
					}else{
						doms = document.querySelectorAll("[validate]");
					}
					angular.forEach(doms,function(dom){
						var el =HT.$(dom),
							me =el.scope(),
							validate =  el.attr("validate"),
							ngModel =  el.attr("ng-model");
						var rtn = scope._handValidResult(me,el,validate,ngModel);
						if (!rtn)
							_v = false;
					});
				}
				ctrl.$setValidity("validate",_v);
				return _v;
			}
			//初始调用
	    	//scope._handValidResult(scope,elm,attrs,ctrl);	
	    	//值更新监控
    	   ctrl.$parsers.unshift(function (viewValue) {
    			var valid =scope._handValidResult(scope,elm,attrs.validate,attrs.ngModel,viewValue);
    			ctrl.$setValidity("validate",valid);
				return viewValue;
    	   });
    	   //控件focus、blur监控
    	   ctrl.$focused = false;
    	   elm.bind("focus",function(event){
    		   var el = event.serElement|| event.target;
    		   scope.valid({element:angular.element(el)});
    		   scope.$apply(function(){
    			   ctrl.$focused = true;
    		   });
    	   }).bind("blur",function(event){
    		   var el = event.serElement|| event.target;
    		   scope.valid({element:angular.element(el)});
    		   scope.$apply(function(){
    			   ctrl.$focused = true;
    		   });
    	   });
		}
	}
})
/**
 * checkbox 值选中
 * @param {Object} $compile
 */
.directive('checkValueBind', function($compile) {
    return function(scope,elm,attrs){
    	scope.$watch(attrs.checkValueBind,function(newVal,oldVal){
    		if(newVal!==oldVal){
    			var name = elm.attr("name"),
    				parent = elm.parent().parent().parent(),
    				brothers = parent.find("input"),
    				valAry = newVal.split(",");
    			brothers.removeAttr("check-value-bind");
    			
    			angular.forEach(brothers,function(brother){
    				var b = angular.element(brother);
					var value = b.attr("value");
					b.attr("checked",false);
					for(var i=0,c;c=valAry[i++];){
						if(c==value){
							b.attr("checked",true);
						}
					}
				});
    			//$compile(parent.contents())(scope);
    		}
    	});
    };
})
/**
 * 日期格式化
 * @param {Object} $window
 */
.directive('ngDateFormat', function ($window) {
    return {
        require:'^ngModel',
        restrict:'A',
		priority:3,
        link:function (scope, elm, attrs, ctrl) {
            var dateFormat = attrs.ngDateFormat;
			attrs.$observe('ngDateFormat', function (newValue) {
                if (dateFormat == newValue || !ctrl.$modelValue) return;
                dateFormat = newValue;
                ctrl.$modelValue = new Date(ctrl.$setViewValue);
            });
			
            ctrl.$formatters.unshift(function (modelValue) {
            	scope = scope;
                if (!dateFormat || !modelValue) return "";
                var retVal = "";
                if(!HT.isDate(modelValue)){//判断是否是日期 TODO
                	//不是日期则把当前值转为日期
                	if(HT.isOnlyTime(modelValue)){
                		retVal =  modelValue.substring(0,modelValue.lastIndexOf(":"));
                	}else{
                     	retVal = HT.date(modelValue,dateFormat);
                	} 
                }else{
                	retVal  = modelValue.Format(dateFormat);
                }	
				ctrl.$render();
                return retVal;
            });

            ctrl.$parsers.unshift(function (viewValue) {
                scope = scope;
				var date = new Date(viewValue);
                var tempDate = date.Format("yyyy-MM-dd HH:mm:ss");
                return (date && date.getFullYear() > 1950 ) ? tempDate : "";
            });
        }
    };
})
/**
 * 监听时间改变后的格式化
 * @param {Object} $parse
 */
.directive('ngDateChange', function ($parse) {
    return {
        require:'^ngModel',
        restrict:'A',
		priority:2,
        link:function (scope, elm, attrs, ctrl) {
            var proxyExp = attrs.ngDateChange;
            var modelExp = attrs.ngModel;
            scope.$watch(proxyExp, function (nVal,oVal) {
                if (nVal != oVal)
                    $parse(modelExp).assign(scope, nVal);
            });
        }
    };
})
.directive('controltype', function($compile,$timeout){
    return {
        restrict: 'A',
        scope: {},
		priority:1,
        link:function(scope,element,attrs){
			var wrap="";
			var html="";
			switch(attrs.controltype){
				case "radio":
						element.addClass("uhide");
						if(attrs.readonly){
							element.parent().parent().parent().find("li").eq(0).addClass('t-disable');
						}else{  
							element.parent().parent().attr({
								"onclick": "zy_for_input(event)",
								"ontouchstart": "zy_touch('c-foc')"
							});
						}
		                wrap='<div class="uba_whi h22""></div>';
		                html = '<div class="ub c-m2"><div class="ub-f1 ut-s tx-l"></div><div class="icon-radio ub-img umw2 umh2"></div></div>' ;
						element.wrap(wrap).after(html);
						if(attrs.checked)element[0].checked = "checked";
				break;
				case "checkbox":
						element.addClass("uhide");
						var ul = element.parent().parent();
						ul.attr({
								"onclick": "zy_for_input(event)",
								"ontouchstart": "zy_touch('c-foc')"
							});
		                wrap='<div class="uba_whi h22""></div>';
		                html = '<div class="ub c-m2"><div class="ub-f1 ut-s tx-l"></div><div class="icon-checkbox ub-img umw2 umh2"></div></div>' ;
						element.wrap(wrap).after(html);
				break;
				case "date":
						if(element.attr("ng-date-format")) return;
						
						if(element.attr("wrap")){
							element.parent().addClass("ub-f1").addClass("time-div").addClass("w95").addClass("mar-t043").removeClass("t-right").removeClass("umar-l1").removeClass("mar-t-03");
							element.addClass("user-c-bd");
							element.parent().parent().addClass("ub-ver");
							var preli=element.parent().parent().find("li").eq(0);
							preli.addClass("ub-f1").addClass("w100");
						}
						
						var dateWrap=' <div class="ub  uc-a1 b-ddd  uinput ub-ac h22" ontouchstart="zy_touch(\'\')" onclick="HT.getDate(event)">' +
							     ' 		</div>';
						var timeWrap=' <div class="ub  uc-a1 b-ddd  uinput ub-ac h22" ontouchstart="zy_touch(\'\')" onclick="HT.getTime(event)">' +
							     ' 		</div>';
						var html ='<div class="icon-calendar2 ">'+
		                        '</div>';
						var dateFmt=attrs.datefmt;
						//时间的格式
						var formatArrs=["yyyy-MM-dd","yyyy-MM-dd HH:mm:ss","yyyy-MM-dd HH:mm:00","HH:mm:ss"]; 
						//设置默认格式为yyyy-MM-dd
						dateFmt=dateFmt?dateFmt:"yyyy-MM-dd";
						var ngModel=attrs.ngModel;
						element.attr({"ng-date-change":ngModel});
						element.addClass("ub-f1").addClass("ipt-date").css("text-align","left");
						switch(dateFmt) {
							case formatArrs[0]:
								element.wrap(dateWrap).after(html);
								element.attr("ng-date-format",formatArrs[0]);
								element.attr("ng-date-format","yyyy-MM-dd");
								break;
							case formatArrs[1]:
							case formatArrs[2]:
								var elementTemp=element.clone().attr({"controltype":"time","ng-date-format":"hh:mm","ng-date-change":ngModel});
//								(elementTemp.wrap(timeWrap).after(html).parent().addClass("umar-t")).parent().after(element.wrap(dateWrap).after(html));
								(element.wrap(dateWrap).after(html)).parent().after(elementTemp.wrap(timeWrap).after(html).parent().addClass("umar-t"));
								element.attr({"ng-date-format":formatArrs[0]});
								elementTemp.addClass("ub-f1").addClass("ipt-date").css("text-align","left");
								$compile(elementTemp)(scope.$parent);
							break;
							case formatArrs[3]:
								element.wrap(timeWrap).after(html);
								element.attr("ng-date-format","hh:mm");
								break;
						}
						$compile(element)(scope.$parent);
						
				break;
				case "select":
						wrap=' <div class="ub uba uc-a1 c-wh b-ddd tx-r h22 uof-x"></div>';
		                html ='<div class="ub-f1 "></div><div class="ubl b-ddd c-wh z-index1 umw2 ub ub-pc uc-r1 ub-ac" onclick="HT.triggerSelect();"><div class="ub-img icon-caret-down ulev1"></div></div>';
						element.wrap(wrap).addClass("select").parent().prepend(html);
				break;
				case "file":
//						element.parent().addClass("user-c-bd").addClass("ub-f1").addClass("w95").removeClass("t-right").removeClass("umar-l1").removeClass("mar-t-03");
//						var pui=element.parent().parent();
//						pui.addClass("ub-ver");
//						var preli=element.parent().parent().find("li").eq(0);
//						preli.addClass("ub-f1").addClass("w100");
//						
//						wrap=' <div class="ub  uc-a1 b-ddd uinput ub-ac ub" ontouchstart="zy_touch(\'\')" onclick="HT.openFilePicter(true,event);"></div>';
//		                html ='<div class="filec ub"><div class="icon-plus5 ub-f1 center mar-t05"></div></div>';
//						element.removeClass("mr42");
//						element.wrap(wrap).after(html);
				break;
				case "input":
							if (element.attr("wrap")) {
								element.parent().addClass("user-c-bd").addClass("ub-f1").addClass("w95").removeClass("t-right").removeClass("umar-l1").removeClass("mar-t-03");
								var pui = element.parent().parent();
								pui.addClass("ub-ver");
								var preli = element.parent().parent().find("li").eq(0);
								preli.addClass("ub-f1").addClass("w100");
							}
						wrap='  <div class="ub  uc-a1 b-ddd  uinput ub-ac h22"></div>';
						element.addClass("t-left").addClass("t-888"); 
						element.wrap(wrap);
				break;
				case "textarea":
							if (element.attr("wrap")) {
								element.parent().addClass("user-c-bd").addClass("ub-f1").addClass("w95").removeClass("t-right").removeClass("umar-l1").removeClass("mar-t-03");
								var pui = element.parent().parent();
								pui.addClass("ub-ver");
								var preli = element.parent().parent().find("li").eq(0);
								preli.addClass("ub-f1").addClass("w100");
							}
						wrap='<div class=" uc-a1 b-ddd uinput uinn4"></div>';
						element.addClass("t-888");
						element.wrap(wrap);
				break;
				case "toggle":
						wrap='<div class="toggle toggle-positive ufr" onclick="zy_for_input(event)" ontouchstart="zy_touch(\'\')"></div>';
		                html =' <div class="track"><div class="icon-check t-wh mar-t02 mar-l02 absolute"></div><div class="handle"></div><div class="icon-times2 t-wh mar-t02 mar-r02"></div></div>';
						element.wrap(wrap).after(html);
				break;
			}
		}
    };
}) 
.directive('file', function() {
    return {
        restrict: 'E',
        transclude: true,
        scope: {},
        controller: function($scope,$attrs,$element,baseService) {
        	$scope.files = [];
			$scope.ngModel=$attrs.ngModel;
            $scope.mutil = $attrs.mutil||"true";
            $scope.readonly = true;
            if($attrs.write&&$attrs.write=='true'){
            	$scope.readonly = false;
            }
            $scope.wrap = function(){
				$element.parent().addClass("user-c-bd").addClass("ub-f1").addClass("w95").removeClass("t-right").removeClass("umar-l1").removeClass("mar-t-03");
				var pui = $element.parent().parent();
				pui.addClass("ub-ver");
				var preli = $element.parent().parent().find("li").eq(0);
				preli.addClass("ub-f1").addClass("w100");
            }
			$scope.open = function(ngModel){
				HT.fileTarget.scope=$scope.$parent;
				HT.fileTarget.ngModel=ngModel;
				var html = '<div id="fileDiv" targatname="" class="ub ub-ver c-192 w100 h100" > <div class="ub-f1 ub c-wh b-radius03"> <div class="ub ub-ver ub-f1 relative" onclick="HT.multiExplorerFile();" id="__filePic" ontouchstart="zy_touch(\'\')"> <div class="ub ub-f3 ub-ver center"> <div class="ub-f1 file"> <span class="icon-folder2 center t-109 pad-t08 f-sz2"></span> </div> </div> <div class="ub ub-f1 ub-ver center mra-t-2" > <div class="ub-f1"> <span class="center t-109 ">'+$i18n$.FILE.FILES+'</span> </div> </div> </div> <div class="ub ub-ver ub-f1 relative" onclick="HT.openBrowser();" id="__photoPic" ontouchstart="zy_touch(\'\')"> <div class="ub ub-f3 ub-ver center"> <div class="ub-f1 file"> <span class="icon-pictures center t-109 pad-t08 f-sz2" ></span> </div> </div> <div class="ub ub-f1 ub-ver center mra-t-2"> <div class="ub-f1"> <span class="center t-109 ">'+$i18n$.FILE.PICTURE+'</span> </div> </div> </div> </div> </div>';
				baseService.openDialog({
	                title: $i18n$.FILE.UPLOAD,
	                html: html,
	                buttons: [{
	                    label: $i18n$.COMMON.CANCEL,
	                    callback: function(){
	                        baseService.closeDialog();
	                    }
	                }]
	            })
			}
            /**
             *         	//TODO 通过fileId获取用户信息，首先从常用联系人中获取，没有则从服务器获取
             * @param file
             * @returns
             */
			$scope.displayDetail = function(file){
				var buttons = [{
	                    label: $i18n$.COMMON.CANCEL,
	                    callback: function(){
	                        baseService.closeDialog();
	                    }
	                }]
				var showdialog = function(u, buttons, digest){
					var html = '<ul ontouchstart="zy_touch()" class="ub ubb b-ddd t-bla ub-ac lis h275"><li class="ut-s t-111 f-sz10625">文件名</li><li class="ub-f1 ulev-app1 t-888 t-right">' + file.name +
					'</li></ul><ul ontouchstart="zy_touch()" class=" ub t-bla ub-ac lis h275"><li class="ut-s t-111 f-sz10625">文件ID</li><li class="ub-f1 ulev-app1 t-888 t-right">' +
					file.id +
					'</li></ul>';
					baseService.openDialog({
						title: $i18n$.FILE.FILEINFO,
						html: html,
						digest: digest,
						buttons: buttons
					});
				}
				showdialog(file,buttons,false);
			};
			if ($attrs.wrap) 
				$scope.wrap();
        },
		
		template:'<div class="ub  uc-a1 b-ddd  ub-ac pad040" selector="file"><div id="{{flag}}" class="mar-rl0a ub ub-ac w100">'
				+'<div class="ub  ub-ver ub-f1 ut-s t-left  mar-r0125" >'
				+'<span class="info-span ut-s ub-f1 filespan mar-r0125 mar-t0125" ng-repeat="file in files" style="border: 1px solid #219a52;background-color: #2dcd70;max-width: 8em;float:left;"  ng-click="displayDetail(file)">'
				+'<span ontouchstart="zy_touch()" ontouchstart="zy_touch(\'c-gra\') class="umar-r05 no-break ut-s">'
				+'{{file.name}}</div>'
				+'</span></span>'
				+'<div class="h256 w3" ontouchstart="zy_touch(\'\')" ng-click="open(ngModel)"><div class="filec ub f-r"><div class="icon-plus5 ub-f1 center mar-t05"></div></div></div>',
        replace: true
    };
})
/**
 * 选择器值监听
 */
.directive('selector', function($compile) {
	  return {
		    restrict: 'A',
			priority:4,
	        link:function (scope, elm, attrs, ctrl) {
				//修改该方法要修改util.addSubTableCol()
				var parentScope = scope.$parent;
				if(attrs.selector=="selector"){
		        	parentScope.$watch(attrs.refid,function(newVal,oldVal){
						var ary = [];
						if(oldVal&&newVal==oldVal)return;
		        		if (newVal) {
							if (!HT.isStirng(newVal)) 
								newVal = new String(newVal);
							var ids = newVal.split(',');
							var name = 'parentScope.' + attrs.ngModel;
							var nameStr = eval('(' + name + ')');
							var uns = nameStr.split(',');
							var count = ids.length;
							for (var i = 0; i < count; i++) {
								var id = ids[i], un = uns[i];
								if(un){
									ary.push({
									"id": id,
									"un": un
								});
								}
							}
						}
		          		scope.users = ary;
		    			parentScope.valid();
		        	});
				}else if(attrs.selector=="file"){
					parentScope.$watch(attrs.ngModel,function(newVal,oldVal){
		        		if(!newVal||newVal.indexOf("￥@@￥")==-1)return;
						var jsonArr=eval(newVal.replaceAll("￥@@￥","\""));
						if(scope.files.length>0){
							scope.files=jsonArr;
						}else{
							for(var i=0;i<jsonArr.length;i++){
			          			var id = jsonArr[i].id,
			          				name = jsonArr[i].name;
			          			scope.files.push({"id":id,"name":name});
			          		}
						}
		    			parentScope.valid();
		        	});
				}
	
	        }
	    };
})
/**
 * 查询展示
 */
.directive('ngSearch', function($compile) {
	  return {
		    restrict: 'A',
		    link:function (scope, elm, attrs, ctrl) {
				scope.keywords = "" ;
				scope.reload=function(){
					scope.keywords="";
					scope.search();
				}
		    	scope.$watch(attrs.ngSearch, function(newVal, oldVal) {
						if(!HT.isEmpty(newVal))
							scope._disclick=false;
						else 
							scope._disclick=true;	
				  });	
		    	}
		    }
	})
/**
 * 监听Modal的改变状态
 */
.directive('eventModal', function($compile,$injector) {
	  return {
		    restrict: 'A',
		    link:function (scope, elm, attrs, ctrl) {
				scope.baseService = $injector.get("baseService");
				scope.$watch('baseService', function(newVal, oldVal) {
				    if (newVal !== oldVal&&!newVal.hasModal) {
							HT.addModalToParent();
				    }else{
						HT.hideModalFromParent();
					}
				  }, true);
		    	}
		    }
	})
/**
 * 监听是否点击过搜索按钮，如果有，则显示reload按钮。否则隐藏
 */
.directive('ngSearchCancel', function($compile) {
	  return {
		    restrict: 'A',
		    link:function (scope, elm, attrs, ctrl) {
		    	scope.$watch(attrs.ngSearchCancel, function(newVal, oldVal) {
						if(newVal)
							elm.removeClass("uhide");
						if(!newVal&&attrs.allownull)
							elm.addClass("uhide");
				  });	
		    	}
		    }
	})
/**
 * 当list数据为空时，设置空页面
 */
.directive('ngNullPage', function($compile) {
	  return {
		    restrict: 'A',
		    link:function (scope, elm, attrs, ctrl) {
				var text=attrs.nullText?attrs.nullText:$i18n$.COMMON.NULL_PAGE_PULL_RELOAD;
				if(attrs.isForm) text='<div style="z-index: 1000;background-color: rgba(3, 0, 0, 0.3);padding: 0.625em;color: white;margin-top: 20%;border-radius: 3%;"><div class="icon-spinner" style="display:-webkit-inline-box;"></div><span>'+$i18n$.COMMON.WATTING_MSG+'</span></div>';
				var nullpic=attrs.nullPic?attrs.nullPic:'icon-smiley';
				elm.parent().append("<div class=' pad-t30 t-gra uhide' id='ngNullPage'><div class='"+nullpic+" center f-sz3'></div><div class='center'>"+text+"</div></div>");
		    	var  ngNullPage=HT.$($$("ngNullPage"));
				scope.$watch(attrs.ngNullPage, function(newVal, oldVal) {
					if((newVal&&newVal.length==0)||(attrs.isText&&!newVal)){
						elm.addClass("uhide");
						ngNullPage.removeClass("uhide");
					}else{
						elm.removeClass("uhide");
						ngNullPage.addClass("uhide");
					}
				  });	
		    	}
		    }
	}).directive('modalDialog', function() {
    return {
        restrict: 'E',
        scope: {
            show: '='
        },
        replace: true, // Replace with the template below
        transclude: true, // we want to insert custom content inside the directive
        link: function(scope, element, attrs) {
            scope.dialogStyle = {};
            if (attrs.width) scope.dialogStyle.width = attrs.width;
            if (attrs.height) scope.dialogStyle.height = attrs.height;
            scope.hideModal = function() {
                scope.show = false;
            };
        },
        template: '<div class="ng-modal " ng-show="show">'
                  +'<div ontouchstart="zy_touch()" class="ng-modal-overlay"></div>'
                  +'<div class="ng-modal-dialog" ng-style="dialogStyle">'
                    +'<div class="ng-modal-dialog-content" ng-transclude></div>'
                  +'</div>'
                +'</div>'
    };
});
